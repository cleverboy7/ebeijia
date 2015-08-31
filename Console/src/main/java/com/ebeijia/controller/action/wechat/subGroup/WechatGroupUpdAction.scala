package com.ebeijia.controller.action.wechat.subGroup

import java.util.Map
import javax.servlet.http.{HttpServletRequest, HttpSession}

import com.ebeijia.ajax.resp.AjaxResp
import com.ebeijia.annotation.MyLog
import com.ebeijia.service.wechat.group.WechatSubGroupService
import com.ebeijia.util.Constant
import com.ebeijia.util.wechat.WechatError
import org.ebeijia.tools.Validate4J
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, ResponseBody}

/**
 * WechatGroupUpdAction
 * @author zhicheng.xu
 *         15/8/10
 */


@Controller
@RequestMapping(value = Array("/wechat/subGroup"))
class WechatGroupUpdAction {

  @Autowired
  private val wechatSubGroupService: WechatSubGroupService = null
  private val logger: Logger = LoggerFactory.getLogger(classOf[WechatGroupUpdAction])


  @RequestMapping(value = Array("upd.html"), method = Array(RequestMethod.POST))
  @MyLog(remark = "修改用户组别")
  @ResponseBody def upd(session: HttpSession, request: HttpServletRequest): Map[String, AnyRef] = {
    val mchtId: String = request.getParameter("mchtId")
    val name: String = request.getParameter("name")
    val groupId: String = request.getParameter("groupId")
    val s: Array[Array[String]] = Array(Array(mchtId, "15", "15"), Array(name, "1", "64"), Array(groupId, "1", "8"))
    if (!Validate4J.checkStrArrLen(s)) {
      AjaxResp.getReturn(Constant.ERROR_CODE, "参数长度或格式不正确", "")
    }
    try {
      if (wechatSubGroupService.getById(groupId) == null) {
        AjaxResp.getReturn(Constant.ERROR_CODE, "组别不存在", "")
      }
      val respMsg: String = wechatSubGroupService.update(mchtId, groupId, name)
      if (respMsg == null) {
        AjaxResp.getReturn(Constant.SUCCESS_CODE, "", "")
      }
      else {
        AjaxResp.getReturn(Constant.ERROR_CODE, WechatError.checkCode(respMsg), "")
      }
    }
    catch {
      case e: Exception => {
        logger.info("修改用户组别失败")
        AjaxResp.getReturn(Constant.ERROR_CODE, "修改用户组别失败,请联系管理员或稍后再试", "")
      }
    }
  }

  @RequestMapping(value = Array("del.html"), method = Array(RequestMethod.POST))
  @MyLog(remark = "删除用户组别")
  @ResponseBody def del(session: HttpSession, request: HttpServletRequest): Map[String, AnyRef] = {
    val mchtId: String = request.getParameter("mchtId")
    val groupId: String = request.getParameter("groupId")
    val s: Array[Array[String]] = Array(Array(mchtId, "15", "15"), Array(groupId, "1", "8"))
    if (!Validate4J.checkStrArrLen(s)) {
      AjaxResp.getReturn(Constant.ERROR_CODE, "参数长度或格式不正确", "")
    }
    try {
      if (wechatSubGroupService.getById(groupId) == null) {
        AjaxResp.getReturn(Constant.ERROR_CODE, "组别不存在", "")
      }
      val respMsg: String = wechatSubGroupService.del(mchtId, groupId)
      if (respMsg == null) {
        AjaxResp.getReturn(Constant.SUCCESS_CODE, "", "")
      }
      else {
        AjaxResp.getReturn(Constant.ERROR_CODE, WechatError.checkCode(respMsg), "")
      }
    }
    catch {
      case e: Exception => {
        logger.info("删除用户组别失败,请联系管理员或稍后再试")
        AjaxResp.getReturn(Constant.ERROR_CODE, "删除用户组别失败,请联系管理员或稍后再试", "")
      }
    }
  }
}

