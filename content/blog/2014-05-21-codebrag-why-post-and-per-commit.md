---
title: 'Codebrag: why post- and per- commit?'
author: Adam Warski
type: blog
date: 2014-05-21T10:56:59+00:00
url: /blog/2014/05/codebrag-why-post-and-per-commit/
simplecatch-sidebarlayout:
  - default
dsq_thread_id:
  - 2701885824
tags:
  - code review
  - softwaremill

---
[We][1] have recently released version 2.0 of [Codebrag][2], our code-review tool.

Why a new code-review tool in the first place? We are certainly not the first in the market. When working on projects, we prefer tools that are as light as possible, adding minimal overhead to the process. We usually have small teams, and we couldn’t find a tool that would meet our needs; the existing ones were either too heavy, or didn’t fit our style of work. Plus we have a couple of unique features, such as **code-likes**, **to-do list of commits** left to review and a **to-do list of followups** to the changes we made.

<a href="http://www.warski.org/blog/2014/05/codebrag-why-post-and-per-commit/tumblr_inline_ms8jgilxp01qz4rgp/" rel="attachment wp-att-1142"><img loading="lazy" decoding="async" src="http://www.warski.org/blog/wp-content/uploads/2013/10/tumblr_inline_ms8jgiLXp01qz4rgp.jpg" alt="tumblr_inline_ms8jgiLXp01qz4rgp" width="285" height="170" class="aligncenter size-full wp-image-1142" srcset="https://www.warski.org/blog/wp-content/uploads/2013/10/tumblr_inline_ms8jgiLXp01qz4rgp.jpg 285w, https://www.warski.org/blog/wp-content/uploads/2013/10/tumblr_inline_ms8jgiLXp01qz4rgp-210x125.jpg 210w" sizes="(max-width: 285px) 100vw, 285px" /></a>

Hence [Codebrag][2] was first of all meant for ourselves. One of our main differentiators when creating software is an emphasis on code quality and value: and this means testing, good communication and code reviews. Codebrag helps us to achieve that, not only in the finding bugs part, but also by spreading the knowledge about the design and implemented features.

**Why per-commit?** That’s the most controversial choice among developers. Some prefer to review changes _commit-by-commit_, some prefer to view _squashed commits_ (or even whole branches). It would be best to have both in [Codebrag][2] of course, but we had to start with one. I think that per-commit is the better default. When reviewing a larger set of commits, chances are, that the changes will include a refactor-commit, which touches a lot of code, but doesn’t really introduce a lot of new logic. Or that you’ll get an overwhelming amount of changes in a lot of layers, which will be hard to grasp. All of that can make it hard to notice the _”core”_, most important changes.

Reviewing changes commit-by-commit gives you a chance to read them step-by-step, following the implementation order. You also get the additional value in the form of the commit message, which helps to understand what’s the scope of the changes in the commit.

As I said, per-commit is not always the better chance, and I suppose [Codebrag][2] will get squashed commits support in the future, but my experiences from such review style are very positive.

**Aren’t pull requests better?** Pull requests work great in some projects, especially open-source ones. But they certainly are not a silver bullet. Implementing a feature first, and then creating a pull requests, can result in a long feedback cycle. On the other hand, reviewing a feature as it is implemented, provides a short feedback loop. And in fact [Codebrag][2] and pull requests aren’t exclusive.

**Why post-commit?** We prefer optimistic locking instead of pessimistic locking. In a majority of cases there isn’t a need to rollback changes because the design is flawed or needs re-implementing. We also don’t want to block each other by waiting for a review. Hence removing the synchronisation point (between reviewers and the commiter) streamlines the development. And besides, we usually review features as they are built, so before a feature is finished (see above).

Work on [Codebrag][2] definitely doesn’t stop here. Support for multiple repositories, pull-request like collaboration, and many other features is ahead. The exact order of implementing them is depending only on the feedback from you, our users &#8211; so [let us know][3] what you’d need.

Not sure if [Codebrag][2] is for you? Well, you don’t risk much to try it out. The worst that can happen is that you’ll review a couple of commits :).

 [1]: http://softwaremill.com/
 [2]: http://www.codebrag.com/
 [3]: mailto:ask@codebrag.com
