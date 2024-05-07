---
title: Clustering reactmq with akka-cluster
author: Adam Warski
type: blog
date: 2014-11-04T13:05:42+00:00
url: /blog/2014/11/clustering-reactmq-with-akka-cluster/
simplecatch-sidebarlayout:
  - default
dsq_thread_id:
  - 3190539382
wp-syntax-cache-content:
  - |
    a:10:{i:1;s:2444:"
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
    </pre></td><td class="code"><pre class="bash" style="font-family:monospace;">akka <span style="color: #7a0874; font-weight: bold;">&#123;</span>
      actor.provider = <span style="color: #ff0000;">&quot;akka.cluster.ClusterActorRefProvider&quot;</span>
    &nbsp;
      remote.netty.tcp.port = <span style="color: #000000;">0</span> <span style="color: #000000; font-weight: bold;">//</span> should be overriden
      remote.netty.tcp.hostname = <span style="color: #ff0000;">&quot;127.0.0.1&quot;</span>
    &nbsp;
      cluster <span style="color: #7a0874; font-weight: bold;">&#123;</span>
        seed-nodes = <span style="color: #7a0874; font-weight: bold;">&#91;</span>
          <span style="color: #ff0000;">&quot;akka.tcp://broker@127.0.0.1:9171&quot;</span>,
          <span style="color: #ff0000;">&quot;akka.tcp://broker@127.0.0.1:9172&quot;</span>,
          <span style="color: #ff0000;">&quot;akka.tcp://broker@127.0.0.1:9173&quot;</span>
        <span style="color: #7a0874; font-weight: bold;">&#93;</span>
        auto-down-unreachable-after = 10s
        roles = <span style="color: #7a0874; font-weight: bold;">&#91;</span> <span style="color: #ff0000;">&quot;broker&quot;</span> <span style="color: #7a0874; font-weight: bold;">&#93;</span>
        role.broker.min-nr-of-members = <span style="color: #000000;">2</span>
      <span style="color: #7a0874; font-weight: bold;">&#125;</span>
    &nbsp;
      extensions = <span style="color: #7a0874; font-weight: bold;">&#91;</span> <span style="color: #ff0000;">&quot;akka.contrib.pattern.ClusterReceptionistExtension&quot;</span> <span style="color: #7a0874; font-weight: bold;">&#93;</span>
    <span style="color: #7a0874; font-weight: bold;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">akka {
      actor.provider = &quot;akka.cluster.ClusterActorRefProvider&quot;
    
      remote.netty.tcp.port = 0 // should be overriden
      remote.netty.tcp.hostname = &quot;127.0.0.1&quot;
    
      cluster {
        seed-nodes = [
          &quot;akka.tcp://broker@127.0.0.1:9171&quot;,
          &quot;akka.tcp://broker@127.0.0.1:9172&quot;,
          &quot;akka.tcp://broker@127.0.0.1:9173&quot;
        ]
        auto-down-unreachable-after = 10s
        roles = [ &quot;broker&quot; ]
        role.broker.min-nr-of-members = 2
      }
    
      extensions = [ &quot;akka.contrib.pattern.ClusterReceptionistExtension&quot; ]
    }</p></div>
    ";i:2;s:1969:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    6
    7
    8
    9
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">class</span> BrokerManager<span style="color: #F78811;">&#40;</span>clusterPort<span style="color: #000080;">:</span> Int<span style="color: #F78811;">&#41;</span> <span style="color: #F78811;">&#123;</span>
       <span style="color: #008000; font-style: italic;">// …</span>
       <span style="color: #0000ff; font-weight: bold;">val</span> conf <span style="color: #000080;">=</span> ConfigFactory
          .<span style="color: #000000;">parseString</span><span style="color: #F78811;">&#40;</span>s<span style="color: #6666FF;">&quot;akka.remote.netty.tcp.port=$clusterPort&quot;</span><span style="color: #F78811;">&#41;</span>
          .<span style="color: #000000;">withFallback</span><span style="color: #F78811;">&#40;</span>ConfigFactory.<span style="color: #000000;">load</span><span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;cluster-broker-template&quot;</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span>
    &nbsp;
       <span style="color: #0000ff; font-weight: bold;">val</span> system <span style="color: #000080;">=</span> ActorSystem<span style="color: #F78811;">&#40;</span>s<span style="color: #6666FF;">&quot;broker&quot;</span>, conf<span style="color: #F78811;">&#41;</span>
       <span style="color: #008000; font-style: italic;">// ...</span>
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">class BrokerManager(clusterPort: Int) {
       // …
       val conf = ConfigFactory
          .parseString(s&quot;akka.remote.netty.tcp.port=$clusterPort&quot;)
          .withFallback(ConfigFactory.load(&quot;cluster-broker-template&quot;))
    
       val system = ActorSystem(s&quot;broker&quot;, conf)
       // ...
    }</p></div>
    ";i:3;s:884:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">object</span> ClusteredBroker1 <span style="color: #0000ff; font-weight: bold;">extends</span> App <span style="color: #F78811;">&#123;</span>
      <span style="color: #0000ff; font-weight: bold;">new</span> BrokerManager<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">9171</span><span style="color: #F78811;">&#41;</span>.<span style="color: #000000;">run</span><span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">object ClusteredBroker1 extends App {
      new BrokerManager(9171).run()
    }</p></div>
    ";i:4;s:5038:"
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
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">def</span> run<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span> <span style="color: #F78811;">&#123;</span>
       <span style="color: #008000; font-style: italic;">// …</span>
       system.<span style="color: #000000;">actorOf</span><span style="color: #F78811;">&#40;</span>ClusterSingletonManager.<span style="color: #000000;">props</span><span style="color: #F78811;">&#40;</span>
          singletonProps <span style="color: #000080;">=</span> Props<span style="color: #F78811;">&#40;</span>classOf<span style="color: #F78811;">&#91;</span>BrokerManagerActor<span style="color: #F78811;">&#93;</span>, clusterPort<span style="color: #F78811;">&#41;</span>,
          singletonName <span style="color: #000080;">=</span> <span style="color: #6666FF;">&quot;broker&quot;</span>,
          terminationMessage <span style="color: #000080;">=</span> PoisonPill,
          role <span style="color: #000080;">=</span> Some<span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;broker&quot;</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span>,
          name <span style="color: #000080;">=</span> <span style="color: #6666FF;">&quot;broker-manager&quot;</span><span style="color: #F78811;">&#41;</span>
    <span style="color: #F78811;">&#125;</span>
    &nbsp;
    <span style="color: #0000ff; font-weight: bold;">class</span> BrokerManagerActor<span style="color: #F78811;">&#40;</span>clusterPort<span style="color: #000080;">:</span> Int<span style="color: #F78811;">&#41;</span> <span style="color: #0000ff; font-weight: bold;">extends</span> Actor <span style="color: #F78811;">&#123;</span>
      <span style="color: #0000ff; font-weight: bold;">val</span> sendServerAddress     <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> InetSocketAddress<span style="color: #F78811;">&#40;</span>
        <span style="color: #6666FF;">&quot;localhost&quot;</span>, clusterPort + <span style="color: #F78811;">10</span><span style="color: #F78811;">&#41;</span>
      <span style="color: #0000ff; font-weight: bold;">val</span> receiveServerAddress  <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> InetSocketAddress<span style="color: #F78811;">&#40;</span>
        <span style="color: #6666FF;">&quot;localhost&quot;</span>, clusterPort + <span style="color: #F78811;">20</span><span style="color: #F78811;">&#41;</span>
    &nbsp;
      <span style="color: #0000ff; font-weight: bold;">override</span> <span style="color: #0000ff; font-weight: bold;">def</span> preStart<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span> <span style="color: #000080;">=</span> <span style="color: #F78811;">&#123;</span>
        <span style="color: #0000ff; font-weight: bold;">super</span>.<span style="color: #000000;">preStart</span><span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>
        <span style="color: #0000ff; font-weight: bold;">new</span> Broker<span style="color: #F78811;">&#40;</span>sendServerAddress, receiveServerAddress<span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#40;</span>context.<span style="color: #000000;">system</span><span style="color: #F78811;">&#41;</span>
          .<span style="color: #000000;">run</span><span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>
      <span style="color: #F78811;">&#125;</span>
    &nbsp;
      <span style="color: #0000ff; font-weight: bold;">override</span> <span style="color: #0000ff; font-weight: bold;">def</span> receive <span style="color: #000080;">=</span> <span style="color: #F78811;">&#123;</span> <span style="color: #0000ff; font-weight: bold;">case</span> <span style="color: #000080;">_</span> <span style="color: #000080;">=&gt;</span> <span style="color: #F78811;">&#125;</span> 
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">def run() {
       // …
       system.actorOf(ClusterSingletonManager.props(
          singletonProps = Props(classOf[BrokerManagerActor], clusterPort),
          singletonName = &quot;broker&quot;,
          terminationMessage = PoisonPill,
          role = Some(&quot;broker&quot;)),
          name = &quot;broker-manager&quot;)
    }
    
    class BrokerManagerActor(clusterPort: Int) extends Actor {
      val sendServerAddress     = new InetSocketAddress(
        &quot;localhost&quot;, clusterPort + 10)
      val receiveServerAddress  = new InetSocketAddress(
        &quot;localhost&quot;, clusterPort + 20)
    
      override def preStart() = {
        super.preStart()
        new Broker(sendServerAddress, receiveServerAddress)(context.system)
          .run()
      }
    
      override def receive = { case _ =&gt; } 
    }</p></div>
    ";i:5;s:2295:"
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
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">case</span> <span style="color: #0000ff; font-weight: bold;">object</span> GetBrokerAddresses
    <span style="color: #0000ff; font-weight: bold;">case</span> <span style="color: #0000ff; font-weight: bold;">class</span> BrokerAddresses<span style="color: #F78811;">&#40;</span>sendServerAddress<span style="color: #000080;">:</span> InetSocketAddress, 
       receiveServerAddress<span style="color: #000080;">:</span> InetSocketAddress<span style="color: #F78811;">&#41;</span>
    &nbsp;
    <span style="color: #0000ff; font-weight: bold;">class</span> BrokerManagerActor<span style="color: #F78811;">&#40;</span>clusterPort<span style="color: #000080;">:</span> Int<span style="color: #F78811;">&#41;</span> <span style="color: #0000ff; font-weight: bold;">extends</span> Actor <span style="color: #F78811;">&#123;</span>
       <span style="color: #008000; font-style: italic;">// as above, plus:</span>
       <span style="color: #0000ff; font-weight: bold;">override</span> <span style="color: #0000ff; font-weight: bold;">def</span> receive <span style="color: #000080;">=</span> <span style="color: #F78811;">&#123;</span>
          <span style="color: #0000ff; font-weight: bold;">case</span> GetBrokerAddresses <span style="color: #000080;">=&gt;</span> sender<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span> <span style="color: #000080;">!</span> BrokerAddresses<span style="color: #F78811;">&#40;</span>
             sendServerAddress, receiveServerAddress<span style="color: #F78811;">&#41;</span>
      <span style="color: #F78811;">&#125;</span>
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">case object GetBrokerAddresses
    case class BrokerAddresses(sendServerAddress: InetSocketAddress, 
       receiveServerAddress: InetSocketAddress)
    
    class BrokerManagerActor(clusterPort: Int) extends Actor {
       // as above, plus:
       override def receive = {
          case GetBrokerAddresses =&gt; sender() ! BrokerAddresses(
             sendServerAddress, receiveServerAddress)
      }
    }</p></div>
    ";i:6;s:1648:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    6
    7
    8
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">class</span> BrokerManagerActor<span style="color: #F78811;">&#40;</span>clusterPort<span style="color: #000080;">:</span> Int<span style="color: #F78811;">&#41;</span> <span style="color: #0000ff; font-weight: bold;">extends</span> Actor <span style="color: #F78811;">&#123;</span>
       <span style="color: #008000; font-style: italic;">// …</span>
       <span style="color: #0000ff; font-weight: bold;">override</span> <span style="color: #0000ff; font-weight: bold;">def</span> preStart<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span> <span style="color: #000080;">=</span> <span style="color: #F78811;">&#123;</span>
          <span style="color: #008000; font-style: italic;">// ...</span>
          ClusterReceptionistExtension<span style="color: #F78811;">&#40;</span>context.<span style="color: #000000;">system</span><span style="color: #F78811;">&#41;</span>
             .<span style="color: #000000;">registerService</span><span style="color: #F78811;">&#40;</span>self<span style="color: #F78811;">&#41;</span>
       <span style="color: #F78811;">&#125;</span>
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">class BrokerManagerActor(clusterPort: Int) extends Actor {
       // …
       override def preStart() = {
          // ...
          ClusterReceptionistExtension(context.system)
             .registerService(self)
       }
    }</p></div>
    ";i:7;s:1434:"
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
    </pre></td><td class="code"><pre class="bash" style="font-family:monospace;">akka <span style="color: #7a0874; font-weight: bold;">&#123;</span>
      actor.provider = <span style="color: #ff0000;">&quot;akka.remote.RemoteActorRefProvider&quot;</span>
    &nbsp;
      remote.netty.tcp.port = <span style="color: #000000;">0</span>
      remote.netty.tcp.hostname = <span style="color: #ff0000;">&quot;127.0.0.1&quot;</span>
    <span style="color: #7a0874; font-weight: bold;">&#125;</span>
    &nbsp;
    cluster.client.initial-contact-points = <span style="color: #7a0874; font-weight: bold;">&#91;</span>
      <span style="color: #ff0000;">&quot;akka.tcp://broker@127.0.0.1:9171&quot;</span>,
      <span style="color: #ff0000;">&quot;akka.tcp://broker@127.0.0.1:9172&quot;</span>,
      <span style="color: #ff0000;">&quot;akka.tcp://broker@127.0.0.1:9173&quot;</span>
    <span style="color: #7a0874; font-weight: bold;">&#93;</span></pre></td></tr></table><p class="theCode" style="display:none;">akka {
      actor.provider = &quot;akka.remote.RemoteActorRefProvider&quot;
    
      remote.netty.tcp.port = 0
      remote.netty.tcp.hostname = &quot;127.0.0.1&quot;
    }
    
    cluster.client.initial-contact-points = [
      &quot;akka.tcp://broker@127.0.0.1:9171&quot;,
      &quot;akka.tcp://broker@127.0.0.1:9172&quot;,
      &quot;akka.tcp://broker@127.0.0.1:9173&quot;
    ]</p></div>
    ";i:8;s:2966:"
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
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">val</span> conf <span style="color: #000080;">=</span> ConfigFactory.<span style="color: #000000;">load</span><span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;cluster-client&quot;</span><span style="color: #F78811;">&#41;</span>
    <span style="color: #0000ff; font-weight: bold;">implicit</span> <span style="color: #0000ff; font-weight: bold;">val</span> system <span style="color: #000080;">=</span> ActorSystem<span style="color: #F78811;">&#40;</span>name, conf<span style="color: #F78811;">&#41;</span>
    &nbsp;
    <span style="color: #0000ff; font-weight: bold;">val</span> initialContacts <span style="color: #000080;">=</span> conf
      .<span style="color: #000000;">getStringList</span><span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;cluster.client.initial-contact-points&quot;</span><span style="color: #F78811;">&#41;</span>
      .<span style="color: #000000;">asScala</span>.<span style="color: #000000;">map</span> <span style="color: #F78811;">&#123;</span>
        <span style="color: #0000ff; font-weight: bold;">case</span> AddressFromURIString<span style="color: #F78811;">&#40;</span>addr<span style="color: #F78811;">&#41;</span> <span style="color: #000080;">=&gt;</span> system.<span style="color: #000000;">actorSelection</span><span style="color: #F78811;">&#40;</span>
          RootActorPath<span style="color: #F78811;">&#40;</span>addr<span style="color: #F78811;">&#41;</span> / <span style="color: #6666FF;">&quot;user&quot;</span> / <span style="color: #6666FF;">&quot;receptionist&quot;</span><span style="color: #F78811;">&#41;</span>
      <span style="color: #F78811;">&#125;</span>.<span style="color: #000000;">toSet</span>
    &nbsp;
    <span style="color: #0000ff; font-weight: bold;">val</span> clusterClient <span style="color: #000080;">=</span> system.<span style="color: #000000;">actorOf</span><span style="color: #F78811;">&#40;</span>
      ClusterClient.<span style="color: #000000;">props</span><span style="color: #F78811;">&#40;</span>initialContacts<span style="color: #F78811;">&#41;</span>, <span style="color: #6666FF;">&quot;cluster-client&quot;</span><span style="color: #F78811;">&#41;</span></pre></td></tr></table><p class="theCode" style="display:none;">val conf = ConfigFactory.load(&quot;cluster-client&quot;)
    implicit val system = ActorSystem(name, conf)
    
    val initialContacts = conf
      .getStringList(&quot;cluster.client.initial-contact-points&quot;)
      .asScala.map {
        case AddressFromURIString(addr) =&gt; system.actorSelection(
          RootActorPath(addr) / &quot;user&quot; / &quot;receptionist&quot;)
      }.toSet
     
    val clusterClient = system.actorOf(
      ClusterClient.props(initialContacts), &quot;cluster-client&quot;)</p></div>
    ";i:9;s:1675:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    6
    7
    8
    9
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;">clusterClient <span style="color: #000080;">?</span> ClusterClient.<span style="color: #000000;">Send</span><span style="color: #F78811;">&#40;</span>
       <span style="color: #6666FF;">&quot;/user/broker-manager/broker&quot;</span>, 
       GetBrokerAddresses, 
       localAffinity <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">false</span><span style="color: #F78811;">&#41;</span>
          .<span style="color: #000000;">mapTo</span><span style="color: #F78811;">&#91;</span>BrokerAddresses<span style="color: #F78811;">&#93;</span>
          .<span style="color: #000000;">flatMap</span> <span style="color: #F78811;">&#123;</span> ba <span style="color: #000080;">=&gt;</span>
             logger.<span style="color: #000000;">info</span><span style="color: #F78811;">&#40;</span>s<span style="color: #6666FF;">&quot;Connecting a $name using broker address $ba.&quot;</span><span style="color: #F78811;">&#41;</span>
             runClient<span style="color: #F78811;">&#40;</span>ba, system<span style="color: #F78811;">&#41;</span>
          <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">clusterClient ? ClusterClient.Send(
       &quot;/user/broker-manager/broker&quot;, 
       GetBrokerAddresses, 
       localAffinity = false)
          .mapTo[BrokerAddresses]
          .flatMap { ba =&gt;
             logger.info(s&quot;Connecting a $name using broker address $ba.&quot;)
             runClient(ba, system)
          }</p></div>
    ";i:10;s:1462:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">object</span> ClusterReceiver <span style="color: #0000ff; font-weight: bold;">extends</span> App <span style="color: #0000ff; font-weight: bold;">with</span> ClusterClientSupport <span style="color: #F78811;">&#123;</span>
      start<span style="color: #F78811;">&#40;</span><span style="color: #6666FF;">&quot;receiver&quot;</span>, <span style="color: #F78811;">&#40;</span>ba, system<span style="color: #F78811;">&#41;</span> <span style="color: #000080;">=&gt;</span> 
        <span style="color: #0000ff; font-weight: bold;">new</span> Receiver<span style="color: #F78811;">&#40;</span>ba.<span style="color: #000000;">receiveServerAddress</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#40;</span>system<span style="color: #F78811;">&#41;</span>.<span style="color: #000000;">run</span><span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span>
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">object ClusterReceiver extends App with ClusterClientSupport {
      start(&quot;receiver&quot;, (ba, system) =&gt; 
        new Receiver(ba.receiveServerAddress)(system).run())
    }</p></div>
    ";}
