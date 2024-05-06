---
title: 'Starting with Scala Macros: a short tutorial'
author: Adam Warski
type: post
date: 2012-12-02T19:22:56+00:00
url: /blog/2012/12/starting-with-scala-macros-a-short-tutorial/
simplecatch-sidebarlayout:
  - default
dsq_thread_id:
  - 1050954025
wp-syntax-cache-content:
  - |
    a:11:{i:1;s:588:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;">println<span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;After register; user = &quot;</span> + user + <span style="color: #6666FF;">&quot;, userCount = &quot;</span> + userCount<span style="color: #F78811;">&#41;</span></pre></td></tr></table><p class="theCode" style="display:none;">println(&quot;After register; user = &quot; + user + &quot;, userCount = &quot; + userCount)</p></div>
    ";i:2;s:467:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;">debug<span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;After register&quot;</span>, user, userCount<span style="color: #F78811;">&#41;</span></pre></td></tr></table><p class="theCode" style="display:none;">debug(&quot;After register&quot;, user, userCount)</p></div>
    ";i:3;s:2173:"
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
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">package</span> com.<span style="color: #000000;">softwaremill</span>.<span style="color: #000000;">debug</span>
    &nbsp;
    <span style="color: #0000ff; font-weight: bold;">import</span> language.<span style="color: #000000;">experimental</span>.<span style="color: #000000;">macros</span>
    &nbsp;
    <span style="color: #0000ff; font-weight: bold;">import</span> reflect.<span style="color: #000000;">macros</span>.<span style="color: #000000;">Context</span>
    &nbsp;
    <span style="color: #0000ff; font-weight: bold;">object</span> DebugMacros <span style="color: #F78811;">&#123;</span>
      <span style="color: #0000ff; font-weight: bold;">def</span> hello<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span><span style="color: #000080;">:</span> Unit <span style="color: #000080;">=</span> macro hello<span style="color: #000080;">_</span>impl
    &nbsp;
      <span style="color: #0000ff; font-weight: bold;">def</span> hello<span style="color: #000080;">_</span>impl<span style="color: #F78811;">&#40;</span>c<span style="color: #000080;">:</span> Context<span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span><span style="color: #000080;">:</span> c.<span style="color: #000000;">Expr</span><span style="color: #F78811;">&#91;</span>Unit<span style="color: #F78811;">&#93;</span> <span style="color: #000080;">=</span> <span style="color: #F78811;">&#123;</span>
        <span style="color: #008000; font-style: italic;">// TODO</span>
      <span style="color: #F78811;">&#125;</span>
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">package com.softwaremill.debug
    
    import language.experimental.macros
    
    import reflect.macros.Context
    
    object DebugMacros {
      def hello(): Unit = macro hello_impl
    
      def hello_impl(c: Context)(): c.Expr[Unit] = {
        // TODO
      }
    }</p></div>
    ";i:4;s:1393:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">def</span> hello<span style="color: #000080;">_</span>impl<span style="color: #F78811;">&#40;</span>c<span style="color: #000080;">:</span> Context<span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span><span style="color: #000080;">:</span> c.<span style="color: #000000;">Expr</span><span style="color: #F78811;">&#91;</span>Unit<span style="color: #F78811;">&#93;</span> <span style="color: #000080;">=</span> <span style="color: #F78811;">&#123;</span>
      <span style="color: #0000ff; font-weight: bold;">import</span> c.<span style="color: #000000;">universe</span>.<span style="color: #000080;">_</span>
      reify <span style="color: #F78811;">&#123;</span> println<span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;Hello World!&quot;</span><span style="color: #F78811;">&#41;</span> <span style="color: #F78811;">&#125;</span>
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">def hello_impl(c: Context)(): c.Expr[Unit] = {
      import c.universe._
      reify { println(&quot;Hello World!&quot;) }
    }</p></div>
    ";i:5;s:764:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">object</span> DebugExample <span style="color: #0000ff; font-weight: bold;">extends</span> App <span style="color: #F78811;">&#123;</span>
      <span style="color: #0000ff; font-weight: bold;">import</span> DebugMacros.<span style="color: #000080;">_</span>
      hello<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">object DebugExample extends App {
      import DebugMacros._
      hello()
    }</p></div>
    ";i:6;s:1985:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    6
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">def</span> printparam<span style="color: #F78811;">&#40;</span>param<span style="color: #000080;">:</span> Any<span style="color: #F78811;">&#41;</span><span style="color: #000080;">:</span> Unit <span style="color: #000080;">=</span> macro printparam<span style="color: #000080;">_</span>impl
    &nbsp;
    <span style="color: #0000ff; font-weight: bold;">def</span> printparam<span style="color: #000080;">_</span>impl<span style="color: #F78811;">&#40;</span>c<span style="color: #000080;">:</span> Context<span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#40;</span>param<span style="color: #000080;">:</span> c.<span style="color: #000000;">Expr</span><span style="color: #F78811;">&#91;</span>Any<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#41;</span><span style="color: #000080;">:</span> c.<span style="color: #000000;">Expr</span><span style="color: #F78811;">&#91;</span>Unit<span style="color: #F78811;">&#93;</span> <span style="color: #000080;">=</span> <span style="color: #F78811;">&#123;</span>
      <span style="color: #0000ff; font-weight: bold;">import</span> c.<span style="color: #000000;">universe</span>.<span style="color: #000080;">_</span>
      reify <span style="color: #F78811;">&#123;</span> println<span style="color: #F78811;">&#40;</span>param.<span style="color: #000000;">splice</span><span style="color: #F78811;">&#41;</span> <span style="color: #F78811;">&#125;</span>
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">def printparam(param: Any): Unit = macro printparam_impl
    
    def printparam_impl(c: Context)(param: c.Expr[Any]): c.Expr[Unit] = {
      import c.universe._
      reify { println(param.splice) }
    }</p></div>
    ";i:7;s:3147:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    6
    7
    8
    9
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">def</span> debug<span style="color: #F78811;">&#40;</span>param<span style="color: #000080;">:</span> Any<span style="color: #F78811;">&#41;</span><span style="color: #000080;">:</span> Unit <span style="color: #000080;">=</span> macro debug<span style="color: #000080;">_</span>impl
    &nbsp;
    <span style="color: #0000ff; font-weight: bold;">def</span> debug<span style="color: #000080;">_</span>impl<span style="color: #F78811;">&#40;</span>c<span style="color: #000080;">:</span> Context<span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#40;</span>param<span style="color: #000080;">:</span> c.<span style="color: #000000;">Expr</span><span style="color: #F78811;">&#91;</span>Any<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#41;</span><span style="color: #000080;">:</span> c.<span style="color: #000000;">Expr</span><span style="color: #F78811;">&#91;</span>Unit<span style="color: #F78811;">&#93;</span> <span style="color: #000080;">=</span> <span style="color: #F78811;">&#123;</span>
      <span style="color: #0000ff; font-weight: bold;">import</span> c.<span style="color: #000000;">universe</span>.<span style="color: #000080;">_</span>
      <span style="color: #0000ff; font-weight: bold;">val</span> paramRep <span style="color: #000080;">=</span> show<span style="color: #F78811;">&#40;</span>param.<span style="color: #000000;">tree</span><span style="color: #F78811;">&#41;</span>
      <span style="color: #0000ff; font-weight: bold;">val</span> paramRepTree <span style="color: #000080;">=</span> Literal<span style="color: #F78811;">&#40;</span>Constant<span style="color: #F78811;">&#40;</span>paramRep<span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span>
      <span style="color: #0000ff; font-weight: bold;">val</span> paramRepExpr <span style="color: #000080;">=</span> c.<span style="color: #000000;">Expr</span><span style="color: #F78811;">&#91;</span>String<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#40;</span>paramRepTree<span style="color: #F78811;">&#41;</span>
      reify <span style="color: #F78811;">&#123;</span> println<span style="color: #F78811;">&#40;</span>paramRepExpr.<span style="color: #000000;">splice</span> + <span style="color: #6666FF;">&quot; = &quot;</span> + param.<span style="color: #000000;">splice</span><span style="color: #F78811;">&#41;</span> <span style="color: #F78811;">&#125;</span>
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">def debug(param: Any): Unit = macro debug_impl
    
    def debug_impl(c: Context)(param: c.Expr[Any]): c.Expr[Unit] = {
      import c.universe._
      val paramRep = show(param.tree)
      val paramRepTree = Literal(Constant(paramRep))
      val paramRepExpr = c.Expr[String](paramRepTree)
      reify { println(paramRepExpr.splice + &quot; = &quot; + param.splice) }
    }</p></div>
    ";i:8;s:1621:"
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
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">object</span> DebugExample <span style="color: #0000ff; font-weight: bold;">extends</span> App <span style="color: #F78811;">&#123;</span>
      <span style="color: #0000ff; font-weight: bold;">import</span> DebugMacros.<span style="color: #000080;">_</span>
    &nbsp;
      <span style="color: #0000ff; font-weight: bold;">val</span> y <span style="color: #000080;">=</span> <span style="color: #F78811;">10</span>
    &nbsp;
      <span style="color: #0000ff; font-weight: bold;">def</span> test<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span> <span style="color: #F78811;">&#123;</span>
        <span style="color: #0000ff; font-weight: bold;">val</span> p <span style="color: #000080;">=</span> <span style="color: #F78811;">11</span>
        debug1<span style="color: #F78811;">&#40;</span>p<span style="color: #F78811;">&#41;</span>
        debug1<span style="color: #F78811;">&#40;</span>p + y<span style="color: #F78811;">&#41;</span>
      <span style="color: #F78811;">&#125;</span>
    &nbsp;
      test<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">object DebugExample extends App {
      import DebugMacros._
    
      val y = 10
    
      def test() {
        val p = 11
        debug1(p)
        debug1(p + y)
      }
    
      test()
    }</p></div>
    ";i:9;s:646:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;">p <span style="color: #000080;">=</span> <span style="color: #F78811;">11</span>
    p.+<span style="color: #F78811;">&#40;</span>DebugExample.<span style="color: #0000ff; font-weight: bold;">this</span>.<span style="color: #000000;">y</span><span style="color: #F78811;">&#41;</span> <span style="color: #000080;">=</span> <span style="color: #F78811;">21</span></pre></td></tr></table><p class="theCode" style="display:none;">p = 11
    p.+(DebugExample.this.y) = 21</p></div>
    ";i:10;s:467:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;">debug<span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;After register&quot;</span>, user, userCount<span style="color: #F78811;">&#41;</span></pre></td></tr></table><p class="theCode" style="display:none;">debug(&quot;After register&quot;, user, userCount)</p></div>
    ";i:11;s:541:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;">AfterRegister, user <span style="color: #000080;">=</span> User<span style="color: #F78811;">&#40;</span>x, y<span style="color: #F78811;">&#41;</span>, userCount <span style="color: #000080;">=</span> <span style="color: #F78811;">1029</span></pre></td></tr></table><p class="theCode" style="display:none;">AfterRegister, user = User(x, y), userCount = 1029</p></div>
    ";}
