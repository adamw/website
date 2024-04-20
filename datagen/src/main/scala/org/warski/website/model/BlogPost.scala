package org.warski.website.model

import java.time.Instant
import java.util.UUID

enum BlogPost:
  case Local(id: UUID, title: String, url: Uri, coverImage: Option[Uri], when: Instant, tags: List[String], content: String)
      extends BlogPost
      with ActivityMetaData(id, title, url, coverImage, when, tags)
  case Remote(id: UUID, title: String, url: Uri, coverImage: Option[Uri], when: Instant, tags: List[String])
      extends BlogPost
      with ActivityMetaData(id, title, url, coverImage, when, tags)
