package com.ebeijia.entity.vo.wechat.mass

import java.util.List

/** 群发筛选用户
  * SendTextMassByUsr
  * @author zhicheng.xu
  *         15/8/19
  */

class SendTextMassByUsr extends BaseMass {
  private var text: Text = null
  private var touser: List[_] = null

  def getTouser: List[_] = {
    touser
  }

  def setTouser(touser: List[_]) {
    this.touser = touser
  }

  def getText: Text = {
    text
  }

  def setText(text: Text) {
    this.text = text
  }
}

