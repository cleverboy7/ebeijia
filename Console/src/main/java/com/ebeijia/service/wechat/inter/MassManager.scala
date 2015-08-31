package com.ebeijia.service.wechat.inter

import net.sf.json.JSONObject

/**
 * MassManager群发管理器类
 * @author zhicheng.xu
 *         15/8/13
 */

trait MassManager {
  /**
   * 群发所有，组别
   * @param accessToken 有效的access_token
   * @param sendJson 群发json
   * @return JSONObject
   */
  def massAll(accessToken: String, sendJson: String): JSONObject

  /**
   * 群发用户群发
   * @param accessToken 有效的access_token
   * @param sendJson 群发json
   * @return JSONObject
   */
  def massUsr(accessToken: String, sendJson: String): JSONObject

  /**
   * 删除群发
   * @param accessToken 有效的access_token
   * @param msgId 图文和视频media
   * @return JSONObject
   */
  def massDel(accessToken: String, msgId: String): JSONObject

  /**
   * 群发预览
   * @param accessToken 有效的access_token
   * @param toUsrJson 群发预览json
   * @return JSONObject
   */
  def massPreview(accessToken: String, toUsrJson: String): String

  /**
   * 查询群发状态
   * @param accessToken 有效的access_token
   * @param msgId 群发id
   * @return JSONObject
   */
  def massStatus(accessToken: String, msgId: String): String
}

