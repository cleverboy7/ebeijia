package com.ebeijia.service.wechat.media

import java.util.{LinkedHashMap, LinkedList, List, Map}

import com.ebeijia.dao.wechat.{WechatFodderDao, WechatMchtInfDao}
import com.ebeijia.entity.vo.wechat.AccessToken
import com.ebeijia.entity.wechat.{TblWechatFodder, TblWechatMchtInf}
import com.ebeijia.service.wechat.core.WechatTokenService
import com.ebeijia.service.wechat.inter.MediaManager
import com.ebeijia.util.wechat.WechatUtil
import net.sf.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.{CacheEvict, Cacheable}
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
/**
 * WechatFodderServiceImpl
 * @author zhicheng.xu
 *         15/8/14
 */


@Service
final class WechatFodderServiceImpl extends WechatFodderService {
  @Autowired
  private val wechatFodderDao: WechatFodderDao = null
  @Autowired
  private val wechatMchtInfDao: WechatMchtInfDao = null
  @Autowired
  private val wechatTokenService: WechatTokenService = null
  @Autowired
  private val mediaManager: MediaManager = null

  @Transactional
  @Cacheable(value = Array("wechatFooderCache"))
  def findBySql(mchtId: String, `type`: String, mediaType: String, aoData: String): Map[String, AnyRef] = {
    val query: StringBuilder = new StringBuilder
    query.append("from TblWechatFodder")
    query.append(" where type <> 'news' ")
    if (mchtId != null && !("" == mchtId)) {
      query.append(" AND mchtId like '%").append(mchtId).append("%'")
    }
    if (`type` != null && !("" == `type`)) {
      query.append(" AND type ='").append(`type`).append("'")
    }
    if (mediaType != null && !("" == mediaType)) {
      query.append(" AND mediaType like '%").append(mediaType).append("%'")
    }
    query.append(" ORDER BY createTime desc")
    val m: Map[String, AnyRef] = wechatFodderDao.findByPage(query.toString, aoData)
    m
  }

  @Transactional
  @Cacheable(value = Array("wechatFooderCache"))
  def findBySqltoNews(mchtId: String, aoData: String): Map[String, AnyRef] = {
    val query: StringBuilder = new StringBuilder
    query.append("from TblWechatFodder")
    query.append(" where type ='news' ")
    if (mchtId != null && !("" == mchtId)) {
      query.append(" AND mchtId like '%").append(mchtId).append("%'")
    }
    val m: Map[String, AnyRef] = wechatFodderDao.findByPage(query.toString(), aoData)
    m
  }

  @Transactional
  @CacheEvict(value = Array("wechatFooderCache"), allEntries = true)
  def upFodder(media: String, name: String, dsc: String) {
    val data: TblWechatFodder = wechatFodderDao.getById(media)
    data.setName(name)
    data.setDsc(dsc)
    wechatFodderDao.update(data)
  }

  @Transactional
  @CacheEvict(value = Array("wechatFooderCache"), allEntries = true)
  def delFodder(mchtId: String, media: String): String = {
    val sb: StringBuilder = new StringBuilder
    sb.append("from TblWechatMchtInf where mchtId = '").append(mchtId).append("'")
    val tblWechatMchtInf: TblWechatMchtInf = wechatMchtInfDao.find(sb.toString).get(0)
    val at: AccessToken = wechatTokenService.getAccessToken(tblWechatMchtInf.getAppid, tblWechatMchtInf.getAppsecret)
    var jsonObject: JSONObject = null
    var result: String = null
    if (null != at) {
      jsonObject = mediaManager.mediaDel(at.getToken, media)
      if (null != jsonObject) {
        if (WechatUtil.getWechatCallBackStatus(jsonObject.toString) != 0) {
          result = jsonObject.getString("errcode")
        } else {
          wechatFodderDao.deleteById(media)
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
  @Cacheable(value = Array("wechatFooderCache"), key = "#root.method.name+#id")
  def getById(id: String): TblWechatFodder = {
    wechatFodderDao.getById(id)
  }

  @Transactional
  @Cacheable(value = Array("wechatFooderCache"))
  def mediaList(mchtId: String, msgType: String): Map[String, AnyRef] = {
    val map: Map[String, AnyRef] = new LinkedHashMap[String, AnyRef]
    val hql: StringBuilder = new StringBuilder
    hql.append("FROM TblWechatFodder WHERE mchtId='").append(mchtId).append("'")
    hql.append(" and type ='").append(msgType).append("' and mediaType ='1'")
    hql.append("ORDER BY createTime desc")
    val dataList: List[TblWechatFodder] = wechatFodderDao.find(hql.toString)
    val list: List[AnyRef] = new LinkedList[AnyRef]
    import scala.collection.JavaConversions._
    for (data <- dataList) {
      val hashMap: Map[String, AnyRef] = new LinkedHashMap[String, AnyRef]
      hashMap.put("key", data.getMedia + "-" + data.getName)
      hashMap.put("value", data.getMedia)
      hashMap.put("url", data.getUrl)
      list.add(hashMap)
    }
    map.put("info", list)
    map
  }

  @Transactional
  @CacheEvict(value = Array("wechatFooderCache"), allEntries = true)
  def save(data: TblWechatFodder) {
    wechatFodderDao.save(data)
  }
}
