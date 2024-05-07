---
title: 'Quicklens: modify deeply nested case class fields'
author: Adam Warski
type: post
date: 2015-02-28T19:51:46+00:00
url: /blog/2015/02/quicklens-modify-deeply-nested-case-class-fields/
simplecatch-sidebarlayout:
  - default
dsq_thread_id:
  - 3556536666
wp-syntax-cache-content:
  - |
    a:4:{i:1;s:1671:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">case</span> <span style="color: #0000ff; font-weight: bold;">class</span> Street<span style="color: #F78811;">&#40;</span>name<span style="color: #000080;">:</span> String<span style="color: #F78811;">&#41;</span>
    <span style="color: #0000ff; font-weight: bold;">case</span> <span style="color: #0000ff; font-weight: bold;">class</span> Address<span style="color: #F78811;">&#40;</span>street<span style="color: #000080;">:</span> Street<span style="color: #F78811;">&#41;</span>
    <span style="color: #0000ff; font-weight: bold;">case</span> <span style="color: #0000ff; font-weight: bold;">class</span> Person<span style="color: #F78811;">&#40;</span>address<span style="color: #000080;">:</span> Address<span style="color: #F78811;">&#41;</span>
    &nbsp;
    <span style="color: #0000ff; font-weight: bold;">val</span> person <span style="color: #000080;">=</span> Person<span style="color: #F78811;">&#40;</span>Address<span style="color: #F78811;">&#40;</span>Street<span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;1 Functional Rd.&quot;</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span></pre></td></tr></table><p class="theCode" style="display:none;">case class Street(name: String)
    case class Address(street: Street)
    case class Person(address: Address)
    
    val person = Person(Address(Street(&quot;1 Functional Rd.&quot;)))</p></div>
    ";i:2;s:1287:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    6
    7
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;">person.<span style="color: #000000;">copy</span><span style="color: #F78811;">&#40;</span>
      address <span style="color: #000080;">=</span> person.<span style="color: #000000;">address</span>.<span style="color: #000000;">copy</span><span style="color: #F78811;">&#40;</span>
        street <span style="color: #000080;">=</span> person.<span style="color: #000000;">address</span>.<span style="color: #000000;">street</span>.<span style="color: #000000;">copy</span><span style="color: #F78811;">&#40;</span>
          name <span style="color: #000080;">=</span> person.<span style="color: #000000;">address</span>.<span style="color: #000000;">street</span>.<span style="color: #000000;">name</span>.<span style="color: #000000;">toUpperCase</span>
        <span style="color: #F78811;">&#41;</span>
      <span style="color: #F78811;">&#41;</span>
    <span style="color: #F78811;">&#41;</span></pre></td></tr></table><p class="theCode" style="display:none;">person.copy(
      address = person.address.copy(
        street = person.address.street.copy(
          name = person.address.street.name.toUpperCase
        )
      )
    )</p></div>
    ";i:3;s:2282:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">val</span> <span style="color: #000080;">_</span>name <span style="color: #000080;">=</span> Lenser<span style="color: #F78811;">&#91;</span>Street<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#40;</span><span style="color: #000080;">_</span>.<span style="color: #000000;">name</span><span style="color: #F78811;">&#41;</span>
    <span style="color: #0000ff; font-weight: bold;">val</span> <span style="color: #000080;">_</span>street <span style="color: #000080;">=</span> Lenser<span style="color: #F78811;">&#91;</span>Address<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#40;</span><span style="color: #000080;">_</span>.<span style="color: #000000;">address</span><span style="color: #F78811;">&#41;</span>
    <span style="color: #0000ff; font-weight: bold;">val</span> <span style="color: #000080;">_</span>address <span style="color: #000080;">=</span> Lenser<span style="color: #F78811;">&#91;</span>Person<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#40;</span><span style="color: #000080;">_</span>.<span style="color: #000000;">person</span><span style="color: #F78811;">&#41;</span>
    &nbsp;
    <span style="color: #F78811;">&#40;</span><span style="color: #000080;">_</span>address ^|-<span style="color: #000080;">&gt;</span> <span style="color: #000080;">_</span>street ^|-<span style="color: #000080;">&gt;</span> <span style="color: #000080;">_</span>name<span style="color: #F78811;">&#41;</span>.<span style="color: #000000;">modify</span><span style="color: #F78811;">&#40;</span><span style="color: #000080;">_</span>.<span style="color: #000000;">toUpperCase</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#40;</span>person<span style="color: #F78811;">&#41;</span></pre></td></tr></table><p class="theCode" style="display:none;">val _name = Lenser[Street](_.name)
    val _street = Lenser[Address](_.address)
    val _address = Lenser[Person](_.person)
    
    (_address ^|-&gt; _street ^|-&gt; _name).modify(_.toUpperCase)(person)</p></div>
    ";i:4;s:869:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;">modify<span style="color: #F78811;">&#40;</span>person<span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#40;</span><span style="color: #000080;">_</span>.<span style="color: #000000;">address</span>.<span style="color: #000000;">street</span>.<span style="color: #000000;">name</span><span style="color: #F78811;">&#41;</span>.<span style="color: #000000;">using</span><span style="color: #F78811;">&#40;</span><span style="color: #000080;">_</span>.<span style="color: #000000;">toUpperCase</span><span style="color: #F78811;">&#41;</span></pre></td></tr></table><p class="theCode" style="display:none;">modify(person)(_.address.street.name).using(_.toUpperCase)</p></div>
    ";}
