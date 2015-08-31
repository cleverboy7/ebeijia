package com.ebeijia.controller.action.wechat.redpachet

import java.util.{HashMap, Map}
import javax.servlet.http.{HttpServletRequest, HttpSession}
import com.ebeijia.ajax.resp.AjaxResp
import com.ebeijia.annotation.MyLog
import com.ebeijia.entity.wechat.{TblWechatRedpacket, TblWechatRedpacketId}
import com.ebeijia.service.wechat.act.WechatRedpacketService
import com.ebeijia.util.Constant
import org.ebeijia.tools.{UUID4J, Validate4J}
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, ResponseBody}

/**
 * WechatRedpacketAddAction
 * @author zhicheng.xu
 *         15/8/10
 */

@Controller
@RequestMapping(value = Array("/wechat/redpacket"))
class WechatRedpacketAddAction {
  @Autowired
  private val wechatRedpacketService: WechatRedpacketService = null
  private val logger: Logger = LoggerFactory.getLogger(classOf[WechatRedpacketAddAction])

  @RequestMapping(value = Array("add.html"), method = Array(RequestMethod.POST))
  @MyLog(remark = "新增红包")
  @ResponseBody def add(session: HttpSession, request: HttpServletRequest): Map[String, AnyRef] = {
    val data: TblWechatRedpacket = this.build(request)
    if (data == null) {
      AjaxResp.getReturn(Constant.ERROR_CODE, "参数长度或格式不正确", "")
    }
    try {
      val respMsg: String = wechatRedpacketService.save(data)
      if (respMsg == null) {
        AjaxResp.getReturn(Constant.SUCCESS_CODE, "", "")
      }
      else {
        AjaxResp.getReturn(Constant.ERROR_CODE, "新增红包失败,请联系管理员或稍后再试", "")
      }
    }
    catch {
      case e: Exception => {
        logger.info("新增红包失败")
        AjaxResp.getReturn(Constant.ERROR_CODE, "新增红包失败,请联系管理员或稍后再试", "")
      }
    }
  }

  private def build(request: HttpServletRequest): TblWechatRedpacket = {
    val mchtId: String = request.getParameter("mchtId")
    val sendName: String = request.getParameter("sendName")
    val openid: String = request.getParameter("openid")
    val totalAmount: Int = request.getParameter("totalAmount").toInt
    val minValue: Int = request.getParameter("minValue").toInt
    val maxValue: Int = request.getParameter("maxValue").toInt
    val totalNum: Int = request.getParameter("totalNum").toInt
    val wishing: String = request.getParameter("wishing")
    val actName: String = request.getParameter("actName")
    val remark: String = request.getParameter("remark")
    val logoImgUrl: String = request.getParameter("logoImgUrl")
    val shareContent: String = request.getParameter("shareContent")
    val shareUrl: String = request.getParameter("shareUrl")
    val shareImgUrl: String = request.getParameter("shareImgUrl")
    val s: Array[Array[String]] = Array(Array(mchtId, "15", "15"), Array(sendName, "1", "32"), Array(openid, "0", "32"), Array(logoImgUrl, "0", "128"), Array(shareContent, "0", "256"), Array(shareUrl, "0", "256"), Array(shareImgUrl, "0", "10"), Array(wishing, "1", "128"), Array(actName, "1", "32"), Array(remark, "0", "256"))
    if (!Validate4J.checkStrArrLen(s)) {
      return null
    }
    val id: TblWechatRedpacketId = new TblWechatRedpacketId
    val data: TblWechatRedpacket = new TblWechatRedpacket
    id.setMchtId(mchtId)
    id.setNonceStr(UUID4J.getUUID)
    data.setId(id)
    data.setSendName(sendName)
    data.setOpenid(openid)
    data.setTotalAmount(totalAmount)
    data.setMinValue(minValue)
    data.setMaxValue(maxValue)
    data.setTotalNum(totalNum)
    data.setWishing(wishing)
    data.setActName(actName)
    data.setRemark(remark)
    data.setLogoImgUrl(logoImgUrl)
    data.setShareContent(shareContent)
    data.setShareUrl(shareUrl)
    data.setShareImgUrl(shareImgUrl)
    data.setStatus("0")
    data
  }
}

