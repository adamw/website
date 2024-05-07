---
title: 'ElasticMQ 0.7.0: long polling, non-blocking implementation using Akka and Spray'
author: Adam Warski
type: blog
date: 2013-06-06T09:02:03+00:00
url: /blog/2013/06/elasticmq-0-7-0-long-polling-non-blocking-implementation-using-akka-and-spray/
simplecatch-sidebarlayout:
  - default
dsq_thread_id:
  - 1368661260
wp-syntax-cache-content:
  - |
    a:7:{i:1;s:412:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    </pre></td><td class="code"><pre class="bash" style="font-family:monospace;"><span style="color: #c20cb9; font-weight: bold;">java</span> <span style="color: #660033;">-jar</span> elasticmq-server-0.7.0.jar</pre></td></tr></table><p class="theCode" style="display:none;">java -jar elasticmq-server-0.7.0.jar</p></div>
    ";i:2;s:853:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">case</span> <span style="color: #0000ff; font-weight: bold;">class</span> LookupQueue<span style="color: #F78811;">&#40;</span>queueName<span style="color: #000080;">:</span> String<span style="color: #F78811;">&#41;</span> <span style="color: #0000ff; font-weight: bold;">extends</span> Replyable<span style="color: #F78811;">&#91;</span>Option<span style="color: #F78811;">&#91;</span>ActorRef<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#93;</span></pre></td></tr></table><p class="theCode" style="display:none;">case class LookupQueue(queueName: String) extends Replyable[Option[ActorRef]]</p></div>
    ";i:3;s:1159:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">import</span> org.<span style="color: #000000;">elasticmq</span>.<span style="color: #000000;">actor</span>.<span style="color: #000000;">reply</span>.<span style="color: #000080;">_</span>
    <span style="color: #0000ff; font-weight: bold;">val</span> lookupFuture<span style="color: #000080;">:</span> Future<span style="color: #F78811;">&#91;</span>Option<span style="color: #F78811;">&#91;</span>ActorRef<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#93;</span> <span style="color: #000080;">=</span> queueManagerActor <span style="color: #000080;">?</span> LookupQueue<span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;q2&quot;</span><span style="color: #F78811;">&#41;</span></pre></td></tr></table><p class="theCode" style="display:none;">import org.elasticmq.actor.reply._
    val lookupFuture: Future[Option[ActorRef]] = queueManagerActor ? LookupQueue(&quot;q2&quot;)</p></div>
    ";i:4;s:2262:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">case</span> <span style="color: #0000ff; font-weight: bold;">class</span> SendMessage<span style="color: #F78811;">&#40;</span>message<span style="color: #000080;">:</span> NewMessageData<span style="color: #F78811;">&#41;</span>   <span style="color: #0000ff; font-weight: bold;">extends</span> Replyable<span style="color: #F78811;">&#91;</span>MessageData<span style="color: #F78811;">&#93;</span>
    <span style="color: #0000ff; font-weight: bold;">case</span> <span style="color: #0000ff; font-weight: bold;">class</span> ReceiveMessages<span style="color: #F78811;">&#40;</span>visibilityTimeout<span style="color: #000080;">:</span> VisibilityTimeout, count<span style="color: #000080;">:</span> Int, 
               waitForMessages<span style="color: #000080;">:</span> Option<span style="color: #F78811;">&#91;</span>Duration<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#41;</span>     <span style="color: #0000ff; font-weight: bold;">extends</span> Replyable<span style="color: #F78811;">&#91;</span>List<span style="color: #F78811;">&#91;</span>MessageData<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#93;</span>
    <span style="color: #0000ff; font-weight: bold;">case</span> <span style="color: #0000ff; font-weight: bold;">class</span> GetQueueStatistics<span style="color: #F78811;">&#40;</span>deliveryTime<span style="color: #000080;">:</span> Long<span style="color: #F78811;">&#41;</span> <span style="color: #0000ff; font-weight: bold;">extends</span> Replyable<span style="color: #F78811;">&#91;</span>QueueStatistics<span style="color: #F78811;">&#93;</span></pre></td></tr></table><p class="theCode" style="display:none;">case class SendMessage(message: NewMessageData)   extends Replyable[MessageData]
    case class ReceiveMessages(visibilityTimeout: VisibilityTimeout, count: Int, 
               waitForMessages: Option[Duration])     extends Replyable[List[MessageData]]
    case class GetQueueStatistics(deliveryTime: Long) extends Replyable[QueueStatistics]</p></div>
    ";i:5;s:1337:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    6
    7
    8
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">val</span> listQueuesDirective <span style="color: #000080;">=</span> 
      action<span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;ListQueues&quot;</span><span style="color: #F78811;">&#41;</span> <span style="color: #F78811;">&#123;</span>
        rootPath <span style="color: #F78811;">&#123;</span>
          anyParam<span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;QueueNamePrefix&quot;</span><span style="color: #000080;">?</span><span style="color: #F78811;">&#41;</span> <span style="color: #F78811;">&#123;</span> prefixOption <span style="color: #000080;">=&gt;</span>
            <span style="color: #008000; font-style: italic;">// logic</span>
          <span style="color: #F78811;">&#125;</span>
        <span style="color: #F78811;">&#125;</span>
      <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">val listQueuesDirective = 
      action(&quot;ListQueues&quot;) {
        rootPath {
          anyParam(&quot;QueueNamePrefix&quot;?) { prefixOption =&gt;
            // logic
          }
        }
      }</p></div>
    ";i:6;s:1871:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    6
    7
    8
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #008000; font-style: italic;">// Looking up the queue and deleting it are going to be called in sequence,</span>
    <span style="color: #008000; font-style: italic;">// but asynchronously, as ? returns a Future</span>
    <span style="color: #0000ff; font-weight: bold;">for</span> <span style="color: #F78811;">&#123;</span>
       queueActor <span style="color: #000080;">&lt;</span>- queueManagerActor <span style="color: #000080;">?</span> LookupQueue<span style="color: #F78811;">&#40;</span>queueName<span style="color: #F78811;">&#41;</span>
       <span style="color: #000080;">_</span> <span style="color: #000080;">&lt;</span>- queueActor <span style="color: #000080;">?</span> DeleteMessage<span style="color: #F78811;">&#40;</span>DeliveryReceipt<span style="color: #F78811;">&#40;</span>receipt<span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span>
    <span style="color: #F78811;">&#125;</span> <span style="color: #F78811;">&#123;</span>
       requestContext.<span style="color: #000000;">complete</span><span style="color: #F78811;">&#40;</span><span style="color: #F78811;">200</span>, <span style="color: #6666FF;">&quot;message deleted&quot;</span><span style="color: #F78811;">&#41;</span>
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">// Looking up the queue and deleting it are going to be called in sequence,
    // but asynchronously, as ? returns a Future
    for {
       queueActor &lt;- queueManagerActor ? LookupQueue(queueName)
       _ &lt;- queueActor ? DeleteMessage(DeliveryReceipt(receipt))
    } {
       requestContext.complete(200, &quot;message deleted&quot;)
    }</p></div>
    ";i:7;s:3920:"
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
    13
    14
    15
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;">flow <span style="color: #F78811;">&#123;</span>
      <span style="color: #0000ff; font-weight: bold;">val</span> queueActorOption <span style="color: #000080;">=</span> <span style="color: #F78811;">&#40;</span>queueManagerActor <span style="color: #000080;">?</span> LookupQueue<span style="color: #F78811;">&#40;</span>newQueueData.<span style="color: #000000;">name</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span>.<span style="color: #000000;">apply</span><span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>
      queueActorOption <span style="color: #0000ff; font-weight: bold;">match</span> <span style="color: #F78811;">&#123;</span>
        <span style="color: #0000ff; font-weight: bold;">case</span> None <span style="color: #000080;">=&gt;</span> <span style="color: #F78811;">&#123;</span>
          <span style="color: #0000ff; font-weight: bold;">val</span> createResult <span style="color: #000080;">=</span> <span style="color: #F78811;">&#40;</span>queueManagerActor <span style="color: #000080;">?</span> CreateQueue<span style="color: #F78811;">&#40;</span>newQueueData<span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span>.<span style="color: #000000;">apply</span><span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>
          createResult <span style="color: #0000ff; font-weight: bold;">match</span> <span style="color: #F78811;">&#123;</span>
            <span style="color: #0000ff; font-weight: bold;">case</span> Left<span style="color: #F78811;">&#40;</span>e<span style="color: #F78811;">&#41;</span> <span style="color: #000080;">=&gt;</span> <span style="color: #0000ff; font-weight: bold;">throw</span> <span style="color: #0000ff; font-weight: bold;">new</span> SQSException<span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;Queue already created: &quot;</span> + e.<span style="color: #000000;">message</span><span style="color: #F78811;">&#41;</span>
            <span style="color: #0000ff; font-weight: bold;">case</span> Right<span style="color: #F78811;">&#40;</span><span style="color: #000080;">_</span><span style="color: #F78811;">&#41;</span> <span style="color: #000080;">=&gt;</span> newQueueData
          <span style="color: #F78811;">&#125;</span>
        <span style="color: #F78811;">&#125;</span>
        <span style="color: #0000ff; font-weight: bold;">case</span> Some<span style="color: #F78811;">&#40;</span>queueActor<span style="color: #F78811;">&#41;</span> <span style="color: #000080;">=&gt;</span> <span style="color: #F78811;">&#123;</span>
          <span style="color: #F78811;">&#40;</span>queueActor <span style="color: #000080;">?</span> GetQueueData<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span>.<span style="color: #000000;">apply</span><span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>
        <span style="color: #F78811;">&#125;</span>
      <span style="color: #F78811;">&#125;</span>
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">flow {
      val queueActorOption = (queueManagerActor ? LookupQueue(newQueueData.name)).apply()
      queueActorOption match {
        case None =&gt; {
          val createResult = (queueManagerActor ? CreateQueue(newQueueData)).apply()
          createResult match {
            case Left(e) =&gt; throw new SQSException(&quot;Queue already created: &quot; + e.message)
            case Right(_) =&gt; newQueueData
          }
        }
        case Some(queueActor) =&gt; {
          (queueActor ? GetQueueData()).apply()
        }
      }
    }</p></div>
    ";}
