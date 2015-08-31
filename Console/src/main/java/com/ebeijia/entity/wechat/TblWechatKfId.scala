package com.ebeijia.entity.wechat

import javax.persistence.{Column, Embeddable}

import org.apache.commons.lang.builder.{EqualsBuilder, HashCodeBuilder}

/**
 * TblWechatKfId
 * @author zhicheng.xu
 *         15/8/12
 */


@Embeddable
@SerialVersionUID(1L)
class TblWechatKfId extends java.io.Serializable {
  private var kfId: String = null
  private var mchtId: String = null


  @Column(name = "KF_ID", unique = true, nullable = false, length = 32) def getKfId: String = {
    kfId
  }

  def setKfId(kfId: String) {
    this.kfId = kfId
  }

  @Column(name = "MCHT_ID", length = 15) def getMchtId: String = {
    mchtId
  }

  def setMchtId(mchtId: String) {
    this.mchtId = mchtId
  }

//  override def equals(obj: AnyRef): Boolean = {
//    if (!(obj.isInstanceOf[TblWechatKfId])) {
//      return false
//    }
//    val data: TblWechatKfId = obj.asInstanceOf[TblWechatKfId]
//    new EqualsBuilder().appendSuper(this.equals(AnyRef) == obj).append(this.kfId, data.kfId).append(this.mchtId, data.mchtId).isEquals
//  }

  override def hashCode: Int = {
    new HashCodeBuilder(-528253723, -475504089).appendSuper(super.hashCode).append(this.kfId).append(this.mchtId).toHashCode
  }
}

