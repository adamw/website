package org.warski.website

import java.io.{BufferedInputStream, BufferedOutputStream, FileInputStream, FileOutputStream, InputStream, OutputStream}
import scala.util.Using

object DataFiles:
  private val baseDir = "/Users/adamw/projects/website/core/data/"
  def read(fileName: String): InputStream = new BufferedInputStream(new FileInputStream(baseDir + fileName))
  def write(fileName: String, f: OutputStream => Unit): Unit =
    Using.resource(new BufferedOutputStream(new FileOutputStream(baseDir + fileName)))(f)
