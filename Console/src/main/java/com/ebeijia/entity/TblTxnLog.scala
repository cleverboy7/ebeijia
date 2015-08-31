package com.ebeijia.entity

import java.io.Serializable
import javax.persistence._

import org.hibernate.annotations.GenericGenerator

/**
 * TblTxnLog
 * @author zhicheng.xu
 *         15/8/11
 */


@Entity
@Table(name = "TBL_TXN_LOG")
@SerialVersionUID(1L)
class TblTxnLog extends Serializable {
  private var txnNo: String = null
  private var txnDate: String = null
  private var txnTime: String = null
  private var operator: String = null
  private var remoteAddr: String = null
  private var operaTime: String = null
  private var txnStatus: String = null
  private var txnName: String = null
  private var txnChl: String = null
  private var txnError: String = null
  private var tlMisc: String = null

  /** full constructor */
  def this(txnNo: String, txnDate: String, txnTime: String, operator: String, remoteAddr: String, operaTime: String, txnStatus: String, txnName: String, txnChl: String, txnError: String, tlMisc: String) {
    this()
    this.txnNo = txnNo
    this.txnDate = txnDate
    this.txnTime = txnTime
    this.operator = operator
    this.remoteAddr = remoteAddr
    this.operaTime = operaTime
    this.txnStatus = txnStatus
    this.txnName = txnName
    this.txnChl = txnChl
    this.txnError = txnError
    this.tlMisc = tlMisc
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "TXN_NO", unique = true, nullable = false, length = 15)
  def getTxnNo: String = {
    txnNo
  }

  def setTxnNo(txnNo: String) {
    this.txnNo = txnNo
  }

  @Column(name = "TXN_DATE", nullable = false, length = 8)
  def getTxnDate: String = {
    txnDate
  }

  def setTxnDate(txnDate: String) {
    this.txnDate = txnDate
  }

  @Column(name = "TXN_TIME", nullable = false, length = 6)
  def getTxnTime: String = {
    txnTime
  }

  def setTxnTime(txnTime: String) {
    this.txnTime = txnTime
  }

  @Column(name = "OPERATOR", length = 32)
  def getOperator: String = {
    operator
  }

  def setOperator(operator: String) {
    this.operator = operator
  }

  @Column(name = "REMOTE_ADDR", length = 15) def getRemoteAddr: String = {
    remoteAddr
  }

  def setRemoteAddr(remoteAddr: String) {
    this.remoteAddr = remoteAddr
  }

  @Column(name = "OPERA_TIME", length = 7) def getOperaTime: String = {
    operaTime
  }

  def setOperaTime(operaTime: String) {
    this.operaTime = operaTime
  }

  @Column(name = "TXN_STATUS", length = 1) def getTxnStatus: String = {
    txnStatus
  }

  def setTxnStatus(txnStatus: String) {
    this.txnStatus = txnStatus
  }

  @Column(name = "TXN_NAME", length = 36) def getTxnName: String = {
    txnName
  }

  def setTxnName(txnName: String) {
    this.txnName = txnName
  }

  @Column(name = "TXN_CHL", length = 1) def getTxnChl: String = {
    txnChl
  }

  def setTxnChl(txnChl: String) {
    this.txnChl = txnChl
  }

  @Column(name = "TXN_ERROR", length = 128) def getTxnError: String = {
    txnError
  }

  def setTxnError(txnError: String) {
    this.txnError = txnError
  }

  @Column(name = "TL_MISC", length = 128) def getTlMisc: String = {
    tlMisc
  }

  def setTlMisc(tlMisc: String) {
    this.tlMisc = tlMisc
  }
}

