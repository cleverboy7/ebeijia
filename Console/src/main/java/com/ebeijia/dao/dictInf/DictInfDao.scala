package com.ebeijia.dao.dictInf

import java.util.{ArrayList, List}
import com.ebeijia.dao.base.{BaseDao, BaseDaoImplHibernate}
import com.ebeijia.entity.TblDictInf
import org.springframework.stereotype.Repository

/**
 * DictInfDao
 * @author zhicheng.xu
 *         15/8/11
 */


@Repository("DictInfDao") class DictInfDao extends BaseDaoImplHibernate[TblDictInf] with BaseDao[TblDictInf] {
  def getDictInfList: List[_] = {
    var dictInfList: List[_] = new ArrayList[TblDictInf]
    val hql: String = "FROM TblDictInf "
    dictInfList = getHibernateTemplate.find(hql)
    dictInfList
  }

  /**
   * 获取实体信息的总数
   * @return Integer 当前实体类在数据库的信息总数
   */
  def countTotalNum(dictInf: TblDictInf): Integer = {
    val hql: String = "SELECT COUNT(*) FROM TblDictInf"
    (this.getHibernateTemplate.find(hql).get(0).asInstanceOf[Long]).intValue
  }
}
