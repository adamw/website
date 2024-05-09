---
title: Automatic generation of delegate methods with Macro Annotations
author: Adam Warski
type: blog
date: 2013-09-07T12:03:37+00:00
url: /blog/2013/09/automatic-generation-of-delegate-methods-with-macro-annotations/
simplecatch-sidebarlayout:
  - default
disqus_identifier:
  - 1726022073
wp-syntax-cache-content:
  - |
    a:4:{i:1;s:2530:"
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
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">trait</span> Foo <span style="color: #F78811;">&#123;</span>
       <span style="color: #0000ff; font-weight: bold;">def</span> method1<span style="color: #F78811;">&#40;</span>param1<span style="color: #000080;">:</span> String<span style="color: #F78811;">&#41;</span><span style="color: #000080;">:</span> Int
       <span style="color: #0000ff; font-weight: bold;">def</span> method2<span style="color: #F78811;">&#40;</span>p1<span style="color: #000080;">:</span> Int, p2<span style="color: #000080;">:</span> Long<span style="color: #F78811;">&#41;</span><span style="color: #000080;">:</span> Float
       <span style="color: #0000ff; font-weight: bold;">def</span> method3<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span><span style="color: #000080;">:</span> String
    <span style="color: #F78811;">&#125;</span>
    &nbsp;
    <span style="color: #0000ff; font-weight: bold;">class</span> FooImpl <span style="color: #0000ff; font-weight: bold;">extends</span> Foo <span style="color: #F78811;">&#123;</span>
       <span style="color: #0000ff; font-weight: bold;">def</span> method1<span style="color: #F78811;">&#40;</span>param1<span style="color: #000080;">:</span> String<span style="color: #F78811;">&#41;</span> <span style="color: #000080;">=</span> param1.<span style="color: #000000;">length</span>
       <span style="color: #0000ff; font-weight: bold;">def</span> method2<span style="color: #F78811;">&#40;</span>p1<span style="color: #000080;">:</span> Int, p2<span style="color: #000080;">:</span> Long<span style="color: #F78811;">&#41;</span> <span style="color: #000080;">=</span> p1 + p2
       <span style="color: #0000ff; font-weight: bold;">def</span> method3<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span> <span style="color: #000080;">=</span> <span style="color: #6666FF;">&quot;Hello World!&quot;</span>
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">trait Foo {
       def method1(param1: String): Int
       def method2(p1: Int, p2: Long): Float
       def method3(): String
    }
    
    class FooImpl extends Foo {
       def method1(param1: String) = param1.length
       def method2(p1: Int, p2: Long) = p1 + p2
       def method3() = &quot;Hello World!&quot;
    }</p></div>
    ";i:2;s:1968:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">class</span> FooWrapper<span style="color: #F78811;">&#40;</span>wrapped<span style="color: #000080;">:</span> Foo<span style="color: #F78811;">&#41;</span> <span style="color: #0000ff; font-weight: bold;">extends</span> Foo <span style="color: #F78811;">&#123;</span>
       <span style="color: #0000ff; font-weight: bold;">def</span> method1<span style="color: #F78811;">&#40;</span>param1<span style="color: #000080;">:</span> String<span style="color: #F78811;">&#41;</span> <span style="color: #000080;">=</span> wrapped.<span style="color: #000000;">method1</span><span style="color: #F78811;">&#40;</span>param1<span style="color: #F78811;">&#41;</span>
       <span style="color: #0000ff; font-weight: bold;">def</span> method2<span style="color: #F78811;">&#40;</span>p1<span style="color: #000080;">:</span> Int, p2<span style="color: #000080;">:</span> Long<span style="color: #F78811;">&#41;</span> <span style="color: #000080;">=</span> wrapped.<span style="color: #000000;">method2</span><span style="color: #F78811;">&#40;</span>p1, p2<span style="color: #F78811;">&#41;</span>
       <span style="color: #0000ff; font-weight: bold;">def</span> method3<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span> <span style="color: #000080;">=</span> wrapped.<span style="color: #000000;">method3</span><span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">class FooWrapper(wrapped: Foo) extends Foo {
       def method1(param1: String) = wrapped.method1(param1)
       def method2(p1: Int, p2: Long) = wrapped.method2(p1, p2)
       def method3() = wrapped.method3()
    }</p></div>
    ";i:3;s:1057:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">class</span> FooWrapper<span style="color: #F78811;">&#40;</span><span style="color: #000080;">@</span>delegate wrapped<span style="color: #000080;">:</span> Foo<span style="color: #F78811;">&#41;</span> <span style="color: #0000ff; font-weight: bold;">extends</span> Foo <span style="color: #F78811;">&#123;</span>
       <span style="color: #008000; font-style: italic;">// method1, method2 and method3 are generated at compile time</span>
       <span style="color: #008000; font-style: italic;">// and delegate to the annotated parameter</span>
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">class FooWrapper(@delegate wrapped: Foo) extends Foo {
       // method1, method2 and method3 are generated at compile time
       // and delegate to the annotated parameter
    }</p></div>
    ";i:4;s:1205:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">class</span> FooWrapper<span style="color: #F78811;">&#40;</span><span style="color: #000080;">@</span>delegate wrapped<span style="color: #000080;">:</span> Foo<span style="color: #F78811;">&#41;</span> <span style="color: #0000ff; font-weight: bold;">extends</span> Foo <span style="color: #F78811;">&#123;</span>
       <span style="color: #0000ff; font-weight: bold;">def</span> method2<span style="color: #F78811;">&#40;</span>p1<span style="color: #000080;">:</span> Int, p2<span style="color: #000080;">:</span> Long<span style="color: #F78811;">&#41;</span> <span style="color: #000080;">=</span> p1 - p1
       <span style="color: #008000; font-style: italic;">// only method1 and method3 are generated</span>
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">class FooWrapper(@delegate wrapped: Foo) extends Foo {
       def method2(p1: Int, p2: Long) = p1 - p1
       // only method1 and method3 are generated
    }</p></div>
    ";}
