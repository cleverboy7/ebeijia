package com.ebeijia.entity.vo.wechat.mass
import java.util.List
/**
 * ToUser
 * @author zhicheng.xu
 *         15/8/19
 */

class ToUser {
  private var toUser: List[_] = null

  def getToUser: List[_] = {
    toUser
  }

  def setToUser(toUser: List[_]) {
    this.toUser = toUser
  }
}