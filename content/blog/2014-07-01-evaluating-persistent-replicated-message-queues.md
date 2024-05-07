---
title: Evaluating persistent, replicated message queues (updated w/ Kafka)
author: Adam Warski
type: blog
date: 2014-07-01T12:08:01+00:00
url: /blog/2014/07/evaluating-persistent-replicated-message-queues/
simplecatch-sidebarlayout:
  - default
dsq_thread_id:
  - 2809356882
tags:
  - aws
  - cloud
  - distributed
  - messaging
  - performance
  - testing

---
> **An updated and extended version of this post [is available on SoftwareMill&#8217;s website][1]. Below you can find the original content containing benchmarks from 2014.**

**Update 17/07/2014: added [Kafka][2] benchmarks**  
**Update 20/03/2015: updated HornetQ replication description**  
**Update 4/05/2015: updated & extended version [here][1]**

Message queues are useful in a number of situations; for example when we want to execute a task asynchronously, we enqueue it and some executor eventually completes it. Depending on the use case, the queues can give various guarantees of message persistence and delivery. For some use-cases, it is enough to have an in-memory message queue. For others, we **want to be sure** that once the message send completes, it is persistently enqueued and will be eventually delivered, despite node or system crashes.

To be really sure that messages are not lost, we will be looking at queues which:

  * persist messages to disk
  * replicate messages across the network

Ideally, we want to have 3 identical, replicated nodes containing the message queue.

There is a number of open-source messaging projects available, but only a handful supports both persistence and replication. We’ll evaluate the performance and characteristics of 5 message queues:

  * [Amazon SQS][3]
  * [Mongo DB][4]
  * [RabbitMq][5]
  * [HornetQ][6]
  * [Kafka][7]

<a href="http://www.warski.org/blog/2014/07/evaluating-persistent-replicated-message-queues/mqperf_logos-2/" rel="attachment wp-att-1432"><img loading="lazy" decoding="async" src="http://www.warski.org/blog/wp-content/uploads/2014/07/mqperf_logos-300x181.jpg" alt="mqperf_logos" width="300" height="181" class="aligncenter size-medium wp-image-1432" srcset="https://www.warski.org/blog/wp-content/uploads/2014/07/mqperf_logos-300x181.jpg 300w, https://www.warski.org/blog/wp-content/uploads/2014/07/mqperf_logos-255x154.jpg 255w, https://www.warski.org/blog/wp-content/uploads/2014/07/mqperf_logos-210x127.jpg 210w, https://www.warski.org/blog/wp-content/uploads/2014/07/mqperf_logos.jpg 498w" sizes="(max-width: 300px) 100vw, 300px" /></a>

While SQS isn’t an open-source messaging system, it matches the requirements and I&#8217;ve recently [benchmarked it][8], so it will be interesting to compare self-hosted solutions with an as-a-service one.

MongoDB isn&#8217;t a queue of course, but a document-based NoSQL database, however using some of its mechanisms it is very easy to implement a message queue on top of it.

By no way this aims to be a comprehensive overview, just an evaluation of some of the projects. If you know of any other messaging systems, which provide durable, replicated queues, let me know!

## Testing methodology

All sources for the tests are [available on GitHub][9]. The tests run a variable number of nodes (1-8); each node either sends or receives messages, using a variable number of threads (1-25), depending on the concrete test setup.

Each [Sender][10] thread tries to send the given number of messages as fast as possible, in batches of random size between 1 and 10 messages. For some queues, we’ll also evaluate larger batches, up to 100 or 1000 messages. After sending all messages, the sender reports the number of messages sent per second.

The [Receiver][11] tries to receive messages (also in batches), and after receiving them, acknowledges their delivery (which should cause the message to be removed from the queue). When no messages are received for a minute, the receiver thread reports the number of messages received per second.

<a href="http://www.warski.org/blog/2014/07/evaluating-persistent-replicated-message-queues/mqtestsetup/" rel="attachment wp-att-1411"><img loading="lazy" decoding="async" src="http://www.warski.org/blog/wp-content/uploads/2014/06/mqtestsetup.png" alt="mqtestsetup" width="1684" height="1036" class="aligncenter size-full wp-image-1411" srcset="https://www.warski.org/blog/wp-content/uploads/2014/06/mqtestsetup.png 1684w, https://www.warski.org/blog/wp-content/uploads/2014/06/mqtestsetup-255x156.png 255w, https://www.warski.org/blog/wp-content/uploads/2014/06/mqtestsetup-300x184.png 300w, https://www.warski.org/blog/wp-content/uploads/2014/06/mqtestsetup-1024x629.png 1024w, https://www.warski.org/blog/wp-content/uploads/2014/06/mqtestsetup-210x129.png 210w" sizes="(max-width: 1684px) 100vw, 1684px" /></a>

The queues have to implement the [Mq][12] interface. The methods should have the following characteristics:

  * `send` should be synchronous, that is when it completes, we want to be sure (what _sure_ means exactly may vary) that the messages are sent
  * `receive` should receive messages from the queue and block them; if the node crashes, the messages should be returned to the queue and re-delivered
  * `ack` should acknowledge delivery and processing of the messages. Acknowledgments can be asynchronous, that is we don’t have to be sure that the messages really got deleted.

The model above describes an **at-least-once** message delivery model. Some queues offer also other delivery models, but we&#8217;ll focus on that one to compare possibly similar things.

We’ll be looking at how fast (in terms of throughput) we can send and receive messages using a single 2 or 3 node message queue cluster.

## Mongo

Mongo has two main features which make it possible to easily implement a durable, replicated message queue on top of it: very simple replication setup (we’ll be using a 3-node replica set), and various document-level atomic operations, like `find-and-modify`. The implementation is just a handful of lines of code; take a look at [MongoMq][13].

We are also able to control the guarantees which `send` gives us by using an appropriate write concern when writing new messages:

  * `WriteConcern.ACKNOWLEDGED` (previously `SAFE`) ensures that once a send completes, the messages have been written to disk (though it’s not a 100% durability guarantee, as the disk may have its own write caches)
  * `WriteConcern.REPLICA_ACKNOWLEDGED` ensures that a message is written to the majority of the nodes in the cluster

The main downside of the Mongo-based queue is that:

  * messages can’t be received in bulk &#8211; the `find-and-modify` operation only works on a single document at a time
  * when there’s a lot of connections trying to receive messages, the collection will encounter a lot of contention, and all operations are serialised.

And this shows in the results: sends are faster then receives. But overall the performance is quite good!

A single-thread, single-node setup achieves **7 900 msgs/s** sent and **1 900 msgs/s** received. The maximum send throughput with multiple thread/nodes that I was able to achieve is about **10 500 msgs/s**, while the maximum receive rate is **3 200 msgs/s**, when using the &#8220;safe&#8221; write concern.

<table>
  <tr>
    <th>
      Threads
    </th>
    
    <th width="25%">
      Nodes
    </th>
    
    <th width="25%">
      Send msgs/s
    </th>
    
    <th width="25%">
      Receive msgs/s
    </th>
  </tr>
  
  <tr>
    <th>
      1
    </th>
    
    <th>
      1
    </th>
    
    <td>
      7 968,60
    </td>
    
    <td>
      1 914,05
    </td>
  </tr>
  
  <tr>
    <th>
      5
    </th>
    
    <th>
      1
    </th>
    
    <td>
      9 903,47
    </td>
    
    <td>
      3 149,00
    </td>
  </tr>
  
  <tr>
    <th>
      25
    </th>
    
    <th>
      1
    </th>
    
    <td>
      <strong>10 903,00</strong>
    </td>
    
    <td>
      <strong>3 266,83</strong>
    </td>
  </tr>
  
  <tr>
    <th>
      1
    </th>
    
    <th>
      2
    </th>
    
    <td>
      9 569,99
    </td>
    
    <td>
      2 779,87
    </td>
  </tr>
  
  <tr>
    <th>
      5
    </th>
    
    <th>
      2
    </th>
    
    <td>
      10 078,65
    </td>
    
    <td>
      3 112,55
    </td>
  </tr>
  
  <tr>
    <th>
      25
    </th>
    
    <th>
      2
    </th>
    
    <td>
      7 930,50
    </td>
    
    <td>
      3 014,00
    </td>
  </tr>
</table>

If we wait for the replica to acknowledge the writes (instead of just one node), the send throughput falls to **6 500 msgs/s**, and the receive to about **2 900 msgs/s**.

<table>
  <tr>
    <th>
      Threads
    </th>
    
    <th width="25%">
      Nodes
    </th>
    
    <th width="25%">
      Send msgs/s
    </th>
    
    <th width="25%">
      Receive msgs/s
    </th>
  </tr>
  
  <tr>
    <th>
      1
    </th>
    
    <th>
      1
    </th>
    
    <td>
      1 489,21
    </td>
    
    <td>
      1 483,69
    </td>
  </tr>
  
  <tr>
    <th>
      1
    </th>
    
    <th>
      2
    </th>
    
    <td>
      2 431,27
    </td>
    
    <td>
      2 421,01
    </td>
  </tr>
  
  <tr>
    <th>
      5
    </th>
    
    <th>
      2
    </th>
    
    <td>
      6 333,10
    </td>
    
    <td>
      <strong>2 913,90</strong>
    </td>
  </tr>
  
  <tr>
    <th>
      25
    </th>
    
    <th>
      2
    </th>
    
    <td>
      <strong>6 550,00</strong>
    </td>
    
    <td>
      2 841,00
    </td>
  </tr>
</table>

In my opinion, not bad for a very straightforward queue implementation on top of Mongo.

## SQS

SQS is pretty well covered in [my previous blog][8], so here&#8217;s just a short recap.

SQS guarantees that if a send completes, the message is replicated to multiple nodes. It also provides at-least-once delivery guarantees.

We don’t really know how SQS is implemented, but it most probably spreads the load across many servers, so including it here is a bit of an unfair competition: the other systems use a single replicated cluster, while SQS can employ multiple replicated clusters and route/balance the messages between them. But since we have the results, let&#8217;s see how it compares.

A single thread on single node achieves **430 msgs/s** sent and the same number of msgs received.

These results are not impressive, but SQS scales nicely both when increasing the number of threads, and the number of nodes. On a single node, with 50 threads, we can send up to **14 500 msgs/s**, and receive up to **4 200 msgs/s**.

On an 8-node cluster, these numbers go up to **63 500 msgs/s** sent, and **34 800 msgs/s** received.

<a href="http://www.warski.org/blog/2014/06/benchmarking-sqs/sqsperf3-2/" rel="attachment wp-att-1395"><img loading="lazy" decoding="async" src="http://www.warski.org/blog/wp-content/uploads/2014/06/sqsperf31.png" alt="sqsperf3" width="876" height="452" class="aligncenter size-full wp-image-1395" srcset="https://www.warski.org/blog/wp-content/uploads/2014/06/sqsperf31.png 876w, https://www.warski.org/blog/wp-content/uploads/2014/06/sqsperf31-255x131.png 255w, https://www.warski.org/blog/wp-content/uploads/2014/06/sqsperf31-300x154.png 300w, https://www.warski.org/blog/wp-content/uploads/2014/06/sqsperf31-210x108.png 210w" sizes="(max-width: 876px) 100vw, 876px" /></a>

## RabbitMQ

RabbitMQ is one of the leading open-source messaging systems. It is written in Erlang, implements [AMQP][14] and is a very popular choice when messaging is involved. It supports both message persistence and replication, with well documented behaviour in case of e.g. [partitions][15].

We&#8217;ll be testing a 3-node Rabbit cluster. To be sure that sends complete successfully, we&#8217;ll be using [publisher confirms][16], a Rabbit extension to AMQP. The confirmations are cluster-wide, so this gives us pretty strong guarantees: that messages will be both written to disk, and replicated to the cluster (see the [docs][17]).

Such strong guarantees are probably one of the reasons for poor performance. A single-thread, single-node gives us **310 msgs/s** sent&received. This scales nicely as we add nodes, up to **1 600 msgs/s**:

<a href="http://www.warski.org/blog/2014/07/evaluating-persistent-replicated-message-queues/rabbit1/" rel="attachment wp-att-1404"><img loading="lazy" decoding="async" src="http://www.warski.org/blog/wp-content/uploads/2014/06/rabbit1.png" alt="rabbit1" width="877" height="452" class="aligncenter size-full wp-image-1404" srcset="https://www.warski.org/blog/wp-content/uploads/2014/06/rabbit1.png 877w, https://www.warski.org/blog/wp-content/uploads/2014/06/rabbit1-255x131.png 255w, https://www.warski.org/blog/wp-content/uploads/2014/06/rabbit1-300x154.png 300w, https://www.warski.org/blog/wp-content/uploads/2014/06/rabbit1-210x108.png 210w" sizes="(max-width: 877px) 100vw, 877px" /></a>

The [RabbitMq][18] implementation of the `Mq` interface is again pretty straightforward. We are using the mentioned publisher confirms, and setting the quality-of-service when receiving so that at most 10 messages are delivered unconfirmed.

Interestingly, increasing the number of threads on a node doesn&#8217;t impact the results. It may be because I&#8217;m incorrectly using the Rabbit API, or maybe it&#8217;s just the way Rabbit works. With 5 sending threads on a single node, the throughput increases just to **410 msgs/s**.

Things improve if we send messages in batches up to 100 or 1000, instead of 10. In both cases, we can get to **3 300 msgs/s** sent&received, which seems to be the maximum that Rabbit can achieve. Results for batches up to 100:

<table>
  <tr>
    <th>
      Threads
    </th>
    
    <th width="25%">
      Nodes
    </th>
    
    <th width="25%">
      Send msgs/s
    </th>
    
    <th width="25%">
      Receive msgs/s
    </th>
  </tr>
  
  <tr>
    <th>
      1
    </th>
    
    <th>
      1
    </th>
    
    <td>
      1 829,63
    </td>
    
    <td>
      1 811,14
    </td>
  </tr>
  
  <tr>
    <th>
      1
    </th>
    
    <th>
      2
    </th>
    
    <td>
      2 626,16
    </td>
    
    <td>
      2 625,85
    </td>
  </tr>
  
  <tr>
    <th>
      1
    </th>
    
    <th>
      4
    </th>
    
    <td>
      3 158,46
    </td>
    
    <td>
      3 124,92
    </td>
  </tr>
  
  <tr>
    <th>
      1
    </th>
    
    <th>
      8
    </th>
    
    <td>
      <strong>3 261,36</strong>
    </td>
    
    <td>
      <strong>3 226,40</strong>
    </td>
  </tr>
</table>

And for batches up to 1000:

<table>
  <tr>
    <th>
      Threads
    </th>
    
    <th width="25%">
      Nodes
    </th>
    
    <th width="25%">
      Send msgs/s
    </th>
    
    <th width="25%">
      Receive msgs/s
    </th>
  </tr>
  
  <tr>
    <th>
      1
    </th>
    
    <th>
      1
    </th>
    
    <td>
      3 181,08
    </td>
    
    <td>
      2 549,45
    </td>
  </tr>
  
  <tr>
    <th>
      1
    </th>
    
    <th>
      2
    </th>
    
    <td>
      3 307,10
    </td>
    
    <td>
      3 278,29
    </td>
  </tr>
  
  <tr>
    <th>
      1
    </th>
    
    <th>
      4
    </th>
    
    <td>
      <strong>3 566,72</strong>
    </td>
    
    <td>
      <strong>3 533,92</strong>
    </td>
  </tr>
  
  <tr>
    <th>
      1
    </th>
    
    <th>
      8
    </th>
    
    <td>
      3 406,72
    </td>
    
    <td>
      3 377,68
    </td>
  </tr>
</table>

## HornetQ

HornetQ, written by JBoss and part of the JBossAS (implements JMS) is a strong contender. Since some time it supports over-the-network replication using [live-backup pairs][19]. I tried setting up a 3-node cluster, but it seems that data is replicated only to one node. Hence here we will be using a two-node cluster.

This raises a question on how partitions are handled; if a node dies, the fact is detected automatically, but then we can end up with two live servers (unless we have more nodes in the cluster), and that rises the question what happens with the data on both primaries when the connection is re-established. Overall, the replication support and documentation is worse than for Mongo and Rabbit.

Although it is not clearly stated in the documentation (see [send guarantees][20]), replication is synchronous, hence when the transaction commits, we can be sure that messages are written to journals on both nodes. That is similar to Rabbit, and corresponds to Mongo&#8217;s `replica-safe` write concern.

The [HornetMq][21] implementation uses the core Hornet API. For sends, we are using transactions, for receives we rely on the internal receive buffers and turn off blocking confirmations (making them asynchronous). Interestingly, we can only receive one message at a time before acknowledging it, otherwise we get exceptions on the server. But this doesn’t seem to impact performance.

Speaking of performance, it is very good! A single-node, single-thread setup achieves **1 100 msgs/s**. With 25 threads, we are up to **12 800 msgs/s**! And finally, with 25 threads and 4 nodes, we can achieve **17 000 msgs/s**.

<table>
  <tr>
    <th>
      Threads
    </th>
    
    <th width="25%">
      Nodes
    </th>
    
    <th width="25%">
      Send msgs/s
    </th>
    
    <th width="25%">
      Receive msgs/s
    </th>
  </tr>
  
  <tr>
    <th>
      1
    </th>
    
    <th>
      1
    </th>
    
    <td>
      1 108,38
    </td>
    
    <td>
      1 106,68
    </td>
  </tr>
  
  <tr>
    <th>
      5
    </th>
    
    <th>
      1
    </th>
    
    <td>
      4 333,13
    </td>
    
    <td>
      4 318,25
    </td>
  </tr>
  
  <tr>
    <th>
      25
    </th>
    
    <th>
      1
    </th>
    
    <td>
      12 791,83
    </td>
    
    <td>
      12 802,42
    </td>
  </tr>
  
  <tr>
    <th>
      1
    </th>
    
    <th>
      2
    </th>
    
    <td>
      2 095,15
    </td>
    
    <td>
      2 029,99
    </td>
  </tr>
  
  <tr>
    <th>
      5
    </th>
    
    <th>
      2
    </th>
    
    <td>
      7 855,75
    </td>
    
    <td>
      7 759,40
    </td>
  </tr>
  
  <tr>
    <th>
      25
    </th>
    
    <th>
      2
    </th>
    
    <td>
      14 200,25
    </td>
    
    <td>
      13 761,75
    </td>
  </tr>
  
  <tr>
    <th>
      1
    </th>
    
    <th>
      4
    </th>
    
    <td>
      3 768,28
    </td>
    
    <td>
      3 627,02
    </td>
  </tr>
  
  <tr>
    <th>
      5
    </th>
    
    <th>
      4
    </th>
    
    <td>
      11 572,10
    </td>
    
    <td>
      10 708,70
    </td>
  </tr>
  
  <tr>
    <th>
      25
    </th>
    
    <th>
      4
    </th>
    
    <td>
      <strong>17 402,50</strong>
    </td>
    
    <td>
      <strong>16 160,50</strong>
    </td>
  </tr>
</table>

One final note: when trying to send messages using 25 threads in bulks of up to 1000, I once got into a situation where the backup considered the primary dead even though it was working, and another time when the sending failed because the &#8220;address was blocked&#8221; (in other words, queue was full and couldn&#8217;t fit in memory), even though the receivers worked all the time. Maybe that’s due to GC? Or just the very high load?

<a id="kafka"></a>

## Kafka

Kafka takes a different approach to messaging. The server itself is a streaming publish-subscribe system. Each Kafka topic can have multiple partitions; by using more partitions, the consumers of the messages (and the throughput) may be scaled and concurrency of processing increased.

On top of publish-subscribe with partitions, a point-to-point messaging system is built, by putting a significant amount of logic into the consumers (in the other messaging systems we’ve looked at, it was the server that contained most of the message-consumed-by-one-consumer logic; here it’s the consumer).

Each consumer in a consumer group reads messages from a number of dedicated partitions; hence it doesn&#8217;t make sense to have more consumer threads than partitions. Messages aren&#8217;t acknowledged on server (which is a very important design difference!), but instead message offsets processed by consumers are written to Zookeeper, either automatically in the background, or manually. This allows Kafka to achieve much better performance.

Such a design has a couple of consequences:

  * messages from each partition are processed in-order. A custom partition-routing strategy can be defined
  * all consumers should consume messages at the same speed. Messages from a slow consumer won&#8217;t be &#8220;taken over&#8221; by a fast consumer
  * messages are acknowledged &#8220;up to&#8221; an offset. That is messages can&#8217;t be selectively acknowledged.
  * no &#8220;advanced&#8221; messaging options are available, such as routing, delaying messages, re-delivering messages, etc.

You can read more about the design of the consumer in [Kafka&#8217;s docs][22].

To achieve guaranteed sends and at-least-once delivery, I used the following configuration (see the [KafkaMq][23] class):

  * topic is created with a `replication-factor` of 3
  * for the sender, the `request.required.acks` option is set to `1` (a send request blocks until it is accepted by the partition leader &#8211; no guarantees on replication, though)
  * consumer offsets are committed every 10 seconds manually; during that time, message receiving is blocked (a read-write locked is used to assure that). That way we can achieve at-least-once delivery (only committing when messages have been &#8220;observed&#8221;).

Now, to the results. Kafka&#8217;s performance is great. A single-node single-thread achieves about **2 550 msgs/s**, and the best result was **33 500msgs/s** with 25 sending&receiving threads and 4 nodes.

<table>
  <tr>
    <th>
      Threads
    </th>
    
    <th width="25%">
      Nodes
    </th>
    
    <th width="25%">
      Send msgs/s
    </th>
    
    <th width="25%">
      Receive msgs/s
    </th>
  </tr>
  
  <tr>
    <th>
      1
    </th>
    
    <th>
      1
    </th>
    
    <td>
      2 558,81
    </td>
    
    <td>
      2 561,10
    </td>
  </tr>
  
  <tr>
    <th>
      5
    </th>
    
    <th>
      1
    </th>
    
    <td>
      11 804,39
    </td>
    
    <td>
      11 354,90
    </td>
  </tr>
  
  <tr>
    <th>
      25
    </th>
    
    <th>
      1
    </th>
    
    <td>
      29 691,33
    </td>
    
    <td>
      27 093,58
    </td>
  </tr>
  
  <tr>
    <th>
      1
    </th>
    
    <th>
      2
    </th>
    
    <td>
      5 879,21
    </td>
    
    <td>
      5 847,03
    </td>
  </tr>
  
  <tr>
    <th>
      5
    </th>
    
    <th>
      2
    </th>
    
    <td>
      14 660,90
    </td>
    
    <td>
      13 552,35
    </td>
  </tr>
  
  <tr>
    <th>
      25
    </th>
    
    <th>
      2
    </th>
    
    <td>
      29 373,50
    </td>
    
    <td>
      27 822,50
    </td>
  </tr>
  
  <tr>
    <th>
      5
    </th>
    
    <th>
      4
    </th>
    
    <td>
      22 271,50
    </td>
    
    <td>
      20 757,50
    </td>
  </tr>
  
  <tr>
    <th>
      25
    </th>
    
    <th>
      4
    </th>
    
    <td>
      <strong>33 587,00</strong>
    </td>
    
    <td>
      <strong>31 891,00</strong>
    </td>
  </tr></tr>
</table>

Kafka has a big scalability potential, by adding nodes and increasing the number of partitions; however how it scales exactly is another topic, and would have to be tested.

## In summary

As always, which message queue you choose depends on specific project requirements. All of the above solutions have some good sides:

  * SQS is a service, so especially if you are using the AWS cloud, it&#8217;s an easy choice: good performance and no setup required
  * if you are using Mongo, it is easy to build a replicated message queue on top of it, without the need to create and maintain a separate messaging cluster
  * if you want to have high persistence guarantees, RabbitMQ ensures replication across the cluster and on disk on message send.
  * HornetQ has great performance with a very rich messaging interface and routing options
  * Kafka offers the best performance and scalability

When looking only at the throughput, Kafka is a clear winner (unless we include SQS with multiple nodes, but as mentioned, that would be unfair):

<a href="http://www.warski.org/blog/2014/07/evaluating-persistent-replicated-message-queues/summary1-2/" rel="attachment wp-att-1433"><img loading="lazy" decoding="async" src="http://www.warski.org/blog/wp-content/uploads/2014/07/summary1.png" alt="summary1" width="880" height="453" class="aligncenter size-full wp-image-1433" srcset="https://www.warski.org/blog/wp-content/uploads/2014/07/summary1.png 880w, https://www.warski.org/blog/wp-content/uploads/2014/07/summary1-255x131.png 255w, https://www.warski.org/blog/wp-content/uploads/2014/07/summary1-300x154.png 300w, https://www.warski.org/blog/wp-content/uploads/2014/07/summary1-210x108.png 210w" sizes="(max-width: 880px) 100vw, 880px" /></a>

It is also interesting to see how sending more messages in a batch improves the throughput. As already mentioned, when increasing the batch size from 10 to 100, Rabbit gets a 2x speedup, HornetQ a 1.2x speedup, and Kafka a 2.5x speedup, achieving about **89 000 msgs/s**!

<a href="http://www.warski.org/blog/2014/07/evaluating-persistent-replicated-message-queues/summary2/" rel="attachment wp-att-1434"><img loading="lazy" decoding="async" src="http://www.warski.org/blog/wp-content/uploads/2014/07/summary2.png" alt="summary2" width="880" height="453" class="aligncenter size-full wp-image-1434" srcset="https://www.warski.org/blog/wp-content/uploads/2014/07/summary2.png 880w, https://www.warski.org/blog/wp-content/uploads/2014/07/summary2-255x131.png 255w, https://www.warski.org/blog/wp-content/uploads/2014/07/summary2-300x154.png 300w, https://www.warski.org/blog/wp-content/uploads/2014/07/summary2-210x108.png 210w" sizes="(max-width: 880px) 100vw, 880px" /></a>

There are of course many other aspects besides performance, which should be taken into account when choosing a message queue, such as administration overhead, partition tolerance, feature set regarding routing, etc.

Do you have any experiences with persistent, replicated queues? Or maybe you are using some other messaging solutions?

 [1]: https://softwaremill.com/mqperf/
 [2]: #kafka
 [3]: http://aws.amazon.com/sqs/
 [4]: http://www.mongodb.com/
 [5]: http://www.rabbitmq.com/
 [6]: http://hornetq.jboss.org/
 [7]: https://kafka.apache.org
 [8]: http://www.warski.org/blog/2014/06/benchmarking-sqs/
 [9]: https://github.com/adamw/mqperf
 [10]: https://github.com/adamw/mqperf/blob/master/src/main/scala/com/softwaremill/mqperf/Sender.scala
 [11]: https://github.com/adamw/mqperf/blob/master/src/main/scala/com/softwaremill/mqperf/Receiver.scala
 [12]: https://github.com/adamw/mqperf/blob/master/src/main/scala/com/softwaremill/mqperf/mq/Mq.scala
 [13]: https://github.com/adamw/mqperf/blob/master/src/main/scala/com/softwaremill/mqperf/mq/MongoMq.scala
 [14]: http://www.amqp.org/
 [15]: http://www.rabbitmq.com/clustering.html
 [16]: http://www.rabbitmq.com/confirms.html
 [17]: http://www.rabbitmq.com/ha.html
 [18]: https://github.com/adamw/mqperf/blob/master/src/main/scala/com/softwaremill/mqperf/mq/RabbitMq.scala
 [19]: http://docs.jboss.org/hornetq/2.4.0.Final/docs/user-manual/html_single/index.html#d0e11342
 [20]: http://docs.jboss.org/hornetq/2.4.0.Final/docs/user-manual/html_single/index.html#send-guarantees
 [21]: https://github.com/adamw/mqperf/blob/master/src/main/scala/com/softwaremill/mqperf/mq/HornetMq.scala
 [22]: http://kafka.apache.org/documentation.html#theconsumer
 [23]: https://github.com/adamw/mqperf/blob/master/src/main/scala/com/softwaremill/mqperf/mq/KafkaMq.scala
