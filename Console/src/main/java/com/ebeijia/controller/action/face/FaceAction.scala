package com.ebeijia.controller.action.face

import java.util.{HashMap, Map}
import javax.servlet.http.{HttpServletRequest, HttpSession}

import com.ebeijia.ajax.resp.AjaxResp
import com.ebeijia.annotation.MyLog
import com.ebeijia.service.face.FaceInfService
import com.ebeijia.util.Constant
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestMapping, ResponseBody}
import org.springframework.web.multipart.{MultipartFile, MultipartHttpServletRequest}

/**
 * FaceAction
 * @author zhicheng.xu
 *         15/8/10
 */
@Controller
class FaceAction {
  @Autowired
  private val faceInfService: FaceInfService = null

  @RequestMapping(value = Array("face.html"))
  @MyLog(remark = "人脸识别")
  @ResponseBody
  def logout(session: HttpSession, request: HttpServletRequest): Map[String, AnyRef] = {
    val multipartRequest: MultipartHttpServletRequest = request.asInstanceOf[MultipartHttpServletRequest]
    val file: MultipartFile = multipartRequest.getFile("file")
    var ext: String = null
    if (file != null) {
      val fileName: String = file.getOriginalFilename
      ext = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length)
      ext = ext.toLowerCase
      val respMsg: String = this.vali(ext)
      if (!("" == respMsg)) {
        AjaxResp.getReturn(Constant.ERROR_CODE, respMsg, "")
      }
    }
    try {
      val result: String = faceInfService.faceDetect(file, ext)
      if (result != null) {
        val tmp: Array[String] = result.split(",")
        val tmpMap: Map[String, AnyRef] = new HashMap[String, AnyRef]
        tmpMap.put("info", tmp(0))
        tmpMap.put("person", tmp(1))
        tmpMap.put("sex", tmp(2))
        tmpMap.put("age", tmp(3))
        tmpMap.put("smile", tmp(4))
        AjaxResp.getReturn(Constant.SUCCESS_CODE, "", tmpMap)
      }
      else {
        AjaxResp.getReturn(Constant.ERROR_CODE, "未识别的人脸，请换一张", "")
      }
    }
    catch {
      case e: Exception => {
        AjaxResp.getReturn(Constant.ERROR_CODE, "未识别的人脸，请换一张", "")
      }
    }
  }

  private def vali(ext: String): String = {
    var respMsg: String = ""
    if (("jpg" == ext) || ("png" == ext)) {
    }
    else {
      respMsg = "图片格式不正确"
    }
    respMsg
  }
}