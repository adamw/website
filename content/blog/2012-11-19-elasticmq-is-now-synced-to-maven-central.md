---
title: ElasticMQ is now synced to Maven central
author: Adam Warski
type: blog
date: 2012-11-19T11:41:53+00:00
url: /blog/2012/11/elasticmq-is-now-synced-to-maven-central/
disqus_identifier: 1054907258
wp-syntax-cache-content:
  - |
    a:1:{i:1;s:3428:"
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
    </pre></td><td class="code"><pre class="xml" style="font-family:monospace;"><span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;dependency<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
        <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;groupId<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>org.elasticmq<span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/groupId<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
        <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;artifactId<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>elasticmq-core_2.9.1<span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/artifactId<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
        <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;version<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>0.6.1<span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/version<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
    <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/dependency<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
    <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;dependency<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
        <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;groupId<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>org.elasticmq<span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/groupId<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
        <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;artifactId<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>elasticmq-rest-sqs_2.9.1<span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/artifactId<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
        <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;version<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>0.6.1<span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/version<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
    <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/dependency<span style="color: #000000; font-weight: bold;">&gt;</span></span></span></pre></td></tr></table><p class="theCode" style="display:none;">&lt;dependency&gt;
        &lt;groupId&gt;org.elasticmq&lt;/groupId&gt;
        &lt;artifactId&gt;elasticmq-core_2.9.1&lt;/artifactId&gt;
        &lt;version&gt;0.6.1&lt;/version&gt;
    &lt;/dependency&gt;
    &lt;dependency&gt;
        &lt;groupId&gt;org.elasticmq&lt;/groupId&gt;
        &lt;artifactId&gt;elasticmq-rest-sqs_2.9.1&lt;/artifactId&gt;
        &lt;version&gt;0.6.1&lt;/version&gt;
    &lt;/dependency&gt;</p></div>
    ";}
tags:
  - aws
  - cloud
  - elasticmq
  - java
  - messaging
  - scala

---
Thanks to Sonatype OSS repositories, [ElasticMQ version 0.6.1][1] is now synced to central. All of the artifacts, except the standalone server (which depends on Ostrich, which isn&#8217;t available in the central repo) are synchronized. The [procedure][2] turned out to be pretty straighforward, and after a couple of days since the initial request the artifacts are there: <http://repo1.maven.org/maven2/org/elasticmq/>

Hence now it is even easier to use ElasticMQ to test your SQS application. Just follow the [README][3] and add the following dependencies to your project:
```xml
<dependency>
    <groupId>org.elasticmq</groupId>
    <artifactId>elasticmq-core_2.9.1</artifactId>
    <version>0.6.1</version>
</dependency>
<dependency>
    <groupId>org.elasticmq</groupId>
    <artifactId>elasticmq-rest-sqs_2.9.1</artifactId>
    <version>0.6.1</version>
</dependency>
```

Adam

 [1]: http://elasticmq.org
 [2]: https://docs.sonatype.org/display/Repository/Sonatype+OSS+Maven+Repository+Usage+Guide
 [3]: https://github.com/adamw/elasticmq/blob/master/README.md
