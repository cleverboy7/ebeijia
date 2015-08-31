package com.ebeijia.entity.vo.wechat.resp

/**
 * Image图片model
 * @author zhicheng.xu
 *         15/8/12
 */
class Image {
  private var MediaId: String = null

  def getMediaId: String = {
    MediaId
  }

  def setMediaId(MediaId: String) {
    this.MediaId = MediaId
  }
}
