package com.ebeijia.entity.vo.wechat.mass

/**
 * SendImageMass群发所有，用户组 
 * @author zhicheng.xu
 *         15/8/12
 */
class SendImageMass extends BaseMass {
  private var filter: Filter = null
  private var image: Image = null

  def getFilter: Filter = {
    filter
  }

  def setFilter(filter: Filter) {
    this.filter = filter
  }

  def getImage: Image = {
    image
  }

  def setImage(image: Image) {
    this.image = image
  }
}
