package com.ebeijia.entity

/**
 * TblUsrInf
 * @author zhicheng.xu
 *         15/8/11
 */

import javax.persistence.{Column, Entity, Id, Table}

@Entity
@Table(name = "TBL_USR_INF")
@SerialVersionUID(1L)
class TblUsrInf extends java.io.Serializable {
  private var usrId: String = null
  private var usrType: String = null
  private var usrLev: String = null
  private var mobileNo: String = null
  private var loginPwd: String = null
  private var usrName: String = null
  private var usrRname: String = null
  private var usrStat: String = null
  private var usrLastLogTime: String = null
  private var usrSex: String = null
  private var usrMarriageStat: String = null
  private var usrEducation: String = null
  private var usrRegTime: String = null
  private var usrLockTime: String = null
  private var usrNum: Integer = null
  private var usrBirthday: String = null
  private var usrCerNo: String = null
  private var usrEmail: String = null
  private var usrPostNo: String = null
  private var usrAddr: String = null
  private var usrWchatNo: String = null
  private var usrWboNo: String = null
  private var usrNo1: String = null
  private var usrNo2: String = null
  private var usrPic: String = null
  private var usrSign: String = null
  private var usrNote: String = null
  private var usrJob: String = null
  private var usrWage: Integer = null
  private var usrSpecialty: String = null
  private var usrAge: Integer = null
  private var usrHigh: Integer = null
  private var usrWeight: Integer = null
  private var usrColor: String = null
  private var usrNumber: String = null
  private var usrConste: String = null
  private var usrZodiac: String = null
  private var usrNo: String = null
  private var inviteNo: String = null
  private var usrBlood: String = null
  private var usrHobby: String = null


  /** minimal constructor */
  def this(usrId: String, mobileNo: String, loginPwd: String, usrRegTime: String) {
    this()
    this.usrId = usrId
    this.mobileNo = mobileNo
    this.loginPwd = loginPwd
    this.usrRegTime = usrRegTime
  }

  /** full constructor */
  def this(usrId: String, usrType: String, usrLvl: String, mobileNo: String, loginPwd: String, opnPwd: String, usrName: String, usrRname: String, usrStat: String, usrLogStat: String, usrLastLogTime: String, usrFlag: String, usrSex: String, usrMarriageStat: String, usrEducation: String, usrRegTime: String, usrBirthday: String, usrCerType: String, usrCerNo: String, usrEmail: String, usrPostNo: String, usrAddr: String, usrWchatNo: String, usrWboNo: String, usrNo1: String, usrNo2: String, usrPic: String, usrNote: String, usrLockTime: String, usrNum: Integer, usrSign: String, usrWage: Integer, usrSpecialty: String, usrAge: Integer, usrHigh: Integer, usrWeight: Integer, usrColor: String, usrNumber: String, usrConste: String, usrZodiac: String, usrNo: String, inviteNo: String, usrBlood: String, usrHobby: String) {
    this()
    this.usrId = usrId
    this.usrType = usrType
    this.mobileNo = mobileNo
    this.loginPwd = loginPwd
    this.usrName = usrName
    this.usrRname = usrRname
    this.usrStat = usrStat
    this.usrLastLogTime = usrLastLogTime
    this.usrSex = usrSex
    this.usrMarriageStat = usrMarriageStat
    this.usrEducation = usrEducation
    this.usrRegTime = usrRegTime
    this.usrBirthday = usrBirthday
    this.usrCerNo = usrCerNo
    this.usrEmail = usrEmail
    this.usrPostNo = usrPostNo
    this.usrAddr = usrAddr
    this.usrWchatNo = usrWchatNo
    this.usrWboNo = usrWboNo
    this.usrNo1 = usrNo1
    this.usrNo2 = usrNo2
    this.usrPic = usrPic
    this.usrNote = usrNote
    this.usrLockTime = usrLockTime
    this.usrNum = usrNum
    this.usrSign = usrSign
    this.usrWage = usrWage
    this.usrSpecialty = usrSpecialty
    this.usrAge = usrAge
    this.usrHigh = usrHigh
    this.usrWeight = usrWeight
    this.usrColor = usrColor
    this.usrNumber = usrNumber
    this.usrConste = usrConste
    this.usrZodiac = usrZodiac
    this.usrNo = usrNo
    this.inviteNo = inviteNo
    this.usrBlood = usrBlood
    this.usrHobby = usrHobby
  }

