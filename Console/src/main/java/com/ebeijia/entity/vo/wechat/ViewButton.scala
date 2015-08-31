package com.ebeijia.entity.vo.wechat

/**
 * ViewButton
 * @author zhicheng.xu
 *         15/8/11
 */
class ViewButton extends Button {
  private var `type`: String = null
  private var url: String = null

  def getType: String = {
    `type`
  }

  def setType(`type`: String) {
    this.`type` = `type`
  }

  def getUrl: String = {
    url
  }

  def setUrl(url: String) {
    this.url = url
  }
}
