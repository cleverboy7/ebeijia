package com.ebeijia.service.wechat.media

import java.util.Map

import com.ebeijia.entity.wechat.TblWechatFodder

/**
 * WechatFodderService
 * @author zhicheng.xu
 *         15/8/14
 */


trait WechatFodderService {
  def findBySql(mchtId: String, `type`: String, mediaType: String, aoData: String): Map[String, AnyRef]

  def upFodder(media: String, name: String, dsc: String)

  def delFodder(mchtId: String, media: String): String

  def mediaList(mchtId: String, msgType: String): Map[String, AnyRef]

  def findBySqltoNews(mchtId: String, aoData: String): Map[String, AnyRef]

  def save(data: TblWechatFodder)

  def getById(id: String): TblWechatFodder
}
