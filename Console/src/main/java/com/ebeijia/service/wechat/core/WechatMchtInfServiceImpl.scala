package com.ebeijia.service.wechat.core

import java.util.{LinkedHashMap, LinkedList, List, Map}

import com.ebeijia.dao.mcht.MchtInfDao
import com.ebeijia.dao.wechat.WechatMchtInfDao
import com.ebeijia.entity.wechat.TblWechatMchtInf
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.{CacheEvict, Cacheable}
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * WechatMchtInfServiceImpl
 * @author zhicheng.xu
 *         15/8/13
 */


@Service
final class WechatMchtInfServiceImpl extends WechatMchtInfService {
  @Autowired
  private val wechatMchtInfDao: WechatMchtInfDao = null
  @Autowired
  private val mchtInfDao: MchtInfDao = null

  @Transactional
  @CacheEvict(value = Array("wechatMchtCache"), allEntries = true)
  def updateWechatMchtInf(wechatMchtInf: TblWechatMchtInf) {
    wechatMchtInfDao.saveOrUpdate(wechatMchtInf)
  }

  @Transactional
  @CacheEvict(value = Array("wechatMchtCache"), allEntries = true)
  def deleteWechatMchtInf(wechatMchtInf: TblWechatMchtInf) {
    wechatMchtInfDao.delete(wechatMchtInf)
  }

  @Transactional
  @CacheEvict(value = Array("wechatMchtCache"), allEntries = true)
  def addWechatMchtInf(wechatMchtInf: TblWechatMchtInf): String = {
    val hql: StringBuilder = new StringBuilder
    hql.append("from TblWechatMchtInf where mchtId ='").append(wechatMchtInf.getMchtId).append("'")
    hql.append(" or  nickName ='").append(wechatMchtInf.getNickname).append("'")
    hql.append(" or  appId ='").append(wechatMchtInf.getAppid).append("'")
    hql.append(" or  appsecret ='").append(wechatMchtInf.getAppsecret).append("'")
    val list: List[TblWechatMchtInf] = wechatMchtInfDao.find(hql.toString())
    var respMsg: String = ""
    if (list.isEmpty) {
      wechatMchtInfDao.saveOrUpdate(wechatMchtInf)
    }
    else {
      respMsg = "0"
    }
    respMsg
  }

  @Transactional
  @Cacheable(value = Array("wechatMchtCache"), key = "#root.method.name")
  def queryWechatMchtInfList: List[TblWechatMchtInf] = {
    wechatMchtInfDao.getWechatMchtInfList
  }

  @Transactional
  @Cacheable(value = Array("wechatMchtCache"), key = "#root.method.name+#id")
  def getById(id: String): TblWechatMchtInf = {
    wechatMchtInfDao.getById(id)
  }

  @Transactional
  @Cacheable(value = Array("wechatMchtCache"), key = "#root.method.name")
  def findMchtList: Map[String, AnyRef] = {
    val map: Map[String, AnyRef] = new LinkedHashMap[String, AnyRef]
    val mchtList: List[Array[AnyRef]] = mchtInfDao.findMchtList
    val list: List[AnyRef] = new LinkedList[AnyRef]
    import scala.collection.JavaConversions._
    for (obj <- mchtList) {
      val hashMap: Map[String, AnyRef] = new LinkedHashMap[String, AnyRef]
      hashMap.put("key", obj(0) + "-" + obj(1))
      hashMap.put("value", obj(0))
      list.add(hashMap)
    }
    map.put("info", list)
    map
  }

  @Transactional
  @Cacheable(value = Array("wechatMchtCache"))
  def findBySql(mchtId: String, pagaData: String): Map[String, AnyRef] = {
    val query: StringBuffer = new StringBuffer
    query.append("from TblWechatMchtInf")
    query.append(" where 1=1")
    if (mchtId != null && !("" == mchtId)) {
      query.append(" AND id.mchtId like '%").append(mchtId).append("%'")
    }
    query.append(" ORDER BY id.mchtId")
    val m: Map[String, AnyRef] = wechatMchtInfDao.findByPage(query.toString, pagaData)
    m
  }

  @Transactional
  @Cacheable(value = Array("wechatMchtCache"), key = "#root.method.name+#nickName")
  def findByNikeName(nickName: String): TblWechatMchtInf = {
    val sb: StringBuilder = new StringBuilder
    sb.append("from TblWechatMchtInf where nickName = '").append(nickName).append("'")
    val list: List[TblWechatMchtInf] = wechatMchtInfDao.find(sb.toString)
    if (list.isEmpty) {
      null
    }
    else {
      list.get(0)
    }
  }

  @Transactional
  @Cacheable(value = Array("wechatMchtCache"), key = "#root.method.name+#appid")
  def getByAppid(appid: String): TblWechatMchtInf = {
    val sb: StringBuilder = new StringBuilder
    sb.append("from TblWechatMchtInf where app_id = '").append(appid).append("'")
    val list: List[TblWechatMchtInf] = wechatMchtInfDao.find(sb.toString)
    if (list.isEmpty) {
      null
    }
    else {
      list.get(0)
    }
  }
}
