---
title: Reactive Queue with Akka Reactive Streams
author: Adam Warski
type: blog
date: 2014-06-10T12:32:54+00:00
url: /blog/2014/06/reactive-queue-with-akka-reactive-streams/
simplecatch-sidebarlayout:
  - default
dsq_thread_id:
  - 2752406333
wp-syntax-cache-content:
  - |
    a:4:{i:1;s:2302:"
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
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">implicit</span> <span style="color: #0000ff; font-weight: bold;">val</span> materializer <span style="color: #000080;">=</span> FlowMaterializer<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>
    &nbsp;
    <span style="color: #008000; font-style: italic;">// sender:</span>
    <span style="color: #0000ff; font-weight: bold;">val</span> serverFlow <span style="color: #000080;">=</span> StreamTcp<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>.<span style="color: #000000;">outgoingConnection</span><span style="color: #F78811;">&#40;</span>sendServerAddress<span style="color: #F78811;">&#41;</span>.<span style="color: #000000;">flow</span>
    &nbsp;
    <span style="color: #008000; font-style: italic;">// serverFlow is of type Flow[ByteString, ByteString] and can be used on the</span>
    <span style="color: #008000; font-style: italic;">// client to build a flow graph including server-side processing</span>
    &nbsp;
    <span style="color: #008000; font-style: italic;">// broker:</span>
    StreamTcp<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>.<span style="color: #000000;">bind</span><span style="color: #F78811;">&#40;</span>sendServerAddress<span style="color: #F78811;">&#41;</span>.<span style="color: #000000;">connections</span>.<span style="color: #000000;">foreach</span> <span style="color: #F78811;">&#123;</span> conn <span style="color: #000080;">=&gt;</span>
        <span style="color: #008000; font-style: italic;">// per-connection logic</span>
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">implicit val materializer = FlowMaterializer()
    
    // sender:
    val serverFlow = StreamTcp().outgoingConnection(sendServerAddress).flow
    
    // serverFlow is of type Flow[ByteString, ByteString] and can be used on the
    // client to build a flow graph including server-side processing
    
    // broker:
    StreamTcp().bind(sendServerAddress).connections.foreach { conn =&gt;
        // per-connection logic
    }</p></div>
    ";i:2;s:3167:"
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
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">val</span> source <span style="color: #000080;">=</span> 
      Source<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">1</span>.<span style="color: #000000;">second</span>, <span style="color: #F78811;">1</span>.<span style="color: #000000;">second</span>, <span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span> <span style="color: #000080;">=&gt;</span> <span style="color: #F78811;">&#123;</span> idx +<span style="color: #000080;">=</span> <span style="color: #F78811;">1</span><span style="color: #000080;">;</span> s<span style="color: #6666FF;">&quot;Message $idx from $senderName&quot;</span> <span style="color: #F78811;">&#125;</span><span style="color: #F78811;">&#41;</span>
      .<span style="color: #000000;">map</span> <span style="color: #F78811;">&#123;</span> msg <span style="color: #000080;">=&gt;</span>
        logger.<span style="color: #000000;">debug</span><span style="color: #F78811;">&#40;</span>s<span style="color: #6666FF;">&quot;Sender: sending $msg&quot;</span><span style="color: #F78811;">&#41;</span>
        createFrame<span style="color: #F78811;">&#40;</span>msg<span style="color: #F78811;">&#41;</span>
      <span style="color: #F78811;">&#125;</span>
    <span style="color: #0000ff; font-weight: bold;">val</span> sink <span style="color: #000080;">=</span> OnCompleteSink<span style="color: #F78811;">&#91;</span>ByteString<span style="color: #F78811;">&#93;</span> <span style="color: #F78811;">&#123;</span> t <span style="color: #000080;">=&gt;</span> logger.<span style="color: #000000;">info</span><span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;Stream completed&quot;</span><span style="color: #F78811;">&#41;</span><span style="color: #000080;">;</span> <span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span> <span style="color: #F78811;">&#125;</span>
    &nbsp;
    <span style="color: #0000ff; font-weight: bold;">val</span> completeFlow <span style="color: #000080;">=</span> source.<span style="color: #000000;">via</span><span style="color: #F78811;">&#40;</span>serverConnection.<span style="color: #000000;">flow</span><span style="color: #F78811;">&#41;</span>.<span style="color: #000000;">to</span><span style="color: #F78811;">&#40;</span>sink<span style="color: #F78811;">&#41;</span>
    completeFlow.<span style="color: #000000;">run</span><span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span></pre></td></tr></table><p class="theCode" style="display:none;">val source = 
      Source(1.second, 1.second, () =&gt; { idx += 1; s&quot;Message $idx from $senderName&quot; })
      .map { msg =&gt;
        logger.debug(s&quot;Sender: sending $msg&quot;)
        createFrame(msg)
      }
    val sink = OnCompleteSink[ByteString] { t =&gt; logger.info(&quot;Stream completed&quot;); () }
    
    val completeFlow = source.via(serverConnection.flow).to(sink)
    completeFlow.run()</p></div>
    ";i:3;s:3868:"
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
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;">StreamTcp<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>.<span style="color: #000000;">bind</span><span style="color: #F78811;">&#40;</span>sendServerAddress<span style="color: #F78811;">&#41;</span>.<span style="color: #000000;">connections</span>.<span style="color: #000000;">foreach</span> <span style="color: #F78811;">&#123;</span> conn <span style="color: #000080;">=&gt;</span>
      logger.<span style="color: #000000;">info</span><span style="color: #F78811;">&#40;</span>s<span style="color: #6666FF;">&quot;Broker: send client connected (${conn.remoteAddress})&quot;</span><span style="color: #F78811;">&#41;</span>
    &nbsp;
      <span style="color: #0000ff; font-weight: bold;">val</span> sendToQueueSubscriber <span style="color: #000080;">=</span> ActorSubscriber<span style="color: #F78811;">&#91;</span>String<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#40;</span>
        system.<span style="color: #000000;">actorOf</span><span style="color: #F78811;">&#40;</span>Props<span style="color: #F78811;">&#40;</span><span style="color: #0000ff; font-weight: bold;">new</span> SendToQueueSubscriber<span style="color: #F78811;">&#40;</span>queueActor<span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span>
    &nbsp;
      <span style="color: #008000; font-style: italic;">// sending messages to the queue, receiving from the client</span>
      <span style="color: #0000ff; font-weight: bold;">val</span> reconcileFrames <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> ReconcileFrames<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>
    &nbsp;
      <span style="color: #0000ff; font-weight: bold;">val</span> sendSink <span style="color: #000080;">=</span> Flow<span style="color: #F78811;">&#91;</span>ByteString<span style="color: #F78811;">&#93;</span>
        .<span style="color: #000000;">mapConcat</span><span style="color: #F78811;">&#40;</span>reconcileFrames.<span style="color: #000000;">apply</span><span style="color: #F78811;">&#41;</span>
        .<span style="color: #000000;">to</span><span style="color: #F78811;">&#40;</span>Sink<span style="color: #F78811;">&#40;</span>sendToQueueSubscriber<span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span>
    &nbsp;
      conn.<span style="color: #000000;">flow</span>.<span style="color: #000000;">to</span><span style="color: #F78811;">&#40;</span>sendSink<span style="color: #F78811;">&#41;</span>.<span style="color: #000000;">runWith</span><span style="color: #F78811;">&#40;</span>FutureSource<span style="color: #F78811;">&#40;</span>Promise<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>.<span style="color: #000000;">future</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span>
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">StreamTcp().bind(sendServerAddress).connections.foreach { conn =&gt;
      logger.info(s&quot;Broker: send client connected (${conn.remoteAddress})&quot;)
    
      val sendToQueueSubscriber = ActorSubscriber[String](
        system.actorOf(Props(new SendToQueueSubscriber(queueActor))))
    
      // sending messages to the queue, receiving from the client
      val reconcileFrames = new ReconcileFrames()
    
      val sendSink = Flow[ByteString]
        .mapConcat(reconcileFrames.apply)
        .to(Sink(sendToQueueSubscriber))
    
      conn.flow.to(sendSink).runWith(FutureSource(Promise().future))
    }</p></div>
    ";i:4;s:3157:"
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
    16
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">class</span> SendToQueueSubscriber<span style="color: #F78811;">&#40;</span>queueActor<span style="color: #000080;">:</span> ActorRef<span style="color: #F78811;">&#41;</span> <span style="color: #0000ff; font-weight: bold;">extends</span> ActorSubscriber <span style="color: #F78811;">&#123;</span>
    &nbsp;
      <span style="color: #0000ff; font-weight: bold;">private</span> <span style="color: #0000ff; font-weight: bold;">var</span> inFlight <span style="color: #000080;">=</span> <span style="color: #F78811;">0</span>
    &nbsp;
      <span style="color: #0000ff; font-weight: bold;">override</span> <span style="color: #0000ff; font-weight: bold;">protected</span> <span style="color: #0000ff; font-weight: bold;">def</span> requestStrategy <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> MaxInFlightRequestStrategy<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">10</span><span style="color: #F78811;">&#41;</span> <span style="color: #F78811;">&#123;</span>
        <span style="color: #0000ff; font-weight: bold;">override</span> <span style="color: #0000ff; font-weight: bold;">def</span> inFlightInternally <span style="color: #000080;">=</span> inFlight
      <span style="color: #F78811;">&#125;</span>
    &nbsp;
      <span style="color: #0000ff; font-weight: bold;">override</span> <span style="color: #0000ff; font-weight: bold;">def</span> receive <span style="color: #000080;">=</span> <span style="color: #F78811;">&#123;</span>
        <span style="color: #0000ff; font-weight: bold;">case</span> OnNext<span style="color: #F78811;">&#40;</span>msg<span style="color: #000080;">:</span> String<span style="color: #F78811;">&#41;</span> <span style="color: #000080;">=&gt;</span>
          queueActor <span style="color: #000080;">!</span> SendMessage<span style="color: #F78811;">&#40;</span>msg<span style="color: #F78811;">&#41;</span>
          inFlight +<span style="color: #000080;">=</span> <span style="color: #F78811;">1</span>
    &nbsp;
        <span style="color: #0000ff; font-weight: bold;">case</span> SentMessage<span style="color: #F78811;">&#40;</span><span style="color: #000080;">_</span><span style="color: #F78811;">&#41;</span> <span style="color: #000080;">=&gt;</span> inFlight -<span style="color: #000080;">=</span> <span style="color: #F78811;">1</span>
      <span style="color: #F78811;">&#125;</span>
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">class SendToQueueSubscriber(queueActor: ActorRef) extends ActorSubscriber {
    
      private var inFlight = 0
    
      override protected def requestStrategy = new MaxInFlightRequestStrategy(10) {
        override def inFlightInternally = inFlight
      }
    
      override def receive = {
        case OnNext(msg: String) =&gt;
          queueActor ! SendMessage(msg)
          inFlight += 1
    
        case SentMessage(_) =&gt; inFlight -= 1
      }
    }</p></div>
    ";}
