---
title: 'Nonnull-check generator: a scala compiler plugin'
author: Adam Warski
type: post
date: 2009-10-15T15:04:45+00:00
url: /blog/2009/10/nonnull-check-generator-a-scala-compiler-plugin/
dsq_thread_id:
  - 1051176035
wp-syntax-cache-content:
  - |
    a:6:{i:1;s:848:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;">def parameterMustBeNull<span style="color: #009900;">&#40;</span>@Nonnull parameter <span style="color: #339933;">:</span> <span style="color: #003399;">Object</span><span style="color: #009900;">&#41;</span> <span style="color: #339933;">=</span> <span style="color: #009900;">&#123;</span>
     parameter.<span style="color: #006633;">toString</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
    <span style="color: #009900;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">def parameterMustBeNull(@Nonnull parameter : Object) = {
     parameter.toString();
    }</p></div>
    ";i:2;s:1577:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;">def parameterMustBeNull<span style="color: #009900;">&#40;</span>@Nonnull parameter <span style="color: #339933;">:</span> <span style="color: #003399;">Object</span><span style="color: #009900;">&#41;</span> <span style="color: #339933;">=</span> <span style="color: #009900;">&#123;</span>
     <span style="color: #000000; font-weight: bold;">if</span> <span style="color: #009900;">&#40;</span>param <span style="color: #339933;">==</span> <span style="color: #000066; font-weight: bold;">null</span><span style="color: #009900;">&#41;</span>
      <span style="color: #000000; font-weight: bold;">throw</span> <span style="color: #000000; font-weight: bold;">new</span> <span style="color: #003399;">IllegalArgumentException</span><span style="color: #009900;">&#40;</span><span style="color: #0000ff;">&quot;Parameter 'parameter' should not be null.&quot;</span><span style="color: #009900;">&#41;</span>
     parameter.<span style="color: #006633;">toString</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
    <span style="color: #009900;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">def parameterMustBeNull(@Nonnull parameter : Object) = {
     if (param == null)
      throw new IllegalArgumentException(&quot;Parameter 'parameter' should not be null.&quot;)
     parameter.toString();
    }</p></div>
    ";i:3;s:2235:"
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
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;">object AnnotationsCheckGenComponent <span style="color: #000000; font-weight: bold;">extends</span> PluginComponent with Transform <span style="color: #009900;">&#123;</span>
     ...
     <span style="color: #006633;">object</span> AnnotationsCheckGenTransformer <span style="color: #000000; font-weight: bold;">extends</span> Transformer <span style="color: #009900;">&#123;</span>
      override def transform<span style="color: #009900;">&#40;</span>tree<span style="color: #339933;">:</span> Tree<span style="color: #009900;">&#41;</span> <span style="color: #339933;">=</span> <span style="color: #009900;">&#123;</span>
       tree match <span style="color: #009900;">&#123;</span>
        <span style="color: #666666; font-style: italic;">// DefDef is a method definition</span>
        <span style="color: #000000; font-weight: bold;">case</span> dd @ DefDef<span style="color: #009900;">&#40;</span>_, _, _, vparamss, _, _<span style="color: #009900;">&#41;</span> <span style="color: #339933;">=&gt;</span> <span style="color: #009900;">&#123;</span>
         <span style="color: #666666; font-style: italic;">// here we add the nonnull checks</span>
        <span style="color: #009900;">&#125;</span>
        <span style="color: #000000; font-weight: bold;">case</span> t <span style="color: #339933;">=&gt;</span> <span style="color: #000000; font-weight: bold;">super</span>.<span style="color: #006633;">transform</span><span style="color: #009900;">&#40;</span>t<span style="color: #009900;">&#41;</span>
      <span style="color: #009900;">&#125;</span>
    <span style="color: #009900;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">object AnnotationsCheckGenComponent extends PluginComponent with Transform {
     ...
     object AnnotationsCheckGenTransformer extends Transformer {
      override def transform(tree: Tree) = {
       tree match {
        // DefDef is a method definition
        case dd @ DefDef(_, _, _, vparamss, _, _) =&gt; {
         // here we add the nonnull checks
        }
        case t =&gt; super.transform(t)
      }
    }</p></div>
    ";i:4;s:2877:"
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
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;">vparamss.<span style="color: #006633;">flatten</span><span style="color: #009900;">&#91;</span>ValDef<span style="color: #009900;">&#93;</span>.<span style="color: #006633;">filter</span><span style="color: #009900;">&#40;</span>param <span style="color: #339933;">=&gt;</span> <span style="color: #009900;">&#123;</span>
     <span style="color: #666666; font-style: italic;">// Checking if the parameter contains a non-null annotation</span>
     param.<span style="color: #006633;">mods</span>.<span style="color: #006633;">annotations</span>.<span style="color: #006633;">exists</span><span style="color: #009900;">&#40;</span>annotation <span style="color: #339933;">=&gt;</span> <span style="color: #009900;">&#123;</span>
      <span style="color: #666666; font-style: italic;">// Checking if the annotation is a non-null annotation</span>
      annotation.<span style="color: #006633;">constr</span>.<span style="color: #006633;">find</span><span style="color: #009900;">&#40;</span>tree <span style="color: #339933;">=&gt;</span> <span style="color: #009900;">&#123;</span>
       tree match <span style="color: #009900;">&#123;</span>
        <span style="color: #000000; font-weight: bold;">case</span> Ident<span style="color: #009900;">&#40;</span>name<span style="color: #009900;">&#41;</span> <span style="color: #339933;">=&gt;</span> <span style="color: #0000ff;">&quot;No[nt][Nn]ull&quot;</span>.<span style="color: #006633;">r</span>.<span style="color: #006633;">findFirstIn</span><span style="color: #009900;">&#40;</span>name.<span style="color: #006633;">toString</span><span style="color: #009900;">&#41;</span>.<span style="color: #006633;">isDefined</span>
        <span style="color: #000000; font-weight: bold;">case</span> _ <span style="color: #339933;">=&gt;</span> <span style="color: #000066; font-weight: bold;">false</span>
       <span style="color: #009900;">&#125;</span>
      <span style="color: #009900;">&#125;</span><span style="color: #009900;">&#41;</span>.<span style="color: #006633;">isDefined</span>
     <span style="color: #009900;">&#125;</span><span style="color: #009900;">&#41;</span>
    <span style="color: #009900;">&#125;</span><span style="color: #009900;">&#41;</span></pre></td></tr></table><p class="theCode" style="display:none;">vparamss.flatten[ValDef].filter(param =&gt; {
     // Checking if the parameter contains a non-null annotation
     param.mods.annotations.exists(annotation =&gt; {
      // Checking if the annotation is a non-null annotation
      annotation.constr.find(tree =&gt; {
       tree match {
        case Ident(name) =&gt; &quot;No[nt][Nn]ull&quot;.r.findFirstIn(name.toString).isDefined
        case _ =&gt; false
       }
      }).isDefined
     })
    })</p></div>
    ";i:5;s:3417:"
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
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;"><span style="color: #000000; font-weight: bold;">If</span><span style="color: #009900;">&#40;</span>
     <span style="color: #666666; font-style: italic;">// if: param == ...</span>
     Apply<span style="color: #009900;">&#40;</span>
      Select<span style="color: #009900;">&#40;</span>
       Ident<span style="color: #009900;">&#40;</span>param.<span style="color: #006633;">name</span><span style="color: #009900;">&#41;</span>,
       newTermName<span style="color: #009900;">&#40;</span><span style="color: #0000ff;">&quot;$eq$eq&quot;</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span>,
      <span style="color: #003399;">List</span><span style="color: #009900;">&#40;</span>Literal<span style="color: #009900;">&#40;</span>Constant<span style="color: #009900;">&#40;</span><span style="color: #000066; font-weight: bold;">null</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span>
     <span style="color: #009900;">&#41;</span>,
     <span style="color: #666666; font-style: italic;">// then: throw ...</span>
     <span style="color: #000000; font-weight: bold;">Throw</span><span style="color: #009900;">&#40;</span>
      Apply<span style="color: #009900;">&#40;</span>
       Select<span style="color: #009900;">&#40;</span>
        <span style="color: #000000; font-weight: bold;">New</span><span style="color: #009900;">&#40;</span>Ident<span style="color: #009900;">&#40;</span>newTypeName<span style="color: #009900;">&#40;</span><span style="color: #0000ff;">&quot;IllegalArgumentException&quot;</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span>,
        newTermName<span style="color: #009900;">&#40;</span><span style="color: #0000ff;">&quot;&lt;init&gt;&quot;</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span>,
       <span style="color: #003399;">List</span><span style="color: #009900;">&#40;</span>Literal<span style="color: #009900;">&#40;</span>Constant<span style="color: #009900;">&#40;</span><span style="color: #0000ff;">&quot;Parameter '%s' should not be null.&quot;</span>.<span style="color: #006633;">format</span><span style="color: #009900;">&#40;</span>param.<span style="color: #006633;">name</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span>,
     <span style="color: #666666; font-style: italic;">// else</span>
     EmptyTree<span style="color: #009900;">&#41;</span></pre></td></tr></table><p class="theCode" style="display:none;">If(
     // if: param == ...
     Apply(
      Select(
       Ident(param.name),
       newTermName(&quot;$eq$eq&quot;)),
      List(Literal(Constant(null)))
     ),
     // then: throw ...
     Throw(
      Apply(
       Select(
        New(Ident(newTypeName(&quot;IllegalArgumentException&quot;))),
        newTermName(&quot;&lt;init&gt;&quot;)),
       List(Literal(Constant(&quot;Parameter '%s' should not be null.&quot;.format(param.name)))))),
     // else
     EmptyTree)</p></div>
    ";i:6;s:658:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;">scalac <span style="color: #339933;">-</span>cp $JSR305_JAR_PATH <span style="color: #339933;">-</span>Xplugin<span style="color: #339933;">:</span>annotations<span style="color: #339933;">-</span>check<span style="color: #339933;">-</span>gen.<span style="color: #006633;">jar</span> Example.<span style="color: #006633;">scala</span></pre></td></tr></table><p class="theCode" style="display:none;">scalac -cp $JSR305_JAR_PATH -Xplugin:annotations-check-gen.jar Example.scala</p></div>
    ";}
