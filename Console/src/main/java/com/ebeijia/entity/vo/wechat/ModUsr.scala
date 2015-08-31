package com.ebeijia.entity.vo.wechat

/**
 * ModUsr
 * @author zhicheng.xu
 *         15/8/11
 */
class ModUsr {
  private var remark: String = null
  private var openid: String = null

  def getRemark: String = {
    remark
  }

  def setRemark(remark: String) {
    this.remark = remark
  }

  def getOpenid: String = {
    openid
  }

  def setOpenid(openid: String) {
    this.openid = openid
  }
}
