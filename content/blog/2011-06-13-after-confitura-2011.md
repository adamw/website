---
title: After Confitura 2011
author: Adam Warski
type: blog
date: 2011-06-13T14:31:13+00:00
url: /blog/2011/06/after-confitura-2011/
disqus_identifier: 1057971689
tags:
  - testing
  - conferences
  - aws
  - jee
  - cloud
  - dependency injection
  - java

---
[Confitura 2011][1] went past very quickly, it was a great conference and a great occasion to meet with the polish Java community on the [SPOINA][2] after-party.

During the conference I had the opportunity to present two talks.

The first, done together with [Tomek Szymański][3] was an introduction to cloud computing using [Amazon Web Services][4]. We&#8217;ve explained how the basic services that Amazon offers (servers, load balancing, databases, queueing) work, and then did a live demo transforming a traditional hosted-server+JMS+JPA application into a version which uses EC2+SQS+SimpleDB. During the presentation we simulated heavy load on our application and started a couple of servers using the EC2 API to show that it scales nicely.

The full code of the (pretty simple) application used in the presentation is available here: <https://github.com/softwaremill/aws-demo>, and the slides (in polish) are here: <http://t.co/nCDan5R>.

The second presentation was about CDI Portable Extensions. First I did an introduction to CDI and then explained the idea behind Portable Extensions. The main part of the presentation was a live demo, where I showed how to create two extensions:

  * dynamic alternatives &#8211; the extension was adding and removing the `@Alternative` annotation at run-time basing on some configuration file, allowing the application to dynamically choose an implementation of the bean that should be used
  * dynamically creating beans which check if methods on a secure bean (secured with an interceptor) can be invoked (allowing the programmer to inject `CanAccess<MyBean>`)

Again, the full code of the demo extensions is available on GitHub: <https://github.com/adamw/portable-extensions-demo> and the slides (in polish) here: <http://slidesha.re/lvx3hh>.

And finally, a huge &#8220;thank you&#8221; to all of the conference organizers (Łukasz Lenart, Bartek Zdanowski, Tomek Szymański, Michał Margiel, Paweł Wrzeszcz, Sebastian Pietrowski and Jacek Laskowski), volunteers, sponsors and participants for putting together a great event!

Adam

 [1]: http://confitura.pl
 [2]: https://picasaweb.google.com/Confiturapl/Spoina
 [3]: http://twitter.com/#!/szimano
 [4]: http://aws.amazon.com
