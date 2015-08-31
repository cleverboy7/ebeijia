package com.ebeijia.entity

import java.io.Serializable
import javax.persistence.{AttributeOverride, AttributeOverrides, Column, EmbeddedId, Entity, Table}

/**
 * TblRoleMap
 * @author zhicheng.xu
 *         15/8/11
 */


@Entity
@Table(name = "TBL_ROLE_MAP")
@SerialVersionUID(1L)
class TblRoleMap extends Serializable {
  private var id: TblRoleMapId = null

  @EmbeddedId
  @AttributeOverrides(Array(new AttributeOverride(name = "roleId", column = new Column(name = "ROLE_ID", nullable = false, length = 6)), new AttributeOverride(name = "funcId", column = new Column(name = "FUNC_ID", nullable = false, length = 6)))) def getId: TblRoleMapId = {
    id
  }

  def setId(id: TblRoleMapId) {
    this.id = id
  }
}
