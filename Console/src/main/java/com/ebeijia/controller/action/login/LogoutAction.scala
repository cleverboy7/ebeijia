package com.ebeijia.controller.action.login

/**
 * LogoutAction
 * @author zhicheng.xu
 *         15/8/7
 */

import java.util.Map
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import com.ebeijia.ajax.resp.AjaxResp
import com.ebeijia.annotation.MyLog
import com.ebeijia.util.Constant

@Controller
class LogoutAction {

  @RequestMapping(value = Array("logout.html"))
//  @MyLog(remark = "登出")
  @ResponseBody def logout(session: HttpSession, request: HttpServletRequest): Map[String, AnyRef] = {
    session.removeAttribute("admin")
    session.removeAttribute("operator")
    session.removeAttribute("role")
    AjaxResp.getReturn(Constant.SUCCESS_CODE, "", "")
  }
}
