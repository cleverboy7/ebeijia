package com.ebeijia.entity.vo.wechat

/**
 * UsrToGroup
 * @author zhicheng.xu
 *         15/8/11
 */
class UsrToGroup {
  private var openid: String = null
  private var to_groupid: String = null

  def getOpenid: String = {
    openid
  }

  def setOpenid(openid: String) {
    this.openid = openid
  }

  def getTo_groupid: String = {
    to_groupid
  }

  def setTo_groupid(to_groupid: String) {
    this.to_groupid = to_groupid
  }
}
