package com.ebeijia.controller.action.txnLog

import java.util.{HashMap, Map}
import javax.servlet.http.{HttpServletRequest, HttpSession}

import com.ebeijia.ajax.resp.AjaxResp
import com.ebeijia.service.txnLog.TxnLogService
import com.ebeijia.util.Constant
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, ResponseBody}

/**
 * TxnLogAction
 * @author zhicheng.xu
 *         15/8/10
 */

@Controller
@RequestMapping(value = Array("/wechat/txnLog")) class TxnLogAction {
  @Autowired
  private val txnLogService: TxnLogService = null
  private val logger: Logger = LoggerFactory.getLogger(classOf[TxnLogAction])

  @RequestMapping(value = Array("/query"), method = Array(RequestMethod.POST))
  @ResponseBody def query(session: HttpSession, request: HttpServletRequest): Map[String, AnyRef] = {
    val date: String = request.getParameter("date")
    val txnChl: String = request.getParameter("txnChl")
    val status: String = request.getParameter("status")
    val pageData: String = request.getParameter("aoData")
    val map: Map[String, AnyRef] = new HashMap[String, AnyRef]
    try {
      val mapTmp: Map[String, AnyRef] = txnLogService.findBySql(date, txnChl, status, pageData)
      map.put("txnLogList", mapTmp.get("list"))
      map.put("current", mapTmp.get("current"))
      map.put("total", mapTmp.get("total"))
      AjaxResp.getReturn(Constant.SUCCESS_CODE, "", map)
    }
    catch {
      case e: Exception => {
        logger.info("操作流水查询失败")
        AjaxResp.getReturn(Constant.ERROR_CODE, "操作流水查询失败,请联系管理员或稍后再试", "")
      }
    }
  }
}

