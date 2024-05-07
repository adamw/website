---
title: Envers moves to Hibernate!
author: Adam Warski
type: post
date: 2008-10-30T11:08:13+00:00
url: /blog/2008/10/envers-moves-to-hibernate/
dsq_thread_id:
  - 1051934692
tags:
  - envers
  - java
  - hibernate

---
I&#8217;m happy to announce that [Envers][1] is now a module of Hibernate! This means that:

  * the code is now in the [hibernate-core][2] repository (`envers` module). There will be no more commits to the old repository. 
  * issue tracking moved to [Hibernate&#8217;s JIRA][3]. All open issues from the old JIRA have been moved there. When creating an Envers issue, please select the &#8220;envers&#8221; component. 
  * envers is now built using maven2, which replaces the old ant build 

The website, documentation and forum so far stay unchanged, but stay tuned for more updates :).

There will be some changes to Envers coming with this move. One of them is renaming of `@Versioned` to `@Audited`, to avoid confusion with the `@Version` annotation used in JPA for optimistic locking. The old `@Versioned` annotation will become deprecated, and after some time removed completely.

I would like to thank Steve Ebersole for doing the actual move and creating the maven build!

Adam

 [1]: http://www.jboss.org/envers/
 [2]: http://anonsvn.jboss.org/repos/hibernate/core/trunk/
 [3]: http://opensource.atlassian.com/projects/hibernate/browse/HHH
