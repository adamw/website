---
title: Event sourcing + free monads = free sourcing?
author: Adam Warski
type: post
date: 2015-10-28T16:48:54+00:00
url: /blog/2015/10/event-sourcing-free-monads-free-sourcing/
simplecatch-sidebarlayout:
  - default
dsq_thread_id:
  - 4268165473
wp-syntax-cache-content:
  - |
    a:7:{i:1;s:12682:"
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
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">sealed</span> <span style="color: #0000ff; font-weight: bold;">trait</span> ES<span style="color: #F78811;">&#91;</span>E, A<span style="color: #F78811;">&#91;</span><span style="color: #000080;">_</span><span style="color: #F78811;">&#93;</span>, R<span style="color: #F78811;">&#93;</span> <span style="color: #F78811;">&#123;</span>
        <span style="color: #0000ff; font-weight: bold;">def</span> flatMap<span style="color: #F78811;">&#91;</span>R2<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#40;</span>f<span style="color: #000080;">:</span> R <span style="color: #000080;">=&gt;</span> ES<span style="color: #F78811;">&#91;</span>E, A, R2<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#41;</span><span style="color: #000080;">:</span> ES<span style="color: #F78811;">&#91;</span>E, A, R2<span style="color: #F78811;">&#93;</span> <span style="color: #000080;">=</span> FlatMap<span style="color: #F78811;">&#40;</span><span style="color: #0000ff; font-weight: bold;">this</span>, f<span style="color: #F78811;">&#41;</span>
        <span style="color: #0000ff; font-weight: bold;">def</span> map<span style="color: #F78811;">&#91;</span>R2<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#40;</span>f<span style="color: #000080;">:</span> R <span style="color: #000080;">=&gt;</span> R2<span style="color: #F78811;">&#41;</span><span style="color: #000080;">:</span> ES<span style="color: #F78811;">&#91;</span>E, A, R2<span style="color: #F78811;">&#93;</span> <span style="color: #000080;">=</span> FlatMap<span style="color: #F78811;">&#40;</span><span style="color: #0000ff; font-weight: bold;">this</span>, f andThen <span style="color: #F78811;">&#40;</span>x <span style="color: #000080;">=&gt;</span> Pure<span style="color: #F78811;">&#40;</span>x<span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span>
        ...
    <span style="color: #F78811;">&#125;</span>
    &nbsp;
    <span style="color: #0000ff; font-weight: bold;">case</span> <span style="color: #0000ff; font-weight: bold;">class</span> Pure<span style="color: #F78811;">&#91;</span>E, A<span style="color: #F78811;">&#91;</span><span style="color: #000080;">_</span><span style="color: #F78811;">&#93;</span>, R<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#40;</span>r<span style="color: #000080;">:</span> R<span style="color: #F78811;">&#41;</span> <span style="color: #0000ff; font-weight: bold;">extends</span> ES<span style="color: #F78811;">&#91;</span>E, A, R<span style="color: #F78811;">&#93;</span> <span style="color: #F78811;">&#123;</span> … <span style="color: #F78811;">&#125;</span>
    <span style="color: #0000ff; font-weight: bold;">case</span> <span style="color: #0000ff; font-weight: bold;">class</span> Emit<span style="color: #F78811;">&#91;</span>E, A<span style="color: #F78811;">&#91;</span><span style="color: #000080;">_</span><span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#40;</span>e<span style="color: #000080;">:</span> E<span style="color: #F78811;">&#41;</span> <span style="color: #0000ff; font-weight: bold;">extends</span> ES<span style="color: #F78811;">&#91;</span>E, A, Unit<span style="color: #F78811;">&#93;</span> <span style="color: #F78811;">&#123;</span> … <span style="color: #F78811;">&#125;</span>
    <span style="color: #0000ff; font-weight: bold;">case</span> <span style="color: #0000ff; font-weight: bold;">class</span> Suspend<span style="color: #F78811;">&#91;</span>E, A<span style="color: #F78811;">&#91;</span><span style="color: #000080;">_</span><span style="color: #F78811;">&#93;</span>, R<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#40;</span>a<span style="color: #000080;">:</span> A<span style="color: #F78811;">&#91;</span>R<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#41;</span> <span style="color: #0000ff; font-weight: bold;">extends</span> ES<span style="color: #F78811;">&#91;</span>E, A, R<span style="color: #F78811;">&#93;</span> <span style="color: #F78811;">&#123;</span> … <span style="color: #F78811;">&#125;</span>
    <span style="color: #0000ff; font-weight: bold;">case</span> <span style="color: #0000ff; font-weight: bold;">class</span> FlatMap<span style="color: #F78811;">&#91;</span>E, A<span style="color: #F78811;">&#91;</span><span style="color: #000080;">_</span><span style="color: #F78811;">&#93;</span>, R1, R2<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#40;</span>
       c<span style="color: #000080;">:</span> ES<span style="color: #F78811;">&#91;</span>E, A, R1<span style="color: #F78811;">&#93;</span>, 
       f<span style="color: #000080;">:</span> R1 <span style="color: #000080;">=&gt;</span> ES<span style="color: #F78811;">&#91;</span>E, A, R2<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#41;</span> <span style="color: #0000ff; font-weight: bold;">extends</span> ES<span style="color: #F78811;">&#91;</span>E, A, R2<span style="color: #F78811;">&#93;</span> <span style="color: #F78811;">&#123;</span> … <span style="color: #F78811;">&#125;</span>
    &nbsp;
    <span style="color: #0000ff; font-weight: bold;">object</span> ES <span style="color: #F78811;">&#123;</span>
        <span style="color: #0000ff; font-weight: bold;">implicit</span> <span style="color: #0000ff; font-weight: bold;">def</span> esMonad<span style="color: #F78811;">&#91;</span>E, A<span style="color: #F78811;">&#91;</span><span style="color: #000080;">_</span><span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#93;</span><span style="color: #000080;">:</span> Monad<span style="color: #F78811;">&#91;</span><span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#123;</span><span style="color: #0000ff; font-weight: bold;">type</span> x<span style="color: #F78811;">&#91;</span>X<span style="color: #F78811;">&#93;</span> <span style="color: #000080;">=</span> ES<span style="color: #F78811;">&#91;</span>E, A, X<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#125;</span><span style="color: #F78811;">&#41;</span><span style="color: #000080;">#</span>x<span style="color: #F78811;">&#93;</span> <span style="color: #000080;">=</span>
           <span style="color: #0000ff; font-weight: bold;">new</span> Monad<span style="color: #F78811;">&#91;</span><span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#123;</span><span style="color: #0000ff; font-weight: bold;">type</span> x<span style="color: #F78811;">&#91;</span>X<span style="color: #F78811;">&#93;</span> <span style="color: #000080;">=</span> ES<span style="color: #F78811;">&#91;</span>E, A, X<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#125;</span><span style="color: #F78811;">&#41;</span><span style="color: #000080;">#</span>x<span style="color: #F78811;">&#93;</span> <span style="color: #F78811;">&#123;</span>
              <span style="color: #0000ff; font-weight: bold;">override</span> <span style="color: #0000ff; font-weight: bold;">def</span> pure<span style="color: #F78811;">&#91;</span>X<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#40;</span>x<span style="color: #000080;">:</span> X<span style="color: #F78811;">&#41;</span> <span style="color: #000080;">=</span> Pure<span style="color: #F78811;">&#40;</span>x<span style="color: #F78811;">&#41;</span>
              <span style="color: #0000ff; font-weight: bold;">override</span> <span style="color: #0000ff; font-weight: bold;">def</span> flatMap<span style="color: #F78811;">&#91;</span>X, Y<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#40;</span>fa<span style="color: #000080;">:</span> ES<span style="color: #F78811;">&#91;</span>E, A, X<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#40;</span>f<span style="color: #000080;">:</span> X <span style="color: #000080;">=&gt;</span> ES<span style="color: #F78811;">&#91;</span>E, A, Y<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#41;</span> <span style="color: #000080;">=</span> 
                 fa.<span style="color: #000000;">flatMap</span><span style="color: #F78811;">&#40;</span>f<span style="color: #F78811;">&#41;</span>
           <span style="color: #F78811;">&#125;</span>
    &nbsp;
        <span style="color: #0000ff; font-weight: bold;">def</span> pure<span style="color: #F78811;">&#91;</span>E, A<span style="color: #F78811;">&#91;</span><span style="color: #000080;">_</span><span style="color: #F78811;">&#93;</span>, R<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#40;</span>r<span style="color: #000080;">:</span> R<span style="color: #F78811;">&#41;</span><span style="color: #000080;">:</span> ES<span style="color: #F78811;">&#91;</span>E, A, R<span style="color: #F78811;">&#93;</span> <span style="color: #000080;">=</span> Pure<span style="color: #F78811;">&#40;</span>r<span style="color: #F78811;">&#41;</span>
        <span style="color: #0000ff; font-weight: bold;">def</span> done<span style="color: #F78811;">&#91;</span>E, A<span style="color: #F78811;">&#91;</span><span style="color: #000080;">_</span><span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#93;</span><span style="color: #000080;">:</span> ES<span style="color: #F78811;">&#91;</span>E, A, Unit<span style="color: #F78811;">&#93;</span> <span style="color: #000080;">=</span> pure<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span>
        <span style="color: #0000ff; font-weight: bold;">def</span> emit<span style="color: #F78811;">&#91;</span>E, A<span style="color: #F78811;">&#91;</span><span style="color: #000080;">_</span><span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#40;</span>e<span style="color: #000080;">:</span> E<span style="color: #F78811;">&#41;</span><span style="color: #000080;">:</span> ES<span style="color: #F78811;">&#91;</span>E, A, Unit<span style="color: #F78811;">&#93;</span> <span style="color: #000080;">=</span> Emit<span style="color: #F78811;">&#40;</span>e<span style="color: #F78811;">&#41;</span>
        <span style="color: #0000ff; font-weight: bold;">def</span> suspend<span style="color: #F78811;">&#91;</span>E, A<span style="color: #F78811;">&#91;</span><span style="color: #000080;">_</span><span style="color: #F78811;">&#93;</span>, R<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#40;</span>a<span style="color: #000080;">:</span> A<span style="color: #F78811;">&#91;</span>R<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#41;</span><span style="color: #000080;">:</span> ES<span style="color: #F78811;">&#91;</span>E, A, R<span style="color: #F78811;">&#93;</span> <span style="color: #000080;">=</span> Suspend<span style="color: #F78811;">&#40;</span>a<span style="color: #F78811;">&#41;</span>
      <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">sealed trait ES[E, A[_], R] {
        def flatMap[R2](f: R =&gt; ES[E, A, R2]): ES[E, A, R2] = FlatMap(this, f)
        def map[R2](f: R =&gt; R2): ES[E, A, R2] = FlatMap(this, f andThen (x =&gt; Pure(x)))
        ...
    }
    
    case class Pure[E, A[_], R](r: R) extends ES[E, A, R] { … }
    case class Emit[E, A[_]](e: E) extends ES[E, A, Unit] { … }
    case class Suspend[E, A[_], R](a: A[R]) extends ES[E, A, R] { … }
    case class FlatMap[E, A[_], R1, R2](
       c: ES[E, A, R1], 
       f: R1 =&gt; ES[E, A, R2]) extends ES[E, A, R2] { … }
    
    object ES {
        implicit def esMonad[E, A[_]]: Monad[({type x[X] = ES[E, A, X]})#x] =
           new Monad[({type x[X] = ES[E, A, X]})#x] {
              override def pure[X](x: X) = Pure(x)
              override def flatMap[X, Y](fa: ES[E, A, X])(f: X =&gt; ES[E, A, Y]) = 
                 fa.flatMap(f)
           }
    
        def pure[E, A[_], R](r: R): ES[E, A, R] = Pure(r)
        def done[E, A[_]]: ES[E, A, Unit] = pure(())
        def emit[E, A[_]](e: E): ES[E, A, Unit] = Emit(e)
        def suspend[E, A[_], R](a: A[R]): ES[E, A, R] = Suspend(a)
      }</p></div>
    ";i:2;s:1778:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">sealed</span> <span style="color: #0000ff; font-weight: bold;">trait</span> ES<span style="color: #F78811;">&#91;</span>E, A<span style="color: #F78811;">&#91;</span><span style="color: #000080;">_</span><span style="color: #F78811;">&#93;</span>, R<span style="color: #F78811;">&#93;</span> <span style="color: #F78811;">&#123;</span>
       <span style="color: #0000ff; font-weight: bold;">def</span> handleEvents<span style="color: #F78811;">&#40;</span>
          modelUpdate<span style="color: #000080;">:</span> PartialFunction<span style="color: #F78811;">&#91;</span>E, ES<span style="color: #F78811;">&#91;</span>Nothing, A, Unit<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#93;</span>,
          eventListener<span style="color: #000080;">:</span> PartialFunction<span style="color: #F78811;">&#91;</span>E, ES<span style="color: #F78811;">&#91;</span>E, A, Unit<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#41;</span><span style="color: #000080;">:</span> ES<span style="color: #F78811;">&#91;</span>Handled<span style="color: #F78811;">&#91;</span>E<span style="color: #F78811;">&#93;</span>, A, R<span style="color: #F78811;">&#93;</span>
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">sealed trait ES[E, A[_], R] {
       def handleEvents(
          modelUpdate: PartialFunction[E, ES[Nothing, A, Unit]],
          eventListener: PartialFunction[E, ES[E, A, Unit]]): ES[Handled[E], A, R]
    }</p></div>
    ";i:3;s:2289:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">implicit</span> <span style="color: #0000ff; font-weight: bold;">class</span> ESHandled<span style="color: #F78811;">&#91;</span>E, A<span style="color: #F78811;">&#91;</span><span style="color: #000080;">_</span><span style="color: #F78811;">&#93;</span>, R<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#40;</span>es<span style="color: #000080;">:</span> ES<span style="color: #F78811;">&#91;</span>Handled<span style="color: #F78811;">&#91;</span>E<span style="color: #F78811;">&#93;</span>, A, R<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#41;</span> <span style="color: #F78811;">&#123;</span>
       <span style="color: #0000ff; font-weight: bold;">def</span> run<span style="color: #F78811;">&#91;</span>M<span style="color: #F78811;">&#91;</span><span style="color: #000080;">_</span><span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#40;</span>ai<span style="color: #000080;">:</span> A ~<span style="color: #000080;">&gt;</span> M, storeEvent<span style="color: #000080;">:</span> E <span style="color: #000080;">=&gt;</span> M<span style="color: #F78811;">&#91;</span>Unit<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#40;</span>
          <span style="color: #0000ff; font-weight: bold;">implicit</span> M<span style="color: #000080;">:</span> Monad<span style="color: #F78811;">&#91;</span>M<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#41;</span><span style="color: #000080;">:</span> M<span style="color: #F78811;">&#91;</span>R<span style="color: #F78811;">&#93;</span> <span style="color: #000080;">=</span> <span style="color: #000080;">???</span>
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">implicit class ESHandled[E, A[_], R](es: ES[Handled[E], A, R]) {
       def run[M[_]](ai: A ~&gt; M, storeEvent: E =&gt; M[Unit])(
          implicit M: Monad[M]): M[R] = ???
    }</p></div>
    ";i:4;s:4923:"
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
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">case</span> <span style="color: #0000ff; font-weight: bold;">class</span> User<span style="color: #F78811;">&#40;</span>id<span style="color: #000080;">:</span> Long, email<span style="color: #000080;">:</span> String, password<span style="color: #000080;">:</span> String<span style="color: #F78811;">&#41;</span>
    <span style="color: #0000ff; font-weight: bold;">case</span> <span style="color: #0000ff; font-weight: bold;">class</span> ApiKey<span style="color: #F78811;">&#40;</span>userId<span style="color: #000080;">:</span> Long, key<span style="color: #000080;">:</span> String<span style="color: #F78811;">&#41;</span>
    &nbsp;
    <span style="color: #0000ff; font-weight: bold;">sealed</span> <span style="color: #0000ff; font-weight: bold;">trait</span> Action<span style="color: #F78811;">&#91;</span>R<span style="color: #F78811;">&#93;</span>
    <span style="color: #0000ff; font-weight: bold;">case</span> <span style="color: #0000ff; font-weight: bold;">class</span> FindUserByEmail<span style="color: #F78811;">&#40;</span>email<span style="color: #000080;">:</span> String<span style="color: #F78811;">&#41;</span> <span style="color: #0000ff; font-weight: bold;">extends</span> Action<span style="color: #F78811;">&#91;</span>Option<span style="color: #F78811;">&#91;</span>User<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#93;</span>
    <span style="color: #0000ff; font-weight: bold;">case</span> <span style="color: #0000ff; font-weight: bold;">class</span> WriteUser<span style="color: #F78811;">&#40;</span>u<span style="color: #000080;">:</span> User<span style="color: #F78811;">&#41;</span> <span style="color: #0000ff; font-weight: bold;">extends</span> Action<span style="color: #F78811;">&#91;</span>Unit<span style="color: #F78811;">&#93;</span>
    <span style="color: #0000ff; font-weight: bold;">case</span> <span style="color: #0000ff; font-weight: bold;">class</span> FindApiKeyByUserId<span style="color: #F78811;">&#40;</span>userId<span style="color: #000080;">:</span> Long<span style="color: #F78811;">&#41;</span> <span style="color: #0000ff; font-weight: bold;">extends</span> Action<span style="color: #F78811;">&#91;</span>Option<span style="color: #F78811;">&#91;</span>ApiKey<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#93;</span>
    <span style="color: #0000ff; font-weight: bold;">case</span> <span style="color: #0000ff; font-weight: bold;">class</span> WriteApiKey<span style="color: #F78811;">&#40;</span>ak<span style="color: #000080;">:</span> ApiKey<span style="color: #F78811;">&#41;</span> <span style="color: #0000ff; font-weight: bold;">extends</span> Action<span style="color: #F78811;">&#91;</span>Unit<span style="color: #F78811;">&#93;</span>
    <span style="color: #0000ff; font-weight: bold;">case</span> <span style="color: #0000ff; font-weight: bold;">class</span> SendEmail<span style="color: #F78811;">&#40;</span>to<span style="color: #000080;">:</span> String, body<span style="color: #000080;">:</span> String<span style="color: #F78811;">&#41;</span> <span style="color: #0000ff; font-weight: bold;">extends</span> Action<span style="color: #F78811;">&#91;</span>Unit<span style="color: #F78811;">&#93;</span>
    &nbsp;
    <span style="color: #0000ff; font-weight: bold;">sealed</span> <span style="color: #0000ff; font-weight: bold;">trait</span> Event
    <span style="color: #0000ff; font-weight: bold;">case</span> <span style="color: #0000ff; font-weight: bold;">class</span> UserRegistered<span style="color: #F78811;">&#40;</span>u<span style="color: #000080;">:</span> User<span style="color: #F78811;">&#41;</span> <span style="color: #0000ff; font-weight: bold;">extends</span> Event
    <span style="color: #0000ff; font-weight: bold;">case</span> <span style="color: #0000ff; font-weight: bold;">class</span> ApiKeyCreated<span style="color: #F78811;">&#40;</span>ak<span style="color: #000080;">:</span> ApiKey<span style="color: #F78811;">&#41;</span> <span style="color: #0000ff; font-weight: bold;">extends</span> Event</pre></td></tr></table><p class="theCode" style="display:none;">case class User(id: Long, email: String, password: String)
    case class ApiKey(userId: Long, key: String)
    
    sealed trait Action[R]
    case class FindUserByEmail(email: String) extends Action[Option[User]]
    case class WriteUser(u: User) extends Action[Unit]
    case class FindApiKeyByUserId(userId: Long) extends Action[Option[ApiKey]]
    case class WriteApiKey(ak: ApiKey) extends Action[Unit]
    case class SendEmail(to: String, body: String) extends Action[Unit]
    
    sealed trait Event
    case class UserRegistered(u: User) extends Event
    case class ApiKeyCreated(ak: ApiKey) extends Event</p></div>
    ";i:5;s:3223:"
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
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">def</span> registerUserCommand<span style="color: #F78811;">&#40;</span>
       email<span style="color: #000080;">:</span> String, password<span style="color: #000080;">:</span> String<span style="color: #F78811;">&#41;</span><span style="color: #000080;">:</span> ES<span style="color: #F78811;">&#91;</span>Event, Action, Either<span style="color: #F78811;">&#91;</span>String, Unit<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#93;</span> <span style="color: #000080;">=</span> <span style="color: #F78811;">&#123;</span>
    &nbsp;
       action<span style="color: #F78811;">&#40;</span>FindUserByEmail<span style="color: #F78811;">&#40;</span>email<span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span>.<span style="color: #000000;">flatMap</span> <span style="color: #F78811;">&#123;</span>
          <span style="color: #0000ff; font-weight: bold;">case</span> None <span style="color: #000080;">=&gt;</span>
             emit<span style="color: #F78811;">&#40;</span>UserRegistered<span style="color: #F78811;">&#40;</span>User<span style="color: #F78811;">&#40;</span><span style="color: #0000ff; font-weight: bold;">new</span> Random<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>.<span style="color: #000000;">nextInt</span><span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>, email, password<span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span>
                .<span style="color: #000000;">map</span><span style="color: #F78811;">&#40;</span><span style="color: #000080;">_</span> <span style="color: #000080;">=&gt;</span> Right<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span>
    &nbsp;
          <span style="color: #0000ff; font-weight: bold;">case</span> Some<span style="color: #F78811;">&#40;</span>user<span style="color: #F78811;">&#41;</span> <span style="color: #000080;">=&gt;</span>
             pure<span style="color: #F78811;">&#40;</span>Left<span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;User with the given email already exists&quot;</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span>
       <span style="color: #F78811;">&#125;</span>
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">def registerUserCommand(
       email: String, password: String): ES[Event, Action, Either[String, Unit]] = {
    
       action(FindUserByEmail(email)).flatMap {
          case None =&gt;
             emit(UserRegistered(User(new Random().nextInt(), email, password)))
                .map(_ =&gt; Right(()))
    
          case Some(user) =&gt;
             pure(Left(&quot;User with the given email already exists&quot;))
       }
    }</p></div>
    ";i:6;s:3882:"
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
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">val</span> modelUpdate<span style="color: #000080;">:</span> PartialFunction<span style="color: #F78811;">&#91;</span>Event, ES<span style="color: #F78811;">&#91;</span>Nothing, Action, Unit<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#93;</span> <span style="color: #000080;">=</span> <span style="color: #F78811;">&#123;</span>
       <span style="color: #0000ff; font-weight: bold;">case</span> UserRegistered<span style="color: #F78811;">&#40;</span>u<span style="color: #F78811;">&#41;</span> <span style="color: #000080;">=&gt;</span> action<span style="color: #F78811;">&#40;</span>WriteUser<span style="color: #F78811;">&#40;</span>u<span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span>
       <span style="color: #0000ff; font-weight: bold;">case</span> ApiKeyCreated<span style="color: #F78811;">&#40;</span>ak<span style="color: #F78811;">&#41;</span> <span style="color: #000080;">=&gt;</span> action<span style="color: #F78811;">&#40;</span>WriteApiKey<span style="color: #F78811;">&#40;</span>ak<span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span>
    <span style="color: #F78811;">&#125;</span>
    &nbsp;
    <span style="color: #0000ff; font-weight: bold;">val</span> eventListeners<span style="color: #000080;">:</span> PartialFunction<span style="color: #F78811;">&#91;</span>Event, ES<span style="color: #F78811;">&#91;</span>Event, Action, Unit<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#93;</span> <span style="color: #000080;">=</span> <span style="color: #F78811;">&#123;</span>
       <span style="color: #0000ff; font-weight: bold;">case</span> UserRegistered<span style="color: #F78811;">&#40;</span>u<span style="color: #F78811;">&#41;</span> <span style="color: #000080;">=&gt;</span> <span style="color: #0000ff; font-weight: bold;">for</span> <span style="color: #F78811;">&#123;</span>
          <span style="color: #000080;">_</span> <span style="color: #000080;">&lt;</span>- emit<span style="color: #F78811;">&#40;</span>ApiKeyCreated<span style="color: #F78811;">&#40;</span>ApiKey<span style="color: #F78811;">&#40;</span>u.<span style="color: #000000;">id</span>, UUID.<span style="color: #000000;">randomUUID</span><span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>.<span style="color: #000000;">toString</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span>
          <span style="color: #000080;">_</span> <span style="color: #000080;">&lt;</span>- action<span style="color: #F78811;">&#40;</span>SendEmail<span style="color: #F78811;">&#40;</span>u.<span style="color: #000000;">email</span>, <span style="color: #6666FF;">&quot;Welcome!&quot;</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span>
       <span style="color: #F78811;">&#125;</span> <span style="color: #0000ff; font-weight: bold;">yield</span> <span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">val modelUpdate: PartialFunction[Event, ES[Nothing, Action, Unit]] = {
       case UserRegistered(u) =&gt; action(WriteUser(u))
       case ApiKeyCreated(ak) =&gt; action(WriteApiKey(ak))
    }
    
    val eventListeners: PartialFunction[Event, ES[Event, Action, Unit]] = {
       case UserRegistered(u) =&gt; for {
          _ &lt;- emit(ApiKeyCreated(ApiKey(u.id, UUID.randomUUID().toString)))
          _ &lt;- action(SendEmail(u.email, &quot;Welcome!&quot;))
       } yield ()
    }</p></div>
    ";i:7;s:5087:"
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
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">val</span> handledCommand <span style="color: #000080;">=</span> registerUserCommand<span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;adam@example.org&quot;</span>, <span style="color: #6666FF;">&quot;1234&quot;</span><span style="color: #F78811;">&#41;</span>
       .<span style="color: #000000;">handleEvents</span><span style="color: #F78811;">&#40;</span>modelUpdate, eventListeners<span style="color: #F78811;">&#41;</span>
    &nbsp;
    <span style="color: #0000ff; font-weight: bold;">val</span> result<span style="color: #000080;">:</span> Either<span style="color: #F78811;">&#91;</span>String, Unit<span style="color: #F78811;">&#93;</span> <span style="color: #000080;">=</span> handledCommand.<span style="color: #000000;">run</span><span style="color: #F78811;">&#91;</span>Id<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#40;</span><span style="color: #0000ff; font-weight: bold;">new</span> <span style="color: #F78811;">&#40;</span>Action ~<span style="color: #000080;">&gt;</span> Id<span style="color: #F78811;">&#41;</span> <span style="color: #F78811;">&#123;</span>
       <span style="color: #0000ff; font-weight: bold;">override</span> <span style="color: #0000ff; font-weight: bold;">def</span> apply<span style="color: #F78811;">&#91;</span>A<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#40;</span>fa<span style="color: #000080;">:</span> Action<span style="color: #F78811;">&#91;</span>A<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#41;</span> <span style="color: #000080;">=</span> fa <span style="color: #0000ff; font-weight: bold;">match</span> <span style="color: #F78811;">&#123;</span>
          <span style="color: #0000ff; font-weight: bold;">case</span> FindUserByEmail<span style="color: #F78811;">&#40;</span>email<span style="color: #F78811;">&#41;</span> <span style="color: #000080;">=&gt;</span> println<span style="color: #F78811;">&#40;</span>s<span style="color: #6666FF;">&quot;Find user by email: $email&quot;</span><span style="color: #F78811;">&#41;</span><span style="color: #000080;">;</span> None
          <span style="color: #0000ff; font-weight: bold;">case</span> WriteUser<span style="color: #F78811;">&#40;</span>u<span style="color: #F78811;">&#41;</span> <span style="color: #000080;">=&gt;</span> println<span style="color: #F78811;">&#40;</span>s<span style="color: #6666FF;">&quot;Write user $u&quot;</span><span style="color: #F78811;">&#41;</span>
          <span style="color: #0000ff; font-weight: bold;">case</span> FindApiKeyByUserId<span style="color: #F78811;">&#40;</span>id<span style="color: #F78811;">&#41;</span> <span style="color: #000080;">=&gt;</span> println<span style="color: #F78811;">&#40;</span>s<span style="color: #6666FF;">&quot;Find api key by user id: $id&quot;</span><span style="color: #F78811;">&#41;</span><span style="color: #000080;">;</span> None
          <span style="color: #0000ff; font-weight: bold;">case</span> WriteApiKey<span style="color: #F78811;">&#40;</span>ak<span style="color: #F78811;">&#41;</span> <span style="color: #000080;">=&gt;</span> println<span style="color: #F78811;">&#40;</span>s<span style="color: #6666FF;">&quot;Write api key: $ak&quot;</span><span style="color: #F78811;">&#41;</span>
          <span style="color: #0000ff; font-weight: bold;">case</span> SendEmail<span style="color: #F78811;">&#40;</span>to, body<span style="color: #F78811;">&#41;</span> <span style="color: #000080;">=&gt;</span> println<span style="color: #F78811;">&#40;</span>s<span style="color: #6666FF;">&quot;Send email to $to, body: $body&quot;</span><span style="color: #F78811;">&#41;</span>
       <span style="color: #F78811;">&#125;</span>
    <span style="color: #F78811;">&#125;</span>, e <span style="color: #000080;">=&gt;</span> println<span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;Store event: &quot;</span> + e<span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span></pre></td></tr></table><p class="theCode" style="display:none;">val handledCommand = registerUserCommand(&quot;adam@example.org&quot;, &quot;1234&quot;)
       .handleEvents(modelUpdate, eventListeners)
    
    val result: Either[String, Unit] = handledCommand.run[Id](new (Action ~&gt; Id) {
       override def apply[A](fa: Action[A]) = fa match {
          case FindUserByEmail(email) =&gt; println(s&quot;Find user by email: $email&quot;); None
          case WriteUser(u) =&gt; println(s&quot;Write user $u&quot;)
          case FindApiKeyByUserId(id) =&gt; println(s&quot;Find api key by user id: $id&quot;); None
          case WriteApiKey(ak) =&gt; println(s&quot;Write api key: $ak&quot;)
          case SendEmail(to, body) =&gt; println(s&quot;Send email to $to, body: $body&quot;)
       }
    }, e =&gt; println(&quot;Store event: &quot; + e))</p></div>
    ";}
