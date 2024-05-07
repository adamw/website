---
title: 'DI in Scala: Cake Pattern pros & cons'
author: Adam Warski
type: post
date: 2011-04-29T06:14:08+00:00
url: /blog/2011/04/di-in-scala-cake-pattern-pros-cons/
dsq_thread_id:
  - 1051048148
wp-syntax-cache-content:
  - |
    a:6:{i:1;s:729:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">val</span> env <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> MysqlDatabaseComponentImpl
       <span style="color: #0000ff; font-weight: bold;">with</span> UserRepositoryComponent
       <span style="color: #0000ff; font-weight: bold;">with</span> UserAuthenticatorComponent</pre></td></tr></table><p class="theCode" style="display:none;">val env = new MysqlDatabaseComponentImpl
       with UserRepositoryComponent
       with UserAuthenticatorComponent</p></div>
    ";i:2;s:727:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">val</span> env <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> MockDatabaseComponentImpl
       <span style="color: #0000ff; font-weight: bold;">with</span> UserRepositoryComponent
       <span style="color: #0000ff; font-weight: bold;">with</span> UserAuthenticatorComponent</pre></td></tr></table><p class="theCode" style="display:none;">val env = new MockDatabaseComponentImpl
       with UserRepositoryComponent
       with UserAuthenticatorComponent</p></div>
    ";i:3;s:1053:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">val</span> env <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> MysqlDatabaseComponentImpl
       <span style="color: #0000ff; font-weight: bold;">with</span> UserRepositoryComponent
       <span style="color: #0000ff; font-weight: bold;">with</span> UserAuthenticatorComponent <span style="color: #F78811;">&#123;</span>
       <span style="color: #0000ff; font-weight: bold;">val</span> encryptionMethod <span style="color: #000080;">=</span> EncryptionMethods.<span style="color: #000000;">MD5</span>
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">val env = new MysqlDatabaseComponentImpl
       with UserRepositoryComponent
       with UserAuthenticatorComponent {
       val encryptionMethod = EncryptionMethods.MD5
    }</p></div>
    ";i:4;s:1218:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    6
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">trait</span> UserAuthenticatorComponentWithMD5 
             <span style="color: #0000ff; font-weight: bold;">extends</span> UserAuthenticatorComponent  <span style="color: #F78811;">&#123;</span>
       <span style="color: #008000; font-style: italic;">// dependency specification duplication!</span>
       <span style="color: #0000ff; font-weight: bold;">this</span><span style="color: #000080;">:</span> UserRepositoryComponent <span style="color: #000080;">=&gt;</span> 
       <span style="color: #0000ff; font-weight: bold;">val</span> encryptionMethod <span style="color: #000080;">=</span> EncryptionMethods.<span style="color: #000000;">MD5</span>
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">trait UserAuthenticatorComponentWithMD5 
             extends UserAuthenticatorComponent  {
       // dependency specification duplication!
       this: UserRepositoryComponent =&gt; 
       val encryptionMethod = EncryptionMethods.MD5
    }</p></div>
    ";i:5;s:1215:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">trait</span> PasswordEncoderComponent <span style="color: #F78811;">&#123;</span>
       <span style="color: #0000ff; font-weight: bold;">this</span><span style="color: #000080;">:</span> UserAuthenticatorComponent <span style="color: #000080;">=&gt;</span>
       <span style="color: #008000; font-style: italic;">// encryptionMethod comes from UserAuthenticatorComponent</span>
       <span style="color: #0000ff; font-weight: bold;">val</span> encryptionAlgorithm <span style="color: #000080;">=</span> Encryption.<span style="color: #000000;">getAlgorithm</span><span style="color: #F78811;">&#40;</span>encryptionMethod<span style="color: #F78811;">&#41;</span>
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">trait PasswordEncoderComponent {
       this: UserAuthenticatorComponent =&gt;
       // encryptionMethod comes from UserAuthenticatorComponent
       val encryptionAlgorithm = Encryption.getAlgorithm(encryptionMethod)
    }</p></div>
    ";i:6;s:1180:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    6
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">val</span> env <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> MysqlDatabaseComponentImpl
       <span style="color: #0000ff; font-weight: bold;">with</span> UserRepositoryComponent
       <span style="color: #0000ff; font-weight: bold;">with</span> UserAuthenticatorComponent 
       <span style="color: #0000ff; font-weight: bold;">with</span> PasswordEncoderComponent <span style="color: #F78811;">&#123;</span>
       <span style="color: #0000ff; font-weight: bold;">val</span> encryptionMethod <span style="color: #000080;">=</span> EncryptionMethods.<span style="color: #000000;">MD5</span>
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">val env = new MysqlDatabaseComponentImpl
       with UserRepositoryComponent
       with UserAuthenticatorComponent 
       with PasswordEncoderComponent {
       val encryptionMethod = EncryptionMethods.MD5
    }</p></div>
    ";}
