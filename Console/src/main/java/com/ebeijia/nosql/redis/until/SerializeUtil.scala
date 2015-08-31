package com.ebeijia.nosql.redis.until

import java.io.{ByteArrayInputStream, ByteArrayOutputStream, ObjectInputStream, ObjectOutputStream}

import org.slf4j.{Logger, LoggerFactory}

/**
 * SerializeUtil对象 <-> bytes[] 转换
 * @author zhicheng.xu
 *         15/8/20
 */
class SerializeUtil {
}

object SerializeUtil {
  private val log: Logger = LoggerFactory.getLogger(classOf[SerializeUtil])

  def serialize(`object`: AnyRef): Array[Byte] = {
    var oos: ObjectOutputStream = null
    var baos: ByteArrayOutputStream = null
    val bytes: Array[Byte] = baos.toByteArray
    try {
      baos = new ByteArrayOutputStream
      oos = new ObjectOutputStream(baos)
      oos.writeObject(`object`)
      oos.flush
    }
    catch {
      case e: Exception =>
        log.error("SerializeUtil method serialize {}", e)
    }
    bytes
  }

  def unserialize(bytes: Array[Byte]): AnyRef = {
    var bais: ByteArrayInputStream = null
    var ois: ObjectInputStream = null
    try {
      bais = new ByteArrayInputStream(bytes)
      ois = new ObjectInputStream(bais)
    }
    catch {
      case e: Exception =>
        log.error("SerializeUtil method unserialize {}", e)
    }
    ois.readObject
  }
}


