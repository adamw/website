---
title: Envers 1.1.0.beta2 released with collections support
author: Adam Warski
type: blog
date: 2008-09-27T08:44:29+00:00
url: /blog/2008/09/envers-110beta2-released-with-collections-support/
disqus_identifier:
  - 1051934634
tags:
  - envers
  - java
  - hibernate

---
Today [Envers 1.1.0.beta2][1] has been released. This release focuses on supporting persistent collections. In earlier versions, it was only possible to version collections belonging to one-to-many bidirectional relations. Right now Envers supports (almost: [see here which and why][2]) all persistent collections [supported][3] by Hibernate:

  * one-to-many uni- and bi-directional
  * many-to-many uni- and bi-directional
  * sets and indexed lists of &#8220;simple&#8221; types, like strings, numbers, etc.
  * various maps (mapped with `@MapKey`, `@MapKeyManyToMany`, &#8230;)

Moreover, there is preliminary support for custom user types. Please test! :) Detailed release notes can be found [here][4].

You can head straight to the [downloads][5], and then, if you have any problems or questions, to the [forums][6].

I&#8217;ve also [pushed][7] 1.1.0.beta2 to JBoss&#8217;s Maven2 repository, and 1.0.0.GA [made it][8] to the central maven repository.

Enjoy!  
Adam

 [1]: http://www.jboss.org/envers
 [2]: http://www.jboss.org/envers/collections.html
 [3]: http://www.hibernate.org/hib_docs/v3/reference/en/html/collections.html
 [4]: https://jira.jboss.org/jira/secure/ReleaseNote.jspa?projectId=12310660&styleName=Html&version=12312718
 [5]: http://www.jboss.org/envers/downloads
 [6]: http://www.jboss.com/index.html?module=bb&op=viewforum&f=283
 [7]: http://repository.jboss.org/maven2/org/jboss/envers/jboss-envers/
 [8]: http://repo1.maven.org/maven2/org/jboss/envers/jboss-envers/
