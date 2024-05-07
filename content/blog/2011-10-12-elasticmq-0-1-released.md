---
title: ElasticMQ 0.1 released!
author: Adam Warski
type: post
date: 2011-10-12T10:04:08+00:00
url: /blog/2011/10/elasticmq-0-1-released/
dsq_thread_id:
  - 1062114731
wp-syntax-cache-content:
  - |
    a:1:{i:1;s:2310:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    6
    7
    8
    9
    10
    11
    12
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #008000; font-style: italic;">// First we need to create a Node</span>
    <span style="color: #0000ff; font-weight: bold;">val</span> node <span style="color: #000080;">=</span> NodeBuilder.<span style="color: #000000;">withInMemoryStorage</span><span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>.<span style="color: #000000;">build</span><span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>
    <span style="color: #008000; font-style: italic;">// Then we can expose the native client using the SQS REST interface</span>
    <span style="color: #0000ff; font-weight: bold;">val</span> server <span style="color: #000080;">=</span> SQSRestServerFactory.<span style="color: #000000;">start</span><span style="color: #F78811;">&#40;</span>node.<span style="color: #000000;">nativeClient</span>, 
                       <span style="color: #F78811;">8888</span>, 
                       <span style="color: #6666FF;">&quot;http://localhost:8888&quot;</span><span style="color: #F78811;">&#41;</span>
    &nbsp;
    <span style="color: #008000; font-style: italic;">// ... use: http://localhost:8888 exposes the full SQS interface ...</span>
    &nbsp;
    <span style="color: #008000; font-style: italic;">// Finally we need to stop the server and the node</span>
    server.<span style="color: #000000;">stop</span><span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>
    node.<span style="color: #000000;">shutdown</span><span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span></pre></td></tr></table><p class="theCode" style="display:none;">// First we need to create a Node
    val node = NodeBuilder.withInMemoryStorage().build()
    // Then we can expose the native client using the SQS REST interface
    val server = SQSRestServerFactory.start(node.nativeClient, 
                       8888, 
                       &quot;http://localhost:8888&quot;)
    
    // ... use: http://localhost:8888 exposes the full SQS interface ...
    
    // Finally we need to stop the server and the node
    server.stop()
    node.shutdown()</p></div>
    ";}
tags:
  - aws
  - cloud
  - messaging
  - java
  - scala
  - elasticmq

---
[ElasticMQ][1] is a simple messaging system, exposing an [SQS][2]-compatible REST interface. It can run using an in-memory H2 database (ideal for testing), or backed by a normal database (e.g. MySQL).

Using ElasticMQ can&#8217;t get much simpler, just include the dependency and: (the code below is Scala, but it is just as easy to use from Java)

<pre lang="scala" line="1" escaped="true">// First we need to create a Node
val node = NodeBuilder.withInMemoryStorage().build()
// Then we can expose the native client using the SQS REST interface
val server = SQSRestServerFactory.start(node.nativeClient, 
                   8888, 
                   "http://localhost:8888")

// ... use: http://localhost:8888 exposes the full SQS interface ...

// Finally we need to stop the server and the node
server.stop()
node.shutdown()
</pre>

ElasticMQ is written entirely in [Scala][3], leveraging the [Squeryl][4] library for ORM and [Netty][5] for implementing the REST server.

The 0.1 release contains support for all SQS actions, providing a fully-functional SQS replacement. A tiny fraction message attributes is still not supported (e.g. `SenderId`).

Moreover, ElasticMQ will soon see its first production usage, serving as the messaging system behind one of the services we develop. The service used to be deployed on US-based servers, and it then successfully used the US-East SQS service. However, after moving the servers to Australia, as there&#8217;s no local AWS center, the communication delays with US and Singapore SQS servers became unacceptable; hence the need for a local SQS installation.

Adam

 [1]: http://elasticmq.org
 [2]: http://aws.amazon.com/sqs/
 [3]: http://scala-lang.org
 [4]: http://squeryl.org/
 [5]: http://www.jboss.org/netty
