---
title: Timing interceptor for CDI/Weld
author: Adam Warski
type: post
date: 2011-03-01T15:31:42+00:00
url: /blog/2011/03/timing-interceptor-for-cdiweld/
dsq_thread_id:
  - 1051935522
wp-syntax-cache-content:
  - |
    a:1:{i:1;s:3922:"
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
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;"><span style="color: #000000; font-weight: bold;">public</span> <span style="color: #339933;">&lt;</span>T<span style="color: #339933;">&gt;</span> <span style="color: #000066; font-weight: bold;">void</span> processAnnotatedType<span style="color: #009900;">&#40;</span>
          @Observes ProcessAnnotatedType<span style="color: #339933;">&lt;</span>T<span style="color: #339933;">&gt;</span> event<span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
       <span style="color: #000000; font-weight: bold;">if</span> <span style="color: #009900;">&#40;</span>interceptAnnotatedType<span style="color: #009900;">&#40;</span>event.<span style="color: #006633;">getAnnotatedType</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
          log.<span style="color: #006633;">info</span><span style="color: #009900;">&#40;</span><span style="color: #0000ff;">&quot;Adding the timing interceptor for &quot;</span> <span style="color: #339933;">+</span> 
                event.<span style="color: #006633;">getAnnotatedType</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span>.<span style="color: #006633;">getJavaClass</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span>.<span style="color: #006633;">getName</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
          AnnotatedTypeBuilder<span style="color: #339933;">&lt;</span>T<span style="color: #339933;">&gt;</span> builder <span style="color: #339933;">=</span> <span style="color: #000000; font-weight: bold;">new</span> AnnotatedTypeBuilder<span style="color: #339933;">&lt;</span>T<span style="color: #339933;">&gt;</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span>
                .<span style="color: #006633;">readFromType</span><span style="color: #009900;">&#40;</span>event.<span style="color: #006633;">getAnnotatedType</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
          builder.<span style="color: #006633;">addToClass</span><span style="color: #009900;">&#40;</span><span style="color: #000000; font-weight: bold;">new</span> TimedImpl<span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
          event.<span style="color: #006633;">setAnnotatedType</span><span style="color: #009900;">&#40;</span>builder.<span style="color: #006633;">create</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
       <span style="color: #009900;">&#125;</span>
     <span style="color: #009900;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">public &lt;T&gt; void processAnnotatedType(
          @Observes ProcessAnnotatedType&lt;T&gt; event) {
       if (interceptAnnotatedType(event.getAnnotatedType())) {
          log.info(&quot;Adding the timing interceptor for &quot; + 
                event.getAnnotatedType().getJavaClass().getName());
          AnnotatedTypeBuilder&lt;T&gt; builder = new AnnotatedTypeBuilder&lt;T&gt;()
                .readFromType(event.getAnnotatedType());
          builder.addToClass(new TimedImpl());
          event.setAnnotatedType(builder.create());
       }
     }</p></div>
    ";}
categories:
  - CDI
  - Java
  - JBoss
  - Uncategorized
  - Weld

---
Lately we did some performance tuning, and in order to find out which methods are the hotspots I wrote a simple timing interceptor (it uses some code from a [Seam forum post][1] by Tobias Hill). Apart from timing the execution time of single methods, it gathers all results from a request and prints out a short sorted summary at the end containing the number of invocations, average time and so on.

You can find the source code and some documentation on github ([softwaremill-debug][2] module of the [softwaremill-common][3] project, licensed under Apache2), and the artifacts in our [maven repository][4].

There are in fact four parts:

### Interceptor

The interceptor is a regular CDI interceptor which times the execution of a method and stores the result in a thread-local storage. It must be enabled in beans.xml and can be applied via an annotation (`@Timed`).

### Servlet filter

The servlet filter prints out the summary after a request completes. You just need to add it in your `web.xml`. It is also possible to print out the current stats at any point by invoking `TimingResults.dumpCurrent()`.

### Extension

The portable extension can save you some time if you want to intercept a lot of methods. Instead of adding the annotation manually, it automatically adds the interceptor to all beans for which the full class name (including the package) includes one of the elements of the `timed.autoadd` system property.

The extension is also a good example on how to automatically add interceptors in CDI (using [Seam Solder][5]); in essence it boils down to the following:

<pre lang="java" line="1" escaped="true">public &lt;T&gt; void processAnnotatedType(
      @Observes ProcessAnnotatedType&lt;T&gt; event) {
   if (interceptAnnotatedType(event.getAnnotatedType())) {
      log.info("Adding the timing interceptor for " + 
            event.getAnnotatedType().getJavaClass().getName());
      AnnotatedTypeBuilder&lt;T&gt; builder = new AnnotatedTypeBuilder&lt;T&gt;()
            .readFromType(event.getAnnotatedType());
      builder.addToClass(new TimedImpl());
      event.setAnnotatedType(builder.create());
   }
 }
</pre>

### Proxy

For timing methods on objects which aren&#8217;t managed by the CDI container, there&#8217;s a timing proxy. Simple wrap your object with `TimingProxy.createFor` and any invocations will be timed and added to the current results (of the current request).

Hope you&#8217;ll find it useful!

Adam

 [1]: http://seamframework.org/Community/SeamPerformanceProblemRewardingWorkaround
 [2]: https://github.com/softwaremill/softwaremill-common/tree/master/softwaremill-debug
 [3]: https://github.com/softwaremill/softwaremill-common
 [4]: http://tools.softwaremill.pl/nexus/content/repositories/snapshots
 [5]: http://seamframework.org/Seam3/Solder
