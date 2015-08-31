package com.ebeijia.service.wechat.media

import java.io.File
import java.util.Map

import com.ebeijia.dao.wechat.WechatFodderDao
import com.ebeijia.entity.vo.wechat.AccessToken
import com.ebeijia.entity.wechat.{TblWechatFodder, TblWechatMchtInf}
import com.ebeijia.service.wechat.core.{WechatMchtInfService, WechatTokenService}
import com.ebeijia.service.wechat.inter.MediaManager
import com.ebeijia.util.wechat.WechatUtil
import com.ebeijia.util.{SystemProperties, UpLoad}
import com.google.gson.{JsonArray, JsonObject, JsonParser}
import net.sf.json.JSONObject
import org.ebeijia.tools.DateTime4J
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.{CacheEvict, Cacheable}
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

/**
 * WechatMediaServiceImpl
 * @author zhicheng.xu
 *         15/8/14
 */


@Service("wechatMediaService")
final class WechatMediaServiceImpl extends WechatMediaService {
  @Autowired
  private val wechatMchtInfService: WechatMchtInfService = null
  @Autowired
  private val wechatTokenService: WechatTokenService = null
  @Autowired
  private val mediaManager: MediaManager = null
  @Autowired
  private val wechatFodderDao: WechatFodderDao = null

  @Transactional
  @CacheEvict(value = Array("wechatFooderCache"), allEntries = true)
  def upLoadMedia(mchtId: String, `type`: String, f: MultipartFile, mediaType: String, ext: String, name: String, dsc: String): String = {
    val upload: UpLoad = new UpLoad
    val file: File = upload.getFile(f, SystemProperties.getProperties("image.path1"), `type`, ext)
    val data: TblWechatMchtInf = wechatMchtInfService.getById(mchtId)
    val at: AccessToken = wechatTokenService.getAccessToken(data.getAppid, data.getAppsecret)
    var jsonObject: JSONObject = null
    var result: String = null
    if (null != at) {
      if ("0" == mediaType) {
        jsonObject = mediaManager.upLoadTmpMedia(at.getToken, `type`, file, ext)
      } else {
        jsonObject = mediaManager.upLoadMedia(at.getToken, `type`, file, ext)
      }
      if (null != jsonObject) {
        if (jsonObject.toString.indexOf("errcode") != -1) {
          result = jsonObject.getString("errcode")
        } else {
          val fodder: TblWechatFodder = new TblWechatFodder
          fodder.setCreateTime(DateTime4J.getCurrentDateTime)
          fodder.setMedia(jsonObject.getString("media_id"))
          fodder.setMediaType(mediaType)
          fodder.setMchtId(mchtId)
          val url: String = SystemProperties.getProperties("mediaPath") + SystemProperties.getProperties("image.path1") + "/" + `type` + "/" + file.getName
          fodder.setUrl(url)
          fodder.setType(`type`)
          fodder.setName(name)
          fodder.setDsc(dsc)
          wechatFodderDao.save(fodder)
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
  def dowLoadMedia(mchtId: String, mediaId: String, mediaType: String): String = {
    val data: TblWechatMchtInf = wechatMchtInfService.getById(mchtId)
    var result: String = null
    var jsonObject: String = null
    val at: AccessToken = wechatTokenService.getAccessToken(data.getAppid, data.getAppsecret)
    if (null != at) {
      if ("0" == mediaType) {
        jsonObject = mediaManager.dowLoadTmpMedia(at.getToken, mediaId)
      } else {
        jsonObject = mediaManager.dowLoadMedia(at.getToken, mediaId)
      }
      if (null != jsonObject) {
        result = jsonObject.toString
      } else {
        result = "8888888"
      }
    } else {
      result = "9999999"
    }
    result
  }

  @Transactional
  @CacheEvict(value = Array("wechatFooderCache"), allEntries = true)
  def upGtMedia(mchtId: String, name: String, dsc: String, articles: String): String = {
    val data: TblWechatMchtInf = wechatMchtInfService.getById(mchtId)
    val at: AccessToken = wechatTokenService.getAccessToken(data.getAppid, data.getAppsecret)
    var jsonObject: JSONObject = null
    var result: String = null
    if (null != at) {
      jsonObject = mediaManager.upGtMedia(at.getToken, articles)
      if (null != jsonObject) {
        if (jsonObject.toString.indexOf("errcode") != -1) {
          result = jsonObject.getString("errcode")
        } else {
          val tmp: String = jsonObject.get("media_id").toString
          val fodder: TblWechatFodder = new TblWechatFodder
          fodder.setCreateTime(DateTime4J.getCurrentDateTime)
          fodder.setMedia(tmp)
          fodder.setMediaType("1")
          fodder.setMchtId(mchtId)
          fodder.setUrl(" ")
          fodder.setType("news")
          fodder.setName(name)
          fodder.setDsc(dsc)
          wechatFodderDao.save(fodder)
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
  @CacheEvict(value = Array("wechatFooderCache"), allEntries = true)
  def updateGtMedia(mchtId: String, name: String, dsc: String, media: String, articles: String): String = {
    val data: TblWechatMchtInf = wechatMchtInfService.getById(mchtId)
    val at: AccessToken = wechatTokenService.getAccessToken(data.getAppid, data.getAppsecret)
    var jsonObject: JSONObject = null
    var result: String = null
    if (null != at) {
      jsonObject = mediaManager.updateMedia(at.getToken, media, articles)
      if (null != jsonObject) {
        if (WechatUtil.getWechatCallBackStatus(jsonObject.toString) != 0) {
          result = jsonObject.getString("errcode")
        } else {
          val fodder: TblWechatFodder = wechatFodderDao.getById(media)
          fodder.setName(name)
          fodder.setDsc(dsc)
          wechatFodderDao.update(fodder)
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
  @Cacheable(value = Array("wechatFooderCache"), key = "#root.method.name+#mchtId")
  def mediaAllCount(mchtId: String): Map[_,_] = {
    val data: TblWechatMchtInf = wechatMchtInfService.getById(mchtId)
    val at: AccessToken = wechatTokenService.getAccessToken(data.getAppid, data.getAppsecret)
    var result: JSONObject = null
    if (null != at) {
      result = mediaManager.mediaCountAll(at.getToken)
      WechatUtil.jsonToMap(result.toString)
    }
    null
  }

  @Transactional
  @Cacheable(value = Array("wechatFooderCache"))
  def newsGet(mchtId: String, media: String): String = {
    val data: TblWechatMchtInf = wechatMchtInfService.getById(mchtId)
    val at: AccessToken = wechatTokenService.getAccessToken(data.getAppid, data.getAppsecret)
    var resultJson: JsonObject = null
    var result: JSONObject = null
    var resultMsg: String = null
    if (null != at) {
      result = mediaManager.newsGet(at.getToken, "news", "0", "20")
      val jsonparer: JsonParser = new JsonParser
      val json: JsonObject = jsonparer.parse(result.toString).getAsJsonObject
      val array: JsonArray = json.get("item").getAsJsonArray
      for (i <- 0 until array.size()) {
        val jsonElement: JsonObject = array.get(i).getAsJsonObject();
        val respMedia: String = jsonElement.get("media_id").getAsString();
        if (media.equals(respMedia)) {
          resultJson = jsonElement.get("content").getAsJsonObject();
          resultJson.toString();
        }
      }
      if (resultJson == null) {
        resultMsg = "8888888"
      }
    } else {
      resultMsg = "9999999"
    }
    resultMsg
  }
}
