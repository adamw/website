---
title: Benchmarking SQS
author: Adam Warski
type: blog
date: 2014-06-24T12:05:33+00:00
url: /blog/2014/06/benchmarking-sqs/
simplecatch-sidebarlayout:
  - default
disqus_identifier:
  - 2791242746
tags:
  - aws
  - cloud
  - messaging
  - distributed
  - testing
  - performance

---
SQS, [Simple Message Queue][1], is a message-queue-as-a-service offering from Amazon Web Services. It supports only a handful of messaging operations, far from the complexity of e.g. [AMQP][2], but thanks to the easy to understand interfaces, and the as-a-service nature, it is very useful in a number of situations.

But how fast is SQS? How does it scale? Is it useful only for low-volume messaging, or can it be used for high-load applications as well?

<a href="http://www.warski.org/blog/2014/06/benchmarking-sqs/sqsperf-logo/" rel="attachment wp-att-1388"><img loading="lazy" decoding="async" src="http://www.warski.org/blog/wp-content/uploads/2014/06/sqsperf-logo-300x251.png" alt="sqsperf-logo" width="300" height="251" class="aligncenter size-medium wp-image-1388" srcset="https://www.warski.org/blog/wp-content/uploads/2014/06/sqsperf-logo-300x251.png 300w, https://www.warski.org/blog/wp-content/uploads/2014/06/sqsperf-logo-255x213.png 255w, https://www.warski.org/blog/wp-content/uploads/2014/06/sqsperf-logo-210x176.png 210w, https://www.warski.org/blog/wp-content/uploads/2014/06/sqsperf-logo.png 440w" sizes="(max-width: 300px) 100vw, 300px" /></a>

If you know how SQS works, and want to skip the details on the testing methodology, you can jump straight to the [test results][3].

## SQS semantics

SQS exposes an HTTP-based interface. To access it, you need AWS credentials to sign the requests. But that’s usually done by a client library (there are libraries for most popular languages; we’ll use the official [Java SDK][4]).

The basic message-related operations are:

  * send a message, up to 256 KB in size, encoded as a string. Messages can be sent in bulks of up to 10 (but the total size is capped at 256 KB).
  * receive a message. Up to 10 messages can be received in bulk, if available in the queue
  * long-polling of messages. The request will wait up to 20 seconds for messages, if none are available initially
  * delete a message

There are also some other operations, concerning security, delaying message delivery, and changing a messages&#8217; visibility timeout, but we won’t use them in the tests.

SQS offers at-least-once delivery guarantee. If a message is received, then it is blocked for a period called “visibility timeout”. Unless the message is deleted within that period, it will become available for delivery again. Hence if a node processing a message crashes, it will be delivered again. However, we also run into the risk of processing a message twice (if e.g. the network connection when deleting the message dies, or if an SQS server dies), which we have to manage on the application side.

SQS is a replicated message queue, so you can be sure that once a message is sent, it is safe and will be delivered; quoting from the website:

> Amazon SQS runs within Amazon’s high-availability data centers, so queues will be available whenever applications need them. To prevent messages from being lost or becoming unavailable, all messages are stored redundantly across multiple servers and data centers. 

## Testing methodology

To test how fast SQS is and how it scales, we will be running various numbers of nodes, each running various number of threads either sending or receiving simple, 100-byte messages.

Each sending node is parametrised with the number of messages to send, and it tries to do so as fast as possible. Messages are sent in bulk, with bulk sizes chosen randomly between 1 and 10. Message sends are synchronous, that is we want to be sure that the request completed successfully before sending the next bulk. At the end the node reports the average number of messages per second that were sent.

The receiving node receives messages in maximum bulks of 10. The [`AmazonSQSBufferedAsyncClient`][5] is used, which pre-fetches messages to speed up delivery. After receiving, each message is asynchronously deleted. The node assumes that testing is complete once it didn’t receive any messages within a minute, and reports the average number of messages per second that it received.

