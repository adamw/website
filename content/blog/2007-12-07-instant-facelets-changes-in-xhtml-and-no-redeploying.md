---
title: 'Instant Facelets: changes in .xhtml and no redeploying'
author: Adam Warski
type: post
date: 2007-12-07T12:02:56+00:00
url: /blog/2007/12/instant-facelets-changes-in-xhtml-and-no-redeploying/
dsq_thread_id:
  - 1051116499
categories:
  - java
  - jboss

---
If you are developing anything with Facelets/Seam/&#8230; frameworks, you probably know the pain of having to redeploy after each .xhtml file change to see the changes, even if they are only cosmetic. I wrote about possible solutions for that problem earlier, but they didn&#8217;t quite work for facelets (more specifically, templates didn&#8217;t get refreshed and any included pages).

Of course, the best solution is to try [JBoss Tools][1] and [Red Hat Developer Studio][2]. However if you want to use IDEA, or some other IDE (vi? :) ), try the following.

The trick is to tell Facelets to read the files from disk. Sounds simple, and Facelets have built-in mechanisms which make it simple.

First, you have to implement the `ResourceResolver` interface:

<pre>package org.jboss.shotoku.web;

import com.sun.facelets.impl.ResourceResolver;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Adam Warski
 */
public class FilesystemResourceResolver implements ResourceResolver {
    public URL resolveUrl(String s) {
        try {
            return new URL("file", "",
                  "PATH_TO_FACELETS_FILES_GOES_HERE" + s);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }
}</pre>

and replace the path to your Facelets directory in the appropriate place (absolute path, for example `/home/user/mygreatapp/view`). (It is also easy to externalize that configuration using a context parameter.)  
Then, to your `web.xml` you have to add:

<pre>&lt;context-param&gt;
        &lt;param-name&gt;facelets.DEVELOPMENT&lt;/param-name&gt;
        &lt;param-value&gt;true&lt;/param-value&gt;
    &lt;/context-param&gt;
    &lt;context-param&gt;
        &lt;param-name&gt;facelets.REFRESH_PERIOD&lt;/param-name&gt;
        &lt;param-value&gt;0&lt;/param-value&gt;
    &lt;/context-param&gt;
    &lt;context-param&gt;
        &lt;param-name&gt;facelets.RESOURCE_RESOLVER&lt;/param-name&gt;
        &lt;param-value&gt;org.jboss.shotoku.web.FilesystemResourceResolver&lt;/param-value&gt;
    &lt;/context-param&gt;</pre>

And you&#8217;re done! Try editing a .xhtml page (or template, or included page) and refreshing your browser.

If you want also other resources to be read from disk (like css files), try the [Resources Filter][3].

Adam

 [1]: http://labs.jboss.com/tools/
 [2]: http://labs.jboss.com/rhdevstudio/
 [3]: http://www.warski.org/blog/?p=3
