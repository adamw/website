---
title: 'MacWire 0.2: Scopes are simple!'
author: Adam Warski
type: post
date: 2013-04-25T18:14:51+00:00
url: /blog/2013/04/macwire-0-2-scopes-are-simple/
simplecatch-sidebarlayout:
  - default
dsq_thread_id:
  - 1235516220
wp-syntax-cache-content:
  - |
    a:3:{i:1;s:827:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">trait</span> MyModule <span style="color: #F78811;">&#123;</span>
       ...
       <span style="color: #000000;">lazy</span> <span style="color: #0000ff; font-weight: bold;">val</span> theUserFinder<span style="color: #000080;">:</span> UserFinder <span style="color: #000080;">=</span> wire<span style="color: #F78811;">&#91;</span>UserFinder<span style="color: #F78811;">&#93;</span>
       ...
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">trait MyModule {
       ...
       lazy val theUserFinder: UserFinder = wire[UserFinder]
       ...
    }</p></div>
    ";i:2;s:1015:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">trait</span> WebModule <span style="color: #F78811;">&#123;</span>
       lazy <span style="color: #0000ff; font-weight: bold;">val</span> loggedInUser<span style="color: #000080;">:</span> LoggedInUser <span style="color: #000080;">=</span> session<span style="color: #F78811;">&#40;</span>wire<span style="color: #F78811;">&#91;</span>LoggedInUser<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#41;</span>
    &nbsp;
       <span style="color: #0000ff; font-weight: bold;">def</span> session<span style="color: #000080;">:</span> Scope
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">trait WebModule {
       lazy val loggedInUser: LoggedInUser = session(wire[LoggedInUser])
    
       def session: Scope
    }</p></div>
    ";i:3;s:4388:"
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
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">class</span> ScopeFilter<span style="color: #F78811;">&#40;</span>sessionScope<span style="color: #000080;">:</span> ThreadLocalScope<span style="color: #F78811;">&#41;</span> <span style="color: #0000ff; font-weight: bold;">extends</span> Filter <span style="color: #F78811;">&#123;</span>
      <span style="color: #0000ff; font-weight: bold;">def</span> init<span style="color: #F78811;">&#40;</span>filterConfig<span style="color: #000080;">:</span> FilterConfig<span style="color: #F78811;">&#41;</span> <span style="color: #F78811;">&#123;</span><span style="color: #F78811;">&#125;</span>
    &nbsp;
      <span style="color: #0000ff; font-weight: bold;">def</span> doFilter<span style="color: #F78811;">&#40;</span>request<span style="color: #000080;">:</span> ServletRequest, response<span style="color: #000080;">:</span> ServletResponse, chain<span style="color: #000080;">:</span> FilterChain<span style="color: #F78811;">&#41;</span> <span style="color: #F78811;">&#123;</span>
        <span style="color: #0000ff; font-weight: bold;">val</span> httpRequest <span style="color: #000080;">=</span> request.<span style="color: #000000;">asInstanceOf</span><span style="color: #F78811;">&#91;</span>HttpServletRequest<span style="color: #F78811;">&#93;</span>
    &nbsp;
        <span style="color: #0000ff; font-weight: bold;">val</span> sessionScopeStorage <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> ScopeStorage <span style="color: #F78811;">&#123;</span>
          <span style="color: #0000ff; font-weight: bold;">def</span> get<span style="color: #F78811;">&#40;</span>key<span style="color: #000080;">:</span> String<span style="color: #F78811;">&#41;</span> <span style="color: #000080;">=</span> Option<span style="color: #F78811;">&#40;</span>httpRequest.<span style="color: #000000;">getSession</span>.<span style="color: #000000;">getAttribute</span><span style="color: #F78811;">&#40;</span>key<span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span>
          <span style="color: #0000ff; font-weight: bold;">def</span> set<span style="color: #F78811;">&#40;</span>key<span style="color: #000080;">:</span> String, value<span style="color: #000080;">:</span> Any<span style="color: #F78811;">&#41;</span> <span style="color: #F78811;">&#123;</span>
            httpRequest.<span style="color: #000000;">getSession</span>.<span style="color: #000000;">setAttribute</span><span style="color: #F78811;">&#40;</span>key, value<span style="color: #F78811;">&#41;</span>
          <span style="color: #F78811;">&#125;</span>
        <span style="color: #F78811;">&#125;</span>
    &nbsp;
        sessionScope.<span style="color: #000000;">withStorage</span><span style="color: #F78811;">&#40;</span>sessionScopeStorage<span style="color: #F78811;">&#41;</span> <span style="color: #F78811;">&#123;</span>
          chain.<span style="color: #000000;">doFilter</span><span style="color: #F78811;">&#40;</span>request, response<span style="color: #F78811;">&#41;</span>
        <span style="color: #F78811;">&#125;</span>
      <span style="color: #F78811;">&#125;</span>
    &nbsp;
      <span style="color: #0000ff; font-weight: bold;">def</span> destroy<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span> <span style="color: #F78811;">&#123;</span><span style="color: #F78811;">&#125;</span>
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">class ScopeFilter(sessionScope: ThreadLocalScope) extends Filter {
      def init(filterConfig: FilterConfig) {}
    
      def doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val httpRequest = request.asInstanceOf[HttpServletRequest]
    
        val sessionScopeStorage = new ScopeStorage {
          def get(key: String) = Option(httpRequest.getSession.getAttribute(key))
          def set(key: String, value: Any) {
            httpRequest.getSession.setAttribute(key, value)
          }
        }
    
        sessionScope.withStorage(sessionScopeStorage) {
          chain.doFilter(request, response)
        }
      }
    
      def destroy() {}
    }</p></div>
    ";}
