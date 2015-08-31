package com.ebeijia.controller.action.admin

import java.util.Map
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
import org.springframework.web.multipart.{MultipartFile, MultipartHttpServletRequest}

/**
 * AdminInfAddAction
 * @author zhicheng.xu
 *         15/8/7
 */


@Controller
@RequestMapping(value = Array("/wechat/admin"))
class AdminInfAddAction {

  private val logger: Logger = LoggerFactory.getLogger(classOf[AdminInfAddAction])
  @Autowired
  private val adminService: AdminService = null

  @RequestMapping(value = Array("add.html"), method = Array(RequestMethod.POST))
  @MyLog(remark = "管理员添加")
  @ResponseBody def add(session: HttpSession, request: HttpServletRequest): Map[String, AnyRef] = {
    val tblAdminInf: TblAdminInf = this.build(request)
    if (tblAdminInf == null) {
      AjaxResp.getReturn(Constant.ERROR_CODE, "参数长度或格式不正确", "")
    }
    val multipartRequest: MultipartHttpServletRequest = request.asInstanceOf[MultipartHttpServletRequest]
    val file: MultipartFile = multipartRequest.getFile("file")
    var ext: String = null
    if (file != null) {
      val fileName: String = file.getOriginalFilename
      ext = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length)
      ext = ext.toLowerCase
      val respMsg: String = this.vali(ext)
      if (!("" == respMsg)) {
        AjaxResp.getReturn(Constant.ERROR_CODE, respMsg, "")
      }
    }
    try {
      adminService.save(tblAdminInf, "jpg", file, ext)
      AjaxResp.getReturn(Constant.SUCCESS_CODE, "", "")
    }
    catch {
      case e: Exception => {
        logger.info("新增管理员失败")
        AjaxResp.getReturn(Constant.ERROR_CODE, "新增管理员失败,请联系管理员或稍后再试", "")
      }
    }
  }

  private def build(request: HttpServletRequest): TblAdminInf = {
    val adminName: String = request.getParameter("adminName")
    val roleId: String = request.getParameter("roleId")
    val adminPwd: String = request.getParameter("adminPwd")
    val adminDsc: String = request.getParameter("adminDsc")
    val s: Array[Array[String]] = Array(Array(adminName, "4", "32"), Array(roleId, "1", "6"), Array(adminPwd, "6", "32"), Array(adminDsc, "0", "128"))
    if (!Validate4J.checkStrArrLen(s)) {
      null
    }
    val tblAdminInf: TblAdminInf = new TblAdminInf
    tblAdminInf.setAdminName(adminName)
    tblAdminInf.setRoleId(roleId)
    tblAdminInf.setAdminStat(Constant.BASE_SUCCESS)
    tblAdminInf.setAdminPwd(EncryptMD5Util.encrypt(adminPwd))
    tblAdminInf.setAdminDsc(adminDsc)
    tblAdminInf.setLastLogTime(" ")
    tblAdminInf.setCrtTime(DateTime4J.getCurrentDateTime)
    tblAdminInf
  }

  private def vali(ext: String): String = {
    var respMsg: String = ""
    if (("jpg" == ext) || ("png" == ext)) {
    }
    else {
      respMsg = "图片格式不正确"
    }
    respMsg
  }
}

