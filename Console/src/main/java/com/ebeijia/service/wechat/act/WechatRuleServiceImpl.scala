package com.ebeijia.service.wechat.act

import java.util.Map
import com.ebeijia.dao.wechat.WechatRuleDao
import com.ebeijia.entity.wechat.{TblWechatRule, TblWechatRuleId}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.{CacheEvict, Cacheable}
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * WechatRuleServiceImpl
 * @author zhicheng.xu
 *         15/8/13
 */

@Service final class WechatRuleServiceImpl extends WechatRuleService {
  @Autowired private val wechatRuleDao: WechatRuleDao = null

  @Transactional
  @CacheEvict(value = Array("wechatRuleCache"), allEntries = true)
  def save(data: TblWechatRule, modId: String): String = {
    val id: TblWechatRuleId = new TblWechatRuleId
    id.setModId(modId)
    data.setId(id)
    wechatRuleDao.save(data)
    null
  }

  @Transactional
  @CacheEvict(value = Array("wechatRuleCache"), allEntries = true)
  def upd(data: TblWechatRule): String = {
    wechatRuleDao.update(data)
    null
  }

  @Transactional
  @Cacheable(value = Array("wechatRuleCache"), key = "#root.method.name+#mchtId+#pageDate")
  def findBySql(mchtId: String, pageData: String): Map[String, AnyRef] = {
    val query: StringBuilder = new StringBuilder
    query.append(" from TblWechatRule ")
    query.append(" where 1=1")
    if (mchtId != null && !("" == mchtId)) {
      query.append(" AND mchtId like '%").append(mchtId).append("%'")
    }
    query.append(" order by createTime")
    wechatRuleDao.findByPage(query.toString, pageData)
  }

  @Transactional
  @CacheEvict(value = Array("wechatRuleCache"), allEntries = true)
  def del(id: String): String = {
    wechatRuleDao.deleteById(id)
    null
  }

  @Transactional
  @Cacheable(value = Array("wechatRuleCache"), key = "#root.method.name+#id")
  def getById(id: String): TblWechatRule = {
    wechatRuleDao.getById(id)
  }

}