package com.ebeijia.entity

import java.io.Serializable
import javax.persistence.{Column, Entity, Id, Table}

/**
 * TblDictInf
 * @author zhicheng.xu
 *         15/8/11
 */


@Entity
@Table(name = "TBL_DICT_INF")
@SerialVersionUID(1L)
class TblDictInf extends Serializable {
  private var dictId: String = null
  private var dictType: String = null
  private var fieldType: String = null
  private var fieldName: String = null

  @Id
  @Column(name = "`DICT_ID", unique = true, nullable = false, length = 5)
  def getDictId: String = {
    dictId
  }

  def setDictId(dictId: String) {
    this.dictId = dictId
  }

  @Column(name = "DICT_TYPE", unique = true, nullable = false, length = 5)
  def getDictType: String = {
    dictType
  }

  def setDictType(dictType: String) {
    this.dictType = dictType
  }

  @Column(name = "FIELD_TYPE", nullable = false, length = 16)
  def getFieldType: String = {
    fieldType
  }

  def setFieldType(fieldType: String) {
    this.fieldType = fieldType
  }

  @Column(name = "FIELD_NAME", nullable = false, length = 64)
  def getFieldName: String = {
    fieldName
  }

  def setFieldName(fieldName: String) {
    this.fieldName = fieldName
  }
}

