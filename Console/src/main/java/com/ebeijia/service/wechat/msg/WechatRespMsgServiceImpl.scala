package com.ebeijia.service.wechat.msg

import java.util.{List, Map}
import com.ebeijia.dao.wechat.WechatRespMsgDao
import com.ebeijia.entity.wechat.{TblArticlesInf, TblWechatRespMsg}
import com.ebeijia.service.wechat.media.ArticlesService
import com.ebeijia.util.StringUtil
import com.google.gson.{JsonArray, JsonObject, JsonParser}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.{CacheEvict, Cacheable}
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * WechatRespMsgServiceImpl
 * @author zhicheng.xu
 *         15/8/14
 */


@Service
final class WechatRespMsgServiceImpl extends WechatRespMsgService {
  @Autowired
  private val wechatRespMsgDao: WechatRespMsgDao = null
  @Autowired
  private val articlesService: ArticlesService = null

  @Transactional
  @CacheEvict(value = Array("wechatRespMsgCache"), allEntries = true)
  def findBySql(mchtId: String, pagaData: String): Map[String, AnyRef] = {
    val query: StringBuilder = new StringBuilder
    query.append("from TblWechatRespMsg")
    query.append(" where 1=1")
    if (mchtId != null && !("" == mchtId)) {
      query.append(" AND mchtId like '%").append(mchtId).append("%'")
    }
    query.append(" ORDER BY id.mchtId")
    val m: Map[String, AnyRef] = wechatRespMsgDao.findByPage(query.toString, pagaData)
    m
  }

  @Transactional
  @CacheEvict(value = Array("wechatRespMsgCache"), allEntries = true)
  def save(data: TblWechatRespMsg, articlesJson: String) {
    val articleId: StringBuilder = new StringBuilder
    if (articlesJson != null) {
      val jsonparer: JsonParser = new JsonParser
      val json: JsonObject = jsonparer.parse(articlesJson).getAsJsonObject
      val array: JsonArray = json.get("articles").getAsJsonArray
      for (i <- 0 until array.size()) {
        val artData: TblArticlesInf = new TblArticlesInf
        val jsonElement: JsonObject = array.get(i).getAsJsonObject
        val title: String = jsonElement.get("title").getAsString
        val picUrl: String = jsonElement.get("picUrl").getAsString
        val description: String = jsonElement.get("description").getAsString
        val url: String = jsonElement.get("url").getAsString
        artData.setDescription(description)
        artData.setPicUrl(picUrl)
        artData.setTitle(title)
        artData.setUrl(url)
        articlesService.Save(artData)
        articleId.append(artData.getId).append(",")
      }
      data.setArticleIds(articleId.toString.substring(0, articleId.length - 1))
      wechatRespMsgDao.save(data)
    }
    else {
      wechatRespMsgDao.save(data)
    }
  }

  @Transactional
  @CacheEvict(value = Array("wechatRespMsgCache"), allEntries = true)
  def update(data: TblWechatRespMsg, articlesJson: String) {
    val articleId: StringBuilder = new StringBuilder
    if (articlesJson != null) {
      //bug区
      //      val id: String = articlesService.getArticlesId
      //      articleId.append(id).append(",")
      data.setArticleIds(articleId.toString)
      wechatRespMsgDao.save(data)
    }
    else {
      wechatRespMsgDao.save(data)
    }
  }

  @Transactional
  @Cacheable(value = Array("wechatRespMsgCache"))
  def findByMchtType(mchtId: String, `type`: String, keywords: String): List[TblWechatRespMsg] = {
    val sb: StringBuilder = new StringBuilder
    sb.append("from TblWechatRespMsg where mchtId='")
    sb.append(mchtId).append("' ")
    sb.append(" and respType ='").append(`type`).append("'")
    if (keywords != null) {
      sb.append(" and keywords ='").append(keywords).append("'")
    }
    val result: List[TblWechatRespMsg] = wechatRespMsgDao.find(sb.toString)
    result
  }

  @Transactional
  @Cacheable(value = Array("wechatRespMsgCache"), key = "#root.method.name+#id")
  def getById(id: String): TblWechatRespMsg = {
    wechatRespMsgDao.getById(id)
  }

  @Transactional
  @CacheEvict(value = Array("wechatRespMsgCache"), allEntries = true)
  def delete(id: String) {
    wechatRespMsgDao.deleteById(id)
  }

  def check(respId: String, mchtId: String, keywords: String): Int = {
    val sb: StringBuilder = new StringBuilder
    sb.append("from TblWechatRespMsg where mchtId='")
    sb.append(mchtId).append("' ")
    sb.append(" and keywords ='").append(keywords).append("'")
    if (respId != null && !("" == respId)) {
      sb.append(" AND respMsgId <>'").append(respId).append("'")
    }
    val total: Int = wechatRespMsgDao.getTotalRows(sb.toString)
    total
  }

  @Transactional
  def getRespMsgId: String = {
    val sql: String = "SELECT SEQ_WECHAT_RESP_ID.NEXTVAL FROM DUAL"
    val respMsgId: String = wechatRespMsgDao.getListSQL(sql).get(0).toString
    StringUtil.beforFillValue(respMsgId, 20, '0')
  }
}
