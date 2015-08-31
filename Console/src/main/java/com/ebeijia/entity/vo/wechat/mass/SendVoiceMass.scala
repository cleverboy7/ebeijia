package com.ebeijia.entity.vo.wechat.mass

/** 群发所有，用户组
  * SendVoiceMass
  * @author zhicheng.xu
  *         15/8/19
  */

class SendVoiceMass extends BaseMass {
  private var filter: Filter = null
  private var voice: Voice = null

  def getFilter: Filter = {
    filter
  }

  def setFilter(filter: Filter) {
    this.filter = filter
  }

  def getVoice: Voice = {
    voice
  }

  def setVoice(voice: Voice) {
    this.voice = voice
  }
}
