
package com.ebeijia.controller.action.admin

import java.util.{HashMap, List, Map}
import javax.servlet.http.{HttpServletRequest, HttpSession}

import com.ebeijia.ajax.resp.AjaxResp
import com.ebeijia.annotation.MyLog
import com.ebeijia.entity.TblAdminInf
import com.ebeijia.service.adminInf.AdminService
import com.ebeijia.util.{Constant, EncryptMD5Util}
import org.ebeijia.tools.{DateTime4J, Validate4J}
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, ResponseBody}

/**
 * AdminInfUpdAction
 * @author zhicheng.xu
 *         15/8/7
 */

@Controller
@RequestMapping(value = Array("/wechat/admin")) class AdminInfUpdAction {
  private val logger: Logger = LoggerFactory.getLogger(classOf[AdminInfUpdAction])
  @Autowired
  private val adminService: AdminService = null

  @RequestMapping(value = Array("upd.html"), method = Array(RequestMethod.POST))
  @ResponseBody def upd(session: HttpSession, request: HttpServletRequest): Map[String, AnyRef] = {
    val adminId: String = request.getParameter("adminId")
    val adminName: String = request.getParameter("adminName")
    val roleId: String = request.getParameter("roleId")
    val adminDsc: String = request.getParameter("dsc")
    val s: Array[Array[String]] = Array(Array(adminId, "1", "10"), Array(adminName, "4", "32"), Array(roleId, "1", "6"), Array(adminDsc, "0", "128"))
    if (!Validate4J.checkStrArrLen(s)) {
      AjaxResp.getReturn(Constant.ERROR_CODE, "参数长度或格式不正确", "")
    }
    val tblAdminInf: TblAdminInf = adminService.getById(adminId)
    if (tblAdminInf == null) {
      AjaxResp.getReturn(Constant.ERROR_CODE, "该用户不存在", "")

    }
    tblAdminInf.setAdminId(adminId)
    tblAdminInf.setAdminName(adminName)
    tblAdminInf.setRoleId(roleId)
    tblAdminInf.setAdminDsc(adminDsc)
    tblAdminInf.setUpdTime(DateTime4J.getCurrentDateTime)
    try {
      adminService.update(tblAdminInf)
      AjaxResp.getReturn(Constant.SUCCESS_CODE, "", "")
    }
    catch {
      case e: Exception => {
        AjaxResp.getReturn(Constant.ERROR_CODE, "修改管理员失败,请联系管理员或稍后再试", "")
      }
    }
  }

  @RequestMapping(value = Array("passUpd.html"), method = Array(RequestMethod.POST))
  @MyLog(remark = "管理员修改密码")
  @ResponseBody def passUpd(session: HttpSession, request: HttpServletRequest): Map[String, AnyRef] = {
    val adminId: String = request.getParameter("adminId")
    val oldPwd: String = request.getParameter("oldPwd")
    val newPwd: String = request.getParameter("newPwd")
    val s: Array[Array[String]] = Array(Array(adminId, "1", "10"), Array(newPwd, "6", "32"), Array(oldPwd, "6", "32"))
    if (!Validate4J.checkStrArrLen(s)) {
      AjaxResp.getReturn(Constant.ERROR_CODE, "密码格式不正确", "")
    }
    try {
      val list: List[TblAdminInf] = adminService.checkScalaAdmin(adminId, EncryptMD5Util.encrypt(oldPwd))
      if (list.isEmpty) {
        AjaxResp.getReturn(Constant.ERROR_CODE, "账户不存在", "")
      }
      else {
        AjaxResp.getReturn(Constant.SUCCESS_CODE, "", "")
      }
    }
    catch {
      case e: Exception => {
        logger.info("管理员修改密码失败")
        AjaxResp.getReturn(Constant.ERROR_CODE, "管理员修改密码失败,请联系管理员或稍后再试", "")
      }
    }
  }

  @RequestMapping(value = Array("del.html"), method = Array(RequestMethod.POST))
  @ResponseBody def del(session: HttpSession, request: HttpServletRequest): Map[String, AnyRef] = {
    val adminId: String = request.getParameter("adminId")
    val s: Array[Array[String]] = Array(Array(adminId, "1", "10"))
    var map: Map[String, AnyRef] = new HashMap[String, AnyRef]
    if (!Validate4J.checkStrArrLen(s)) {
      map = AjaxResp.getReturn(Constant.ERROR_CODE, "id格式不正确", "")
      return map
    }
    try {
      val tblAdminInf: TblAdminInf = adminService.getById(adminId)
      if (tblAdminInf == null) {
        AjaxResp.getReturn(Constant.ERROR_CODE, "账户不存在", "")
      }
      else {
        adminService.delById(tblAdminInf.getAdminId)
        AjaxResp.getReturn(Constant.SUCCESS_CODE, "", "")
      }
    }
    catch {
      case e: Exception => {
        logger.info("删除管理员失败")
        AjaxResp.getReturn(Constant.ERROR_CODE, "删除管理员失败,请联系管理员或稍后再试", "")
      }
    }
  }
}

