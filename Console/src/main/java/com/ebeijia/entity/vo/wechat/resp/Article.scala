package com.ebeijia.entity.vo.wechat.resp

/**
 * Article图文model
 * @author zhicheng.xu
 *         15/8/12
 */
class Article {
  private var Title: String = null
  private var Description: String = null
  private var PicUrl: String = null
  private var Url: String = null

  def getTitle: String = {
    Title
  }

  def setTitle(title: String) {
    Title = title
  }

  def getDescription: String = {
    if (null == Description) "" else Description
  }

  def setDescription(description: String) {
    Description = description
  }

  def getPicUrl: String = {
    if (null == PicUrl) "" else PicUrl
  }

  def setPicUrl(picUrl: String) {
    PicUrl = picUrl
  }

  def getUrl: String = {
    if (null == Url) "" else Url
  }

  def setUrl(url: String) {
    Url = url
  }
  
}

