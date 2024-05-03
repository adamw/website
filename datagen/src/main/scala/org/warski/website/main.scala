package org.warski.website

import net.ruippeixotog.scalascraper.browser.JsoupBrowser
import net.ruippeixotog.scalascraper.dsl.DSL.*
import net.ruippeixotog.scalascraper.dsl.DSL.Extract.*
import net.ruippeixotog.scalascraper.dsl.DSL.Parse.*
import org.joda.time.{DateTimeZone, LocalTime}
import org.warski.website.model.{BlogPost, Talk, Uri, Video}
import org.warski.website.persistence.{CommitDataFiles, PersistentModel}

import java.time.format.DateTimeFormatter
import java.time.{Instant, LocalDate, YearMonth, ZoneOffset}
import java.util.UUID
import scala.io.{Source, StdIn}

@main def addTalks(): Unit = while (true) addTalk()

def addTalk(): Unit =
  println("Conference name:")
  val conferenceName = StdIn.readLine().trim
  println("Conference url:")
  val conferenceUrl = noneIfEmpty(StdIn.readLine().trim)
  println("Talk title:")
  val talkTitle = StdIn.readLine().trim
  println("mm/yy:")
  val monthYear = parseMMYYtoInstant(StdIn.readLine().trim)
  println("City + country:")
  val cityCountry = StdIn.readLine().trim

  println("Video url:")
  val videoUrl = noneIfEmpty(StdIn.readLine().trim)
  val video = videoUrl.map(v => addVideo(Uri(v)))
  println("Slides url:")
  val slidesUrl = noneIfEmpty(StdIn.readLine().trim).map(Uri(_))

  val tags = video match
    case Some(v) => v.tags
    case None =>
      println("Talk tags:")
      readTags()

  val talk =
    Talk(
      UUID.randomUUID(),
      talkTitle,
      conferenceUrl.map(Uri(_)),
      None,
      monthYear,
      tags,
      cityCountry,
      conferenceName,
      slidesUrl,
      video.map(_.id),
      None,
      None
    )
  PersistentModel.talks.add(talk)
  CommitDataFiles.run(s"Adding talk $talkTitle ($conferenceName)")

@main def test =
  println(parseMMYYtoInstant("11/23"))
  // println(addVideo(Uri("https://www.youtube.com/watch?v=Ia0J0yfxTCA")))

def addVideo(url: Uri, useTags: Option[List[String]] = None): Video =
  val video = if url.toString.contains("youtube") then
    val browser = JsoupBrowser()
    val doc = browser.get(url.toString)

    val title = (doc >> attr("content")("meta[name=title]")).trim
    val coverImage = noneIfEmpty((doc >> attr("content")("meta[property=og:image]")).trim)
    val created = Instant.ofEpochMilli(
      (doc >> extractor("meta[itemprop=datePublished]", attr("content"), asDateTime("yyyy-MM-dd'T'HH:mm:ssZ"))).toInstant.getMillis
    )
    val tags = useTags.getOrElse {
      println("Video tags:")
      readTags()
    }

    Video(UUID.randomUUID(), title, url, coverImage.map(Uri(_)), created, tags, ytIdFromUrl(url.toString))
  else
    val tags = useTags.getOrElse {
      println("Video tags:")
      readTags()
    }
    Video(UUID.randomUUID(), "???", url, None, Instant.now(), tags, None)

  PersistentModel.videos.add(video)
  video

@main def addVideoToTalk() =
  val talks = PersistentModel.talks.read().sortBy(_.when)
  for (i <- 1 to talks.length) println(s"$i: ${talks(i - 1).title} (${talks(i - 1).conference})")
  println()
  println("Talk number:")
  val talk = talks(StdIn.readLine().toInt - 1)
  println("Video url:")
  val videoUrl = StdIn.readLine().trim
  val video = addVideo(Uri(videoUrl), Some(talk.tags))
  PersistentModel.talks.update(talk.copy(video = Some(video.id)))
  CommitDataFiles.run(s"Adding video to ${talk.title}")

@main def addBlog(): Unit =
  println("Blog url:")
  val url = StdIn.readLine()
  val bp = readBlog(url)
  PersistentModel.blogs.add(bp)
  CommitDataFiles.run(s"Adding blog ${bp.title}")

