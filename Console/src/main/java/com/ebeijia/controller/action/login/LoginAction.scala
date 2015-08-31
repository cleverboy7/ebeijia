package com.ebeijia.controller.action.login

import java.util.List
import javax.servlet.http.{HttpServletRequest, HttpSession}

import com.ebeijia.annotation.MyLog
import com.ebeijia.entity.TblAdminInf
import com.ebeijia.service.adminInf.AdminService
import com.ebeijia.service.roleInf.RoleInfService
import com.ebeijia.util.EncryptMD5Util
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod}
import org.springframework.web.servlet.ModelAndView

/**
 * LoginAction
 * @author zhicheng.xu
 *         15/8/7
 */

@Controller
class LoginAction {
  @Autowired
  private val adminService: AdminService = null
  @Autowired
  private val roleInfService: RoleInfService = null

  @RequestMapping(value = Array("login.html"), method = Array(RequestMethod.POST))
  @MyLog(remark = "用户登录")
  @throws(classOf[Exception])
  def Login(session: HttpSession, request: HttpServletRequest): ModelAndView = {
    val mav: ModelAndView = new ModelAndView
    val usrName: String = request.getParameter("usrName")
    val loginPwd: String = request.getParameter("loginPwd")
    if (loginPwd.trim == null || ("" == loginPwd.trim) || usrName.trim == null || ("" == usrName.trim)) {
      request.setAttribute("message", "用户名和密码不能为空！")
      mav.setViewName("login")
      return mav
    }
    if (loginPwd.length < 6) {
      request.setAttribute("message", "密码不合法，请重新输入！")
      mav.setViewName("login")
      return mav
    }
    val adminInfs: List[TblAdminInf] = adminService.login(usrName)
    if (adminInfs.isEmpty) {
      request.setAttribute("message", "用户名不存在，请重新输入！")
      mav.setViewName("login")
      return mav
    }
    val tblAdminInf: TblAdminInf = adminInfs.get(0)
    if (!(EncryptMD5Util.encrypt(loginPwd) == tblAdminInf.getAdminPwd)) {
      request.setAttribute("message", "用户名或密码有误，请重新输入！")
      mav.setViewName("login")
      return mav
    }
    val l: List[_] = adminService.usrInfHead(tblAdminInf.getAdminId)
    val role: String = roleInfService.funcFind(tblAdminInf.getAdminId)
    if (role == null) {
      request.setAttribute("message", "该用户没用权限!请联系管理员分配权限!")
      mav.setViewName("login")
      return mav
    }
    session.setAttribute("admin", l.get(0))
    session.setAttribute("role", role)
    session.setAttribute("operator", tblAdminInf)
    mav.setViewName("index")
    mav
  }
}

