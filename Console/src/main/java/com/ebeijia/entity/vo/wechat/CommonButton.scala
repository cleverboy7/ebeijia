package com.ebeijia.entity.vo.wechat

/**
 * CommonButton
 * @author zhicheng.xu
 *         15/8/11
 */
class CommonButton extends Button {
  private var `type`: String = null
  private var key: String = null

  def getType: String = {
    `type`
  }

  def setType(`type`: String) {
    this.`type` = `type`
  }

  def getKey: String = {
    key
  }

  def setKey(key: String) {
    this.key = key
  }
}
