package com.ebeijia.service.dictInf

import java.util.{LinkedHashMap, LinkedList, List, Map}
import com.ebeijia.dao.dictInf.DictInfDao
import com.ebeijia.entity.TblDictInf
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * DictInfServiceImpl
 * @author zhicheng.xu
 *         15/8/17
 */


@Service
final class DictInfServiceImpl extends DictInfService {
  @Autowired
  private val dictInfDao: DictInfDao = null

  @Transactional
  @Cacheable(value = Array("dictCache"), key = "#root.method.name+#dictType")
  def findByDictType(dictType: String): Map[String, AnyRef] = {
    val map: Map[String, AnyRef] = new LinkedHashMap[String, AnyRef]
    val sb: StringBuilder = new StringBuilder
    sb.append("from TblDictInf where id.dictType ='").append(dictType).append("'")
    sb.append(" order by id.dictId")
    val tblDictInfs: List[TblDictInf] = dictInfDao.find(sb.toString)
    val list: List[AnyRef] = new LinkedList[AnyRef]
    import scala.collection.JavaConversions._
    for (tblDictInf <- tblDictInfs) {
      val hashMap: Map[String, AnyRef] = new LinkedHashMap[String, AnyRef]
      hashMap.put("key", tblDictInf.getFieldType + "-" + tblDictInf.getFieldName)
      hashMap.put("value", tblDictInf.getDictId)
      list.add(hashMap)
    }
    map.put("info", list)
    map
  }
}
