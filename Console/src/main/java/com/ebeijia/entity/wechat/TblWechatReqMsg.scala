package com.ebeijia.entity.wechat

import javax.persistence._

/**
 * TblWechatReqMsg
 * @author zhicheng.xu
 *         15/8/12
 */


@Entity
@Table(name = "TBL_WECHAT_REQ_MSG")
@SerialVersionUID(1L)
class TblWechatReqMsg extends java.io.Serializable {
  private var reqMsgId: String = null
  private var mchtId: String = null
  private var toUsrName: String = null
  private var fromUsrName: String = null
  private var msgId: String = null
  private var createTime: String = null
  private var msgType: String = null
  private var content: String = null
  private var picUrl: String = null
  private var format: String = null
  private var mediaId: String = null
  private var thumbMediaId: String = null
  private var locationX: String = null
  private var locationY: String = null
  private var scale: String = null
  private var label: String = null
  private var title: String = null
  private var description: String = null
  private var url: String = null
  private var eventType: String = null
  private var eventKey: String = null
  private var ticket: String = null
  private var respContent: String = null
  private var latitude: String = null
  private var longitude: String = null
  private var precise: String = null



  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "REQ_MSG_ID", unique = true, nullable = false, length = 20)
  def getReqMsgId: String = {
    reqMsgId
  }

  def setReqMsgId(reqMsgId: String) {
    this.reqMsgId = reqMsgId
  }

  @Column(name = "MCHT_ID", nullable = false, length = 15)
  def getMchtId: String = {
    mchtId
  }

  def setMchtId(mchtId: String) {
    this.mchtId = mchtId
  }

  @Column(name = "TO_USR_NAME", length = 256)
  def getToUsrName: String = {
    toUsrName
  }

  def setToUsrName(toUsrName: String) {
    this.toUsrName = toUsrName
  }

  @Column(name = "FROM_USR_NAME", length = 256)
  def getFromUsrName: String = {
    fromUsrName
  }

  def setFromUsrName(fromUsrName: String) {
    this.fromUsrName = fromUsrName
  }

  @Column(name = "MSG_ID", length = 64)
  def getMsgId: String = {
    msgId
  }

  def setMsgId(msgId: String) {
    this.msgId = msgId
  }

  @Column(name = "CREATE_TIME", length = 14)
  def getCreateTime: String = {
    createTime
  }

  def setCreateTime(createTime: String) {
    this.createTime = createTime
  }

  @Column(name = "MSG_TYPE", length = 32)
  def getMsgType: String = {
    msgType
  }

  def setMsgType(msgType: String) {
    this.msgType = msgType
  }

  @Column(name = "CONTENT", length = 2048)
  def getContent: String = {
    content
  }

  def setContent(content: String) {
    this.content = content
  }

  @Column(name = "PIC_URL", length = 256)
  def getPicUrl: String = {
    picUrl
  }

  def setPicUrl(picUrl: String) {
    this.picUrl = picUrl
  }

  @Column(name = "FORMAT", length = 64)
  def getFormat: String = {
    format
  }

  def setFormat(format: String) {
    this.format = format
  }

  @Column(name = "MEDIA_ID", length = 128)
  def getMediaId: String = {
    mediaId
  }

  def setMediaId(mediaId: String) {
    this.mediaId = mediaId
  }

  @Column(name = "THUMB_MEDIA_ID", length = 128)
  def getThumbMediaId: String = {
    thumbMediaId
  }

  def setThumbMediaId(thumbMediaId: String) {
    this.thumbMediaId = thumbMediaId
  }

  @Column(name = "LOCATION_X", length = 256)
  def getLocationX: String = {
    locationX
  }

  def setLocationX(locationX: String) {
    this.locationX = locationX
  }

  @Column(name = "LOCATION_Y", length = 256)
  def getLocationY: String = {
    locationY
  }

  def setLocationY(locationY: String) {
    this.locationY = locationY
  }

  @Column(name = "SCALE", length = 256)
  def getScale: String = {
    scale
  }

  def setScale(scale: String) {
    this.scale = scale
  }

  @Column(name = "LABEL", length = 256)
  def getLabel: String = {
    label
  }

  def setLabel(label: String) {
    this.label = label
  }

  @Column(name = "TITLE", length = 64)
  def getTitle: String = {
    title
  }

  def setTitle(title: String) {
    this.title = title
  }

  @Column(name = "DESCRIPTION", length = 1024)
  def getDescription: String = {
    description
  }

  def setDescription(description: String) {
    this.description = description
  }

  @Column(name = "URL", length = 256)
  def getUrl: String = {
    url
  }

  def setUrl(url: String) {
    this.url = url
  }

  @Column(name = "EVENT_TYPE", length = 256)
  def getEventType: String = {
    eventType
  }

  def setEventType(eventType: String) {
    this.eventType = eventType
  }

  @Column(name = "EVENT_KAY", length = 256)
  def getEventKey: String = {
    eventKey
  }

  def setEventKey(eventKey: String) {
    this.eventKey = eventKey
  }

  @Column(name = "TICKET", length = 256)
  def getTicket: String = {
    ticket
  }

  def setTicket(ticket: String) {
    this.ticket = ticket
  }

  @Column(name = "RESP_CONTENT", length = 64)
  def getRespContent: String = {
    respContent
  }

  def setRespContent(respContent: String) {
    this.respContent = respContent
  }

  @Column(name = "LATITUDE", length = 64)
  def getLatitude: String = {
    latitude
  }

  def setLatitude(latitude: String) {
    this.latitude = latitude
  }

  @Column(name = "LONGITUDE", length = 64)
  def getLongitude: String = {
    longitude
  }

  def setLongitude(longitude: String) {
    this.longitude = longitude
  }

  @Column(name = "PRECISE", length = 64)
  def getPrecise: String = {
    precise
  }

  def setPrecise(precise: String) {
    this.precise = precise
  }
}

