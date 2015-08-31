package com.ebeijia.service.wechat.inter

import java.io.File

import com.ebeijia.entity.vo.wechat.kf.Kf
import com.ebeijia.service.wechat.core.WechatTokenService
import com.ebeijia.util.wechat.WechatUtil
import net.sf.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * KfManagerImpl
 * @author zhicheng.xu
 *         15/8/13
 */


@Service("KfManager")
class KfManagerImpl extends KfManager {
  @Autowired
  private val wechatTokenService: WechatTokenService = null

  /**
   * 新增客服
   * @param kf 客服对象
   * @param accessToken 有效的access_token
   * @return JSONObject
   */
  def createKf(kf: Kf, accessToken: String): JSONObject = {
    val url: String = WechatUtil.KF_ADD.replace("ACCESS_TOKEN", accessToken)
    val jsonKf: String = JSONObject.fromObject(kf).toString
    val jsonObject: JSONObject = wechatTokenService.httpRequest(url, "POST", jsonKf)
    jsonObject
  }

  /**
   * 修改客服
   * @param kf 客服对象
   * @param accessToken 有效的access_token
   * @return JSONObject
   */
  def updKf(kf: Kf, accessToken: String): JSONObject = {
    val url: String = WechatUtil.KF_UPD.replace("ACCESS_TOKEN", accessToken)
    val jsonKf: String = JSONObject.fromObject(kf).toString
    val jsonObject: JSONObject = wechatTokenService.httpRequest(url, "POST", jsonKf)
    jsonObject
  }

  /**
   * 删除客服
   * @param account 客服账号
   * @param accessToken 有效的access_token
   * @return JSONObject
   */
  def delKf(account: String, accessToken: String): JSONObject = {
    val url: String = WechatUtil.KF_DEL.replace("ACCESS_TOKEN", accessToken).replace("KFACCOUNT", account)
    val jsonObject: JSONObject = wechatTokenService.httpRequest(url, "POST", null)
    jsonObject
  }

  /**
   * 上传客服头像
   * @param type 素材类型
   * @param f 文件对象
   * @param ext 后缀
   * @param accessToken 有效的access_token
   * @return JSONObject
   */
  def upHeadKf(accessToken: String, `type`: String, f: File, ext: String): JSONObject = {
    val url: String = WechatUtil.KF_UPHEAD.replace("ACCESS_TOKEN", accessToken)
    val jsonObject: JSONObject = wechatTokenService.upload(url, accessToken, f, `type`, ext, null)
    jsonObject
  }
}

