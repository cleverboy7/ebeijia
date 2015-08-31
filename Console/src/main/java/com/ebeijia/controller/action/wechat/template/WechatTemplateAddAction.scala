package com.ebeijia.controller.action.wechat.template

import java.util.{HashMap, Map}
import javax.servlet.http.{HttpServletRequest, HttpSession}

import com.ebeijia.ajax.resp.AjaxResp
import com.ebeijia.annotation.MyLog
import com.ebeijia.service.wechat.template.WechatTemplateService
import com.ebeijia.util.Constant
import com.ebeijia.util.wechat.WechatError
import org.ebeijia.tools.Validate4J
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, ResponseBody}

/**
 * WechatTemplateAddAction
 * @author zhicheng.xu
 *         15/8/10
 */


@Controller
@RequestMapping(value = Array("/wechat/template"))
class WechatTemplateAddAction {
  @Autowired
  private val wechatTemplateService: WechatTemplateService = null
  private val logger: Logger = LoggerFactory.getLogger(classOf[WechatTemplateAddAction])

  @RequestMapping(value = Array("add.html"), method = Array(RequestMethod.POST))
  @MyLog(remark = "设置行业模板")
  @ResponseBody def add(session: HttpSession, request: HttpServletRequest): Map[String, AnyRef] = {
    val mchtId: String = request.getParameter("mchtId")
    val id1: String = request.getParameter("id1")
    val id2: String = request.getParameter("id2")
    val s: Array[Array[String]] = Array(Array(mchtId, "15", "15"), Array(id1, "1", "6"), Array(id2, "0", "6"))
    if (!Validate4J.checkStrArrLen(s)) {
      AjaxResp.getReturn(Constant.ERROR_CODE, "参数长度或格式不正确", "")
    }
    try {
      val respMsg: String = wechatTemplateService.set(mchtId, id1, id2)
      if (respMsg == null) {
        AjaxResp.getReturn(Constant.SUCCESS_CODE, "", "")
      }
      else {
        AjaxResp.getReturn(Constant.ERROR_CODE, WechatError.checkCode(respMsg), "")
      }
    }
    catch {
      case e: Exception => {
        logger.info("设置行业模板失败")
        AjaxResp.getReturn(Constant.ERROR_CODE, "设置行业模板失败,请联系管理员或稍后再试", "")
      }
    }
  }
}

