package com.ebeijia.entity.wechat

import javax.persistence.{AttributeOverride, AttributeOverrides, Column, EmbeddedId, Entity, Table}

/**
 * TblWechatKf
 * @author zhicheng.xu
 *         15/8/12
 */


@Entity
@Table(name = "TBL_WECHAT_KF")
@SerialVersionUID(1L)
class TblWechatKf extends java.io.Serializable {
  private var id: TblWechatKfId = null
  private var kfNick: String = null
  private var kfPwd: String = null



  @EmbeddedId
  @AttributeOverrides(Array(new AttributeOverride(name = "kfId", column = new Column(name = "KF_ID", nullable = false, length = 32)), new AttributeOverride(name = "mchtId", column = new Column(name = "MCHT_ID", nullable = false, length = 32)))) def getId: TblWechatKfId = {
    id
  }

  def setId(id: TblWechatKfId) {
    this.id = id
  }

  @Column(name = "KF_NICK", length = 32) def getKfNick: String = {
    kfNick
  }

  def setKfNick(kfNick: String) {
    this.kfNick = kfNick
  }

  @Column(name = "KF_PWD", length = 32) def getKfPwd: String = {
    kfPwd
  }

  def setKfPwd(kfPwd: String) {
    this.kfPwd = kfPwd
  }
}

