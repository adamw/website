---
title: StaticAccess detector for FindBugs
author: Adam Warski
type: blog
date: 2009-05-11T17:30:19+00:00
url: /blog/2009/05/staticaccess-detector-for-findbugs/
simplecatch-sidebarlayout:
  - default
dsq_thread_id:
  - 1051935010
wp-syntax-cache-content:
  - |
    a:1:{i:1;s:4033:"
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
    19
    20
    21
    22
    23
    24
    25
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;"><span style="color: #000000; font-weight: bold;">import</span> <span style="color: #006699;">pl.net.mamut.staticaccess.StaticIndependent</span><span style="color: #339933;">;</span>
    &nbsp;
    <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000000; font-weight: bold;">class</span> StaticIndependentExample <span style="color: #009900;">&#123;</span>
        <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000000; font-weight: bold;">static</span> <span style="color: #003399;">Integer</span> globalInt <span style="color: #339933;">=</span> <span style="color: #cc66cc;">10</span><span style="color: #339933;">;</span>
        <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000000; font-weight: bold;">static</span> <span style="color: #000000; font-weight: bold;">final</span> <span style="color: #003399;">Integer</span> CONSTANT <span style="color: #339933;">=</span> <span style="color: #cc66cc;">12</span><span style="color: #339933;">;</span>
    &nbsp;
        <span style="color: #666666; font-style: italic;">// This method is annotated as static-independent,</span>
        <span style="color: #666666; font-style: italic;">// that is, cannot rely on static (global) state</span>
        @StaticIndependent
        <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000066; font-weight: bold;">void</span> testStaticIndepdendent<span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
            <span style="color: #666666; font-style: italic;">// line 12</span>
            <span style="color: #000066; font-weight: bold;">int</span> local <span style="color: #339933;">=</span> globalInt<span style="color: #339933;">;</span> 
            <span style="color: #666666; font-style: italic;">// line 14</span>
            globalInt <span style="color: #339933;">=</span> <span style="color: #cc66cc;">11</span><span style="color: #339933;">;</span>          
    &nbsp;
            <span style="color: #666666; font-style: italic;">// line 17</span>
            <span style="color: #000066; font-weight: bold;">int</span> local2 <span style="color: #339933;">=</span> CONSTANT<span style="color: #339933;">;</span>      
        <span style="color: #009900;">&#125;</span>
    &nbsp;
        <span style="color: #666666; font-style: italic;">// Unannotated methods aren't checked</span>
        <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000066; font-weight: bold;">void</span> testStaticDependent<span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span> 
            <span style="color: #666666; font-style: italic;">// line 23</span>
            <span style="color: #000066; font-weight: bold;">int</span> local <span style="color: #339933;">=</span> globalInt<span style="color: #339933;">;</span> 
        <span style="color: #009900;">&#125;</span>
    <span style="color: #009900;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">import pl.net.mamut.staticaccess.StaticIndependent;
    
    public class StaticIndependentExample {
        public static Integer globalInt = 10;
        public static final Integer CONSTANT = 12;
    
        // This method is annotated as static-independent,
        // that is, cannot rely on static (global) state
        @StaticIndependent
        public void testStaticIndepdendent() {
            // line 12
            int local = globalInt; 
            // line 14
            globalInt = 11;          
        
            // line 17
            int local2 = CONSTANT;      
        }
        
        // Unannotated methods aren't checked
        public void testStaticDependent() { 
            // line 23
            int local = globalInt; 
        }
    }</p></div>
    ";}
tags:
  - java
  - static analysis

---
The [StaticAccess][1] detector is a [FindBugs][2] plugin, which lets you verify that methods do not rely on static (global) state, that is, that they don&#8217;t read or write static variables which aren&#8217;t constant. This can be useful for example when writing concurrent programs (which should use as little global state as possible) or &#8220;pure&#8221; functions (I&#8217;ll write about that in another post).

You can specify that a method shouldn&#8217;t rely on global state using the <tt>@StaticIndependent</tt> annotation. Such methods will be checked by the detector included in the plugin, if they don&#8217;t read or write static variables, or call non-static-independent methods.[water trampoline australia for sale][3]

For example, running FindBugs with the StaticAccess plugin on the following code:
```java
import pl.net.mamut.staticaccess.StaticIndependent;

public class StaticIndependentExample {
    public static Integer globalInt = 10;
    public static final Integer CONSTANT = 12;

    // This method is annotated as static-independent,
    // that is, cannot rely on static (global) state
    @StaticIndependent
    public void testStaticIndepdendent() {
        // line 12
        int local = globalInt; 
        // line 14
        globalInt = 11;          
    
        // line 17
        int local2 = CONSTANT;      
    }
    
    // Unannotated methods aren't checked
    public void testStaticDependent() { 
        // line 23
        int local = globalInt; 
    }
}
```

will report errors on lines 12 and 14, but won&#8217;t report errors on line 17 (accessing a constant which can&#8217;t change) or line 23 (the method isn&#8217;t annotated with <tt>@StaticIndependent</tt>).

You can annotate all methods in a class or package by default as static independent using FindBugs&#8217;s <tt>@DefaultAnnotationForMethods</tt> meta-annotation, and later override that for selected methods using <tt>@StaticDependent</tt>.

All methods from the <tt>java.lang</tt> package are static-independent by default, and only <tt>String</tt> and primitive types wrapper classes are treated as constants when declared as <tt>static final</tt> fields. This list should be certainly expanded, so if you&#8217;ve got good candidates, write or send a patch!

For information on installation, downloads and usage see the [webpage][1]. You can find the jars there; they are also available in [maven][4], and the source code on [github][5].

Have fun!  
Adam

 [1]: http://www.warski.org/staticaccess.html
 [2]: http://findbugs.sourceforge.net/
 [3]: http://www.jumpingcastleonsale.com.au/inflatable-water-games-c-16.html
 [4]: http://repository.mamut.net.pl/content/repositories/releases/pl/net/mamut/staticaccess-detector/0.1/
 [5]: https://github.com/adamw/staticaccess-detector/tree/master
