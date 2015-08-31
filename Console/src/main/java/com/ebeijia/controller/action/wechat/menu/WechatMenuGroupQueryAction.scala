package com.ebeijia.controller.action.wechat.menu

import java.util.{HashMap, List, Map}
import javax.servlet.http.{HttpServletRequest, HttpSession}
import com.ebeijia.ajax.resp.AjaxResp
import com.ebeijia.entity.wechat.TblWechatGroup
import com.ebeijia.service.wechat.menu.WechatMenuGroupService
import com.ebeijia.util.Constant
import org.ebeijia.tools.Validate4J
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, ResponseBody}

/**
 * WechatMenuGroupQueryAction
 * @author zhicheng.xu
 *         15/8/10
 */


@Controller
@RequestMapping(value = Array("/wechat/menu"))
class WechatMenuGroupQueryAction {
  @Autowired
  private val wechatMenuGroupService: WechatMenuGroupService = null

  @RequestMapping(value = Array("groupQuery.html"), method = Array(RequestMethod.POST))
  @ResponseBody def wechatMenuQuery(session: HttpSession, request: HttpServletRequest): Map[String, AnyRef] = {
    val mchtId: String = request.getParameter("mchtId")
    val s: Array[Array[String]] = Array(Array(mchtId, "15", "15"))
    val map: Map[String, AnyRef] = new HashMap[String, AnyRef]
    if (!Validate4J.checkStrArrLen(s)) {
      AjaxResp.getReturn(Constant.ERROR_CODE, "参数长度或格式不正确", "")
    }
    val list: List[TblWechatGroup] = wechatMenuGroupService.find(mchtId)
    map.put("info", list)
    AjaxResp.getReturn(Constant.SUCCESS_CODE, "", map)

  }
}