dsq_needs_sync:
  - 1
categories:
  - Java
  - Macros
  - Scala
  - Uncategorized

---
Using some time during the weekend, I decided to finally explore one the new features in the coming Scala 2.10, [macros][1]. Macros are also written in Scala so in essence a macro is a piece of Scala code, executed at compile-time, which manipulates and modifies the AST of a Scala program.

To do something useful, I wanted to implement a simple macro for debugging; I suppose I&#8217;m not alone in using println-debugging, that is debugging by inserting statements like:

<pre lang="scala" line="1">println("After register; user = " + user + ", userCount = " + userCount)
</pre>

running a test, and checking what the output is. Writing the variable name before the variable is tedious, so I wanted to write a macro which would do that for me; that is:

<pre lang="scala" line="1">debug("After register", user, userCount)
</pre>

should have the same effect as the first snippet (it should generate code similar to the one above). 

Let&#8217;s see step-by-step how to implement such a macro. There&#8217;s a good [getting started][2] guide on the scala macros page, which I used. All code explained below is available on GitHub, in the [scala-macro-debug project][3].

### 1. Project setup

To experiment comfortably we&#8217;ll need to setup a simple project first. We will need at least two subprojects: one for macros, and one for testing the macros. That is because the macros must be compiled separately and before and code that uses them (as they influence the compilation process).