tags:
  - functional programming
  - metaprogramming
  - scala

---
_TL;DR_: [Quicklens][1]: modify deeply nested fields in case classes, e.g.:  
`modify(person)(_.address.street.name).using(_.toUpperCase)`.  
Similar to [lenses][2], but without the actual lens creation.

Lenses are very useful when you have to update a deeply nested field in a hierarchy of case classes. For example if we have:

<pre lang="scala" line="1">case class Street(name: String)
case class Address(street: Street)
case class Person(address: Address)

val person = Person(Address(Street("1 Functional Rd.")))
</pre>

and we’d like to modify the `name` of the street (let’s say convert to upper case), we would have to do:

<pre lang="scala" line="1">person.copy(
  address = person.address.copy(
    street = person.address.street.copy(
      name = person.address.street.name.toUpperCase
    )
  )
)
</pre>

Quite a lot of boilerplate! Plus it’s quite hard to see what we are actually trying to achieve.

One solution is to use lenses and lens composition, which provide a much shorter way to achieve the above. There’s a couple of lens libraries, e.g. [Monocle][3]; using it, our example now becomes:

<pre lang="scala" line="1">val _name = Lenser[Street](_.name)
val _street = Lenser[Address](_.address)
val _address = Lenser[Person](_.person)

(_address ^|-> _street ^|-> _name).modify(_.toUpperCase)(person)
</pre>

Lenses can be also created using macro annotations, however that’s IDE-unfriendly. The lens objects (`_name`, `_street`, `_address`) provide a view into a specific field of a case class, and can be composed with each other (plus a couple of extra things!).

What if we just want to update a field? The process of creating the lens objects can then become boilerplate as well.

Using the [Quicklens][1] macro we can simplify the original task to:

<pre lang="scala" line="1">modify(person)(_.address.street.name).using(_.toUpperCase)
</pre>

As `modify` is a macro, this code is transformed during **compile time** into the appropriate chain of &#8220;copy&#8221; invocations (no intermediate objects or actual lenses are created); hence the bytecode is almost identical to the original, &#8220;boilerplate&#8221; solution.

I think Quicklens can be a good alternative for lens creation if you need just the field updates, without the additional features of full-blown lenses. I’m also not aware of a similar existing implementation. [Quicklens][1] is open-source and available on Github under the Apache2 license. Comments welcome!

 [1]: https://github.com/adamw/quicklens
 [2]: http://eed3si9n.com/learning-scalaz/Lens.html
 [3]: https://github.com/julien-truffaut/Monocle
