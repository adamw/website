---
title: Implementing message replication in ElasticMQ with JGroups
author: Adam Warski
type: post
date: 2012-06-12T19:10:34+00:00
url: /blog/2012/06/implementing-message-replication-in-elasticmq-with-jgroups/
dsq_thread_id:
  - 1051712119
tags:
  - aws
  - cloud
  - elasticmq
  - java
  - jgroups
  - distributes

---
_[ElasticMQ][1] is a messaging server, with a Scala, Java, and an Amazon SQS-compatible interface. It supports guaranteed messaging by replicating the messages across a cluster of servers, and message persistence with journalling._

Message replication is one of the core features of ElasticMQ. However if you look at the code, it&#8217;s only a handful of classes, the longest having 76 lines (remember that this is Scala, though ;) ). That is because ElasticMQ uses [JGroups][2] as the underlying communication library. JGroups is quite old, especially for a Java library &#8211; the first release was in 1999 (!). But it&#8217;s quite far from being outdated and obsolete &#8211; it has a nice API, works without problems, has a good community; and as any Java library cooperates well with Scala.

JGroups has a lot of useful features:

  * reliable multicast
  * cluster management
  * failure detection
  * node discovery
  * many years of performance improvements

which are extensively used for implementing replication in ElasticMQ. Below is a summary of how it&#8217;s done.

**How does an ElasticMQ cluster work?**

In a single ElasticMQ cluster one node is always the master. You can only execute operations against this node. The results of each operation are then replicated to the other members. There are a couple of options related to blocking; the replication can be fully asynchronous, or it can wait until at least one or all nodes ack the operation. To make sure that in case of cluster partitions the same messages aren&#8217;t received from different partitions, only the partition which has at least half+1 of the nodes operations is active.

The central concept in ElasticMQ is the message storage. A storage executes commands (such as a send message command, delete message command etc.). The replication layer is just a wrapper around any other storage. Note though, that we can only replicate the resulting storage mutations (so after the command is executed), not the original command itself. For example, if the command is &#8220;receive a message&#8221;, the results of executing it may be different on each machine. Hence we replicate only the change of the message visibility (in ElasticMQ, similarly to [Amazon SQS][3], if a message is received it is blocked from a subsequent receive for a specified period of time), if receiving a message succeedes. You can see this basic logic in [JGroupsReplicatedStorage][4].

<p style="text-align: center;">
  <a href="http://www.warski.org/blog/wp-content/uploads/2012/06/replication_basics.png"><img loading="lazy" decoding="async" class="aligncenter size-full wp-image-604" title="replication_basics" src="http://www.warski.org/blog/wp-content/uploads/2012/06/replication_basics.png" alt="" width="447" height="232" srcset="https://www.warski.org/blog/wp-content/uploads/2012/06/replication_basics.png 447w, https://www.warski.org/blog/wp-content/uploads/2012/06/replication_basics-300x155.png 300w" sizes="(max-width: 447px) 100vw, 447px" /></a>
</p>

**Initializing the cluster**

Before we get to the replication itself though, the first thing to do is to initialize the cluster; that is done in [ReplicatedStorageConfigurator][5]. As a parameter there, we need a JGroups configuration file, which is a stack of protocols. You don&#8217;t really need to know what each of these protocols do and what all those configuration parameters mean. The two most useful are [udp.xml][6] and [tcp.xml][7]. The first one should be used if you have multicast in your network, the second &#8211; if all communication should go through TCP (e.g. on EC2). In the latter case you will also need to provide the list of initial IPs. The list doesn&#8217;t have to be exhaustive, just a list of seeds.

Having a protocol stack, ElasticMQ creates a JChannel, and connects it, which simply means connecting to the cluster. And that&#8217;s in fact all you need to do to create a cluster with JGroups &#8211; pretty simple, right? As you can see at the end of [ReplicatedStorageConfigurator][5], the first thing after connecting is a call to _channel.getState(null, 0)_. This will go to the current master node (more on master election later), fetch the state (current queues and messages) and apply it on the current node (see the very simple [JGroupsStateTransferMessageListener][8] &#8211; handles both sending and receiving). There are two important things to note here. First is that this transfer doesn&#8217;t block the whole cluster from normal operation. Second is that if an operation is executed during the state transfer, it will also be replicated. So it may happen that one command is executed twice on the new node. That doesn&#8217;t matter though, as each replicated command is idempotent, so may be applied many times. In other scenarios some application-side mechanism would have to be implemented to prevent such situations.

<p style="text-align: center;">
  <a href="http://www.warski.org/blog/wp-content/uploads/2012/06/replication_connect.png"><img loading="lazy" decoding="async" class="aligncenter size-full wp-image-606" title="replication_connect" src="http://www.warski.org/blog/wp-content/uploads/2012/06/replication_connect.png" alt="" width="495" height="315" srcset="https://www.warski.org/blog/wp-content/uploads/2012/06/replication_connect.png 495w, https://www.warski.org/blog/wp-content/uploads/2012/06/replication_connect-300x190.png 300w" sizes="(max-width: 495px) 100vw, 495px" /></a>
</p>

**Replicating data**

Finally we get to the core: replicating the commands. On the sender side, this is handled by [JGroupsReplicationMessageSender][9]. Again, not a very complicated class. It uses the [MessageDispatcher][10] &#8220;building block&#8221; from JGroups which, apart from multicasing messages across the cluster, enables you to wait until a specified number of nodes receive it. On the receiving side, we have [JGroupsRequestHandler][11]. Again, pretty simple. When a message is received it is simply sent to the storage.

