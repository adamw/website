---
title: What data should be stored in versions tables? – a poll
author: Adam Warski
type: post
date: 2008-06-04T08:10:28+00:00
url: /blog/2008/06/what-data-should-be-stored-in-versions-tables-a-poll/
dsq_thread_id:
  - 1296285125
tags:
  - envers
  - java
  - hibernate

---
Hello,

I&#8217;ve posted a poll on what data should be stored in versions tables in [Envers][1], see here and cast your vote:

<http://www.jboss.com/index.html?module=bb&op=viewtopic&t=136764>

The results will determine the future implementation, so votes really count. And as always, comments & ideas are very welcome.

The two options, in short, are:

  1. Store only historical data in the versions table, making queries poorer and more difficult (you always have to combine data from two sources &#8211; the versions and &#8220;current&#8221; tables)
  2. Store all data in the versions table, making queries easier and more powerful. This however duplicates the &#8220;current&#8221; data.

Adam

 [1]: http://www.jboss.org/envers
