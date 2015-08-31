package com.ebeijia.controller.action.wechat.menu

import java.util.{HashMap, Map}
import javax.servlet.http.{HttpServletRequest, HttpSession}

import com.ebeijia.ajax.resp.AjaxResp
import com.ebeijia.annotation.MyLog
import com.ebeijia.entity.vo.wechat.Menu
import com.ebeijia.service.wechat.menu.WechatMenuService
import com.ebeijia.util.Constant
import org.ebeijia.tools.Validate4J
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, ResponseBody}

/**
 * WechatMenuQueryAction
 * @author zhicheng.xu
 *         15/8/10
 */


@Controller
@RequestMapping(value = Array("/wechat/menu"))
class WechatMenuQueryAction {
  @Autowired
  private val wechatMenuService: WechatMenuService = null
  private val logger: Logger = LoggerFactory.getLogger(classOf[WechatMenuQueryAction])

  @RequestMapping(value = Array("query.html"), method = Array(RequestMethod.POST))
  @ResponseBody def query(session: HttpSession, request: HttpServletRequest): Map[String, AnyRef] = {
    val mchtId: String = request.getParameter("mchtId")
    val groupId: String = request.getParameter("groupId")
    val pageData: String = request.getParameter("aoData")
    val map: Map[String, AnyRef] = new HashMap[String, AnyRef]
    try {
      val mapTmp: Map[String, AnyRef] = wechatMenuService.findBySql(mchtId, groupId, pageData)
      map.put("mchtList", mapTmp.get("list"))
      map.put("current", mapTmp.get("current"))
      map.put("total", mapTmp.get("total"))
      AjaxResp.getReturn(Constant.SUCCESS_CODE, "", map)
    }
    catch {
      case e: Exception => {
        logger.info("微信菜单查询失败,请联系管理员或稍后再试")
        AjaxResp.getReturn(Constant.ERROR_CODE, "微信菜单查询失败,请联系管理员或稍后再试", "")
      }
    }
  }

  @RequestMapping(value = Array("menuSyn.html"), method = Array(RequestMethod.POST))
  @MyLog(remark = "微信菜单同步")
  @ResponseBody def menuSyn(session: HttpSession, request: HttpServletRequest): Map[String, AnyRef] = {
    val mchtId: String = request.getParameter("mchtId")
    val groupId: String = request.getParameter("groupId")
    val s: Array[Array[String]] = Array(Array(mchtId, "15", "15"), Array(groupId, "1", "3"))
    if (!Validate4J.checkStrArrLen(s)) {
      AjaxResp.getReturn(Constant.ERROR_CODE, "参数长度或格式不正确", "")
    }
    var menu: Menu = null
    try {
      menu = wechatMenuService.SynchToMenu(mchtId, groupId)
      if (menu != null) {
        AjaxResp.getReturn(Constant.SUCCESS_CODE, "", menu)
      }
      else {
        AjaxResp.getReturn(Constant.ERROR_CODE, "同步微信菜单失败", "")
      }
    }
    catch {
      case e: Exception => {
        logger.info("同步微信菜单失败")
        AjaxResp.getReturn(Constant.ERROR_CODE, "同步微信菜单失败", "")
      }
    }
  }
}