@main def addBlogs(): Unit =
  for (url <- Source.fromFile("/Users/adamw/projects/website/blogs.txt").getLines()) {
    val allBlogs = PersistentModel.blogs.read()
    if allBlogs.exists(_.url.toString == url) then println(s"Blog $url already exists")
    else
      val bp = readBlog(url)
      PersistentModel.blogs.add(bp)
      println()
  }

@main def simulateAddBlog(): Unit =
  println(readBlog("https://softwaremill.com/go-like-selects-using-jox-channels-in-java/"))

def readBlog(url: String): BlogPost =
  println(s"Blog url: $url")

  val browser = JsoupBrowser()
  val doc = browser.get(url)

  val title = (doc >?> attr("content")("meta[property=og:title]"))
    .orElse(doc >?> attr("content")("meta[name=title]"))
    .getOrElse(doc >> text("title"))
    .trim
  println(s"Title: $title")

  val coverImage = (doc >?> attr("content")("meta[property=og:image]"))
    .orElse(doc >?> attr("src")("img"))
    .map(_.trim)
    .map(Uri(_))
  println(s"Cover image: $coverImage")

  // SML blog
  var whenBase =
    (doc >?> extractor("span[class=date]", text, asLocalDate("dd MMM yyyy'.'"))).map(v =>
      Instant.ofEpochMilli(v.toDateTime(LocalTime.parse("12:00"), DateTimeZone.UTC).toInstant.getMillis)
    )
  // medium blog
  if whenBase.isEmpty then
    whenBase = (doc >?> extractor("span[data-testid=storyPublishDate]", text, asLocalDate("MMM dd, yyyy"))).map(v =>
      Instant.ofEpochMilli(v.toDateTime(LocalTime.parse("12:00"), DateTimeZone.UTC).toInstant.getMillis)
    )
  val when = whenBase
    .map { w =>
      println(s"When: $w"); w
    }
    .getOrElse {
      println("dd/mm/yy:")
      parseDDMMYYtoInstant(StdIn.readLine().trim)
    }

  // SML blog
  var tags = (doc >?> attr("content")("meta[name=keywords]")).getOrElse("").split(",").map(_.trim.toLowerCase).filter(_.nonEmpty).toList
  if tags.isEmpty then
    // medium blog
    val anchors = doc >> elementList("a")
    tags = anchors
      .filter(_.attr("href").contains("https://medium.com/tag/"))
      .map(_ >> allText("div"))

    if tags.isEmpty then
      // SML blog, v2
      tags = anchors
        .filter(_.attr("href").contains("/blog/?tag="))
        .map(_ >> text)

      if tags.isEmpty then
        println("Tags:")
        tags = readTags()
      else println(s"Tags: $tags")
    else println(s"Tags: $tags")
  else println(s"Tags: $tags")

  tags = tags.map(_.toLowerCase)

  BlogPost(UUID.randomUUID(), title, Uri(url), coverImage, when, tags)

private def noneIfEmpty(s: String): Option[String] = if s.isEmpty then None else Some(s)
private def readTags(): List[String] = StdIn.readLine().split(",").map(_.trim.toLowerCase).toList.filterNot(_.isEmpty)
private def parseDDMMYYtoInstant(dateString: String) =
  val formatter = DateTimeFormatter.ofPattern("dd/MM/yy")
  val date = LocalDate.parse(dateString, formatter).atTime(12, 0)
  val zonedDateTime = date.atZone(ZoneOffset.UTC)
  zonedDateTime.toInstant
private def parseMMYYtoInstant(dateString: String) =
  val formatter = DateTimeFormatter.ofPattern("MM/yy")
  val date = YearMonth.parse(dateString, formatter).atDay(1)
  val dateTime = date.atStartOfDay
  val zonedDateTime = dateTime.atZone(ZoneOffset.UTC)
  zonedDateTime.toInstant
private def ytIdFromUrl(url: String): Option[String] =
  val urlPattern = ".*[?&]v=([^&]+).*".r
  url match
    case urlPattern(value) => Some(value)
    case _                 => None
