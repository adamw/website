---
title: Typestate checker – introduction
author: Adam Warski
type: post
date: 2008-11-17T18:17:29+00:00
url: /blog/2008/11/typestate-checker-introduction/
dsq_thread_id:
  - 1051934841
wp-syntax-cache-content:
  - |
    a:5:{i:1;s:2215:"
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
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;"><span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000000; font-weight: bold;">class</span> RemoteResourceReader <span style="color: #009900;">&#123;</span>
    <span style="color: #008000; font-style: italic; font-weight: bold;">/** Connection to the resource is being configured */</span>
    <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000000; font-weight: bold;">static</span> @<span style="color: #000000; font-weight: bold;">interface</span> Configuration <span style="color: #009900;">&#123;</span> <span style="color: #009900;">&#125;</span>
    <span style="color: #008000; font-style: italic; font-weight: bold;">/** Connection to the resource is open, data can be read */</span>
    <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000000; font-weight: bold;">static</span> @<span style="color: #000000; font-weight: bold;">interface</span> Open <span style="color: #009900;">&#123;</span> <span style="color: #009900;">&#125;</span>
    <span style="color: #008000; font-style: italic; font-weight: bold;">/** Connection to the resource is closed, data cannot be read */</span>
    <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000000; font-weight: bold;">static</span> @<span style="color: #000000; font-weight: bold;">interface</span> Closed <span style="color: #009900;">&#123;</span> <span style="color: #009900;">&#125;</span>
    &nbsp;
    <span style="color: #009900;">&#40;</span>...<span style="color: #009900;">&#41;</span>
    <span style="color: #009900;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">public class RemoteResourceReader {
    /** Connection to the resource is being configured */
    public static @interface Configuration { }
    /** Connection to the resource is open, data can be read */
    public static @interface Open { }
    /** Connection to the resource is closed, data cannot be read */
    public static @interface Closed { }
    
    (...)
    }</p></div>
    ";i:2;s:1200:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;"><span style="color: #009900;">&#40;</span>...<span style="color: #009900;">&#41;</span>
    <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #003399;">String</span> read<span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span> @Open <span style="color: #009900;">&#123;</span> ... <span style="color: #009900;">&#125;</span>
    <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000066; font-weight: bold;">void</span> setURL<span style="color: #009900;">&#40;</span><span style="color: #003399;">String</span> newURL<span style="color: #009900;">&#41;</span> @Configuration <span style="color: #009900;">&#123;</span> ... <span style="color: #009900;">&#125;</span>
    <span style="color: #009900;">&#40;</span>...<span style="color: #009900;">&#41;</span></pre></td></tr></table><p class="theCode" style="display:none;">(...)
    public String read() @Open { ... }
    public void setURL(String newURL) @Configuration { ... }
    (...)</p></div>
    ";i:3;s:1806:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;"><span style="color: #009900;">&#40;</span>...<span style="color: #009900;">&#41;</span>
    <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000066; font-weight: bold;">void</span> open<span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span> @Configuration<span style="color: #009900;">&#40;</span>after<span style="color: #339933;">=</span>Open.<span style="color: #000000; font-weight: bold;">class</span><span style="color: #009900;">&#41;</span>
    @Closed<span style="color: #009900;">&#40;</span>after<span style="color: #339933;">=</span>Open.<span style="color: #000000; font-weight: bold;">class</span><span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span> ... <span style="color: #009900;">&#125;</span>
    <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000066; font-weight: bold;">void</span> close<span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span> @Open<span style="color: #009900;">&#40;</span>after<span style="color: #339933;">=</span>Closed.<span style="color: #000000; font-weight: bold;">class</span><span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span> ... <span style="color: #009900;">&#125;</span>
    <span style="color: #009900;">&#40;</span>...<span style="color: #009900;">&#41;</span></pre></td></tr></table><p class="theCode" style="display:none;">(...)
    public void open() @Configuration(after=Open.class)
    @Closed(after=Open.class) { ... }
    public void close() @Open(after=Closed.class) { ... }
    (...)</p></div>
    ";i:4;s:5661:"
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
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;"><span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000000; font-weight: bold;">class</span> RemoteResourceReader <span style="color: #009900;">&#123;</span>
    <span style="color: #008000; font-style: italic; font-weight: bold;">/** Connection to the resource is being configured */</span>
    @State <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000000; font-weight: bold;">static</span> @<span style="color: #000000; font-weight: bold;">interface</span> Configuration <span style="color: #009900;">&#123;</span>
    Class<span style="color: #339933;">&lt;?&gt;</span> after <span style="color: #000000; font-weight: bold;">default</span> <span style="color: #003399;">Object</span>.<span style="color: #000000; font-weight: bold;">class</span> <span style="color: #009900;">&#125;</span>
    <span style="color: #008000; font-style: italic; font-weight: bold;">/** Connection to the resource is open, data can be read */</span>
    @State <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000000; font-weight: bold;">static</span> @<span style="color: #000000; font-weight: bold;">interface</span> Open <span style="color: #009900;">&#123;</span>
    Class<span style="color: #339933;">&lt;?&gt;</span> after <span style="color: #000000; font-weight: bold;">default</span> <span style="color: #003399;">Object</span>.<span style="color: #000000; font-weight: bold;">class</span> <span style="color: #009900;">&#125;</span>
    <span style="color: #008000; font-style: italic; font-weight: bold;">/** Connection to the resource is closed, data cannot be read */</span>
    @State <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000000; font-weight: bold;">static</span> @<span style="color: #000000; font-weight: bold;">interface</span> Closed <span style="color: #009900;">&#123;</span>
    Class<span style="color: #339933;">&lt;?&gt;</span> after <span style="color: #000000; font-weight: bold;">default</span> <span style="color: #003399;">Object</span>.<span style="color: #000000; font-weight: bold;">class</span> <span style="color: #009900;">&#125;</span>
    &nbsp;
    <span style="color: #666666; font-style: italic;">// Initial state of the object - specified in the</span>
    <span style="color: #666666; font-style: italic;">// constructor's receiver annotation</span>
    <span style="color: #000000; font-weight: bold;">public</span> RemoteResourceReader<span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span> <span style="color: #666666; font-style: italic;">/*@Configuration*/</span> <span style="color: #009900;">&#123;</span> ... <span style="color: #009900;">&#125;</span>
    &nbsp;
    <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000066; font-weight: bold;">void</span> setURL<span style="color: #009900;">&#40;</span><span style="color: #003399;">String</span> newURL<span style="color: #009900;">&#41;</span> <span style="color: #666666; font-style: italic;">/*@Configuration*/</span> <span style="color: #009900;">&#123;</span> ... <span style="color: #009900;">&#125;</span>
    &nbsp;
    <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000066; font-weight: bold;">void</span> open<span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span> <span style="color: #666666; font-style: italic;">/*@Configuration(after=Open.class)*/</span>
    <span style="color: #666666; font-style: italic;">/*@Closed(after=Open.class)*/</span> <span style="color: #009900;">&#123;</span> ... <span style="color: #009900;">&#125;</span>
    &nbsp;
    <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #003399;">String</span> read<span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span> <span style="color: #666666; font-style: italic;">/*@Open*/</span> <span style="color: #009900;">&#123;</span> ... <span style="color: #009900;">&#125;</span>
    &nbsp;
    <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000066; font-weight: bold;">void</span> close<span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span> <span style="color: #666666; font-style: italic;">/*@Open(after=Closed.class)*/</span> <span style="color: #009900;">&#123;</span> ... <span style="color: #009900;">&#125;</span>
    <span style="color: #009900;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">public class RemoteResourceReader {
    /** Connection to the resource is being configured */
    @State public static @interface Configuration {
    Class&lt;?&gt; after default Object.class }
    /** Connection to the resource is open, data can be read */
    @State public static @interface Open {
    Class&lt;?&gt; after default Object.class }
    /** Connection to the resource is closed, data cannot be read */
    @State public static @interface Closed {
    Class&lt;?&gt; after default Object.class }
    
    // Initial state of the object - specified in the
    // constructor's receiver annotation
    public RemoteResourceReader() /*@Configuration*/ { ... }
    
    public void setURL(String newURL) /*@Configuration*/ { ... }
    
    public void open() /*@Configuration(after=Open.class)*/
    /*@Closed(after=Open.class)*/ { ... }
    
    public String read() /*@Open*/ { ... }
    
    public void close() /*@Open(after=Closed.class)*/ { ... }
    }</p></div>
    ";i:5;s:4088:"
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
    26
    27
    28
    29
    30
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;"><span style="color: #000066; font-weight: bold;">void</span> readMore<span style="color: #009900;">&#40;</span>@Open RemoteResourceReader rrr<span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span> ... <span style="color: #009900;">&#125;</span>
    &nbsp;
    <span style="color: #000066; font-weight: bold;">void</span> test<span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
    <span style="color: #666666; font-style: italic;">// initially in the 'configuration' state</span>
    RemoteResourceReader rrr <span style="color: #339933;">=</span> <span style="color: #000000; font-weight: bold;">new</span> RemoteResourceReader<span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
    &nbsp;
    <span style="color: #666666; font-style: italic;">// ok: correct state</span>
    rrr.<span style="color: #006633;">setUrl</span><span style="color: #009900;">&#40;</span><span style="color: #0000ff;">&quot;http://something&quot;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
    &nbsp;
    <span style="color: #666666; font-style: italic;">// error: not in the 'open' state</span>
    rrr.<span style="color: #006633;">close</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
    &nbsp;
    <span style="color: #666666; font-style: italic;">// changing state from 'configuration' to 'open'</span>
    rrr.<span style="color: #006633;">open</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
    &nbsp;
    <span style="color: #666666; font-style: italic;">// ok</span>
    <span style="color: #003399;">String</span> read <span style="color: #339933;">=</span> rrr.<span style="color: #006633;">read</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
    &nbsp;
    <span style="color: #666666; font-style: italic;">// error: not in the 'configuration' state</span>
    rrr.<span style="color: #006633;">setUrl</span><span style="color: #009900;">&#40;</span><span style="color: #0000ff;">&quot;http://different&quot;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
    &nbsp;
    <span style="color: #666666; font-style: italic;">// ok: parameter in the 'open' state</span>
    readMore<span style="color: #009900;">&#40;</span>rrr<span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
    &nbsp;
    <span style="color: #666666; font-style: italic;">// ok</span>
    rrr.<span style="color: #006633;">close</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
    &nbsp;
    <span style="color: #666666; font-style: italic;">// error: parameter not in the correct state</span>
    readMore<span style="color: #009900;">&#40;</span>rrr<span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
    <span style="color: #009900;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">void readMore(@Open RemoteResourceReader rrr) { ... }
    
    void test() {
    // initially in the 'configuration' state
    RemoteResourceReader rrr = new RemoteResourceReader();
    
    // ok: correct state
    rrr.setUrl(&quot;http://something&quot;);
    
    // error: not in the 'open' state
    rrr.close();
    
    // changing state from 'configuration' to 'open'
    rrr.open();
    
    // ok
    String read = rrr.read();
    
    // error: not in the 'configuration' state
    rrr.setUrl(&quot;http://different&quot;);
    
    // ok: parameter in the 'open' state
    readMore(rrr);
    
    // ok
    rrr.close();
    
    // error: parameter not in the correct state
    readMore(rrr);
    }</p></div>
    ";}
tags:
  - java
  - jsr308
  - static analysis

---
When dealing with mutable objects in Java, we quite often see that &#8220;states&#8221; of the class considered emerge. The (abstract) &#8220;state&#8221; depends of course on the content of the fields of the object. When methods are invoked, which mutate the fields, the state of the object may change. Also, it may be illegal to call some methods in one state, but legal in another.

So far, specifying the state space of an object, which methods can be called in which states, and how methods change the state, is done mainly in comments (javadocs). With the new possibilities brought by [JSR 308 &#8211; type annotations][1], it&#8217;s now possible to do it in a much more structured way.

The basic idea is simple. Each state of our object is one annotation. We can define those annotations for example as inner classes. There are 2 immediate gains:

  * we enumerate all the states of our object, so the state space is well defined;
  * each state can be commented, so we have a very good place to describe when an object is in the given state and what does it mean.

As an example, let&#8217;s assume that we have a class for reading a remote resource, for example `RemoteResourceReader`. Objects of this class can be in one of the three states:

  * **configuration** &#8211; connection to the resource is being configured
  * **open** &#8211; connection to the resource is open, data can be read
  * **closed** &#8211; the connection is closed, data cannot be read

The inital state is **configuration**, in which we set the connection properties, like the URL, username, password, etc. Then, we can change the state to **open**, read some data, and close the connection, changing the state to **closed**. After that, we may re-open the connection to read more data, etc.

So how would the source of our class look like? First, we define the states:

<pre lang="java" line="1" escaped="true">public class RemoteResourceReader {
/** Connection to the resource is being configured */
public static @interface Configuration { }
/** Connection to the resource is open, data can be read */
public static @interface Open { }
/** Connection to the resource is closed, data cannot be read */
public static @interface Closed { }

(...)
}
</pre>

Now, we have to specify what is the initial state and how methods change the state. Thanks to JSR 308, that is easy. Type annotations extend the number of places, where annotations are allowed; you may place them on any type. See [its web page][1] for more information.

One of the new places, where annotations are allowed, is the &#8220;method receiver&#8221;. This lets you specify meta-data (annotations) that apply to the object, on which the method is called. For example, we want to specify that the `read` method can be invoked on an object only when it is in the **open** state; similarly, the `setURL` method can be invoked only when the object is in the **configuration** state. Using JSR308, we can write it like this:

<pre lang="java" line="1" escaped="true">(...)
public String read() @Open { ... }
public void setURL(String newURL) @Configuration { ... }
(...)
</pre>

However, to keep the code compatible with Java 5/6, we may place the annotations in comments. They will still be recognized by the JSR308 checker framework (why this is important &#8211; read on :) ).

Finally, what about state changes? Here, we also use the method receiver annotations. We may add an element to our state annotations, which will specify the state of the object after the method invocation. For example:

<pre lang="java" line="1">(...)
public void open() @Configuration(after=Open.class)
@Closed(after=Open.class) { ... }
public void close() @Open(after=Closed.class) { ... }
(...)
</pre>

We have specified that:

  * we can invoke the `open` method, if the object was in the **configuration** or **closed** states. After invoking this method, the object will be in the **open** state
  * we can invoke the `close` method, only if the object was in the **open** state. After invoking this method, the object will be in the **closed** state

What are the benefits of using state annotations so far? Improved documentation: users immediately see in what states objects of the class can be, when it is legal to call a method and how the states change. However, there is more: you can check if your objects are in the correct state by using the **typestate checker**!

The **typestate checker** is built on top of the [checkers framework][1], which out-of-the box provides other very useful checkers, for nullness and immutability. Be sure to try them out :).

To use the typestate checker, [go here for downloads and installation instructions][2]. The only additional requirement is that you must annotate your state annotations with `@State`. The checker will verify, using flow analysis, if you invoke methods on objects in the correct state, pass parameters in the correct state etc. This version of the checker should be treated more like a proof-of-concept, than as a final version. It is in a very early development stage, so your comments and experiences are very welcome! :)

To complete the `RemoteResourceReader` example, here is a full version of the class, ready to be used with the [typestate checker][2]:

<pre lang="java" line="1">public class RemoteResourceReader {
/** Connection to the resource is being configured */
@State public static @interface Configuration {
Class<?> after default Object.class }
/** Connection to the resource is open, data can be read */
@State public static @interface Open {
Class

<?> after default Object.class }
/** Connection to the resource is closed, data cannot be read */
@State public static @interface Closed {
Class

<?> after default Object.class }

// Initial state of the object - specified in the
// constructor's receiver annotation
public RemoteResourceReader() /*@Configuration*/ { ... }

public void setURL(String newURL) /*@Configuration*/ { ... }

public void open() /*@Configuration(after=Open.class)*/
/*@Closed(after=Open.class)*/ { ... }

public String read() /*@Open*/ { ... }

public void close() /*@Open(after=Closed.class)*/ { ... }
}
</pre>

Finally, here is some example code which demonstrates what errors are caught by the [typestate checker][2]:

<pre lang="java" line="1">void readMore(@Open RemoteResourceReader rrr) { ... }

void test() {
// initially in the 'configuration' state
RemoteResourceReader rrr = new RemoteResourceReader();

// ok: correct state
rrr.setUrl("http://something");

// error: not in the 'open' state
rrr.close();

// changing state from 'configuration' to 'open'
rrr.open();

// ok
String read = rrr.read();

// error: not in the 'configuration' state
rrr.setUrl("http://different");

// ok: parameter in the 'open' state
readMore(rrr);

// ok
rrr.close();

// error: parameter not in the correct state
readMore(rrr);
}
</pre>

If you&#8217;d like to make experiments with the typestate checker on your own, go ahead and [download it][2]. The setup is really easy. And remember, that your feedback is very valuable and important!

I&#8217;d like to thank Michael Ernst and the rest of the JSR308 team for helping me with some questions that I had during development. The specification and framework is really good and worth taking a look at :)

Adam

 [1]: http://groups.csail.mit.edu/pag/jsr308/
 [2]: http://www.warski.org/typestate.html
