---
title: 'Supler 0.1.0: complex forms made easier'
author: Adam Warski
type: post
date: 2014-12-16T13:05:35+00:00
url: /blog/2014/12/supler-0-1-0-complex-forms-made-easier/
simplecatch-sidebarlayout:
  - default
dsq_thread_id:
  - 3329627995
wp-syntax-cache-content:
  - |
    a:3:{i:1;s:2425:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    6
    7
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">case</span> <span style="color: #0000ff; font-weight: bold;">class</span> Person<span style="color: #F78811;">&#40;</span>name<span style="color: #000080;">:</span> String<span style="color: #F78811;">&#41;</span>
    &nbsp;
    <span style="color: #0000ff; font-weight: bold;">val</span> personForm <span style="color: #000080;">=</span> form<span style="color: #F78811;">&#91;</span>Person<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#40;</span>f <span style="color: #000080;">=&gt;</span> List<span style="color: #F78811;">&#40;</span>
      f.<span style="color: #000000;">field</span><span style="color: #F78811;">&#40;</span><span style="color: #000080;">_</span>.<span style="color: #000000;">name</span><span style="color: #F78811;">&#41;</span>.<span style="color: #000000;">label</span><span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;Name&quot;</span><span style="color: #F78811;">&#41;</span>,
      f.<span style="color: #000000;">action</span><span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;duplicateName&quot;</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#40;</span>p <span style="color: #000080;">=&gt;</span> ActionResult<span style="color: #F78811;">&#40;</span>p.<span style="color: #000000;">copy</span><span style="color: #F78811;">&#40;</span>name <span style="color: #000080;">=</span> s<span style="color: #6666FF;">&quot;${p.name} ${p.name}&quot;</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span>
        .<span style="color: #000000;">label</span><span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;Duplicate name&quot;</span><span style="color: #F78811;">&#41;</span>
    <span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span></pre></td></tr></table><p class="theCode" style="display:none;">case class Person(name: String)
    
    val personForm = form[Person](f =&gt; List(
      f.field(_.name).label(&quot;Name&quot;),
      f.action(&quot;duplicateName&quot;)(p =&gt; ActionResult(p.copy(name = s&quot;${p.name} ${p.name}&quot;))
        .label(&quot;Duplicate name&quot;)
    ))</p></div>
    ";i:2;s:5833:"
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
    14
    15
    16
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">case</span> <span style="color: #0000ff; font-weight: bold;">class</span> Address<span style="color: #F78811;">&#40;</span>street<span style="color: #000080;">:</span> String<span style="color: #F78811;">&#41;</span>
    <span style="color: #0000ff; font-weight: bold;">case</span> <span style="color: #0000ff; font-weight: bold;">class</span> Person<span style="color: #F78811;">&#40;</span>name<span style="color: #000080;">:</span> String, addresses<span style="color: #000080;">:</span> List<span style="color: #F78811;">&#91;</span>Address<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#41;</span> <span style="color: #F78811;">&#123;</span>
      <span style="color: #0000ff; font-weight: bold;">def</span> removeAddress<span style="color: #F78811;">&#40;</span>a<span style="color: #000080;">:</span> Address<span style="color: #F78811;">&#41;</span> <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">this</span>.<span style="color: #000000;">copy</span><span style="color: #F78811;">&#40;</span>addresses <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">this</span>.<span style="color: #000000;">addresses</span> diff List<span style="color: #F78811;">&#40;</span>a<span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span>
    <span style="color: #F78811;">&#125;</span>
    &nbsp;
    <span style="color: #0000ff; font-weight: bold;">def</span> addressForm<span style="color: #F78811;">&#40;</span>removeAction<span style="color: #000080;">:</span> Address <span style="color: #000080;">=&gt;</span> ActionResult<span style="color: #F78811;">&#91;</span>Address<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#41;</span> <span style="color: #000080;">=</span> form<span style="color: #F78811;">&#91;</span>Address<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#40;</span>f <span style="color: #000080;">=&gt;</span> List<span style="color: #F78811;">&#40;</span>
      f.<span style="color: #000000;">field</span><span style="color: #F78811;">&#40;</span><span style="color: #000080;">_</span>.<span style="color: #000000;">street</span><span style="color: #F78811;">&#41;</span>.<span style="color: #000000;">label</span><span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;Street&quot;</span><span style="color: #F78811;">&#41;</span>,
      f.<span style="color: #000000;">action</span><span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;remove&quot;</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#40;</span>removeAction<span style="color: #F78811;">&#41;</span>.<span style="color: #000000;">label</span><span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;Remove&quot;</span><span style="color: #F78811;">&#41;</span>
    <span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span>
    &nbsp;
    <span style="color: #0000ff; font-weight: bold;">val</span> personForm <span style="color: #000080;">=</span> form<span style="color: #F78811;">&#91;</span>Person<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#40;</span>f <span style="color: #000080;">=&gt;</span> List<span style="color: #F78811;">&#40;</span>
      f.<span style="color: #000000;">field</span><span style="color: #F78811;">&#40;</span><span style="color: #000080;">_</span>.<span style="color: #000000;">name</span><span style="color: #F78811;">&#41;</span>.<span style="color: #000000;">label</span><span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;Name&quot;</span><span style="color: #F78811;">&#41;</span>,
      f.<span style="color: #000000;">subform</span><span style="color: #F78811;">&#40;</span><span style="color: #000080;">_</span>.<span style="color: #000000;">addresses</span>, addressForm<span style="color: #F78811;">&#40;</span>
        f.<span style="color: #000000;">parentAction</span><span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#40;</span>person, index, address<span style="color: #F78811;">&#41;</span> <span style="color: #000080;">=&gt;</span> ActionResult<span style="color: #F78811;">&#40;</span>person.<span style="color: #000000;">removeAddress</span><span style="color: #F78811;">&#40;</span>address<span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span>
        .<span style="color: #000000;">label</span><span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;Addresses&quot;</span><span style="color: #F78811;">&#41;</span>
    <span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span></pre></td></tr></table><p class="theCode" style="display:none;">case class Address(street: String)
    case class Person(name: String, addresses: List[Address]) {
      def removeAddress(a: Address) = this.copy(addresses = this.addresses diff List(a))
    }
    
    def addressForm(removeAction: Address =&gt; ActionResult[Address]) = form[Address](f =&gt; List(
      f.field(_.street).label(&quot;Street&quot;),
      f.action(&quot;remove&quot;)(removeAction).label(&quot;Remove&quot;)
    ))
    
    val personForm = form[Person](f =&gt; List(
      f.field(_.name).label(&quot;Name&quot;),
      f.subform(_.addresses, addressForm(
        f.parentAction((person, index, address) =&gt; ActionResult(person.removeAddress(address)))))
        .label(&quot;Addresses&quot;)
    ))</p></div>
    ";i:3;s:1015:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    6
    7
    8
    9
    </pre></td><td class="code"><pre class="html" style="font-family:monospace;">&lt;div id=&quot;form-container&quot;&gt;
      &lt;div supler:fieldTemplate supler:fieldPath=&quot;lastName&quot;&gt;
        &lt;div class=&quot;formGroup&quot;&gt;
          &lt;i&gt;{{suplerLabel}} &lt;span style=&quot;font-size: xx-small&quot;&gt;(extra information)&lt;/span&gt;&lt;/i&gt;
          {{suplerInput}}
          {{suplerValidation}}
        &lt;/div&gt;
      &lt;/div&gt;
    &lt;/div&gt;</pre></td></tr></table><p class="theCode" style="display:none;">&lt;div id=&quot;form-container&quot;&gt;
      &lt;div supler:fieldTemplate supler:fieldPath=&quot;lastName&quot;&gt;
        &lt;div class=&quot;formGroup&quot;&gt;
          &lt;i&gt;{{suplerLabel}} &lt;span style=&quot;font-size: xx-small&quot;&gt;(extra information)&lt;/span&gt;&lt;/i&gt;
          {{suplerInput}}
          {{suplerValidation}}
        &lt;/div&gt;
      &lt;/div&gt;
    &lt;/div&gt;</p></div>
    ";}
categories:
  - Javascript
  - Languages
  - Library
  - Scala
  - Supler
  - Uncategorized

---
[Supler][1] aims to make complex web forms development easier, without tying you to a web framework. Supler provides a server-side DSL for defining forms, generating a JSON form representation, applying values received from the client, validating and running actions. It also provides a front-end renderer for the forms, client-side validation, serialization to json and background refresh support.

For a general introduction, see the [previous blog][2], or just head over to [GitHub][1]. Please **star the project** if you find it interesting and would like to see it developed further!

We also have a [live demo][3] if you’d like to see things in action, with the (quite simple) code [available here][4].

Supler 0.1.0 is available now in [Sonatype’s OSS repository][5] (backend) and in [Bower][6] (frontend).

## The Supler Flow &#8230;

&#8230; is best illustrated by a diagram:

<a href="http://www.warski.org/blog/2014/12/supler-0-1-0-complex-forms-made-easier/supler-diagram/" rel="attachment wp-att-1492"><img loading="lazy" decoding="async" src="http://www.warski.org/blog/wp-content/uploads/2014/12/supler-diagram-1024x952.png" alt="supler diagram" width="978" height="909" class="aligncenter size-large wp-image-1492" srcset="https://www.warski.org/blog/wp-content/uploads/2014/12/supler-diagram-1024x952.png 1024w, https://www.warski.org/blog/wp-content/uploads/2014/12/supler-diagram-255x237.png 255w, https://www.warski.org/blog/wp-content/uploads/2014/12/supler-diagram-300x278.png 300w, https://www.warski.org/blog/wp-content/uploads/2014/12/supler-diagram-210x195.png 210w" sizes="(max-width: 978px) 100vw, 978px" /></a>

Note that all the details on where the form-backing objects (entities) come from, how they are persisted, how the HTTP endpoints are exposed, what JS framework (if any) you use to handle AJAX requests, are left to you: you are free to do it in any way you like. Supler is only concerned with form handling, nothing else.

What’s new since the initial announcement? Three major features:

## Actions

First of all, forms can now contain buttons tied to server-side actions. These actions can modify the backing object, e.g.:

<pre lang="scala" line="1">case class Person(name: String)

val personForm = form[Person](f => List(
  f.field(_.name).label("Name"),
  f.action("duplicateName")(p => ActionResult(p.copy(name = s"${p.name} ${p.name}"))
    .label("Duplicate name")
))
</pre>

Quite commonly, we need to modify the parent object, e.g. to remove or change the order of sub-objects (sub-forms). This is possible by parametrising the form with the action and using `parentAction`s:

<pre lang="scala" line="1">case class Address(street: String)
case class Person(name: String, addresses: List[Address]) {
  def removeAddress(a: Address) = this.copy(addresses = this.addresses diff List(a))
}

def addressForm(removeAction: Address => ActionResult[Address]) = form[Address](f => List(
  f.field(_.street).label("Street"),
  f.action("remove")(removeAction).label("Remove")
))

val personForm = form[Person](f => List(
  f.field(_.name).label("Name"),
  f.subform(_.addresses, addressForm(
    f.parentAction((person, index, address) => ActionResult(person.removeAddress(address)))))
    .label("Addresses")
))
</pre>

Note that the parametrisation is pure Scala: we are defining a method, which returns an immutable form description.

On the frontend, we need to have some way to reload the form when an action is invoked. This is done by providing Supler with a Javascript function that should be invoked on an action, and which receives two parameters: the serialized form and a successful response callback. On the backend, a convenient `FormWithObject.reload(JValue): JValue` method is provided, which applies the values, runs validation, actions and generates a new JSON.

Further development will include running the actions only when certain form area is valid, or handling action responses which are a custom json (so that you can perform custom naviagation, for example).

## Background refresh

Another important feature for dynamic forms is being able to change the form content in response to user input (e.g. change a detail-dropdown when the master-dropdown selection is changed). This is implemented using the same callback as for the actions. Whenever a field is changed, the form is reloaded and re-rendered.

Further development will include making the mechanism configurable by specifying changes to which fields should trigger a background reload.

## HTML templates

The last addition is being able to customise the HTML rendering process by providing HTML templates. By default [Bootstrap3][7]-compatible markup is generated, but that may not be of course what you want.

You may customise rendering at various levels of granularity: starting from labels, through validation, individual inputs, to overall shape of a form entry. The customisations may affect an individual field, all fields of a given type, or all fields. For example:

<pre lang="html" line="1"><div id="form-container">
  <div supler:fieldTemplate supler:fieldPath="lastName">
    <div class="formGroup">
      <i>{{suplerLabel}} <span style="font-size: xx-small">(extra information)</span></i>
            {{suplerInput}}
            {{suplerValidation}}
          
    </div>
      
  </div>
  
</div>
</pre>

The full details are [here][8].

## Summing up

Please remember that Supler is still in early stages of development. If you would have any design suggestions or ideas, now is the time! Also, if you like the project, please let us know by **starring it on [GitHub][1]**!

 [1]: https://github.com/softwaremill/supler
 [2]: http://www.warski.org/blog/2014/09/introducing-supler-a-functional-reactive-form-library/
 [3]: http://supler.softwaremill.com/
 [4]: https://github.com/softwaremill/supler/tree/master/examples/src/main
 [5]: https://oss.sonatype.org/content/repositories/releases/com/softwaremill/supler_2.11/
 [6]: http://bower.io/search/?q=supler
 [7]: http://getbootstrap.com
 [8]: https://github.com/softwaremill/supler/blob/master/CUSTOMIZE_RENDER.md
