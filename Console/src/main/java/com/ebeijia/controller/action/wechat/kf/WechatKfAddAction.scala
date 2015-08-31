package com.ebeijia.controller.action.wechat.kf

import java.util.{HashMap, Map}
import javax.servlet.http.{HttpServletRequest, HttpSession}

import com.ebeijia.ajax.resp.AjaxResp
import com.ebeijia.annotation.MyLog
import com.ebeijia.entity.vo.wechat.kf.Kf
import com.ebeijia.service.wechat.kf.WechatKfService
import com.ebeijia.util.{Constant, EncryptMD5Util}
import com.ebeijia.util.wechat.WechatError
import org.ebeijia.tools.Validate4J
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, ResponseBody}

/**
 * WechatKfAddAction
 * @author zhicheng.xu
 *         15/8/10
 */

@Controller
@RequestMapping(value = Array("/wechat/kf"))
class WechatKfAddAction {
  @Autowired
  private val wechatKfService: WechatKfService = null
  private val logger: Logger = LoggerFactory.getLogger(classOf[WechatKfAddAction])

  @RequestMapping(value = Array("add.html"), method = Array(RequestMethod.POST))
  @MyLog(remark = "新增多客服")
  @ResponseBody
  def add(session: HttpSession, request: HttpServletRequest): Map[String, AnyRef] = {
    val mchtId: String = request.getParameter("mchtId")
    val name: String = request.getParameter("name")
    val id: String = request.getParameter("id")
    val pwd: String = request.getParameter("pwd")
    val s: Array[Array[String]] = Array(Array(mchtId, "15", "15"), Array(name, "1", "12"), Array(id, "1", "32"), Array(pwd, "1", "32"))
    if (!Validate4J.checkStrArrLen(s)) {
      AjaxResp.getReturn(Constant.ERROR_CODE, "参数长度或格式不正确", "")
    }
    val kf: Kf = new Kf
    kf.setKf_account(id)
    kf.setNickname(name)
    kf.setPassword(EncryptMD5Util.encrypt(pwd))
    try {
      val total: Int = wechatKfService.findCount(mchtId)
      if (total >= 10) {
        AjaxResp.getReturn(Constant.ERROR_CODE, "一个公众账号，最多只能创建10个客服", "")
      }
      val respMsg: String = wechatKfService.save(mchtId, kf)
      if (respMsg == null) {
        AjaxResp.getReturn(Constant.SUCCESS_CODE, "", "")
      }
      else {
        AjaxResp.getReturn(Constant.ERROR_CODE, WechatError.checkCode(respMsg), "")
      }
    }
    catch {
      case e: Exception => {
        logger.info("新增多客服失败")
        AjaxResp.getReturn(Constant.ERROR_CODE, "新增多客服失败,请联系管理员或稍后再试", "")
      }
    }
  }
}

