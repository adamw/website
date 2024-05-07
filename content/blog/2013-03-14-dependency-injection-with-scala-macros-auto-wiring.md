---
title: 'Dependency injection with Scala macros: auto-wiring'
author: Adam Warski
type: post
date: 2013-03-14T21:33:04+00:00
url: /blog/2013/03/dependency-injection-with-scala-macros-auto-wiring/
simplecatch-sidebarlayout:
  - default
dsq_thread_id:
  - 1137657221
wp-syntax-cache-content:
  - |
    a:6:{i:1;s:903:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">class</span> A
    <span style="color: #0000ff; font-weight: bold;">class</span> B
    <span style="color: #0000ff; font-weight: bold;">class</span> C<span style="color: #F78811;">&#40;</span>a<span style="color: #000080;">:</span> A, b<span style="color: #000080;">:</span> B<span style="color: #F78811;">&#41;</span>
    <span style="color: #0000ff; font-weight: bold;">class</span> D<span style="color: #F78811;">&#40;</span>b<span style="color: #000080;">:</span> B, c<span style="color: #000080;">:</span> C<span style="color: #F78811;">&#41;</span></pre></td></tr></table><p class="theCode" style="display:none;">class A
    class B
    class C(a: A, b: B)
    class D(b: B, c: C)</p></div>
    ";i:2;s:1320:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">val</span> a    <span style="color: #000080;">=</span> wire<span style="color: #F78811;">&#91;</span>A<span style="color: #F78811;">&#93;</span>
    <span style="color: #0000ff; font-weight: bold;">val</span> theB <span style="color: #000080;">=</span> wire<span style="color: #F78811;">&#91;</span>B<span style="color: #F78811;">&#93;</span> <span style="color: #008000; font-style: italic;">// &quot;theB&quot;, not &quot;b&quot;, just to show that we can use any name</span>
    <span style="color: #0000ff; font-weight: bold;">val</span> theC <span style="color: #000080;">=</span> wire<span style="color: #F78811;">&#91;</span>C<span style="color: #F78811;">&#93;</span>
    <span style="color: #0000ff; font-weight: bold;">val</span> d    <span style="color: #000080;">=</span> wire<span style="color: #F78811;">&#91;</span>D<span style="color: #F78811;">&#93;</span></pre></td></tr></table><p class="theCode" style="display:none;">val a    = wire[A]
    val theB = wire[B] // &quot;theB&quot;, not &quot;b&quot;, just to show that we can use any name
    val theC = wire[C]
    val d    = wire[D]</p></div>
    ";i:3;s:1359:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">val</span> a    <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> A<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>
    <span style="color: #0000ff; font-weight: bold;">val</span> theB <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> B<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>
    <span style="color: #0000ff; font-weight: bold;">val</span> theC <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> C<span style="color: #F78811;">&#40;</span>a, theB<span style="color: #F78811;">&#41;</span>
    <span style="color: #0000ff; font-weight: bold;">val</span> d    <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> D<span style="color: #F78811;">&#40;</span>theB, c<span style="color: #F78811;">&#41;</span></pre></td></tr></table><p class="theCode" style="display:none;">val a    = new A()
    val theB = new B()
    val theC = new C(a, theB)
    val d    = new D(theB, c)</p></div>
    ";i:4;s:1482:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    </pre></td><td class="code"><pre class="bash" style="font-family:monospace;"><span style="color: #7a0874; font-weight: bold;">&#91;</span>info<span style="color: #7a0874; font-weight: bold;">&#93;</span> <span style="color: #000000; font-weight: bold;">/</span>Users<span style="color: #000000; font-weight: bold;">/</span>adamw<span style="color: #000000; font-weight: bold;">/</span><span style="color: #7a0874; font-weight: bold;">&#40;</span>...<span style="color: #7a0874; font-weight: bold;">&#41;</span><span style="color: #000000; font-weight: bold;">/</span>DiExample.scala:<span style="color: #000000;">13</span>: Generated code: new C<span style="color: #7a0874; font-weight: bold;">&#40;</span>a, theB<span style="color: #7a0874; font-weight: bold;">&#41;</span>
    <span style="color: #7a0874; font-weight: bold;">&#91;</span>info<span style="color: #7a0874; font-weight: bold;">&#93;</span>   val c = wire<span style="color: #7a0874; font-weight: bold;">&#91;</span>C<span style="color: #7a0874; font-weight: bold;">&#93;</span>
    <span style="color: #7a0874; font-weight: bold;">&#91;</span>info<span style="color: #7a0874; font-weight: bold;">&#93;</span>               ^</pre></td></tr></table><p class="theCode" style="display:none;">[info] /Users/adamw/(...)/DiExample.scala:13: Generated code: new C(a, theB)
    [info]   val c = wire[C]
    [info]               ^</p></div>
    ";i:5;s:1474:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;">Apply<span style="color: #F78811;">&#40;</span>
       Select<span style="color: #F78811;">&#40;</span>New<span style="color: #F78811;">&#40;</span>Ident<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#91;</span><span style="color: #0000ff; font-weight: bold;">class</span><span style="color: #CC66FF;">'s</span> <span style="color: #0000ff; font-weight: bold;">type</span><span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span>, nme.<span style="color: #000000;">CONSTRUCTOR</span><span style="color: #F78811;">&#41;</span>, 
       List<span style="color: #F78811;">&#40;</span>Ident<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#91;</span>arg1<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#41;</span>, Ident<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#91;</span>arg2<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#41;</span>, ...<span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span></pre></td></tr></table><p class="theCode" style="display:none;">Apply(
       Select(New(Ident([class's type])), nme.CONSTRUCTOR), 
       List(Ident([arg1]), Ident([arg2]), ...))</p></div>
    ";i:6;s:3091:"
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
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #008000; font-style: italic;">// &quot;scopes&quot;</span>
    <span style="color: #0000ff; font-weight: bold;">val</span> a <span style="color: #000080;">=</span> wire<span style="color: #F78811;">&#91;</span>X<span style="color: #F78811;">&#93;</span>
    lazy <span style="color: #0000ff; font-weight: bold;">val</span> b <span style="color: #000080;">=</span> wire<span style="color: #F78811;">&#91;</span>Y<span style="color: #F78811;">&#93;</span>
    <span style="color: #0000ff; font-weight: bold;">def</span> c <span style="color: #000080;">=</span> wire<span style="color: #F78811;">&#91;</span>Z<span style="color: #F78811;">&#93;</span> 
    <span style="color: #0000ff; font-weight: bold;">val</span> d <span style="color: #000080;">=</span> provided<span style="color: #F78811;">&#40;</span>manuallyCreatedInstance<span style="color: #F78811;">&#41;</span>
    &nbsp;
    <span style="color: #008000; font-style: italic;">// override a single dependency</span>
    <span style="color: #0000ff; font-weight: bold;">val</span> a <span style="color: #000080;">=</span> wire<span style="color: #F78811;">&#91;</span>X<span style="color: #F78811;">&#93;</span>.<span style="color: #0000ff; font-weight: bold;">with</span><span style="color: #F78811;">&#40;</span>anotherYInstance<span style="color: #F78811;">&#41;</span>
    &nbsp;
    <span style="color: #008000; font-style: italic;">// factories: p1, p2, ... are used in the constructor where needed</span>
    <span style="color: #0000ff; font-weight: bold;">def</span> e<span style="color: #F78811;">&#40;</span>p1<span style="color: #000080;">:</span> T, p2<span style="color: #000080;">:</span> U, ...<span style="color: #F78811;">&#41;</span> <span style="color: #000080;">=</span> wire<span style="color: #F78811;">&#91;</span>X<span style="color: #F78811;">&#93;</span> 
    &nbsp;
    <span style="color: #008000; font-style: italic;">// by-name binding for configuration parameters; whenever a class has a</span>
    <span style="color: #008000; font-style: italic;">// &quot;maxConnections&quot; constructor argument, this value is used.</span>
    <span style="color: #0000ff; font-weight: bold;">val</span> maxConnections <span style="color: #000080;">=</span> conf<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">10</span><span style="color: #F78811;">&#41;</span></pre></td></tr></table><p class="theCode" style="display:none;">// &quot;scopes&quot;
    val a = wire[X]
    lazy val b = wire[Y]
    def c = wire[Z] 
    val d = provided(manuallyCreatedInstance)
    
    // override a single dependency
    val a = wire[X].with(anotherYInstance)
    
    // factories: p1, p2, ... are used in the constructor where needed
    def e(p1: T, p2: U, ...) = wire[X] 
    
    // by-name binding for configuration parameters; whenever a class has a
    // &quot;maxConnections&quot; constructor argument, this value is used.
    val maxConnections = conf(10)</p></div>
    ";}
tags:
  - dependency injection
  - metaprogramming
  - macwire

---
You can look at dependency injection as a fancy name for passing parameters to a function (or constructor arguments to a constructor). However usually, DI containers do much more than that. Among other things, one very nice feature is **auto-wiring**: instantiating the right objects with the right arguments. Most popular frameworks ([Spring][1], [Guice][2], [CDI/Weld][3]) accomplish this task at runtime using reflection.

[rant]  
Doing the wiring at runtime with reflection has its downsides though. Firstly, there&#8217;s **no compile-time checking** that each dependency is satisfied. Secondly, we **loose some of the flexibility** we would have when doing things by hand, as we have to obey the rules by which the objects are created &#8220;automatically&#8221;. For example, if for some reason an object needs to be created manually, this requires a level of indirection (boilerplate), namely a factory. Finally, often the **dependency injection is &#8220;global&#8221;,** that is there is a single container with all the objects, it&#8217;s hard to create local/parametrized &#8220;universes&#8221; (Guice is an exception here). Finally-finally some frameworks do **classpath scanning**, which is slow, and sometimes can give unexpected results.  
[/rant]

Way too magical for such a simple thing. 

But isn&#8217;t what we really want just a way to have all the `new`s with correct parameters generated for us? If you&#8217;re using Scala, and want code generation, the obvious answer are [macros][4]!

To finally show some code, given: 

<pre lang="scala" line="1">class A
class B
class C(a: A, b: B)
class D(b: B, c: C)
</pre>

it would be nice to have:

<pre lang="scala" line="1">val a    = wire[A]
val theB = wire[B] // "theB", not "b", just to show that we can use any name
val theC = wire[C]
val d    = wire[D]
</pre>

transformed to:

<pre lang="scala" line="1">val a    = new A()
val theB = new B()
val theC = new C(a, theB)
val d    = new D(theB, c)
</pre>

Turns out it&#8217;s possible, and even not very complicated.

A proof-of-concept is available on [GitHub][5]. It&#8217;s very primitive and currently supports only one specific way of defining classes/wirings, but works :). If a dependency is missing, there&#8217;s a compile error. To check it out, simply clone the repo, run `sbt` and then invoke the task: `run-main com.softwaremill.di.DiExampleRunner` ([implementation][6]). During compilation, you should see some info messages regarding the generated code, e.g.:

<pre lang="bash" line="1">[info] /Users/adamw/(...)/DiExample.scala:13: Generated code: new C(a, theB)
[info]   val c = wire[C]
[info]               ^
</pre>

and then a proof that indeed the code was generated correctly: when the code is executed, the instances are printed to stdout so that you can see the arguments.

The macro here is of course the `wire` method ([implementation][7]). What it does is it first checks what are the parameters of the constructor of the class, and then for each parameter, tries to find a `val` defined in the enclosing class of the desired type (`findWiredOfType` method; see also [this StackOverflow question][8] why the search is limited to the enclosing class). Finally, it assembles a tree corresponding to invoking the constructor with the right arguments:

<pre lang="scala" line="1">Apply(
   Select(New(Ident([class's type])), nme.CONSTRUCTOR), 
   List(Ident([arg1]), Ident([arg2]), ...))
</pre>

This concept can be extended in many ways. Firstly, by adding support for sub-typing (now only exact type matches will work). Then, there&#8217;s the ability to define the wirings not only in a class, but also in methods; or extending the search to mixed-in traits, so that you could split the `wire` definitions among multiple traits (&#8220;modules&#8221;?). Notice that we could also have full flexibility in how we access the wired valued; it could be a `val`, `lazy val` or a `def`. There&#8217;s also support for scoping, factories, singletons, configurations values, &#8230;; for example:

(_Dependency Injection of the future!_)

<pre lang="scala" line="1">// "scopes"
val a = wire[X]
lazy val b = wire[Y]
def c = wire[Z] 
val d = provided(manuallyCreatedInstance)

// override a single dependency
val a = wire[X].with(anotherYInstance)

// factories: p1, p2, ... are used in the constructor where needed
def e(p1: T, p2: U, ...) = wire[X] 

// by-name binding for configuration parameters; whenever a class has a
// "maxConnections" constructor argument, this value is used.
val maxConnections = conf(10)
</pre>

A recent project by Guice&#8217;s creator, Bob Lee, goes in the same direction. [Dagger][9] (mainly targeted at Android as far as I know) uses an annotation processor to generate the wiring code; at runtime, it&#8217;s just plain constructor invocations, no reflection. Similarly here, with the difference that we use Scala&#8217;s macros. 

What do you think of such an approach to DI?

Adam

 [1]: http://www.springsource.org/
 [2]: https://code.google.com/p/google-guice/
 [3]: http://seamframework.org/Weld
 [4]: http://scalamacros.org/
 [5]: https://github.com/adamw/scala-macro-di
 [6]: https://github.com/adamw/scala-macro-di/blob/2cec0f6ad231ead205fcf47570c1150abbd496ac/examples/src/main/scala/com/softwaremill/di/DiExample.scala
 [7]: https://github.com/adamw/scala-macro-di/blob/2cec0f6ad231ead205fcf47570c1150abbd496ac/macros/src/main/scala/com/softwaremill/di/DiMacro.scala
 [8]: http://stackoverflow.com/questions/15373336/is-it-possible-to-access-the-symbol-table-in-a-macro/
 [9]: http://square.github.com/dagger/
