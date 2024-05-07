---
title: Typed ask for Akka
author: Adam Warski
type: blog
date: 2013-05-21T09:42:25+00:00
url: /blog/2013/05/typed-ask-for-akka/
simplecatch-sidebarlayout:
  - default
dsq_thread_id:
  - 1303501382
wp-syntax-cache-content:
  - |
    a:7:{i:1;s:2029:"
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
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">import</span> akka.<span style="color: #000000;">pattern</span>.<span style="color: #000000;">ask</span>
    &nbsp;
    <span style="color: #0000ff; font-weight: bold;">case</span> <span style="color: #0000ff; font-weight: bold;">class</span> LookupUser<span style="color: #F78811;">&#40;</span>id<span style="color: #000080;">:</span> Int<span style="color: #F78811;">&#41;</span>
    &nbsp;
    <span style="color: #008000; font-style: italic;">// the actor impl should send back a message to the sender</span>
    <span style="color: #0000ff; font-weight: bold;">val</span> userFuture <span style="color: #000080;">=</span> actor <span style="color: #000080;">?</span> LookupUser<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">10</span><span style="color: #F78811;">&#41;</span>
    &nbsp;
    <span style="color: #008000; font-style: italic;">// userFuture: Future[Any]</span>
    &nbsp;
    userFuture onSuccess <span style="color: #F78811;">&#123;</span>
       <span style="color: #0000ff; font-weight: bold;">case</span> result <span style="color: #000080;">=&gt;</span> <span style="color: #F78811;">&#123;</span>
          <span style="color: #008000; font-style: italic;">// do something with the result</span>
          <span style="color: #008000; font-style: italic;">// result has type Any </span>
       <span style="color: #F78811;">&#125;</span>
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">import akka.pattern.ask
    
    case class LookupUser(id: Int)
    
    // the actor impl should send back a message to the sender
    val userFuture = actor ? LookupUser(10)
    
    // userFuture: Future[Any]
    
    userFuture onSuccess {
       case result =&gt; {
          // do something with the result
          // result has type Any 
       }
    }</p></div>
    ";i:2;s:422:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">trait</span> Replyable<span style="color: #F78811;">&#91;</span>T<span style="color: #F78811;">&#93;</span></pre></td></tr></table><p class="theCode" style="display:none;">trait Replyable[T]</p></div>
    ";i:3;s:1250:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">case</span> <span style="color: #0000ff; font-weight: bold;">class</span> LookupUser<span style="color: #F78811;">&#40;</span>id<span style="color: #000080;">:</span> Int<span style="color: #F78811;">&#41;</span> <span style="color: #0000ff; font-weight: bold;">extends</span> Replyable<span style="color: #F78811;">&#91;</span>Option<span style="color: #F78811;">&#91;</span>User<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#93;</span>
    <span style="color: #0000ff; font-weight: bold;">case</span> <span style="color: #0000ff; font-weight: bold;">class</span> UserCount<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span> <span style="color: #0000ff; font-weight: bold;">extends</span> Replyable<span style="color: #F78811;">&#91;</span>Int<span style="color: #F78811;">&#93;</span></pre></td></tr></table><p class="theCode" style="display:none;">case class LookupUser(id: Int) extends Replyable[Option[User]]
    case class UserCount() extends Replyable[Int]</p></div>
    ";i:4;s:2666:"
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
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">trait</span> ReplySupport <span style="color: #F78811;">&#123;</span>
      <span style="color: #0000ff; font-weight: bold;">implicit</span> <span style="color: #0000ff; font-weight: bold;">class</span> ReplyActorRef<span style="color: #F78811;">&#40;</span>actorRef<span style="color: #000080;">:</span> ActorRef<span style="color: #F78811;">&#41;</span> <span style="color: #F78811;">&#123;</span>
        <span style="color: #0000ff; font-weight: bold;">def</span> <span style="color: #000080;">?</span><span style="color: #F78811;">&#91;</span>T<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#40;</span>message<span style="color: #000080;">:</span> Replyable<span style="color: #F78811;">&#91;</span>T<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#41;</span>
                <span style="color: #F78811;">&#40;</span><span style="color: #0000ff; font-weight: bold;">implicit</span> timeout<span style="color: #000080;">:</span> Timeout, tag<span style="color: #000080;">:</span> ClassTag<span style="color: #F78811;">&#91;</span>T<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#41;</span><span style="color: #000080;">:</span> Future<span style="color: #F78811;">&#91;</span>T<span style="color: #F78811;">&#93;</span> <span style="color: #000080;">=</span> <span style="color: #F78811;">&#123;</span>
          akka.<span style="color: #000000;">pattern</span>.<span style="color: #000000;">ask</span><span style="color: #F78811;">&#40;</span>actorRef, message<span style="color: #F78811;">&#41;</span>.<span style="color: #000000;">mapTo</span><span style="color: #F78811;">&#91;</span>T<span style="color: #F78811;">&#93;</span>
        <span style="color: #F78811;">&#125;</span>
      <span style="color: #F78811;">&#125;</span>
    <span style="color: #F78811;">&#125;</span>
    &nbsp;
    <span style="color: #0000ff; font-weight: bold;">package</span> <span style="color: #0000ff; font-weight: bold;">object</span> reply <span style="color: #0000ff; font-weight: bold;">extends</span> ReplySupport</pre></td></tr></table><p class="theCode" style="display:none;">trait ReplySupport {
      implicit class ReplyActorRef(actorRef: ActorRef) {
        def ?[T](message: Replyable[T])
                (implicit timeout: Timeout, tag: ClassTag[T]): Future[T] = {
          akka.pattern.ask(actorRef, message).mapTo[T]
        }
      }
    }
    
    package object reply extends ReplySupport</p></div>
    ";i:5;s:1522:"
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
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">import</span> reply.<span style="color: #000080;">_</span>
    &nbsp;
    <span style="color: #0000ff; font-weight: bold;">val</span> userFuture <span style="color: #000080;">=</span> actor <span style="color: #000080;">?</span> LookupUser<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">10</span><span style="color: #F78811;">&#41;</span>
    &nbsp;
    <span style="color: #008000; font-style: italic;">// userFuture: Future[Option[User]]</span>
    &nbsp;
    userFuture onSuccess <span style="color: #F78811;">&#123;</span>
       <span style="color: #0000ff; font-weight: bold;">case</span> result <span style="color: #000080;">=&gt;</span> <span style="color: #F78811;">&#123;</span>
          <span style="color: #008000; font-style: italic;">// do something with the result</span>
          <span style="color: #008000; font-style: italic;">// result has type Option[User] </span>
       <span style="color: #F78811;">&#125;</span>
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">import reply._
    
    val userFuture = actor ? LookupUser(10)
    
    // userFuture: Future[Option[User]]
    
    userFuture onSuccess {
       case result =&gt; {
          // do something with the result
          // result has type Option[User] 
       }
    }</p></div>
    ";i:6;s:2800:"
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
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">trait</span> ReplyingActor <span style="color: #0000ff; font-weight: bold;">extends</span> Actor <span style="color: #F78811;">&#123;</span>
      <span style="color: #0000ff; font-weight: bold;">def</span> receive <span style="color: #000080;">=</span> <span style="color: #F78811;">&#123;</span>
        <span style="color: #0000ff; font-weight: bold;">case</span> m<span style="color: #000080;">:</span> Replyable<span style="color: #F78811;">&#91;</span><span style="color: #000080;">_</span><span style="color: #F78811;">&#93;</span> <span style="color: #0000ff; font-weight: bold;">if</span> receiveReplyable.<span style="color: #000000;">isDefinedAt</span><span style="color: #F78811;">&#40;</span>m<span style="color: #F78811;">&#41;</span> <span style="color: #000080;">=&gt;</span> <span style="color: #F78811;">&#123;</span>
          <span style="color: #0000ff; font-weight: bold;">try</span> <span style="color: #F78811;">&#123;</span>
            sender <span style="color: #000080;">!</span> receiveReplyable<span style="color: #F78811;">&#40;</span>m<span style="color: #F78811;">&#41;</span>
          <span style="color: #F78811;">&#125;</span> <span style="color: #0000ff; font-weight: bold;">catch</span> <span style="color: #F78811;">&#123;</span>
            <span style="color: #0000ff; font-weight: bold;">case</span> e<span style="color: #000080;">:</span> Exception <span style="color: #000080;">=&gt;</span> sender <span style="color: #000080;">!</span> Failure<span style="color: #F78811;">&#40;</span>e<span style="color: #F78811;">&#41;</span>
          <span style="color: #F78811;">&#125;</span>
        <span style="color: #F78811;">&#125;</span>
      <span style="color: #F78811;">&#125;</span>
    &nbsp;
      <span style="color: #0000ff; font-weight: bold;">def</span> receiveReplyable<span style="color: #F78811;">&#91;</span>T<span style="color: #F78811;">&#93;</span><span style="color: #000080;">:</span> PartialFunction<span style="color: #F78811;">&#91;</span>Replyable<span style="color: #F78811;">&#91;</span>T<span style="color: #F78811;">&#93;</span>, T<span style="color: #F78811;">&#93;</span>
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">trait ReplyingActor extends Actor {
      def receive = {
        case m: Replyable[_] if receiveReplyable.isDefinedAt(m) =&gt; {
          try {
            sender ! receiveReplyable(m)
          } catch {
            case e: Exception =&gt; sender ! Failure(e)
          }
        }
      }
    
      def receiveReplyable[T]: PartialFunction[Replyable[T], T]
    }</p></div>
    ";i:7;s:1585:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    6
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">class</span> UserActor <span style="color: #0000ff; font-weight: bold;">extends</span> ReplyingActor <span style="color: #F78811;">&#123;</span>
       <span style="color: #0000ff; font-weight: bold;">def</span> receiveReplyable<span style="color: #F78811;">&#91;</span>T<span style="color: #F78811;">&#93;</span> <span style="color: #000080;">=</span> <span style="color: #F78811;">&#123;</span>
          <span style="color: #0000ff; font-weight: bold;">case</span> LookupUser<span style="color: #F78811;">&#40;</span>id<span style="color: #F78811;">&#41;</span> <span style="color: #000080;">=&gt;</span> Some<span style="color: #F78811;">&#40;</span>User<span style="color: #F78811;">&#40;</span>...<span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span>
          <span style="color: #0000ff; font-weight: bold;">case</span> UserCount<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span> <span style="color: #000080;">=&gt;</span> <span style="color: #F78811;">512</span>
       <span style="color: #F78811;">&#125;</span>
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">class UserActor extends ReplyingActor {
       def receiveReplyable[T] = {
          case LookupUser(id) =&gt; Some(User(...))
          case UserCount() =&gt; 512
       }
    }</p></div>
    ";}
