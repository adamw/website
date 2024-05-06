---
title: 'Implementing factories in Scala & MacWire 0.3'
author: Adam Warski
type: post
date: 2013-06-18T10:47:38+00:00
url: /blog/2013/06/implementing-factories-in-scala-macwire-0-3/
simplecatch-sidebarlayout:
  - default
dsq_thread_id:
  - 1409436131
wp-syntax-cache-content:
  - |
    a:9:{i:1;s:1826:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    6
    7
    8
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">case</span> <span style="color: #0000ff; font-weight: bold;">class</span> User
    <span style="color: #0000ff; font-weight: bold;">class</span> DatabaseAccess
    <span style="color: #0000ff; font-weight: bold;">class</span> PriceCalculator<span style="color: #F78811;">&#40;</span>databaseAccess<span style="color: #000080;">:</span> DatabaseAccess, user<span style="color: #000080;">:</span> User<span style="color: #F78811;">&#41;</span>
    &nbsp;
    <span style="color: #0000ff; font-weight: bold;">trait</span> ShoppingModule <span style="color: #0000ff; font-weight: bold;">extends</span> Macwire <span style="color: #F78811;">&#123;</span>
       lazy <span style="color: #0000ff; font-weight: bold;">val</span> databaseAccess <span style="color: #000080;">=</span> wire<span style="color: #F78811;">&#91;</span>DatabaseAccess<span style="color: #F78811;">&#93;</span>
       <span style="color: #0000ff; font-weight: bold;">def</span> priceCalculator<span style="color: #F78811;">&#40;</span>user<span style="color: #000080;">:</span> User<span style="color: #F78811;">&#41;</span> <span style="color: #000080;">=</span> wire<span style="color: #F78811;">&#91;</span>PriceCalculator<span style="color: #F78811;">&#93;</span>
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">case class User
    class DatabaseAccess
    class PriceCalculator(databaseAccess: DatabaseAccess, user: User)
    
    trait ShoppingModule extends Macwire {
       lazy val databaseAccess = wire[DatabaseAccess]
       def priceCalculator(user: User) = wire[PriceCalculator]
    }</p></div>
    ";i:2;s:1286:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">trait</span> ShoppingModule <span style="color: #0000ff; font-weight: bold;">extends</span> Macwire <span style="color: #F78811;">&#123;</span>
       lazy <span style="color: #0000ff; font-weight: bold;">val</span> databaseAccess <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> DatabaseAccess
       <span style="color: #0000ff; font-weight: bold;">def</span> priceCalculator<span style="color: #F78811;">&#40;</span>user<span style="color: #000080;">:</span> User<span style="color: #F78811;">&#41;</span> <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> PriceCalculator<span style="color: #F78811;">&#40;</span>databaseAccess, user<span style="color: #F78811;">&#41;</span>
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">trait ShoppingModule extends Macwire {
       lazy val databaseAccess = new DatabaseAccess
       def priceCalculator(user: User) = new PriceCalculator(databaseAccess, user)
    }</p></div>
    ";i:3;s:598:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">class</span> SpecialOfferMailer<span style="color: #F78811;">&#40;</span>priceCalculator<span style="color: #000080;">:</span> User <span style="color: #000080;">=&gt;</span> PriceCalculator<span style="color: #F78811;">&#41;</span></pre></td></tr></table><p class="theCode" style="display:none;">class SpecialOfferMailer(priceCalculator: User =&gt; PriceCalculator)</p></div>
    ";i:4;s:1776:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    6
    7
    8
    9
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">class</span> PriceCalculator<span style="color: #F78811;">&#40;</span>databaseAccess<span style="color: #000080;">:</span> DatabaseAccess, user<span style="color: #000080;">:</span> User<span style="color: #F78811;">&#41;</span>
    &nbsp;
    <span style="color: #0000ff; font-weight: bold;">object</span> PriceCalculator <span style="color: #F78811;">&#123;</span>
       <span style="color: #0000ff; font-weight: bold;">trait</span> Factory <span style="color: #F78811;">&#123;</span>
          <span style="color: #0000ff; font-weight: bold;">def</span> create<span style="color: #F78811;">&#40;</span>user<span style="color: #000080;">:</span> User<span style="color: #F78811;">&#41;</span><span style="color: #000080;">:</span> PriceCalculator
       <span style="color: #F78811;">&#125;</span>
    <span style="color: #F78811;">&#125;</span>
    &nbsp;
    <span style="color: #0000ff; font-weight: bold;">class</span> SpecialOfferMailer<span style="color: #F78811;">&#40;</span>priceCalculatorFactory<span style="color: #000080;">:</span> PriceCalculator.<span style="color: #000000;">Factory</span><span style="color: #F78811;">&#41;</span> <span style="color: #F78811;">&#123;</span> ... <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">class PriceCalculator(databaseAccess: DatabaseAccess, user: User)
    
    object PriceCalculator {
       trait Factory {
          def create(user: User): PriceCalculator
       }
    }
    
    class SpecialOfferMailer(priceCalculatorFactory: PriceCalculator.Factory) { ... }</p></div>
    ";i:5;s:878:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">trait</span> ShoppingModule <span style="color: #F78811;">&#123;</span>
       lazy <span style="color: #0000ff; font-weight: bold;">val</span> priceCalculatorFactory 
              <span style="color: #000080;">=</span> wireFactory<span style="color: #F78811;">&#91;</span>PriceCalculator, PriceCalculator.<span style="color: #000000;">Factory</span><span style="color: #F78811;">&#93;</span>
       ...
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">trait ShoppingModule {
       lazy val priceCalculatorFactory 
              = wireFactory[PriceCalculator, PriceCalculator.Factory]
       ...
    }</p></div>
    ";i:6;s:1595:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    6
    7
    8
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">trait</span> ShoppingModule <span style="color: #F78811;">&#123;</span>
       lazy <span style="color: #0000ff; font-weight: bold;">val</span> priceCalculatorFactory <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> PriceCalculator.<span style="color: #000000;">Factory</span> <span style="color: #F78811;">&#123;</span>
          <span style="color: #008000; font-style: italic;">// the wire here uses values from the method parameters and from the </span>
          <span style="color: #008000; font-style: italic;">// outer module</span>
          <span style="color: #0000ff; font-weight: bold;">def</span> create<span style="color: #F78811;">&#40;</span>user<span style="color: #000080;">:</span> User<span style="color: #F78811;">&#41;</span> <span style="color: #000080;">=</span> wire<span style="color: #F78811;">&#91;</span>PriceCalculator<span style="color: #F78811;">&#93;</span>
       <span style="color: #F78811;">&#125;</span>
       ...
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">trait ShoppingModule {
       lazy val priceCalculatorFactory = new PriceCalculator.Factory {
          // the wire here uses values from the method parameters and from the 
          // outer module
          def create(user: User) = wire[PriceCalculator]
       }
       ...
    }</p></div>
    ";i:7;s:2806:"
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
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">class</span> PriceCalculatorFactory<span style="color: #F78811;">&#40;</span>databaseAccess<span style="color: #000080;">:</span> DatabaseAccess<span style="color: #F78811;">&#41;</span> <span style="color: #F78811;">&#123;</span>
       <span style="color: #0000ff; font-weight: bold;">case</span> <span style="color: #0000ff; font-weight: bold;">class</span> create<span style="color: #F78811;">&#40;</span>user<span style="color: #000080;">:</span> User<span style="color: #F78811;">&#41;</span> <span style="color: #F78811;">&#123;</span>
          <span style="color: #008000; font-style: italic;">// methods</span>
       <span style="color: #F78811;">&#125;</span>
    <span style="color: #F78811;">&#125;</span>
    &nbsp;
    <span style="color: #0000ff; font-weight: bold;">class</span> SpecialOfferMailer<span style="color: #F78811;">&#40;</span>priceCalculatorFactory<span style="color: #000080;">:</span> PriceCalculatorFactory<span style="color: #F78811;">&#41;</span>
    &nbsp;
    <span style="color: #008000; font-style: italic;">// Nothing special here. Just plain ol' MacWire</span>
    <span style="color: #0000ff; font-weight: bold;">trait</span> ShoppingModule <span style="color: #0000ff; font-weight: bold;">extends</span> Macwire <span style="color: #F78811;">&#123;</span>
       lazy <span style="color: #0000ff; font-weight: bold;">val</span> databaseAccess <span style="color: #000080;">=</span> wire<span style="color: #F78811;">&#91;</span>DatabaseAccess<span style="color: #F78811;">&#93;</span>
       lazy <span style="color: #0000ff; font-weight: bold;">val</span> priceCalculatorFactory <span style="color: #000080;">=</span> wire<span style="color: #F78811;">&#91;</span>PriceCalculatorFactory<span style="color: #F78811;">&#93;</span>
       lazy <span style="color: #0000ff; font-weight: bold;">val</span> specialOfferMailer <span style="color: #000080;">=</span> wire<span style="color: #F78811;">&#91;</span>SpecialOfferMailer<span style="color: #F78811;">&#93;</span>
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">class PriceCalculatorFactory(databaseAccess: DatabaseAccess) {
       case class create(user: User) {
          // methods
       }
    }
    
    class SpecialOfferMailer(priceCalculatorFactory: PriceCalculatorFactory)
    
    // Nothing special here. Just plain ol' MacWire
    trait ShoppingModule extends Macwire {
       lazy val databaseAccess = wire[DatabaseAccess]
       lazy val priceCalculatorFactory = wire[PriceCalculatorFactory]
       lazy val specialOfferMailer = wire[SpecialOfferMailer]
    }</p></div>
    ";i:8;s:1818:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    6
    7
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">class</span> SpecialOfferMailer<span style="color: #F78811;">&#40;</span>...<span style="color: #F78811;">&#41;</span> <span style="color: #F78811;">&#123;</span>
       <span style="color: #0000ff; font-weight: bold;">def</span> mailOfferOfTheDay<span style="color: #F78811;">&#40;</span>user<span style="color: #000080;">:</span> User<span style="color: #F78811;">&#41;</span> <span style="color: #F78811;">&#123;</span>
          <span style="color: #0000ff; font-weight: bold;">val</span> priceCalculator <span style="color: #000080;">=</span> priceCalculatorFactory.<span style="color: #000000;">create</span><span style="color: #F78811;">&#40;</span>user<span style="color: #F78811;">&#41;</span>
          products.<span style="color: #000000;">foreach</span> <span style="color: #F78811;">&#123;</span> product <span style="color: #000080;">=&gt;</span> 
              mailOffer<span style="color: #F78811;">&#40;</span>user, product, priceCalculator.<span style="color: #000000;">price</span><span style="color: #F78811;">&#40;</span>product<span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span> <span style="color: #F78811;">&#125;</span>
       <span style="color: #F78811;">&#125;</span>
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">class SpecialOfferMailer(...) {
       def mailOfferOfTheDay(user: User) {
          val priceCalculator = priceCalculatorFactory.create(user)
          products.foreach { product =&gt; 
              mailOffer(user, product, priceCalculator.price(product)) }
       }
    }</p></div>
    ";i:9;s:482:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">type</span> PriceCalculator <span style="color: #000080;">=</span> PriceCalculatorFactory<span style="color: #000080;">#</span>create</pre></td></tr></table><p class="theCode" style="display:none;">type PriceCalculator = PriceCalculatorFactory#create</p></div>
    ";}
