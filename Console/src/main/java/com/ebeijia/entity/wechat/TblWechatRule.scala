package com.ebeijia.entity.wechat

import javax.persistence.{AttributeOverride, AttributeOverrides, Column, EmbeddedId, Entity, Table}

/**
 * TblWechatRule
 * @author zhicheng.xu
 *         15/8/12
 */


@Entity
@Table(name = "TBL_WECHAT_RULE")
@SerialVersionUID(1L)
class TblWechatRule extends java.io.Serializable {
  private var id: TblWechatRuleId = null
  private var prName: String = null
  private var prize: String = null
  private var winNum: String = null
  private var probability: Double = .0



  @EmbeddedId
  @AttributeOverrides(Array(new AttributeOverride(name = "ruleId", column = new Column(name = "RULE_ID", nullable = false, length = 20)), new AttributeOverride(name = "modId", column = new Column(name = "MOD_ID", nullable = false, length = 15)))) def getId: TblWechatRuleId = {
    id
  }

  def setId(id: TblWechatRuleId) {
    this.id = id
  }

  @Column(name = "PR_NAME", length = 32) def getPrName: String = {
    prName
  }

  def setPrName(prName: String) {
    this.prName = prName
  }

  @Column(name = "PRIZE", length = 64) def getPrize: String = {
    prize
  }

  def setPrize(prize: String) {
    this.prize = prize
  }

  @Column(name = "WIN_NUM", length = 2) def getWinNum: String = {
    winNum
  }

  def setWinNum(winNum: String) {
    this.winNum = winNum
  }

  @Column(name = "PROBABILITY") def getProbability: Double = {
    probability
  }

  def setProbability(probability: Double) {
    this.probability = probability
  }
}

