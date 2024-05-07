---
title: 'Introducing Supler: a Functional Reactive Form Library'
author: Adam Warski
type: blog
date: 2014-09-17T18:58:28+00:00
url: /blog/2014/09/introducing-supler-a-functional-reactive-form-library/
simplecatch-sidebarlayout:
  - default
dsq_thread_id:
  - 3027521268
wp-syntax-cache-content:
  - |
    a:6:{i:1;s:2290:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    6
    7
    8
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">case</span> <span style="color: #0000ff; font-weight: bold;">class</span> Person<span style="color: #F78811;">&#40;</span>
      firstName<span style="color: #000080;">:</span> String, lastName<span style="color: #000080;">:</span> String, age<span style="color: #000080;">:</span> Int,
      address1<span style="color: #000080;">:</span> Option<span style="color: #F78811;">&#91;</span>String<span style="color: #F78811;">&#93;</span>, address2<span style="color: #000080;">:</span> Option<span style="color: #F78811;">&#91;</span>String<span style="color: #F78811;">&#93;</span>,
      gender<span style="color: #000080;">:</span> String, cars<span style="color: #000080;">:</span> List<span style="color: #F78811;">&#91;</span>Car<span style="color: #F78811;">&#93;</span>, legoSets<span style="color: #000080;">:</span> List<span style="color: #F78811;">&#91;</span>LegoSet<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#41;</span>
    &nbsp;
    <span style="color: #0000ff; font-weight: bold;">case</span> <span style="color: #0000ff; font-weight: bold;">class</span> Car<span style="color: #F78811;">&#40;</span>make<span style="color: #000080;">:</span> String, year<span style="color: #000080;">:</span> Int<span style="color: #F78811;">&#41;</span>
    &nbsp;
    <span style="color: #0000ff; font-weight: bold;">case</span> <span style="color: #0000ff; font-weight: bold;">class</span> LegoSet<span style="color: #F78811;">&#40;</span>name<span style="color: #000080;">:</span> String, theme<span style="color: #000080;">:</span> String, number<span style="color: #000080;">:</span> Int, age<span style="color: #000080;">:</span> Int<span style="color: #F78811;">&#41;</span></pre></td></tr></table><p class="theCode" style="display:none;">case class Person(
      firstName: String, lastName: String, age: Int,
      address1: Option[String], address2: Option[String],
      gender: String, cars: List[Car], legoSets: List[LegoSet])
    
    case class Car(make: String, year: Int)
    
    case class LegoSet(name: String, theme: String, number: Int, age: Int)</p></div>
    ";i:2;s:6403:"
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
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">val</span> carForm <span style="color: #000080;">=</span> form<span style="color: #F78811;">&#91;</span>Car<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#40;</span>f <span style="color: #000080;">=&gt;</span> List<span style="color: #F78811;">&#40;</span>
      f.<span style="color: #000000;">field</span><span style="color: #F78811;">&#40;</span><span style="color: #000080;">_</span>.<span style="color: #000000;">make</span><span style="color: #F78811;">&#41;</span>.<span style="color: #000000;">use</span><span style="color: #F78811;">&#40;</span>dataProvider<span style="color: #F78811;">&#40;</span><span style="color: #000080;">_</span> <span style="color: #000080;">=&gt;</span> List<span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;Ford&quot;</span>, <span style="color: #6666FF;">&quot;Toyota&quot;</span>, <span style="color: #6666FF;">&quot;Mondeo&quot;</span>, <span style="color: #6666FF;">&quot;Transit&quot;</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span>.<span style="color: #000000;">label</span><span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;Make&quot;</span><span style="color: #F78811;">&#41;</span>,
      f.<span style="color: #000000;">field</span><span style="color: #F78811;">&#40;</span><span style="color: #000080;">_</span>.<span style="color: #000000;">year</span><span style="color: #F78811;">&#41;</span>.<span style="color: #000000;">validate</span><span style="color: #F78811;">&#40;</span>gt<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">1900</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span>.<span style="color: #000000;">label</span><span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;Year&quot;</span><span style="color: #F78811;">&#41;</span>
    <span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span>
    &nbsp;
    <span style="color: #0000ff; font-weight: bold;">val</span> legoSetForm <span style="color: #000080;">=</span> form<span style="color: #F78811;">&#91;</span>LegoSet<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#40;</span>f <span style="color: #000080;">=&gt;</span> List<span style="color: #F78811;">&#40;</span>
      f.<span style="color: #000000;">field</span><span style="color: #F78811;">&#40;</span><span style="color: #000080;">_</span>.<span style="color: #000000;">name</span><span style="color: #F78811;">&#41;</span>.<span style="color: #000000;">label</span><span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;Name&quot;</span><span style="color: #F78811;">&#41;</span>,
      f.<span style="color: #000000;">field</span><span style="color: #F78811;">&#40;</span><span style="color: #000080;">_</span>.<span style="color: #000000;">theme</span><span style="color: #F78811;">&#41;</span>.<span style="color: #000000;">label</span><span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;Theme&quot;</span><span style="color: #F78811;">&#41;</span>.<span style="color: #000000;">use</span><span style="color: #F78811;">&#40;</span>dataProvider<span style="color: #F78811;">&#40;</span><span style="color: #000080;">_</span> <span style="color: #000080;">=&gt;</span> List<span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;City&quot;</span>, <span style="color: #6666FF;">&quot;Technic&quot;</span>, <span style="color: #6666FF;">&quot;Duplo&quot;</span>, <span style="color: #6666FF;">&quot;Space&quot;</span>, <span style="color: #6666FF;">&quot;Friends&quot;</span>, <span style="color: #6666FF;">&quot;Universal&quot;</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span>,
      f.<span style="color: #000000;">field</span><span style="color: #F78811;">&#40;</span><span style="color: #000080;">_</span>.<span style="color: #000000;">number</span><span style="color: #F78811;">&#41;</span>.<span style="color: #000000;">label</span><span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;Set number&quot;</span><span style="color: #F78811;">&#41;</span>.<span style="color: #000000;">validate</span><span style="color: #F78811;">&#40;</span>lt<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">100000</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span>,
      f.<span style="color: #000000;">field</span><span style="color: #F78811;">&#40;</span><span style="color: #000080;">_</span>.<span style="color: #000000;">age</span><span style="color: #F78811;">&#41;</span>.<span style="color: #000000;">label</span><span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;Age&quot;</span><span style="color: #F78811;">&#41;</span>.<span style="color: #000000;">validate</span><span style="color: #F78811;">&#40;</span>ge<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">0</span><span style="color: #F78811;">&#41;</span>, le<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">50</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span>
    <span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span></pre></td></tr></table><p class="theCode" style="display:none;">val carForm = form[Car](f =&gt; List(
      f.field(_.make).use(dataProvider(_ =&gt; List(&quot;Ford&quot;, &quot;Toyota&quot;, &quot;Mondeo&quot;, &quot;Transit&quot;))).label(&quot;Make&quot;),
      f.field(_.year).validate(gt(1900)).label(&quot;Year&quot;)
    ))
    
    val legoSetForm = form[LegoSet](f =&gt; List(
      f.field(_.name).label(&quot;Name&quot;),
      f.field(_.theme).label(&quot;Theme&quot;).use(dataProvider(_ =&gt; List(&quot;City&quot;, &quot;Technic&quot;, &quot;Duplo&quot;, &quot;Space&quot;, &quot;Friends&quot;, &quot;Universal&quot;))),
      f.field(_.number).label(&quot;Set number&quot;).validate(lt(100000)),
      f.field(_.age).label(&quot;Age&quot;).validate(ge(0), le(50))
    ))</p></div>
    ";i:3;s:6721:"
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
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">val</span> personForm <span style="color: #000080;">=</span> form<span style="color: #F78811;">&#91;</span>Person<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#40;</span>f <span style="color: #000080;">=&gt;</span> List<span style="color: #F78811;">&#40;</span>
      f.<span style="color: #000000;">field</span><span style="color: #F78811;">&#40;</span><span style="color: #000080;">_</span>.<span style="color: #000000;">firstName</span><span style="color: #F78811;">&#41;</span>.<span style="color: #000000;">label</span><span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;First name&quot;</span><span style="color: #F78811;">&#41;</span>,
      f.<span style="color: #000000;">field</span><span style="color: #F78811;">&#40;</span><span style="color: #000080;">_</span>.<span style="color: #000000;">lastName</span><span style="color: #F78811;">&#41;</span>.<span style="color: #000000;">label</span><span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;Last name&quot;</span><span style="color: #F78811;">&#41;</span>
        .<span style="color: #000000;">validate</span><span style="color: #F78811;">&#40;</span>custom<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#40;</span>e, v<span style="color: #F78811;">&#41;</span> <span style="color: #000080;">=&gt;</span> v.<span style="color: #000000;">length</span> <span style="color: #000080;">&lt;=</span> e.<span style="color: #000000;">firstName</span>.<span style="color: #000000;">length</span>, <span style="color: #F78811;">&#40;</span>e, v<span style="color: #F78811;">&#41;</span> <span style="color: #000080;">=&gt;</span> ValidationError<span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;Last name must be longer than first name!&quot;</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span>,
      f.<span style="color: #000000;">field</span><span style="color: #F78811;">&#40;</span><span style="color: #000080;">_</span>.<span style="color: #000000;">age</span><span style="color: #F78811;">&#41;</span>.<span style="color: #000000;">label</span><span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;Age&quot;</span><span style="color: #F78811;">&#41;</span>,
      f.<span style="color: #000000;">field</span><span style="color: #F78811;">&#40;</span><span style="color: #000080;">_</span>.<span style="color: #000000;">address1</span><span style="color: #F78811;">&#41;</span>.<span style="color: #000000;">label</span><span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;Address 1&quot;</span><span style="color: #F78811;">&#41;</span>,
      f.<span style="color: #000000;">field</span><span style="color: #F78811;">&#40;</span><span style="color: #000080;">_</span>.<span style="color: #000000;">address2</span><span style="color: #F78811;">&#41;</span>.<span style="color: #000000;">label</span><span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;Address 2&quot;</span><span style="color: #F78811;">&#41;</span>,
      f.<span style="color: #000000;">field</span><span style="color: #F78811;">&#40;</span><span style="color: #000080;">_</span>.<span style="color: #000000;">gender</span><span style="color: #F78811;">&#41;</span>.<span style="color: #000000;">label</span><span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;Gender&quot;</span><span style="color: #F78811;">&#41;</span>.<span style="color: #000000;">use</span><span style="color: #F78811;">&#40;</span>dataProvider<span style="color: #F78811;">&#40;</span><span style="color: #000080;">_</span> <span style="color: #000080;">=&gt;</span> List<span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;Male&quot;</span>, <span style="color: #6666FF;">&quot;Female&quot;</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span>,
      f.<span style="color: #000000;">subform</span><span style="color: #F78811;">&#40;</span><span style="color: #000080;">_</span>.<span style="color: #000000;">cars</span>, carForm, Car<span style="color: #F78811;">&#40;</span><span style="color: #0000ff; font-weight: bold;">null</span>, <span style="color: #F78811;">0</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span>.<span style="color: #000000;">label</span><span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;Cars&quot;</span><span style="color: #F78811;">&#41;</span>.<span style="color: #000000;">renderHint</span><span style="color: #F78811;">&#40;</span>asList<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span>,
      f.<span style="color: #000000;">subform</span><span style="color: #F78811;">&#40;</span><span style="color: #000080;">_</span>.<span style="color: #000000;">legoSets</span>, legoSetForm, LegoSet<span style="color: #F78811;">&#40;</span><span style="color: #0000ff; font-weight: bold;">null</span>, <span style="color: #0000ff; font-weight: bold;">null</span>, <span style="color: #F78811;">0</span>, <span style="color: #F78811;">0</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span>.<span style="color: #000000;">label</span><span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;Lego sets&quot;</span><span style="color: #F78811;">&#41;</span>
    <span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span></pre></td></tr></table><p class="theCode" style="display:none;">val personForm = form[Person](f =&gt; List(
      f.field(_.firstName).label(&quot;First name&quot;),
      f.field(_.lastName).label(&quot;Last name&quot;)
        .validate(custom((e, v) =&gt; v.length &lt;= e.firstName.length, (e, v) =&gt; ValidationError(&quot;Last name must be longer than first name!&quot;))),
      f.field(_.age).label(&quot;Age&quot;),
      f.field(_.address1).label(&quot;Address 1&quot;),
      f.field(_.address2).label(&quot;Address 2&quot;),
      f.field(_.gender).label(&quot;Gender&quot;).use(dataProvider(_ =&gt; List(&quot;Male&quot;, &quot;Female&quot;))),
      f.subform(_.cars, carForm, Car(null, 0)).label(&quot;Cars&quot;).renderHint(asList()),
      f.subform(_.legoSets, legoSetForm, LegoSet(null, null, 0, 0)).label(&quot;Lego sets&quot;)
    ))</p></div>
    ";i:4;s:775:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    </pre></td><td class="code"><pre class="html" style="font-family:monospace;">&lt;div&gt;
      &lt;a href=&quot;#&quot; class=&quot;btn btn-primary btn-lg&quot; id=&quot;submit&quot; role=&quot;button&quot;&gt;Submit&lt;/a&gt;
      &lt;p id=&quot;feedback&quot;&gt;&lt;/p&gt;
      &lt;div id=&quot;form-container&quot;&gt;&lt;/div&gt;
    &lt;/div&gt;</pre></td></tr></table><p class="theCode" style="display:none;">&lt;div&gt;
      &lt;a href=&quot;#&quot; class=&quot;btn btn-primary btn-lg&quot; id=&quot;submit&quot; role=&quot;button&quot;&gt;Submit&lt;/a&gt;
      &lt;p id=&quot;feedback&quot;&gt;&lt;/p&gt;
      &lt;div id=&quot;form-container&quot;&gt;&lt;/div&gt;
    &lt;/div&gt;</p></div>
    ";i:5;s:1393:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    </pre></td><td class="code"><pre class="javascript" style="font-family:monospace;"><span style="color: #000066; font-weight: bold;">var</span> formContainer <span style="color: #339933;">=</span> document.<span style="color: #660066;">getElementById</span><span style="color: #009900;">&#40;</span><span style="color: #3366CC;">'form-container'</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
    <span style="color: #000066; font-weight: bold;">var</span> form <span style="color: #339933;">=</span>  <span style="color: #339933;">=</span> <span style="color: #000066; font-weight: bold;">new</span> SuplerForm<span style="color: #009900;">&#40;</span>formContainer<span style="color: #339933;">,</span> <span style="color: #009900;">&#123;</span><span style="color: #009900;">&#125;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
    form.<span style="color: #660066;">render</span><span style="color: #009900;">&#40;</span>formJson<span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span></pre></td></tr></table><p class="theCode" style="display:none;">var formContainer = document.getElementById('form-container');
    var form =  = new SuplerForm(formContainer, {});
    form.render(formJson);</p></div>
    ";i:6;s:5606:"
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
    17
    18
    19
    20
    21
    22
    23
    24
    25
    26
    27
    </pre></td><td class="code"><pre class="javascript" style="font-family:monospace;">$<span style="color: #009900;">&#40;</span><span style="color: #3366CC;">'#submit'</span><span style="color: #009900;">&#41;</span>.<span style="color: #660066;">click</span><span style="color: #009900;">&#40;</span><span style="color: #000066; font-weight: bold;">function</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
      <span style="color: #000066; font-weight: bold;">var</span> hasErrors <span style="color: #339933;">=</span> form.<span style="color: #660066;">validate</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
    &nbsp;
      <span style="color: #000066; font-weight: bold;">if</span> <span style="color: #009900;">&#40;</span>hasErrors<span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
        feedback.<span style="color: #660066;">html</span><span style="color: #009900;">&#40;</span><span style="color: #3366CC;">'There are client-side validation errors.'</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
        feedback.<span style="color: #660066;">show</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
      <span style="color: #009900;">&#125;</span> <span style="color: #000066; font-weight: bold;">else</span> <span style="color: #009900;">&#123;</span>
        $.<span style="color: #660066;">ajax</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#123;</span>
          url<span style="color: #339933;">:</span> <span style="color: #3366CC;">'/rest/form1.json'</span><span style="color: #339933;">,</span>
          type<span style="color: #339933;">:</span> <span style="color: #3366CC;">'POST'</span><span style="color: #339933;">,</span>
          data<span style="color: #339933;">:</span> JSON.<span style="color: #660066;">stringify</span><span style="color: #009900;">&#40;</span>form.<span style="color: #660066;">getValue</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">,</span>
          dataType<span style="color: #339933;">:</span> <span style="color: #3366CC;">'json'</span><span style="color: #339933;">,</span>
          contentType<span style="color: #339933;">:</span> <span style="color: #3366CC;">'application/json; charset=utf-8'</span><span style="color: #339933;">,</span>
          success<span style="color: #339933;">:</span> <span style="color: #000066; font-weight: bold;">function</span> <span style="color: #009900;">&#40;</span>data<span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
            <span style="color: #000066; font-weight: bold;">if</span> <span style="color: #009900;">&#40;</span>form.<span style="color: #660066;">processServerValidationErrors</span><span style="color: #009900;">&#40;</span>data.<span style="color: #660066;">validation_errors</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
              feedback.<span style="color: #660066;">html</span><span style="color: #009900;">&#40;</span><span style="color: #3366CC;">'There are server-side validation errors'</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
            <span style="color: #009900;">&#125;</span> <span style="color: #000066; font-weight: bold;">else</span> <span style="color: #009900;">&#123;</span>
              feedback.<span style="color: #660066;">html</span><span style="color: #009900;">&#40;</span>data.<span style="color: #660066;">msg</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
            <span style="color: #009900;">&#125;</span>
    &nbsp;
            feedback.<span style="color: #660066;">show</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
          <span style="color: #009900;">&#125;</span>
        <span style="color: #009900;">&#125;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
      <span style="color: #009900;">&#125;</span>
    &nbsp;
      <span style="color: #000066; font-weight: bold;">return</span> <span style="color: #003366; font-weight: bold;">false</span><span style="color: #339933;">;</span>
    <span style="color: #009900;">&#125;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span></pre></td></tr></table><p class="theCode" style="display:none;">$('#submit').click(function() {
      var hasErrors = form.validate();
    
      if (hasErrors) {
        feedback.html('There are client-side validation errors.');
        feedback.show();
      } else {
        $.ajax({
          url: '/rest/form1.json',
          type: 'POST',
          data: JSON.stringify(form.getValue()),
          dataType: 'json',
          contentType: 'application/json; charset=utf-8',
          success: function (data) {
            if (form.processServerValidationErrors(data.validation_errors)) {
              feedback.html('There are server-side validation errors');
            } else {
              feedback.html(data.msg);
            }
    
            feedback.show();
          }
        });
      }
    
      return false;
    });</p></div>
    ";}
