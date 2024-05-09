---
title: Let’s turn packages into a module system!
author: Adam Warski
type: blog
date: 2012-11-08T19:22:30+00:00
url: /blog/2012/11/lets-turn-packages-into-a-module-system/
disqus_identifier:
  - 1051208358
wp-syntax-cache-content:
  - |
    a:1:{i:1;s:2614:"
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
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">package</span> foo.<span style="color: #000000;">user</span>
    &nbsp;
    <span style="color: #008000; font-style: italic;">// Even without definition, each package has an implicit package object </span>
    <span style="color: #008000; font-style: italic;">// implementing a PackageModule trait ...</span>
    <span style="color: #0000ff; font-weight: bold;">package</span> <span style="color: #0000ff; font-weight: bold;">object</span> dao <span style="color: #F78811;">&#123;</span> 
      <span style="color: #008000; font-style: italic;">// ... which is used here. The type of the val below is </span>
      <span style="color: #008000; font-style: italic;">// List[PackageModule].</span>
      <span style="color: #0000ff; font-weight: bold;">override</span> <span style="color: #0000ff; font-weight: bold;">val</span> moduleDependsOn <span style="color: #000080;">=</span> List<span style="color: #F78811;">&#40;</span>foo.<span style="color: #000000;">security</span>, foo.<span style="color: #000000;">user</span>.<span style="color: #000000;">model</span><span style="color: #F78811;">&#41;</span> 
      <span style="color: #0000ff; font-weight: bold;">override</span> <span style="color: #0000ff; font-weight: bold;">val</span> moduleType <span style="color: #000080;">=</span> ModuleType.<span style="color: #000000;">API</span>
      <span style="color: #008000; font-style: italic;">// FooLibs enum is defined in a top-level package or the build system</span>
      <span style="color: #0000ff; font-weight: bold;">override</span> <span style="color: #0000ff; font-weight: bold;">val</span> moduleLibraries <span style="color: #000080;">=</span> List<span style="color: #F78811;">&#40;</span>FooLibs.<span style="color: #000000;">JPA</span><span style="color: #F78811;">&#41;</span> 
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">package foo.user
    
    // Even without definition, each package has an implicit package object 
    // implementing a PackageModule trait ...
    package object dao { 
      // ... which is used here. The type of the val below is 
      // List[PackageModule].
      override val moduleDependsOn = List(foo.security, foo.user.model) 
      override val moduleType = ModuleType.API
      // FooLibs enum is defined in a top-level package or the build system
      override val moduleLibraries = List(FooLibs.JPA) 
    }</p></div>
    ";}
tags:
  - java
  - architecture
  - scala
  - clean code

---
Many projects are divided into modules/subprojects using the build system ([Maven][1], [Gradle][2], [SBT][3] &#8230;); and writing modular code is generally a Good Thing. Dividing the code into build modules is mainly used for:

  * isolating parts of code (decreasing coupling)
  * api/impl split
  * adding a third-party dependency only to a specific part of code
  * grouping code with similar functionality
  * statically checking that code in one module only uses code from its dependent modules (inter-module dependencies)

While some may say that it is also useful for separate compilation, I don&#8217;t think that matters a lot (when considering one project). The build tools are pretty smart nowadays to figure out what needs to be recompiled.

### Problems with build modules

I think there are several problems with this approach. First of all, it is pretty hard to decide when a piece of functionality is &#8220;big enough&#8221; to turn it into a build module. Is a handful of classes enough? Or do you need more? Should it strictly be one functionality per module? But that would cause a module explosion; and so on. At least in the projects I took part in, it was a common theme of discussions, how coarse-grained the build modules should be.

Secondly, build modules are pretty &#8220;heavy&#8221;. Maven is worst I suppose, you need a large piece of xml to create a module, with lots of boilerplate (for example repeated group id, version number, parent definition); SBT and Gradle are much better, but still, it is a significant effort. A separate directory needs to be created, the whole directory structure (`src/main/...`, `src/test/...`), build config updated, etc. Overall it is quite a hassle.

And then quite often when we have our beautiful modules separated, it turns out that in order for two of them to cooperate, we need a &#8220;common&#8221; part. Then we either end up with a bloated _foo-common_ module, which contains loads of unrelated classes, or multiple small _foo-foomodule-common_ modules; the second solution is fine of course, except for the time wasted setting it up.

Finally, a build module is an additional thing you have to name; most probably already the package name and the class name reflect what the code is doing, now it also needs to be repeated in the build module name (DRY violation). 

All in all, I think creating build modules is much too hard and time-consuming. Programmers are lazy (which, of course, is a good thing), and this leads to designs which are not as clean as they could be. Time to change that :).

(See also my [earlier blog][4] on modules.)

### Packages

Java, Scala and Groovy already have a system for grouping code: packages. However, currently a package is just a string identifier. Except for some very limited visibility options (package-private in Java, package-scoping in Scala) packages have no semantic meaning. So we have several levels of grouping code:

  1. Project
  2. Build module
  3. Package
  4. Class

What if we merged 2. and 3. together; why shouldn&#8217;t packages be used for creating modules?

### Packages as modules?

Let&#8217;s see what would it take to extend packages to be modules. Obviously the first thing that we&#8217;d need is to associate some meta-data with each module. There are already some mechanisms for this (e.g. via annotations on `package-info.java`), or this could be an extension of package objects in Scala &#8211; some traits to mix in, or _val_s to override.

What kind of meta-data? Of course we don&#8217;t want to move the whole build definition to the packages. But let&#8217;s **separate concerns** &#8211; the build definition should define how to build the project, not what the module dependencies are. Then the first thing to define in a module&#8217;s meta-data would be dependencies on third-party libraries. Such definitions could be only symbols, which would be bound to concrete versions in the build definition.

For example, we would specify that package &#8220;`foo.bar.dao`&#8221; depends on the &#8220;`jpa`&#8221; libraries. The build definition would then contain a mapping from &#8220;`jpa`&#8221; to a list of maven artifacts (e.g. hibernate-core, hibernate-entitymanager etc.). Moreover, it would probably make most sense if such dependencies where transitive to sub-packages. So defining a global library would mean adding a dependency on the root package.

As a side note, with an extension of Scala&#8217;s package objects, this could even be made type-safe. The package objects could implement a trait, where one of the values to override could be the list of third-party dependencies symbols. The symbols themselves could be e.g. contained in an `Enumeration`, defined in the root package; which could make things like &#8220;find all modules dependent on jpa&#8221; a simple usage-search in the IDE.

Second step is to define inter-module dependencies using this mechanism as well. It would be possible, in the package&#8217;s meta-data, to define a list of other packages, from which code is visible. This follows how currently build modules are used: each contains a list of project modules which can be accessed. (Another Scala side-note: as the package objects would implement a trait, this would mean defining a list of objects with a given type.)

Taking this further, we could specify **api** and **impl** type-packages. **Api**-type ones would by default be accessible from other packages. **Impl**-type packages, on the other hand, couldn&#8217;t be accessed without explicitly specifying them as a dependency.

How could it look like in practice? A very rough sketch in Scala:
```scala
package foo.user

// Even without definition, each package has an implicit package object 
// implementing a PackageModule trait ...
package object dao { 
  // ... which is used here. The type of the val below is 
  // List[PackageModule].
  override val moduleDependsOn = List(foo.security, foo.user.model) 
  override val moduleType = ModuleType.API
  // FooLibs enum is defined in a top-level package or the build system
  override val moduleLibraries = List(FooLibs.JPA) 
}
```

### Refactoring

Refactoring is an everyday activity; however, refactoring modules is usually a huge task, approached only once in a while. Should it be so? If packages were extended to modules, refactoring modules would be the same as moving around and renaming packages, with the additional need to update the meta-data. It would be much easier than currently, which I think would lead to better overall designs.

### Build system

The above would obviously mean more work to the build system &#8211; it would have a harder time figuring out the list of modules, build order, list of artifacts to create etc (by the way, should a separate jar be created for a package, could also be part of the meta-data). Also some validations would be needed &#8211; for circular dependencies, or trying to constraint the visibility in a wrong way.

But then, people have done more complicated software than that.

### Jigsaw?

You would probably say that this overlaps with project [Jigsaw][5], which will come in Java 9 (or not). However, I think Jigsaw aims at a different scale: project-level modules. So one jigsaw module would be your whole project, while you would have multiple (tens) of packages-modules.

The name &#8220;module&#8221; is overloaded here, maybe the name &#8220;mini-modules&#8221; would be better, or very modestly &#8220;packages done right&#8221;.

### Bottom line

I think that currently the way to define build modules is way too hard and constraining. On the other hand, lifting packages to modules would be very lightweight. Defining a new module would be the same as creating a new package &#8211; couldn&#8217;t get much simpler. Third-party libraries could be added only where needed easily. There would be one less thing to name. And there would be one source tree per project.

Also such an approach would be scalable and adjustable to the project&#8217;s needs. It would be possible to define fine-grained modules or coarse-grained ones without much effort. Or even better, why not create both &#8211; modules could be nested and built one on top of the other.

Now &#8230; the only problem is implementing, and adding IDE support ;)

 [1]: http://maven.apache.org/
 [2]: http://www.gradle.org/
 [3]: http://www.scala-sbt.org/
 [4]: http://www.warski.org/blog/2011/08/modules-modules-modules/ "Modules, modules, modules …"
 [5]: http://openjdk.java.net/projects/jigsaw/
