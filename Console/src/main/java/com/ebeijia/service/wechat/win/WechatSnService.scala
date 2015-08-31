package com.ebeijia.service.wechat.win

import com.ebeijia.entity.wechat.{TblWechatSn, TblWechatSnId}

/**
 * WechatSnService
 * @author zhicheng.xu
 *         15/8/14
 */


trait WechatSnService {
  def save(mchtId: String, actType: String): String

  def getById(id: TblWechatSnId): TblWechatSn
}