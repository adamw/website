---
title: Object Services in Scala
author: Adam Warski
type: post
date: 2010-06-21T16:39:46+00:00
url: /blog/2010/06/object-services-in-scala/
dsq_thread_id:
  - 1051935123
wp-syntax-cache-content:
  - |
    a:5:{i:1;s:611:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;">trait Animal
    <span style="color: #000000; font-weight: bold;">class</span> Elephant <span style="color: #000000; font-weight: bold;">extends</span> Animal
    <span style="color: #000000; font-weight: bold;">class</span> Ant <span style="color: #000000; font-weight: bold;">extends</span> Animal</pre></td></tr></table><p class="theCode" style="display:none;">trait Animal
    class Elephant extends Animal
    class Ant extends Animal</p></div>
    ";i:2;s:2781:"
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
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;">trait PaintService<span style="color: #009900;">&#91;</span>O <span style="color: #339933;">&lt;:</span> Animal<span style="color: #009900;">&#93;</span> <span style="color: #000000; font-weight: bold;">extends</span> OS<span style="color: #009900;">&#91;</span>O<span style="color: #009900;">&#93;</span> <span style="color: #009900;">&#123;</span>
       def paint<span style="color: #009900;">&#40;</span>c<span style="color: #339933;">:</span> <span style="color: #003399;">Canvas</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">:</span> Unit
    <span style="color: #009900;">&#125;</span>
    &nbsp;
    <span style="color: #000000; font-weight: bold;">class</span> ElephantPaintService<span style="color: #009900;">&#40;</span>elephant<span style="color: #339933;">:</span> Elephant<span style="color: #009900;">&#41;</span> <span style="color: #000000; font-weight: bold;">extends</span> PaintService<span style="color: #009900;">&#91;</span>Elephant<span style="color: #009900;">&#93;</span> <span style="color: #009900;">&#123;</span>
       def paint<span style="color: #009900;">&#40;</span>c<span style="color: #339933;">:</span> <span style="color: #003399;">Canvas</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">:</span> Unit <span style="color: #009900;">&#123;</span> ... <span style="color: #009900;">&#125;</span>
    <span style="color: #009900;">&#125;</span>
    &nbsp;
    <span style="color: #000000; font-weight: bold;">class</span> AntPaintService<span style="color: #009900;">&#40;</span>ant<span style="color: #339933;">:</span> Ant<span style="color: #009900;">&#41;</span> <span style="color: #000000; font-weight: bold;">extends</span> PaintService<span style="color: #009900;">&#91;</span>Ant<span style="color: #009900;">&#93;</span> <span style="color: #009900;">&#123;</span>
       def paint<span style="color: #009900;">&#40;</span>c<span style="color: #339933;">:</span> <span style="color: #003399;">Canvas</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">:</span> Unit <span style="color: #009900;">&#123;</span> ... <span style="color: #009900;">&#125;</span>
    <span style="color: #009900;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">trait PaintService[O &lt;: Animal] extends OS[O] {
       def paint(c: Canvas): Unit
    }
    
    class ElephantPaintService(elephant: Elephant) extends PaintService[Elephant] {
       def paint(c: Canvas): Unit { ... }
    }
    
    class AntPaintService(ant: Ant) extends PaintService[Ant] {
       def paint(c: Canvas): Unit { ... }
    }</p></div>
    ";i:3;s:918:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;">object PaintServiceReg <span style="color: #000000; font-weight: bold;">extends</span> OSP<span style="color: #009900;">&#91;</span>Animal, PaintService<span style="color: #009900;">&#93;</span> <span style="color: #009900;">&#123;</span>
       register<span style="color: #009900;">&#91;</span>Elephant, ElephantPaintService<span style="color: #009900;">&#93;</span>
       register<span style="color: #009900;">&#91;</span>Ant, AntPaintService<span style="color: #009900;">&#93;</span>
    <span style="color: #009900;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">object PaintServiceReg extends OSP[Animal, PaintService] {
       register[Elephant, ElephantPaintService]
       register[Ant, AntPaintService]
    }</p></div>
    ";i:4;s:1362:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    6
    7
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;"><span style="color: #000000; font-weight: bold;">import</span> <span style="color: #006699;">PaintServiceReg._</span>
    &nbsp;
    val animal1<span style="color: #339933;">:</span> Animal <span style="color: #339933;">=</span> <span style="color: #000000; font-weight: bold;">new</span> Elephant
    animal1.<span style="color: #006633;">paint</span><span style="color: #009900;">&#40;</span>c<span style="color: #009900;">&#41;</span>  <span style="color: #666666; font-style: italic;">// ElephantPaintService is called</span>
    &nbsp;
    val animal2<span style="color: #339933;">:</span> Animal <span style="color: #339933;">=</span> <span style="color: #000000; font-weight: bold;">new</span> Ant
    animal2.<span style="color: #006633;">paint</span><span style="color: #009900;">&#40;</span>c<span style="color: #009900;">&#41;</span>  <span style="color: #666666; font-style: italic;">// AntPaintService is called</span></pre></td></tr></table><p class="theCode" style="display:none;">import PaintServiceReg._
    
    val animal1: Animal = new Elephant
    animal1.paint(c)  // ElephantPaintService is called
    
    val animal2: Animal = new Ant
    animal2.paint(c)  // AntPaintService is called</p></div>
    ";i:5;s:7873:"
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
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;">trait OS<span style="color: #009900;">&#91;</span>O<span style="color: #009900;">&#93;</span>
    &nbsp;
    trait OSP<span style="color: #009900;">&#91;</span>O <span style="color: #339933;">&lt;:</span> AnyRef, S<span style="color: #009900;">&#91;</span>_ <span style="color: #339933;">&lt;:</span> O<span style="color: #009900;">&#93;</span> <span style="color: #339933;">&lt;:</span> OS<span style="color: #009900;">&#91;</span>_<span style="color: #009900;">&#93;</span><span style="color: #009900;">&#93;</span> <span style="color: #009900;">&#123;</span>
       implicit def oToS<span style="color: #009900;">&#40;</span>obj<span style="color: #339933;">:</span> O<span style="color: #009900;">&#41;</span><span style="color: #339933;">:</span> S<span style="color: #009900;">&#91;</span>O<span style="color: #009900;">&#93;</span> <span style="color: #339933;">=</span> <span style="color: #009900;">&#123;</span>
          val bestService <span style="color: #339933;">=</span> findBestService<span style="color: #009900;">&#40;</span>obj.<span style="color: #006633;">getClass</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span>
          bestService.<span style="color: #006633;">getConstructors</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#40;</span><span style="color: #cc66cc;">0</span><span style="color: #009900;">&#41;</span>.<span style="color: #006633;">newInstance</span><span style="color: #009900;">&#40;</span>obj<span style="color: #009900;">&#41;</span>.<span style="color: #006633;">asInstanceOf</span><span style="color: #009900;">&#91;</span>S<span style="color: #009900;">&#91;</span>O<span style="color: #009900;">&#93;</span><span style="color: #009900;">&#93;</span>
       <span style="color: #009900;">&#125;</span>
    &nbsp;
       <span style="color: #000000; font-weight: bold;">protected</span> def register<span style="color: #009900;">&#91;</span>RO <span style="color: #339933;">&lt;:</span> O, RS <span style="color: #339933;">&lt;:</span> S<span style="color: #009900;">&#91;</span>RO<span style="color: #009900;">&#93;</span><span style="color: #009900;">&#93;</span><span style="color: #009900;">&#40;</span>implicit manifestRO<span style="color: #339933;">:</span> <span style="color: #003399;">Manifest</span><span style="color: #009900;">&#91;</span>RO<span style="color: #009900;">&#93;</span>, 
                manifestRS<span style="color: #339933;">:</span> <span style="color: #003399;">Manifest</span><span style="color: #009900;">&#91;</span>RS<span style="color: #009900;">&#93;</span><span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
          serviceMap <span style="color: #339933;">+=</span> manifestRO.<span style="color: #006633;">erasure</span> <span style="color: #339933;">-&gt;</span> manifestRS.<span style="color: #006633;">erasure</span>
       <span style="color: #009900;">&#125;</span>
    &nbsp;
       <span style="color: #000000; font-weight: bold;">private</span> var serviceMap<span style="color: #339933;">:</span> <span style="color: #003399;">Map</span><span style="color: #009900;">&#91;</span><span style="color: #000000; font-weight: bold;">Class</span><span style="color: #009900;">&#91;</span>_<span style="color: #009900;">&#93;</span>, <span style="color: #000000; font-weight: bold;">Class</span><span style="color: #009900;">&#91;</span>_<span style="color: #009900;">&#93;</span><span style="color: #009900;">&#93;</span> <span style="color: #339933;">=</span> <span style="color: #003399;">Map</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span>
    &nbsp;
       <span style="color: #000000; font-weight: bold;">private</span> def findBestService<span style="color: #009900;">&#40;</span>objectCls<span style="color: #339933;">:</span> <span style="color: #000000; font-weight: bold;">Class</span><span style="color: #009900;">&#91;</span>_<span style="color: #009900;">&#93;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">:</span> <span style="color: #000000; font-weight: bold;">Class</span><span style="color: #009900;">&#91;</span>_<span style="color: #009900;">&#93;</span> <span style="color: #339933;">=</span> <span style="color: #009900;">&#123;</span>
          serviceMap.<span style="color: #006633;">foldLeft</span><span style="color: #009900;">&#91;</span><span style="color: #009900;">&#40;</span><span style="color: #000000; font-weight: bold;">Class</span><span style="color: #009900;">&#91;</span>_<span style="color: #009900;">&#93;</span>, <span style="color: #000000; font-weight: bold;">Class</span><span style="color: #009900;">&#91;</span>_<span style="color: #009900;">&#93;</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#93;</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#40;</span>classOf<span style="color: #009900;">&#91;</span>AnyRef<span style="color: #009900;">&#93;</span>, classOf<span style="color: #009900;">&#91;</span>AnyRef<span style="color: #009900;">&#93;</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span>
             <span style="color: #009900;">&#40;</span><span style="color: #009900;">&#40;</span>curr, mapping<span style="color: #009900;">&#41;</span> <span style="color: #339933;">=&gt;</span> <span style="color: #009900;">&#123;</span>
             <span style="color: #666666; font-style: italic;">// Checking if the mapping is appropriate for the given object class </span>
             <span style="color: #666666; font-style: italic;">// and more specific than the current one</span>
             <span style="color: #000000; font-weight: bold;">if</span> <span style="color: #009900;">&#40;</span>mapping._1.<span style="color: #006633;">isAssignableFrom</span><span style="color: #009900;">&#40;</span>objectCls<span style="color: #009900;">&#41;</span> <span style="color: #339933;">&amp;&amp;</span>
                    curr._1.<span style="color: #006633;">isAssignableFrom</span><span style="color: #009900;">&#40;</span>mapping._1<span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span>
                mapping
             <span style="color: #000000; font-weight: bold;">else</span> curr
          <span style="color: #009900;">&#125;</span><span style="color: #009900;">&#41;</span>._2
       <span style="color: #009900;">&#125;</span>
    <span style="color: #009900;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">trait OS[O]
    
    trait OSP[O &lt;: AnyRef, S[_ &lt;: O] &lt;: OS[_]] {
       implicit def oToS(obj: O): S[O] = {
          val bestService = findBestService(obj.getClass())
          bestService.getConstructors()(0).newInstance(obj).asInstanceOf[S[O]]
       }
    
       protected def register[RO &lt;: O, RS &lt;: S[RO]](implicit manifestRO: Manifest[RO], 
                manifestRS: Manifest[RS]) {
          serviceMap += manifestRO.erasure -&gt; manifestRS.erasure
       }
    
       private var serviceMap: Map[Class[_], Class[_]] = Map()
    
       private def findBestService(objectCls: Class[_]): Class[_] = {
          serviceMap.foldLeft[(Class[_], Class[_])]((classOf[AnyRef], classOf[AnyRef]))
             ((curr, mapping) =&gt; {
             // Checking if the mapping is appropriate for the given object class 
             // and more specific than the current one
             if (mapping._1.isAssignableFrom(objectCls) &amp;&amp;
                    curr._1.isAssignableFrom(mapping._1))
                mapping
             else curr
          })._2
       }
    }</p></div>
    ";}
categories:
  - Java
  - JBoss
  - Scala
  - Weld

---
Using Scala&#8217;s implicits it&#8217;s possible to implement [Object Services][1] in a much more &#8220;user-friendly&#8221; way. Just to remind, the goal is to extend a class hierarchy with a method, polymorphically. E.g. we have:

<pre lang="java" line="1">trait Animal
class Elephant extends Animal
class Ant extends Animal
</pre>

and we want to add a `paint` method, which has a different implementation for an elephant, and for an ant (quite obviously :) ). In another words, we want to have a polymorphic extension method. One approach in Scala is to use pattern-matching. And in fact pattern-matching can be used to implement object services as described here (below is a method which uses reflection, but it can be easily swapped).

