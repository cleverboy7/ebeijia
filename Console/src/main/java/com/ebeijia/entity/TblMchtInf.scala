package com.ebeijia.entity

import javax.persistence.{Column, Entity, Id, Table}

/**
 * TblMchtInf
 * @author zhicheng.xu
 *         15/8/11
 */


@Entity
@Table(name = "TBL_MCHT_INF")
@SerialVersionUID(1L)
class TblMchtInf extends java.io.Serializable {
  private var mchtId: String = null
  private var mchtName: String = null
  private var mchtType: String = null
  private var mchtBusType: String = null
  private var mchtLvl: String = null
  private var upperMchtId: String = null
  private var mchtStat: String = null
  private var mchtNationCd: String = null
  private var mchtCityCd: String = null
  private var mchtDistCd: String = null
  private var mchtBusDistCd: String = null
  private var mchtAddr: String = null
  private var mchtPostCd: String = null
  private var mchtTel1: String = null
  private var mchtTel2: String = null
  private var mchtTel3: String = null
  private var mchtUsr1: String = null
  private var mchtMobile1: String = null
  private var mchtEmail: String = null
  private var mchtUsr2: String = null
  private var mchtMobile2: String = null
  private var mchtEmail2: String = null
  private var mchtUrl: String = null
  private var mchtWapUrl: String = null
  private var mchtLongitude: String = null
  private var mchtLatitude: String = null
  private var mchtTasteGd: Double = .0
  private var mchtServiceGd: Double = .0
  private var mchtEnvGd: Double = .0
  private var mchtGd: Double = .0
  private var mchtGdPartNum: Double = .0
  private var mchtDsc: String = null
  private var mchtPlatId: String = null
  private var ptrMchtId: String = null
  private var avePrice: String = null
  private var remark: String = null
  private var serviceFee: String = null

  /** minimal constructor */
  def this(mchtId: String) {
    this()
    this.mchtId = mchtId
  }

  /** full constructor */
  def this(mchtId: String, mchtName: String, mchtType: String, mchtBusType: String, mchtLvl: String, upperMchtId: String, mchtStat: String, mchtNationCd: String, mchtCityCd: String, mchtDistCd: String, mchtBusDistCd: String, mchtAddr: String, mchtPostCd: String, mchtTel1: String, mchtTel2: String, mchtTel3: String, mchtUsr1: String, mchtMobile1: String, mchtEmail: String, mchtUsr2: String, mchtMobile2: String, mchtEmail2: String, mchtUrl: String, mchtWapUrl: String, mchtLongitude: String, mchtLatitude: String, mchtTasteGd: Double, mchtServiceGd: Double, mchtEnvGd: Double, mchtGd: Double, mchtGdPartNum: Double, mchtDsc: String, mchtPlatId: String, ptrMchtId: String, avePrice: String, remark: String, serviceFee: String) {
    this()
    this.mchtId = mchtId
    this.mchtName = mchtName
    this.mchtType = mchtType
    this.mchtBusType = mchtBusType
    this.mchtLvl = mchtLvl
    this.upperMchtId = upperMchtId
    this.mchtStat = mchtStat
    this.mchtNationCd = mchtNationCd
    this.mchtCityCd = mchtCityCd
    this.mchtDistCd = mchtDistCd
    this.mchtBusDistCd = mchtBusDistCd
    this.mchtAddr = mchtAddr
    this.mchtPostCd = mchtPostCd
    this.mchtTel1 = mchtTel1
    this.mchtTel2 = mchtTel2
    this.mchtTel3 = mchtTel3
    this.mchtUsr1 = mchtUsr1
    this.mchtMobile1 = mchtMobile1
    this.mchtEmail = mchtEmail
    this.mchtUsr2 = mchtUsr2
    this.mchtMobile2 = mchtMobile2
    this.mchtEmail2 = mchtEmail2
    this.mchtUrl = mchtUrl
    this.mchtWapUrl = mchtWapUrl
    this.mchtLongitude = mchtLongitude
    this.mchtLatitude = mchtLatitude
    this.mchtTasteGd = mchtTasteGd
    this.mchtServiceGd = mchtServiceGd
    this.mchtEnvGd = mchtEnvGd
    this.mchtGd = mchtGd
    this.mchtGdPartNum = mchtGdPartNum
    this.mchtDsc = mchtDsc
    this.mchtPlatId = mchtPlatId
    this.ptrMchtId = ptrMchtId
    this.avePrice = avePrice
    this.remark = remark
    this.serviceFee = serviceFee
  }