tags:
  - akka
  - distributed
  - scala
  - messaging

---
In the last two posts on [reactmq][1], I described how to write a [reactive][2], [persistent][3] message queue. The queue has the following characteristics:

  * there is a single broker storing the messages, with potentially multiple clients, either sending or receiving messages
  * provides at-least-once-delivery; receiving a message blocks the message; unless it is deleted within some time it will be available for delivery again
  * sending & receiving messages is reactive thanks to [akka-streams][4]: back-pressure is applied if any of the components cannot keep up with the load
  * messages are persistent, using [akka-persistence][5]: all send/receive/delete events are persisted in a journal, and when the system is restarted the state of the queue actor is re-created

The event store can be replicated by using a replicated journal implementation, e.g. [Cassandra][6]. However, there is one piece missing: clustering the broker itself.

Luckily, [akka-cluster][7] is there to help! Let’s see how using it we can make sure that there’s always a broker running in our cluster.

<a href="http://www.warski.org/blog/2014/11/clustering-reactmq-with-akka-cluster/reactmq-cluster-2/" rel="attachment wp-att-1473"><img loading="lazy" decoding="async" src="http://www.warski.org/blog/wp-content/uploads/2014/11/reactmq-cluster1-1024x1024.png" alt="reactmq cluster" width="978" height="978" class="aligncenter size-large wp-image-1473" srcset="https://www.warski.org/blog/wp-content/uploads/2014/11/reactmq-cluster1-1024x1024.png 1024w, https://www.warski.org/blog/wp-content/uploads/2014/11/reactmq-cluster1-255x255.png 255w, https://www.warski.org/blog/wp-content/uploads/2014/11/reactmq-cluster1-300x300.png 300w, https://www.warski.org/blog/wp-content/uploads/2014/11/reactmq-cluster1-210x210.png 210w, https://www.warski.org/blog/wp-content/uploads/2014/11/reactmq-cluster1.png 1608w" sizes="(max-width: 978px) 100vw, 978px" /></a>

