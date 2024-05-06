---
title: Making the Reactive Queue durable with Akka Persistence
author: Adam Warski
type: post
date: 2014-07-03T12:17:32+00:00
url: /blog/2014/07/making-the-reactive-queue-durable-with-akka-persistence/
simplecatch-sidebarlayout:
  - default
dsq_thread_id:
  - 2814616328
wp-syntax-cache-content:
  - |
    a:1:{i:1;s:1921:"
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
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">def</span> handleQueueMsg<span style="color: #000080;">:</span> Receive <span style="color: #000080;">=</span> <span style="color: #F78811;">&#123;</span>
      <span style="color: #0000ff; font-weight: bold;">case</span> SendMessage<span style="color: #F78811;">&#40;</span>content<span style="color: #F78811;">&#41;</span> <span style="color: #000080;">=&gt;</span>
        <span style="color: #0000ff; font-weight: bold;">val</span> msg <span style="color: #000080;">=</span> sendMessage<span style="color: #F78811;">&#40;</span>content<span style="color: #F78811;">&#41;</span>
        persistAsync<span style="color: #F78811;">&#40;</span>msg.<span style="color: #000000;">toMessageAdded</span><span style="color: #F78811;">&#41;</span> <span style="color: #F78811;">&#123;</span> msgAdded <span style="color: #000080;">=&gt;</span>
          sender<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span> <span style="color: #000080;">!</span> SentMessage<span style="color: #F78811;">&#40;</span>msgAdded.<span style="color: #000000;">id</span><span style="color: #F78811;">&#41;</span>
          tryReply<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>
        <span style="color: #F78811;">&#125;</span>
    &nbsp;
       <span style="color: #008000; font-style: italic;">// ...</span>
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">def handleQueueMsg: Receive = {
      case SendMessage(content) =&gt;
        val msg = sendMessage(content)
        persistAsync(msg.toMessageAdded) { msgAdded =&gt;
          sender() ! SentMessage(msgAdded.id)
          tryReply()
        }
       
       // ...
    }</p></div>
    ";}
categories:
  - Akka
  - Event streaming
  - Java
  - Mongo
  - reactive
  - Replication
  - Scala
  - SQS
  - Uncategorized

---
Some time ago I wrote how to implement a [reactive message queue with Akka Streams][1]. The queue supports streaming send and receive operations with back-pressure, but has one downside: all messages are stored in-memory, and hence in case of a restart are lost.

But this can be easily solved with the experimental `akka-persistence` module, which just [got an update in Akka 2.3.4][2].

## Queue actor refresher

To make the queue durable, we only need to change the queue actor; the reactive/streaming parts remain intact. Just as a reminder, the reactive queue consists of:

  * a single [queue actor][3], which holds an internal priority queue of messages to be delivered. The queue actor accepts actor-messages to send, receive and delete queue-messages
  * a [Broker][4], which creates the queue actor, listens for connections from senders and receivers, and creates the reactive streams when a connection is established
  * a [Sender][5], which sends messages to the queue (for testing, one message each second). Multiple senders can be started. Messages are sent only if they can be accepted (back-pressure from the broker)
  * a [Receiver][6], which receives messages from queue, as they become available and as they can be processed (back-pressure from the receiver)

<a href="http://www.warski.org/blog/2014/06/reactive-queue-with-akka-reactive-streams/reactmq-actors-2/" rel="attachment wp-att-1349"><img loading="lazy" decoding="async" src="http://www.warski.org/blog/wp-content/uploads/2014/06/reactmq-actors1-248x300.png" alt="reactmq actors" width="248" height="300" class="aligncenter size-medium wp-image-1349" srcset="https://www.warski.org/blog/wp-content/uploads/2014/06/reactmq-actors1-248x300.png 248w, https://www.warski.org/blog/wp-content/uploads/2014/06/reactmq-actors1-211x255.png 211w, https://www.warski.org/blog/wp-content/uploads/2014/06/reactmq-actors1-847x1024.png 847w, https://www.warski.org/blog/wp-content/uploads/2014/06/reactmq-actors1-173x210.png 173w, https://www.warski.org/blog/wp-content/uploads/2014/06/reactmq-actors1.png 972w" sizes="(max-width: 248px) 100vw, 248px" /></a>

## Going persistent (remaining reactive)

The changes needed are quite minimal.

First of all, the [`QueueActor`][7] needs to extend `PersistentActor`, and define two methods:

  * `receiveCommand`, which defines the “normal” behaviour when actor-messages (commands) arrive
  * `receiveRecover`, which is used during recovery only, and where replayed events are sent

