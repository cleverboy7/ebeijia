package com.ebeijia.dao.adminInf

import java.util.{ArrayList,List,Map}
import org.springframework.stereotype.Repository
import com.ebeijia.dao.base.BaseDaoImplHibernate
import com.ebeijia.entity.TblAdminInf
import com.ebeijia.entity.page.{Page,PageEntity}

/**
 * AdminDao
 * @author zhicheng.xu
 *         15/8/4
 */
@Repository("AdminDao")
class AdminInfDao extends BaseDaoImplHibernate[TblAdminInf]  {
  def getAdminInfList: List[_] = {
    var adminInfList: List[_] = new ArrayList[TblAdminInf]
    val hql: String = "FROM TblAdminInf "
    adminInfList = getHibernateTemplate.find(hql)
    adminInfList
  }

  /**
   * 获取实体信息的总数
   * @return Integer 当前实体类在数据库的信息总数
   */
  def countTotalNum(adminInf: TblAdminInf): Integer = {
    val hql: String = "SELECT COUNT(*) FROM TblAdminInf"
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
