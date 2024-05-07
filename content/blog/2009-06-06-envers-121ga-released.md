---
title: Envers 1.2.1.GA released
author: Adam Warski
type: post
date: 2009-06-06T08:44:18+00:00
url: /blog/2009/06/envers-121ga-released/
dsq_thread_id:
  - 1051934977
tags:
  - envers
  - java
  - hibernate

---
Yesterday I uploaded a new release of [Envers][1], 1.2.1.GA. It is mainly a bugfix release, but there is also one new feature. You can now access the current revision entity directly using `AuditReader`, without the need to use a revisions listener. For details, see the description of the `AuditReader.getCurrentRevision()` method in the [javadocs][2].

You can see the list of issues closed in [jira][3].

The release is available for download [here][4]; it&#8217;s also in [jboss&#8217;s maven2 repository][5].

Have fun! :)  
Adam

 [1]: http://www.jboss.org/envers
 [2]: http://www.jboss.org/files/envers/api-new/index.html
 [3]: http://opensource.atlassian.com/projects/hibernate/secure/IssueNavigator.jspa?reset=true&&pid=10031&updated%3Aafter=5%2FMar%2F09&component=10280&status=5&status=6&sorter/field=priority&sorter/order=DESC
 [4]: http://www.jboss.org/envers/downloads
 [5]: http://repository.jboss.org/maven2/org/jboss/envers/jboss-envers/1.2.1.GA-hibernate-3.3/
