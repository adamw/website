package org.warski.website.persistence

import com.github.plokhotnyuk.jsoniter_scala.core.{JsonValueCodec, WriterConfig, readFromStream, writeToStream}
import org.warski.website.model.{Talk, Video}

import scala.util.Using

case class PersistentModel[T](dataFile: String, codec: JsonValueCodec[Vector[T]]):
  given JsonValueCodec[Vector[T]] = codec
  def read(): Vector[T] =
    Using.resource(DataFiles.read(dataFile))(is => readFromStream[Vector[T]](is))

  def write(ts: Vector[T]): Unit =
    DataFiles.write(dataFile, writeToStream[Vector[T]](ts, _, jsoniterWriteConfig))

  def add(t: T): Unit =
    println("Adding " + t)
    val ts = read()
    write(ts :+ t)

object PersistentModel:
  val videos: PersistentModel[Video] = PersistentModel[Video]("videos.json", summon)
  val talks: PersistentModel[Talk] = PersistentModel[Talk]("talks.json", summon)

private val jsoniterWriteConfig = WriterConfig.withIndentionStep(2)