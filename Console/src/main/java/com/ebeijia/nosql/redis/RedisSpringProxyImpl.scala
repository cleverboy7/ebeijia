package com.ebeijia.nosql.redis

import java.io.Serializable
import java.util.{ArrayList, HashMap, List, Map}

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataAccessException
import org.springframework.data.redis.connection.RedisConnection
import org.springframework.data.redis.core.{RedisCallback, RedisTemplate}
import org.springframework.data.redis.serializer.RedisSerializer
import org.springframework.stereotype.Service

/** 代理jedis客户端功能与redis server通信
  * RedisSpringProxyImpl
  * @author zhicheng.xu
  *         15/8/19
  */

@Service
class RedisSpringProxyImpl extends RedisSpringProxy {
//  @Autowired
//  private val redisTemplate: RedisTemplate[Serializable, Serializable] = null
//
//  /**
//   * 将指定的value 与此映射中的指定key 关联, 保存到redis 中
//   */
//  def save(key: String, value: AnyRef) {
//    redisTemplate.execute(new RedisCallback[AnyRef]() {
//      @throws(classOf[DataAccessException])
//      def doInRedis(connection: RedisConnection): AnyRef = {
//        connection.set(serializeKey(key), serializeValue(value))
//        null
//      }
//    })
//  }
//
//  /**
//   * 返回指定key 所映射的值. 如果redis 中不包含该key 的映射关系，则返回 null
//   */
//  def read(key: String): AnyRef = {
//    redisTemplate.execute(new RedisCallback[AnyRef]() {
//      @throws(classOf[DataAccessException])
//      def doInRedis(connection: RedisConnection): AnyRef = {
//        val keyBytes: Array[Byte] = serializeKey(key)
//        if (connection.exists(keyBytes)) {
//          val valueBytes: Array[Byte] = connection.get(keyBytes)
//          deserializeValue(valueBytes)
//        }
//        null
//      }
//    })
//  }
//
//  /**
//   * 如果存在一个key 的映射关系，则将其从redis 中移除
//   * @return 被删除 key 的数量
//   */
//  def delete(key: String): Long = {
//    redisTemplate.execute(new RedisCallback[Long]() {
//      def doInRedis(connection: RedisConnection): Long = {
//        connection.del(serializeKey(key))
//      }
//    })
//  }
//
//  /**
//   * <p>将哈希表 key 中的域 field(map键) 的值设为 value</p>
//   * <p>如果 key 不存在，一个新的哈希表被创建并进行 HSET 操作</p>
//   * <p>如果域 field 已经存在于哈希表中，旧值将被覆盖</p>
//   *
//   * @param key		redis哈希表 key
//   * @param field		 与指定值关联的field(map键)
//   * @param value		与指定field(map键)关联的值
//   * @return 如果 field 是哈希表中的一个新建域，并且值设置成功, 返回 true, <br>如果哈希表中域 field(map键) 已经存在且旧值已被新值覆盖，返回false ,
//   *         操作失败返回null 。
//   */
//  def hSet(key: String, field: Array[Byte], value: Array[Byte]): Boolean = {
//    redisTemplate.execute(new RedisCallback[Boolean]() {
//      @throws(classOf[DataAccessException])
//      def doInRedis(connection: RedisConnection): Boolean = {
//        connection.hSet(serializeKey(key), field, value)
//      }
//    })
//  }
//
//  /**
//   * 返回redis哈希表 key 中给定域 field(map键) 的值
//   * @param key       redis哈希表 key
//   * @param field		 与指定值关联的field(map键)
//   * @return 返回指定 key 和指定 field(map键) 所映射的值,当给定 field(map键) 不存在或是给定 key 不存在时，返回 nil 。
//   */
//  def hGet(key: String, field: Array[Byte]): AnyRef = {
//    redisTemplate.execute(new RedisCallback[AnyRef]() {
//      @throws(classOf[DataAccessException])
//      def doInRedis(connection: RedisConnection): AnyRef = {
//        val value: Array[Byte] = connection.hGet(serializeKey(key), field)
//        deserializeValue(value).asInstanceOf
//      }
//    })
//  }
//
//  /**
//   * redis 存储 map
//   *
//   * @param key redis哈希表 key
//   * @param mapObject  map对象
//   */
//  def hmSet(key: String, mapObject: Map[AnyRef, AnyRef]) {
//    redisTemplate.execute(new RedisCallback[AnyRef]() {
//      @throws(classOf[DataAccessException])
//      def doInRedis(connection: RedisConnection): AnyRef = {
//        var mapByte: Map[Array[Byte], Array[Byte]] = new HashMap[Array[Byte], Array[Byte]](mapObject.size)
//        import scala.collection.JavaConversions._
//        for (entry <- mapObject.entrySet) {
//          val mapKey: Array[Byte] = serializeValue(entry.getKey)
//          val mapValue: Array[Byte] = serializeValue(entry.getValue)
//          mapByte.put(mapKey, mapValue)
//        }
//        connection.hMSet(serializeKey(key), mapByte)
//        mapByte = null
//        null
//      }
//    })
//  }
//
//  /**
//   * 返回reids 哈希表 key 中, 一个或多个给定域的值
//   * @param key		redis哈希表 key
//   * @param field		 与指定值关联的field(map键)
//   * @return 返回指定 key 和指定 field(map键) 所映射的值,当给定 field(map键) 不存在或是给定 key 不存在时，返回 nil 。
//   */
//  def hmGet(key: String, field: AnyRef*): List[AnyRef] = {
//    redisTemplate.execute(new RedisCallback[List[AnyRef]]() {
//      @throws(classOf[DataAccessException])
//      def doInRedis(connection: RedisConnection): List[AnyRef] = {
//        var redisRet: List[Array[Byte]] = null
//        val ret: List[AnyRef] = new ArrayList[AnyRef]
//        if (field.length > 1) {
//          val fields: Array[Array[Byte]] = new Array[Array[Byte]](field.length)
//          var i: Int = 0
//          for (objectTmp <- field) {
//            fields(i) = serializeValue(objectTmp)
//            i += 1
//          }
//          redisRet = connection.hMGet(serializeKey(key), fields.asInstanceOf)
//        }
//        else {
//          redisRet = connection.hMGet(serializeKey(key), serializeValue(field(0)))
//        }
//        if (null != redisRet) {
//          import scala.collection.JavaConversions._
//          for (value <- redisRet) {
//            if (value != null) ret.add(deserializeValue(value).asInstanceOf)
//          }
//        }
//        if (ret.size > 0) ret else null
//      }
//    })
//  }
//
//  /**
//   * 返回redis 哈希表 key 中,所有的域和值
//   *
//   * @param key redis哈希表 key
//   * @return
//   */
//  @SuppressWarnings(Array("unchecked")) def hGetAll(key: String): Map[AnyRef, AnyRef] = {
//    redisTemplate.execute(new RedisCallback[AnyRef]() {
//      @throws(classOf[DataAccessException])
//      def doInRedis(connection: RedisConnection): AnyRef = {
//        var mapByte: Map[Array[Byte], Array[Byte]] = connection.hGetAll(serializeKey(key))
//        val mapObject: Map[AnyRef, AnyRef] = new HashMap[AnyRef, AnyRef](mapByte.size)
//        import scala.collection.JavaConversions._
//        for (entry <- mapByte.entrySet) {
//          val mapKey: AnyRef = deserializeValue(entry.getKey).asInstanceOf
//          val mapValue: AnyRef = deserializeValue(entry.getValue).asInstanceOf
//          mapObject.put(mapKey, mapValue)
//        }
//        mapByte = null
//        mapObject
//      }
//    }).asInstanceOf[Map[AnyRef, AnyRef]]
//  }
//
//  /**
//   * 以<code>List</code> 数据结构存储到redis 中, 允许添加重复值
//   * @param key	redis哈希表 key
//   * @param values 存储一个值或多个值
//   * @return 返回列表长度
//   */
//  def lPush(key: String, values: AnyRef*): Long = {
//    redisTemplate.execute(new RedisCallback[AnyRef]() {
//      @throws(classOf[DataAccessException])
//      def doInRedis(connection: RedisConnection): AnyRef = {
//        var count: Long = 0l
//        for (value <- values) {
//          val keyByte: Array[Byte] = serializeKey(key)
//          val `val`: Array[Byte] = serializeValue(value)
//          count = connection.lPush(keyByte, `val`)
//        }
//        count.asInstanceOf
//      }
//    }).asInstanceOf[Long]
//  }
//
//  /**
//   * 从redis 中取出 <code>List</code>结构数据, 参数中指定取值范围, 假如你有一个包含一百个元素的列表,
//   * 对该列表执行 LRANGE list 0 10, 结果是一个包含11个元素的列表.如果指定0 -1那么返回全部元素.
//   * @param key	redis哈希表 key
//   * @param begin	起始位置
//   * @param end	结束位置
//   * @return	List结果集或null
//   */
//  def lRange(key: String, begin: Long, end: Long): List[AnyRef] = {
//    redisTemplate.execute(new RedisCallback[List[AnyRef]]() {
//      @throws(classOf[DataAccessException])
//      def doInRedis(connection: RedisConnection): List[AnyRef] = {
//        val retByteLst: List[Array[Byte]] = connection.lRange(serializeKey(key), begin, end)
//        if (null != retByteLst) {
//          val retValLst: List[AnyRef] = new ArrayList[AnyRef](retByteLst.size)
//          import scala.collection.JavaConversions._
//          for (retValByte <- retByteLst) {
//            val `val`: AnyRef = deserializeValue(retValByte).asInstanceOf
//            retValLst.add(`val`)
//          }
//          retValLst
//        }
//        null
//      }
//    })
//  }
//
//  /**
//   * 移除并返回列表 key 的头元素
//   * @param key	redis哈希表 key
//   * @return value
//   */
//  def lPop(key: String): AnyRef = {
//    redisTemplate.execute(new RedisCallback[AnyRef]() {
//      @throws(classOf[DataAccessException])
//      def doInRedis(connection: RedisConnection): AnyRef = {
//        val value: Array[Byte] = connection.lPop(serializeKey(key))
//        deserializeValue(value).asInstanceOf
//      }
//    })
//  }
//
//  /**
//   * 移除并返回列表 key 的尾元素
//   * @param key	redis哈希表 key
//   * @return value
//   */
//  def rPop(key: String): AnyRef = {
//    redisTemplate.execute(new RedisCallback[AnyRef]() {
//      @throws(classOf[DataAccessException])
//      def doInRedis(connection: RedisConnection): AnyRef = {
//        val value: Array[Byte] = connection.rPop(serializeKey(key))
//        deserializeValue(value).asInstanceOf
//      }
//    })
//  }
//
//  protected def serializeKey(key: String): Array[Byte] = {
//    redisTemplate.getStringSerializer.serialize(key)
//  }
//
//  @SuppressWarnings(Array("unchecked"))
//  protected def serializeValue(value: AnyRef): Array[Byte] = {
//    val reidsSerializer: RedisSerializer[AnyRef] = redisTemplate.getValueSerializer.asInstanceOf[RedisSerializer[AnyRef]]
//    reidsSerializer.serialize(value)
//  }
//
//  protected def deserializeValue(value: Array[Byte]): Any = {
//    redisTemplate.getValueSerializer.deserialize(value)
//  }
}
