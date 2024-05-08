---
title: Event streaming with MongoDB
author: Adam Warski
type: blog
date: 2012-11-27T13:41:24+00:00
url: /blog/2012/11/event-streaming-with-mongodb/
simplecatch-sidebarlayout:
  - default
dsq_thread_id:
  - 1050968887
wp-syntax-cache-content:
  - |
    a:1:{i:1;s:3503:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    6
    7
    8
    9
    10
    11
    12
    13
    14
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;">DBObject query <span style="color: #339933;">=</span> lastReceivedEventId.<span style="color: #006633;">isPresent</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span>
       <span style="color: #339933;">?</span> BasicDBObjectBuilder.<span style="color: #006633;">start</span><span style="color: #009900;">&#40;</span><span style="color: #0000ff;">&quot;_id&quot;</span>, BasicDBObjectBuilder
             .<span style="color: #006633;">start</span><span style="color: #009900;">&#40;</span><span style="color: #0000ff;">&quot;$gte&quot;</span>, lookFrom<span style="color: #009900;">&#40;</span>lastReceivedEventId.<span style="color: #006633;">get</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span>.<span style="color: #006633;">get</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span>
             .<span style="color: #006633;">get</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span>
       <span style="color: #339933;">:</span> <span style="color: #000066; font-weight: bold;">null</span><span style="color: #339933;">;</span>
    &nbsp;
    DBObject sortBy <span style="color: #339933;">=</span> BasicDBObjectBuilder.<span style="color: #006633;">start</span><span style="color: #009900;">&#40;</span><span style="color: #0000ff;">&quot;$natural&quot;</span>, <span style="color: #cc66cc;">1</span><span style="color: #009900;">&#41;</span>.<span style="color: #006633;">get</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
    &nbsp;
    DBCollection collection <span style="color: #339933;">=</span> ... <span style="color: #666666; font-style: italic;">// must be a capped collection</span>
    DBCursor cursor <span style="color: #339933;">=</span> collection
       .<span style="color: #006633;">find</span><span style="color: #009900;">&#40;</span>query<span style="color: #009900;">&#41;</span>
       .<span style="color: #006633;">sort</span><span style="color: #009900;">&#40;</span>sortBy<span style="color: #009900;">&#41;</span>
       .<span style="color: #006633;">addOption</span><span style="color: #009900;">&#40;</span>Bytes.<span style="color: #006633;">QUERYOPTION_TAILABLE</span><span style="color: #009900;">&#41;</span>
       .<span style="color: #006633;">addOption</span><span style="color: #009900;">&#40;</span>Bytes.<span style="color: #006633;">QUERYOPTION_AWAITDATA</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span></pre></td></tr></table><p class="theCode" style="display:none;">DBObject query = lastReceivedEventId.isPresent()
       ? BasicDBObjectBuilder.start(&quot;_id&quot;, BasicDBObjectBuilder
             .start(&quot;$gte&quot;, lookFrom(lastReceivedEventId.get())).get())
             .get()
       : null;
    
    DBObject sortBy = BasicDBObjectBuilder.start(&quot;$natural&quot;, 1).get();
    
    DBCollection collection = ... // must be a capped collection
    DBCursor cursor = collection
       .find(query)
       .sort(sortBy)
       .addOption(Bytes.QUERYOPTION_TAILABLE)
       .addOption(Bytes.QUERYOPTION_AWAITDATA);</p></div>
    ";}
tags:
  - distributed
  - nosql

---
[MongoDB][1] is a really great &#8220;NoSQL&#8221; database, with a very wide range of applications. In one project that we are developing at [SoftwareMill][2], we used it as a replicated event storage, from which we stream the events to other components.

### Introduction

The basic idea is pretty simple (see also Martin Fowler&#8217;s article on [Event Sourcing][3]). Our system generates a series of events. These events are persisted in the event storage. Other components in the system follow the stream of events and do &#8220;something&#8221; with them; for example they can get aggregated and written into a reporting database (this, on the other hand, resembles [CQRS][4]). Such an approach has many advantages:

  * reading and writing of the events is decoupled (asynchronous)
  * any following-component may die and then &#8220;catch up&#8221;, given that it wasn&#8217;t dead for too long 
  * there may be multiple followers. The followers may read the data from slave replicas, for better scalability
  * bursts of event activity have a reduced impact on event sinks; at worst, the reports will get generated slower

The key component here is of course a fast and reliable event storage. The three key features of MongoDB that we used to implement one are:

  * capped collections and tailable cursors
  * fast collection appends
  * replica sets

### Collection

As the base, we are using a [capped collection][5], which by definition is size-constrained. If writing a new event would cause the collection to exceed the size limit, the oldest events are overwritten. This gives us something similar to a [circular buffer][6] for events. (Plus we are also quite safe from out-of-disk-space errors.)

Until version 2.2, capped collection didn&#8217;t have an \_id field by default (and hence no index). However, as we wanted the events to be written reliably across the replica set, both the \_id field and an index on it are mandatory.

### Writing events

Writing events is a simple Mongo insert operation; inserts can also be done in batches. Depending on how tolerant we are of event loss, we may use various Mongo [write concerns][7] (e.g. waiting for a write confirmation from a single-node or from multiple nodes).

All of the events are immutable. Apart from nicer, thread-safe Java code, this is a necessity for event streaming; if the events were mutable, how would the event sink know what was updated? Also, this has good Mongo performance implications. As the data is never changed, the documents that are written to disk never shrink or expand, so there is no need to move blocks on disk. In fact, in a capped collection, Mongo doesn&#8217;t allow to grow a document that was once written.

### Reading events

Reading the event stream is a little bit more complex. First of all, there may be multiple readers, each with a different level of advancement in the stream. Secondly, if there are no events in the stream, we would like the reader to wait until some events are available, and avoid active polling. Finally, we would like to process the events in batches, to improve performance. 

[Tailable cursors][8] solve these problems. To create such a cursor we have to provide a starting point &#8211; an id of an event, from which we&#8217;ll start reading; if an id is not provided, the cursor will return events from the oldest one available. Thus each reader must store the last event that it has read and processed.

More importantly, tailable cursors can optionally block for some amount of time if no new data is available, solving the active polling problem.

(By the way, the oplog collection that mongo uses to replicate data across a replica set, is also a capped collection. Slave Mongo instances tail this collection, streaming the &#8220;events&#8221;, which are database operations, and applying them locally in order.)

### Reading events in Java

When using the Mongo [Java Driver][9], there are a few &#8220;catches&#8221;. First of all you need to initialise the cursor. To do that, we need to provide (1) the starting event id (2) an order in which we want to read the events (here: natural, that is the insertion order); and (3) two crucial cursor options, that we want the cursor to be tailable, and that we want to block if there&#8217;s no new data:
```java
DBObject query = lastReceivedEventId.isPresent()
   ? BasicDBObjectBuilder.start("_id", BasicDBObjectBuilder
         .start("$gte", lookFrom(lastReceivedEventId.get())).get())
         .get()
   : null;

DBObject sortBy = BasicDBObjectBuilder.start("$natural", 1).get();

DBCollection collection = ... // must be a capped collection
DBCursor cursor = collection
   .find(query)
   .sort(sortBy)
   .addOption(Bytes.QUERYOPTION_TAILABLE)
   .addOption(Bytes.QUERYOPTION_AWAITDATA);
```

You may wonder what is `lookFrom`. In general, we&#8217;d like to get all events inserted in the collection after the `lastReceivedEventId`. Unfortunately, that is not possible with Mongo currently. The ids of events (`ObjectId`s) have timestamps built-in, but we can&#8217;t fully rely on them, as they might be produced by clients with clock skews etc. Of course looking through all the events would work, but to speed up the process, we could use some heuristic. For example, we may start looking for events with an id greater than an id corresponding to the last event&#8217;s id timestamp minus 10 minutes. Then we need to discard all events, until the last one is reached. This assumes that clock skews are smaller than 10 minutes, and that events are persisted within 10 minutes from creation.

The cursor&#8217;s class extends the basic Java `Iterator` interface, so it&#8217;s fairly easy to use. So now we can take care of batching. When iterating over a cursor, the driver receives the data from the Mongo server in batches; so we may simply call `hasNext()` and `next()`, as with any other iterator, to receive subsequent elements, and only some calls will actually cause network communication with the server. 

In the Mongo Java driver the call that is actually potentially blocking is `hasNext()`. If we want to process the events in batches, we need to (a) read the elements as long as they are available, and (b) have some way of knowing before getting blocked that there are no more events, and that we can process the events already batched. And as `hasNext()` can block, we can&#8217;t do this directly.

That&#8217;s why we introduced an intermediate queue ([`LinkedBlockingQueue`][10]). In a separate thread, events read from the cursor are put on the queue as they come. If there are no events, the thread will block on `cursor.hasNext()`. The blocking queue has an optional size limit, so if it&#8217;s full, putting an element will block as well until space is available. In the event-consumer thread, we first try to read a single element from the queue, in a blocking fashion (using `.poll`, so here we wait until any event is available). Then we try to drain the whole content of the queue to a temporary collection (using `.drainTo`, building the batch, and potentially getting 0 elements, but we always have the first one).

[<img loading="lazy" decoding="async" src="http://www.warski.org/blog/wp-content/uploads/2012/11/mongo-streaming-300x241.png" alt="" title="mongo streaming" width="300" height="241" class="aligncenter size-medium wp-image-728" srcset="https://www.warski.org/blog/wp-content/uploads/2012/11/mongo-streaming-300x241.png 300w, https://www.warski.org/blog/wp-content/uploads/2012/11/mongo-streaming.png 704w" sizes="(max-width: 300px) 100vw, 300px" />][11]

An important thing to mention is that if the collection is empty, Mongo won&#8217;t block, so we have to fall back to active polling. We also have to take care of the fact that the cursor may die during this wait; to check this we should verify that `cursor.getCursorId() != 0`, where 0 is an id of a &#8220;dead cursor&#8221;. In such a case we simply need to re-instantiate the cursor.

### Summing up

To sum up, we got a very fast event sourcing/streaming solution. It is &#8220;self regulating&#8221;, in the sense that if there&#8217;s a peak of event activity, they will be read by the event sinks with a delay, in large batches. If the event activity is low, they will be processed quickly in small batches.

We&#8217;re also using the same Mongo instance for other purposes; having a single DB system to cluster and maintain both for regular data and events is certainly great from an ops point of view.

**EDIT 25/02/2013:** Fixed a bug with re-starting a stream, looking from a given point.

 [1]: http://www.mongodb.org/
 [2]: http://softwaremill.com/
 [3]: http://martinfowler.com/eaaDev/EventSourcing.html
 [4]: http://martinfowler.com/bliki/CQRS.html
 [5]: http://docs.mongodb.org/manual/core/capped-collections/
 [6]: http://en.wikipedia.org/wiki/Circular_buffer
 [7]: http://api.mongodb.org/java/2.6/com/mongodb/WriteConcern.html
 [8]: http://www.mongodb.org/display/DOCS/Tailable+Cursors
 [9]: https://github.com/mongodb/mongo-java-driver
 [10]: http://docs.oracle.com/javase/6/docs/api/java/util/concurrent/LinkedBlockingQueue.html
 [11]: http://www.warski.org/blog/wp-content/uploads/2012/11/mongo-streaming.png
