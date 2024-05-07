---
title: New features in Typestate Checker
author: Adam Warski
type: post
date: 2009-04-14T18:51:35+00:00
url: /blog/2009/04/new-features-in-typestate-checker/
dsq_thread_id:
  - 1095511693
wp-syntax-cache-content:
  - |
    a:2:{i:1;s:1343:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;"><span style="color: #000000; font-weight: bold;">class</span> <span style="color: #003399;">InputStream</span> <span style="color: #000000; font-weight: bold;">implements</span> Closeable <span style="color: #009900;">&#123;</span>
       <span style="color: #000066; font-weight: bold;">int</span> read<span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span> <span style="color: #666666; font-style: italic;">/*@Open(onException=InputStreamError.class)*/</span> 
        <span style="color: #000000; font-weight: bold;">throws</span> java.<span style="color: #006633;">io</span>.<span style="color: #003399;">IOException</span><span style="color: #339933;">;</span>
       close<span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span> <span style="color: #666666; font-style: italic;">/*@Any(after=Closed.class)*/</span>
    <span style="color: #009900;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">class InputStream implements Closeable {
       int read() /*@Open(onException=InputStreamError.class)*/ 
        throws java.io.IOException;
       close() /*@Any(after=Closed.class)*/
    }</p></div>
    ";i:2;s:2949:"
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
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;"><span style="color: #000066; font-weight: bold;">int</span> test<span style="color: #009900;">&#40;</span>@Open <span style="color: #003399;">InputStream</span> stream<span style="color: #009900;">&#41;</span> <span style="color: #000000; font-weight: bold;">throws</span> <span style="color: #003399;">IOException</span> <span style="color: #009900;">&#123;</span>
      <span style="color: #000066; font-weight: bold;">int</span> ret <span style="color: #339933;">=</span> <span style="color: #cc66cc;">0</span><span style="color: #339933;">;</span>
      <span style="color: #000000; font-weight: bold;">try</span> <span style="color: #009900;">&#123;</span>
        <span style="color: #000066; font-weight: bold;">int</span> input<span style="color: #339933;">;</span>
        <span style="color: #000000; font-weight: bold;">while</span> <span style="color: #009900;">&#40;</span><span style="color: #009900;">&#40;</span>input <span style="color: #339933;">=</span> stream.<span style="color: #006633;">read</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span> <span style="color: #339933;">!=</span> <span style="color: #339933;">-</span><span style="color: #cc66cc;">1</span><span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
          ret <span style="color: #339933;">+=</span> input<span style="color: #339933;">;</span>
        <span style="color: #009900;">&#125;</span>
      <span style="color: #009900;">&#125;</span> <span style="color: #000000; font-weight: bold;">finally</span> <span style="color: #009900;">&#123;</span>
        stream.<span style="color: #006633;">close</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
      <span style="color: #009900;">&#125;</span>
    &nbsp;
      <span style="color: #666666; font-style: italic;">// An error will be reported here; the stream is closed</span>
      ret <span style="color: #339933;">+=</span> stream.<span style="color: #006633;">read</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
    &nbsp;
      <span style="color: #000000; font-weight: bold;">return</span> ret<span style="color: #339933;">;</span>
    <span style="color: #009900;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">int test(@Open InputStream stream) throws IOException {
      int ret = 0;
      try {
        int input;
        while ((input = stream.read()) != -1) {
          ret += input;
        }
      } finally {
        stream.close();
      }
    
      // An error will be reported here; the stream is closed
      ret += stream.read();
    
      return ret;
    }</p></div>
    ";}
categories:
  - java
  - static analysis

---
I&#8217;ve uploaded a new version (0.2) of the typestate checker (for an introduction, see [this blog post][1]), which contains bug fixes and much improved exception handling. The binaries, source code and installation instructions are on the [web page][2]; the source code is also on [github][3], and the binaries in the [maven repository][4]. You can use the typestate checker with the [jsr308-maven plugin][5].

A new state-annotation element: <tt>onException</tt> is supported, which specifies the state, to which an object transits, in case of an exception being thrown (from the place where the object is used: a method or constructor). For example, the specification of some of <tt>InputStream</tt>s methods could look like this:

<pre lang="java" line="1">class InputStream implements Closeable {
   int read() /*@Open(onException=InputStreamError.class)*/ 
    throws java.io.IOException;
   close() /*@Any(after=Closed.class)*/
}
</pre>

This specification says that the <tt>read</tt> method can only be invoked when the object is in the &#8220;open&#8221; state, and in case of an error, the object transits to the &#8220;inputStreamError&#8221; state; that means that the <tt>read</tt> method cannot be invoked anymore. The <tt>close</tt> method transits the object from any state (so it can be invoked both when the stream is open, or in an error), to a closed state; again, <tt>read</tt> cannot be invoked anymore.

The annotations are in comments to make the code Java5-compatible; the checkers framework will recognize them.

Thanks to such specification, and the typestate checker, we can verify at compile-time that no read operations are invoked when the stream is closed. For example, the code below won&#8217;t compile (type-check), if compiled with the typestate checker, because of the invalid use of the last <tt>read</tt> method:

<pre lang="java" line="1">int test(@Open InputStream stream) throws IOException {
  int ret = 0;
  try {
    int input;
    while ((input = stream.read()) != -1) {
      ret += input;
    }
  } finally {
    stream.close();
  }

  // An error will be reported here; the stream is closed
  ret += stream.read();

  return ret;
}
</pre>

More examples can be found in the <tt>examples</tt> directory of the source distribution (and ran with the <tt>example.sh</tt> script; on github: [example 1][6], [example 2][7])

The flow algorithm was improved to properly handle various try-catch-finally combinations, inferring the states of the objects properly when the catches are &#8220;alive&#8221; or &#8220;dead&#8221; (if they return/throw an exception or not).

Any opinions, comments, suggestions are welcome! :)

Adam

 [1]: http://www.warski.org/blog/?p=37
 [2]: http://www.warski.org/typestate.html
 [3]: http://github.com/adamw/jsr308-typestate-checker/tree/master
 [4]: http://repository.mamut.net.pl/content/repositories/releases/mamut/net/pl/typestate-checker/0.2/
 [5]: http://www.warski.org/blog/?p=58
 [6]: http://github.com/adamw/jsr308-typestate-checker/blob/80894c97ce345bf833a7df6fb9079ec66d7f0c89/example/src/checkers/typestate/ioexample/Example1.java
 [7]: http://github.com/adamw/jsr308-typestate-checker/blob/80894c97ce345bf833a7df6fb9079ec66d7f0c89/example/src/checkers/typestate/ioexample/Example2.java
