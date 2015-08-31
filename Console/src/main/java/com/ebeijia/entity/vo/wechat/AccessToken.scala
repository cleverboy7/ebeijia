package com.ebeijia.entity.vo.wechat

/**
 * 微信通用接口凭证
 * AccessToken
 * @author zhicheng.xu
 *         15/8/11
 */
class AccessToken {
  private var token: String = null
  private var expiresIn: Int = 0

  def getToken: String = {
    token
  }

  def setToken(token: String) {
    this.token = token
  }

  def getExpiresIn: Int = {
    expiresIn
  }

  def setExpiresIn(expiresIn: Int) {
    this.expiresIn = expiresIn
  }
}
