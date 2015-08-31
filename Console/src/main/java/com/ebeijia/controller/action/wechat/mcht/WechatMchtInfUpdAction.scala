package com.ebeijia.controller.action.wechat.mcht

import java.util.{List, Map}
import javax.servlet.http.{HttpServletRequest, HttpSession}

import com.ebeijia.ajax.resp.AjaxResp
import com.ebeijia.annotation.MyLog
import com.ebeijia.entity.wechat.TblWechatMchtInf
import com.ebeijia.service.wechat.core.WechatMchtInfService
import com.ebeijia.util.Constant
import org.ebeijia.tools.{DateTime4J, Validate4J}
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, ResponseBody}

/**
 * WechatMchtInfUpdAction
 * @author zhicheng.xu
 *         15/8/10
 */


@Controller
@RequestMapping(value = Array("/wechat/mcht"))
class WechatMchtInfUpdAction {
  @Autowired
  private val wechatMchtInfService: WechatMchtInfService = null
  private val logger: Logger = LoggerFactory.getLogger(classOf[WechatMchtInfUpdAction])

  @RequestMapping(value = Array("upd.html"), method = Array(RequestMethod.POST))
  @MyLog(remark = "微信账号修改")
  @ResponseBody def upd(session: HttpSession, request: HttpServletRequest): Map[String, AnyRef] = {
    val tblWechatMchtInf: TblWechatMchtInf = this.build(request)
    if (tblWechatMchtInf == null) {
      AjaxResp.getReturn(Constant.ERROR_CODE, "参数长度或格式不正确", "")

    }
    try {
      if (wechatMchtInfService.getById(tblWechatMchtInf.getMchtId) == null) {
        AjaxResp.getReturn(Constant.ERROR_CODE, "微信账号不存在", "")

      }
      wechatMchtInfService.updateWechatMchtInf(tblWechatMchtInf)
      AjaxResp.getReturn(Constant.SUCCESS_CODE, "", "")

    }
    catch {
      case e: Exception => {
        logger.info("微信账户修改失败,请联系管理员或稍后再试")
        AjaxResp.getReturn(Constant.ERROR_CODE, "微信账户修改失败,请联系管理员或稍后再试", "")

      }
    }
  }

  @RequestMapping(value = Array("del.html"), method = Array(RequestMethod.POST))
  @MyLog(remark = "微信账户删除")
  @ResponseBody def del(session: HttpSession, request: HttpServletRequest): Map[String, AnyRef] = {
    val mchtId: String = request.getParameter("mchtId")
    try {
      val tblWechatMchtInf: TblWechatMchtInf = wechatMchtInfService.getById(mchtId)
      if (tblWechatMchtInf == null) {
        AjaxResp.getReturn(Constant.ERROR_CODE, "没有找到该商户的微信信息", "")

      }
      else {
        wechatMchtInfService.deleteWechatMchtInf(tblWechatMchtInf)
        AjaxResp.getReturn(Constant.ERROR_CODE, "微信账号不存在", "")

      }
    }
    catch {
      case e: Exception => {
        logger.info("微信账户删除失败,请联系管理员或稍后再试")
        AjaxResp.getReturn(Constant.ERROR_CODE, "微信账户删除失败,请联系管理员或稍后再试", "")
      }
    }
  }

  private def build(request: HttpServletRequest): TblWechatMchtInf = {
    val mchtId: String = request.getParameter("mchtId")
    val url: String = request.getParameter("url")
    val token: String = request.getParameter("token")
    val appid: String = request.getParameter("appid")
    val appsecret: String = request.getParameter("appsecret")
    val nickName: String = request.getParameter("nickName")
    val wechatType: String = request.getParameter("wechatType")
    val s: Array[Array[String]] = Array(Array(mchtId, "15", "15"), Array(url, "1", "256"), Array(token, "1", "64"), Array(appid, "1", "64"), Array(appsecret, "1", "64"), Array(wechatType, "1", "1"), Array(nickName, "1", "256"))
    if (!Validate4J.checkStrArrLen(s)) {
      return null
    }
    val tblWechatMchtInfs: List[TblWechatMchtInf] = wechatMchtInfService.queryWechatMchtInfList
    var tblWechatMchtInf: TblWechatMchtInf = new TblWechatMchtInf
    if (tblWechatMchtInfs.isEmpty) {
    }
    else {
      tblWechatMchtInf = tblWechatMchtInfs.get(0)
      tblWechatMchtInf.setMchtId(mchtId)
      tblWechatMchtInf.setUrl(url)
      tblWechatMchtInf.setToken(token)
      tblWechatMchtInf.setAppid(appid)
      tblWechatMchtInf.setAppsecret(appsecret)
      tblWechatMchtInf.setNickname(nickName)
      tblWechatMchtInf.setWechatType(wechatType)
      tblWechatMchtInf.setUpdateTime(DateTime4J.getCurrentDateTime)
    }
    tblWechatMchtInf
  }
}