tags:
  - akka
  - scala

---
[Akka][1] is a great tool for writing distributed applications. One thing that always surprised me though is that while being based on Scala, which is a very type-safe language, the elementary construct in Akka &#8211; an actor &#8211; is not really type safe. You can send any message to any actor, and get back any object in reply.

The upcoming 2.2 release of Akka will contain an experimental implementation of [typed channels][2], which use macros to ensure type-safety in actor communication; but before that is final, here&#8217;s a simpler and less powerful approach, to add some typing to the [ask pattern][3]. 

You can look at the ask pattern as a kind of asynchronous method invocation: you send a message (its class corresponds to the method name) with some arguments, and expect a reply (method result). More specifically, we get back a `Future`, which will eventually hold the reply (if any). Note that the reply can also be of type `Unit`, corresponding to methods returning no result. Knowing when such &#8220;methods&#8221; complete may still be useful, though.

A very simple example of the basic ask pattern usage:

<pre lang="scala" line="1">import akka.pattern.ask

case class LookupUser(id: Int)

// the actor impl should send back a message to the sender
val userFuture = actor ? LookupUser(10)

// userFuture: Future[Any]

userFuture onSuccess {
   case result => {
      // do something with the result
      // result has type Any 
   }
}
</pre>

The not-so-nice thing here is that the return type of `?` (see the [AskSupport implementation][4]) is `Future[Any]`, as the actor may respond with any message. However ideally, when sending `LookupUser` we would want to get a `Future[Option[User]]`, when sending `UserCount` a `Future[Int]` and so on.

