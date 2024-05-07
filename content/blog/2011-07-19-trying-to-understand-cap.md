---
title: Trying to understand CAP
author: Adam Warski
type: post
date: 2011-07-19T11:38:28+00:00
url: /blog/2011/07/trying-to-understand-cap/
dsq_thread_id:
  - 1051935622
tags:
  - databases
  - architecture

---
The [CAP theorem][1], stated by [Brewer][2] and proved by [Gilbert and Lynch][3] specifies a property of distributed systems. It states that such a system cannot guarantee at the same time Consistency, Availability and Partition tolerance. It is also often said as a catchy phrase:

_Consistency, Availability, Partition Tolerance &#8211; pick any two_

used mostly when talking about NoSQL databases and suggesting that a distributed system can be characterized as either CA, AP or CP (see e.g. [here][4]).

For some time I&#8217;ve been trying to understand the different combinations and what do they mean in practice; having some time at various airports I caught up on reading, and here&#8217;s what I came up with.

**What&#8217;s C, A and P?**

First let&#8217;s define how I understand the three guarantees, basing on some of the articles I&#8217;ve read.

_Consistency_ is the easiest one. It roughly means that the clients get the same view of data. By saying that a system is consistent we often mean strong consistency, but it also can come in different flavors, e.g. [casual][5].

_Availability_ is a property saying that every request to a non-failing node will return a (meaningful) response. The response may not contain all data (so the harvest will not be 100%, see the appropriate section in [3]), but it should be useful for the client.

_Partition tolerance_ means that the system will continue working even if any number of messages sent between nodes is lost. This can be e.g. a network failure between two datacenters, where nodes in each datacenter form a partition. Also note that a failure of any number of nodes forms a partition (it is not possible to distinguish between a network failure and a node failing and stopping to respond to messages).

The hardest part for me is understanding the difference between Availability and Partition tolerance. Also, the various articles don&#8217;t specify what they mean by saying that a system is &#8220;working&#8221; after being e.g. partitioned &#8211; does it mean that every request gets a response with useful data, or are responses &#8220;Sorry, I can&#8217;t give you data right now&#8221; acceptable also?

**P+C?**

Let&#8217;s assume that a system is partition tolerant and that it has more than one node. If a partition is formed, splitting the system in two, the system should continue working. Hence both partitions allow clients to write. But then, how to guarantee consistency? If one client writes to partition 1, and another to partition 2? Hence: **P => ~C**.

**A+~P?**

Suppose now that a system is available and that we have more than one node. As the system is available, it should respond to requests even if some nodes die. As noted above, some nodes dying are equivalent to a partition. So if the system is still working, we have partition tolerance. Hence: **A => P**.

**A+C?**

Summarizing the two implications above (A => P and P => ~C), we get: **A => ~C**, so that an available system cannot be consistent (if it has more than one node). In practice however, there are of course AC systems, e.g. single-node RDBMS. Or even master-slave/master-master replicated RDBMS, provided there&#8217;s a central router knowing which nodes live and directing client appropriately. Such a router is then a single point of failure (SPoF).

**Relax?**

I suspect that in reality, when e.g. NoSQL/NewSQL systems are characterized with the CAP properties, they assume some relaxed form of C/A/P. Unfortunately, all of the definitions flying around seem to be pretty vague and are more of hand-waving than proper, precise statements. I think it would be much easier to explore the ever-growing ecosystem of new datastores if they could be more easily characterized; maybe the CAP vocabulary is just not enough?

Please correct me if I&#8217;m wrong somewhere, and I probably am! :)

Adam

Some articles I used for my research:

[1] <http://www.julianbrowne.com/article/viewer/brewers-cap-theorem>  
[2] <http://www.cloudera.com/blog/2010/04/cap-confusion-problems-with-partition-tolerance/>  
[3] <http://codahale.com/you-cant-sacrifice-partition-tolerance/>

 [1]: http://en.wikipedia.org/wiki/CAP_theorem
 [2]: http://www.cs.berkeley.edu/~brewer/cs262b-2004/PODC-keynote.pdf
 [3]: http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.20.1495&rep=rep1&type=pdf
 [4]: http://blog.nahurst.com/visual-guide-to-nosql-systems
 [5]: http://en.wikipedia.org/wiki/Causal_consistency
