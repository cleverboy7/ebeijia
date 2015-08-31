package com.ebeijia.entity

/**
 * TblRoleMapId
 * @author zhicheng.xu
 *         15/8/11
 */

import java.io.Serializable
import javax.persistence.{Column, Embeddable}

import org.apache.commons.lang.builder.{EqualsBuilder, HashCodeBuilder}

@Embeddable
@SerialVersionUID(1L)
class TblRoleMapId extends Serializable {
  private var roleId: String = null
  private var funcId: String = null

  @Column(name = "ROLE_ID", nullable = false, length = 6) def getRoleId: String = {
    roleId
  }

  def setRoleId(roleId: String) {
    this.roleId = roleId
  }

  @Column(name = "FUNC_ID", nullable = false, length = 6) def getFuncId: String = {
    funcId
  }

  def setFuncId(funcId: String) {
    this.funcId = funcId
  }

//   override def equals(obj: AnyRef): Boolean = {
//    if (!(obj.isInstanceOf[TblRoleMapId])) {
//      return false
//    }
//    val data: TblRoleMapId = obj.asInstanceOf[TblRoleMapId]
//    new EqualsBuilder().appendSuper(this == obj).append(this.roleId, data.roleId).append(this.funcId, data.funcId).isEquals
//  }
//
//  override def hashCode: Int = {
//    new HashCodeBuilder(-528253723, -475504089).appendSuper(super.hashCode).append(this.roleId).append(this.funcId).toHashCode
//  }
}