tags:
  - cloud
  - aws 
  - elasticmq
  - messaging

---
[ElasticMQ 0.7.0][1], a message queueing system with an actor-based Scala and [Amazon SQS][2]-compatible interfaces, was just released. 

It is a major rewrite, using [Akka][3] actors at the core and [Spray][4] for the REST layer. So far only the core and SQS modules have been rewritten; journaling, SQL backend and replication are yet to be done.

The major client-side improvements are:

  * long polling support, which was added to SQS some time ago
  * simpler stand-alone server &#8211; just a single jar to download

With long polling, when receiving a message, you can specify an additional `MessageWaitTime` attribute. If there are no messages in the queue, instead of completing the request with an empty response, ElasticMQ will wait up to `MessageWaitTime` seconds until messages arrive. This helps both to reduce the bandwidth used (no need for very frequent requests), improve overall system performance (messages are received immediately after being sent) and to reduce SQS costs.

The stand-alone server is now a single jar. To run a local, in-memory SQS implementation (e.g. for testing an application which uses SQS), all you need to do is [download the jar file][5] and run: 

<pre lang="bash" line="1">java -jar elasticmq-server-0.7.0.jar
</pre>

This will start a server on `http://localhost:9324`. Of course the interface and port are configurable, see the [README][1] for details. As before, you can also run an embedded server from any JVM-based language.

