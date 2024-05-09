---
title: Generational caching and Envers
author: Adam Warski
type: blog
date: 2012-07-13T11:47:12+00:00
url: /blog/2012/07/generational-caching-and-envers/
disqus_identifier: 1051809435
tags:
  - envers
  - hibernate
  - java

---
[Konrad][1] recently shared on our [company&#8217;s][2] technical room an interesting article [on how caching is done][3] is a big polish social network, nk.pl. One of the central concepts in the algorithm is **generational caching** (see [here][4] or [here][5]). The basic idea is that for cache keys you use some entity-specific string + version number. The version number increases whenever data changes, thus invalidating any old cache entries, and preventing stale data reads. This makes the assumption that the cache has some garbage collection, e.g. it may simply be a LRU cache.

Of course on each request we must know the version number &#8211; that&#8217;s why it must be stored in a global cache (but depending on our consistency requirements, it also may be distributed across the cluster asynchronously). However the data itself can be stored in local caches. So if our system is read-most, the only &#8220;expensive&#8221; operation that we will have to do per request is retrieve the version numbers for the entities we are interested in. And this is usually very simple information, which can be kept entirely in-memory.

Depending on the type of data and the usage patterns, you can cache individual entities (e.g. for a `Person` entity, the cache key could be `person-9128-123`, 9128 being the id, 123 the version number), or the whole lot (e.g. for a `Countries` entity, the cache key could be `countries-8`, 8 being the version number). Moreover in the global cache you can keep the latest version number per-id or per-entity; meaning that when the version changes, you invalidate a specific entity or all of them.

Having written most of [Envers][6], it quite naturally occurred to me that you may **use the entity revision numbers as the cache versions**. Subsequent Envers revisions are monotonically increasing numbers, for each transaction you get the next one. So whenever a cached entity changes, you would have to populate the global cache with the latest revision number.

Envers provides several ways to get the revision numbers. During the transaction, you can call `<a href="http://docs.jboss.org/hibernate/core/4.1/javadocs/org/hibernate/envers/AuditReader.html">AuditReader</a>.getCurrentRevision()` method, which will give you the revision metadata, including the revision number. If you want more fine-grained control, you may implement your own listener ([`EntityTrackingRevisionListener`][7]), see [the docs][8]), and get notified whenever an entity is changed, and update the global cache in there. You can also register an after-transaction-completed callback, and update the cache outside of the transaction boundaries. Or, if you know the entity ids, you may lookup the maximum revision number using either `AuditReader.getRevisions` or an [`AuditQueryCreator`][9].

As you can obtain the current revision number during a transaction, you may even update the version/revision in the global cache atomically, if you use a transactional cache such as [Infinispan][10].

All of that of course in addition to auditing, which is still the main purpose of Envers :)

Adam

 [1]: https://plus.google.com/116133683664833809819
 [2]: http://softwaremill.com
 [3]: https://groups.google.com/forum/#!msg/memcached/OiScvRbGaU8/C1vny7DiGakJ
 [4]: http://www.regexprn.com/2011/06/web-application-caching-strategies_05.html
 [5]: http://37signals.com/svn/posts/3113-how-key-based-cache-expiration-works
 [6]: http://docs.jboss.org/hibernate/core/4.1/devguide/en-US/html/ch15.html
 [7]: http://docs.jboss.org/hibernate/core/4.1/javadocs/org/hibernate/envers/EntityTrackingRevisionListener.html
 [8]: http://docs.jboss.org/hibernate/orm/4.1/devguide/en-US/html/ch15.html#envers-tracking-modified-entities-revchanges
 [9]: http://docs.jboss.org/hibernate/core/4.1/javadocs/org/hibernate/envers/query/AuditQueryCreator.html
 [10]: http://www.jboss.org/infinispan/
