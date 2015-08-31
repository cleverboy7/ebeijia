package com.ebeijia.entity.vo.wechat.resp

/**
 * Voice语音model
 * @author zhicheng.xu
 *         15/8/12
 */
class Voice {
  private var MediaId: String = null

  def getMediaId: String = {
    MediaId
  }

  def setMediaId(MediaId: String) {
    this.MediaId = MediaId
  }
}
