package com.ebeijia.service.wechat.subscribe

import java.util.{ArrayList, List, Map}

import com.ebeijia.dao.wechat.{WechatMchtInfDao, WechatSubscribeDao}
import com.ebeijia.entity.vo.wechat.{AccessToken, ModUsr, UsrToGroup, UsrsToGroup}
import com.ebeijia.entity.wechat.{TblWechatMchtInf, TblWechatSubscribe}
import com.ebeijia.service.wechat.core.{WechatMchtInfService, WechatTokenService}
import com.ebeijia.service.wechat.inter.UsrManager
import com.ebeijia.util.StringUtil
import com.ebeijia.util.wechat.WechatUtil
import net.sf.json.{JSONArray, JSONObject}
import org.ebeijia.tools.DateTime4J
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.{CacheEvict, Cacheable}
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * WechatSubscribeServiceImpl
 * @author zhicheng.xu
 *         15/8/14
 */

@Service("wechatSubscribeService")
final class WechatSubscribeServiceImpl extends WechatSubscribeService {
  @Autowired
  private val wechatMchtInfDao: WechatMchtInfDao = null
  @Autowired
  private val wechatMchtInfService: WechatMchtInfService = null
  @Autowired
  private val wechatTokenService: WechatTokenService = null
  @Autowired
  private val usrManager: UsrManager = null
  @Autowired
  private val wechatSubscribeDao: WechatSubscribeDao = null

  @Transactional
  @Cacheable(value = Array("wechatUsrCache"))
  def findBySql(mchtId: String, openid: String, pagaData: String): Map[String, AnyRef] = {
    val query: StringBuilder = new StringBuilder
    query.append("from TblWechatSubscribe")
    query.append(" where 1=1")
    if (mchtId != null && !("" == mchtId)) {
      query.append(" AND mchtId like '%").append(mchtId).append("%'")
    }
    if (openid != null && !("" == openid)) {
      query.append(" AND openid like '%").append(openid).append("%'")
    }
    query.append(" ORDER BY subscribeTime")
    wechatSubscribeDao.findByPage(query.toString, pagaData)
  }

  @Transactional
  @Cacheable(value = Array("wechatUsrCache"))
  def findByBatch(mchtId: String, groupId: String, pagaData: String): Map[String, AnyRef] = {
    val query: StringBuilder = new StringBuilder
    query.append("from TblWechatSubscribe")
    query.append(" where mchtId ='").append(mchtId).append("'")
    query.append(" and  subscribeTiny =1")
    if ("0" == groupId) {
      query.append(" AND groupId is not null")
    } else {
      query.append(" AND (groupId <>'").append(groupId).append("' or groupId is null )")
    }
    query.append(" ORDER BY subscribeTime")
    wechatSubscribeDao.findByPage(query.toString, pagaData)
  }

  @Transactional
  @Cacheable(value = Array("wechatUsrCache"), key = "#root.method.name+#mchtId+#groupId")
  def findByGroup(mchtId: String, groupId: String): List[TblWechatSubscribe] = {
    val query: StringBuilder = new StringBuilder
    query.append("from TblWechatSubscribe")
    query.append(" where mchtId ='").append(mchtId).append("'")
    query.append(" and  subscribeTiny =1")
    if ("0" == groupId) {
      query.append(" AND groupId is null")
    }
    else {
      query.append(" AND groupId ='").append(groupId).append("'")
    }
    query.append(" ORDER BY subscribeTime")
    wechatSubscribeDao.find(query.toString)
  }

  @CacheEvict(value = Array("wechatUsrCache"), allEntries = true)
  def synUsr: String = {
    val sb: StringBuilder = new StringBuilder
    sb.append("from TblWechatMchtInf ")
    val list: List[TblWechatMchtInf] = wechatMchtInfDao.find(sb.toString)
    var resp: String = null
    import scala.collection.JavaConversions._
    for (data <- list) {
      resp = this.SynchUsrIdList(data.getAppid, data.getAppsecret, data.getMchtId)
      if (resp != null) {
        resp
      }
    }
    resp
  }

