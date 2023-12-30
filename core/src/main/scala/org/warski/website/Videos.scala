package org.warski.website

import com.github.plokhotnyuk.jsoniter_scala.core.{readFromStream, writeToStream}
import org.warski.website.model.Video

import scala.util.Using

object Videos:
  def read(): Vector[Video] =
    Using.resource(DataFiles.read("videos.json"))(is => readFromStream[Vector[Video]](is))

  def write(videos: Vector[Video]): Unit =
    DataFiles.write("videos.json", writeToStream[Vector[Video]](videos, _, jsoniterWriteConfig))

  def add(v: Video): Unit =
    val videos = read()
    write(videos :+ v)
