---
title: Adding structure to Seam events
author: Adam Warski
type: post
date: 2008-10-23T13:19:07+00:00
url: /blog/2008/10/adding-structure-to-seam-events/
simplecatch-sidebarlayout:
  - default
dsq_thread_id:
  - 1051934657
categories:
  - java
  - jboss

---
The idea described here is originally by [Tomek Szymański][1]. I just enhanced it a little.

[Seam events][2] are a very convenient tool to divide your business logic into smaller pieces, and add some new behaviour as necessary. However, they are very unstructured &#8211; an event name is just a String that you&#8217;ve got to remember. It&#8217;s hard to find out what events your application raises, what are the parameters and in what situations the events are actually raised &#8211; there&#8217;s no place (in code) to document it.[Inflatable Toys][3]

As a remedy, you can create a special package (let&#8217;s say `events`), where, for each event that your application raises, you create an interface corresponding to that event. So, if you raise an event `org.myapp.events.MessageCreated`, you create a `MessageCreated` interface in the `org.myapp.events` package. In the javadoc for this interface, you can describe when the event is raised and what are the parameters.

Furthermore, all components that contain methods, which observe the event, can implement the corresponding event interface (so it will be used as a marker interface). All modern IDEs let you find implementations of an interface, so finding out which methods are observing your event is trivial!

Taking it a step further, the marker interface may also contain a method declaration. An implementation of that method should observe the event. That way, you can make it even easier to provide information about the parameters of the event. For example:

<pre lang="java" line="1" escape="true">package org.myapp.events;

public interface MessageCreated {
   void handleMessageCreated(Message message, User user);
}
</pre>

<pre lang="java" line="1" escape="true">package org.myapp.components;

@Name("sendEmails")
public class SendEmails implements MessageCreated {
   @Observer("org.myapp.events.MessageCreated")
   void handleMessageCreated(Message message, User user) {
      // Send an e-mail to the administrator that there is a new 
      // message
   }
}
</pre>

Finally, what about raising events? If you raise them programmatically, you may also use the interface, instead of the `String` form of the event name. For example:

<pre lang="java" line="1" escape="true">public void sendMessage() {
   // ...
   Events.instance().raiseEvent(MessageCreated.class.getName(), 
         message, user);
   // ...
}
</pre>

This has two benefits. Firstly, by finding the usages of the `MessageCreated` interface, you know where the event is raised. Secondly, it&#8217;s **refactor-safe**: when you decide to rename the event, you just rename or move the interface. Your IDE does the rest for you (unless your IDE is vim ;) ).

Of course, you&#8217;ll still need to use the `String` form of the event&#8217;s name in the `@Raise` annotation and when raising events in `pages.xml`.

To sum up: creating marker interfaces for events gives you a place to document them, and can provide partial safe refactoring of event names. What are your practicies when using Seam events?

Adam

 [1]: http://szimano.org
 [2]: http://docs.jboss.com/seam/latest/reference/en-US/html/events.html#d0e5201
 [3]: http://www.china-inflatable.co.uk/Wholesale-4-b0-Inflatable-Toys/
