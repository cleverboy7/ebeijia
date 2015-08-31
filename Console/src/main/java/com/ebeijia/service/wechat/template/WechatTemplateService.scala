package com.ebeijia.service.wechat.template
import java.util.Map
/**
 * WechatTemplateService
 * @author zhicheng.xu
 *         15/8/14
 */



trait WechatTemplateService {
  def set(mchtId: String, id1: String, id2: String): String

  def findBySql(mchtId: String, pagaData: String): Map[String, AnyRef]
}