  @Id
  @Column(name = "MCHT_ID", unique = true, nullable = false, length = 15) def getMchtId: String = {
    this.mchtId
  }

  def setMchtId(mchtId: String) {
    this.mchtId = mchtId
  }

  @Column(name = "MCHT_NAME", length = 256) def getMchtName: String = {
    this.mchtName
  }

  def setMchtName(mchtName: String) {
    this.mchtName = mchtName
  }

  @Column(name = "MCHT_TYPE", length = 2) def getMchtType: String = {
    this.mchtType
  }

  def setMchtType(mchtType: String) {
    this.mchtType = mchtType
  }

  @Column(name = "MCHT_BUS_TYPE", length = 2) def getMchtBusType: String = {
    this.mchtBusType
  }

  def setMchtBusType(mchtBusType: String) {
    this.mchtBusType = mchtBusType
  }

  @Column(name = "MCHT_LVL", length = 2) def getMchtLvl: String = {
    this.mchtLvl
  }

  def setMchtLvl(mchtLvl: String) {
    this.mchtLvl = mchtLvl
  }

  @Column(name = "UPPER_MCHT_ID", length = 15) def getUpperMchtId: String = {
    this.upperMchtId
  }

  def setUpperMchtId(upperMchtId: String) {
    this.upperMchtId = upperMchtId
  }

  @Column(name = "MCHT_STAT", length = 1) def getMchtStat: String = {
    this.mchtStat
  }

  def setMchtStat(mchtStat: String) {
    this.mchtStat = mchtStat
  }

  @Column(name = "MCHT_NATION_CD", length = 3) def getMchtNationCd: String = {
    this.mchtNationCd
  }

  def setMchtNationCd(mchtNationCd: String) {
    this.mchtNationCd = mchtNationCd
  }

  @Column(name = "MCHT_CITY_CD", length = 3) def getMchtCityCd: String = {
    this.mchtCityCd
  }

  def setMchtCityCd(mchtCityCd: String) {
    this.mchtCityCd = mchtCityCd
  }

  @Column(name = "MCHT_DIST_CD", length = 3) def getMchtDistCd: String = {
    this.mchtDistCd
  }

  def setMchtDistCd(mchtDistCd: String) {
    this.mchtDistCd = mchtDistCd
  }

  @Column(name = "MCHT_BUS_DIST_CD", length = 3) def getMchtBusDistCd: String = {
    this.mchtBusDistCd
  }

  def setMchtBusDistCd(mchtBusDistCd: String) {
    this.mchtBusDistCd = mchtBusDistCd
  }

  @Column(name = "MCHT_ADDR", length = 256) def getMchtAddr: String = {
    this.mchtAddr
  }

  def setMchtAddr(mchtAddr: String) {
    this.mchtAddr = mchtAddr
  }

  @Column(name = "MCHT_POST_CD", length = 6) def getMchtPostCd: String = {
    this.mchtPostCd
  }

  def setMchtPostCd(mchtPostCd: String) {
    this.mchtPostCd = mchtPostCd
  }

  @Column(name = "MCHT_TEL1", length = 20) def getMchtTel1: String = {
    this.mchtTel1
  }

  def setMchtTel1(mchtTel1: String) {
    this.mchtTel1 = mchtTel1
  }

  @Column(name = "MCHT_TEL2", length = 20) def getMchtTel2: String = {
    this.mchtTel2
  }

  def setMchtTel2(mchtTel2: String) {
    this.mchtTel2 = mchtTel2
  }

  @Column(name = "MCHT_TEL3", length = 20) def getMchtTel3: String = {
    this.mchtTel3
  }

  def setMchtTel3(mchtTel3: String) {
    this.mchtTel3 = mchtTel3
  }

  @Column(name = "MCHT_USR1", length = 32) def getMchtUsr1: String = {
    this.mchtUsr1
  }

  def setMchtUsr1(mchtUsr1: String) {
    this.mchtUsr1 = mchtUsr1
  }

  @Column(name = "MCHT_MOBILE1", length = 20) def getMchtMobile1: String = {
    this.mchtMobile1
  }

  def setMchtMobile1(mchtMobile1: String) {
    this.mchtMobile1 = mchtMobile1
  }

  @Column(name = "MCHT_EMAIL", length = 64) def getMchtEmail: String = {
    this.mchtEmail
  }