tags:
  - metaprogramming
  - scala
  - web

---
Let&#8217;s face it. Creating websites with complex forms is a pain. Writing the HTML in the frontend, the supporting javascript, defining mappings in the backed, server-side validation, and &#8211; let&#8217;s not forget, it&#8217;s 2014 &#8211; corresponding client-side validation, cause a lot of duplication of code and effort, and result in frustration.

<a href="http://www.warski.org/blog/2014/09/introducing-supler-a-functional-reactive-form-library/screen-shot-2014-09-17-at-11-51-00/" rel="attachment wp-att-1456"><img loading="lazy" decoding="async" src="http://www.warski.org/blog/wp-content/uploads/2014/09/Screen-Shot-2014-09-17-at-11.51.00-300x210.png" alt="Screen Shot 2014-09-17 at 11.51.00" width="300" height="210" class="aligncenter size-medium wp-image-1456" srcset="https://www.warski.org/blog/wp-content/uploads/2014/09/Screen-Shot-2014-09-17-at-11.51.00-300x210.png 300w, https://www.warski.org/blog/wp-content/uploads/2014/09/Screen-Shot-2014-09-17-at-11.51.00-255x178.png 255w, https://www.warski.org/blog/wp-content/uploads/2014/09/Screen-Shot-2014-09-17-at-11.51.00-1024x717.png 1024w, https://www.warski.org/blog/wp-content/uploads/2014/09/Screen-Shot-2014-09-17-at-11.51.00-210x147.png 210w" sizes="(max-width: 300px) 100vw, 300px" /></a>

