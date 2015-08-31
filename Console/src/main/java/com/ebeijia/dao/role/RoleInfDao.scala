package com.ebeijia.dao.role

import java.util.{ArrayList, List, Map}
import com.ebeijia.dao.base.{BaseDao, BaseDaoImplHibernate}
import com.ebeijia.entity.TblRoleInf
import com.ebeijia.entity.page.{Page, PageEntity}
import org.springframework.stereotype.Repository

/**
 * RoleInfDao
 * @author zhicheng.xu
 *         15/8/13
 */

@Repository("RoleDao") class RoleInfDao extends BaseDaoImplHibernate[TblRoleInf] with BaseDao[TblRoleInf] {
  def getTxnLogList: List[_] = {
    var txnLogList: List[_] = new ArrayList[TblRoleInf]
    val hql: String = "FROM TblRoleInf "
    txnLogList = getHibernateTemplate.find(hql)
    txnLogList
  }

  /**
   * 获取实体信息的总数
   * @return Integer 当前实体类在数据库的信息总数
   */
  def countTotalNum(txnLog: TblRoleInf): Integer = {
    val hql: String = "SELECT COUNT(*) FROM TblRoleInf"
    (this.getHibernateTemplate.find(hql).get(0).asInstanceOf[Long]).intValue
  }

  /**
   * 分页方法
   * @param query:sql语句
   * @param aoData:分页对象
   * @return Map<String,Object>
   */
  def findByPage(query: String, aoData: String): Map[String, AnyRef] = {
    var page: PageEntity = new PageEntity
    page = Page.init(aoData)
    val m: Map[String, AnyRef] = this.findByPageAndTotal(query.toString, page.getiDisplayStart, page.getiDisplayLength)
    m
  }
}
