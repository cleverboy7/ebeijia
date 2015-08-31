package com.ebeijia.entity.vo.wechat

import java.util.List

/**
 * UsrsToGroup
 * @author zhicheng.xu
 *         15/8/11
 */


class UsrsToGroup {
  private var openid_list: List[_] = null
  private var to_groupid: String = null

  def getOpenid_list: List[_] = {
    openid_list
  }

  def setOpenid_list(openid_list: List[_]) {
    this.openid_list = openid_list
  }

  def getTo_groupid: String = {
    to_groupid
  }

  def setTo_groupid(to_groupid: String) {
    this.to_groupid = to_groupid
  }
}