tags:
  - metaprogramming
  - annotations
  - scala

---
[Macro Annotations][1] are a new type of macros, which are one of the candidates for inclusion (see also comment by Eugene below) in the upcoming Scala 2.11 release. However, thanks to the recently released [Macro Paradise Scala 2.10 compiler plugin][2], with an extra option in the compiler/SBT settings, you can use them today, while still using a stable Scala version at runtime.

One of the Macro Annotations use-cases mentioned in the manual is compile-time AOP. I decided to try implementing something similar, but a bit simpler for a start: automatic generation of delegate methods (decorator pattern/proxy pattern). In fact, some years ago there was a similar effort using a compiler plugin ([autoproxy plugin][3]). As an additional motivation, [Łukasz][4] recently asked on our technical room if Scala has this exact functionality &#8211; instead of saying &#8220;No&#8221;, I should have said &#8220;Not yet&#8221; ;).

The results of the POC are available on GitHub, in the scala-macro-aop repository: <https://github.com/adamw/scala-macro-aop>. If you have SBT, you can play with the implementation just by invoking `run` from the SBT console.

How does it work? Let&#8217;s say we have an interface `Foo` with three methods (with very original names: `method1`, `method2` and `method3`), each taking some parameters. We have a default implementation:
```scala
trait Foo {
   def method1(param1: String): Int
   def method2(p1: Int, p2: Long): Float
   def method3(): String
}

class FooImpl extends Foo {
   def method1(param1: String) = param1.length
   def method2(p1: Int, p2: Long) = p1 + p2
   def method3() = "Hello World!"
}
```

Now we would like to create a wrapper for a `Foo` instance, which would delegate all method calls to the given instance, unless the method is defined in the wrapper.

The traditional solution is to create a delegate for each method by hand, e.g.:
```scala
class FooWrapper(wrapped: Foo) extends Foo {
   def method1(param1: String) = wrapped.method1(param1)
   def method2(p1: Int, p2: Long) = wrapped.method2(p1, p2)
   def method3() = wrapped.method3()
}
```

But that&#8217;s a lot of work. Using the `@delegate` macro, the delegate methods will now be automatically generated **at compile time**! That is, the wrapper now becomes:
```scala
class FooWrapper(@delegate wrapped: Foo) extends Foo {
   // method1, method2 and method3 are generated at compile time
   // and delegate to the annotated parameter
}
```

What if we want to implement some methods? The macro will generate only the missing ones:
```scala
class FooWrapper(@delegate wrapped: Foo) extends Foo {
   def method2(p1: Int, p2: Long) = p1 - p1
   // only method1 and method3 are generated
}
```

As the implementation is just a POC, it will only work in simple cases, that is for methods with a single parameter list, without type parameters and when the method is not overloaded. Plus the code of the macro is, let&#8217;s say, &#8220;not yet polished&#8221; ;).

As mentioned before, the code is on GitHub: <https://github.com/adamw/scala-macro-aop>, available under the Apache2 license.

Adam

UPDATE 9/9/2013: clarifying Macro Annotations availability in 2.11

 [1]: http://docs.scala-lang.org/overviews/macros/annotations.html
 [2]: http://docs.scala-lang.org/overviews/macros/paradise.html
 [3]: https://github.com/kevinwright/Autoproxy-Lite
 [4]: https://twitter.com/Zuchos
