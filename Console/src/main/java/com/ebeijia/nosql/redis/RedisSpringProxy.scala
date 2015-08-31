package com.ebeijia.nosql.redis

import java.util.{List, Map}

/**
 * RedisSpringProxy代理jedis客户端功能与redis server通信
 * @author zhicheng.xu
 *         15/8/19
 */

trait RedisSpringProxy {
//  /**
//   * 将指定的value 与此映射中的指定key 关联, 保存到redis 中
//   */
//  def save(key: String, value: AnyRef)
//
//  /**
//   * 返回指定key 所映射的值. 如果redis 中不包含该key 的映射关系，则返回 null
//   */
//  def read(key: String): AnyRef
//
//  /**
//   * 如果存在一个key 的映射关系，则将其从redis 中移除
//   * @return 被删除 key 的数量
//   */
//  def delete(key: String): Long
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
//  def hSet(key: String, field: Array[Byte], value: Array[Byte]): Boolean
//
//  /**
//   * 返回redis哈希表 key 中给定域 field(map键) 的值
//   * @param key       redis哈希表 key
//   * @param field		 与指定值关联的field(map键)
//   * @return 返回指定 key 和指定 field(map键) 所映射的值,当给定 field(map键) 不存在或是给定 key 不存在时，返回 nil 。
//   */
//  def hGet(key: String, field: Array[Byte]): AnyRef
//
//  /**
//   * redis 存储 map
//   *
//   * @param key redis哈希表 key
//   * @param mapObject  map对象
//   */
//  def hmSet(key: String, mapObject: Map[AnyRef, AnyRef])
//
//  /**
//   * 返回reids 哈希表 key 中, 一个或多个给定域的值
//   * @param key		redis哈希表 key
//   * @param field		 与指定值关联的field(map键)
//   * @return 返回指定 key 和指定 field(map键) 所映射的值,当给定 field(map键) 不存在或是给定 key 不存在时，返回 nil 。
//   */
//  def hmGet(key: String, field: AnyRef*): List[AnyRef]
//
//  /**
//   * 返回redis 哈希表 key 中,所有的域和值
//   *
//   * @param key redis哈希表 key
//   * @return
//   */
//  @SuppressWarnings(Array("unchecked")) def hGetAll(key: String): Map[AnyRef, AnyRef]
//
//  /**
//   * 以<code>List</code> 数据结构存储到redis 中, 允许添加重复值
//   * @param key	redis哈希表 key
//   * @param values 存储一个值或多个值
//   * @return 返回列表长度
//   */
//  def lPush(key: String, values: AnyRef*): Long
//
//  /**
//   * 从redis 中取出 <code>List</code>结构数据, 参数中指定取值范围, 假如你有一个包含一百个元素的列表,
//   * 对该列表执行 LRANGE list 0 10, 结果是一个包含11个元素的列表.如果指定0 -1那么返回全部元素.
//   * @param key	redis哈希表 key
//   * @param begin	起始位置
//   * @param end	结束位置
//   * @return	List结果集或null
//   */
//  def lRange(key: String, begin: Long, end: Long): List[AnyRef]
//
//  /**
//   * 移除并返回列表 key 的头元素
//   * @param key	redis哈希表 key
//   * @return value
//   */
//  def lPop(key: String): AnyRef
//
//  /**
//   * 移除并返回列表 key 的尾元素
//   * @param key	redis哈希表 key
//   * @return value
//   */
//  def rPop(key: String): AnyRef
}