tags:
  - dependency injection
  - java
  - scala
  - modularity

---
I&#8217;ve been looking at alternatives for java-style DI and DI containers which would use pure Scala; a promising candidate is the Cake Pattern (see my [earlier blog post][1] for information on how the Cake Pattern works). FP enthusiast also claim that they don&#8217;t need any DI frameworks, as higher-order functions are enough. 

Recently Debasish Ghosh also [blogged on a similar subject][2]. I think his article is a very good introduction into the subject.

Below are some problems I encountered with the Cake Pattern. (Higher-order functions are coming up in the next post.) If you have solutions to any of them, let me know!

##### Parametrizing the system with a component implementation

First of all, it is not possible to parametrize a system with a component implementation. Supposing I have three components: `DatabaseComponent`, `UserRepositoryComponent`, `UserAuthenticatorComponent` with implementations, the top-level environment/entry point of the system would be created as follows:

<pre lang="scala" line="1" escaped="true">val env = new MysqlDatabaseComponentImpl
   with UserRepositoryComponent
   with UserAuthenticatorComponent
</pre>

Now to create a testing environment with a mock database, I would have to do:

<pre lang="scala" line="1" escaped="true">val env = new MockDatabaseComponentImpl
   with UserRepositoryComponent
   with UserAuthenticatorComponent
</pre>

Note how much of the code is the same. This isn&#8217;t a problem with 3 components, but if there are 20? All of them but one have to be repeated just to change the implementation of one component. This clearly leads to quite a lot of code duplication.

##### Component configuration

Quite often a component needs to be configured. Let&#8217;s say I have a `UserAuthenticatorComponent` which depends on `UserRepositoryComponent`. However, the authenticator component has an abstract val `encryptionMethod`, used to configure the encryption algorithm. How can I configure the component? There are two ways. The abstract val can be concretized when defining the env, e.g.:

<pre lang="scala" line="1" escaped="true">val env = new MysqlDatabaseComponentImpl
   with UserRepositoryComponent
   with UserAuthenticatorComponent {
   val encryptionMethod = EncryptionMethods.MD5
}
</pre>

But what if I want to re-use a configured component? An obvious answer is to extend the `UserAuthenticatorComponent` trait. However if that component has any dependencies (which, in the Cake Pattern, are expressed using self-types), they need to be repeated, as **self-types are not inherited**. So a reusable, configured component could look like this:

<pre lang="scala" line="1" escaped="true">trait UserAuthenticatorComponentWithMD5 
         extends UserAuthenticatorComponent  {
   // dependency specification duplication!
   this: UserRepositoryComponent =&gt; 
   val encryptionMethod = EncryptionMethods.MD5
}
</pre>

If we don&#8217;t repeat the self-types, the compiler will complain about incorrect `UserAuthenticatorComponent` usage.

##### No control over initialization order

A problem also related to configuration, is that there is no type-safe way to assure that the components are initialized in the proper order. Suppose as above that the `UserAuthenticatorComponent` has an abstract `encryptionMethod` which must be specified when creating the component. If we have another component that depends on `UserAuthenticatorComponent`:

<pre lang="scala" line="1" escaped="true">trait PasswordEncoderComponent {
   this: UserAuthenticatorComponent =&gt;
   // encryptionMethod comes from UserAuthenticatorComponent
   val encryptionAlgorithm = Encryption.getAlgorithm(encryptionMethod)
}
</pre>

and initialize our system as follow: 

<pre lang="scala" line="1" escaped="true">val env = new MysqlDatabaseComponentImpl
   with UserRepositoryComponent
   with UserAuthenticatorComponent 
   with PasswordEncoderComponent {
   val encryptionMethod = EncryptionMethods.MD5
}
</pre>

then at the moment of initialization of `encryptionAlgorithm`, `encryptionMethod` will be `null`! The only way to prevent this is to mix in the `UserAuthenticatorComponentWithMD5` **before** the `PasswordEncoderComponent`. But the type checker won&#8217;t tell us that.

##### Pros

Don&#8217;t get me wrong that I don&#8217;t like the Cake Pattern &#8211; I think it offers a very nice way to structure your programs. For example it eliminates the need for factories ([which I&#8217;m not a very big fan of][3]), or nicely separates dependencies on components and dependencies on data (*). But still, it could be better ;).

(*) Here each code fragment has in fact two types of arguments: normal method arguments, which can be used to pass data, and component arguments, expressed as the self type of the containing component. Whether these two types of arguments should be treated differently is a good question :).

What are your experiences with DI in Scala? Do you use a Java DI framework, one of the approaches used above or some other way?

Adam

 [1]: http://www.warski.org/blog/?p=291
 [2]: http://debasishg.blogspot.com/2011/03/pushing-envelope-on-oo-and-functional.html
 [3]: http://www.warski.org/blog/?p=272