Before we&#8217;ll go to the actual implementation, here&#8217;s how you can use the object services. We already have the objects, so it&#8217;s time to create the services:

<pre lang="java" line="1">trait PaintService[O &lt;: Animal] extends OS[O] {
   def paint(c: Canvas): Unit
}

class ElephantPaintService(elephant: Elephant) extends PaintService[Elephant] {
   def paint(c: Canvas): Unit { ... }
}

class AntPaintService(ant: Ant) extends PaintService[Ant] {
   def paint(c: Canvas): Unit { ... }
}
</pre>

Now we need a place, where we register the services. In the Weld implementation, this was done automatically at container startup time. Here we'll need one object (which could be auto-generated by a compiler plugin):

<pre lang="java" line="1">object PaintServiceReg extends OSP[Animal, PaintService] {
   register[Elephant, ElephantPaintService]
   register[Ant, AntPaintService]
}
</pre>

The type bound's of the `register` method will make sure that you can register only appropriate services for appropriate object types. Finally, we can use our services. If we want to use the `PaintService`, we'll need to import the content of the `PaintServiceReg` object. That way we have control, on what services are available when. Usage is quite simple and **looks like a regular method invocation**, as if the method was in the class hierarchy:

<pre lang="java" line="1">import PaintServiceReg._

