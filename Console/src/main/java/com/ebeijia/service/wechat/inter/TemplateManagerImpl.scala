package com.ebeijia.service.wechat.inter

import com.ebeijia.entity.vo.wechat.Group
import com.ebeijia.service.wechat.core.WechatTokenService
import com.ebeijia.util.wechat.WechatUtil
import net.sf.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


/**
 * TemplateManagerImpl分组管理器类
 * @author zhicheng.xu
 *         15/8/13
 */


@Service("TemplateManager")
class TemplateManagerImpl extends TemplateManager {
  @Autowired
  private val wechatTokenService: WechatTokenService = null

  /**
   * 创建分组
   * @param accessToken 有效的access_token
   * @return JSONObject
   */
  def set(id1: String, id2: String, accessToken: String): JSONObject = {
    val url: String = WechatUtil.TEMPLATE_SET.replace("ACCESS_TOKEN", accessToken)
    val jsonId: String = "{\"industry_id1\":\"" + id1 + "\",\"industry_id\":\"" + id2 + "\"}"
    wechatTokenService.httpRequest(url, "POST", jsonId)
  }

  /**
   * 修改分组
   * @param group 分组实例
   * @param accessToken 有效的access_token
   * @return JSONObject
   */
  def add(group: Group, accessToken: String): JSONObject = {
    val url: String = WechatUtil.TEMPLATE_ADD.replace("ACCESS_TOKEN", accessToken)
    val jsonGroup: String = "{\"group\":" + JSONObject.fromObject(group).toString + "}"
    wechatTokenService.httpRequest(url, "POST", jsonGroup)
  }

  /**
   * 删除分组
   * @param groupId 分组实例
   * @param accessToken 有效的access_token
   * @return JSONObject
   */
  def send(groupId: String, accessToken: String): JSONObject = {
    val url: String = WechatUtil.TEMPLATE_SEND.replace("ACCESS_TOKEN", accessToken)
    val jsonGroup: String = "{\"group\":{\"id\":" + groupId + "}}"
    wechatTokenService.httpRequest(url, "POST", jsonGroup)
  }
}
