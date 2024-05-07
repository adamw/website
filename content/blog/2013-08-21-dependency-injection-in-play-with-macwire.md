---
title: Dependency Injection in Play! with MacWire
author: Adam Warski
type: post
date: 2013-08-21T17:05:37+00:00
url: /blog/2013/08/dependency-injection-in-play-with-macwire/
simplecatch-sidebarlayout:
  - default
dsq_thread_id:
  - 1624520417
wp-syntax-cache-content:
  - |
    a:7:{i:1;s:1685:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    6
    7
    8
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">object</span> Example1 <span style="color: #F78811;">&#123;</span>
       <span style="color: #0000ff; font-weight: bold;">val</span> aString <span style="color: #000080;">=</span> <span style="color: #6666FF;">&quot;Hello World!&quot;</span>
       <span style="color: #0000ff; font-weight: bold;">val</span> userAuthenticator <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> UserAuthenticator<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>
       <span style="color: #008000; font-style: italic;">// here we are using the core MacWire feature: the wire macro</span>
       <span style="color: #0000ff; font-weight: bold;">val</span> userFinder <span style="color: #000080;">=</span> wire<span style="color: #F78811;">&#91;</span>UserFinder<span style="color: #F78811;">&#93;</span> 
    <span style="color: #F78811;">&#125;</span>
    &nbsp;
    <span style="color: #0000ff; font-weight: bold;">val</span> theMap <span style="color: #000080;">=</span> valsByClass<span style="color: #F78811;">&#40;</span>Example1<span style="color: #F78811;">&#41;</span></pre></td></tr></table><p class="theCode" style="display:none;">object Example1 {
       val aString = &quot;Hello World!&quot;
       val userAuthenticator = new UserAuthenticator()
       // here we are using the core MacWire feature: the wire macro
       val userFinder = wire[UserFinder] 
    }
    
    val theMap = valsByClass(Example1)</p></div>
    ";i:2;s:1490:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">val</span> theMap <span style="color: #000080;">=</span> Map<span style="color: #F78811;">&#91;</span>Class<span style="color: #F78811;">&#91;</span><span style="color: #000080;">_</span><span style="color: #F78811;">&#93;</span>, AnyRef<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#40;</span>
       classOf<span style="color: #F78811;">&#91;</span>String<span style="color: #F78811;">&#93;</span> -<span style="color: #000080;">&gt;</span> Example1.<span style="color: #000000;">aString</span>,
       classOf<span style="color: #F78811;">&#91;</span>UserAuthenticator<span style="color: #F78811;">&#93;</span> -<span style="color: #000080;">&gt;</span> Example1.<span style="color: #000000;">userAuthenticator</span>,
       classOf<span style="color: #F78811;">&#91;</span>UserFinder<span style="color: #F78811;">&#93;</span> -<span style="color: #000080;">&gt;</span> Example1.<span style="color: #000000;">userFinder</span>
    <span style="color: #F78811;">&#41;</span></pre></td></tr></table><p class="theCode" style="display:none;">val theMap = Map[Class[_], AnyRef](
       classOf[String] -&gt; Example1.aString,
       classOf[UserAuthenticator] -&gt; Example1.userAuthenticator,
       classOf[UserFinder] -&gt; Example1.userFinder
    )</p></div>
    ";i:3;s:1850:"
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
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">trait</span> Database
    <span style="color: #0000ff; font-weight: bold;">class</span> MySQLDatabase <span style="color: #0000ff; font-weight: bold;">extends</span> Database
    &nbsp;
    <span style="color: #0000ff; font-weight: bold;">object</span> App <span style="color: #F78811;">&#123;</span>
       <span style="color: #0000ff; font-weight: bold;">val</span> database <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> MySQLDatabase<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>
    <span style="color: #F78811;">&#125;</span>
    &nbsp;
    <span style="color: #0000ff; font-weight: bold;">val</span> instanceLookup <span style="color: #000080;">=</span> InstanceLookup<span style="color: #F78811;">&#40;</span>valsByClass<span style="color: #F78811;">&#40;</span>App<span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span>
    &nbsp;
    require<span style="color: #F78811;">&#40;</span>instanceLookup<span style="color: #F78811;">&#40;</span>classOf<span style="color: #F78811;">&#91;</span>Database<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#41;</span> <span style="color: #000080;">==</span> App.<span style="color: #000000;">database</span><span style="color: #F78811;">&#41;</span></pre></td></tr></table><p class="theCode" style="display:none;">trait Database
    class MySQLDatabase extends Database
    
    object App {
       val database = new MySQLDatabase()
    }
    
    val instanceLookup = InstanceLookup(valsByClass(App))
    
    require(instanceLookup(classOf[Database]) == App.database)</p></div>
    ";i:4;s:2385:"
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
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">package</span> controllers
    &nbsp;
    <span style="color: #0000ff; font-weight: bold;">import</span> play.<span style="color: #000000;">api</span>.<span style="color: #000000;">mvc</span>.<span style="color: #000080;">_</span>
    <span style="color: #0000ff; font-weight: bold;">import</span> services.<span style="color: #000000;">HelloWorldProvider</span>
    &nbsp;
    <span style="color: #0000ff; font-weight: bold;">class</span> MainController<span style="color: #F78811;">&#40;</span>helloWorldProvider<span style="color: #000080;">:</span> HelloWorldProvider<span style="color: #F78811;">&#41;</span> <span style="color: #0000ff; font-weight: bold;">extends</span> Controller <span style="color: #F78811;">&#123;</span>
      <span style="color: #0000ff; font-weight: bold;">def</span> index <span style="color: #000080;">=</span> Action <span style="color: #F78811;">&#123;</span>
        Ok<span style="color: #F78811;">&#40;</span>views.<span style="color: #000000;">html</span>.<span style="color: #000000;">index</span><span style="color: #F78811;">&#40;</span>helloWorldProvider.<span style="color: #000000;">text</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span>
      <span style="color: #F78811;">&#125;</span>
    <span style="color: #F78811;">&#125;</span>
    &nbsp;
    <span style="color: #0000ff; font-weight: bold;">package</span> services
    &nbsp;
    <span style="color: #0000ff; font-weight: bold;">class</span> HelloWorldProvider <span style="color: #F78811;">&#123;</span>
       <span style="color: #0000ff; font-weight: bold;">def</span> text <span style="color: #000080;">=</span> <span style="color: #6666FF;">&quot;Hello World!&quot;</span>
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">package controllers
    
    import play.api.mvc._
    import services.HelloWorldProvider
    
    class MainController(helloWorldProvider: HelloWorldProvider) extends Controller {
      def index = Action {
        Ok(views.html.index(helloWorldProvider.text))
      }
    }
    
    package services
    
    class HelloWorldProvider {
       def text = &quot;Hello World!&quot;
    }</p></div>
    ";i:5;s:1073:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">trait</span> OurApplication <span style="color: #F78811;">&#123;</span>
       lazy <span style="color: #0000ff; font-weight: bold;">val</span> helloWorldProvider <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> HelloWorldProvider
       lazy <span style="color: #0000ff; font-weight: bold;">val</span> mainController <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> MainController<span style="color: #F78811;">&#40;</span>helloWorldProvider<span style="color: #F78811;">&#41;</span>
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">trait OurApplication {
       lazy val helloWorldProvider = new HelloWorldProvider
       lazy val mainController = new MainController(helloWorldProvider)
    }</p></div>
    ";i:6;s:696:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #008000; font-style: italic;">// File: conf/routes</span>
    &nbsp;
    GET     /                           <span style="color: #000080;">@</span>controllers.<span style="color: #000000;">MainController</span>.<span style="color: #000000;">index</span><span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span></pre></td></tr></table><p class="theCode" style="display:none;">// File: conf/routes
    
    GET     /                           @controllers.MainController.index()</p></div>
    ";i:7;s:2520:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    6
    7
    8
    9
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">import</span> com.<span style="color: #000000;">softwaremill</span>.<span style="color: #000000;">macwire</span>.<span style="color: #F78811;">&#123;</span>InstanceLookup, Macwire<span style="color: #F78811;">&#125;</span>
    <span style="color: #0000ff; font-weight: bold;">import</span> play.<span style="color: #000000;">api</span>.<span style="color: #000000;">GlobalSettings</span>
    &nbsp;
    <span style="color: #0000ff; font-weight: bold;">object</span> Global <span style="color: #0000ff; font-weight: bold;">extends</span> GlobalSettings <span style="color: #0000ff; font-weight: bold;">with</span> Macwire <span style="color: #F78811;">&#123;</span>
      <span style="color: #0000ff; font-weight: bold;">val</span> instanceLookup <span style="color: #000080;">=</span> InstanceLookup<span style="color: #F78811;">&#40;</span>valsByClass<span style="color: #F78811;">&#40;</span><span style="color: #0000ff; font-weight: bold;">new</span> OurApplication <span style="color: #F78811;">&#123;</span><span style="color: #F78811;">&#125;</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span>
    &nbsp;
      <span style="color: #0000ff; font-weight: bold;">override</span> <span style="color: #0000ff; font-weight: bold;">def</span> getControllerInstance<span style="color: #F78811;">&#91;</span>A<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#40;</span>controllerClass<span style="color: #000080;">:</span> Class<span style="color: #F78811;">&#91;</span>A<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#41;</span> <span style="color: #000080;">=</span> 
          instanceLookup.<span style="color: #000000;">lookupSingleOrThrow</span><span style="color: #F78811;">&#40;</span>controllerClass<span style="color: #F78811;">&#41;</span>
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">import com.softwaremill.macwire.{InstanceLookup, Macwire}
    import play.api.GlobalSettings
    
    object Global extends GlobalSettings with Macwire {
      val instanceLookup = InstanceLookup(valsByClass(new OurApplication {}))
    
      override def getControllerInstance[A](controllerClass: Class[A]) = 
          instanceLookup.lookupSingleOrThrow(controllerClass)
    }</p></div>
    ";}
