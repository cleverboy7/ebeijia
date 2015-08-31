package com.ebeijia.controller.list.wechat
import java.util.Map
import javax.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import com.ebeijia.service.wechat.menu.WechatMenuGroupService
/**
 * ListGroupId
 * @author zhicheng.xu
 *         15/8/11
 */



@Controller
@RequestMapping(value = Array("/list"))
class ListGroupId {
  @Autowired
  private val wechatMenuGroupService: WechatMenuGroupService = null

  @RequestMapping(value = Array("groupId.html"))
  @ResponseBody def listGroupId(request: HttpServletRequest): Map[String, AnyRef] = {
    val mchtId: String = request.getParameter("mchtId")
    wechatMenuGroupService.listFind(mchtId)
  }
}
