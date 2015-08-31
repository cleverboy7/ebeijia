package com.ebeijia.dao.usr

import java.util.{ArrayList, List}
import com.ebeijia.dao.base.{BaseDao, BaseDaoImplHibernate}
import com.ebeijia.entity.TblUsrInf
import org.springframework.stereotype.Repository

/**
 * UsrInfDao
 * @author zhicheng.xu
 *         15/8/13
 */


@Repository("UsrInfDao") class UsrInfDao extends BaseDaoImplHibernate[TblUsrInf] with BaseDao[TblUsrInf] {
  def getUsrInfList: List[_] = {
    var usrInfList: List[_] = new ArrayList[TblUsrInf]
    val hql: String = "FROM TblUsrInf "
    usrInfList = getHibernateTemplate.find(hql)
    usrInfList
  }

  /**
   * 获取实体信息的总数
   * @return Integer 当前实体类在数据库的信息总数
   */
  def countTotalNum(usrInf: TblUsrInf): Integer = {
    val hql: String = "SELECT COUNT(*) FROM TblUsrInf"
    (this.getHibernateTemplate.find(hql).get(0).asInstanceOf[Long]).intValue
  }

  /**
   * 分页方法
   * @param page:当前第几页
   * @param size:当前页面每页显示信息的个数
   * @return List<T> 集合
   */
  def findByPage(usrInf: TblUsrInf, page: Int, size: Int): List[TblUsrInf] = {
    val hql: String = "FROM TblUsrInf"
    super.findByPage(hql, page, size)
  }
}
