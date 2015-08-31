package com.ebeijia.controller.action.wechat.act

import java.util.HashMap
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
import com.ebeijia.annotation.MyLog
import com.ebeijia.entity.wechat.TblWechatAct
import com.ebeijia.service.wechat.act.WechatActService
import com.ebeijia.util.Constant
/**
 * WechatActAddAction
 * @author zhicheng.xu
 *         15/8/10
 */


@Controller
@RequestMapping(value = Array("/wechat/act")) class WechatActAddAction {
  @Autowired
  private val wechatActService: WechatActService = null
  private val logger: Logger = LoggerFactory.getLogger(classOf[WechatActAddAction])

  @RequestMapping(value = Array("add.html"), method = Array(RequestMethod.POST))
  @MyLog(remark = "新增活动")
  @ResponseBody def add(session: HttpSession, request: HttpServletRequest): Map[String, AnyRef] = {
    val data: TblWechatAct = this.build(request)
    if (data == null) {
      AjaxResp.getReturn(Constant.ERROR_CODE, "参数长度或格式不正确", "")
    }
    try {
      val respMsg: String = wechatActService.save(data)
      if (respMsg == null) {
        AjaxResp.getReturn(Constant.SUCCESS_CODE, "", "")
      }
      else {
        AjaxResp.getReturn(Constant.ERROR_CODE, "新增活动失败,请联系管理员或稍后再试", "")
      }
    }
    catch {
      case e: Exception => {
        logger.info("新增活动失败")
        AjaxResp.getReturn(Constant.ERROR_CODE, "新增活动失败,请联系管理员或稍后再试", "")
      }
    }
  }

  private def build(request: HttpServletRequest): TblWechatAct = {
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
    val s: Array[Array[String]] = Array(Array(mchtId, "15", "15"), Array(actName, "1", "32"), Array(dsc, "0", "128"), Array(actType, "1", "1"), Array(modId, "1", "20"), Array(dayFlag, "1", "1"), Array(lotNum, "1", "2"), Array(beginDate, "8", "8"), Array(endDate, "8", "8"), Array(url, "1", "256"))
    if (!Validate4J.checkStrArrLen(s)) {
      return null
    }
    val data: TblWechatAct = new TblWechatAct
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
    data.setStatus("0")
    data
  }
}

