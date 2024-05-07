---
title: 'MacWire 0.1: Framework-less Dependency Injection with Scala Macros'
author: Adam Warski
type: post
date: 2013-04-04T11:07:53+00:00
url: /blog/2013/04/macwire-0-1-framework-less-dependency-injection-with-scala-macros/
simplecatch-sidebarlayout:
  - default
dsq_thread_id:
  - 1186104076
wp-syntax-cache-content:
  - |
    a:4:{i:1;s:2947:"
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
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">class</span> DatabaseAccess<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>
    <span style="color: #0000ff; font-weight: bold;">class</span> SecurityFilter<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>
    <span style="color: #0000ff; font-weight: bold;">class</span> UserFinder<span style="color: #F78811;">&#40;</span>databaseAccess<span style="color: #000080;">:</span> DatabaseAccess, securityFilter<span style="color: #000080;">:</span> SecurityFilter<span style="color: #F78811;">&#41;</span>
    <span style="color: #0000ff; font-weight: bold;">class</span> UserStatusReader<span style="color: #F78811;">&#40;</span>userFinder<span style="color: #000080;">:</span> UserFinder<span style="color: #F78811;">&#41;</span>
    &nbsp;
    <span style="color: #0000ff; font-weight: bold;">trait</span> UserModule <span style="color: #F78811;">&#123;</span>
        <span style="color: #0000ff; font-weight: bold;">import</span> com.<span style="color: #000000;">softwaremill</span>.<span style="color: #000000;">macwire</span>.<span style="color: #000000;">MacwireMacros</span>.<span style="color: #000080;">_</span>
    &nbsp;
        lazy <span style="color: #0000ff; font-weight: bold;">val</span> theDatabaseAccess   <span style="color: #000080;">=</span> wire<span style="color: #F78811;">&#91;</span>DatabaseAccess<span style="color: #F78811;">&#93;</span>
        lazy <span style="color: #0000ff; font-weight: bold;">val</span> theSecurityFilter   <span style="color: #000080;">=</span> wire<span style="color: #F78811;">&#91;</span>SecurityFilter<span style="color: #F78811;">&#93;</span>
        lazy <span style="color: #0000ff; font-weight: bold;">val</span> theUserFinder       <span style="color: #000080;">=</span> wire<span style="color: #F78811;">&#91;</span>UserFinder<span style="color: #F78811;">&#93;</span>
        lazy <span style="color: #0000ff; font-weight: bold;">val</span> theUserStatusReader <span style="color: #000080;">=</span> wire<span style="color: #F78811;">&#91;</span>UserStatusReader<span style="color: #F78811;">&#93;</span>
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">class DatabaseAccess()
    class SecurityFilter()
    class UserFinder(databaseAccess: DatabaseAccess, securityFilter: SecurityFilter)
    class UserStatusReader(userFinder: UserFinder)
    
    trait UserModule {
        import com.softwaremill.macwire.MacwireMacros._
    
        lazy val theDatabaseAccess   = wire[DatabaseAccess]
        lazy val theSecurityFilter   = wire[SecurityFilter]
        lazy val theUserFinder       = wire[UserFinder]
        lazy val theUserStatusReader = wire[UserStatusReader]
    }</p></div>
    ";i:2;s:1909:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    6
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">trait</span> UserModule <span style="color: #F78811;">&#123;</span>
        lazy <span style="color: #0000ff; font-weight: bold;">val</span> theDatabaseAccess   <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> DatabaseAccess<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>
        lazy <span style="color: #0000ff; font-weight: bold;">val</span> theSecurityFilter   <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> SecurityFilter<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>
        lazy <span style="color: #0000ff; font-weight: bold;">val</span> theUserFinder       <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> UserFinder<span style="color: #F78811;">&#40;</span>theDatabaseAccess, theSecurityFilter<span style="color: #F78811;">&#41;</span>
        lazy <span style="color: #0000ff; font-weight: bold;">val</span> theUserStatusReader <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> UserStatusReader<span style="color: #F78811;">&#40;</span>theUserFinder<span style="color: #F78811;">&#41;</span>
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">trait UserModule {
        lazy val theDatabaseAccess   = new DatabaseAccess()
        lazy val theSecurityFilter   = new SecurityFilter()
        lazy val theUserFinder       = new UserFinder(theDatabaseAccess, theSecurityFilter)
        lazy val theUserStatusReader = new UserStatusReader(theUserFinder)
    }</p></div>
    ";i:3;s:1089:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">trait</span> UserModuleForTests <span style="color: #0000ff; font-weight: bold;">extends</span> UserModule <span style="color: #F78811;">&#123;</span>
        <span style="color: #0000ff; font-weight: bold;">override</span> lazy <span style="color: #0000ff; font-weight: bold;">val</span> theDatabaseAccess <span style="color: #000080;">=</span> mockDatabaseAccess
        <span style="color: #0000ff; font-weight: bold;">override</span> lazy <span style="color: #0000ff; font-weight: bold;">val</span> theSecurityFilter <span style="color: #000080;">=</span> mockSecurityFilter
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">trait UserModuleForTests extends UserModule {
        override lazy val theDatabaseAccess = mockDatabaseAccess
        override lazy val theSecurityFilter = mockSecurityFilter
    }</p></div>
    ";i:4;s:664:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;">libraryDependencies +<span style="color: #000080;">=</span> <span style="color: #6666FF;">&quot;com.softwaremill.macwire&quot;</span> <span style="color: #000080;">%%</span> <span style="color: #6666FF;">&quot;core&quot;</span> <span style="color: #000080;">%</span> <span style="color: #6666FF;">&quot;0.1&quot;</span></pre></td></tr></table><p class="theCode" style="display:none;">libraryDependencies += &quot;com.softwaremill.macwire&quot; %% &quot;core&quot; % &quot;0.1&quot;</p></div>
    ";}
