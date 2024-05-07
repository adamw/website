---
title: 'ElasticMQ 0.5: journalling, stand-alone server'
author: Adam Warski
type: post
date: 2012-05-29T05:29:12+00:00
url: /blog/2012/05/elasticmq-0-5-journalling-stand-alone-server/
dsq_thread_id:
  - 1057216025
categories:
  - distributed
  - elasticmq
  - java
  - cloud
  - aws
  - scala
  - messaging

---
[ElasticMQ][1] is a message queue server, with Scala, Java, and an Amazon SQS-compatible interface. It also supports guaranteed messaging via replicating the messages across a cluster of servers.

The new [0.5 version][1] brings two major features. Firstly the file-log, that is a journal for operations on the queue&message storage. Using it makes most sense with the in-memory storage, as the DB storage has its own persistence. Thanks to the journal you can make sure that you won&#8217;t loose messages across server restarts, when moving the whole server etc. The file log uses only append operations to write to files, making it pretty fast (no random access). However, writing the journal is an asynchronous process, so on a server crash some data could be lost (why making this synchronous wouldn&#8217;t help much &#8211; see the [README][2]). If you require an even higher level of message durability, use [replication][3].

Of course, replication can be combined with journalling, providing a server where your messages are safe both when stopping and starting the whole cluster, and in case of an individual node crash.

The second feature is (finally!) a stand-alone server distribution. You can download a [.tar.gz][4] or a [.zip][5] archive. Running the server is very easy, you just need to unpack it and execute the provided `run.sh` or `run.bat` script.

Configuration is done in a type-safe way (via [Ostrich][6]). You can specify which storage to use (in-memory or DB-backed), should the file log be used, if and how to setup replication and node discovery, and whether to expose an SQS interface. The configuration file is self-documenting, see the [base class][7] and the [default config][8] file.

Have fun!

Adam

 [1]: http://www.elasticmq.org
 [2]: https://github.com/adamw/elasticmq#adding-journaling-to-an-in-memory-storage
 [3]: https://github.com/adamw/elasticmq#starting-a-replicated-storage
 [4]: https://github.com/downloads/adamw/elasticmq/elasticmq-0.5.tar.gz
 [5]: https://github.com/downloads/adamw/elasticmq/elasticmq-0.5.zip
 [6]: https://github.com/twitter/ostrich/
 [7]: https://github.com/adamw/elasticmq/blob/master/server/src/main/scala/org/elasticmq/server/ElasticMQServerConfig.scala
 [8]: https://github.com/adamw/elasticmq/blob/master/server/src/main/resources/conf/Default.scala
