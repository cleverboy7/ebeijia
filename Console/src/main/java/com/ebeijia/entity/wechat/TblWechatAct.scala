package com.ebeijia.entity.wechat

import javax.persistence._

/**
 * TblWechatAct
 * @author zhicheng.xu
 *         15/8/12
 */


@Entity
@Table(name = "TBL_WECHAT_ACT")
@SerialVersionUID(1L)
class TblWechatAct extends java.io.Serializable {
  private var actId: String = null
  private var mchtId: String = null
  private var actName: String = null
  private var dsc: String = null
  private var actType: String = null
  private var modId: String = null
  private var dayFlag: String = null
  private var lotNum: Int = 0
  private var beginDate: String = null
  private var endDate: String = null
  private var status: String = null
  private var url: String = null

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ACT_ID", nullable = false, length = 20) def getActId: String = {
    actId
  }

  def setActId(actId: String) {
    this.actId = actId
  }

  @Column(name = "URL", length = 256) def getUrl: String = {
    url
  }

  @Column(name = "MCHT_ID", length = 15) def getMchtId: String = {
    mchtId
  }

  def setMchtId(mchtId: String) {
    this.mchtId = mchtId
  }

  @Column(name = "ACT_NAME", length = 32) def getActName: String = {
    actName
  }

  def setActName(actName: String) {
    this.actName = actName
  }

  @Column(name = "DSC", length = 128) def getDsc: String = {
    dsc
  }

  def setDsc(dsc: String) {
    this.dsc = dsc
  }

  @Column(name = "ACT_TYPE", length = 1) def getActType: String = {
    actType
  }

  def setActType(actType: String) {
    this.actType = actType
  }

  @Column(name = "MOD_ID", length = 20) def getModId: String = {
    modId
  }

  def setModId(modId: String) {
    this.modId = modId
  }

  @Column(name = "DAY_FLAG", length = 1) def getDayFlag: String = {
    dayFlag
  }

  def setDayFlag(dayFlag: String) {
    this.dayFlag = dayFlag
  }

  @Column(name = "LOT_NUM", length = 2) def getLotNum: Int = {
    lotNum
  }

  def setLotNum(lotNum: Int) {
    this.lotNum = lotNum
  }

  @Column(name = "BEGIN_DATE", length = 8) def getBeginDate: String = {
    beginDate
  }

  def setBeginDate(beginDate: String) {
    this.beginDate = beginDate
  }

  @Column(name = "END_DATE", length = 8) def getEndDate: String = {
    endDate
  }

  def setEndDate(endDate: String) {
    this.endDate = endDate
  }

  @Column(name = "STATUS", length = 1) def getStatus: String = {
    status
  }

  def setStatus(status: String) {
    this.status = status
  }

  def setUrl(url: String) {
    this.url = url
  }
}

