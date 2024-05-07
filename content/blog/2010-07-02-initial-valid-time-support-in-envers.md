---
title: Initial valid-time support in Envers
author: Adam Warski
type: post
date: 2010-07-02T12:12:42+00:00
url: /blog/2010/07/initial-valid-time-support-in-envers/
dsq_thread_id:
  - 1051934923
wp-syntax-cache-content:
  - |
    a:1:{i:1;s:910:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    </pre></td><td class="code"><pre class="xml" style="font-family:monospace;"><span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;property</span> <span style="color: #000066;">name</span>=<span style="color: #ff0000;">&quot;org.hibernate.envers.audit_strategy&quot;</span><span style="color: #000000; font-weight: bold;">&gt;</span></span>
       org.hibernate.envers.strategy.ValidTimeAuditStrategy
    <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/property<span style="color: #000000; font-weight: bold;">&gt;</span></span></span></pre></td></tr></table><p class="theCode" style="display:none;">&lt;property name=&quot;org.hibernate.envers.audit_strategy&quot;&gt;
       org.hibernate.envers.strategy.ValidTimeAuditStrategy
    &lt;/property&gt;</p></div>
    ";}
tags:
  - envers
  - java
  - hibernate

---
I just commited initial support for valid-time auditing in Envers, a feature that a lot of users has been (directly or indirectly) asking for. It&#8217;s joint work, as Stephanie Pau contributed a patch with a large portion of those changes &#8211; thanks!

You can try it out by checking out [Hibernate trunk][1] source code.

What is valid-time about? So far Envers only stored the revision at which a change was made. This information is enough to retrieve historical data, however the queries are quite complicated and in advanced use cases can be time-consuming. This can be improved when we store both the start and end revisions, that is information on when data was &#8220;valid&#8221;. For historic entities, both column are filled, and for &#8220;current&#8221; data, the end revision column is `null`.

Using the valid-time audit strategy, it will be possible to:

  * speed up and simplify the queries to retrieve historical data
  * implement support for queries, which traverse relations
  * implement other types of queries, like latest changes

To configure Envers to store the end-revision number, you have to specify a property in your configuration file:

<pre lang="xml" line="1">&lt;property name="org.hibernate.envers.audit_strategy">
   org.hibernate.envers.strategy.ValidTimeAuditStrategy
&lt;/property>
</pre>

Envers will then generate and additional `REVEND` column (next to the `REV` column) in every audit (`_AUD`) entity/table; however this column won&#8217;t be part of the primary key. You can change the name of the end-revision column by setting the `org.hibernate.envers.audit_strategy_valid_time_end_name` property value.

The value of the end-revision column can be calculated basing only on the original revision-changed columns, so using a couple of queries it will be possible to easily migrate existing data to the new audit strategy.

Please note that this feature is experimental, and can be changed in the future. The associated JIRA issue is [HHH-3763][2].

Adam

 [1]: http://anonsvn.jboss.org/repos/hibernate/core/trunk
 [2]: http://opensource.atlassian.com/projects/hibernate/browse/HHH-3763
