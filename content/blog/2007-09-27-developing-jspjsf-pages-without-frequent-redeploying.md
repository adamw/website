---
title: Developing JSP/JSF pages without frequent redeploying
author: Adam Warski
type: blog
date: 2007-09-27T20:14:56+00:00
url: /blog/2007/09/developing-jspjsf-pages-without-frequent-redeploying/
dsq_thread_id:
  - 1051933987
tags:
  - java
  - jsf

---
Imagine you are developing a web application, which displays some JSP files. It is pretty annoying to have to re-deploy the application whenever you make any change to a JSP to see the result in the browser (there are other ways to get rid of that annoying problem, of course; here I&#8217;m describing only one way of many). It would be really handy if the JSP files were read from outside the .war &#8211; most preferably, from the directory in which you develop your web application.

This is where the **resources filter** may help you. This filter reads JSP files (and other resources) from a specified directory in the file system, instead of reading them from the deployed application archive. Thanks to that, if you are developing web pages, you can edit them using your favorite IDE and view your changes immediately.

To use the filter, you only need to include a jar with one class and add one filter definition to your `web.xml`. The filter works with JBoss AS 4.0.5 and 4.2, **portlets** and **JSF**. For more details check out:

  * [usage & configuration (wiki)][1], and of course
  * [downloads.][2]

Cheers,  
Adam

 [1]: http://labs.jboss.com/wiki/ShotokuWebFilter
 [2]: http://labs.jboss.com/shotoku/downloads/web
