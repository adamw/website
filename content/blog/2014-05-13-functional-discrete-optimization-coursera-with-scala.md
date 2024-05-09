---
title: Functional Discrete Optimization @ Coursera with Scala
author: Adam Warski
type: blog
date: 2014-05-13T10:43:21+00:00
url: /blog/2014/05/functional-discrete-optimization-coursera-with-scala/
simplecatch-sidebarlayout:
  - default
disqus_identifier:
  - 2681596581
tags:
  - something different
  - functional programming
  - scala

---
Recently I took part in the [Discrete Optimization][1] class at Coursera. I must say that it was the best [MOOC][2] I have attended so far, because of two factors.

<a href="http://www.warski.org/blog/2014/05/functional-discrete-optimization-coursera-with-scala/discreetoptimisation_logo/" rel="attachment wp-att-1312"><img loading="lazy" decoding="async" src="http://www.warski.org/blog/wp-content/uploads/2014/05/DiscreetOptimisation_logo.png" alt="Course logo" width="240" height="135" class="aligncenter size-full wp-image-1312" srcset="https://www.warski.org/blog/wp-content/uploads/2014/05/DiscreetOptimisation_logo.png 240w, https://www.warski.org/blog/wp-content/uploads/2014/05/DiscreetOptimisation_logo-210x118.png 210w" sizes="(max-width: 240px) 100vw, 240px" /></a>

First of all, there was a lot of coding, and as a programmer, I like coding. The final grade depended only on how well you solved the programming assignments; there were no quizzes, exams, essays, etc., only practical optimization problems to solve. Nothing helps you to understand the different optimization techniques and the different problems you can encounter, as coding a solver by hand.

Secondly, the tasks were pretty hard, so the class was challenging. Optimization problems are usually at least NP-complete, so there’s a natural ground for competition, trying to find the best solutions. And that’s what the course authors did, apart from a 0-10 score for each assigment part, there was also a leaderboard, showing who got the best results on each problem (the leaderboard didn’t count towards the final grade). To get 10 points you had to find a very good quality solution, but the leaders of the leaderboard usually achieved much better results.

Of course, there were video lectures, which were crucial to solving the problems. Professor Pascal Van Hentenryck does a great job explaining the algorithms (with lots of examples), keeping you awake at the same time, because of his lecturing style and enthusiasm.

On the technical side, I used Scala to implement the solutions for all but one problem (which I solved using general-purpose [Mixed Integer Programming][3] solvers: [lpsolve][4] and [SCIP][5]). And Scala proved to be excellent to concisely and rapidly implement various attempts to solve the problems. I also very often used the “functional” approach; e.g. to represent the solutions, I used immutable data structures, for which there’s excellent support in the Scala collections library, and most of the functions, manipulating those data structures, were pure.

Immutability of the data structures played an important role: implementing a Constraint Programming solver (backtracking recursive search), or a Local Search algorithm is much easier when you don’t have to worry about cloning the solutions, &#8220;rolling back&#8221; changes which proved to yield a worse score, etc. Each change introduces a completely new solution, which you can freely manipulate further, leaving the original intact.

I could probably achieve better performance by using lower-level abstractions and mutable data structures; but I think that a much better time investment was improving the algorithms. In the end, usually using a simple, immutable representation of the solutions (but investing in the algorithms) worked very well, and got me 10 points on the assignment parts.

If you haven’t attended the course yet, watch out for the next edition!

 [1]: https://www.coursera.org/course/optimization
 [2]: http://en.wikipedia.org/wiki/Massive_open_online_course
 [3]: http://en.wikipedia.org/wiki/Integer_programming
 [4]: http://lpsolve.sourceforge.net/5.5/
 [5]: http://scip.zib.de/
