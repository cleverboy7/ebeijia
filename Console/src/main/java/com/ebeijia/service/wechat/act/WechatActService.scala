package com.ebeijia.service.wechat.act
import java.util.Map
import com.ebeijia.entity.wechat.TblWechatAct
/**
 * WechatActService
 * @author zhicheng.xu
 *         15/8/13
 */



trait WechatActService {
  def save(data: TblWechatAct): String

  def upd(data: TblWechatAct): String

  def del(id: String): String

  def getById(id: String): TblWechatAct

  def findBySql(mchtId: String, pageData: String): Map[String, AnyRef]

  def getAct(id: String): TblWechatAct
}
