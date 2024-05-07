---
title: Envers 1.0.0.GA released!
author: Adam Warski
type: blog
date: 2008-07-16T10:51:30+00:00
url: /blog/2008/07/envers-100ga-released/
dsq_thread_id:
  - 1051934516
tags:
  - envers
  - java
  - hibernate

---
Today the first general availability version of [Envers][1] has been released ([downloads][2], [release notes][3]).

This is a stable version containig all the features found in the preview and beta versions. It doesn&#8217;t contain any major new additions, only some minor bug fixes and the possibility to generate revisions on collections change.

What does it mean? Imagine you have a `Person` and `Address` entities in a bidirectional many-to-one relation (each person has exactly one address and many people can live at the same address). Now you change an address of a person &#8211; hence, the content of the collection of persons for the new and old addresses change. In the preview and beta versions of Envers, no revision will be generated for the Addresses (only for Person), as data in the database tables is not modified (but data in the java beans **is** modified). Now, however, a revision will be created for all three entities.

You can switch to the old behaviour by setting the `org.jboss.envers.revisionOnCollectionChange` configuration option to `false` (see [more][4] on configuration).

There is also now a separate build for Hibernate 3.3.0.CR1.

If you&#8217;re an Ohloh user and like Envers, you can click on the &#8220;I use this&#8221; link on the [project profile page][5].

Thanks to talios, aamonten, genman, liuxiaodu, andrewwheeler and other forum users for very valuable feedback, ideas, testing and bug reports.

Hope you enjoy this release,  
Adam

 [1]: http://www.jboss.org/envers
 [2]: http://www.jboss.org/envers/downloads/
 [3]: https://jira.jboss.org/jira/secure/ReleaseNote.jspa?projectId=12310660&styleName=Html&version=12312323
 [4]: http://www.jboss.org/envers/configuration.html
 [5]: http://www.ohloh.net/projects/envers
