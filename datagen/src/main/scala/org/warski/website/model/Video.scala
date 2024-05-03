package org.warski.website.model

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker

import java.time.Instant
import java.util.UUID

case class Video(id: UUID, title: String, url: Uri, coverImage: Option[Uri], when: Instant, tags: List[String], youtube: Option[String])
    extends ActivityMetaData

object Video:
  given JsonValueCodec[Vector[Video]] = JsonCodecMaker.make
