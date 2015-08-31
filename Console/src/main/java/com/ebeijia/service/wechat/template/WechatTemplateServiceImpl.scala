package com.ebeijia.service.wechat.template

/**
 * WechatTemplateServiceImpl
 * @author zhicheng.xu
 *         15/8/14
 */

import java.util.Map

import com.ebeijia.dao.wechat.WechatMchtInfDao
import com.ebeijia.entity.vo.wechat.AccessToken
import com.ebeijia.entity.wechat.TblWechatMchtInf
import com.ebeijia.service.wechat.core.{WechatMchtInfService, WechatTokenService}
import com.ebeijia.service.wechat.inter.TemplateManager
import com.ebeijia.util.wechat.WechatUtil
import net.sf.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
final class WechatTemplateServiceImpl extends WechatTemplateService {
  @Autowired
  private val wechatMchtInfService: WechatMchtInfService = null
  @Autowired
  private val wechatMchtInfDao: WechatMchtInfDao = null
  @Autowired
  private val wechatTokenService: WechatTokenService = null
  @Autowired
  private val templateManager: TemplateManager = null

  @Transactional
  def set(mchtId: String, id1: String, id2: String): String = {
    val data: TblWechatMchtInf = wechatMchtInfService.getById(mchtId)
    val at: AccessToken = wechatTokenService.getAccessToken(data.getAppid, data.getAppsecret)
    var result: String = null
    if (null != at) {
      val jsonObject: JSONObject = templateManager.set(id1, id2, at.getToken)
      if (null != jsonObject) {
        if (WechatUtil.getWechatCallBackStatus(jsonObject.toString) != 0) {
          result = jsonObject.getString("errcode")
        }
        else {
          data.setTemplate1(id1)
          data.setTemplate2(id2)
          wechatMchtInfDao.update(data)
        }
      }
    } else {
      result = "9999999"
    }
    result
  }

  @Transactional
  def findBySql(mchtId: String, pagaData: String): Map[String, AnyRef] = {
    val m: Map[String, AnyRef] = wechatMchtInfService.findBySql(mchtId, pagaData)
    m
  }
}
