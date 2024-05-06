---
title: 'MacWire 0.5: Interceptors'
author: Adam Warski
type: post
date: 2013-10-01T13:14:19+00:00
url: /blog/2013/10/macwire-0-5-interceptors/
simplecatch-sidebarlayout:
  - default
dsq_thread_id:
  - 1814387619
wp-syntax-cache-content:
  - |
    a:3:{i:1;s:2247:"
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
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">trait</span> BusinessLogicModule <span style="color: #F78811;">&#123;</span>
      <span style="color: #008000; font-style: italic;">// not intercepted</span>
      lazy <span style="color: #0000ff; font-weight: bold;">val</span> balanceChecker <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> BalanceChecker<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>
    &nbsp;
      <span style="color: #008000; font-style: italic;">// we declare that the usage of these objects is transactional</span>
      lazy <span style="color: #0000ff; font-weight: bold;">val</span> moneyTransferer <span style="color: #000080;">=</span> transactional<span style="color: #F78811;">&#40;</span><span style="color: #0000ff; font-weight: bold;">new</span> MoneyTransferer<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span>
      lazy <span style="color: #0000ff; font-weight: bold;">val</span> creditCard <span style="color: #000080;">=</span> transactional<span style="color: #F78811;">&#40;</span><span style="color: #0000ff; font-weight: bold;">new</span> CreditCard<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span>
    &nbsp;
      <span style="color: #008000; font-style: italic;">// abstract interceptor</span>
      <span style="color: #0000ff; font-weight: bold;">def</span> transactional<span style="color: #000080;">:</span> Interceptor
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">trait BusinessLogicModule {
      // not intercepted
      lazy val balanceChecker = new BalanceChecker()
    
      // we declare that the usage of these objects is transactional
      lazy val moneyTransferer = transactional(new MoneyTransferer())
      lazy val creditCard = transactional(new CreditCard())
    
      // abstract interceptor
      def transactional: Interceptor
    }</p></div>
    ";i:2;s:3629:"
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
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">object</span> MyApplication <span style="color: #0000ff; font-weight: bold;">extends</span> BusinessLogicModule <span style="color: #F78811;">&#123;</span>
      lazy <span style="color: #0000ff; font-weight: bold;">val</span> tm <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> TransactionManager<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>
    &nbsp;
      <span style="color: #008000; font-style: italic;">// Implementing the abstract interceptor</span>
      lazy <span style="color: #0000ff; font-weight: bold;">val</span> transactional <span style="color: #000080;">=</span> ProxyingInterceptor <span style="color: #F78811;">&#123;</span> ctx <span style="color: #000080;">=&gt;</span>
        <span style="color: #008000; font-style: italic;">// This function will be called when a method on the intercepted</span>
        <span style="color: #008000; font-style: italic;">// object is invoked</span>
    &nbsp;
        <span style="color: #0000ff; font-weight: bold;">try</span> <span style="color: #F78811;">&#123;</span>
          <span style="color: #008000; font-style: italic;">// Using objects (dependencies) defined in the application</span>
          tm.<span style="color: #000000;">begin</span><span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>
          <span style="color: #008000; font-style: italic;">// Proceeding with the invocation: calls the original method</span>
          <span style="color: #0000ff; font-weight: bold;">val</span> result <span style="color: #000080;">=</span> ctx.<span style="color: #000000;">proceed</span><span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>
          tm.<span style="color: #000000;">commit</span><span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>
    &nbsp;
          result
        <span style="color: #F78811;">&#125;</span> <span style="color: #0000ff; font-weight: bold;">catch</span> <span style="color: #F78811;">&#123;</span>
          <span style="color: #0000ff; font-weight: bold;">case</span> e<span style="color: #000080;">:</span> Exception <span style="color: #000080;">=&gt;</span> <span style="color: #F78811;">&#123;</span>
            tm.<span style="color: #000000;">rollback</span><span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>
            <span style="color: #0000ff; font-weight: bold;">throw</span> e
          <span style="color: #F78811;">&#125;</span>
        <span style="color: #F78811;">&#125;</span>
      <span style="color: #F78811;">&#125;</span>
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">object MyApplication extends BusinessLogicModule {
      lazy val tm = new TransactionManager()
    
      // Implementing the abstract interceptor
      lazy val transactional = ProxyingInterceptor { ctx =&gt;
        // This function will be called when a method on the intercepted
        // object is invoked
    
        try {
          // Using objects (dependencies) defined in the application
          tm.begin()
          // Proceeding with the invocation: calls the original method
          val result = ctx.proceed()
          tm.commit()
    
          result
        } catch {
          case e: Exception =&gt; {
            tm.rollback()
            throw e
          }
        }
      }
    }</p></div>
    ";i:3;s:2939:"
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
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">object</span> TimingInterceptor <span style="color: #0000ff; font-weight: bold;">extends</span> ProxyingInterceptor <span style="color: #F78811;">&#123;</span>
      <span style="color: #0000ff; font-weight: bold;">def</span> handle<span style="color: #F78811;">&#40;</span>ctx<span style="color: #000080;">:</span> InvocationContext<span style="color: #F78811;">&#41;</span> <span style="color: #000080;">=</span> <span style="color: #F78811;">&#123;</span>
        <span style="color: #0000ff; font-weight: bold;">val</span> classWithMethodName <span style="color: #000080;">=</span> s<span style="color: #6666FF;">&quot;${ctx.target.getClass.getSimpleName}.${ctx.method.getName}&quot;</span>
        <span style="color: #0000ff; font-weight: bold;">val</span> start <span style="color: #000080;">=</span> System.<span style="color: #000000;">currentTimeMillis</span><span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>
        println<span style="color: #F78811;">&#40;</span>s<span style="color: #6666FF;">&quot;Invoking $classWithMethodName...&quot;</span><span style="color: #F78811;">&#41;</span>
        <span style="color: #0000ff; font-weight: bold;">try</span> <span style="color: #F78811;">&#123;</span>
          ctx.<span style="color: #000000;">proceed</span><span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>
        <span style="color: #F78811;">&#125;</span> <span style="color: #0000ff; font-weight: bold;">finally</span> <span style="color: #F78811;">&#123;</span>
          <span style="color: #0000ff; font-weight: bold;">val</span> end <span style="color: #000080;">=</span> System.<span style="color: #000000;">currentTimeMillis</span><span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>
          println<span style="color: #F78811;">&#40;</span>s<span style="color: #6666FF;">&quot;Invocation of $classWithMethodName took: ${end-start}ms&quot;</span><span style="color: #F78811;">&#41;</span>
        <span style="color: #F78811;">&#125;</span>
      <span style="color: #F78811;">&#125;</span>
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">object TimingInterceptor extends ProxyingInterceptor {
      def handle(ctx: InvocationContext) = {
        val classWithMethodName = s&quot;${ctx.target.getClass.getSimpleName}.${ctx.method.getName}&quot;
        val start = System.currentTimeMillis()
        println(s&quot;Invoking $classWithMethodName...&quot;)
        try {
          ctx.proceed()
        } finally {
          val end = System.currentTimeMillis()
          println(s&quot;Invocation of $classWithMethodName took: ${end-start}ms&quot;)
        }
      }
    }</p></div>
    ";}
