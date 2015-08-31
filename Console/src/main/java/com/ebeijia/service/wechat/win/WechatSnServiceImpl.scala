package com.ebeijia.service.wechat.win

import java.util.Random

import com.ebeijia.dao.wechat.WechatSnDao
import com.ebeijia.entity.wechat.{TblWechatSn, TblWechatSnId}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * WechatSnServiceImpl
 * @author zhicheng.xu
 *         15/8/14
 */


@Service
final class WechatSnServiceImpl extends WechatSnService {
  @Autowired
  private val wechatSnDao: WechatSnDao = null

  @Transactional
  def save(mchtId: String, actType: String): String = {
    val data: TblWechatSn = new TblWechatSn
    val id: TblWechatSnId = new TblWechatSnId
    id.setMchtId(mchtId)
    id.setSnCode(evNo)
    var tmp: Int = 1
    while (tmp == 1) {
      if (wechatSnDao.getById(id) == null) {
        tmp = 2
      }
    }
    wechatSnDao.save(data)
    id.getSnCode
  }

  @Transactional
  def getById(id: TblWechatSnId): TblWechatSn = {
    wechatSnDao.getById(id)
  }

  private def evNo: String = {
    val base: String = "0123456789"
    val random: Random = new Random
    val sb: StringBuffer = new StringBuffer
    for (i <- 0 to 9) {
      val number: Int = random.nextInt(base.length)
      sb.append(base.charAt(number))
    }
    sb.toString
  }
}
