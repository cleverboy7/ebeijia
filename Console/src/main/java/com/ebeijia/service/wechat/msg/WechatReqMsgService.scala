package com.ebeijia.service.wechat.msg

import java.util.Map

import com.ebeijia.entity.wechat.TblWechatReqMsg

/**
 * WechatReqMsgService
 * @author zhicheng.xu
 *         15/8/14
 */


trait WechatReqMsgService {
  def findByMsgId(msgId: String): TblWechatReqMsg

  def save(data: TblWechatReqMsg)

  def findBySql(mchtId: String, aoData: String): Map[String, AnyRef]
}
