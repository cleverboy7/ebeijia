package com.ebeijia.dao.wechat

/**
 * WechatConfigDao
 * @author zhicheng.xu
 *         15/8/17
 */

import java.util.ArrayList
import java.util.List
import org.springframework.stereotype.Repository
import com.ebeijia.dao.base.BaseDao
import com.ebeijia.dao.base.BaseDaoImplHibernate
import com.ebeijia.entity.wechat.TblWechatConfig

@Repository("WechatConfigDao")
class WechatConfigDao extends BaseDaoImplHibernate[TblWechatConfig] with BaseDao[TblWechatConfig] {
  def getWechatConfigList: List[TblWechatConfig] = {
    var wechatConfigList: List[TblWechatConfig] = new ArrayList[TblWechatConfig]
    val hql: String = "FROM TblWechatConfig "
    getHibernateTemplate.find(hql).asInstanceOf
  }

  /**
   * 获取实体信息的总数
   * @return Integer 当前实体类在数据库的信息总数
   */
  def countTotalNum(wechatConfig: TblWechatConfig): Integer = {
    val hql: String = "SELECT COUNT(*) FROM TblWechatConfig"
    (this.getHibernateTemplate.find(hql).get(0).asInstanceOf[Long]).intValue
  }

  /**
   * 分页方法
   * @param page:当前第几页
   * @param size:当前页面每页显示信息的个数
   * @return List<T> 集合
   */
  def findByPage(wechatConfig: TblWechatConfig, page: Int, size: Int): List[TblWechatConfig] = {
    val hql: String = "FROM TblWechatConfig"
    super.findByPage(hql, page, size)
  }
}
