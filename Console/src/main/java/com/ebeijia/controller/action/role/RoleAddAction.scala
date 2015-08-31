package com.ebeijia.controller.action.role

import java.util.Map
import javax.servlet.http.{HttpServletRequest, HttpSession}

import com.ebeijia.ajax.resp.AjaxResp
import com.ebeijia.annotation.MyLog
import com.ebeijia.service.roleInf.RoleInfService
import com.ebeijia.util.Constant
import org.ebeijia.tools.Validate4J
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, ResponseBody}

/**
 * RoleAddAction
 * @author zhicheng.xu
 *         15/8/10
 */
@Controller
@RequestMapping(value = Array("/wechat/role")) class RoleAddAction {

  @Autowired
  private val roleInfService: RoleInfService = null
  private val logger: Logger = LoggerFactory.getLogger(classOf[RoleAddAction])

  @RequestMapping(value = Array("add.html"), method = Array(RequestMethod.POST))
  @MyLog(remark = "角色新增")
  @ResponseBody def add(session: HttpSession, request: HttpServletRequest): Map[String, AnyRef] = {
    val roleList: String = request.getParameter("roleList")
    val name: String = request.getParameter("name")
    val dsc: String = request.getParameter("dsc")
    val s: Array[Array[String]] = Array(Array(dsc, "0", "64"), Array(name, "1", "32"), Array(roleList, "5", null))
    if (!Validate4J.checkStrArrLen(s)) {
      AjaxResp.getReturn(Constant.ERROR_CODE, "参数长度或格式不正确", "")
    }
    try {
      roleInfService.save(name, dsc, roleList)
      AjaxResp.getReturn(Constant.SUCCESS_CODE, "", "")
    }
    catch {
      case e: Exception => {
        logger.info("角色新增失败")
        AjaxResp.getReturn(Constant.ERROR_CODE, "角色新增失败,请联系管理员或稍后再试", "")
      }
    }
  }
}
