package com.ebeijia.service.wechat.inter

import java.io.File

import com.ebeijia.service.wechat.core.WechatTokenService
import com.ebeijia.util.wechat.WechatUtil
import net.sf.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * MediaManagerImpl素材管理器类
 * @author zhicheng.xu
 *         15/8/13
 */

@Service("MediaManager")
class MediaManagerImpl extends MediaManager {
  @Autowired
  private val wechatTokenService: WechatTokenService = null

  /**
   * 上传临时素材
   * @param accessToken 有效的access_token
   * @param type 素材类型
   * @param f 素材对象
   * @return String
   */
  def upLoadTmpMedia(accessToken: String, `type`: String, f: File, ext: String): JSONObject = {
    val url: String = WechatUtil.MEDIA_UPLOAD_TMP_URL.replace("ACCESS_TOKEN", accessToken).replace("TYPE", `type`)
    val result: JSONObject = wechatTokenService.upload(url, accessToken, f, `type`, ext, null)
    result
  }

  /**
   * 上传永久素材
   * @param accessToken 有效的access_token
   * @param type 素材类型
   * @param f 素材对象
   * @return JSONObject
   */
  def upLoadMedia(accessToken: String, `type`: String, f: File, ext: String): JSONObject = {
    val url: String = WechatUtil.MEDIA_UPLOAD_URL.replace("ACCESS_TOKEN", accessToken)
    var flag: String = null
    if ("video" == `type`) {
      flag = "video"
    }
    val result: JSONObject = wechatTokenService.upload(url, accessToken, f, `type`, ext, flag)
    result
  }

  /**
   * 下载临时素材
   * @param accessToken 有效的access_token
   * @param mediaId 素材id
   * @return JSONObject
   */
  def dowLoadTmpMedia(accessToken: String, mediaId: String): String = {
    val url: String = WechatUtil.MEDIA_DOWLOAD_TMP_URL.replace("ACCESS_TOKEN", accessToken).replace("MEDIA_ID", mediaId)
    val result: String = wechatTokenService.dowload(url, "GET", "")
    result
  }

  /**
   * 下载永久素材
   * @param accessToken 有效的access_token
   * @param mediaId 素材id
   * @return String
   */
  def dowLoadMedia(accessToken: String, mediaId: String): String = {
    val url: String = WechatUtil.MEDIA_DOWLOAD_URL.replace("ACCESS_TOKEN", accessToken)
    val result: String = wechatTokenService.dowload(url, "POST", mediaId)
    result
  }

  /**
   * 获得素材总数
   * @param accessToken 有效的access_token
   * @return JSONObject
   */
  def mediaCountAll(accessToken: String): JSONObject = {
    val url: String = WechatUtil.MEDIA_COUNTALL_URL.replace("ACCESS_TOKEN", accessToken)
    val jsonObject: JSONObject = wechatTokenService.httpRequest(url, "POST", null)
    jsonObject
  }

  /**
   * 素材删除
   * @param accessToken 有效的access_token
   * @param media 素材id
   * @return JSONObject
   */
  def mediaDel(accessToken: String, media: String): JSONObject = {
    val url: String = WechatUtil.MEDIA_DEL_URL.replace("ACCESS_TOKEN", accessToken)
    val mediaTmp = "{\"media_id\":\"" + media + "\"}"
    val jsonObject: JSONObject = wechatTokenService.httpRequest(url, "POST", mediaTmp)
    jsonObject
  }

  /**
   * 上传图文素材
   * @param accessToken 有效的access_token
   * @param articles 图文对象
   * @return JSONObject
   */
  def upGtMedia(accessToken: String, articles: String): JSONObject = {
    val url: String = WechatUtil.MEDIA_UPLOAD_GT_URL.replace("ACCESS_TOKEN", accessToken)
    val jsonObject: JSONObject = wechatTokenService.httpRequest(url, "POST", articles)
    jsonObject
  }

  /**
   * 修改图文素材
   * @param accessToken 有效的access_token
   * @param articles 图文对象
   * @return JSONObject
   */
  def updateMedia(accessToken: String, media: String, articles: String): JSONObject = {
    val mediaTmp = "\"media_id\":\"" + media + "\",\"index\":\"0\","
    val articlesTmp = articles.substring(0, 1) + mediaTmp + articles.substring(1)
    val url: String = WechatUtil.MEDIA_NEWS_UPDATE.replace("ACCESS_TOKEN", accessToken)
    val jsonObject: JSONObject = wechatTokenService.httpRequest(url, "POST", articlesTmp)
    jsonObject
  }

  /**
   * 获得素材对象
   * @param accessToken 有效的access_token
   * @param type 素材类型
   * @return JSONObject
   */
  def newsGet(accessToken: String, `type`: String, offset: String, count: String): JSONObject = {
    val url: String = WechatUtil.MEDIA_NEWS_GET.replace("ACCESS_TOKEN", accessToken)
    val sendJson: String = "{\"type\":\"" + `type` + "\",\"offset\":\"" + offset + "\",\"count\":\"" + count + "\"}"
    val jsonObject: JSONObject = wechatTokenService.httpRequest(url, "POST", sendJson)
    jsonObject
  }

  /**
   * 群发上传群发图文素材
   * @param accessToken 有效的access_token
   * @param articles 图文对象
   * @return JSONObject
   */
  def massUpNews(accessToken: String, articles: String): JSONObject = {
    val url: String = WechatUtil.MASS_UP_NEWS.replace("ACCESS_TOKEN", accessToken)
    val jsonObject: JSONObject = wechatTokenService.httpRequest(url, "POST", articles)
    jsonObject
  }

  /**
   * 群发上传群发视频
   * @param accessToken 有效的access_token
   * @param mediaId 素材id
   * @param title 标题
   * @param description 描述
   * @return JSONObject
   */
  def massUpVideo(accessToken: String, mediaId: String, title: String, description: String): JSONObject = {
    val url: String = WechatUtil.MASS_VIDEO_SEND.replace("ACCESS_TOKEN", accessToken)
    val sendJson: String = "{\"media_id\": \"" + mediaId + "\", \"title\":\"" + title + "\",\"description\": \"" + description + "\"}"
    val jsonObject: JSONObject = wechatTokenService.httpRequest(url, "POST", sendJson)
    jsonObject
  }
}

