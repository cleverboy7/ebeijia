package com.ebeijia.entity.wechat

import javax.persistence._

/**
 * TblWechatSubscribe
 * @author zhicheng.xu
 *         15/8/12
 */


@Entity
@Table(name = "TBL_WECHAT_SUBSCRIBE")
@SerialVersionUID(1L)
class TblWechatSubscribe extends java.io.Serializable {
  private var subcribeId: String = null
  private var mchtId: String = null
  private var subscribeTiny: Int = 0
  private var openid: String = null
  private var nickname: String = null
  private var sex: Int = 0
  private var city: String = null
  private var country: String = null
  private var province: String = null
  private var language: String = null
  private var headimgurl: String = null
  private var subscribeTime: String = null
  private var createTime: String = null
  private var updateTime: String = null
  private var groupId: String = null
  private var groupName: String = null
  private var remarks: String = null



  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "SUBCRIBE_ID", unique = true, nullable = false, length = 20)
  def getSubcribeId: String = {
    subcribeId
  }

  def setSubcribeId(subcribeId: String) {
    this.subcribeId = subcribeId
  }

  @Column(name = "MCHT_ID", nullable = false, length = 15) def getMchtId: String = {
    mchtId
  }

  def setMchtId(mchtId: String) {
    this.mchtId = mchtId
  }

  @Column(name = "SUBSCRIBE_TINY", length = 4) def getSubscribeTiny: Int = {
    subscribeTiny
  }

  def setSubscribeTiny(subscribeTiny: Int) {
    this.subscribeTiny = subscribeTiny
  }

  @Column(name = "OPENID", length = 256) def getOpenid: String = {
    openid
  }

  def setOpenid(openid: String) {
    this.openid = openid
  }

  @Column(name = "NICKNAME", length = 256) def getNickname: String = {
    nickname
  }

  def setNickname(nickname: String) {
    this.nickname = nickname
  }

  @Column(name = "SEX", length = 4) def getSex: Int = {
    sex
  }

  def setSex(sex: Int) {
    this.sex = sex
  }

  @Column(name = "CITY", length = 256) def getCity: String = {
    city
  }

  def setCity(city: String) {
    this.city = city
  }

  @Column(name = "COUNTRY", length = 256) def getCountry: String = {
    country
  }

  def setCountry(country: String) {
    this.country = country
  }

  @Column(name = "PROVINCE", length = 256) def getProvince: String = {
    province
  }

  def setProvince(province: String) {
    this.province = province
  }

  @Column(name = "LANGUAGE", length = 32) def getLanguage: String = {
    language
  }

  def setLanguage(language: String) {
    this.language = language
  }

  @Column(name = "HEADIMGURL", length = 256) def getHeadimgurl: String = {
    headimgurl
  }

  def setHeadimgurl(headimgurl: String) {
    this.headimgurl = headimgurl
  }

  @Column(name = "SUBSCRIBE_TIME", length = 14) def getSubscribeTime: String = {
    subscribeTime
  }

  def setSubscribeTime(subscribeTime: String) {
    this.subscribeTime = subscribeTime
  }

  @Column(name = "CREATE_TIME", length = 14) def getCreateTime: String = {
    createTime
  }

  def setCreateTime(createTime: String) {
    this.createTime = createTime
  }

  @Column(name = "UPDATE_TIME", length = 14) def getUpdateTime: String = {
    updateTime
  }

  def setUpdateTime(updateTime: String) {
    this.updateTime = updateTime
  }

  @Column(name = "GROUP_ID", length = 6) def getGroupId: String = {
    groupId
  }

  def setGroupId(groupId: String) {
    this.groupId = groupId
  }

  @Column(name = "REMARKS", length = 16) def getRemarks: String = {
    remarks
  }

  def setRemarks(remarks: String) {
    this.remarks = remarks
  }

  @Column(name = "GROUP_NAME", length = 32) def getGroupName: String = {
    groupName
  }

  def setGroupName(groupName: String) {
    this.groupName = groupName
  }
}

