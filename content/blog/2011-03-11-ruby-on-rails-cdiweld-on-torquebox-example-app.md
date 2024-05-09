---
title: Ruby on Rails + CDI/Weld on Torquebox example app
author: Adam Warski
type: blog
date: 2011-03-11T12:04:30+00:00
url: /blog/2011/03/ruby-on-rails-cdiweld-on-torquebox-example-app/
disqus_identifier:
  - 1051935756
wp-syntax-cache-content:
  - |
    a:1:{i:1;s:1135:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    6
    7
    </pre></td><td class="code"><pre class="ruby" style="font-family:monospace;"><span style="color:#9966CC; font-weight:bold;">class</span> MyController <span style="color:#006600; font-weight:bold;">&lt;</span> ApplicationController    
      include_class <span style="color:#996600;">&quot;pl.softwaremill.demo.HelloWorld&quot;</span>
    &nbsp;
      <span style="color:#9966CC; font-weight:bold;">def</span> index
        <span style="color:#0066ff; font-weight:bold;">@data</span> = inject<span style="color:#006600; font-weight:bold;">&#40;</span>HelloWorld<span style="color:#006600; font-weight:bold;">&#41;</span>.<span style="color:#9900CC;">compute_data</span>
      <span style="color:#9966CC; font-weight:bold;">end</span>
    <span style="color:#9966CC; font-weight:bold;">end</span></pre></td></tr></table><p class="theCode" style="display:none;">class MyController &lt; ApplicationController    
      include_class &quot;pl.softwaremill.demo.HelloWorld&quot;
    
      def index
        @data = inject(HelloWorld).compute_data
      end
    end</p></div>
    ";}
tags:
  - jee
  - dependency injection
  - java
  - ruby

---
For almost a year we&#8217;ve been successfully using [Torquebox][1] together with CDI/Weld as a base for two of our services: [JBison][2] and [Circular][3]. As we&#8217;ll be doing [some presentations][4] together with [Tomek Szymański][5] we&#8217;ve created a small application showing our setup and the CDI<->RoR integration.

The code is available on [GitHub: https://github.com/softwaremill/trqbox-demo][6] together with a readme explaining the modules, how to run the application and how to re-create it. Hope it will be helpful.

The application consists of two main modules: the backend and the frontend.

The backend is written using Java and [CDI/Weld][7] for dependency injection and management. Here we can use the whole power of CDI, with extensions, interceptors, producers etc. Also nothing stops us from using other JEE components (e.g. EJBs, JAX-RS, servlets), as Torquebox is built on JBoss AS 6, and the deployed application behaves more or less like a `.war`.

The frontend, on the other hand, is done purely in [Ruby on Rails][8] (plus some Javascript). For frontend development static typing is more often an obstacle than help; also, the fact that all changes are immediately visible (no redeploying etc.) is a great gain. Moreover (at least for me) doing rich ajax apps is **much** easier in RoR than when using e.g. JSF. 

To bind the frontend and the backend, with the help from some infrastructure we can &#8220;inject&#8221; CDI beans into Rails controllers or an arbitrary Ruby class (which runs on JRuby, same JVM), e.g.:

<pre lang="ruby" line="1" escaped="true">class MyController &lt; ApplicationController    
  include_class "pl.softwaremill.demo.HelloWorld"

  def index
    @data = inject(HelloWorld).compute_data
  end
end
</pre>

So we can easily call our business logic to obtain data that should be displayed or trigger an action. Currently this works as a service locator, but thanks to some feedback from the [Poznan JUG][9] presentation we had this week I&#8217;ve got some ideas on improving it.

Our normal cycle of development is the following: first we develop the backend, using unit tests and [Arquillian][10] (integration tests) to verify that the beans behave as they should (if, for example, there are some new entities or queries, we test them using [DB test][11]). When this is done, we can deploy our new beans and proceed with writing the frontend. 

This results in pretty fast development and little time spent waiting for the AS to boot. Plus the pleasure of using something new and unconventional ;).

Many thanks to [Bob McWhirter][12] and the whole Torquebox team as well as to [Ales Justin][13] for helping to solve some MC-related problems.

Adam

 [1]: http://torquebox.org/
 [2]: https://www.jbison.com
 [3]: https://www.circulardms.com
 [4]: http://softwaremill.pl/blog/?p=397&lang=en
 [5]: http://twitter.com/#!/szimano
 [6]: https://github.com/softwaremill/trqbox-demo
 [7]: http://seamframework.org/Weld
 [8]: http://rubyonrails.org/
 [9]: http://www.jug.poznan.pl/
 [10]: http://www.jboss.org/arquillian
 [11]: http://www.warski.org/blog/?p=325
 [12]: http://twitter.com/#!/bobmcwhirter
 [13]: http://in.relation.to/Bloggers/Ales
