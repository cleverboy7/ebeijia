package com.ebeijia.service.txnLog
import java.util.List
import java.util.Map
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import com.ebeijia.dao.txnLog.TxnLogDao
import com.ebeijia.entity.TblTxnLog
/**
 * TxnLogServiceImpl
 * @author zhicheng.xu
 *         15/8/13
 */



@Service final class TxnLogServiceImpl extends TxnLogService {
  @Autowired
  private val txnLogDao: TxnLogDao = null

  @Transactional def updateTxnLog(txnLog: TblTxnLog) {
    txnLogDao.saveOrUpdate(txnLog)
  }

  @Transactional def deleteTxnLog(txnLog: TblTxnLog) {
    txnLogDao.update(txnLog)
  }

  @Transactional def addTxnLog(txnLog: TblTxnLog) {
    txnLogDao.saveOrUpdate(txnLog)
  }

  @Transactional def queryTxnLogList: List[_] = {
    txnLogDao.getTxnLogList
  }

  @Transactional def countTotalNum(txnLog: TblTxnLog): Int = {
    txnLogDao.countTotalNum(txnLog)
  }

  @Transactional def queryTxnLogById(id: Int): TblTxnLog = {
    txnLogDao.getById(id)
  }

  @Transactional def findBySql(date: String, txnChl: String, status: String, pageData: String): Map[String, AnyRef] = {
    val query: StringBuilder = new StringBuilder
    query.append("from TblTxnLog ")
    query.append(" where 1=1")
    if (date != null && !("" == date)) {
    }
    if (txnChl != null && !("" == txnChl)) {
      query.append(" AND txnChl ='").append(txnChl).append("' ")
    }
    if (status != null && !("" == status)) {
      query.append(" AND txnStatus ='").append(status).append("' ")
    }
    query.append(" order by txnNo desc")
    txnLogDao.findByPage(query.toString, pageData)
  }
}
