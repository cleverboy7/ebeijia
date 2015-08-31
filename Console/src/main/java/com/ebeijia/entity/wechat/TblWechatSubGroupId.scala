package com.ebeijia.entity.wechat

/**
 * TblWechatSubGroupId
 * @author zhicheng.xu
 *         15/8/12
 */

import javax.persistence.Column
import javax.persistence.Embeddable
import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

@Embeddable
@SerialVersionUID(1L)
class TblWechatSubGroupId extends java.io.Serializable {
  private var groupId: String = null
  private var mchtId: String = null



  @Column(name = "GROUP_ID", unique = true, nullable = false, length = 6) def getGroupId: String = {
    groupId
  }

  def setGroupId(groupId: String) {
    this.groupId = groupId
  }

  @Column(name = "MCHT_ID", length = 15) def getMchtId: String = {
    mchtId
  }

  def setMchtId(mchtId: String) {
    this.mchtId = mchtId
  }

//  override def equals(obj: AnyRef): Boolean = {
//    if (!(obj.isInstanceOf[TblWechatSubGroupId])) {
//      false
//    }
//    val data: TblWechatSubGroupId = obj.asInstanceOf[TblWechatSubGroupId]
//    new EqualsBuilder().appendSuper(super == obj).append(this.groupId, data.groupId).append(this.mchtId, data.mchtId).isEquals
//  }

  override def hashCode: Int = {
    new HashCodeBuilder(-528253723, -475504089).appendSuper(super.hashCode).append(this.groupId).append(this.mchtId).toHashCode
  }
}

