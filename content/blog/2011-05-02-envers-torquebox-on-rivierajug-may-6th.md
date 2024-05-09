---
title: 'Envers & TorqueBox on RivieraJUG – May 6th'
author: Adam Warski
type: blog
date: 2011-05-02T06:13:49+00:00
url: /blog/2011/05/envers-torquebox-on-rivierajug-may-6th/
disqus_identifier: 1058428028
tags:
  - testing
  - jee
  - envers
  - hibernate
  - java
  - ruby

---
This Friday (6th of May) I will have the pleasure to present two talks: one on [Envers][1] and one on [Torquebox][2], thanks to an invitation from the [RivieraJUG][3].

Envers is a data auditing/versioning module of [Hibernate][4]; with a single `@Audited` annotation all changes to the selected entity will be written and available for easy retrieval.

TorqueBox, which recently [reached version 1.0][5], is a JBoss-based Ruby server, integrating the Ruby and Java worlds. JBoss services are exposed using Ruby APIs, and you can deploy both Ruby (including Ruby On Rails) and &#8220;traditional&#8221; JEE apps to the server.

Expect a lot of **live demos**! I will show how to use the basic functionalities of Envers to implement data auditing, some advanced features as well as how Envers maps data to its database schema. I will also roughly explain how Envers is built and what is needed to &#8220;build your own Envers&#8221; &#8211; it&#8217;s not as complicated as it looks, Hibernate offers a lot of not-well-known but powerful features. 

In the Torquebox talk, we will create a simple Ruby On Rails application and deploy it to Torquebox. Later, we will add a java module which contains [CDI beans][6] (CDI is the new dependency injection framework in JEE6) and integrate the Ruby On Rails app with our CDI beans. Also, we will create a simple scheduler service using Torquebox&#8217;s API and an integration test of java module using [Arquillian][7].

The meeting will be held at [INRIA Sophia-Antipolis][8] on 6pm. [See the announcement][9] for details. 

And see you there! :)  
Adam

 [1]: http://jboss.org/envers
 [2]: http://torquebox.org
 [3]: http://www.rivierajug.org/
 [4]: http://hibernate.org
 [5]: http://torquebox.org/news/2011/04/29/torquebox-ruby-appserver-1-0-0-available-now/
 [6]: http://docs.jboss.org/weld/reference/1.1.0.Final/en-US/html_single/
 [7]: http://www.jboss.org/arquillian
 [8]: http://maps.google.fr/maps?f=q&source=s_q&hl=en&geocode=&q=inria,+sophia-antipolis&sll=47.15984,2.988281&sspn=20.81297,46.757813&ie=UTF8&t=h&ll=43.616722,7.067868&spn=0.005406,0.011415&z=17&iwloc=A
 [9]: http://www.rivierajug.org/xwiki/bin/view/Main/201105-envers-torquebox
