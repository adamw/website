---
title: Envers bundled with JBoss AS 7.0.2!
author: Adam Warski
type: blog
date: 2011-09-28T20:05:03+00:00
url: /blog/2011/09/envers-bundled-with-jboss-as-7-0-2/
dsq_thread_id:
  - 1051936242
tags:
  - envers
  - hibernate
  - java

---
Using [Envers][1] is now even easier! Since [version 7.0.2][2], Envers comes bundled with JBoss Application Server.

To showcase how easy the integration is, I [created a small JSF/CDI application][3], which uses Envers and can be deployed straight to AS7. To build the application I decided to try out [JBoss Forge][4], which turned out to be very easy to use and provided me with a simple one-entity CRUD in no time. Just a few changes made it possible to track changes made to the entity, and view its history.

So if you are using AS 7.0.2, to add Envers to your app you just need to:

  1. Add `hibernate-envers` to the `pom.xml` using the `provided` scope
  2. Add `@Audited` to the entities that you want to audit

To view all the changes necessary in detail, just [take a look at this commit][5] (note that the `MANIFEST.MF` file is not required, as I initially mistakenly thought).

Apart from storing the changes, you may also want to view the history of an entity. That&#8217;s also pretty straightforward. All we need is to create a new JSF view and a CDI bean with a method running a simple history query. Again, [this commit][6] shows all the changes needed.

<img decoding="async" src="http://www.warski.org/blog/wp-content/uploads/2011/09/envers_as7_demo_screenshot.png" alt="" width="75%" /> 

You can deploy the application in two ways:

  1. Run `mvn clean install`, and copy the resulting `.war` to `jboss/standalone/deployments`
  2. If you are using Forge, run `forge` from the checkout directory, and invoke: `build`, then: `as7 deploy`

After deployment, you should be able to access the application using `http://localhost:8080/envers-as7-demo`. Click on the `Person` link on the left to add, edit, list and view history of the entity.

By default, the application uses an example datasource, but if you wish to explore the additional schema generated by Envers you can easily change it to another database by modifying `persistence.xml` and creating a new datasource in the AS administration console.

As always I invite you to submit feedback on the [Envers forum][7]. Have fun!

Adam

 [1]: http://jboss.org/envers
 [2]: http://jboss.org/jbossas/downloads
 [3]: https://github.com/adamw/envers-as7-demo
 [4]: http://jboss.org/forge
 [5]: https://github.com/adamw/envers-as7-demo/commit/ccad766bc834b6211d57391bed04f76904a64456
 [6]: https://github.com/adamw/envers-as7-demo/commit/be08646bf3bca842004cdd5374c218bbdfbf820c
 [7]: http://community.jboss.org/en/hibernate/envers?view=discussions