  @Id
  @Column(name = "USR_ID", unique = true, nullable = false, length = 10) def getUsrId: String = {
    this.usrId
  }

  def setUsrId(usrId: String) {
    this.usrId = usrId
  }

  @Column(name = "USR_LEV", length = 2) def getUsrLev: String = {
    usrLev
  }

  def setUsrLev(usrLev: String) {
    this.usrLev = usrLev
  }

  @Column(name = "USR_TYPE", length = 2) def getUsrType: String = {
    this.usrType
  }

  def setUsrType(usrType: String) {
    this.usrType = usrType
  }

  @Column(name = "MOBILE_NO", unique = true, nullable = false, length = 20) def getMobileNo: String = {
    this.mobileNo
  }

  def setMobileNo(mobileNo: String) {
    this.mobileNo = mobileNo
  }

  @Column(name = "LOGIN_PWD", nullable = false, length = 32) def getLoginPwd: String = {
    this.loginPwd
  }

  def setLoginPwd(loginPwd: String) {
    this.loginPwd = loginPwd
  }

  @Column(name = "USR_NAME", length = 32) def getUsrName: String = {
    this.usrName
  }

  def setUsrName(usrName: String) {
    this.usrName = usrName
  }

  @Column(name = "USR_RNAME", length = 32) def getUsrRname: String = {
    this.usrRname
  }

  def setUsrRname(usrRname: String) {
    this.usrRname = usrRname
  }

  @Column(name = "USR_STAT", length = 1) def getUsrStat: String = {
    this.usrStat
  }

  def setUsrStat(usrStat: String) {
    this.usrStat = usrStat
  }

  @Column(name = "USR_LAST_LOG_TIME", length = 14) def getUsrLastLogTime: String = {
    this.usrLastLogTime
  }

  def setUsrLastLogTime(usrLastLogTime: String) {
    this.usrLastLogTime = usrLastLogTime
  }

  @Column(name = "USR_SEX", length = 1) def getUsrSex: String = {
    this.usrSex
  }

  def setUsrSex(usrSex: String) {
    this.usrSex = usrSex
  }

  @Column(name = "USR_MARRIAGE_STAT", length = 1) def getUsrMarriageStat: String = {
    this.usrMarriageStat
  }

  def setUsrMarriageStat(usrMarriageStat: String) {
    this.usrMarriageStat = usrMarriageStat
  }

  @Column(name = "USR_EDUCATION", length = 1) def getUsrEducation: String = {
    this.usrEducation
  }

  def setUsrEducation(usrEducation: String) {
    this.usrEducation = usrEducation
  }

  @Column(name = "USR_REG_TIME", length = 14) def getUsrRegTime: String = {
    this.usrRegTime
  }

  def setUsrRegTime(usrRegTime: String) {
    this.usrRegTime = usrRegTime
  }

  @Column(name = "USR_BIRTHDAY", length = 8) def getUsrBirthday: String = {
    this.usrBirthday
  }

  def setUsrBirthday(usrBirthday: String) {
    this.usrBirthday = usrBirthday
  }

  @Column(name = "USR_CER_NO", length = 32) def getUsrCerNo: String = {
    this.usrCerNo
  }

  def setUsrCerNo(usrCerNo: String) {
    this.usrCerNo = usrCerNo
  }

  @Column(name = "USR_EMAIL", length = 64) def getUsrEmail: String = {
    this.usrEmail
  }

  def setUsrEmail(usrEmail: String) {
    this.usrEmail = usrEmail
  }

  @Column(name = "USR_POST_NO", length = 6) def getUsrPostNo: String = {
    this.usrPostNo
  }

  def setUsrPostNo(usrPostNo: String) {
    this.usrPostNo = usrPostNo
  }

  @Column(name = "USR_ADDR", length = 256) def getUsrAddr: String = {
    this.usrAddr
  }

  def setUsrAddr(usrAddr: String) {
    this.usrAddr = usrAddr
  }

  @Column(name = "USR_WCHAT_NO", length = 32) def getUsrWchatNo: String = {
    this.usrWchatNo
  }

  def setUsrWchatNo(usrWchatNo: String) {
    this.usrWchatNo = usrWchatNo
  }

  @Column(name = "USR_WBO_NO", length = 32) def getUsrWboNo: String = {
    this.usrWboNo
  }

  def setUsrWboNo(usrWboNo: String) {
    this.usrWboNo = usrWboNo
  }

