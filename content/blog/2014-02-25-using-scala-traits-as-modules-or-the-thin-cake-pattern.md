---
title: Using Scala traits as modules, or the “Thin Cake” Pattern
author: Adam Warski
type: post
date: 2014-02-25T13:07:28+00:00
url: /blog/2014/02/using-scala-traits-as-modules-or-the-thin-cake-pattern/
simplecatch-sidebarlayout:
  - default
dsq_thread_id:
  - 2318309811
wp-syntax-cache-content:
  - |
    a:6:{i:1;s:1244:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">class</span> WheatField
    <span style="color: #0000ff; font-weight: bold;">class</span> Mill<span style="color: #F78811;">&#40;</span>wheatField<span style="color: #000080;">:</span> wheatField<span style="color: #F78811;">&#41;</span>
    <span style="color: #0000ff; font-weight: bold;">class</span> CowPasture
    <span style="color: #0000ff; font-weight: bold;">class</span> DiaryFarm<span style="color: #F78811;">&#40;</span>cowPasture<span style="color: #000080;">:</span> CowPasture<span style="color: #F78811;">&#41;</span>
    <span style="color: #0000ff; font-weight: bold;">class</span> Bakery<span style="color: #F78811;">&#40;</span>mill<span style="color: #000080;">:</span> Mill, dairyFarm<span style="color: #000080;">:</span> DairyFarm<span style="color: #F78811;">&#41;</span></pre></td></tr></table><p class="theCode" style="display:none;">class WheatField
    class Mill(wheatField: wheatField)
    class CowPasture
    class DiaryFarm(cowPasture: CowPasture)
    class Bakery(mill: Mill, dairyFarm: DairyFarm)</p></div>
    ";i:2;s:2864:"
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
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">object</span> BakeMeCake <span style="color: #0000ff; font-weight: bold;">extends</span> App <span style="color: #F78811;">&#123;</span>
         <span style="color: #008000; font-style: italic;">// creating the object graph</span>
         lazy <span style="color: #0000ff; font-weight: bold;">val</span> wheatField <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> WheatField<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>
         lazy <span style="color: #0000ff; font-weight: bold;">val</span> mill <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> Mill<span style="color: #F78811;">&#40;</span>wheatField<span style="color: #F78811;">&#41;</span>     
         lazy <span style="color: #0000ff; font-weight: bold;">val</span> cowPasture <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> CowPasture<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>
         lazy <span style="color: #0000ff; font-weight: bold;">val</span> diaryFarm <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> DiaryFarm<span style="color: #F78811;">&#40;</span>cowPasture<span style="color: #F78811;">&#41;</span>
         lazy <span style="color: #0000ff; font-weight: bold;">val</span> bakery <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> Bakery<span style="color: #F78811;">&#40;</span>mill, dairyFarm<span style="color: #F78811;">&#41;</span>
    &nbsp;
         <span style="color: #008000; font-style: italic;">// using the object graph</span>
         <span style="color: #0000ff; font-weight: bold;">val</span> cake <span style="color: #000080;">=</span> bakery.<span style="color: #000000;">bakeCake</span><span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>
         me.<span style="color: #000000;">eat</span><span style="color: #F78811;">&#40;</span>cake<span style="color: #F78811;">&#41;</span>
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">object BakeMeCake extends App {
         // creating the object graph
         lazy val wheatField = new WheatField()
         lazy val mill = new Mill(wheatField)     
         lazy val cowPasture = new CowPasture()
         lazy val diaryFarm = new DiaryFarm(cowPasture)
         lazy val bakery = new Bakery(mill, dairyFarm)
    
         // using the object graph
         val cake = bakery.bakeCake()
         me.eat(cake)
    }</p></div>
    ";i:3;s:1952:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    6
    7
    8
    9
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">trait</span> CropModule <span style="color: #F78811;">&#123;</span>
         lazy <span style="color: #0000ff; font-weight: bold;">val</span> wheatField <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> WheatField<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>
         lazy <span style="color: #0000ff; font-weight: bold;">val</span> mill <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> Mill<span style="color: #F78811;">&#40;</span>wheatField<span style="color: #F78811;">&#41;</span>     
    <span style="color: #F78811;">&#125;</span> 
    &nbsp;
    <span style="color: #0000ff; font-weight: bold;">trait</span> LivestockModule <span style="color: #F78811;">&#123;</span>
         lazy <span style="color: #0000ff; font-weight: bold;">val</span> cowPasture <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> CowPasture<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>
         lazy <span style="color: #0000ff; font-weight: bold;">val</span> diaryFarm <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> DiaryFarm<span style="color: #F78811;">&#40;</span>cowPasture<span style="color: #F78811;">&#41;</span>
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">trait CropModule {
         lazy val wheatField = new WheatField()
         lazy val mill = new Mill(wheatField)     
    } 
    
    trait LivestockModule {
         lazy val cowPasture = new CowPasture()
         lazy val diaryFarm = new DiaryFarm(cowPasture)
    }</p></div>
    ";i:4;s:1415:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    6
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">object</span> BakeMeCake <span style="color: #0000ff; font-weight: bold;">extends</span> CropModule <span style="color: #0000ff; font-weight: bold;">with</span> LivestockModule <span style="color: #F78811;">&#123;</span>
         lazy <span style="color: #0000ff; font-weight: bold;">val</span> bakery <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> Bakery<span style="color: #F78811;">&#40;</span>mill, dairyFarm<span style="color: #F78811;">&#41;</span>
    &nbsp;
         <span style="color: #0000ff; font-weight: bold;">val</span> cake <span style="color: #000080;">=</span> bakery.<span style="color: #000000;">bakeCake</span><span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>
         me.<span style="color: #000000;">eat</span><span style="color: #F78811;">&#40;</span>cake<span style="color: #F78811;">&#41;</span> 
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">object BakeMeCake extends CropModule with LivestockModule {
         lazy val bakery = new Bakery(mill, dairyFarm)
    
         val cake = bakery.bakeCake()
         me.eat(cake) 
    }</p></div>
    ";i:5;s:3126:"
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
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #008000; font-style: italic;">// composition via inheritance: bakery depends on crop and livestock modules</span>
    <span style="color: #0000ff; font-weight: bold;">trait</span> BakeryModule <span style="color: #0000ff; font-weight: bold;">extends</span> CropModule <span style="color: #0000ff; font-weight: bold;">with</span> LivestockModule <span style="color: #F78811;">&#123;</span>
         lazy <span style="color: #0000ff; font-weight: bold;">val</span> bakery <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> Bakery<span style="color: #F78811;">&#40;</span>mill, dairyFarm<span style="color: #F78811;">&#41;</span>
    <span style="color: #F78811;">&#125;</span>   
    &nbsp;
    <span style="color: #008000; font-style: italic;">// abstract member: we need a bakery</span>
    <span style="color: #0000ff; font-weight: bold;">trait</span> CafeModule <span style="color: #F78811;">&#123;</span>
         lazy <span style="color: #0000ff; font-weight: bold;">val</span> espressoMachine <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> EspressoMachine<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>
         lazy <span style="color: #0000ff; font-weight: bold;">val</span> cafe <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> Cafe<span style="color: #F78811;">&#40;</span>bakery, espressoMachine<span style="color: #F78811;">&#41;</span>
    &nbsp;
         <span style="color: #0000ff; font-weight: bold;">def</span> bakery<span style="color: #000080;">:</span> Bakery
    <span style="color: #F78811;">&#125;</span>
    &nbsp;
    <span style="color: #008000; font-style: italic;">// the abstract bakery member is implemented in another module</span>
    <span style="color: #0000ff; font-weight: bold;">object</span> CafeApp <span style="color: #0000ff; font-weight: bold;">extends</span> CafeModule <span style="color: #0000ff; font-weight: bold;">with</span> BakeryModule <span style="color: #F78811;">&#123;</span>
         cafe.<span style="color: #000000;">orderCoffeeAndCroissant</span><span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">// composition via inheritance: bakery depends on crop and livestock modules
    trait BakeryModule extends CropModule with LivestockModule {
         lazy val bakery = new Bakery(mill, dairyFarm)
    }   
    
    // abstract member: we need a bakery
    trait CafeModule {
         lazy val espressoMachine = new EspressoMachine()
         lazy val cafe = new Cafe(bakery, espressoMachine)
    
         def bakery: Bakery
    }
    
    // the abstract bakery member is implemented in another module
    object CafeApp extends CafeModule with BakeryModule {
         cafe.orderCoffeeAndCroissant()
    }</p></div>
    ";i:6;s:3496:"
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
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">trait</span> MillModule <span style="color: #F78811;">&#123;</span>
         <span style="color: #0000ff; font-weight: bold;">def</span> mill<span style="color: #000080;">:</span> Mill
    <span style="color: #F78811;">&#125;</span>
    &nbsp;
    <span style="color: #0000ff; font-weight: bold;">trait</span> CornMillModule <span style="color: #0000ff; font-weight: bold;">extends</span> MillModule <span style="color: #F78811;">&#123;</span> 
         lazy <span style="color: #0000ff; font-weight: bold;">val</span> cornField <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> CornField<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>
         lazy <span style="color: #0000ff; font-weight: bold;">val</span> mill <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> CornMill<span style="color: #F78811;">&#40;</span>cornField<span style="color: #F78811;">&#41;</span>
    <span style="color: #F78811;">&#125;</span>
    &nbsp;
    <span style="color: #0000ff; font-weight: bold;">trait</span> WheatMillModule <span style="color: #0000ff; font-weight: bold;">extends</span> MillModule <span style="color: #F78811;">&#123;</span> 
         lazy <span style="color: #0000ff; font-weight: bold;">val</span> wheatField <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> WheatField<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>
         lazy <span style="color: #0000ff; font-weight: bold;">val</span> mill <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> WheatMill<span style="color: #F78811;">&#40;</span>wheatField<span style="color: #F78811;">&#41;</span>
    <span style="color: #F78811;">&#125;</span>
    &nbsp;
    <span style="color: #0000ff; font-weight: bold;">val</span> modules <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">if</span> <span style="color: #F78811;">&#40;</span>config.<span style="color: #000000;">cornPreferred</span><span style="color: #F78811;">&#41;</span> <span style="color: #F78811;">&#123;</span>
         <span style="color: #0000ff; font-weight: bold;">new</span> BakeryModule <span style="color: #0000ff; font-weight: bold;">with</span> CornMillModule
    <span style="color: #F78811;">&#125;</span> <span style="color: #0000ff; font-weight: bold;">else</span> <span style="color: #F78811;">&#123;</span>
         <span style="color: #0000ff; font-weight: bold;">new</span> BakeryModule <span style="color: #0000ff; font-weight: bold;">with</span> WheatMillModule
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">trait MillModule {
         def mill: Mill
    }
    
    trait CornMillModule extends MillModule { 
         lazy val cornField = new CornField()
         lazy val mill = new CornMill(cornField)
    }
    
    trait WheatMillModule extends MillModule { 
         lazy val wheatField = new WheatField()
         lazy val mill = new WheatMill(wheatField)
    }
     
    val modules = if (config.cornPreferred) {
         new BakeryModule with CornMillModule
    } else {
         new BakeryModule with WheatMillModule
    }</p></div>
    ";}
