package com.ebeijia.service.wechat.inter

import com.ebeijia.service.wechat.core.WechatTokenService
import com.ebeijia.util.wechat.WechatUtil
import net.sf.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * MassManagerImpl用户管理器类
 * @author zhicheng.xu
 *         15/8/13
 */

@Service("MassManager")
class MassManagerImpl extends MassManager {
  @Autowired
  private val wechatTokenService: WechatTokenService = null

  /**
   * 群发所有，组别
   * @param accessToken 有效的access_token
   * @param sendJson 群发json
   * @return JSONObject
   */
  def massAll(accessToken: String, sendJson: String): JSONObject = {
    val url: String = WechatUtil.MASS_All.replace("ACCESS_TOKEN", accessToken)
    val jsonObject: JSONObject = wechatTokenService.httpRequest(url, "POST", sendJson)
    jsonObject
  }

  /**
   * 群发用户群发
   * @param accessToken 有效的access_token
   * @param sendJson 群发json
   * @return JSONObject
   */
  def massUsr(accessToken: String, sendJson: String): JSONObject = {
    val url: String = WechatUtil.MASS_USR.replace("ACCESS_TOKEN", accessToken)
    val jsonObject: JSONObject = wechatTokenService.httpRequest(url, "POST", sendJson)
    jsonObject
  }

  /**
   * 删除群发
   * @param accessToken 有效的access_token
   * @param msgId 消息id
   * @return JSONObject
   */
  def massDel(accessToken: String, msgId: String): JSONObject = {
    val url: String = WechatUtil.MASS_DEL.replace("ACCESS_TOKEN", accessToken)
    val msgIdTmp = "{\"msg_id\":\"" + msgId + "\"}"
    val jsonObject: JSONObject = wechatTokenService.httpRequest(url, "POST", msgIdTmp)
    jsonObject
  }

  /**
   * 群发预览
   * @param accessToken 有效的access_token
   * @param toUsrJson 群发预览json
   * @return JSONObject
   */
  def massPreview(accessToken: String, toUsrJson: String): String = {
    val url: String = WechatUtil.MASS_PREVIEW.replace("ACCESS_TOKEN", accessToken)
    val jsonObject: JSONObject = wechatTokenService.httpRequest(url, "POST", toUsrJson)
    jsonObject.toString
  }

  /**
   * 查询群发状态
   * @param accessToken 有效的access_token
   * @param msgId 群发id
   * @return JSONObject
   */
  def massStatus(accessToken: String, msgId: String): String = {
    val url: String = WechatUtil.MASS_STATUS.replace("ACCESS_TOKEN", accessToken)
    val msgIdTmp = "{\"msg_id\":\"" + msgId + "\"}"
    val jsonObject: JSONObject = wechatTokenService.httpRequest(url, "POST", msgIdTmp)
    jsonObject.toString
  }
}

