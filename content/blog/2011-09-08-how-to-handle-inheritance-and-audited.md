---
title: How to handle inheritance and @Audited?
author: Adam Warski
type: blog
date: 2011-09-08T12:13:05+00:00
url: /blog/2011/09/how-to-handle-inheritance-and-audited/
dsq_thread_id:
  - 1051860488
tags:
  - envers
  - hibernate
  - java

---
Some [Envers][1] uses had problems because of the limited flexibility in specifying which fields from superclasses (especially those annotated with `@MappedSuperclass`) should be audited or not. We improved a bit in the latest release, by being able to explicitly enumerate the superclasses to audit.

But still this is not fully flexible. That&#8217;s why [Łukasz Antoniak][2] started a discussion on the Envers forum (<http://community.jboss.org/message/624057#624057>) to gather remarks and use-cases on how people use Envers with inheritance. Your opinion will be very valuable to us, so please share!

Adam

 [1]: http://jboss.org/envers
 [2]: http://lukaszantoniak.wordpress.com/
