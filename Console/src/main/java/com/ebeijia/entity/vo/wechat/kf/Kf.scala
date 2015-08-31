package com.ebeijia.entity.vo.wechat.kf

/**
 * Kf
 * @author zhicheng.xu
 *         15/8/12
 */
class Kf {
  private var kf_account: String = null
  private var nickname: String = null
  private var password: String = null

  def getKf_account: String = {
    kf_account
  }

  def setKf_account(kf_account: String) {
    this.kf_account = kf_account
  }

  def getNickname: String = {
    nickname
  }

  def setNickname(nickname: String) {
    this.nickname = nickname
  }

  def getPassword: String = {
    password
  }

  def setPassword(password: String) {
    this.password = password
  }
}
