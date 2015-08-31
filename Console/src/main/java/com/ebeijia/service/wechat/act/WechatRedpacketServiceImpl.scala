package com.ebeijia.service.wechat.act

import java.util.Map

import com.ebeijia.dao.wechat.WechatRedpacketDao
import com.ebeijia.entity.wechat.{TblWechatRedpacket, TblWechatRedpacketId}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.{CacheEvict, Cacheable}
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * WechatRedpacketServiceImpl
 * @author zhicheng.xu
 *         15/8/13
 */


@Service
final class WechatRedpacketServiceImpl extends WechatRedpacketService {

  @Autowired
  private val wechatRedpacketDao: WechatRedpacketDao = null

  @Transactional
  @CacheEvict(value = Array("wechatRedpacketCache"), allEntries = true)
  def save(data: TblWechatRedpacket): String = {
    wechatRedpacketDao.save(data)
    null
  }

  @Transactional
  @CacheEvict(value = Array("wechatRedpacketCache"), allEntries = true)
  def upd(data: TblWechatRedpacket): String = {
    wechatRedpacketDao.update(data)
    null
  }

  @Transactional
  @Cacheable(value = Array("wechatRedpacketCache"), key = "#root.method.name+#mchtId+#pageDate")
  def findBySql(mchtId: String, pageData: String): Map[String, AnyRef] = {
    val query: StringBuilder = new StringBuilder
    query.append(" from TblWechatRedpacket ").append(" where 1=1")
    if (mchtId != null && !("" == mchtId)) {
      query.append(" AND mchtId like '%").append(mchtId).append("%'")
    }
    query.append(" order by createTime")
    wechatRedpacketDao.findByPage(query.toString(), pageData)
  }

  @CacheEvict(value = Array("wechatRedpacketCache"), allEntries = true)
  def del(id: TblWechatRedpacketId): String = {
    wechatRedpacketDao.deleteById(id)
    null
  }

  @Cacheable(value = Array("wechatRedpacketCache"), key = "#root.method.name+#id")
  def getById(id: TblWechatRedpacketId): TblWechatRedpacket = {
    wechatRedpacketDao.getById(id)
  }
}