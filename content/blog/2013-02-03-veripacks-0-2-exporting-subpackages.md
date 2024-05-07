---
title: 'Veripacks 0.2: exporting subpackages'
author: Adam Warski
type: blog
date: 2013-02-03T13:35:31+00:00
url: /blog/2013/02/veripacks-0-2-exporting-subpackages/
slider_fx:
  - fade
slider_autoduration:
  - 2000
dsq_thread_id:
  - 1062688771
wp-syntax-cache-content:
  - |
    a:1:{i:1;s:5063:"
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
    26
    27
    28
    29
    30
    31
    32
    33
    34
    35
    36
    37
    38
    39
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;"><span style="color: #000000; font-weight: bold;">package</span> <span style="color: #006699;">foo.bar.p1.sub_p1</span> <span style="color: #009900;">&#123;</span>
       @Export
       <span style="color: #000000; font-weight: bold;">class</span> Sub1 <span style="color: #009900;">&#123;</span> ... <span style="color: #009900;">&#125;</span>
    <span style="color: #009900;">&#125;</span>
    &nbsp;
    <span style="color: #000000; font-weight: bold;">package</span> <span style="color: #006699;">foo.bar.p1.sub_p2</span> <span style="color: #009900;">&#123;</span>
       @Export
       <span style="color: #000000; font-weight: bold;">class</span> Sub2 <span style="color: #009900;">&#123;</span> ... <span style="color: #009900;">&#125;</span>
    &nbsp;
       <span style="color: #000000; font-weight: bold;">class</span> Sub3 <span style="color: #009900;">&#123;</span> ... <span style="color: #009900;">&#125;</span>
    <span style="color: #009900;">&#125;</span>
    &nbsp;
    <span style="color: #666666; font-style: italic;">// Normally the annotation would go to package-info.java</span>
    @ExportSubpackages<span style="color: #009900;">&#40;</span><span style="color: #0000ff;">&quot;sub_p1&quot;</span><span style="color: #009900;">&#41;</span> 
    <span style="color: #000000; font-weight: bold;">package</span> <span style="color: #006699;">foo.bar.p1</span> <span style="color: #009900;">&#123;</span>
       @Export
       <span style="color: #000000; font-weight: bold;">class</span> A <span style="color: #009900;">&#123;</span> ... 
          <span style="color: #666666; font-style: italic;">// Legal, Sub1 is exported by sub_p1</span>
          <span style="color: #000000; font-weight: bold;">new</span> sub_p1.<span style="color: #006633;">Sub1</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span> 
          <span style="color: #666666; font-style: italic;">// Legal, Sub2 is exported by sub_p2</span>
          <span style="color: #000000; font-weight: bold;">new</span> sub_p2.<span style="color: #006633;">Sub2</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span> 
    &nbsp;
          <span style="color: #666666; font-style: italic;">// Illegal, Sub3 is not exported by sub_p2</span>
          <span style="color: #000000; font-weight: bold;">new</span> sub_p2.<span style="color: #006633;">Sub3</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span> 
       <span style="color: #009900;">&#125;</span>
    <span style="color: #009900;">&#125;</span>
    &nbsp;
    <span style="color: #000000; font-weight: bold;">package</span> <span style="color: #006699;">foo.bar</span> <span style="color: #009900;">&#123;</span>
       <span style="color: #000000; font-weight: bold;">class</span> B <span style="color: #009900;">&#123;</span>
          <span style="color: #666666; font-style: italic;">// Legal, A is exported by p1</span>
          <span style="color: #000000; font-weight: bold;">new</span> p1.<span style="color: #006633;">A</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span> 
    &nbsp;
          <span style="color: #666666; font-style: italic;">// Legal, sub_p1 is exported by p1, and Sub1 is exported by sub_p1 </span>
          <span style="color: #000000; font-weight: bold;">new</span> p1.<span style="color: #006633;">sub_p1</span>.<span style="color: #006633;">Sub1</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span> 
    &nbsp;
          <span style="color: #666666; font-style: italic;">// Illegal, sub_p2 is not exported by p1 </span>
          <span style="color: #000000; font-weight: bold;">new</span> p1.<span style="color: #006633;">sub_p2</span>.<span style="color: #006633;">Sub2</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span> 
       <span style="color: #009900;">&#125;</span>
    <span style="color: #009900;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">package foo.bar.p1.sub_p1 {
       @Export
       class Sub1 { ... }
    }
    
    package foo.bar.p1.sub_p2 {
       @Export
       class Sub2 { ... }
    
       class Sub3 { ... }
    }
    
    // Normally the annotation would go to package-info.java
    @ExportSubpackages(&quot;sub_p1&quot;) 
    package foo.bar.p1 {
       @Export
       class A { ... 
          // Legal, Sub1 is exported by sub_p1
          new sub_p1.Sub1() 
          // Legal, Sub2 is exported by sub_p2
          new sub_p2.Sub2() 
    
          // Illegal, Sub3 is not exported by sub_p2
          new sub_p2.Sub3() 
       }
    }
      
    package foo.bar {
       class B {
          // Legal, A is exported by p1
          new p1.A() 
     
          // Legal, sub_p1 is exported by p1, and Sub1 is exported by sub_p1 
          new p1.sub_p1.Sub1() 
     
          // Illegal, sub_p2 is not exported by p1 
          new p1.sub_p2.Sub2() 
       }
    }</p></div>
    ";}
tags:
  - java
  - testing
  - veripacks
  - modularity

---
[Veripacks 0.1][1] allowed to specify which classes should be exported from a package hierarchy, by using a simple `@Export` annotation, and later verify that the specification is met. [Version 0.2][2] extends this by allowing to export subpackages as well, using the `@ExportSubpackages` package annotation.

When exporting a subpackage, only the classes exported by the subpackage will be re-exported (same holds for sub-subpackages). This is in line with Veripacks respecting the hierarchical structure of packages.

(Previously, either all subpackages and all classes where exported &#8211; when no `@Export` annotations were present, or no subpackages were exported, if at least one `@Export` annotation was present.)

There are also two new convenience package annotations, `@ExportAllClasses` and `@ExportAllSubpackages`, which have pretty self-explaining names.

How does this work? An example will illustrate it best, using some imaginary syntax:

<pre lang="java" line="1">package foo.bar.p1.sub_p1 {
   @Export
   class Sub1 { ... }
}

package foo.bar.p1.sub_p2 {
   @Export
   class Sub2 { ... }

   class Sub3 { ... }
}

// Normally the annotation would go to package-info.java
@ExportSubpackages("sub_p1") 
package foo.bar.p1 {
   @Export
   class A { ... 
      // Legal, Sub1 is exported by sub_p1
      new sub_p1.Sub1() 
      // Legal, Sub2 is exported by sub_p2
      new sub_p2.Sub2() 

      // Illegal, Sub3 is not exported by sub_p2
      new sub_p2.Sub3() 
   }
}
  
package foo.bar {
   class B {
      // Legal, A is exported by p1
      new p1.A() 
 
      // Legal, sub_p1 is exported by p1, and Sub1 is exported by sub_p1 
      new p1.sub_p1.Sub1() 
 
      // Illegal, sub_p2 is not exported by p1 
      new p1.sub_p2.Sub2() 
   }
}
</pre>

How can this be useful? Veripacks goes beyond what can be achieved with normal Java access modifiers (especially public and package-private), by extending the meaning of packages to a hierarchical structure. It also aims to replace, in some cases, the need for separate build modules.

What&#8217;s next? As mentioned in the [README][3], the plans are to add a possibility to require an explicit import of a package, to be able to use its classes. And of course, some IDE support to be able to quickly see what is exported and where would be great.

The release is available in [SoftwareMill&#8217;s maven repository][4], and the code is available under the Apache2 license on [GitHub][2].

Have fun!  
Adam

 [1]: http://www.warski.org/blog/2013/01/veripacks-0-1-verify-package-specifications/
 [2]: https://github.com/adamw/veripacks
 [3]: https://github.com/adamw/veripacks#readme
 [4]: https://nexus.softwaremill.com/content/repositories/releases/
