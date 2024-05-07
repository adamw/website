---
title: Unexpected problems with Apache and mod_rewrite under high load
author: Adam Warski
type: post
date: 2012-09-18T09:44:33+00:00
url: /blog/2012/09/unexpected-problems-with-apache-and-mod_rewrite-under-high-load/
dsq_thread_id:
  - 1053516311
wp-syntax-cache-content:
  - |
    a:5:{i:1;s:461:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;">Cannot assign requested address attempt to connect to <span style="color: #009900;">&#40;</span>...<span style="color: #009900;">&#41;</span> failed</pre></td></tr></table><p class="theCode" style="display:none;">Cannot assign requested address attempt to connect to (...) failed</p></div>
    ";i:2;s:533:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    </pre></td><td class="code"><pre class="bash" style="font-family:monospace;"><span style="color: #c20cb9; font-weight: bold;">netstat</span> <span style="color: #660033;">-p</span> tcp <span style="color: #000000; font-weight: bold;">|</span> <span style="color: #c20cb9; font-weight: bold;">wc</span> <span style="color: #660033;">-l</span></pre></td></tr></table><p class="theCode" style="display:none;">netstat -p tcp | wc -l</p></div>
    ";i:3;s:685:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    </pre></td><td class="code"><pre class="bash" style="font-family:monospace;">tcpdump src <span style="color: #7a0874; font-weight: bold;">&#91;</span>proxy <span style="color: #c20cb9; font-weight: bold;">ip</span><span style="color: #7a0874; font-weight: bold;">&#93;</span> and dst <span style="color: #7a0874; font-weight: bold;">&#91;</span>backend <span style="color: #c20cb9; font-weight: bold;">ip</span><span style="color: #7a0874; font-weight: bold;">&#93;</span></pre></td></tr></table><p class="theCode" style="display:none;">tcpdump src [proxy ip] and dst [backend ip]</p></div>
    ";i:4;s:838:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    </pre></td><td class="code"><pre class="xml" style="font-family:monospace;"><span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;VirtualHost</span> *:80<span style="color: #000000; font-weight: bold;">&gt;</span></span>
      RewriteEngine On
      ProxyPreserveHost On
      RewriteRule ^(.*)$ http://[backend ip]:8080$1 [P,L]
    <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/VirtualHost<span style="color: #000000; font-weight: bold;">&gt;</span></span></span></pre></td></tr></table><p class="theCode" style="display:none;">&lt;VirtualHost *:80&gt;
      RewriteEngine On
      ProxyPreserveHost On
      RewriteRule ^(.*)$ http://[backend ip]:8080$1 [P,L]
    &lt;/VirtualHost&gt;</p></div>
    ";i:5;s:773:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    </pre></td><td class="code"><pre class="xml" style="font-family:monospace;"><span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;VirtualHost</span> *:80<span style="color: #000000; font-weight: bold;">&gt;</span></span>
       ProxyPreserveHost On
       ProxyPass / http://[backend ip]:8080/
    <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/VirtualHost<span style="color: #000000; font-weight: bold;">&gt;</span></span></span></pre></td></tr></table><p class="theCode" style="display:none;">&lt;VirtualHost *:80&gt;
       ProxyPreserveHost On
       ProxyPass / http://[backend ip]:8080/
    &lt;/VirtualHost&gt;</p></div>
    ";}
categories:
  - distributed
  - java
  - testing

---
In one of the projects that we are currently working on we have a fairly typical setup with one server (Apache with `mod_rewrite`) proxying traffic to backend servers.

We also have some automated performance/stress tests. The whole system worked fine with around 250 requests/second hitting the server (http get/post calls). However, when we increased that to 700 requests/second, after some time we started getting 503 responses.

As it turned out, these requests never reached the backend servers, but the Apache error logs contained entries such as:

<pre lang="java" line="1">Cannot assign requested address attempt to connect to (...) failed
</pre>

Googling for a while revealed that this may be because the OS (Ubuntu in this case) wasn&#8217;t able to allocate new ports. Each TCP client-server connection gets assigned a new [ephemeral port][1] on the client side, which typically is from the range 32768 to 61000. After a request completes, even if both sides properly close the TCP connection, the port will be freed and re-useable only after about 4 minutes. That&#8217;s because the connection is put in the TIME_WAIT state, and will be discarded after the [associated timeout passes][2]. 

As a TCP client-server connection is uniquely identified by the (client IP, client port, server IP, server port) tuple, in the default setup, the server can only handle about 30k requests in 4 minutes from a single client (the server&#8217;s address is fixed and well-known, so only the client port can change).

The next step was checking if this is indeed a problem with allocating ports. To get a rough number of open connections, we simply ran during the tests:

<pre lang="bash" line="1">netstat -p tcp | wc -l
</pre>

We also added `grep`s on the client&#8217;s or backend&#8217;s IP, to get a count on the number of connections between client<->proxy and proxy<->backend. It turned out that while there is a constant pool of connections between the client and proxy (so HTTP keepalive was working properly &#8211; also see below), a new connection was established for each request between the proxy and the backend! 

So in our case the client side of the TCP connection was the proxy, the server side &#8211; the backend, and the limit on the number of connections applied to the proxy<->backend pair, even though originally the requests could have come from various clients. Hence our whole setup was limited to 30k requests per 4 minutes per backend server.

Of course the next step was to find out which side is initiating closing of the connections. For that we used:

<pre lang="bash" line="1">tcpdump src [proxy ip] and dst [backend ip]
</pre>

and directed single requests at the server. The flow clearly showed that Apache was closing the connections.

Why? That was a very good question. Our Apache+`mod_rewrite` configuration was really simple:

<pre lang="xml" line="1">&lt;VirtualHost *:80>
  RewriteEngine On
  ProxyPreserveHost On
  RewriteRule ^(.*)$ http://[backend ip]:8080$1 [P,L]
&lt;/VirtualHost>
</pre>

For a lack of other leads I [asked on ServerFault][3], which turned out to be a very good idea. I quickly got the answer that **`mod_rewrite` does not do connection pooling**. I couldn&#8217;t find any mention about this in the docs, and I think it&#8217;s pretty important, especially for systems under high load.

The solution was also very simple: use `mod_proxy` instead. Changing the above config to:

<pre lang="xml" line="1">&lt;VirtualHost *:80>
   ProxyPreserveHost On
   ProxyPass / http://[backend ip]:8080/
&lt;/VirtualHost>
</pre>

caused that our tests finally passed under the ~700 requests/second load.

As a side note, we also made sure that the test agent sending the requests (it was one machine) uses HTTP keepalive, which causes a single TCP connection to be reused for multiple HTTP requests. As it turns out, if you are using Java&#8217;s `URLConnection` this isn&#8217;t that [straighforward][4] (for simplicity we didn&#8217;t use [Apache HttpClient][5] here): you need to adjust the `http.maxConnections` system property and not use the `.connect()` or `.close()` methods on `URLConnection`. 

Adam

 [1]: http://www.ncftp.com/ncftpd//doc/misc/ephemeral_ports.html
 [2]: http://blog.davidvassallo.me/2010/07/13/time_wait-and-port-reuse/
 [3]: http://serverfault.com/questions/423821/mod-rewriteproxy-closes-connections-to-backend-keepalive
 [4]: http://stackoverflow.com/questions/1936872/how-to-keep-multiple-java-httpconnections-open-to-same-destination
 [5]: http://hc.apache.org/
