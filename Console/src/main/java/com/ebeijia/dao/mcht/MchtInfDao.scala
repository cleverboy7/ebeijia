package com.ebeijia.dao.mcht

import java.util.{ArrayList, List, Map}

import com.ebeijia.dao.base.{BaseDao, BaseDaoImplHibernate}
import com.ebeijia.entity.TblMchtInf
import com.ebeijia.entity.page.{Page, PageEntity}
import org.hibernate.{Query, Session}
import org.springframework.stereotype.Repository

/**
 * MchtInfDao
 * @author zhicheng.xu
 *         15/8/17
 */
@Repository("MchtInfDao") class MchtInfDao extends BaseDaoImplHibernate[TblMchtInf] with BaseDao[TblMchtInf] {
  @SuppressWarnings(Array("unchecked")) def getMchtInfList: List[TblMchtInf] = {
    var mchtInfList: List[TblMchtInf] = new ArrayList[TblMchtInf]
    val hql: String = "FROM TblMchtInf "
    mchtInfList = getHibernateTemplate.find(hql).asInstanceOf
    mchtInfList
  }

  /**
   * 获取实体信息的总数
   * @return Integer 当前实体类在数据库的信息总数
   */
  def countTotalNum(mchtInf: TblMchtInf): Integer = {
    val hql: String = "SELECT COUNT(*) FROM TblMchtInf"
    (this.getHibernateTemplate.find(hql).get(0).asInstanceOf[Long]).intValue
  }

  /**
   * 分页方法
   * @param page:当前第几页
   * @param size:当前页面每页显示信息的个数
   * @return List<T> 集合
   */
  def findByPage(mchtInf: TblMchtInf, page: Int, size: Int): List[TblMchtInf] = {
    val hql: String = "FROM TblMchtInf"
    super.findByPage(hql, page, size)
  }

  /**
   * 分页方法(商户平台ID)
   * @param mchtInf
   * @param page 当前第几页
   * @param size 当前页面每页显示信息的个数
   * @param mchtPlatId 商户平台ID
   * @return
   */
  def findByPage(mchtPlatId: String, mchtInf: TblMchtInf, page: Int, size: Int): List[TblMchtInf] = {
    val hql: String = "FROM TblMchtInf WHERE mchtPlatId ='" + mchtPlatId + "' AND mchtType != '99' ORDER BY mchtId"
    super.findByPage(hql, page, size)
  }

  /**
   * 获取商户ID(根据商户平台ID和request商户ID)
   *
   * @param mchtPlatId 商户平台ID
   * @param merchantId request商户ID
   * @return String 商户ID
   */
  @SuppressWarnings(Array("unchecked"))
  def findMchtIdByMerchant(mchtPlatId: String, merchantId: String): String = {
    var mchtId: String = ""
    val hql: String = "select mcht_id,mcht_stat FROM Tbl_Mcht_Inf WHERE TRIM(mcht_Plat_Id) = '" + mchtPlatId + "' AND TRIM(ptr_Mcht_Id) = '" + merchantId + "' "
    val tblMchtInfList: List[Array[AnyRef]] = super.getListSQL(hql).asInstanceOf
    if (!tblMchtInfList.isEmpty) {
      if ("1" == tblMchtInfList.get(0)(1).toString) {
        mchtId = tblMchtInfList.get(0)(0).toString
      }
    }
    mchtId
  }

  /**
   * 分页获取商户列表
   *
   * @param hql
   * @return
   */
  @SuppressWarnings(Array("unchecked"))
  def findObject(hql: String, page: Int, size: Int): List[Array[AnyRef]] = {
    val session: Session = getHibernateTemplate.getSessionFactory.openSession
    val q: Query = session.createQuery(hql)
    q.setFirstResult((page - 1) * size)
    q.setMaxResults(size)
    q.list.asInstanceOf
  }

  /**
   * 获得查询的所有数据  返回的是数组
   * @param hql
   * @return
   */
  @SuppressWarnings(Array("unchecked"))
  def findObject(hql: String): List[Array[AnyRef]] = {
    val session: Session = getHibernateTemplate.getSessionFactory.openSession
    val q: Query = session.createQuery(hql)
    q.list.asInstanceOf
  }

  /**
   * 获得查询的所有数据  返回的是非数组
   * @param hql
   * @return
   */
  @SuppressWarnings(Array("unchecked")) def findObject2(hql: String): List[AnyRef] = {
    val session: Session = getHibernateTemplate.getSessionFactory.openSession
    session.createQuery(hql).asInstanceOf
  }

  @SuppressWarnings(Array("unchecked")) def findAllObject(hql: String): List[Array[AnyRef]] = {
    getHibernateTemplate.find(hql).asInstanceOf
  }

  def findMchtList: List[Array[AnyRef]] = {
    val sb: StringBuilder = new StringBuilder
    sb.append("select MCHT_ID,MCHT_NAME ")
    sb.append("from Tbl_MCHT_INF ")
    sb.append(" where MCHT_ID not in(select MCHT_ID from TBL_WECHAT_MCHT_INF) ")
    sb.append("order by MCHT_LVL,MCHT_ID")
    super.getListSQL(sb.toString).asInstanceOf
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