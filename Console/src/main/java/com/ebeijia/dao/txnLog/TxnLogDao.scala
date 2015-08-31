package com.ebeijia.dao.txnLog

/**
 * TxnLogDao
 * @author zhicheng.xu
 *         15/8/13
 */

import java.util.{ArrayList, List, Map}
import com.ebeijia.dao.base.{BaseDao, BaseDaoImplHibernate}
import com.ebeijia.entity.TblTxnLog
import com.ebeijia.entity.page.{Page, PageEntity}
import org.springframework.stereotype.Repository

@Repository("TxnLogDao") class TxnLogDao extends BaseDaoImplHibernate[TblTxnLog] with BaseDao[TblTxnLog] {
  def getTxnLogList: List[_] = {
    var txnLogList: List[_] = new ArrayList[TblTxnLog]
    val hql: String = "FROM TblTxnLog "
    txnLogList = getHibernateTemplate.find(hql)
    txnLogList.asInstanceOf
  }

  /**
   * 获取实体信息的总数
   * @return Integer 当前实体类在数据库的信息总数
   */
  def countTotalNum(txnLog: TblTxnLog): Integer = {
    val hql: String = "SELECT COUNT(*) FROM TblTxnLog"
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
    this.findByPageAndTotal(query.toString, page.getiDisplayStart, page.getiDisplayLength)
  }
}
