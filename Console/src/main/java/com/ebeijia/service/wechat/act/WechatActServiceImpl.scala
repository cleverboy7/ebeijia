package com.ebeijia.service.wechat.act

import java.util.{List, Map}

import com.ebeijia.dao.wechat.WechatActDao
import com.ebeijia.entity.wechat.TblWechatAct
import org.ebeijia.tools.DateTime4J
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.{CacheEvict, Cacheable}
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
final class WechatActServiceImpl extends WechatActService {
  @Autowired
  private val wechatActDao: WechatActDao = null

  @Transactional
  @CacheEvict(value = Array("wechatActCache"), allEntries = true)
  def save(data: TblWechatAct): String = {
    wechatActDao.save(data)
    null
  }

  @Transactional
  @CacheEvict(value = Array("wechatActCache"), allEntries = true)
  def upd(data: TblWechatAct): String = {
    wechatActDao.update(data)
    null
  }

  @Transactional
  @Cacheable(value = Array("wechatActCache"))
  def findBySql(mchtId: String, pageData: String): Map[String, AnyRef] = {
    val query: StringBuilder = new StringBuilder
    query.append(" from TblWechatAct ")
    query.append(" where 1=1")
    if (mchtId != null && !("" == mchtId)) {
      query.append(" AND mchtId like '%").append(mchtId).append("%'")
    }
    query.append(" order by beginDate")
    val m: Map[String, AnyRef] = wechatActDao.findByPage(query.toString, pageData)
    m
  }

  @Transactional
  @CacheEvict(value = Array("wechatActCache"), allEntries = true)
  def del(id: String): String = {
    wechatActDao.deleteById(id)
    null
  }

  @Transactional
  @Cacheable(value = Array("wechatActCache"), key = "#root.method.name+#id")
  def getById(id: String): TblWechatAct = {
    wechatActDao.getById(id)
  }

  @Transactional
  @Cacheable(value = Array("wechatActCache"), key = "#root.method.name+#id")
  def getAct(id: String): TblWechatAct = {
    val sb: StringBuilder = new StringBuilder
    val date: String = DateTime4J.getCurrentDate
    sb.append("from TblWechatAct where id = '").append(id).append("'")
    sb.append(" and type = '1' and begin_date <='").append(date)
    sb.append("' and end_Date >= '").append(date).append("'")
    val list: List[TblWechatAct] = wechatActDao.find(sb.toString)
    if (list.isEmpty) {
      null
    }
    else {
      list.get(0)
    }
  }

}