---
title: 'MacWire 0.8.0: towards 1.0, tagging, anonymous functions support'
author: Adam Warski
type: blog
date: 2015-01-20T15:14:52+00:00
url: /blog/2015/01/macwire-0-8-0-towards-1-0-tagging-anonymous-functions-support/
simplecatch-sidebarlayout:
  - default
dsq_thread_id:
  - 3438397454
wp-syntax-cache-content:
  - |
    a:2:{i:1;s:2117:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    6
    7
    8
    9
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">class</span> Berry<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>
    <span style="color: #0000ff; font-weight: bold;">trait</span> Black
    <span style="color: #0000ff; font-weight: bold;">trait</span> Blue
    &nbsp;
    <span style="color: #0000ff; font-weight: bold;">case</span> <span style="color: #0000ff; font-weight: bold;">class</span> Basket<span style="color: #F78811;">&#40;</span>blueberry<span style="color: #000080;">:</span> Berry <span style="color: #000080;">@@</span> Blue, blackberry<span style="color: #000080;">:</span> Berry <span style="color: #000080;">@@</span> Black<span style="color: #F78811;">&#41;</span>
    &nbsp;
    lazy <span style="color: #0000ff; font-weight: bold;">val</span> blueberry <span style="color: #000080;">=</span> wire<span style="color: #F78811;">&#91;</span>Berry<span style="color: #F78811;">&#93;</span>.<span style="color: #000000;">taggedWith</span><span style="color: #F78811;">&#91;</span>Blue<span style="color: #F78811;">&#93;</span>
    lazy <span style="color: #0000ff; font-weight: bold;">val</span> blackberry <span style="color: #000080;">=</span> wire<span style="color: #F78811;">&#91;</span>Berry<span style="color: #F78811;">&#93;</span>.<span style="color: #000000;">taggedWith</span><span style="color: #F78811;">&#91;</span>Black<span style="color: #F78811;">&#93;</span>
    lazy <span style="color: #0000ff; font-weight: bold;">val</span> basket <span style="color: #000080;">=</span> wire<span style="color: #F78811;">&#91;</span>Basket<span style="color: #F78811;">&#93;</span></pre></td></tr></table><p class="theCode" style="display:none;">class Berry()
    trait Black
    trait Blue
    
    case class Basket(blueberry: Berry @@ Blue, blackberry: Berry @@ Black)
    
    lazy val blueberry = wire[Berry].taggedWith[Blue]
    lazy val blackberry = wire[Berry].taggedWith[Black]
    lazy val basket = wire[Basket]</p></div>
    ";i:2;s:990:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">class</span> TaxCalculator<span style="color: #F78811;">&#40;</span>taxBase<span style="color: #000080;">:</span> TaxBase, …<span style="color: #F78811;">&#41;</span>
    &nbsp;
    lazy <span style="color: #0000ff; font-weight: bold;">val</span> taxCalculator <span style="color: #000080;">=</span> <span style="color: #F78811;">&#40;</span>taxBase<span style="color: #000080;">:</span> TaxBase<span style="color: #F78811;">&#41;</span> <span style="color: #000080;">=&gt;</span> wire<span style="color: #F78811;">&#91;</span>TaxCalculator<span style="color: #F78811;">&#93;</span></pre></td></tr></table><p class="theCode" style="display:none;">class TaxCalculator(taxBase: TaxBase, …)
    
    lazy val taxCalculator = (taxBase: TaxBase) =&gt; wire[TaxCalculator]</p></div>
    ";}
tags:
  - dependency injection
  - macwire
  - scala

---
A couple of days ago [MacWire 0.8.0][1] got released. It contains a couple of changes and new features. But first, I’d like to thank [Marcin Kubala][2] for his contributions.

All of the changes are reflected in the [guide to DI in Scala][3].

## Tags/qualifiers

If you have multiple objects of the same type which you want to use as dependencies, you need to somehow differentiate between them if you want `wire` to work (otherwise you’ll get a compile-time error). This can be now done with tags, inspired by the work of [Miles Sabin][4]:
```scala
class Berry()
trait Black
trait Blue

case class Basket(blueberry: Berry @@ Blue, blackberry: Berry @@ Black)

lazy val blueberry = wire[Berry].taggedWith[Blue]
lazy val blackberry = wire[Berry].taggedWith[Black]
lazy val basket = wire[Basket]
```

`X @@ T` is type `X` tagged with tag `T`. The `x.taggedWith[T]` method is available on any type. Tags don’t have any runtime overhead, they are a purely compile-time construct.

As a bonus, your code will be more type-safe, as you won’t be able to use incorrectly tagged instances where a tag is required: tags follow the expected subtyping rules, so you can upcast a tagged instance to an untagged one, but you can’t cast an instance with one tag to an instance with another tag.

## Wiring with anonymous function parameters

This feature is especially useful when creating factories. Before, wiring only took into account values from the enclosing method parameters, enclosing trait/class/object and parents. Now, parameters of any enclosing anonymous functions are taken into account as well, allowing to write for example the following:
```scala
class TaxCalculator(taxBase: TaxBase, …)

lazy val taxCalculator = (taxBase: TaxBase) => wire[TaxCalculator]
```

## `wireImplicit`

Finally, there’s a new variant of `wire`: `wireImplicit`. When looking up values for a given constructor parameter, in addition to the usual lookup steps, an implicit lookup on the type is done. If this yields multiple values, a compile-time error is reported.

Note that if a parameter list is marked as implicit, an implicit lookup is done both for `wire` and `wireImplicit`. The difference with `wireImplicit` is that it makes all parameters behave as if they were marked as implicit.

## Changes

MacWire no longer does by-name parameter matching for enclosing method parameters. This was a special-case which we think was rarely used (if at all), and only caused confusion.

## Summing up

The 0.8.0 release will be the basis for a future 1.0 release. Exactly when 1.0 is released depends on bugs reported (or lack thereof) and user feedback on the new features. So if you are using MacWire, please try updating and let us know if there are any problems!

 [1]: https://github.com/adamw/macwire
 [2]: https://twitter.com/marcin_kubala
 [3]: http://di-in-scala.github.io
 [4]: https://gist.github.com/milessabin/89c9b47a91017973a35f
