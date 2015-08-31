package com.ebeijia.controller.action.wechat.media

import java.util.{HashMap, Map}
import javax.servlet.http.{HttpServletRequest, HttpSession}
import com.ebeijia.ajax.resp.AjaxResp
import com.ebeijia.annotation.MyLog
import com.ebeijia.service.wechat.media.WechatMediaService
import com.ebeijia.util.Constant
import com.ebeijia.util.wechat.WechatError
import net.sf.json.JSONObject
import org.ebeijia.tools.Validate4J
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, ResponseBody}
import org.springframework.web.multipart.{MultipartFile, MultipartHttpServletRequest}

/**
 * WechatMediaAddAction
 * @author zhicheng.xu
 *         15/8/10
 */

@Controller
@RequestMapping(value = Array("/wechat/media"))
class WechatMediaAddAction {
  @Autowired
  private val wechatMediaService: WechatMediaService = null
  private val logger: Logger = LoggerFactory.getLogger(classOf[WechatMediaAddAction])

  @RequestMapping(value = Array("upLoad.html"), method = Array(RequestMethod.POST))
  @MyLog(remark = "微信上传素材")
  @ResponseBody def upLoad(session: HttpSession, request: HttpServletRequest): Map[String, AnyRef] = {
    val mchtId: String = request.getParameter("mchtId")
    val `type`: String = request.getParameter("type")
    val mediaType: String = request.getParameter("mediaType")
    val name: String = request.getParameter("name")
    val dsc: String = request.getParameter("dsc")
    val s: Array[Array[String]] = Array(Array(mchtId, "15", "15"), Array(`type`, "4", "6"), Array(mediaType, "1", "1"), Array(name, "1", "30"), Array(dsc, "0", "64"))
    if (!Validate4J.checkStrArrLen(s)) {
      AjaxResp.getReturn(Constant.ERROR_CODE, "参数长度或格式不正确", "")
    }
    val multipartRequest: MultipartHttpServletRequest = request.asInstanceOf[MultipartHttpServletRequest]
    val file: MultipartFile = multipartRequest.getFile("file")
    if (file == null) {
      AjaxResp.getReturn(Constant.ERROR_CODE, "没有找到要上传的文件", "")
    }
    val fileName: String = file.getOriginalFilename
    var ext: String = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length)
    ext = ext.toLowerCase
    var respMsg: String = this.vali(mediaType, `type`, ext)
    if (!("" == respMsg)) {
      AjaxResp.getReturn(Constant.ERROR_CODE, respMsg, "")
    }
    try {
      respMsg = wechatMediaService.upLoadMedia(mchtId, `type`, file, mediaType, ext, name, dsc)
      if (respMsg == null) {
        AjaxResp.getReturn(Constant.SUCCESS_CODE, "", "")
      } else {
        AjaxResp.getReturn(Constant.ERROR_CODE, WechatError.checkCode(respMsg), "")
      }

    }
    catch {
      case e: Exception => {
        logger.info("上传素材失败")
        AjaxResp.getReturn(Constant.ERROR_CODE, "上传素材失败,请联系管理员或稍后再试", "")

      }
    }
  }

  @RequestMapping(value = Array("dowLoad.html"), method = Array(RequestMethod.POST))
  @MyLog(remark = "微信下载素材")
  @ResponseBody def dowLoad(session: HttpSession, request: HttpServletRequest): Map[String, AnyRef] = {
    val mchtId: String = request.getParameter("mchtId")
    val mediaType: String = request.getParameter("mediaType")
    val media: String = request.getParameter("media")
    var resp: String = null
    val s: Array[Array[String]] = Array(Array(mchtId, "15", "15"), Array(media, "1", "100"), Array(mediaType, "1", "1"))
    if (!Validate4J.checkStrArrLen(s)) {
      AjaxResp.getReturn(Constant.ERROR_CODE, "参数长度或格式不正确", "")

    }
    try {
      resp = wechatMediaService.dowLoadMedia(mchtId, media, mediaType)
      if (resp.indexOf("http") == 0) {
        AjaxResp.getReturn(Constant.SUCCESS_CODE, "", resp)
      } else {
        val json: JSONObject = JSONObject.fromObject(resp)
        resp = json.getString("errcode")
        AjaxResp.getReturn(Constant.ERROR_CODE, WechatError.checkCode(resp), "")
      }

    }
    catch {
      case e: Exception => {
        logger.info("下载多媒体失败")
        AjaxResp.getReturn(Constant.ERROR_CODE, "下载多媒体失败,请联系管理员或稍后再试", "")
      }
    }
  }

  @RequestMapping(value = Array("upNews.html"), method = Array(RequestMethod.POST))
  @MyLog(remark = "微信上传图文素材")
  @ResponseBody def upNews(session: HttpSession, request: HttpServletRequest): Map[String, AnyRef] = {
    val mchtId: String = request.getParameter("mchtId")
    val articles: String = request.getParameter("articles")
    val name: String = request.getParameter("name")
    val dsc: String = request.getParameter("dsc")
    var respMsg: String = null
    var map: Map[String, AnyRef] = new HashMap[String, AnyRef]
    val s: Array[Array[String]] = Array(Array(mchtId, "15", "15"), Array(articles, "1", null), Array(name, "1", "30"), Array(dsc, "0", "64"))
    if (!Validate4J.checkStrArrLen(s)) {
      AjaxResp.getReturn(Constant.ERROR_CODE, "参数长度或格式不正确", "")
    }
    try {
      respMsg = wechatMediaService.upGtMedia(mchtId, name, dsc, articles)
      if (respMsg == null) {
        AjaxResp.getReturn(Constant.SUCCESS_CODE, "", "")
      } else {
        AjaxResp.getReturn(Constant.ERROR_CODE, WechatError.checkCode(respMsg), "")
      }
    }
    catch {
      case e: Exception => {
        logger.info("上传图文失败,请联系管理员或稍后再试")
        AjaxResp.getReturn(Constant.ERROR_CODE, "上传图文失败,请联系管理员或稍后再试", "")

      }
    }
  }

  private def vali(`type`: String, mediaType: String, ext: String): String = {
    var respMsg: String = ""
    if ("0" == mediaType) {
      if (("image" == `type`) || ("thumb" == `type`)) {
        if (!("jpg" == ext)) {
          respMsg = "图片或缩略图格式不正确"
        }
      } else if ("voice" == `type`) {
        if (!("amr" == ext) || !("mp3" == ext)) {
          respMsg = "语音格式不正确"
        }
      } else if ("video" == `type`) {
        if (!("mp4" == ext)) {
          respMsg = "视频格式不正确"
        }
      }
    } else {
      if (("image" == `type`) || ("thumb" == `type`)) {
        if (!("jpg" == ext) || !("bmp" == ext) || !("png" == ext) || !("jpeg" == ext) || !("gif" == ext)) {
          respMsg = "图片或缩略图格式不正确"
        }
      } else if ("voice" == `type`) {
        if (!("amr" == ext) || !("mp3" == ext) || !("wma" == ext) || !("wav" == ext)) {
          respMsg = "语音格式不正确"
        }
      } else if ("video" == `type`) {
        if (!("mp4" == ext)) {
          respMsg = "视频格式不正确"
        }
      }
    }
    respMsg
  }
}

