---
title: Dependency Injection and replacing dependencies in CDI/Weld
author: Adam Warski
type: blog
date: 2009-12-16T21:37:27+00:00
url: /blog/2009/12/dependency-injection-and-replacing-dependencies-in-cdiweld/
dsq_thread_id:
  - 1051935059
wp-syntax-cache-content:
  - |
    a:11:{i:1;s:2699:"
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
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;">@ApplicationScoped
    <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000000; font-weight: bold;">class</span> FirstBean <span style="color: #000000; font-weight: bold;">implements</span> <span style="color: #003399;">Serializable</span> <span style="color: #009900;">&#123;</span>
        <span style="color: #000000; font-weight: bold;">private</span> ISecondBean second<span style="color: #339933;">;</span>
        <span style="color: #000000; font-weight: bold;">public</span> FirstBean<span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span> <span style="color: #009900;">&#125;</span>
    &nbsp;
        @Inject
        <span style="color: #000000; font-weight: bold;">public</span> FirstBean<span style="color: #009900;">&#40;</span>ISecondBean second<span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span> <span style="color: #000000; font-weight: bold;">this</span>.<span style="color: #006633;">second</span> <span style="color: #339933;">=</span> second<span style="color: #339933;">;</span> <span style="color: #009900;">&#125;</span>
    &nbsp;
        <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000066; font-weight: bold;">void</span> start<span style="color: #009900;">&#40;</span><span style="color: #000066; font-weight: bold;">int</span> c<span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
            <span style="color: #003399;">System</span>.<span style="color: #006633;">out</span>.<span style="color: #006633;">println</span><span style="color: #009900;">&#40;</span><span style="color: #0000ff;">&quot;Type of second: &quot;</span> <span style="color: #339933;">+</span> second.<span style="color: #006633;">getMessage</span><span style="color: #009900;">&#40;</span>c<span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
        <span style="color: #009900;">&#125;</span>
    <span style="color: #009900;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">@ApplicationScoped
    public class FirstBean implements Serializable {
        private ISecondBean second;
        public FirstBean() { }
    
        @Inject
        public FirstBean(ISecondBean second) { this.second = second; }
    
        public void start(int c) {
            System.out.println(&quot;Type of second: &quot; + second.getMessage(c));
        }
    }</p></div>
    ";i:2;s:3002:"
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
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;"><span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000000; font-weight: bold;">interface</span> ISecondBean <span style="color: #000000; font-weight: bold;">extends</span> <span style="color: #003399;">Serializable</span> <span style="color: #009900;">&#123;</span>
        <span style="color: #003399;">String</span> getMessage<span style="color: #009900;">&#40;</span><span style="color: #000066; font-weight: bold;">int</span> c<span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
    <span style="color: #009900;">&#125;</span>
    &nbsp;
    @Alternative
    <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000000; font-weight: bold;">class</span> SecondBeanA <span style="color: #000000; font-weight: bold;">implements</span> ISecondBean <span style="color: #009900;">&#123;</span>
        <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #003399;">String</span> getMessage<span style="color: #009900;">&#40;</span><span style="color: #000066; font-weight: bold;">int</span> c<span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span> <span style="color: #000000; font-weight: bold;">return</span> <span style="color: #0000ff;">&quot;variant A; &quot;</span> <span style="color: #339933;">+</span> c<span style="color: #339933;">;</span> <span style="color: #009900;">&#125;</span>
    <span style="color: #009900;">&#125;</span>
    &nbsp;
    @Alternative
    <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000000; font-weight: bold;">class</span> SecondBeanB <span style="color: #000000; font-weight: bold;">implements</span> ISecondBean <span style="color: #009900;">&#123;</span>
        <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #003399;">String</span> getMessage<span style="color: #009900;">&#40;</span><span style="color: #000066; font-weight: bold;">int</span> c<span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span> <span style="color: #000000; font-weight: bold;">return</span> <span style="color: #0000ff;">&quot;variant B; &quot;</span> <span style="color: #339933;">+</span> c<span style="color: #339933;">;</span> <span style="color: #009900;">&#125;</span>
    <span style="color: #009900;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">public interface ISecondBean extends Serializable {
        String getMessage(int c);
    }
    
    @Alternative
    public class SecondBeanA implements ISecondBean {
        public String getMessage(int c) { return &quot;variant A; &quot; + c; }
    }
    
    @Alternative
    public class SecondBeanB implements ISecondBean {
        public String getMessage(int c) { return &quot;variant B; &quot; + c; }
    }</p></div>
    ";i:3;s:1341:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    6
    7
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;">@ApplicationScoped
    <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000000; font-weight: bold;">class</span> SecondBeanProducer <span style="color: #009900;">&#123;</span>
        @Produces
        <span style="color: #000000; font-weight: bold;">public</span> ISecondBean getSecondBean<span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
            <span style="color: #000000; font-weight: bold;">return</span> <span style="color: #000000; font-weight: bold;">new</span> SecondBeanA<span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>  <span style="color: #666666; font-style: italic;">// by default we use the A variant</span>
        <span style="color: #009900;">&#125;</span>
    <span style="color: #009900;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">@ApplicationScoped
    public class SecondBeanProducer {
        @Produces
        public ISecondBean getSecondBean() {
            return new SecondBeanA();  // by default we use the A variant
        }
    }</p></div>
    ";i:4;s:2389:"
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
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;">@ApplicationScoped
    <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000000; font-weight: bold;">class</span> Main <span style="color: #009900;">&#123;</span>
        @Inject
        <span style="color: #000000; font-weight: bold;">private</span> FirstBean first<span style="color: #339933;">;</span>
    &nbsp;
        <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000066; font-weight: bold;">void</span> start<span style="color: #009900;">&#40;</span>@Observes ContainerInitialized event<span style="color: #009900;">&#41;</span> <span style="color: #000000; font-weight: bold;">throws</span> <span style="color: #003399;">Exception</span> <span style="color: #009900;">&#123;</span>
            <span style="color: #003399;">System</span>.<span style="color: #006633;">out</span>.<span style="color: #006633;">println</span><span style="color: #009900;">&#40;</span><span style="color: #0000ff;">&quot;Welcome to Main!&quot;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
            first.<span style="color: #006633;">start</span><span style="color: #009900;">&#40;</span><span style="color: #cc66cc;">1</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
            first.<span style="color: #006633;">start</span><span style="color: #009900;">&#40;</span><span style="color: #cc66cc;">2</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
            first.<span style="color: #006633;">start</span><span style="color: #009900;">&#40;</span><span style="color: #cc66cc;">3</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
        <span style="color: #009900;">&#125;</span>
    <span style="color: #009900;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">@ApplicationScoped
    public class Main {
        @Inject
        private FirstBean first;
    
        public void start(@Observes ContainerInitialized event) throws Exception {
            System.out.println(&quot;Welcome to Main!&quot;);
            first.start(1);
            first.start(2);
            first.start(3);
        }
    }</p></div>
    ";i:5;s:832:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;">Welcome to Main<span style="color: #339933;">!</span>
    Type of second<span style="color: #339933;">:</span> variant A<span style="color: #339933;">;</span> <span style="color: #cc66cc;">1</span>
    Type of second<span style="color: #339933;">:</span> variant A<span style="color: #339933;">;</span> <span style="color: #cc66cc;">2</span>
    Type of second<span style="color: #339933;">:</span> variant A<span style="color: #339933;">;</span> <span style="color: #cc66cc;">3</span></pre></td></tr></table><p class="theCode" style="display:none;">Welcome to Main!
    Type of second: variant A; 1
    Type of second: variant A; 2
    Type of second: variant A; 3</p></div>
    ";i:6;s:2056:"
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
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;">@InterceptorBinding
    @Target<span style="color: #009900;">&#40;</span><span style="color: #009900;">&#123;</span> ElementType.<span style="color: #006633;">TYPE</span>, ElementType.<span style="color: #006633;">METHOD</span> <span style="color: #009900;">&#125;</span><span style="color: #009900;">&#41;</span>
    @Retention<span style="color: #009900;">&#40;</span>RetentionPolicy.<span style="color: #006633;">RUNTIME</span><span style="color: #009900;">&#41;</span>
    <span style="color: #000000; font-weight: bold;">public</span> @<span style="color: #000000; font-weight: bold;">interface</span> ReplaceableResult <span style="color: #009900;">&#123;</span>
    <span style="color: #009900;">&#125;</span>
    &nbsp;
    @Interceptor
    @ReplaceableResult
    <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000000; font-weight: bold;">class</span> ReplaceableResultInterceptor <span style="color: #009900;">&#123;</span>
        @AroundInvoke
        <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #003399;">Object</span> replace<span style="color: #009900;">&#40;</span>InvocationContext ctx<span style="color: #009900;">&#41;</span> <span style="color: #000000; font-weight: bold;">throws</span> <span style="color: #003399;">Exception</span> <span style="color: #009900;">&#123;</span>
            ...
        <span style="color: #009900;">&#125;</span>
    <span style="color: #009900;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">@InterceptorBinding
    @Target({ ElementType.TYPE, ElementType.METHOD })
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ReplaceableResult {
    }
    
    @Interceptor
    @ReplaceableResult
    public class ReplaceableResultInterceptor {
        @AroundInvoke
        public Object replace(InvocationContext ctx) throws Exception {
            ...
        }
    }</p></div>
    ";i:7;s:1448:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    </pre></td><td class="code"><pre class="xml" style="font-family:monospace;"><span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;beans<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
        <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;interceptors<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
            <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;class<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>test.ReplaceableResultInterceptor<span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/class<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
        <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/interceptors<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
    <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/beans<span style="color: #000000; font-weight: bold;">&gt;</span></span></span></pre></td></tr></table><p class="theCode" style="display:none;">&lt;beans&gt;
        &lt;interceptors&gt;
            &lt;class&gt;test.ReplaceableResultInterceptor&lt;/class&gt;
        &lt;/interceptors&gt;
    &lt;/beans&gt;</p></div>
    ";i:8;s:1379:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    6
    7
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;">@ApplicationScoped
    <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000000; font-weight: bold;">class</span> SecondBeanProducer <span style="color: #009900;">&#123;</span>
        @Produces @ReplaceableResult
        <span style="color: #000000; font-weight: bold;">public</span> ISecondBean getSecondBean<span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
            <span style="color: #000000; font-weight: bold;">return</span> <span style="color: #000000; font-weight: bold;">new</span> SecondBeanA<span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>  <span style="color: #666666; font-style: italic;">// by default we use the A variant</span>
        <span style="color: #009900;">&#125;</span>
    <span style="color: #009900;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">@ApplicationScoped
    public class SecondBeanProducer {
        @Produces @ReplaceableResult
        public ISecondBean getSecondBean() {
            return new SecondBeanA();  // by default we use the A variant
        }
    }</p></div>
    ";i:9;s:4769:"
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
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;">@ApplicationScoped
    <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000000; font-weight: bold;">class</span> Main <span style="color: #009900;">&#123;</span>
        @Inject
        <span style="color: #000000; font-weight: bold;">private</span> FirstBean first<span style="color: #339933;">;</span>
    &nbsp;
        <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000066; font-weight: bold;">void</span> start<span style="color: #009900;">&#40;</span>@Observes ContainerInitialized event<span style="color: #009900;">&#41;</span> <span style="color: #000000; font-weight: bold;">throws</span> <span style="color: #003399;">Exception</span> <span style="color: #009900;">&#123;</span>
            <span style="color: #003399;">System</span>.<span style="color: #006633;">out</span>.<span style="color: #006633;">println</span><span style="color: #009900;">&#40;</span><span style="color: #0000ff;">&quot;Welcome to Main!&quot;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
            first.<span style="color: #006633;">start</span><span style="color: #009900;">&#40;</span><span style="color: #cc66cc;">1</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
    &nbsp;
            <span style="color: #666666; font-style: italic;">// Here we replace the implementation of ISecondBean just for </span>
            <span style="color: #666666; font-style: italic;">// the duration of one method call.</span>
            ReplaceableResultInterceptor.<span style="color: #006633;">withReplacement</span><span style="color: #009900;">&#40;</span>
                    ISecondBean.<span style="color: #000000; font-weight: bold;">class</span>,
                    <span style="color: #000000; font-weight: bold;">new</span> SecondBeanB<span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span>, <span style="color: #666666; font-style: italic;">// we are using the B variant</span>
                    <span style="color: #000000; font-weight: bold;">new</span> Callable<span style="color: #339933;">&lt;</span>Void<span style="color: #339933;">&gt;</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
                        @Override
                        <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #003399;">Void</span> call<span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span> <span style="color: #000000; font-weight: bold;">throws</span> <span style="color: #003399;">Exception</span> <span style="color: #009900;">&#123;</span>
                            first.<span style="color: #006633;">start</span><span style="color: #009900;">&#40;</span><span style="color: #cc66cc;">2</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
                            <span style="color: #000000; font-weight: bold;">return</span> <span style="color: #000066; font-weight: bold;">null</span><span style="color: #339933;">;</span>
                        <span style="color: #009900;">&#125;</span>
                    <span style="color: #009900;">&#125;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
    &nbsp;
            first.<span style="color: #006633;">start</span><span style="color: #009900;">&#40;</span><span style="color: #cc66cc;">3</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
        <span style="color: #009900;">&#125;</span>
    <span style="color: #009900;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">@ApplicationScoped
    public class Main {
        @Inject
        private FirstBean first;
    
        public void start(@Observes ContainerInitialized event) throws Exception {
            System.out.println(&quot;Welcome to Main!&quot;);
            first.start(1);
    
            // Here we replace the implementation of ISecondBean just for 
            // the duration of one method call.
            ReplaceableResultInterceptor.withReplacement(
                    ISecondBean.class,
                    new SecondBeanB(), // we are using the B variant
                    new Callable&lt;Void&gt;() {
                        @Override
                        public Void call() throws Exception {
                            first.start(2);
                            return null;
                        }
                    });
    
            first.start(3);
        }
    }</p></div>
    ";i:10;s:832:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;">Welcome to Main<span style="color: #339933;">!</span>
    Type of second<span style="color: #339933;">:</span> variant A<span style="color: #339933;">;</span> <span style="color: #cc66cc;">1</span>
    Type of second<span style="color: #339933;">:</span> variant B<span style="color: #339933;">;</span> <span style="color: #cc66cc;">2</span>
    Type of second<span style="color: #339933;">:</span> variant A<span style="color: #339933;">;</span> <span style="color: #cc66cc;">3</span></pre></td></tr></table><p class="theCode" style="display:none;">Welcome to Main!
    Type of second: variant A; 1
    Type of second: variant B; 2
    Type of second: variant A; 3</p></div>
    ";i:11;s:16213:"
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
    31
    32
    33
    34
    35
    36
    37
    38
    39
    40
    41
    42
    43
    44
    45
    46
    47
    48
    49
    50
    51
    52
    53
    54
    55
    56
    57
    58
    59
    60
    61
    62
    63
    64
    65
    66
    67
    68
    69
    70
    71
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;">@Interceptor
    @ReplaceableResult
    <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000000; font-weight: bold;">class</span> ReplaceableResultInterceptor <span style="color: #009900;">&#123;</span>
        <span style="color: #000000; font-weight: bold;">private</span> <span style="color: #000000; font-weight: bold;">static</span> ThreadLocal<span style="color: #339933;">&lt;</span>Map<span style="color: #339933;">&lt;</span>Class<span style="color: #339933;">&lt;?&gt;</span>, Object<span style="color: #339933;">&gt;&gt;</span> replacements 
            <span style="color: #339933;">=</span> <span style="color: #000000; font-weight: bold;">new</span> ThreadLocal<span style="color: #339933;">&lt;</span>Map<span style="color: #339933;">&lt;</span>Class<span style="color: #339933;">&lt;?&gt;</span>, Object<span style="color: #339933;">&gt;&gt;</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
            @Override
            <span style="color: #000000; font-weight: bold;">protected</span> Map<span style="color: #339933;">&lt;</span>Class<span style="color: #339933;">&lt;?&gt;</span>, Object<span style="color: #339933;">&gt;</span> initialValue<span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
                <span style="color: #000000; font-weight: bold;">return</span> <span style="color: #000000; font-weight: bold;">new</span> HashMap<span style="color: #339933;">&lt;</span>Class<span style="color: #339933;">&lt;?&gt;</span>, Object<span style="color: #339933;">&gt;</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
            <span style="color: #009900;">&#125;</span>
        <span style="color: #009900;">&#125;</span><span style="color: #339933;">;</span>
    &nbsp;
        @AroundInvoke
        <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #003399;">Object</span> replace<span style="color: #009900;">&#40;</span>InvocationContext ctx<span style="color: #009900;">&#41;</span> <span style="color: #000000; font-weight: bold;">throws</span> <span style="color: #003399;">Exception</span> <span style="color: #009900;">&#123;</span>
            Class<span style="color: #339933;">&lt;?&gt;</span> returnType <span style="color: #339933;">=</span> ctx.<span style="color: #006633;">getMethod</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span>.<span style="color: #006633;">getReturnType</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
            <span style="color: #000000; font-weight: bold;">if</span> <span style="color: #009900;">&#40;</span><span style="color: #339933;">!</span>returnType.<span style="color: #006633;">isInterface</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
                <span style="color: #000000; font-weight: bold;">throw</span> <span style="color: #000000; font-weight: bold;">new</span> <span style="color: #003399;">UnsupportedOperationException</span><span style="color: #009900;">&#40;</span><span style="color: #0000ff;">&quot;Only results of methods &quot;</span> <span style="color: #339933;">+</span> 
                   <span style="color: #0000ff;">&quot;which produce implementations of an interface can be replaced!&quot;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
            <span style="color: #009900;">&#125;</span>
    &nbsp;
            <span style="color: #666666; font-style: italic;">// Getting the result of the producer method</span>
            <span style="color: #003399;">Object</span> result <span style="color: #339933;">=</span> ctx.<span style="color: #006633;">proceed</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
    &nbsp;
            <span style="color: #666666; font-style: italic;">// Wrapping it in an interceptor, which on an invocation will check if </span>
            <span style="color: #666666; font-style: italic;">// there's a replacement</span>
            result <span style="color: #339933;">=</span> <span style="color: #003399;">Proxy</span>.<span style="color: #006633;">newProxyInstance</span><span style="color: #009900;">&#40;</span>
                    returnType.<span style="color: #006633;">getClassLoader</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span>,
                    <span style="color: #000000; font-weight: bold;">new</span> <span style="color: #000000; font-weight: bold;">Class</span><span style="color: #009900;">&#91;</span><span style="color: #009900;">&#93;</span> <span style="color: #009900;">&#123;</span> returnType <span style="color: #009900;">&#125;</span>,
                    <span style="color: #000000; font-weight: bold;">new</span> ReplaceableInterceptor<span style="color: #009900;">&#40;</span>returnType, result<span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
    &nbsp;
            <span style="color: #000000; font-weight: bold;">return</span> result<span style="color: #339933;">;</span>
        <span style="color: #009900;">&#125;</span>
    &nbsp;
        <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000000; font-weight: bold;">static</span> <span style="color: #339933;">&lt;</span>V<span style="color: #339933;">&gt;</span> V withReplacement<span style="color: #009900;">&#40;</span>Class<span style="color: #339933;">&lt;?&gt;</span> replacing, <span style="color: #003399;">Object</span> replacement, 
            Callable<span style="color: #339933;">&lt;</span>V<span style="color: #339933;">&gt;</span> toCall<span style="color: #009900;">&#41;</span> <span style="color: #000000; font-weight: bold;">throws</span> <span style="color: #003399;">Exception</span> <span style="color: #009900;">&#123;</span>
            <span style="color: #000066; font-weight: bold;">boolean</span> hasOld <span style="color: #339933;">=</span> <span style="color: #000066; font-weight: bold;">false</span><span style="color: #339933;">;</span>
            <span style="color: #003399;">Object</span> old <span style="color: #339933;">=</span> <span style="color: #000066; font-weight: bold;">null</span><span style="color: #339933;">;</span>
            <span style="color: #000000; font-weight: bold;">try</span> <span style="color: #009900;">&#123;</span>
                hasOld <span style="color: #339933;">=</span> replacements.<span style="color: #006633;">get</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span>.<span style="color: #006633;">containsKey</span><span style="color: #009900;">&#40;</span>replacing<span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
                old <span style="color: #339933;">=</span> replacements.<span style="color: #006633;">get</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span>.<span style="color: #006633;">put</span><span style="color: #009900;">&#40;</span>replacing, replacement<span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
                <span style="color: #000000; font-weight: bold;">return</span> toCall.<span style="color: #006633;">call</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
            <span style="color: #009900;">&#125;</span> <span style="color: #000000; font-weight: bold;">finally</span> <span style="color: #009900;">&#123;</span>
                <span style="color: #000000; font-weight: bold;">if</span> <span style="color: #009900;">&#40;</span>hasOld<span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
                    replacements.<span style="color: #006633;">get</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span>.<span style="color: #006633;">put</span><span style="color: #009900;">&#40;</span>replacing, old<span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
                <span style="color: #009900;">&#125;</span> <span style="color: #000000; font-weight: bold;">else</span> <span style="color: #009900;">&#123;</span>
                    replacements.<span style="color: #006633;">get</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span>.<span style="color: #006633;">remove</span><span style="color: #009900;">&#40;</span>replacing<span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
                <span style="color: #009900;">&#125;</span>
            <span style="color: #009900;">&#125;</span>
        <span style="color: #009900;">&#125;</span>
    &nbsp;
        <span style="color: #000000; font-weight: bold;">private</span> <span style="color: #000000; font-weight: bold;">class</span> ReplaceableInterceptor <span style="color: #000000; font-weight: bold;">implements</span> <span style="color: #003399;">InvocationHandler</span> <span style="color: #009900;">&#123;</span>
            <span style="color: #000000; font-weight: bold;">private</span> <span style="color: #000000; font-weight: bold;">final</span> Class<span style="color: #339933;">&lt;?&gt;</span> replacementsKey<span style="color: #339933;">;</span>
            <span style="color: #000000; font-weight: bold;">private</span> <span style="color: #000000; font-weight: bold;">final</span> <span style="color: #003399;">Object</span> delegate<span style="color: #339933;">;</span>
    &nbsp;
            <span style="color: #000000; font-weight: bold;">private</span> ReplaceableInterceptor<span style="color: #009900;">&#40;</span>Class<span style="color: #339933;">&lt;?&gt;</span> replacementsKey, 
                <span style="color: #003399;">Object</span> delegate<span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
                <span style="color: #000000; font-weight: bold;">this</span>.<span style="color: #006633;">replacementsKey</span> <span style="color: #339933;">=</span> replacementsKey<span style="color: #339933;">;</span>
                <span style="color: #000000; font-weight: bold;">this</span>.<span style="color: #006633;">delegate</span> <span style="color: #339933;">=</span> delegate<span style="color: #339933;">;</span>
            <span style="color: #009900;">&#125;</span>
    &nbsp;
            @Override
            <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #003399;">Object</span> invoke<span style="color: #009900;">&#40;</span><span style="color: #003399;">Object</span> proxy, <span style="color: #003399;">Method</span> method, <span style="color: #003399;">Object</span><span style="color: #009900;">&#91;</span><span style="color: #009900;">&#93;</span> args<span style="color: #009900;">&#41;</span> 
                <span style="color: #000000; font-weight: bold;">throws</span> <span style="color: #003399;">Throwable</span> <span style="color: #009900;">&#123;</span>
                <span style="color: #000000; font-weight: bold;">if</span> <span style="color: #009900;">&#40;</span>replacements.<span style="color: #006633;">get</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span>.<span style="color: #006633;">containsKey</span><span style="color: #009900;">&#40;</span>replacementsKey<span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
                    <span style="color: #000000; font-weight: bold;">return</span> method.<span style="color: #006633;">invoke</span><span style="color: #009900;">&#40;</span>replacements.<span style="color: #006633;">get</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span>.<span style="color: #006633;">get</span><span style="color: #009900;">&#40;</span>replacementsKey<span style="color: #009900;">&#41;</span>, 
                        args<span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>                                                     
                <span style="color: #009900;">&#125;</span> <span style="color: #000000; font-weight: bold;">else</span> <span style="color: #009900;">&#123;</span>
                    <span style="color: #000000; font-weight: bold;">return</span> method.<span style="color: #006633;">invoke</span><span style="color: #009900;">&#40;</span>delegate, args<span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
                <span style="color: #009900;">&#125;</span>
            <span style="color: #009900;">&#125;</span>
        <span style="color: #009900;">&#125;</span>
    <span style="color: #009900;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">@Interceptor
    @ReplaceableResult
    public class ReplaceableResultInterceptor {
        private static ThreadLocal&lt;Map&lt;Class&lt;?&gt;, Object&gt;&gt; replacements 
            = new ThreadLocal&lt;Map&lt;Class&lt;?&gt;, Object&gt;&gt;() {
            @Override
            protected Map&lt;Class&lt;?&gt;, Object&gt; initialValue() {
                return new HashMap&lt;Class&lt;?&gt;, Object&gt;();
            }
        };
    
        @AroundInvoke
        public Object replace(InvocationContext ctx) throws Exception {
            Class&lt;?&gt; returnType = ctx.getMethod().getReturnType();
            if (!returnType.isInterface()) {
                throw new UnsupportedOperationException(&quot;Only results of methods &quot; + 
                   &quot;which produce implementations of an interface can be replaced!&quot;);
            }
    
            // Getting the result of the producer method
            Object result = ctx.proceed();
    
            // Wrapping it in an interceptor, which on an invocation will check if 
            // there's a replacement
            result = Proxy.newProxyInstance(
                    returnType.getClassLoader(),
                    new Class[] { returnType },
                    new ReplaceableInterceptor(returnType, result));
    
            return result;
        }
    
        public static &lt;V&gt; V withReplacement(Class&lt;?&gt; replacing, Object replacement, 
            Callable&lt;V&gt; toCall) throws Exception {
            boolean hasOld = false;
            Object old = null;
            try {
                hasOld = replacements.get().containsKey(replacing);
                old = replacements.get().put(replacing, replacement);
                return toCall.call();
            } finally {
                if (hasOld) {
                    replacements.get().put(replacing, old);
                } else {
                    replacements.get().remove(replacing);
                }
            }
        }
    
        private class ReplaceableInterceptor implements InvocationHandler {
            private final Class&lt;?&gt; replacementsKey;
            private final Object delegate;
    
            private ReplaceableInterceptor(Class&lt;?&gt; replacementsKey, 
                Object delegate) {
                this.replacementsKey = replacementsKey;
                this.delegate = delegate;
            }
    
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) 
                throws Throwable {
                if (replacements.get().containsKey(replacementsKey)) {
                    return method.invoke(replacements.get().get(replacementsKey), 
                        args);                                                     
                } else {
                    return method.invoke(delegate, args);
                }
            }
        }
    }</p></div>
    ";}
