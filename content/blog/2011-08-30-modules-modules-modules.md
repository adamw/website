---
title: Modules, modules, modules …
author: Adam Warski
type: post
date: 2011-08-30T18:34:39+00:00
url: /blog/2011/08/modules-modules-modules/
simplecatch-sidebarlayout:
  - default
dsq_thread_id:
  - 1051936123
tags:
  - architecture
  - modularity

---
I think everybody will agree that writing modular applications and modularity in general is a _good thing_.

But how does support for modularity look like, both from the Java and Scala languages and various Java/Scala frameworks? There&#8217;s a lot of different approaches! Let&#8217;s look at some of them. Below &#8220;protection&#8221; means how well modules are separated either at compile-time or run-time.[uk inflatable boats][1]

#### Packages

First of all we&#8217;ve got **Java (or Scala) packages**. The concept is very useful when it comes to organizing the source code. However it doesn&#8217;t offer compile-time or run-time (except for the package-protected visibility, which isn&#8217;t used too widely) protection, so it&#8217;s rather hard to say that packages are any help in modularizing the code. Also, there&#8217;s a lot of naming problems and hence conventions: if for example we have two implementations of a data reader, one using a database, the second using the filesystem, should the associated classes go to `database` and `filesystem` subpackages, or stay in the same top-level package? Should the class names be prefixed with `Database` and `Filesystem`, even if they are inside the dedicated packages?

#### Build subprojects

Secondly, there&#8217;s **Maven/SBT modules/subprojects**. They offer compile-time protection, which is a very nice thing: we can now statically make sure that our code only references classes from the explicitly specified dependencies. However, then comes the obvious question: when is a functionality &#8220;big enough&#8221; to make it a separate Maven module? Is it ok to have many Maven modules, each containing only several classes (which can be a PITA, having to define a separate pom, directory structure for each), or should they be bigger? What about naming conventions, should the package name correspond to the module name? If so, we&#8217;re clearly violating [DRY][2] here ;)!

Imagine we decide to put our database and filesystem data readers into separate packages, maven modules and name them appropriately. So we have a `DatabaseReader` (plus 10 helper classes) in a `foo.bar.database` package in the `myapp-database` Maven module. Now a simple refactoring: renaming &#8220;database&#8221; to &#8220;db&#8221; becomes a really complex task, most certainly ending up in using various terms in various places.

#### OSGi

The next step of the evolution would be [OSGi bundles][3] (or, equivalently, a module in [JBoss Modules][4] or in [Project Jigsaw][5]). One such bundle is typically a product of one (or more) Maven module, but it also offers run-time protection by appropriately scoping class-loaders. All of the problems from Maven modules are inherited &#8230; with the additional need to name the bundle!

#### Nested classes/cake pattern

Another possibility of scoping your code is using **nested classes**, or even to go further and use the [Cake pattern in Scala][6]. Apart from the old problems: what package to use and how to name the module class, we&#8217;ve got a couple new ones. Firstly, the whole source code of the module is now in one file. This can mean really long files! However maybe that&#8217;s more of an IDE problem: nobody said that a file needs to be edited all at once; the editor could show only one module class/method (like in the Smalltalk IDE). Cake pattern is also [not free of problems][7]. And, what about nested modules?

#### DI frameworks

Finally, we&#8217;ve got various **DI frameworks** (like Guice, Spring or CDI), where, if we look from an appropriate perspective, we define small modules (classes) which can depend on other modules (injected by the container). Separating the module interface (java interface) from the module implementation (java class), and only injecting the interface is also very common. But again, the only way to partially statically prevent injecting the implementation we have to resort to creating separate `-api` and `-impl` Maven modules.

In fact, if we take the approach of nested classes, a DI framework may be very well suited for resolving inter-module dependencies and doing all of the wiring for us (which can be very useful if we want to be protected from situations where a module has a new dependency, and each use-site must be now amended).

#### Next?

To sum up, I think that the various approaches to supporting modularity could use some unification. And, unfortunately, this would probably mean totally departing from what we know today from Java/Scala: a new package system, a new build system, a new runtime system. Unfortunately the outcrop of new programming languages doesn&#8217;t offer much in that area.

Do you think there should be one module system, usable both in the small (single classes) and in the large (sets of classes)? After all, what a **DI container** does to resolve the dependencies and instantiate classes isn&#8217;t so much different from what **Maven** or an **OSGi** runtime does when booting!

Or maybe modules inherently have several types, small ones (class-level) and big ones (maven module/osgi bundle-level)?

Lastly, maybe there are some other non-JVM based languages, which solve the modularity problem (still being usable) in a nicer way?

Adam

 [1]: http://www.east-inflatables.co.uk/p/015026.html
 [2]: http://en.wikipedia.org/wiki/Don%27t_repeat_yourself
 [3]: http://www.google.pl/url?sa=t&source=web&cd=2&ved=0CDQQFjAB&url=http%3A%2F%2Fwww.osgi.org%2F&ei=Jt1HTrKdAcWZOpSCpfMD&usg=AFQjCNGL-pQ2Iv69bMfSqJx-r9JSJhmWZA&sig2=4ts3qWb8xeebWC80x4Kefw
 [4]: https://docs.jboss.org/author/display/MODULES/Home
 [5]: http://openjdk.java.net/projects/jigsaw/
 [6]: http://www.warski.org/blog/2010/12/di-in-scala-cake-pattern/
 [7]: http://www.warski.org/blog/2011/04/di-in-scala-cake-pattern-pros-cons/
