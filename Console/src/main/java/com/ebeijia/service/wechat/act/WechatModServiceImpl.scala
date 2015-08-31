package com.ebeijia.service.wechat.act

import java.util.{LinkedHashMap, LinkedList, List, Map}

import com.ebeijia.dao.wechat.WechatModDao
import com.ebeijia.entity.wechat.{TblWechatMod, TblWechatRule}
import com.google.gson.{JsonArray, JsonObject, JsonParser}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.{CacheEvict, Cacheable}
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * WechatModServiceImpl
 * @author zhicheng.xu
 *         15/8/13
 */


@Service final class WechatModServiceImpl extends WechatModService {
  @Autowired
  private val wechatModDao: WechatModDao = null
  @Autowired
  private val wechatRuleService: WechatRuleService = null

  @Transactional
  @CacheEvict(value = Array("wechatModCache"), allEntries = true)
  def save(mchtId: String, modName: String, prizeJson: String, `type`: String): String = {
    val data: TblWechatMod = new TblWechatMod
    data.setActType(`type`)
    data.setMchtId(mchtId)
    data.setModName(modName)
    wechatModDao.save(data)
    val jsonparer: JsonParser = new JsonParser
    val json: JsonObject = jsonparer.parse(prizeJson).getAsJsonObject
    val array: JsonArray = json.get("prizes").getAsJsonArray
    for (i <- 0 until array.size()) {
      val ruleData: TblWechatRule = new TblWechatRule
      val jsonElement: JsonObject = array.get(i).getAsJsonObject
      val probability: String = jsonElement.get("probability").getAsString
      val prName: String = jsonElement.get("prName").getAsString
      val prize: String = jsonElement.get("prize").getAsString
      val winNum: String = jsonElement.get("winNum").getAsString
      ruleData.setPrize(prize)
      ruleData.setPrName(prName)
      ruleData.setProbability(probability.toInt)
      ruleData.setWinNum(winNum)
      wechatRuleService.save(ruleData, data.getModId)
    }
    null
  }

  @Transactional
  @CacheEvict(value = Array("wechatModCache"), allEntries = true)
  def upd(data: TblWechatMod): String = {
    wechatModDao.update(data)
    null
  }

  @Transactional
  @Cacheable(value = Array("wechatModCache"), key = "#root.method.name+#mchtId+#pageDate")
  def findBySql(mchtId: String, pageData: String): Map[String, AnyRef] = {
    val query: StringBuilder = new StringBuilder
    query.append(" from TblWechatMod ")
    query.append(" where 1=1")
    if (mchtId != null && !("" == mchtId)) {
      query.append(" AND mchtId like '%").append(mchtId).append("%'")
    }
    query.append(" order by createTime")
    val m: Map[String, AnyRef] = wechatModDao.findByPage(query.toString(), pageData)
    m
  }

  @Transactional
  @CacheEvict(value = Array("wechatModCache"), allEntries = true)
  def del(id: String): String = {
    wechatModDao.deleteById(id)
    null
  }

  @Transactional
  @Cacheable(value = Array("wechatModCache"), key = "#root.method.name+#id")
  def getById(id: String): TblWechatMod = {
    wechatModDao.getById(id)
  }

  @Transactional
  @Cacheable(value = Array("wechatModCache"), key = "#root.method.name+#mchtId+#type")
  def modList(mchtId: String, `type`: String): Map[String, AnyRef] = {
    val map: Map[String, AnyRef] = new LinkedHashMap[String, AnyRef]
    val hql: StringBuilder = new StringBuilder
    hql.append("FROM TblWechatMod WHERE mchtId='").append(mchtId).append("'")
    hql.append(" and actType ='").append(`type`).append("' ")
    val dataList: List[TblWechatMod] = wechatModDao.find(hql.toString())
    val list: List[AnyRef] = new LinkedList[AnyRef]
    import scala.collection.JavaConversions._
    for (data <- dataList) {
      val hashMap: Map[String, AnyRef] = new LinkedHashMap[String, AnyRef]
      hashMap.put("key", data.getModId + "-" + data.getModName)
      hashMap.put("value", data.getModId)
      hashMap.put("url", data.getUrl)
      list.add(hashMap)
    }
    map.put("info", list)
    map
  }

}
