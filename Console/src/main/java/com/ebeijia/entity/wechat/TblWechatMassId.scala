package com.ebeijia.entity.wechat

import javax.persistence.{Column, Embeddable}

import org.apache.commons.lang.builder.{EqualsBuilder, HashCodeBuilder}

/**
 * TblWechatMassId
 * @author zhicheng.xu
 *         15/8/12
 */


@Embeddable
@SerialVersionUID(1L)
class TblWechatMassId extends java.io.Serializable {
  private var msgId: String = null
  private var mchtId: String = null



  @Column(name = "MSG_ID", unique = true, nullable = false, length = 10) def getMsgId: String = {
    msgId
  }

  def setMsgId(msgId: String) {
    this.msgId = msgId
  }

  @Column(name = "MCHT_ID", length = 15) def getMchtId: String = {
    mchtId
  }

  def setMchtId(mchtId: String) {
    this.mchtId = mchtId
  }

//  override def equals(obj: AnyRef): Boolean = {
//    if (!(obj.isInstanceOf[TblWechatMassId])) {
//      false
//    }
//    val data: TblWechatMassId = obj.asInstanceOf[TblWechatMassId]
//    new EqualsBuilder().appendSuper(super == obj).append(this.msgId, data.msgId).append(this.mchtId, data.mchtId).isEquals
//  }

  override def hashCode: Int = {
    new HashCodeBuilder(-528253723, -475504089).appendSuper(super.hashCode).append(this.msgId).append(this.mchtId).toHashCode
  }
}