categories:
  - Library
  - Macros
  - MacWire
  - Scala
  - Uncategorized

---
Factories are useful when we need to create multiple instances of a class at run-time, usually providing some parameters, but still without using `new` explicitly; we want to make some complex object creation abstract. The created object may depend both on runtime-provided data (the parameters), and some other &#8220;services&#8221;.

[MacWire 0.3][1] adds support for some factories usage scenarios, while others can be implemented in pure Scala.

TL;DR:

  * [MacWire 0.3][1] adds support for wiring using parameters of the method enclosing the `wire[]` macro
  * traditional approaches to defining injectable factories are verbose
  * but using Scala&#8217;s nested case classes it is possible to define factories in a [simple, compact and readable way][2]

As a running example, our aim will be to create a wrapper for a `User` domain object. We will want to wrap the user with a `PriceCalculator`, which calculates product prices taking into account user-specific discounts. To read the discounts, the calculator needs to access the database, using an instance of the `DatabaseAccess` class.

Hence ideally, we would want a partially-wired `PriceCalculator`, which has the database access provided, and which can be easily created for any given user. How to implement this using Scala and MacWire?

### Factories in modules {#factories_in_modules}

Firstly, factories may be parts of the &#8220;modules&#8221; which contain the wired instances. In such case, instead of a `val`, we should use a `def`.

