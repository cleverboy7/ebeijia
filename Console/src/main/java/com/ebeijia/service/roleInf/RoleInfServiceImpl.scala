package com.ebeijia.service.roleInf

import java.util.{HashMap, Iterator, LinkedHashMap, LinkedList, List, Map}

import com.ebeijia.dao.role.{RoleInfDao, RoleMapDao}
import com.ebeijia.entity.{TblRoleInf, TblRoleMap, TblRoleMapId}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.{CacheEvict, Cacheable}
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * RoleInfServiceImpl
 * @author zhicheng.xu
 *         15/8/13
 */


@Service final class RoleInfServiceImpl extends RoleInfService {
  @Autowired
  private val roleInfDao: RoleInfDao = null
  @Autowired
  private val roleMapDao: RoleMapDao = null

  @Transactional
  @Cacheable(value = Array("roleCache"), key = "#root.method.name+#usrId")
  def funcFind(usrId: String): String = {
    val sb: StringBuilder = new StringBuilder
    sb.append("select f.funcName from TblAdminInf a ,TblRoleMap r ,TblFuncInf f ")
    sb.append("where a.adminId = '").append(usrId).append("' and a.roleId =r.id.roleId")
    sb.append(" and r.id.funcId = f.funcId")
    val list: List[_] = roleInfDao.find(sb.toString)
    val result: StringBuilder = new StringBuilder
    if (list.isEmpty) {
      return null
    } else {
      for ( i <- 0 until list.size()) {
        result.append(list.get(i)).append(",")
      }
    }
    result.toString.substring(0, result.length - 1)
  }

  @Transactional
  @Cacheable(value = Array("roleCache"), key = "#root.method.name+#roleId")
  def findRoleDef(roleId: String): Map[String, AnyRef] = {
    val map: Map[String, AnyRef] = new HashMap[String, AnyRef]
    val exis: StringBuilder = new StringBuilder
    exis.append("select f.funcId,f.funcName from TblFuncInf f , TblRoleMap r ")
    exis.append("where f.funcId = r.id.funcId and r.id.roleId = '").append(roleId).append("' ")
    exis.append(" order by f.funcId")
    val list: List[_] = roleInfDao.find(exis.toString)
    map.put("exis", list)
    val noexis: StringBuilder = new StringBuilder
    noexis.append("select funcId,funcName from TblFuncInf where funcId not in(")
    noexis.append("select f.funcId from TblRoleMap r,TblFuncInf f ")
    noexis.append("where r.id.funcId = f.funcId and r.id.roleId='").append(roleId).append("') and parentId <>'-'")
    noexis.append(" order by funcId")
    val noexisList: List[_] = roleInfDao.find(noexis.toString)
    map.put("noexis", noexisList)
    map
  }

  @Transactional
  @Cacheable(value = Array("funcCache"), key = "#root.method.name")
  def funcFindAll: Map[String, AnyRef] = {
    val map: Map[String, AnyRef] = new LinkedHashMap[String, AnyRef]
    val sb: StringBuilder = new StringBuilder
    sb.append("select f.funcId,f.funcName  from TblFuncInf f where f.parentId <> '-'")
    sb.append(" order by f.funcId")
    val datas: List[_] = roleInfDao.find(sb.toString)
    val it: Iterator[_] = datas.iterator
    val list: List[AnyRef] = new LinkedList[AnyRef]
    while (it.hasNext) {
      val hashMap: Map[String, AnyRef] = new LinkedHashMap[String, AnyRef]
      val o: Array[AnyRef] = it.next.asInstanceOf[Array[AnyRef]]
      hashMap.put("key", o(0) + "-" + o(1))
      hashMap.put("value", o(0))
      list.add(hashMap)
    }
    map.put("info", list)
    map
  }

  @Transactional
  @CacheEvict(value = Array("roleCache"), allEntries = true)
  def save(name: String, dsc: String, roleList: String) {
    val data: TblRoleInf = new TblRoleInf
    data.setRoleName(name)
    data.setDsc(dsc)
    roleInfDao.save(data)
    val list: Array[String] = roleList.split(",")
    for (funcId <- list) {
      val roleMap: TblRoleMap = new TblRoleMap
      val id: TblRoleMapId = new TblRoleMapId
      id.setFuncId(funcId)
      id.setRoleId(data.getRoleId)
      roleMap.setId(id)
      roleMapDao.save(roleMap)
    }
  }

  @Transactional
  @Cacheable(value = Array("roleCache"))
  def findBySql(roleId: String, roleName: String, pageData: String): Map[String, AnyRef] = {
    val query: StringBuilder = new StringBuilder
    query.append(" from TblRoleInf where 1=1")
    if (roleId != null && !("" == roleId)) {
      query.append(" AND roleId = '").append(roleId).append("'")
    }
    if (roleName != null && !("" == roleName)) {
      query.append(" AND roleName like '").append(roleName).append("'")
    }
    val m: Map[String, AnyRef] = roleInfDao.findByPage(query.toString, pageData)
    m
  }

  @Transactional
  @Cacheable(value = Array("roleCache"), key = "#root.method.name")
  def findRole: Map[String, AnyRef] = {
    val map: Map[String, AnyRef] = new LinkedHashMap[String, AnyRef]
    val sb: StringBuilder = new StringBuilder
    sb.append("select roleId,roleName from TblRoleInf f")
    sb.append(" order by roleId")
    val datas: List[_] = roleInfDao.find(sb.toString)
    val it: Iterator[_] = datas.iterator
    val list: List[AnyRef] = new LinkedList[AnyRef]
    while (it.hasNext) {
      val hashMap: Map[String, AnyRef] = new LinkedHashMap[String, AnyRef]
      val o: Array[AnyRef] = it.next.asInstanceOf[Array[AnyRef]]
      hashMap.put("key", o(0) + "-" + o(1))
      hashMap.put("value", o(0))
      list.add(hashMap)
    }
    map.put("info", list)
    map
  }

  @Transactional
  @Cacheable(value = Array("roleCache"), key = "#root.method.name+#id")
  def getById(id: String): TblRoleInf = {
    roleInfDao.getById(id)
  }

  @Transactional
  @CacheEvict(value = Array("roleCache"), allEntries = true)
  def delById(id: String) {
    roleInfDao.deleteById(id)
    val sqlDelete: String = "delete from TblRoleMap where id.roleId = '" + id + "'"
    roleMapDao.updateAll(sqlDelete)
  }

  @Transactional
  @CacheEvict(value = Array("roleCache"), allEntries = true)
  def update(tblRoleInf: TblRoleInf, roleList: String) {
    roleInfDao.update(tblRoleInf)
    val sqlDelete: String = "delete from TblRoleMap where id.roleId = '" + tblRoleInf.getRoleId + "'"
    roleMapDao.updateAll(sqlDelete)
    val list: Array[String] = roleList.split(",")
    for (funcId <- list) {
      val roleMap: TblRoleMap = new TblRoleMap
      val id: TblRoleMapId = new TblRoleMapId
      id.setFuncId(funcId)
      id.setRoleId(tblRoleInf.getRoleId)
      roleMap.setId(id)
      roleMapDao.save(roleMap)
    }
  }

}