There&#8217;s quite a lot of frameworks offering end-to-end solutions to the problem, most often generating and rendering whole pages on the server. However, using them usually ends in even more pain. The generated HTML and Javascript is hard to customize and extend with custom bindings. You end up being constrained with what the framework authors envisioned.

That&#8217;s where Supler comes in. It&#8217;s a **library**, with a very focused set of functionality:

  * _backend_: Scala DSL for defining forms based on a backing class (with validation)
  * _backend_: generating a JSON description of the form
  * _frontend_: generating HTML basing on the JSON form description
  * _frontend_: serializing a form to JSON and _backend_: applying the values
  * _frontend_: running client-side validations, for supported validations
  * _backend_: running server-side validations, and _frontend_: showing the results to the client

As important as Supler&#8217;s features, are its non-features. Supler **does not** define or manage:

  * what web framework you use
  * how is the JSON data served
  * how you write your JavaScript
  * the lifecycle of the entities on the backend
  * the lifecycle of the data on the frontend
  * if and what ORM you use
  * how are your entities defined on the backend

Looks interesting? You can find the sources [on GitHub][1]. **Give us a star** if you&#8217;d like to see Supler developed further!

## Demo

But, to the important parts &#8211; how does Supler look in action? You can see a [live demo here][2]. Try to remove some values, enter incorrect values, submit the form, refresh the page. You should sometimes get client-side validation errors, sometimes server-side validation errors, and sometimes, hopefully, success. A more detailed description of how the code works is below.

