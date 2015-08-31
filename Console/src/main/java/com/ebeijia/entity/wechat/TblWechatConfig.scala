package com.ebeijia.entity.wechat

import javax.persistence.{Column, Entity, Id, Table}

/**
 * TblWechatConfig
 * @author zhicheng.xu
 *         15/8/12
 */

@Entity
@Table(name = "TBL_WECHAT_CONFIG")
@SerialVersionUID(1L)
class TblWechatConfig extends java.io.Serializable {
  private var seq: String = null
  private var mchtId: String = null
  private var reply: String = null
  private var noMatch: String = null
  private var noMatch2: String = null
  private var lsRange: String = null


  @Id
  @Column(name = "SEQ", unique = true, nullable = false, length = 20) def getSeq: String = {
    seq
  }

  def setSeq(seq: String) {
    this.seq = seq
  }

  @Column(name = "MCHT_ID", nullable = false, length = 15) def getMchtId: String = {
    mchtId
  }

  def setMchtId(mchtId: String) {
    this.mchtId = mchtId
  }

  @Column(name = "REPLY", length = 1) def getReply: String = {
    reply
  }

  def setReply(reply: String) {
    this.reply = reply
  }

  @Column(name = "NO_MATCH", length = 1) def getNoMatch: String = {
    noMatch
  }

  def setNoMatch(noMatch: String) {
    this.noMatch = noMatch
  }

  @Column(name = "NO_MATCH2", length = 200) def getNoMatch2: String = {
    noMatch2
  }

  def setNoMatch2(noMatch2: String) {
    this.noMatch2 = noMatch2
  }

  @Column(name = "LS_RANGE", length = 20) def getLsRange: String = {
    lsRange
  }

  def setLsRange(lsRange: String) {
    this.lsRange = lsRange
  }
}

