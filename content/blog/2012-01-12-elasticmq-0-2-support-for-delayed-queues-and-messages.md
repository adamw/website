---
title: ElasticMQ 0.2 – support for delayed queues and messages
author: Adam Warski
type: blog
date: 2012-01-12T16:52:54+00:00
url: /blog/2012/01/elasticmq-0-2-support-for-delayed-queues-and-messages/
disqus_identifier:
  - 1060340361
wp-syntax-cache-content:
  - |
    a:1:{i:1;s:4417:"
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
    17
    18
    19
    20
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #008000; font-style: italic;">// First we need to create a Node</span>
    <span style="color: #0000ff; font-weight: bold;">val</span> node <span style="color: #000080;">=</span> NodeBuilder.<span style="color: #000000;">withInMemoryStorage</span><span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>.<span style="color: #000000;">build</span><span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>
    <span style="color: #008000; font-style: italic;">// Then we can expose the native client using the SQS REST interface</span>
    <span style="color: #0000ff; font-weight: bold;">val</span> server <span style="color: #000080;">=</span> SQSRestServerFactory.<span style="color: #000000;">start</span><span style="color: #F78811;">&#40;</span>node.<span style="color: #000000;">nativeClient</span>, <span style="color: #F78811;">8888</span>, 
          <span style="color: #6666FF;">&quot;http://localhost:8888&quot;</span><span style="color: #F78811;">&#41;</span>
    &nbsp;
    <span style="color: #008000; font-style: italic;">// Now we need to create the sqs client</span>
    client <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> AmazonSQSClient<span style="color: #F78811;">&#40;</span><span style="color: #0000ff; font-weight: bold;">new</span> BasicAWSCredentials<span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;x&quot;</span>, <span style="color: #6666FF;">&quot;x&quot;</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span>
    client.<span style="color: #000000;">setEndpoint</span><span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;http://localhost:8888&quot;</span><span style="color: #F78811;">&#41;</span>
    &nbsp;
    <span style="color: #008000; font-style: italic;">// Using the client is quite straightforward</span>
    <span style="color: #0000ff; font-weight: bold;">val</span> queueUrl <span style="color: #000080;">=</span> client.<span style="color: #000000;">createQueue</span><span style="color: #F78811;">&#40;</span><span style="color: #0000ff; font-weight: bold;">new</span> CreateQueueRequest<span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;queue1&quot;</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span>
          .<span style="color: #000000;">getQueueUrl</span>
    client.<span style="color: #000000;">sendMessage</span><span style="color: #F78811;">&#40;</span><span style="color: #0000ff; font-weight: bold;">new</span> SendMessageRequest<span style="color: #F78811;">&#40;</span>queueUrl, <span style="color: #6666FF;">&quot;message1&quot;</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span>
    &nbsp;
    client.<span style="color: #000000;">shutdown</span><span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>
    &nbsp;
    <span style="color: #008000; font-style: italic;">// Finally we need to stop the server and the node</span>
    server.<span style="color: #000000;">stop</span><span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>
    node.<span style="color: #000000;">shutdown</span><span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span></pre></td></tr></table><p class="theCode" style="display:none;">// First we need to create a Node
    val node = NodeBuilder.withInMemoryStorage().build()
    // Then we can expose the native client using the SQS REST interface
    val server = SQSRestServerFactory.start(node.nativeClient, 8888, 
          &quot;http://localhost:8888&quot;)
    
    // Now we need to create the sqs client
    client = new AmazonSQSClient(new BasicAWSCredentials(&quot;x&quot;, &quot;x&quot;))
    client.setEndpoint(&quot;http://localhost:8888&quot;)
    
    // Using the client is quite straightforward
    val queueUrl = client.createQueue(new CreateQueueRequest(&quot;queue1&quot;))
          .getQueueUrl
    client.sendMessage(new SendMessageRequest(queueUrl, &quot;message1&quot;))
    
    client.shutdown()
    
    // Finally we need to stop the server and the node
    server.stop()
    node.shutdown()</p></div>
    ";}
tags:
  - aws
  - cloud
  - messaging
  - java
  - scala
  - elasticmq

---
Time to start blogging in 2012 :)

I just released version 0.2 of [ElasticMQ][1] &#8211; a simple message queue system, which implements the [Amazon SQS][2] interface. Ideal if you want to test a service which uses SQS, or create a system which can leverage either the AWS infrastructure or run stand-alone.

The new version brings support for some new Amazon SQS features: delayed messages and queues. See the [Amazon Blog][3] for details.

Also, I changed the testsuite to use [Amazon&#8217;s Java SDK][4]. So you can be pretty sure that ElasticMQ works with SQS clients :). As an example, here&#8217;s the required code (in Scala, Java equivalent would be obviously a bit longer ;) ) to start an ElasticMQ node, send & receive a message using Amazon Java SDK and shutdown the server.

<pre lang="scala" line="1" escaped="true">// First we need to create a Node
val node = NodeBuilder.withInMemoryStorage().build()
// Then we can expose the native client using the SQS REST interface
val server = SQSRestServerFactory.start(node.nativeClient, 8888, 
      "http://localhost:8888")

// Now we need to create the sqs client
client = new AmazonSQSClient(new BasicAWSCredentials("x", "x"))
client.setEndpoint("http://localhost:8888")

// Using the client is quite straightforward
val queueUrl = client.createQueue(new CreateQueueRequest("queue1"))
      .getQueueUrl
client.sendMessage(new SendMessageRequest(queueUrl, "message1"))

client.shutdown()

// Finally we need to stop the server and the node
server.stop()
node.shutdown()
</pre>

For more information about ElasticMQ, information on SBT and Maven dependencies, see the [webpage][1].

Stay tuned,  
Adam

 [1]: https://github.com/adamw/elasticmq
 [2]: http://aws.amazon.com/sqs/
 [3]: http://aws.typepad.com/aws/2011/10/amazon-simple-queue-service-batch-operations-delay-queue-and-message-timers.html
 [4]: http://aws.amazon.com/sdkforjava/