tags:
  - dependency injection
  - metaprogramming
  - macwire
  - scala

---
_[MacWire][1] generates new instance creation code of given classes, using values in the enclosing type for constructor parameters, with the help of Scala Macros. DI container replacement._

Version 0.2 has just landed!

### First things first &#8230;

First, some bad news. Due to limitations of the current macros implementation in Scala (for more details see [this discussion][2]) in order to avoid occasional compilation errors it is necessary to add type ascriptions to the dependencies. This is a way of helping the type-checker that is invoked by the macro to figure out the types of the values which can be wired.

In practice it is good to always write the type ascription. For example:

<pre lang="scala" line="1">trait MyModule {
   ...
   lazy val theUserFinder: UserFinder = wire[UserFinder]
   ...
}
</pre>

This is a major inconvenience, but hopefully will get resolved some day. See the &#8220;Limitations&#8221; section in the [README][1] for more details.

**Update 28/04/2013** As noted in the comments below, the recommendation for adding type ascriptions may be in fact relaxed by adding special-handling for declarations that are simple `wire[X]` expressions. So stay tuned for future releases for improvements :)

Also, it is worth noting that the type ascription may be a subtype of the wired type. This can be useful if you want to expose e.g. a trait that the wired class extends, instead of the full implementation.

### &#8230; to the point

Now, to the good news. Especially when developing web applications, it is very useful to scope some of the dependencies, so that there&#8217;s a new instance for each **request** (but for each dependency usage, it is the same instance in one request), or that there&#8217;s a single instance per **http session**.

So far MacWire had two &#8220;built-in&#8221; scopes: **singleton** (declare the dependency as `lazy val`) and **dependent** (`def`). Now it is possible to create custom scopes, by implementing the `Scope` trait.

Scopes often seem &#8220;magical&#8221;; in traditional frameworks it is usually necessary to add an annotation to the class and then the dependency is automagically turned into a scoped one by the container. 

In fact implementing a scoped bean is quite simple. We need two things. Firstly, instead of using instances directly, we need to use a proxy which delegates method calls to the &#8220;current instance&#8221;. What &#8220;current instance&#8221; means is scope-specific.

Secondly, we need a way to read a value from the scope, and store a value in the scope, if there&#8217;s no value yet. The two methods in the `Scope` trait correspond directly to the two cases outlined above.

