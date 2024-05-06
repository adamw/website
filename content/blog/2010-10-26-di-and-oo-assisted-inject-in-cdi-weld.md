---
title: 'DI and OO: Assisted Inject in CDI / Weld'
author: Adam Warski
type: post
date: 2010-10-26T15:22:36+00:00
url: /blog/2010/10/di-and-oo-assisted-inject-in-cdi-weld/
dsq_thread_id:
  - 1051935270
wp-syntax-cache-content:
  - |
    a:3:{i:1;s:1195:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    6
    7
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;"><span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000000; font-weight: bold;">interface</span> ProductShipper <span style="color: #009900;">&#123;</span>
         <span style="color: #000066; font-weight: bold;">void</span> ship<span style="color: #009900;">&#40;</span>User target<span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
    &nbsp;
         <span style="color: #000000; font-weight: bold;">interface</span> Factory <span style="color: #009900;">&#123;</span>
              ProductShipper create<span style="color: #009900;">&#40;</span>Product product<span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
         <span style="color: #009900;">&#125;</span>
    <span style="color: #009900;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">public interface ProductShipper {
         void ship(User target);
    
         interface Factory {
              ProductShipper create(Product product);
         }
    }</p></div>
    ";i:2;s:2439:"
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
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;">@CreatedWith<span style="color: #009900;">&#40;</span>ProductShipper.<span style="color: #006633;">Factory</span>.<span style="color: #000000; font-weight: bold;">class</span><span style="color: #009900;">&#41;</span>
    <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000000; font-weight: bold;">class</span> ProductShipperImpl implement ProductShipper <span style="color: #009900;">&#123;</span>
         <span style="color: #000000; font-weight: bold;">private</span> <span style="color: #000000; font-weight: bold;">final</span> PriceCalculatorService priceCalculator<span style="color: #339933;">;</span>
         <span style="color: #000000; font-weight: bold;">private</span> <span style="color: #000000; font-weight: bold;">final</span> TransportService transport<span style="color: #339933;">;</span>
    &nbsp;
         <span style="color: #000000; font-weight: bold;">private</span> <span style="color: #000000; font-weight: bold;">final</span> Product product<span style="color: #339933;">;</span>
    &nbsp;
         @Inject
         <span style="color: #000000; font-weight: bold;">public</span> ProductShipperImpl<span style="color: #009900;">&#40;</span>@FactoryParameter Product product, 
              PriceCalculatorService priceCalculator, 
              TransportService transport<span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
              <span style="color: #666666; font-style: italic;">// assign fields</span>
         <span style="color: #009900;">&#125;</span>
    &nbsp;
         <span style="color: #666666; font-style: italic;">// implement shipTo(User)</span>
    <span style="color: #009900;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">@CreatedWith(ProductShipper.Factory.class)
    public class ProductShipperImpl implement ProductShipper {
         private final PriceCalculatorService priceCalculator;
         private final TransportService transport;
    
         private final Product product;
    
         @Inject
         public ProductShipperImpl(@FactoryParameter Product product, 
              PriceCalculatorService priceCalculator, 
              TransportService transport) {
              // assign fields
         }
    
         // implement shipTo(User)
    }</p></div>
    ";i:3;s:2182:"
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
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;"><span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000000; font-weight: bold;">class</span> Test <span style="color: #009900;">&#123;</span>
         @Inject
         <span style="color: #000000; font-weight: bold;">private</span> ProductShipper.<span style="color: #006633;">Factory</span> productShipperFactory<span style="color: #339933;">;</span>
    &nbsp;
         <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000066; font-weight: bold;">void</span> test<span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
              ProductShipper shipper <span style="color: #339933;">=</span> productShipperFactory
                   .<span style="color: #006633;">create</span><span style="color: #009900;">&#40;</span><span style="color: #000000; font-weight: bold;">new</span> Product<span style="color: #009900;">&#40;</span><span style="color: #0000ff;">&quot;butter&quot;</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
    &nbsp;
              shipper.<span style="color: #006633;">shipTo</span><span style="color: #009900;">&#40;</span><span style="color: #000000; font-weight: bold;">new</span> User<span style="color: #009900;">&#40;</span><span style="color: #0000ff;">&quot;me&quot;</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
         <span style="color: #009900;">&#125;</span>
    <span style="color: #009900;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">public class Test {
         @Inject
         private ProductShipper.Factory productShipperFactory;
    
         public void test() {
              ProductShipper shipper = productShipperFactory
                   .create(new Product(&quot;butter&quot;));
    
              shipper.shipTo(new User(&quot;me&quot;));
         }
    }</p></div>
    ";}
categories:
  - CDI
  - Dependency Injection
  - Java
  - JBoss
  - Uncategorized
  - Weld

---
My [last][1] post sparked quite a lot of interest &#8211; thanks for all the comments both on the blog and on [dzone][2]! Some of them rightly pointed out that the original post contains a mistake [(*)][3] (but luckily it didn&#8217;t impact the main point). Many other suggested using the factory pattern as a solution for the problem described. However, as I wrote in the post, this requires one to implement a factory, which is impractical and leads to quite a lot of boilerplate code. 

[Maciej Biłas][4] pointed me however to a nice solution, that is implemented in Guice: [Assisted Inject][5]. The idea is that an implementation of the factory can be automatically generated by the container, basing on the factory&#8217;s interface and leveraging some additional metadata from annotations. As I&#8217;m using [CDI/Weld][6] for some of my projects, I decided to write a portable extension supporting this.

As always an example would be best to illustrate what it&#8217;s about; I called my implementation `autofactories`. 

Suppose that, as in my last post, we have a `ProductShipper` interface. To create a shipper, we need a product, so we&#8217;ll create a factory which will have one method with one argument. We can write this as follows:

<pre lang="java" line="1" escaped="true">public interface ProductShipper {
     void ship(User target);

     interface Factory {
          ProductShipper create(Product product);
     }
}
</pre>

By making the `Factory` a nested interface, we gain two things:

  * it is **clear what is needed** to create a shipper just by looking at the main interface (no need to look at a separate factory class)
  * **we spare some typing** as we don&#8217;t need to write another long class name (`ProductShipperFactory`). However, usages of the factory identify it exactly (`ProductShipper.Factory` &#8211; notice the dot)

Now the implementation; let&#8217;s say it requires two services, which we want to inject, apart from the `Product` object, which is obtained through the factory method. Using autofactories, we can write it like this:

<pre lang="java" line="1" escaped="true">@CreatedWith(ProductShipper.Factory.class)
public class ProductShipperImpl implement ProductShipper {
     private final PriceCalculatorService priceCalculator;
     private final TransportService transport;

     private final Product product;

     @Inject
     public ProductShipperImpl(@FactoryParameter Product product, 
          PriceCalculatorService priceCalculator, 
          TransportService transport) {
          // assign fields
     }

     // implement shipTo(User)
}
</pre>

Now you can just inject the factory interface and call the `create` method on it, for example:

<pre lang="java" line="1" escaped="true">public class Test {
     @Inject
     private ProductShipper.Factory productShipperFactory;

     public void test() {
          ProductShipper shipper = productShipperFactory
               .create(new Product("butter"));

          shipper.shipTo(new User("me"));
     }
}
</pre>

Note that we never wrote the actual implementation of `ProductShipper.Factory`. There are three annotations which are important here:

  * `<strong>@CreatedWith</strong>` specifies the factory interface, for which an implementation will be created. The interface should have only one method (later referred to as the factory method)
  * `<strong>@FactoryParameter</strong>` specifies that the annotated constructor parameter corresponds to a parameter of the same class in the factory method
  * `<strong>@Inject</strong>` specifies that other parameters should be injected from the context

Does assisted inject/autofactories solve the problems from my last blog? Only partly; there two big drawbacks of this solution:

  * The dependencies of a bean on the environment/context (injected from the container) and on data (passed from the factory method) are mixed, while they are **different kinds of dependencies**
  * **No type safety**: only at deployment time it is possible to verify that the parameters of the factory method match the constructor

The code is available on github, in the [softwaremill-common][7] project (`softwaremill-cdi` module). It is a portable extension, so you can use autofactories simply by including the jar in the classpath. 

The jar is deployed to our public Maven repository, see the project&#8217;s README for the artifact data. 

There is also a [test using Arquillian][8] which can be a good usage example.

**Next step**: combining [object services][9] (where the emphasis is on polymorphism and extension methods) with assisted inject/autofactories.

Adam

<a name="star">(*)</a> The statement that you can only create one instance of an object is wrong. Injecting an `Instance<MyBean>` in CDI or a bean in the `prototype scope` in Spring, you can create on-demand instances. However all of them are constructed in the same way, using only dependencies from the container, so this doesn&#8217;t solve the original problem.

 [1]: http://www.warski.org/blog/?p=272
 [2]: http://dzone.com/links/dependency_injection_discourages_objectoriented_p.html
 [3]: #star
 [4]: http://maciej.inszy.org/
 [5]: http://code.google.com/p/google-guice/wiki/AssistedInject
 [6]: http://seamframework.org/Weld
 [7]: http://github.com/softwaremill/softwaremill-common
 [8]: http://github.com/softwaremill/softwaremill-common/tree/master/softwaremill-cdi/src/test/java/pl/softwaremill/common/cdi/autofactory/
 [9]: http://www.warski.org/blog/?p=226
