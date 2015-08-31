package com.ebeijia.service.wechat.mass
import java.util.Map
/**
 * WechatMassService
 * @author zhicheng.xu
 *         15/8/14
 */



trait WechatMassService {
  def upGtMedia(mchtId: String, title: String, dsc: String, articles: String): String

  def upVideoMedia(mchtId: String, title: String, dsc: String, mediaId: String): String

  def sendByGroup(mchtId: String, content: String, `type`: String, groupId: String, media: String): String

  def sendByUsr(mchtId: String, content: String, msgtype: String, toUsr: String, media: String): String

  def sendDel(mchtId: String, mediaId: String): String

  def sendStatusFind(mchtId: String, msgId: String): String

  def findBySql(mchtId: String, pagaData: String): Map[String, AnyRef]
}
