package com.ebeijia.controller.action.role

import java.util.{HashMap, Map}
import javax.servlet.http.{HttpServletRequest, HttpSession}

import com.ebeijia.ajax.resp.AjaxResp
import com.ebeijia.annotation.MyLog
import com.ebeijia.entity.TblRoleInf
import com.ebeijia.service.roleInf.RoleInfService
import com.ebeijia.util.Constant
import org.ebeijia.tools.Validate4J
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, ResponseBody}

/**
 * RoleUpdAction
 * @author zhicheng.xu
 *         15/8/10
 */

@Controller
@RequestMapping(value = Array("/wechat/role")) class RoleUpdAction {
  @Autowired
  private val roleInfService: RoleInfService = null
  private val logger: Logger = LoggerFactory.getLogger(classOf[RoleUpdAction])

  @RequestMapping(value = Array("upd.html"), method = Array(RequestMethod.POST))
  @ResponseBody
  @MyLog(remark = "角色修改") def upd(session: HttpSession, request: HttpServletRequest): Map[String, AnyRef] = {
    val roleList: String = request.getParameter("roleList")
    val roleId: String = request.getParameter("roleId")
    val name: String = request.getParameter("name")
    val dsc: String = request.getParameter("dsc")
    val s: Array[Array[String]] = Array(Array(dsc, "0", "64"), Array(name, "1", "32"), Array(roleList, "5", null))
    if (!Validate4J.checkStrArrLen(s)) {
      AjaxResp.getReturn(Constant.ERROR_CODE, "参数长度或格式不正确", "")
    }
    val tblRoleInf: TblRoleInf = roleInfService.getById(roleId)
    if (tblRoleInf == null) {
      AjaxResp.getReturn(Constant.ERROR_CODE, "该角色不存在", "")
    }
    tblRoleInf.setRoleName(name)
    tblRoleInf.setDsc(dsc)
    try {
      roleInfService.update(tblRoleInf, roleList)
      AjaxResp.getReturn(Constant.SUCCESS_CODE, "", "")
    }
    catch {
      case e: Exception => {
        logger.info("修改角色失败")
        AjaxResp.getReturn(Constant.ERROR_CODE, "修改角色失败,请联系管理员或稍后再试", "")
      }
    }
  }

  @RequestMapping(value = Array("del.html"), method = Array(RequestMethod.POST))
  @ResponseBody
  @MyLog(remark = "角色删除") def del(session: HttpSession, request: HttpServletRequest): Map[String, AnyRef] = {
    val roleId: String = request.getParameter("roleId")
    val s: Array[Array[String]] = Array(Array(roleId, "1", "6"))
    if (!Validate4J.checkStrArrLen(s)) {
      AjaxResp.getReturn(Constant.ERROR_CODE, "角色id格式不正确", "")
    }
    try {
      val tblRoleInf: TblRoleInf = roleInfService.getById(roleId)
      if (tblRoleInf == null) {
        AjaxResp.getReturn(Constant.ERROR_CODE, "该角色不存在", "")
      }
      else {
        roleInfService.delById(tblRoleInf.getRoleId)
        AjaxResp.getReturn(Constant.SUCCESS_CODE, "", "")
      }
    }
    catch {
      case e: Exception => {
        logger.info("删除角色失败")
        AjaxResp.getReturn(Constant.ERROR_CODE, "删除角色失败,请联系管理员或稍后再试", "")
      }
    }
  }
}

