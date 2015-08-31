package com.ebeijia.controller.action.wechat.msg

import java.util.Map
import javax.servlet.http.{HttpServletRequest, HttpSession}

import com.ebeijia.ajax.resp.AjaxResp
import com.ebeijia.annotation.MyLog
import com.ebeijia.entity.wechat.TblWechatRespMsg
import com.ebeijia.service.wechat.msg.WechatRespMsgService
import com.ebeijia.util.Constant
import org.ebeijia.tools.{DateTime4J, Validate4J}
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, ResponseBody}

/**
 * WechatRespMsgAddAction
 * @author zhicheng.xu
 *         15/8/10
 */

@Controller
@RequestMapping(value = Array("/wechat/respMsg"))
class WechatRespMsgAddAction {
  @Autowired
  private val wechatRespMsgService: WechatRespMsgService = null
  private val logger: Logger = LoggerFactory.getLogger(classOf[WechatRespMsgAddAction])

  @RequestMapping(value = Array("add.html"), method = Array(RequestMethod.POST))
  @MyLog(remark = "微信回复消息新增")
  @ResponseBody def add(session: HttpSession, request: HttpServletRequest): Map[String, AnyRef] = {
    val tblWechatRespMsg: TblWechatRespMsg = this.build(request)
    if (tblWechatRespMsg == null) {
      AjaxResp.getReturn(Constant.ERROR_CODE, "参数长度或格式不正确", "")
    }
    val articlesJson: String = request.getParameter("articles")
    val mchtId: String = request.getParameter("mchtId")
    val keywords: String = request.getParameter("keywords")
    val msgType: String = request.getParameter("msgType")
    if ("news" == msgType) {
      if (("" == articlesJson) || articlesJson == null) {
        AjaxResp.getReturn(Constant.ERROR_CODE, "微信图文格式不正确", "")
      }
    }
    try {
      val total: Int = wechatRespMsgService.check(null, mchtId, keywords)
      if (total > 0) {
        AjaxResp.getReturn(Constant.ERROR_CODE, "关键字已存在！", "")
      }
      wechatRespMsgService.save(tblWechatRespMsg, articlesJson)
      AjaxResp.getReturn(Constant.SUCCESS_CODE, "", "")
    }
    catch {
      case e: Exception => {
        logger.info("微信回复消息新增失败")
        AjaxResp.getReturn(Constant.ERROR_CODE, "微信回复消息新增失败,请联系管理员或稍后再试", "")
      }
    }
  }

  private def build(request: HttpServletRequest): TblWechatRespMsg = {
    val mchtId: String = request.getParameter("mchtId")
    val keywords: String = request.getParameter("keywords")
    val keywordType: String = request.getParameter("keywordType")
    val respType: String = request.getParameter("respType")
    val msgType: String = request.getParameter("msgType")
    val content: String = request.getParameter("content")
    val picUrl: String = request.getParameter("picUrl")
    val title: String = request.getParameter("title")
    val dsc: String = request.getParameter("dsc")
    val url: String = request.getParameter("url")
    val mediaId: String = request.getParameter("mediaId")
    val musicUrl: String = request.getParameter("musicUrl")
    val hqMusicUrl: String = request.getParameter("hqMusicUrl")
    val s: Array[Array[String]] = Array(Array(mchtId, "15", "15"), Array(keywords, "0", "32"), Array(keywordType, "1", "1"), Array(respType, "4", "6"), Array(msgType, "4", "25"), Array(content, "0", "1000"), Array(picUrl, "0", "256"), Array(title, "0", "64"), Array(dsc, "0", "1024"), Array(url, "0", "256"), Array(mediaId, "0", "256"), Array(musicUrl, "0", "256"), Array(hqMusicUrl, "0", "256"))
    if (!Validate4J.checkStrArrLen(s)) {
      return null
    }
    val tblWechatRespMsg: TblWechatRespMsg = new TblWechatRespMsg
    tblWechatRespMsg.setMchtId(mchtId)
    tblWechatRespMsg.setKeywords(keywords)
    tblWechatRespMsg.setKeywordType(keywordType)
    tblWechatRespMsg.setRespType(respType)
    tblWechatRespMsg.setMsgType(msgType)
    tblWechatRespMsg.setContent(content)
    tblWechatRespMsg.setPicUrl(picUrl)
    tblWechatRespMsg.setUrl(url)
    tblWechatRespMsg.setTitle(title)
    tblWechatRespMsg.setDescription(dsc)
    tblWechatRespMsg.setMediaId(mediaId)
    tblWechatRespMsg.setMusicUrl(musicUrl)
    tblWechatRespMsg.setHqMusicUrl(hqMusicUrl)
    tblWechatRespMsg.setCreateTime(DateTime4J.getCurrentDateTime)
    tblWechatRespMsg
  }
}

