package com.ebeijia.controller.action.wechat.menu

import java.util.{HashMap, Map}
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
 * WechatMenuUpdAction
 * @author zhicheng.xu
 *         15/8/10
 */


@Controller
@RequestMapping(value = Array("/wechat/menu"))
class WechatMenuUpdAction {
  @Autowired
  private val wechatMenuService: WechatMenuService = null
  private val logger: Logger = LoggerFactory.getLogger(classOf[WechatMenuUpdAction])

  @RequestMapping(value = Array("upd.html"), method = Array(RequestMethod.POST))
  @MyLog(remark = "微信菜单修改")
  @ResponseBody def upd(session: HttpSession, request: HttpServletRequest): Map[String, AnyRef] = {
    val tblWechatMenu: TblWechatMenu = this.build(request)
    if (tblWechatMenu == null) {
      AjaxResp.getReturn(Constant.ERROR_CODE, "参数长度或格式不正确", "")

    }
    if (wechatMenuService.getById(tblWechatMenu.getMenuId) == null) {
      AjaxResp.getReturn(Constant.ERROR_CODE, "菜单不存在", "")
    }
    try {
      wechatMenuService.updateWechatMenu(tblWechatMenu)
      AjaxResp.getReturn(Constant.SUCCESS_CODE, "", "")
    }
    catch {
      case e: Exception => {
        logger.info("修改菜单失败,请联系管理员或稍后再试")
        AjaxResp.getReturn(Constant.ERROR_CODE, "修改菜单失败,请联系管理员或稍后再试", "")
      }
    }
  }

  @RequestMapping(value = Array("del.html"), method = Array(RequestMethod.POST))
  @MyLog(remark = "微信菜单删除")
  @ResponseBody def del(session: HttpSession, request: HttpServletRequest): Map[String, AnyRef] = {
    val menuId: String = request.getParameter("menuId")
    var map: Map[String, AnyRef] = new HashMap[String, AnyRef]
    if (wechatMenuService.getById(menuId) == null) {
      AjaxResp.getReturn(Constant.ERROR_CODE, "菜单不存在", "")

    }
    try {
      val resp: String = wechatMenuService.deleteMenuById(menuId)
      if (resp == null) {
        AjaxResp.getReturn(Constant.SUCCESS_CODE, "", "")
      }
      else {
        AjaxResp.getReturn(Constant.ERROR_CODE, resp, "")
      }
    }
    catch {
      case e: Exception => {
        logger.info("删除菜单失败,请联系管理员或稍后再试")
        AjaxResp.getReturn(Constant.ERROR_CODE, "删除菜单失败,请联系管理员或稍后再试", "")
      }
    }
  }

  private def build(request: HttpServletRequest): TblWechatMenu = {
    val mchtId: String = request.getParameter("mchtId")
    val menuId: String = request.getParameter("menuId")
    val orderNo: String = request.getParameter("orderNo")
    val menuName: String = request.getParameter("menuName")
    val parentId: String = request.getParameter("parentId")
    val `type`: String = request.getParameter("type")
    val menuKey: String = request.getParameter("menuKey")
    val url: String = request.getParameter("url")
    val groupId: String = request.getParameter("groupId")
    val s: Array[Array[String]] = Array(Array(menuId, "1", "10"), Array(mchtId, "15", "15"), Array(orderNo, "1", "4"), Array(menuName, "1", "40"), Array(parentId, "1", "10"), Array(`type`, "1", "1"), Array(groupId, "1", "3"), Array(url, "0", "256"), Array(menuKey, "0", "128"))
    if (!Validate4J.checkStrArrLen(s)) {
      return null
    }
    val tblWechatMenu: TblWechatMenu = new TblWechatMenu
    tblWechatMenu.setMchtId(mchtId)
    tblWechatMenu.setMenuId(menuId)
    tblWechatMenu.setOrderNo(orderNo)
    tblWechatMenu.setMenuName(menuName)
    tblWechatMenu.setParentId(parentId)
    tblWechatMenu.setType(`type`)
    tblWechatMenu.setMenuKey(menuKey)
    tblWechatMenu.setUrl(url)
    tblWechatMenu.setGroupId(groupId)
    tblWechatMenu.setUpdateTime(DateTime4J.getCurrentDateTime)
    tblWechatMenu
  }
}

