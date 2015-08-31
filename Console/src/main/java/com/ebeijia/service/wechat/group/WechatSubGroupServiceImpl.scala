package com.ebeijia.service.wechat.group

import java.util.{LinkedHashMap, LinkedList, List, Map}

import com.ebeijia.dao.wechat.WechatSubGroupDao
import com.ebeijia.entity.vo.wechat.{AccessToken, Group}
import com.ebeijia.entity.wechat.{TblWechatMchtInf, TblWechatSubGroup, TblWechatSubGroupId}
import com.ebeijia.service.wechat.core.{WechatMchtInfService, WechatTokenService}
import com.ebeijia.service.wechat.inter.GroupManager
import com.ebeijia.service.wechat.subscribe.WechatSubscribeService
import com.ebeijia.util.wechat.WechatUtil
import net.sf.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.{CacheEvict, Cacheable}
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * WechatSubGroupServiceImpl
 * @author zhicheng.xu
 *         15/8/13
 */


@Service final class WechatSubGroupServiceImpl extends WechatSubGroupService {
  @Autowired
  private val wechatSubGroupDao: WechatSubGroupDao = null
  @Autowired
  private val wechatSubscribeService: WechatSubscribeService = null
  @Autowired
  private val wechatMchtInfService: WechatMchtInfService = null
  @Autowired
  private val wechatTokenService: WechatTokenService = null
  @Autowired
  private val groupManager: GroupManager = null

  @Transactional
  @CacheEvict(value = Array("wechatSubGroupCache"), allEntries = true)
  def save(mchtId: String, name: String): String = {
    var result: String = null
    val tblWechatMchtInf: TblWechatMchtInf = wechatMchtInfService.getById(mchtId)
    val at: AccessToken = wechatTokenService.getAccessToken(tblWechatMchtInf.getAppid, tblWechatMchtInf.getAppsecret)
    if (null != at) {
      val jsonObject: JSONObject = groupManager.createGroup(name, at.getToken)
      if (null != jsonObject) {
        if (WechatUtil.getWechatCallBackStatus(jsonObject.toString) != 0) {
          result = jsonObject.getString("errcode")
        } else {
          val group: String = jsonObject.getJSONObject("group").getString("id")
          val id: TblWechatSubGroupId = new TblWechatSubGroupId
          id.setGroupId(group)
          id.setMchtId(mchtId)
          val data: TblWechatSubGroup = new TblWechatSubGroup
          data.setId(id)
          data.setName(name)
          wechatSubGroupDao.save(data)
        }
      } else {
        result = "8888888"
      }
    } else {
      result = "9999999"
    }
    result
  }

  @Transactional
  @CacheEvict(value = Array("wechatSubGroupCache"), allEntries = true)
  def update(mchtId: String, groupId: String, name: String): String = {
    var result: String = null
    val tblWechatMchtInf: TblWechatMchtInf = wechatMchtInfService.getById(mchtId)
    val at: AccessToken = wechatTokenService.getAccessToken(tblWechatMchtInf.getAppid, tblWechatMchtInf.getAppsecret)
    if (null != at) {
      val group: Group = new Group
      group.setId(groupId)
      group.setName(name)
      val jsonObject: JSONObject = groupManager.modGroup(group, at.getToken)
      if (null != jsonObject) {
        if (!("0" == jsonObject.getString("errcode"))) {
          result = jsonObject.getString("errcode")
        }
        else {
          val id: TblWechatSubGroupId = new TblWechatSubGroupId
          id.setGroupId(groupId)
          id.setMchtId(mchtId)
          val data: TblWechatSubGroup = new TblWechatSubGroup
          data.setId(id)
          data.setName(name)
          wechatSubGroupDao.update(data)
        }
      }
    } else {
      result = "9999999"
    }
    result
  }

  @Transactional
  @CacheEvict(value = Array("wechatSubGroupCache"), allEntries = true)
  def del(mchtId: String, groupId: String): String = {
    var result: String = null
    val tblWechatMchtInf: TblWechatMchtInf = wechatMchtInfService.getById(mchtId)
    val at: AccessToken = wechatTokenService.getAccessToken(tblWechatMchtInf.getAppid, tblWechatMchtInf.getAppsecret)
    if (null != at) {
      val jsonObject: JSONObject = groupManager.delGroup(groupId, at.getToken)
      if (null != jsonObject) {
        if (!("0" == jsonObject.getString("errcode"))) {
          result = jsonObject.getString("errcode")
        }
        else {
          val id: TblWechatSubGroupId = new TblWechatSubGroupId
          id.setGroupId(groupId)
          id.setMchtId(mchtId)
          wechatSubGroupDao.delete(id)
          wechatSubscribeService.upGroup(groupId)
        }
      }
    } else {
      result = "9999999"
    }
    result
  }

  @Transactional
  @Cacheable(value = Array("wechatSubGroupCache"))
  def findBySql(name: String, pageData: String): Map[String, AnyRef] = {
    val query: StringBuilder = new StringBuilder
    query.append(" from TblWechatSubGroup ")
    query.append(" where 1=1")
    if (name != null && !("" == name)) {
      query.append(" AND name like '%").append(name).append("%'")
    }
    query.append(" order by id.groupId")
    val m: Map[String, AnyRef] = wechatSubGroupDao.findByPage(query.toString, pageData)
    m
  }

  @Transactional
  @Cacheable(value = Array("wechatSubGroupCache"), key = "#root.method.name+#mchtId")
  def listGroupByMchtId(mchtId: String): Map[String, AnyRef] = {
    val map: Map[String, AnyRef] = new LinkedHashMap[String, AnyRef]
    val sb: StringBuilder = new StringBuilder
    sb.append("FROM TblWechatSubGroup  where id.mchtId ='")
    sb.append(mchtId).append("' order by id.groupId")
    val list: List[TblWechatSubGroup] = wechatSubGroupDao.find(sb.toString)
    val result: List[AnyRef] = new LinkedList[AnyRef]
    import scala.collection.JavaConversions._
    for (data <- list) {
      val hashMap: Map[String, AnyRef] = new LinkedHashMap[String, AnyRef]
      hashMap.put("key", data.getId.getGroupId + "-" + data.getName)
      hashMap.put("value", data.getId.getGroupId)
      result.add(hashMap)
    }
    map.put("info", result)
    map
  }

  @Transactional
  @Cacheable(value = Array("wechatSubGroupCache"), key = "#root.method.name+#id")
  def getById(id: String): TblWechatSubGroup = {
    wechatSubGroupDao.getById(id)
  }

  @Transactional
  @Cacheable(value = Array("wechatSubGroupCache"), key = "#root.method.name+#mchtId")
  def findCount(mchtId: String): Int = {
    val sb: StringBuilder = new StringBuilder
    sb.append("From TblWechatSubGroup where id.mchtId ='").append(mchtId).append("'")
    val total: Int = wechatSubGroupDao.getTotalRows(sb.toString)
    total
  }
}