# Cluster setup

First of all we need a working cluster. We will need two new dependencies in our [`build.sbt` file][8], `akka-cluster` and `akka-contrib`, as we will be using some contributed extensions.

Secondly, we need to provide configuration for the `ActorSystem` with clustering enabled. It’s good to make it clear what’s the purpose of a config file, hence there&#8217;s the [`cluster-broker-template.conf` file][9]:
```bash
akka {
  actor.provider = "akka.cluster.ClusterActorRefProvider"

  remote.netty.tcp.port = 0 // should be overriden
  remote.netty.tcp.hostname = "127.0.0.1"

  cluster {
    seed-nodes = [
      "akka.tcp://broker@127.0.0.1:9171",
      "akka.tcp://broker@127.0.0.1:9172",
      "akka.tcp://broker@127.0.0.1:9173"
    ]
    auto-down-unreachable-after = 10s
    roles = [ "broker" ]
    role.broker.min-nr-of-members = 2
  }

  extensions = [ "akka.contrib.pattern.ClusterReceptionistExtension" ]
}
```

Going through the settings:

  * we need to use the `ClusterActorRefProvider` to communicate with actors on other cluster nodes
  * as we will be using the same config file for multiple nodes (for local testing), the port will be overridden in code. Apart from the port, we also need to specify the hostname to which cluster communication will bind.
  * the node needs an initial list of seed nodes, with which it will try to communicate on start-up to form a cluster
  * we&#8217;re using auto-downing, which causes nodes to be declared `down` after 10 seconds. This [can cause partitions][10], however we also specify that there need to be at least 2 nodes alive for the cluster fragment to be operative (we will have 3 nodes in total, so with 2 we are safe against partitions)
  * finally, we are declaring that any cluster nodes started using the config file will have the `broker` role and use the cluster receptionist extension &#8211; but more on that later.

