package org.warski.website.model

import java.time.Instant
import java.util.UUID

trait ActivityMetaData(id: UUID, title: String, url: Uri, coverImage: Option[Uri], created: Instant, tags: List[String])
