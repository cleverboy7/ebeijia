package com.ebeijia.controller.list.wechat.media

import java.util.Map
import javax.servlet.http.HttpServletRequest
import com.ebeijia.service.wechat.media.WechatFodderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestMapping, ResponseBody}

/**
 * ListNews
 * @author zhicheng.xu
 *         15/8/11
 */
@Controller
@RequestMapping(value = Array("/list"))
class ListNews {
  @Autowired
  private val wechatFodderService: WechatFodderService = null

  @RequestMapping(value = Array("news.html"))
  @ResponseBody def listCompUsrId(request: HttpServletRequest): Map[String, AnyRef] = {
    val mchtId: String = request.getParameter("mchtId")
    wechatFodderService.mediaList(mchtId, "news")
  }
}
