package com.ebeijia.controller.action.wechat.menu

import java.util.{LinkedHashMap, List, Map}
import javax.servlet.http.{HttpServletRequest, HttpSession}

import com.ebeijia.ajax.resp.AjaxResp
import com.ebeijia.annotation.MyLog
import com.ebeijia.entity.wechat.TblWechatMenu
import com.ebeijia.service.wechat.menu.WechatMenuService
import com.ebeijia.util.Constant
import org.ebeijia.tools.{DateTime4J, Validate4J}
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, ResponseBody}

/**
 * WechatMenuAddAction
 * @author zhicheng.xu
 *         15/8/10
 */


@Controller
@RequestMapping(value = Array("/wechat/menu"))
class WechatMenuAddAction {
  @Autowired
  private val wechatMenuService: WechatMenuService = null
  private val logger: Logger = LoggerFactory.getLogger(classOf[WechatMenuAddAction])

  @RequestMapping(value = Array("add.html"), method = Array(RequestMethod.POST))
  @MyLog(remark = "微信菜单新增")
  @ResponseBody def add(session: HttpSession, request: HttpServletRequest): Map[String, AnyRef] = {
    val tblWechatMenu: TblWechatMenu = this.build(request)
    if (tblWechatMenu == null) {
      AjaxResp.getReturn(Constant.ERROR_CODE, "参数长度或格式不正确", "")
    }
    try {
      wechatMenuService.save(tblWechatMenu)
      AjaxResp.getReturn(Constant.SUCCESS_CODE, "", "")
    }
    catch {
      case e: Exception => {
        logger.info("新增微信菜单失败")
        AjaxResp.getReturn(Constant.ERROR_CODE, "新增微信菜单失败", "")
      }
    }
  }

  @RequestMapping(value = Array("wechatMenuAddCheck.html"), method = Array(RequestMethod.POST))
  @ResponseBody def wechatMenuAddCheck(session: HttpSession, request: HttpServletRequest): Map[String, AnyRef] = {
    val map: Map[String, AnyRef] = new LinkedHashMap[String, AnyRef]
    val menuKey: String = request.getParameter("menuKey")
    val mchtId: String = request.getParameter("mchtId")
    val tblWechatMenu: List[TblWechatMenu] = wechatMenuService.findByHql(menuKey, mchtId)
    if (!tblWechatMenu.isEmpty) {
      map.put("info", "yes")
    }
    else {
      map.put("info", "no")
    }
    map
  }

  private def build(request: HttpServletRequest): TblWechatMenu = {
    val mchtId: String = request.getParameter("mchtId")
    val orderNo: String = request.getParameter("orderNo")
    val menuName: String = request.getParameter("menuName")
    val parentId: String = request.getParameter("parentId")
    val `type`: String = request.getParameter("type")
    val menuKey: String = request.getParameter("menuKey")
    val url: String = request.getParameter("url")
    val groupId: String = request.getParameter("groupId")
    val s: Array[Array[String]] = Array(Array(mchtId, "15", "15"), Array(orderNo, "1", "4"), Array(menuName, "1", "40"), Array(parentId, "1", "10"), Array(`type`, "1", "1"), Array(groupId, "1", "3"), Array(url, "0", "256"), Array(menuKey, "0", "128"))
    if (!Validate4J.checkStrArrLen(s)) {
      return null
    }
    val tblWechatMenu: TblWechatMenu = new TblWechatMenu
    tblWechatMenu.setMchtId(mchtId)
    tblWechatMenu.setOrderNo(orderNo)
    tblWechatMenu.setMenuName(menuName)
    tblWechatMenu.setParentId(parentId)
    tblWechatMenu.setGroupId(groupId)
    tblWechatMenu.setType(`type`)
    tblWechatMenu.setMenuKey(menuKey)
    tblWechatMenu.setUrl(url)
    tblWechatMenu.setCreateTime(DateTime4J.getCurrentDateTime)
    tblWechatMenu
  }
}

