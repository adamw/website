---
title: Per-commit e-mail GitHub notifications
author: Adam Warski
type: blog
date: 2013-04-16T14:34:59+00:00
url: /blog/2013/04/per-commit-e-mail-github-notifications/
simplecatch-sidebarlayout:
  - default
disqus_identifier: 1215243679
tags:
  - code review
  - softwaremill

---
One thing that I miss in GitHub is the ability to get e-mail notifications on each push/commit. There is an option to set an e-mail notification address in the repository settings, but you can only specify a single, global address, and only the administrator can do it.

So while waiting for [CodeBrag beta][1], which will bring a much better code-review experience, here&#8217;s how I get an e-mail notification on each push to the repositories I&#8217;m interested in. 

The general idea is to get a RSS feed, and then use a simple [IFTTT][2] recipe to receive an e-mail whenever there&#8217;s a new item containing the words &#8220;pushed to&#8221; in the feed. The recipe looks like this:

[<img loading="lazy" decoding="async" src="http://www.warski.org/blog/wp-content/uploads/2013/03/2013-03-19_1616-300x109.png" alt="2013-03-19_1616" width="300" height="109" class="aligncenter size-medium wp-image-932" srcset="https://www.warski.org/blog/wp-content/uploads/2013/03/2013-03-19_1616-300x109.png 300w, https://www.warski.org/blog/wp-content/uploads/2013/03/2013-03-19_1616-1024x374.png 1024w, https://www.warski.org/blog/wp-content/uploads/2013/03/2013-03-19_1616-210x76.png 210w, https://www.warski.org/blog/wp-content/uploads/2013/03/2013-03-19_1616.png 1296w" sizes="(max-width: 300px) 100vw, 300px" />][3]

There are two ways of getting an RSS feed with all the pushes. Firstly, you can get an RSS feed which aggregates pushes to **all of the repositories that you are watching**. Simply go to [GitHub][4], log in, and in the upper-right corner you should see a &#8220;News Feed&#8221; link. Copy that to IFTTT and you&#8217;re done!

Secondly, you can get a feed for a single branch of a repository. Go to the repository you wish to get notifications for, and click on the &#8220;commits tab&#8221; (you should get a page like [this one][5]). Next to &#8220;Commit History&#8221;, there&#8217;s a feed symbol, which is a link to the branch&#8217;s feed.

Hope this will be useful!  
Adam

 [1]: http://www.codebrag.com/
 [2]: https://ifttt.com
 [3]: http://www.warski.org/blog/wp-content/uploads/2013/03/2013-03-19_1616.png
 [4]: https://github.com/
 [5]: https://github.com/adamw/veripacks/commits/master
