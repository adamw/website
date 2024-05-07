---
title: Simple security interceptor in Weld/JSF2
author: Adam Warski
type: post
date: 2010-03-31T13:52:30+00:00
url: /blog/2010/03/simple-security-interceptor-in-weldjsf2/
dsq_thread_id:
  - 1051935172
wp-syntax-cache-content:
  - |
    a:4:{i:1;s:895:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;">@Secure<span style="color: #009900;">&#40;</span><span style="color: #0000ff;">&quot;#{loggedInUser.name == arg0.name}&quot;</span><span style="color: #009900;">&#41;</span> 
    <span style="color: #000000; font-weight: bold;">public</span> List<span style="color: #339933;">&lt;</span>Message<span style="color: #339933;">&gt;</span> listMessages<span style="color: #009900;">&#40;</span>User owner<span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span> ... <span style="color: #009900;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">@Secure(&quot;#{loggedInUser.name == arg0.name}&quot;) 
    public List&lt;Message&gt; listMessages(User owner) { ... }</p></div>
    ";i:2;s:2043:"
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
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;"><span style="color: #008000; font-style: italic; font-weight: bold;">/**
     * @author Adam Warski (adam at warski dot org)
     */</span>
    @Retention<span style="color: #009900;">&#40;</span>RUNTIME<span style="color: #009900;">&#41;</span>
    @Target<span style="color: #009900;">&#40;</span><span style="color: #009900;">&#123;</span>TYPE, METHOD<span style="color: #009900;">&#125;</span><span style="color: #009900;">&#41;</span>
    @InterceptorBinding
    <span style="color: #000000; font-weight: bold;">public</span> @<span style="color: #000000; font-weight: bold;">interface</span> Secure <span style="color: #009900;">&#123;</span>
        <span style="color: #008000; font-style: italic; font-weight: bold;">/**
         * @return The EL expression that should be evaluated. If it evaluates to 
         * {@code true}, access will be granted. The EL expression may reference 
         * any objects that are in any context, as well as the arguments of the method,
         * under the names {@code arg0, arg1, arg2, ...}.
         */</span>
        @Nonbinding
        <span style="color: #003399;">String</span>  value<span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
    <span style="color: #009900;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">/**
     * @author Adam Warski (adam at warski dot org)
     */
    @Retention(RUNTIME)
    @Target({TYPE, METHOD})
    @InterceptorBinding
    public @interface Secure {
        /**
         * @return The EL expression that should be evaluated. If it evaluates to 
         * {@code true}, access will be granted. The EL expression may reference 
         * any objects that are in any context, as well as the arguments of the method,
         * under the names {@code arg0, arg1, arg2, ...}.
         */
        @Nonbinding
        String  value();
    }</p></div>
    ";i:3;s:13490:"
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
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;">@Interceptor
    @Secure<span style="color: #009900;">&#40;</span><span style="color: #0000ff;">&quot;&quot;</span><span style="color: #009900;">&#41;</span>
    <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000000; font-weight: bold;">class</span> SecurityInterceptor <span style="color: #009900;">&#123;</span>
        <span style="color: #000000; font-weight: bold;">private</span> <span style="color: #003399;">String</span> getArgName<span style="color: #009900;">&#40;</span><span style="color: #000066; font-weight: bold;">int</span> index<span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span> <span style="color: #000000; font-weight: bold;">return</span> <span style="color: #0000ff;">&quot;arg&quot;</span> <span style="color: #339933;">+</span> index<span style="color: #339933;">;</span> <span style="color: #009900;">&#125;</span>
    &nbsp;
        @AroundInvoke
        <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #003399;">Object</span> invoke<span style="color: #009900;">&#40;</span>InvocationContext ctx<span style="color: #009900;">&#41;</span> <span style="color: #000000; font-weight: bold;">throws</span> <span style="color: #003399;">Exception</span> <span style="color: #009900;">&#123;</span>
            FacesContext facesCtx <span style="color: #339933;">=</span> FacesContext.<span style="color: #006633;">getCurrentInstance</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
            ELContext elCtx <span style="color: #339933;">=</span> facesCtx.<span style="color: #006633;">getELContext</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
    &nbsp;
            Secure secure <span style="color: #339933;">=</span> getSecureAnnotation<span style="color: #009900;">&#40;</span>ctx.<span style="color: #006633;">getMethod</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
            <span style="color: #003399;">String</span> expression <span style="color: #339933;">=</span> secure.<span style="color: #006633;">value</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
    &nbsp;
            <span style="color: #666666; font-style: italic;">// Populating the request map so that parameters are available (arg0, ...)</span>
            Map<span style="color: #339933;">&lt;</span><span style="color: #003399;">String</span>, Object<span style="color: #339933;">&gt;</span> requestMap <span style="color: #339933;">=</span> facesCtx.<span style="color: #006633;">getExternalContext</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span>
                    .<span style="color: #006633;">getRequestMap</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
            <span style="color: #000000; font-weight: bold;">for</span> <span style="color: #009900;">&#40;</span><span style="color: #000066; font-weight: bold;">int</span> i <span style="color: #339933;">=</span> <span style="color: #cc66cc;">0</span><span style="color: #339933;">;</span> i <span style="color: #339933;">&lt;</span> ctx.<span style="color: #006633;">getParameters</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span>.<span style="color: #006633;">length</span><span style="color: #339933;">;</span> i<span style="color: #339933;">++</span><span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
                <span style="color: #003399;">Object</span> parameter <span style="color: #339933;">=</span> ctx.<span style="color: #006633;">getParameters</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#91;</span>i<span style="color: #009900;">&#93;</span><span style="color: #339933;">;</span>
                requestMap.<span style="color: #006633;">put</span><span style="color: #009900;">&#40;</span>getArgName<span style="color: #009900;">&#40;</span>i<span style="color: #009900;">&#41;</span>, parameter<span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
            <span style="color: #009900;">&#125;</span>
    &nbsp;
            <span style="color: #003399;">Boolean</span> expressionValue <span style="color: #339933;">=</span> <span style="color: #009900;">&#40;</span><span style="color: #003399;">Boolean</span><span style="color: #009900;">&#41;</span> facesCtx.<span style="color: #006633;">getApplication</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span>
                    .<span style="color: #006633;">getExpressionFactory</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span>
                    .<span style="color: #006633;">createValueExpression</span><span style="color: #009900;">&#40;</span>elCtx, expression, <span style="color: #003399;">Boolean</span>.<span style="color: #000000; font-weight: bold;">class</span><span style="color: #009900;">&#41;</span>
                    .<span style="color: #006633;">getValue</span><span style="color: #009900;">&#40;</span>elCtx<span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
    &nbsp;
            <span style="color: #666666; font-style: italic;">// Removing the parameters (arg0, arg1, ...)</span>
            <span style="color: #000000; font-weight: bold;">for</span> <span style="color: #009900;">&#40;</span><span style="color: #000066; font-weight: bold;">int</span> i <span style="color: #339933;">=</span> <span style="color: #cc66cc;">0</span><span style="color: #339933;">;</span> i <span style="color: #339933;">&lt;</span> ctx.<span style="color: #006633;">getParameters</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span>.<span style="color: #006633;">length</span><span style="color: #339933;">;</span> i<span style="color: #339933;">++</span><span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
                requestMap.<span style="color: #006633;">remove</span><span style="color: #009900;">&#40;</span>getArgName<span style="color: #009900;">&#40;</span>i<span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
            <span style="color: #009900;">&#125;</span>
    &nbsp;
            <span style="color: #000000; font-weight: bold;">if</span> <span style="color: #009900;">&#40;</span>expressionValue <span style="color: #339933;">==</span> <span style="color: #000066; font-weight: bold;">null</span> <span style="color: #339933;">||</span> <span style="color: #339933;">!</span>expressionValue<span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
                <span style="color: #000000; font-weight: bold;">throw</span> <span style="color: #000000; font-weight: bold;">new</span> <span style="color: #003399;">SecurityException</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
            <span style="color: #009900;">&#125;</span>
    &nbsp;
            <span style="color: #000000; font-weight: bold;">return</span> ctx.<span style="color: #006633;">proceed</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
        <span style="color: #009900;">&#125;</span>
    &nbsp;
        <span style="color: #000000; font-weight: bold;">private</span> Secure getSecureAnnotation<span style="color: #009900;">&#40;</span><span style="color: #003399;">Method</span> m<span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
            <span style="color: #000000; font-weight: bold;">for</span> <span style="color: #009900;">&#40;</span><span style="color: #003399;">Annotation</span> a<span style="color: #339933;">:</span> m.<span style="color: #006633;">getAnnotations</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
                <span style="color: #000000; font-weight: bold;">if</span> <span style="color: #009900;">&#40;</span>a <span style="color: #000000; font-weight: bold;">instanceof</span> Secure<span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span> <span style="color: #000000; font-weight: bold;">return</span> <span style="color: #009900;">&#40;</span>Secure<span style="color: #009900;">&#41;</span> a<span style="color: #339933;">;</span> <span style="color: #009900;">&#125;</span>
            <span style="color: #009900;">&#125;</span>
            <span style="color: #000000; font-weight: bold;">for</span> <span style="color: #009900;">&#40;</span><span style="color: #003399;">Annotation</span> a<span style="color: #339933;">:</span> m.<span style="color: #006633;">getDeclaringClass</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span>.<span style="color: #006633;">getAnnotations</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
                <span style="color: #000000; font-weight: bold;">if</span> <span style="color: #009900;">&#40;</span>a <span style="color: #000000; font-weight: bold;">instanceof</span> Secure<span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span> <span style="color: #000000; font-weight: bold;">return</span> <span style="color: #009900;">&#40;</span>Secure<span style="color: #009900;">&#41;</span> a<span style="color: #339933;">;</span> <span style="color: #009900;">&#125;</span>
            <span style="color: #009900;">&#125;</span>
    &nbsp;
            <span style="color: #000000; font-weight: bold;">throw</span> <span style="color: #000000; font-weight: bold;">new</span> <span style="color: #003399;">RuntimeException</span><span style="color: #009900;">&#40;</span><span style="color: #0000ff;">&quot;@Secure not found on method &quot;</span> <span style="color: #339933;">+</span> m.<span style="color: #006633;">getName</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span> <span style="color: #339933;">+</span>
                    <span style="color: #0000ff;">&quot; or its class &quot;</span> <span style="color: #339933;">+</span> m.<span style="color: #006633;">getClass</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span>.<span style="color: #006633;">getName</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
        <span style="color: #009900;">&#125;</span>
    <span style="color: #009900;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">@Interceptor
    @Secure(&quot;&quot;)
    public class SecurityInterceptor {
        private String getArgName(int index) { return &quot;arg&quot; + index; }
    
        @AroundInvoke
        public Object invoke(InvocationContext ctx) throws Exception {
            FacesContext facesCtx = FacesContext.getCurrentInstance();
            ELContext elCtx = facesCtx.getELContext();
    
            Secure secure = getSecureAnnotation(ctx.getMethod());
            String expression = secure.value();
    
            // Populating the request map so that parameters are available (arg0, ...)
            Map&lt;String, Object&gt; requestMap = facesCtx.getExternalContext()
                    .getRequestMap();
            for (int i = 0; i &lt; ctx.getParameters().length; i++) {
                Object parameter = ctx.getParameters()[i];
                requestMap.put(getArgName(i), parameter);
            }
    
            Boolean expressionValue = (Boolean) facesCtx.getApplication()
                    .getExpressionFactory()
                    .createValueExpression(elCtx, expression, Boolean.class)
                    .getValue(elCtx);
    
            // Removing the parameters (arg0, arg1, ...)
            for (int i = 0; i &lt; ctx.getParameters().length; i++) {
                requestMap.remove(getArgName(i));
            }
            
            if (expressionValue == null || !expressionValue) {
                throw new SecurityException();
            }
    
            return ctx.proceed();
        }
    
        private Secure getSecureAnnotation(Method m) {
            for (Annotation a: m.getAnnotations()) {
                if (a instanceof Secure) { return (Secure) a; }
            }
            for (Annotation a: m.getDeclaringClass().getAnnotations()) {
                if (a instanceof Secure) { return (Secure) a; }
            }
    
            throw new RuntimeException(&quot;@Secure not found on method &quot; + m.getName() +
                    &quot; or its class &quot; + m.getClass().getName());
        }
    }</p></div>
    ";i:4;s:1060:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    </pre></td><td class="code"><pre class="xml" style="font-family:monospace;"><span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;interceptors<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
       <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;class<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>util.security.SecurityInterceptor<span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/class<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
    <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/interceptors<span style="color: #000000; font-weight: bold;">&gt;</span></span></span></pre></td></tr></table><p class="theCode" style="display:none;">&lt;interceptors&gt;
       &lt;class&gt;util.security.SecurityInterceptor&lt;/class&gt;
    &lt;/interceptors&gt;</p></div>
    ";}
tags:
  - jee
  - java
  - jsf

---
Waiting for the [Seam3][1] security module, I wrote a simple security interceptor (inspired by the [Seam2 security annotation][2]). You can use it like this:

<pre lang="java" line="1">@Secure("#{loggedInUser.name == arg0.name}") 
public List&lt;Message> listMessages(User owner) { ... }
</pre>

Here, `loggedInUser` is a `@Named` Weld (CDI) bean, and `arg0` is the first argument of the method. This is a slight modification to the Seam2 security annotation, where it wasn&#8217;t possible to reference arguments. If the EL expression doesn&#8217;t evaluate to `true`, an exception is thrown.

The implementation is pretty straightforward, as adding an interceptor in Weld is really simple. First, we need the annotation:

<pre lang="java" line="1">/**
 * @author Adam Warski (adam at warski dot org)
 */
@Retention(RUNTIME)
@Target({TYPE, METHOD})
@InterceptorBinding
public @interface Secure {
    /**
     * @return The EL expression that should be evaluated. If it evaluates to 
     * {@code true}, access will be granted. The EL expression may reference 
     * any objects that are in any context, as well as the arguments of the method,
     * under the names {@code arg0, arg1, arg2, ...}.
     */
    @Nonbinding
    String  value();
}
</pre>

The key components here is the `@InterceptorBinding` meta-annotation, and specifying the value of to be `@Nonbinding`. If the value element was binding, then we would need to define an interceptor for each possible String value.

Next, the interceptor itself:

<pre lang="java" line="1">@Interceptor
@Secure("")
public class SecurityInterceptor {
    private String getArgName(int index) { return "arg" + index; }

    @AroundInvoke
    public Object invoke(InvocationContext ctx) throws Exception {
        FacesContext facesCtx = FacesContext.getCurrentInstance();
        ELContext elCtx = facesCtx.getELContext();

        Secure secure = getSecureAnnotation(ctx.getMethod());
        String expression = secure.value();

        // Populating the request map so that parameters are available (arg0, ...)
        Map&lt;String, Object> requestMap = facesCtx.getExternalContext()
                .getRequestMap();
        for (int i = 0; i &lt; ctx.getParameters().length; i++) {
            Object parameter = ctx.getParameters()[i];
            requestMap.put(getArgName(i), parameter);
        }

        Boolean expressionValue = (Boolean) facesCtx.getApplication()
                .getExpressionFactory()
                .createValueExpression(elCtx, expression, Boolean.class)
                .getValue(elCtx);

        // Removing the parameters (arg0, arg1, ...)
        for (int i = 0; i &lt; ctx.getParameters().length; i++) {
            requestMap.remove(getArgName(i));
        }
        
        if (expressionValue == null || !expressionValue) {
            throw new SecurityException();
        }

        return ctx.proceed();
    }

    private Secure getSecureAnnotation(Method m) {
        for (Annotation a: m.getAnnotations()) {
            if (a instanceof Secure) { return (Secure) a; }
        }
        for (Annotation a: m.getDeclaringClass().getAnnotations()) {
            if (a instanceof Secure) { return (Secure) a; }
        }

        throw new RuntimeException("@Secure not found on method " + m.getName() +
                " or its class " + m.getClass().getName());
    }
}
</pre>

And finally, we need an entry in `beans.xml`, enabling the interceptor:

<pre lang="xml" line="1">&lt;interceptors>
   &lt;class>util.security.SecurityInterceptor&lt;/class>
&lt;/interceptors>
</pre>

One shortcoming is that it's not currently possible to place one `@Secure` annotation on the class, and another on the methods (see [this][3] thread on the forum). The idea is that then the class-level annotation expresses general security constraints, which can be later refined on the method level.

Another missing feature, which could be easily added, is a message parameter to the annotation, which would be included in the exception in case the check fails.

Adam

 [1]: http://www.seamframework.org/Seam3
 [2]: http://docs.jboss.org/seam/2.2.1.CR1/reference/en-US/html/security.html#d0e11220
 [3]: http://www.seamframework.org/Community/AdditiveInterceptors