### Implementation notes

For the curious, here&#8217;s a short description of how ElasticMQ is implemented, including the core system, REST layer, Akka Dataflow usage and long polling implementation. All the code is available on [GitHub][1].

As already mentioned, ElasticMQ is now implemented using Akka and Spray, and doesn&#8217;t contain **any blocking calls**. Everything is asynchronous.

##### Core

The core system is actor-based. There&#8217;s one main actor ([QueueManagerActor][6]), which knows what queues are currently created in the system, and gives the possibility to create and delete queues.

For communication with the actors, the [typed ask pattern][7] is used. For example, to lookup a queue (a queue is also an actor), a message is defined:

<pre lang="scala" line="1">case class LookupQueue(queueName: String) extends Replyable[Option[ActorRef]]
</pre>

Usage looks like this:

<pre lang="scala" line="1">import org.elasticmq.actor.reply._
val lookupFuture: Future[Option[ActorRef]] = queueManagerActor ? LookupQueue("q2")
</pre>

As already mentioned, each queue is an actor, and encapsulates the queue state. We can use simple mutable data structures, without any need for thread synchronisation, as the actor model takes care of that for us. There&#8217;s a number of messages which can be sent to a queue-actor, e.g.:

<pre lang="scala" line="1">case class SendMessage(message: NewMessageData)   extends Replyable[MessageData]
case class ReceiveMessages(visibilityTimeout: VisibilityTimeout, count: Int, 
           waitForMessages: Option[Duration])     extends Replyable[List[MessageData]]
case class GetQueueStatistics(deliveryTime: Long) extends Replyable[QueueStatistics]
</pre>

##### Rest layer

The SQS query/REST layer is implemented using [Spray][8], a lightweight REST/HTTP toolkit based on Akka.

Apart from a non-blocking, actor-based IO implementation, Spray also offers a powerful routing library, `spray-routing`. It contains a number of built-in directives, for matching on the request method (get/post etc.), extracting query of form parameters or matching on the request path. But it also lets you define your own directives, using simple directive composition. A typical ElasticMQ route looks like this:

