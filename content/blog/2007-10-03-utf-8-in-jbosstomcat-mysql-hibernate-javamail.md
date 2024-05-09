---
title: UTF-8 in JBoss/Tomcat + MySQL + Hibernate + JavaMail
author: Adam Warski
type: blog
date: 2007-10-03T13:07:50+00:00
url: /blog/2007/10/utf-8-in-jbosstomcat-mysql-hibernate-javamail/
disqus_identifier:
  - 1050968006
wp-syntax-cache-content:
  - |
    a:4:{i:1;s:1706:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    6
    7
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;"><span style="color: #000000; font-weight: bold;">public</span> <span style="color: #000066; font-weight: bold;">void</span> doFilter<span style="color: #009900;">&#40;</span>ServletRequest request,
    ServletResponse response, FilterChain chain<span style="color: #009900;">&#41;</span>
    <span style="color: #000000; font-weight: bold;">throws</span> <span style="color: #003399;">IOException</span>, ServletException <span style="color: #009900;">&#123;</span>
    response.<span style="color: #006633;">setCharacterEncoding</span><span style="color: #009900;">&#40;</span><span style="color: #0000ff;">&quot;UTF-8&quot;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
    request.<span style="color: #006633;">setCharacterEncoding</span><span style="color: #009900;">&#40;</span><span style="color: #0000ff;">&quot;UTF-8&quot;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
    chain.<span style="color: #006633;">doFilter</span><span style="color: #009900;">&#40;</span>request, response<span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
    <span style="color: #009900;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">public void doFilter(ServletRequest request,
    ServletResponse response, FilterChain chain)
    throws IOException, ServletException {
    response.setCharacterEncoding(&quot;UTF-8&quot;);
    request.setCharacterEncoding(&quot;UTF-8&quot;);
    chain.doFilter(request, response);
    }</p></div>
    ";i:2;s:1297:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    </pre></td><td class="code"><pre class="sql" style="font-family:monospace;"><span style="color: #993333; font-weight: bold;">ALTER</span> <span style="color: #993333; font-weight: bold;">TABLE</span> <span style="color: #ff0000;">`&amp;lt;database&amp;gt;`</span><span style="color: #66cc66;">.</span><span style="color: #ff0000;">`&amp;lt;table_name&amp;gt;`</span> <span style="color: #993333; font-weight: bold;">MODIFY</span> <span style="color: #993333; font-weight: bold;">COLUMN</span> <span style="color: #ff0000;">`&amp;lt;column_name&amp;gt;`</span> <span style="color: #993333; font-weight: bold;">VARCHAR</span><span style="color: #66cc66;">&#40;</span><span style="color: #cc66cc;">255</span><span style="color: #66cc66;">&#41;</span> <span style="color: #993333; font-weight: bold;">CHARACTER</span> <span style="color: #993333; font-weight: bold;">SET</span> utf8 <span style="color: #993333; font-weight: bold;">COLLATE</span> utf8_general_ci;</pre></td></tr></table><p class="theCode" style="display:none;">ALTER TABLE `&amp;lt;database&amp;gt;`.`&amp;lt;table_name&amp;gt;` MODIFY COLUMN `&amp;lt;column_name&amp;gt;` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci;</p></div>
    ";i:3;s:1075:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    </pre></td><td class="code"><pre class="sql" style="font-family:monospace;"><span style="color: #993333; font-weight: bold;">CREATE</span> <span style="color: #993333; font-weight: bold;">TABLE</span> <span style="color: #ff0000;">`&amp;lt;database&amp;gt;`</span><span style="color: #66cc66;">.</span><span style="color: #ff0000;">`&amp;lt;table_name&amp;gt;`</span> <span style="color: #66cc66;">&#40;</span>&amp;lt;column_list&amp;gt;<span style="color: #66cc66;">&#41;</span> <span style="color: #993333; font-weight: bold;">DEFAULT</span> <span style="color: #993333; font-weight: bold;">CHARACTER</span> <span style="color: #993333; font-weight: bold;">SET</span> utf8 <span style="color: #993333; font-weight: bold;">COLLATE</span> utf8_general_ci;</pre></td></tr></table><p class="theCode" style="display:none;">CREATE TABLE `&amp;lt;database&amp;gt;`.`&amp;lt;table_name&amp;gt;` (&amp;lt;column_list&amp;gt;) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;</p></div>
    ";i:4;s:3095:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    6
    7
    8
    </pre></td><td class="code"><pre class="java" style="font-family:monospace;"><span style="color: #009900;">&#40;</span>...<span style="color: #009900;">&#41;</span>
    MimeMessage msg <span style="color: #339933;">=</span> <span style="color: #000000; font-weight: bold;">new</span> MimeMessage<span style="color: #009900;">&#40;</span>session<span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
    msg.<span style="color: #006633;">setFrom</span><span style="color: #009900;">&#40;</span>InternetAddress.<span style="color: #006633;">parse</span><span style="color: #009900;">&#40;</span>from, <span style="color: #000066; font-weight: bold;">false</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#91;</span><span style="color: #cc66cc;">0</span><span style="color: #009900;">&#93;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
    msg.<span style="color: #006633;">setSentDate</span><span style="color: #009900;">&#40;</span><span style="color: #000000; font-weight: bold;">new</span> <span style="color: #003399;">Date</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
    msg.<span style="color: #006633;">setRecipients</span><span style="color: #009900;">&#40;</span>Message.<span style="color: #006633;">RecipientType</span>.<span style="color: #006633;">TO</span>, InternetAddress.<span style="color: #006633;">parse</span><span style="color: #009900;">&#40;</span>to, <span style="color: #000066; font-weight: bold;">false</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
    msg.<span style="color: #006633;">setSubject</span><span style="color: #009900;">&#40;</span>subject, <span style="color: #0000ff;">&quot;UTF-8&quot;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
    msg.<span style="color: #006633;">setText</span><span style="color: #009900;">&#40;</span>body, <span style="color: #0000ff;">&quot;UTF-8&quot;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
    transport.<span style="color: #006633;">sendMessage</span><span style="color: #009900;">&#40;</span>msg, msg.<span style="color: #006633;">getAllRecipients</span><span style="color: #009900;">&#40;</span><span style="color: #009900;">&#41;</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span></pre></td></tr></table><p class="theCode" style="display:none;">(...)
    MimeMessage msg = new MimeMessage(session);
    msg.setFrom(InternetAddress.parse(from, false)[0]);
    msg.setSentDate(new Date());
    msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
    msg.setSubject(subject, &quot;UTF-8&quot;);
    msg.setText(body, &quot;UTF-8&quot;);
    transport.sendMessage(msg, msg.getAllRecipients());</p></div>
    ";}
epcl_post:
  - 'a:1:{s:13:"views_counter";i:1;}'
views_counter:
  - 1
tags:
  - java
  - jboss

---
While most of (web)applications communicate with the end user in English, a lot of them use native languages, which often have some special characters (not to look too far for an example, we have the Polish alphabet, with ą, ę, ś, etc). A widely accepted standard for coding such characters is [UTF-8][1]. However, it is not quite trivial to use the UTF-8 encoding in a Tomcat+MySQL+Hibernate+JavaMail combination, and have full UTF-8 support, in the database, web forms, jsp-s and e-mails.

**Part I. Preliminaries**

On every request, you have to set the encoding of characters manually; it is best to create a filter, with the following body:
```java
public void doFilter(ServletRequest request,
ServletResponse response, FilterChain chain)
throws IOException, ServletException {
response.setCharacterEncoding("UTF-8");
request.setCharacterEncoding("UTF-8");
chain.doFilter(request, response);
}
```

This is needed by almost all successive parts.

**Part II. JSPs**

If you want to display native characters on a JSP page, you have to:

  * at the top of the page, add `<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>`
  * in the `head` section, add `<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />`
  * and, of course, you have to edit the .jsp file using the UTF-8 encoding (to set this in Eclipse, right-click a project, go to &#8220;Resource&#8221; tab, and set the &#8220;Text file encoding&#8221; value to &#8220;UTF-8&#8221;)

**Part III. Java Strings**

It may also be the case, that you have some strings in your code, that contain native characters, and, for example, you would like to pass them to a .jsp page using `request.setAttribute(String, String)` or send them as an e-mail subject/body. To have them properly handled:

  * set the encoding of the java source files to UTF-8 (just as with .jsp files) in your favorite editor
  * compile the sources using the `-encoding UTF-8` option

**Part IV. Forms**

After displaying native characters, you may want to have some forms, where users can input text values using native characters. To have them properly handled by Tomcat, you need to edit the `server.xml` file, which is located:

  * in JBoss 4.0.x: `$JBOSS_HOME/server/ <conf> /deploy /jbossweb-tomcat55.sar/server.xml`
  * in JBoss 4.2: `$JBOSS_HOME/server/ <conf> /deploy /jboss-web.deployer/server.xml`

and add to the appropriate `<Connector ...>` (usually the first one) the following attribute: `URIEncoding="UTF-8"`.

**Part V. MySQL and Hibernate**

Storing strings in a database in UTF-8 is a bit more tricky. First of all, you have to tell MySQL that your varchar/text fields will be using UTF-8.

If you already have a database, or if your database was created by hibernate (using `hibernate.hbm2ddl.auto`), you will have to run this statement for each column:
```sql
ALTER TABLE `<database>`.`<table_name>` MODIFY COLUMN `<column_name>` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci;
```

([MySQL Administator][2] can help you with that).

If you are creating a database, you can set a default encoding for all text fields:
```sql
CREATE TABLE `<database>`.`<table_name>` (<column_list>) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
```

There are other possibilities as well, for example compiling mysql with UTF-8 support set as default. For the complete list of options, see [here][3].

But configuring your database is not all; you also have to tell hibernate that in your connection to MySQL, you will be using the UTF-8 encoding. To do this:

  * if you are using a data source, to the connection URL add the parameters as in this example: `<connection-url>jdbc:mysql://localhost:3306/<br />
<my_database>?useUnicode=true&characterEncoding=UTF-8<br />
</connection-url>`
  * if you are using EJB3/JPA, add to `persistence.xml` the following properties (in the appropriate `<persistence-unit>`):  
    `<br />
<property name="hibernate.connection.useUnicode"<br />
value="true" /><br />
<property name="hibernate.connection.characterEncoding"<br />
value="UTF-8" />`
  * in case of &#8220;plain&#8221; hibernate, just specify the above properties in your configuration file (`hibernate.properties` or `hibernate.cfg.xml`)

**Part VI. Java Mail**

Finally comes the easiest part: sending e-mails with the subject and body in UTF-8. The only things you have to do here is use `MimeMessage`, and give additional parameters when setting the subject and text of your message:
```java
(...)
MimeMessage msg = new MimeMessage(session);
msg.setFrom(InternetAddress.parse(from, false)[0]);
msg.setSentDate(new Date());
msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
msg.setSubject(subject, "UTF-8");
msg.setText(body, "UTF-8");
transport.sendMessage(msg, msg.getAllRecipients());
```

Do you know any other areas of Java which you have to configure to have full support for UTF8?

Thanks to [Tomek Szymański][4] for helping me in finding the above information.

 [1]: http://en.wikipedia.org/wiki/UTF-8
 [2]: http://www.mysql.com/products/tools/administrator/
 [3]: http://dev.mysql.com/doc/refman/5.0/en/charset-syntax.html
 [4]: http://szimano.org
