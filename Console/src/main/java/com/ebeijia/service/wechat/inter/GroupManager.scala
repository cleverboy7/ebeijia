package com.ebeijia.service.wechat.inter
import com.ebeijia.entity.vo.wechat.Group
import net.sf.json.JSONObject
/**
 * GroupManager分组管理器类
 * @author zhicheng.xu
 *         15/8/13
 */

trait GroupManager {
  /**
   * 创建分组
   * @param group 分组名称
   * @param accessToken 有效的access_token
   * @return JSONObject
   */
  def createGroup(group: String, accessToken: String): JSONObject

  /**
   * 修改分组
   * @param group 分组实例
   * @param accessToken 有效的access_token
   * @return JSONObject
   */
  def modGroup(group: Group, accessToken: String): JSONObject

  /**
   * 删除分组
   * @param groupId 组别id
   * @param accessToken 有效的access_token
   * @return JSONObject
   */
  def delGroup(groupId: String, accessToken: String): JSONObject

  /**
   * 查询分组
   * @param accessToken 有效的access_token
   * @return JSONObject
   */
  def getGroup(accessToken: String): JSONObject
}