tags:
  - static analysis
  - java
  - scala

---
Recently I played a bit with compiler plugins for [Scala][1].

My goal is to write a plugin which would generate checks in code for methods annotated with [JSR305][2] annotations. As a first step, I wanted to handle the `@Nonnull` annotation on parameters. Supposing you have the following code:

<pre lang="java" line="1">def parameterMustBeNull(@Nonnull parameter : Object) = {
 parameter.toString();
}
</pre>

the compiler plugin should transform it to:

<pre lang="java" line="1">def parameterMustBeNull(@Nonnull parameter : Object) = {
 if (param == null)
  throw new IllegalArgumentException("Parameter 'parameter' should not be null.")
 parameter.toString();
}
</pre>

That way, the method contract expressed by annotations in the method header doesn&#8217;t have to be duplicated by explicit checks in the code later &#8211; they are generated automatically.

To achieve that, the plugin must first find the parameters that are annotated with `@Nonnull`. Then, for each such parameter, it must generate a tree corresponding to the `if` part. Finally, these generated chunks of code must be added to the beginning of the body of the method. All of this is quite easy to do thanks to the Scala compiler API and the AST that you can inspect and manipulate. But I think it&#8217;s best just to look at [source code][3].

In some more detail, as we want to transform the AST, the plugin must use the `Transform` trait. Then we have to implement the transform method, which looks if the tree passed is a method and if so, looks for the nonnull parameters:

