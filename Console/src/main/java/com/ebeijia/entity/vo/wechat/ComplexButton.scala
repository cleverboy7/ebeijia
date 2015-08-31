package com.ebeijia.entity.vo.wechat

/**
 * ComplexButton
 * @author zhicheng.xu
 *         15/8/11
 */
class ComplexButton extends Button {
  private var sub_button: Array[Button] = null

  def getSub_button: Array[Button] = {
    sub_button
  }

  def setSub_button(sub_button: Array[Button]) {
    this.sub_button = sub_button
  }
}
