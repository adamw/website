package org.warski.website.model

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker

import java.time.Instant
import java.util.UUID

case class Video(id: UUID, title: String, url: Uri, coverImage: Option[Uri], created: Instant, tags: List[String])
    extends ActivityMetaData(id, title, url, coverImage, created, tags)
object Video:
  given JsonValueCodec[Vector[Video]] = JsonCodecMaker.make