Actually starting a clustered actor system is now very simple, see the [`BrokerManager` class][11]:
```scala
class BrokerManager(clusterPort: Int) {
   // …
   val conf = ConfigFactory
      .parseString(s"akka.remote.netty.tcp.port=$clusterPort")
      .withFallback(ConfigFactory.load("cluster-broker-template"))

   val system = ActorSystem(s"broker", conf)
   // ...
}
```

To specify the port we create configuration from a string with only the port part specified, and to use the other settings, we fall back to the configuration from the template file.

Starting cluster nodes is just a matter of starting three [simple applications][12]:
```scala
object ClusteredBroker1 extends App {
  new BrokerManager(9171).run()
}
```

# Cluster singleton

Having the cluster ready, we now have to start the broker on one of the nodes. At any time, there should be only one broker running &#8211; otherwise the queue would get corrupted. For that the [Cluster Singleton][13] contrib extension is perfect.

To use the extension we need to create an actor, which will be managed by the singleton extension and started on only one cluster node. Hence we create the `BrokerManagerActor` and we can now [start the singleton][14]:
```scala
def run() {
   // …
   system.actorOf(ClusterSingletonManager.props(
      singletonProps = Props(classOf[BrokerManagerActor], clusterPort),
      singletonName = "broker",
      terminationMessage = PoisonPill,
      role = Some("broker")),
      name = "broker-manager")
}

class BrokerManagerActor(clusterPort: Int) extends Actor {
  val sendServerAddress     = new InetSocketAddress(
    "localhost", clusterPort + 10)
  val receiveServerAddress  = new InetSocketAddress(
    "localhost", clusterPort + 20)

  override def preStart() = {
    super.preStart()
    new Broker(sendServerAddress, receiveServerAddress)(context.system)
      .run()
  }

  override def receive = { case _ => } 
}
```

