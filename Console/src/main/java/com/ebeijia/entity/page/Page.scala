package com.ebeijia.entity.page

/**
 * Page
 * @author zhicheng.xu
 *         15/8/17
 */

import net.sf.json.JSONArray
import net.sf.json.JSONObject

object Page {
  def init(aoData: String): PageEntity = {
    val page: PageEntity = new PageEntity
    if (aoData == null) {
      page.setiDisplayStart(1)
      page.setiDisplayLength(10)
    }
    else {
      val jsonarray: JSONArray = JSONArray.fromObject(aoData)
      for(i <- 0 until jsonarray.size()){
        val obj: JSONObject = jsonarray.get(i).asInstanceOf[JSONObject]
        if (obj.get("name") == "sEcho") {
          page.setsEcho(obj.get("value").toString)
        }
        if (obj.get("name") == "iDisplayStart") {
          page.setiDisplayStart(obj.getInt("value"))
        }
        if (obj.get("name") == "iDisplayLength") page.setiDisplayLength(obj.getInt("value"))
      }
    }
    page
  }
}