Using a scope is quite straightforward; the scope of a dependency is declared in the wiring code, in the module. For example:

<pre lang="scala" line="1">trait WebModule {
   lazy val loggedInUser: LoggedInUser = session(wire[LoggedInUser])

   def session: Scope
}
</pre>

Note that the scope here is abstract (only the name suggests that it is a session scope). This has a couple of advantages. For testing, we can substitute a `NoOpScope`. Also, we can move the framework-integration code to the integration layer, or even provide a couple of implementations depending on the framework/container used.

MacWire comes with a `Scope` implementation targeted at synchronous web frameworks, `ThreadLocalScope`. The scope needs to be associated and disassociated with a scope storage. To implement a:

  * _request scope_, we need a new empty storage for every request (`associateWithEmptyStorage` method)
  * _session scope_, the storage (a `Map`) should be stored in the `HttpSession` (`associate(Map)` method)

This association can be done, for example, in a servlet filter:

<pre lang="scala" line="1">class ScopeFilter(sessionScope: ThreadLocalScope) extends Filter {
  def init(filterConfig: FilterConfig) {}

  def doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
    val httpRequest = request.asInstanceOf[HttpServletRequest]

    val sessionScopeStorage = new ScopeStorage {
      def get(key: String) = Option(httpRequest.getSession.getAttribute(key))
      def set(key: String, value: Any) {
        httpRequest.getSession.setAttribute(key, value)
      }
    }

    sessionScope.withStorage(sessionScopeStorage) {
      chain.doFilter(request, response)
    }
  }

  def destroy() {}
}
</pre>

Note also that it is trivial to use a scoped value e.g. in an asynchronous process, where no web request is available: simply associate the scope with any storage, can be a temporary map. In traditional web frameworks this usually requires some amount of hacking.

To sum up, to use a scope in MacWire you need to:

  * declare a `Scope` in your module, use if for scoped dependencies
  * provide an implementation of the scope, e.g. `ThreadLocalScope`
  * associate the scope with a storage, e.g. in a servlet filter

You can see how this works in practice by browsing & running a [Scalatra][3]+MacWire example application. To run, clone MacWire and use this command: `sbt examples-scalatra/run`. To browse the code, simply head to [GitHub][4].

[<img loading="lazy" decoding="async" src="http://www.warski.org/blog/wp-content/uploads/2013/04/2013-04-25_1649-300x105.png" alt="2013-04-25_1649" width="300" height="105" class="aligncenter size-medium wp-image-972" srcset="https://www.warski.org/blog/wp-content/uploads/2013/04/2013-04-25_1649-300x105.png 300w, https://www.warski.org/blog/wp-content/uploads/2013/04/2013-04-25_1649-1024x359.png 1024w, https://www.warski.org/blog/wp-content/uploads/2013/04/2013-04-25_1649-210x73.png 210w, https://www.warski.org/blog/wp-content/uploads/2013/04/2013-04-25_1649.png 1760w" sizes="(max-width: 300px) 100vw, 300px" />][5]

The example contains a request-scoped dependency (`SubmittedData`), session-scoped dependency (`LoggedInUser`) and three main services (`Service1, Service2, Service3`). Each class has some dependencies (declared in the constructor), and is wired using `wire[]`. The code is organised in two modules: `logic` and `servlet`. The services have methods to return a string-status, containing the current request and session-scoped values, so that it is possible to verify that indeed the values are shared properly. The servlet module contains a servlet which shows the service status, plus the scopes implementations.

Note that the `scopes` subproject is completely independent, and can be used stand-alone with manual wiring or any other library/framework. Its only dependency is javassist.

Have fun!  
Adam

 [1]: https://github.com/adamw/macwire
 [2]: https://groups.google.com/forum/?fromgroups=#!topic/scala-user/k_2KCvO5g04
 [3]: http://www.scalatra.org/
 [4]: https://github.com/adamw/macwire/tree/master/examples/scalatra/src/main/scala/com/softwaremill/macwire/examples/scalatra
 [5]: http://www.warski.org/blog/wp-content/uploads/2013/04/2013-04-25_1649.png
