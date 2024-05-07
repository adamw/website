---
title: Envers 1.2.2 released!
author: Adam Warski
type: post
date: 2009-12-18T12:17:59+00:00
url: /blog/2009/12/envers-122-released/
dsq_thread_id:
  - 1051935032
categories:
  - envers
  - java
  - hibernate

---
After quite a long break, I&#8217;m happy to announce a new release of [Envers][1] (available on the [downloads][2] page, or [via Maven][3]). This is mainly a bugfix release, but there are also new features:

  * possibility to audit relations from audited to non-audited entites. This feature was actually one of the most asked for. You have to explicitly annotate such relations with `@Audited(targetAuditMode=NOT_AUDITED)`. Then, when reading historic versions of your entities, the relation will always point to the &#8220;current&#8221; version of the entity. This is mainly useful for dictionary-type entities, which never change.
  * new configuration properties: `org.hibernate.envers.default_schema` and `org.hibernate.envers.default_catalog` with which you can specify the default catalog/schema for audit tables
  * by default, when you delete an entity, in the audit table only a &#8220;marker&#8221; entry will be written with all fields `null` except the id. But if you want, you can now store all data of the entity by specifying the `org.hibernate.envers.store_data_at_delete` property to true. This is normally not needed as the data is already stored in the last revision before the delete.
  * experimental support for &#8220;fake&#8221; bidirectional relations, mapped with `@OneToMany+@JoinColumn` on one side, and `@ManyToOne+@Column(insertable=false, updatable=false)` on the many side. Envers can now audit such relations without a middle table, but the user has to add an `@AuditMappedBy` annotation. See the [documentation][4] for more details.

Also, all configuration properties were renamed from camel-case to the Hibernate standard, separating words using underscores. But don&#8217;t worry, old properties will work also. Moreover, the legacy `@Versioned` annotations support was removed. If somebody didn&#8217;t yet migrate, the transition should be trivial.

This release is fully compatible with Hibernate 3.3, but remember that Envers is now a Hibernate core module, so it will be also included in the coming Hibernate 3.5 release.

For a full list of resolved issues, see the [Hibernate JIRA][5] page.

Thanks to Tomasz Bech, Nicolas Rouge and Eugene Goroschenya for providing patches and all other contributors and users for valuable input on the [forums][6] and in [JIRA][7]! As always, feedback is very welcome.

Envers also has a new [homepage][1]; big thanks for the work to Rysiek, Joseph and the JBoss.ORG team!

Merry Christmas!  
Adam

 [1]: http://www.jboss.org/envers/
 [2]: http://www.jboss.org/envers/downloads
 [3]: https://community.jboss.org/wiki/EnversFAQ
 [4]: http://www.jboss.org/files/envers/docs/index.html
 [5]: http://opensource.atlassian.com/projects/hibernate/secure/IssueNavigator.jspa?reset=true&jqlQuery=project+%3D+HHH+AND+component+%3D+envers+AND+status+in+%28Resolved%2C+Closed%29+AND+resolved+%3E%3D+2009-06-06+AND+resolved+%3C%3D+2009-12-18
 [6]: http://community.jboss.org/en/envers?view=discussions
 [7]: http://opensource.atlassian.com/projects/hibernate/browse/HHH
