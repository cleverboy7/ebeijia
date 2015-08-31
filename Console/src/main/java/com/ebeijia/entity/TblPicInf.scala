package com.ebeijia.entity

import javax.persistence._

/**
 * TblPicInf
 * @author zhicheng.xu
 *         15/8/11
 */


@Entity
@Table(name = "TBL_PIC_INF") class TblPicInf {
  private var picId: String = null
  private var picType: String = null
  private var picName: String = null
  private var picUrl: String = null


  def this(picId: String, picType: String, picName: String, picUrl: String) {
    this()
    this.picId = picId
    this.picType = picType
    this.picName = picName
    this.picUrl = picUrl
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "PIC_ID", unique = true, nullable = false, length = 10) def getPicId: String = {
    picId
  }

  def setPicId(picId: String) {
    this.picId = picId
  }

  @Column(name = "PIC_TYPE", length = 2) def getPicType: String = {
    picType
  }

  def setPicType(picType: String) {
    this.picType = picType
  }

  @Column(name = "PIC_NAME", length = 64) def getPicName: String = {
    picName
  }

  def setPicName(picName: String) {
    this.picName = picName
  }

  @Column(name = "PIC_URL", length = 128) def getPicUrl: String = {
    picUrl
  }

  def setPicUrl(picUrl: String) {
    this.picUrl = picUrl
  }
}

