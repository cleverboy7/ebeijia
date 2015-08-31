package com.ebeijia.service.wechat.inter

import com.ebeijia.entity.vo.wechat.{ModUsr, UsrToGroup, UsrsToGroup}
import com.ebeijia.service.wechat.core.WechatTokenService
import com.ebeijia.util.wechat.WechatUtil
import net.sf.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * UsrManagerImpl用户管理器类
 * @author zhicheng.xu
 *         15/8/13
 */

@Service("UsrManager")
class UsrManagerImpl extends UsrManager {
  @Autowired
  private val wechatTokenService: WechatTokenService = null

  /**
   * 移动用户分组
   * @param utg 用户实例
   * @param accessToken 有效的access_token
   * @return JSONObject
   */
  def mvUsr(utg: UsrToGroup, accessToken: String): JSONObject = {
    val url: String = WechatUtil.USR_GROUP_URL.replace("ACCESS_TOKEN", accessToken)
    val jsonUtg: String = JSONObject.fromObject(utg).toString
    val jsonObject: JSONObject = wechatTokenService.httpRequest(url, "POST", jsonUtg)
    jsonObject
  }

  /**
   * 批量移动用户分组
   * @param utg 用户实例
   * @param accessToken 有效的access_token
   * @return JSONObject
   */
  def mvUsrs(utg: UsrsToGroup, accessToken: String): JSONObject = {
    val requestUrl: String = WechatUtil.USRS_GROUP_URL.replace("ACCESS_TOKEN", accessToken)
    val jsonUtg: String = JSONObject.fromObject(utg).toString
    val jsonObject: JSONObject = wechatTokenService.httpRequest(requestUrl, "POST", jsonUtg)
    jsonObject
  }

  /**
   * 查询用户信息
   * @param openId 用户id
   * @param accessToken 有效的access_token
   * @return JSONObject
   */
  def getUsrInf(accessToken: String, openId: String): JSONObject = {
    val requestUrl: String = WechatUtil.USR_INF_URL.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId)
    val jsonObject: JSONObject = wechatTokenService.httpRequest(requestUrl, "POST", null)
    jsonObject
  }

  /**
   * 查询关注者用户第一页
   * @param accessToken 有效的access_token
   * @return JSONObject
   */
  def getUsr(accessToken: String): JSONObject = {
    val requestUrl: String = WechatUtil.SUBSCRIBE_FIRST_PAGE_URL.replace("ACCESS_TOKEN", accessToken)
    val jsonObject: JSONObject = wechatTokenService.httpRequest(requestUrl, "POST", null)
    jsonObject
  }

  /**
   * 查询关注者用户下一页
   * @param nextOpenid 下一页
   * @param accessToken 有效的access_token
   * @return JSONObject
   */
  def getUsrNext(accessToken: String, nextOpenid: String): JSONObject = {
    val requestUrl: String = WechatUtil.SUBSCRIBE_NEXT_PAGE_URL.replace("ACCESS_TOKEN", accessToken).replace("NEXT_OPENID", nextOpenid)
    val jsonObject: JSONObject = wechatTokenService.httpRequest(requestUrl, "POST", null)
    jsonObject
  }

  /**
   * 修改用户备注
   * @param modUsr 对象
   * @param accessToken 有效的access_token
   * @return JSONObject
   */
  def upRemark(modUsr: ModUsr, accessToken: String): JSONObject = {
    val requestUrl: String = WechatUtil.USR_UPD_REMARK.replace("ACCESS_TOKEN", accessToken)
    val jsonUsr: String = JSONObject.fromObject(modUsr).toString
    val jsonObject: JSONObject = wechatTokenService.httpRequest(requestUrl, "POST", jsonUsr)
    jsonObject
  }
}

