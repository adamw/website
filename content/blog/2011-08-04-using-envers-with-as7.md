---
title: Using Envers with AS7
author: Adam Warski
type: blog
date: 2011-08-04T05:42:00+00:00
url: /blog/2011/08/using-envers-with-as7/
disqus_identifier:
  - 1051935638
wp-syntax-cache-content:
  - |
    a:2:{i:1;s:4841:"
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
    </pre></td><td class="code"><pre class="xml" style="font-family:monospace;"><span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;?xml</span> <span style="color: #000066;">version</span>=<span style="color: #ff0000;">&quot;1.0&quot;</span> <span style="color: #000066;">encoding</span>=<span style="color: #ff0000;">&quot;UTF-8&quot;</span><span style="color: #000000; font-weight: bold;">?&gt;</span></span>
    <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;module</span> <span style="color: #000066;">xmlns</span>=<span style="color: #ff0000;">&quot;urn:jboss:module:1.0&quot;</span> <span style="color: #000066;">name</span>=<span style="color: #ff0000;">&quot;org.hibernate.envers&quot;</span><span style="color: #000000; font-weight: bold;">&gt;</span></span>
      <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;resources<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
        <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;resource-root</span> <span style="color: #000066;">path</span>=<span style="color: #ff0000;">&quot;hibernate-envers-4.0.0.Beta1.jar&quot;</span><span style="color: #000000; font-weight: bold;">/&gt;</span></span>
      <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/resources<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
    &nbsp;
      <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;dependencies<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
        <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;module</span> <span style="color: #000066;">name</span>=<span style="color: #ff0000;">&quot;org.hibernate&quot;</span><span style="color: #000000; font-weight: bold;">/&gt;</span></span>
        <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;module</span> <span style="color: #000066;">name</span>=<span style="color: #ff0000;">&quot;org.jboss.logging&quot;</span><span style="color: #000000; font-weight: bold;">/&gt;</span></span>
        <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;module</span> <span style="color: #000066;">name</span>=<span style="color: #ff0000;">&quot;org.dom4j&quot;</span><span style="color: #000000; font-weight: bold;">/&gt;</span></span>
        <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;module</span> <span style="color: #000066;">name</span>=<span style="color: #ff0000;">&quot;javax.api&quot;</span><span style="color: #000000; font-weight: bold;">/&gt;</span></span>
        <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;module</span> <span style="color: #000066;">name</span>=<span style="color: #ff0000;">&quot;javax.persistence.api&quot;</span><span style="color: #000000; font-weight: bold;">/&gt;</span></span>
        <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;module</span> <span style="color: #000066;">name</span>=<span style="color: #ff0000;">&quot;javax.transaction.api&quot;</span><span style="color: #000000; font-weight: bold;">/&gt;</span></span>
        <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;module</span> <span style="color: #000066;">name</span>=<span style="color: #ff0000;">&quot;org.javassist&quot;</span><span style="color: #000000; font-weight: bold;">/&gt;</span></span>
      <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/dependencies<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
    <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/module<span style="color: #000000; font-weight: bold;">&gt;</span></span></span></pre></td></tr></table><p class="theCode" style="display:none;">&lt;?xml version=&quot;1.0&quot; encoding=&quot;UTF-8&quot;?&gt;
    &lt;module xmlns=&quot;urn:jboss:module:1.0&quot; name=&quot;org.hibernate.envers&quot;&gt;
      &lt;resources&gt;
        &lt;resource-root path=&quot;hibernate-envers-4.0.0.Beta1.jar&quot;/&gt;
      &lt;/resources&gt;
    
      &lt;dependencies&gt;
        &lt;module name=&quot;org.hibernate&quot;/&gt;
        &lt;module name=&quot;org.jboss.logging&quot;/&gt;
        &lt;module name=&quot;org.dom4j&quot;/&gt;
        &lt;module name=&quot;javax.api&quot;/&gt;
        &lt;module name=&quot;javax.persistence.api&quot;/&gt;
        &lt;module name=&quot;javax.transaction.api&quot;/&gt;
        &lt;module name=&quot;org.javassist&quot;/&gt;
      &lt;/dependencies&gt;
    &lt;/module&gt;</p></div>
    ";i:2;s:332:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    </pre></td><td class="code"><pre class="xml" style="font-family:monospace;">Dependencies: org.hibernate.envers services</pre></td></tr></table><p class="theCode" style="display:none;">Dependencies: org.hibernate.envers services</p></div>
    ";}
tags:
  - envers
  - java
  - hibernate

---
Recently I tried deploying a web application which uses [Envers][1] into [AS7][2], but unfortunately I encountered some problems (see the [forum][3] discussion). Luckily thanks to the helpful JBoss guys I&#8217;ve got it working now. Moreover, thanks to the work done by Strong Liu, you should see full Envers integration (included OOTB as a module) in AS7.1!

Until then, here&#8217;s how to use Envers in a webapp in AS7.

First, we need to create an Envers module. Create a directory: `jboss-as-7.0.0.Final/modules/org/hibernate/envers/main` and inside it, create a `module.xml` with the following content:

<pre lang="xml" line="1" escaped="true">&lt;?xml version="1.0" encoding="UTF-8"?&gt;
&lt;module xmlns="urn:jboss:module:1.0" name="org.hibernate.envers"&gt;
  &lt;resources&gt;
    &lt;resource-root path="hibernate-envers-4.0.0.Beta1.jar"/&gt;
  &lt;/resources&gt;

  &lt;dependencies&gt;
    &lt;module name="org.hibernate"/&gt;
    &lt;module name="org.jboss.logging"/&gt;
    &lt;module name="org.dom4j"/&gt;
    &lt;module name="javax.api"/&gt;
    &lt;module name="javax.persistence.api"/&gt;
    &lt;module name="javax.transaction.api"/&gt;
    &lt;module name="org.javassist"/&gt;
  &lt;/dependencies&gt;
&lt;/module&gt;
</pre>

Moreover, you&#8217;ll need to put the Envers jar inside that directory. You can [download it][4] straight from the JBoss Maven repository.

I&#8217;ve prepared a ready zip of the module, which can be downloaded [here][5]. Just unpack its contents to `jboss-as-7.0.0.Final/modules/org/hibernate`.

Secondly, you must add an entry in your manifest file (`META-INF/MANIFEST.MF`) to make the new module available to your application: 
```xml
Dependencies: org.hibernate.envers services
```

And you&#8217;re done. Enjoy! :)

Adam

 [1]: http://jboss.org/envers
 [2]: http://jboss.org/jbossas
 [3]: http://community.jboss.org/message/618785#618785
 [4]: https://repository.jboss.org/nexus/content/groups/public-jboss/org/hibernate/hibernate-envers/4.0.0.Beta1/hibernate-envers-4.0.0.Beta1.jar
 [5]: http://www.warski.org/envers-module-as7.zip
