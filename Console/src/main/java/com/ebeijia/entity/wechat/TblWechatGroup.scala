package com.ebeijia.entity.wechat

import javax.persistence.{Column, Entity, Id, Table}

/**
 * TblWechatGroup
 * @author zhicheng.xu
 *         15/8/12
 */


@Entity
@Table(name = "TBL_WECHAT_GROUP")
@SerialVersionUID(1L)
class TblWechatGroup extends java.io.Serializable {
  private var id: String = null
  private var name: String = null
  private var mchtId: String = null

  @Id
  @Column(name = "ID", unique = true, nullable = false, length = 3) def getId: String = {
    id
  }

  def setId(id: String) {
    this.id = id
  }

  @Column(name = "NAME", length = 10) def getName: String = {
    name
  }

  def setName(name: String) {
    this.name = name
  }

  @Column(name = "MCHT_ID", length = 15) def getMchtId: String = {
    mchtId
  }

  def setMchtId(mchtId: String) {
    this.mchtId = mchtId
  }
}

