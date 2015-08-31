package com.ebeijia.entity.wechat

import javax.persistence.{GenerationType, GeneratedValue, Column, Embeddable}

import org.apache.commons.lang.builder.{EqualsBuilder, HashCodeBuilder}

/**
 * TblWechatRuleId
 * @author zhicheng.xu
 *         15/8/12
 */


@Embeddable
@SerialVersionUID(1L)
class TblWechatRuleId extends java.io.Serializable {
  private var modId: String = null
  private var ruleId: String = null



  @Column(name = "MOD_ID", length = 20) def getModId: String = {
    modId
  }

  def setModId(modId: String) {
    this.modId = modId
  }

  @Column(name = "RULE_ID", length = 20)
  @GeneratedValue(strategy = GenerationType.AUTO)
  def getRuleId: String = {
    ruleId
  }

  def setRuleId(ruleId: String) {
    this.ruleId = ruleId
  }

//  override def equals(obj: AnyRef): Boolean = {
//    if (!(obj.isInstanceOf[TblWechatRuleId])) {
//      false
//    }
//    val data: TblWechatRuleId = obj.asInstanceOf[TblWechatRuleId]
//    new EqualsBuilder().appendSuper(super == obj).append(this.modId, data.modId).append(this.ruleId, data.ruleId).isEquals
//  }

  override def hashCode: Int = {
    new HashCodeBuilder(-528253723, -475504089).appendSuper(super.hashCode).append(this.ruleId).append(this.modId).toHashCode
  }
}
