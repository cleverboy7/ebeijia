package com.ebeijia.service.wechat.inter

import java.io.File

import net.sf.json.JSONObject

/**
 * MediaManager素材管理器类
 * @author zhicheng.xu
 *         15/8/13
 */

trait MediaManager {
  /**
   * 上传临时素材
   * @param accessToken 有效的access_token
   * @param Type 素材类型
   * @param f 文件对象
   * @param ext 后缀
   * @return JSONObject
   */
  def upLoadTmpMedia(accessToken: String, Type: String, f: File, ext: String): JSONObject

  /**
   * 上传永久素材
   * @param accessToken 有效的access_token
   * @param type 素材类型
   * @param f 文件对象
   * @param ext 后缀
   * @return JSONObject
   */
  def upLoadMedia(accessToken: String, `type`: String, f: File, ext: String): JSONObject

  /**
   * 下载临时素材
   * @param accessToken 有效的access_token
   * @param mediaId 素材id
   * @return String
   */
  def dowLoadTmpMedia(accessToken: String, mediaId: String): String

  /**
   * 下载素材
   * @param accessToken 有效的access_token
   * @param mediaId 素材id
   * @return String
   */
  def dowLoadMedia(accessToken: String, mediaId: String): String

  /**
   * 获得素材总数
   * @param accessToken 有效的access_token
   * @return JSONObject
   */
  def mediaCountAll(accessToken: String): JSONObject

  /**
   * 删除永久素材
   * @param accessToken 有效的access_token
   * @return JSONObject
   */
  def mediaDel(accessToken: String, media: String): JSONObject

  /**
   * 上传图文素材
   * @param accessToken 有效的access_token
   * @param articles 图文对象
   * @return JSONObject
   */
  def upGtMedia(accessToken: String, articles: String): JSONObject

  /**
   * 获得素材列表
   * @param accessToken 有效的access_token
   * @param type 素材类型
   * @param offset 起始数
   * @param count 条数
   * @return JSONObject
   */
  def newsGet(accessToken: String, `type`: String, offset: String, count: String): JSONObject

  /**
   * 修改图文素材
   * @param accessToken 有效的access_token
   * @param articles 图文对象
   * @return JSONObject
   */
  def updateMedia(accessToken: String, media: String, articles: String): JSONObject

  /**
   * 群发上传图文素材
   * @param accessToken 有效的access_token
   * @param articles 图文对象
   * @return JSONObject
   */
  def massUpNews(accessToken: String, articles: String): JSONObject

  /**
   * 群发上传群发视频
   * @param accessToken 有效的access_token
   * @param mediaId 素材id
   * @param title 标题
   * @param description 描述
   * @return JSONObject
   */
  def massUpVideo(accessToken: String, mediaId: String, title: String, description: String): JSONObject
}