tags:
  - jee
  - dependency injection
  - java

---
From time to time, in a system I develop, which uses EJB3 beans as the basic &#8220;component model&#8221;, I have a need to do some operations using a new entity manager (but still in the same transaction). The problem here is that if I invoke a method on another bean, which has an entity manager injected (using `@PersistenceContext EntityManager em`), the entity manager will be the original one. There is no way to temporarily replace it or any other dependency, just for the time of one invocation.

That&#8217;s why I wanted to see if that would be possible to achieve using the recently released [Weld][1] framework, reference implementation of the CDI specification (part of JEE 6). Unlike in [Seam][2], where injection is done whenever a method on a managed bean is invoked (which would theoretically make it easier to do the temporary replacement), in CDI injection happens once, when the objects are constructed. So the only way (at least the only way I see) to implement temporary replacement is to wrap the injected beans in some kind of an interceptor.

In CDI, [injected beans][3] can either be &#8220;stand-alone&#8221;, or come from a producer (factory) method (annotated with `@Producer`). As `EntityManager`s are typically created by producer methods, in the example below I assume this scenario. It is possible to slightly change the code so that it works for normal beans, however I didn&#8217;t manage to create a nice universal solution (which wouldn&#8217;t simply do an &#8220;if&#8221; to check if the bean is created by a producer method).