First, we need to define the form on the backend. Here you can see the model, three simple case classes:
```scala
case class Person(
  firstName: String, lastName: String, age: Int,
  address1: Option[String], address2: Option[String],
  gender: String, cars: List[Car], legoSets: List[LegoSet])

case class Car(make: String, year: Int)

case class LegoSet(name: String, theme: String, number: Int, age: Int)
```

Next, we can use Supler&#8217;s DSL to define forms for editing `Car`s and `LegoSet`s:
```scala
val carForm = form[Car](f => List(
  f.field(_.make).use(dataProvider(_ => List("Ford", "Toyota", "Mondeo", "Transit"))).label("Make"),
  f.field(_.year).validate(gt(1900)).label("Year")
))

val legoSetForm = form[LegoSet](f => List(
  f.field(_.name).label("Name"),
  f.field(_.theme).label("Theme").use(dataProvider(_ => List("City", "Technic", "Duplo", "Space", "Friends", "Universal"))),
  f.field(_.number).label("Set number").validate(lt(100000)),
  f.field(_.age).label("Age").validate(ge(0), le(50))
))
```

As you can see, a form is a list of fields. Each field is defined using a type-safe closure, with an optional label, select options, and validation rules (which will be run both on the frontend and backend).

We can now define a form for `Person`s, re-using the previously defined forms:
```scala
val personForm = form[Person](f => List(
  f.field(_.firstName).label("First name"),
  f.field(_.lastName).label("Last name")
    .validate(custom((e, v) => v.length <= e.firstName.length, (e, v) => ValidationError("Last name must be longer than first name!"))),
  f.field(_.age).label("Age"),
  f.field(_.address1).label("Address 1"),
  f.field(_.address2).label("Address 2"),
  f.field(_.gender).label("Gender").use(dataProvider(_ => List("Male", "Female"))),
  f.subform(_.cars, carForm, Car(null, 0)).label("Cars").renderHint(asList()),
  f.subform(_.legoSets, legoSetForm, LegoSet(null, null, 0, 0)).label("Lego sets")
))
```

