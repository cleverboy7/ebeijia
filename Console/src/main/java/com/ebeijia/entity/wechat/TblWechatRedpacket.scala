package com.ebeijia.entity.wechat

import javax.persistence.{AttributeOverride, AttributeOverrides, Column, EmbeddedId, Entity, Table}

/**
 * TblWechatRedpacket
 * @author zhicheng.xu
 *         15/8/12
 */


@Entity
@Table(name = "TBL_WECHAT_REDPACKET")
@SerialVersionUID(1L)
class TblWechatRedpacket extends java.io.Serializable {
  private var id: TblWechatRedpacketId = null
  private var sendName: String = null
  private var openid: String = null
  private var totalAmount: Int = 0
  private var minValue: Int = 0
  private var maxValue: Int = 0
  private var totalNum: Int = 0
  private var wishing: String = null
  private var actName: String = null
  private var remark: String = null
  private var logoImgUrl: String = null
  private var shareContent: String = null
  private var shareUrl: String = null
  private var shareImgUrl: String = null
  private var status: String = null
  private var createTime: String = null



  @EmbeddedId
  @AttributeOverrides(Array(new AttributeOverride(name = "nonceStr", column = new Column(name = "NONCE_STR", nullable = false, length = 32)), new AttributeOverride(name = "mchtId", column = new Column(name = "MCHT_ID", nullable = false, length = 32)))) def getId: TblWechatRedpacketId = {
    id
  }

  def setId(id: TblWechatRedpacketId) {
    this.id = id
  }

  @Column(name = "SEND_NAME", length = 32) def getSendName: String = {
    sendName
  }

  def setSendName(sendName: String) {
    this.sendName = sendName
  }

  @Column(name = "RE_OPENID", length = 32) def getOpenid: String = {
    openid
  }

  def setOpenid(openid: String) {
    this.openid = openid
  }

  @Column(name = "TOTAL_AMOUNT", length = 4) def getTotalAmount: Int = {
    totalAmount
  }

  def setTotalAmount(totalAmount: Int) {
    this.totalAmount = totalAmount
  }

  @Column(name = "MIN_VALUE", length = 4) def getMinValue: Int = {
    minValue
  }

  def setMinValue(minValue: Int) {
    this.minValue = minValue
  }

  @Column(name = "MAX_VALUE", length = 4) def getMaxValue: Int = {
    maxValue
  }

  def setMaxValue(maxValue: Int) {
    this.maxValue = maxValue
  }

  @Column(name = "TOTAL_NUM", length = 10) def getTotalNum: Int = {
    totalNum
  }

  def setTotalNum(totalNum: Int) {
    this.totalNum = totalNum
  }

  @Column(name = "WISHING", length = 128) def getWishing: String = {
    wishing
  }

  def setWishing(wishing: String) {
    this.wishing = wishing
  }

  @Column(name = "ACT_NAME", length = 32) def getActName: String = {
    actName
  }

  def setActName(actName: String) {
    this.actName = actName
  }

  @Column(name = "REMARK", length = 256) def getRemark: String = {
    remark
  }

  def setRemark(remark: String) {
    this.remark = remark
  }

  @Column(name = "LOGO_IMGURL", length = 128) def getLogoImgUrl: String = {
    logoImgUrl
  }

  def setLogoImgUrl(logoImgUrl: String) {
    this.logoImgUrl = logoImgUrl
  }

  @Column(name = "SHARE_CONTENT", length = 256) def getShareContent: String = {
    shareContent
  }

  def setShareContent(shareContent: String) {
    this.shareContent = shareContent
  }

  @Column(name = "SHARE_URL", length = 256) def getShareUrl: String = {
    shareUrl
  }

  def setShareUrl(shareUrl: String) {
    this.shareUrl = shareUrl
  }

  @Column(name = "SHARE_IMGURL", length = 10) def getShareImgUrl: String = {
    shareImgUrl
  }

  def setShareImgUrl(shareImgUrl: String) {
    this.shareImgUrl = shareImgUrl
  }

  @Column(name = "STATUS", length = 1) def getStatus: String = {
    status
  }

  def setStatus(status: String) {
    this.status = status
  }

  @Column(name = "CREATE_TIME", length = 14) def getCreateTime: String = {
    createTime
  }

  def setCreateTime(createTime: String) {
    this.createTime = createTime
  }
}