[<img loading="lazy" decoding="async" class="aligncenter size-full wp-image-607" title="replication_request" src="http://www.warski.org/blog/wp-content/uploads/2012/06/replication_request.png" alt="" width="563" height="383" srcset="https://www.warski.org/blog/wp-content/uploads/2012/06/replication_request.png 563w, https://www.warski.org/blog/wp-content/uploads/2012/06/replication_request-300x204.png 300w" sizes="(max-width: 563px) 100vw, 563px" />][12]

**Cluster management**

You may have also noticed the _SetMaster_ special message. This is needed for the user to be able to read the current master&#8217;s node address. Master election (deciding which node is the master) is handled entirely with JGroups. There isn&#8217;t a specific algorithm in JGroups to elect a master, however we can use the fact that each node has the same cluster view, which is represented by the JGroups [View][13] class. All we need to do is simply get the first (or last, or 3rd, etc. &#8211; anything as long as it&#8217;s the same on all nodes) element from that list, and set it as the master.

Cluster view is handled by the last &#8220;core&#8221; replication class, [JGroupsMembershipListener][14]. Two things happen there. The viewAccepted callback is called whenever a new node joins or leaves the cluster; each node with the same (well, equal :) ) instance of the [View][13] class. The master broadcasts its address (which is the ElasticMQ server address, not the internal JGroups cluster-communication address) in a **separate thread**. It&#8217;s a very easy mistake to perform a blocking operation in one of the JGroups callback methods. You should never do that, as the whole stack can then lock. We also need the _FLUSH_ protocol (which is always added during the cluster setup); this protocol makes sure that no new messages are sent when before the new view has been installed by all nodes, and hence we make sure that a new node always receives the master information.

The membership listener also handles cluster merges. Again, JGroups gives us a view of the partitions that were merged and the new combined view. In ElasticMQ, all partitions except the primary parition (which is the largest one) request a state transfer, just as after connecting to the cluster. That way the data is kept in a consistent state.

[<img loading="lazy" decoding="async" class="aligncenter size-full wp-image-605" title="replication_clusterman" src="http://www.warski.org/blog/wp-content/uploads/2012/06/replication_clusterman.png" alt="" width="586" height="346" srcset="https://www.warski.org/blog/wp-content/uploads/2012/06/replication_clusterman.png 586w, https://www.warski.org/blog/wp-content/uploads/2012/06/replication_clusterman-300x177.png 300w" sizes="(max-width: 586px) 100vw, 586px" />][15]

**Summing up**

It&#8217;s also worth noting that ElasticMQ&#8217;s replication is fully tested using ScalaTest. Each test creates a cluster of in-memory storages, creating new nodes or simulating node crashes. See the [JGroupsReplicatedStorageTest][16] class.

Having the mechanisms from JGroups it is pretty straightforward to implement cluster communication. As always, though, you need to remember about some traps when it comes to concurrency (e.g. that there may be cluster activity when a new node joins; that partitions and merges can happen anytime; that there&#8217;s no ordering between normal messages and cluster view changes; that messages may be sent during state transfer; etc.). However both the JGroups [tutorial][17] and [manual][18] is pretty comprehensive and given the additional help from the forums (thanks Bela!), you should be good to go.

You can try how the replication works in practice by downloading a [standalone distribution][19] of ElasticMQ or running it [embedded][20].

Adam

 [1]: http://www.elasticmq.org
 [2]: http://www.jgroups.org/
 [3]: http://aws.amazon.com/sqs/
 [4]: https://github.com/adamw/elasticmq/blob/master/replication/src/main/scala/org/elasticmq/replication/jgroups/JGroupsReplicatedStorage.scala
 [5]: https://github.com/adamw/elasticmq/blob/master/replication/src/main/scala/org/elasticmq/replication/ReplicatedStorageConfigurator.scala
 [6]: https://github.com/belaban/JGroups/blob/master/conf/udp.xml
 [7]: https://github.com/belaban/JGroups/blob/master/conf/tcp.xml
 [8]: https://github.com/adamw/elasticmq/blob/master/replication/src/main/scala/org/elasticmq/replication/jgroups/JGroupsStateTransferMessageListener.scala
 [9]: https://github.com/adamw/elasticmq/blob/master/replication/src/main/scala/org/elasticmq/replication/jgroups/JGroupsReplicationMessageSender.scala
 [10]: https://github.com/belaban/JGroups/blob/master/src/org/jgroups/blocks/MessageDispatcher.java
 [11]: https://github.com/adamw/elasticmq/blob/master/replication/src/main/scala/org/elasticmq/replication/jgroups/JGroupsRequestHandler.scala
 [12]: http://www.warski.org/blog/wp-content/uploads/2012/06/replication_request.png
 [13]: https://github.com/belaban/JGroups/blob/master/src/org/jgroups/View.java
 [14]: https://github.com/adamw/elasticmq/blob/master/replication/src/main/scala/org/elasticmq/replication/jgroups/JGroupsMembershipListener.scala
 [15]: http://www.warski.org/blog/wp-content/uploads/2012/06/replication_clusterman.png
 [16]: https://github.com/adamw/elasticmq/blob/master/replication/src/test/scala/org/elasticmq/replication/JGroupsReplicatedStorageTest.scala
 [17]: http://www.jgroups.org/tutorial-3.x/html/index.html
 [18]: http://www.jgroups.org/manual-3.x/html/index.html
 [19]: https://github.com/adamw/elasticmq/downloads
 [20]: https://github.com/adamw/elasticmq
