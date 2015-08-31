package com.ebeijia.controller.action.wechat.msg
import java.util.HashMap
import java.util.Map
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import com.ebeijia.ajax.resp.AjaxResp
import com.ebeijia.service.wechat.msg.WechatReqMsgService
import com.ebeijia.util.Constant
/**
 * WechatReqMsgQueryAction
 * @author zhicheng.xu
 *         15/8/10
 */

@Controller
@RequestMapping(value = Array("/wechat/reqMsg"))
class WechatReqMsgQueryAction {
  @Autowired
  private val wechatReqMsgService: WechatReqMsgService = null
  private val logger: Logger = LoggerFactory.getLogger(classOf[WechatReqMsgQueryAction])

  @RequestMapping(value = Array("query.html"), method = Array(RequestMethod.POST))
  @ResponseBody def query(session: HttpSession, request: HttpServletRequest): Map[String, AnyRef] = {
    val mchtId: String = request.getParameter("mchtId")
    val pageData: String = request.getParameter("aoData")
    val map: Map[String, AnyRef] = new HashMap[String, AnyRef]
    try {
      val mapTmp: Map[String, AnyRef] = wechatReqMsgService.findBySql(mchtId, pageData)
      map.put("reqMsgList", mapTmp.get("list"))
      map.put("current", mapTmp.get("current"))
      map.put("total", mapTmp.get("total"))
      AjaxResp.getReturn(Constant.SUCCESS_CODE, "", map)
    }
    catch {
      case e: Exception => {
        logger.info("微信收到消息查询失败,请联系管理员或稍后再试")
        AjaxResp.getReturn(Constant.ERROR_CODE, "微信收到消息查询失败,请联系管理员或稍后再试", "")
      }
    }
  }
}
