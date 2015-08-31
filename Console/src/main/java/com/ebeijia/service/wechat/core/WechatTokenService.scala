package com.ebeijia.service.wechat.core

/**
 * WechatTokenService
 * @author zhicheng.xu
 *         15/8/13
 */

import java.io.File

import com.ebeijia.entity.vo.wechat.AccessToken
import net.sf.json.JSONObject

trait WechatTokenService {
  /**
   * 获取access_token
   *
   * @param appid 凭证
   * @param appsecret 密钥
   * @return
   */
  def getAccessToken(appid: String, appsecret: String): AccessToken

  /**
   * 发起https请求并获取结果
   *
   * @param requestUrl
	 * 请求地址
   * @param requestMethod
	 * 请求方式（GET、POST）
   * @param outputStr
	 * 提交的数据
   * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
   */
  def httpRequest(requestUrl: String, requestMethod: String, outputStr: String): JSONObject

  /**
   * 上传多媒体
   * @param url
   * @param access_token
   * @param file
   * @param ext
   * @return result
   */
  def upload(url: String, access_token: String, file: File, `type`: String, ext: String, flag: String): JSONObject

  /**
   * 下载临时多媒体
   * @param url
   * @param method
   * @param mediaId
   * @return JSONObject
   */
  def dowload(url: String, method: String, mediaId: String): String
}
