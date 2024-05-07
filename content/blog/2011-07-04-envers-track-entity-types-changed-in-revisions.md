---
title: 'Envers: track entity types changed in revisions'
author: Adam Warski
type: blog
date: 2011-07-04T10:02:57+00:00
url: /blog/2011/07/envers-track-entity-types-changed-in-revisions/
dsq_thread_id:
  - 1055556994
tags:
  - envers
  - java
  - hibernate

---
Łukasz Antoniak, who is a regular contributor to [Envers][1] (thanks!), recently finished working on [HHH-5580][2], a feature which enables you to quickly retrieve the entity types that have been modified in a revision, by optionally storing them in a dedicated table.

[Łukasz wrote an excellent tutorial][3] on the subject, so if you are intersted, be sure to read it!

The feature described is available since the [Hibernate 4.0.0.Beta2][4] release.

Adam

 [1]: http://jboss.org/envers
 [2]: http://opensource.atlassian.com/projects/hibernate/browse/HHH-5580
 [3]: http://lukaszantoniak.wordpress.com/2011/07/02/hhh-5580/
 [4]: http://in.relation.to/Bloggers/HibernateCore400Beta2Release
