---
title: 'Supler update: 0.3.0 release'
author: Adam Warski
type: post
date: 2015-03-19T12:21:50+00:00
url: /blog/2015/03/supler-update-0-3-0-release/
simplecatch-sidebarlayout:
  - default
dsq_thread_id:
  - 3608686278
wp-syntax-cache-content:
  - |
    a:2:{i:1;s:3002:"
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
    </pre></td><td class="code"><pre class="javascript" style="font-family:monospace;"><span style="color: #000066; font-weight: bold;">new</span> Supler.<span style="color: #660066;">Form</span><span style="color: #009900;">&#40;</span>container<span style="color: #339933;">,</span> <span style="color: #009900;">&#123;</span>
    field_options<span style="color: #339933;">:</span> <span style="color: #009900;">&#123;</span>
      <span style="color: #3366CC;">'secretField'</span><span style="color: #339933;">:</span> <span style="color: #009900;">&#123;</span>
        render_hint<span style="color: #339933;">:</span> <span style="color: #3366CC;">'password'</span> 
      <span style="color: #009900;">&#125;</span><span style="color: #339933;">,</span>
      <span style="color: #3366CC;">'friends[].bio'</span><span style="color: #339933;">:</span> <span style="color: #009900;">&#123;</span>
        render_options<span style="color: #339933;">:</span> <span style="color: #009900;">&#123;</span>
          renderLabel<span style="color: #339933;">:</span> <span style="color: #000066; font-weight: bold;">function</span><span style="color: #009900;">&#40;</span>forId<span style="color: #339933;">,</span> label<span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span> <span style="color: #000066; font-weight: bold;">return</span> <span style="color: #3366CC;">'&lt;div&gt;some html&lt;/div&gt;'</span><span style="color: #339933;">;</span> <span style="color: #009900;">&#125;</span> 
        <span style="color: #009900;">&#125;</span>
        read_value<span style="color: #339933;">:</span> <span style="color: #000066; font-weight: bold;">function</span><span style="color: #009900;">&#40;</span>element<span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span> <span style="color: #000066; font-weight: bold;">return</span> ... <span style="color: #009900;">&#125;</span> 
      <span style="color: #009900;">&#125;</span><span style="color: #339933;">,</span>
      <span style="color: #3366CC;">'render_hint:radio'</span><span style="color: #339933;">:</span> <span style="color: #009900;">&#123;</span>
        <span style="color: #006600; font-style: italic;">// options common for all fields rendered as radios</span>
      <span style="color: #009900;">&#125;</span>
    <span style="color: #009900;">&#125;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span></pre></td></tr></table><p class="theCode" style="display:none;">new Supler.Form(container, {
    field_options: {
      'secretField': {
        render_hint: 'password' 
      },
      'friends[].bio': {
        render_options: {
          renderLabel: function(forId, label) { return '&lt;div&gt;some html&lt;/div&gt;'; } 
        }
        read_value: function(element) { return ... } 
      },
      'render_hint:radio': {
        // options common for all fields rendered as radios
      }
    });</p></div>
    ";i:2;s:2500:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    6
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;">form<span style="color: #F78811;">&#91;</span>Person<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#40;</span>f <span style="color: #000080;">=&gt;</span> List<span style="color: #F78811;">&#40;</span>
      <span style="color: #008000; font-style: italic;">// row 1</span>
      f.<span style="color: #000000;">field</span><span style="color: #F78811;">&#40;</span><span style="color: #000080;">_</span>.<span style="color: #000000;">age</span><span style="color: #F78811;">&#41;</span>.<span style="color: #000000;">label</span><span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;Age&quot;</span><span style="color: #F78811;">&#41;</span> || f.<span style="color: #000000;">field</span><span style="color: #F78811;">&#40;</span><span style="color: #000080;">_</span>.<span style="color: #000000;">birthday</span><span style="color: #F78811;">&#41;</span>.<span style="color: #000000;">label</span><span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;Birthday&quot;</span><span style="color: #F78811;">&#41;</span>,
      <span style="color: #008000; font-style: italic;">// row 2</span>
      f.<span style="color: #000000;">field</span><span style="color: #F78811;">&#40;</span><span style="color: #000080;">_</span>.<span style="color: #000000;">address1</span><span style="color: #F78811;">&#41;</span>.<span style="color: #000000;">label</span><span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;Address 1&quot;</span><span style="color: #F78811;">&#41;</span> || f.<span style="color: #000000;">field</span><span style="color: #F78811;">&#40;</span><span style="color: #000080;">_</span>.<span style="color: #000000;">address2</span><span style="color: #F78811;">&#41;</span>.<span style="color: #000000;">label</span><span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;Address 2&quot;</span><span style="color: #F78811;">&#41;</span>
    <span style="color: #F78811;">&#41;</span></pre></td></tr></table><p class="theCode" style="display:none;">form[Person](f =&gt; List(
      // row 1
      f.field(_.age).label(&quot;Age&quot;) || f.field(_.birthday).label(&quot;Birthday&quot;),
      // row 2
      f.field(_.address1).label(&quot;Address 1&quot;) || f.field(_.address2).label(&quot;Address 2&quot;)
    )</p></div>
    ";}
