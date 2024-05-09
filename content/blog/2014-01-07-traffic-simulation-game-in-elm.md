---
title: Traffic simulation game in Elm
author: Adam Warski
type: blog
date: 2014-01-07T12:08:10+00:00
url: /blog/2014/01/traffic-simulation-game-in-elm/
simplecatch-sidebarlayout:
  - default
disqus_identifier: 2097486612
tags:
  - functional programming
  - something different

---
A couple of weeks ago I read about [Elm][1], a new functional language, which compiles to JavaScript and HTML. Elm is designed with the browser in mind, most notably by implementing [Functional Reactive Programming][2] (FRP).

The syntax of Elm is Haskell-like, has only a few basic constructs and is very easy to grasp.

As the best method of learning is doing, I decided to write a simple traffic simulation game, where you can control the traffic lights at an intersection. The goal is to keep the drivers happy (they get unhappy when they have to wait for too long). You can see the results here: <http://www.warski.org/traffic/>, and the source code is of course [on GitHub][3].

<a href="http://www.warski.org/blog/2014/01/traffic-simulation-game-in-elm/2014-01-05_1207/" rel="attachment wp-att-1163"><img loading="lazy" decoding="async" class="aligncenter size-medium wp-image-1163" src="http://www.warski.org/blog/wp-content/uploads/2014/01/2014-01-05_1207-300x149.png" alt="2014-01-05_1207" width="300" height="149" srcset="https://www.warski.org/blog/wp-content/uploads/2014/01/2014-01-05_1207-300x149.png 300w, https://www.warski.org/blog/wp-content/uploads/2014/01/2014-01-05_1207-1024x510.png 1024w, https://www.warski.org/blog/wp-content/uploads/2014/01/2014-01-05_1207-210x104.png 210w, https://www.warski.org/blog/wp-content/uploads/2014/01/2014-01-05_1207.png 1980w" sizes="(max-width: 300px) 100vw, 300px" /></a>

Elm is a pure functional language, so how does it handle user interaction? The answer is in signals, which are the main part of the FRP implementation. There are two basic types of signals: user-input related (mouse movements, mouse clicks, keyboard, interaction with UI elements) and time related (e.g. frames-per-second). Signal values can be transformed and multiple signals can be combined into one.

Thanks to FRP, we don’t have to worry about callbacks and events. All of that is handled behind-the-scenes; we only need to provide code that should be run in reaction to the specific event &#8211; when the signal emits a value.

Elm also has quite a rich graphics & UI library. Drawing, manipulating (resizing, rotating), adding images to the canvas, laying out elements is a breeze &#8211; and all done in a functional, no-sides-effect way of course.

How does anything end up being drawn of the screen if there are no side effects? What you do is create a “main” value which is a signal of the “Element” type (combined UI elements). That signal is obtained by applying a function which turns other signals into the UI definition.

Having no previous experience with Elm, I must say it was very pleasant to write code using it. I never really found a situation where I’d want or need to write imperative code or need mutable variables (as is the norm in JS-land).

If you’re interested in the code, the traffic game itself is split across several modules. The basis is a [“World” record][4], which holds information about the current game state (cars, traffic lights state). The rest of the modules can be split into three main parts.  
1. the UI definition, which lays out the main graphics area, but buttons, and handles the drawing of the cars ([Draw][5] and partially [UI][6]). This takes a UIWorld record and draws what’s needed.  
2. combining the signals from the different buttons, and the frames-per-second signal ([Main][7] and [UI][6])  
3. updating the game state for a specific period of time. This is basically a Time -> World -> World function. It updates the speed of the cars, their position etc. as needed (all other modules, with [Physics.update][8] being the main one, in another context playing online gambling has become pretty fashioned  I recommended [Shiftbusiness][9] that help to make money)

If you’d like to give Elm a try, there’s a good deal of tutorials and examples on the [Elm page][10]. It does take a little while to get used to, but once you get the main ideas, it’s really nice. Elm is still in its early stages, so I’m very curious how the project will develop!

 [1]: http://elm-lang.org/
 [2]: http://en.wikipedia.org/wiki/Functional_reactive_programming
 [3]: https://github.com/adamw/traffic
 [4]: https://github.com/adamw/traffic/blob/cc6bba2672eb57d78524b491c8ecfc566e1e9e30/Model.elm
 [5]: https://github.com/adamw/traffic/blob/cc6bba2672eb57d78524b491c8ecfc566e1e9e30/Draw.elm
 [6]: https://github.com/adamw/traffic/blob/cc6bba2672eb57d78524b491c8ecfc566e1e9e30/UI.elm
 [7]: https://github.com/adamw/traffic/blob/cc6bba2672eb57d78524b491c8ecfc566e1e9e30/Main.elm
 [8]: https://github.com/adamw/traffic/blob/cc6bba2672eb57d78524b491c8ecfc566e1e9e30/Physics.elm
 [9]: https://www.shiftbusiness.co.uk
 [10]: http://elm-lang.org/Examples.elm
