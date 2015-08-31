package com.ebeijia.entity.vo.wechat.resp

/**
 * Video视频model
 * @author zhicheng.xu
 *         15/8/12
 */
class Video {
  private var MediaId: String = null
  private var Title: String = null
  private var Description: String = null

  def getMediaId: String = {
    MediaId
  }

  def setMediaId(MediaId: String) {
    this.MediaId = MediaId
  }

  def getTitle: String = {
    Title
  }

  def setTitle(Title: String) {
    this.Title = Title
  }

  def getDescription: String = {
    Description
  }

  def setDescription(Description: String) {
    this.Description = Description
  }
}