epcl_post:
  - 'a:1:{s:13:"views_counter";i:2;}'
views_counter:
  - 2
tags:
  - dependency injection
  - macwire
  - modularity
  - scala

---
I would like to describe a pure-Scala approach to modularity that we are successfully using in a couple of our Scala projects.

But let’s start with how we do Dependency Injection (see also my [other][1] [blogs][2]). Each class can have dependencies in the form of constructor parameters, e.g.:

<pre lang="scala" line="1">class WheatField
class Mill(wheatField: wheatField)
class CowPasture
class DiaryFarm(cowPasture: CowPasture)
class Bakery(mill: Mill, dairyFarm: DairyFarm) 
</pre>

At the &#8220;end of the world&#8221;, there is a main class which runs the application and where the whole object graph is created:

<pre lang="scala" line="1">object BakeMeCake extends App {
     // creating the object graph
     lazy val wheatField = new WheatField()
     lazy val mill = new Mill(wheatField)     
     lazy val cowPasture = new CowPasture()
     lazy val diaryFarm = new DiaryFarm(cowPasture)
     lazy val bakery = new Bakery(mill, dairyFarm)

     // using the object graph
     val cake = bakery.bakeCake()
     me.eat(cake)
}
</pre>

The wiring can be done manually, or e.g. using [MacWire][3].

