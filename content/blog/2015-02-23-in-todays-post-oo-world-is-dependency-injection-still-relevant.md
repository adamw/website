---
title: In today’s post-OO world, is dependency injection still relevant?
author: Adam Warski
type: blog
date: 2015-02-23T11:54:13+00:00
url: /blog/2015/02/in-todays-post-oo-world-is-dependency-injection-still-relevant/
simplecatch-sidebarlayout:
  - default
dsq_thread_id:
  - 3540407983
tags:
  - dependency injection
  - clean code
  - different
  - functional programming
  - macwire
  - scala

---
It’s 2015. Most of the new popular languages are more or less functional. The old ones, like Java, gain functional programming elements. In Scala, people are increasingly leaning towards the pure side, using more FP and less OO. So &#8211; Dependency Injection? Really?

You could say that DI is just using (constructor) parameters. I’m saying that as well in my talks on [no-framework DI in Scala using MacWire][1]. Passing parameters is the basic building block of FP. So why give it another name? Is DI a separate concept?

## Different kinds of parameters

Recently I had a short discussion with [Alois Cochard on his blog][2], where he describes an approach to DI similar to [mine][3]. He also once wrote a DI library ([Sindi][4]), so it’s good to know we came to similar conclusions. But that got me thinking &#8211; maybe there are two kinds of parameters?

One kind of parameters would be ordinary **&#8220;data&#8221;** parameters. That’s what we pass around functions, manipulate, modify, persist, etc. These are entities, aggregates, collections, case classes. The other kind would be **&#8220;service&#8221;** parameters. These are classes which encapsulate business logic, allow communicating with external systems, provide data access.

While you could of course use _only_ FP to create an entire system end-to-end, I think a number of people (me included) find it convenient and readable to use FP &#8220;in the small&#8221; and objects &#8220;in the large&#8221;, as described e.g. in the [Programming Scala book][5], or in [this Quora question][6]. So you have some possibly pure FP code manipulating your data, implementing the core business logic fragments, and that is encapsulated (organized into?) by objects (you could call them “services”, “modules&#8221;, or some name better reflecting the overall responsibility).

That’s where DI comes into the picture. It can be very useful when wiring the &#8220;service&#8221;/&#8221;module&#8221; object graph. The means to create the object graph may be the same as when dealing with any other kinds of objects &#8211; either by doing [DI manually][3], or with the help of [MacWire][7], or with a container &#8211; but the way we are using parameters feels different.

## Environments

Another way to think about the &#8220;data&#8221; and &#8220;service&#8221; parameter split is that the services form an execution environment for our (pure-FP or not) code. Such an environment may contain services for accessing the database, communicating with the user, etc. Currently we use the same mechanism &#8211; parameters &#8211; to define the environment (e.g. via constructor parameters), as we use to pass data around functions. But maybe in some future programming language, these concepts will be separate?

Or instead of creating a separate mechanism, which could end up as poorer, “shadow” functions, we could have some more convenient syntax for creating and using environments?

A good example of such an &#8220;environmental&#8221; parameter is the quite commonly used `ExecutionContext` in Scala. Currently it is very often passed as an implicit parameter (and it’s contagious, the implicit is needed in every place we want to manipulate futures, from method to method …). At least for me it is a different &#8220;kind&#8221; of parameter then, let’s say, a `Set[Person]`. Wouldn’t it be great if we could just assume that our code is executed in an environment, where &#8220;some&#8221; execution context is provided? Other examples might include “some” way to access a database; or &#8220;some&#8221; way to talk to a webservice.

Such &#8220;environmental&#8221; parameters are often what we want to swap out for testing, or change depending on deployment configuration &#8211; &#8220;canonical&#8221; use cases for dependency injection.

Finally, Alois Cochard also pointed me to a talk by Tomas Petricek on a [calculus for environmental parameters][8] (it’s just 20 minutes!). Time to study coeffects? :)

 [1]: http://www.parleys.com/play/53a7d2cce4b0543940d9e55b/about
 [2]: http://aloiscochard.blogspot.ch/2014/12/the-cake-is-lie.html
 [3]: http://di-in-scala.github.io
 [4]: https://github.com/aloiscochard/sindi
 [5]: https://www.safaribooksonline.com/library/view/programming-scala-2nd/9781491950135/ch08.html
 [6]: http://www.quora.com/What-are-some-good-practices-in-combining-Object-Oriented-programming-and-Functional-Programming
 [7]: https://github.com/adamw/macwire
 [8]: https://www.youtube.com/watch?v=xtxx4iADMbM
