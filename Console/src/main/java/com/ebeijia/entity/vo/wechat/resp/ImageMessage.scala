package com.ebeijia.entity.vo.wechat.resp

/**
 * ImageMessage图片消息
 * @author zhicheng.xu
 *         15/8/12
 */
class ImageMessage extends BaseMessage {
  private var Image: Image = null

  def getImage: Image = {
    Image
  }

  def setImage(Image: Image) {
    this.Image = Image
  }
}
