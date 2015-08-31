package com.ebeijia.entity

/**
 * TblUsrBehavPoint
 * @author zhicheng.xu
 *         15/8/11
 */

import javax.persistence.{Column, Entity, Id, Table}

@Entity
@Table(name = "TBL_USR_BEHAV_POINT")
@SerialVersionUID(1L)
class TblUsrBehavPoint extends java.io.Serializable {
  private var txnCode: String = null
  private var txnName: String = null
  private var flag: String = null
  private var point: Integer = null


  def this(txnCode: String, txnName: String, flag: String, point: Integer) {
    this()
    this.txnCode = txnCode
    this.txnName = txnName
    this.flag = flag
    this.point = point
  }

  @Id
  @Column(name = "TXN_CODE", length = 6) def getTxnCode: String = {
    txnCode
  }

  def setTxnCode(txnCode: String) {
    this.txnCode = txnCode
  }

  @Column(name = "TXN_NAME", length = 32) def getTxnName: String = {
    txnName
  }

  def setTxnName(txnName: String) {
    this.txnName = txnName
  }

  @Column(name = "FLAG", length = 1) def getFlag: String = {
    flag
  }

  def setFlag(flag: String) {
    this.flag = flag
  }

  @Column(name = "POINT", length = 10) def getPoint: Integer = {
    point
  }

  def setPoint(point: Integer) {
    this.point = point
  }

  override def toString: String = {
    "TblUsrBehavPoint [txnCode=" + txnCode + ", txnName=" + txnName + ", flag=" + flag + ", point=" + point + "]"
  }
}