Each test sends from 10 000 to 50 000 messages per thread. So the tests are relatively short, 2-5 minutes. There are also longer tests, which last about 15 minutes.

The full (but still short) code is here: [`Sender`][6], [`Receiver`][7], [`SqsMq`][8]. One set of nodes runs the `MqSender` code, the other runs the `MqReceiver` code.

The sending and receiving nodes are m3.large EC2 servers in the eu-west region, hence with the following parameters:

  * 2 cores
  * Intel Xeon E5-2670 v2
  * 7.5 GB RAMs

The queue is of course also created in the eu-west region.

<a name="sqstestresults"></a>

## Minimal setup

The minimal setup consists of 1 sending node and 1 receiving node, both running a single thread. The results are, in messages/second:

<table>
  <tr>
    <th>
      &nbsp;
    </th>
    
    <th width="20%">
      average
    </th>
    
    <th width="20%">
      min
    </th>
    
    <th width="20%">
      max
    </th>
  </tr>
  
  <tr>
    <th>
      sender
    </th>
    
    <td>
      429
    </td>
    
    <td>
      365
    </td>
    
    <td>
      466
    </td>
  </tr>
  
  <tr>
    <th>
      receiver
    </th>
    
    <td>
      427
    </td>
    
    <td>
      363
    </td>
    
    <td>
      463
    </td>
  </tr>
</table>

## Scaling threads

How do these results scale when we add more threads (still using one sender and one receiver node)? The tests were run with 1, 5, 25, 50 and 75 threads. The numbers are an average msg/second throughput.

<table>
  <tr>
    <th>
      number of threads:
    </th><th width=“15%”>1</th> <th width=“15%”>5</th> <th width=“15%”>25</th> <th width=“15%”>50</th> <th width=“15%”>75</th>
  </tr>
  
  <tr>
    <th>
      sender per thread
    </th>
    
    <td>
      429,33
    </td>
    
    <td>
      407,35
    </td>
    
    <td>
      354,15
    </td>
    
    <td>
      289,88
    </td>
    
    <td>
      193,71
    </td>
  </tr>
  
  <tr>
    <th>
      sender total
    </th>
    
    <td>
      429,33
    </td>
    
    <td>
      2 036,76
    </td>
    
    <td>
      8 853,75
    </td>
    
    <td>
      14 493,83
    </td>
    
    <td>
      <strong>14 528,25</strong>
    </td>
  </tr>
  
  <tr>
    <th>
      receiver per thread
    </th>
    
    <td>
      427,86
    </td>
    
    <td>
      381,55
    </td>
    
    <td>
      166,38
    </td>
    
    <td>
      83,92
    </td>
    
    <td>
      47,46
    </td>
  </tr>
  
  <tr>
    <th>
      receiver total
    </th>
    
    <td>
      427,86
    </td>
    
    <td>
      1 907,76
    </td>
    
    <td>
      4 159,50
    </td>
    
    <td>
      <strong>4 196,17</strong>
    </td>
    
    <td>
      3 559,50
    </td>
  </tr>
</table>

As you can see, on the sender side, we get near-to-linear scalability as the number of thread increases, peaking at 14k msgs/second sent (on a single node!) with 50 threads. Going any further doesn’t seem to make a difference.

<a href="http://www.warski.org/blog/?attachment_id=1393" rel="attachment wp-att-1393"><img loading="lazy" decoding="async" src="http://www.warski.org/blog/wp-content/uploads/2014/06/sqsperf11.png" alt="sqsperf1" width="876" height="453" class="aligncenter size-full wp-image-1393" srcset="https://www.warski.org/blog/wp-content/uploads/2014/06/sqsperf11.png 876w, https://www.warski.org/blog/wp-content/uploads/2014/06/sqsperf11-255x131.png 255w, https://www.warski.org/blog/wp-content/uploads/2014/06/sqsperf11-300x155.png 300w, https://www.warski.org/blog/wp-content/uploads/2014/06/sqsperf11-210x108.png 210w" sizes="(max-width: 876px) 100vw, 876px" /></a>

