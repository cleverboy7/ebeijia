package com.ebeijia.controller.list.wechat.act
import java.util.LinkedHashMap
import java.util.Map
import javax.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import com.ebeijia.service.wechat.act.WechatModService
/**
 * ListModId
 * @author zhicheng.xu
 *         15/8/11
 */



@Controller
@RequestMapping(value = Array("/list"))
class ListModId {
  @Autowired
  private val wechatModService: WechatModService = null

  @RequestMapping(value = Array("modList.html"))
  @ResponseBody def listCompUsrId(request: HttpServletRequest): Map[String, AnyRef] = {
    val mchtId: String = request.getParameter("mchtId")
    val `type`: String = request.getParameter("type")
    wechatModService.modList(mchtId, `type`)
  }
}
