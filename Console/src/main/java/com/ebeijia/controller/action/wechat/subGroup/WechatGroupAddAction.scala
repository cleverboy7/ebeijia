package com.ebeijia.controller.action.wechat.subGroup

import java.util.Map
import javax.servlet.http.{HttpServletRequest, HttpSession}

import com.ebeijia.ajax.resp.AjaxResp
import com.ebeijia.annotation.MyLog
import com.ebeijia.service.wechat.group.WechatSubGroupService
import com.ebeijia.util.Constant
import com.ebeijia.util.wechat.WechatError
import org.ebeijia.tools.Validate4J
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, ResponseBody}

/**
 * WechatGroupAddAction
 * @author zhicheng.xu
 *         15/8/10
 */

@Controller
@RequestMapping(value = Array("/wechat/subGroup"))
class WechatGroupAddAction {
  @Autowired
  private val wechatSubGroupService: WechatSubGroupService = null
  private val logger: Logger = LoggerFactory.getLogger(classOf[WechatGroupAddAction])

  @RequestMapping(value = Array("add.html"), method = Array(RequestMethod.POST))
  @MyLog(remark = "新增用户组别")
  @ResponseBody def add(session: HttpSession, request: HttpServletRequest): Map[String, AnyRef] = {
    val mchtId: String = request.getParameter("mchtId")
    val name: String = request.getParameter("name")
    val s: Array[Array[String]] = Array(Array(mchtId, "15", "15"), Array(name, "1", "64"))
    if (!Validate4J.checkStrArrLen(s)) {
      AjaxResp.getReturn(Constant.ERROR_CODE, "参数长度或格式不正确", "")
    }
    try {
      val total: Int = wechatSubGroupService.findCount(mchtId)
      if (total >= 100) {
        AjaxResp.getReturn(Constant.ERROR_CODE, "一个公众账号，最多支持创建100个分组", "")
      }
      val respMsg: String = wechatSubGroupService.save(mchtId, name)
      if (respMsg == null) {
        AjaxResp.getReturn(Constant.SUCCESS_CODE, "", "")
      }
      else {
        AjaxResp.getReturn(Constant.ERROR_CODE, WechatError.checkCode(respMsg), "")
      }
    }
    catch {
      case e: Exception => {
        logger.info("新增用户组别失败")
        AjaxResp.getReturn(Constant.ERROR_CODE, "新增用户组别失败,请联系管理员或稍后再试", "")
      }
    }
  }
}

