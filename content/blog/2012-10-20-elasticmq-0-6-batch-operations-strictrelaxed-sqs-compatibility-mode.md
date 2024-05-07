---
title: 'ElasticMQ 0.6: batch operations, strict/relaxed SQS-compatibility mode'
author: Adam Warski
type: post
date: 2012-10-20T11:49:53+00:00
url: /blog/2012/10/elasticmq-0-6-batch-operations-strictrelaxed-sqs-compatibility-mode/
dsq_thread_id:
  - 1052550816
categories:
  - cloud
  - aws
  - elasticmq
  - messaging

---
Summer is definitely over, so it&#8217;s about time to release a new version of [ElasticMQ][1] (0.6).

There are two major changes. The first adds support for batch operations in the SQS interface, which have been added by Amazon some time ago: send, receive, delete and change visibility.

The second change is also in the SQS server. You can now start it in two modes: _strict_ and _relaxed_. In the strict mode, the message size is restricted to 64KB, the maximum number of messages in a batch operation is 10, etc., as specified in SQS&#8217;s documentation. The strict mode is the default one, and is most useful when testing applications which use SQS. In the relaxed mode on the other hand, the restrictions imposed by Amazon are lifted where possible. 

As always ElasticMQ is available in [SoftwareMill&#8217;s][2] Maven repository (see instructions for [SBT and Maven][3]). You can also download a stand-alone server [here][4].

Adam

 [1]: http://www.elasticmq.org
 [2]: http://www.softwaremill.com
 [3]: https://github.com/adamw/elasticmq#elasticmq-dependencies-in-sbt
 [4]: https://github.com/adamw/elasticmq/downloads
