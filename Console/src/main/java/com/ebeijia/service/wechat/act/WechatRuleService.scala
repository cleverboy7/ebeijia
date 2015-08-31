package com.ebeijia.service.wechat.act

import java.util.Map

import com.ebeijia.entity.wechat.TblWechatRule

/**
 * WechatRuleService
 * @author zhicheng.xu
 *         15/8/13
 */


trait WechatRuleService {
  def save(data: TblWechatRule, modId: String): String

  def upd(data: TblWechatRule): String

  def del(id: String): String

  def getById(id: String): TblWechatRule

  def findBySql(mchtId: String, pageData: String): Map[String, AnyRef]
}
