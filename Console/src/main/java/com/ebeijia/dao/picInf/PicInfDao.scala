package com.ebeijia.dao.picInf

import java.util.{ArrayList, List}
import com.ebeijia.dao.base.{BaseDao, BaseDaoImplHibernate}
import com.ebeijia.entity.TblPicInf
import org.springframework.stereotype.Repository

/**
 * PicInfDao
 * @author zhicheng.xu
 *         15/8/13
 */

@Repository("PicInfDao") class PicInfDao extends BaseDaoImplHibernate[TblPicInf] with BaseDao[TblPicInf] {
  def getPicInfList: List[_] = {
    var picInfList: List[_] = new ArrayList[TblPicInf]
    val hql: String = "FROM TblPicInf "
    picInfList = getHibernateTemplate.find(hql)
    picInfList
  }

  /**
   * 获取实体信息的总数
   * @return Integer 当前实体类在数据库的信息总数
   */
  def countTotalNum(picInf: TblPicInf): Integer = {
    val hql: String = "SELECT COUNT(*) FROM TblPicInf"
    (this.getHibernateTemplate.find(hql).get(0).asInstanceOf[Long]).intValue
  }

  /**
   * 分页方法
   * @param page:当前第几页
   * @param size:当前页面每页显示信息的个数
   * @return List<T> 集合
   */
  def findByPage(picInf: TblPicInf, page: Int, size: Int): List[TblPicInf] = {
    val hql: String = "FROM TblPicInf"
    super.findByPage(hql, page, size)
  }
}
