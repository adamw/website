---
title: 'JSF2 navigation: post->redirect->get'
author: Adam Warski
type: blog
date: 2010-03-11T20:25:07+00:00
url: /blog/2010/03/jsf2-navigation-post-redirect-get/
disqus_identifier: 1051065533
wp-syntax-cache-content:
  - |
    a:6:{i:1;s:1783:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    </pre></td><td class="code"><pre class="xml" style="font-family:monospace;"><span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;f:metadata<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
       <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;f:viewParam</span> <span style="color: #000066;">name</span>=<span style="color: #ff0000;">&quot;entry_id&quot;</span> <span style="color: #000066;">value</span>=<span style="color: #ff0000;">&quot;#{blog.entry}&quot;</span> <span style="color: #000066;">required</span>=<span style="color: #ff0000;">&quot;true&quot;</span><span style="color: #000000; font-weight: bold;">&gt;</span></span>
          <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;f:converter</span> <span style="color: #000066;">converterId</span>=<span style="color: #ff0000;">&quot;blog-entry-converter&quot;</span> <span style="color: #000000; font-weight: bold;">/&gt;</span></span>
       <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/f:viewParam<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
    <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/f:metadata<span style="color: #000000; font-weight: bold;">&gt;</span></span></span></pre></td></tr></table><p class="theCode" style="display:none;">&lt;f:metadata&gt;
       &lt;f:viewParam name=&quot;entry_id&quot; value=&quot;#{blog.entry}&quot; required=&quot;true&quot;&gt;
          &lt;f:converter converterId=&quot;blog-entry-converter&quot; /&gt;
       &lt;/f:viewParam&gt;
    &lt;/f:metadata&gt;</p></div>
    ";i:2;s:935:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;"><span style="color: #000000; font-weight: bold;">public</span> <span style="color: #003399;">String</span> action<span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
         <span style="color: #666666; font-style: italic;">// business logic ...</span>
         <span style="color: #000000; font-weight: bold;">return</span> <span style="color: #0000ff;">&quot;view.xhtml?faces-redirect=true&amp;includeViewParams=true&quot;</span>
    <span style="color: #009900;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">public String action() {
         // business logic ...
         return &quot;view.xhtml?faces-redirect=true&amp;includeViewParams=true&quot;
    }</p></div>
    ";i:3;s:1541:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    6
    7
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;">@Inject
    <span style="color: #000000; font-weight: bold;">private</span> Nav nav<span style="color: #339933;">;</span>
    &nbsp;
    <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #003399;">String</span> action<span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
         <span style="color: #666666; font-style: italic;">// business logic ...</span>
         <span style="color: #000000; font-weight: bold;">return</span> nav.<span style="color: #006633;">getViewEntry</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span>.<span style="color: #006633;">redirect</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span>.<span style="color: #006633;">includeViewParams</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span>.<span style="color: #006633;">s</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
    <span style="color: #009900;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">@Inject
    private Nav nav;
    
    public String action() {
         // business logic ...
         return nav.getViewEntry().redirect().includeViewParams().s();
    }</p></div>
    ";i:4;s:1448:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;"><span style="color: #000000; font-weight: bold;">public</span> <span style="color: #003399;">String</span> action<span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
         <span style="color: #666666; font-style: italic;">// business logic ...</span>
         <span style="color: #000000; font-weight: bold;">return</span> nav.<span style="color: #006633;">getViewEntry</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span>.<span style="color: #006633;">redirect</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span>.<span style="color: #006633;">includeViewParam</span><span style="color: #009900;">&#40;</span><span style="color: #0000ff;">&quot;name&quot;</span><span style="color: #009900;">&#41;</span>.<span style="color: #006633;">s</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
    <span style="color: #009900;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">public String action() {
         // business logic ...
         return nav.getViewEntry().redirect().includeViewParam(&quot;name&quot;).s();
    }</p></div>
    ";i:5;s:770:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    </pre></td><td class="code"><pre class="xml" style="font-family:monospace;"><span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;h:link</span> <span style="color: #000066;">outcome</span>=<span style="color: #ff0000;">&quot;#{nav.manageIndex.s}&quot;</span><span style="color: #000000; font-weight: bold;">&gt;</span></span>Manage<span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/h:link<span style="color: #000000; font-weight: bold;">&gt;</span></span></span></pre></td></tr></table><p class="theCode" style="display:none;">&lt;h:link outcome=&quot;#{nav.manageIndex.s}&quot;&gt;Manage&lt;/h:link&gt;</p></div>
    ";i:6;s:21275:"
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
    83
    84
    85
    86
    87
    88
    89
    90
    91
    92
    93
    94
    95
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;"><span style="color: #008000; font-style: italic; font-weight: bold;">/**
     * @author Adam Warski (adam at warski dot org)
     */</span>
    @Named
    @ApplicationScoped
    <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000000; font-weight: bold;">class</span> Nav <span style="color: #009900;">&#123;</span>
        <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000000; font-weight: bold;">static</span> <span style="color: #000000; font-weight: bold;">class</span> Page <span style="color: #009900;">&#123;</span>
            <span style="color: #000000; font-weight: bold;">private</span> <span style="color: #000000; font-weight: bold;">final</span> <span style="color: #003399;">String</span> viewId<span style="color: #339933;">;</span>
            <span style="color: #000000; font-weight: bold;">private</span> <span style="color: #000000; font-weight: bold;">final</span> Map<span style="color: #339933;">&lt;</span><span style="color: #003399;">String</span>, String<span style="color: #339933;">&gt;</span> params<span style="color: #339933;">;</span>
    &nbsp;
            <span style="color: #000000; font-weight: bold;">private</span> Page<span style="color: #009900;">&#40;</span><span style="color: #003399;">String</span> viewId<span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
                <span style="color: #000000; font-weight: bold;">this</span>.<span style="color: #006633;">viewId</span> <span style="color: #339933;">=</span> viewId<span style="color: #339933;">;</span>
                <span style="color: #000000; font-weight: bold;">this</span>.<span style="color: #006633;">params</span> <span style="color: #339933;">=</span> <span style="color: #000000; font-weight: bold;">new</span> LinkedHashMap<span style="color: #339933;">&lt;</span><span style="color: #003399;">String</span>, String<span style="color: #339933;">&gt;</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
            <span style="color: #009900;">&#125;</span>
    &nbsp;
            <span style="color: #000000; font-weight: bold;">private</span> Page<span style="color: #009900;">&#40;</span><span style="color: #003399;">String</span> viewId, Map<span style="color: #339933;">&lt;</span><span style="color: #003399;">String</span>, String<span style="color: #339933;">&gt;</span> params<span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
                <span style="color: #000000; font-weight: bold;">this</span>.<span style="color: #006633;">viewId</span> <span style="color: #339933;">=</span> viewId<span style="color: #339933;">;</span>
                <span style="color: #000000; font-weight: bold;">this</span>.<span style="color: #006633;">params</span> <span style="color: #339933;">=</span> params<span style="color: #339933;">;</span>
            <span style="color: #009900;">&#125;</span>
    &nbsp;
            <span style="color: #000000; font-weight: bold;">public</span> Page redirect<span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
                <span style="color: #000000; font-weight: bold;">return</span> includeParam<span style="color: #009900;">&#40;</span><span style="color: #0000ff;">&quot;faces-redirect&quot;</span>, <span style="color: #0000ff;">&quot;true&quot;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
            <span style="color: #009900;">&#125;</span>
    &nbsp;
            <span style="color: #000000; font-weight: bold;">public</span> Page includeViewParams<span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
                <span style="color: #000000; font-weight: bold;">return</span> includeParam<span style="color: #009900;">&#40;</span><span style="color: #0000ff;">&quot;includeViewParams&quot;</span>, <span style="color: #0000ff;">&quot;true&quot;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
            <span style="color: #009900;">&#125;</span>
    &nbsp;
            <span style="color: #000000; font-weight: bold;">public</span> Page includeViewParam<span style="color: #009900;">&#40;</span><span style="color: #003399;">String</span> name<span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
                <span style="color: #666666; font-style: italic;">// Getting the metadata facet of the view</span>
                FacesContext ctx <span style="color: #339933;">=</span> FacesContext.<span style="color: #006633;">getCurrentInstance</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
                ViewDeclarationLanguage vdl <span style="color: #339933;">=</span> ctx.<span style="color: #006633;">getApplication</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span>.<span style="color: #006633;">getViewHandler</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span>
                      .<span style="color: #006633;">getViewDeclarationLanguage</span><span style="color: #009900;">&#40;</span>ctx, viewId<span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
                ViewMetadata viewMetadata <span style="color: #339933;">=</span> vdl.<span style="color: #006633;">getViewMetadata</span><span style="color: #009900;">&#40;</span>ctx, viewId<span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
                UIViewRoot viewRoot <span style="color: #339933;">=</span> viewMetadata.<span style="color: #006633;">createMetadataView</span><span style="color: #009900;">&#40;</span>ctx<span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
                UIComponent metadataFacet <span style="color: #339933;">=</span> viewRoot.<span style="color: #006633;">getFacet</span><span style="color: #009900;">&#40;</span>
                      UIViewRoot.<span style="color: #006633;">METADATA_FACET_NAME</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
    &nbsp;
                <span style="color: #666666; font-style: italic;">// Looking for a view parameter with the specified name</span>
                UIViewParameter viewParam <span style="color: #339933;">=</span> <span style="color: #000066; font-weight: bold;">null</span><span style="color: #339933;">;</span>
                <span style="color: #000000; font-weight: bold;">for</span> <span style="color: #009900;">&#40;</span>UIComponent child <span style="color: #339933;">:</span> metadataFacet.<span style="color: #006633;">getChildren</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
                    <span style="color: #000000; font-weight: bold;">if</span> <span style="color: #009900;">&#40;</span>child <span style="color: #000000; font-weight: bold;">instanceof</span> UIViewParameter<span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
                        UIViewParameter tempViewParam <span style="color: #339933;">=</span> <span style="color: #009900;">&#40;</span>UIViewParameter<span style="color: #009900;">&#41;</span> child<span style="color: #339933;">;</span>
                        <span style="color: #000000; font-weight: bold;">if</span> <span style="color: #009900;">&#40;</span>name.<span style="color: #006633;">equals</span><span style="color: #009900;">&#40;</span>tempViewParam.<span style="color: #006633;">getName</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
                            viewParam <span style="color: #339933;">=</span> tempViewParam<span style="color: #339933;">;</span>
                            <span style="color: #000000; font-weight: bold;">break</span><span style="color: #339933;">;</span>
                        <span style="color: #009900;">&#125;</span>
                    <span style="color: #009900;">&#125;</span>
                <span style="color: #009900;">&#125;</span>
    &nbsp;
                <span style="color: #000000; font-weight: bold;">if</span> <span style="color: #009900;">&#40;</span>viewParam <span style="color: #339933;">==</span> <span style="color: #000066; font-weight: bold;">null</span><span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
                    <span style="color: #000000; font-weight: bold;">throw</span> <span style="color: #000000; font-weight: bold;">new</span> FacesException<span style="color: #009900;">&#40;</span><span style="color: #0000ff;">&quot;Unknown parameter: '&quot;</span> <span style="color: #339933;">+</span> name <span style="color: #339933;">+</span> 
                         <span style="color: #0000ff;">&quot;' for view: &quot;</span> <span style="color: #339933;">+</span> viewId<span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
                <span style="color: #009900;">&#125;</span>
    &nbsp;
                <span style="color: #666666; font-style: italic;">// Getting the value</span>
                <span style="color: #003399;">String</span> value <span style="color: #339933;">=</span> viewParam.<span style="color: #006633;">getStringValue</span><span style="color: #009900;">&#40;</span>ctx<span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
                <span style="color: #000000; font-weight: bold;">return</span> includeParam<span style="color: #009900;">&#40;</span>name, value<span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
            <span style="color: #009900;">&#125;</span>
    &nbsp;
            <span style="color: #000000; font-weight: bold;">public</span> Page includeParam<span style="color: #009900;">&#40;</span><span style="color: #003399;">String</span> name, <span style="color: #003399;">String</span> value<span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
                Map<span style="color: #339933;">&lt;</span><span style="color: #003399;">String</span>, String<span style="color: #339933;">&gt;</span> newParams <span style="color: #339933;">=</span> <span style="color: #000000; font-weight: bold;">new</span> LinkedHashMap<span style="color: #339933;">&lt;</span><span style="color: #003399;">String</span>, String<span style="color: #339933;">&gt;</span><span style="color: #009900;">&#40;</span>params<span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
                newParams.<span style="color: #006633;">put</span><span style="color: #009900;">&#40;</span>name, value<span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
                <span style="color: #000000; font-weight: bold;">return</span> <span style="color: #000000; font-weight: bold;">new</span> Page<span style="color: #009900;">&#40;</span>viewId, newParams<span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
            <span style="color: #009900;">&#125;</span>
    &nbsp;
            <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #003399;">String</span> s<span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
                StringBuilder sb <span style="color: #339933;">=</span> <span style="color: #000000; font-weight: bold;">new</span> StringBuilder<span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
                sb.<span style="color: #006633;">append</span><span style="color: #009900;">&#40;</span>viewId<span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
    &nbsp;
                <span style="color: #003399;">String</span> paramSeparator <span style="color: #339933;">=</span> <span style="color: #0000ff;">&quot;?&quot;</span><span style="color: #339933;">;</span>
                <span style="color: #000000; font-weight: bold;">for</span> <span style="color: #009900;">&#40;</span><span style="color: #003399;">Map</span>.<span style="color: #006633;">Entry</span><span style="color: #339933;">&lt;</span><span style="color: #003399;">String</span>, String<span style="color: #339933;">&gt;</span> nameValue <span style="color: #339933;">:</span> params.<span style="color: #006633;">entrySet</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
                    sb.<span style="color: #006633;">append</span><span style="color: #009900;">&#40;</span>paramSeparator<span style="color: #009900;">&#41;</span>.<span style="color: #006633;">append</span><span style="color: #009900;">&#40;</span>nameValue.<span style="color: #006633;">getKey</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span>
                          .<span style="color: #006633;">append</span><span style="color: #009900;">&#40;</span><span style="color: #0000ff;">&quot;=&quot;</span><span style="color: #009900;">&#41;</span>.<span style="color: #006633;">append</span><span style="color: #009900;">&#40;</span>nameValue.<span style="color: #006633;">getValue</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
                    paramSeparator <span style="color: #339933;">=</span> <span style="color: #0000ff;">&quot;&amp;&quot;</span><span style="color: #339933;">;</span>
                <span style="color: #009900;">&#125;</span>
    &nbsp;
                <span style="color: #000000; font-weight: bold;">return</span> sb.<span style="color: #006633;">toString</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
            <span style="color: #009900;">&#125;</span>
    &nbsp;
            <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #003399;">String</span> getS<span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span> <span style="color: #000000; font-weight: bold;">return</span> s<span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span> <span style="color: #009900;">&#125;</span>
        <span style="color: #009900;">&#125;</span>
    &nbsp;
        <span style="color: #000000; font-weight: bold;">private</span> <span style="color: #000000; font-weight: bold;">final</span> Page manageIndex <span style="color: #339933;">=</span> <span style="color: #000000; font-weight: bold;">new</span> Page<span style="color: #009900;">&#40;</span><span style="color: #0000ff;">&quot;/manage/index.xhtml&quot;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
        <span style="color: #000000; font-weight: bold;">private</span> <span style="color: #000000; font-weight: bold;">final</span> Page manageUsers <span style="color: #339933;">=</span> <span style="color: #000000; font-weight: bold;">new</span> Page<span style="color: #009900;">&#40;</span><span style="color: #0000ff;">&quot;/manage/users.xhtml&quot;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
        <span style="color: #000000; font-weight: bold;">private</span> <span style="color: #000000; font-weight: bold;">final</span> Page home <span style="color: #339933;">=</span> <span style="color: #000000; font-weight: bold;">new</span> Page<span style="color: #009900;">&#40;</span><span style="color: #0000ff;">&quot;/home.xhtml&quot;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
        <span style="color: #000000; font-weight: bold;">private</span> <span style="color: #000000; font-weight: bold;">final</span> Page thisPage <span style="color: #339933;">=</span> <span style="color: #000000; font-weight: bold;">new</span> Page<span style="color: #009900;">&#40;</span><span style="color: #0000ff;">&quot;&quot;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
        <span style="color: #666666; font-style: italic;">// other pages ...</span>
    &nbsp;
        <span style="color: #000000; font-weight: bold;">public</span> Page getManageIndex<span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
            <span style="color: #000000; font-weight: bold;">return</span> manageIndex<span style="color: #339933;">;</span>
        <span style="color: #009900;">&#125;</span>
    &nbsp;
        <span style="color: #666666; font-style: italic;">// other getters ...</span>
    <span style="color: #009900;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">/**
     * @author Adam Warski (adam at warski dot org)
     */
    @Named
    @ApplicationScoped
    public class Nav {
        public static class Page {
            private final String viewId;
            private final Map&lt;String, String&gt; params;
    
            private Page(String viewId) {
                this.viewId = viewId;
                this.params = new LinkedHashMap&lt;String, String&gt;();
            }
    
            private Page(String viewId, Map&lt;String, String&gt; params) {
                this.viewId = viewId;
                this.params = params;
            }
    
            public Page redirect() {
                return includeParam(&quot;faces-redirect&quot;, &quot;true&quot;);
            }
    
            public Page includeViewParams() {
                return includeParam(&quot;includeViewParams&quot;, &quot;true&quot;);
            }
    
            public Page includeViewParam(String name) {
                // Getting the metadata facet of the view
                FacesContext ctx = FacesContext.getCurrentInstance();
                ViewDeclarationLanguage vdl = ctx.getApplication().getViewHandler()
                      .getViewDeclarationLanguage(ctx, viewId);
                ViewMetadata viewMetadata = vdl.getViewMetadata(ctx, viewId);
                UIViewRoot viewRoot = viewMetadata.createMetadataView(ctx);
                UIComponent metadataFacet = viewRoot.getFacet(
                      UIViewRoot.METADATA_FACET_NAME);
    
                // Looking for a view parameter with the specified name
                UIViewParameter viewParam = null;
                for (UIComponent child : metadataFacet.getChildren()) {
                    if (child instanceof UIViewParameter) {
                        UIViewParameter tempViewParam = (UIViewParameter) child;
                        if (name.equals(tempViewParam.getName())) {
                            viewParam = tempViewParam;
                            break;
                        }
                    }
                }
    
                if (viewParam == null) {
                    throw new FacesException(&quot;Unknown parameter: '&quot; + name + 
                         &quot;' for view: &quot; + viewId);
                }
    
                // Getting the value
                String value = viewParam.getStringValue(ctx);
                return includeParam(name, value);
            }
    
            public Page includeParam(String name, String value) {
                Map&lt;String, String&gt; newParams = new LinkedHashMap&lt;String, String&gt;(params);
                newParams.put(name, value);
                return new Page(viewId, newParams);
            }
    
            public String s() {
                StringBuilder sb = new StringBuilder();
                sb.append(viewId);
    
                String paramSeparator = &quot;?&quot;;
                for (Map.Entry&lt;String, String&gt; nameValue : params.entrySet()) {
                    sb.append(paramSeparator).append(nameValue.getKey())
                          .append(&quot;=&quot;).append(nameValue.getValue());
                    paramSeparator = &quot;&amp;&quot;;
                }
    
                return sb.toString();
            }
    
            public String getS() { return s(); }
        }
    
        private final Page manageIndex = new Page(&quot;/manage/index.xhtml&quot;);
        private final Page manageUsers = new Page(&quot;/manage/users.xhtml&quot;);
        private final Page home = new Page(&quot;/home.xhtml&quot;);
        private final Page thisPage = new Page(&quot;&quot;);
        // other pages ...
    
        public Page getManageIndex() {
            return manageIndex;
        }
    
        // other getters ...
    }</p></div>
    ";}
tags:
  - jee
  - java
  - jsf

---
JSF2 improves a lot both how navigation can be done (you can now return a view id from an action method, no need to describe every navigation case in faces-config.xml) and how URLs are handled (finally, GET support). JSF2 introduces **view parameters** (for those who know Seam: standardized page parameters). Each page can define a metadata section, where the view parameters are described, bound to bean values, converted and validated.

As an example, a blog-entry-viewing page would define the id of the entry to be displayed as follows:
```xml
<f:metadata>
   <f:viewParam name="entry_id" value="#{blog.entry}" required="true">
      <f:converter converterId="blog-entry-converter" />
   </f:viewParam>
</f:metadata>
```

Unfortunately I had some trouble with one thing: how to redirect to a page, including the view parameters, after a POST? This post->redirect->get pattern is very common. E.g. when you post a new comment for a blog entry and press submit, the data is persisted and you want to be redirected back to `view.jsf?entry_id=819` (using the default JSF command-button behavior, you would land on a plain, non-bookmarkable `view.jsf`).

Dan Allen [wrote a series][1] of very good introductory articles to JSF2 on DZone. There, he writes that what I described above should be possible to achieve by adding a `<redirect include-view-params="true"/>` tag to the appropriate `<navigation-case>` in `faces-config.xml`. Unfortunately, the xsd doesn&#8217;t allow such an attribute and it doesn&#8217;t work &#8211; I suppose that this construct didn&#8217;t make it into the final version of the spec (although somebody may correct me if I&#8217;m wrong).

Another solution, this time working, can be found on Ed Burns&#8217;s [blog][2]. The trick is to return a string containing the view id and some additional parameters from the action method or use them as the command button/link action, e.g.:
```java
public String action() {
     // business logic ...
     return "view.xhtml?faces-redirect=true&includeViewParams=true"
}
```

However this way you&#8217;ll have to repeat the combination of the &#8220;magical parameters&#8221; a lot in your code. And it&#8217;s pretty easy to do a spelling mistake in one of the strings you return. Furthermore, it&#8217;s not possible to easily include one view parameter, without repeating the value mapping.

The way I solved this is by introducing a `Nav` component (I&#8217;m using [Weld][3]), which holds information about pages. It contains a nested `Page` class, which has a &#8220;fluent&#8221; interface for building a link. Navigation then looks as follows:
```java
@Inject
private Nav nav;

public String action() {
     // business logic ...
     return nav.getViewEntry().redirect().includeViewParams().s();
}
```

Or, if you want to include only one parameter:
```java
public String action() {
     // business logic ...
     return nav.getViewEntry().redirect().includeViewParam("name").s();
}
```

In xhtml pages, you can also use the `nav` component to generate links:
```xml
<h:link outcome="#{nav.manageIndex.s}">Manage</h:link>
```

Notice that you completely **abstract away** from the actual names of the xhtml views (pages) &#8211; they are stored **centrally** only in the `nav` component! This makes any refactorings really easy.

Speaking of the `nav` component, here&#8217;s the code:
```java
/**
 * @author Adam Warski (adam at warski dot org)
 */
@Named
@ApplicationScoped
public class Nav {
    public static class Page {
        private final String viewId;
        private final Map<String, String> params;

        private Page(String viewId) {
            this.viewId = viewId;
            this.params = new LinkedHashMap<String, String>();
        }

        private Page(String viewId, Map<String, String> params) {
            this.viewId = viewId;
            this.params = params;
        }

        public Page redirect() {
            return includeParam("faces-redirect", "true");
        }

        public Page includeViewParams() {
            return includeParam("includeViewParams", "true");
        }

        public Page includeViewParam(String name) {
            // Getting the metadata facet of the view
            FacesContext ctx = FacesContext.getCurrentInstance();
            ViewDeclarationLanguage vdl = ctx.getApplication().getViewHandler()
                  .getViewDeclarationLanguage(ctx, viewId);
            ViewMetadata viewMetadata = vdl.getViewMetadata(ctx, viewId);
            UIViewRoot viewRoot = viewMetadata.createMetadataView(ctx);
            UIComponent metadataFacet = viewRoot.getFacet(
                  UIViewRoot.METADATA_FACET_NAME);

            // Looking for a view parameter with the specified name
            UIViewParameter viewParam = null;
            for (UIComponent child : metadataFacet.getChildren()) {
                if (child instanceof UIViewParameter) {
                    UIViewParameter tempViewParam = (UIViewParameter) child;
                    if (name.equals(tempViewParam.getName())) {
                        viewParam = tempViewParam;
                        break;
                    }
                }
            }

            if (viewParam == null) {
                throw new FacesException("Unknown parameter: '" + name + 
                     "' for view: " + viewId);
            }

            // Getting the value
            String value = viewParam.getStringValue(ctx);
            return includeParam(name, value);
        }

        public Page includeParam(String name, String value) {
            Map<String, String> newParams = new LinkedHashMap<String, String>(params);
            newParams.put(name, value);
            return new Page(viewId, newParams);
        }

        public String s() {
            StringBuilder sb = new StringBuilder();
            sb.append(viewId);

            String paramSeparator = "?";
            for (Map.Entry<String, String> nameValue : params.entrySet()) {
                sb.append(paramSeparator).append(nameValue.getKey())
                      .append("=").append(nameValue.getValue());
                paramSeparator = "&";
            }

            return sb.toString();
        }

        public String getS() { return s(); }
    }

    private final Page manageIndex = new Page("/manage/index.xhtml");
    private final Page manageUsers = new Page("/manage/users.xhtml");
    private final Page home = new Page("/home.xhtml");
    private final Page thisPage = new Page("");
    // other pages ...

    public Page getManageIndex() {
        return manageIndex;
    }

    // other getters ...
}
```

Looking forward, the `Page` class may also include e.g. security management, however that would require some more JSF bindings.

Adam

 [1]: http://java.dzone.com/articles/bookmarkability-jsf-2
 [2]: http://blogs.sun.com/enterprisetechtips/entry/post_redirect_get_and_jsf
 [3]: http://seamframework.org/Weld
