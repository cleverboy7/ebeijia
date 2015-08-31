package com.ebeijia.service.mcht

import java.util.{LinkedHashMap, LinkedList, List, Map}

import com.ebeijia.dao.mcht.MchtInfDao
import com.ebeijia.entity.TblMchtInf
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

/**
 * MchtInfServiceImpl
 * @author zhicheng.xu
 *         15/8/17
 */


@Service("mchtInfService")
class MchtInfServiceImpl extends MchtInfService {
  @Autowired
  private val mchtInfDao: MchtInfDao = null

  @Cacheable(value = Array("mchtCache"), key = "#root.method.name")
  def ListMchtId: Map[String, AnyRef] = {
    val map: Map[String, AnyRef] = new LinkedHashMap[String, AnyRef]
    val hql: String = "FROM TblMchtInf ORDER BY mchtLvl,mchtId"
    val tblMchtInfs: List[TblMchtInf] = mchtInfDao.find(hql)
    val list: List[AnyRef] = new LinkedList[AnyRef]
    import scala.collection.JavaConversions._
    for (tblMchtInf <- tblMchtInfs) {
      val hashMap: Map[String, AnyRef] = new LinkedHashMap[String, AnyRef]
      hashMap.put("key", tblMchtInf.getMchtId + "-" + tblMchtInf.getMchtName)
      hashMap.put("value", tblMchtInf.getMchtId)
      list.add(hashMap)
    }
    map.put("info", list)
    map
  }
}
