package com.ebeijia.service.wechat.group

import java.util.Map

import com.ebeijia.entity.wechat.TblWechatSubGroup

/**
 * WechatSubGroupService
 * @author zhicheng.xu
 *         15/8/13
 */


trait WechatSubGroupService {
  def save(mchtId: String, name: String): String

  def update(mchtId: String, groupId: String, name: String): String

  def del(mchtId: String, groupId: String): String

  def findCount(mchtId: String): Int

  def findBySql(name: String, pageData: String): Map[String, AnyRef]

  def listGroupByMchtId(mchtId: String): Map[String, AnyRef]

  def getById(id: String): TblWechatSubGroup
}
