package com.ebeijia.entity.wechat

import javax.persistence._

/**
 * TblArticlesInf
 * @author zhicheng.xu
 *         15/8/12
 */


@Entity
@Table(name = "TBL_ARTICLES_INF")
@SerialVersionUID(1L)
class TblArticlesInf extends java.io.Serializable {
  private var id: String = null
  private var title: String = null
  private var description: String = null
  private var picUrl: String = null
  private var url: String = null


  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID", nullable = false, length = 20) def getId: String = {
    id
  }

  def setId(id: String) {
    this.id = id
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
}

