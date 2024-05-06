---
title: Dry parameter names
author: Adam Warski
type: post
date: 2013-01-26T18:56:34+00:00
url: /blog/2013/01/dry-parameter-names/
dsq_thread_id:
  - 1050955939
wp-syntax-cache-content:
  - |
    a:4:{i:1;s:889:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">class</span> FriendsTimelineReader<span style="color: #F78811;">&#40;</span>userFinder<span style="color: #000080;">:</span> UserFinder,
       userStatusReader<span style="color: #000080;">:</span> UserStatusReader,
       statusSecurityFilter<span style="color: #000080;">:</span> StatusSecurityFilter<span style="color: #F78811;">&#41;</span> <span style="color: #F78811;">&#123;</span>
       ...
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">class FriendsTimelineReader(userFinder: UserFinder,
       userStatusReader: UserStatusReader,
       statusSecurityFilter: StatusSecurityFilter) {
       ...
    }</p></div>
    ";i:2;s:570:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">class</span> FriendsTimelineReader<span style="color: #F78811;">&#40;</span>a UserFinder,
      a UserStatusReader,
      a StatusSecurityFilter<span style="color: #F78811;">&#41;</span></pre></td></tr></table><p class="theCode" style="display:none;">class FriendsTimelineReader(a UserFinder,
      a UserStatusReader,
      a StatusSecurityFilter)</p></div>
    ";i:3;s:663:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">val</span> user10 <span style="color: #000080;">=</span> <span style="color: #F78811;">&#40;</span>the UserFinder<span style="color: #F78811;">&#41;</span>.<span style="color: #000000;">findById</span><span style="color: #F78811;">&#40;</span><span style="color: #F78811;">10</span><span style="color: #F78811;">&#41;</span></pre></td></tr></table><p class="theCode" style="display:none;">val user10 = (the UserFinder).findById(10)</p></div>
    ";i:4;s:992:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">class</span> Friends<span style="color: #F78811;">&#40;</span>a <span style="color: #CC66FF;">'first</span> Person, a <span style="color: #CC66FF;">'second</span> Person<span style="color: #F78811;">&#41;</span> <span style="color: #F78811;">&#123;</span>
       ...
       <span style="color: #F78811;">&#40;</span>the <span style="color: #CC66FF;">'first</span> Person<span style="color: #F78811;">&#41;</span>.<span style="color: #000000;">getName</span><span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>
       ...
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">class Friends(a 'first Person, a 'second Person) {
       ...
       (the 'first Person).getName()
       ...
    }</p></div>
    ";}
categories:
  - Java
  - Languages
  - Methodology
  - Object oriented
  - Scala
  - Uncategorized

---
How often do you see code like this, especially when using dependency injection, single-responsibility principle, and other &#8220;good practices&#8221;?

<pre lang="scala" line="1">class FriendsTimelineReader(userFinder: UserFinder,
   userStatusReader: UserStatusReader,
   statusSecurityFilter: StatusSecurityFilter) {
   ...
}
</pre>

Note that the parameter names are **exact copies** of the class name. That&#8217;s certainly not [DRY][1]!

In Java writing such code is a bit easier than in Scala, since you write the type first, and then the variable name can be auto-completed (the fact that there&#8217;s an auto-complete in IDEs indicates that it&#8217;s a common pattern). In Scala there&#8217;s more typing, but then, less boilerplate around declaring fields/defining a constructor/assigning to fields.

How to avoid that? We can always use cryptic variable names, like &#8220;`ssf`&#8221; instead of &#8220;`statusSecurityFilter`&#8220;. But then the whole effort of having nicely named classes to make the code readable, which is quite a hard task, goes to waste.

Of course, variable names are very useful, for example when we have to distinguish between the 10 `String` parameters that our method takes, create an `Int` counter, etc. But the more we use specialised wrapper-types (and now possibly even more, with Scala 2.10 introducing [value classes][2]) to make our code more type-safe, the more fine-grained our services &#8211; the more often the variable/parameter names will mirror the class name.

Even when there are a couple of instances of a class, often the parameter name contains some **qualifier** + the class name (e.g. given a `Person` class, `personAssociator.createFriends(<strong>firstPerson</strong>, <strong>secondPerson</strong>)`). It would be interesting to see some statistics on how often variable name is a mirror of the class name or a suffix &#8211; I suspect it can be a large percentage.

Maybe the next step then in type safety is limiting the complete freedom when naming parameters? Or even, getting rid of the parameter names at all in some cases.

For example. In the snippet from the beginning, we just want to get &#8220;an instance&#8221; of a `UserFinder`. Most probably there will ever be only one instance of this class in the system, and certainly one will be used at a time by other classes. So why not let the class express that it wants an instance of a class, without having to name it? Let&#8217;s use the [indefinite article][3] &#8220;a&#8221; as a keyword (we don&#8217;t really care which instance is passed):

<pre lang="scala" line="1">class FriendsTimelineReader(a UserFinder,
  a UserStatusReader,
  a StatusSecurityFilter)
</pre>

Now, how would you use such a variable inside a class? Let&#8217;s use the definite article &#8220;the&#8221; &#8211; the instance that was given to the class, for example:

<pre lang="scala" line="1">val user10 = (the UserFinder).findById(10)
</pre>

Maybe this looks a bit like science-fiction, but wouldn&#8217;t it be convenient? Or maybe this problem is already solved by some mechanisms in other languages?

Adam

_EDIT 27/01/2013_: Extending the above to handle qualifiers:

<pre lang="scala" line="1">class Friends(a 'first Person, a 'second Person) {
   ...
   (the 'first Person).getName()
   ...
}
</pre>

 [1]: http://en.wikipedia.org/wiki/Don't_repeat_yourself
 [2]: http://docs.scala-lang.org/sips/pending/value-classes.html
 [3]: http://en.wikipedia.org/wiki/Article_(grammar)