Note that we can do scoping using Scala constructs: a `lazy val` corresponds to a singleton object (in the constructed object graph), a `def` to a dependent-scoped object (a new instance will be created for each usage).

## Thin Cake pattern

What if the object graph, and at the same time the main class, becomes large? The answer is simple: we have to break it into pieces, which will be the &#8220;modules&#8221;. Each module is a Scala `trait`, and contains some part of the object graph.

For example:

<pre lang="scala" line="1">trait CropModule {
     lazy val wheatField = new WheatField()
     lazy val mill = new Mill(wheatField)     
} 

trait LivestockModule {
     lazy val cowPasture = new CowPasture()
     lazy val diaryFarm = new DiaryFarm(cowPasture)
}
</pre>

The main object then becomes a composition of traits. This is exactly what also happens in the Cake Pattern. However here we are using only one element of it, hence the &#8220;Thin Cake&#8221; Pattern name.

<pre lang="scala" line="1">object BakeMeCake extends CropModule with LivestockModule {
     lazy val bakery = new Bakery(mill, dairyFarm)

     val cake = bakery.bakeCake()
     me.eat(cake) 
}
</pre>

If you have ever used [Google Guice][4], you may see a similarity: trait-modules directly correspond to Guice modules. However, here we gain the additional type-safety and compile-time checking that dependency requirements for all classes are met.