tags:
  - akka
  - concurrency
  - scala

---
**Update 15/09/2014: introduced API changes from [akka-streams 0.7][1].  
Update 30/10/2014: introduced API changes from akka-streams 0.9.**  
**Update 15/12/2014: introduced API changes from akka-streams 1.0-M1.**

[Reactive streams][2] is a recently announced initiative to create a standard for asynchronous stream processing with built-in back-pressure, on the JVM. The working group is formed by companies such as Typesafe, Red Hat, Oracle, Netflix, and others.

One of the early, experimental implementations is based on [Akka][3]. [Preview version 0.3][4] includes actor publishers & subscribers, which opens up some new integration possibilities.

<a href="http://www.warski.org/blog/2014/06/reactive-queue-with-akka-reactive-streams/cascade-on-the-river/" rel="attachment wp-att-1358"><img loading="lazy" decoding="async" src="http://www.warski.org/blog/wp-content/uploads/2014/06/iStock_000040449260Large-300x198.jpg" alt="Cascade on the river" width="300" height="198" class="aligncenter size-medium wp-image-1358" srcset="https://www.warski.org/blog/wp-content/uploads/2014/06/iStock_000040449260Large-300x198.jpg 300w, https://www.warski.org/blog/wp-content/uploads/2014/06/iStock_000040449260Large-255x168.jpg 255w, https://www.warski.org/blog/wp-content/uploads/2014/06/iStock_000040449260Large-1024x678.jpg 1024w, https://www.warski.org/blog/wp-content/uploads/2014/06/iStock_000040449260Large-210x139.jpg 210w" sizes="(max-width: 300px) 100vw, 300px" /></a>

