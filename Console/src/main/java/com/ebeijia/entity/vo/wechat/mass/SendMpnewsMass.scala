package com.ebeijia.entity.vo.wechat.mass

/**群发所有，用户组
 * SendMpnewsMass
 * @author zhicheng.xu
 *         15/8/19
 */

class SendMpnewsMass extends BaseMass {
  private var filter: Filter = null
  private var mpnews: Mpnews = null

  def getFilter: Filter = {
    filter
  }

  def setFilter(filter: Filter) {
    this.filter = filter
  }

  def getMpnews: Mpnews = {
    mpnews
  }

  def setMpnews(mpnews: Mpnews) {
    this.mpnews = mpnews
  }
}