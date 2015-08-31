package com.ebeijia.controller.action.wechat.act

import java.util.Map
import javax.servlet.http.HttpServletRequest
import com.ebeijia.ajax.resp.AjaxResp
import com.ebeijia.annotation.MyLog
import com.ebeijia.service.wechat.act.WechatModService
import com.ebeijia.util.Constant
import org.ebeijia.tools.Validate4J
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, ResponseBody}

/**
 * WechatQueryAction
 * @author zhicheng.xu
 *         15/8/10
 */


@Controller
@RequestMapping(value = Array("/wechat/act"))
class WechatActLuckydrawQueryAction {
  @Autowired
  private val wechatModService: WechatModService = null
  private val logger: Logger = LoggerFactory.getLogger(classOf[WechatActLuckydrawQueryAction])

  @RequestMapping(value = Array("luckydrawAdd.html"), method = Array(RequestMethod.POST))
  @MyLog(remark = "新增大转盘活动")
  @ResponseBody def add(request: HttpServletRequest): Map[String, AnyRef] = {
    val mchtId: String = request.getParameter("mchtId")
    val modName: String = request.getParameter("modName")
    val prizeJson: String = request.getParameter("prizeJson")
    val s: Array[Array[String]] = Array(Array(mchtId, "15", "15"), Array(modName, "1", "64"), Array(prizeJson, "1", "0"))
    if (!Validate4J.checkStrArrLen(s)) {
      AjaxResp.getReturn(Constant.ERROR_CODE, "参数长度或格式不正确", "")
    }
    try {
      val respMsg: String = wechatModService.save(mchtId, modName, prizeJson, "0")
      if (respMsg == null) {
        AjaxResp.getReturn(Constant.SUCCESS_CODE, "", "")
      }
      else {
        AjaxResp.getReturn(Constant.ERROR_CODE, "新增大转盘活动失败,请联系管理员或稍后再试", "")
      }
    }
    catch {
      case e: Exception => {
        logger.info("新增大转盘活动失败")
        AjaxResp.getReturn(Constant.ERROR_CODE, "新增大转盘活动失败,请联系管理员或稍后再试", "")
      }
    }
  }
}

