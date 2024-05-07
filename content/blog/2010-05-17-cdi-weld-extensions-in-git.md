---
title: 'CDI & Weld Extensions in Git'
author: Adam Warski
type: blog
date: 2010-05-17T19:37:46+00:00
url: /blog/2010/05/cdi-weld-extensions-in-git/
dsq_thread_id:
  - 1062179844
wp-syntax-cache-content:
  - |
    a:2:{i:1;s:1677:"
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
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;">@SecureBinding
    @Secure<span style="color: #009900;">&#40;</span><span style="color: #0000ff;">&quot;#{loggedInUser.administrator}&quot;</span><span style="color: #009900;">&#41;</span>
    <span style="color: #000000; font-weight: bold;">public</span> @<span style="color: #000000; font-weight: bold;">interface</span> AdministratorOnly <span style="color: #009900;">&#123;</span>
    <span style="color: #009900;">&#125;</span>
    &nbsp;
    <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000000; font-weight: bold;">class</span> SecureBean <span style="color: #009900;">&#123;</span>
        @AdministratorOnly
        @Secure<span style="color: #009900;">&#40;</span><span style="color: #0000ff;">&quot;#{additionalSecurityCheck}&quot;</span><span style="color: #009900;">&#41;</span>
        <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000066; font-weight: bold;">void</span> doSecret<span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span> ... <span style="color: #009900;">&#125;</span>
    <span style="color: #009900;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">@SecureBinding
    @Secure(&quot;#{loggedInUser.administrator}&quot;)
    public @interface AdministratorOnly {
    }
    
    public class SecureBean {
        @AdministratorOnly
        @Secure(&quot;#{additionalSecurityCheck}&quot;)
        public void doSecret() { ... }
    }</p></div>
    ";i:2;s:1484:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    6
    7
    8
    9
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;">@Inject
    <span style="color: #000000; font-weight: bold;">private</span> ELEvaluator elEvaluator<span style="color: #339933;">;</span>
    &nbsp;
    <span style="color: #000066; font-weight: bold;">void</span> someMethod<span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
        <span style="color: #666666; font-style: italic;">// ...</span>
        <span style="color: #003399;">Integer</span> result <span style="color: #339933;">=</span> elEvaluator.<span style="color: #006633;">evaluate</span><span style="color: #009900;">&#40;</span>
                <span style="color: #0000ff;">&quot;#{testParam1 + 10 + testParam2}&quot;</span>, <span style="color: #003399;">Integer</span>.<span style="color: #000000; font-weight: bold;">class</span>, params<span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
        <span style="color: #666666; font-style: italic;">// ...</span>
    <span style="color: #009900;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">@Inject
    private ELEvaluator elEvaluator;
    
    void someMethod() {
        // ...
        Integer result = elEvaluator.evaluate(
                &quot;#{testParam1 + 10 + testParam2}&quot;, Integer.class, params);
        // ...
    }</p></div>
    ";}
tags:
  - jee
  - dependency injection
  - java

---
Hello,

I&#8217;ve created a new [`cdiext` project at github][1], initially with two extensions:

1. Stackable Security Interceptors, about which I blogged [here][2] and [here][3]. Example usage:

<pre lang="java" line="1">@SecureBinding
@Secure("#{loggedInUser.administrator}")
public @interface AdministratorOnly {
}

public class SecureBean {
    @AdministratorOnly
    @Secure("#{additionalSecurityCheck}")
    public void doSecret() { ... }
}
</pre>

2. Injectable ELEvaluator, which works both during a faces request and outside of one (e.g. during invocation of an MDB). Example usage:

<pre lang="java" line="1">@Inject
private ELEvaluator elEvaluator;

void someMethod() {
    // ...
    Integer result = elEvaluator.evaluate(
            "#{testParam1 + 10 + testParam2}", Integer.class, params);
    // ...
}
</pre>

Thanks to [Dan Allen][4] for [helping out][5] with this one.

There are also some tests done using [Arquillian][6] &#8211; looks like it&#8217;s going to be a great testing tool! :)

Adam

 [1]: http://github.com/adamw/cdiext
 [2]: http://www.warski.org/blog/?p=197
 [3]: http://www.warski.org/blog/?p=211
 [4]: http://in.relation.to/Bloggers/Dan
 [5]: http://community.jboss.org/thread/148045?tstart=0
 [6]: http://community.jboss.org/en/arquillian
