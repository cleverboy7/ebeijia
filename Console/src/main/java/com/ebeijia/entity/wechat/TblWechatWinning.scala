package com.ebeijia.entity.wechat

import javax.persistence._

/**
 * TblWechatWinning
 * @author zhicheng.xu
 *         15/8/12
 */


@Entity
@Table(name = "TBL_WECHAT_WINNING")
@SerialVersionUID(1L)
class TblWechatWinning extends java.io.Serializable {
  private var id: String = null
  private var mchtId: String = null
  private var openid: String = null
  private var prize: String = null
  private var mobile: String = null
  private var snCode: String = null
  private var actId: String = null
  private var actType: String = null
  private var createDate: String = null



  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "MOD_ID", nullable = false, length = 20) def getId: String = {
    id
  }

  def setId(id: String) {
    this.id = id
  }

  @Column(name = "MCHT_ID", length = 15) def getMchtId: String = {
    mchtId
  }

  def setMchtId(mchtId: String) {
    this.mchtId = mchtId
  }

  @Column(name = "ACT_TYPE", length = 1) def getActType: String = {
    actType
  }

  def setActType(actType: String) {
    this.actType = actType
  }

  @Column(name = "OPENID", length = 256) def getOpenid: String = {
    openid
  }

  def setOpenid(openid: String) {
    this.openid = openid
  }

  @Column(name = "PRIZE", length = 32) def getPrize: String = {
    prize
  }

  def setPrize(prize: String) {
    this.prize = prize
  }

  @Column(name = "MOBILE", length = 12) def getMobile: String = {
    mobile
  }

  def setMobile(mobile: String) {
    this.mobile = mobile
  }

  @Column(name = "SN_CODE", length = 20) def getSnCode: String = {
    snCode
  }

  def setSnCode(snCode: String) {
    this.snCode = snCode
  }

  @Column(name = "CREATE_DATE", length = 8) def getCreateDate: String = {
    createDate
  }

  def setCreateDate(createDate: String) {
    this.createDate = createDate
  }

  @Column(name = "ACT_ID", length = 20) def getActId: String = {
    actId
  }

  def setActId(actId: String) {
    this.actId = actId
  }
}

