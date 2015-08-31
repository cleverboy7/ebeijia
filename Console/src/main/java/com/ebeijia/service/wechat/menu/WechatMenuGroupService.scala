package com.ebeijia.service.wechat.menu

import java.util.{List, Map}
import com.ebeijia.entity.wechat.TblWechatGroup

/**
 * WechatMenuGroupService
 * @author zhicheng.xu
 *         15/8/14
 */


trait WechatMenuGroupService {
  def find(mchtId: String): List[TblWechatGroup]

  def listFind(mchtId: String): Map[String, AnyRef]
}
