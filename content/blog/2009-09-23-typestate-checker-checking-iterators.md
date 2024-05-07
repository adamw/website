---
title: Typestate checker – checking iterators
author: Adam Warski
type: blog
date: 2009-09-23T17:02:54+00:00
url: /blog/2009/09/typestate-checker-checking-iterators/
dsq_thread_id:
  - 1081320797
wp-syntax-cache-content:
  - |
    a:3:{i:1;s:2154:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    6
    7
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;"><span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000000; font-weight: bold;">interface</span> Iterator<span style="color: #339933;">&lt;</span>E<span style="color: #339933;">&gt;</span> <span style="color: #009900;">&#123;</span>
      <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000000; font-weight: bold;">abstract</span> <span style="color: #000066; font-weight: bold;">boolean</span> hasNext<span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span> 
        @CheckHasNext<span style="color: #009900;">&#40;</span>afterTrue<span style="color: #339933;">=</span>ReadNext.<span style="color: #000000; font-weight: bold;">class</span><span style="color: #009900;">&#41;</span> 
        @ReadNext<span style="color: #339933;">;</span>
      <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000000; font-weight: bold;">abstract</span> E next<span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span> @ReadNext<span style="color: #009900;">&#40;</span>after<span style="color: #339933;">=</span>CheckHasNext.<span style="color: #000000; font-weight: bold;">class</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
      <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000000; font-weight: bold;">abstract</span> <span style="color: #000066; font-weight: bold;">void</span> remove<span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
    <span style="color: #009900;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">public interface Iterator&lt;E&gt; {
      public abstract boolean hasNext() 
        @CheckHasNext(afterTrue=ReadNext.class) 
        @ReadNext;
      public abstract E next() @ReadNext(after=CheckHasNext.class);
      public abstract void remove();
    }</p></div>
    ";i:2;s:1159:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;">  <span style="color: #003399;">Iterator</span> iter <span style="color: #339933;">=</span> collection.<span style="color: #006633;">iterator</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
      <span style="color: #000000; font-weight: bold;">while</span> <span style="color: #009900;">&#40;</span>iter.<span style="color: #006633;">hasNext</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
        iter.<span style="color: #006633;">next</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
      <span style="color: #009900;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">  Iterator iter = collection.iterator();
      while (iter.hasNext()) {
        iter.next();
      }</p></div>
    ";i:3;s:1475:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;">  <span style="color: #003399;">Iterator</span> iter <span style="color: #339933;">=</span> collection.<span style="color: #006633;">iterator</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
      <span style="color: #000000; font-weight: bold;">while</span> <span style="color: #009900;">&#40;</span>iter.<span style="color: #006633;">hasNext</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
        iter.<span style="color: #006633;">next</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
      <span style="color: #009900;">&#125;</span>
      iter.<span style="color: #006633;">next</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span> <span style="color: #666666; font-style: italic;">// error: iter is in a wrong state</span></pre></td></tr></table><p class="theCode" style="display:none;">  Iterator iter = collection.iterator();
      while (iter.hasNext()) {
        iter.next();
      }
      iter.next(); // error: iter is in a wrong state</p></div>
    ";}
tags:
  - java
  - static analysis

---
In the new 0.2.2 release of the [typestate checker][1] (an extension to the [JSR308 checkers framework][2]), you can now specify a state, to which an object transits, if a method returns true or false using the <tt>afterTrue</tt> and <tt>afterFalse</tt> elements in state annotations.

A good use-case and example is an <tt>Iterator</tt>. As you probably know, before calling <tt>next()</tt>, it&#8217;s good to check if an element is available using <tt>hasNext()</tt>. So we can distinguish two &#8220;states&#8221; of an iterator: <tt>CheckHasNext</tt> &#8211; which means that it is unknown if an element is available or not and <tt>ReadNext</tt>, in which it is safe to call <tt>next()</tt>.

After calling <tt>hasNext()</tt>, the iterator should transit to the <tt>ReadNext</tt> state, but only if the method returns true. Also, after calling <tt>next()</tt>, the iterator should always transit to the <tt>CheckHasNext</tt> state. Using typestate annotations, we can write that specification on a stub of the <tt>Iterator</tt> interface:
```java
public interface Iterator<E> {
  public abstract boolean hasNext() 
    @CheckHasNext(afterTrue=ReadNext.class) 
    @ReadNext;
  public abstract E next() @ReadNext(after=CheckHasNext.class);
  public abstract void remove();
}
```

Here I am using annotations on the receiver of the method, a new place where you can put annotations introduced with JSR308.

You can download the definitions of the stubs and the states in an easy-to-run example [here][3]. All you need to do is edit the <tt>example.sh</tt> file to specify the path to the JSR308 distribution, which you can download from its [homepage][1]. There are several example source code files which you can typestate-check. It is also quite easy to typestate check your own files &#8211; just change the <tt>SOURCES</tt> parameter of <tt>javac</tt>.

For example, this snippet of code checks without errors:
```java
Iterator iter = collection.iterator();
  while (iter.hasNext()) {
    iter.next();
  }
```

While this throws an error on line 5:
```java
Iterator iter = collection.iterator();
  while (iter.hasNext()) {
    iter.next();
  }
  iter.next(); // error: iter is in a wrong state
```

You can find the newest release available for download on the checker&#8217;s [homepage][2], as well as in a [maven2 repository][4]. The source code is available on [github][5].

Have fun!  
Adam

 [1]: http://www.warski.org/typestate.html
 [2]: http://types.cs.washington.edu/jsr308/
 [3]: http://www.warski.org/example.zip
 [4]: http://repository.mamut.net.pl/content/repositories/releases/mamut/net/pl/typestate-checker/0.2.2/
 [5]: http://github.com/adamw/jsr308-typestate-checker/tree/master
