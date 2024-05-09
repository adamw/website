---
title: Value-to-variable binding “let” tag for JSF, Facelets and Seam
author: Adam Warski
type: blog
date: 2008-04-28T10:54:56+00:00
url: /blog/2008/04/value-to-variable-binding-let-tag-for-jsf-facelets-and-seam/
disqus_identifier: 1051934278
wp-syntax-cache-content:
  - |
    a:4:{i:1;s:984:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    </pre></td><td class="code"><pre class="xml" style="font-family:monospace;"><span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;mamut:let</span> <span style="color: #000066;">var</span>=<span style="color: #ff0000;">&quot;result&quot;</span> <span style="color: #000066;">value</span>=<span style="color: #ff0000;">&quot;#{anyElExpression}&quot;</span><span style="color: #000000; font-weight: bold;">&gt;</span></span>
       Now you can use #{result} as a normal variable.
    <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/mamut<span style="color: #000000; font-weight: bold;">&gt;</span></span></span></pre></td></tr></table><p class="theCode" style="display:none;">&lt;mamut:let var=&quot;result&quot; value=&quot;#{anyElExpression}&quot;&gt;
       Now you can use #{result} as a normal variable.
    &lt;/mamut&gt;</p></div>
    ";i:2;s:2318:"
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
    </pre></td><td class="code"><pre class="xml" style="font-family:monospace;"><span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;ui:repeat</span> <span style="color: #000066;">var</span>=<span style="color: #ff0000;">&quot;group&quot;</span> <span style="color: #000066;">value</span>=<span style="color: #ff0000;">&quot;#{groupsService.all}&quot;</span><span style="color: #000000; font-weight: bold;">&gt;</span></span>
       <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;s:fragment</span> <span style="color: #000066;">rendered</span>=</span>
    <span style="color: #009900;">      <span style="color: #ff0000;">&quot;#{groupsService.accFeeds(group).size() &gt;</span></span> 0}&quot;&gt;
          (some header)
          <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;ui:repeat</span> <span style="color: #000066;">var</span>=<span style="color: #ff0000;">&quot;feed&quot;</span></span>
    <span style="color: #009900;">         <span style="color: #000066;">value</span>=<span style="color: #ff0000;">&quot;#{groupsService.accFeeds(group)}&quot;</span><span style="color: #000000; font-weight: bold;">&gt;</span></span>
             (...)
          <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/ui:repeat<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
       <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/s:fragment<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
    <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;ui:repeat<span style="color: #000000; font-weight: bold;">&gt;</span></span></span></pre></td></tr></table><p class="theCode" style="display:none;">&lt;ui:repeat var=&quot;group&quot; value=&quot;#{groupsService.all}&quot;&gt;
       &lt;s:fragment rendered=
          &quot;#{groupsService.accFeeds(group).size() &gt; 0}&quot;&gt;
          (some header)
          &lt;ui:repeat var=&quot;feed&quot;
             value=&quot;#{groupsService.accFeeds(group)}&quot;&gt;
             (...)
          &lt;/ui:repeat&gt;
       &lt;/s:fragment&gt;
    &lt;ui:repeat&gt;</p></div>
    ";i:3;s:2909:"
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
    </pre></td><td class="code"><pre class="xml" style="font-family:monospace;"><span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;ui:repeat</span> <span style="color: #000066;">var</span>=<span style="color: #ff0000;">&quot;group&quot;</span> <span style="color: #000066;">value</span>=<span style="color: #ff0000;">&quot;#{groupsService.all}&quot;</span><span style="color: #000000; font-weight: bold;">&gt;</span></span>
       <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;mamut:let</span> <span style="color: #000066;">var</span>=<span style="color: #ff0000;">&quot;acceptedFeeds&quot;</span></span>
    <span style="color: #009900;">      <span style="color: #000066;">value</span>=<span style="color: #ff0000;">&quot;#{groupsService.accFeeds(group)&quot;</span><span style="color: #000000; font-weight: bold;">&gt;</span></span>
          <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;s:fragment</span> <span style="color: #000066;">rendered</span>=<span style="color: #ff0000;">&quot;#{accFeeds.size() &gt;</span></span> 0}&quot;&gt;
             (some header)
             <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;ui:repeat</span> <span style="color: #000066;">var</span>=<span style="color: #ff0000;">&quot;feed&quot;</span> <span style="color: #000066;">value</span>=<span style="color: #ff0000;">&quot;#{accFeeds}&quot;</span><span style="color: #000000; font-weight: bold;">&gt;</span></span>
                (...)
             <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/ui:repeat<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
          <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/s:fragment<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
       <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/mamut:let<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
    <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;ui:repeat<span style="color: #000000; font-weight: bold;">&gt;</span></span></span></pre></td></tr></table><p class="theCode" style="display:none;">&lt;ui:repeat var=&quot;group&quot; value=&quot;#{groupsService.all}&quot;&gt;
       &lt;mamut:let var=&quot;acceptedFeeds&quot;
          value=&quot;#{groupsService.accFeeds(group)&quot;&gt;
          &lt;s:fragment rendered=&quot;#{accFeeds.size() &gt; 0}&quot;&gt;
             (some header)
             &lt;ui:repeat var=&quot;feed&quot; value=&quot;#{accFeeds}&quot;&gt;
                (...)
             &lt;/ui:repeat&gt;
          &lt;/s:fragment&gt;
       &lt;/mamut:let&gt;
    &lt;ui:repeat&gt;</p></div>
    ";i:4;s:340:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    </pre></td><td class="code"><pre class="xml" style="font-family:monospace;">xmlns:mamut=&quot;http://mamut.net.pl/jsf&quot;</pre></td></tr></table><p class="theCode" style="display:none;">xmlns:mamut=&quot;http://mamut.net.pl/jsf&quot;</p></div>
    ";}
tags:
  - java
  - jsf

---
The &#8220;let&#8221; tag enables you to bind the value of any expression to a variable and later reuse it, without recalculating the value. The concept comes of course from functional programming. It is especially useful with Seam&#8217;s [extended EL][1].

The usage is really simple:
```xml
<mamut:let var="result" value="#{anyElExpression}">
   Now you can use #{result} as a normal variable.
</mamut>
```

I was looking at other tag libraries but couldn&#8217;t find anything similar. Or maybe there is?

The use case that motivated me to write this comes from the [JBoss.ORG feeds][2] application. There, in several places I have code similar to the following one:
```xml
<ui:repeat var="group" value="#{groupsService.all}">
   <s:fragment rendered=
      "#{groupsService.accFeeds(group).size() > 0}">
      (some header)
      <ui:repeat var="feed"
         value="#{groupsService.accFeeds(group)}">
         (...)
      </ui:repeat>
   </s:fragment>
<ui:repeat>
```

Of course calling `groupsService.acceptedFeeds(group)` twice is unnecessarily inefficient. I could move this call to a backing bean, but doing so would only cause me to write some really simple code many times. The version using the let tag calls the method only once:
```xml
<ui:repeat var="group" value="#{groupsService.all}">
   <mamut:let var="acceptedFeeds"
      value="#{groupsService.accFeeds(group)">
      <s:fragment rendered="#{accFeeds.size() > 0}">
         (some header)
         <ui:repeat var="feed" value="#{accFeeds}">
            (...)
         </ui:repeat>
      </s:fragment>
   </mamut:let>
<ui:repeat>
```

If somebody finds this useful, I&#8217;ve put the jar [here][3]. To use it, just bundle the jar with your application. The namespace for the tag is the following:
```xml
xmlns:mamut="http://mamut.net.pl/jsf"
```

Adam

 [1]: http://docs.jboss.com/seam/latest/reference/en/html/elenhancements.html
 [2]: http://www.jboss.org/feeds/
 [3]: http://www.jboss.org/shotoku/downloads/other/