categories:
  - annotations
  - aop
  - CDI
  - Dependency Injection
  - Framework
  - Java
  - Library
  - Macros
  - MacWire
  - Modularity
  - Scala
  - Uncategorized
  - Weld

---
Interceptors are very useful for implementing cross-cutting concerns. Classic use-cases include security, logging or transaction support. Since version 0.5, [MacWire][1] contains an implementation of interceptors which can be applied to arbitrary object instances in a Scala-friendly way, and which plays nicely with the traits-as-modules approach. No compile-time or load-time bytecode manipulation is required; only javassist is used at run-time to generate a proxy. 

MacWire defines an `Interceptor` trait, which has just one method: `apply`. When applied to an object, it should return an intercepted instance.

Suppose we have a couple of objects, and we want each method in these objects to be surrounded by a transaction. The objects are defined and wired inside a trait (could also be inside a class/object). That&#8217;s of course a perfect fit for an interceptor; we&#8217;ll call the interceptor `transactional`. We will also make it abstract so that we can swap implementations for testing, and keep the interceptor usage **declarative**:

<pre lang="scala" line="1">trait BusinessLogicModule {
  // not intercepted
  lazy val balanceChecker = new BalanceChecker()

  // we declare that the usage of these objects is transactional
  lazy val moneyTransferer = transactional(new MoneyTransferer())
  lazy val creditCard = transactional(new CreditCard())

  // abstract interceptor
  def transactional: Interceptor
}
</pre>

