---
title: 'Updating to Scala 2.10: ElasticMQ and scala-macro-debug'
author: Adam Warski
type: blog
date: 2013-01-21T18:37:17+00:00
url: /blog/2013/01/updating-to-scala-2-10-elasticmq-and-scala-macro-debug/
disqus_identifier:
  - 1050958679
wp-syntax-cache-content:
  - |
    a:1:{i:1;s:1083:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;">resolvers +<span style="color: #000080;">=</span> <span style="color: #6666FF;">&quot;SoftwareMill Snapshots&quot;</span> at <span style="color: #6666FF;">&quot;https://nexus.softwaremill.com/content/repositories/snapshots/&quot;</span>
    &nbsp;
    libraryDependencies +<span style="color: #000080;">=</span> <span style="color: #6666FF;">&quot;com.softwaremill.scalamacrodebug&quot;</span> <span style="color: #000080;">%%</span> <span style="color: #6666FF;">&quot;macros&quot;</span> <span style="color: #000080;">%</span> <span style="color: #6666FF;">&quot;0.0.1-SNAPSHOT&quot;</span></pre></td></tr></table><p class="theCode" style="display:none;">resolvers += &quot;SoftwareMill Snapshots&quot; at &quot;https://nexus.softwaremill.com/content/repositories/snapshots/&quot;
    
    libraryDependencies += &quot;com.softwaremill.scalamacrodebug&quot; %% &quot;macros&quot; % &quot;0.0.1-SNAPSHOT&quot;</p></div>
    ";}
tags:
  - elasticmq
  - metaprogramming
  - scala

---
As [Scala 2.10 was released][1] some time ago, it is high time to update the projects using 2.9: [ElasticMQ][2] and [scala-macro-debug][3]. [Veripacks][4] was already released using 2.10.

### ElasticMQ

Version 0.6.3 of [ElasticMQ][2] has only one major change &#8211; it is built with Scala 2.10. That meant two code changes:

  * Using [Typesafe&#8217;s Scala Logging][5] instead of slf4s
  * Using [Typesafe&#8217;s Config][6] instead of [Ostrich][7] for stand-alone server

The second change is the only one visible to the user. The type-safe configuration that was made possible by Ostrich is great, but it doesn&#8217;t look as if a 2.10-compatible version is coming soon. Typesafe Config uses more traditional configuration files, with reasonable amount of verification (though it&#8217;s a bit funny that migrating to Typesafe&#8217;s library causes the config to stop being type-safe ;) ). Also, a good side of the change is that the server always starts up quickly &#8211; no need to compile the config file. Locally on my laptop start up takes about 0.5 seconds.

As always, the new release should be available in Maven Central soon, and links to download the standalone server packages can be found on the main page.

### scala-macro-debug

I also updated the [example Scala macros][8] project, and deployed it to our repositories. If you don&#8217;t want to copy the code, you can now use the debug macro by adding:
```scala
resolvers += "SoftwareMill Snapshots" at "https://nexus.softwaremill.com/content/repositories/snapshots/"

libraryDependencies += "com.softwaremill.scalamacrodebug" %% "macros" % "0.0.1-SNAPSHOT"
```

Adam

 [1]: http://www.scala-lang.org/node/27499
 [2]: http://elasticmq.org
 [3]: https://github.com/adamw/scala-macro-debug
 [4]: https://github.com/adamw/veripacks
 [5]: https://github.com/typesafehub/scalalogging
 [6]: https://github.com/typesafehub/config
 [7]: https://github.com/twitter/ostrich
 [8]: http://www.warski.org/blog/2012/12/starting-with-scala-macros-a-short-tutorial/ "Starting with Scala Macros: a short tutorial"
