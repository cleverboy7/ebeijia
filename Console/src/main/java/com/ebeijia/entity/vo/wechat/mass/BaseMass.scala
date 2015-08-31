package com.ebeijia.entity.vo.wechat.mass

/**
 * 群发基类
 * BaseMass
 * @author zhicheng.xu
 *         15/8/12
 */
class BaseMass {
  private var msgtype: String = null

  def getMsgtype: String = {
    msgtype
  }

  def setMsgtype(msgtype: String) {
    this.msgtype = msgtype
  }
}
