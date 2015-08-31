package com.ebeijia.service.wechat.inter

import com.ebeijia.entity.vo.wechat.Group
import com.ebeijia.service.wechat.core.WechatTokenService
import com.ebeijia.util.wechat.WechatUtil
import net.sf.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * 分组管理器类
 * @author zhicheng.xu
 */

@Service("GroupManager") class GroupManagerImpl extends GroupManager {
  @Autowired private var wechatTokenService: WechatTokenService = null

  /**
   * 创建分组
   * @param group 分组名称
   * @param accessToken 有效的access_token
   * @@return JSONObject
   */
  def createGroup(group: String, accessToken: String): JSONObject = {
    val url: String = WechatUtil.GROUPS_CREAT_URL.replace("ACCESS_TOKEN", accessToken)
    val groupTmp = "{\"group\":{\"name\":\"" + group + "\"}}"
    val jsonObject: JSONObject = wechatTokenService.httpRequest(url, "POST", groupTmp)
    jsonObject
  }

  /**
   * 修改分组
   * @param group 分组实例
   * @param accessToken 有效的access_token
   * @return JSONObject
   */
  def modGroup(group: Group, accessToken: String): JSONObject = {
    val url: String = WechatUtil.GROUP_MOD_URL.replace("ACCESS_TOKEN", accessToken)
    val jsonGroup: String = "{\"group\":" + JSONObject.fromObject(group).toString + "}"
    val jsonObject: JSONObject = wechatTokenService.httpRequest(url, "POST", jsonGroup)
    jsonObject
  }

  /**
   * 删除分组
   * @param groupId 分组实例
   * @param accessToken 有效的access_token
   * @return JSONObject
   */
  def delGroup(groupId: String, accessToken: String): JSONObject = {
    val url: String = WechatUtil.GROUP_DEL_URL.replace("ACCESS_TOKEN", accessToken)
    val jsonGroup: String = "{\"group\":{\"id\":" + groupId + "}}"
    val jsonObject: JSONObject = wechatTokenService.httpRequest(url, "POST", jsonGroup)
    jsonObject
  }

  /**
   * 查询分组
   * @param accessToken 有效的access_token
   * @return JSONObject
   */
  def getGroup(accessToken: String): JSONObject = {
    val url: String = WechatUtil.GROUPS_GET_URL.replace("ACCESS_TOKEN", accessToken)
    val jsonObject: JSONObject = wechatTokenService.httpRequest(url, "POST", null)
    jsonObject
  }
}