In addition to the previous definitions, here we have a custom validation rule, which will be run on the backend only &#8211; no translation to JavaScript is provided. Apart from simple fields, this form also contains two subforms &#8211; for lists of a person&#8217;s cars and Lego sets. The default rendering of a subform list is a table; an alternative is a list of forms, which is used for cars.

And that&#8217;s it for the backend part. How you serve the JSON is entirely up to you. In our [demo server][3] we use [spray.io][4] for HTTP part, but it can be any web framework.

Now, the frontend. We need to designate a place, where our form will be rendered. A simple `div` will suffice. We also need a submit button and some placeholder for feedback:
```html
<div>
  <a href="#" class="btn btn-primary btn-lg" id="submit" role="button">Submit</a>
    
  
  <p id="feedback">
    
  </p>
    
  
  <div id="form-container">
    
  </div>
  
</div>
```

Next, when the page loads and we obtain the JSON description of the form (e.g. via a simple ajax call), we create a `SuplerForm` instance and instruct it to create and render the form:
```javascript
var formContainer = document.getElementById('form-container');
var form =  = new SuplerForm(formContainer, {});
form.render(formJson);
```

And finally, we need some JavaScript to call the correct methods; here I&#8217;m using JQuery to send the data to the server and handle the response:
```javascript
$('#submit').click(function() {
  var hasErrors = form.validate();

  if (hasErrors) {
    feedback.html('There are client-side validation errors.');
    feedback.show();
  } else {
    $.ajax({
      url: '/rest/form1.json',
      type: 'POST',
      data: JSON.stringify(form.getValue()),
      dataType: 'json',
      contentType: 'application/json; charset=utf-8',
      success: function (data) {
        if (form.processServerValidationErrors(data.validation_errors)) {
          feedback.html('There are server-side validation errors');
        } else {
          feedback.html(data.msg);
        }

        feedback.show();
      }
    });
  }

  return false;
});
```

