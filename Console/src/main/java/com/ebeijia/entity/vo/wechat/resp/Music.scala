package com.ebeijia.entity.vo.wechat.resp

/**
 * Music音乐model
 * @author zhicheng.xu
 *         15/8/12
 */
class Music {
  private var Title: String = null
  private var Description: String = null
  private var MusicUrl: String = null
  private var HQMusicUrl: String = null
  private var ThumbMediaId: String = null

  def getTitle: String = {
    Title
  }

  def setTitle(title: String) {
    Title = title
  }

  def getDescription: String = {
    Description
  }

  def setDescription(description: String) {
    Description = description
  }

  def getMusicUrl: String = {
    MusicUrl
  }

  def setMusicUrl(musicUrl: String) {
    MusicUrl = musicUrl
  }

  def getHQMusicUrl: String = {
    HQMusicUrl
  }

  def setHQMusicUrl(musicUrl: String) {
    HQMusicUrl = musicUrl
  }

  def getThumbMediaId: String = {
    ThumbMediaId
  }

  def setThumbMediaId(thumbMediaId: String) {
    ThumbMediaId = thumbMediaId
  }
}

