package com.ebeijia.controller.action.role

import java.util.{HashMap, Map}
import javax.servlet.http.{HttpServletRequest, HttpSession}

import com.ebeijia.ajax.resp.AjaxResp
import com.ebeijia.service.roleInf.RoleInfService
import com.ebeijia.util.Constant
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, ResponseBody}

/**
 * RoleQueryAction
 * @author zhicheng.xu
 *         15/8/10
 */

@Controller
@RequestMapping(value = Array("/wechat/role")) class RoleQueryAction {
  private val logger: Logger = LoggerFactory.getLogger(classOf[RoleQueryAction])

  @Autowired
  private val roleInfService: RoleInfService = null

  @RequestMapping(value = Array("query.html"), method = Array(RequestMethod.POST))
  @ResponseBody def query(session: HttpSession, request: HttpServletRequest): Map[String, AnyRef] = {
    val map: Map[String, AnyRef] = new HashMap[String, AnyRef]
    val roleId: String = request.getParameter("roleId")
    val roleName: String = request.getParameter("roleName")
    val pageData: String = request.getParameter("pageData")
    try {
      val mapTmp: Map[String, AnyRef] = roleInfService.findBySql(roleId, roleName, pageData)
      map.put("roleList", mapTmp.get("list"))
      map.put("current", mapTmp.get("current"))
      map.put("total", mapTmp.get("total"))
      AjaxResp.getReturn(Constant.SUCCESS_CODE, "", map)
    }
    catch {
      case e: Exception => {
        logger.info("角色查询失败")
        AjaxResp.getReturn(Constant.ERROR_CODE, "角色查询失败,请联系管理员或稍后再试", "")
      }
    }
  }

  @RequestMapping(value = Array("queryDsc.html"), method = Array(RequestMethod.POST))
  @ResponseBody def queryDsc(session: HttpSession, request: HttpServletRequest): Map[String, AnyRef] = {
    val roleId: String = request.getParameter("roleId")
    try {
      val mapTmp: Map[String, AnyRef] = roleInfService.findRoleDef(roleId)
      AjaxResp.getReturn(Constant.SUCCESS_CODE, "", mapTmp)
    }
    catch {
      case e: Exception => {
        logger.info("角色详情查询失败")
        AjaxResp.getReturn(Constant.ERROR_CODE, "角色详情查询失败,请联系管理员或稍后再试", "")
      }
    }
  }
}