  @Column(name = "USR_NO1", length = 32) def getUsrNo1: String = {
    this.usrNo1
  }

  def setUsrNo1(usrNo1: String) {
    this.usrNo1 = usrNo1
  }

  @Column(name = "USR_NO2", length = 32) def getUsrNo2: String = {
    this.usrNo2
  }

  def setUsrNo2(usrNo2: String) {
    this.usrNo2 = usrNo2
  }

  @Column(name = "USR_PIC", length = 10) def getUsrPic: String = {
    this.usrPic
  }

  def setUsrPic(usrPic: String) {
    this.usrPic = usrPic
  }

  @Column(name = "USR_NOTE", length = 256) def getUsrNote: String = {
    this.usrNote
  }

  def setUsrNote(usrNote: String) {
    this.usrNote = usrNote
  }

  @Column(name = "USR_LOCK_TIME", length = 14) def getUsrLockTime: String = {
    usrLockTime
  }

  def setUsrLockTime(usrLockTime: String) {
    this.usrLockTime = usrLockTime
  }

  @Column(name = "USR_NUM", length = 1) def getUsrNum: Integer = {
    usrNum
  }

  def setUsrNum(usrNum: Integer) {
    this.usrNum = usrNum
  }

  @Column(name = "USR_SIGN", length = 64) def getUsrSign: String = {
    usrSign
  }

  def setUsrSign(usrSign: String) {
    this.usrSign = usrSign
  }

  @Column(name = "USR_JOB", length = 32) def getUsrJob: String = {
    usrJob
  }

  def setUsrJob(usrJob: String) {
    this.usrJob = usrJob
  }

  @Column(name = "USR_WAGE", length = 10) def getUsrWage: Integer = {
    usrWage
  }

  def setUsrWage(usrWage: Integer) {
    this.usrWage = usrWage
  }

  @Column(name = "USR_SPECIALTY", length = 128) def getUsrSpecialty: String = {
    usrSpecialty
  }

  def setUsrSpecialty(usrSpecialty: String) {
    this.usrSpecialty = usrSpecialty
  }

  @Column(name = "USR_AGE", length = 3) def getUsrAge: Integer = {
    usrAge
  }

  def setUsrAge(usrAge: Integer) {
    this.usrAge = usrAge
  }

  @Column(name = "USR_HIGH", length = 3) def getUsrHigh: Integer = {
    usrHigh
  }

  def setUsrHigh(usrHigh: Integer) {
    this.usrHigh = usrHigh
  }

  @Column(name = "USR_WEIGHT", length = 3) def getUsrWeight: Integer = {
    usrWeight
  }

  def setUsrWeight(usrWeight: Integer) {
    this.usrWeight = usrWeight
  }

  @Column(name = "USR_COLOR", length = 15) def getUsrColor: String = {
    usrColor
  }

  def setUsrColor(usrColor: String) {
    this.usrColor = usrColor
  }

  @Column(name = "USR_NUMBER", length = 5) def getUsrNumber: String = {
    usrNumber
  }

  def setUsrNumber(usrNumber: String) {
    this.usrNumber = usrNumber
  }

  @Column(name = "USR_CONSTE", length = 16) def getUsrConste: String = {
    usrConste
  }

  def setUsrConste(usrConste: String) {
    this.usrConste = usrConste
  }

  @Column(name = "USR_ZODIAC", length = 3) def getUsrZodiac: String = {
    usrZodiac
  }

  def setUsrZodiac(usrZodiac: String) {
    this.usrZodiac = usrZodiac
  }

  @Column(name = "USR_NO", length = 6) def getUsrNo: String = {
    usrNo
  }

  def setUsrNo(usrNo: String) {
    this.usrNo = usrNo
  }

  @Column(name = "INVITE_NO", length = 6) def getInviteNo: String = {
    inviteNo
  }

  def setInviteNo(inviteNo: String) {
    this.inviteNo = inviteNo
  }

  @Column(name = "USR_BLOOD", length = 2) def getUsrBlood: String = {
    usrBlood
  }

  def setUsrBlood(usrBlood: String) {
    this.usrBlood = usrBlood
  }

  @Column(name = "USR_HOBBY", length = 30) def getUsrHobby: String = {
    usrHobby
  }

  def setUsrHobby(usrHobby: String) {
    this.usrHobby = usrHobby
  }
}
