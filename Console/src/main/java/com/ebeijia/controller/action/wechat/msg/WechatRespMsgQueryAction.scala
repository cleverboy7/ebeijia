package com.ebeijia.controller.action.wechat.msg

import java.util.{HashMap, Map}
import javax.servlet.http.{HttpServletRequest, HttpSession}

import com.ebeijia.ajax.resp.AjaxResp
import com.ebeijia.service.wechat.msg.WechatRespMsgService
import com.ebeijia.util.Constant
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, ResponseBody}

/**
 * WechatRespMsgQueryAction
 * @author zhicheng.xu
 *         15/8/10
 */

@Controller
@RequestMapping(value = Array("/wechat/respMsg"))
class WechatRespMsgQueryAction {
  @Autowired
  private val wechatRespMsgService: WechatRespMsgService = null
  private val logger: Logger = LoggerFactory.getLogger(classOf[WechatRespMsgQueryAction])

  @RequestMapping(value = Array("query.html"), method = Array(RequestMethod.POST))
  @ResponseBody def query(session: HttpSession, request: HttpServletRequest): Map[String, AnyRef] = {
    val mchtId: String = request.getParameter("mchtId")
    val pageData: String = request.getParameter("aoData")
    val map: Map[String, AnyRef] = new HashMap[String, AnyRef]
    try {
      val mapTmp: Map[String, AnyRef] = wechatRespMsgService.findBySql(mchtId, pageData)
      map.put("respMsgList", mapTmp.get("list"))
      map.put("current", mapTmp.get("current"))
      map.put("total", mapTmp.get("total"))
      AjaxResp.getReturn(Constant.SUCCESS_CODE, "", map)
    }
    catch {
      case e: Exception => {
        logger.info("微信自动回复查询失败,请联系管理员或稍后再试")
        AjaxResp.getReturn(Constant.ERROR_CODE, "微信自动回复查询失败,请联系管理员或稍后再试", "")
      }
    }
  }
}