MacWire provides two interceptor implementations:

  * `ProxyingInterceptor` &#8211; proxies the given instance, and returns the proxy. A provided function is called on invocation
  * `NoOpInterceptor` &#8211; useful for testing, when applied returns the instance unchanged

A proxying interceptor can be created in two ways: either by extending the `ProxyingInterceptor` trait, or by passing a function to the `ProxyingInterceptor` object. For example:

<pre lang="scala" line="1">object MyApplication extends BusinessLogicModule {
  lazy val tm = new TransactionManager()

  // Implementing the abstract interceptor
  lazy val transactional = ProxyingInterceptor { ctx =>
    // This function will be called when a method on the intercepted
    // object is invoked

    try {
      // Using objects (dependencies) defined in the application
      tm.begin()
      // Proceeding with the invocation: calls the original method
      val result = ctx.proceed()
      tm.commit()

      result
    } catch {
      case e: Exception => {
        tm.rollback()
        throw e
      }
    }
  }
}
</pre>

The `ctx` instance contains information on the invocation, such as the method being called, the parameters or the target object. Another example of an interceptor, which uses this information, is a `TimingInterceptor`, defined in the trait-extension style:

<pre lang="scala" line="1">object TimingInterceptor extends ProxyingInterceptor {
  def handle(ctx: InvocationContext) = {
    val classWithMethodName = s"${ctx.target.getClass.getSimpleName}.${ctx.method.getName}"
    val start = System.currentTimeMillis()
    println(s"Invoking $classWithMethodName...")
    try {
      ctx.proceed()
    } finally {
      val end = System.currentTimeMillis()
      println(s"Invocation of $classWithMethodName took: ${end-start}ms")
    }
  }
}
</pre>

You can see this interceptor in action in the MacWire+Scalatra example, which also uses scopes and the `wire[]` macro. [Just explore the code on GitHub][2], or run it by executing `sbt examples-scalatra/run` after cloning MacWire and going to <http://localhost:8080>.

Interceptors can be stacked (order of interceptor invocation is simply ordering of declarations &#8211; no XML! :) ), and combined with scopes.

Note that although the code above does not use `wire[]` for instance wiring, interceptors of course work in cooperation with the `wire[]` macro. The interceptors can be also used stand-alone, without even depending on the `macwire-macros` artifact.

The interceptors in MacWire correspond to Java annotation-based interceptors known from [CDI][3] or [Guice][4]. For more general AOP, e.g. if you want to apply an interceptor to all methods matching a given pointcut expression, you should use [AspectJ][5] or an equivalent library.

If you&#8217;d like to try MacWire, just head to the [GitHub project page][1], which contains all the details on installation and usage.

What do you think about such an approach to interceptors?

Adam

 [1]: https://github.com/adamw/macwire
 [2]: https://github.com/adamw/macwire/tree/master/examples/scalatra/src/main/scala/com/softwaremill/macwire/examples/scalatra
 [3]: http://docs.jboss.org/weld/reference/1.0.0/en-US/html/interceptors.html
 [4]: https://code.google.com/p/google-guice/wiki/AOP
 [5]: http://eclipse.org/aspectj/