Remember that `form` is an instance of the `SuplerForm` class. Upon an invocation of `validate()`, client-side validations are run, and incorrect fields are marked as such. If there are no errors, the form is submitted, and depending if server-side errors where returned or not, they are applied to the form, or a success message is shown.

The full sources are in the [example subproject][5] of the Supler codebase.

## Other important Supler traits

An important note, is that both `Form` and `Field` instances in Supler are re-useable and composable: you can compose a form of previously defined fields and forms; you can also have stand-alone field definitions.

The generated HTML has a simple structure and predictable naming, following 1-1 what&#8217;s in the form definition. Hence it&#8217;s easy to lookup a form element or form section generated by Supler and further customize it, either by adding styling, or additional client-side JavaScript logic.

It is also possible to customize the rendering process and influence how all parts of the HTML are rendered by providing custom render functions. Individual HTML tags, depending on type, templates of rendering a field, and more, can be overriden.

## What&#8217;s next?

What you can see currently is only the beginning for what we have in mind for Supler. A rough list of the features that we hope will form the future Supler 1.0:

  * customization of form rendering via HTML snippets
  * dynamically re-rendering form parts basing on user input
  * support for all kinds of form controls
  * i18n support
  * server-side value conversion

If Supler looks interesting to you, let us know by **[starring the project][1]**!

Brought to you by Adam & [Tomek][6]

 [1]: https://github.com/softwaremill/supler
 [2]: https://supler.softwaremill.com
 [3]: https://github.com/softwaremill/supler/blob/master/examples/src/main/scala/org/supler/demo/DemoServer.scala
 [4]: http://spray.io/
 [5]: https://github.com/softwaremill/supler/tree/master/examples/src/main/resources
 [6]: https://twitter.com/szimano
