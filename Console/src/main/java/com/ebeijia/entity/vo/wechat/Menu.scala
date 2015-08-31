package com.ebeijia.entity.vo.wechat

/**
 * Menu
 * @author zhicheng.xu
 *         15/8/11
 */
class Menu {
  private var button: Array[Button] = null

  def getButton: Array[Button] = {
    button
  }

  def setButton(button: Array[Button]) {
    this.button = button
  }
}