In the `ClusterSingletonManager` properties, we specify the actor to run, the message that can be used to terminate the actor, and the cluster roles on which the actor can run (our clusters’ nodes have only one role, `broker`).

The `BrokerManagerActor` takes a port (should be unique to each node if we want to run a couple on localhost), and creates basing on that an address on which the socket for new queue-message-sending-clients will listen, and another for the queue-message-receive-client listening socket.

# Cluster client & receptionist: cluster side

We now have a single broker running in the cluster, but how can clients know what’s the address of the singleton? Well, we can just ask the `BrokerManagerActor` actor for that! This can be done by a simple message exchange:
```scala
case object GetBrokerAddresses
case class BrokerAddresses(sendServerAddress: InetSocketAddress, 
   receiveServerAddress: InetSocketAddress)

class BrokerManagerActor(clusterPort: Int) extends Actor {
   // as above, plus:
   override def receive = {
      case GetBrokerAddresses => sender() ! BrokerAddresses(
         sendServerAddress, receiveServerAddress)
  }
}
```

One problem remains, though. The clients that want to use our message queue need not be members of the cluster. Here the [Cluster Client][15] contrib extension can help.

On the cluster node side, the client extension provides a receptionist, with which actors, which want to be visible outside can register. And that’s what our `BrokerManagerActor` [does on startup][16]:
```scala
class BrokerManagerActor(clusterPort: Int) extends Actor {
   // …
   override def preStart() = {
      // ...
      ClusterReceptionistExtension(context.system)
         .registerService(self)
   }
}
```

