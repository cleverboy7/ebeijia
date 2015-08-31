package com.ebeijia.entity.vo.wechat.resp

/**
 * VideoMessage视频消息
 * @author zhicheng.xu
 *         15/8/12
 */
class VideoMessage extends BaseMessage {
  private var Video: Video = null

  def getVideo: Video = {
    Video
  }

  def setVideo(Video: Video) {
    this.Video = Video
  }
}
