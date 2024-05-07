---
title: Veripacks 0.1 – Verify Package Specifications
author: Adam Warski
type: blog
date: 2013-01-05T18:31:57+00:00
url: /blog/2013/01/veripacks-0-1-verify-package-specifications/
dsq_thread_id:
  - 1051357075
wp-syntax-cache-content:
  - |
    a:3:{i:1;s:2725:"
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
    13
    14
    15
    16
    17
    18
    19
    20
    21
    22
    23
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;"><span style="color: #000000; font-weight: bold;">package</span> <span style="color: #006699;">foo.bar.p1</span> <span style="color: #009900;">&#123;</span>
       @Export
       <span style="color: #000000; font-weight: bold;">class</span> A <span style="color: #009900;">&#123;</span> ... <span style="color: #009900;">&#125;</span>
    &nbsp;
       <span style="color: #000000; font-weight: bold;">class</span> B <span style="color: #009900;">&#123;</span> ... <span style="color: #009900;">&#125;</span>
    <span style="color: #009900;">&#125;</span>
    &nbsp;
    <span style="color: #000000; font-weight: bold;">package</span> <span style="color: #006699;">foo.bar.p1.sub_p1</span> <span style="color: #009900;">&#123;</span>
       <span style="color: #000000; font-weight: bold;">class</span> C <span style="color: #009900;">&#123;</span> ... <span style="color: #009900;">&#125;</span>
    <span style="color: #009900;">&#125;</span>
    &nbsp;
    <span style="color: #000000; font-weight: bold;">package</span> <span style="color: #006699;">foo.bar.p2</span> <span style="color: #009900;">&#123;</span>
       <span style="color: #000000; font-weight: bold;">class</span> Test <span style="color: #009900;">&#123;</span>
          <span style="color: #666666; font-style: italic;">// ok, A is exported</span>
          <span style="color: #000000; font-weight: bold;">new</span> A<span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span> 
    &nbsp;
          <span style="color: #666666; font-style: italic;">// illegal, B is not exported</span>
          <span style="color: #000000; font-weight: bold;">new</span> B<span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span> 
    &nbsp;
          <span style="color: #666666; font-style: italic;">// illegal, C is in a subpackage of p1, and p1 only exports A </span>
          <span style="color: #000000; font-weight: bold;">new</span> C<span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span> 
       <span style="color: #009900;">&#125;</span>
    <span style="color: #009900;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">package foo.bar.p1 {
       @Export
       class A { ... }
    
       class B { ... }
    }
    
    package foo.bar.p1.sub_p1 {
       class C { ... }
    }
    
    package foo.bar.p2 {
       class Test {
          // ok, A is exported
          new A() 
    
          // illegal, B is not exported
          new B() 
    
          // illegal, C is in a subpackage of p1, and p1 only exports A 
          new C() 
       }
    }</p></div>
    ";i:2;s:1112:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;">    <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000066; font-weight: bold;">void</span> runVeripacksTest<span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
          <span style="color: #000000; font-weight: bold;">new</span> Verifier<span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span>
            .<span style="color: #006633;">verify</span><span style="color: #009900;">&#40;</span><span style="color: #0000ff;">&quot;foo.bar&quot;</span><span style="color: #009900;">&#41;</span>
            .<span style="color: #006633;">throwIfNotOk</span>
        <span style="color: #009900;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">    public void runVeripacksTest() {
          new Verifier()
            .verify(&quot;foo.bar&quot;)
            .throwIfNotOk
        }</p></div>
    ";i:3;s:7710:"
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
    13
    14
    15
    16
    17
    18
    19
    20
    21
    22
    23
    24
    25
    </pre></td><td class="code"><pre class="xml" style="font-family:monospace;">    <span style="color: #808080; font-style: italic;">&lt;!-- Only the annotations --&gt;</span>
        <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;dependency<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
            <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;groupId<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>org.veripacks<span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/groupId<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
            <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;artifactId<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>veripacks-annotations_2.10<span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/artifactId<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
            <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;version<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>0.1<span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/version<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
        <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/dependency<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
    &nbsp;
        <span style="color: #808080; font-style: italic;">&lt;!-- The verifier, has a dependency on the annotations --&gt;</span>
        <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;dependency<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
            <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;groupId<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>org.veripacks<span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/groupId<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
            <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;artifactId<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>veripacks-verifier_2.10<span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/artifactId<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
            <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;version<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>0.1<span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/version<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
            <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;scope<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>test<span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/scope<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
        <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/dependency<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
    &nbsp;
        <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;repository<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
            <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;id<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>SotwareMillPublicReleases<span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/id<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
            <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;name<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>SotwareMill Public Releases<span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/name<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
            <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;url<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>http://nexus.softwaremill.com/content/repositories/releases/<span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/url<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
        <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/repository<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
        <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;repository<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
            <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;id<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>SotwareMillPublicSnapshots<span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/id<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
            <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;name<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>SotwareMill Public Snapshots<span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/name<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
            <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;url<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>http://nexus.softwaremill.com/content/repositories/snapshots/<span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/url<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
        <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/repository<span style="color: #000000; font-weight: bold;">&gt;</span></span></span></pre></td></tr></table><p class="theCode" style="display:none;">    &lt;!-- Only the annotations --&gt;
        &lt;dependency&gt;
            &lt;groupId&gt;org.veripacks&lt;/groupId&gt;
            &lt;artifactId&gt;veripacks-annotations_2.10&lt;/artifactId&gt;
            &lt;version&gt;0.1&lt;/version&gt;
        &lt;/dependency&gt;
        
        &lt;!-- The verifier, has a dependency on the annotations --&gt;
        &lt;dependency&gt;
            &lt;groupId&gt;org.veripacks&lt;/groupId&gt;
            &lt;artifactId&gt;veripacks-verifier_2.10&lt;/artifactId&gt;
            &lt;version&gt;0.1&lt;/version&gt;
            &lt;scope&gt;test&lt;/scope&gt;
        &lt;/dependency&gt;
    
        &lt;repository&gt;
            &lt;id&gt;SotwareMillPublicReleases&lt;/id&gt;
            &lt;name&gt;SotwareMill Public Releases&lt;/name&gt;
            &lt;url&gt;http://nexus.softwaremill.com/content/repositories/releases/&lt;/url&gt;
        &lt;/repository&gt;
        &lt;repository&gt;
            &lt;id&gt;SotwareMillPublicSnapshots&lt;/id&gt;
            &lt;name&gt;SotwareMill Public Snapshots&lt;/name&gt;
            &lt;url&gt;http://nexus.softwaremill.com/content/repositories/snapshots/&lt;/url&gt;
        &lt;/repository&gt;</p></div>
    ";}
tags:
  - java
  - modularity
  - testing
  - veripacks

---
Using some free time during my Christmas & New Year break, I worked on a new project, which implements some of the ideas from my earlier blog [&#8220;Let&#8217;s turn packages into a module system&#8221;][1].

The project is [Veripacks][2]; it allows to specify which classes from a package should be accessible, and verify that the specification is met.

This is similar to **package-private** access in Java, however Veripacks extends this to **subpackages**, respecting package parent-child dependencies. While usually the package is just a string identifier, Veripacks treats packages in a hierarchical way. For example, `foo.bar.baz` is a subpackage of `foo.bar`. That means that exporting a class not only hides other classes from the same package, but also classes from subpackages.

In some cases, Veripacks can be used to replace a separate build module. It aims to be a scalable and composable solution, allowing for multi-layered exports, that is specifying access both for small pieces of code and large functionalities.

Veripacks currently defines two annotations:

  * `@Export` &#8211; applicable to a class, specifies that the annotated class should be visible to other packages. Classes without the annotation but in the same package, and all classes in subpackages won&#8217;t be visible. Several classes in one package can be exported.
  * `@ExportAll` &#8211; applicable to a package (in `package-info.java`), specifies that all classes and subpackages should be exported. Has only documentational significance, as this is the default for all packages if no classes are explicitly exported.

##### Example

Using a bit of an imaginary syntax to make things compact:

<pre lang="java" line="1">package foo.bar.p1 {
   @Export
   class A { ... }

   class B { ... }
}

package foo.bar.p1.sub_p1 {
   class C { ... }
}

package foo.bar.p2 {
   class Test {
      // ok, A is exported
      new A() 

      // illegal, B is not exported
      new B() 

      // illegal, C is in a subpackage of p1, and p1 only exports A 
      new C() 
   }
}
</pre>

##### How to use it?

Veripacks can be used with any language running on the JVM; while written in Scala, it will work without problems in Java-only projects.

No build plugins or such are needed; just create a new test, with the following body:

<pre lang="java" line="1">public void runVeripacksTest() {
      new Verifier()
        .verify("foo.bar")
        .throwIfNotOk
    }
</pre>

This will throw an exception if there are some specification violations. You can also inspect the result of the `verify` call, which contains more detailed information (also included in the exception message).

The project files are deployed to SoftwareMill&#8217;s public Nexus repository:

<pre lang="xml" line="1"><!-- Only the annotations -->
    &lt;dependency>
        &lt;groupId>org.veripacks&lt;/groupId>
        &lt;artifactId>veripacks-annotations_2.10&lt;/artifactId>
        &lt;version>0.1&lt;/version>
    &lt;/dependency>
    
    

<!-- The verifier, has a dependency on the annotations -->
    &lt;dependency>
        &lt;groupId>org.veripacks&lt;/groupId>
        &lt;artifactId>veripacks-verifier_2.10&lt;/artifactId>
        &lt;version>0.1&lt;/version>
        &lt;scope>test&lt;/scope>
    &lt;/dependency>

    &lt;repository>
        &lt;id>SotwareMillPublicReleases&lt;/id>
        &lt;name>SotwareMill Public Releases&lt;/name>
        &lt;url>http://nexus.softwaremill.com/content/repositories/releases/&lt;/url>
    &lt;/repository>
    &lt;repository>
        &lt;id>SotwareMillPublicSnapshots&lt;/id>
        &lt;name>SotwareMill Public Snapshots&lt;/name>
        &lt;url>http://nexus.softwaremill.com/content/repositories/snapshots/&lt;/url>
    &lt;/repository>
</pre>

##### What&#8217;s next?

Of course this is just the beginning. Many more functionalities could be added:

  * allow exporting none/some/all subpackages, along with exporting none/some/all classes
  * add support for importing: 
      * specify that a package can only be used if explicitly imported using a `@RequiresImport` annotation
      * support an `@Import` annotation to specify classes from which packages can be used in a package
  * allow to specify which classes/subpackages are exported in a separate file

The last two points will allow to constrain usage of external libraries. For example, if using Hibernate, we could specify that only classes from the `org.hibernate` package should be accessible, while classes from `org.hibernate.internal` &#8211; not. Furthermore, by specifying that Hibernate needs to be explicitly imported, we could verify that only packages that contain a `@Import("org.hibernate")` can access the Hibernate classes.

This is similar to creating a separate build-module and adding the Hibernate dependency to it only.

The code is available on [GitHub][2]; feel free to use, fork & extend. Also as always waiting for any comments.

Adam

 [1]: http://www.warski.org/blog/2012/11/lets-turn-packages-into-a-module-system/ "Let’s turn packages into a module system!"
 [2]: https://github.com/adamw/veripacks
