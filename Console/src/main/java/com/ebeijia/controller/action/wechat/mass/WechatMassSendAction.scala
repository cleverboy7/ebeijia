package com.ebeijia.controller.action.wechat.mass

import java.util.Map
import javax.servlet.http.{HttpServletRequest, HttpSession}

import com.ebeijia.ajax.resp.AjaxResp
import com.ebeijia.annotation.MyLog
import com.ebeijia.service.wechat.mass.WechatMassService
import com.ebeijia.util.Constant
import com.ebeijia.util.wechat.WechatError
import org.ebeijia.tools.Validate4J
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, ResponseBody}

/**
 * WechatMassSendAction
 * @author zhicheng.xu
 *         15/8/10
 */


@Controller
@RequestMapping(value = Array("/wechat/mass"))
class WechatMassSendAction {
  @Autowired
  private val wechatMassService: WechatMassService = null

  private val logger: Logger = LoggerFactory.getLogger(classOf[WechatMassSendAction])

  @RequestMapping(value = Array("sendAll.html"), method = Array(RequestMethod.POST))
  @MyLog(remark = "微信群发消息")
  @ResponseBody def sendAll(session: HttpSession, request: HttpServletRequest): Map[String, AnyRef] = {
    val mchtId: String = request.getParameter("mchtId")
    val content: String = request.getParameter("content")
    val `type`: String = request.getParameter("type")
    val groupId: String = request.getParameter("groupId")
    val media: String = request.getParameter("media")
    val s: Array[Array[String]] = Array(Array(mchtId, "15", "15"), Array(content, "0", "128"), Array(`type`, "4", "5"), Array(groupId, "1", "6"), Array(media, "0", "100"))
    if (!Validate4J.checkStrArrLen(s)) {
      AjaxResp.getReturn(Constant.ERROR_CODE, "参数长度或格式不正确", "")
    }
    try {
      val respMsg: String = wechatMassService.sendByGroup(mchtId, content, `type`, groupId, media)
      if (respMsg == null) {
        AjaxResp.getReturn(Constant.SUCCESS_CODE, "", "")
      }
      else {
        AjaxResp.getReturn(Constant.ERROR_CODE, WechatError.checkCode(respMsg), "")
      }
    }
    catch {
      case e: Exception => {
        request.setAttribute("message", "群发消息失败")
        logger.info("群发消息失败")
        AjaxResp.getReturn(Constant.ERROR_CODE, "群发消息失败,请联系管理员或稍后再试", "")
      }
    }
  }

  @RequestMapping(value = Array("sendUsr.html"), method = Array(RequestMethod.POST))
  @MyLog(remark = "微信筛选群发消息")
  @ResponseBody def sendUsr(session: HttpSession, request: HttpServletRequest): Map[String, AnyRef] = {
    val mchtId: String = request.getParameter("mchtId")
    val content: String = request.getParameter("content")
    val msgtype: String = request.getParameter("msgType")
    val toUsr: String = request.getParameter("toUsr")
    val media: String = request.getParameter("media")
    val s: Array[Array[String]] = Array(Array(mchtId, "15", "15"), Array(content, "0", "128"), Array(msgtype, "4", "5"), Array(toUsr, "1", null), Array(media, "0", "100"))
    if (!Validate4J.checkStrArrLen(s)) {
      AjaxResp.getReturn(Constant.ERROR_CODE, "参数长度或格式不正确", "")
    }
    try {
      val respMsg: String = wechatMassService.sendByUsr(mchtId, content, msgtype, toUsr, media)
      if (respMsg == null) {
        AjaxResp.getReturn(Constant.SUCCESS_CODE, "", "")
      }
      else {
        AjaxResp.getReturn(Constant.ERROR_CODE, WechatError.checkCode(respMsg), "")
      }
    }
    catch {
      case e: Exception => {
        request.setAttribute("message", "筛选群发消息失败")
        logger.info("筛选群发消息失败")
        AjaxResp.getReturn(Constant.ERROR_CODE, "筛选群发消息失败,请联系管理员或稍后再试", "")
      }
    }
  }
}

