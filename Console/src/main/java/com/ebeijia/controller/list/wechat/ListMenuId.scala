package com.ebeijia.controller.list.wechat
import java.util.Map
import javax.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import com.ebeijia.service.wechat.menu.WechatMenuService
/**
 * ListMenuId
 * @author zhicheng.xu
 *         15/8/11
 */



@Controller
@RequestMapping(value = Array("/list"))
class ListMenuId {
  @Autowired
  private val wechatMenuService: WechatMenuService = null

  @RequestMapping(value = Array("menuUpId.html"))
  @ResponseBody def menuUpId(request: HttpServletRequest): Map[String, AnyRef] = {
    val mchtId: String = request.getParameter("mchtId")
    val groupId: String = request.getParameter("groupId")
    wechatMenuService.listFind(mchtId, groupId)
  }
}
