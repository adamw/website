---
title: 'Hibernate 4.1.4: Envers tests run&pass on multiple DBs'
author: Adam Warski
type: post
date: 2012-05-31T08:59:15+00:00
url: /blog/2012/05/hibernate-4-1-4-envers-tests-runpass-on-multiple-dbs/
dsq_thread_id:
  - 1054041242
categories:
  - envers
  - hibernate
  - java

---
_[Envers][1] is an entity auditing framework, making it possible to store and query for historical data._

The [Hibernate 4.1.4][2] release may seem to be a minor one, but it&#8217;s pretty important for Envers. Thanks to a huge amount of work mainly by [Łukasz Antoniak][3] and [Strong Liu][4], with substantial help from [Steve Ebersole][5], Envers tests are now part of the matrix testsuite. **Thanks guys**! :)

That means the tests now run regularly (and importantly, as of now pass) on MySQL, PostgreSQL, Oracle, MS SQL, DB2, Sybase (and the usual H2) databases. The testsuite contains dozens of mapping scenarios, each trying to write some data in a couple of transactions and then checking that the history is properly written, using both the default and [validity audit strategy][6].

Envers is [bundled][7] with [JBoss AS 7][8], as well is part of the [Hibernate distribution][9]. For more information see the [documentation][1].

Adam

 [1]: http://docs.jboss.org/hibernate/orm/4.1/devguide/en-US/html/ch15.html
 [2]: http://in.relation.to/Bloggers/HibernateORM414Release
 [3]: http://lukaszantoniak.wordpress.com/
 [4]: http://relation.to/Bloggers/StrongLiu
 [5]: http://relation.to/Bloggers/Steve
 [6]: http://docs.jboss.org/hibernate/orm/4.1/devguide/en-US/html/ch15.html#d5e4085
 [7]: http://www.warski.org/blog/2011/09/envers-bundled-with-jboss-as-7-0-2/
 [8]: http://www.jboss.org/jbossas
 [9]: http://hibernate.org/downloads