The receiving side is slower, and that is kind of expected, as receiving a single message is in fact two operations: receive + delete, while sending is a single operation. The scalability is worse, but still we can get as much as 4k msgs/second received.

## Scaling nodes

Another (more promising) method of scaling is adding nodes, which is quite easy as we are &#8220;in the cloud&#8221;. The test results when running multiple nodes, each running a single thread are:

<table>
  <tr>
    <th>
      number of nodes:
    </th><th width=“15%”>1</th> <th width=“15%”>2</th> <th width=“15%”>4</th> <th width=“15%”>8</th>
  </tr>
  
  <tr>
    <th>
      sender per node
    </th>
    
    <td>
      429,33
    </td>
    
    <td>
      370,36
    </td>
    
    <td>
      350,30
    </td>
    
    <td>
      337,84
    </td>
  </tr>
  
  <tr>
    <th>
      sender total
    </th>
    
    <td>
      429,33
    </td>
    
    <td>
      740,71
    </td>
    
    <td>
      1 401,19
    </td>
    
    <td>
      <strong>2 702,75</strong>
    </td>
  </tr>
  
  <tr>
    <th>
      receiver per node
    </th>
    
    <td>
      427,86
    </td>
    
    <td>
      360,60
    </td>
    
    <td>
      329,54
    </td>
    
    <td>
      306,40
    </td>
  </tr>
  
  <tr>
    <th>
      receiver total
    </th>
    
    <td>
      427,86
    </td>
    
    <td>
      721,19
    </td>
    
    <td>
      1 318,15
    </td>
    
    <td>
      <strong>2 451,23</strong>
    </td>
  </tr>
</table>

In this case, both on the sending&receiving side, we get near-linear scalability, reaching 2.5k messages sent&received per second with 8 nodes.

<a href="http://www.warski.org/blog/2014/06/benchmarking-sqs/sqsperf2-2/" rel="attachment wp-att-1394"><img loading="lazy" decoding="async" src="http://www.warski.org/blog/wp-content/uploads/2014/06/sqsperf21.png" alt="sqsperf2" width="876" height="453" class="aligncenter size-full wp-image-1394" srcset="https://www.warski.org/blog/wp-content/uploads/2014/06/sqsperf21.png 876w, https://www.warski.org/blog/wp-content/uploads/2014/06/sqsperf21-255x131.png 255w, https://www.warski.org/blog/wp-content/uploads/2014/06/sqsperf21-300x155.png 300w, https://www.warski.org/blog/wp-content/uploads/2014/06/sqsperf21-210x108.png 210w" sizes="(max-width: 876px) 100vw, 876px" /></a>

## Scaling nodes and threads

The natural next step is, of course, to scale up both the nodes, and the threads! Here are the results, when using 25 threads on each node:

<table>
  <tr>
    <th>
      number of nodes:
    </th><th width=“15%”>1</th> <th width=“15%”>2</th> <th width=“15%”>4</th> <th width=“15%”>8</th>
  </tr>
  
  <tr>
    <th>
      sender per node&thread
    </th>
    
    <td>
      354,15
    </td>
    
    <td>
      338,52
    </td>
    
    <td>
      305,03
    </td>
    
    <td>
      317,33
    </td>
  </tr>
  
  <tr>
    <th>
      sender total
    </th>
    
    <td>
      8 853,75
    </td>
    
    <td>
      16 925,83
    </td>
    
    <td>
      30 503,33
    </td>
    
    <td>
      <strong>63 466,00</strong>
    </td>
  </tr>
  
  <tr>
    <th>
      receiver per node&thread
    </th>
    
    <td>
      166,38
    </td>
    
    <td>
      159,13
    </td>
    
    <td>
      170,09
    </td>
    
    <td>
      174,26
    </td>
  </tr>
  
  <tr>
    <th>
      receiver total
    </th>
    
    <td>
      4 159,50
    </td>
    
    <td>
      7 956,33
    </td>
    
    <td>
      17 008,67
    </td>
    
    <td>
      <strong>34 851,33</strong>
    </td>
  </tr>