Moreover, the macro subproject needs to have a dependency on scala-compiler, to be able to access the reflection and AST classes.

A simple [SBT][4] build file could look like this: [Build.scala][5].

### 2. Hello World!

&#8220;Hello World!&#8221; is always a great starting point. So my first step was to write a macro, which would expand `hello()` to `println("Hello World!")` at compile-time.

In the macros subproject, we have to create a new object, which defines `hello()` and the macro:

<pre lang="scala" line="1">package com.softwaremill.debug

import language.experimental.macros

import reflect.macros.Context

object DebugMacros {
  def hello(): Unit = macro hello_impl

  def hello_impl(c: Context)(): c.Expr[Unit] = {
    // TODO
  }
}
</pre>

There are a couple of important things here:

  1. we have to import `language.experimental.macros`, to enable the macros feature in the given source file. Otherwise we&#8217;ll get compilation errors reminding us about the import.
  2. the definition of `hello()` uses the `macro` keyword, followed by a method which implements the macro
  3. the macro implementation has two parameter lists: the first is the context (you can think about it as a compilation context), the second mirrors the parameter list of our method &#8211; here it&#8217;s empty. Finally, the return type must also match &#8211; however in the method we have a return type unit, in the macro we return an expression (which wraps a piece of an AST) of type unit.

