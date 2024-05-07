---
title: Missing OO and FP bridge in Scala
author: Adam Warski
type: blog
date: 2012-08-18T11:09:38+00:00
url: /blog/2012/08/missing-oo-and-fp-bridge-in-scala/
simplecatch-sidebarlayout:
  - default
dsq_thread_id:
  - 1051933729
wp-syntax-cache-content:
  - |
    a:9:{i:1;s:720:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">def</span> say<span style="color: #F78811;">&#40;</span>to<span style="color: #000080;">:</span> Person, what<span style="color: #000080;">:</span> String<span style="color: #F78811;">&#41;</span><span style="color: #000080;">:</span> String <span style="color: #000080;">=</span> <span style="color: #F78811;">&#123;</span> ... <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">def say(to: Person, what: String): String = { ... }</p></div>
    ";i:2;s:632:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">val</span> sayFun<span style="color: #000080;">:</span> <span style="color: #F78811;">&#40;</span>Person, String<span style="color: #F78811;">&#41;</span> <span style="color: #000080;">=&gt;</span> String <span style="color: #000080;">=</span> say <span style="color: #000080;">_</span></pre></td></tr></table><p class="theCode" style="display:none;">val sayFun: (Person, String) =&gt; String = say _</p></div>
    ";i:3;s:1839:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    6
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #008000; font-style: italic;">// Method with multiple parameter lists</span>
    <span style="color: #0000ff; font-weight: bold;">def</span> connect<span style="color: #F78811;">&#40;</span>person1<span style="color: #000080;">:</span> Person<span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#40;</span>person2<span style="color: #000080;">:</span> person<span style="color: #F78811;">&#41;</span><span style="color: #000080;">:</span> Connection <span style="color: #000080;">=</span> <span style="color: #F78811;">&#123;</span> ... <span style="color: #F78811;">&#125;</span>
    &nbsp;
    <span style="color: #008000; font-style: italic;">// Function, created by partially applying the previous method</span>
    <span style="color: #0000ff; font-weight: bold;">val</span> createConnectionWithAdam<span style="color: #000080;">:</span> Person <span style="color: #000080;">=&gt;</span> Connection <span style="color: #000080;">=</span> 
          connect<span style="color: #F78811;">&#40;</span><span style="color: #0000ff; font-weight: bold;">new</span> Person<span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;Adam&quot;</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span> <span style="color: #000080;">_</span></pre></td></tr></table><p class="theCode" style="display:none;">// Method with multiple parameter lists
    def connect(person1: Person)(person2: person): Connection = { ... }
    
    // Function, created by partially applying the previous method
    val createConnectionWithAdam: Person =&gt; Connection = 
          connect(new Person(&quot;Adam&quot;)) _</p></div>
    ";i:4;s:1236:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">class</span> Person<span style="color: #F78811;">&#40;</span>name<span style="color: #000080;">:</span> String, age<span style="color: #000080;">:</span> Int<span style="color: #F78811;">&#41;</span> <span style="color: #F78811;">&#123;</span> ... <span style="color: #F78811;">&#125;</span>
    &nbsp;
    <span style="color: #008000; font-style: italic;">// The &quot;signature&quot; of new is (String, Int) =&gt; Person</span>
    <span style="color: #0000ff; font-weight: bold;">val</span> somebody <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> Person<span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;John&quot;</span>, <span style="color: #F78811;">34</span><span style="color: #F78811;">&#41;</span></pre></td></tr></table><p class="theCode" style="display:none;">class Person(name: String, age: Int) { ... }
    
    // The &quot;signature&quot; of new is (String, Int) =&gt; Person
    val somebody = new Person(&quot;John&quot;, 34)</p></div>
    ";i:5;s:1703:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">val</span> makePerson<span style="color: #000080;">:</span> <span style="color: #F78811;">&#40;</span>String, Int<span style="color: #F78811;">&#41;</span> <span style="color: #000080;">=&gt;</span> Person <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> Person <span style="color: #000080;">_</span>
    &nbsp;
    <span style="color: #0000ff; font-weight: bold;">class</span> Person2<span style="color: #F78811;">&#40;</span>name<span style="color: #000080;">:</span> String<span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#40;</span>age<span style="color: #000080;">:</span> Int<span style="color: #F78811;">&#41;</span> <span style="color: #F78811;">&#123;</span> ... <span style="color: #F78811;">&#125;</span>
    <span style="color: #0000ff; font-weight: bold;">val</span> makeNewJack<span style="color: #000080;">:</span> Int <span style="color: #000080;">=&gt;</span> Person <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> Person2<span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;Jack&quot;</span><span style="color: #F78811;">&#41;</span> <span style="color: #000080;">_</span></pre></td></tr></table><p class="theCode" style="display:none;">val makePerson: (String, Int) =&gt; Person = new Person _
    
    class Person2(name: String)(age: Int) { ... }
    val makeNewJack: Int =&gt; Person = new Person2(&quot;Jack&quot;) _</p></div>
    ";i:6;s:1484:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">object</span> Person2 <span style="color: #F78811;">&#123;</span>
       <span style="color: #0000ff; font-weight: bold;">def</span> apply<span style="color: #F78811;">&#40;</span>name<span style="color: #000080;">:</span> String<span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#40;</span>age<span style="color: #000080;">:</span> Int<span style="color: #F78811;">&#41;</span> <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> Person2<span style="color: #F78811;">&#40;</span>name, age<span style="color: #F78811;">&#41;</span>
    <span style="color: #F78811;">&#125;</span>
    &nbsp;
    <span style="color: #0000ff; font-weight: bold;">val</span> makeNewJack<span style="color: #000080;">:</span> Int <span style="color: #000080;">=&gt;</span> Person <span style="color: #000080;">=</span> Person2<span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;Jack&quot;</span><span style="color: #F78811;">&#41;</span> <span style="color: #000080;">_</span></pre></td></tr></table><p class="theCode" style="display:none;">object Person2 {
       def apply(name: String)(age: Int) = new Person2(name, age)
    }
    
    val makeNewJack: Int =&gt; Person = Person2(&quot;Jack&quot;) _</p></div>
    ";i:7;s:4939:"
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
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #008000; font-style: italic;">// This service depends on a concrete Person instance</span>
    <span style="color: #0000ff; font-weight: bold;">class</span> Service3<span style="color: #F78811;">&#40;</span>service1<span style="color: #000080;">:</span> Service1, service2<span style="color: #000080;">:</span> Service2<span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#40;</span>person<span style="color: #000080;">:</span> Person<span style="color: #F78811;">&#41;</span> <span style="color: #F78811;">&#123;</span> 
       ... 
    <span style="color: #F78811;">&#125;</span>
    &nbsp;
    <span style="color: #008000; font-style: italic;">// Note that the multiple parameter notation above also provides a nice </span>
    <span style="color: #008000; font-style: italic;">// separation of the parameters that should be &quot;injected&quot; - services, </span>
    <span style="color: #008000; font-style: italic;">// and the data that can be variable.</span>
    &nbsp;
    <span style="color: #008000; font-style: italic;">// This service depends on Service1, and wants to create it having Person </span>
    <span style="color: #008000; font-style: italic;">// instances</span>
    <span style="color: #0000ff; font-weight: bold;">class</span> Service4<span style="color: #F78811;">&#40;</span>makeService3<span style="color: #000080;">:</span> Person <span style="color: #000080;">=&gt;</span> Service3<span style="color: #F78811;">&#41;</span> <span style="color: #F78811;">&#123;</span>
      <span style="color: #008000; font-style: italic;">// Usage:</span>
      <span style="color: #0000ff; font-weight: bold;">for</span> <span style="color: #F78811;">&#40;</span>person <span style="color: #000080;">&lt;</span>- persons<span style="color: #F78811;">&#41;</span> makeService3<span style="color: #F78811;">&#40;</span>person<span style="color: #F78811;">&#41;</span>.<span style="color: #000000;">doSomething</span><span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>
    <span style="color: #F78811;">&#125;</span>
    &nbsp;
    <span style="color: #0000ff; font-weight: bold;">class</span> Main <span style="color: #F78811;">&#123;</span>
       <span style="color: #008000; font-style: italic;">// Bootstrap: (or - no-framework DI container ;) )</span>
       <span style="color: #0000ff; font-weight: bold;">val</span> service1 <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> Service1
       <span style="color: #0000ff; font-weight: bold;">val</span> service2 <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> Service2
       <span style="color: #008000; font-style: italic;">// That's the part that is illegal in Scala</span>
       <span style="color: #0000ff; font-weight: bold;">val</span> makeService3 <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> Service3<span style="color: #F78811;">&#40;</span>service1, service2<span style="color: #F78811;">&#41;</span> <span style="color: #000080;">_</span>
       <span style="color: #0000ff; font-weight: bold;">val</span> service4 <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> Service4<span style="color: #F78811;">&#40;</span>makeService1<span style="color: #F78811;">&#41;</span>
    &nbsp;
       ...
    &nbsp;
       <span style="color: #008000; font-style: italic;">// Today we'd have to write: val makeService3 = </span>
       <span style="color: #008000; font-style: italic;">//     (person: Person) =&gt; new Service3(service1, service2, person)</span>
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">// This service depends on a concrete Person instance
    class Service3(service1: Service1, service2: Service2)(person: Person) { 
       ... 
    }
    
    // Note that the multiple parameter notation above also provides a nice 
    // separation of the parameters that should be &quot;injected&quot; - services, 
    // and the data that can be variable.
    
    // This service depends on Service1, and wants to create it having Person 
    // instances
    class Service4(makeService3: Person =&gt; Service3) {
      // Usage:
      for (person &lt;- persons) makeService3(person).doSomething()
    }
    
    class Main {
       // Bootstrap: (or - no-framework DI container ;) )
       val service1 = new Service1
       val service2 = new Service2
       // That's the part that is illegal in Scala
       val makeService3 = new Service3(service1, service2) _
       val service4 = new Service4(makeService1)
    
       ...
    
       // Today we'd have to write: val makeService3 = 
       //     (person: Person) =&gt; new Service3(service1, service2, person)
    }</p></div>
    ";i:8;s:524:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;">Person.<span style="color: #0000ff; font-weight: bold;">new</span><span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;John&quot;</span>, <span style="color: #F78811;">34</span><span style="color: #F78811;">&#41;</span></pre></td></tr></table><p class="theCode" style="display:none;">Person.new(&quot;John&quot;, 34)</p></div>
    ";i:9;s:1809:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">class</span> Person<span style="color: #F78811;">&#40;</span>name<span style="color: #000080;">:</span> String, age<span style="color: #000080;">:</span> Int<span style="color: #F78811;">&#41;</span>
    <span style="color: #0000ff; font-weight: bold;">val</span> makePerson <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> Person <span style="color: #000080;">_</span> <span style="color: #008000; font-style: italic;">// type: (String, Int) =&gt; Person </span>
    &nbsp;
    <span style="color: #0000ff; font-weight: bold;">class</span> Person2<span style="color: #F78811;">&#40;</span>name<span style="color: #000080;">:</span> String<span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#40;</span>age<span style="color: #000080;">:</span> Int<span style="color: #F78811;">&#41;</span>
    <span style="color: #0000ff; font-weight: bold;">val</span> makeNewJack <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> Person2<span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;Jack&quot;</span><span style="color: #F78811;">&#41;</span> <span style="color: #000080;">_</span> <span style="color: #008000; font-style: italic;">// type: Int =&gt; Person</span></pre></td></tr></table><p class="theCode" style="display:none;">class Person(name: String, age: Int)
    val makePerson = new Person _ // type: (String, Int) =&gt; Person 
    
    class Person2(name: String)(age: Int)
    val makeNewJack = new Person2(&quot;Jack&quot;) _ // type: Int =&gt; Person</p></div>
    ";}
