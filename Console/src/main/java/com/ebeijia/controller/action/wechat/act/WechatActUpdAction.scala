package com.ebeijia.controller.action.wechat.act

import java.util.Map
import javax.servlet.http.{HttpServletRequest, HttpSession}

import com.ebeijia.ajax.resp.AjaxResp
import com.ebeijia.annotation.MyLog
import com.ebeijia.entity.wechat.TblWechatAct
import com.ebeijia.service.wechat.act.WechatActService
import com.ebeijia.util.Constant
import org.ebeijia.tools.Validate4J
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, ResponseBody}

/**
 * WechatActUpdAction
 * @author zhicheng.xu
 *         15/8/10
 */

@Controller
@RequestMapping(value = Array("/wechat/act"))
class WechatActUpdAction {
  @Autowired
  private val wechatActService: WechatActService = null
  private val logger: Logger = LoggerFactory.getLogger(classOf[WechatActUpdAction])

  @RequestMapping(value = Array("upd.html"), method = Array(RequestMethod.POST))
  @MyLog(remark = "修改活动")
  @ResponseBody def upd(session: HttpSession, request: HttpServletRequest): Map[String, AnyRef] = {
    val status: String = request.getParameter("status")
    if (!("0" == status)) {
      AjaxResp.getReturn(Constant.ERROR_CODE, "未开启的活动才能修改", "")
    }
    val data: TblWechatAct = this.build(request)
    if (data == null) {
      AjaxResp.getReturn(Constant.ERROR_CODE, "参数长度或格式不正确", "")
    }
    try {
      if (wechatActService.getById(data.getActId) == null) {
        AjaxResp.getReturn(Constant.ERROR_CODE, "活动不存在", "")
      }
      val respMsg: String = wechatActService.upd(data)
      if (respMsg == null) {
        AjaxResp.getReturn(Constant.SUCCESS_CODE, "", "")
      }
      else {
        AjaxResp.getReturn(Constant.ERROR_CODE, "修改活动失败,请联系管理员或稍后再试", "")
      }
    }
    catch {
      case e: Exception => {
        logger.info("修改活动失败")
        AjaxResp.getReturn(Constant.ERROR_CODE, "修改活动失败,请联系管理员或稍后再试", "")
      }
    }
  }

  @RequestMapping(value = Array("del.html"), method = Array(RequestMethod.POST))
  @MyLog(remark = "删除活动")
  @ResponseBody
  def del(session: HttpSession, request: HttpServletRequest): Map[String, AnyRef] = {
    val actId: String = request.getParameter("actId")
    val s: Array[Array[String]] = Array(Array(actId, "1", "20"))
    if (!Validate4J.checkStrArrLen(s)) {
      AjaxResp.getReturn(Constant.ERROR_CODE, "参数长度或格式不正确", "")
    }
    try {
      val data: TblWechatAct = wechatActService.getById(actId)
      if (data == null) {
        AjaxResp.getReturn(Constant.ERROR_CODE, "活动不存在", "")
      }
      if ("1" == (data.getStatus)) {
        AjaxResp.getReturn(Constant.ERROR_CODE, "活动正在运行,不能删除", "")
      }
      val respMsg: String = wechatActService.del(actId)
      if (respMsg == null) {
        AjaxResp.getReturn(Constant.SUCCESS_CODE, "", "")
      }
      else {
        AjaxResp.getReturn(Constant.ERROR_CODE, "删除活动失败,请联系管理员或稍后再试", "")
      }
    }
    catch {
      case e: Exception => {
        logger.info("删除活动失败")
        AjaxResp.getReturn(Constant.ERROR_CODE, "删除活动失败,请联系管理员或稍后再试", "")
      }
    }
  }

  @RequestMapping(value = Array("sta.html"), method = Array(RequestMethod.POST))
  @MyLog(remark = "开启关闭活动")
  @ResponseBody
  def sta(session: HttpSession, request: HttpServletRequest): Map[String, AnyRef] = {
    val actId: String = request.getParameter("actId")
    val status: String = request.getParameter("status")
    val s: Array[Array[String]] = Array(Array(actId, "1", "20"), Array(status, "1", "1"))
    if (!Validate4J.checkStrArrLen(s)) {
      AjaxResp.getReturn(Constant.ERROR_CODE, "参数长度或格式不正确", "")
    }
    try {
      val data: TblWechatAct = wechatActService.getById(actId)
      if (data == null) {
        AjaxResp.getReturn(Constant.ERROR_CODE, "活动不存在", "")
      }
      if ("0" == status) {
        data.setStatus("1")
      }
      else {
        data.setStatus("0")
      }
      val respMsg: String = wechatActService.upd(data)
      if (respMsg == null) {
        AjaxResp.getReturn(Constant.SUCCESS_CODE, "", "")
      }
      else {
        AjaxResp.getReturn(Constant.ERROR_CODE, "开启关闭活动失败,请联系管理员或稍后再试", "")
      }
    }
    catch {
      case e: Exception => {
        logger.info("开启关闭活动失败")
        AjaxResp.getReturn(Constant.ERROR_CODE, "开启关闭活动失败,请联系管理员或稍后再试", "")
      }
    }
  }

  private def build(request: HttpServletRequest): TblWechatAct = {
    val actId: String = request.getParameter("actId")
    val mchtId: String = request.getParameter("mchtId")
    val actName: String = request.getParameter("actName")
    val dsc: String = request.getParameter("dsc")
    val actType: String = request.getParameter("actType")
    val modId: String = request.getParameter("modId")
    val dayFlag: String = request.getParameter("dayFlag")
    val lotNum: String = request.getParameter("lotNum")
    val beginDate: String = request.getParameter("beginDate")
    val endDate: String = request.getParameter("endDate")
    val url: String = request.getParameter("url")
    val status: String = request.getParameter("status")
    val s: Array[Array[String]] = Array(Array(actId, "1", "20"), Array(status, "1", "1"), Array(mchtId, "15", "15"), Array(actName, "1", "32"), Array(dsc, "0", "128"), Array(actType, "1", "1"), Array(modId, "1", "20"), Array(dayFlag, "1", "1"), Array(lotNum, "1", "2"), Array(beginDate, "8", "8"), Array(endDate, "8", "8"), Array(url, "1", "256"))
    if (!Validate4J.checkStrArrLen(s)) {
      return null
    }
    val data: TblWechatAct = new TblWechatAct
    data.setActId(actId)
    data.setActName(actName)
    data.setActType(actType)
    data.setBeginDate(beginDate)
    data.setDayFlag(dayFlag)
    data.setDsc(dsc)
    data.setEndDate(endDate)
    data.setLotNum(lotNum.toInt)
    data.setMchtId(mchtId)
    data.setModId(modId)
    data.setUrl(url)
    data.setStatus(status)
    data
  }
}