epcl_post:
  - 'a:1:{s:13:"views_counter";i:1;}'
views_counter:
  - 1
categories:
  - event sourcing
  - functional programming
  - scala
  - architecture

---
I recently wrote about [Event Sourcing][1] and [Free Monads][2]. The natural next step is combining the two! How would a free monad adjusted for event sourcing look like?

Please bear in mind that the below is just really a draft, not a complete solution. Hence any suggestions for improvements are very welcome! But, let’s review the basic assumptions. Our application emits events of a certain type `E`. This will typically be a hierarchy of case classes. As we want to be free from any specific interpretation of side-effects, we will use data of type `A[_]` to describe all actions that can happen in the system (again usually using case classes). Actions include reading and writing the model, sending e-mails, indexing data in search subsystems etc. Every action returns some result; writes typically result in `Unit`, reads in an `Option[SomeData]`.

The events record what **happened** in the system and drive all of the “business logic&#8221;. All events are stored, however we don’t specify upfront how; the stream of events forms the the primary “source of truth”.

There are two main functions that will interpret the events:

  * **model update**: as the name suggests, basing on the event, updates the model. For example, for a `UserRegistered` event, this should write the user to a database, or store it in an in-memory storage. _Cannot_ emit new events, only perform actions.
  * **event listeners**: run some logic basing on events. For example, for a `UserRegistered` event, this could trigger an action sending an email. Here we can emit new events, like creating some initial data for a new user (in our example, this will be an api key).

