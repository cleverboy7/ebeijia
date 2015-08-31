package com.ebeijia.dao.wechat

import java.util.List

import com.ebeijia.dao.base.{BaseDao, BaseDaoImplHibernate}
import com.ebeijia.entity.wechat.TblWechatGroup
import org.springframework.stereotype.Repository

/**
 * WechatGroupDao
 * @author zhicheng.xu
 *         15/8/17
 */
@Repository("WechatGroupDao")
class WechatGroupDao extends BaseDaoImplHibernate[TblWechatGroup] with BaseDao[TblWechatGroup] {
  def getWechatConfigList: List[TblWechatGroup] = {
    val hql: String = "FROM TblWechatGroup "
    getHibernateTemplate.find(hql).asInstanceOf
  }

  /**
   * 获取实体信息的总数
   * @return Integer 当前实体类在数据库的信息总数
   */
  def countTotalNum(wechatConfig: TblWechatGroup): Integer = {
    val hql: String = "SELECT COUNT(*) FROM TblWechatGroup"
    (this.getHibernateTemplate.find(hql).get(0).asInstanceOf[Long]).intValue
  }

  /**
   * 分页方法
   * @param page:当前第几页
   * @param size:当前页面每页显示信息的个数
   * @return List<T> 集合
   */
  def findByPage(wechatConfig: TblWechatGroup, page: Int, size: Int): List[TblWechatGroup] = {
    val hql: String = "FROM TblWechatGroup"
    super.findByPage(hql, page, size)
  }
}