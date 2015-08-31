package com.ebeijia.ajax.resp

import java.util.HashMap
import java.util.Map

/**
 * AjaxResp
 * @author zhicheng.xu
 *         15/8/7
 */
object AjaxResp {
  def getReturn(respCode: String, msg: String, o: AnyRef): Map[String, AnyRef] = {
    val map: Map[String, AnyRef] = new HashMap[String, AnyRef]
    map.put("respCode", respCode)
    map.put("errorInfo", msg)
    map.put("content", o)
    map
  }
}

