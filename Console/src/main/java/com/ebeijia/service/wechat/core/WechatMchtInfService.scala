package com.ebeijia.service.wechat.core

import java.util.{List, Map}
import com.ebeijia.entity.wechat.TblWechatMchtInf

/**
 * WechatMchtInfService
 * @author zhicheng.xu
 *         15/8/13
 */


trait WechatMchtInfService {
  def updateWechatMchtInf(wechatMchtInf: TblWechatMchtInf)

  def queryWechatMchtInfList: List[TblWechatMchtInf]

  def findMchtList: Map[String, AnyRef]

  def findBySql(mchtId: String, pagaData: String): Map[String, AnyRef]

  def findByNikeName(nikeName: String): TblWechatMchtInf

  def addWechatMchtInf(wechatMchtInf: TblWechatMchtInf): String

  def deleteWechatMchtInf(wechatMchtInf: TblWechatMchtInf)

  def getById(id: String): TblWechatMchtInf

  def getByAppid(appid: String): TblWechatMchtInf
}