The main idea behind this distinction is that given a list of events, model update can be used to rebuild the model. We can do this multiple times; for example we could store the events in a persistent storage, and the model in-memory. We could then use the model update function to re-create a model on a new node. Or, we can run the model update functions to create a new view of the data.

On the other hand, event listeners should be run only once, and they should perform the main “business logic” for an event. Sending a welcome email should be done only once, even if the event is replayed to re-create the model. Here we can also emit new events, and they will be processed recursively using the two functions.

The top-level entry points are **commands**, parametrised with user-provided data (e.g. from a web form or REST endpoint), which basing on the model validate the input emit some events and return a value, which is then returned to the user.

To describe programs which can emit events, perform actions and where events can be handled using the model update & event listener functions, we will use a data type `ES[E, A, R]`.

> `ES[E, A, R]` is a description of a program which emits events of type `E`, contains actions of type `A` and produces a result of type `R`. 

The three components described above should have the following signatures:

  * command: `AnyUserData => ES[E, A, R]`
  * model update: `PartialFunction[E, ES[Nothing, A, Unit]]` (cannot emit events; partial, as we don’t require model updates for every event)
  * event listener: `PartialFunction[E, ES[E, A, Unit]]`

# What is `ES`?

`ES` is an extension of the free monad over `A`, which in addition to the normal constructors (`FlatMap`, `Pure` and `Suspend` &#8211; used for actions), contains an additional `Emit` constructor (its meaning shouldn’t be a surprise). Very similarly to the free monad, we can define a method to interpret `ES` using any monad, given an interpretation for actions and events (`foldMap` function).

Here’s the basic structure of `ES`:

<pre lang="scala" line="1">sealed trait ES[E, A[_], R] {
    def flatMap[R2](f: R => ES[E, A, R2]): ES[E, A, R2] = FlatMap(this, f)
    def map[R2](f: R => R2): ES[E, A, R2] = FlatMap(this, f andThen (x => Pure(x)))
    ...
}

case class Pure[E, A[_], R](r: R) extends ES[E, A, R] { … }
case class Emit[E, A[_]](e: E) extends ES[E, A, Unit] { … }
case class Suspend[E, A[_], R](a: A[R]) extends ES[E, A, R] { … }
case class FlatMap[E, A[_], R1, R2](
   c: ES[E, A, R1], 
   f: R1 => ES[E, A, R2]) extends ES[E, A, R2] { … }

object ES {
    implicit def esMonad[E, A[_]]: Monad[({type x[X] = ES[E, A, X]})#x] =
       new Monad[({type x[X] = ES[E, A, X]})#x] {
          override def pure[X](x: X) = Pure(x)
          override def flatMap[X, Y](fa: ES[E, A, X])(f: X => ES[E, A, Y]) = 
             fa.flatMap(f)
       }

    def pure[E, A[_], R](r: R): ES[E, A, R] = Pure(r)
    def done[E, A[_]]: ES[E, A, Unit] = pure(())
    def emit[E, A[_]](e: E): ES[E, A, Unit] = Emit(e)
    def suspend[E, A[_], R](a: A[R]): ES[E, A, R] = Suspend(a)
  }
</pre>

# Event-sourcing `ES` specifics

In addition to normal monad operations, we need two operations on the `ES` data type. Firstly, we need a way to provide the model update & event listener functions, which should be applied to the description of the program. As a result, we want to get a program where all of the events are handled, and which contains appropriate actions (for each event, first the actions described by the model update, and then the actions described by the event listener).

Hence `ES` contains a function:

<pre lang="scala" line="1">sealed trait ES[E, A[_], R] {
   def handleEvents(
      modelUpdate: PartialFunction[E, ES[Nothing, A, Unit]],
      eventListener: PartialFunction[E, ES[E, A, Unit]]): ES[Handled[E], A, R]
}
</pre>

Note that in the return type, events are wrapped in an `case class Handled[E](e: E)` wrapper. That’s to make sure that we can’t simply call `handleEvents` twice and re-interpret the events. And we need to retain the events in the description of the program to be able to store them when we do the final interpretation.

Which brings us to the interpretation function. Given a description of a program with the events handled as described by our model update/event listener functions, we want to interpret it in any monad, hence we get the function:

<pre lang="scala" line="1">implicit class ESHandled[E, A[_], R](es: ES[Handled[E], A, R]) {
   def run[M[_]](ai: A ~> M, storeEvent: E => M[Unit])(
      implicit M: Monad[M]): M[R] = ???
}
</pre>

To do the interpretation, we need both an interpretation of the actions (the `A ~> M` natural transformation) and a way to store the events. Given a program `ES[E, A, R]` and a monad `M`, we get back the result: `M[R]` with all events handled, stored and actions interpreted.

# Example usage

How would a simple usage example look like? We will describe a program where users can register using a unique email, and for each new user an api key is created. Here’s the data, actions and events that we will use:

<pre lang="scala" line="1">case class User(id: Long, email: String, password: String)
case class ApiKey(userId: Long, key: String)

sealed trait Action[R]
case class FindUserByEmail(email: String) extends Action[Option[User]]
case class WriteUser(u: User) extends Action[Unit]
case class FindApiKeyByUserId(userId: Long) extends Action[Option[ApiKey]]
case class WriteApiKey(ak: ApiKey) extends Action[Unit]
case class SendEmail(to: String, body: String) extends Action[Unit]

sealed trait Event
case class UserRegistered(u: User) extends Event
case class ApiKeyCreated(ak: ApiKey) extends Event
</pre>

The entry point will be a command to register users:

<pre lang="scala" line="1">def registerUserCommand(
   email: String, password: String): ES[Event, Action, Either[String, Unit]] = {

   action(FindUserByEmail(email)).flatMap {
      case None =>
         emit(UserRegistered(User(new Random().nextInt(), email, password)))
            .map(_ => Right(()))

      case Some(user) =>
         pure(Left("User with the given email already exists"))
   }
}
</pre>

Note that we are using concrete types (`Event`, `Action`) as the type parameters for `ES`. The result of the command can be either an error message (represented by the left side of the either), or success (`Right(())`). In the command we perform actions (looking user up by email) and emit events (user registered) if validation succeeds.

We also need the model update/event listener functions to intepret the events:

<pre lang="scala" line="1">val modelUpdate: PartialFunction[Event, ES[Nothing, Action, Unit]] = {
   case UserRegistered(u) => action(WriteUser(u))
   case ApiKeyCreated(ak) => action(WriteApiKey(ak))
}

val eventListeners: PartialFunction[Event, ES[Event, Action, Unit]] = {
   case UserRegistered(u) => for {
      _ &lt;- emit(ApiKeyCreated(ApiKey(u.id, UUID.randomUUID().toString)))
      _ &lt;- action(SendEmail(u.email, "Welcome!"))
   } yield ()
}
</pre>

There are no event listeners for `ApiKeyCreated`, and for `UserRegistered` we emit another event and perform an action.

Given user input, we can handle the result of the command and interpret it in the `Id` monad:

<pre lang="scala" line="1">val handledCommand = registerUserCommand("adam@example.org", "1234")
   .handleEvents(modelUpdate, eventListeners)

val result: Either[String, Unit] = handledCommand.run[Id](new (Action ~> Id) {
   override def apply[A](fa: Action[A]) = fa match {
      case FindUserByEmail(email) => println(s"Find user by email: $email"); None
      case WriteUser(u) => println(s"Write user $u")
      case FindApiKeyByUserId(id) => println(s"Find api key by user id: $id"); None
      case WriteApiKey(ak) => println(s"Write api key: $ak")
      case SendEmail(to, body) => println(s"Send email to $to, body: $body")
   }
}, e => println("Store event: " + e))
</pre>

`Id` is great for testing, in real-life you would interpret the actions using e.g. `Future` or `Task` and write the results to a database. When executed, you would see a trail of actions being performed by the program.

# Supporting code

The code for `ES` is mainly an expansion of the free monad interpretation. The full (quite short) source is [available in a gist][3], alongside with the example above. It uses [Cats][4] for the monad and natural transformation abstractions.

# Summing up

To make this usable in a real-life scenario, a couple of features are missing. First of all, we would need to add support for combining a number of different types of events/actions. Secondly, we would need to provide a convenient way to enrich events, to capture e.g. the timestamp of an event, some wider context (user’s ip/id) etc.

 [1]: https://softwaremill.com/entry-level-event-sourcing/
 [2]: https://softwaremill.com/free-monads/
 [3]: https://gist.github.com/adamw/5ce8b18cc28865cdde69
 [4]: https://github.com/non/cats
