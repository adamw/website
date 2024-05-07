---
title: Envers 1.1.0.GA released
author: Adam Warski
type: post
date: 2008-10-21T18:29:18+00:00
url: /blog/2008/10/envers-110ga-released/
dsq_thread_id:
  - 1051934686
tags:
  - envers
  - java
  - hibernate

---
Today [Envers][1] 1.1.0.GA has been released. For the impatient: go straight to the [downloads][2]! :) This release contains only minor modifications from the last beta version. You can see the release notes for this version [here][3], and combined release notes for all 1.1.0 versions [here][4].

The main emphasis of this release is support for persistent collections. You can now version any relation, collections of &#8220;simple&#8221; types, maps, etc. All collection mappings defined by JPA are now supported, and most of what Hibernate additionally allows also. One exception is that collections of components won&#8217;t yet work, and the second exception is described [here][5].

There are also improvements to the revision-of-entity query. You can now easily retrieve the history of an entity along with the revision metadata (like the date when a revision was commited) using a single query. See [here][6] for details.

Both [demos][2] have been updated to use the newest version of Envers. The wiki part of the Seam demo is additionally enhanced by adding a set of links (`String`-s) to each wiki page (of course this set of links is also versioned).

All comments, negative or positive, are as always very welcome on the [forum][7]. Thanks to all contributors and forum users, who have been very helpful in finding bugs and designing new features! :)

Enjoy!  
Adam

 [1]: http://www.jboss.org/envers/
 [2]: http://www.jboss.org/envers/downloads
 [3]: https://jira.jboss.org/jira/secure/ReleaseNote.jspa?projectId=12310660&styleName=Html&version=12312473
 [4]: https://jira.jboss.org/jira/secure/ConfigureReport.jspa?versions=12312473&versions=12312718&versions=12312514&sections=.1.7.2.4.10.9.8.3.12.11.5&style=none&selectedProjectId=12310660&reportKey=pl.net.mamut%3Areleasenotes&Next=Next
 [5]: http://www.jboss.org/envers/collections.html
 [6]: http://www.jboss.org/envers/queries.html
 [7]: http://www.jboss.com/index.html?module=bb&op=viewforum&f=283
