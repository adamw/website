---
title: Static typing is a great static analysis tool
author: Adam Warski
type: blog
date: 2011-06-27T15:48:25+00:00
url: /blog/2011/06/static-typing-is-a-great-static-analysis-tool/
dsq_thread_id:
  - 1051935727
tags:
  - java
  - static analysis
  - ruby
  - scala
  - clean code

---
Statically-typed languages are great because, well, they have static typing. However very often developing using a dynamically-typed language is much more convenient. Take writing a webapp in Ruby On Rails &#8211; change some code, hit reload and you can see the new code in action. JavaScript &#8211; same thing. On the other hand change a bean in a JSF app: first redeploy (*), wait, and only then go and see the changes. Or even, run a very simple [Scala][1] unit test in IntelliJ &#8211; I don&#8217;t know why, but the compilation always takes a long time, even if the change is very minor. The total run time of the unit test far exceeds the milliseconds in which a unit test should run.

I know it&#8217;s in fact a compiled vs interpreted thing, but somehow statically-typed languages most often come in the &#8220;compiled&#8221; flavor, while dynamically typed in the interpreted one (**). I&#8217;m a huge fan of static typing, and I think **static typing is the best static-analysis tool** out there, but why shouldn&#8217;t it be left just at that: as a static analysis tool.

Moreover, the code is usually type-checked twice: first by the IDE (e.g. IntelliJ won&#8217;t let you run anything as long as there are errors, Eclipse I suppose does the same), then again by the compiler. Isn&#8217;t this a waste of time? If the modern virtual machines are so great at run-time optimization, is the compiler still needed, especially during development?

Adam

(*) Or just use e.g. [JRebel][2]; I think that JRebel is great, but I still view it as a huge hack on the JVM making our lives easier, rather than a &#8220;proper&#8221; solution to the redeployments problem.

(**) Scala takes a step in the &#8220;right&#8221; direction providing the REPL.

 [1]: http://scala-lang.org
 [2]: http://www.zeroturnaround.com/jrebel/
