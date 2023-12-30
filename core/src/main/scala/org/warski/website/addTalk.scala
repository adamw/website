package org.warski.website

import net.ruippeixotog.scalascraper.browser.JsoupBrowser
import net.ruippeixotog.scalascraper.dsl.DSL.*
import net.ruippeixotog.scalascraper.dsl.DSL.Extract.*
import net.ruippeixotog.scalascraper.dsl.DSL.Parse.*
import org.warski.website.model.{Uri, Video}

import java.time.Instant
import java.util.UUID
import scala.io.StdIn

@main def addTalk(url: String): Unit =
  // read url, create Talk
  // read from file
  // add
  // write to file
  // commit

  println("Conference name: ")
  val conferenceName = StdIn.readLine().trim
  println("Conference url: ")
  val conferenceUrl = StdIn.readLine().trim
  println("Talk title: ")
  val talkTitle = StdIn.readLine().trim
  println("Month + year: ")
  val monthYear = StdIn.readLine().trim
  println("City + country: ")
  val cityCountry = StdIn.readLine().trim

  println("Video url: ")
  val videoUrl = StdIn.readLine().trim
  println("Slides url: ")
  val slidesUrl = StdIn.readLine().trim

//  val video = if (videoUrl.nonEmpty) Some(addVideo(videoUrl)) else None
//
//  Talk(ActivityMetaData(UUID.randomUUID(), talkTitle, conferenceUrl, None, created, tags)

  ()

@main def test = println(addVideo(Uri("https://www.youtube.com/watch?v=Ia0J0yfxTCA")))

def addVideo(url: Uri): Video =
  val browser = JsoupBrowser()
  val doc = browser.get(url.toString)

  val title = (doc >> attr("content")("meta[name=title]")).trim
  val coverImage = noneIfEmpty((doc >> attr("content")("meta[property=og:image]")).trim)
  val created = Instant.ofEpochMilli(
    (doc >> extractor("meta[itemprop=datePublished]", attr("content"), asDateTime("yyyy-MM-dd'T'HH:mm:ssZ"))).toInstant.getMillis
  )
  println("Tags: ")
  // TODO val tags = readTags()
  val tags = Nil

  val video = Video(UUID.randomUUID(), title, url, coverImage.map(Uri(_)), created, tags)
  Videos.add(video)
  video

private def noneIfEmpty(s: String): Option[String] = if s.isEmpty then None else Some(s)
private def readTags(): List[String] = StdIn.readLine().split(",").map(_.trim.toLowerCase).toList
