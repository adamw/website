---
title: JSR308 Checkers Maven2 plugin
author: Adam Warski
type: blog
date: 2009-02-04T18:11:54+00:00
url: /blog/2009/02/jsr308-checkers-maven2-plugin/
disqus_identifier:
  - 1051934899
wp-syntax-cache-content:
  - |
    a:3:{i:1;s:6868:"
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
    </pre></td><td class="code"><pre class="xml" style="font-family:monospace;"><span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;repositories<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
        <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;repository<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
            <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;id<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>mamut-releases<span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/id<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
            <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;url<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>http://repository.mamut.net.pl/content/repositories/releases<span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/url<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
            <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;releases<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
                <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;enabled<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>true<span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/enabled<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
            <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/releases<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
            <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;snapshots<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
                <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;enabled<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>false<span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/enabled<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
            <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/snapshots<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
        <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/repository<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
    <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/repositories<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
    <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;pluginRepositories<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
        <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;pluginRepository<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
            <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;id<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>mamut-releases<span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/id<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
            <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;url<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>http://repository.mamut.net.pl/content/repositories/releases<span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/url<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
            <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;releases<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
                <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;enabled<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>true<span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/enabled<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
            <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/releases<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
            <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;snapshots<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
                <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;enabled<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>false<span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/enabled<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
            <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/snapshots<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
        <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/pluginRepository<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>        
    <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/pluginRepositories<span style="color: #000000; font-weight: bold;">&gt;</span></span></span></pre></td></tr></table><p class="theCode" style="display:none;">&lt;repositories&gt;
        &lt;repository&gt;
            &lt;id&gt;mamut-releases&lt;/id&gt;
            &lt;url&gt;http://repository.mamut.net.pl/content/repositories/releases&lt;/url&gt;
            &lt;releases&gt;
                &lt;enabled&gt;true&lt;/enabled&gt;
            &lt;/releases&gt;
            &lt;snapshots&gt;
                &lt;enabled&gt;false&lt;/enabled&gt;
            &lt;/snapshots&gt;
        &lt;/repository&gt;
    &lt;/repositories&gt;
    &lt;pluginRepositories&gt;
        &lt;pluginRepository&gt;
            &lt;id&gt;mamut-releases&lt;/id&gt;
            &lt;url&gt;http://repository.mamut.net.pl/content/repositories/releases&lt;/url&gt;
            &lt;releases&gt;
                &lt;enabled&gt;true&lt;/enabled&gt;
            &lt;/releases&gt;
            &lt;snapshots&gt;
                &lt;enabled&gt;false&lt;/enabled&gt;
            &lt;/snapshots&gt;
        &lt;/pluginRepository&gt;        
    &lt;/pluginRepositories&gt;</p></div>
    ";i:2;s:4435:"
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
    </pre></td><td class="code"><pre class="xml" style="font-family:monospace;"><span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;dependencies<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
        <span style="color: #808080; font-style: italic;">&lt;!-- annotations for the standard checkers: nullness, interning, mutability --&gt;</span>
        <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;dependency<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
            <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;groupId<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>mamut.net.pl<span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/groupId<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
            <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;artifactId<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>checkers-quals<span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/artifactId<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
            <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;version<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>0.8.6<span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/version<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
        <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/dependency<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
        <span style="color: #808080; font-style: italic;">&lt;!-- and if you want to use the typestate checker: --&gt;</span>
        <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;dependency<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
            <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;groupId<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>mamut.net.pl<span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/groupId<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
            <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;artifactId<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>typestate-checker<span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/artifactId<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
            <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;version<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>0.1<span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/version<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
        <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/dependency<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
        <span style="color: #808080; font-style: italic;">&lt;!-- other dependencies --&gt;</span>
    <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/dependencies<span style="color: #000000; font-weight: bold;">&gt;</span></span></span></pre></td></tr></table><p class="theCode" style="display:none;">&lt;dependencies&gt;
        &lt;!-- annotations for the standard checkers: nullness, interning, mutability --&gt;
        &lt;dependency&gt;
            &lt;groupId&gt;mamut.net.pl&lt;/groupId&gt;
            &lt;artifactId&gt;checkers-quals&lt;/artifactId&gt;
            &lt;version&gt;0.8.6&lt;/version&gt;
        &lt;/dependency&gt;
        &lt;!-- and if you want to use the typestate checker: --&gt;
        &lt;dependency&gt;
            &lt;groupId&gt;mamut.net.pl&lt;/groupId&gt;
            &lt;artifactId&gt;typestate-checker&lt;/artifactId&gt;
            &lt;version&gt;0.1&lt;/version&gt;
        &lt;/dependency&gt;
        &lt;!-- other dependencies --&gt;
    &lt;/dependencies&gt;</p></div>
    ";i:3;s:7787:"
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
    </pre></td><td class="code"><pre class="xml" style="font-family:monospace;"><span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;build<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
        <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;plugins<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
            <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;plugin<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
                <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;groupId<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>mamut.net.pl<span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/groupId<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
                <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;artifactId<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>checkersplugin<span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/artifactId<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
                <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;version<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>0.1<span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/version<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
                <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;executions<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
                    <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;execution<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
                        <span style="color: #808080; font-style: italic;">&lt;!-- run the checkers after compilation; this can also be any later phase --&gt;</span>
                        <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;phase<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>process-classes<span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/phase<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
                        <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;goals<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
                            <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;goal<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>check<span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/goal<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
                        <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/goals<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
                    <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/execution<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
                <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/executions<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
                <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;configuration<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
                    <span style="color: #808080; font-style: italic;">&lt;!-- checkers you want to be run --&gt;</span>
                    <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;processors<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
                        <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;processor<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>checkers.nullness.NullnessChecker<span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/processor<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
                        <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;processor<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>checkers.interning.InterningChecker<span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/processor<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
                        <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;processor<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>checkers.typestate.TypestateChecker<span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/processor<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
                    <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/processors<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
                    <span style="color: #808080; font-style: italic;">&lt;!-- other configuration - see webpage for details --&gt;</span>
                <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/configuration<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
            <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/plugin<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
        <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/plugins<span style="color: #000000; font-weight: bold;">&gt;</span></span></span>
    <span style="color: #009900;"><span style="color: #000000; font-weight: bold;">&lt;/build<span style="color: #000000; font-weight: bold;">&gt;</span></span></span></pre></td></tr></table><p class="theCode" style="display:none;">&lt;build&gt;
        &lt;plugins&gt;
            &lt;plugin&gt;
                &lt;groupId&gt;mamut.net.pl&lt;/groupId&gt;
                &lt;artifactId&gt;checkersplugin&lt;/artifactId&gt;
                &lt;version&gt;0.1&lt;/version&gt;
                &lt;executions&gt;
                    &lt;execution&gt;
                        &lt;!-- run the checkers after compilation; this can also be any later phase --&gt;
                        &lt;phase&gt;process-classes&lt;/phase&gt;
                        &lt;goals&gt;
                            &lt;goal&gt;check&lt;/goal&gt;
                        &lt;/goals&gt;
                    &lt;/execution&gt;
                &lt;/executions&gt;
                &lt;configuration&gt;
                    &lt;!-- checkers you want to be run --&gt;
                    &lt;processors&gt;
                        &lt;processor&gt;checkers.nullness.NullnessChecker&lt;/processor&gt;
                        &lt;processor&gt;checkers.interning.InterningChecker&lt;/processor&gt;
                        &lt;processor&gt;checkers.typestate.TypestateChecker&lt;/processor&gt;
                    &lt;/processors&gt;
                    &lt;!-- other configuration - see webpage for details --&gt;
                &lt;/configuration&gt;
            &lt;/plugin&gt;
        &lt;/plugins&gt;
    &lt;/build&gt;</p></div>
    ";}
tags:
  - java
  - static analysis

---
I&#8217;ve written a prototype [JSR308 Checkers][1] Maven2 plugin. You can use the plugin to run any checker on the sources of your module(s).

The JSR308 compiler, and in effect also the checkers, are run in a separate, forked, JVM process. Additionally, this process only does the processing, without actual compilation. So all of your files will be compiled by the same <tt>javac</tt> as before. The plugin only runs the additional verification of the source code, and will not affect the produced bytecode. Java6 is required to run the forked JVM.

You can easily try using the standard checkers, for checking nullness, interning and mutability errors, as well as the [typestate][2] checker; I&#8217;ve uploaded the necessary jars to my repository, so to use any of them, you&#8217;ll need first to add repositories to your <tt>pom.xml</tt>:
```xml
<repositories>
    <repository>
        <id>mamut-releases</id>
        <url>http://repository.mamut.net.pl/content/repositories/releases</url>
        <releases>
            <enabled>true</enabled>
        </releases>
        <snapshots>
            <enabled>false</enabled>
        </snapshots>
    </repository>
</repositories>
<pluginRepositories>
    <pluginRepository>
        <id>mamut-releases</id>
        <url>http://repository.mamut.net.pl/content/repositories/releases</url>
        <releases>
            <enabled>true</enabled>
        </releases>
        <snapshots>
            <enabled>false</enabled>
        </snapshots>
    </pluginRepository>        
</pluginRepositories>
```

Then, to use the annotations used by the standard checkers, you&#8217;ll have to declare a dependency:
```xml
<dependencies>
    <!-- annotations for the standard checkers: nullness, interning, mutability -->
    <dependency>
        <groupId>mamut.net.pl</groupId>
        <artifactId>checkers-quals</artifactId>
        <version>0.8.6</version>
    </dependency>
    

<!-- and if you want to use the typestate checker: -->
    <dependency>
        <groupId>mamut.net.pl</groupId>
        <artifactId>typestate-checker</artifactId>
        <version>0.1</version>
    </dependency>
    

<!-- other dependencies -->
</dependencies>
```

And finally, you need to attach the plugin to your build lifecycle:
```xml
<build>
    <plugins>
        <plugin>
            <groupId>mamut.net.pl</groupId>
            <artifactId>checkersplugin</artifactId>
            <version>0.1</version>
            <executions>
                <execution>
                    <!-- run the checkers after compilation; this can also be any later phase -->
                    <phase>process-classes</phase>
                    <goals>
                        <goal>check</goal>
                    </goals>
                </execution>
            </executions>
            <configuration>
                

<!-- checkers you want to be run -->
                <processors>
                    <processor>checkers.nullness.NullnessChecker</processor>
                    <processor>checkers.interning.InterningChecker</processor>
                    <processor>checkers.typestate.TypestateChecker</processor>
                </processors>
                

<!-- other configuration - see webpage for details -->
            </configuration>
        </plugin>
    </plugins>
</build>
```

And that&#8217;s it! After compiling, the specified checkers (annotations processors) will be run on your source code and report any errors found.

For more information and full configuration options description, see the web page: <http://www.warski.org/checkersplugin.html>

Waiting for feedback :)

Adam

 [1]: http://groups.csail.mit.edu/pag/jsr308/
 [2]: /blog/?p=37
