package com.ebeijia.service.wechat.menu

/**
 * WechatMenuService
 * @author zhicheng.xu
 *         15/8/14
 */

import java.util.List
import java.util.Map
import com.ebeijia.entity.vo.wechat.Menu
import com.ebeijia.entity.wechat.TblWechatMenu

trait WechatMenuService {
  def updateWechatMenu(wechatMenu: TblWechatMenu)

  def save(wechatMenu: TblWechatMenu)

  def deleteMenuById(menuId: String): String

  def findByHql(menuKey: String, mchtId: String): List[TblWechatMenu]

  def modifyMenuById(tblWechatMenu: TblWechatMenu)

  def SynchToMenu(mchtId: String, groupId: String): Menu

  def findBySql(mchtId: String, groupId: String, aoData: String): Map[String, AnyRef]

  def listFind(mchtId: String, groupId: String): Map[String, AnyRef]

  def getById(id: String): TblWechatMenu
}