tags:
  - functional programming
  - scala
  - clean code

---
[Scala][1] blends functional and object-oriented programming in many nice ways. You can use both FP an OO-like constructs whichever fits the current problem better. But there&#8217;s always room for improvement! Here&#8217;s one thing I think is missing ([short version][2] at the bottom).

### FP side

It&#8217;s easy to convert a method to a function in Scala. For example, if you have a method:
```scala
def say(to: Person, what: String): String = { ... } 
```

we can get the corresponding function using the underscore notation:
```scala
val sayFun: (Person, String) => String = say _
```

Moreover, Scala supports multiple parameter lists, which is sometimes also referred to as [currying][3], and which makes partial application easier:
```scala
// Method with multiple parameter lists
def connect(person1: Person)(person2: person): Connection = { ... }

// Function, created by partially applying the previous method
val createConnectionWithAdam: Person => Connection = 
      connect(new Person("Adam")) _
```

### OO side

One thing every class has is a constructor. But what is a constructor, really? You give values for the constructor&#8217;s arguments, and get a new instance of the class in return. So it&#8217;s really just a function!
```scala
class Person(name: String, age: Int) { ... }

// The "signature" of new is (String, Int) => Person
val somebody = new Person("John", 34)
```

### Link?

However, that&#8217;s where the combination of OO and FP fails in Scala: you can&#8217;t use the two features of methods (convert to function, currying) mentioned above with constructors. Both of these **won&#8217;t work**:
```scala
val makePerson: (String, Int) => Person = new Person _

class Person2(name: String)(age: Int) { ... }
val makeNewJack: Int => Person = new Person2("Jack") _
```