# Cluster client & receptionist: client side

As for the clients itself, they also [need some configuration][17] to communicate with the cluster:
```bash
akka {
  actor.provider = "akka.remote.RemoteActorRefProvider"

  remote.netty.tcp.port = 0
  remote.netty.tcp.hostname = "127.0.0.1"
}

cluster.client.initial-contact-points = [
  "akka.tcp://broker@127.0.0.1:9171",
  "akka.tcp://broker@127.0.0.1:9172",
  "akka.tcp://broker@127.0.0.1:9173"
]
```

Again going through the settings:

  * to communicate with remote actors (which live in the cluster), we need to use the `RemoteActorRefProvider` (the `ClusterARP` is a richer version of the `RemoteARP`)
  * similarly to the seed nodes, we need to provide seed contact points, so that the client has some way of initiating communication with the cluster

Actually initiating a cluster client is [quite straightforward][18], we need to create an actor system and create an actor which will communicate with the cluster:
```scala
val conf = ConfigFactory.load("cluster-client")
implicit val system = ActorSystem(name, conf)

val initialContacts = conf
  .getStringList("cluster.client.initial-contact-points")
  .asScala.map {
    case AddressFromURIString(addr) => system.actorSelection(
      RootActorPath(addr) / "user" / "receptionist")
  }.toSet
 
val clusterClient = system.actorOf(
  ClusterClient.props(initialContacts), "cluster-client")
```

