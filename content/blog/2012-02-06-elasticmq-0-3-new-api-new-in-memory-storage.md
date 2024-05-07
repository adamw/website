---
title: 'ElasticMQ 0.3: new API, new in-memory storage'
author: Adam Warski
type: post
date: 2012-02-06T20:52:35+00:00
url: /blog/2012/02/elasticmq-0-3-new-api-new-in-memory-storage/
simplecatch-sidebarlayout:
  - default
dsq_thread_id:
  - 1052976167
wp-syntax-cache-content:
  - |
    a:2:{i:1;s:1228:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;">  <span style="color: #0000ff; font-weight: bold;">val</span> node <span style="color: #000080;">=</span> NodeBuilder.<span style="color: #000000;">withInMemoryStorage</span><span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>.<span style="color: #000000;">build</span><span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>
      <span style="color: #0000ff; font-weight: bold;">val</span> server <span style="color: #000080;">=</span> SQSRestServerFactory.<span style="color: #000000;">start</span><span style="color: #F78811;">&#40;</span>node.<span style="color: #000000;">nativeClient</span>, 
        <span style="color: #F78811;">8888</span>, <span style="color: #6666FF;">&quot;http://localhost:8888&quot;</span><span style="color: #F78811;">&#41;</span></pre></td></tr></table><p class="theCode" style="display:none;">  val node = NodeBuilder.withInMemoryStorage().build()
      val server = SQSRestServerFactory.start(node.nativeClient, 
        8888, &quot;http://localhost:8888&quot;)</p></div>
    ";i:2;s:2857:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    6
    7
    8
    9
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;">  <span style="color: #0000ff; font-weight: bold;">val</span> node <span style="color: #000080;">=</span> NodeBuilder.<span style="color: #000000;">withInMemoryStorage</span><span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>.<span style="color: #000000;">build</span><span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>
      <span style="color: #0000ff; font-weight: bold;">val</span> client <span style="color: #000080;">=</span> node.<span style="color: #000000;">nativeClient</span>
      <span style="color: #0000ff; font-weight: bold;">val</span> queue <span style="color: #000080;">=</span> client.<span style="color: #000000;">createOrLookupQueue</span><span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;queue1&quot;</span><span style="color: #F78811;">&#41;</span><span style="color: #000080;">;</span>
      queue.<span style="color: #000000;">sendMessage</span><span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;msg1&quot;</span><span style="color: #F78811;">&#41;</span>
      queue.<span style="color: #000000;">sendMessage</span><span style="color: #F78811;">&#40;</span>MessageBuilder<span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;msg2&quot;</span><span style="color: #F78811;">&#41;</span>.<span style="color: #000000;">withNextDelivery</span><span style="color: #F78811;">&#40;</span>tomorrow<span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span>
      queue.<span style="color: #000000;">receiveMessage</span><span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>.<span style="color: #000000;">map</span><span style="color: #F78811;">&#40;</span>message <span style="color: #000080;">=&gt;</span> <span style="color: #F78811;">&#123;</span>
        println<span style="color: #F78811;">&#40;</span>message.<span style="color: #000000;">content</span><span style="color: #F78811;">&#41;</span>
        message.<span style="color: #000000;">delete</span><span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>
      <span style="color: #F78811;">&#125;</span><span style="color: #F78811;">&#41;</span></pre></td></tr></table><p class="theCode" style="display:none;">  val node = NodeBuilder.withInMemoryStorage().build()
      val client = node.nativeClient
      val queue = client.createOrLookupQueue(&quot;queue1&quot;);
      queue.sendMessage(&quot;msg1&quot;)
      queue.sendMessage(MessageBuilder(&quot;msg2&quot;).withNextDelivery(tomorrow))
      queue.receiveMessage().map(message =&gt; {
        println(message.content)
        message.delete()
      })</p></div>
    ";}
categories:
  - aws
  - cloud
  - elasticmq
  - java
  - scala
  - messaging

---
[ElasticMQ][1] 0.3 just got released! ElasticMQ is a simple message queue system, which exposes both a native and an Amazon SQS-compatible interface. There are two major changes in this release.

Firstly, there&#8217;s a new all-Scala/Java in-memory message storage, significantly faster than the previous one, which simply used an in-memory H2 instance; currently the implementation is based on Java concurrent queues. The usage is still very simple, to create an ElasticMQ instance with in-memory storage just execute [christmas inflatables canada][2]:

<pre lang="scala" line="1" escaped="true">val node = NodeBuilder.withInMemoryStorage().build()
  val server = SQSRestServerFactory.start(node.nativeClient, 
    8888, "http://localhost:8888")
</pre>

To show some numbers, here are a couple of runs of the [MultiThreadPerformanceTest][3] test on my MBP (2.4GHz Core2Duo, 8GB ram; all operations executed using the native API, without the http overhead):

<pre>Storage: InMemory, number of threads: 5, number of messages: 50000
    Send took: 3 (3140), ops: 250000, ops per second: 83333
    Receive took: 5 (5057), ops: 250000, ops per second: 50000

    Storage: MySQL, number of threads: 5, number of messages: 2000
    Send took: 5 (5500), ops: 10000, ops per second: 2000
    Receive took: 74 (74269), ops: 10000, ops per second: 135

    Storage: H2, number of threads: 5, number of messages: 2000
    Send took: 0 (841), ops: 10000, ops per second: 10000
    Receive took: 30 (30388), ops: 10000, ops per second: 333
</pre>

I think the difference is pretty clear.

Secondly, the native API has seen a major rewrite. It has a much nicer, object-oriented feel, for example (all classes are thread-safe):

<pre lang="scala" line="1" escaped="true">val node = NodeBuilder.withInMemoryStorage().build()
  val client = node.nativeClient
  val queue = client.createOrLookupQueue("queue1");
  queue.sendMessage("msg1")
  queue.sendMessage(MessageBuilder("msg2").withNextDelivery(tomorrow))
  queue.receiveMessage().map(message =&gt; {
    println(message.content)
    message.delete()
  })
</pre>

See the [Client][4], [Queue][5], [QueueOperations][6], [Message][7] and [MessageOperations][8] traits for details.

I also created a [Google Group][9] in case you have any questions, ideas or problems with ElasticMQ (feedback is very welcome!).

See the [README][1] for details on how to download and use ElasticMQ in your SBT/Maven project.

Adam

 [1]: https://github.com/adamw/elasticmq
 [2]: http://www.east-inflatables.ca/Wholesale-12-b0-Christmas-Inflatable/
 [3]: https://github.com/adamw/elasticmq/blob/master/core/src/test/scala/org/elasticmq/performance/MultiThreadPerformanceTest.scala
 [4]: https://github.com/adamw/elasticmq/blob/master/api/src/main/scala/org/elasticmq/Client.scala
 [5]: https://github.com/adamw/elasticmq/blob/master/api/src/main/scala/org/elasticmq/Queue.scala
 [6]: https://github.com/adamw/elasticmq/blob/master/api/src/main/scala/org/elasticmq/QueueOperations.scala
 [7]: https://github.com/adamw/elasticmq/blob/master/api/src/main/scala/org/elasticmq/Message.scala
 [8]: https://github.com/adamw/elasticmq/blob/master/api/src/main/scala/org/elasticmq/MessageOperations.scala
 [9]: https://groups.google.com/forum/?fromgroups#!forum/elasticmq
