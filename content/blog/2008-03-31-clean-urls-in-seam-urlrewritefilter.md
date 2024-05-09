---
title: 'Clean URLs in Seam: URLRewriteFilter'
author: Adam Warski
type: blog
date: 2008-03-31T12:10:34+00:00
url: /blog/2008/03/clean-urls-in-seam-urlrewritefilter/
disqus_identifier: 1050981825
tags:
  - java
  - jboss

---
Starting with Seam 2.0.1 (annoucement [here][1], download [here][2]) you can fully use [UrlRewriteFilter][3] to make URLs in your Seam app nice and clean.

When using this filter, you need to define inbound-`rule`s, which translate your pretty URLs into Seam views, for example: `/myapp/view/rice` &#8211;> `/myapp/view.seam?name=rice`.

Moreover, you can define `outbound-rule`s, which do the translation the other way round: from Seam views into your pretty URLs. This way, you can separate the URL managing part and the Seam view files completely: if you decide to change the URLs, you just need to modify the UrlRewriteFilter configuration file, without the need to change any `.xhtml` files, where you can use the link-generating components (`<s:link>, <s:button>`) as always. (Before Seam 2.0.1, outbound rules didn&#8217;t work.)

There is one catch, however, when using the `outbound-rule`s: you need to include the context name in them, as opposed to inboud-`rule`s. For example, when your application is deployed in the context `/myapp`, to &#8220;hide&#8221; the `home.seam` page and make it being displayed when the user hits the root of your app (that is, `/myapp` or `/myapp/`), you&#8217;ll need the following rules:

<pre>&lt;rule&gt;
   &lt;from&gt;^/index.html$&lt;/from&gt;
   &lt;to&gt;/home.seam&lt;/to&gt;
&lt;/rule&gt;</pre>

<pre>&lt;outbound-rule&gt;
   &lt;from&gt;^/myapp/home.seam$&lt;/from&gt;
   &lt;to&gt;/myapp/&lt;/to&gt;
&lt;/outbound-rule&gt;</pre>

Very often, you&#8217;ll want to translate part of the URL path to a parameter, and also include the `cid` parameter without changes, if it is present. Hence, the translated parameter must once be preceded by a `?`, and once by a `&`. Here&#8217;s how you would translate the view example from the beginning:

<pre>&lt;rule&gt;
   &lt;from&gt;^/view/(w+)$&lt;/from&gt;
   &lt;to&gt;/view.seam?name=$1&lt;/to&gt;
&lt;/rule&gt;</pre>

<pre>&lt;rule&gt;
   &lt;from&gt;^/view/(w+)?cid=(d+)$&lt;/from&gt;
   &lt;to&gt;/view.seam?cid=$2&name=$1&lt;/to&gt;
&lt;/rule&gt;</pre>

<pre>&lt;outbound-rule&gt;
   &lt;from&gt;^/myapp/view.seam?name=(w+)$&lt;/from&gt;
   &lt;to&gt;/myapp/view/$1&lt;/to&gt;
&lt;/outbound-rule&gt;</pre>

<pre>&lt;outbound-rule&gt;
   &lt;from&gt;^/myapp/view.seam?cid=(d+)&?name=(w+)$&lt;/from&gt;
   &lt;to&gt;/myapp/view/$2?cid=$1&lt;/to&gt;
&lt;/outbound-rule&gt;</pre>

Please refer to the UrlRewriteFilter [manual][4] for further configuration details.

Cheers,

Adam

 [1]: http://in.relation.to/Bloggers/Seam201GA
 [2]: http://sourceforge.net/project/showfiles.php?group_id=22866&package_id=163777&release_id=572530
 [3]: http://tuckey.org/urlrewrite/
 [4]: http://tuckey.org/urlrewrite/manual/3.0/
