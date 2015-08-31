package com.ebeijia.entity.vo.wechat.resp

/**
 * VoiceMessage语音消息
 * @author zhicheng.xu
 *         15/8/12
 */
class VoiceMessage extends BaseMessage {
  private var Voice: Voice = null

  def getVoice: Voice = {
    Voice
  }

  def setVoice(Voice: Voice) {
    this.Voice = Voice
  }
}  
