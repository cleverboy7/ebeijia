package com.ebeijia.service.txnLog
import java.util.List
import java.util.Map
import com.ebeijia.entity.TblTxnLog
/**
 * TxnLogService
 * @author zhicheng.xu
 *         15/8/13
 */



trait TxnLogService {
  def updateTxnLog(txnLog: TblTxnLog)

  def deleteTxnLog(txnLog: TblTxnLog)

  def addTxnLog(txnLog: TblTxnLog)

  def queryTxnLogList: List[_]

  def countTotalNum(txnLog: TblTxnLog): Int

  def queryTxnLogById(id: Int): TblTxnLog

  def findBySql(date: String, txnChl: String, status: String, pageData: String): Map[String, AnyRef]
}