You can get around this using companion objects and `apply` (or any other factory method, apply just has a nicer notation afterwards):
```scala
object Person2 {
   def apply(name: String)(age: Int) = new Person2(name, age)
}

val makeNewJack: Int => Person = Person2("Jack") _
```

But that requires repeating the signature of the constructor in the companion object, and nobody likes code duplication, right? ;)

### Use-case

Where can this be useful? For example in the classic factory example. Imagine you have a class which depends on some services, but also on some data available at runtime. Of course we use IoC so instances of the other services are provided to our class:
```scala
// This service depends on a concrete Person instance
class Service3(service1: Service1, service2: Service2)(person: Person) { 
   ... 
}

// Note that the multiple parameter notation above also provides a nice 
// separation of the parameters that should be "injected" - services, 
// and the data that can be variable.

// This service depends on Service1, and wants to create it having Person 
// instances
class Service4(makeService3: Person => Service3) {
  // Usage:
  for (person <- persons) makeService3(person).doSomething()
}

class Main {
   // Bootstrap: (or - no-framework DI container ;) )
   val service1 = new Service1
   val service2 = new Service2
   // That's the part that is illegal in Scala
   val makeService3 = new Service3(service1, service2) _
   val service4 = new Service4(makeService1)

   ...

   // Today we'd have to write: val makeService3 = 
   //     (person: Person) => new Service3(service1, service2, person)
}
```

That&#8217;s also connected to my post on [DI and OO][4], and how current DI frameworks make it hard to define services which depend on data and services that we&#8217;d like to have multiple copies of.

### Side note

When viewing constructors as methods/functions (which they are, really ;) ), I suppose the Ruby-like notation:
```scala
Person.new("John", 34)
```

would be better and adding support for _ and multiple parameter lists would be obvious.

<a name="tldr_short"></a>

### Bottom line for TL;DR fans

Why not treat class constructors as every other method/function? Make this legal:
```scala
class Person(name: String, age: Int)
val makePerson = new Person _ // type: (String, Int) => Person 

class Person2(name: String)(age: Int)
val makeNewJack = new Person2("Jack") _ // type: Int => Person
```

Adam

 [1]: http://www.scala-lang.org/
 [2]: #tldr_short
 [3]: http://www.scala-lang.org/node/135
 [4]: http://www.warski.org/blog/2010/10/dependency-injection-discourages-object-oriented-programming/ "Dependency injection discourages object-oriented programming?"
