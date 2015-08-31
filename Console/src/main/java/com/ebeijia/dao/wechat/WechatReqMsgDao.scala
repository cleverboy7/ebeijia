package com.ebeijia.dao.wechat
import java.util.{List, Map}

import com.ebeijia.dao.base.{BaseDao, BaseDaoImplHibernate}
import com.ebeijia.entity.page.{Page, PageEntity}
import com.ebeijia.entity.wechat.TblWechatReqMsg
import org.springframework.stereotype.Repository
/**
 * WechatReqMsgDao
 * @author zhicheng.xu
 *         15/8/18
 */



@Repository("WechatReqMsgDao")
class WechatReqMsgDao extends BaseDaoImplHibernate[TblWechatReqMsg] with BaseDao[TblWechatReqMsg] {
  def getWechatReqMsgList: List[TblWechatReqMsg] = {
    val hql: String = "FROM TblWechatReqMsg "
    getHibernateTemplate.find(hql).asInstanceOf
  }

  /**
   * 获取实体信息的总数
   * @return Integer 当前实体类在数据库的信息总数
   */
  def countTotalNum(wechatReqMsg: TblWechatReqMsg): Integer = {
    val hql: String = "SELECT COUNT(*) FROM TblWechatReqMsg"
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
