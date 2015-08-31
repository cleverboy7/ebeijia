package com.ebeijia.controller.action.wechat.mcht

import java.util.Map
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
 * WechatMchtInfAddAction
 * @author zhicheng.xu
 *         15/8/10
 */


@Controller
@RequestMapping(value = Array("/wechat/mcht"))
class WechatMchtInfAddAction {
  @Autowired
  private val wechatMchtInfService: WechatMchtInfService = null
  private val logger: Logger = LoggerFactory.getLogger(classOf[WechatMchtInfAddAction])

  @RequestMapping(value = Array("add.html"), method = Array(RequestMethod.POST))
  @MyLog(remark = "绑定微信账户")
  @ResponseBody def add(session: HttpSession, request: HttpServletRequest): Map[String, AnyRef] = {
    val tblWechatMchtInf: TblWechatMchtInf = this.build(request)
    if (tblWechatMchtInf == null) {
      AjaxResp.getReturn(Constant.ERROR_CODE, "参数长度或格式不正确", "")
    }
    try {
      val respMsg: String = wechatMchtInfService.addWechatMchtInf(tblWechatMchtInf)
      if ("0" == respMsg) {
        AjaxResp.getReturn(Constant.ERROR_CODE, "该账户已经绑定", "")
      }
      AjaxResp.getReturn(Constant.SUCCESS_CODE, "", "")
    }
    catch {
      case e: Exception => {
        logger.info("绑定失败,请联系管理员或稍后再试")
        AjaxResp.getReturn(Constant.ERROR_CODE, "绑定失败,请联系管理员或稍后再试", "")
      }
    }
  }

  private def build(request: HttpServletRequest): TblWechatMchtInf = {
    val mchtId: String = request.getParameter("mchtId")
    val url: String = request.getParameter("url")
    val token: String = request.getParameter("token")
    val appid: String = request.getParameter("appid")
    val appsecret: String = request.getParameter("appsecret")
    val wechatType: String = request.getParameter("wechatType")
    val nickName: String = request.getParameter("nickName")
    val s: Array[Array[String]] = Array(Array(mchtId, "15", "15"), Array(url, "1", "256"), Array(token, "1", "64"), Array(appid, "1", "64"), Array(appsecret, "1", "64"), Array(wechatType, "1", "1"), Array(nickName, "1", "256"))
    if (!Validate4J.checkStrArrLen(s)) {
      return null
    }
    val tblWechatMchtInf: TblWechatMchtInf = new TblWechatMchtInf
    tblWechatMchtInf.setMchtId(mchtId)
    tblWechatMchtInf.setUrl(url)
    tblWechatMchtInf.setToken(token)
    tblWechatMchtInf.setAppid(appid)
    tblWechatMchtInf.setAppsecret(appsecret)
    tblWechatMchtInf.setWechatType(wechatType)
    tblWechatMchtInf.setNickname(nickName)
    tblWechatMchtInf.setCreateTime(DateTime4J.getCurrentDateTime)
    tblWechatMchtInf
  }
}

