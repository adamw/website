---
title: Using Amazon’s Elastic Map Reduce to compute recommendations with Apache Mahout 0.8
author: Adam Warski
type: post
date: 2013-10-15T11:35:18+00:00
url: /blog/2013/10/using-amazons-elastic-map-reduce-to-compute-recommendations-with-apache-mahout-0-8/
simplecatch-sidebarlayout:
  - default
dsq_thread_id:
  - 1859384858
wp-syntax-cache-content:
  - |
    a:3:{i:1;s:4835:"
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
    </pre></td><td class="code"><pre class="bash" style="font-family:monospace;"><span style="color: #000000;">2013</span>-<span style="color: #000000;">10</span>-04 <span style="color: #000000;">11</span>:05:03,<span style="color: #000000;">921</span> FATAL org.apache.hadoop.mapred.Child <span style="color: #7a0874; font-weight: bold;">&#40;</span>main<span style="color: #7a0874; font-weight: bold;">&#41;</span>: Error running child : java.lang.NoSuchMethodError: org.apache.lucene.util.PriorityQueue.<span style="color: #000000; font-weight: bold;">&lt;</span>init<span style="color: #000000; font-weight: bold;">&gt;</span><span style="color: #7a0874; font-weight: bold;">&#40;</span>I<span style="color: #7a0874; font-weight: bold;">&#41;</span>V
      at org.apache.mahout.math.hadoop.similarity.cooccurrence.TopElementsQueue.<span style="color: #000000; font-weight: bold;">&lt;</span>init<span style="color: #000000; font-weight: bold;">&gt;</span><span style="color: #7a0874; font-weight: bold;">&#40;</span>TopElementsQueue.java:<span style="color: #000000;">33</span><span style="color: #7a0874; font-weight: bold;">&#41;</span>
        at org.apache.mahout.math.hadoop.similarity.cooccurrence.RowSimilarityJob<span style="color: #007800;">$UnsymmetrifyMapper</span>.map<span style="color: #7a0874; font-weight: bold;">&#40;</span>RowSimilarityJob.java:<span style="color: #000000;">405</span><span style="color: #7a0874; font-weight: bold;">&#41;</span>
        at org.apache.mahout.math.hadoop.similarity.cooccurrence.RowSimilarityJob<span style="color: #007800;">$UnsymmetrifyMapper</span>.map<span style="color: #7a0874; font-weight: bold;">&#40;</span>RowSimilarityJob.java:<span style="color: #000000;">389</span><span style="color: #7a0874; font-weight: bold;">&#41;</span>
        at org.apache.hadoop.mapreduce.Mapper.run<span style="color: #7a0874; font-weight: bold;">&#40;</span>Mapper.java:<span style="color: #000000;">144</span><span style="color: #7a0874; font-weight: bold;">&#41;</span>
        at org.apache.hadoop.mapred.MapTask.runNewMapper<span style="color: #7a0874; font-weight: bold;">&#40;</span>MapTask.java:<span style="color: #000000;">771</span><span style="color: #7a0874; font-weight: bold;">&#41;</span>
        at org.apache.hadoop.mapred.MapTask.run<span style="color: #7a0874; font-weight: bold;">&#40;</span>MapTask.java:<span style="color: #000000;">375</span><span style="color: #7a0874; font-weight: bold;">&#41;</span>
        at org.apache.hadoop.mapred.Child<span style="color: #007800;">$4</span>.run<span style="color: #7a0874; font-weight: bold;">&#40;</span>Child.java:<span style="color: #000000;">255</span><span style="color: #7a0874; font-weight: bold;">&#41;</span>
        at java.security.AccessController.doPrivileged<span style="color: #7a0874; font-weight: bold;">&#40;</span>Native Method<span style="color: #7a0874; font-weight: bold;">&#41;</span>
        at javax.security.auth.Subject.doAs<span style="color: #7a0874; font-weight: bold;">&#40;</span>Subject.java:<span style="color: #000000;">415</span><span style="color: #7a0874; font-weight: bold;">&#41;</span>
        at org.apache.hadoop.security.UserGroupInformation.doAs<span style="color: #7a0874; font-weight: bold;">&#40;</span>UserGroupInformation.java:<span style="color: #000000;">1132</span><span style="color: #7a0874; font-weight: bold;">&#41;</span>
        at org.apache.hadoop.mapred.Child.main<span style="color: #7a0874; font-weight: bold;">&#40;</span>Child.java:<span style="color: #000000;">249</span><span style="color: #7a0874; font-weight: bold;">&#41;</span></pre></td></tr></table><p class="theCode" style="display:none;">2013-10-04 11:05:03,921 FATAL org.apache.hadoop.mapred.Child (main): Error running child : java.lang.NoSuchMethodError: org.apache.lucene.util.PriorityQueue.&lt;init&gt;(I)V
      at org.apache.mahout.math.hadoop.similarity.cooccurrence.TopElementsQueue.&lt;init&gt;(TopElementsQueue.java:33)
        at org.apache.mahout.math.hadoop.similarity.cooccurrence.RowSimilarityJob$UnsymmetrifyMapper.map(RowSimilarityJob.java:405)
        at org.apache.mahout.math.hadoop.similarity.cooccurrence.RowSimilarityJob$UnsymmetrifyMapper.map(RowSimilarityJob.java:389)
        at org.apache.hadoop.mapreduce.Mapper.run(Mapper.java:144)
        at org.apache.hadoop.mapred.MapTask.runNewMapper(MapTask.java:771)
        at org.apache.hadoop.mapred.MapTask.run(MapTask.java:375)
        at org.apache.hadoop.mapred.Child$4.run(Child.java:255)
        at java.security.AccessController.doPrivileged(Native Method)
        at javax.security.auth.Subject.doAs(Subject.java:415)
        at org.apache.hadoop.security.UserGroupInformation.doAs(UserGroupInformation.java:1132)
        at org.apache.hadoop.mapred.Child.main(Child.java:249)</p></div>
    ";i:2;s:2442:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    6
    7
    8
    9
    </pre></td><td class="code"><pre class="bash" style="font-family:monospace;"><span style="color: #666666; font-style: italic;">#!/bin/bash</span>
    <span style="color: #7a0874; font-weight: bold;">cd</span> <span style="color: #000000; font-weight: bold;">/</span>home<span style="color: #000000; font-weight: bold;">/</span>hadoop
    <span style="color: #c20cb9; font-weight: bold;">wget</span> https:<span style="color: #000000; font-weight: bold;">//</span>s3.amazonaws.com<span style="color: #000000; font-weight: bold;">/</span>bucket_name<span style="color: #000000; font-weight: bold;">/</span>bucket_path<span style="color: #000000; font-weight: bold;">/</span>lucene-4.3.0.tgz
    <span style="color: #c20cb9; font-weight: bold;">tar</span> <span style="color: #660033;">-xzf</span> lucene-4.3.0.tgz
    <span style="color: #7a0874; font-weight: bold;">cd</span> lib
    <span style="color: #c20cb9; font-weight: bold;">rm</span> lucene-<span style="color: #000000; font-weight: bold;">*</span>.jar
    <span style="color: #7a0874; font-weight: bold;">cd</span> ..
    <span style="color: #7a0874; font-weight: bold;">cd</span> lucene-4.3.0
    <span style="color: #c20cb9; font-weight: bold;">find</span> . <span style="color: #000000; font-weight: bold;">|</span> <span style="color: #c20cb9; font-weight: bold;">grep</span> lucene- <span style="color: #000000; font-weight: bold;">|</span> <span style="color: #c20cb9; font-weight: bold;">grep</span> <span style="color: #c20cb9; font-weight: bold;">jar</span>$ <span style="color: #000000; font-weight: bold;">|</span> <span style="color: #c20cb9; font-weight: bold;">xargs</span> <span style="color: #660033;">-I</span> <span style="color: #7a0874; font-weight: bold;">&#123;</span><span style="color: #7a0874; font-weight: bold;">&#125;</span> <span style="color: #c20cb9; font-weight: bold;">cp</span> <span style="color: #7a0874; font-weight: bold;">&#123;</span><span style="color: #7a0874; font-weight: bold;">&#125;</span> ..<span style="color: #000000; font-weight: bold;">/</span>lib</pre></td></tr></table><p class="theCode" style="display:none;">#!/bin/bash
    cd /home/hadoop
    wget https://s3.amazonaws.com/bucket_name/bucket_path/lucene-4.3.0.tgz
    tar -xzf lucene-4.3.0.tgz
    cd lib
    rm lucene-*.jar
    cd ..
    cd lucene-4.3.0
    find . | grep lucene- | grep jar$ | xargs -I {} cp {} ../lib</p></div>
    ";i:3;s:1003:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    </pre></td><td class="code"><pre class="bash" style="font-family:monospace;">org.apache.mahout.cf.taste.hadoop.item.RecommenderJob 
    <span style="color: #660033;">--booleanData</span> 
    <span style="color: #660033;">--similarityClassname</span> SIMILARITY_LOGLIKELIHOOD 
    <span style="color: #660033;">--output</span> s3n:<span style="color: #000000; font-weight: bold;">//</span>bucket_name<span style="color: #000000; font-weight: bold;">/</span>output 
    <span style="color: #660033;">--input</span> s3n:<span style="color: #000000; font-weight: bold;">//</span>bucket_name<span style="color: #000000; font-weight: bold;">/</span>input.dat</pre></td></tr></table><p class="theCode" style="display:none;">org.apache.mahout.cf.taste.hadoop.item.RecommenderJob 
    --booleanData 
    --similarityClassname SIMILARITY_LOGLIKELIHOOD 
    --output s3n://bucket_name/output 
    --input s3n://bucket_name/input.dat</p></div>
    ";}
categories:
  - apache
  - AWS
  - Distributed
  - hadoop
  - Java
  - mahout
  - recommender
  - Uncategorized

---
Apache Mahout is a &#8220;scalable machine learning library&#8221; which, among others, contains implementations of various single-node and distributed recommendation algorithms. In my last blog post [I described how to implement an on-line recommender system][1] processing data on a single node. What if the data is too large to fit into memory (>100M preference data points)? Then we have no choice, but to take a look at Mahout&#8217;s distributed recommenders implementation!

The distributed recommender is based on Apache Hadoop; it&#8217;s a job which takes as input a list of user preferences, computes an item co-occurence matrix, and outputs top-K recommendations for each user. For an introductory blog on how this works and how to run it locally, see for example [this blog post][2].

We can of course run this job on a custom Hadoop cluster, but it&#8217;s much faster (and less painful) to just use a pre-configured one, like EMR. However, there&#8217;s a slight problem. The latest Hadoop version that is available on EMR is 1.0.3, and it contains jars for Apache Lucene 2.9.4. However, the recommender job depends on Lucene 4.3.0, which results in the following beautiful stack trace:

<pre lang="bash" line="1">2013-10-04 11:05:03,921 FATAL org.apache.hadoop.mapred.Child (main): Error running child : java.lang.NoSuchMethodError: org.apache.lucene.util.PriorityQueue.&lt;init>(I)V
  at org.apache.mahout.math.hadoop.similarity.cooccurrence.TopElementsQueue.&lt;init>(TopElementsQueue.java:33)
    at org.apache.mahout.math.hadoop.similarity.cooccurrence.RowSimilarityJob$UnsymmetrifyMapper.map(RowSimilarityJob.java:405)
    at org.apache.mahout.math.hadoop.similarity.cooccurrence.RowSimilarityJob$UnsymmetrifyMapper.map(RowSimilarityJob.java:389)
    at org.apache.hadoop.mapreduce.Mapper.run(Mapper.java:144)
    at org.apache.hadoop.mapred.MapTask.runNewMapper(MapTask.java:771)
    at org.apache.hadoop.mapred.MapTask.run(MapTask.java:375)
    at org.apache.hadoop.mapred.Child$4.run(Child.java:255)
    at java.security.AccessController.doPrivileged(Native Method)
    at javax.security.auth.Subject.doAs(Subject.java:415)
    at org.apache.hadoop.security.UserGroupInformation.doAs(UserGroupInformation.java:1132)
    at org.apache.hadoop.mapred.Child.main(Child.java:249)
</pre>

How to solve this? Well, we &#8220;just&#8221; need to update Lucene in the EMR Hadoop installation. We can use a **bootstrap action** for that. Here are the exact steps:

  1. Download lucene-4.3.0.tgz (e.g. from [here][3]) and upload it into a S3 bucket; make the file public.</p> 
  2. Upload this script to the bucket as well; call it e.g. `update-lucene.sh`:

<pre lang="bash" line="1">#!/bin/bash
cd /home/hadoop
wget https://s3.amazonaws.com/bucket_name/bucket_path/lucene-4.3.0.tgz
tar -xzf lucene-4.3.0.tgz
cd lib
rm lucene-*.jar
cd ..
cd lucene-4.3.0
find . | grep lucene- | grep jar$ | xargs -I {} cp {} ../lib
</pre>

This script will be run on the Hadoop nodes and will update the Lucene version. Make sure to change the script and enter the correct bucket name and bucket path, so that it points to the public Lucene archive.

  1. Upload `mahout-core-0.8-job.jar` to the bucket</p> 
  2. Finally, we need to upload the input data into S3. Output data will be saved on S3 as well.

  3. Now we can start setting up the EMR job flow. Go to the EMR page on Amazon&#8217;s console, and start creating a new job flow. We&#8217;ll be using the &#8220;Amazon Distribution&#8221; Hadoop version and using a &#8220;Custom JAR&#8221; as the job type.

<a href="http://www.warski.org/blog/2013/10/using-amazons-elastic-map-reduce-to-compute-recommendations-with-apache-mahout-0-8/2013-10-15_1230/" rel="attachment wp-att-1144"><img loading="lazy" decoding="async" src="http://www.warski.org/blog/wp-content/uploads/2013/10/2013-10-15_1230-300x196.png" alt="2013-10-15_1230" width="300" height="196" class="aligncenter size-medium wp-image-1144" srcset="https://www.warski.org/blog/wp-content/uploads/2013/10/2013-10-15_1230-300x196.png 300w, https://www.warski.org/blog/wp-content/uploads/2013/10/2013-10-15_1230-1024x672.png 1024w, https://www.warski.org/blog/wp-content/uploads/2013/10/2013-10-15_1230-210x137.png 210w, https://www.warski.org/blog/wp-content/uploads/2013/10/2013-10-15_1230.png 1724w" sizes="(max-width: 300px) 100vw, 300px" /></a>

  1. The &#8220;JAR location&#8221; must point to the place where we&#8217;ve uploaded the Mahout jar, e.g. `s3n://bucket_name/bucket_path/mahout-0.8-job.jar` (make sure to change this to point to the real bucket!). As for the jar arguments, we&#8217;ll be running the `RecommenderJob` and using the log-likelihood similarity:

<pre lang="bash" line="1">org.apache.mahout.cf.taste.hadoop.item.RecommenderJob 
--booleanData 
--similarityClassname SIMILARITY_LOGLIKELIHOOD 
--output s3n://bucket_name/output 
--input s3n://bucket_name/input.dat
</pre>

That&#8217;s also the place to specify where the input data on S3 is, and where output should be written.

<a href="http://www.warski.org/blog/2013/10/using-amazons-elastic-map-reduce-to-compute-recommendations-with-apache-mahout-0-8/2013-10-15_1232/" rel="attachment wp-att-1145"><img loading="lazy" decoding="async" src="http://www.warski.org/blog/wp-content/uploads/2013/10/2013-10-15_1232-300x118.png" alt="2013-10-15_1232" width="300" height="118" class="aligncenter size-medium wp-image-1145" srcset="https://www.warski.org/blog/wp-content/uploads/2013/10/2013-10-15_1232-300x118.png 300w, https://www.warski.org/blog/wp-content/uploads/2013/10/2013-10-15_1232-1024x405.png 1024w, https://www.warski.org/blog/wp-content/uploads/2013/10/2013-10-15_1232-210x83.png 210w, https://www.warski.org/blog/wp-content/uploads/2013/10/2013-10-15_1232.png 1722w" sizes="(max-width: 300px) 100vw, 300px" /></a>

  1. Then we can choose how many machines we want to use. This depends of course on the size of the input data and how fast you want the results. The main thing to change here is the &#8220;core instance group&#8221; count. 2 is a reasonable default for testing.

<a href="http://www.warski.org/blog/2013/10/using-amazons-elastic-map-reduce-to-compute-recommendations-with-apache-mahout-0-8/2013-10-15_1236/" rel="attachment wp-att-1146"><img loading="lazy" decoding="async" src="http://www.warski.org/blog/wp-content/uploads/2013/10/2013-10-15_1236-300x155.png" alt="2013-10-15_1236" width="300" height="155" class="aligncenter size-medium wp-image-1146" srcset="https://www.warski.org/blog/wp-content/uploads/2013/10/2013-10-15_1236-300x155.png 300w, https://www.warski.org/blog/wp-content/uploads/2013/10/2013-10-15_1236-1024x532.png 1024w, https://www.warski.org/blog/wp-content/uploads/2013/10/2013-10-15_1236-210x109.png 210w, https://www.warski.org/blog/wp-content/uploads/2013/10/2013-10-15_1236.png 1724w" sizes="(max-width: 300px) 100vw, 300px" /></a>

  1. We can leave the advanced options as-is</p> 
  2. Now we get to one of the more important steps: setting up bootstrap actions. We&#8217;ll need to setup two:

  * a Memory Intenstive Configuration (otherwise you&#8217;ll see an OOM quickly)
  * our custom update-lucene action (the path should point to S3, e.g. `s3://bucket_name/bucket_path/update-lucene.sh`)

<a href="http://www.warski.org/blog/2013/10/using-amazons-elastic-map-reduce-to-compute-recommendations-with-apache-mahout-0-8/2013-10-15_1240/" rel="attachment wp-att-1147"><img loading="lazy" decoding="async" src="http://www.warski.org/blog/wp-content/uploads/2013/10/2013-10-15_1240-300x182.png" alt="2013-10-15_1240" width="300" height="182" class="aligncenter size-medium wp-image-1147" srcset="https://www.warski.org/blog/wp-content/uploads/2013/10/2013-10-15_1240-300x182.png 300w, https://www.warski.org/blog/wp-content/uploads/2013/10/2013-10-15_1240-1024x622.png 1024w, https://www.warski.org/blog/wp-content/uploads/2013/10/2013-10-15_1240-210x127.png 210w, https://www.warski.org/blog/wp-content/uploads/2013/10/2013-10-15_1240.png 1720w" sizes="(max-width: 300px) 100vw, 300px" /></a>

And that&#8217;s it! You can now create and run the job flow, and after a couple of minutes/hours/days you&#8217;ll have the results waiting on S3.

Adam

 [1]: http://www.warski.org/blog/2013/10/creating-an-on-line-recommender-system-with-apache-mahout/
 [2]: http://chimpler.wordpress.com/2013/02/20/playing-with-the-mahout-recommendation-engine-on-a-hadoop-cluster/
 [3]: http://archive.apache.org/dist/lucene/java/4.3.0/
