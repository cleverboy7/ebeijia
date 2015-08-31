package com.ebeijia.controller.list.wechat
import java.util.Map
import javax.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import com.ebeijia.service.dictInf.DictInfService
/**
 * ListDictByType
 * @author zhicheng.xu
 *         15/8/11
 */



@Controller
@RequestMapping(value = Array("/list"))
class ListDictByType {
  @Autowired
  private val dictInfService: DictInfService = null

  @RequestMapping(value = Array("dictType.html"))
  @ResponseBody def dictType(request: HttpServletRequest): Map[String, AnyRef] = {
    val `type`: String = request.getParameter("type")
    dictInfService.findByDictType(`type`)
  }
}
