package org.warski.website.model

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker

import java.time.Instant
import java.util.UUID

case class Talk(
    id: UUID,
    title: String,
    url: Option[Uri],
    coverImage: Option[Uri],
    when: Instant,
    tags: List[String],
    where: String,
    conference: String,
    slides: Option[Uri],
    video: Option[UUID],
    audio: Option[Uri]
) extends ActivityMetaData(id, title, url, coverImage, when, tags)

object Talk:
  given JsonValueCodec[Vector[Talk]] = JsonCodecMaker.make