  def setMchtEmail(mchtEmail: String) {
    this.mchtEmail = mchtEmail
  }

  @Column(name = "MCHT_USR2", length = 32) def getMchtUsr2: String = {
    this.mchtUsr2
  }

  def setMchtUsr2(mchtUsr2: String) {
    this.mchtUsr2 = mchtUsr2
  }

  @Column(name = "MCHT_MOBILE2", length = 20) def getMchtMobile2: String = {
    this.mchtMobile2
  }

  def setMchtMobile2(mchtMobile2: String) {
    this.mchtMobile2 = mchtMobile2
  }

  @Column(name = "MCHT_EMAIL2", length = 64) def getMchtEmail2: String = {
    this.mchtEmail2
  }

  def setMchtEmail2(mchtEmail2: String) {
    this.mchtEmail2 = mchtEmail2
  }

  @Column(name = "MCHT_URL", length = 128) def getMchtUrl: String = {
    this.mchtUrl
  }

  def setMchtUrl(mchtUrl: String) {
    this.mchtUrl = mchtUrl
  }

  @Column(name = "MCHT_WAP_URL", length = 128) def getMchtWapUrl: String = {
    this.mchtWapUrl
  }

  def setMchtWapUrl(mchtWapUrl: String) {
    this.mchtWapUrl = mchtWapUrl
  }

  @Column(name = "MCHT_LONGITUDE", length = 16) def getMchtLongitude: String = {
    this.mchtLongitude
  }

  def setMchtLongitude(mchtLongitude: String) {
    this.mchtLongitude = mchtLongitude
  }

  @Column(name = "MCHT_LATITUDE", length = 16) def getMchtLatitude: String = {
    this.mchtLatitude
  }

  def setMchtLatitude(mchtLatitude: String) {
    this.mchtLatitude = mchtLatitude
  }

  @Column(name = "MCHT_TASTE_GD", precision = 22, scale = 0) def getMchtTasteGd: Double = {
    this.mchtTasteGd
  }

  def setMchtTasteGd(mchtTasteGd: Double) {
    this.mchtTasteGd = mchtTasteGd
  }

  @Column(name = "MCHT_SERVICE_GD", precision = 22, scale = 0) def getMchtServiceGd: Double = {
    this.mchtServiceGd
  }

  def setMchtServiceGd(mchtServiceGd: Double) {
    this.mchtServiceGd = mchtServiceGd
  }

  @Column(name = "MCHT_ENV_GD", precision = 22, scale = 0) def getMchtEnvGd: Double = {
    this.mchtEnvGd
  }

  def setMchtEnvGd(mchtEnvGd: Double) {
    this.mchtEnvGd = mchtEnvGd
  }

  @Column(name = "MCHT_GD", precision = 22, scale = 0) def getMchtGd: Double = {
    this.mchtGd
  }

  def setMchtGd(mchtGd: Double) {
    this.mchtGd = mchtGd
  }

  @Column(name = "MCHT_GD_PART_NUM", precision = 22, scale = 0) def getMchtGdPartNum: Double = {
    this.mchtGdPartNum
  }

  def setMchtGdPartNum(mchtGdPartNum: Double) {
    this.mchtGdPartNum = mchtGdPartNum
  }

  @Column(name = "MCHT_DSC", length = 512) def getMchtDsc: String = {
    this.mchtDsc
  }

  def setMchtDsc(mchtDsc: String) {
    this.mchtDsc = mchtDsc
  }

  @Column(name = "MCHT_PLAT_ID", length = 15) def getMchtPlatId: String = {
    mchtPlatId
  }

  def setMchtPlatId(mchtPlatId: String) {
    this.mchtPlatId = mchtPlatId
  }

  @Column(name = "PTR_MCHT_ID", length = 15) def getPtrMchtId: String = {
    ptrMchtId
  }

  def setPtrMchtId(ptrMchtId: String) {
    this.ptrMchtId = ptrMchtId
  }

  @Column(name = "AVE_PRICE", length = 22) def getAvePrice: String = {
    avePrice
  }

  def setAvePrice(avePrice: String) {
    this.avePrice = avePrice
  }

  @Column(name = "REMARK", length = 256) def getRemark: String = {
    remark
  }

  def setRemark(remark: String) {
    this.remark = remark
  }

  @Column(name = "SERVICE_FEE", precision = 22, scale = 0) def getServiceFee: String = {
    serviceFee
  }

  def setServiceFee(serviceFee: String) {
    this.serviceFee = serviceFee
  }
}
