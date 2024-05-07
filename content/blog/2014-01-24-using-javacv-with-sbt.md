---
title: Using JavaCV with Scala and SBT
author: Adam Warski
type: post
date: 2014-01-24T15:40:40+00:00
url: /blog/2014/01/using-javacv-with-sbt/
simplecatch-sidebarlayout:
  - default
dsq_thread_id:
  - 2168796244
wp-syntax-cache-content:
  - |
    a:2:{i:1;s:1139:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    6
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">val</span> javacv <span style="color: #000080;">=</span> 
      <span style="color: #6666FF;">&quot;com.googlecode.javacv&quot;</span> <span style="color: #000080;">%</span> 
      <span style="color: #6666FF;">&quot;javacv&quot;</span> <span style="color: #000080;">%</span> 
      <span style="color: #6666FF;">&quot;0.7&quot;</span> classifier <span style="color: #6666FF;">&quot;linux-x86_64&quot;</span> classifier <span style="color: #6666FF;">&quot;macosx-x86_64&quot;</span> classifier <span style="color: #6666FF;">&quot;&quot;</span>
    &nbsp;
    libraryDependencies +<span style="color: #000080;">=</span> javacv</pre></td></tr></table><p class="theCode" style="display:none;">val javacv = 
      &quot;com.googlecode.javacv&quot; % 
      &quot;javacv&quot; % 
      &quot;0.7&quot; classifier &quot;linux-x86_64&quot; classifier &quot;macosx-x86_64&quot; classifier &quot;&quot;
    
    libraryDependencies += javacv</p></div>
    ";i:2;s:406:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;">classpathTypes +<span style="color: #000080;">=</span> <span style="color: #6666FF;">&quot;maven-plugin&quot;</span></pre></td></tr></table><p class="theCode" style="display:none;">classpathTypes += &quot;maven-plugin&quot;</p></div>
    ";}
epcl_post:
  - 'a:1:{s:13:"views_counter";i:1;}'
views_counter:
  - 1
categories:
  - machine learning
  - scala

---
Recently I&#8217;ve been doing some simple face detection in a Scala-based project. The &#8220;industry standard&#8221; for such kind of tasks is [OpenCV][1]; face detection is one of its basic use-cases.

<figure id="attachment_1194" aria-describedby="caption-attachment-1194" style="width: 298px" class="wp-caption aligncenter"><a href="http://www.warski.org/blog/2014/01/using-javacv-with-sbt/2014-01-24_1556/" rel="attachment wp-att-1194"><img loading="lazy" decoding="async" src="http://www.warski.org/blog/wp-content/uploads/2014/01/2014-01-24_1556-298x300.png" alt="Everybody knows this picture, right?" width="298" height="300" class="size-medium wp-image-1194" srcset="https://www.warski.org/blog/wp-content/uploads/2014/01/2014-01-24_1556-298x300.png 298w, https://www.warski.org/blog/wp-content/uploads/2014/01/2014-01-24_1556-150x150.png 150w, https://www.warski.org/blog/wp-content/uploads/2014/01/2014-01-24_1556-208x210.png 208w, https://www.warski.org/blog/wp-content/uploads/2014/01/2014-01-24_1556-210x210.png 210w, https://www.warski.org/blog/wp-content/uploads/2014/01/2014-01-24_1556.png 1014w" sizes="(max-width: 298px) 100vw, 298px" /></a><figcaption id="caption-attachment-1194" class="wp-caption-text">Everybody knows this picture, right?</figcaption></figure>

However OpenCV is written in C/C++, so obviously to use it from Scala a JVM interface is needed. One of such interfaces is [JavaCV][2], which wraps several computer-vision related libraries, one of them being OpenCV.

The process of actually doing face detection from Java using JavaCV is fairly well documented (see for example [here][3]), but what I had some trouble with, is getting [SBT][4] to get the JavaCV jars from the Maven central repository.

There are two steps. First, you need to add the dependency to your build settings:

<pre lang="scala" line="1">val javacv = 
  "com.googlecode.javacv" % 
  "javacv" % 
  "0.7" classifier "linux-x86_64" classifier "macosx-x86_64" classifier ""

libraryDependencies += javacv
</pre>

The unusual thing here are the classifiers. To get the basic jars you need an empty classifier, but you also need some platform-dependent bindings to the native libraries. Here I&#8217;m including bindings for OSX (my dev platform) and Linux (my deployment platform). There are also bindings for Windows, e.g. `windows-x86`.

But that won&#8217;t get you all the jars yet. The second SBT setting you must modify is:

<pre lang="scala" line="1">classpathTypes += "maven-plugin"
</pre>

That is because some of the dependent jars (`javacpp`) are packaged with the `maven-plugin` packaging. And as SBT isn&#8217;t Maven, they won&#8217;t be included by default.

With the two settings above, you should be all set to start doing computer vision with JavaCV, SBT and Scala :)

Adam

 [1]: http://opencv.org/
 [2]: https://code.google.com/p/javacv/
 [3]: http://opencvlover.blogspot.com/2012/11/face-detection-in-javacv-using-haar.html
 [4]: http://www.scala-sbt.org/
