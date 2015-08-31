package com.ebeijia.service.wechat.act

import java.util.Map

import com.ebeijia.entity.wechat.TblWechatMod

/**
 * WechatModService
 * @author zhicheng.xu
 *         15/8/13
 */


trait WechatModService {
  def save(mchtId: String, modName: String, prizeJson: String, `type`: String): String

  def upd(data: TblWechatMod): String

  def del(id: String): String

  def getById(id: String): TblWechatMod

  def findBySql(mchtId: String, pageData: String): Map[String, AnyRef]

  def modList(mchtId: String, `type`: String): Map[String, AnyRef]
}
