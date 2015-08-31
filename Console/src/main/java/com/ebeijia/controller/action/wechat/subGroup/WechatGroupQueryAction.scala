package com.ebeijia.controller.action.wechat.subGroup

/**
 * WechatGroupQueryAction
 * @author zhicheng.xu
 *         15/8/10
 */

import java.util.HashMap
import java.util.Map
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import com.ebeijia.ajax.resp.AjaxResp
import com.ebeijia.service.wechat.group.WechatSubGroupService
import com.ebeijia.util.Constant

@Controller
@RequestMapping(value = Array("/wechat/subGroup"))
class WechatGroupQueryAction {

  @Autowired
  private val wechatSubGroupService: WechatSubGroupService = null
  private val logger: Logger = LoggerFactory.getLogger(classOf[WechatGroupQueryAction])

  @RequestMapping(value = Array("query.html"), method = Array(RequestMethod.POST))
  @ResponseBody def query(session: HttpSession, request: HttpServletRequest): Map[String, AnyRef] = {
    val name: String = request.getParameter("name")
    val pageData: String = request.getParameter("aoData")
    val map: Map[String, AnyRef] = new HashMap[String, AnyRef]
    try {
      val mapTmp: Map[String, AnyRef] = wechatSubGroupService.findBySql(name, pageData)
      map.put("groupList", mapTmp.get("list"))
      map.put("current", mapTmp.get("current"))
      map.put("total", mapTmp.get("total"))
      AjaxResp.getReturn(Constant.SUCCESS_CODE, "", map)
    }
    catch {
      case e: Exception => {
        logger.info("关注者组别查询失败")
        AjaxResp.getReturn(Constant.ERROR_CODE, "关注者组别查询失败,请联系管理员或稍后再试", "")
      }
    }
  }
}

