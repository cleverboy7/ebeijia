package com.ebeijia.entity.vo.wechat.mass

import java.util.List

/** 群发筛选用户
  * SendMpvideoMassByUsr
  * @author zhicheng.xu
  *         15/8/19
  */

class SendMpvideoMassByUsr extends BaseMass {
  private var mpvideo: Mpvideo = null
  private var touser: List[_] = null

  def getTouser: List[_] = {
    touser
  }

  def setTouser(touser: List[_]) {
    this.touser = touser
  }

  def getMpvideo: Mpvideo = {
    mpvideo
  }

  def setMpvideo(mpvideo: Mpvideo) {
    this.mpvideo = mpvideo
  }
}