To start a client of our message queue (we have two types of clients: one sends messages to the queue, the other received messages from it), we need to find out what’s the address of the broker. To do that, we [ask (using Akka’s ask pattern) the broker][19] registered with the receptionist about its address:
```scala
clusterClient ? ClusterClient.Send(
   "/user/broker-manager/broker", 
   GetBrokerAddresses, 
   localAffinity = false)
      .mapTo[BrokerAddresses]
      .flatMap { ba =>
         logger.info(s"Connecting a $name using broker address $ba.")
         runClient(ba, system)
      }
```

Finally, when the client stream completes (e.g. because a broker is down), we try to [restart it][20] after 1 second. Probably some exponential back-off mechanism would be useful here.

The [runnable application][21] for running the queue-message senders and receivers uses the same code as the single-node one, the difference being that the broker address is obtained from the cluster:
```scala
object ClusterReceiver extends App with ClusterClientSupport {
  start("receiver", (ba, system) => 
    new Receiver(ba.receiveServerAddress)(system).run())
}
```

# Running

If you try to run `ClusterReceiver` (any number), `ClusterSender` (any number), `ClusteredBroker1`, `ClusteredBroker2` and `ClusteredBroker3`, you will see that that the messages flow from senders, through a single running broker, to the receivers. You can kill a broker node, and after a couple of seconds another one will be started on another cluster node, and the senders/receivers will re-connect.

