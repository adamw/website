---
title: 'Veripacks 0.3: importing packages (transitively, of course)'
author: Adam Warski
type: post
date: 2013-03-12T19:33:36+00:00
url: /blog/2013/03/veripacks-0-3-importing-packages-transitively-of-course/
simplecatch-sidebarlayout:
  - default
dsq_thread_id:
  - 1133250883
wp-syntax-cache-content:
  - |
    a:1:{i:1;s:2960:"
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
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;">@RequiresImport
    <span style="color: #006699;">package</span> <span style="color: #006699;">foo.bar.p1</span> <span style="color: #009900;">&#123;</span>
      @Export
      <span style="color: #000000; font-weight: bold;">class</span> A <span style="color: #009900;">&#123;</span> ... <span style="color: #009900;">&#125;</span>
      <span style="color: #000000; font-weight: bold;">class</span> B <span style="color: #009900;">&#123;</span> ... <span style="color: #009900;">&#125;</span> 
    <span style="color: #009900;">&#125;</span>
    &nbsp;
    @<span style="color: #000000; font-weight: bold;">Import</span><span style="color: #009900;">&#40;</span><span style="color: #0000ff;">&quot;foo.bar.p1&quot;</span><span style="color: #009900;">&#41;</span>
    <span style="color: #000000; font-weight: bold;">package</span> <span style="color: #006699;">foo.bar.p2</span> <span style="color: #009900;">&#123;</span>
      <span style="color: #000000; font-weight: bold;">class</span> Test1 <span style="color: #009900;">&#123;</span>
        <span style="color: #000000; font-weight: bold;">new</span> A<span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span> <span style="color: #666666; font-style: italic;">// OK, p1 is imported and A is exported</span>
        <span style="color: #000000; font-weight: bold;">new</span> B<span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span> <span style="color: #666666; font-style: italic;">// illegal, B is not exported</span>
      <span style="color: #009900;">&#125;</span>
    <span style="color: #009900;">&#125;</span>
    &nbsp;
    <span style="color: #000000; font-weight: bold;">package</span> <span style="color: #006699;">foo.bar.p3</span> <span style="color: #009900;">&#123;</span>
      <span style="color: #000000; font-weight: bold;">class</span> Test2 <span style="color: #009900;">&#123;</span>
        <span style="color: #000000; font-weight: bold;">new</span> A<span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span> <span style="color: #666666; font-style: italic;">// illegal, p1 is not imported</span>
      <span style="color: #009900;">&#125;</span>
    <span style="color: #009900;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">@RequiresImport
    package foo.bar.p1 {
      @Export
      class A { ... }
      class B { ... } 
    }
    
    @Import(&quot;foo.bar.p1&quot;)
    package foo.bar.p2 {
      class Test1 {
        new A(); // OK, p1 is imported and A is exported
        new B(); // illegal, B is not exported
      }
    }
    
    package foo.bar.p3 {
      class Test2 {
        new A(); // illegal, p1 is not imported
      }
    }</p></div>
    ";}
categories:
  - Java
  - Library
  - Maven
  - Scala
  - specification
  - Uncategorized
  - verification
  - veripacks

---
Previous versions of [Veripacks][1] focused on defining what classes are visible outside of a package hierarchy (_exporting_). This release focuses on defining what classes can be used inside a package hierarchy (_importing_).

By default, no imports are required. As long as a class is visible (exported), it can be used. This can be now constrained using the `@RequiresImport` and `@Import` annotations.

If a package is annotated with `@RequiresImport`, exported classes from this package (and any child packages) can only be used if the package is imported using `@Import`. It is illegal to import a package, which doesn&#8217;t require importing. Note that only the package with the annotation can be imported (as a whole), not individual child packages.

As all annotations, the effects of `@Import` are transitive. That is, if a package (A) is imported in a package (B), its classes are also visible in all child packages (of B). 

A simple example, combining the export and import features (which can be used separately as well):

<pre lang="java" line="1">@RequiresImport
package foo.bar.p1 {
  @Export
  class A { ... }
  class B { ... } 
}

@Import("foo.bar.p1")
package foo.bar.p2 {
  class Test1 {
    new A(); // OK, p1 is imported and A is exported
    new B(); // illegal, B is not exported
  }
}

package foo.bar.p3 {
  class Test2 {
    new A(); // illegal, p1 is not imported
  }
}
</pre>

Moreover, Veripacks is now available in [Maven Central][2], so using Veripacks just got easier: no need to add a repository to your build definition. See the README for details.

As always the whole code is available on GitHub: <https://github.com/adamw/veripacks>.

Have fun!

Adam

 [1]: https://github.com/adamw/veripacks
 [2]: http://search.maven.org/#browse%7C1714172582
