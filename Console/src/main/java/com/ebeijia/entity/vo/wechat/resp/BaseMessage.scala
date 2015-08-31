package com.ebeijia.entity.vo.wechat.resp

/**
 * BaseMessage消息基类（公众帐号 -> 普通用户）
 * @author zhicheng.xu
 *         15/8/12
 */
class BaseMessage {
  private var ToUserName: String = null
  private var FromUserName: String = null
  private var CreateTime: Long = 0L
  private var MsgType: String = null
  private var FuncFlag: Int = 0

  def getToUsrName: String = {
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

  def getFuncFlag: Int = {
    FuncFlag
  }

  def setFuncFlag(funcFlag: Int) {
    FuncFlag = funcFlag
  }
}

