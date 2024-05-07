---
title: 'Dependency Injection in Scala: Extending the Cake Pattern'
author: Adam Warski
type: post
date: 2010-12-14T09:38:42+00:00
url: /blog/2010/12/di-in-scala-cake-pattern/
simplecatch-sidebarlayout:
  - default
dsq_thread_id:
  - 1051549739
wp-syntax-cache-content:
  - |
    a:7:{i:1;s:615:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">sealed</span> <span style="color: #0000ff; font-weight: bold;">case</span> <span style="color: #0000ff; font-weight: bold;">class</span> User<span style="color: #F78811;">&#40;</span>username<span style="color: #000080;">:</span> String<span style="color: #F78811;">&#41;</span></pre></td></tr></table><p class="theCode" style="display:none;">sealed case class User(username: String)</p></div>
    ";i:2;s:1504:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    6
    7
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">trait</span> UserRepositoryComponent <span style="color: #F78811;">&#123;</span> <span style="color: #008000; font-style: italic;">// For expressing dependencies</span>
       <span style="color: #0000ff; font-weight: bold;">def</span> userRepository<span style="color: #000080;">:</span> UserRepository <span style="color: #008000; font-style: italic;">// Way to obtain the dependency</span>
    &nbsp;
       <span style="color: #0000ff; font-weight: bold;">trait</span> UserRepository <span style="color: #F78811;">&#123;</span> <span style="color: #008000; font-style: italic;">// Interface exposed to the user</span>
          <span style="color: #0000ff; font-weight: bold;">def</span> find<span style="color: #F78811;">&#40;</span>username<span style="color: #000080;">:</span> String<span style="color: #F78811;">&#41;</span><span style="color: #000080;">:</span> User
       <span style="color: #F78811;">&#125;</span>
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">trait UserRepositoryComponent { // For expressing dependencies
       def userRepository: UserRepository // Way to obtain the dependency
    
       trait UserRepository { // Interface exposed to the user
          def find(username: String): User
       }
    }</p></div>
    ";i:3;s:2103:"
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
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">trait</span> UserRepositoryComponentHibernateImpl
                    <span style="color: #0000ff; font-weight: bold;">extends</span> UserRepositoryComponent <span style="color: #F78811;">&#123;</span>
       <span style="color: #0000ff; font-weight: bold;">def</span> userRepository <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> UserRepositoryImpl 
    &nbsp;
       <span style="color: #0000ff; font-weight: bold;">class</span> UserRepositoryImpl <span style="color: #0000ff; font-weight: bold;">extends</span> UserRepository <span style="color: #F78811;">&#123;</span>
          <span style="color: #0000ff; font-weight: bold;">def</span> find<span style="color: #F78811;">&#40;</span>username<span style="color: #000080;">:</span> String<span style="color: #F78811;">&#41;</span><span style="color: #000080;">:</span> User <span style="color: #000080;">=</span> <span style="color: #F78811;">&#123;</span>
             println<span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;Find with Hibernate: &quot;</span> + username<span style="color: #F78811;">&#41;</span>
             <span style="color: #0000ff; font-weight: bold;">new</span> User<span style="color: #F78811;">&#40;</span>username<span style="color: #F78811;">&#41;</span>
          <span style="color: #F78811;">&#125;</span>
       <span style="color: #F78811;">&#125;</span>
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">trait UserRepositoryComponentHibernateImpl
                    extends UserRepositoryComponent {
       def userRepository = new UserRepositoryImpl 
    
       class UserRepositoryImpl extends UserRepository {
          def find(username: String): User = {
             println(&quot;Find with Hibernate: &quot; + username)
             new User(username)
          }
       }
    }</p></div>
    ";i:4;s:3758:"
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
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #008000; font-style: italic;">// Component definition, as before</span>
    <span style="color: #0000ff; font-weight: bold;">trait</span> UserAuthorizationComponent <span style="color: #F78811;">&#123;</span>
       <span style="color: #0000ff; font-weight: bold;">def</span> userAuthorization<span style="color: #000080;">:</span> UserAuthorization
    &nbsp;
       <span style="color: #0000ff; font-weight: bold;">trait</span> UserAuthorization <span style="color: #F78811;">&#123;</span>
          <span style="color: #0000ff; font-weight: bold;">def</span> authorize<span style="color: #F78811;">&#40;</span>user<span style="color: #000080;">:</span> User<span style="color: #F78811;">&#41;</span>
       <span style="color: #F78811;">&#125;</span>
    <span style="color: #F78811;">&#125;</span>
    &nbsp;
    <span style="color: #008000; font-style: italic;">// Component implementation</span>
    <span style="color: #0000ff; font-weight: bold;">trait</span> UserAuthorizationComponentImpl 
                    <span style="color: #0000ff; font-weight: bold;">extends</span> UserAuthorizationComponent <span style="color: #F78811;">&#123;</span>
       <span style="color: #008000; font-style: italic;">// Dependencies</span>
       <span style="color: #0000ff; font-weight: bold;">this</span><span style="color: #000080;">:</span> UserRepositoryComponent <span style="color: #000080;">=&gt;</span>
    &nbsp;
       <span style="color: #0000ff; font-weight: bold;">def</span> userAuthorization <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> UserAuthorizationImpl
    &nbsp;
       <span style="color: #0000ff; font-weight: bold;">class</span> UserAuthorizationImpl <span style="color: #0000ff; font-weight: bold;">extends</span> UserAuthorization <span style="color: #F78811;">&#123;</span>
          <span style="color: #0000ff; font-weight: bold;">def</span> authorize<span style="color: #F78811;">&#40;</span>user<span style="color: #000080;">:</span> User<span style="color: #F78811;">&#41;</span> <span style="color: #F78811;">&#123;</span>
             println<span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;Authorizing &quot;</span> + user.<span style="color: #000000;">username</span><span style="color: #F78811;">&#41;</span>
             <span style="color: #008000; font-style: italic;">// Obtaining the dependency and calling a method on it</span>
             userRepository.<span style="color: #000000;">find</span><span style="color: #F78811;">&#40;</span>user.<span style="color: #000000;">username</span><span style="color: #F78811;">&#41;</span>
          <span style="color: #F78811;">&#125;</span>
       <span style="color: #F78811;">&#125;</span>
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">// Component definition, as before
    trait UserAuthorizationComponent {
       def userAuthorization: UserAuthorization
    
       trait UserAuthorization {
          def authorize(user: User)
       }
    }
    
    // Component implementation
    trait UserAuthorizationComponentImpl 
                    extends UserAuthorizationComponent {
       // Dependencies
       this: UserRepositoryComponent =&gt;
    
       def userAuthorization = new UserAuthorizationImpl
    
       class UserAuthorizationImpl extends UserAuthorization {
          def authorize(user: User) {
             println(&quot;Authorizing &quot; + user.username)
             // Obtaining the dependency and calling a method on it
             userRepository.find(user.username)
          }
       }
    }</p></div>
    ";i:5;s:1049:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">val</span> env <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> UserAuthorizationComponentImpl 
                <span style="color: #0000ff; font-weight: bold;">with</span> UserRepositoryComponentHibernateImpl
    &nbsp;
    env.<span style="color: #000000;">userAuthorization</span>.<span style="color: #000000;">authorize</span><span style="color: #F78811;">&#40;</span>User<span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;1&quot;</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span></pre></td></tr></table><p class="theCode" style="display:none;">val env = new UserAuthorizationComponentImpl 
                with UserRepositoryComponentHibernateImpl
    
    env.userAuthorization.authorize(User(&quot;1&quot;))</p></div>
    ";i:6;s:1503:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">val</span> envTesting <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> UserAuthorizationComponentImpl 
                <span style="color: #0000ff; font-weight: bold;">with</span> UserRepositoryComponent <span style="color: #F78811;">&#123;</span>
       <span style="color: #0000ff; font-weight: bold;">def</span> userRepository <span style="color: #000080;">=</span> mock<span style="color: #F78811;">&#40;</span>classOf<span style="color: #F78811;">&#91;</span>UserRepository<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#41;</span>
    <span style="color: #F78811;">&#125;</span>
    envTesting.<span style="color: #000000;">userAuthorization</span>.<span style="color: #000000;">authorize</span><span style="color: #F78811;">&#40;</span>User<span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;3&quot;</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span></pre></td></tr></table><p class="theCode" style="display:none;">val envTesting = new UserAuthorizationComponentImpl 
                with UserRepositoryComponent {
       def userRepository = mock(classOf[UserRepository])
    }
    envTesting.userAuthorization.authorize(User(&quot;3&quot;))</p></div>
    ";i:7;s:4770:"
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
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #008000; font-style: italic;">// Interface</span>
    <span style="color: #0000ff; font-weight: bold;">trait</span> UserInformationComponent <span style="color: #F78811;">&#123;</span>
       <span style="color: #008000; font-style: italic;">// What is needed to create the component</span>
       <span style="color: #0000ff; font-weight: bold;">def</span> userInformation<span style="color: #F78811;">&#40;</span>user<span style="color: #000080;">:</span> User<span style="color: #F78811;">&#41;</span>
    &nbsp;
       <span style="color: #0000ff; font-weight: bold;">trait</span> UserInformation <span style="color: #F78811;">&#123;</span>
          <span style="color: #0000ff; font-weight: bold;">def</span> userCountry<span style="color: #000080;">:</span> Country
       <span style="color: #F78811;">&#125;</span>
    <span style="color: #F78811;">&#125;</span>
    &nbsp;
    <span style="color: #008000; font-style: italic;">// Implementation</span>
    <span style="color: #0000ff; font-weight: bold;">trait</span> UserInformationComponentImpl
                    <span style="color: #0000ff; font-weight: bold;">extends</span> UserInformationComponent <span style="color: #F78811;">&#123;</span>
       <span style="color: #008000; font-style: italic;">// Dependencies</span>
       <span style="color: #0000ff; font-weight: bold;">this</span><span style="color: #000080;">:</span> CountryRepositoryComponent <span style="color: #000080;">=&gt;</span>
    &nbsp;
       <span style="color: #0000ff; font-weight: bold;">def</span> userInformation<span style="color: #F78811;">&#40;</span>user<span style="color: #000080;">:</span> User<span style="color: #F78811;">&#41;</span> <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> UserInformationImpl<span style="color: #F78811;">&#40;</span>user<span style="color: #F78811;">&#41;</span>
    &nbsp;
       <span style="color: #0000ff; font-weight: bold;">class</span> UserInformationImpl<span style="color: #F78811;">&#40;</span><span style="color: #0000ff; font-weight: bold;">val</span> user<span style="color: #000080;">:</span> User<span style="color: #F78811;">&#41;</span> <span style="color: #0000ff; font-weight: bold;">extends</span> UserInformation <span style="color: #F78811;">&#123;</span>
          <span style="color: #0000ff; font-weight: bold;">def</span> userCountry<span style="color: #000080;">:</span> Country <span style="color: #F78811;">&#123;</span>
             <span style="color: #008000; font-style: italic;">// Using the dependency</span>
             countryRepository.<span style="color: #000000;">findByEmail</span><span style="color: #F78811;">&#40;</span>user.<span style="color: #000000;">email</span><span style="color: #F78811;">&#41;</span>
          <span style="color: #F78811;">&#125;</span>
       <span style="color: #F78811;">&#125;</span>
    <span style="color: #F78811;">&#125;</span>
    &nbsp;
    <span style="color: #008000; font-style: italic;">// Usage</span>
    <span style="color: #0000ff; font-weight: bold;">val</span> env <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> UserInformationComponentImpl 
                            <span style="color: #0000ff; font-weight: bold;">with</span> CountryRepositoryComponentImpl    
    env.<span style="color: #000000;">userInformation</span><span style="color: #F78811;">&#40;</span>User<span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;someuser@domain.pl&quot;</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span>.<span style="color: #000000;">userCountry</span></pre></td></tr></table><p class="theCode" style="display:none;">// Interface
    trait UserInformationComponent {
       // What is needed to create the component
       def userInformation(user: User)
    
       trait UserInformation {
          def userCountry: Country
       }
    }
    
    // Implementation
    trait UserInformationComponentImpl
                    extends UserInformationComponent {
       // Dependencies
       this: CountryRepositoryComponent =&gt;
    
       def userInformation(user: User) = new UserInformationImpl(user)
    
       class UserInformationImpl(val user: User) extends UserInformation {
          def userCountry: Country {
             // Using the dependency
             countryRepository.findByEmail(user.email)
          }
       }
    }
    
    // Usage
    val env = new UserInformationComponentImpl 
                            with CountryRepositoryComponentImpl    
    env.userInformation(User(&quot;someuser@domain.pl&quot;)).userCountry</p></div>
    ";}
