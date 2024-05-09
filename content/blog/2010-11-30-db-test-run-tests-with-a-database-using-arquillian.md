---
title: 'DB test: run tests with a database using Arquillian'
author: Adam Warski
type: blog
date: 2010-11-30T13:38:56+00:00
url: /blog/2010/11/db-test-run-tests-with-a-database-using-arquillian/
disqus_identifier:
  - 1051357161
wp-syntax-cache-content:
  - |
    a:2:{i:1;s:5125:"
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
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;"><span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000000; font-weight: bold;">class</span> MyEntityDAOTest <span style="color: #000000; font-weight: bold;">extends</span> AbstractDBTest <span style="color: #009900;">&#123;</span>
        @Override
        <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000066; font-weight: bold;">void</span> configureEntities<span style="color: #009900;">&#40;</span>Ejb3Configuration cfg<span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
            cfg.<span style="color: #006633;">addAnnotatedClass</span><span style="color: #009900;">&#40;</span>MyEntity.<span style="color: #000000; font-weight: bold;">class</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
        <span style="color: #009900;">&#125;</span>
    &nbsp;
        @Deployment
        <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000000; font-weight: bold;">static</span> JavaArchive createTestArchive<span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
            <span style="color: #000000; font-weight: bold;">return</span> <span style="color: #000000; font-weight: bold;">new</span> ArchiveConfigurator<span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
                @Override
                <span style="color: #000000; font-weight: bold;">protected</span> JavaArchive configureBeans<span style="color: #009900;">&#40;</span>JavaArchive ar<span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
                    <span style="color: #000000; font-weight: bold;">return</span> ar.<span style="color: #006633;">addPackage</span><span style="color: #009900;">&#40;</span>MyEntityDAO.<span style="color: #000000; font-weight: bold;">class</span>.<span style="color: #006633;">getPackage</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
                <span style="color: #009900;">&#125;</span>
            <span style="color: #009900;">&#125;</span>.<span style="color: #006633;">createTestArchive</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
        <span style="color: #009900;">&#125;</span>
    &nbsp;
        @Inject
        <span style="color: #000000; font-weight: bold;">private</span> MyEntityDAO manager<span style="color: #339933;">;</span>
    &nbsp;
        @Test
        <span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000066; font-weight: bold;">void</span> testEntityManager<span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
            manager.<span style="color: #006633;">persist</span><span style="color: #009900;">&#40;</span><span style="color: #000000; font-weight: bold;">new</span> MyEntity<span style="color: #009900;">&#40;</span>10l, <span style="color: #0000ff;">&quot;test&quot;</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
            assertThat<span style="color: #009900;">&#40;</span>manager.<span style="color: #006633;">findById</span><span style="color: #009900;">&#40;</span>10l<span style="color: #009900;">&#41;</span>.<span style="color: #006633;">getText</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span>.<span style="color: #006633;">isEqualTo</span><span style="color: #009900;">&#40;</span><span style="color: #0000ff;">&quot;test&quot;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
        <span style="color: #009900;">&#125;</span>
    <span style="color: #009900;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">public class MyEntityDAOTest extends AbstractDBTest {
        @Override
        public void configureEntities(Ejb3Configuration cfg) {
            cfg.addAnnotatedClass(MyEntity.class);
        }
    
        @Deployment
        public static JavaArchive createTestArchive() {
            return new ArchiveConfigurator() {
                @Override
                protected JavaArchive configureBeans(JavaArchive ar) {
                    return ar.addPackage(MyEntityDAO.class.getPackage());
                }
            }.createTestArchive();
        }
    
        @Inject
        private MyEntityDAO manager;
    
        @Test
        public void testEntityManager() {
            manager.persist(new MyEntity(10l, &quot;test&quot;));
            assertThat(manager.findById(10l).getText()).isEqualTo(&quot;test&quot;);
        }
    }</p></div>
    ";i:2;s:781:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;"><span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000000; font-weight: bold;">class</span> MyEntityDAO <span style="color: #009900;">&#123;</span>
        @Inject @Writeable
        <span style="color: #000000; font-weight: bold;">private</span> EntityManager entityManager<span style="color: #339933;">;</span>
    &nbsp;
        <span style="color: #666666; font-style: italic;">// methods</span></pre></td></tr></table><p class="theCode" style="display:none;">public class MyEntityDAO {
        @Inject @Writeable
        private EntityManager entityManager;
    
        // methods</p></div>
    ";}
tags:
  - testing
  - jee
  - hibernate
  - java

---
DB test is a set of helper classes which let you run tests that use a database. By default each test has a separate, in-memory H2 database, providing fast startup time and isolation. A fairly large test runs on my machine in 3 seconds, so you can include such tests in your testsuite without a big overhead. Such tests are perfect for:

  * validating entity configuration (proper usage of annotations, hibernate.cfg.xml) &#8211; the normal [Hibernate][1] configuration sequence is done
  * testing query syntax (named and regular queries) and complex queries
  * testing methods which heavily use the EntityManager and would be hard to test otherwise

DB Test is based on [Arquillian][2] and can be used in applications that use CDI (e.g. [Weld][3]) for dependency injection and component management.

A very basic test looks like this:

<pre lang="java" line="1" escaped="true">public class MyEntityDAOTest extends AbstractDBTest {
    @Override
    public void configureEntities(Ejb3Configuration cfg) {
        cfg.addAnnotatedClass(MyEntity.class);
    }

    @Deployment
    public static JavaArchive createTestArchive() {
        return new ArchiveConfigurator() {
            @Override
            protected JavaArchive configureBeans(JavaArchive ar) {
                return ar.addPackage(MyEntityDAO.class.getPackage());
            }
        }.createTestArchive();
    }

    @Inject
    private MyEntityDAO manager;

    @Test
    public void testEntityManager() {
        manager.persist(new MyEntity(10l, "test"));
        assertThat(manager.findById(10l).getText()).isEqualTo("test");
    }
}
</pre>

Inside `MyEntityDAO` we can normally use an `EntityManager`, e.g.:

<pre lang="java" line="1" escaped="true">public class MyEntityDAO {
    @Inject @Writeable
    private EntityManager entityManager;

    // methods
</pre>

(`@Writeable` is one of the qualifiers from [softwaremill-cdi][4]: using them you can inject both a RO and RW entity managers).

A full example: [TestOfDBTest][5] can be found in the tests for the DB test module (yes, a test testing helper classes for tests! :) ).

All test methods are surrounded with a JTA transaction. If needed, another database can be used (e.g. mysql/postgres/etc): you can change any of the Hibernate configuration settings in the `configureEntities` method, just as in a normal configuration file (`persistence.xml` or `hibernate.cfg.xml`).

Moreover, before each test an sql script is optionally executed, which can be used to populate the DB with test data. The name of the sql file is the same as of the test class.

You can find the code in the [softwaremill-db-test][6] module of [softwaremill-common][7] library, which is licensed under the Apache2 license.

**Update:** New module URL.

Adam

 [1]: http://hibernate.org
 [2]: http://jboss.org/arquillian
 [3]: http://seamframework.org/
 [4]: https://github.com/softwaremill/softwaremill-common
 [5]: https://github.com/softwaremill/softwaremill-common/tree/master/softwaremill-test/softwaremill-test-db/src/test/java/pl/softwaremill/common/dbtest
 [6]: https://github.com/softwaremill/softwaremill-common/tree/master/softwaremill-test/softwaremill-test-db
 [7]: https://github.com/softwaremill/softwaremill-common/