categories:
  - scala
  - web

---
It’s been some time since the last release of [Supler][1]. Just as a short reminder: Supler is a Rapid Form Development library, allowing you to use your favorite Javascript frontend, and Scala backend frameworks. It keeps the form definition centralized, taking care of client/server-side validation, form rendering, applying new values and serializing/deserializing. [Star Supler on GitHub][1] if you think that might be interesting!

If you’re new to Supler, or if you’d like to get started with form development using Supler, take a look at our [docs][2] or the [live demo][3].

The 0.3.0 release brings a lot of smaller and bigger improvements as well as bug fixes (a mandatory position in each release of any software ;) ). It is now possible to customise a number of field options on the frontend, starting with specifying the field order, to more specific per-field options like render hints or overriding rendering functions using javascript (it is still possible of course to override rendering using HTML templates). A short example of how field options may look like:

<pre lang="javascript" line="1">new Supler.Form(container, {
field_options: {
  'secretField': {
    render_hint: 'password' 
  },
  'friends[].bio': {
    render_options: {
      renderLabel: function(forId, label) { return '

<div>
  some html
</div>'; } 
    }
    read_value: function(element) { return ... } 
  },
  'render_hint:radio': {
    // options common for all fields rendered as radios
  }
});
</pre>

Before the only way to layout fields was one field per row. Now it is also possible to have multiple fields in one row; just combine the fields using the `||` operator:

<pre lang="scala" line="1">form[Person](f => List(
  // row 1
  f.field(_.age).label("Age") || f.field(_.birthday).label("Birthday"),
  // row 2
  f.field(_.address1).label("Address 1") || f.field(_.address2).label("Address 2")
)
</pre>

The row decorations and how rows are rendered is of course fully customisable via overriding the javascript rendering functions. We are also looking into making this more flexible, with frontend-defined form layout templates.

Other changes include:

  * more validators for basic types
  * improved option validators
  * improved single/multi-select fields support
  * easier definitions of custom transformers (for serializing/deserializing custom classes to/from json).
  * support for fields which serialised to complex JSON objects (not plain strings/numbers etc.)

We also have a new [Bintray][4]/[Artifactory][5]/[Travis][6]-based build system, which fully automates the releases so snapshots are always up-to-date!

As always, if you like the project, please [start it on Github][1] or let us know your opinion either here, [on the forum][7] or on the [Gitter chat][8]!

 [1]: https://github.com/softwaremill/supler
 [2]: http://docs.supler.io/en/latest/first.html#first
 [3]: http://demo.supler.io
 [4]: https://bintray.com
 [5]: http://www.jfrog.com/open-source/
 [6]: https://travis-ci.org/softwaremill/supler
 [7]: https://groups.google.com/forum/#!forum/supler
 [8]: https://gitter.im/softwaremill/supler
