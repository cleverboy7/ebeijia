package com.ebeijia.entity.vo.wechat.mass

import java.util.List

/** 群发筛选用户
  * SendMpnewsMassByUsr
  * @author zhicheng.xu
  *         15/8/19
  */

class SendMpnewsMassByUsr extends BaseMass {
  private var mpnews: Mpnews = null
  private var touser: List[_] = null

  def getTouser: List[_] = {
    touser
  }

  def setTouser(touser: List[_]) {
    this.touser = touser
  }

  def getMpnews: Mpnews = {
    mpnews
  }

  def setMpnews(mpnews: Mpnews) {
    this.mpnews = mpnews
  }
}
