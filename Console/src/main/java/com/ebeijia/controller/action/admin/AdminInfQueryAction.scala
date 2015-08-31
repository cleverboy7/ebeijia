package com.ebeijia.controller.action.admin

import java.util.{HashMap, Map}
import javax.servlet.http.{HttpServletRequest, HttpSession}

import com.ebeijia.ajax.resp.AjaxResp
import com.ebeijia.service.adminInf.AdminService
import com.ebeijia.util.Constant
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, ResponseBody}


@Controller
@RequestMapping(value = Array("/wechat/admin")) class AdminInfQueryAction {
  private val logger: Logger = LoggerFactory.getLogger(classOf[AdminInfQueryAction])
  @Autowired
  private val adminService: AdminService = null

  @RequestMapping(value = Array("/query.html"), method = Array(RequestMethod.POST))
  @ResponseBody def query(session: HttpSession, request: HttpServletRequest): Map[String, AnyRef] = {
    val adminId: String = request.getParameter("adminId")
    val adminName: String = request.getParameter("adminName")
    val adminStat: String = request.getParameter("adminStat")
    val pageData: String = request.getParameter("aoData")
    var map: Map[String, AnyRef] = new HashMap[String, AnyRef]
    try {
      val mapTmp: Map[String, AnyRef] = adminService.findBySql(adminId, adminName, adminStat, pageData)
      map.put("adminList", mapTmp.get("list"))
      map.put("current", mapTmp.get("current"))
      map.put("total", mapTmp.get("total"))
      map = AjaxResp.getReturn(Constant.SUCCESS_CODE, "", map)
      map
    }
    catch {
      case e: Exception => {
        logger.info("系统管理员查询失败")
        AjaxResp.getReturn(Constant.ERROR_CODE, "系统管理员查询失败,请联系管理员或稍后再试", "")
      }
    }
  }
}
