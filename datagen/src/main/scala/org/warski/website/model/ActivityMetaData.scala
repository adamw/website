package org.warski.website.model

import java.time.Instant
import java.util.UUID

trait ActivityMetaData(id: UUID, title: String, url: Option[Uri], coverImage: Option[Uri], when: Instant, tags: List[String])