val animal1: Animal = new Elephant
animal1.paint(c)  // ElephantPaintService is called

val animal2: Animal = new Ant
animal2.paint(c)  // AntPaintService is called
</pre>

What's left to show is the actual code for `OS` and `OSP`:

<pre lang="java" line="1">trait OS[O]

trait OSP[O &lt;: AnyRef, S[_ &lt;: O] &lt;: OS[_]] {
   implicit def oToS(obj: O): S[O] = {
      val bestService = findBestService(obj.getClass())
      bestService.getConstructors()(0).newInstance(obj).asInstanceOf[S[O]]
   }

   protected def register[RO &lt;: O, RS &lt;: S[RO]](implicit manifestRO: Manifest[RO], 
            manifestRS: Manifest[RS]) {
      serviceMap += manifestRO.erasure -> manifestRS.erasure
   }

   private var serviceMap: Map[Class[_], Class[_]] = Map()

   private def findBestService(objectCls: Class[_]): Class[_] = {
      serviceMap.foldLeft[(Class[_], Class[_])]((classOf[AnyRef], classOf[AnyRef]))
         ((curr, mapping) => {
         // Checking if the mapping is appropriate for the given object class 
         // and more specific than the current one
         if (mapping._1.isAssignableFrom(objectCls) &&
                curr._1.isAssignableFrom(mapping._1))
            mapping
         else curr
      })._2
   }
}
</pre>

The implementation can be improved e.g. to always return the same object service reference for a given object (something like [Eclipse Adapters][2] - thanks for the link), or to provide alternative ways to instantiate the services - not necessarily with a one-arg constructor.

The biggest problem here is that completeness is not checked - that is, if there's a service missing for a class in the hierarchy, there will be a run-time error. But I think this also can be checked with a compiler plugin.

 [1]: http://www.warski.org/blog/?p=226
 [2]: http://www.eclipse.org/resources/resource.php?id=407
