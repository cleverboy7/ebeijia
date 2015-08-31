package com.ebeijia.entity.wechat

import javax.persistence.{AttributeOverride, AttributeOverrides, Column, EmbeddedId, Entity, Table}

/**
 * TblWechatSn
 * @author zhicheng.xu
 *         15/8/12
 */

@Entity
@Table(name = "TBL_WECHAT_SN")
@SerialVersionUID(1L)
class TblWechatSn extends java.io.Serializable {
  private var id: TblWechatSnId = null
  private var actType: String = null



  @EmbeddedId
  @AttributeOverrides(Array(new AttributeOverride(name = "snCode", column = new Column(name = "SN_CODE", nullable = false, length = 20)), new AttributeOverride(name = "mchtId", column = new Column(name = "MCHT_ID", nullable = false, length = 15)))) def getId: TblWechatSnId = {
    id
  }

  def setId(id: TblWechatSnId) {
    this.id = id
  }

  @Column(name = "ACT_TYPE", length = 1) def getActType: String = {
    actType
  }

  def setActType(actType: String) {
    this.actType = actType
  }
}

