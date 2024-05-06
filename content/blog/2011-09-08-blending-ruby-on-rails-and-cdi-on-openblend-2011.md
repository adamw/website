---
title: Blending Ruby (on Rails) and CDI on OpenBlend 2011
author: Adam Warski
type: post
date: 2011-09-08T08:56:57+00:00
url: /blog/2011/09/blending-ruby-on-rails-and-cdi-on-openblend-2011/
dsq_thread_id:
  - 1054625668
categories:
  - CDI
  - Java
  - JBoss
  - Languages
  - OpenBlend
  - Ruby
  - TorqueBox
  - Uncategorized
  - Weld

---
I will have the pleasure to speak on [OpenBlend 2011][1], in exactly a week: 15th September 2011, which will take place in [Ljubljana, Slovenia][2].

Apart from many other interesting presentations, I will be demoing [Torquebox][3] & CDI integration. Torquebox is an enhanced JBoss AS, which besides being the regular JEE container everybody knows lets you deploy Ruby applications and provides Ruby integration for various AS components (like clustering or caching). This also includes applications written in Ruby on Rails.

Moreover, you can deploy mixed Ruby and Java applications. And that&#8217;s exactly what we will do: deploy an application which has a Ruby-on-Rails frontend (which will provide us with instance code changes) and a CDI/Weld backend (with all the goodies coming from a DI framework, typesafety and Java).

Additionally, everything will run on the brand-new and lightning-fast [JBoss AS 7][4], which is the base for Torquebox 2.0.

See you there!  
Adam

 [1]: http://www.openblend.org/en/home
 [2]: http://en.wikipedia.org/wiki/Ljubljana
 [3]: http://torquebox.org/
 [4]: http://jboss.org/jbossas