<pre lang="scala" line="1">val listQueuesDirective = 
  action("ListQueues") {
    rootPath {
      anyParam("QueueNamePrefix"?) { prefixOption =>
        // logic
      }
    }
  }
</pre>

Where `action` matches on the action name specified in the `"Action"` URL of body parameter and accepts/rejects the request, `rootPath` matches on an empty path and so on. Spray has a [good tutorial][9], so I encourage you to take a look there, if you are interested.

How to use the queue actors from the routes to complete HTTP requests?

The nice thing about Spray is that all it does is passing a `RequestContext` instance to your routes, expecting nothing in return. It is up to the route to discard the request completely or complete it with a value. The request may also be completed in another thread &#8211; or, for example, when some future is completed. Which is exactly what ElasticMQ does. Here `map`, `flatMap` and `for-comprehensions` (which are a nicer syntax for `map`/`flatMap`) are very handy, e.g. (simplified):

<pre lang="scala" line="1">// Looking up the queue and deleting it are going to be called in sequence,
// but asynchronously, as ? returns a Future
for {
   queueActor &lt;- queueManagerActor ? LookupQueue(queueName)
   _ &lt;- queueActor ? DeleteMessage(DeliveryReceipt(receipt))
} {
   requestContext.complete(200, "message deleted")
}
</pre>

Sometimes, when the flow is more complex, ElasticMQ uses [Akka Dataflow][10], which requires the continuations plugin to be enabled. There's also a similar project which uses macros, [Scala Async][11], but it's in early development.

Using Akka Dataflow, you can write code which uses `Future`s as if it was normal sequential code. The CPS plugin will transform it to use callbacks where needed. An example, taken from [CreateQueueDirectives][12]:

<pre lang="scala" line="1">flow {
  val queueActorOption = (queueManagerActor ? LookupQueue(newQueueData.name)).apply()
  queueActorOption match {
    case None => {
      val createResult = (queueManagerActor ? CreateQueue(newQueueData)).apply()
      createResult match {
        case Left(e) => throw new SQSException("Queue already created: " + e.message)
        case Right(_) => newQueueData
      }
    }
    case Some(queueActor) => {
      (queueActor ? GetQueueData()).apply()
    }
  }
}
</pre>

The important parts here are the `flow` block, which delimits the scope of the transformation, and the `apply()` calls on `Future`s which extract the content of the future. This looks like completely normal, sequential code, but when executed, since the first `Future` usage will be run asynchronously.

##### Long polling

With all of the code being asynchronous and non-blocking, implementing long polling was quite easy. Note that when receiving messages from a queue, we get a `Future[List[MessageData]]`. In response to completing this future, the HTTP request is also completed with the appropriate response. However this future may be completed almost immediately (as is the case normally), or after e.g. 10 seconds - there's no changes in code needed to support that. So the only thing to do was to delay completing the future until the specified amount of time passed or new messages have arrived.

The implementation is in [QueueActorWaitForMessagesOps][13]. When a request to receive messages arrives, and there's nothing in the queue, instead of replying (that is, sending an empty list to the sender actor) immediately, we store the reference to the original request and the sender actor in a map. Using the Akka scheduler, we also schedule sending back an empty list and removal of the entry after the specified timeout.

When new messages arrive, we simply take a waiting request from the map and try to complete it. Again, all synchronisation and concurrency problems are handled by Akka and the actor model.

Please test the new release, and let me know if you have any feedback!

Adam

 [1]: http://elasticmq.org
 [2]: http://aws.amazon.com/sqs/
 [3]: http://akka.io/
 [4]: http://spray.io/
 [5]: https://s3-eu-west-1.amazonaws.com/softwaremill-public/elasticmq-server-0.7.0.jar
 [6]: https://github.com/adamw/elasticmq/blob/892332e9f0c1c4226b37d6d780062ee0c2875bfa/core/src/main/scala/org/elasticmq/actor/QueueManagerActor.scala
 [7]: http://www.warski.org/blog/2013/05/typed-ask-for-akka/ "Typed ask for Akka"
 [8]: http://spray.io
 [9]: http://spray.io/documentation/spray-routing/
 [10]: http://doc.akka.io/docs/akka/2.1.4/scala/dataflow.html
 [11]: https://github.com/scala/async
 [12]: https://github.com/adamw/elasticmq/blob/892332e9f0c1c4226b37d6d780062ee0c2875bfa/rest/rest-sqs/src/main/scala/org/elasticmq/rest/sqs/CreateQueueDirectives.scala
 [13]: https://github.com/adamw/elasticmq/blob/892332e9f0c1c4226b37d6d780062ee0c2875bfa/core/src/main/scala/org/elasticmq/actor/queue/QueueActorWaitForMessagesOps.scala
