package com.ebeijia.controller.list.system

import java.util.Map
import javax.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import com.ebeijia.service.roleInf.RoleInfService

/**
 * ListFunc
 * @author zhicheng.xu
 *         15/8/11
 */
@Controller
@RequestMapping(value = Array("/list"))
class ListFunc {
  @Autowired
  private val roleInfService: RoleInfService = null

  @RequestMapping(value = Array("funcInf.html"))
  @ResponseBody def funcInf(request: HttpServletRequest): Map[String, AnyRef] = {
    roleInfService.funcFindAll
  }
}
