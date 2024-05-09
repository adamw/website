---
title: Envers 1.2.0.GA released!
author: Adam Warski
type: blog
date: 2009-03-05T17:09:08+00:00
url: /blog/2009/03/envers-120ga-released/
disqus_identifier:
  - 1051934967
tags:
  - envers
  - java
  - hibernate

---
Today [Envers 1.2.0.GA][1] has been released! It is a Hibernate 3.3 compatible version of the newest Envers codebase, which will be included as a module in Hibernate 3.5. You can download it [here][2], and find documentation [here][3].

The main highlights of this release are:

  * renamed &#8220;versioning&#8221; to &#8220;auditing&#8221;, so `@Versioned` becomes `@Audited` etc
  * full support for inheritance
  * support for relations in components
  * improved queries (for example possibility to add restricitions on the revision entity)

For a full list of issues solved, see [here][4]. Special thanks to Erik-Berndt Scheper for providing valuable patches!

The release will also be soon available through the JBoss maven repository.

Have fun,  
Adam

 [1]: http://www.jboss.org/envers/
 [2]: http://www.jboss.org/envers/downloads
 [3]: http://jboss.org/files/envers/docs/index.html
 [4]: http://opensource.atlassian.com/projects/hibernate/secure/IssueNavigator.jspa?reset=true&&pid=10031&resolution=1&resolution=2&resolution=3&resolution=4&resolution=5&resolution=6&updated%3Abefore=5%2FMar%2F09&component=10280&sorter/field=priority&sorter/order=DESC
