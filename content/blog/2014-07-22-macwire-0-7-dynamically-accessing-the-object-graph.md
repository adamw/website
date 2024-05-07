---
title: 'MacWire 0.7: dynamically accessing the object graph'
author: Adam Warski
type: blog
date: 2014-07-22T11:24:42+00:00
url: /blog/2014/07/macwire-0-7-dynamically-accessing-the-object-graph/
simplecatch-sidebarlayout:
  - default
dsq_thread_id:
  - 2863835964
wp-syntax-cache-content:
  - |
    a:1:{i:1;s:4766:"
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
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #008000; font-style: italic;">// 1. Defining the object graph and the module</span>
    <span style="color: #0000ff; font-weight: bold;">class</span> SecurityFilter
    &nbsp;
    <span style="color: #0000ff; font-weight: bold;">trait</span> DatabaseConnector
    <span style="color: #0000ff; font-weight: bold;">class</span> MysqlDatabaseConnector <span style="color: #0000ff; font-weight: bold;">extends</span> DatabaseConnector
    &nbsp;
    <span style="color: #0000ff; font-weight: bold;">class</span> MyApp <span style="color: #F78811;">&#123;</span>
        <span style="color: #0000ff; font-weight: bold;">def</span> securityFilter <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> SecurityFilter<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>
        <span style="color: #0000ff; font-weight: bold;">val</span> databaseConnector <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> MysqlDatabaseConnector<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>
    <span style="color: #F78811;">&#125;</span>
    &nbsp;
    <span style="color: #008000; font-style: italic;">// 2. Creating a Wired instance</span>
    <span style="color: #0000ff; font-weight: bold;">import</span> MacwireMacros.<span style="color: #000080;">_</span>
    <span style="color: #0000ff; font-weight: bold;">val</span> wired <span style="color: #000080;">=</span> wiredInModule<span style="color: #F78811;">&#40;</span><span style="color: #0000ff; font-weight: bold;">new</span> MyApp<span style="color: #F78811;">&#41;</span>
    &nbsp;
    <span style="color: #008000; font-style: italic;">// 3. Dynamic lookup of instances</span>
    wired.<span style="color: #000000;">lookup</span><span style="color: #F78811;">&#40;</span>classOf<span style="color: #F78811;">&#91;</span>SecurityFilter<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#41;</span>
    &nbsp;
    <span style="color: #008000; font-style: italic;">// Returns the mysql database connector, even though its type is </span>
    <span style="color: #008000; font-style: italic;">// MysqlDatabaseConnector, which is assignable to DatabaseConnector.</span>
    wired.<span style="color: #000000;">lookup</span><span style="color: #F78811;">&#40;</span>classOf<span style="color: #F78811;">&#91;</span>DatabaseConnector<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#41;</span>
    &nbsp;
    <span style="color: #008000; font-style: italic;">// 4. Instantiation using the available dependencies</span>
    <span style="color: #F78811;">&#123;</span>
        <span style="color: #0000ff; font-weight: bold;">package</span> com.<span style="color: #000000;">softwaremill</span>
        <span style="color: #0000ff; font-weight: bold;">class</span> AuthenticationPlugin<span style="color: #F78811;">&#40;</span>databaseConnector<span style="color: #000080;">:</span> DatabaseConnector<span style="color: #F78811;">&#41;</span>
    <span style="color: #F78811;">&#125;</span>
    &nbsp;
    <span style="color: #008000; font-style: italic;">// Creates a new instance of the given class using the dependencies </span>
    <span style="color: #008000; font-style: italic;">// available in MyApp</span>
    wired.<span style="color: #000000;">wireClassInstanceByName</span><span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;com.softwaremill.AuthenticationPlugin&quot;</span><span style="color: #F78811;">&#41;</span></pre></td></tr></table><p class="theCode" style="display:none;">// 1. Defining the object graph and the module
    class SecurityFilter
    
    trait DatabaseConnector
    class MysqlDatabaseConnector extends DatabaseConnector
    
    class MyApp {
        def securityFilter = new SecurityFilter()
        val databaseConnector = new MysqlDatabaseConnector()
    }
    
    // 2. Creating a Wired instance
    import MacwireMacros._
    val wired = wiredInModule(new MyApp)
    
    // 3. Dynamic lookup of instances
    wired.lookup(classOf[SecurityFilter])
    
    // Returns the mysql database connector, even though its type is 
    // MysqlDatabaseConnector, which is assignable to DatabaseConnector.
    wired.lookup(classOf[DatabaseConnector])
    
    // 4. Instantiation using the available dependencies
    {
        package com.softwaremill
        class AuthenticationPlugin(databaseConnector: DatabaseConnector)
    }
    
    // Creates a new instance of the given class using the dependencies 
    // available in MyApp
    wired.wireClassInstanceByName(&quot;com.softwaremill.AuthenticationPlugin&quot;)</p></div>
    ";}
tags:
  - dependency injection
  - macwire
  - modularity
  - scala

---
_[MacWire][1] is a Lightweight and Nonintrusive Scala Dependency Injection library._

While it would be great to be able to define in a type-safe way the whole object graph for an application upfront, there are cases when it is necessary to access and extend it dynamically. That’s why [MacWire 0.7][1] contains new API and functionality for that purpose.

First use-case is when integrating with web frameworks, such as [Play 2][2]. There it is often needed to access a wired instance by-class. Such functionality was available in MacWire before (with instance maps), however using it was cumbersome. For a full example, see the [Play-MacWire activator][3].

Second use-case is dynamically creating instances of classes, which names are only known at run-time, such as plugins. It is now possible to instantiate such classes, using the dependencies defined in a module.

Both of these use-cases can be realised by the `Wired` class, which can be created given an instance of a module, containing the object graph, using the `wiredInModule` macro. Any `val`s, `lazy val`s and parameter-less `def`s will be available. An instance of `Wired` can be also extended with new instances and instance factories.

For example:
```scala
// 1. Defining the object graph and the module
class SecurityFilter

trait DatabaseConnector
class MysqlDatabaseConnector extends DatabaseConnector

class MyApp {
    def securityFilter = new SecurityFilter()
    val databaseConnector = new MysqlDatabaseConnector()
}

// 2. Creating a Wired instance
import MacwireMacros._
val wired = wiredInModule(new MyApp)

// 3. Dynamic lookup of instances
wired.lookup(classOf[SecurityFilter])

// Returns the mysql database connector, even though its type is 
// MysqlDatabaseConnector, which is assignable to DatabaseConnector.
wired.lookup(classOf[DatabaseConnector])

// 4. Instantiation using the available dependencies
{
    package com.softwaremill
    class AuthenticationPlugin(databaseConnector: DatabaseConnector)
}

// Creates a new instance of the given class using the dependencies 
// available in MyApp
wired.wireClassInstanceByName("com.softwaremill.AuthenticationPlugin")
```

Also, if you are an existing or future user of MacWire, remember to [+1 the features][4] you’d like to see implemented.

 [1]: https://github.com/adamw/macwire
 [2]: http://www.playframework.com/
 [3]: http://typesafe.com/activator/template/macwire-activator
 [4]: https://github.com/adamw/macwire#future-development---vote
