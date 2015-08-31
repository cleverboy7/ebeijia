package com.ebeijia.service.wechat.act

import java.util.Map

import com.ebeijia.entity.wechat.{TblWechatRedpacket, TblWechatRedpacketId}

/**
 * WechatRedpacketService
 * @author zhicheng.xu
 *         15/8/13
 */


trait WechatRedpacketService {
  def save(data: TblWechatRedpacket): String

  def upd(data: TblWechatRedpacket): String

  def del(id: TblWechatRedpacketId): String

  def getById(id: TblWechatRedpacketId): TblWechatRedpacket

  def findBySql(mchtId: String, pageData: String): Map[String, AnyRef]
}
