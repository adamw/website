---
title: 'Envers preview 3: logging data for revisions, Seam demo'
author: Adam Warski
type: post
date: 2008-05-06T12:57:24+00:00
url: /blog/2008/05/envers-preview-3-logging-data-for-revisions-seam-demo/
simplecatch-sidebarlayout:
  - default
dsq_thread_id:
  - 1051934455
tags:
  - envers
  - java
  - hibernate

---
With the preview 3 release of [Envers][1], you can easily associate additional data with revisions. This could be, for example, the name of the user making the change. You simply need to annotate an entity with `@RevisionEntity`: an instance of this entity will be persisted with each new revision. To provide a way to fill the entity with custom data, you need to implement an interface, and pass it as a parameter to the annotation.[buy inflatable aviva water sports][2]

For example, to store the name of the currently logged in user in a JBoss Seam application, the implementation of the listener would look as follows:

<pre lang="java" line="1" escape="true">public class ExampleListener
    implements RevisionListener {
    public void newRevision(Object revisionEntity) {
        ExampleRevEntity exampleRevEntity =
           (ExampleRevEntity) revisionEntity;
        Identity identity =
           (Identity) Component.getInstance(
              "org.jboss.seam.security.identity");

        exampleRevEntity.setUsername(
           identity.getUsername());
    }
}
</pre>

For more details see [here][3].

There is also a [demo][4] Envers+Seam application: a very simple wiki. It allows users to create and edit pages, view their history and see which users made the changes.

And, as always, waiting for your comments on the [forums][5]! Thanks to the current forum members for the bug reports and ideas!

Adam

 [1]: http://www.jboss.org/envers/
 [2]: http://www.east-inflatables.co.uk/p/016007.html
 [3]: http://www.jboss.org/envers/revision_log.html
 [4]: http://www.jboss.org/envers/downloads/
 [5]: http://www.jboss.com/index.html?module=bb&op=viewforum&f=283
