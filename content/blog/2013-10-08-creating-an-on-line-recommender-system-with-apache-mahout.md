---
title: Creating an on-line recommender system with Apache Mahout
author: Adam Warski
type: post
date: 2013-10-08T14:54:51+00:00
url: /blog/2013/10/creating-an-on-line-recommender-system-with-apache-mahout/
simplecatch-sidebarlayout:
  - default
dsq_thread_id:
  - 1836582143
wp-syntax-cache-content:
  - |
    a:5:{i:1;s:1775:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">val</span> dataModel <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> FileDataModel<span style="color: #F78811;">&#40;</span>file<span style="color: #F78811;">&#41;</span>
    <span style="color: #0000ff; font-weight: bold;">val</span> userSimilarity <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> LogLikelihoodSimilarity<span style="color: #F78811;">&#40;</span>dataModel<span style="color: #F78811;">&#41;</span>
    <span style="color: #0000ff; font-weight: bold;">val</span> neighborhood <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> NearestNUserNeighborhood<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">25</span>, userSimilarity, dataModel<span style="color: #F78811;">&#41;</span>
    <span style="color: #0000ff; font-weight: bold;">val</span> recommender <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> GenericBooleanPrefUserBasedRecommender<span style="color: #F78811;">&#40;</span>dataModel, neighborhood, userSimilarity<span style="color: #F78811;">&#41;</span></pre></td></tr></table><p class="theCode" style="display:none;">val dataModel = new FileDataModel(file)
    val userSimilarity = new LogLikelihoodSimilarity(dataModel)
    val neighborhood = new NearestNUserNeighborhood(25, userSimilarity, dataModel)
    val recommender = new GenericBooleanPrefUserBasedRecommender(dataModel, neighborhood, userSimilarity)</p></div>
    ";i:2;s:1763:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">val</span> dataModel <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> FileDataModel<span style="color: #F78811;">&#40;</span>file<span style="color: #F78811;">&#41;</span>
    <span style="color: #0000ff; font-weight: bold;">val</span> userSimilarity <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> PearsonCorrelationSimilarity<span style="color: #F78811;">&#40;</span>dataModel<span style="color: #F78811;">&#41;</span>
    <span style="color: #0000ff; font-weight: bold;">val</span> neighborhood <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> NearestNUserNeighborhood<span style="color: #F78811;">&#40;</span><span style="color: #F78811;">25</span>, userSimilarity, dataModel<span style="color: #F78811;">&#41;</span>
    <span style="color: #0000ff; font-weight: bold;">val</span> recommender <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> GenericUserBasedRecommender<span style="color: #F78811;">&#40;</span>dataModel, neighborhood, userSimilarity<span style="color: #F78811;">&#41;</span></pre></td></tr></table><p class="theCode" style="display:none;">val dataModel = new FileDataModel(file)
    val userSimilarity = new PearsonCorrelationSimilarity(dataModel)
    val neighborhood = new NearestNUserNeighborhood(25, userSimilarity, dataModel)
    val recommender = new GenericUserBasedRecommender(dataModel, neighborhood, userSimilarity)</p></div>
    ";i:3;s:1732:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    6
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #008000; font-style: italic;">// Gets 10 recommendations</span>
    <span style="color: #0000ff; font-weight: bold;">val</span> result <span style="color: #000080;">=</span> recommender.<span style="color: #000000;">recommend</span><span style="color: #F78811;">&#40;</span>userId, <span style="color: #F78811;">10</span><span style="color: #F78811;">&#41;</span>
    &nbsp;
    <span style="color: #008000; font-style: italic;">// We get back a list of item-estimated preference value, </span>
    <span style="color: #008000; font-style: italic;">// sorted from the highest score</span>
    result.<span style="color: #000000;">foreach</span><span style="color: #F78811;">&#40;</span>r <span style="color: #000080;">=&amp;</span>gt<span style="color: #000080;">;</span> println<span style="color: #F78811;">&#40;</span>r.<span style="color: #000000;">getItemID</span><span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span> + <span style="color: #6666FF;">&quot;: &quot;</span> + r.<span style="color: #000000;">getValue</span><span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span></pre></td></tr></table><p class="theCode" style="display:none;">// Gets 10 recommendations
    val result = recommender.recommend(userId, 10)
    
    // We get back a list of item-estimated preference value, 
    // sorted from the highest score
    result.foreach(r =&amp;gt; println(r.getItemID() + &quot;: &quot; + r.getValue()))</p></div>
    ";i:4;s:7035:"
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
    28
    29
    30
    31
    32
    33
    34
    35
    36
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">val</span> dataModel <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> PlusAnonymousConcurrentUserDataModel<span style="color: #F78811;">&#40;</span>
        <span style="color: #0000ff; font-weight: bold;">new</span> FileDataModel<span style="color: #F78811;">&#40;</span>file<span style="color: #F78811;">&#41;</span>,
        <span style="color: #F78811;">100</span><span style="color: #F78811;">&#41;</span>
    &nbsp;
    <span style="color: #0000ff; font-weight: bold;">val</span> recommender<span style="color: #000080;">:</span> org.<span style="color: #000000;">apache</span>.<span style="color: #000000;">mahout</span>.<span style="color: #000000;">cf</span>.<span style="color: #000000;">taste</span>.<span style="color: #000000;">recommender</span>.<span style="color: #000000;">Recommender</span> <span style="color: #000080;">=</span> ...
    &nbsp;
    <span style="color: #008000; font-style: italic;">// we are assuming a unary model: we only know which items a user likes</span>
    <span style="color: #0000ff; font-weight: bold;">def</span> recommendFor<span style="color: #F78811;">&#40;</span>userId<span style="color: #000080;">:</span> Long, userPreferences<span style="color: #000080;">:</span> List<span style="color: #F78811;">&#91;</span>Long<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#41;</span> <span style="color: #000080;">=</span> <span style="color: #F78811;">&#123;</span>
      <span style="color: #0000ff; font-weight: bold;">if</span> <span style="color: #F78811;">&#40;</span>userExistsInDataModel<span style="color: #F78811;">&#40;</span>userId<span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span> <span style="color: #F78811;">&#123;</span>
        recommendForExistingUser<span style="color: #F78811;">&#40;</span>userId<span style="color: #F78811;">&#41;</span>
      <span style="color: #F78811;">&#125;</span> <span style="color: #0000ff; font-weight: bold;">else</span> <span style="color: #F78811;">&#123;</span>
        recommendForNewUser<span style="color: #F78811;">&#40;</span>userPreferences<span style="color: #F78811;">&#41;</span>
      <span style="color: #F78811;">&#125;</span>
    <span style="color: #F78811;">&#125;</span>
    &nbsp;
    <span style="color: #0000ff; font-weight: bold;">def</span> recommendForNewUser<span style="color: #F78811;">&#40;</span>userPreferences<span style="color: #000080;">:</span> List<span style="color: #F78811;">&#91;</span>Long<span style="color: #F78811;">&#93;</span><span style="color: #F78811;">&#41;</span> <span style="color: #000080;">=</span> <span style="color: #F78811;">&#123;</span>
      <span style="color: #0000ff; font-weight: bold;">val</span> tempUserId <span style="color: #000080;">=</span> dataModel.<span style="color: #000000;">takeAvailableUser</span><span style="color: #F78811;">&#40;</span><span style="color: #F78811;">&#41;</span>
    &nbsp;
      <span style="color: #0000ff; font-weight: bold;">try</span> <span style="color: #F78811;">&#123;</span>
        <span style="color: #008000; font-style: italic;">// filling in a Mahout data structure with the user's preferences</span>
        <span style="color: #0000ff; font-weight: bold;">val</span> tempPrefs <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> BooleanUserPreferenceArray<span style="color: #F78811;">&#40;</span>userPreferences.<span style="color: #000000;">size</span><span style="color: #F78811;">&#41;</span>
        tempPrefs.<span style="color: #000000;">setUserID</span><span style="color: #F78811;">&#40;</span><span style="color: #F78811;">0</span>, tempUserId<span style="color: #F78811;">&#41;</span>
        userPreferences.<span style="color: #000000;">zipWithIndex</span>.<span style="color: #000000;">foreach</span> <span style="color: #F78811;">&#123;</span> <span style="color: #0000ff; font-weight: bold;">case</span> <span style="color: #F78811;">&#40;</span>preference, idx<span style="color: #F78811;">&#41;</span> <span style="color: #000080;">=&amp;</span>gt<span style="color: #000080;">;</span> 
          tempPrefs.<span style="color: #000000;">setItemID</span><span style="color: #F78811;">&#40;</span>idx, preference<span style="color: #F78811;">&#41;</span> 
        <span style="color: #F78811;">&#125;</span>
        dataModel.<span style="color: #000000;">setTempPrefs</span><span style="color: #F78811;">&#40;</span>tempPrefs, tempUserId<span style="color: #F78811;">&#41;</span>
    &nbsp;
        recommendForExistingUser<span style="color: #F78811;">&#40;</span>tempUserId<span style="color: #F78811;">&#41;</span>
      <span style="color: #F78811;">&#125;</span> <span style="color: #0000ff; font-weight: bold;">finally</span> <span style="color: #F78811;">&#123;</span>
        dataModel.<span style="color: #000000;">releaseUser</span><span style="color: #F78811;">&#40;</span>tempUserId<span style="color: #F78811;">&#41;</span>
      <span style="color: #F78811;">&#125;</span>
    <span style="color: #F78811;">&#125;</span>
    &nbsp;
    <span style="color: #0000ff; font-weight: bold;">def</span> recommendForExistingUser<span style="color: #F78811;">&#40;</span>userId<span style="color: #000080;">:</span> Long<span style="color: #F78811;">&#41;</span> <span style="color: #000080;">=</span> <span style="color: #F78811;">&#123;</span>
      recommender.<span style="color: #000000;">recommend</span><span style="color: #F78811;">&#40;</span>userId, <span style="color: #F78811;">10</span><span style="color: #F78811;">&#41;</span>
    <span style="color: #F78811;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">val dataModel = new PlusAnonymousConcurrentUserDataModel(
        new FileDataModel(file),
        100)
    
    val recommender: org.apache.mahout.cf.taste.recommender.Recommender = ...
    
    // we are assuming a unary model: we only know which items a user likes
    def recommendFor(userId: Long, userPreferences: List[Long]) = {
      if (userExistsInDataModel(userId)) {
        recommendForExistingUser(userId)
      } else {
        recommendForNewUser(userPreferences)
      }
    }
    
    def recommendForNewUser(userPreferences: List[Long]) = {
      val tempUserId = dataModel.takeAvailableUser()
    
      try {
        // filling in a Mahout data structure with the user's preferences
        val tempPrefs = new BooleanUserPreferenceArray(userPreferences.size)
        tempPrefs.setUserID(0, tempUserId)
        userPreferences.zipWithIndex.foreach { case (preference, idx) =&amp;gt; 
          tempPrefs.setItemID(idx, preference) 
        }
        dataModel.setTempPrefs(tempPrefs, tempUserId)
    
        recommendForExistingUser(tempUserId)
      } finally {
        dataModel.releaseUser(tempUserId)
      }
    }
    
    def recommendForExistingUser(userId: Long) = {
      recommender.recommend(userId, 10)
    }</p></div>
    ";i:5;s:2549:"
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
    </pre></td><td class="code"><pre class="scala" style="font-family:monospace;"><span style="color: #0000ff; font-weight: bold;">val</span> rescorer <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">new</span> IDRescorer <span style="color: #F78811;">&#123;</span>
      <span style="color: #0000ff; font-weight: bold;">def</span> rescore<span style="color: #F78811;">&#40;</span>id<span style="color: #000080;">:</span> Long, originalScore<span style="color: #000080;">:</span> Double<span style="color: #F78811;">&#41;</span> <span style="color: #000080;">=</span> <span style="color: #F78811;">&#123;</span>
        <span style="color: #0000ff; font-weight: bold;">if</span> <span style="color: #F78811;">&#40;</span>showIsNew<span style="color: #F78811;">&#40;</span>id<span style="color: #F78811;">&#41;</span><span style="color: #F78811;">&#41;</span> <span style="color: #F78811;">&#123;</span>
          originalScore <span style="color: #000080;">*</span> <span style="color: #F78811;">1.2</span> 
        <span style="color: #F78811;">&#125;</span> <span style="color: #0000ff; font-weight: bold;">else</span> <span style="color: #F78811;">&#123;</span>
          originalScore
        <span style="color: #F78811;">&#125;</span>
      <span style="color: #F78811;">&#125;</span>
    &nbsp;
      <span style="color: #0000ff; font-weight: bold;">def</span> isFiltered<span style="color: #F78811;">&#40;</span>id<span style="color: #000080;">:</span> Long<span style="color: #F78811;">&#41;</span> <span style="color: #000080;">=</span> <span style="color: #0000ff; font-weight: bold;">false</span>
    <span style="color: #F78811;">&#125;</span>
    &nbsp;
    <span style="color: #008000; font-style: italic;">// Gets 10 recommendations</span>
    <span style="color: #0000ff; font-weight: bold;">val</span> result <span style="color: #000080;">=</span> recommender.<span style="color: #000000;">recommend</span><span style="color: #F78811;">&#40;</span>userId, <span style="color: #F78811;">10</span>, rescorer<span style="color: #F78811;">&#41;</span></pre></td></tr></table><p class="theCode" style="display:none;">val rescorer = new IDRescorer {
      def rescore(id: Long, originalScore: Double) = {
        if (showIsNew(id)) {
          originalScore * 1.2 
        } else {
          originalScore
        }
      }
    
      def isFiltered(id: Long) = false
    }
    
    // Gets 10 recommendations
    val result = recommender.recommend(userId, 10, rescorer)</p></div>
    ";}
