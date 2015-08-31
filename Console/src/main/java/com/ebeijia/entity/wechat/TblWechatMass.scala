package com.ebeijia.entity.wechat

import javax.persistence.{AttributeOverride, AttributeOverrides, Column, EmbeddedId, Entity, Table}

/**
 * TblWechatMass
 * @author zhicheng.xu
 *         15/8/12
 */

@Entity
@Table(name = "TBL_WECHAT_MASS")
@SerialVersionUID(1L)
class TblWechatMass extends java.io.Serializable {
  private var id: TblWechatMassId = null
  private var `type`: String = null
  private var msgType: String = null
  private var content: String = null
  private var media: String = null
  private var toUsr: Int = 0
  private var createTime: String = null


  @EmbeddedId
  @AttributeOverrides(Array(new AttributeOverride(name = "msgId", column = new Column(name = "MSG_ID", nullable = false, length = 10)), new AttributeOverride(name = "mchtId", column = new Column(name = "MCHT_ID", nullable = false, length = 32)))) def getId: TblWechatMassId = {
    id
  }

  def setId(id: TblWechatMassId) {
    this.id = id
  }

  @Column(name = "TYPE", length = 1) def getType: String = {
    `type`
  }

  def setType(`type`: String) {
    this.`type` = `type`
  }

  @Column(name = "MSG_TYPE", length = 6) def getMsgType: String = {
    msgType
  }

  def setMsgType(msgType: String) {
    this.msgType = msgType
  }

  @Column(name = "CONTENT", length = 128) def getContent: String = {
    content
  }

  def setContent(content: String) {
    this.content = content
  }

  @Column(name = "MEDIA_ID", length = 100) def getMedia: String = {
    media
  }

  def setMedia(media: String) {
    this.media = media
  }

  @Column(name = "TO_USR", length = 6) def getToUsr: Int = {
    toUsr
  }

  def setToUsr(toUsr: Int) {
    this.toUsr = toUsr
  }

  @Column(name = "CREATE_TIME", length = 14) def getCreateTime: String = {
    createTime
  }

  def setCreateTime(createTime: String) {
    this.createTime = createTime
  }
}