To test, we define a simple bean which will have a dependency injected via constructor injection:

<pre lang="java" line="1">@ApplicationScoped
public class FirstBean implements Serializable {
    private ISecondBean second;
    public FirstBean() { }

    @Inject
    public FirstBean(ISecondBean second) { this.second = second; }

    public void start(int c) {
        System.out.println("Type of second: " + second.getMessage(c));
    }
}
</pre>

The interface `ISecondBean` has two implementations:

<pre lang="java" line="1">public interface ISecondBean extends Serializable {
    String getMessage(int c);
}

@Alternative
public class SecondBeanA implements ISecondBean {
    public String getMessage(int c) { return "variant A; " + c; }
}

@Alternative
public class SecondBeanB implements ISecondBean {
    public String getMessage(int c) { return "variant B; " + c; }
}
</pre>

The `@Alternative` annotation makes the bean disabled by default, so that Weld doesn&#8217;t try to inject an instance of it when there&#8217;s a matching injection point. That&#8217;s because we want to produce implementations of `ISecondBean` using a producer method:

<pre lang="java" line="1">@ApplicationScoped
public class SecondBeanProducer {
    @Produces
    public ISecondBean getSecondBean() {
        return new SecondBeanA();  // by default we use the A variant
    }
}
</pre>

A very nice feature of Weld/CDI, especially for evaluation purposes, is that it&#8217;s possible to use it very easily in Java SE. To test our beans, all we need to do is run the `org.jboss.weld.environment.se.StartMain`, putting a jar with our classes in the classpath. One important thing is that the jar **must** contain a `beans.xml` file (it can be empty, but it must exist). We can observe the container startup-event to do some work:

