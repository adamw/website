---
title: Improving autofactories/assisted inject
author: Adam Warski
type: post
date: 2010-12-07T14:22:07+00:00
url: /blog/2010/12/improving-autofactoriesassisted-inject/
dsq_thread_id:
  - 1051935351
wp-syntax-cache-content:
  - |
    a:4:{i:1;s:1195:"
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
    ";i:2;s:2303:"
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
    <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000000; font-weight: bold;">class</span> ProductShipperImpl <span style="color: #000000; font-weight: bold;">implements</span> ProductShipper <span style="color: #009900;">&#123;</span>
         @Inject
         <span style="color: #000000; font-weight: bold;">private</span> PriceCalculatorService priceCalculator<span style="color: #339933;">;</span>
    &nbsp;
         @Inject
         <span style="color: #000000; font-weight: bold;">private</span> TransportService transport<span style="color: #339933;">;</span>
    &nbsp;
         <span style="color: #000000; font-weight: bold;">private</span> <span style="color: #000000; font-weight: bold;">final</span> Product product<span style="color: #339933;">;</span>
    &nbsp;
         <span style="color: #000000; font-weight: bold;">public</span> ProductShipperImpl<span style="color: #009900;">&#40;</span>Product product<span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
              <span style="color: #000000; font-weight: bold;">this</span>.<span style="color: #006633;">product</span> <span style="color: #339933;">=</span> product<span style="color: #339933;">;</span>
         <span style="color: #009900;">&#125;</span>
    &nbsp;
         <span style="color: #666666; font-style: italic;">// implement shipTo(User)</span>
    <span style="color: #009900;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">@CreatedWith(ProductShipper.Factory.class)
    public class ProductShipperImpl implements ProductShipper {
         @Inject
         private PriceCalculatorService priceCalculator;
    
         @Inject
         private TransportService transport;
    
         private final Product product;
    
         public ProductShipperImpl(Product product) {
              this.product = product;
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
    ";i:4;s:2494:"
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
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;"><span style="color: #000000; font-weight: bold;">import</span> <span style="color: #006699;">static</span> pl.<span style="color: #006633;">softwaremill</span>.<span style="color: #006633;">common</span>.<span style="color: #006633;">util</span>.<span style="color: #006633;">CDIInjector</span>.<span style="color: #339933;">*;</span>
    &nbsp;
    <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000000; font-weight: bold;">class</span> ProductShipperTest <span style="color: #009900;">&#123;</span>
         @Test
         <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000066; font-weight: bold;">void</span> testShipping<span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
              Product product <span style="color: #339933;">=</span> ...<span style="color: #339933;">;</span>
              ProductShipper shipper <span style="color: #339933;">=</span> <span style="color: #000000; font-weight: bold;">new</span> ProductShipperImpl<span style="color: #009900;">&#40;</span>product<span style="color: #009900;">&#41;</span>
    &nbsp;
              <span style="color: #666666; font-style: italic;">// Here's the important part: &quot;dependency injection&quot;</span>
              into<span style="color: #009900;">&#40;</span>shipper<span style="color: #009900;">&#41;</span>.<span style="color: #006633;">inject</span><span style="color: #009900;">&#40;</span>mockPriceCalculator, mockService<span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
    &nbsp;
              <span style="color: #666666; font-style: italic;">// Test goes on ...</span>
         <span style="color: #009900;">&#125;</span>
    <span style="color: #009900;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">import static pl.softwaremill.common.util.CDIInjector.*;
    
    public class ProductShipperTest {
         @Test
         public void testShipping() {
              Product product = ...;
              ProductShipper shipper = new ProductShipperImpl(product)
    
              // Here's the important part: &quot;dependency injection&quot;
              into(shipper).inject(mockPriceCalculator, mockService);
    
              // Test goes on ...
         }
    }</p></div>
    ";}
tags:
  - jee
  - dependency injection
  - java

---
In my two previous posts I wrote about some [problems with DI][1] and [a solution][2] to part of those problems: assisted inject (as known in [Guice][3])/autofactories (my implementation for CDI). Some problems remained however; for example in the bean constructor, the dependencies (or the environment) are mixed with the data obtained from the factory. That is also why the `@FactoryParameter` annotation is required.

This shortcoming can be quite easily overcome, by using **field injection** for dependencies, and constructors for data from the factory. How does it work? And what about testing? Read on :).

### The problem

The main goal is to be able to define beans, in which **dependency injection works as usual** and which can be **constructed on-demand with user-supplied data**. Something more object-oriented than creating stateless beans where the user-supplied data is passed in method parameters, and something with less boilerplate than implementing factories. 

Just to remind, as an example throughout the posts I am using a `ProductShipper` bean, which needs some outside dependencies (&#8220;services&#8221;, for example a `PriceCalculatorService`), and must be provided with a `Product` object.

### The solution

The base interface remains the same:

<pre lang="java" line="1" escaped="true">public interface ProductShipper {
     void ship(User target);

     interface Factory {
          ProductShipper create(Product product);
     }
}
</pre>

Again just to remind, the biggest advantage of the nested factory interface is that it is **clear what is needed** to create a bean (here the shipper), and **we spare some typing** as we don&#8217;t need to write a long class name for the factory.

Now for the implementation. Before both outside dependencies and factory parameters were passed in the constructor. Now, we&#8217;ll use field injection, making a clear separation:

<pre lang="java" line="1" escaped="true">@CreatedWith(ProductShipper.Factory.class)
public class ProductShipperImpl implements ProductShipper {
     @Inject
     private PriceCalculatorService priceCalculator;

     @Inject
     private TransportService transport;

     private final Product product;

     public ProductShipperImpl(Product product) {
          this.product = product;
     }

     // implement shipTo(User)
}
</pre>

Looks quite simple. The only thing we needed to do is to specify the factory for the bean (`@CreatedWith`) and provide a constructor matching the parameters of the factory. Hence, one problem remains: there&#8217;s no compile-time checking (only deployment-time), if the arguments lists match. So there&#8217;s still room for improvement! :)

### Usage

To use the autofactory, you simply have to inject the factory bean. The important thing is that you don&#8217;t have to implement the factory bean by hand, it is auto-generated. For example:

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

### Testing

Many would argue that using field injection instead of constructor injection makes the bean much harder to test. But we can improve that quite easily, thanks to the `CDIInjector` helper class, from `<a href="https://github.com/softwaremill/softwaremill-common/tree/master/softwaremill-util">softwaremill-util</a>`. Here&#8217;s how we can test our bean:

<pre lang="java" line="1" escaped="true">import static pl.softwaremill.common.util.CDIInjector.*;

public class ProductShipperTest {
     @Test
     public void testShipping() {
          Product product = ...;
          ProductShipper shipper = new ProductShipperImpl(product)

          // Here's the important part: "dependency injection"
          into(shipper).inject(mockPriceCalculator, mockService);

          // Test goes on ...
     }
}
</pre>

So instead of setting the fields explicitly via reflection (where we would have to write the field names), we just need to provide the target of injection (`<strong>into(shipper)</strong>`), and the objects that we want to be injected (`<strong>.inject(...)</strong>`). We can also inject objects with qualifiers.

As always the code is available on github, as part of the [softwaremill-common][4] project (`<a href="https://github.com/softwaremill/softwaremill-common/tree/master/softwaremill-cdi">softwaremill-cdi</a>` release 9 and `<a href="https://github.com/softwaremill/softwaremill-common/tree/master/softwaremill-util">softwaremill-util</a>` release 13). The autofactories implementation is a portable extension (supports both styles: constructor and field/setter injection for dependencies), so all you need to do is to include the jar in your deployment.

Adam

 [1]: http://www.warski.org/blog/?p=272
 [2]: http://www.warski.org/blog/?p=289
 [3]: http://code.google.com/p/google-guice/wiki/AssistedInject
 [4]: http://github.com/softwaremill/softwaremill-common