Now to the implementation, which is pretty short:

<pre lang="scala" line="1">def hello_impl(c: Context)(): c.Expr[Unit] = {
  import c.universe._
  reify { println("Hello World!") }
}
</pre>

Going line by line:

  1. first we import the &#8220;universe&#8221;, which gives convenient access to AST classes. Note that the return type is `c.Expr` &#8211; so it&#8217;s a path-dependent type, taken from the context. You&#8217;ll see that import in every macro.
  2. as we want to generate code which prints &#8220;Hello World!&#8221;, we need to create an AST for it. Instead of constructing it manually (which is possible, but doesn&#8217;t look too nice), Scala provides a `reify` method (reify is also a macro &#8211; a macro used when compiling macros :) ), which turns the given code into an `Expr[T]` (expressions wrap an AST and its type). As `println` has type unit, the reified expression has type `Expr[Unit]`, and we can just return it.

Usage is pretty simple. In the testing subproject, write the following:

<pre lang="scala" line="1">object DebugExample extends App {
  import DebugMacros._
  hello()
}
</pre>

and run the code (e.g. with the `run` command in SBT shell).

### 3. Printing out a parameter

Printing Hello World is nice, but it&#8217;s even nicer to print a parameter. The second macro will do just that: it will transform `printparam(anything)` into `println(anything)`. Not very useful, and pretty similar to what we&#8217;ve seen, with two crucial differences:

<pre lang="scala" line="1">def printparam(param: Any): Unit = macro printparam_impl

def printparam_impl(c: Context)(param: c.Expr[Any]): c.Expr[Unit] = {
  import c.universe._
  reify { println(param.splice) }
}
</pre>

The first difference is that the method accepts a parameter `param: Any`. In the macro implementation, we have to mirror that &#8211; but same as with the return type, instead of `Any`, we accept an `Expr[Any]`, as during compile-time we operate on ASTs.

