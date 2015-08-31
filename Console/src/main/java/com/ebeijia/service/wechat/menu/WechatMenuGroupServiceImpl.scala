package com.ebeijia.service.wechat.menu

import java.util.{LinkedHashMap, LinkedList, List, Map}

import com.ebeijia.dao.wechat.WechatGroupDao
import com.ebeijia.entity.wechat.TblWechatGroup
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * WechatMenuGroupServiceImpl
 * @author zhicheng.xu
 *         15/8/14
 */


@Service
final class WechatMenuGroupServiceImpl extends WechatMenuGroupService {
  @Autowired
  private val wechatGroupDao: WechatGroupDao = null

  @Transactional
  @Cacheable(value = Array("wechatMenuCache"), key = "#root.method.name+#mchtId")
  def find(mchtId: String): List[TblWechatGroup] = {
    val sb: StringBuilder = new StringBuilder
    sb.append("from TblWechatGroup where mchtId ='").append(mchtId).append("'")
    sb.append(" and id in (select distinct groupId  from TblWechatMenu where mchtId ='").append(mchtId)
    sb.append("')")
    val list: List[TblWechatGroup] = wechatGroupDao.find(sb.toString)
    list
  }

  @Transactional
  @Cacheable(value = Array("wechatMenuCache"), key = "#root.method.name+#mchtId")
  def listFind(mchtId: String): Map[String, AnyRef] = {
    val sb: StringBuilder = new StringBuilder
    sb.append("FROM TblWechatGroup")
    sb.append(" where mchtId='").append(mchtId).append("'")
    sb.append(" ORDER BY id")
    val grouplist: List[TblWechatGroup] = wechatGroupDao.find(sb.toString)
    val list: List[AnyRef] = new LinkedList[AnyRef]
    val map: Map[String, AnyRef] = new LinkedHashMap[String, AnyRef]
    import scala.collection.JavaConversions._
    for (tblWechatGroup <- grouplist) {
      val hashMap: Map[String, AnyRef] = new LinkedHashMap[String, AnyRef]
      hashMap.put("key", tblWechatGroup.getId + "-" + tblWechatGroup.getName)
      hashMap.put("value", tblWechatGroup.getId)
      list.add(hashMap)
    }
    map.put("info", list)
    map
  }
}
