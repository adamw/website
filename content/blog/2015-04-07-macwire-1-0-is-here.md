---
title: MacWire 1.0 is here!
author: Adam Warski
type: blog
date: 2015-04-07T13:20:30+00:00
url: /blog/2015/04/macwire-1-0-is-here/
simplecatch-sidebarlayout:
  - default
disqus_identifier: 3661802634
tags:
  - akka
  - dependency injection
  - metaprogramming
  - macwire
  - scala

---
A couple of days ago [MacWire 1.0][1] was released! If you don&#8217;t yet know what MacWire is: a light-weight and non-intrusive Scala Dependency Injection library. In fact, it&#8217;s more of an approach to DI (see [the step-by-step guide][2]), than a library. Another good thing &#8211; it&#8217;s definitely not a framework &#8211; instead, pure Scala code!

Apart from [the guide][2], there are two Typesafe Activators which can also help you to get started:

  * [MacWire + Akka][3]
  * [MacWire + Play][4]

This release is almost identical to [0.8][5], with two additions:

  * multiple tags can be added to an instance using `someInstance.taggedWith[Tag1].andTaggedWith[Tag2]`
  * the `wire` macro can be brought into scope by simply importing `com.softwaremill.macwire._` (together with tagging support)

As there were no serious bugs in 0.8, we decided to promote that code-base to 1.0. A number of people are using MacWire in their production code-bases (however MacWire is usually only used at compile-time), so we are pretty confident that the code works as it should

Thanks to all of the contributors! [Star the project][1] if you like it!

Adam

 [1]: https://github.com/adamw/macwire
 [2]: http://di-in-scala.github.io
 [3]: https://typesafe.com/activator/template/macwire-akka-activator
 [4]: https://typesafe.com/activator/template/macwire-activator
 [5]: http://www.warski.org/blog/2015/01/macwire-0-8-0-towards-1-0-tagging-anonymous-functions-support/
