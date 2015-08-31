package com.ebeijia.service.wechat.msg

import java.util.{List, Map}

import com.ebeijia.dao.wechat.WechatReqMsgDao
import com.ebeijia.entity.wechat.TblWechatReqMsg
import com.ebeijia.util.StringUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.{CacheEvict, Cacheable}
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * WechatReqMsgServiceImpl
 * @author zhicheng.xu
 *         15/8/14
 */

@Service
final class WechatReqMsgServiceImpl extends WechatReqMsgService {
  @Autowired
  private val wechatReqMsgDao: WechatReqMsgDao = null

  @Transactional
  @Cacheable(value = Array("wechatReqMsgCache"), key = "#root.method.name+#msgId")
  def findByMsgId(msgId: String): TblWechatReqMsg = {
    val sb: StringBuilder = new StringBuilder
    sb.append("from TblWechatReqMsg where msgId ='").append(msgId).append("'")
    val list: List[TblWechatReqMsg] = wechatReqMsgDao.find(sb.toString)
    val data: TblWechatReqMsg = new TblWechatReqMsg
    if (list.isEmpty) {
      data
    }
    else {
      list.get(0)
    }
  }

  @Transactional
  @Cacheable(value = Array("wechatReqMsgCache"))
  def findBySql(mchtId: String, pagaData: String): Map[String, AnyRef] = {
    val query: StringBuilder = new StringBuilder
    query.append("from TblWechatReqMsg")
    query.append(" where 1=1")
    if (mchtId != null && !("" == mchtId)) {
      query.append(" AND id.mchtId like '%").append(mchtId).append("%'")
    }
    query.append(" ORDER BY id.mchtId")
    val m: Map[String, AnyRef] = wechatReqMsgDao.findByPage(query.toString, pagaData)
    m
  }

  @Transactional
  @CacheEvict(value = Array("wechatReqMsgCache"), allEntries = true)
  def save(data: TblWechatReqMsg) {
    wechatReqMsgDao.save(data)
  }


}