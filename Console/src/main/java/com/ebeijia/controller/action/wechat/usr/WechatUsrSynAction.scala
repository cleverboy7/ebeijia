package com.ebeijia.controller.action.wechat.usr

import java.util.Map
import javax.servlet.http.{HttpServletRequest, HttpSession}
import com.ebeijia.ajax.resp.AjaxResp
import com.ebeijia.annotation.MyLog
import com.ebeijia.entity.wechat.TblWechatSubscribe
import com.ebeijia.service.wechat.subscribe.WechatSubscribeService
import com.ebeijia.util.Constant
import com.ebeijia.util.wechat.WechatError
import org.ebeijia.tools.Validate4J
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, ResponseBody}

/**
 * WechatUsrSynAction
 * @author zhicheng.xu
 *         15/8/10
 */

@Controller
@RequestMapping(value = Array("/wechat/usr")) class WechatUsrSynAction {
  @Autowired
  private val wechatSubscribeService: WechatSubscribeService = null
  private val logger: Logger = LoggerFactory.getLogger(classOf[WechatUsrSynAction])

  @RequestMapping(value = Array("syn.html"), method = Array(RequestMethod.POST))
  @MyLog(remark = "微信用户同步")
  @ResponseBody def syn(session: HttpSession, request: HttpServletRequest): Map[String, AnyRef] = {
    try {
      val respMsg: String = wechatSubscribeService.synUsr
      if (respMsg == null) {
        AjaxResp.getReturn(Constant.SUCCESS_CODE, "", "")
      } else {
        AjaxResp.getReturn(Constant.ERROR_CODE, respMsg, "")
      }
    }
    catch {
      case e: Exception => {
        logger.info("微信用户同步失败")
        AjaxResp.getReturn(Constant.ERROR_CODE, "微信用户同步失败,请联系管理员或稍后再试", "")
      }
    }
  }

  @RequestMapping(value = Array("upRemark.html"), method = Array(RequestMethod.POST))
  @MyLog(remark = "微信用户修改备注")
  @ResponseBody def upRemark(session: HttpSession, request: HttpServletRequest): Map[String, AnyRef] = {
    val subcribe: String = request.getParameter("subcribeId")
    val remark: String = request.getParameter("remark")
    val s: Array[Array[String]] = Array(Array(subcribe, "1", "20"), Array(remark, "1", "16"))
    if (!Validate4J.checkStrArrLen(s)) {
      AjaxResp.getReturn(Constant.ERROR_CODE, "参数长度或格式不正确", "")
    }
    val data: TblWechatSubscribe = wechatSubscribeService.getById(subcribe)
    if (data == null) {
      AjaxResp.getReturn(Constant.ERROR_CODE, "该关注者不存在，请重新选择", "")
    }
    data.setRemarks(remark)
    try {
      val respMsg: String = wechatSubscribeService.upRemark(data)
      if (respMsg == null) {
        AjaxResp.getReturn(Constant.SUCCESS_CODE, "", "")
      }
      else {
        AjaxResp.getReturn(Constant.ERROR_CODE, WechatError.checkCode(respMsg), "")
      }
    }
    catch {
      case e: Exception => {
        logger.info("微信用户修改备注失败")
        AjaxResp.getReturn(Constant.ERROR_CODE, "微信用户修改备注失败,请联系管理员或稍后再试", "")
      }
    }
  }

  @RequestMapping(value = Array("mv.html"), method = Array(RequestMethod.POST))
  @MyLog(remark = "微信移动用户分组")
  @ResponseBody def mv(session: HttpSession, request: HttpServletRequest): Map[String, AnyRef] = {
    val subcribe: String = request.getParameter("subcribeId")
    val groupId: String = request.getParameter("groupId")
    val s: Array[Array[String]] = Array(Array(subcribe, "1", "20"), Array(groupId, "1", "6"))
    if (!Validate4J.checkStrArrLen(s)) {
      AjaxResp.getReturn(Constant.ERROR_CODE, "参数长度或格式不正确", "")
    }
    try {
      val data: TblWechatSubscribe = wechatSubscribeService.getById(subcribe)
      if (data == null) {
        AjaxResp.getReturn(Constant.ERROR_CODE, "该关注者不存在，请重新选择", "")
      }
      data.setGroupId(groupId)
      val respMsg: String = wechatSubscribeService.upGroup(data)
      if (respMsg == null) {
        AjaxResp.getReturn(Constant.SUCCESS_CODE, "", "")
      } else {
        AjaxResp.getReturn(Constant.ERROR_CODE, WechatError.checkCode(respMsg), "")
      }
    }
    catch {
      case e: Exception => {
        logger.info("微信移动用户分组失败")
        AjaxResp.getReturn(Constant.ERROR_CODE, "微信移动用户分组失败,请联系管理员或稍后再试", "")
      }
    }
  }

  @RequestMapping(value = Array("batchMv.html"), method = Array(RequestMethod.POST))
  @MyLog(remark = "微信移动用户分组")
  @ResponseBody def batchMv(session: HttpSession, request: HttpServletRequest): Map[String, AnyRef] = {
    val openList: String = request.getParameter("openList")
    val outList: String = request.getParameter("outList")
    val groupId: String = request.getParameter("groupId")
    val mchtId: String = request.getParameter("mchtId")
    val s: Array[Array[String]] = Array(Array(mchtId, "15", "15"), Array(groupId, "1", "6"))
    if (!Validate4J.checkStrArrLen(s)) {
      AjaxResp.getReturn(Constant.ERROR_CODE, "参数长度或格式不正确", "")
    }
    try {
      var respMsg: String = null
      var respMsg2: String = null
      if (openList != null) {
        respMsg = wechatSubscribeService.batchUpGroup(mchtId, openList, groupId)
      }
      if (outList != null) {
        respMsg2 = wechatSubscribeService.batchUpGroup(mchtId, outList, "0")
      }
      if (respMsg == null && respMsg2 == null) {
        AjaxResp.getReturn(Constant.SUCCESS_CODE, "", "")
      } else {
        if (respMsg == null) {
          AjaxResp.getReturn(Constant.ERROR_CODE, WechatError.checkCode(respMsg2), "")
        }
        else {
          AjaxResp.getReturn(Constant.ERROR_CODE, WechatError.checkCode(respMsg), "")
        }
      }
    }
    catch {
      case e: Exception => {
        logger.info("微信批量移动用户分组失败")
        AjaxResp.getReturn(Constant.ERROR_CODE, "微信批量移动用户分组失败,请联系管理员或稍后再试", "")
      }
    }
  }
}

