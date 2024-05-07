---
title: New features in cdi-ext
author: Adam Warski
type: post
date: 2010-08-02T18:28:15+00:00
url: /blog/2010/08/new-features-in-cdi-ext/
dsq_thread_id:
  - 1059349502
wp-syntax-cache-content:
  - |
    a:1:{i:1;s:1396:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;"><span style="color: #000000; font-weight: bold;">private</span> <span style="color: #000000; font-weight: bold;">final</span> Page adminPage <span style="color: #339933;">=</span> <span style="color: #000000; font-weight: bold;">new</span> ViewIdPageBuilder<span style="color: #009900;">&#40;</span><span style="color: #0000ff;">&quot;/admin.xhtml&quot;</span><span style="color: #009900;">&#41;</span>
        .<span style="color: #006633;">setRequiresLogin</span><span style="color: #009900;">&#40;</span><span style="color: #000066; font-weight: bold;">true</span><span style="color: #009900;">&#41;</span>
        .<span style="color: #006633;">setSecurityEL</span><span style="color: #009900;">&#40;</span><span style="color: #0000ff;">&quot;#{currentUser.isAdmin)&quot;</span><span style="color: #009900;">&#41;</span>.<span style="color: #006633;">b</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span></pre></td></tr></table><p class="theCode" style="display:none;">private final Page adminPage = new ViewIdPageBuilder(&quot;/admin.xhtml&quot;)
        .setRequiresLogin(true)
        .setSecurityEL(&quot;#{currentUser.isAdmin)&quot;).b();</p></div>
    ";}
tags:
  - jee
  - jsf

---
[Tomek Szymański][1] just commited two new features to [cdi-ext][2].

The first is the ability to secure JSF pages when using the Nav component to handle navigation (more on it [here][3]). If you want to make a page accessible only if a certain EL expression is true, when defining a page, you can write:

<pre lang="java" line="1">private final Page adminPage = new ViewIdPageBuilder("/admin.xhtml")
    .setRequiresLogin(true)
    .setSecurityEL("#{currentUser.isAdmin)").b();
</pre>

If the condition is not met, the user will get 403 Forbidden. Also, Tomek extended handling of `setRequiresLogin(true)`, so that if a user is redirected to the login page, because he was not logged in and the page is secured, the page which the user tried to access is stored and after logging in, he is redirected back to the original page.

The second is the ability to specify the transaction timeout using a `@TransactionTimeout(timeout = 5)` annotation on a method.

Moreover, we have moved our repository infrastructure to Nexus. The new address where cdi-ext artifacts are deployed is <http://tools.softwaremill.pl/nexus/content/repositories/snapshots>.

Adam

 [1]: http://twitter.com/szimano
 [2]: http://github.com/adamw/cdiext
 [3]: http://www.warski.org/blog/?p=185
