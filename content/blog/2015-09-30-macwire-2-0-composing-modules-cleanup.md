---
title: 'MacWire 2.0: composing modules & cleanup'
author: Adam Warski
type: post
date: 2015-09-30T07:49:01+00:00
url: /blog/2015/09/macwire-2-0-composing-modules-cleanup/
simplecatch-sidebarlayout:
  - default
dsq_thread_id:
  - 4179378336
tags:
  - dependency injection
  - metaprogramming
  - macwire
  - scala

---
Recently we released [MacWire 2.0][1], with a number of new features and general code cleanup. The majority of the work was done by [Bruno Bieth][2] &#8211; thanks!

If you don’t know what MacWire is: a lightweight and non-intrusive Scala dependency injection library, and in many cases a replacement for DI containers. You can read a general guide to DI in Scala [over here][3].

But &#8211; what’s new?

## Modules

First of all, when looking for values matching a given type, in addition to the normal procedure (looking in enclosing code blocks, methods, trait/class/object, parent traits/classes), `import` statements are taken into account. This makes it possible to combine modules using composition, not only inheritance as before.

For example:

<pre><code class="scala">// base module & classes
class UserFinder(...)
class UserModule {
   lazy val userFinder: UserFinder = ...
}

// composing modules
class FacebookAccess(userFinder: UserFinder)
class SocialModule(userModule: UserModule) {
  import userModule._

  lazy val facebookAccess = wire[FacebookAccess]
}
</code></pre>

There’s also an experimental `@Module` annotation which you can put on a trait or class; when the annotation is present, members of nested modules (instances) will be taken into account automatically (without the need for an import) when looking for values.

This style of combining modules can also be useful when developing “plugins” for existing module structures.

## wireWith

Another new feature is the `wireWith` macro, which allows you to create new instances using a factory method, instead of a constructor. For example:

<pre><code class="scala">class A()

class C(a: A, specialValue: Int)
object C {
  // factory method
  def create(a: A) = new C(a, 42)
}

trait MyModule {
  lazy val a = wire[A]
  lazy val c = wireWith(C.create _) // will generate: C.create(a)
}
</code></pre>

## Breaking changes

With the major version bump, there are some breaking changes. Please refer to the [readme][4] for a full list.

The imports are now standardized; you should use `import com.softwaremill.macwire._` to get the `wire` methods, and `import com.softwaremill.macwire.tagging._` for tagging support.

Code is also better separated into modules, allowing you to have a compile-time-only dependency on the wire macros, if you are not using any of the additional features.

## Summing up

Let us know if you encounter any problems migrating your code! Or if you have any other feedback regarding the features. Either [create an issue/PR on GitHub][1] or leave us a message on [Gitter][5].

 [1]: https://github.com/adamw/macwire
 [2]: https://github.com/backuitist
 [3]: http://di-in-scala.github.io
 [4]: https://github.com/adamw/macwire#migrating
 [5]: https://gitter.im/adamw/macwire
