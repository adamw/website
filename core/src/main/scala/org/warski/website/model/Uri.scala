package org.warski.website.model

import com.github.plokhotnyuk.jsoniter_scala.core.{JsonReader, JsonValueCodec, JsonWriter}

opaque type Uri = String

object Uri:
  def apply(uri: String): Uri = uri
  extension (uri: Uri) def toString: String = uri

  given JsonValueCodec[Uri] = new JsonValueCodec[Uri]:
    def decodeValue(in: JsonReader, default: Uri): Uri = Uri(in.readString(null))
    def encodeValue(x: Uri, out: JsonWriter): Unit = out.writeVal(x)
    val nullValue: Uri = Uri(null)
