package com.ebeijia.entity.vo.wechat.mass

/** 群发所有，用户组
  * SendTextMass
  * @author zhicheng.xu
  *         15/8/19
  */

class SendTextMass extends BaseMass {
  private var filter: Filter = null
  private var text: Text = null

  def getFilter: Filter = {
    filter
  }

  def setFilter(filter: Filter) {
    this.filter = filter
  }

  def getText: Text = {
    text
  }

  def setText(text: Text) {
    this.text = text
  }
}

