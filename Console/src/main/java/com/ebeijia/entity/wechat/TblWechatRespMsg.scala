package com.ebeijia.entity.wechat

import javax.persistence._

/**
 * TblWechatRespMsg
 * @author zhicheng.xu
 *         15/8/12
 */


@Entity
@Table(name = "TBL_WECHAT_RESP_MSG")
@SerialVersionUID(1L)
class TblWechatRespMsg extends java.io.Serializable {
  private var respMsgId: String = null
  private var mchtId: String = null
  private var keywords: String = null
  private var keywordType: String = null
  private var respType: String = null
  private var msgType: String = null
  private var content: String = null
  private var title: String = null
  private var description: String = null
  private var picUrl: String = null
  private var url: String = null
  private var mediaId: String = null
  private var articleIds: String = null
  private var musicUrl: String = null
  private var hqMusicUrl: String = null
  private var createTime: String = null
  private var updateTime: String = null



  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "RESP_MSG_ID", nullable = false, length = 20) def getRespMsgId: String = {
    respMsgId
  }

  def setRespMsgId(respMsgId: String) {
    this.respMsgId = respMsgId
  }

  @Column(name = "MCHT_ID", nullable = false, length = 15) def getMchtId: String = {
    mchtId
  }

  def setMchtId(mchtId: String) {
    this.mchtId = mchtId
  }

  @Column(name = "KEYWORDS", length = 32) def getKeywords: String = {
    keywords
  }

  def setKeywords(keywords: String) {
    this.keywords = keywords
  }

  @Column(name = "KEYWORD_TYPE", length = 1) def getKeywordType: String = {
    keywordType
  }

  def setKeywordType(keywordType: String) {
    this.keywordType = keywordType
  }

  @Column(name = "RESP_TYPE", length = 6) def getRespType: String = {
    respType
  }

  def setRespType(respType: String) {
    this.respType = respType
  }

  @Column(name = "MSG_TYPE", length = 25) def getMsgType: String = {
    msgType
  }

  def setMsgType(msgType: String) {
    this.msgType = msgType
  }

  @Column(name = "CONTENT", length = 1000) def getContent: String = {
    content
  }

  def setContent(content: String) {
    this.content = content
  }

  @Column(name = "TITLE", length = 64) def getTitle: String = {
    title
  }

  def setTitle(title: String) {
    this.title = title
  }

  @Column(name = "DESCRIPTION", length = 1024) def getDescription: String = {
    description
  }

  def setDescription(description: String) {
    this.description = description
  }

  @Column(name = "PIC_URL", length = 256) def getPicUrl: String = {
    picUrl
  }

  def setPicUrl(picUrl: String) {
    this.picUrl = picUrl
  }

  @Column(name = "URL", length = 256) def getUrl: String = {
    url
  }

  def setUrl(url: String) {
    this.url = url
  }

  @Column(name = "MEDIA_ID", length = 256) def getMediaId: String = {
    mediaId
  }

  def setMediaId(mediaId: String) {
    this.mediaId = mediaId
  }

  @Column(name = "ARTICLE_IDS", length = 256) def getArticleIds: String = {
    articleIds
  }

  def setArticleIds(articleIds: String) {
    this.articleIds = articleIds
  }

  @Column(name = "MUSIC_URL", length = 256) def getMusicUrl: String = {
    musicUrl
  }

  def setMusicUrl(musicUrl: String) {
    this.musicUrl = musicUrl
  }

  @Column(name = "HQ_MUSIC_URL", length = 256) def getHqMusicUrl: String = {
    hqMusicUrl
  }

  def setHqMusicUrl(hqMusicUrl: String) {
    this.hqMusicUrl = hqMusicUrl
  }

  @Column(name = "CREATE_TIME", length = 14) def getCreateTime: String = {
    createTime
  }

  def setCreateTime(createTime: String) {
    this.createTime = createTime
  }

  @Column(name = "UPDATE_TIME", length = 14) def getUpdateTime: String = {
    updateTime
  }

  def setUpdateTime(updateTime: String) {
    this.updateTime = updateTime
  }
}