To test the new technology, I implemented a very simple [Reactive Message Queue][5]. The code is at a PoC stage, lacks error handling and such, but if used properly &#8211; works ;).

The queue is reactive, meaning that messages will be delivered to interested parties whenever there’s demand, without polling. Back-pressure is applied both when sending messages (so that senders do not overwhelm the broker), and when receiving messages (so that the broker sends only as much messages as the receivers can consume).

Let’s see how it works!

## The queue

First, the queue itself is an actor, and doesn’t know anything about (reactive) streams. The code is in the [`com.reactmq.queue`][6] package. The actor accepts the following actor-messages (the term &#8220;message&#8221; is overloaded here, so I’ll use plain &#8220;message&#8221; to mean the messages we send to and receive from the queue, and &#8220;actor-messages&#8221; to be the Scala class instances sent to actors):

  * `SendMessage(content)` &#8211; sends a message with the specified `String` content. A reply (`SentMessage(id)`) is sent back to the sender with the id of the message
  * `ReceiveMessages(count)` &#8211; signals that the sender (actor) would like to receive up to `count` messages. The count is cumulated with previously signalled demand.
  * `DeleteMessage(id)` &#8211; unsurprisingly, deletes a message

The queue implementation is a simplified version of what&#8217;s in [ElasticMQ][7]. After a message is received, if it is not deleted (acknowledged) within 10 seconds, it becomes available for receiving again.

When an actor signals demand for messages (by sending `ReceiveMessages` to the queue actor), it should expect any number of `ReceivedMessages(msgs)` actor-messages replies, containing the received data.

## Going reactive

To create and test our reactive queue, we need three applications:

  * a [`Sender`][8]
  * a central [`Broker`][9]
  * a [`Receiver`][10]

We can run any number of `Sender`s and `Receiver`s, but of course we should run only one `Broker`.

The first thing that we need to do is to connect the `Sender` with the `Broker`, and the `Receiver` with the `Broker` over a network. We can do that with the Akka reactive `StreamTCP` extension. Using a `bind` & `outgoingConnection` pair, we get a stream of connections on the binding side:

<pre lang="scala" line="1">implicit val materializer = FlowMaterializer()

// sender:
val serverFlow = StreamTcp().outgoingConnection(sendServerAddress).flow

// serverFlow is of type Flow[ByteString, ByteString] and can be used on the
// client to build a flow graph including server-side processing

// broker:
StreamTcp().bind(sendServerAddress).connections.foreach { conn =>
    // per-connection logic
}
</pre>

There’s a different address for sending and receiving messages.

## The sender

Let’s look at the per-connection logic of the [`Sender`][11] first.

<pre lang="scala" line="1">val source = 
  Source(1.second, 1.second, () => { idx += 1; s"Message $idx from $senderName" })
  .map { msg =>
    logger.debug(s"Sender: sending $msg")
    createFrame(msg)
  }
val sink = OnCompleteSink[ByteString] { t => logger.info("Stream completed"); () }

val completeFlow = source.via(serverConnection.flow).to(sink)
completeFlow.run()
</pre>

We are creating a tick-flow source which produces a new message every second (very convenient for testing). Using the `map` stream transformer, we are creating a byte-frame with the message (more on that later). Having constructed a source, and a very simple sink (which just logs a message on stream completion), we define a complete flow: from the source, through the server&#8217;s flow, back to our sink. And we now have a reactive over-the-network stream of messages, meaning that messages will be sent only when the `Broker` can accept them. Otherwise back-pressure will be applied all the way up to the tick publisher.

But that&#8217;s only a description of how our (very simple) stream should look like; it needs to be _materialized_ using the `run` method, which will provide concrete implementations of the stream transformation nodes. Currently there’s only one `FlowMaterializer`, which &#8211; again unsurprisingly &#8211; uses Akka actors under the hood, to actually create the stream and the flow; the materializer instance is implicit in e.g. the `run` method call.

<a href="http://www.warski.org/blog/2014/06/reactive-queue-with-akka-reactive-streams/reactmq-actors-2/" rel="attachment wp-att-1349"><img loading="lazy" decoding="async" src="http://www.warski.org/blog/wp-content/uploads/2014/06/reactmq-actors1-248x300.png" alt="reactmq actors" width="248" height="300" class="aligncenter size-medium wp-image-1349" srcset="https://www.warski.org/blog/wp-content/uploads/2014/06/reactmq-actors1-248x300.png 248w, https://www.warski.org/blog/wp-content/uploads/2014/06/reactmq-actors1-211x255.png 211w, https://www.warski.org/blog/wp-content/uploads/2014/06/reactmq-actors1-847x1024.png 847w, https://www.warski.org/blog/wp-content/uploads/2014/06/reactmq-actors1-173x210.png 173w, https://www.warski.org/blog/wp-content/uploads/2014/06/reactmq-actors1.png 972w" sizes="(max-width: 248px) 100vw, 248px" /></a>

## The broker: sending messages

On the other side of the network sits the [`Broker`][12]. Let’s see what happens when a message arrives.

<pre lang="scala" line="1">StreamTcp().bind(sendServerAddress).connections.foreach { conn =>
  logger.info(s"Broker: send client connected (${conn.remoteAddress})")

  val sendToQueueSubscriber = ActorSubscriber[String](
    system.actorOf(Props(new SendToQueueSubscriber(queueActor))))

  // sending messages to the queue, receiving from the client
  val reconcileFrames = new ReconcileFrames()

  val sendSink = Flow[ByteString]
    .mapConcat(reconcileFrames.apply)
    .to(Sink(sendToQueueSubscriber))

  conn.flow.to(sendSink).runWith(FutureSource(Promise().future))
}
</pre>

The `connections` returned by `bind` are a (reactive) `Source`: here we simply iterate over each connection, handling the incoming stream of bytes. We re-construct the `String` instances that were sent using our framing, and finally we direct that stream to a sink: the send-to-queue subscriber.

To connect all the pieces together, we specify that the client-side flow (`conn.flow`) should be connected to our previously created sink; on the other side, we don&#8217;t send anything, so we just provide a never-completed, no-element source.

The [`SendToQueueSubscriber`][13] is a per-connection bridge to the main queue actor. It uses the `ActorSubscriber` trait from Akka’s Reactive Streams implementation, to automatically manage the demand that should be signalled upstream. Using that trait we can create a reactive-stream-`Subscriber[_]`, backed by an actor &#8211; so a fully customisable sink.

<pre lang="scala" line="1">class SendToQueueSubscriber(queueActor: ActorRef) extends ActorSubscriber {

  private var inFlight = 0

  override protected def requestStrategy = new MaxInFlightRequestStrategy(10) {
    override def inFlightInternally = inFlight
  }

  override def receive = {
    case OnNext(msg: String) =>
      queueActor ! SendMessage(msg)
      inFlight += 1

    case SentMessage(_) => inFlight -= 1
  }
}
</pre>

What needs to be provided to an `ActorSubscriber`, is a way of measuring how many stream items are currently processed. Here, we are counting the number of messages that have been sent to the queue, but for which we have not yet received an id (so they are being processed by the queue).

The subscriber receives new messages wrapped in the `OnNext` actor-message; so `OnNext` is sent to the actor by the stream, and `SentMessage` is sent in reply to a `SendMessage` by the queue actor.

## Receiving

The receiving part is done in a similar way, though it requires some extra steps. First, if you take a look at the [`Receiver`][10], you’ll see that we are reading bytes from the input stream, re-constructing messages from frames, and sending back the ids, hence acknowledging the message. In reality, we would run some message-processing-logic between receiving a message and sending back the id.

On the [`Broker`][14] side, we create both a source and a sink for each connection.

The source is a stream of messages sent to receivers, the sink is a stream of acknowledged message ids from the receivers, which are simply transformed to sending `DeleteMessage` actor-messages to the queue actor.

As before, we need to connect all these pieces together: the source sends data via the client-side flow, to the sink.

Similarly to the subscriber, we need a per-connection receiving bridge from the queue actor, to the stream. That’s implemented in [`ReceiveFromQueuePublisher`][15]. Here we are extending the `ActorPublisher` trait, which lets you fully control the process of actually creating the messages which go into the stream.

In this actor, the `Request` actor-message is being sent by the stream, to signal demand. When there’s demand, we request messages from the queue. The queue will eventually respond with one or more `ReceivedMessages` actor-message (when there are any messages in the queue); as the number of messages will never exceed the signalled demand, we can safely call the `ActorPublisher.onNext` method, which sends the given items downstream.

## Framing

One small detail is that we need a custom framing protocol (thanks to Roland Kuhn for the [clarification][16]), as the TCP stream is just a stream of bytes, so we can get arbitrary fragments of the data, which need to be recombined later. Luckily implementing such a framing is quite simple &#8211; see the [`Framing`][17] class. Each frame consists of the size of the message, and the message itself.

## Summing up

Using [Reactive Streams][2] and the [Akka][3] implementation it is very easy to create reactive applications with end-to-end back-pressure. The queue above, while missing a lot of features and proofing, won’t allow the `Broker` to be overloaded by the `Senders`, and on the other side the `Receivers` to be overloaded by the `Broker`. And all that, without the need to actually write any of the backpressure-handling code!

_Follow-up posts_:

  * [Clustering reactmq with akka-cluster][18]
  * [Making the Reactive Queue durable with Akka Persistence][19]

 [1]: http://akka.io/news/2014/09/12/akka-streams-0.7-released.html
 [2]: http://www.reactive-streams.org/
 [3]: http://akka.io/
 [4]: https://groups.google.com/forum/#!msg/akka-user/YFg3KA8UxIg/S_76rGGzu9gJ
 [5]: https://github.com/adamw/reactmq
 [6]: https://github.com/adamw/reactmq/tree/1636d730b4dc40bedef573ce8d8293063acb8c31/src/main/scala/com/reactmq/queue
 [7]: http://elasticmq.org/
 [8]: https://github.com/adamw/reactmq/blob/1636d730b4dc40bedef573ce8d8293063acb8c31/src/main/scala/com/reactmq/Sender.scala
 [9]: https://github.com/adamw/reactmq/blob/1636d730b4dc40bedef573ce8d8293063acb8c31/src/main/scala/com/reactmq/Broker.scala
 [10]: https://github.com/adamw/reactmq/blob/1636d730b4dc40bedef573ce8d8293063acb8c31/src/main/scala/com/reactmq/Receiver.scala
 [11]: https://github.com/adamw/reactmq/blob/1636d730b4dc40bedef573ce8d8293063acb8c31/src/main/scala/com/reactmq/Sender.scala#L25-L32
 [12]: https://github.com/adamw/reactmq/blob/1636d730b4dc40bedef573ce8d8293063acb8c31/src/main/scala/com/reactmq/Broker.scala#L21-L35
 [13]: https://github.com/adamw/reactmq/blob/1636d730b4dc40bedef573ce8d8293063acb8c31/src/main/scala/com/reactmq/SendToQueueSubscriber.scala
 [14]: https://github.com/adamw/reactmq/blob/1636d730b4dc40bedef573ce8d8293063acb8c31/src/main/scala/com/reactmq/Broker.scala#L37-L55
 [15]: https://github.com/adamw/reactmq/blob/1636d730b4dc40bedef573ce8d8293063acb8c31/src/main/scala/com/reactmq/ReceiveFromQueuePublisher.scala
 [16]: https://groups.google.com/forum/#!topic/akka-user/JDvPsqiEVY4
 [17]: https://github.com/adamw/reactmq/blob/1636d730b4dc40bedef573ce8d8293063acb8c31/src/main/scala/com/reactmq/Framing.scala
 [18]: http://www.warski.org/blog/2014/11/clustering-reactmq-with-akka-cluster/
 [19]: http://www.warski.org/blog/2014/07/making-the-reactive-queue-durable-with-akka-persistence/
