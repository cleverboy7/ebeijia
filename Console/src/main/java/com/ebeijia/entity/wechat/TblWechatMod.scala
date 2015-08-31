package com.ebeijia.entity.wechat

import javax.persistence._

/**
 * TblWechatMod
 * @author zhicheng.xu
 *         15/8/12
 */


@Entity
@Table(name = "TBL_WECHAT_MOD")
@SerialVersionUID(1L)
class TblWechatMod extends java.io.Serializable {
  private var modId: String = null
  private var modName: String = null
  private var mchtId: String = null
  private var actType: String = null
  private var url: String = null


  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "MOD_ID", nullable = false, length = 20) def getModId: String = {
    modId
  }

  def setModId(modId: String) {
    this.modId = modId
  }

  @Column(name = "URL", length = 256) def getUrl: String = {
    url
  }

  @Column(name = "MCHT_ID", length = 15) def getMchtId: String = {
    mchtId
  }

  def setMchtId(mchtId: String) {
    this.mchtId = mchtId
  }

  @Column(name = "ACT_TYPE", length = 1) def getActType: String = {
    actType
  }

  def setActType(actType: String) {
    this.actType = actType
  }

  def setUrl(url: String) {
    this.url = url
  }

  @Column(name = "MOD_NAME", length = 64) def getModName: String = {
    modName
  }

  def setModName(modName: String) {
    this.modName = modName
  }
}

