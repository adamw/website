---
title: Developing with Seam and without frequent redeploying
author: Adam Warski
type: blog
date: 2007-10-12T07:45:35+00:00
url: /blog/2007/10/developing-with-seam-and-without-frequent-redeploying/
disqus_identifier:
  - 1051933700
tags:
  - java
  - jboss

---
[Seam][1] in combination with [JBoss Tools][2] already has the features I [wrote][3] about some time ago (see [here][4]), unless, for example, you aren&#8217;t using Eclipse or you want to develop with Seam 2, which isn&#8217;t supported by JBoss Tools yet.

That&#8217;s why the **Resources Filter** might be handy here too; and with little modifications (and, in fact, simplifications) it now **works with Seam**. Here&#8217;s the information you&#8217;ll need to get it running:

  * [usage & configuration][5]
  * [downloads][6]

The only configuration you need to do in Seam is that in `components.xml`, you need to set: `<core:init debug="true"/>`, which is the default if you generated your project with `seam-gen`.

Adam

 [1]: http://labs.jboss.com/jbossseam
 [2]: http://labs.jboss.com/tools
 [3]: http://www.warski.org/blog/?p=3
 [4]: http://docs.jboss.com/seam/2.0.0.CR2/reference/en/html/gettingstarted.html#d0e2139
 [5]: http://labs.jboss.com/wiki/ShotokuWebFilter
 [6]: http://labs.jboss.com/shotoku/downloads/web
