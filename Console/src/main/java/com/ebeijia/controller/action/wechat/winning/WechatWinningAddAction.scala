package com.ebeijia.controller.action.wechat.winning

import java.util.{HashMap, Map}
import javax.servlet.http.{HttpServletRequest, HttpSession}
import com.ebeijia.ajax.resp.AjaxResp
import com.ebeijia.annotation.MyLog
import com.ebeijia.service.wechat.win.WechatWinService
import com.ebeijia.util.Constant
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, ResponseBody}

/**
 * WechatWinningAddAction
 * @author zhicheng.xu
 *         15/8/10
 */


@Controller
@RequestMapping(value = Array("/wechat/win"))
class WechatWinningAddAction {

  @Autowired
  private val wechatWinService: WechatWinService = null
  private val logger: Logger = LoggerFactory.getLogger(classOf[WechatWinningAddAction])

  @RequestMapping(value = Array("add.html"), method = Array(RequestMethod.POST))
  @MyLog(remark = "微信抽奖")
  @ResponseBody def add(session: HttpSession, request: HttpServletRequest): Map[String, AnyRef] = {
    val openid: String = request.getParameter("openid")
    val appid: String = request.getParameter("appid")
    val actId: String = request.getParameter("actId")
    val `type`: String = request.getParameter("type")
    var map: Map[String, AnyRef] = new HashMap[String, AnyRef]
    val checkMsg: String = wechatWinService.check(openid, appid, actId, `type`)
    if (checkMsg != null) {
      AjaxResp.getReturn(Constant.ERROR_CODE, checkMsg, "")
    }
    try {
      val respMsg: String = wechatWinService.calculation(openid, appid, actId, `type`)
      if (respMsg != null) {
        AjaxResp.getReturn(Constant.SUCCESS_CODE, respMsg, "")
      }
      else {
        AjaxResp.getReturn(Constant.ERROR_CODE, "微信抽奖失败,请联系管理员或稍后再试", "")
      }
    }
    catch {
      case e: Exception => {
        logger.info("微信抽奖失败")
        AjaxResp.getReturn(Constant.ERROR_CODE, "微信抽奖失败,请联系管理员或稍后再试", "")
      }
    }
  }
}