<pre lang="java" line="1">@ApplicationScoped
public class Main {
    @Inject
    private FirstBean first;

    public void start(@Observes ContainerInitialized event) throws Exception {
        System.out.println("Welcome to Main!");
        first.start(1);
        first.start(2);
        first.start(3);
    }
}
</pre>

The result will be:

<pre lang="java" line="1">Welcome to Main!
Type of second: variant A; 1
Type of second: variant A; 2
Type of second: variant A; 3
</pre>

Now we finally come to the point of implementing the replaceable dependencies. As you may have guessed, `ISecondBean` corresponds to `EntityManager`, and that&#8217;s the dependency that we will try to replace.

To do that, we first have to define an interceptor. That&#8217;s quite easy and intuitive in CDI/Weld ([documentation here][4]). There are three steps:

  1. write an annotation, which will be an interceptor binding: all methods annotated with that annotation will be intercepted
  2. write the interceptor, annotating it with the annotation created earlier: that way Weld will know that the annotation binds the given interceptor
  3. enable the interceptor in beans.xml

Here&#8217;s the code (full interceptor code below):

<pre lang="java" line="1">@InterceptorBinding
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ReplaceableResult {
}

@Interceptor
@ReplaceableResult
public class ReplaceableResultInterceptor {
    @AroundInvoke
    public Object replace(InvocationContext ctx) throws Exception {
        ...
    }
}
</pre>