I’d say that’s quite nice for the pretty small amount of code we’ve written!

# Summing up

Our message queue now is:

  * reactive, using akka-streams
  * persistent, using akka-persistence
  * clustered, using akka-cluster

And the best side is, in the code we don’t have to deal with any details of back-pressure handling, storing messages to disk or communicating and reaching consensus in the cluster. We now have a truly reactive application.

Thanks to Endre from the Akka team for helping out with [adding error handling][22] to the streams. The whole code is [available on GitHub][1]. Enjoy!

 [1]: https://github.com/adamw/reactmq
 [2]: http://www.warski.org/blog/2014/06/reactive-queue-with-akka-reactive-streams/
 [3]: http://www.warski.org/blog/2014/07/making-the-reactive-queue-durable-with-akka-persistence/
 [4]: http://www.reactive-streams.org
 [5]: http://doc.akka.io/docs/akka/2.3.6/scala/persistence.html
 [6]: https://github.com/krasserm/akka-persistence-cassandra/
 [7]: http://doc.akka.io/docs/akka/2.3.6/scala/cluster-usage.html
 [8]: https://github.com/adamw/reactmq/blob/41697584e26748d35b837acbe82b652b381c35fd/build.sbt#L15-L16
 [9]: https://github.com/adamw/reactmq/blob/41697584e26748d35b837acbe82b652b381c35fd/src/main/resources/cluster-broker-template.conf
 [10]: http://doc.akka.io/docs/akka/2.3.4/scala/cluster-usage.html
 [11]: https://github.com/adamw/reactmq/blob/41697584e26748d35b837acbe82b652b381c35fd/src/main/scala/com/reactmq/cluster/BrokerManager.scala#L18-L21
 [12]: https://github.com/adamw/reactmq/blob/41697584e26748d35b837acbe82b652b381c35fd/src/main/scala/com/reactmq/cluster/BrokerManager.scala#L50-L60
 [13]: http://doc.akka.io/docs/akka/2.3.6/contrib/cluster-singleton.html
 [14]: https://github.com/adamw/reactmq/blob/41697584e26748d35b837acbe82b652b381c35fd/src/main/scala/com/reactmq/cluster/BrokerManager.scala#L23-L45
 [15]: http://doc.akka.io/docs/akka/2.3.6/contrib/cluster-client.html
 [16]: https://github.com/adamw/reactmq/blob/41697584e26748d35b837acbe82b652b381c35fd/src/main/scala/com/reactmq/cluster/BrokerManager.scala#L39
 [17]: https://github.com/adamw/reactmq/blob/41697584e26748d35b837acbe82b652b381c35fd/src/main/resources/cluster-client.conf
 [18]: https://github.com/adamw/reactmq/blob/41697584e26748d35b837acbe82b652b381c35fd/src/main/scala/com/reactmq/cluster/ClusterClientSupport.scala#L16-L25
 [19]: https://github.com/adamw/reactmq/blob/41697584e26748d35b837acbe82b652b381c35fd/src/main/scala/com/reactmq/cluster/ClusterClientSupport.scala#L28-L34
 [20]: https://github.com/adamw/reactmq/blob/41697584e26748d35b837acbe82b652b381c35fd/src/main/scala/com/reactmq/cluster/ClusterClientSupport.scala#L36-L41
 [21]: https://github.com/adamw/reactmq/blob/41697584e26748d35b837acbe82b652b381c35fd/src/main/scala/com/reactmq/cluster/ClusterReceiver.scala
 [22]: https://groups.google.com/forum/#!topic/akka-user/cr5qekUxRqM
