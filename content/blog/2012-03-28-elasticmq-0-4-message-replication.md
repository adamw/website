---
title: 'ElasticMQ 0.4: message replication'
author: Adam Warski
type: blog
date: 2012-03-28T05:52:47+00:00
url: /blog/2012/03/elasticmq-0-4-message-replication/
disqus_identifier: 1075016042
tags:
  - aws
  - cloud
  - distributed
  - elasticmq
  - java
  - messaging
  - scala

---
[ElasticMQ][1] is a message queue system, with Java, Scala and an Amazon SQS-compatible REST interface.

[The 0.4 release][2] brings a new major feature: data replication. You can now setup a cluster of ElasticMQ nodes, and have each queue and message replicated across this cluster. Each node can use either the in-memory storage, or the DB-backed storage. This enables a wide range of deployment scenarios, e.g.:

  * in-memory storage, single node: ideal for testing (ElasticMQ is easily embeddable and can be used to test applications which use Amazon SQS in production)
  * db storage, multiple nodes, single shared database
  * in-memory storage, multiple nodes, replication: good if at least one node is always alive
  * local db storage, multiple nodes, replication: each node has its own persistance. Data safety without the need to setup a clustered database

Depending on how safe you want to be from loosing a message, there&#8217;s a variety of replication modes to choose from:

  * fire-and-forget: data replication is done in the background
  * wait for at least one cluster member to acknowledge that it received the message
  * wait for a majority of members
  * wait for all members

See the [README][3] for more details. Starting to use ElasticMQ is really simple!

Clustering is implemented using [JGroups][4] (thanks to Bela for the help on the forums!). The cluster can be configured using a standard JGroups configuration file, which means you can either use the UDP stack with multicast discovery, TCP stack with a list of initial node IPs, AWS discovery, and so on. ElasticMQ doesn&#8217;t require any particular JGroups configuration so you are free to use whatever suites your deployment best.

Please note that the replication feature is still at an experimental stage, so bugs (as always) may happen :).

Feedback welcome!

Adam

 [1]: http://www.elasticmq.org
 [2]: http://tools.softwaremill.pl/nexus/content/repositories/releases/org/elasticmq/
 [3]: https://github.com/adamw/elasticmq/blob/master/README.md
 [4]: http://www.jgroups.org
