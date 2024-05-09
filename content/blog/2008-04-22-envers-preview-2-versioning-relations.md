---
title: 'Envers preview 2: versioning relations'
author: Adam Warski
type: blog
date: 2008-04-22T09:39:41+00:00
url: /blog/2008/04/envers-preview-2-versioning-relations/
disqus_identifier: 1094263273
tags:
  - envers
  - java
  - hibernate

---
You can now [download][1] a second preview version of the [Envers][2] library!

As a quick reminder, the library enables you to easily version your JPA entities, by simply annotating their properties with `@Versioned`; it works as an extension to Hibernate and Hibernate Entity Manager.

In this release, in addition to versioning simple properties, like strings, numbers, dates, you can also version the most common type of relations, namely:

  * `@OneToOne`
  * `@OneToOne(mappedBy="...")`
  * `@ManyToOne`
  * `@OneToMany(mappedBy="...")`

Envers has now a growing test suite, which already helped to eliminate some bugs. Moreover, [here][3] you will find a description on how to generate a database schema of your entities together with the versions tables.

If you&#8217;d like to share your opinion, you can now use the [forum][4]. Waiting for your posts there :).

Adam

 [1]: http://www.jboss.org/envers/downloads/
 [2]: http://www.jboss.org/envers
 [3]: http://www.jboss.org/envers/schema.html
 [4]: http://www.jboss.com/index.html?module=bb&op=viewforum&f=283
