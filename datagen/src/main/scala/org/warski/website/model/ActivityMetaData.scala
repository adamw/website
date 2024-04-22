package org.warski.website.model

import java.time.Instant
import java.util.UUID

trait ActivityMetaData:
  def id: UUID
  def title: String
  def coverImage: Option[Uri]
  def when: Instant
  def tags: List[String]
