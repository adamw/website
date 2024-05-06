---
title: Makerland, hackaton and turtles
author: Adam Warski
type: post
date: 2014-03-21T14:35:49+00:00
url: /blog/2014/03/makerland-hackaton-and-turtles/
simplecatch-sidebarlayout:
  - default
dsq_thread_id:
  - 2477146077
categories:
  - different
  - electronics
  - iBeacon
  - makerland
  - Uncategorized

---
The first [Makerland][1] is over &#8211; if you read only the first sentence of this blog, the conference was great, watch out for the second edition (hopefully there will be one)! 

What is Makerland? A hardware conference for non-specialists (not only developers, though they were the majority) &#8211; think Arduinos, Raspberry Pis, sensors, Mindstorms, 3D printers, embedded devices, Internet of Things. On the software side, (almost) everything seems to be done in Node.js ;-).

Why is Makerland special? First of all, it is something completely different to what I&#8217;m doing daily (which is coding in Scala and being one of 25 CEOs in [SoftwareMill][2]). I did some &#8220;make things&#8221; stuff before (see e.g. [piano pedal][3] and [ibeacons][4]), so I wasn&#8217;t completely green in the field, still I learned a lot.

The format of the conference was quite unusual: the first half of the day was full of talks (as for my hobby-level too many focused on actually manufacturing and shipping your thing on a larger scale), and the second half was filled with workshops, which you can choose and switch freely (free seats permitting).

<a href="http://www.warski.org/blog/2014/03/makerland-hackaton-and-turtles/img_3505/" rel="attachment wp-att-1224"><img loading="lazy" decoding="async" src="http://www.warski.org/blog/wp-content/uploads/2014/03/IMG_3505-300x225.jpg" alt="IMG_3505" width="300" height="225" class="aligncenter size-medium wp-image-1224" srcset="https://www.warski.org/blog/wp-content/uploads/2014/03/IMG_3505-300x225.jpg 300w, https://www.warski.org/blog/wp-content/uploads/2014/03/IMG_3505-1024x768.jpg 1024w, https://www.warski.org/blog/wp-content/uploads/2014/03/IMG_3505-210x157.jpg 210w" sizes="(max-width: 300px) 100vw, 300px" /></a>

Completing one workshop took from 1 hour to a whole afternoon, so I had to make some hard choices, as time and energy was limited. I enjoyed all the workshops I attended: building a sumo bot, soldering, creating a makey-makey-style sphero controller with [Cylon.js][5], sewing a smart watch, flying with a quadcopter and more. I also 3d printed a turtle and a magic wand, which is a hit with my daughter.

<a href="http://www.warski.org/blog/2014/03/makerland-hackaton-and-turtles/img_3517/" rel="attachment wp-att-1226"><img loading="lazy" decoding="async" src="http://www.warski.org/blog/wp-content/uploads/2014/03/IMG_3517-300x225.jpg" alt="IMG_3517" width="300" height="225" class="aligncenter size-medium wp-image-1226" srcset="https://www.warski.org/blog/wp-content/uploads/2014/03/IMG_3517-300x225.jpg 300w, https://www.warski.org/blog/wp-content/uploads/2014/03/IMG_3517-1024x768.jpg 1024w, https://www.warski.org/blog/wp-content/uploads/2014/03/IMG_3517-210x157.jpg 210w" sizes="(max-width: 300px) 100vw, 300px" /></a>

Speaking of turtles, the last day was a hackaton, where you could build whatever you wanted. Do you remember the [Logo programming lanugage][6]? You controlled a turtle (with code) which did various drawings. In a team with [Jacek][7], [Antoni][8] and [Jakub][9], we decided to build a real-life version of the turtle: **Turtlebot**. We&#8217;ve used the sumo bot parts as the base, Lego for pen support, Play-Doh for actually holding the pen, some servo motors, spark as a wireless controller (all conference attendees got a [Spark Core][10]!) and some [arduino/python/js code][11]. Thanks to the Spark cloud, we were able to control our turtle via REST calls, and hook up a Python DSL to accept Logo-like commands.

<a href="http://www.warski.org/blog/2014/03/makerland-hackaton-and-turtles/img_3516/" rel="attachment wp-att-1222"><img loading="lazy" decoding="async" src="http://www.warski.org/blog/wp-content/uploads/2014/03/IMG_3516-300x225.jpg" alt="IMG_3516" width="300" height="225" class="aligncenter size-medium wp-image-1222" srcset="https://www.warski.org/blog/wp-content/uploads/2014/03/IMG_3516-300x225.jpg 300w, https://www.warski.org/blog/wp-content/uploads/2014/03/IMG_3516-1024x768.jpg 1024w, https://www.warski.org/blog/wp-content/uploads/2014/03/IMG_3516-210x157.jpg 210w" sizes="(max-width: 300px) 100vw, 300px" /></a>

The WiFi was a bit of a problem, and the robot lacked some precision, but as for a first prototype, I think it behaved very well.

And guess what &#8211; **we won the hackaton** :) The 3d turtle model now waits for the 2.0 version of the robot. Thanks again to all of the organizers for their effort, and see you in a year!

 [1]: http://www.makerland.org/
 [2]: https://softwaremill.com/
 [3]: http://www.warski.org/blog/2012/08/connecting-a-piano-pedal-to-a-computer/
 [4]: https://softwaremill.com/first-softwaremill-hackaton-ibeacons/
 [5]: http://cylonjs.com/
 [6]: http://en.wikipedia.org/wiki/Logo_(programming_language)
 [7]: https://twitter.com/rucek
 [8]: https://twitter.com/spherefoundry
 [9]: https://twitter.com/kvbik
 [10]: https://www.spark.io/
 [11]: https://github.com/adamw/turtlebot
