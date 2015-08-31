package com.ebeijia.service.wechat.subscribe
import java.util.List
import java.util.Map
import com.ebeijia.entity.wechat.TblWechatSubscribe
/**
 * WechatSubscribeService
 * @author zhicheng.xu
 *         15/8/14
 */

trait WechatSubscribeService {
  def addWechatSubscribe(wechatSubscribe: TblWechatSubscribe)

  def findBySql(mchtId: String, openid: String, aoData: String): Map[String, AnyRef]

  def findByBatch(mchtId: String, groupId: String, aoData: String): Map[String, AnyRef]

  def findByGroup(mchtId: String, groupId: String): List[TblWechatSubscribe]

  def synUsr: String

  def upRemark(data: TblWechatSubscribe): String

  def upGroup(data: TblWechatSubscribe): String

  def batchUpGroup(mchtId: String, subList: String, groupId: String): String

  def getById(id: String): TblWechatSubscribe

  def upGroup(groupId: String)
}