tags:
  - dependency injection
  - macwire
  - scala

---
Using _Dependency Injection_ is almost a standard when developing software. However, in many cases it may seem that using the pattern implicates using a DI container/framework. But is a framework really needed? 

To implement DI all you really need is to remove the `new`s from your code, and move the dependencies to the constructor. There must be of course some place where the objects are created; for that you need a top-level (&#8220;main&#8221; class), where all the wiring is done. That&#8217;s where DI containers really help: they remove the tedious task of passing the right parameters to the constructors. Usually that&#8217;s done at run-time using reflection.

[MacWire][1] takes a different approach. Basing on declarations specifying which classes should be instantiated, it generates the code needed to create a new class instance, with the correct parameters taken from the enclosing type. This is done at compile-time using a [Scala Macro][2]. The code is then type-checked by the Scala compiler, so the whole process is type-safe, and if a dependency is missing, you&#8217;ll know that immediately (unlike with traditional DI containers).

For example, given:

<pre lang="scala" line="1">class DatabaseAccess()
class SecurityFilter()
class UserFinder(databaseAccess: DatabaseAccess, securityFilter: SecurityFilter)
class UserStatusReader(userFinder: UserFinder)

trait UserModule {
    import com.softwaremill.macwire.MacwireMacros._

    lazy val theDatabaseAccess   = wire[DatabaseAccess]
    lazy val theSecurityFilter   = wire[SecurityFilter]
    lazy val theUserFinder       = wire[UserFinder]
    lazy val theUserStatusReader = wire[UserStatusReader]
}
</pre>

The generated code will be:

<pre lang="scala" line="1">trait UserModule {
    lazy val theDatabaseAccess   = new DatabaseAccess()
    lazy val theSecurityFilter   = new SecurityFilter()
    lazy val theUserFinder       = new UserFinder(theDatabaseAccess, theSecurityFilter)
    lazy val theUserStatusReader = new UserStatusReader(theUserFinder)
}
</pre>

The classes that should be wired should be contained in a Scala `trait`, `class` or `object` (the container forms a &#8220;module&#8221;). MacWire looks up values from the enclosing type (trait/class/object), and from any super-traits/classes. Hence it is possible to combine several modules using inheritance.

Currently two scopes are supported; the dependency can be a singleton (declared as a `val`/`lazy val`) or a new instance can be created for each usage (declared as a `def`).

Note that this approach is very flexible; all that we are dealing with here is regular Scala code, so if a class needs to be created in some special way, there&#8217;s nothing stopping us from simply writing it down as code. Also, `wire[T]` can be nested inside a method&#8217;s body, and it will be expanded to new instance creation as well.

For integration testing a module, if for some classes we&#8217;d like to use a mock, a simple override suffices, e.g.:

<pre lang="scala" line="1">trait UserModuleForTests extends UserModule {
    override lazy val theDatabaseAccess = mockDatabaseAccess
    override lazy val theSecurityFilter = mockSecurityFilter
}
</pre>

The project is a follow up of my [earlier blog post][3]. There is also a similar project for Java, which uses annotation processors: [Dagger][4].

MacWire is available in [Maven Central][5]. To use, simply add this line to your SBT build:

<pre lang="scala" line="1">libraryDependencies += "com.softwaremill.macwire" %% "core" % "0.1"
</pre>

The [code is on GitHub][1], licensed under the Apache2 license, so feel free to use, fork & explore. Take a look at the README which contains some more information.

Future plans include support for factories and by-name parameter lookup, support for configuration values and more scopes, like request or session scopes, which are very useful for web projects.

Adam

 [1]: https://github.com/adamw/macwire
 [2]: http://scalamacros.org/
 [3]: http://www.warski.org/blog/2013/03/dependency-injection-with-scala-macros-auto-wiring/ "Dependency injection with Scala macros: auto-wiring"
 [4]: https://github.com/square/dagger
 [5]: http://repo1.maven.org/maven2/com/softwaremill/macwire/
