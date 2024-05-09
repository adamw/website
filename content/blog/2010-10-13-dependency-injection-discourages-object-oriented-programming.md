---
title: Dependency injection discourages object-oriented programming?
author: Adam Warski
type: blog
date: 2010-10-13T16:05:41+00:00
url: /blog/2010/10/dependency-injection-discourages-object-oriented-programming/
disqus_identifier: 1050961275
wp-syntax-cache-content:
  - |
    a:3:{i:1;s:2343:"
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
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;"><span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000000; font-weight: bold;">class</span> ProductService <span style="color: #009900;">&#123;</span>
       <span style="color: #666666; font-style: italic;">// Here we can use setter injection, constructor injection, etc., doesn't matter</span>
       @Inject <span style="color: #000000; font-weight: bold;">private</span> PriceCalculatorService priceCalculator<span style="color: #339933;">;</span>
       @Inject <span style="color: #000000; font-weight: bold;">private</span> TransportService transport<span style="color: #339933;">;</span>
    &nbsp;
       <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000066; font-weight: bold;">void</span> ship<span style="color: #009900;">&#40;</span>Product product, Customer where<span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
          <span style="color: #000066; font-weight: bold;">int</span> price <span style="color: #339933;">=</span> priceCalculator.<span style="color: #006633;">calculate</span><span style="color: #009900;">&#40;</span>product<span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
          <span style="color: #666666; font-style: italic;">// (...)</span>
          transport.<span style="color: #006633;">send</span><span style="color: #009900;">&#40;</span>address, product<span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
       <span style="color: #009900;">&#125;</span>
    &nbsp;
       <span style="color: #666666; font-style: italic;">// (...)</span>
    <span style="color: #009900;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">public class ProductService {
       // Here we can use setter injection, constructor injection, etc., doesn't matter
       @Inject private PriceCalculatorService priceCalculator;
       @Inject private TransportService transport;
    
       public void ship(Product product, Customer where) {
          int price = priceCalculator.calculate(product);
          // (...)
          transport.send(address, product);
       }
    
       // (...)
    }</p></div>
    ";i:2;s:1740:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    6
    7
    8
    9
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;"><span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000000; font-weight: bold;">class</span> ProductShipper <span style="color: #009900;">&#123;</span>
       <span style="color: #000000; font-weight: bold;">private</span> <span style="color: #000000; font-weight: bold;">final</span> Product product<span style="color: #339933;">;</span>
    &nbsp;
       <span style="color: #000000; font-weight: bold;">public</span> ProductShipper<span style="color: #009900;">&#40;</span>Product product<span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span> <span style="color: #000000; font-weight: bold;">this</span>.<span style="color: #006633;">product</span> <span style="color: #339933;">=</span> product<span style="color: #339933;">;</span> <span style="color: #009900;">&#125;</span>
    &nbsp;
       <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000066; font-weight: bold;">void</span> ship<span style="color: #009900;">&#40;</span>Customer where<span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
          <span style="color: #666666; font-style: italic;">// ???</span>
       <span style="color: #009900;">&#125;</span>
    <span style="color: #009900;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">public class ProductShipper {
       private final Product product;
      
       public ProductShipper(Product product) { this.product = product; }
    
       public void ship(Customer where) {
          // ???
       }
    }</p></div>
    ";i:3;s:4904:"
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
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;"><span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000000; font-weight: bold;">class</span> UserInterface <span style="color: #009900;">&#123;</span>
       <span style="color: #666666; font-style: italic;">// OSP stands for Object Service Provider</span>
       @Inject OSP<span style="color: #339933;">&lt;</span>Product, ProductShipper<span style="color: #339933;">&gt;</span> shipperProvider<span style="color: #339933;">;</span>   
    &nbsp;
       <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000066; font-weight: bold;">void</span> shipClicked<span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
          shipperProvider.<span style="color: #006633;">f</span><span style="color: #009900;">&#40;</span>getProduct<span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span>.<span style="color: #006633;">ship</span><span style="color: #009900;">&#40;</span>getCustomer<span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
       <span style="color: #009900;">&#125;</span>
    <span style="color: #009900;">&#125;</span>
    &nbsp;
    <span style="color: #666666; font-style: italic;">// OS stands for Object Service</span>
    <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000000; font-weight: bold;">class</span> ProductShipper <span style="color: #000000; font-weight: bold;">implements</span> OS<span style="color: #339933;">&lt;</span>Product<span style="color: #339933;">&gt;</span> <span style="color: #009900;">&#123;</span>
       <span style="color: #000000; font-weight: bold;">private</span> Product product<span style="color: #339933;">;</span>
    &nbsp;
       <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000066; font-weight: bold;">void</span> setServiced<span style="color: #009900;">&#40;</span>Product product<span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span> <span style="color: #000000; font-weight: bold;">this</span>.<span style="color: #006633;">product</span> <span style="color: #339933;">=</span> product<span style="color: #339933;">;</span> <span style="color: #009900;">&#125;</span>
    &nbsp;
       <span style="color: #666666; font-style: italic;">// Here we can use setter injection, constructor injection, etc., doesn't matter</span>
       @Inject <span style="color: #000000; font-weight: bold;">private</span> PriceCalculatorService priceCalculator<span style="color: #339933;">;</span>
       @Inject <span style="color: #000000; font-weight: bold;">private</span> TransportService transport<span style="color: #339933;">;</span>
    &nbsp;
       <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000066; font-weight: bold;">void</span> ship<span style="color: #009900;">&#40;</span>Customer where<span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
          <span style="color: #000066; font-weight: bold;">int</span> price <span style="color: #339933;">=</span> priceCalculator.<span style="color: #006633;">calculate</span><span style="color: #009900;">&#40;</span>product<span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
          <span style="color: #666666; font-style: italic;">// (...)</span>
          transport.<span style="color: #006633;">send</span><span style="color: #009900;">&#40;</span>address, product<span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
       <span style="color: #009900;">&#125;</span>
    <span style="color: #009900;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">public class UserInterface {
       // OSP stands for Object Service Provider
       @Inject OSP&lt;Product, ProductShipper&gt; shipperProvider;   
    
       public void shipClicked() {
          shipperProvider.f(getProduct()).ship(getCustomer());
       }
    }
    
    // OS stands for Object Service
    public class ProductShipper implements OS&lt;Product&gt; {
       private Product product;
      
       public void setServiced(Product product) { this.product = product; }
    
       // Here we can use setter injection, constructor injection, etc., doesn't matter
       @Inject private PriceCalculatorService priceCalculator;
       @Inject private TransportService transport;
    
       public void ship(Customer where) {
          int price = priceCalculator.calculate(product);
          // (...)
          transport.send(address, product);
       }
    }</p></div>
    ";}
tags:
  - jee
  - dependency injection
  - clean code
  - java

---
Suppose you have a `Product` entity and that you want to implement a method which sends the product to a customer (let&#8217;s call it `ship`). For that method to work, you need a service for calculating prices and a goods transporting service. To wire the services nicely, the natural choice nowadays is a DI framework (Seam, Guice, Spring, CDI, &#8230;).

Chances are high that to implement the above scenario, you would create a `ProductService` or a `ProductManager` (or if you have more specialized services, a `ProductShipper`) which would look more or less like this:
```java
public class ProductService {
   // Here we can use setter injection, constructor injection, etc., doesn't matter
   @Inject private PriceCalculatorService priceCalculator;
   @Inject private TransportService transport;

   public void ship(Product product, Customer where) {
      int price = priceCalculator.calculate(product);
      // (...)
      transport.send(address, product);
   }

   // (...)
}
```

The main problem with that solution is that the product is always passed as an argument.

Check in your project: if you&#8217;re using DI, and you have an `X` entity, do you have an `XService` or `XManager` with lots of method where `X` is the first argument?

Wouldn&#8217;t it be better if you could create something like this:
```java
public class ProductShipper {
   private final Product product;
  
   public ProductShipper(Product product) { this.product = product; }

   public void ship(Customer where) {
      // ???
   }
}
```

The previous way is more procedural: the service/manager is a set of procedures you can run, where the execution takes a product and a customer as arguments. Here we have a more OO approach, which has many benefits:

  * the product entity is encapsulated in the service
  * you can pass the shipper object around
  * you control where and how the shipper object is created
  * you can create multiple copies of shipper objects, for multiple products
  * etc

I&#8217;m not saying that achieving the above is not possible with a DI framework; only that DI **encourages** the `ProductService` approach: it&#8217;s just easier to code it procedurally, instead of e.g. creating a `ProductShipper` factory, passing all the needed dependencies to it and so on.

An obvious problem with the new approach is that if you create a `ProductShipper` object yourself, you won&#8217;t have dependencies injected. For DI to work, the object must be managed by the container.

Hence we come to some **problems with DI frameworks**:

  * for DI to work, the **objects must be managed** by the container. Creating an object using some data not available upfront and having dependencies injected is not possible
  * there can only be **one (managed) instance of a class** available at a single &#8220;execution&#8221; (for web applications, this will be per one request)
  * managed objects are either **stateless, or holders of &#8220;anemic&#8221; data objects**; extra steps are required to create &#8220;rich&#8221; objects (if you have two `Product` entities, you won&#8217;t be able to create two `ProductShipper` objects with that products encapsulated, but you can create a `ProductsToShipHolder` managed object into which you can add the products to ship)

How to solve these problems?

I think a good starting point is something that I call [object services][1]. Using them, you can inject a provider, with which you can obtain a service, given an entity (or any other object). The good side is that injection into the obtained services works normally. Following our example, the code could look like this:
```java
public class UserInterface {
   // OSP stands for Object Service Provider
   @Inject OSP<Product, ProductShipper> shipperProvider;   

   public void shipClicked() {
      shipperProvider.f(getProduct()).ship(getCustomer());
   }
}

// OS stands for Object Service
public class ProductShipper implements OS<Product> {
   private Product product;
  
   public void setServiced(Product product) { this.product = product; }

   // Here we can use setter injection, constructor injection, etc., doesn't matter
   @Inject private PriceCalculatorService priceCalculator;
   @Inject private TransportService transport;

   public void ship(Customer where) {
      int price = priceCalculator.calculate(product);
      // (...)
      transport.send(address, product);
   }
}
```

The good thing here is that `shipperProvider.f(getProduct())` is a regular object, which has the product encapsulated. It can be freely passed around, and you can create multiple shippers for different products.

(Another good thing here is that object services support polymorphism, so you can get different services injected for different objects!)

However this implementation still has some drawbacks; for example for injection to work the objects still have to be obtained using the provider, so that they are managed by the container. In reality most objects that are created are not managed by the container. How to obtain dependencies there? Normally they are passed e.g. in constructors, but then if suddenly an object deep down the hierarchy needs a dependency we are in trouble, and end up adding a new constructor parameter all the way up till we get to a managed object.

So stay tuned for more &#8230; :)

Adam

 [1]: http://www.warski.org/blog/?p=226
