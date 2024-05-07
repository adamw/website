---
title: 'Supler 0.2.0: docs and features added, bugs removed'
author: Adam Warski
type: blog
date: 2015-01-30T12:55:44+00:00
url: /blog/2015/01/supler-0-2-0-docs-and-features-added-bugs-removed/
simplecatch-sidebarlayout:
  - default
dsq_thread_id:
  - 3469482728
tags:
  - scala
  - web

---
[Supler][1] is a library which makes writing complex forms easier. It has a server-side (Scala) and a client-side (JavaScript) component. In December we announced the [0.1.0 release][2], so it’s about time for an update!

<a href="http://www.warski.org/blog/2015/01/supler-0-2-0-docs-and-features-added-bugs-removed/screen-shot-2015-01-30-at-13-24-39/" rel="attachment wp-att-1520"><img loading="lazy" decoding="async" src="http://www.warski.org/blog/wp-content/uploads/2015/01/Screen-Shot-2015-01-30-at-13.24.39-300x100.png" alt="Screen Shot 2015-01-30 at 13.24.39" width="300" height="100" class="aligncenter size-medium wp-image-1520" srcset="https://www.warski.org/blog/wp-content/uploads/2015/01/Screen-Shot-2015-01-30-at-13.24.39-300x100.png 300w, https://www.warski.org/blog/wp-content/uploads/2015/01/Screen-Shot-2015-01-30-at-13.24.39-255x85.png 255w, https://www.warski.org/blog/wp-content/uploads/2015/01/Screen-Shot-2015-01-30-at-13.24.39-210x70.png 210w, https://www.warski.org/blog/wp-content/uploads/2015/01/Screen-Shot-2015-01-30-at-13.24.39.png 972w" sizes="(max-width: 300px) 100vw, 300px" /></a>

The biggest improvement for Supler uses will probably be the [brand new documentation][3]. We now have a [“your first Supler form” guide][4], which walks you step-by-step showing how to create a simple form. Plus, all of the features are described in detail in further sections. Let us know what you think about the docs &#8211; is there anything missing? We have created a [discussion group][5] to make leaving feedback easier. All voices count!

Other improvements include [ajax queueing support][6]: if the user filling in the form is very quick and presses a lot of buttons, it shouldn’t cause the form to get into a weird inconsistent state. Actions can now return [custom JSON][7], which you can use e.g. for navigation or giving feedback to users.

There are some internal improvements as well. Maybe we shouldn’t admit it, but before we didn’t have any frontend tests, and only some backend tests. Now the situation is much improved, with a [Travis build][8], an increasing number of frontend tests written using [PhantomJS][9], [MochaJS][10] and [ChaiJS][11], plus new backend tests. We can now sleep peacefully knowing that all the tests are green.

If you’d like to find out more, the code is [on GitHub][1], there’s also [a live demo][12]. If you think the project is interesting, please **star it on GitHub** and let us know your opinion/thoughts/questions on usage/development ideas on the [discussion group][5]!

 [1]: https://github.com/softwaremill/supler
 [2]: http://www.warski.org/blog/2014/12/supler-0-1-0-complex-forms-made-easier/
 [3]: http://docs.supler.io
 [4]: http://docs.supler.io/en/latest/first.html#first
 [5]: https://groups.google.com/forum/#!forum/supler
 [6]: http://docs.supler.io/en/latest/frontend/refreshes.html
 [7]: http://docs.supler.io/en/latest/backend/formdef/actions.html
 [8]: https://travis-ci.org/softwaremill/supler
 [9]: http://phantomjs.org
 [10]: http://mochajs.org
 [11]: http://chaijs.com
 [12]: http://supler.softwaremill.com/site/index.html
