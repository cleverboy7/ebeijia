package com.ebeijia.controller.list.wechat.media

import java.util.{LinkedHashMap, Map}
import javax.servlet.http.HttpServletRequest
import com.ebeijia.service.wechat.media.WechatFodderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestMapping, ResponseBody}

/**
 * ListMchtMedia
 * @author zhicheng.xu
 *         15/8/11
 */


@Controller
@RequestMapping(value = Array("/list"))
class ListMchtMedia {
  @Autowired
  private val wechatFodderService: WechatFodderService = null

  @RequestMapping(value = Array("mediaImg.html"))
  @ResponseBody def listCompUsrId(request: HttpServletRequest): Map[String, AnyRef] = {
    val mchtId: String = request.getParameter("mchtId")
    wechatFodderService.mediaList(mchtId, "image")
  }
}
