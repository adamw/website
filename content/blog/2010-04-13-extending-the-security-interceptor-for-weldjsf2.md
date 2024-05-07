---
title: Extending the security interceptor for Weld/JSF2
author: Adam Warski
type: blog
date: 2010-04-13T09:22:52+00:00
url: /blog/2010/04/extending-the-security-interceptor-for-weldjsf2/
dsq_thread_id:
  - 1051935078
wp-syntax-cache-content:
  - |
    a:5:{i:1;s:897:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;">@Secure<span style="color: #009900;">&#40;</span><span style="color: #0000ff;">&quot;#{loggedInUser.name == arg0.name}&quot;</span><span style="color: #009900;">&#41;</span>  
    <span style="color: #000000; font-weight: bold;">public</span> List<span style="color: #339933;">&lt;</span>Message<span style="color: #339933;">&gt;</span> listMessages<span style="color: #009900;">&#40;</span>User owner<span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span> ... <span style="color: #009900;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">@Secure(&quot;#{loggedInUser.name == arg0.name}&quot;)  
    public List&lt;Message&gt; listMessages(User owner) { ... }</p></div>
    ";i:2;s:2224:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    6
    7
    8
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;">@Secure<span style="color: #009900;">&#40;</span><span style="color: #0000ff;">&quot;#{loggedInUser != null}&quot;</span><span style="color: #009900;">&#41;</span>  
    <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000000; font-weight: bold;">class</span> Messages <span style="color: #009900;">&#123;</span>
       @AdminOnly
       <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000066; font-weight: bold;">void</span> deleteAllMessages<span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span> ... <span style="color: #009900;">&#125;</span>
    &nbsp;
       @Secure<span style="color: #009900;">&#40;</span><span style="color: #0000ff;">&quot;#{loggedInUser.maxMessageListCount == count}&quot;</span><span style="color: #009900;">&#41;</span>
       <span style="color: #000000; font-weight: bold;">public</span> List<span style="color: #339933;">&lt;</span>Message<span style="color: #339933;">&gt;</span> listMessages<span style="color: #009900;">&#40;</span>@ELVar<span style="color: #009900;">&#40;</span><span style="color: #0000ff;">&quot;user&quot;</span><span style="color: #009900;">&#41;</span> User owner, @ELVar<span style="color: #009900;">&#40;</span><span style="color: #0000ff;">&quot;count&quot;</span><span style="color: #009900;">&#41;</span> <span style="color: #000066; font-weight: bold;">int</span> count<span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span> ... <span style="color: #009900;">&#125;</span>
    <span style="color: #009900;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">@Secure(&quot;#{loggedInUser != null}&quot;)  
    public class Messages {
       @AdminOnly
       public void deleteAllMessages() { ... }
    
       @Secure(&quot;#{loggedInUser.maxMessageListCount == count}&quot;)
       public List&lt;Message&gt; listMessages(@ELVar(&quot;user&quot;) User owner, @ELVar(&quot;count&quot;) int count) { ... }
    }</p></div>
    ";i:3;s:766:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;">@SecureBinding
    @Secure<span style="color: #009900;">&#40;</span><span style="color: #0000ff;">&quot;#{loggedInUser.isAdministrator}&quot;</span><span style="color: #009900;">&#41;</span>
    <span style="color: #000000; font-weight: bold;">public</span> @<span style="color: #000000; font-weight: bold;">interface</span> AdminOnly <span style="color: #009900;">&#123;</span> <span style="color: #009900;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">@SecureBinding
    @Secure(&quot;#{loggedInUser.isAdministrator}&quot;)
    public @interface AdminOnly { }</p></div>
    ";i:4;s:20050:"
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
    72
    73
    74
    75
    76
    77
    78
    79
    80
    81
    82
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;">@InterceptorBinding
    <span style="color: #000000; font-weight: bold;">public</span> @<span style="color: #000000; font-weight: bold;">interface</span> InterceptSecure <span style="color: #009900;">&#123;</span>
        @Nonbinding
        <span style="color: #003399;">String</span><span style="color: #009900;">&#91;</span><span style="color: #009900;">&#93;</span>    value<span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
    <span style="color: #009900;">&#125;</span>
    &nbsp;
    <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000000; font-weight: bold;">class</span> SecurityExtension <span style="color: #000000; font-weight: bold;">implements</span> Extension <span style="color: #009900;">&#123;</span>
        <span style="color: #000000; font-weight: bold;">private</span> <span style="color: #000000; font-weight: bold;">final</span> Map<span style="color: #339933;">&lt;</span><span style="color: #003399;">Method</span>, InterceptSecure<span style="color: #339933;">&gt;</span> interceptSecureForMethods <span style="color: #339933;">=</span> <span style="color: #000000; font-weight: bold;">new</span> HashMap<span style="color: #339933;">&lt;</span><span style="color: #003399;">Method</span>, InterceptSecure<span style="color: #339933;">&gt;</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
    &nbsp;
        <span style="color: #000000; font-weight: bold;">public</span> InterceptSecure getInterceptSecure<span style="color: #009900;">&#40;</span><span style="color: #003399;">Method</span> m<span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
            <span style="color: #000000; font-weight: bold;">return</span> interceptSecureForMethods.<span style="color: #006633;">get</span><span style="color: #009900;">&#40;</span>m<span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
        <span style="color: #009900;">&#125;</span>
    &nbsp;
        <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #339933;">&lt;</span>T<span style="color: #339933;">&gt;</span> <span style="color: #000066; font-weight: bold;">void</span> processAnnotatedType<span style="color: #009900;">&#40;</span>@Observes ProcessAnnotatedType<span style="color: #339933;">&lt;</span>T<span style="color: #339933;">&gt;</span> event<span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
            <span style="color: #666666; font-style: italic;">// A flag indicating if the builder was used to modify the annotations</span>
            <span style="color: #000066; font-weight: bold;">boolean</span> used <span style="color: #339933;">=</span> <span style="color: #000066; font-weight: bold;">false</span><span style="color: #339933;">;</span>
            NewAnnotatedTypeBuilder<span style="color: #339933;">&lt;</span>T<span style="color: #339933;">&gt;</span> builder <span style="color: #339933;">=</span> <span style="color: #000000; font-weight: bold;">new</span> NewAnnotatedTypeBuilder<span style="color: #339933;">&lt;</span>T<span style="color: #339933;">&gt;</span><span style="color: #009900;">&#40;</span>event.<span style="color: #006633;">getAnnotatedType</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
    &nbsp;
            <span style="color: #666666; font-style: italic;">// We need to read the values of the @Secure annotation that are present on:</span>
            <span style="color: #666666; font-style: italic;">// 1. types (classes)</span>
            <span style="color: #666666; font-style: italic;">// 2. methods</span>
            <span style="color: #666666; font-style: italic;">// 3. arbitrarily nested on @SecureBinding annotations</span>
    &nbsp;
            <span style="color: #666666; font-style: italic;">// Gathering the initial secure values from the type</span>
            List<span style="color: #339933;">&lt;</span>String<span style="color: #339933;">&gt;</span> initialSecureValues <span style="color: #339933;">=</span> <span style="color: #000000; font-weight: bold;">new</span> ArrayList<span style="color: #339933;">&lt;</span>String<span style="color: #339933;">&gt;</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
            <span style="color: #000000; font-weight: bold;">for</span> <span style="color: #009900;">&#40;</span><span style="color: #003399;">Annotation</span> annotation <span style="color: #339933;">:</span> event.<span style="color: #006633;">getAnnotatedType</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span>.<span style="color: #006633;">getAnnotations</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
                collectSecureValues<span style="color: #009900;">&#40;</span>annotation, initialSecureValues<span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
            <span style="color: #009900;">&#125;</span>
    &nbsp;
            <span style="color: #000000; font-weight: bold;">for</span> <span style="color: #009900;">&#40;</span>AnnotatedMethod<span style="color: #339933;">&lt;?&gt;</span> m <span style="color: #339933;">:</span> event.<span style="color: #006633;">getAnnotatedType</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span>.<span style="color: #006633;">getMethods</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
                <span style="color: #666666; font-style: italic;">// Gathering the secure values from the method</span>
                <span style="color: #000000; font-weight: bold;">final</span> List<span style="color: #339933;">&lt;</span>String<span style="color: #339933;">&gt;</span> values <span style="color: #339933;">=</span> <span style="color: #000000; font-weight: bold;">new</span> ArrayList<span style="color: #339933;">&lt;</span>String<span style="color: #339933;">&gt;</span><span style="color: #009900;">&#40;</span>initialSecureValues<span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
                collectSecureValues<span style="color: #009900;">&#40;</span>m, values<span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
    &nbsp;
                <span style="color: #666666; font-style: italic;">// If any values have been gathered, adding the annotation to the method and storing it</span>
                <span style="color: #666666; font-style: italic;">// in the map.</span>
                <span style="color: #000000; font-weight: bold;">if</span> <span style="color: #009900;">&#40;</span>values.<span style="color: #006633;">size</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span> <span style="color: #339933;">&gt;</span> <span style="color: #cc66cc;">0</span><span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
                    InterceptSecure is <span style="color: #339933;">=</span> <span style="color: #000000; font-weight: bold;">new</span> InterceptSecureImpl<span style="color: #009900;">&#40;</span>values.<span style="color: #006633;">toArray</span><span style="color: #009900;">&#40;</span><span style="color: #000000; font-weight: bold;">new</span> <span style="color: #003399;">String</span><span style="color: #009900;">&#91;</span>values.<span style="color: #006633;">size</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#93;</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
                    builder.<span style="color: #006633;">addToMethod</span><span style="color: #009900;">&#40;</span>m.<span style="color: #006633;">getJavaMember</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span>, is<span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
                    used <span style="color: #339933;">=</span> <span style="color: #000066; font-weight: bold;">true</span><span style="color: #339933;">;</span>
    &nbsp;
                    interceptSecureForMethods.<span style="color: #006633;">put</span><span style="color: #009900;">&#40;</span>m.<span style="color: #006633;">getJavaMember</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span>, is<span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
                <span style="color: #009900;">&#125;</span>
            <span style="color: #009900;">&#125;</span>
    &nbsp;
            <span style="color: #666666; font-style: italic;">// Setting the new annotated type, if any changed were made</span>
            <span style="color: #000000; font-weight: bold;">if</span> <span style="color: #009900;">&#40;</span>used<span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
                event.<span style="color: #006633;">setAnnotatedType</span><span style="color: #009900;">&#40;</span>builder.<span style="color: #006633;">create</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
            <span style="color: #009900;">&#125;</span>
        <span style="color: #009900;">&#125;</span>
    &nbsp;
        <span style="color: #000000; font-weight: bold;">private</span> <span style="color: #000066; font-weight: bold;">void</span> collectSecureValues<span style="color: #009900;">&#40;</span>AnnotatedMethod m, List<span style="color: #339933;">&lt;</span>String<span style="color: #339933;">&gt;</span> values<span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
            <span style="color: #000000; font-weight: bold;">for</span> <span style="color: #009900;">&#40;</span><span style="color: #003399;">Annotation</span> annotation <span style="color: #339933;">:</span> m.<span style="color: #006633;">getAnnotations</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
                collectSecureValues<span style="color: #009900;">&#40;</span>annotation, values<span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
            <span style="color: #009900;">&#125;</span>
        <span style="color: #009900;">&#125;</span>
    &nbsp;
        <span style="color: #000000; font-weight: bold;">private</span> <span style="color: #000066; font-weight: bold;">void</span> collectSecureValues<span style="color: #009900;">&#40;</span><span style="color: #003399;">Annotation</span> annotation, List<span style="color: #339933;">&lt;</span>String<span style="color: #339933;">&gt;</span> values<span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
            <span style="color: #000000; font-weight: bold;">if</span> <span style="color: #009900;">&#40;</span>Secure.<span style="color: #000000; font-weight: bold;">class</span>.<span style="color: #006633;">isAssignableFrom</span><span style="color: #009900;">&#40;</span>annotation.<span style="color: #006633;">annotationType</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
                values.<span style="color: #006633;">add</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#40;</span>Secure<span style="color: #009900;">&#41;</span> annotation<span style="color: #009900;">&#41;</span>.<span style="color: #006633;">value</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
            <span style="color: #009900;">&#125;</span> <span style="color: #000000; font-weight: bold;">else</span> <span style="color: #009900;">&#123;</span>
                <span style="color: #000000; font-weight: bold;">if</span> <span style="color: #009900;">&#40;</span>annotation.<span style="color: #006633;">annotationType</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span>.<span style="color: #006633;">getAnnotation</span><span style="color: #009900;">&#40;</span>SecureBinding.<span style="color: #000000; font-weight: bold;">class</span><span style="color: #009900;">&#41;</span> <span style="color: #339933;">!=</span> <span style="color: #000066; font-weight: bold;">null</span><span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
                    <span style="color: #000000; font-weight: bold;">for</span> <span style="color: #009900;">&#40;</span><span style="color: #003399;">Annotation</span> nestedAnnotation <span style="color: #339933;">:</span> annotation.<span style="color: #006633;">annotationType</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span>.<span style="color: #006633;">getAnnotations</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
                        collectSecureValues<span style="color: #009900;">&#40;</span>nestedAnnotation, values<span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
                    <span style="color: #009900;">&#125;</span>
                <span style="color: #009900;">&#125;</span>
            <span style="color: #009900;">&#125;</span>
        <span style="color: #009900;">&#125;</span>
    &nbsp;
        <span style="color: #000000; font-weight: bold;">private</span> <span style="color: #000000; font-weight: bold;">static</span> <span style="color: #000000; font-weight: bold;">class</span> InterceptSecureImpl <span style="color: #000000; font-weight: bold;">extends</span> AnnotationLiteral<span style="color: #339933;">&lt;</span>InterceptSecure<span style="color: #339933;">&gt;</span> <span style="color: #000000; font-weight: bold;">implements</span> InterceptSecure <span style="color: #009900;">&#123;</span>
            <span style="color: #000000; font-weight: bold;">private</span> <span style="color: #000000; font-weight: bold;">final</span> <span style="color: #003399;">String</span><span style="color: #009900;">&#91;</span><span style="color: #009900;">&#93;</span> values<span style="color: #339933;">;</span>
    &nbsp;
            <span style="color: #000000; font-weight: bold;">private</span> InterceptSecureImpl<span style="color: #009900;">&#40;</span><span style="color: #003399;">String</span><span style="color: #009900;">&#91;</span><span style="color: #009900;">&#93;</span> values<span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
                <span style="color: #000000; font-weight: bold;">this</span>.<span style="color: #006633;">values</span> <span style="color: #339933;">=</span> values<span style="color: #339933;">;</span>
            <span style="color: #009900;">&#125;</span>
    &nbsp;
            @Override
            <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #003399;">String</span><span style="color: #009900;">&#91;</span><span style="color: #009900;">&#93;</span> value<span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
                <span style="color: #000000; font-weight: bold;">return</span> values<span style="color: #339933;">;</span>
            <span style="color: #009900;">&#125;</span>
        <span style="color: #009900;">&#125;</span>
    <span style="color: #009900;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">@InterceptorBinding
    public @interface InterceptSecure {
        @Nonbinding
        String[]    value();
    }
    
    public class SecurityExtension implements Extension {
        private final Map&lt;Method, InterceptSecure&gt; interceptSecureForMethods = new HashMap&lt;Method, InterceptSecure&gt;();
    
        public InterceptSecure getInterceptSecure(Method m) {
            return interceptSecureForMethods.get(m);
        }
    
        public &lt;T&gt; void processAnnotatedType(@Observes ProcessAnnotatedType&lt;T&gt; event) {
            // A flag indicating if the builder was used to modify the annotations
            boolean used = false;
            NewAnnotatedTypeBuilder&lt;T&gt; builder = new NewAnnotatedTypeBuilder&lt;T&gt;(event.getAnnotatedType());
    
            // We need to read the values of the @Secure annotation that are present on:
            // 1. types (classes)
            // 2. methods
            // 3. arbitrarily nested on @SecureBinding annotations
    
            // Gathering the initial secure values from the type
            List&lt;String&gt; initialSecureValues = new ArrayList&lt;String&gt;();
            for (Annotation annotation : event.getAnnotatedType().getAnnotations()) {
                collectSecureValues(annotation, initialSecureValues);
            }
    
            for (AnnotatedMethod&lt;?&gt; m : event.getAnnotatedType().getMethods()) {
                // Gathering the secure values from the method
                final List&lt;String&gt; values = new ArrayList&lt;String&gt;(initialSecureValues);
                collectSecureValues(m, values);
    
                // If any values have been gathered, adding the annotation to the method and storing it
                // in the map.
                if (values.size() &gt; 0) {
                    InterceptSecure is = new InterceptSecureImpl(values.toArray(new String[values.size()]));
                    builder.addToMethod(m.getJavaMember(), is);
                    used = true;
    
                    interceptSecureForMethods.put(m.getJavaMember(), is);
                }
            }
    
            // Setting the new annotated type, if any changed were made
            if (used) {
                event.setAnnotatedType(builder.create());
            }
        }
    
        private void collectSecureValues(AnnotatedMethod m, List&lt;String&gt; values) {
            for (Annotation annotation : m.getAnnotations()) {
                collectSecureValues(annotation, values);
            }
        }
    
        private void collectSecureValues(Annotation annotation, List&lt;String&gt; values) {
            if (Secure.class.isAssignableFrom(annotation.annotationType())) {
                values.add(((Secure) annotation).value());
            } else {
                if (annotation.annotationType().getAnnotation(SecureBinding.class) != null) {
                    for (Annotation nestedAnnotation : annotation.annotationType().getAnnotations()) {
                        collectSecureValues(nestedAnnotation, values);
                    }
                }
            }
        }
    
        private static class InterceptSecureImpl extends AnnotationLiteral&lt;InterceptSecure&gt; implements InterceptSecure {
            private final String[] values;
    
            private InterceptSecureImpl(String[] values) {
                this.values = values;
            }
    
            @Override
            public String[] value() {
                return values;
            }
        }
    }</p></div>
    ";i:5;s:4564:"
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
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;">@Interceptor
    @InterceptSecure<span style="color: #009900;">&#40;</span><span style="color: #0000ff;">&quot;&quot;</span><span style="color: #009900;">&#41;</span>
    <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000000; font-weight: bold;">class</span> SecurityInterceptor <span style="color: #009900;">&#123;</span>
        @Inject
        <span style="color: #000000; font-weight: bold;">private</span> SecurityExtension se<span style="color: #339933;">;</span>
    &nbsp;
        @AroundInvoke
        <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #003399;">Object</span> checkSecurity<span style="color: #009900;">&#40;</span>InvocationContext ctx<span style="color: #009900;">&#41;</span> <span style="color: #000000; font-weight: bold;">throws</span> <span style="color: #003399;">Exception</span> <span style="color: #009900;">&#123;</span>
            <span style="color: #666666; font-style: italic;">// Getting the generated @InterceptSecure annotation for the method.</span>
            <span style="color: #666666; font-style: italic;">// After the CDI Maintenance Release is released, it should be possible to get the annotated</span>
            <span style="color: #666666; font-style: italic;">// type of the currently invoked bean, see:</span>
            <span style="color: #666666; font-style: italic;">// http://old.nabble.com/Retrieving-the-Bean-object-for-an-interceptor-td28147499.html</span>
            <span style="color: #666666; font-style: italic;">// For now, we just use a map in the extension. One limitation is that this doesn't allow</span>
            <span style="color: #666666; font-style: italic;">// different security annotations for methods which are used in several beans.</span>
            InterceptSecure is <span style="color: #339933;">=</span> se.<span style="color: #006633;">getInterceptSecure</span><span style="color: #009900;">&#40;</span>ctx.<span style="color: #006633;">getMethod</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
            <span style="color: #003399;">String</span><span style="color: #009900;">&#91;</span><span style="color: #009900;">&#93;</span> toCheck <span style="color: #339933;">=</span> is <span style="color: #339933;">==</span> <span style="color: #000066; font-weight: bold;">null</span> <span style="color: #339933;">?</span> <span style="color: #000066; font-weight: bold;">null</span> <span style="color: #339933;">:</span> is.<span style="color: #006633;">value</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
    &nbsp;
            <span style="color: #666666; font-style: italic;">// Check the el conditions as in the previous post </span>
            <span style="color: #666666; font-style: italic;">// (...)</span>
    &nbsp;
            <span style="color: #000000; font-weight: bold;">return</span> ctx.<span style="color: #006633;">proceed</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
        <span style="color: #009900;">&#125;</span>
    <span style="color: #009900;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">@Interceptor
    @InterceptSecure(&quot;&quot;)
    public class SecurityInterceptor {
        @Inject
        private SecurityExtension se;
    
        @AroundInvoke
        public Object checkSecurity(InvocationContext ctx) throws Exception {
            // Getting the generated @InterceptSecure annotation for the method.
            // After the CDI Maintenance Release is released, it should be possible to get the annotated
            // type of the currently invoked bean, see:
            // http://old.nabble.com/Retrieving-the-Bean-object-for-an-interceptor-td28147499.html
            // For now, we just use a map in the extension. One limitation is that this doesn't allow
            // different security annotations for methods which are used in several beans.
            InterceptSecure is = se.getInterceptSecure(ctx.getMethod());
            String[] toCheck = is == null ? null : is.value();
    
            // Check the el conditions as in the previous post 
            // (...)
    
            return ctx.proceed();
        }
    }</p></div>
    ";}
tags:
  - jee
  - java
  - jsf

---
In my [previous][1] post, I described how to create a simple security interceptor, which checks conditions defined using EL expressions, e.g.:

<pre lang="java" line="1">@Secure("#{loggedInUser.name == arg0.name}")  
public List&lt;Message> listMessages(User owner) { ... }  
</pre>

Now, it would be nice to be able to stack such annotations, so that they can be placed:  
* on methods  
* on classes &#8211; then the constraint applies to all methods  
* on other annotations, to create &#8220;security bindings&#8221;

An example usage could be:

<pre lang="java" line="1">@Secure("#{loggedInUser != null}")  
public class Messages {
   @AdminOnly
   public void deleteAllMessages() { ... }

   @Secure("#{loggedInUser.maxMessageListCount == count}")
   public List&lt;Message> listMessages(@ELVar("user") User owner, @ELVar("count") int count) { ... }
}  
</pre>

where `@AdminOnly` is defined as:

<pre lang="java" line="1">@SecureBinding
@Secure("#{loggedInUser.isAdministrator}")
public @interface AdminOnly { }
</pre>

This way common security constraints can be expressed as annotations or on the class. I&#8217;m also using an improvement suggested by [Dan Allen][2], to name the method arguments using `@ELVar`, instead of naming them `arg0`, `arg1`, etc. This also allows to refer to method arguments in the &#8220;security binding&#8221; annotations, whatever the position of the argument is.

How to implement such annotations? Well, the first step is to create a portable extension (meaning it will work with any CDI implementation, not just Weld), which will gather, for each method, all the `@Secure` annotations, and their values, into one single annotation, `@InterceptSecure`. Having such a set of constraints to check for each method, we add the annotation to the method meta-data, using a utility class from [Weld Extensions][3]: the `NewAnnotatedTypeBuilder`. As the `@InterceptSecure` is an interceptor binding, the security interceptor will be called whenever the method is invoked.

The extension observes the `ProcessAnnotatedType` event, which is fired for each bean type. If necessary, the type can be modified, to include the new annotations. The annotation is only added to Weld meta-data, not to the method itself (so there&#8217;s no bytecode manipulation or such).

One last obstacle to overcome is to get the value of the generated `@InterceptSecure` annotation in the interceptor. Currently this is not possible using e.g. `BeanManager`, but should be address in CDI Maintenance Release (see [here][4]), so as a temporary solution all the generated annotations are stored in a map in the extension. All extensions are application-scoped beans, so the information can be accessed from the interceptor. One shortcoming of the solution is that one method may belong to several, differently annotated CDI beans.

The code for the annotation and extension:

<pre lang="java" line="1">@InterceptorBinding
public @interface InterceptSecure {
    @Nonbinding
    String[]    value();
}

public class SecurityExtension implements Extension {
    private final Map&lt;Method, InterceptSecure> interceptSecureForMethods = new HashMap&lt;Method, InterceptSecure>();

    public InterceptSecure getInterceptSecure(Method m) {
        return interceptSecureForMethods.get(m);
    }

    public &lt;T> void processAnnotatedType(@Observes ProcessAnnotatedType&lt;T> event) {
        // A flag indicating if the builder was used to modify the annotations
        boolean used = false;
        NewAnnotatedTypeBuilder&lt;T> builder = new NewAnnotatedTypeBuilder&lt;T>(event.getAnnotatedType());

        // We need to read the values of the @Secure annotation that are present on:
        // 1. types (classes)
        // 2. methods
        // 3. arbitrarily nested on @SecureBinding annotations

        // Gathering the initial secure values from the type
        List&lt;String> initialSecureValues = new ArrayList&lt;String>();
        for (Annotation annotation : event.getAnnotatedType().getAnnotations()) {
            collectSecureValues(annotation, initialSecureValues);
        }

        for (AnnotatedMethod<?> m : event.getAnnotatedType().getMethods()) {
            // Gathering the secure values from the method
            final List&lt;String> values = new ArrayList&lt;String>(initialSecureValues);
            collectSecureValues(m, values);

            // If any values have been gathered, adding the annotation to the method and storing it
            // in the map.
            if (values.size() > 0) {
                InterceptSecure is = new InterceptSecureImpl(values.toArray(new String[values.size()]));
                builder.addToMethod(m.getJavaMember(), is);
                used = true;

                interceptSecureForMethods.put(m.getJavaMember(), is);
            }
        }

        // Setting the new annotated type, if any changed were made
        if (used) {
            event.setAnnotatedType(builder.create());
        }
    }

    private void collectSecureValues(AnnotatedMethod m, List&lt;String> values) {
        for (Annotation annotation : m.getAnnotations()) {
            collectSecureValues(annotation, values);
        }
    }

    private void collectSecureValues(Annotation annotation, List&lt;String> values) {
        if (Secure.class.isAssignableFrom(annotation.annotationType())) {
            values.add(((Secure) annotation).value());
        } else {
            if (annotation.annotationType().getAnnotation(SecureBinding.class) != null) {
                for (Annotation nestedAnnotation : annotation.annotationType().getAnnotations()) {
                    collectSecureValues(nestedAnnotation, values);
                }
            }
        }
    }

    private static class InterceptSecureImpl extends AnnotationLiteral&lt;InterceptSecure> implements InterceptSecure {
        private final String[] values;

        private InterceptSecureImpl(String[] values) {
            this.values = values;
        }

        @Override
        public String[] value() {
            return values;
        }
    }
}
</pre>

And for the interceptor:

<pre lang="java" line="1">@Interceptor
@InterceptSecure("")
public class SecurityInterceptor {
    @Inject
    private SecurityExtension se;

    @AroundInvoke
    public Object checkSecurity(InvocationContext ctx) throws Exception {
        // Getting the generated @InterceptSecure annotation for the method.
        // After the CDI Maintenance Release is released, it should be possible to get the annotated
        // type of the currently invoked bean, see:
        // http://old.nabble.com/Retrieving-the-Bean-object-for-an-interceptor-td28147499.html
        // For now, we just use a map in the extension. One limitation is that this doesn't allow
        // different security annotations for methods which are used in several beans.
        InterceptSecure is = se.getInterceptSecure(ctx.getMethod());
        String[] toCheck = is == null ? null : is.value();

        // Check the el conditions as in the previous post 
        // (...)

        return ctx.proceed();
    }
}
</pre>

Adam

 [1]: http://www.warski.org/blog/?p=197
 [2]: http://in.relation.to/Bloggers/Dan
 [3]: http://www.seamframework.org/Documentation/WeldExtensions
 [4]: http://old.nabble.com/Retrieving-the-Bean-object-for-an-interceptor-td28147499.html