But in order to recover, we first need to persist some events! This should of course be done when handling the message queue operations.

For example, when sending a message, a `MessageAdded` event is persisted using `persistAsync`:

<pre lang="scala" line="1">def handleQueueMsg: Receive = {
  case SendMessage(content) =>
    val msg = sendMessage(content)
    persistAsync(msg.toMessageAdded) { msgAdded =>
      sender() ! SentMessage(msgAdded.id)
      tryReply()
    }
   
   // ...
}
</pre>

`persistAsync` is one way of persisting events using akka-persistence. The other, `persist` (which is also the default one), buffers subsequent commands (actor-messages) until the event is persisted; this is a bit slower, but also easier to reason about and remain consistent. However in case of the message queue such behaviour isn&#8217;t necessary. The only guarantee that we need is that the message send is acknowledged only after the event is persisted; and that’s why the reply is sent in the after-persist event handler. You can read more about [`persistAsync` in the docs][8].

Similarly, events are persisted for other commands (actor-messages, see [`QueueActorReceive`][9]). Both for deletes and receives we are using `persistAsync`, as the queue aims to provide an at-least-once delivery guarantee.

The final component is the recovery handler, which is defined in [`QueueActorRecover`][10] (and then used in `QueueActor`). Recovery is quite simple: the events correspond to adding a new message, updating the “next delivery” timestamp or deleting.

The internal representation uses both a priority queue and a by-id map for efficiency, so when the events are handled during recovert we only build the map, and use the `RecoveryCompleted` special event to build the queue as well. The special event is sent by akka-persistence automatically.

And that’s all! If you now run the broker, send some messages, stop the broker, start it again, you’ll see that the messages are recovered, and indeed, they get received if a receiver is run.

The code isn’t production-ready of course. The event log is going to constantly grow, so it would certainly make sense to make use of [snapshots][8], plus delete old events/snapshots to make the storage size small and recovery fast.

## Replication

Now that the queue is durable, we can also have a **replicated persistent queue** almost for free: we simply need to use a different [journal plugin][8]! The default one relies on LevelDB and writes data to the local disk. Other implementations are available: for [Cassandra][11], [HBase][12], and [Mongo][13].

Making a simple switch of the persistence backend we can have our messages replicated across a cluster.

## Summary

With the help of two experimental Akka modules, [reactive streams][14] and [persistence][8], we have been able to implement a durable, reactive queue with a quite minimal amount of code. And that’s just the beginning, as the two technologies are only starting to mature!

If you&#8217;d like to modify/fork the code, it is [available on Github][15].

 [1]: http://www.warski.org/blog/2014/06/reactive-queue-with-akka-reactive-streams/
 [2]: http://letitcrash.com/post/90349128557/akka-2-3-4-released-major-updates-to-akka-persistence
 [3]: https://github.com/adamw/reactmq/tree/bdedce20fbb6fc0bd277922c1124a3d7777c2f96/src/main/scala/com/reactmq/queue
 [4]: https://github.com/adamw/reactmq/blob/bdedce20fbb6fc0bd277922c1124a3d7777c2f96/src/main/scala/com/reactmq/Broker.scala
 [5]: https://github.com/adamw/reactmq/blob/bdedce20fbb6fc0bd277922c1124a3d7777c2f96/src/main/scala/com/reactmq/Sender.scala
 [6]: https://github.com/adamw/reactmq/blob/bdedce20fbb6fc0bd277922c1124a3d7777c2f96/src/main/scala/com/reactmq/Receiver.scala
 [7]: https://github.com/adamw/reactmq/blob/bdedce20fbb6fc0bd277922c1124a3d7777c2f96/src/main/scala/com/reactmq/queue/QueueActor.scala
 [8]: http://doc.akka.io/docs/akka/2.3.4/scala/persistence.html
 [9]: https://github.com/adamw/reactmq/blob/bdedce20fbb6fc0bd277922c1124a3d7777c2f96/src/main/scala/com/reactmq/queue/QueueActorReceive.scala
 [10]: https://github.com/adamw/reactmq/blob/bdedce20fbb6fc0bd277922c1124a3d7777c2f96/src/main/scala/com/reactmq/queue/QueueActorRecover.scala
 [11]: https://github.com/krasserm/akka-persistence-cassandra/
 [12]: https://github.com/ktoso/akka-persistence-hbase/
 [13]: https://github.com/ddevore/akka-persistence-mongo/
 [14]: http://doc.akka.io/docs/akka-stream-and-http-experimental/0.4/
 [15]: https://github.com/adamw/reactmq
