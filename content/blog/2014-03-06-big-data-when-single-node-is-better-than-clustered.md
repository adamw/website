---
title: 'Big data: when single node is better than clustered'
author: Adam Warski
type: blog
date: 2014-03-06T13:22:44+00:00
url: /blog/2014/03/big-data-when-single-node-is-better-than-clustered/
simplecatch-sidebarlayout:
  - default
disqus_identifier: 2371987250
tags:
  - machine learning
  - distributed

---
There’s a lot of hype about “big data” and a general trend to try to apply Hadoop to almost every problem. However, sometimes it turns out that you can get much better results by writing an old-fashioned, but optimised, single-node version of your algorithm.

The specific case I’m writing about is generating recommendations (what items user may like) basing on a data set of **300 million preference values** (user-item pairs, what users currently like). Whether you call it “big data” or not is disputable, however it’s big enough for the classic single-node algorithms (e.g. [Taste recommenders][1]) to stop taking a reasonable amount of time and memory to complete.

It may seem that the obvious choice then is to go clustered: [Mahout][2] contains a Hadoop implementation of a co-occurence based recommendation algorithm. During our test runs, it took about **7 hours on a 10-node** small-instance EMR cluster.

Then I read about GraphChi and what you can do [with a single laptop][3]! While GraphChi isn’t exactly useable by people other than its authors, it got us inspired to try the same approach with our recommendation problem.

Hence we tried implementing a single-node version of the co-occurence based recommendation algorithm, which stores as much data as possible **in-memory** and uses persistent maps from [MapDB][4] as a fall-back in case memory runs out. Mostly thanks to the dominantly in-memory computations, our optimised version takes about **4 hours** to complete on a large EC2 instance. 

Thanks to the simple setup, and full control of the code base it’s also now in fact easier to add various enhancements, like [dithering][5], combining with other recommendation sources or incorporating content-based features.

And this approach scales as well: either by adding more RAM, a faster disk (which is used in case there’s not enough memory), e.g. an SSD, or finally by partitioning the data set and running in parallel. However at some point adding more data points stops to be relevant from a recommendation quality point of view.

_To sum up_: if you have a large data set, not necessarily “big” as in trillions, but too big for classic approaches to digest, consider writing a custom, optimised, in-memory version. It may end up being simpler and faster.

 [1]: https://cwiki.apache.org/confluence/display/MAHOUT/Recommender+Documentation
 [2]: https://mahout.apache.org/
 [3]: http://www.technologyreview.com/news/428497/your-laptop-can-now-analyze-big-data/?nlid=nldly&nld=2012-07-17
 [4]: http://www.mapdb.org/
 [5]: http://parleys.com/play/529e2ecbe4b0e619540cc3e9
