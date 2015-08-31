package com.ebeijia.controller.action.wechat.media

import java.util.{HashMap, Map}
import javax.servlet.http.{HttpServletRequest, HttpSession}
import com.ebeijia.ajax.resp.AjaxResp
import com.ebeijia.annotation.MyLog
import com.ebeijia.service.wechat.media.{WechatFodderService, WechatMediaService}
import com.ebeijia.util.wechat.WechatError
import com.ebeijia.util.{Constant, PatternUtil}
import org.ebeijia.tools.Validate4J
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, ResponseBody}

/**
 * WechatMediaQuery
 * @author zhicheng.xu
 *         15/8/10
 */


@Controller
@RequestMapping(value = Array("/wechat/media"))
class WechatMediaQuery {
  @Autowired
  private val wechatFodderService: WechatFodderService = null
  @Autowired
  private val wechatMediaService: WechatMediaService = null
  private val logger: Logger = LoggerFactory.getLogger(classOf[WechatMediaQuery])

  @RequestMapping(value = Array("query.html"), method = Array(RequestMethod.POST))
  @ResponseBody def query(session: HttpSession, request: HttpServletRequest): Map[String, AnyRef] = {
    val mchtId: String = request.getParameter("mchtId")
    val `type`: String = request.getParameter("type")
    val mediaType: String = request.getParameter("mediaType")
    val pageData: String = request.getParameter("aoData")
    val map: Map[String, AnyRef] = new HashMap[String, AnyRef]
    try {
      val mapTmp: Map[String, AnyRef] = wechatFodderService.findBySql(mchtId, `type`, mediaType, pageData)
      map.put("mediaList", mapTmp.get("list"))
      map.put("current", mapTmp.get("current"))
      map.put("total", mapTmp.get("total"))
      AjaxResp.getReturn(Constant.SUCCESS_CODE, "", map)

    }
    catch {
      case e: Exception => {
        logger.info("微信多媒体查询失败")
        AjaxResp.getReturn(Constant.ERROR_CODE, "微信多媒体查询失败,请联系管理员或稍后再试", "")

      }
    }
  }

  @RequestMapping(value = Array("mediaAll.html"), method = Array(RequestMethod.POST))
  @MyLog(remark = "查询素材总数")
  @ResponseBody def mediaAll(session: HttpSession, request: HttpServletRequest): Map[_, _] = {
    val mchtId: String = request.getParameter("mchtId")
    var map: Map[_,_] = new HashMap
    val s: Array[Array[String]] = Array(Array(mchtId, "15", "15"))
    if (!Validate4J.checkStrArrLen(s)) {
      AjaxResp.getReturn(Constant.ERROR_CODE, "商户号格式不正确", "")
    }
    try {
      map = wechatMediaService.mediaAllCount(mchtId)
      AjaxResp.getReturn(Constant.SUCCESS_CODE, "", map)

    }
    catch {
      case e: Exception => {
        logger.info("查询素材总数失败")
        AjaxResp.getReturn(Constant.ERROR_CODE, "查询素材总数失败", "")
      }
    }
  }

  @RequestMapping(value = Array("newsGet.html"), method = Array(RequestMethod.POST))
  @MyLog(remark = "微信图文查询")
  @ResponseBody def newsGet(session: HttpSession, request: HttpServletRequest): Map[String, AnyRef] = {
    val media: String = request.getParameter("media")
    val mchtId: String = request.getParameter("mchtId")
    val s: Array[Array[String]] = Array(Array(mchtId, "15", "15"), Array(media, "1", "100"))
    if (!Validate4J.checkStrArrLen(s)) {
      AjaxResp.getReturn(Constant.ERROR_CODE, "参数长度或格式不正确", "")

    }
    val respMsg: String = wechatMediaService.newsGet(mchtId, media)
    if (respMsg != null) {
      if (!PatternUtil.NUMBER_REGEX.matcher(respMsg).matches) {
        AjaxResp.getReturn(Constant.SUCCESS_CODE, "", respMsg)
      }
      else {
        AjaxResp.getReturn(Constant.ERROR_CODE, WechatError.checkCode(respMsg), "")
      }
    }
    else {
      AjaxResp.getReturn(Constant.ERROR_CODE, WechatError.checkCode(respMsg), "")
    }
  }

  @RequestMapping(value = Array("newsQuery.html"), method = Array(RequestMethod.POST))
  @ResponseBody def newsQuery(session: HttpSession, request: HttpServletRequest): Map[String, AnyRef] = {
    val mchtId: String = request.getParameter("mchtId")
    val pageData: String = request.getParameter("aoData")
    val map: Map[String, AnyRef] = new HashMap[String, AnyRef]
    try {
      val mapTmp: Map[String, AnyRef] = wechatFodderService.findBySqltoNews(mchtId, pageData)
      map.put("newsList", mapTmp.get("list"))
      map.put("current", mapTmp.get("current"))
      map.put("total", mapTmp.get("total"))
      AjaxResp.getReturn(Constant.SUCCESS_CODE, "", map)
    }
    catch {
      case e: Exception => {
        logger.info("微信图文查询失败,请联系管理员或稍后再试")
        AjaxResp.getReturn(Constant.ERROR_CODE, "微信图文查询失败,请联系管理员或稍后再试", "")
      }
    }
  }
}

