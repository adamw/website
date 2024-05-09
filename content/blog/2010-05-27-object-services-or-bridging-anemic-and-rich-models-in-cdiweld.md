---
title: Object Services, or bridging anemic and rich models, in CDI/Weld
author: Adam Warski
type: blog
date: 2010-05-27T09:43:31+00:00
url: /blog/2010/05/object-services-or-bridging-anemic-and-rich-models-in-cdiweld/
disqus_identifier: 1051935137
wp-syntax-cache-content:
  - |
    a:4:{i:1;s:741:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;"><span style="color: #000000; font-weight: bold;">abstract</span> <span style="color: #000000; font-weight: bold;">class</span> Animal
    <span style="color: #000000; font-weight: bold;">class</span> Elephant <span style="color: #000000; font-weight: bold;">extends</span> Animal
    <span style="color: #000000; font-weight: bold;">class</span> Ant <span style="color: #000000; font-weight: bold;">extends</span> Animal</pre></td></tr></table><p class="theCode" style="display:none;">abstract class Animal
    class Elephant extends Animal
    class Ant extends Animal</p></div>
    ";i:2;s:3850:"
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
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;"><span style="color: #000000; font-weight: bold;">interface</span> PaintService<span style="color: #339933;">&lt;</span>T <span style="color: #000000; font-weight: bold;">extends</span> Animal<span style="color: #339933;">&gt;</span> <span style="color: #000000; font-weight: bold;">extends</span> OS<span style="color: #339933;">&lt;</span>T<span style="color: #339933;">&gt;</span> <span style="color: #009900;">&#123;</span> 
       <span style="color: #000066; font-weight: bold;">void</span> paint<span style="color: #009900;">&#40;</span><span style="color: #003399;">Canvas</span> c<span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span> 
    <span style="color: #009900;">&#125;</span>
    &nbsp;
    <span style="color: #666666; font-style: italic;">// Implements OS&lt;Elephant&gt;</span>
    <span style="color: #000000; font-weight: bold;">class</span> ElephantPaintService <span style="color: #000000; font-weight: bold;">implements</span> PaintService<span style="color: #339933;">&lt;</span>Elephant<span style="color: #339933;">&gt;</span> <span style="color: #009900;">&#123;</span> 
       <span style="color: #666666; font-style: italic;">// Here we can store the object, for which the service was invoked</span>
       <span style="color: #000066; font-weight: bold;">void</span> setServiced<span style="color: #009900;">&#40;</span>Elephant e<span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span> ... <span style="color: #009900;">&#125;</span>
       <span style="color: #000066; font-weight: bold;">void</span> paint<span style="color: #009900;">&#40;</span><span style="color: #003399;">Canvas</span> c<span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span> ... <span style="color: #009900;">&#125;</span>
    <span style="color: #009900;">&#125;</span>
    &nbsp;
    <span style="color: #666666; font-style: italic;">// Implements OS&lt;Ant&gt;</span>
    <span style="color: #000000; font-weight: bold;">class</span> AntPaintService <span style="color: #000000; font-weight: bold;">implements</span> PaintService<span style="color: #339933;">&lt;</span>Ant<span style="color: #339933;">&gt;</span> <span style="color: #009900;">&#123;</span> 
       <span style="color: #666666; font-style: italic;">// Injection works normally</span>
       @Inject AnthillService anthill<span style="color: #339933;">;</span>
    &nbsp;
       <span style="color: #000066; font-weight: bold;">void</span> setServiced<span style="color: #009900;">&#40;</span>Ant a<span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span> ... <span style="color: #009900;">&#125;</span>
       <span style="color: #000066; font-weight: bold;">void</span> paint<span style="color: #009900;">&#40;</span><span style="color: #003399;">Canvas</span> c<span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span> ... <span style="color: #009900;">&#125;</span> 
    <span style="color: #009900;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">interface PaintService&lt;T extends Animal&gt; extends OS&lt;T&gt; { 
       void paint(Canvas c); 
    }
    
    // Implements OS&lt;Elephant&gt;
    class ElephantPaintService implements PaintService&lt;Elephant&gt; { 
       // Here we can store the object, for which the service was invoked
       void setServiced(Elephant e) { ... }
       void paint(Canvas c) { ... }
    }
    
    // Implements OS&lt;Ant&gt;
    class AntPaintService implements PaintService&lt;Ant&gt; { 
       // Injection works normally
       @Inject AnthillService anthill;
    
       void setServiced(Ant a) { ... }
       void paint(Canvas c) { ... } 
    }</p></div>
    ";i:3;s:1218:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    6
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;">@Inject
    OSP<span style="color: #339933;">&lt;</span>Animal, PaintService<span style="color: #339933;">&lt;</span>Animal<span style="color: #339933;">&gt;&gt;</span> paintService<span style="color: #339933;">;</span>
    &nbsp;
    <span style="color: #000066; font-weight: bold;">void</span> paint<span style="color: #009900;">&#40;</span>Animal a, <span style="color: #003399;">Canvas</span> c<span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
       paintService.<span style="color: #006633;">f</span><span style="color: #009900;">&#40;</span>a<span style="color: #009900;">&#41;</span>.<span style="color: #006633;">paint</span><span style="color: #009900;">&#40;</span>c<span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
    <span style="color: #009900;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">@Inject
    OSP&lt;Animal, PaintService&lt;Animal&gt;&gt; paintService;
    
    void paint(Animal a, Canvas c) {
       paintService.f(a).paint(c);
    }</p></div>
    ";i:4;s:1506:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    6
    7
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;"><span style="color: #000066; font-weight: bold;">void</span> test<span style="color: #009900;">&#40;</span><span style="color: #003399;">Canvas</span> c<span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
       <span style="color: #666666; font-style: italic;">// Will invoke paint(c) in AntPaintService</span>
       paint<span style="color: #009900;">&#40;</span><span style="color: #000000; font-weight: bold;">new</span> Ant<span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span>, c<span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
    &nbsp;
       <span style="color: #666666; font-style: italic;">// Will invoke paint(c) in ElephantPaintService</span>
       paint<span style="color: #009900;">&#40;</span><span style="color: #000000; font-weight: bold;">new</span> Elephant<span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span>, c<span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
    <span style="color: #009900;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">void test(Canvas c) {
       // Will invoke paint(c) in AntPaintService
       paint(new Ant(), c);
    
       // Will invoke paint(c) in ElephantPaintService
       paint(new Elephant(), c);
    }</p></div>
    ";}
tags:
  - jee
  - java

---
Rich domain models are certainly a nice, object-oriented idea, but I always had one problem with them: what if they become bloated with completely unrelated methods? For objects that are frequently used in a system, we may want to add various methods, which depend on the actual class of the object. Also, what if we&#8217;d like to use some other (e.g. CDI) beans as part of the method logic? Normally in DI frameworks there&#8217;s no injection into model classes. Or we want to add a frontend-specific method, but we receive the instances from a backend service?

**Object Services** try to address the issues above. Suppose we have a simple class hierarchy of animals:
```java
abstract class Animal
class Elephant extends Animal
class Ant extends Animal
```

and we want to implement a `paint` method, which paints a picture of the given animal on a canvas. Quite obviously, painting an elephant is different from painting an ant. There are several solutions:

  * add a paint method to the `Animal` interface &#8211; problems outlined above
  * add a paint method, in which we check which `Animal` was passed using `instanceof` &#8211; quite ugly
  * use the visitor pattern &#8211; typesafe, but quite verbose

I think the best solution would be to have type-safe &#8220;polymorphic extension methods&#8221;, so that in your code you could just add some methods to each class in a hierarchy, but unfortunately this isn&#8217;t supported by any Java (see also [multiple dispatch][1]).

Another possibility is to use what I call &#8220;Object Services&#8221;. If we want to add some methods to a class hierarchy, we create a parallel hierarchy of &#8220;services&#8221; (which are normal classes):
```java
interface PaintService<T extends Animal> extends OS<T> { 
   void paint(Canvas c); 
}

// Implements OS<Elephant>
class ElephantPaintService implements PaintService<Elephant> { 
   // Here we can store the object, for which the service was invoked
   void setServiced(Elephant e) { ... }
   void paint(Canvas c) { ... }
}

// Implements OS<Ant>
class AntPaintService implements PaintService<Ant> { 
   // Injection works normally
   @Inject AnthillService anthill;

   void setServiced(Ant a) { ... }
   void paint(Canvas c) { ... } 
}
```

`OS` is an interface marking some beans as object services; the class, to which the service corresponds is given as a type parameter.

The `ObjectServiceExtension` will detect all beans that implement the `OS` interface, and register an `OSP` (Object Service Provider) bean which can be later injected to obtain a correct object service given an `Animal`:
```java
@Inject
OSP<Animal, PaintService<Animal>> paintService;

void paint(Animal a, Canvas c) {
   paintService.f(a).paint(c);
}
```

Each invocation of the `f` method will lookup the correct bean, based on the run-time type of the object passed, create a new instance of the found bean and set the object, for which the method was called. All beans created are CDI-managed, so injection etc works normally.
```java
void test(Canvas c) {
   // Will invoke paint(c) in AntPaintService
   paint(new Ant(), c);

   // Will invoke paint(c) in ElephantPaintService
   paint(new Elephant(), c);
}
```

The source code is available on GitHub in the [cdiext project][2]. To use it, just bundle the jar with your application.

Thanks to [Tomek Szymański][3] for discussing the implementation.

So what&#8217;s next? The code could use a couple of improvements, but the biggest next task is to add deploy-time checking if there&#8217;s an object service for each class in a hierarchy (e.g. we have a `PrintService` and an `ElephantPrintService`, but forget to add an `AntPrintService`).

Adam

 [1]: http://en.wikipedia.org/wiki/Multiple_dispatch
 [2]: http://github.com/adamw/cdiext
 [3]: http://szimano.org/
