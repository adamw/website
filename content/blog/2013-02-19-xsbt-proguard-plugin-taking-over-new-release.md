---
title: xsbt-proguard-plugin – taking over, new release
author: Adam Warski
type: blog
date: 2013-02-19T14:20:56+00:00
url: /blog/2013/02/xsbt-proguard-plugin-taking-over-new-release/
simplecatch-sidebarlayout:
  - default
disqus_identifier:
  - 1092661614
wp-syntax-cache-content:
  - |
    a:1:{i:1;s:1490:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;">resolvers +<span style="color: #000080;">=</span> Resolver.<span style="color: #000000;">url</span><span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;sbt-plugin-snapshots&quot;</span>, url<span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;http://repo.scala-sbt.org/scalasbt/sbt-plugin-snapshots/&quot;</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#40;</span>Resolver.<span style="color: #000000;">ivyStylePatterns</span><span style="color: #F78811;">&#41;</span>
    &nbsp;
    addSbtPlugin<span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;org.scala-sbt&quot;</span> <span style="color: #000080;">%</span> <span style="color: #6666FF;">&quot;xsbt-proguard-plugin&quot;</span> <span style="color: #000080;">%</span> <span style="color: #6666FF;">&quot;0.1.3-SNAPSHOT&quot;</span><span style="color: #F78811;">&#41;</span></pre></td></tr></table><p class="theCode" style="display:none;">resolvers += Resolver.url(&quot;sbt-plugin-snapshots&quot;, url(&quot;http://repo.scala-sbt.org/scalasbt/sbt-plugin-snapshots/&quot;))(Resolver.ivyStylePatterns)
    
    addSbtPlugin(&quot;org.scala-sbt&quot; % &quot;xsbt-proguard-plugin&quot; % &quot;0.1.3-SNAPSHOT&quot;)</p></div>
    ";}
tags:
  - scala

---
I recently took over the maintenance of [xsbt-proguard-plugin][1] from [siasia][2]. The plugin lets you create single &#8220;fat&#8221; jars from your project, using the [Proguard][3] library.

Some of the most important changes:

  * the group id of the plugin is now `org.scala-sbt`; the plugin is hosted on sbt&#8217;s [Ivy][4] repository
  * proguarding projects with multiple subprojects (modules) now works as expected
  * supporting sbt 0.12 (only)
  * updating Proguard to 4.8

To use the plugin, add to `project/plugins.sbt`:
```scala
resolvers += Resolver.url("sbt-plugin-snapshots", url("http://repo.scala-sbt.org/scalasbt/sbt-plugin-snapshots/"))(Resolver.ivyStylePatterns)

addSbtPlugin("org.scala-sbt" % "xsbt-proguard-plugin" % "0.1.3-SNAPSHOT")
```

So far I only deployed a snapshot version (0.1.3-SNAPSHOT). If there won&#8217;t be any bug reports, or when the bug reports get fixed, I&#8217;ll do a release of a normal version soon.

Please test!

Adam

 [1]: https://github.com/adamw/xsbt-proguard-plugin
 [2]: https://github.com/siasia
 [3]: http://proguard.sourceforge.net/
 [4]: http://repo.scala-sbt.org/scalasbt
