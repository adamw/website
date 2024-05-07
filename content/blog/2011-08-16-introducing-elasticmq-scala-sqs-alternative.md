---
title: 'Introducing ElasticMQ: Scala SQS alternative'
author: Adam Warski
type: blog
date: 2011-08-16T05:22:48+00:00
url: /blog/2011/08/introducing-elasticmq-scala-sqs-alternative/
simplecatch-sidebarlayout:
  - default
dsq_thread_id:
  - 1051239615
wp-syntax-cache-content:
  - |
    a:4:{i:1;s:894:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;">val node <span style="color: #339933;">=</span> NodeBuilder.<span style="color: #006633;">createNode</span>
    val server <span style="color: #339933;">=</span> SQSRestServerFactory.<span style="color: #006633;">start</span><span style="color: #009900;">&#40;</span>node.<span style="color: #006633;">nativeClient</span>, <span style="color: #cc66cc;">8888</span>, 
                     <span style="color: #0000ff;">&quot;http://localhost:8888&quot;</span><span style="color: #009900;">&#41;</span></pre></td></tr></table><p class="theCode" style="display:none;">val node = NodeBuilder.createNode
    val server = SQSRestServerFactory.start(node.nativeClient, 8888, 
                     &quot;http://localhost:8888&quot;)</p></div>
    ";i:2;s:545:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;">server.<span style="color: #006633;">stop</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span>
    node.<span style="color: #006633;">shutdown</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span></pre></td></tr></table><p class="theCode" style="display:none;">server.stop()
    node.shutdown()</p></div>
    ";i:3;s:1196:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;">update<span style="color: #009900;">&#40;</span>messages<span style="color: #009900;">&#41;</span><span style="color: #009900;">&#40;</span>m <span style="color: #339933;">=&gt;</span>
      where<span style="color: #009900;">&#40;</span>m.<span style="color: #006633;">id</span> <span style="color: #339933;">===</span> message.<span style="color: #006633;">id</span> and m.<span style="color: #006633;">lastDelivered</span> <span style="color: #339933;">===</span> message.<span style="color: #006633;">lastDelivered</span><span style="color: #009900;">&#41;</span>
        set<span style="color: #009900;">&#40;</span>m.<span style="color: #006633;">lastDelivered</span> <span style="color: #339933;">:=</span> lastDelivered<span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span></pre></td></tr></table><p class="theCode" style="display:none;">update(messages)(m =&gt;
      where(m.id === message.id and m.lastDelivered === message.lastDelivered)
        set(m.lastDelivered := lastDelivered))</p></div>
    ";i:4;s:2334:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    6
    7
    8
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;">val handler1 <span style="color: #339933;">=</span> <span style="color: #009900;">&#40;</span>createHandler
       forMethod GET
       forPath <span style="color: #009900;">&#40;</span>root <span style="color: #339933;">/</span> <span style="color: #0000ff;">&quot;products&quot;</span> <span style="color: #339933;">/</span> <span style="color: #339933;">%</span><span style="color: #009900;">&#40;</span><span style="color: #0000ff;">&quot;name&quot;</span><span style="color: #009900;">&#41;</span> <span style="color: #339933;">/</span> <span style="color: #0000ff;">&quot;price&quot;</span><span style="color: #009900;">&#41;</span>
       requiringParameters <span style="color: #003399;">List</span><span style="color: #009900;">&#40;</span><span style="color: #0000ff;">&quot;currency&quot;</span>, <span style="color: #0000ff;">&quot;quantity&quot;</span><span style="color: #009900;">&#41;</span>
       requiringParameterValues <span style="color: #003399;">Map</span><span style="color: #009900;">&#40;</span><span style="color: #0000ff;">&quot;department&quot;</span><span style="color: #339933;">-&gt;</span><span style="color: #0000ff;">&quot;electronics&quot;</span><span style="color: #009900;">&#41;</span>
       running electronicsPriceRequestLogic<span style="color: #009900;">&#41;</span>
    &nbsp;
    val server <span style="color: #339933;">=</span> RestServer.<span style="color: #006633;">start</span><span style="color: #009900;">&#40;</span>handler1 <span style="color: #339933;">::</span> handler2 <span style="color: #339933;">::</span> … <span style="color: #339933;">::</span> Nil, <span style="color: #cc66cc;">8888</span><span style="color: #009900;">&#41;</span></pre></td></tr></table><p class="theCode" style="display:none;">val handler1 = (createHandler
       forMethod GET
       forPath (root / &quot;products&quot; / %(&quot;name&quot;) / &quot;price&quot;)
       requiringParameters List(&quot;currency&quot;, &quot;quantity&quot;)
       requiringParameterValues Map(&quot;department&quot;-&gt;&quot;electronics&quot;)
       running electronicsPriceRequestLogic)
    
    val server = RestServer.start(handler1 :: handler2 :: … :: Nil, 8888)</p></div>
    ";}