categories:
  - Java
  - Library
  - mahout
  - recommender
  - Scala
  - Uncategorized
  - yap

---
Recently [we&#8217;ve][1] been implementing a recommender system for [Yap.TV][2]: you can see it in action after installing the app and going to the &#8220;Just for you&#8221; tab. We&#8217;re using [Apache Mahout][3] as the base for doing recommendations. Mahout is a &#8220;scalable machine learning library&#8221; and contains both local and distributed implementations of user- and item- based recommenders using collaborative filtering algorithms.

<a href="http://www.warski.org/blog/2013/10/creating-an-on-line-recommender-system-with-apache-mahout/screen568x568/" rel="attachment wp-att-1126"><img loading="lazy" decoding="async" class="aligncenter size-medium wp-image-1126" src="http://www.warski.org/blog/wp-content/uploads/2013/10/screen568x568-169x300.jpeg" alt="screen568x568" width="169" height="300" srcset="https://www.warski.org/blog/wp-content/uploads/2013/10/screen568x568-169x300.jpeg 169w, https://www.warski.org/blog/wp-content/uploads/2013/10/screen568x568-118x210.jpeg 118w, https://www.warski.org/blog/wp-content/uploads/2013/10/screen568x568.jpeg 320w" sizes="(max-width: 169px) 100vw, 169px" /></a>

For now we&#8217;ll focus on the local, single-machine implementation. It should work well if you have up to 10s of millions of preference values. Above that, you should probably consider the Hadoop-based implementation, as the data simply won&#8217;t fit into memory.

Writing a basic recommender with Mahout is quite simple; As Mahout is very configurable, usually there are different implementations to choose from; I&#8217;ll just describe what I think are &#8220;good starting points&#8221;.

### Basics

First you need a file with the input data. The format is quite simple: either comma-separated _(user id, item id)_ pairs or _(user id, item id, preference value)_ triples. This expresses what you already know: what users like which items, and optionally how much (e.g. on a 1-5 scale). The ids must be integers, the preference value is treated as a float.

Let&#8217;s first create a user-based recommender: that is a recommender, which when asked for recommendations for user A, first looks up &#8220;similar&#8221; users to A, and then tries to find best items, which these similar users have rated, but A hasn&#8217;t. To do that, we need to create 4 components:

  * **data model**: this will use the file
  * **user similarity**: a measure which given two users, will return a number representing how similar they are
  * **neighborhood**: for finding the neighborhood of a given user
  * **recommender**: which takes these pieces together to produce recommendations

For unary input data (where users either like items or we don&#8217;t know), a good starting point is:

<pre lang="scala" line="1">val dataModel = new FileDataModel(file)
val userSimilarity = new LogLikelihoodSimilarity(dataModel)
val neighborhood = new NearestNUserNeighborhood(25, userSimilarity, dataModel)
val recommender = new GenericBooleanPrefUserBasedRecommender(dataModel, neighborhood, userSimilarity)
</pre>

If we have preference values (triples in the input data):

<pre lang="scala" line="1">val dataModel = new FileDataModel(file)
val userSimilarity = new PearsonCorrelationSimilarity(dataModel)
val neighborhood = new NearestNUserNeighborhood(25, userSimilarity, dataModel)
val recommender = new GenericUserBasedRecommender(dataModel, neighborhood, userSimilarity)
</pre>

Now we are ready to get some recommendations; this is as simple as:

<pre lang="scala" line="1">// Gets 10 recommendations
val result = recommender.recommend(userId, 10)

// We get back a list of item-estimated preference value, 
// sorted from the highest score
result.foreach(r =&gt; println(r.getItemID() + ": " + r.getValue())) 
</pre>

### On-line

What about the on-line aspect? The above will work great for existing users; what about new users which register in the service? For sure we want to provide some reasonable recommendations for them as well. Creating a recommender instance is expensive (for sure takes longer than a &#8220;normal&#8221; network request), so we can&#8217;t just create a new recommender each time.

Luckily Mahout has a possibility of adding temporary users to a data model. The general setup then is:

  * periodically re-create the whole recommender using current data (e.g. each day or each hour &#8211; depending on how long it takes)
  * when doing a recommendation, check if the user exists in the system
  * if yes, do the recommendation as always
  * if no, create a temporary user, fill in the preferences, and do the recommendation

The first part (periodically re-creating the recommender) may be actually quite tricky if you are limited on memory: when creating the new recommender, you need to hold two copies of the data in memory (to still be able to server requests from the old one). But as that doesn&#8217;t really have anything to do with recommendations, I won&#8217;t go into details here.

As for the temporary users, we can wrap our data model with a `PlusAnonymousConcurrentUserDataModel` instance. This class allows to obtain a temporary user id; the id must be later released so that it can be re-used (there&#8217;s a limited number of such ids). After obtaining the id, we have to fill in the preferences, and then we can proceed with the recommendation as always:

<pre lang="scala" line="1">val dataModel = new PlusAnonymousConcurrentUserDataModel(
    new FileDataModel(file),
    100)

val recommender: org.apache.mahout.cf.taste.recommender.Recommender = ...

// we are assuming a unary model: we only know which items a user likes
def recommendFor(userId: Long, userPreferences: List[Long]) = {
  if (userExistsInDataModel(userId)) {
    recommendForExistingUser(userId)
  } else {
    recommendForNewUser(userPreferences)
  }
}

def recommendForNewUser(userPreferences: List[Long]) = {
  val tempUserId = dataModel.takeAvailableUser()

  try {
    // filling in a Mahout data structure with the user's preferences
    val tempPrefs = new BooleanUserPreferenceArray(userPreferences.size)
    tempPrefs.setUserID(0, tempUserId)
    userPreferences.zipWithIndex.foreach { case (preference, idx) =&gt; 
      tempPrefs.setItemID(idx, preference) 
    }
    dataModel.setTempPrefs(tempPrefs, tempUserId)

    recommendForExistingUser(tempUserId)
  } finally {
    dataModel.releaseUser(tempUserId)
  }
}

def recommendForExistingUser(userId: Long) = {
  recommender.recommend(userId, 10)
}
</pre>

### Incorporating business logic

It often happens that we want to boost the score of selected items because of some business rules. In our use-case, for example if a show has a new episode, we want to give it a higher score. That&#8217;s possible using the `IDRescorer` interface for Mahout. An instance of a rescorer is provided when invoking `Recommender.recommend`. For example:

<pre lang="scala" line="1">val rescorer = new IDRescorer {
  def rescore(id: Long, originalScore: Double) = {
    if (showIsNew(id)) {
      originalScore * 1.2 
    } else {
      originalScore
    }
  }

  def isFiltered(id: Long) = false
}

// Gets 10 recommendations
val result = recommender.recommend(userId, 10, rescorer)
</pre>

### Summary

Mahout is a great basis for creating recommenders. It&#8217;s very configurable and provides many extension points. There&#8217;s still quite a lof of work in picking the right configuration parameter values, setting up rescoring and evaluating the recommendation results, but the algorithms are solid, so there&#8217;s one thing less to worry about.

There&#8217;s also a very good book, [Mahout in Action][4], which covers recommender systems and other components of Mahout. It&#8217;s based on version 0.5 (current one is 0.8), but the code examples mostly work and the main logic of the project is the same.

Adam

 [1]: https://softwaremill.com/
 [2]: https://itunes.apple.com/us/app/yap-tv-social-guide-to-tv/id392986707?mt=8
 [3]: http://mahout.apache.org/
 [4]: http://manning.com/owen/