</table>

Again, we get great scalability results, with the number of receive operations about half the number of send operations per second. 34k msgs/second processed is a very nice number!

<a href="http://www.warski.org/blog/2014/06/benchmarking-sqs/sqsperf3-2/" rel="attachment wp-att-1395"><img loading="lazy" decoding="async" src="http://www.warski.org/blog/wp-content/uploads/2014/06/sqsperf31.png" alt="sqsperf3" width="876" height="452" class="aligncenter size-full wp-image-1395" srcset="https://www.warski.org/blog/wp-content/uploads/2014/06/sqsperf31.png 876w, https://www.warski.org/blog/wp-content/uploads/2014/06/sqsperf31-255x131.png 255w, https://www.warski.org/blog/wp-content/uploads/2014/06/sqsperf31-300x154.png 300w, https://www.warski.org/blog/wp-content/uploads/2014/06/sqsperf31-210x108.png 210w" sizes="(max-width: 876px) 100vw, 876px" /></a>

## To the extreme

The highest results I managed to get are:

  * _108k msgs/second sent_ when using 50 threads and 8 nodes
  * _35k msgs/second received_ when using 25 threads and 8 nodes

I also tried running longer “stress” tests with 200k messages/thread, 8 nodes and 25 threads, and the results were the same as with the shorter tests.

## Running the tests &#8211; technically

To run the tests, I built [Docker][9] images containing the `Sender`/`Receiver` binaries, pushed to Docker’s Hub, and downloaded on the nodes by Chef. To provision the servers, I used Amazon OpsWorks. This enabled me to quickly spin up and provision a lot of nodes for testing (up to 16 in the above tests). For details on how this works, see my [&#8220;Cluster-wide Java/Scala application deployments with Docker, Chef and Amazon OpsWorks&#8221; blog][10].

The `Sender`/`Receiver` daemons monitored (by checking each second the last-modification date) a file on S3. If a modification was detected, the file was downloaded &#8211; it contained the test parameters &#8211; and the test started.

## Summing up

[SQS][1] has good performance and really great scalability characteristics. I wasn’t able to reach the peak of its possibilities &#8211; which would probably require more than 16 nodes in total. But once your requirements get above 35k messages per second, chances are you need custom solutions anyway; not to mention that while SQS is cheap, it may become expensive with such loads.

From the results above, I think it is clear that SQS can be safely used for high-volume messaging applications, and scaled on-demand. Together with its reliability guarantees, it is a great fit both for small and large applications, which do any kind of asynchronous processing; especially if your service already resides in the Amazon cloud.

As benchmarking isn’t easy, any remarks on the testing methodology, ideas how to improve the testing code are welcome!

 [1]: http://aws.amazon.com/sqs
 [2]: http://www.amqp.org/
 [3]: #sqstestresults
 [4]: http://aws.amazon.com/sdkforjava/
 [5]: http://docs.aws.amazon.com/AWSJavaSDK/latest/javadoc/com/amazonaws/services/sqs/buffered/AmazonSQSBufferedAsyncClient.html
 [6]: https://gist.github.com/adamw/aea08434c18704e16d55
 [7]: https://gist.github.com/adamw/c86c819920e346d7f671
 [8]: https://gist.github.com/adamw/f89c151f0695a94b3577
 [9]: http://docker.com/
 [10]: http://www.warski.org/blog/2014/06/cluster-wide-javascala-application-deployments-with-docker-chef-and-amazon-opsworks/
