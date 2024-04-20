package org.warski.website

import net.ruippeixotog.scalascraper.browser.JsoupBrowser
import net.ruippeixotog.scalascraper.dsl.DSL.*
import net.ruippeixotog.scalascraper.dsl.DSL.Extract.*
import net.ruippeixotog.scalascraper.dsl.DSL.Parse.*
import org.warski.website.model.{Talk, Uri, Video}
import org.warski.website.persistence.{CommitDataFiles, PersistentModel}

import java.time.format.DateTimeFormatter
import java.time.{Instant, YearMonth, ZoneOffset}
import java.util.UUID
import scala.io.StdIn

@main def addTalks(): Unit = while (true) addTalk()

def addTalk(): Unit =
  println("Conference name: ")
  val conferenceName = StdIn.readLine().trim
  println("Conference url: ")
  val conferenceUrl = StdIn.readLine().trim
  println("Talk title: ")
  val talkTitle = StdIn.readLine().trim
  println("mm/yy: ")
  val monthYear = parseMMYYtoInstant(StdIn.readLine().trim)
  println("City + country: ")
  val cityCountry = StdIn.readLine().trim

  println("Video url: ")
  val videoUrl = noneIfEmpty(StdIn.readLine().trim)
  val video = videoUrl.map(v => addVideo(Uri(v)))
  println("Slides url: ")
  val slidesUrl = noneIfEmpty(StdIn.readLine().trim).map(Uri(_))

  val tags = video match
    case Some(v) => v.tags
    case None =>
      println("Talk tags: ")
      readTags()

  val talk =
    Talk(UUID.randomUUID(), talkTitle, Uri(conferenceUrl), None, monthYear, tags, cityCountry, conferenceName, slidesUrl, video.map(_.id))
  PersistentModel.talks.add(talk)
  CommitDataFiles.run(s"Adding talk $talkTitle ($conferenceName)")

@main def test =
  println(parseMMYYtoInstant("11/23"))
  // println(addVideo(Uri("https://www.youtube.com/watch?v=Ia0J0yfxTCA")))

def addVideo(url: Uri): Video =
  val video = if url.toString.contains("youtube") then
    val browser = JsoupBrowser()
    val doc = browser.get(url.toString)

    val title = (doc >> attr("content")("meta[name=title]")).trim
    val coverImage = noneIfEmpty((doc >> attr("content")("meta[property=og:image]")).trim)
    val created = Instant.ofEpochMilli(
      (doc >> extractor("meta[itemprop=datePublished]", attr("content"), asDateTime("yyyy-MM-dd'T'HH:mm:ssZ"))).toInstant.getMillis
    )
    println("Video tags: ")
    val tags = readTags()

    Video(UUID.randomUUID(), title, url, coverImage.map(Uri(_)), created, tags)
  else
    println("Video tags: ")
    val tags = readTags()
    Video(UUID.randomUUID(), "???", url, None, Instant.now(), tags)

  PersistentModel.videos.add(video)
  video

private def noneIfEmpty(s: String): Option[String] = if s.isEmpty then None else Some(s)
private def readTags(): List[String] = StdIn.readLine().split(",").map(_.trim.toLowerCase).toList.filterNot(_.isEmpty)
private def parseMMYYtoInstant(dateString: String) =
  val formatter = DateTimeFormatter.ofPattern("MM/yy")
  val date = YearMonth.parse(dateString, formatter).atDay(1)
  val dateTime = date.atStartOfDay
  val zonedDateTime = dateTime.atZone(ZoneOffset.UTC)
  zonedDateTime.toInstant
