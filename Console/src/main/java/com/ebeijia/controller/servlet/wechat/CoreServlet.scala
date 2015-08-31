package com.ebeijia.controller.servlet.wechat
import java.io.IOException
import java.io.PrintWriter
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import com.ebeijia.service.wechat.core.CoreService
import com.ebeijia.util.wechat.SignUtil
/**
 * CoreServlet
 * 核心请求处理类
 * @author zhicheng.xu
 *         15/8/11
 */

@Controller
class CoreServlet {
  @Autowired
  private val coreService: CoreService = null

  /**
   * 确认请求来自微信服务器
   */
  @RequestMapping(value = Array("/coreServlet/{mchtId}"), method = Array(RequestMethod.GET))
  @throws(classOf[ServletException])
  @throws(classOf[IOException])
  def doGet(@PathVariable mchtId: String, request: HttpServletRequest, response: HttpServletResponse) {
    val signature: String = request.getParameter("signature")
    val timestamp: String = request.getParameter("timestamp")
    val nonce: String = request.getParameter("nonce")
    val echostr: String = request.getParameter("echostr")
    var out: PrintWriter = response.getWriter
    if (SignUtil.checkSignature(signature, timestamp, nonce)) {
      out.print(echostr)
    }
    out.close
    out = null
  }

  /**
   * 处理微信服务器发来的消息
   */
  @RequestMapping(value = Array("/coreServlet/{mchtId}"), method = Array(RequestMethod.POST))
  @throws(classOf[ServletException])
  @throws(classOf[IOException])
  def doPost(@PathVariable mchtId: String, request: HttpServletRequest, response: HttpServletResponse) {
    request.setCharacterEncoding("UTF-8")
    response.setCharacterEncoding("UTF-8")
    val respMessage: String = coreService.processRequest(request, mchtId)
    val out: PrintWriter = response.getWriter
    out.print(respMessage)
    out.close
  }
}
