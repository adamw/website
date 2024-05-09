---
title: Envers and Hibernate 4.0.0.Alpha2 – automatic listener registration
author: Adam Warski
type: blog
date: 2011-04-18T06:46:23+00:00
url: /blog/2011/04/envers-and-hibernate-4-0-0-alpha2-automatic-listener-registration/
disqus_identifier: 1051040621
tags:
  - envers
  - java
  - hibernate

---
Development of [Hibernate4][1] is well under way, with the Alpha2 version [released recently][2]. It contains one major improvement which is quite significant for Envers, namely Integrators (see [HHH-5562][3] & [HHH-6081][4]).

Using this mechanism it will no longer be necessary to add the 6 event listeners to your Hibernate configuration &#8211; all that is needed for Envers to work is to put it on the classpath! If any of your entities are annotated with `@Audited`, they will get audited automatically (any changes will be written to the history tables).

It is of course possible to turn off the automatic registration, as well as write your own integrators, which automatically register custom event listeners. The integrators are discovered using java&#8217;s service loader, by the presence of a `META-INF/services/org.hibernate.spi.Integrator` file in the jar, which should contain the name of the class implementing the integration functionality.

Adam

 [1]: http://hibernate.org
 [2]: http://in.relation.to/Bloggers/HibernateCore400Alpha2Release
 [3]: http://opensource.atlassian.com/projects/hibernate/browse/HHH-5562
 [4]: http://opensource.atlassian.com/projects/hibernate/browse/HHH-6081
