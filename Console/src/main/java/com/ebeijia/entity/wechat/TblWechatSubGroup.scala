package com.ebeijia.entity.wechat

import javax.persistence.{AttributeOverride, AttributeOverrides, Column, EmbeddedId, Entity, Table}
/**
 * TblWechatSubGroup
 * @author zhicheng.xu
 *         15/8/12
 */


@Entity
@Table(name = "TBL_WECHAT_SUB_GROUP")
@SerialVersionUID(1L)
class TblWechatSubGroup extends java.io.Serializable {
  private var id: TblWechatSubGroupId = null
  private var name: String = null



  @EmbeddedId
  @AttributeOverrides(Array(new AttributeOverride(name = "groupId", column = new Column(name = "GROUP_ID", nullable = false, length = 5)), new AttributeOverride(name = "mchtId", column = new Column(name = "MCHT_ID", nullable = false, length = 5)))) def getId: TblWechatSubGroupId = {
    id
  }

  def setId(id: TblWechatSubGroupId) {
    this.id = id
  }

  @Column(name = "NAME", length = 32) def getName: String = {
    name
  }

  def setName(name: String) {
    this.name = name
  }
}

