package com.ebeijia.dao.wechat

import java.util.{ArrayList, List, Map}

import com.ebeijia.dao.base.{BaseDao, BaseDaoImplHibernate}
import com.ebeijia.entity.page.{Page, PageEntity}
import com.ebeijia.entity.wechat.TblWechatKf
import org.springframework.stereotype.Repository

/**
 * WechatKfDao
 * @author zhicheng.xu
 *         15/8/18
 */

@Repository("WechatKfDao")
class WechatKfDao extends BaseDaoImplHibernate[TblWechatKf] with BaseDao[TblWechatKf] {
  def getWechatRespMsgList: List[TblWechatKf] = {
    var wechatRespMsgList: List[TblWechatKf] = new ArrayList[TblWechatKf]
    val hql: String = "FROM TblWechatKf "
    wechatRespMsgList = getHibernateTemplate.find(hql).asInstanceOf
    wechatRespMsgList
  }

  /**
   * 获取实体信息的总数
   * @return Integer 当前实体类在数据库的信息总数
   */
  def countTotalNum(wechatRespMsg: TblWechatKf): Integer = {
    val hql: String = "SELECT COUNT(*) FROM TblWechatKf"
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
