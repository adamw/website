package org.warski.website.persistence

import java.io.*
import scala.util.Using

object DataFiles:
  private[persistence] val baseDir = "/Users/adamw/projects/website/datagen/data/"
  def read(fileName: String): InputStream = new BufferedInputStream(new FileInputStream(baseDir + fileName))
  def write(fileName: String, f: OutputStream => Unit): Unit =
    Using.resource(new BufferedOutputStream(new FileOutputStream(baseDir + fileName)))(f)
