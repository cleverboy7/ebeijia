package com.ebeijia.service.wechat.inter

import com.ebeijia.entity.vo.wechat.{ModUsr, UsrToGroup, UsrsToGroup}
import net.sf.json.JSONObject

/**
 * UsrManager用户管理器类
 * @author zhicheng.xu
 *         15/8/13
 */


trait UsrManager {
  /**
   * 移动用户分组
   * @param utg 用户实例
   * @param accessToken 有效的access_token
   * @return JSONObject
   */
  def mvUsr(utg: UsrToGroup, accessToken: String): JSONObject

  /**
   * 批量移动用户分组
   * @param utg 用户实例
   * @param accessToken 有效的access_token
   * @return JSONObject
   */
  def mvUsrs(utg: UsrsToGroup, accessToken: String): JSONObject

  /**
   * 查询用户信息
   * @param openId 用户id
   * @param accessToken 有效的access_token
   * @return JSONObject
   */
  def getUsrInf(accessToken: String, openId: String): JSONObject

  /**
   * 查询关注者用户第一页
   * @param accessToken 有效的access_token
   * @return JSONObject
   */
  def getUsr(accessToken: String): JSONObject

  /**
   * 查询关注者用户下一页
   * @param nextOpenid 下一页
   * @param accessToken 有效的access_token
   * @return JSONObject
   */
  def getUsrNext(accessToken: String, nextOpenid: String): JSONObject

  /**
   * 修改用户备注
   * @param modUsr 对象
   * @param accessToken 有效的access_token
   * @return JSONObject
   */
  def upRemark(modUsr: ModUsr, accessToken: String): JSONObject
}