<pre lang="xml" line="1">&lt;beans>
    &lt;interceptors>
        &lt;class>test.ReplaceableResultInterceptor&lt;/class>
    &lt;/interceptors>
&lt;/beans>
</pre>

Adding an interceptor for a method is easy: if we want to be able to temporarily replace implementations of `ISecondBean`, all we need to do is annotate the producer method with the new annotation. `SecondBeanProducer` now takes the form:

<pre lang="java" line="1">@ApplicationScoped
public class SecondBeanProducer {
    @Produces @ReplaceableResult
    public ISecondBean getSecondBean() {
        return new SecondBeanA();  // by default we use the A variant
    }
}
</pre>

We can now test the temporary replacement by modifying the `Main` bean that we used before:

<pre lang="java" line="1">@ApplicationScoped
public class Main {
    @Inject
    private FirstBean first;

    public void start(@Observes ContainerInitialized event) throws Exception {
        System.out.println("Welcome to Main!");
        first.start(1);

        // Here we replace the implementation of ISecondBean just for 
        // the duration of one method call.
        ReplaceableResultInterceptor.withReplacement(
                ISecondBean.class,
                new SecondBeanB(), // we are using the B variant
                new Callable&lt;Void>() {
                    @Override
                    public Void call() throws Exception {
                        first.start(2);
                        return null;
                    }
                });

        first.start(3);
    }
}
</pre>

