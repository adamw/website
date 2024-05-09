---
title: How to replace a build module with Veripacks
author: Adam Warski
type: blog
date: 2013-03-26T21:05:34+00:00
url: /blog/2013/03/how-to-replace-a-build-module-with-veripacks/
simplecatch-sidebarlayout:
  - default
disqus_identifier: 1166825747
wp-syntax-cache-content:
  - |
    a:2:{i:1;s:6797:"
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
    </pre></td><td class="code"><pre class="xml" style="font-family:monospace;"><span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;?xml</span> <span style="color: #000066;">version</span>=<span style="color: #ff0000;">&quot;1.0&quot;</span> <span style="color: #000066;">encoding</span>=<span style="color: #ff0000;">&quot;UTF-8&quot;</span><span style="color: #000000; font-weight: bold;">?&gt;</span></span>
    <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;project</span> <span style="color: #000066;">xmlns</span>=<span style="color: #ff0000;">&quot;http://maven.apache.org/POM/4.0.0&quot;</span></span>
    <span style="color: #009900;">         <span style="color: #000066;">xmlns:xsi</span>=<span style="color: #ff0000;">&quot;http://www.w3.org/2001/XMLSchema-instance&quot;</span></span>
    <span style="color: #009900;">         <span style="color: #000066;">xsi:schemaLocation</span>=<span style="color: #ff0000;">&quot;http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd&quot;</span><span style="color: #000000; font-weight: bold;">&gt;</span></span>
        <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;parent<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
            <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;artifactId<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>parent<span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/artifactId<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
            <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;groupId<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>org.veripacks.battle<span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/groupId<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
            <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;version<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>1.0.0-SNAPSHOT<span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/version<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
        <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/parent<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
        <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;modelVersion<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>4.0.0<span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/modelVersion<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
        <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;name<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>Veripacks vs Build Modules: Frontend<span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/name<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
    &nbsp;
        <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;artifactId<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>frontend<span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/artifactId<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
    &nbsp;
        <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;dependencies<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
            <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;dependency<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
                <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;groupId<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>org.veripacks.battle<span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/groupId<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
                <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;artifactId<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>domain<span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/artifactId<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
                <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;version<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>1.0.0-SNAPSHOT<span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/version<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
            <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/dependency<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
        <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/dependencies<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
    <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/project<span style="color: #000000; font-weight: bold;">&gt;</span></span></span></pre></td></tr></table><p class="theCode" style="display:none;">&lt;?xml version=&quot;1.0&quot; encoding=&quot;UTF-8&quot;?&gt;
    &lt;project xmlns=&quot;http://maven.apache.org/POM/4.0.0&quot;
             xmlns:xsi=&quot;http://www.w3.org/2001/XMLSchema-instance&quot;
             xsi:schemaLocation=&quot;http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd&quot;&gt;
        &lt;parent&gt;
            &lt;artifactId&gt;parent&lt;/artifactId&gt;
            &lt;groupId&gt;org.veripacks.battle&lt;/groupId&gt;
            &lt;version&gt;1.0.0-SNAPSHOT&lt;/version&gt;
        &lt;/parent&gt;
        &lt;modelVersion&gt;4.0.0&lt;/modelVersion&gt;
        &lt;name&gt;Veripacks vs Build Modules: Frontend&lt;/name&gt;
    
        &lt;artifactId&gt;frontend&lt;/artifactId&gt;
    
        &lt;dependencies&gt;
            &lt;dependency&gt;
                &lt;groupId&gt;org.veripacks.battle&lt;/groupId&gt;
                &lt;artifactId&gt;domain&lt;/artifactId&gt;
                &lt;version&gt;1.0.0-SNAPSHOT&lt;/version&gt;
            &lt;/dependency&gt;
        &lt;/dependencies&gt;
    &lt;/project&gt;</p></div>
    ";i:2;s:1173:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    6
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;">@RequiresImport
    @<span style="color: #000000; font-weight: bold;">Import</span><span style="color: #009900;">&#40;</span><span style="color: #0000ff;">&quot;org.veripacks.battle.domain&quot;</span><span style="color: #009900;">&#41;</span>
    <span style="color: #000000; font-weight: bold;">package</span> <span style="color: #006699;">org.veripacks.battle.frontend</span><span style="color: #339933;">;</span>
    &nbsp;
    <span style="color: #000000; font-weight: bold;">import</span> <span style="color: #006699;">org.veripacks.Import</span><span style="color: #339933;">;</span>
    <span style="color: #000000; font-weight: bold;">import</span> <span style="color: #006699;">org.veripacks.RequiresImport</span><span style="color: #339933;">;</span></pre></td></tr></table><p class="theCode" style="display:none;">@RequiresImport
    @Import(&quot;org.veripacks.battle.domain&quot;)
    package org.veripacks.battle.frontend;
    
    import org.veripacks.Import;
    import org.veripacks.RequiresImport;</p></div>
    ";}
tags:
  - java
  - modularity
  - veripacks

---
Compare the two trees below. In both cases the goal is to have an application with two independent modules (`frontend` and `reporting`), and one shared/common module (`domain`). The code in `frontend` shouldn&#8217;t be able to access code in `reporting`, and vice versa. Both modules can use the `domain` code. Ideally, we would like to check these access rules at build-time.

[<img loading="lazy" decoding="async" src="http://www.warski.org/blog/wp-content/uploads/2013/03/2013-03-20_1910-300x297.png" alt="2013-03-20_1910" width="300" height="297" class="aligncenter size-medium wp-image-941" srcset="https://www.warski.org/blog/wp-content/uploads/2013/03/2013-03-20_1910-300x297.png 300w, https://www.warski.org/blog/wp-content/uploads/2013/03/2013-03-20_1910-150x150.png 150w, https://www.warski.org/blog/wp-content/uploads/2013/03/2013-03-20_1910-1024x1016.png 1024w, https://www.warski.org/blog/wp-content/uploads/2013/03/2013-03-20_1910-210x208.png 210w, https://www.warski.org/blog/wp-content/uploads/2013/03/2013-03-20_1910.png 1326w" sizes="(max-width: 300px) 100vw, 300px" />][1]

On the left, there&#8217;s a traditional solution using Maven build modules. Each build module has a pretty elaborate `pom.xml`, e.g.:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>parent</artifactId>
        <groupId>org.veripacks.battle</groupId>
        <version>1.0.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>
    <name>Veripacks vs Build Modules: Frontend</name>

    <artifactId>frontend</artifactId>

    <dependencies>
        <dependency>
            <groupId>org.veripacks.battle</groupId>
            <artifactId>domain</artifactId>
            <version>1.0.0-SNAPSHOT</version>
        </dependency>
    </dependencies>
</project>
```

On the right, on the other hand, we have a much simpler structure with only one build module. Each application module now corresponds to one top-level project package (see also [this blog][2] on package naming conventions). 

Notice the `package-info.java` files. There, using [Veripacks][3], we can specify which packages are visible where. First of all, we specify that the code from top-level packages (`frontend`, `reporting` and `domain`) should be only accessible if explicitly imported, using `@RequiresImport`. Secondly, we specify that we want to access the `domain` package in `frontend` and `reporting` using `@Import`; e.g.:
```java
@RequiresImport
@Import("org.veripacks.battle.domain")
package org.veripacks.battle.frontend;

import org.veripacks.Import;
import org.veripacks.RequiresImport;
```

Now, isn&#8217;t the Veripacks approach simpler? :) There is still build-time checking, which is possible by running a simple test (see the README for details). Plus, you can also use other Veripacks features, like `@Export` annotations, which is a generalized version of package-private scope, taking into account package hierarchies. There are also other benefits, like trivial sharing of test code (which is kind of hard with Maven), or much easier refactoring (introducing a new application module is a matter of adding a top-level package).

The immediate question that arises is &#8211; what about 3rd party libraries? Most probably, we&#8217;d like frontend-specific libraries to be accessible only in the `frontend` module, and reporting-specific ones in the `reporting` module. Well, not supported yet, but good news &#8211; that will be the scope of the next Veripacks release :).

You can view the example projects [on GitHub][4].

Adam

 [1]: http://www.warski.org/blog/wp-content/uploads/2013/03/2013-03-20_1910.png
 [2]: http://blog.schauderhaft.de/2013/01/13/the-importance-of-packages/
 [3]: http://veripacks.org
 [4]: https://github.com/adamw/veripacks-vs-buildmodules