categories:
  - dependency injection
  - java
  - scala

---
Continuing the mini-series on Dependency Injection (see my previous blogs: [problems with DI][1], [assisted inject for CDI][2] and [improving assisted inject][3]), I took a look at how DI is handled in Scala.

There are several approaches, one of the most interesting being the Cake Pattern. It is a DI solution that uses only native language features, without any framework support. For a good introduction see either [Jonas Boner&#8217;s blog][4] (on which this post is largerly based) or Martin Odersky&#8217;s paper [Scalable Component Abstractions][5]. 

I would like to extend the Cake Pattern to allow defining dependencies which need some user-provided data to be constructed (like in autofactories/assisted inject).

### The Cake Pattern: interfaces

But let&#8217;s start with an example of the base pattern. Let&#8217;s say that we have a `User` class,

<pre lang="scala" line="1">sealed case class User(username: String)
</pre>

and that we want to create a `UserRepository` service. Using the Cake Pattern, first we create the &#8220;interface&#8221;:

<pre lang="scala" line="1">trait UserRepositoryComponent { // For expressing dependencies
   def userRepository: UserRepository // Way to obtain the dependency

   trait UserRepository { // Interface exposed to the user
      def find(username: String): User
   }
}
</pre>

We have three important things here:

  * the `UserRepositoryComponent` trait will be used to express dependencies. It contains the component definition, consiting of:
  * a way to obtain the dependency: the `def userRepository` method (could also be a `val`, but why a `def` is better I&#8217;ll explain later)
  * the interface itself, here a `UserRepository` trait, which gives the functionality of locating users by username

### The Cake Pattern: implementations

An implementation of a component looks pretty similar:

<pre lang="scala" line="1">trait UserRepositoryComponentHibernateImpl
                extends UserRepositoryComponent {
   def userRepository = new UserRepositoryImpl 

   class UserRepositoryImpl extends UserRepository {
      def find(username: String): User = {
         println("Find with Hibernate: " + username)
         new User(username)
      }
   }
}
</pre>

Nothing special here. The component implementation extends the &#8220;interface&#8221; component trait. This brings into scope the `UserRepository` trait, which can be implemented.

### Using dependencies

How can one component/service say that it depends on another? Scala&#8217;s [self-type annotations][6] are of much use here. For example, if a `UserAuthorization` component requires the `UserRepository`, we can write this as follows:

<pre lang="scala" line="1">// Component definition, as before
trait UserAuthorizationComponent {
   def userAuthorization: UserAuthorization

   trait UserAuthorization {
      def authorize(user: User)
   }
}

// Component implementation
trait UserAuthorizationComponentImpl 
                extends UserAuthorizationComponent {
   // Dependencies
   this: UserRepositoryComponent =>

   def userAuthorization = new UserAuthorizationImpl

   class UserAuthorizationImpl extends UserAuthorization {
      def authorize(user: User) {
         println("Authorizing " + user.username)
         // Obtaining the dependency and calling a method on it
         userRepository.find(user.username)
      }
   }
}
</pre>

The important part here is `this: UserRepositoryComponent =>`. By this code fragment we specify that the `UserAuthorizationComponentImpl` requires some implementation of the `UserRepositoryComponent`. This also brings the content of the `UserRepositoryComponent` into scope, so both the method to obtain the user repository and the `UserRepository` trait itself are visible.

### Wiring

How do we wire different components together? Again quite easily. For example:

<pre lang="scala" line="1">val env = new UserAuthorizationComponentImpl 
            with UserRepositoryComponentHibernateImpl

env.userAuthorization.authorize(User("1"))
</pre>

First we need to construct the environment, by combining all of the components implementations that we want to use into a single object. Next, we can call methods on the environment to obtain services.

What about testing? Also easy:

<pre lang="scala" line="1">val envTesting = new UserAuthorizationComponentImpl 
            with UserRepositoryComponent {
   def userRepository = mock(classOf[UserRepository])
}
envTesting.userAuthorization.authorize(User("3"))
</pre>

Here we have mocked the user repository, so we can test the `UserAuthorizationComponentImpl` in isolation.

### `def`s over `val`s

Why are `def`s in the component definition better as the way to obtain the dependency? Because if you use a `val`, all implementations are locked and have to provide a single dependency instance (a constant). With a method, you can return different values on each invocation. For example, in a web environment, this is a great way to implement scoping! The method can read from the request or session state. Of course, it is still possible to provide a singleton. Or a new instance of the dependency on each invocation.

### Dependencies that need user data

Finally, we arrive to the main point. What if our dependencies need some data at runtime? For example, if we wanted to create a `UserInformation` service, which wraps a `User` instance?

Well, who said the the methods by which we obtain the dependencies need to be parameterless? 

<pre lang="scala" line="1">// Interface
trait UserInformationComponent {
   // What is needed to create the component
   def userInformation(user: User)

   trait UserInformation {
      def userCountry: Country
   }
}

// Implementation
trait UserInformationComponentImpl
                extends UserInformationComponent {
   // Dependencies
   this: CountryRepositoryComponent =>

   def userInformation(user: User) = new UserInformationImpl(user)

   class UserInformationImpl(val user: User) extends UserInformation {
      def userCountry: Country {
         // Using the dependency
         countryRepository.findByEmail(user.email)
      }
   }
}

// Usage
val env = new UserInformationComponentImpl 
                        with CountryRepositoryComponentImpl    
env.userInformation(User("someuser@domain.pl")).userCountry
</pre>

Isn&#8217;t this better than passing the `User` instance as a method parameter?

Using the Cake Pattern, creating stateful dependencies, which can be created at run-time with user-provided data, and still depend on other components is a breeze. This is similar to a factory method, however with much less noise.

### The good and the bad

**The good:**

  * no framework required, using only language features
  * **type safe** &#8211; a missing dependency is found at compile-time
  * powerful &#8211; &#8220;assisted inject&#8221;, scoping possible by implementing the dependency-providing method appropriately

**The bad:**

  * quite a lot of boilerplate code: each component has a component interface, implementation, service interface and service implementation

However, I don&#8217;t think defining all four parts is always necessary. If there&#8217;s only one implementation of a component, you can combine the component interface and implementation into one, and if there&#8217;s a need, refactor later.

Adam

 [1]: http://www.warski.org/blog/?p=272
 [2]: http://www.warski.org/blog/?p=289
 [3]: http://www.warski.org/blog/?p=334
 [4]: http://jonasboner.com/2008/10/06/real-world-scala-dependency-injection-di
 [5]: http://lamp.epfl.ch/~odersky/papers/ScalableComponent.pdf
 [6]: http://www.scala-lang.org/node/124