The result will now be:

<pre lang="java" line="1">Welcome to Main!
Type of second: variant A; 1
Type of second: variant B; 2
Type of second: variant A; 3
</pre>

So the dependency in `FirstBean` was swapped! Which was our goal: for the duration of the bean method invocation, whenever a `ISecondBean` dependency is injected, the temporary implementation will be used.  
Full code of the interceptor:

<pre lang="java" line="1">@Interceptor
@ReplaceableResult
public class ReplaceableResultInterceptor {
    private static ThreadLocal&lt;Map&lt;Class<?>, Object>> replacements 
        = new ThreadLocal&lt;Map&lt;Class

<?>, Object>>() {
        @Override
        protected Map&lt;Class

<?>, Object> initialValue() {
            return new HashMap&lt;Class

<?>, Object>();
        }
    };

    @AroundInvoke
    public Object replace(InvocationContext ctx) throws Exception {
        Class

<?> returnType = ctx.getMethod().getReturnType();
        if (!returnType.isInterface()) {
            throw new UnsupportedOperationException("Only results of methods " + 
               "which produce implementations of an interface can be replaced!");
        }

        // Getting the result of the producer method
        Object result = ctx.proceed();

        // Wrapping it in an interceptor, which on an invocation will check if 
        // there's a replacement
        result = Proxy.newProxyInstance(
                returnType.getClassLoader(),
                new Class[] { returnType },
                new ReplaceableInterceptor(returnType, result));

        return result;
    }

