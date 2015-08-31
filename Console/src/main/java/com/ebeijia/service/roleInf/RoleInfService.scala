package com.ebeijia.service.roleInf

import java.util.Map

import com.ebeijia.entity.TblRoleInf

/**
 * RoleInfService
 * @author zhicheng.xu
 *         15/8/13
 */

trait RoleInfService {
  def save(name: String, dsc: String, roleList: String)

  def funcFind(usrId: String): String

  def findRoleDef(roleId: String): Map[String, AnyRef]

  def funcFindAll: Map[String, AnyRef]

  def findBySql(roleId: String, roleName: String, pageData: String): Map[String, AnyRef]

  def getById(id: String): TblRoleInf

  def delById(id: String)

  def update(tblRoleInf: TblRoleInf, roleList: String)

  def findRole: Map[String, AnyRef]
}
