package com.ebeijia.controller.action.wechat.redpachet

import java.util.{HashMap, Map}
import javax.servlet.http.{HttpServletRequest, HttpSession}

import com.ebeijia.ajax.resp.AjaxResp
import com.ebeijia.service.wechat.act.WechatRedpacketService
import com.ebeijia.util.Constant
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, ResponseBody}

/**
 * WechatRedpacketQueryAction
 * @author zhicheng.xu
 *         15/8/10
 */

@Controller
@RequestMapping(value = Array("/wechat/redpacket")) class WechatRedpacketQueryAction {
  @Autowired
  private val wechatRedpacketService: WechatRedpacketService = null
  private val logger: Logger = LoggerFactory.getLogger(classOf[WechatRedpacketQueryAction])

  @RequestMapping(value = Array("query.html"), method = Array(RequestMethod.POST))
  @ResponseBody def query(session: HttpSession, request: HttpServletRequest): Map[String, AnyRef] = {
    val mchtId: String = request.getParameter("mchtId")
    val pageData: String = request.getParameter("aoData")
    val map: Map[String, AnyRef] = new HashMap[String, AnyRef]
    try {
      val mapTmp: Map[String, AnyRef] = wechatRedpacketService.findBySql(mchtId, pageData)
      map.put("redpacketList", mapTmp.get("list"))
      map.put("current", mapTmp.get("current"))
      map.put("total", mapTmp.get("total"))
      AjaxResp.getReturn(Constant.SUCCESS_CODE, "", map)
    }
    catch {
      case e: Exception => {
        logger.info("微信红包查询失败")
        AjaxResp.getReturn(Constant.ERROR_CODE, "微信红包查询失败,请联系管理员或稍后再试", "")
      }
    }
  }
}