This is in fact quite easy to implement. First of all, we must somehow embed the expected reply type in the message. For that we can use a trait, which takes the type of the expected response as a type parameter:

<pre lang="scala" line="1">trait Replyable[T]
</pre>

This can be used in the messages that we are sending to the actor:

<pre lang="scala" line="1">case class LookupUser(id: Int) extends Replyable[Option[User]]
case class UserCount() extends Replyable[Int]
</pre>

Now we need a variant of `?` which returns a future with the right type parameter:

<pre lang="scala" line="1">trait ReplySupport {
  implicit class ReplyActorRef(actorRef: ActorRef) {
    def ?[T](message: Replyable[T])
            (implicit timeout: Timeout, tag: ClassTag[T]): Future[T] = {
      akka.pattern.ask(actorRef, message).mapTo[T]
    }
  }
}

package object reply extends ReplySupport
</pre>

You can see that we are simply re-using the existing ask implementation, and mapping the resulting future to the right type. The timeout is an implicit parameter of `ask` and `ClassTag` of `mapTo`, hence we must include them in the signature as well.

Usage is quite simple, in fact it&#8217;s almost the same as before, except for the import and that the future is of the right type:

<pre lang="scala" line="1">import reply._

val userFuture = actor ? LookupUser(10)

// userFuture: Future[Option[User]]

userFuture onSuccess {
   case result => {
      // do something with the result
      // result has type Option[User] 
   }
}
</pre>

That&#8217;s the actor-user side. What about the actor itself? How to ensure that if an actor receives a message of type `Replyable[T]`, it will actually answer with `T`? As quite commonly in Scala, the answer again is a trait, which can be mixed into an actor:

<pre lang="scala" line="1">trait ReplyingActor extends Actor {
  def receive = {
    case m: Replyable[_] if receiveReplyable.isDefinedAt(m) => {
      try {
        sender ! receiveReplyable(m)
      } catch {
        case e: Exception => sender ! Failure(e)
      }
    }
  }

  def receiveReplyable[T]: PartialFunction[Replyable[T], T]
}
</pre>

And example usage:

<pre lang="scala" line="1">class UserActor extends ReplyingActor {
   def receiveReplyable[T] = {
      case LookupUser(id) => Some(User(...))
      case UserCount() => 512
   }
}
</pre>

Now this is all nicely type-checked. If we tried to return a `String` in the `UserCount` branch, we would get a compile-time error.

Hope this will be helpful &#8211; and comments as always are welcome!

Adam

 [1]: http://akka.io/
 [2]: http://doc.akka.io/docs/akka/2.2-M3/scala/typed-channels.html
 [3]: http://doc.akka.io/docs/akka/2.1.4/scala/actors.html#Ask__Send-And-Receive-Future
 [4]: https://github.com/akka/akka/blob/7ea3044af87b28d654bfb955a39c8fd1d4e99e52/akka-actor/src/main/scala/akka/pattern/AskSupport.scala
