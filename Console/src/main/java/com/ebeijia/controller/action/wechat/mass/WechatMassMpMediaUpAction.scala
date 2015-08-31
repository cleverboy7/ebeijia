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
 * WechatMassMpMediaUpAction
 * @author zhicheng.xu
 *         15/8/10
 */

@Controller
@RequestMapping(value = Array("/wechat/mass"))
class WechatMassMpMediaUpAction {

  @Autowired
  private val wechatMassService: WechatMassService = null
  private val logger: Logger = LoggerFactory.getLogger(classOf[WechatMassMpMediaUpAction])

  @RequestMapping(value = Array("mpnews.html"), method = Array(RequestMethod.POST))
  @MyLog(remark = "微信上传群发图文")
  @ResponseBody def mpnews(session: HttpSession, request: HttpServletRequest): Map[String, AnyRef] = {
    val mchtId: String = request.getParameter("mchtId")
    val articles: String = request.getParameter("articles")
    val title: String = request.getParameter("title")
    val description: String = request.getParameter("description")
    val s: Array[Array[String]] = Array(Array(mchtId, "15", "15"), Array(articles, "1", null), Array(title, "1", "30"), Array(description, "1", "64"))
    if (!Validate4J.checkStrArrLen(s)) {
      AjaxResp.getReturn(Constant.ERROR_CODE, "参数长度或格式不正确", "")
    }
    try {
      val respMsg: String = wechatMassService.upGtMedia(mchtId, title, description, articles)
      if (respMsg == null) {
        AjaxResp.getReturn(Constant.SUCCESS_CODE, "", "")
      }
      else {
        AjaxResp.getReturn(Constant.ERROR_CODE, WechatError.checkCode(respMsg), "")
      }
    }
    catch {
      case e: Exception => {
        logger.info("微信上传群发图文失败")
        AjaxResp.getReturn(Constant.ERROR_CODE, "微信上传群发图文失败,请联系管理员或稍后再试", "")
      }
    }
  }

  @RequestMapping(value = Array("mpvideo.html"), method = Array(RequestMethod.POST))
  @MyLog(remark = "微信上传群发视频")
  @ResponseBody def mpvideo(session: HttpSession, request: HttpServletRequest): Map[String, AnyRef] = {
    val mchtId: String = request.getParameter("mchtId")
    val title: String = request.getParameter("title")
    val description: String = request.getParameter("description")
    val mediaId: String = request.getParameter("mediaId")
    val s: Array[Array[String]] = Array(Array(mchtId, "15", "15"), Array(mediaId, "1", null), Array(title, "1", "30"), Array(description, "1", "64"))
    if (!Validate4J.checkStrArrLen(s)) {
      AjaxResp.getReturn(Constant.ERROR_CODE, "参数长度或格式不正确", "")
    }
    try {
      val respMsg: String = wechatMassService.upVideoMedia(mchtId, title, description, mediaId)
      if (respMsg == null) {
        AjaxResp.getReturn(Constant.SUCCESS_CODE, "", "")
      }
      else {
        AjaxResp.getReturn(Constant.ERROR_CODE, WechatError.checkCode(respMsg), "")
      }
    }
    catch {
      case e: Exception => {
        logger.info("微信上传群发视频失败")
        AjaxResp.getReturn(Constant.ERROR_CODE, "微信上传群发视频失败,请联系管理员或稍后再试", "")
      }
    }
  }
}
