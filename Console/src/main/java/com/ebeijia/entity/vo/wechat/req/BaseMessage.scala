package com.ebeijia.entity.vo.wechat.req

/**
 * BaseMessage消息基类（普通用户 -> 公众帐号）
 * @author zhicheng.xu
 *         15/8/12
 */
class BaseMessage {
  private var ToUserName: String = null
  private var FromUserName: String = null
  private var CreateTime: Long = 0L
  private var MsgType: String = null
  private var MsgId: Long = 0L

  def getToUserName: String = {
    ToUserName
  }

  def setToUserName(toUserName: String) {
    ToUserName = toUserName
  }

  def getFromUserName: String = {
    FromUserName
  }

  def setFromUserName(fromUserName: String) {
    FromUserName = fromUserName
  }

  def getCreateTime: Long = {
    CreateTime
  }

  def setCreateTime(createTime: Long) {
    CreateTime = createTime
  }

  def getMsgType: String = {
    MsgType
  }

  def setMsgType(msgType: String) {
    MsgType = msgType
  }

  def getMsgId: Long = {
    MsgId
  }

  def setMsgId(msgId: Long) {
    MsgId = msgId
  }
}

