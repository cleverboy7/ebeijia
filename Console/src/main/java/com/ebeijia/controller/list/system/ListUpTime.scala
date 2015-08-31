package com.ebeijia.controller.list.system

import java.util.List
import javax.servlet.http.HttpServletRequest

import com.ebeijia.entity.TblAdminInf
import com.ebeijia.service.adminInf.AdminService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestMapping, ResponseBody}

import scala.collection.JavaConverters._

/**
 * ListUpTime
 * @author zhicheng.xu
 *         15/8/11
 */


@Controller
@RequestMapping(value = Array("/list"))
class ListUpTime {
  @Autowired
  private val adminService: AdminService = null

  @RequestMapping(value = Array("upTime.html"))
  @ResponseBody def upTime(request: HttpServletRequest): List[TblAdminInf] = {
    adminService.listAdminByUpTime
  }
}
