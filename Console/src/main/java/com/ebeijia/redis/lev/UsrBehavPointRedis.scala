package com.ebeijia.redis.lev

import java.util.{ArrayList, List}

import com.ebeijia.dao.usr.UsrBehavPointDao
import com.ebeijia.entity.TblUsrBehavPoint
import com.ebeijia.nosql.redis.RedisSpringProxy
import com.ebeijia.nosql.redis.until.RedisKeyMaker
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * UsrBehavPointRedis
 * @author zhicheng.xu
 *         15/8/20
 */

@Component
class UsrBehavPointRedis {

//  @Autowired
//  private val usrBehavPointDao: UsrBehavPointDao = null
//  @Autowired
//  private val redisSpringProxy: RedisSpringProxy = null
//
//
//  def init(): Unit = {
//    try {
//      val l: List[TblUsrBehavPoint] = usrBehavPointDao.findAll(classOf[TblUsrBehavPoint])
//      val flag0List: List[TblUsrBehavPoint] = new ArrayList[TblUsrBehavPoint]()
//      val flag1List: List[TblUsrBehavPoint] = new ArrayList[TblUsrBehavPoint]()
//      import scala.collection.JavaConversions._
//      for (behav <- l) {
//        if ("0".equals(behav.getFlag)) {
//          flag0List.add(behav)
//        } else {
//          flag1List.add(behav)
//        }
//      }
//      redisSpringProxy.save(RedisKeyMaker.makeKey(classOf[TblUsrBehavPoint], "flag0"), flag0List)
//      redisSpringProxy.save(RedisKeyMaker.makeKey(classOf[TblUsrBehavPoint], "flag1"), flag1List)
//    } catch {
//      case e: Exception => {
//        val log: Logger = LoggerFactory.getLogger(classOf[UsrBehavPointRedis])
//        log.info("===========用户行为积分数据 缓存 初始化 异常==========", e)
//      }
//    }
//  }
//
//  // 重新初始化
//  def reload: Unit = {
//    init()
//  }
//
//  @throws(classOf[Exception])
//  def get(str: String): List[TblUsrBehavPoint] = {
//    redisSpringProxy.read(str).asInstanceOf
//  }
//
//  def delKey(key: String): Unit = {
//    redisSpringProxy.delete(key)
//  }
}
