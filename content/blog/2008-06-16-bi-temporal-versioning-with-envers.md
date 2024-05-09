---
title: Bi-temporal versioning with Envers
author: Adam Warski
type: blog
date: 2008-06-16T16:22:43+00:00
url: /blog/2008/06/bi-temporal-versioning-with-envers/
disqus_identifier: 1051285577
tags:
  - envers
  - java
  - hibernate

---
With the recent addition of [queries][1] to [Envers][2], it is now possible to easily retrieve data in a bi-temporal way. If you are not familiar with bi-temporal versioning, a good introduction by Martin Fowler can be found [here][3].

Suppose we want to store information about Persons and Addresses, at which these persons live. Moreover, we also want to store dates: when a Person registered the fact that he/she moved, and when did he/she actually move &#8211; these may be different, as a person may have first moved, and only registered the fact after a week. Storing the two dates: registered and actual, for some entity, is the basis of bi-temporal versioning.

We can store this information in a Registration entity, which is in a one-to-one relationship with Person, and one-to-many with Address. (Each Person can have at most one Registration, and therefore also Address; each Address can have many Registrations, and therefore many Persons living there.) The Registration entity can also store the registered and actual dates. Additionally, it can be versioned, so that all historical information is remembered.

Using queries, we can, for example, answer this question: &#8220;What did we know about the address of person X in the state of the database from date Y?&#8221;. To do this, we have to select a registration, which has the actual date as big (recent) as possible, but both the registration and actual dates must be before Y. Hence, the query can look like this:

<pre lang="java" line="1" escape="true">versionsReader.createQuery()
.forRevisionsOfEntity(Registration.class, true)
  .add(VersionsRestrictions.maximizeProperty("actualDate")
    .add(VersionsRestrictions.relatedIdEq("person", personXId))
    .add(VersionsRestrictions.le("registrationDate", dateY))
    .add(VersionsRestrictions.le("actualDate", dateY)))
.getSingleResult();
</pre>

You may experiment with bi-temporal versioning using the updated [Envers+Seam demo][4]. To run the demo, you&#8217;ll need [JBoss AS 4.2][5]. After downloading, unzip and deploy the -ds.xml and .ear files, start the server, and go to [http://localhost:8080/envers\_seam\_demo][6]. A screenshot from the demo:

<img decoding="async" src="http://www.jboss.org/files/envers/seam_demo.png" border="0" /> 

Moreover, the second beta release of Envers is available. It contains minor feature additions ([release notes][7]).

Adam

 [1]: http://www.jboss.org/envers/queries.html
 [2]: http://www.jboss.org/envers/
 [3]: http://martinfowler.com/ap2/timeNarrative.html
 [4]: http://www.jboss.org/envers/downloads/
 [5]: http://www.jboss.org/jbossas/
 [6]: http://localhost:8080/envers_seam_demo
 [7]: http://jira.jboss.org/jira/secure/ConfigureReport.jspa?versions=12312415&sections=1&sections=2&sections=3&style=none&selectedProjectId=12310660&reportKey=pl.net.mamut%3Areleasenotes&Next=Next
