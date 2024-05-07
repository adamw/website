---
title: Why I started learning Emacs in 2016
author: Adam Warski
type: post
date: 2016-08-25T12:22:44+00:00
url: /blog/2016/08/why-i-started-learning-emacs-in-2016/
simplecatch-sidebarlayout:
  - default
dsq_thread_id:
  - 5093911261
categories:
  - something different

---
### Why learn another editor in the first place?

The main role of a good editor is to not get into your way while working, and maybe even help a bit from time to time. If you are a programmer, chances are you are spending a large amount of time using one &#8211; and that’s why it’s so important to choose a good editor. Learning a new editor is always a big investment &#8211; significant time passes before you tame your chosen tool and adjust it to your needs.

I’m a long-time [IntelliJ Idea][1] user. And it’s great for Scala/Java development, probably the best in market. But then, in some ways it feels a bit heavy. Even for the tiniest experiment you have to create a “project”, with its own window, meta-data directory etc. You go through a short wizard, and then it has to be imported and indexed, and re-imported, re-indexed, &#8230;. Maybe it doesn’t sound like much &#8211; but it’s definitely not a “lightweight” experience.

And then there are all those cases where you just want to edit a file. Maybe it’s a simple HTML page, or a blog in markdown. Or maybe you want to quickly clone a repository from GitHub and just browse around without bothering with the whole importing process. That’s why you need _another_ editor in addition to a full-blown IDE such as IntelliJ.

For many years I’ve used either Vim or Sublime, but I never really learned either well, I know only the basic shortcuts. But I decided that it’s about time to get to know at least one “regular” editor better. Choosing between Sublime, Atom, Vim and Emacs I decided to go with the last one. Why? Well, I’ve heard of all those legendary hackers using them, so even though I’m very far from being a legendary hacker, I wanted to join them at least on the editor lever. Secondly, Emacs seems to be gaining popularity among programming hipsters &#8211; again a good reason to try. Finally, it’s been around for so long, and it’s still being developed, also for new languages (see [Ensime][2]), that there must be something in it.

### Results

As always, there are good and bad sides. After 3 months of the experiment, mostly spent configuring Emacs, trying different modes and learning elisp, I’m not yet sure which side wins. I’ll skip the rant on the completely different keybindings from everything else, as that’s just a characteristic of the tool. Considering Emacs’s age, you might think that all the other tools should use `C-w` for cut, not the other way round (I’m currently using IntelliJ for Scala and Emcas for everything else. I’m training my brain to be elastic enough to know when to use which keymap).

On the good side, it’s definitely great to have a homogenous editing experience whether I’m experimenting in PureScript, writing a blog in Markdown, browsing through a simple Scala project or making a quick CSS fix. [Magit][3] is great for git &#8211; if only there’s a `.git` in some parent directory, I can commit, branch, push using the same friendly (!) interface. Without creating any explicit projects, [projectile][4] has good heuristics on when a directory is part of a project and where&#8217;s the project root, allowing me to navigate to other files in the project (again, that’s usually demarcated by where the `.git` directory is) without any additional steps.

On both the mixed good&bad side, you have to configure everything yourself. Not surprisingly, it’s the same experience as with Linux. When I was young and smart I enjoyed spending long days recompiling everything from source using esoteric compiler settings I found on the Gentoo forums, and then spending some more days trying to get my sound card to work. But now I’m a happy MacOS user, happy as I don’t have to deal with those things. So with the Emacs experience of “configure everything” I feel a bit younger, but then I imagine I might want an editor which “just works” at some point.

On the definitely bad side, the quality of the different modes/packages (plugins) varies. They often offer inconsistent keybindings, which causes you to either re-define the keymaps in the configuration, or memorize different keybindings depending on which language you are currently in. For example, when editing Markdown the key combination I use for moving between words is assigned to changing the header size. Another thing is auto-indentation. In some languages it works great. In others, it often gets lost. I’m sure of course I could fix all of these flaws myself, by overwriting some elisp functions using the advice mechanism, for example. But then I don’t want to become a full-time Emacs configurator.

### In the future &#8230;

Who knows, you might see me doing a live-coding presentation from Emacs one day. Or not ;).

Or maybe Jetbrains will develop an all-purpose editor basing on the excellent foundation they have in IntelliJ? Without the need to create projects, allowing me to just browse to a directory and edit files there. That would definitely be a very strong contestant in the editor wars.

 [1]: https://www.jetbrains.com/idea/
 [2]: http://ensime.github.io
 [3]: https://magit.vc
 [4]: https://github.com/bbatsov/projectile
