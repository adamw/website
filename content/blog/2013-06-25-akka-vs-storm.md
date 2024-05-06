---
title: Akka vs Storm
author: Adam Warski
type: post
date: 2013-06-25T10:57:46+00:00
url: /blog/2013/06/akka-vs-storm/
simplecatch-sidebarlayout:
  - default
dsq_thread_id:
  - 1433413451
categories:
  - Akka
  - Clustering
  - concurrency
  - Distributed
  - Framework
  - Java
  - Replication
  - Scala
  - Uncategorized
tags:
  - Storm

---
I was recently working a bit with [Twitter&#8217;s Storm][1], and it got me wondering, how does it compare to another high-performance, concurrent-data-processing framework, [Akka][2].

### What&#8217;s Akka and Storm?

Let&#8217;s start with a short description of both systems. **Storm** is a distributed, real-time computation system. On a Storm cluster, you execute **topologies**, which process streams of **tuples** (data). Each topology is a graph consisting of **spouts** (which produce tuples) and **bolts** (which transform tuples). Storm takes care of cluster communication, fail-over and distributing topologies across cluster nodes.

**Akka** is a toolkit for building distributed, concurrent, fault-tolerant applications. In an Akka application, the basic construct is an **actor**; actors process **messages** asynchronously, and each actor instance is guaranteed to be run using at most one thread at a time, making concurrency much easier. Actors can also be deployed remotely. There&#8217;s a [clustering module][3] coming, which will handle automatic fail-over and distribution of actors across cluster nodes.

Both systems scale very well and can handle large amounts of data. But when to use one, and when to use the other?

There&#8217;s [another][4] good blog post on the subject, but I wanted to take the comparison a bit further: let&#8217;s see how elementary constructs in Storm compare to elementary constructs in Akka.

### Comparing the basics

Firstly, the basic unit of data in Storm is a **tuple**. A tuple can have any number of elements, and each tuple element can be any object, as long as there&#8217;s a serializer for it. In Akka, the basic unit is a **message**, which can be any object, but it should be serializable as well (for sending it to remote actors). So here the concepts are almost equivalent.

Let&#8217;s take a look at the basic unit of computation. In Storm, we have **components**: **bolts** and **sprouts**. A bolt can be any piece of code, which does arbitrary processing on the incoming tuples. It can also store some mutable data, e.g. to accumulate results. Moreover, bolts run in a **single thread**, so unless you start additional threads in your bolts, you don&#8217;t have to worry about concurrent access to the bolt&#8217;s data. This is very similar to an actor, isn&#8217;t it? Hence a Storm bolt/sprout corresponds to an Akka actor. How do these two compare in detail?

Actors can receive arbitrary messages; bolts can receive arbitrary tuples. Both are expected to do some processing basing on the data received.

Both have internal state, which is private and protected from concurrent thread access.

### Actors & bolts: differences

One crucial difference is how actors and bolts communicate. An actor can send a message to any other actor, as long as it has the `ActorRef` (and if not, an actor can be looked up by-name). It can also send back a **reply** to the sender of the message that is being handled. Storm, on the other hand is **one-way**. You cannot send back messages; you also can&#8217;t send messages to arbitrary bolts. You can also send a tuple to a named channel (stream), which will cause the tuple (message) to be broadcast to all listeners, defined in the topology. (Bolts also **ack** messages, which is also a form of communication, to the ackers.)

In Storm, multiple copies of a bolt&#8217;s/sprout&#8217;s code can be run in parallel (depending on the **parallelism setting**). So this corresponds to a set of (potentially remote) actors, with a **load-balancer actor** in front of them; a concept well-known from Akka&#8217;s [routing][5]. There are a couple of choices on how tuples are routed to bolt instances in Storm (random, consistent hashing on a field), and this roughly corresponds to the various router options in Akka (round robin, consistent hashing on the message).

There&#8217;s also a difference in the &#8220;weight&#8221; of a bolt and an actor. In Akka, it is normal to have **lots of actors** (up to millions). In Storm, the expected number of bolts is significantly smaller; this isn&#8217;t in any case a downside of Storm, but rather a design decision. Also, Akka actors typically **share threads**, while each bolt instance tends to have a **dedicated thread**.

### Other features

Storm also has one crucial feature which isn&#8217;t implemented in Akka out-of-the-box: **[guaranteed message delivery][6]**. Storm tracks the whole tree of tuples that originate from any tuple produced by a sprout. If all tuples aren&#8217;t acknowledged, the tuple will be replayed.

Also the cluster management of Storm is more advanced (automatic fail-over, automatic balancing of workers across the cluster; based on [Zookeeper][7]); however the upcoming Akka clustering module should address that.

Finally, the layout of the communication in Storm &#8211; the topology &#8211; is static and defined upfront. In Akka, the communication patterns can change over time and can be totally dynamic; actors can send messages to any other actors, or can even send addresses (ActorRefs).

So overall, Storm implements a specific range of usages very well, while Akka is more of a general-purpose toolkit. It would be possible to build a Storm-like system on top of Akka, but not the other way round (at least it would be very hard).

Adam

 [1]: http://storm-project.net/
 [2]: http://akka.io/
 [3]: http://doc.akka.io/docs/akka/2.2.0-RC1/common/cluster.html
 [4]: http://blog.samibadawi.com/2013/04/akka-vs-finagle-vs-storm.html
 [5]: http://doc.akka.io/docs/akka/2.2.0-RC1/scala/routing.html
 [6]: https://github.com/nathanmarz/storm/wiki/Guaranteeing-message-processing
 [7]: http://zookeeper.apache.org/
