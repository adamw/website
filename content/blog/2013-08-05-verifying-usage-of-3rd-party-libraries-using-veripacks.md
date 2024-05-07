---
title: Verifying usage of 3rd party libraries using Veripacks
author: Adam Warski
type: blog
date: 2013-08-05T18:16:38+00:00
url: /blog/2013/08/verifying-usage-of-3rd-party-libraries-using-veripacks/
simplecatch-sidebarlayout:
  - default
dsq_thread_id:
  - 1573097946
wp-syntax-cache-content:
  - |
    a:3:{i:1;s:1120:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    6
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #008000; font-style: italic;">// test case which runs Veripacks</span>
    VeripacksBuilder
       .<span style="color: #000000;">requireImportOf</span><span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;org.hibernate&quot;</span><span style="color: #F78811;">&#41;</span>
       .<span style="color: #000000;">build</span>
       .<span style="color: #000000;">verify</span><span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;com.foo.project&quot;</span><span style="color: #F78811;">&#41;</span>
       .<span style="color: #000000;">throwIfNotOk</span><span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span></pre></td></tr></table><p class="theCode" style="display:none;">// test case which runs Veripacks
    VeripacksBuilder
       .requireImportOf(&quot;org.hibernate&quot;)
       .build
       .verify(&quot;com.foo.project&quot;)
       .throwIfNotOk()</p></div>
    ";i:2;s:1287:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    6
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #008000; font-style: italic;">// package in which we want to use Hibernate classes</span>
    <span style="color: #008000; font-style: italic;">// package-info.java</span>
    <span style="color: #000080;">@</span>Import<span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;org.hibernate&quot;</span><span style="color: #F78811;">&#41;</span>
    <span style="color: #0000ff; font-weight: bold;">package</span> com.<span style="color: #000000;">foo</span>.<span style="color: #000000;">project</span>.<span style="color: #000000;">db</span>.<span style="color: #000000;">dao</span><span style="color: #000080;">;</span>
    &nbsp;
    <span style="color: #0000ff; font-weight: bold;">import</span> org.<span style="color: #000000;">veripacks</span>.<span style="color: #000000;">Import</span><span style="color: #000080;">;</span></pre></td></tr></table><p class="theCode" style="display:none;">// package in which we want to use Hibernate classes
    // package-info.java
    @Import(&quot;org.hibernate&quot;)
    package com.foo.project.db.dao;
    
    import org.veripacks.Import;</p></div>
    ";i:3;s:2013:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    6
    7
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;">VeripacksBuilder
      .<span style="color: #000000;">withCustomAccessDefinitionReader</span><span style="color: #F78811;">&#40;</span><span style="color: #0000ff; font-weight: bold;">new</span> CustomAccessDefinitionsReader <span style="color: #F78811;">&#123;</span>
        <span style="color: #0000ff; font-weight: bold;">override</span> <span style="color: #0000ff; font-weight: bold;">def</span> isRequiresImport<span style="color: #F78811;">&#40;</span>pkg<span style="color: #000080;">:</span> Pkg<span style="color: #F78811;">&#41;</span> <span style="color: #000080;">=</span> pkg.<span style="color: #000000;">name</span>.<span style="color: #000000;">split</span><span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;.&quot;</span><span style="color: #F78811;">&#41;</span>.<span style="color: #000000;">length</span> <span style="color: #000080;">==</span> <span style="color: #F78811;">4</span>
      <span style="color: #F78811;">&#125;</span><span style="color: #F78811;">&#41;</span>
      .<span style="color: #000000;">build</span>
      .<span style="color: #000000;">verify</span><span style="color: #F78811;">&#40;</span>List<span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;com.[company].[project]&quot;</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span>
      .<span style="color: #000000;">throwIfNotOk</span><span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span></pre></td></tr></table><p class="theCode" style="display:none;">VeripacksBuilder
      .withCustomAccessDefinitionReader(new CustomAccessDefinitionsReader {
        override def isRequiresImport(pkg: Pkg) = pkg.name.split(&quot;.&quot;).length == 4
      })
      .build
      .verify(List(&quot;com.[company].[project]&quot;))
      .throwIfNotOk()</p></div>
    ";}
tags:
  - java
  - modularity
  - veripacks

---
[Veripacks][1] already allows to specify and verify which classes [should be visible outside of a package][2] (in a package-transitive way) as well as [require importing and import packages][3] within a project. This makes it possible to [eliminate some build modules][4], while still keeping their strict isolation (which can be checked by running Veripacks).

However, what if we would like to verify that a 3rd party library is used only in a specific portion of the code? For example, let&#8217;s say we want the Hibernate classes to be used only by the `dao` module, and we want this to be verified at build time. The usual approach could be:

  * create a `dao-api` build module
  * create a `dao` build module with a `dao-api` and Hibernate dependencies
  * implement the DAOs
  * add the `dao-api` module as a dependency to all other modules which use the DAOs
  * add the `dao` module as a dependency to the module which builds the distribution (e.g. war)

If we are using Maven, that&#8217;s quite a lot of xml to write and directories to create. Could it be simpler?

[Veripacks 0.4][1] aims at making this exact case simpler, using package-level annotations. When creating an instance of Veripacks, it is now possible to specify which packages should require importing. To use classes from such packages, you just need to add an `@Import` annotation to the package &#8211; and Veripacks will check that everything is used only where allowed:
```scala
// test case which runs Veripacks
VeripacksBuilder
   .requireImportOf("org.hibernate")
   .build
   .verify("com.foo.project")
   .throwIfNotOk()
```
```scala
// package in which we want to use Hibernate classes
// package-info.java
@Import("org.hibernate")
package com.foo.project.db.dao;

import org.veripacks.Import;
```

Veripacks itself uses these annotations to constraint the usage of the ASM bytecode-reading library; see the [`VeripacksSelfTest`][5] class and the annotation on the [`org.veripacks.reader`][6] package.

The improved process of constraining usage of a 3rd party library code to a specific part of our code now is:

  * create a `dao.api` and `dao.impl` packages (these are just example names; Veripacks doesn&#8217;t require using any specific naming convention)
  * add Hibernate as a dependency to the project
  * implement the DAOs
  * `@Import` the `dao.api` package in packages which use the DAOs

That&#8217;s a lot less XML to write (none), a bit more `package-info.java` to write, and only three directories to create.

Note however, that for project-packages, you should still use the `@RequiresImport` annotation. The `VeripacksBuilder.requireImportOf` and `.doNotRequireImportOf` methods are only intended to be used with 3rd party packages.

The new release also contains two other features. Firstly, it is now possible to skip verification in a class using the `@NotVerified` annotation. This may be useful for bootstrap-like classes, where wiring of the class instances is done, and implementation-classes form various packages are used. 

Secondly, when building Veripacks, you can provide an implementation of the `CustomAccessDefinitionsReader` trait, and specify metadata in a programmatic way. E.g. if the project has a `com.[company].[project].[module].[submodule]` package naming convention, and we would like all module-packages to always require import, instead of adding an `@RequiresImport` annotation to each such package, we can do:
```scala
VeripacksBuilder
  .withCustomAccessDefinitionReader(new CustomAccessDefinitionsReader {
    override def isRequiresImport(pkg: Pkg) = pkg.name.split(".").length == 4
  })
  .build
  .verify(List("com.[company].[project]"))
  .throwIfNotOk()
```

As always Veripacks is available in the [Maven central][7] repository, and the [sources on GitHub][1] under the Apache2 license. Have fun!

Adam

 [1]: https://github.com/adamw/veripacks
 [2]: http://www.warski.org/blog/2013/01/veripacks-0-1-verify-package-specifications/
 [3]: http://www.warski.org/blog/2013/03/veripacks-0-3-importing-packages-transitively-of-course/
 [4]: http://www.warski.org/blog/2013/03/how-to-replace-a-build-module-with-veripacks/
 [5]: https://github.com/adamw/veripacks/blob/master/self-test/src/test/scala/org/veripacks/VeripacksSelfTest.scala
 [6]: https://github.com/adamw/veripacks/blob/master/verifier/src/main/scala/org/veripacks/reader/package-info.java
 [7]: http://search.maven.org/#browse%7C893935363
