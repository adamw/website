---
title: 'When & why to use Supler for web forms?'
author: Adam Warski
type: post
date: 2015-06-30T14:07:03+00:00
url: /blog/2015/06/when-why-to-use-supler-for-web-forms/
simplecatch-sidebarlayout:
  - default
dsq_thread_id:
  - 3892287717
categories:
  - Java
  - Languages
  - Library
  - Scala
  - SoftwareMill
  - Supler
  - Uncategorized

---
If you haven’t yet heard about [Supler][1], it’s a Rapid Form Development library, working with your favorite Javascript frontend and Scala backend frameworks. It keeps the form definition centralized, taking care of client/server-side validation, form rendering, applying new values and serializing/deserializing. Star [Supler on GitHub][1] if you think that might be interesting!

<figure id="attachment_1605" aria-describedby="caption-attachment-1605" style="width: 300px" class="wp-caption aligncenter"><a href="http://www.warski.org/blog/2015/06/when-why-to-use-supler-for-web-forms/2015-06-30_1603/" rel="attachment wp-att-1605"><img loading="lazy" decoding="async" src="http://www.warski.org/blog/wp-content/uploads/2015/06/2015-06-30_1603-300x106.png" alt="From the examples on Github" width="300" height="106" class="size-medium wp-image-1605" srcset="https://www.warski.org/blog/wp-content/uploads/2015/06/2015-06-30_1603-300x106.png 300w, https://www.warski.org/blog/wp-content/uploads/2015/06/2015-06-30_1603-255x90.png 255w, https://www.warski.org/blog/wp-content/uploads/2015/06/2015-06-30_1603-1024x362.png 1024w, https://www.warski.org/blog/wp-content/uploads/2015/06/2015-06-30_1603-210x74.png 210w, https://www.warski.org/blog/wp-content/uploads/2015/06/2015-06-30_1603.png 1148w" sizes="(max-width: 300px) 100vw, 300px" /></a><figcaption id="caption-attachment-1605" class="wp-caption-text">Supler example from [GitHub][2]</figcaption></figure>

But if you are already using [Play!][3], [Scalatra][4], [Spring MVC][5] or any other Scala/Java web framework, why would you add Supler to the mix? All web frameworks already provide support for writing, validating and submitting web forms &#8211; why not use the built-in tools?

The answer is, as often in software development, _it depends_. For simple forms like login, register screens, profile edit etc. you should definitely **use what your web framework provides**. Such forms are usually static, have simple validation rules, don’t have any conditional fields or embedded forms. Also, if the form is central to the user experience of your site you may want to spend the additional time hand-crafting it, providing every possible UX/UI optimisation using custom Javascript.

But then comes the form where you need to conditionally enable/disable fields basing on complex business rules, or dynamically change possible choices in a dropdown, or embed a list of subforms in a form etc., and as most of the fields behave in a standard way, you could use some help from a library. **That’s where using Supler will help the most.**

You define the form structure on the backend, and only there, in one single place. All conditional logic regarding visibility, enabling fields, validation, dropdown choices etc. can be written having full information about the state of the edited entity, in Scala, using all of its power. Sure, such a setup won’t be optimal when it comes to network communication, and sometimes the validation errors will be visible a couple of milliseconds later as opposed to writing the corresponding Javascript by hand. But working and evolving the form will become much easier and faster. Re-using existing form to embed it to another form? Sure, just define a `subform` field. Refactoring your entity? The form will be refactored as well, as everything is type-safe. Adding a new field? Just add it to the backend form definition, no changes on the frontend needed.

While Supler imposes some constraints on how your form works, as every library, it is highly customisable, both on the backend and frontend (presentation) side. Take a look at our [quick-start][6] guide and extensive documentation.

To sum up:

  * for simple, static forms use what your framework provides
  * for highly optimized, user-experience-critical forms, hand-craft using Javascript
  * for dynamic, complex, nested forms use [Supler][7]

Finally, during spring we’ve given a number of talks on Supler of JUGs and a couple of conferences. If you’d like, you can view the shorter one (30 minutes) from Scalar (below) or the [long one (60 minutes) from LJC][8].

<span class="embed-youtube" style="text-align:center; display: block;"></span>

 [1]: https://github.com/softwaremill/supler
 [2]: https://github.com/softwaremill/supler/blob/master/examples/src/main/scala/org/demo/PersonForm.scala
 [3]: https://playframework.com
 [4]: http://www.scalatra.org
 [5]: http://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html
 [6]: http://docs.supler.io/en/latest/first.html#first
 [7]: http://supler.io
 [8]: https://skillsmatter.com/skillscasts/6342-supler-complex-web-forms-not-so-complex#video
