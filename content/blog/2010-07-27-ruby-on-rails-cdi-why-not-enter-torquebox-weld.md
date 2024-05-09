---
title: Ruby on Rails + CDI? Why not! Enter TorqueBox + Weld
author: Adam Warski
type: blog
date: 2010-07-27T12:07:33+00:00
url: /blog/2010/07/ruby-on-rails-cdi-why-not-enter-torquebox-weld/
disqus_identifier:
  - 1051935237
wp-syntax-cache-content:
  - |
    a:2:{i:1;s:1629:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    6
    </pre></td><td class="code"><pre class="xml" style="font-family:monospace;"><span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;classloading</span> <span style="color: #000066;">xmlns</span>=<span style="color: #ff0000;">&quot;urn:jboss:classloading:1.0&quot;</span></span>
    <span style="color: #009900;">              <span style="color: #000066;">domain</span>=<span style="color: #ff0000;">&quot;DefaultDomain&quot;</span></span>
    <span style="color: #009900;">              <span style="color: #000066;">top-level-classloader</span>=<span style="color: #ff0000;">&quot;true&quot;</span></span>
    <span style="color: #009900;">              <span style="color: #000066;">export-all</span>=<span style="color: #ff0000;">&quot;NON_EMPTY&quot;</span></span>
    <span style="color: #009900;">              <span style="color: #000066;">import-all</span>=<span style="color: #ff0000;">&quot;true&quot;</span><span style="color: #000000; font-weight: bold;">&gt;</span></span>
    <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/classloading<span style="color: #000000; font-weight: bold;">&gt;</span></span></span></pre></td></tr></table><p class="theCode" style="display:none;">&lt;classloading xmlns=&quot;urn:jboss:classloading:1.0&quot;
                  domain=&quot;DefaultDomain&quot;
                  top-level-classloader=&quot;true&quot;
                  export-all=&quot;NON_EMPTY&quot;
                  import-all=&quot;true&quot;&gt;
    &lt;/classloading&gt;</p></div>
    ";i:2;s:1480:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    </pre></td><td class="code"><pre class="xml" style="font-family:monospace;"><span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;web-app<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
        <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;listener<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
            <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;listener-class<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>org.jboss.weld.servlet.WeldListener<span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/listener-class<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
        <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/listener<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
    <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/web-app<span style="color: #000000; font-weight: bold;">&gt;</span></span></span></pre></td></tr></table><p class="theCode" style="display:none;">&lt;web-app&gt;
        &lt;listener&gt;
            &lt;listener-class&gt;org.jboss.weld.servlet.WeldListener&lt;/listener-class&gt;
        &lt;/listener&gt;
    &lt;/web-app&gt;</p></div>
    ";}
tags:
  - jee
  - dependency injection
  - java
  - jsf
  - ruby

---
I guess many people are often &#8220;unsatisfied&#8221; with how JSF works and how much time it sometimes takes to do a simple thing. That&#8217;s why [we][1] are trying out a new combination: RoR for the frontend and CDI for the backend. How?

Deploying RoR applications to JBoss AS is really easy thanks to the [TorqueBox][2] project. You just deploy a `.yml` file using the provided `rake` tasks and you can develop the application &#8220;live&#8221; &#8211; no redeploys, instant changes, and so on.

Using RoR as a frontend to a CDI/Weld based application requries two more steps, so that RoR can see the business logic classes and share the same http session with CDI (so it&#8217;s possible to access `@SessionScoped` beans from RoR and CDI code). 

First you need to deploy your application in the `DefaultDomain` (at least until [TORQUE-85][3] is fixed). To do this, add a `jboss-classloading.xml` file to the `META-INF` directory with this content:
```xml
<classloading xmlns="urn:jboss:classloading:1.0"
              domain="DefaultDomain"
              top-level-classloader="true"
              export-all="NON_EMPTY"
              import-all="true">
</classloading>
```

Secondly, you need to add a filter to RoR&#8217;s web application, so that Weld and RoR share the same session. Just edit `config/web.xml` in your RoR application (the magic in the RoR deployer will add it to the virtual .war deployment it creates) and add the following:
```xml
<web-app>
    <listener>
        <listener-class>org.jboss.weld.servlet.WeldListener</listener-class>
    </listener>
</web-app>
```

Now RoR and CDI share the same session (so you can use `@SessionScoped` beans etc, probably also `@ConversationScoped`, but I haven&#8217;t tried that). You can lookup CDI beans from RoR code using e.g. the [BeanInject class from cdi-ext][4], or just by writing a very simple utility method which lookups the `BeanManager`.

Adam

 [1]: http://www.softwaremill.eu
 [2]: http://www.torquebox.com/
 [3]: https://jira.jboss.org/browse/TORQUE-85
 [4]: http://github.com/adamw/cdiext