The new feature in MacWire 0.3 is that the enclosing method&#8217;s parameters are also used for wiring. 

For example:

<pre lang="scala" line="1">case class User
class DatabaseAccess
class PriceCalculator(databaseAccess: DatabaseAccess, user: User)

trait ShoppingModule extends Macwire {
   lazy val databaseAccess = wire[DatabaseAccess]
   def priceCalculator(user: User) = wire[PriceCalculator]
}
</pre>

In this case the macro will expand the code to:

<pre lang="scala" line="1">trait ShoppingModule extends Macwire {
   lazy val databaseAccess = new DatabaseAccess
   def priceCalculator(user: User) = new PriceCalculator(databaseAccess, user)
}
</pre>

Note that this would also work if the module was nested in the def; hence we can create whole wired object graphs, using method parameters for wiring (&#8220;sub-modules&#8221;).

As in factories the parameters often represent data, there may be several parameters of the same type (this includes primitives). That&#8217;s why when wiring using enclosing method parameters, if multiple matches for a type are found, an additional by-name matching step is added.

### Injectable factories

This works very well in modules, but what if we need to pass the factory as a parameter to another class? For example, if we have a `SpecialOfferMailer`, which needs to create per-user `PriceCalculator`s? 

We could pass in a function object, and declare the dependency as follows:

<pre lang="scala" line="1">class SpecialOfferMailer(priceCalculator: User => PriceCalculator)
</pre>

This can be also viewed as a partially applied `PriceCalculator` constructor.

This could work well in simple cases, but has three drawbacks:

  * we need to repeat the whole function signature whenever we declare the dependency (however a type alias could help here)
  * we loose parameter names, which can cause code readability problems if our factory accepts primitives or multiple parameters of the same type
  * special support from MacWire would be needed to automatically convert `def`s to function objects, or a separate function object val would have to be defined manually.

We could also take the traditional route of defining a separate factory trait, e.g.:

<pre lang="scala" line="1">class PriceCalculator(databaseAccess: DatabaseAccess, user: User)

object PriceCalculator {
   trait Factory {
      def create(user: User): PriceCalculator
   }
}

class SpecialOfferMailer(priceCalculatorFactory: PriceCalculator.Factory) { ... }
</pre>

then we could also have some (not yet implemented) support for wiring such factories using MacWire, e.g.:

<pre lang="scala" line="1">trait ShoppingModule {
   lazy val priceCalculatorFactory 
          = wireFactory[PriceCalculator, PriceCalculator.Factory]
   ...
} 
</pre>

which would expand to:

<pre lang="scala" line="1">trait ShoppingModule {
   lazy val priceCalculatorFactory = new PriceCalculator.Factory {
      // the wire here uses values from the method parameters and from the 
      // outer module
      def create(user: User) = wire[PriceCalculator]
   }
   ...
}
</pre>

However again, this has drawbacks:

  * pretty verbose &#8211; separate trait
  * needs special-case support for auto-wiring
  * we need to repeat the factory parameters in the `create` def and in the class constructor

<span id="scala_factories"></span>

### Scala Factories

There is however another way &#8211; using case classes in a bit untypical manner we can have an elegant factory implementation. For example:

<pre lang="scala" line="1">class PriceCalculatorFactory(databaseAccess: DatabaseAccess) {
   case class create(user: User) {
      // methods
   }
}

class SpecialOfferMailer(priceCalculatorFactory: PriceCalculatorFactory)

// Nothing special here. Just plain ol' MacWire
trait ShoppingModule extends Macwire {
   lazy val databaseAccess = wire[DatabaseAccess]
   lazy val priceCalculatorFactory = wire[PriceCalculatorFactory]
   lazy val specialOfferMailer = wire[SpecialOfferMailer]
}
</pre>

Note that using this approach the service-parameters and the data-parameters are nicely separated (former being the main class parameters, latter being the nested case class parameters). Also no parameter list definition is repeated! And we don&#8217;t need any special support for auto-wiring.

Because the nested class is a case class, using `create` looks like a method invocation, while in fact it is a constructor of a new object, e.g.:

<pre lang="scala" line="1">class SpecialOfferMailer(...) {
   def mailOfferOfTheDay(user: User) {
      val priceCalculator = priceCalculatorFactory.create(user)
      products.foreach { product => 
          mailOffer(user, product, priceCalculator.price(product)) }
   }
}
</pre>

The type of the per-user price calculator object is `PriceCalculatorFactory#create`. This is a bit ugly, especially if need to pass it around, but we could add e.g. to the package object a type alias:

<pre lang="scala" line="1">type PriceCalculator = PriceCalculatorFactory#create
</pre>

The sources for [MacWire][1] are available on GitHub under the Apache2 license; and the binary release is in the central repository.

Have fun, and let me know what do you think about the Scala-factories implementation!

 [1]: https://github.com/adamw/macwire
 [2]: #scala_factories
