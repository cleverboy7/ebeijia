package com.ebeijia.service.wechat.msg
import java.util.List
import java.util.Map
import com.ebeijia.entity.wechat.TblWechatRespMsg
/**
 * WechatRespMsgService
 * @author zhicheng.xu
 *         15/8/14
 */

trait WechatRespMsgService {
  def findBySql(mchtId: String, aoData: String): Map[String, AnyRef]

  def save(data: TblWechatRespMsg, articlesJson: String)

  def update(data: TblWechatRespMsg, articlesJson: String)

  def findByMchtType(mcht: String, msgType: String, keywords: String): List[TblWechatRespMsg]

  def getById(id: String): TblWechatRespMsg

  def delete(id: String)

  def check(respMsgId: String, mchtId: String, keywords: String): Int
}
