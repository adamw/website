---
title: Envers in Anvers!
author: Adam Warski
type: post
date: 2008-12-04T18:29:22+00:00
url: /blog/2008/12/envers-in-anvers/
dsq_thread_id:
  - 1051934851
categories:
  - conference
  - envers
  - java
  - hibernate

---
December came, so it&#8217;s time for [Devoxx][1]! (formerly JavaPolis) 

If you are planning to visit Antwerp this year, make sure to come and [see the Envers presentation][2] (first conference day, 3pm)! I will explain the basic concepts, how to use various parts of the library as well as show some use-cases. Also, if you have any questions or suggestions, it will be a great opportunity to meet :).

As you may already know, [Envers][3] is now a [Hibernate][4] core module. There is no official Hibernate release yet available with Envers included, but I&#8217;ve uploaded a [preview jar][5], which will work fine with Hibernate 3.3.x. The migrated documentation is also avialable in html and pdf formats on the site. If you spot any errors, or find some information missing, please let me know! For Maven2 users, a snapshot of Hibernate-3.4.0 (which includes Envers) is deployed to the jboss snapshots repository (located [here][6], instructions [here][7]).

Main changes in this preview release:

  * renaming of &#8220;versioning&#8221; to &#8220;auditing&#8221;, so now there is `@Audited` instead of `@Versioned` etc.
  * support for joined and table-per-class inheritance; this **completes the JPA mappings supported**
  * improved query API, extending the possibilities of specifying restrictions on revision properties
  * correct support for field and property access

As always, waiting for feedback on the [forums][8]!

Enjoy! :)  
Adam

 [1]: http://devoxx.com/
 [2]: http://devoxx.com/display/JV08/Envers+-+Easy+Entity+Versioning
 [3]: http://www.jboss.org/envers/
 [4]: http://hibernate.org/
 [5]: http://www.jboss.org/envers/downloads/
 [6]: https://snapshots.jboss.org/maven2/
 [7]: http://www.jboss.org/community/docs/DOC-11381
 [8]: http://www.jboss.com/index.html?module=bb&op=viewforum&f=283
