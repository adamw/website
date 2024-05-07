---
title: 'Quicklens: traversing options and lists'
author: Adam Warski
type: post
date: 2015-03-14T16:52:37+00:00
url: /blog/2015/03/quicklens-traversing-options-and-lists/
simplecatch-sidebarlayout:
  - default
dsq_thread_id:
  - 3595083606
wp-syntax-cache-content:
  - |
    a:3:{i:1;s:3583:"
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
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">import</span> com.<span style="color: #000000;">softwaremill</span>.<span style="color: #000000;">quicklens</span>.<span style="color: #000080;">_</span>
    &nbsp;
    <span style="color: #0000ff; font-weight: bold;">case</span> <span style="color: #0000ff; font-weight: bold;">class</span> Street<span style="color: #F78811;">&#40;</span>name<span style="color: #000080;">:</span> String<span style="color: #F78811;">&#41;</span>
    <span style="color: #0000ff; font-weight: bold;">case</span> <span style="color: #0000ff; font-weight: bold;">class</span> Address<span style="color: #F78811;">&#40;</span>street<span style="color: #000080;">:</span> Option<span style="color: #F78811;">&#91;</span>Street<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#41;</span>
    <span style="color: #0000ff; font-weight: bold;">case</span> <span style="color: #0000ff; font-weight: bold;">class</span> Person<span style="color: #F78811;">&#40;</span>addresses<span style="color: #000080;">:</span> List<span style="color: #F78811;">&#91;</span>Address<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#41;</span>
    &nbsp;
    <span style="color: #0000ff; font-weight: bold;">val</span> person <span style="color: #000080;">=</span> Person<span style="color: #F78811;">&#40;</span>List<span style="color: #F78811;">&#40;</span>
      Address<span style="color: #F78811;">&#40;</span>Some<span style="color: #F78811;">&#40;</span>Street<span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;1 Functional Rd.&quot;</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span>,
      Address<span style="color: #F78811;">&#40;</span>Some<span style="color: #F78811;">&#40;</span>Street<span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;2 Imperative Dr.&quot;</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span>
    <span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span>
    &nbsp;
    <span style="color: #0000ff; font-weight: bold;">val</span> newPerson <span style="color: #000080;">=</span> modify<span style="color: #F78811;">&#40;</span>person<span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#40;</span><span style="color: #000080;">_</span>.<span style="color: #000000;">addresses</span>.<span style="color: #000000;">each</span>.<span style="color: #000000;">street</span>.<span style="color: #000000;">each</span>.<span style="color: #000000;">name</span><span style="color: #F78811;">&#41;</span>
      .<span style="color: #000000;">using</span><span style="color: #F78811;">&#40;</span><span style="color: #000080;">_</span>.<span style="color: #000000;">toUpperCase</span><span style="color: #F78811;">&#41;</span></pre></td></tr></table><p class="theCode" style="display:none;">import com.softwaremill.quicklens._
    
    case class Street(name: String)
    case class Address(street: Option[Street])
    case class Person(addresses: List[Address])
    
    val person = Person(List(
      Address(Some(Street(&quot;1 Functional Rd.&quot;))),
      Address(Some(Street(&quot;2 Imperative Dr.&quot;)))
    ))
    
    val newPerson = modify(person)(_.addresses.each.street.each.name)
      .using(_.toUpperCase)</p></div>
    ";i:2;s:1887:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">val</span> modifyAddress <span style="color: #000080;">=</span> modify<span style="color: #F78811;">&#40;</span><span style="color: #000080;">_:</span> Person<span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#40;</span><span style="color: #000080;">_</span>.<span style="color: #000000;">address</span><span style="color: #F78811;">&#41;</span>
    <span style="color: #0000ff; font-weight: bold;">val</span> modifyStreetName <span style="color: #000080;">=</span> modify<span style="color: #F78811;">&#40;</span><span style="color: #000080;">_:</span> Address<span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#40;</span><span style="color: #000080;">_</span>.<span style="color: #000000;">street</span>.<span style="color: #000000;">name</span><span style="color: #F78811;">&#41;</span>
    &nbsp;
    <span style="color: #0000ff; font-weight: bold;">val</span> newPerson <span style="color: #000080;">=</span> <span style="color: #F78811;">&#40;</span>modifyAddress andThenModify modifyStreetName<span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#40;</span>person<span style="color: #F78811;">&#41;</span>
      .<span style="color: #000000;">using</span><span style="color: #F78811;">&#40;</span><span style="color: #000080;">_</span>.<span style="color: #000000;">toUpperCase</span><span style="color: #F78811;">&#41;</span></pre></td></tr></table><p class="theCode" style="display:none;">val modifyAddress = modify(_: Person)(_.address)
    val modifyStreetName = modify(_: Address)(_.street.name)
    
    val newPerson = (modifyAddress andThenModify modifyStreetName)(person)
      .using(_.toUpperCase)</p></div>
    ";i:3;s:922:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;">copy<span style="color: #F78811;">&#40;</span>person<span style="color: #F78811;">&#41;</span>.<span style="color: #000000;">modifying</span><span style="color: #F78811;">&#40;</span><span style="color: #000080;">_</span>.<span style="color: #000000;">address</span>.<span style="color: #000000;">street</span>.<span style="color: #000000;">name</span><span style="color: #F78811;">&#41;</span>.<span style="color: #000000;">using</span><span style="color: #F78811;">&#40;</span><span style="color: #000080;">_</span>.<span style="color: #000000;">toUpperCase</span><span style="color: #F78811;">&#41;</span></pre></td></tr></table><p class="theCode" style="display:none;">copy(person).modifying(_.address.street.name).using(_.toUpperCase)</p></div>
    ";}
tags:
  - functional programming
  - metaprogramming
  - scala

---
[Quicklens][1] is a small library which allows to modify deeply nested fields in case classes e.g.: `modify(person)(_.address.street.name).using(_.toUpperCase)`, without the need to create dedicated lens objects.

I got some very good feedback on the initial release &#8211; thanks! There’s also a spin-off [implementation][2], using a different syntax.

One problem that I anticipated from the beginning and also mentioned in the comments, was that it wasn’t possible to traverse `Option`al fields. And that’s of now fixed! `Option`s and `List`s can be “unwrapped” using the `.each` method. For example:

<pre lang="scala" line="1">import com.softwaremill.quicklens._

case class Street(name: String)
case class Address(street: Option[Street])
case class Person(addresses: List[Address])

val person = Person(List(
  Address(Some(Street("1 Functional Rd."))),
  Address(Some(Street("2 Imperative Dr.")))
))

val newPerson = modify(person)(_.addresses.each.street.each.name)
  .using(_.toUpperCase)
</pre>

The `.each` can only be used inside `modify`. You can add support for your own containers by providing an implicit `QuicklensFunctor[C]` with the appropriate `C` type parameter (there are default implementations for `List`, `Vector` and `Option`).

## Other changes

  * added documentation on how to create lenses, that is a modification of a path parametrized by the actual object: `val nameLens = modify(_: Person)(_.address.street.name)`. This can be later used as follows: `nameLens(person).using(_.toUpperCase)`.
  * a dedicated method to set a new value of a field, instead of transforming the old value: `modify(person)(_.address.street.name).setTo("2 Imperative Dr.")`
  * a `andThenModify` method provided by an implicit conversion to combine two lenses:

<pre lang="scala" line="1">val modifyAddress = modify(_: Person)(_.address)
val modifyStreetName = modify(_: Address)(_.street.name)

val newPerson = (modifyAddress andThenModify modifyStreetName)(person)
  .using(_.toUpperCase)
</pre>

## Alternate syntax

There were also some comments that the current syntax suggests mutable behaviour (which, of course, is not true &#8211; all modifications result in copies of the case classes to be created). An alternative could be:

<pre lang="scala" line="1">copy(person).modifying(_.address.street.name).using(_.toUpperCase)
</pre>

However it is a bit longer than the original. What do you think?

[Quicklens][1] is available on GitHub under the Apache2 license.

 [1]: https://github.com/adamw/quicklens
 [2]: https://github.com/pathikrit/sauron
