package com.ebeijia.entity.wechat

import javax.persistence.{Column, Embeddable}

import org.apache.commons.lang.builder.{EqualsBuilder, HashCodeBuilder}

/**
 * TblWechatSnId
 * @author zhicheng.xu
 *         15/8/12
 */

@Embeddable
@SerialVersionUID(1L)
class TblWechatSnId extends java.io.Serializable {
  private var mchtId: String = null
  private var snCode: String = null



  @Column(name = "MCHT_ID", length = 15) def getMchtId: String = {
    mchtId
  }

  def setMchtId(mchtId: String) {
    this.mchtId = mchtId
  }

  @Column(name = "SN_CODE", length = 20) def getSnCode: String = {
    snCode
  }

  def setSnCode(snCode: String) {
    this.snCode = snCode
  }

//  override def equals(obj: AnyRef): Boolean = {
//    if (!(obj.isInstanceOf[TblWechatSnId])) {
//      false
//    }
//    val data: TblWechatSnId = obj.asInstanceOf[TblWechatSnId]
//    new EqualsBuilder().appendSuper(super == obj).append(this.snCode, data.snCode).append(this.mchtId, data.mchtId).isEquals
//  }

  override def hashCode: Int = {
    new HashCodeBuilder(-528253723, -475504089).appendSuper(super.hashCode).append(this.snCode).append(this.mchtId).toHashCode
  }
}

