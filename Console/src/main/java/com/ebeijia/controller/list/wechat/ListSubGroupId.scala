package com.ebeijia.controller.list.wechat

import java.util.Map
import javax.servlet.http.HttpServletRequest

import com.ebeijia.service.wechat.group.WechatSubGroupService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestMapping, ResponseBody}

/**
 * ListSubGroupId
 * @author zhicheng.xu
 *         15/8/11
 */


@Controller
@RequestMapping(value = Array("/list"))
class ListSubGroupId {
  @Autowired
  private val wechatSubGroupService: WechatSubGroupService = null

  @RequestMapping(value = Array("subGroup.html"))
  @ResponseBody def subGroup(request: HttpServletRequest): Map[String, AnyRef] = {
    val mchtId: String = request.getParameter("mchtId")
    wechatSubGroupService.listGroupByMchtId(mchtId)
  }
}