  @Transactional
  @CacheEvict(value = Array("wechatUsrCache"), allEntries = true)
  def upRemark(tblWechatSubscribe: TblWechatSubscribe): String = {
    val data: TblWechatMchtInf = wechatMchtInfService.getById(tblWechatSubscribe.getMchtId)
    val at: AccessToken = wechatTokenService.getAccessToken(data.getAppid, data.getAppsecret)
    var result: String = null
    if (null != at) {
      val modUsr: ModUsr = new ModUsr
      modUsr.setOpenid(tblWechatSubscribe.getOpenid)
      modUsr.setRemark(tblWechatSubscribe.getRemarks)
      val jsonObject: JSONObject = usrManager.upRemark(modUsr, at.getToken)
      if (null != jsonObject) {
        if (WechatUtil.getWechatCallBackStatus(jsonObject.toString) != 0) {
          result = jsonObject.getString("errcode")
        } else {
          wechatSubscribeDao.update(tblWechatSubscribe)
        }
      }
    } else {
      result = "9999999"
    }
    result
  }

  private def SynchUsrIdList(appId: String, appSecret: String, mchtId: String): String = {
    val at: AccessToken = wechatTokenService.getAccessToken(appId, appSecret)
    var openid: String = ""
    var jsonObject: JSONObject = null
    var result: String = null
    if (null != at) {
      jsonObject = usrManager.getUsr(at.getToken)
      if (null != jsonObject) {
        if (WechatUtil.getWechatCallBackStatus(jsonObject.toString) == 0) {
          val usrId: JSONObject = jsonObject.get("data").asInstanceOf[JSONObject]
          if (usrId != null) {
            val jsonArr: JSONArray = usrId.get("openid").asInstanceOf[JSONArray]
            for (i <- 0 until jsonArr.size()) {
              var usrDef: JSONObject = null
              if (null != at) {
                usrDef = usrManager.getUsrInf(at.getToken, jsonArr.get(i).toString)
                this.saveWechatSubscribe(usrDef, mchtId)
                openid += ("'" + usrDef.get("openid") + "',")
              }
            }
          }
          if (!("" == openid)) {
            this.updateWechatSubscribe(openid, mchtId)
          }
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
  @CacheEvict(value = Array("wechatUsrCache"), allEntries = true)
  def updateWechatSubscribe(openid: String, mchtId: String) {
    val openidTmp = openid.substring(0, openid.length - 1)
    val sb: StringBuilder = new StringBuilder
    sb.append("update TblWechatSubscribe set subscribeTiny ='0' where openid in (")
    sb.append("select openid from TblWechatSubscribe where openid not in (").append(openidTmp).append("")
    sb.append(" )) and mchtId ='").append(mchtId).append("'")
    wechatSubscribeDao.updateAll(sb.toString)
  }

  private def saveWechatSubscribe(`def`: JSONObject, mchtId: String) {
    val subscribe: TblWechatSubscribe = new TblWechatSubscribe()
    subscribe.setMchtId(mchtId)
    subscribe.setOpenid(`def`.get("openid").toString)
    subscribe.setSubscribeTiny(`def`.get("subscribe").toString.toInt)
    subscribe.setNickname(`def`.get("nickname").toString)
    subscribe.setSex(`def`.get("sex").toString.toInt)
    subscribe.setCity(`def`.get("city").toString)
    subscribe.setCountry(`def`.get("country").toString)
    subscribe.setProvince(`def`.get("province").toString)
    subscribe.setLanguage(`def`.get("language").toString)
    subscribe.setHeadimgurl(`def`.get("headimgurl").toString)
    val subTime: Long = `def`.get("subscribe_time").asInstanceOf[Long]
    subscribe.setSubscribeTime(DateTime4J.timestampFormat(subTime))
    subscribe.setCreateTime(DateTime4J.getCurrentDateTime)
    this.addWechatSubscribe(subscribe)
  }

  @Transactional
  @CacheEvict(value = Array("wechatUsrCache"), allEntries = true)
  def addWechatSubscribe(wechatSubscribe: TblWechatSubscribe) {
    val hql: StringBuilder = new StringBuilder
    hql.append("from TblWechatSubscribe where mchtId ='").append(wechatSubscribe.getMchtId).append("' and")
    hql.append(" openid ='").append(wechatSubscribe.getOpenid).append("'")
    val list: List[TblWechatSubscribe] = wechatSubscribeDao.find(hql.toString)
    if (!list.isEmpty) {
      val data: TblWechatSubscribe = list.get(0)
      data.setNickname(wechatSubscribe.getNickname)
      data.setSex(wechatSubscribe.getSex)
      data.setCity(wechatSubscribe.getCity)
      data.setCountry(wechatSubscribe.getCountry)
      data.setSubscribeTiny(wechatSubscribe.getSubscribeTiny)
      data.setProvince(wechatSubscribe.getProvince)
      data.setLanguage(wechatSubscribe.getLanguage)
      data.setHeadimgurl(wechatSubscribe.getHeadimgurl)
      data.setSubscribeTime(wechatSubscribe.getSubscribeTime)
      data.setUpdateTime(DateTime4J.getCurrentDateTime)
      wechatSubscribeDao.update(data)
    } else {
      wechatSubscribeDao.save(wechatSubscribe)
    }
  }



  @Transactional
  @CacheEvict(value = Array("wechatUsrCache"), allEntries = true)
  def upGroup(tblWechatSubscribe: TblWechatSubscribe): String = {
    val data: TblWechatMchtInf = wechatMchtInfService.getById(tblWechatSubscribe.getMchtId)
    val at: AccessToken = wechatTokenService.getAccessToken(data.getAppid, data.getAppsecret)
    var result: String = null
    if (null != at) {
      val utg: UsrToGroup = new UsrToGroup
      utg.setOpenid(tblWechatSubscribe.getOpenid)
      utg.setTo_groupid(tblWechatSubscribe.getGroupId)
      val jsonObject: JSONObject = usrManager.mvUsr(utg, at.getToken)
      if (null != jsonObject) {
        if (WechatUtil.getWechatCallBackStatus(jsonObject.toString) != 0) {
          result = jsonObject.getString("errcode")
        } else {
          if (tblWechatSubscribe.getGroupId == "0") {
            tblWechatSubscribe.setGroupId("")
          }
          wechatSubscribeDao.update(tblWechatSubscribe)
        }
      }
    } else {
      result = "9999999"
    }
    result
  }

  @Transactional
  @CacheEvict(value = Array("wechatUsrCache"), allEntries = true)
  def batchUpGroup(mchtId: String, subList: String, groupId: String): String = {
    val data: TblWechatMchtInf = wechatMchtInfService.getById(mchtId)
    val at: AccessToken = wechatTokenService.getAccessToken(data.getAppid, data.getAppsecret)
    var result: String = null
    if (null != at) {
      val utg: UsrsToGroup = new UsrsToGroup
      val tmp: List[String] = new ArrayList[String]
      for (str <- subList.split(",")) {
        tmp.add(str)
      }
      utg.setOpenid_list(tmp)
      utg.setTo_groupid(groupId)
      val jsonObject: JSONObject = usrManager.mvUsrs(utg, at.getToken)
      if (null != jsonObject) {
        if (WechatUtil.getWechatCallBackStatus(jsonObject.toString) != 0) {
          result = jsonObject.getString("errcode")
        }
        else {
          //批量更新组别
          val sb: StringBuilder = new StringBuilder();
          var subTmp: String = "";
          for (i <- 0 until tmp.size()) {
            subTmp += "'" + tmp.get(i) + "',";
          }
          var groupIdTmp: String = ""
          if (groupId == "0") {
            groupIdTmp = ""
          } else {
            groupIdTmp = groupId
          }
          sb.append("update TblWechatSubscribe set groupId = '").append(groupIdTmp).append("' where ")
          sb.append("openid in (").append(subTmp.substring(0, subTmp.length - 1)).append(")")
          wechatSubscribeDao.updateAll(sb.toString)
        }
      }
    } else {
      result = "9999999"
    }
    result
  }

  @Transactional
  @CacheEvict(value = Array("wechatUsrCache"), allEntries = true)
  def upGroup(groupId: String) {
    val sb: StringBuilder = new StringBuilder
    sb.append("update TblWechatSubscribe set groupId = '' where groupId='").append(groupId).append("'")
    wechatSubscribeDao.updateAll(sb.toString)
  }

  @Transactional
  @Cacheable(value = Array("wechatUsrCache"), key = "#root.method.name+#id")
  def getById(id: String): TblWechatSubscribe = {
    val data: TblWechatSubscribe = wechatSubscribeDao.getById(id)
    if (data.getSubscribeTiny == 0) {
      return null
    }
    wechatSubscribeDao.getById(id)
  }
}
