package com.ebeijia.controller.list.wechat

import java.util.Map
import javax.servlet.http.HttpServletRequest

import com.ebeijia.service.mcht.MchtInfService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestMapping, ResponseBody}

/**
 * ListWechatMchtId
 * @author zhicheng.xu
 *         15/8/11
 */


@Controller
@RequestMapping(value = Array("/list"))
class ListWechatMchtId {
  @Autowired
  private val mchtInfService: MchtInfService = null

  @RequestMapping(value = Array("mchtAll.html"))
  @ResponseBody def listMchtId(request: HttpServletRequest): Map[String, AnyRef] = {
    mchtInfService.ListMchtId
  }
}
