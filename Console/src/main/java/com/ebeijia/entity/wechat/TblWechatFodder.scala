package com.ebeijia.entity.wechat

import javax.persistence.{Column, Entity, Id, Table}

/**
 * TblWechatFodder
 * @author zhicheng.xu
 *         15/8/12
 */


@Entity
@Table(name = "TBL_WECHAT_FODDER")
@SerialVersionUID(1L)
class TblWechatFodder extends java.io.Serializable {
  private var media: String = null
  private var mchtId: String = null
  private var name: String = null
  private var dsc: String = null
  private var `type`: String = null
  private var mediaType: String = null
  private var url: String = null
  private var wechatUrl: String = null
  private var createTime: String = null



  @Id
  @Column(name = "MEDIA", unique = true, nullable = false, length = 100) def getMedia: String = {
    media
  }

  def setMedia(media: String) {
    this.media = media
  }

  @Column(name = "MCHT_ID", nullable = false, length = 15) def getMchtId: String = {
    mchtId
  }

  def setMchtId(mchtId: String) {
    this.mchtId = mchtId
  }

  @Column(name = "NAME", nullable = false, length = 30) def getName: String = {
    name
  }

  def setName(name: String) {
    this.name = name
  }

  @Column(name = "DSC", nullable = false, length = 64) def getDsc: String = {
    dsc
  }

  def setDsc(dsc: String) {
    this.dsc = dsc
  }

  @Column(name = "TYPE", nullable = false, length = 5) def getType: String = {
    `type`
  }

  def setType(`type`: String) {
    this.`type` = `type`
  }

  @Column(name = "MEDIA_TYPE", nullable = false, length = 1) def getMediaType: String = {
    mediaType
  }

  def setMediaType(mediaType: String) {
    this.mediaType = mediaType
  }

  @Column(name = "URL", nullable = false, length = 128) def getUrl: String = {
    url
  }

  def setUrl(url: String) {
    this.url = url
  }

  @Column(name = "WECHAT_URL", nullable = false, length = 128) def getWechatUrl: String = {
    wechatUrl
  }

  def setWechatUrl(wechatUrl: String) {
    this.wechatUrl = wechatUrl
  }

  @Column(name = "CREATE_TIME", nullable = false, length = 14) def getCreateTime: String = {
    createTime
  }

  def setCreateTime(createTime: String) {
    this.createTime = createTime
  }
}

