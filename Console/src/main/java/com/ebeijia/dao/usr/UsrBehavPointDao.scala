package com.ebeijia.dao.usr
import java.util.{ArrayList, List}
import com.ebeijia.dao.base.{BaseDao, BaseDaoImplHibernate}
import com.ebeijia.entity.TblUsrBehavPoint
import org.springframework.stereotype.Repository
/**
 * UsrBehavPointDao
 * @author zhicheng.xu
 *         15/8/13
 */

@Repository("UsrBehavPointDao") class UsrBehavPointDao extends BaseDaoImplHibernate[TblUsrBehavPoint] with BaseDao[TblUsrBehavPoint] {
  def getUsrBehavPointList: List[_] = {
    var usrBehavPointList: List[_] = new ArrayList[TblUsrBehavPoint]
    val hql: String = "FROM TblUsrBehavPoint "
    usrBehavPointList = getHibernateTemplate.find(hql)
    usrBehavPointList
  }

  /**
   * 获取实体信息的总数
   * @return Integer 当前实体类在数据库的信息总数
   */
  def countTotalNum(usrBehavPoint: TblUsrBehavPoint): Integer = {
    val hql: String = "SELECT COUNT(*) FROM TblUsrBehavPoint"
    (this.getHibernateTemplate.find(hql).get(0).asInstanceOf[Long]).intValue
  }

  /**
   * 分页方法
   * @param page:当前第几页
   * @param size:当前页面每页显示信息的个数
   * @return List<T> 集合
   */
  def findByPage(usrBehavPoint: TblUsrBehavPoint, page: Int, size: Int): List[TblUsrBehavPoint] = {
    val hql: String = "FROM TblUsrBehavPoint"
    super.findByPage(hql, page, size)
  }
}
