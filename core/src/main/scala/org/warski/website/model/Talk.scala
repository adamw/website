package org.warski.website.model

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker

import java.time.Instant
import java.util.UUID

case class Talk(
    id: UUID,
    title: String,
    url: Uri,
    coverImage: Option[Uri],
    created: Instant,
    tags: List[String],
    where: String,
    slides: Option[Uri],
    video: Option[UUID]
) extends ActivityMetaData(id, title, url, coverImage, created, tags)
object Talk:
  given JsonValueCodec[Talk] = JsonCodecMaker.make
