package com.ebeijia.controller.action.wechat.usr
import java.util.HashMap
import java.util.List
import java.util.Map
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession
import org.ebeijia.tools.Validate4J
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import com.ebeijia.ajax.resp.AjaxResp
import com.ebeijia.entity.wechat.TblWechatSubscribe
import com.ebeijia.service.wechat.subscribe.WechatSubscribeService
import com.ebeijia.util.Constant

/**
 * WechatUsrQueryAction
 * @author zhicheng.xu
 *         15/8/10
 */



@Controller
@RequestMapping(value = Array("/wechat/usr"))
class WechatUsrQueryAction {
  @Autowired
  private val wechatSubscribeService: WechatSubscribeService = null
  private val logger: Logger = LoggerFactory.getLogger(classOf[WechatUsrQueryAction])

  @RequestMapping(value = Array("query.html"), method = Array(RequestMethod.POST))
  @ResponseBody def query(session: HttpSession, request: HttpServletRequest): Map[String, AnyRef] = {
    val mchtId: String = request.getParameter("mchtId")
    val openid: String = request.getParameter("openid")
    val pageData: String = request.getParameter("aoData")
    val map: Map[String, AnyRef] = new HashMap[String, AnyRef]
    try {
      val mapTmp: Map[String, AnyRef] = wechatSubscribeService.findBySql(mchtId, openid, pageData)
      map.put("usrList", mapTmp.get("list"))
      map.put("current", mapTmp.get("current"))
      map.put("total", mapTmp.get("total"))
      AjaxResp.getReturn(Constant.SUCCESS_CODE, "", map)
    }
    catch {
      case e: Exception => {
        logger.info("微信用户查询失败,请联系管理员或稍后再试")
        AjaxResp.getReturn(Constant.ERROR_CODE, "微信用户查询失败,请联系管理员或稍后再试", "")
      }
    }
  }

  @RequestMapping(value = Array("queryBatch.html"), method = Array(RequestMethod.POST))
  @ResponseBody def queryByBatch(session: HttpSession, request: HttpServletRequest): Map[String, AnyRef] = {
    val mchtId: String = request.getParameter("mchtId")
    val groupId: String = request.getParameter("groupId")
    val pageData: String = request.getParameter("aoData")
    val map: Map[String, AnyRef] = new HashMap[String, AnyRef]
    val s: Array[Array[String]] = Array(Array(mchtId, "15", "15"), Array(groupId, "1", "6"))
    if (!Validate4J.checkStrArrLen(s)) {
      AjaxResp.getReturn(Constant.ERROR_CODE, "参数长度或格式不正确", "")
    }
    try {
      val mapTmp: Map[String, AnyRef] = wechatSubscribeService.findByBatch(mchtId, groupId, pageData)
      map.put("usrList", mapTmp.get("list"))
      map.put("current", mapTmp.get("current"))
      map.put("total", mapTmp.get("total"))
      val result: List[TblWechatSubscribe] = wechatSubscribeService.findByGroup(mchtId, groupId)
      map.put("usrInList", result)
      AjaxResp.getReturn(Constant.SUCCESS_CODE, "", map)
    }
    catch {
      case e: Exception => {
        logger.info("微信用户查询失败,请联系管理员或稍后再试")
        AjaxResp.getReturn(Constant.ERROR_CODE, "微信用户查询失败,请联系管理员或稍后再试", "")
      }
    }
  }
}

