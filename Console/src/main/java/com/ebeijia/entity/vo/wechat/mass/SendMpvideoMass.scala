package com.ebeijia.entity.vo.wechat.mass

/** 群发所有，用户组
  * SendMpvideoMass
  * @author zhicheng.xu
  *         15/8/19
  */

class SendMpvideoMass extends BaseMass {
  private var filter: Filter = null
  private var mpvideo: Mpvideo = null

  def getFilter: Filter = {
    filter
  }

  def setFilter(filter: Filter) {
    this.filter = filter
  }

  def getMpvideo: Mpvideo = {
    mpvideo
  }

  def setMpvideo(mpvideo: Mpvideo) {
    this.mpvideo = mpvideo
  }
}