<pre lang="java" line="1">object AnnotationsCheckGenComponent extends PluginComponent with Transform {
 ...
 object AnnotationsCheckGenTransformer extends Transformer {
  override def transform(tree: Tree) = {
   tree match {
    // DefDef is a method definition
    case dd @ DefDef(_, _, _, vparamss, _, _) => {
     // here we add the nonnull checks
    }
    case t => super.transform(t)
  }
}
</pre>

The `vparamss` list holds the list of parameters. To search for parameters which are annotated with a nonnull annotation, we can use the very handy Scala list manipulation methods:

<pre lang="java" line="1">vparamss.flatten[ValDef].filter(param => {
 // Checking if the parameter contains a non-null annotation
 param.mods.annotations.exists(annotation => {
  // Checking if the annotation is a non-null annotation
  annotation.constr.find(tree => {
   tree match {
    case Ident(name) => "No[nt][Nn]ull".r.findFirstIn(name.toString).isDefined
    case _ => false
   }
  }).isDefined
 })
})
</pre>

As you can see above, any nonnull annotation will be detected, not only the ones defined by JSR305. And finally, for each parameter annotated, we must generate the if block containing the check. It&#8217;s almost like writing the code directly ;).

<pre lang="java" line="1">If(
 // if: param == ...
 Apply(
  Select(
   Ident(param.name),
   newTermName("$eq$eq")),
  List(Literal(Constant(null)))
 ),
 // then: throw ...
 Throw(
  Apply(
   Select(
    New(Ident(newTypeName("IllegalArgumentException"))),
    newTermName("&lt;init>")),
   List(Literal(Constant("Parameter '%s' should not be null.".format(param.name)))))),
 // else
 EmptyTree)
</pre>

To use the plugin during compiling, all you need to do is have a jar (you can [download a ready one here][4]) that includes the compiled plugin class and a descriptor, and run the compilation with the `-Xplugin` parameter, for example:

<pre lang="java" line="1">scalac -cp $JSR305_JAR_PATH -Xplugin:annotations-check-gen.jar Example.scala
</pre>

The only problem I had was in which phase the plugin should be executed. At first I tried executing it after `namer`, and later, taking the advice I got on the forums (by the way, the forum members are really helpful), after `typer`. However, then I had to run the typer again on the generated code, and I didn&#8217;t yet figure out how to type the generated `if` in a context of the method, so that the typer has access to the information about available values (here, the value being the parameter checked is used). So for now, instead, the plugin runs after the `parser` phase (so in fact as the first plugin), and then the result handled by the rest of the phases as if the code was there from the beginning. But if somebody has an idea, on how to run the typer properly, please let me know :).

You can find the entire source code on [github][5]. For building and testing I used [buildr][6]. I must say that writing the build using that tool was a lot more pleasant than using Maven2, even considering the fact that I don&#8217;t know Ruby.

The plugin can be extended to generate checks for other annotations, for example `@MatchesPattern`, `@RegEx` etc. Another thing to do is to generate checks also for return values of methods (e.g. if a method is annotated with `@Nonnull`, this means that the return value shuoldn&#8217;t be `null`). This is a bit more tricky however, as there can be many exit points from the method. Going further, the generation can be extended to more advanced method contracts, expressed for example via [typestate annotations][7].

If you are also interested in writing compiler plugins for Scala you can find a good tutorial [here][8], and a step-by-step guide describing another plugin [here][9].

Adam

 [1]: http://www.scala-lang.org/
 [2]: http://code.google.com/p/jsr-305/
 [3]: http://github.com/adamw/jsr305-scala-compiler-plugin/blob/master/plugin/src/main/scala/AnnotationsCheckGenPlugin.scala
 [4]: http://www.warski.org/annotations-check-gen.jar
 [5]: http://github.com/adamw/jsr305-scala-compiler-plugin
 [6]: http://buildr.apache.org/
 [7]: http://www.warski.org/typestate.html
 [8]: http://www.scala-lang.org/node/140
 [9]: http://www.scaladudes.com/node/26
