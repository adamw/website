---
title: Envers is back from vacations
author: Adam Warski
type: blog
date: 2008-09-03T09:58:52+00:00
url: /blog/2008/09/envers-is-back-from-vacations/
disqus_identifier: 1051934527
wp-syntax-cache-content:
  - |
    a:2:{i:1;s:3954:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    6
    7
    8
    9
    10
    11
    12
    </pre></td><td class="code"><pre class="xml" style="font-family:monospace;"><span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;property</span> <span style="color: #000066;">name</span>=<span style="color: #ff0000;">&quot;hibernate.ejb.event.post-insert&quot;</span></span>
    <span style="color: #009900;">  <span style="color: #000066;">value</span>=<span style="color: #ff0000;">&quot;org.jboss.envers.event.VersionsEventListener&quot;</span> <span style="color: #000000; font-weight: bold;">/&gt;</span></span>
    <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;property</span> <span style="color: #000066;">name</span>=<span style="color: #ff0000;">&quot;hibernate.ejb.event.post-update&quot;</span> </span>
    <span style="color: #009900;">  <span style="color: #000066;">value</span>=<span style="color: #ff0000;">&quot;org.jboss.envers.event.VersionsEventListener&quot;</span> <span style="color: #000000; font-weight: bold;">/&gt;</span></span>
    <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;property</span> <span style="color: #000066;">name</span>=<span style="color: #ff0000;">&quot;hibernate.ejb.event.post-delete&quot;</span> </span>
    <span style="color: #009900;">  <span style="color: #000066;">value</span>=<span style="color: #ff0000;">&quot;org.jboss.envers.event.VersionsEventListener&quot;</span> <span style="color: #000000; font-weight: bold;">/&gt;</span></span>
    <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;property</span> <span style="color: #000066;">name</span>=<span style="color: #ff0000;">&quot;hibernate.ejb.event.pre-collection-update&quot;</span></span>
    <span style="color: #009900;">  <span style="color: #000066;">value</span>=<span style="color: #ff0000;">&quot;org.jboss.envers.event.VersionsEventListener&quot;</span> <span style="color: #000000; font-weight: bold;">/&gt;</span></span>
    <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;property</span> <span style="color: #000066;">name</span>=<span style="color: #ff0000;">&quot;hibernate.ejb.event.pre-collection-remove&quot;</span> </span>
    <span style="color: #009900;">  <span style="color: #000066;">value</span>=<span style="color: #ff0000;">&quot;org.jboss.envers.event.VersionsEventListener&quot;</span> <span style="color: #000000; font-weight: bold;">/&gt;</span></span>
    <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;property</span> <span style="color: #000066;">name</span>=<span style="color: #ff0000;">&quot;hibernate.ejb.event.post-collection-recreate&quot;</span> </span>
    <span style="color: #009900;">  <span style="color: #000066;">value</span>=<span style="color: #ff0000;">&quot;org.jboss.envers.event.VersionsEventListener&quot;</span> <span style="color: #000000; font-weight: bold;">/&gt;</span></span></pre></td></tr></table><p class="theCode" style="display:none;">&lt;property name=&quot;hibernate.ejb.event.post-insert&quot;
      value=&quot;org.jboss.envers.event.VersionsEventListener&quot; /&gt;
    &lt;property name=&quot;hibernate.ejb.event.post-update&quot; 
      value=&quot;org.jboss.envers.event.VersionsEventListener&quot; /&gt;
    &lt;property name=&quot;hibernate.ejb.event.post-delete&quot; 
      value=&quot;org.jboss.envers.event.VersionsEventListener&quot; /&gt;
    &lt;property name=&quot;hibernate.ejb.event.pre-collection-update&quot;
      value=&quot;org.jboss.envers.event.VersionsEventListener&quot; /&gt;
    &lt;property name=&quot;hibernate.ejb.event.pre-collection-remove&quot; 
      value=&quot;org.jboss.envers.event.VersionsEventListener&quot; /&gt;
    &lt;property name=&quot;hibernate.ejb.event.post-collection-recreate&quot; 
      value=&quot;org.jboss.envers.event.VersionsEventListener&quot; /&gt;</p></div>
    ";i:2;s:5608:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    6
    7
    8
    9
    10
    11
    12
    13
    14
    15
    16
    17
    18
    </pre></td><td class="code"><pre class="xml" style="font-family:monospace;"><span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;event</span> <span style="color: #000066;">type</span>=<span style="color: #ff0000;">&quot;post-insert&quot;</span><span style="color: #000000; font-weight: bold;">&gt;</span></span>
    <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;listener</span> <span style="color: #000066;">class</span>=<span style="color: #ff0000;">&quot;org.jboss.envers.event.VersionsEventListener&quot;</span><span style="color: #000000; font-weight: bold;">/&gt;</span></span>
    <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/event<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
    <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;event</span> <span style="color: #000066;">type</span>=<span style="color: #ff0000;">&quot;post-update&quot;</span><span style="color: #000000; font-weight: bold;">&gt;</span></span>
    <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;listener</span> <span style="color: #000066;">class</span>=<span style="color: #ff0000;">&quot;org.jboss.envers.event.VersionsEventListener&quot;</span><span style="color: #000000; font-weight: bold;">/&gt;</span></span>
    <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/event<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
    <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;event</span> <span style="color: #000066;">type</span>=<span style="color: #ff0000;">&quot;post-delete&quot;</span><span style="color: #000000; font-weight: bold;">&gt;</span></span>
    <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;listener</span> <span style="color: #000066;">class</span>=<span style="color: #ff0000;">&quot;org.jboss.envers.event.VersionsEventListener&quot;</span><span style="color: #000000; font-weight: bold;">/&gt;</span></span>
    <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/event<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
    <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;event</span> <span style="color: #000066;">type</span>=<span style="color: #ff0000;">&quot;pre-collection-update&quot;</span><span style="color: #000000; font-weight: bold;">&gt;</span></span>
    <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;listener</span> <span style="color: #000066;">class</span>=<span style="color: #ff0000;">&quot;org.jboss.envers.event.VersionsEventListener&quot;</span><span style="color: #000000; font-weight: bold;">/&gt;</span></span>
    <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/event<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
    <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;event</span> <span style="color: #000066;">type</span>=<span style="color: #ff0000;">&quot;pre-collection-remove&quot;</span><span style="color: #000000; font-weight: bold;">&gt;</span></span>
    <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;listener</span> <span style="color: #000066;">class</span>=<span style="color: #ff0000;">&quot;org.jboss.envers.event.VersionsEventListener&quot;</span><span style="color: #000000; font-weight: bold;">/&gt;</span></span>
    <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/event<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
    <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;event</span> <span style="color: #000066;">type</span>=<span style="color: #ff0000;">&quot;post-collection-recreate&quot;</span><span style="color: #000000; font-weight: bold;">&gt;</span></span>
    <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;listener</span> <span style="color: #000066;">class</span>=<span style="color: #ff0000;">&quot;org.jboss.envers.event.VersionsEventListener&quot;</span><span style="color: #000000; font-weight: bold;">/&gt;</span></span>
    <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/event<span style="color: #000000; font-weight: bold;">&gt;</span></span></span></pre></td></tr></table><p class="theCode" style="display:none;">&lt;event type=&quot;post-insert&quot;&gt;
    &lt;listener class=&quot;org.jboss.envers.event.VersionsEventListener&quot;/&gt;
    &lt;/event&gt;
    &lt;event type=&quot;post-update&quot;&gt;
    &lt;listener class=&quot;org.jboss.envers.event.VersionsEventListener&quot;/&gt;
    &lt;/event&gt;
    &lt;event type=&quot;post-delete&quot;&gt;
    &lt;listener class=&quot;org.jboss.envers.event.VersionsEventListener&quot;/&gt;
    &lt;/event&gt;
    &lt;event type=&quot;pre-collection-update&quot;&gt;
    &lt;listener class=&quot;org.jboss.envers.event.VersionsEventListener&quot;/&gt;
    &lt;/event&gt;
    &lt;event type=&quot;pre-collection-remove&quot;&gt;
    &lt;listener class=&quot;org.jboss.envers.event.VersionsEventListener&quot;/&gt;
    &lt;/event&gt;
    &lt;event type=&quot;post-collection-recreate&quot;&gt;
    &lt;listener class=&quot;org.jboss.envers.event.VersionsEventListener&quot;/&gt;
    &lt;/event&gt;</p></div>
    ";}
epcl_post:
  - 'a:1:{s:13:"views_counter";i:1;}'
views_counter:
  - 1
tags:
  - envers
  - java
  - hibernate

---
Hello,

after a vacation break, [Envers][1] is back with a [1.1.0.beta1][2] release. You can find the release notes [here][3].

There&#8217;s quite a lot of changes and improvements in this release. Firstly, Envers now only works with Hibernate 3.2.6 or Hibernate 3.3.0. That is because support (not yet complete) for persistent collections is added &#8211; and that requires collection event listeners, which were introduced in Hibernate 3.2.6.

However, thanks to the new listeners, you can now version many-to-many relations and unidirectional one-to-many relations (except for the mapping `@OneToMany+@JoinColumn`, which will be supported later).

Other improvements include:

  * a configuration option (`org.jboss.envers.unversionedOptimisticLockingField`) to automatically unversion fields in entities annotated with @Version, contributed by Nicolás Doroskevich
  * an `@Unversioned` annotation, which lets you exclude some fields from versioning, while keeping the entity-wide @Versioned annotation, contributed by Sebastian Komander
  * a new implementation of the query system, which provides room for future improvements
  * the revisions-of-entity query returns now by default a list of triples: the entity that was modified, revision entity containing all revision data and the revision type (see the [beta javadoc][4] for details); all this is read using only one database query; there are also slight API changes in version queries
  * for Maven2 users, Envers is now deployed to JBoss&#8217;s repository, <http://repository.jboss.org/maven2/org/jboss/envers/jboss-envers/>. </ul> 
    I&#8217;d like to welcome and thank two new contributors: Nicolás and Sebastian. Don&#8217;t hesitate to follow their way :). As always, all forum users were very helpful in finding bugs and suggesting new ideas.
    
    Finally, some updates on configuration. The Envers home page doesn&#8217;t yet reflect this (as it&#8217;s still a beta release). To use the new collection listeners, be sure that your `persistence.xml` includes the following:
    
    ```xml
<property name="hibernate.ejb.event.post-insert"
  value="org.jboss.envers.event.VersionsEventListener" />
<property name="hibernate.ejb.event.post-update" 
  value="org.jboss.envers.event.VersionsEventListener" />
<property name="hibernate.ejb.event.post-delete" 
  value="org.jboss.envers.event.VersionsEventListener" />
<property name="hibernate.ejb.event.pre-collection-update"
  value="org.jboss.envers.event.VersionsEventListener" />
<property name="hibernate.ejb.event.pre-collection-remove" 
  value="org.jboss.envers.event.VersionsEventListener" />
<property name="hibernate.ejb.event.post-collection-recreate" 
  value="org.jboss.envers.event.VersionsEventListener" />
```
    
    Or if you are using Envers directly with Hibernate:
    
    ```xml
<event type="post-insert">
<listener class="org.jboss.envers.event.VersionsEventListener"/>
</event>
<event type="post-update">
<listener class="org.jboss.envers.event.VersionsEventListener"/>
</event>
<event type="post-delete">
<listener class="org.jboss.envers.event.VersionsEventListener"/>
</event>
<event type="pre-collection-update">
<listener class="org.jboss.envers.event.VersionsEventListener"/>
</event>
<event type="pre-collection-remove">
<listener class="org.jboss.envers.event.VersionsEventListener"/>
</event>
<event type="post-collection-recreate">
<listener class="org.jboss.envers.event.VersionsEventListener"/>
</event>
```
    
    If you are using Envers with JBoss AS 4.2.x, you&#8217;ll have either to bundle the hibernate jars with your application, or upgrade hibernate in the `lib` directory of the AS, as the versioned used there is 3.2.4.SP1.
    
    As always, waiting on the [forums][5] for your opinions and for bug reports in [JIRA][6].
    
    Adam

 [1]: http://www.jboss.org/envers/
 [2]: http://www.jboss.org/envers/downloads/
 [3]: https://jira.jboss.org/jira/secure/ReleaseNote.jspa?version=12312514&styleName=Text&projectId=12310660&Create=Create
 [4]: http://www.jboss.org/files/envers/api-beta/index.html
 [5]: http://www.jboss.com/index.html?module=bb&op=viewforum&f=283
 [6]: http://jira.jboss.com/jira/browse/ENVERS
