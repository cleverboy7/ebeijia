package com.ebeijia.dao.wechat

/**
 * ArticlesDao
 * @author zhicheng.xu
 *         15/8/17
 */

import java.util.{List, Map}

import com.ebeijia.dao.base.{BaseDao, BaseDaoImplHibernate}
import com.ebeijia.entity.page.{Page, PageEntity}
import com.ebeijia.entity.wechat.TblArticlesInf
import org.springframework.stereotype.Repository

@Repository("ArticlesDao")
class ArticlesDao extends BaseDaoImplHibernate[TblArticlesInf] with BaseDao[TblArticlesInf] {
  def getWechatRespMsgList: List[TblArticlesInf] = {
    val hql: String = "FROM TblArticlesInf "
    getHibernateTemplate.find(hql).asInstanceOf
  }

  /**
   * 获取实体信息的总数
   * @return Integer 当前实体类在数据库的信息总数
   */
  def countTotalNum(wechatRespMsg: TblArticlesInf): Integer = {
    val hql: String = "SELECT COUNT(*) FROM TblArticlesInf"
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
