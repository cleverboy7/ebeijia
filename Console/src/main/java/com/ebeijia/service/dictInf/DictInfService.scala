package com.ebeijia.service.dictInf
import java.util.Map
/**
 * DictInfService
 * @author zhicheng.xu
 *         15/8/17
 */



trait DictInfService {
  def findByDictType(dictType: String): Map[String, AnyRef]
}
