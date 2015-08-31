package com.ebeijia.controller.action.wechat.media

import java.util.{HashMap, Map}
import javax.servlet.http.{HttpServletRequest, HttpSession}

import com.ebeijia.ajax.resp.AjaxResp
import com.ebeijia.annotation.MyLog
import com.ebeijia.service.wechat.media.{WechatFodderService, WechatMediaService}
import com.ebeijia.util.Constant
import com.ebeijia.util.wechat.WechatError
import org.ebeijia.tools.Validate4J
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, ResponseBody}

/**
 * WechatMediaUpd
 * @author zhicheng.xu
 *         15/8/10
 */


@Controller
@RequestMapping(value = Array("/wechat/media"))
class WechatMediaUpd {
  @Autowired
  private val wechatFodderService: WechatFodderService = null
  @Autowired
  private val wechatMediaService: WechatMediaService = null
  private val logger: Logger = LoggerFactory.getLogger(classOf[WechatMediaUpd])

  @RequestMapping(value = Array("upd.html"), method = Array(RequestMethod.POST))
  @MyLog(remark = "微信多媒体修改")
  @ResponseBody def upd(session: HttpSession, request: HttpServletRequest): Map[String, AnyRef] = {
    val media: String = request.getParameter("media")
    val name: String = request.getParameter("name")
    val dsc: String = request.getParameter("dsc")
    val s: Array[Array[String]] = Array(Array(media, "1", "100"), Array(name, "1", "30"), Array(dsc, "0", "64"))
    if (!Validate4J.checkStrArrLen(s)) {
      AjaxResp.getReturn(Constant.ERROR_CODE, "参数长度或格式不正确", "")
    }
    if (wechatFodderService.getById(media) == null) {
      AjaxResp.getReturn(Constant.ERROR_CODE, "多媒体不存在", "")
    }
    try {
      wechatFodderService.upFodder(media, name, dsc)
      AjaxResp.getReturn(Constant.SUCCESS_CODE, "", "")
    }
    catch {
      case e: Exception => {
        logger.info("微信多媒体修改失败")
        AjaxResp.getReturn(Constant.ERROR_CODE, "微信多媒体修改失败,请联系管理员或稍后再试", "")
      }
    }
  }

  @RequestMapping(value = Array("del.html"), method = Array(RequestMethod.POST))
  @MyLog(remark = "微信永久素材删除")
  @ResponseBody def del(session: HttpSession, request: HttpServletRequest): Map[String, AnyRef] = {
    val media: String = request.getParameter("media")
    val mchtId: String = request.getParameter("mchtId")
    var respMsg: String = null
    val s: Array[Array[String]] = Array(Array(media, "1", "100"), Array(mchtId, "1", "15"))
    if (!Validate4J.checkStrArrLen(s)) {
      AjaxResp.getReturn(Constant.ERROR_CODE, "参数长度或格式不正确", "")
    }
    if (wechatFodderService.getById(media) == null) {
      AjaxResp.getReturn(Constant.ERROR_CODE, "多媒体不存在", "")
    }
    try {
      respMsg = wechatFodderService.delFodder(mchtId, media)
      if (respMsg == null) {
        AjaxResp.getReturn(Constant.SUCCESS_CODE, "", "")
      }
      else {
        AjaxResp.getReturn(Constant.ERROR_CODE, WechatError.checkCode(respMsg), "")
      }
    }
    catch {
      case e: Exception => {
        logger.info("微信永久素材删除失败")
        AjaxResp.getReturn(Constant.ERROR_CODE, "微信永久素材删除失败,请联系管理员或稍后再试", "")
      }
    }
  }

  @RequestMapping(value = Array("updateNews.html"), method = Array(RequestMethod.POST))
  @MyLog(remark = "微信修改图文素材")
  @ResponseBody def updateGt(session: HttpSession, request: HttpServletRequest): Map[String, AnyRef] = {
    val mchtId: String = request.getParameter("mchtId")
    val articles: String = request.getParameter("articles")
    val media: String = request.getParameter("media")
    val name: String = request.getParameter("name")
    val dsc: String = request.getParameter("dsc")
    val s: Array[Array[String]] = Array(Array(mchtId, "15", "15"), Array(articles, "1", null), Array(name, "1", "30"), Array(dsc, "0", "64"), Array(media, "1", "100"))
    if (!Validate4J.checkStrArrLen(s)) {
      AjaxResp.getReturn(Constant.ERROR_CODE, "参数长度或格式不正确", "")
    }
    if (wechatFodderService.getById(media) == null) {
      AjaxResp.getReturn(Constant.ERROR_CODE, "图文不存在", "")
    }
    var respMsg: String = null
    try {
      respMsg = wechatMediaService.updateGtMedia(mchtId, name, dsc, media, articles)
      if (respMsg == null) {
        AjaxResp.getReturn(Constant.SUCCESS_CODE, "", "")
      }
      else {
        AjaxResp.getReturn(Constant.ERROR_CODE, WechatError.checkCode(respMsg), "")
      }
    }
    catch {
      case e: Exception => {
        logger.info("微信修改图文失败")
        AjaxResp.getReturn(Constant.ERROR_CODE, "微信修改图文失败,请联系管理员或稍后再试", "")
      }
    }
  }
}

