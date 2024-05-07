---
title: 'Add a “dependencies” badge & tree to your project using UpdateImpact'
author: Adam Warski
type: post
date: 2016-02-24T16:22:28+00:00
url: /blog/2016/02/add-a-dependencies-badge-tree-to-your-project-using-updateimpact/
simplecatch-sidebarlayout:
  - default
dsq_thread_id:
  - 4607581685
epcl_post:
  - 'a:1:{s:13:"views_counter";i:1;}'
views_counter:
  - 1
categories:
  - softwaremill

---
Most Java/Scala/Groovy project depend on a number of libraries, these libraries depend on other libraries, and so on for many levels deep. When creating the final project bundle we end up with a large number of external dependencies, which are often very hard to manage.

That&#8217;s why [UpdateImpact][1] was created: it makes it easy to search the dependency tree, find out why a specific version of a library was evicted, plus detects some common classpath problems, such as multiple logging libraries.

But wouldn&#8217;t it be great to be able to browse the dependencies of a library before adding it to your project? Just to verify what will be the impact on your project and what kind of transitive dependencies it has. That way we could avoid many nasty surprises, coming from either incompatible transitive library versions or a dependency we rely on being evicted by a newer version.

This is now possible using the [UpdateImpact][1] badge, which you can embed e.g. in your project&#8217;s `README`, just as for example [Bootzooka][2] does:

<a href="https://app.updateimpact.com/latest/634276070333485056/bootzooka" rel="attachment"><img decoding="async" src="https://app.updateimpact.com/badge/634276070333485056/bootzooka.svg?config=compile" alt="badge" class="aligncenter size-medium" /></a>

This relies on the CI server submitting the dependency tree information after a successfull `master` branch build. To submit information to [UpdateImpact][1], you need two things:

  1. add the [Maven][3], [SBT][4] or [Gradle][5] plugin to your build
  2. define the UpdateImpact api key (available after you register in the service) either in the project’s settings or in the `UPDATEIMPACT_API_KEY` environmental variable

After you submit your first build and open the dependency tree for browsing, you&#8217;ll see the badge in the top-right corner. Click it to obtain the URL of the (dynamic) image showing the number of dependencies of your project & warnings (if any), plus the link which points to the tree of the latest build.

<a href="http://www.warski.org/blog/2016/02/add-a-dependencies-badge-tree-to-your-project-using-updateimpact/badge/" rel="attachment wp-att-1651"><img loading="lazy" decoding="async" src="http://www.warski.org/blog/wp-content/uploads/2016/02/badge-300x153.png" alt="badge" width="300" height="153" class="aligncenter size-medium wp-image-1651" srcset="https://www.warski.org/blog/wp-content/uploads/2016/02/badge-300x153.png 300w, https://www.warski.org/blog/wp-content/uploads/2016/02/badge-255x130.png 255w, https://www.warski.org/blog/wp-content/uploads/2016/02/badge-768x391.png 768w, https://www.warski.org/blog/wp-content/uploads/2016/02/badge-1024x521.png 1024w, https://www.warski.org/blog/wp-content/uploads/2016/02/badge-210x107.png 210w, https://www.warski.org/blog/wp-content/uploads/2016/02/badge.png 1482w" sizes="(max-width: 300px) 100vw, 300px" /></a>

If you want to put the badge in a public place, you should probably also make the project publicly accessible by clicking the &#8220;Private / Shareable via link&#8221; switch (the private/public setting applies top-level project name).

However, even when you have a public project, it’s necessary to have the [UpdateImpact][1] api key hidden. You cannot expose it in the build, it needs to be provided by the CI server and not accessible publicly.

## Adding private build configuration on Travis

On [Travis][6], you can do that by going to your project’s settings (available under _More Options_ -> _Settings_), and adding an environmental variable with &#8220;_Display value in build log_&#8221; turned off. And that’s it!

You&#8217;ll also probably need to modify the build target that Travis invokes. For SBT, you need to add the following to `.travis.yml`:

    script:
    - sbt ++$TRAVIS_SCALA_VERSION test
    - '[ "${TRAVIS_PULL_REQUEST}" = "false" ] && sbt updateImpactSubmit || true'
    

We have two steps here: `test` is always run, while submitting to UpdateImpact is done only if the build isn&#8217;t a PR. That&#8217;s because private env variables aren&#8217;t visible for PRs (so that they cannot leak due to a malicious change), and you probably don&#8217;t want the &#8220;latest&#8221; UpdateImpact dependency tree to show the results of a PR, only of merged changes.

 [1]: https://app.updateimpact.com
 [2]: https://github.com/softwaremill/bootzooka
 [3]: https://app.updateimpact.com/buildtool/maven
 [4]: https://app.updateimpact.com/buildtool/sbt
 [5]: https://app.updateimpact.com/buildtool/gradle
 [6]: https://travis-ci.org