    public static &lt;V> V withReplacement(Class

<?> replacing, Object replacement, 
        Callable&lt;V> toCall) throws Exception {
        boolean hasOld = false;
        Object old = null;
        try {
            hasOld = replacements.get().containsKey(replacing);
            old = replacements.get().put(replacing, replacement);
            return toCall.call();
        } finally {
            if (hasOld) {
                replacements.get().put(replacing, old);
            } else {
                replacements.get().remove(replacing);
            }
        }
    }

    private class ReplaceableInterceptor implements InvocationHandler {
        private final Class

<?> replacementsKey;
        private final Object delegate;

        private ReplaceableInterceptor(Class

<?> replacementsKey, 
            Object delegate) {
            this.replacementsKey = replacementsKey;
            this.delegate = delegate;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) 
            throws Throwable {
            if (replacements.get().containsKey(replacementsKey)) {
                return method.invoke(replacements.get().get(replacementsKey), 
                    args);                                                     
            } else {
                return method.invoke(delegate, args);
            }
        }
    }
}
</pre>

Overall, CDI/Weld works very well and I&#8217;m very satisfied with it. One thing I&#8217;m missing is being to able to do programmatic configuration ([Guice][5]-style), but it&#8217;s already in the [list of ideas][6] for future portable extensions. I think that programmatic configuration and the ability to run Weld in JavaSE will create a great mix for _&#8220;semi-integration&#8221; testing_.

Adam

 [1]: http://seamframework.org/Weld
 [2]: http://seamframework.org
 [3]: http://docs.jboss.org/weld/reference/1.0.0/en-US/html_single/#d0e785
 [4]: http://docs.jboss.org/weld/reference/1.0.0/en-US/html_single/#interceptors
 [5]: http://code.google.com/p/google-guice/
 [6]: http://www.seamframework.org/Weld/PortableExtensionsPackage
