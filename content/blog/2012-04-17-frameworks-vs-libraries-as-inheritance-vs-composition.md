---
title: Frameworks vs Libraries as Inheritance vs Composition?
author: Adam Warski
type: post
date: 2012-04-17T06:04:21+00:00
url: /blog/2012/04/frameworks-vs-libraries-as-inheritance-vs-composition/
dsq_thread_id:
  - 1051936566
tags:
  - architecture
  - clean code

---
For quite some time inheritance was the dominant model of structuring programs in OO languages like Java. Very often it was used as a mechanism for reusing code &#8211; &#8220;common&#8221; functions where placed in an abstract class, so that subclasses can use them.

However, this often proves to be very limiting, as you can only inherit from a single class. The code becomes constrained and tied to one particular framework-specific class. Not to mention testing &#8211; such base classes often depend on outside state, making tests hard to setup.

That&#8217;s why nowadays time and again you can hear that you should prefer composition over inheritance (see for example [this StackOverflow question][1]). When using composition, you can leverage multiple re-usable chunks of code, and combine them in an arbitrary way. Also using IoC/Dependency Injection strongly favors composition.

I think the above inheritance-composition opposition strongly resembles the framework-library distinction. When using a framework, you are forced into a specific structure, where you must model your code in one specific way. Quite obviously it&#8217;s often hard or impossible to use two frameworks in one layer/module. That&#8217;s how hacks, ugly workarounds, reflection madness, etc. is born.

Libraries on the other hand (unless they are deliberately wrongly written), can be freely combined. Just like composition of classes, you can compose usage of many libraries in one module. Your code can be kept clean and only use the library functionality it really requires.

I&#8217;m not saying that frameworks are bad &#8211; just like inheritance, they may be very useful if used in the correct places. However, next time you put your code into a framework, maybe it&#8217;s better to think twice: can this functionality be implemented using composition, with the help of a library? Won&#8217;t this make my code cleaner and more maintainable?

Adam

 [1]: http://stackoverflow.com/questions/49002/prefer-composition-over-inheritance