Of course, the module trait can contain more than just new object instantiations, however you have to be cautious not to put too much logic in there &#8211; at some point you probably need to extract a class. Typical code that also goes into modules is e.g. new actor creation code and setting up caches.

## Dependencies

What if our trait modules have inter-module dependencies? There are two ways we can deal with that problem.

The first is abstract members. If there’s an instance of a class that is needed in our module, we can simply define it as an abstract member of the trait-module. This abstract member has to be then implemented in some other module with which our module gets composed in the end. Using a consistent naming convention helps here. The fact that all abstract dependencies are defined at some point is checked by the compiler. 

The second way is composition via inheritance. If we e.g. want to create a bigger module out of three smaller modules, we can simply extend the other module-traits, and due to the way inheritance works we can use all of the objects defined there. 

Putting the two methods together we get for example: 

<pre lang="scala" line="1">// composition via inheritance: bakery depends on crop and livestock modules
trait BakeryModule extends CropModule with LivestockModule {
     lazy val bakery = new Bakery(mill, dairyFarm)
}   

// abstract member: we need a bakery
trait CafeModule {
     lazy val espressoMachine = new EspressoMachine()
     lazy val cafe = new Cafe(bakery, espressoMachine)

     def bakery: Bakery
}

// the abstract bakery member is implemented in another module
object CafeApp extends CafeModule with BakeryModule {
     cafe.orderCoffeeAndCroissant()
}
</pre>

## Multiple implementations

Taking this idea a bit further, in some situations we might have trait-module-interfaces and several trait-module-implementions. The interface would contain only abstract members, and the implementations would wire the appropriate classes. If other modules depend only on the trait-module-interface, when we do the final composition we can use any implementation.

This isn’t perfect, however. The implementation must be known statically, when writing the code &#8211; we cannot dynamically decide which implementations we want to use. If we want to dynamically choose an implementation for only one trait-interface, that’s not a problem &#8211; we can use a simple “if”. But every additional combination causes an exponential increase in the cases we have to cover. For example:

<pre lang="scala" line="1">trait MillModule {
     def mill: Mill
}

trait CornMillModule extends MillModule { 
     lazy val cornField = new CornField()
     lazy val mill = new CornMill(cornField)
}

trait WheatMillModule extends MillModule { 
     lazy val wheatField = new WheatField()
     lazy val mill = new WheatMill(wheatField)
}
 
val modules = if (config.cornPreferred) {
     new BakeryModule with CornMillModule
} else {
     new BakeryModule with WheatMillModule
}
</pre>

## Can it be any better?

Sure! There’s always something to improve :). One of the problems was already mentioned &#8211; you cannot choose which trait-module to use dynamically (run-time configuration).

Another area that could get improved is the relation between trait-modules and packages. A good approach is to have a single trait-module per package (or per package tree). That way you logically group code that implements some functionality in a single package, and specify **how** the classes that form the implementations should be used in the trait-module. But why then do you have to define both the package and trait-module? Maybe they can be merged together somehow? Increasing the role of packages is also an idea I’ve been exploring in the [Veripacks][5] project.

It may also be good to restrict the visibility of some of the defined objects. Following the &#8220;one public class per package&#8221; rule, here we might have “one public object per trait-module”. However, if we are creating bigger trait-modules out of smaller ones, the bigger module has no way to restrict the visibility of the objects in the module it composes of. In fact, the smaller modules would have to know the maximum scope of their visibility and use an appropriate private[package name] modifier (supposing the bigger module is in a parent package).

## Summing up

Overall, we found this solution to be a simple, clear way to structure our code and create the object graph. It uses only native Scala constructs, does not depend on any frameworks or libraries, and provides compile-time checking that everything is defined properly.

Bon Appetit!

 [1]: http://www.warski.org/blog/2013/03/dependency-injection-with-scala-macros-auto-wiring/
 [2]: http://www.warski.org/blog/2013/04/macwire-0-1-framework-less-dependency-injection-with-scala-macros/
 [3]: https://github.com/adamw/macwire
 [4]: https://code.google.com/p/google-guice/
 [5]: http://veripacks.org/
