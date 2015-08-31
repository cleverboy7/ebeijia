package com.ebeijia.dao.role
import java.util.ArrayList
import java.util.List
import java.util.Map
import org.springframework.stereotype.Repository
import com.ebeijia.dao.base.BaseDao
import com.ebeijia.dao.base.BaseDaoImplHibernate
import com.ebeijia.entity.TblRoleMap
import com.ebeijia.entity.page.Page
import com.ebeijia.entity.page.PageEntity
/**
 * RoleMapDao
 * @author zhicheng.xu
 *         15/8/13
 */



@Repository("RoleMapDao") class RoleMapDao extends BaseDaoImplHibernate[TblRoleMap] with BaseDao[TblRoleMap] {
  def getTxnLogList: List[_] = {
    var txnLogList: List[_] = new ArrayList[TblRoleMap]
    val hql: String = "FROM TblRoleMap "
    txnLogList = getHibernateTemplate.find(hql)
    txnLogList
  }

  /**
   * 获取实体信息的总数
   * @return Integer 当前实体类在数据库的信息总数
   */
  def countTotalNum(txnLog: TblRoleMap): Integer = {
    val hql: String = "SELECT COUNT(*) FROM TblRoleMap"
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