The second difference is the usage of `splice`. It is a special method of `Expr`, which can only be used inside a `reify` call, and does kind of the opposite of reify: it embeds the given expression into the code that is being reified. Here, we have `param` which is an `Expr` (that is, tree + type), and we want to put that tree as a child of `println`; we want the value that is represented by `param` to be passed to `println`, not the AST. `splice` called on an `Expr[T]` returns a `T`, so the reified code type-checks.

### 4. Single-variable debug

Let&#8217;s now get to our debug method. First maybe let&#8217;s implement a single-variable debug, that is `debug(x)` should be transformed into something like `println("x = " + x)`.

Here&#8217;s the macro:

<pre lang="scala" line="1">def debug(param: Any): Unit = macro debug_impl

def debug_impl(c: Context)(param: c.Expr[Any]): c.Expr[Unit] = {
  import c.universe._
  val paramRep = show(param.tree)
  val paramRepTree = Literal(Constant(paramRep))
  val paramRepExpr = c.Expr[String](paramRepTree)
  reify { println(paramRepExpr.splice + " = " + param.splice) }
}
</pre>

The new thing is of course generating the prefix. To do that, we first turn the parameter&#8217;s tree into a `String`. The built-in method `show` does exactly that. A little note here; as we are turning an AST into a `String`, the output may look a bit different than in the original code. For vals declared inside a method, it will return simply the val name. For class fields, you&#8217;ll see something like `DebugExample.this.myField`. For expressions, e.g. `left + right`, you&#8217;ll see `left.+(right)`. Not perfect, but readable enough I think.

Secondly, we need to create a tree (by hand this time) representing a constant `String`. Here you just have to know what to construct, e.g. by inspecting trees created by reification (or reading Scala compiler&#8217;s source code ;) ).

Finally, we turn that simple tree into an expression of type `String`, and splice it inside the `println`. Running for example such code:

<pre lang="scala" line="1">object DebugExample extends App {
  import DebugMacros._

  val y = 10

  def test() {
    val p = 11
    debug1(p)
    debug1(p + y)
  }

  test()
}
</pre>

outputs:

<pre lang="scala" line="1">p = 11
p.+(DebugExample.this.y) = 21
</pre>

### 5. Final product

Implementing the full debug macro, as described above, introduces only one new concept. The full source is a bit long, so you can [view it on GitHub][6].

In the macro implementation we first generate a tree (AST) for each parameter &#8211; which represents either printing a constant, or an expression. Then we interleave the trees with separators (`", "`) for easier reading.

Finally, we have to turn the list of trees into an expression. To do that, we create a `Block`. A block takes a list of statements that should be executed, and an expression which is a result of the whole block. In our case the result is of course `()`.

And now we can happily debug! For example, writing:

<pre lang="scala" line="1">debug("After register", user, userCount)
</pre>

will print, when executed:

<pre lang="scala" line="1">AfterRegister, user = User(x, y), userCount = 1029
</pre>

### Summing up

That&#8217;s quite a long post, glad somebody made it that far :). Anyway, macros look really interesting, and it&#8217;s pretty simple to start writing macros on your own. You can find a simple SBT project plus the code discussed here on GitHub ([scala-macro-debug project][7]). And I suppose soon we&#8217;ll see an outcrop of macro-leveraging projects. Already there are some, for example [Expecty][8] or [Macrocosm][9].

**EDIT** 9/3/2013: the macro is now available in Maven central, see the [README][3].

 [1]: http://scalamacros.org/
 [2]: http://scalamacros.org/documentation/gettingstarted.html
 [3]: https://github.com/adamw/scala-macro-debug
 [4]: http://www.scala-sbt.org/
 [5]: https://github.com/adamw/scala-macro-debug/blob/7c36fca26ca934ba2eb5dc2036eb67c8475941ec/project/Build.scala
 [6]: https://github.com/adamw/scala-macro-debug/blob/15fc8af48a6e7772c44b726513949a8ac9c9e3b7/macros/src/main/scala/com/softwaremill/debug/DebugMacros.scala
 [7]: https://github.com/adamw/scala-macro-debug/
 [8]: https://github.com/pniederw/expecty
 [9]: https://github.com/retronym/macrocosm
