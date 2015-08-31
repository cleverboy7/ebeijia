package com.ebeijia.entity

import java.io.Serializable
import javax.persistence._

/**
 * TblRoleInf
 * @author zhicheng.xu
 *         15/8/11
 */


@Entity
@Table(name = "TBL_ROLE_INF")
@SerialVersionUID(1L)
class TblRoleInf extends Serializable {
  private var roleId: String = null
  private var dsc: String = null
  private var roleName: String = null

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ROLE_ID", unique = true, nullable = false, length = 6) def getRoleId: String = {
    roleId
  }

  def setRoleId(roleId: String) {
    this.roleId = roleId
  }

  @Column(name = "DSC", length = 64) def getDsc: String = {
    dsc
  }

  def setDsc(dsc: String) {
    this.dsc = dsc
  }

  @Column(name = "ROLE_NAME", length = 32) def getRoleName: String = {
    roleName
  }

  def setRoleName(roleName: String) {
    this.roleName = roleName
  }
}
