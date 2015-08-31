package com.ebeijia.entity.wechat

import javax.persistence.{Column, Embeddable}

import org.apache.commons.lang.builder.{EqualsBuilder, HashCodeBuilder}

/**
 * TblWechatRedpacketId
 * @author zhicheng.xu
 *         15/8/12
 */


@Embeddable
@SerialVersionUID(1L)
class TblWechatRedpacketId extends java.io.Serializable {
  private var nonceStr: String = null
  private var mchtId: String = null



  @Column(name = "NONCE_STR", unique = true, nullable = false, length = 32) def getNonceStr: String = {
    nonceStr
  }

  def setNonceStr(nonceStr: String) {
    this.nonceStr = nonceStr
  }

  @Column(name = "MCHT_ID", length = 15) def getMchtId: String = {
    mchtId
  }

  def setMchtId(mchtId: String) {
    this.mchtId = mchtId
  }

//  override def equals(obj: AnyRef): Boolean = {
//    if (!(obj.isInstanceOf[TblWechatRedpacketId])) {
//      false
//    }
//    val data: TblWechatRedpacketId = obj.asInstanceOf[TblWechatRedpacketId]
//    new EqualsBuilder().appendSuper(super == obj).append(this.nonceStr, data.nonceStr).append(this.mchtId, data.mchtId).isEquals
//  }

  override def hashCode: Int = {
    new HashCodeBuilder(-528253723, -475504089).appendSuper(super.hashCode).append(this.nonceStr).append(this.mchtId).toHashCode
  }
}

