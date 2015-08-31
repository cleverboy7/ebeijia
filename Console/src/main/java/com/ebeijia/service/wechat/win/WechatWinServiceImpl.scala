package com.ebeijia.service.wechat.win

import java.util.{Iterator, List, Map}

import com.ebeijia.dao.wechat.WechatWinDao
import com.ebeijia.entity.wechat.{TblWechatAct, TblWechatMchtInf, TblWechatWinning}
import com.ebeijia.service.wechat.act.WechatActService
import com.ebeijia.service.wechat.core.WechatMchtInfService
import org.ebeijia.tools.DateTime4J
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * WechatWinServiceImpl
 * @author zhicheng.xu
 *         15/8/14
 */


@Service
final class WechatWinServiceImpl extends WechatWinService {
  @Autowired
  private val wechatWinDao: WechatWinDao = null
  @Autowired
  private val wechatMchtInfService: WechatMchtInfService = null
  @Autowired
  private val wechatActService: WechatActService = null
  @Autowired
  private val wechatSnService: WechatSnService = null

  @Transactional
  def save(data: TblWechatWinning): String = {
    data.setCreateDate(DateTime4J.getCurrentDate)
    val snCode: String = wechatSnService.save(data.getMchtId, data.getActType)
    wechatWinDao.save(data)
    data.setSnCode(snCode)
    null
  }

  @Transactional
  def upd(data: TblWechatWinning): String = {
    wechatWinDao.update(data)
    null
  }

  @Transactional
  def findBySql(mchtId: String, pageData: String): Map[String, AnyRef] = {
    val query: StringBuilder = new StringBuilder
    query.append(" from TblWechatWinning ")
    query.append(" where 1=1")
    if (mchtId != null && !("" == mchtId)) {
      query.append(" AND mchtId like '%").append(mchtId).append("%'")
    }
    query.append(" order by beginDate")
    wechatWinDao.findByPage(query.toString, pageData)
  }

  @Transactional
  def winCheck(actId: String, openid: String): TblWechatWinning = {
    val sb: StringBuilder = new StringBuilder
    sb.append("from TblWechatWinning where actId = '").append(actId).append("'")
    sb.append(" and openid= '").append(openid).append("'")
    val list: List[TblWechatWinning] = wechatWinDao.find(sb.toString)
    if (list.isEmpty) {
      null
    }
    else {
      list.get(0)
    }
  }

  @Transactional
  def del(id: String): String = {
    wechatWinDao.deleteById(id)
    null
  }

  @Transactional
  def getById(id: String): TblWechatWinning = {
    wechatWinDao.getById(id)
  }

  def check(openid: String, appid: String, actId: String, `type`: String): String = {
    val mchtInf: TblWechatMchtInf = wechatMchtInfService.getByAppid(appid)
    if (mchtInf == null) {
      return "appid不存在,请检查该appid是否绑定"
    }
    val actInf: TblWechatAct = wechatActService.getAct(actId)
    if (actInf == null) {
      return "活动不存在或已经失效"
    }
    val winInf: TblWechatWinning = this.winCheck(actId, openid)
    if (winInf != null) {
      return "该用户已经中过奖，不再具备抽奖资格"
    }
    null
  }

  def calculation(openid: String, appid: String, actId: String, `type`: String): String = {
    val sb: StringBuilder = new StringBuilder
    sb.append("select r.probability,r.pr_name,r.prize,r.win_num from tbl_wechat_act a ,tbl_wechat_mod m ,tbl_wechat_rule r ")
    sb.append(" where a.mod_id = m.mod_id and a.act_id ='").append(actId).append("'")
    sb.append(" and r.mod_id =r.mod_id and win_num <( select count(*) from tbl_wechat_winning w where w.act_id = '").append(actId).append("'")
    sb.append(" and w.mod_id = r.mod_id)")
    val l: List[_] = wechatWinDao.find(sb.toString)
    var probability: Double = 0
    val it: Iterator[_] = l.iterator
    while (it.hasNext) {
      val o: Array[AnyRef] = it.next.asInstanceOf[Array[AnyRef]]
      val tmp: Double = o(0).asInstanceOf[Double]
      probability += tmp
    }
    var x: Double = (Math.random * probability).toDouble
    val it2: Iterator[_] = l.iterator
    while (it2.hasNext) {
      val o: Array[AnyRef] = it.next.asInstanceOf[Array[AnyRef]]
      val tmp: Double = o(0).asInstanceOf[Double]
      if (x - tmp <= 0) {
        o(1).toString
      }
      else {
        x -= tmp
      }
    }
    null
  }

}