tags:
  - aws
  - cloud
  - messaging
  - java
  - scala
  - elasticmq

---
Wanting to explore Scala and some new Scala/Java libraries I started writing [ElasticMQ][1]. It is a simple message queueing system, following closely [Amazon SQS][2] semantics and exposing a REST SQS-like interface. Currently only the basic operations are implemented. ElasticMQ can be useful as an SQS replacement, e.g. for for testing SQS applications.

My aim was to make the usage as simple as possible, that&#8217;s why to create a new node and expose the REST interface all you need to do is:

<pre lang="java" line="1" escaped="true">val node = NodeBuilder.createNode
val server = SQSRestServerFactory.start(node.nativeClient, 8888, 
                 "http://localhost:8888")
</pre>

Now simply point your SQS client to `http://localhost:8888` and you should be able to create/delete queues, change the visibility timeout, send and receive messages.  
When you are done using ElasticMQ, you can shutdown the node and the server:

<pre lang="java" line="1">server.stop()
node.shutdown()
</pre>

All of the source code is available on [GitHub][1]. See the readme for instructions on how to add ElasticMQ as a dependency to your Maven or SBT project.

On the technical side, ElasticMQ uses a number of interesting things.

Firstly, there&#8217;s [Netty][3], an asynchronous, event-driven java NIO framework. I used Netty to implement the REST server. All of the requests are processed in a non-blocking way.

The messages are currently stored in an in-memory H2 database (but this can be easily changed to point e.g. to a MySQL or PostgreSQL DB), and managed using the [Squeryl][4] Scala library. Squeryl lets you write SQL queries as typesafe Scala code, e.g.:

<pre lang="java" line="1" escaped="true">update(messages)(m =&gt;
  where(m.id === message.id and m.lastDelivered === message.lastDelivered)
    set(m.lastDelivered := lastDelivered))
</pre>

For integration testing I am using the [Typica][5] library.

On the Scala side, I created a simple internal DSL for defining rest handlers. The server (which uses Netty) is generic and can be provided with any number of &#8220;rest handlers&#8221;, to which requests are dispatched. Each rest handler specifies what is the path it will respond to, what are the required parameters and what should be run in response to a request. For example:

<pre lang="java" line="1" escaped="true">val handler1 = (createHandler
   forMethod GET
   forPath (root / "products" / %("name") / "price")
   requiringParameters List("currency", "quantity")
   requiringParameterValues Map("department"-&gt;"electronics")
   running electronicsPriceRequestLogic)

val server = RestServer.start(handler1 :: handler2 :: … :: Nil, 8888)
</pre>

See the [RequestHandlerLogic][6] file for the implementation and [RequestHandlerBuilderTestSuite][7] for example usage.

In fact there&#8217;s another small DSL for defining paths. For example `(root / "a" / "b" / "c")` will only match `a/b/c`, but `(root / "a" / %("p1") / "c")` will match `a/anything/c`, with the middle component being assigned to the `p1` parameter. You can also use regex, e.g. `(root /  "a" / """[a-z0-9]+""".r / "c")` for matching path components. See the RestPath [class][8] and [test][9] for details.

Not sure where ElasticMQ will go in the future, but I can image lots of interesting possibilities. So stay tuned :).

Comments about the code [adult water slides][10], Scala style, Netty/Squeryl usage are very welcome! As well as any other ideas, improvement suggestions etc.

Adam

 [1]: https://github.com/adamw/elasticmq
 [2]: http://aws.amazon.com/sqs/
 [3]: http://www.jboss.org/netty
 [4]: http://squeryl.org/
 [5]: http://code.google.com/p/typica/
 [6]: https://github.com/adamw/elasticmq/blob/master/rest/rest-core/src/main/scala/org/elasticmq/rest/RequestHandlerLogic.scala
 [7]: https://github.com/adamw/elasticmq/blob/master/rest/rest-core/src/test/scala/org/elasticmq/rest/RequestHandlerBuilderTestSuite.scala
 [8]: https://github.com/adamw/elasticmq/blob/master/rest/rest-core/src/main/scala/org/elasticmq/rest/RestPath.scala
 [9]: https://github.com/adamw/elasticmq/blob/master/rest/rest-core/src/test/scala/org/elasticmq/rest/RestPathTestSuite.scala
 [10]: http://www.sale-inflatable.com/Wholesale-Giant-Water-Slide-Products-For-Sale-313.html
