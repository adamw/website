package org.warski.website.model

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker

import java.time.Instant
import java.util.UUID

case class BlogPost(id: UUID, title: String, url: Uri, coverImage: Option[Uri], when: Instant, tags: List[String]) extends ActivityMetaData

object BlogPost:
  given JsonValueCodec[Vector[BlogPost]] = JsonCodecMaker.make
