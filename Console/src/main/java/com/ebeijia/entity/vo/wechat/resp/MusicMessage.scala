package com.ebeijia.entity.vo.wechat.resp

/**
 * MusicMessage音乐消息
 * @author zhicheng.xu
 *         15/8/12
 */
class MusicMessage extends BaseMessage {
  private var Music: Music = null

  def getMusic: Music = {
    Music
  }

  def setMusic(music: Music) {
    Music = music
  }
}
