package com.ebeijia.nosql.redis.until

/**
 * @author zhicheng.xu
 *         redis key生成策略
 */
object RedisKeyMaker {

  def makeKey(clazz: Class[_], string: String) :String ={
    val tableName: String = clazz.getSimpleName
    val sb: StringBuilder = new StringBuilder
    sb.append(RedisConstant.KEY)
    sb.append(tableName)
    sb.append('_')
    sb.append(string)
    sb.toString()
  }

  def makeKey(key: String): Unit = {
    key.getBytes()
  }

}