tags:
  - dependency injection
  - macwire
  - scala

---
The most recent release of [MacWire (0.4)][1] (a Scala macro to generate wiring code for class instantiation, DI container replacement) comes with new utilities which make it easier to integrate with frameworks which require **by-class instance lookup**. An example of such a framework is [Play!][2], which is quite quickly gaining popularity in the web-development world.

There are two ways to find out how to do no-framework container-less dependency injection in Play! (and other web frameworks) with MacWire:

  1. read this blog
  2. try the [MacWire+Play Typesafe Activator][3]

Or you can do both ;)

### Motivation

Many web frameworks require some form of generic instance lookups, e.g. when controller instances are referenced from views. In traditional DI containers like [Guice][4], this is provided out-of-the-box, as it is the main way to get the wired objects. For example in Guice we have the `Injector` which translates `Class` objects into instances of the given class.

When using MacWire (or when doing &#8220;manual&#8221; DI), we are doing as much as possible in a type-safe way, simply referencing the objects as fields/methods in a container object, so we don&#8217;t usually need such maps. But for web framework integration, that is still needed.

### General utilities

MacWire 0.4 comes with two general utilities useful for doing by-class instance lookups.

The first is a macro, `valsByClass(object)` which generates (at compile-time of course) a map of `val`s in the given object, keyed by their classes. So for example:

<pre lang="scala" line="1">object Example1 {
   val aString = "Hello World!"
   val userAuthenticator = new UserAuthenticator()
   // here we are using the core MacWire feature: the wire macro
   val userFinder = wire[UserFinder] 
}

val theMap = valsByClass(Example1)
</pre>

will translate the following code:

<pre lang="scala" line="1">val theMap = Map[Class[_], AnyRef](
   classOf[String] -> Example1.aString,
   classOf[UserAuthenticator] -> Example1.userAuthenticator,
   classOf[UserFinder] -> Example1.userFinder
)
</pre>

The second utility, `InstanceLookup`, extends that map to allow by-subclass/by-trait lookups. For example, say we have a `Database` trait and an implementation, `MySQLDatabase`, which ends up in the vals-by-class map under the `Class[MySQLDatabase]` key.

However, we would still like to get the instance when querying by `Database`. We can achieve that by wrapping the map with `InstanceLookup`:

<pre lang="scala" line="1">trait Database
class MySQLDatabase extends Database

object App {
   val database = new MySQLDatabase()
}

val instanceLookup = InstanceLookup(valsByClass(App))

require(instanceLookup(classOf[Database]) == App.database)
</pre>

### Play integration

Having these utilities we may now proceed to integrating MacWire with Play.

The main change is that controllers now won&#8217;t be `object`s, but `class`es (usually with non-empty constructor parameter lists &#8211; the dependencies). The challenge is now how to tell Play how to get controller istances?

Let&#8217;s say we have the following controller and service class:

<pre lang="scala" line="1">package controllers

import play.api.mvc._
import services.HelloWorldProvider

class MainController(helloWorldProvider: HelloWorldProvider) extends Controller {
  def index = Action {
    Ok(views.html.index(helloWorldProvider.text))
  }
}

package services

class HelloWorldProvider {
   def text = "Hello World!"
}
</pre>

Here the controller (`MainController`) has one dependency, the `HelloWorldProvider` service.

Now that we have the example ready, we can proceed with the integration. There are three easy steps. Firstly we need to wire the dependencies. We can use the `wire[]` macro, or just go with manual DI:

<pre lang="scala" line="1">trait OurApplication {
   lazy val helloWorldProvider = new HelloWorldProvider
   lazy val mainController = new MainController(helloWorldProvider)
}
</pre>

Secondly, we need to specify in the routes file that we want to provide controller instances ourselves, instead of letting Play instantiate the controllers. This is done by adding the `@` character before the controller name:

<pre lang="scala" line="1">// File: conf/routes

GET     /                           @controllers.MainController.index()
</pre>

Finally, we need to create a `Global` object in the main package. That&#8217;s the main integration point; here we use the by-class instance lookup maps:

<pre lang="scala" line="1">import com.softwaremill.macwire.{InstanceLookup, Macwire}
import play.api.GlobalSettings

object Global extends GlobalSettings with Macwire {
  val instanceLookup = InstanceLookup(valsByClass(new OurApplication {}))

  override def getControllerInstance[A](controllerClass: Class[A]) = 
      instanceLookup.lookupSingleOrThrow(controllerClass)
}
</pre>

And that&#8217;s it! From now on the controller instances will be provided from our map, using the wiring we defined in the `OurApplication` trait.

Be sure to checkout the [Activator][3] if you want to see a working example in action!

Adam

 [1]: https://github.com/adamw/macwire
 [2]: http://www.playframework.com/
 [3]: http://www.typesafe.com/activator/template/macwire-activator
 [4]: https://code.google.com/p/google-guice/
