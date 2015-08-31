package com.ebeijia.service.wechat.menu

import java.util.{Iterator, LinkedHashMap, LinkedList, List, Map}

import com.ebeijia.dao.wechat.WechatMenuDao
import com.ebeijia.entity.vo.wechat.{AccessToken, Button, CommonButton, ComplexButton, Menu, ViewButton}
import com.ebeijia.entity.wechat.{TblWechatMchtInf, TblWechatMenu}
import com.ebeijia.service.wechat.core.{WechatMchtInfService, WechatTokenService}
import com.ebeijia.service.wechat.inter.MenuManager
import com.ebeijia.util.StringUtil
import com.ebeijia.util.wechat.WechatUtil
import net.sf.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.{CacheEvict, Cacheable}
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * WechatMenuServiceImpl
 * @author zhicheng.xu
 *         15/8/14
 */


@Service
final class WechatMenuServiceImpl extends WechatMenuService {
  @Autowired
  private val wechatMenuDao: WechatMenuDao = null
  @Autowired
  private val wechatMchtInfService: WechatMchtInfService = null
  @Autowired
  private val wechatTokenService: WechatTokenService = null
  @Autowired
  private val menuManager: MenuManager = null

  @Transactional
  @CacheEvict(value = Array("wechatMenuCache"), allEntries = true)
  def updateWechatMenu(wechatMenu: TblWechatMenu) {
    wechatMenuDao.saveOrUpdate(wechatMenu)
  }

  @Transactional
  @CacheEvict(value = Array("wechatMenuCache"), allEntries = true)
  def save(wechatMenu: TblWechatMenu) {
    wechatMenuDao.save(wechatMenu)
  }

  @Transactional
  @CacheEvict(value = Array("wechatMenuCache"), allEntries = true)
  def deleteWechatMenu(wechatMenu: TblWechatMenu) {
    wechatMenuDao.update(wechatMenu)
  }

  @Transactional
  @CacheEvict(value = Array("wechatMenuCache"), allEntries = true)
  def addWechatMenu(wechatMenu: TblWechatMenu) {
    wechatMenuDao.saveOrUpdate(wechatMenu)
  }

  @Transactional
  @Cacheable(value = Array("wechatMenuCache"), key = "#root.method.name+#id")
  def getById(id: String): TblWechatMenu = {
    wechatMenuDao.getById(id)
  }

  @Transactional
  @Cacheable(value = Array("wechatMenuCache"), key = "#root.method.name")
  def queryWechatMenuList: List[TblWechatMenu] = {
    wechatMenuDao.getWechatMenuList
  }

  @Transactional
  @Cacheable(value = Array("wechatMenuCache"), key = "#root.method.name+#wechatMenu")
  def countTotalNum(wechatMenu: TblWechatMenu): Int = {
    wechatMenuDao.countTotalNum(wechatMenu)
  }

  @Transactional
  @Cacheable(value = Array("wechatMenuCache"), key = "#root.method.name+#id")
  def queryWechatMenuById(id: Int): TblWechatMenu = {
    wechatMenuDao.getById(id)
  }

  @Transactional
  @Cacheable(value = Array("wechatMenuCache"), key = "#root.method.name+#mchtId+#groupId")
  def SynchToMenu(mchtId: String, groupId: String): Menu = {
    val tblWechatMchtInf: TblWechatMchtInf = wechatMchtInfService.getById(mchtId)
    val at: AccessToken = wechatTokenService.getAccessToken(tblWechatMchtInf.getAppid, tblWechatMchtInf.getAppsecret)
    var menu: Menu = new Menu
    var jsonObject: JSONObject = null
    var result: String = null
    if (null != at) {
      menu = getMenu(mchtId, groupId)
      jsonObject = menuManager.createMenu(menu, at.getToken)
      if (null != jsonObject) {
        if (WechatUtil.getWechatCallBackStatus(jsonObject.toString) != 0) {
          result = jsonObject.getString("errcode")
        }
        else {
          menu
        }
      }
      else {
        result = "8888888"
      }
    }
    else {
      result = "9999999"
    }
    menu
  }

  @Transactional
  @Cacheable(value = Array("wechatMenuCache"), key = "#root.method.name+#menuKey+#mchtId")
  def findByHql(menuKey: String, mchtId: String): List[TblWechatMenu] = {
    val hql: String = "FROM TblWechatMenu WHERE menuKey = '" + menuKey + "'"
    wechatMenuDao.find(hql)
  }

  /**
   * 从DB中查出的菜单转换为微信API格式
   * @param groupId 菜单组
   * @param mchtId 商户号
   * @return
   */
  @Transactional
  @Cacheable(value = Array("wechatMenuCache"), key = "#root.method.name+#mchtId+#groupId")
  private def getMenu(mchtId: String, groupId: String): Menu = {
    val sb: StringBuilder = new StringBuilder
    sb.append("from TblWechatMenu where mchtId = '").append(mchtId).append("' ")
    sb.append(" and  groupId ='").append(groupId).append("' order by orderNo")
    val listMenu: List[TblWechatMenu] = wechatMenuDao.find(sb.toString)
    val mapFir: Map[String, TblWechatMenu] = this.tblWechatFirMenuFormate(listMenu)
    val mapSec: Map[String, TblWechatMenu] = this.tblWechatSecMenuFormate(listMenu)
    var flag: Boolean = false
    val menu: Menu = new Menu
    val button: Array[Button] = new Array[Button](mapFir.size)
    val interator: Iterator[String] = mapFir.keySet.iterator
    var i: Int = 0
    var count: Int = 0
    while (interator.hasNext) {
      val key: String = interator.next
      val rootMenu: TblWechatMenu = mapFir.get(key)
      val interatorSec: Iterator[String] = mapSec.keySet.iterator
      while (interatorSec.hasNext) {
        val keySec: String = interatorSec.next
        if (mapSec.get(keySec).getParentId == rootMenu.getMenuId) {
          flag = true
          count += 1
        }
      }
      if (!flag) {
        if ("1" == rootMenu.getType) {
          val btn: CommonButton = new CommonButton
          btn.setType("click")
          btn.setName(rootMenu.getMenuName)
          btn.setKey(rootMenu.getMenuKey)
          button(i) = btn
        } else if ("2" == rootMenu.getType) {
          val btn: ViewButton = new ViewButton
          btn.setType("view")
          btn.setName(rootMenu.getMenuName)
          btn.setUrl(rootMenu.getUrl)
          button(i) = btn
        }
      } else {
        val rootBtn: ComplexButton = new ComplexButton
        rootBtn.setName(rootMenu.getMenuName)
        val subButton: Array[Button] = new Array[Button](count)
        val interatorMapSec: Iterator[String] = mapSec.keySet.iterator
        var j: Int = 0
        while (interatorMapSec.hasNext) {
          val keyMapSec: String = interatorMapSec.next
          if (mapSec.get(keyMapSec).getParentId == rootMenu.getMenuId) {
            val subMenu: TblWechatMenu = mapSec.get(keyMapSec)
            if ("1" == subMenu.getType) {
              val btn: CommonButton = new CommonButton
              btn.setType("click")
              btn.setName(subMenu.getMenuName)
              btn.setKey(subMenu.getMenuKey)
              subButton(j) = btn
            } else if ("2" == subMenu.getType) {
              val btn: ViewButton = new ViewButton
              btn.setType("view")
              btn.setName(subMenu.getMenuName)
              btn.setUrl(subMenu.getUrl)
              subButton(j) = btn
            }
            j += 1
          }
        }
        rootBtn.setSub_button(subButton)
        button(i) = rootBtn
      }
      i += 1
      flag = false
      count = 0
    }
    menu.setButton(button)
    menu
  }

  @Transactional
  @CacheEvict(value = Array("wechatMenuCache"), allEntries = true)
  def modifyMenuById(tblWechatMenu: TblWechatMenu) {
    wechatMenuDao.update(tblWechatMenu)
  }

  @Transactional
  @CacheEvict(value = Array("wechatMenuCache"), allEntries = true)
  def deleteMenuById(menuId: String): String = {
    val tblWechatMenu: TblWechatMenu = wechatMenuDao.getById(menuId)
    val mchtId: String = tblWechatMenu.getMchtId
    var result: String = null
    if (tblWechatMenu.getParentId == "-") {
      val sql: String = "from TblWechatMenu where parentId = '" + tblWechatMenu.getMenuId + "' and mchtId = '" + mchtId + "'"
      val list: List[TblWechatMenu] = wechatMenuDao.find(sql)
      if (list != null && !list.isEmpty) {
        result = "没有找到此菜单"
      }
      val sqlDeleteMenu: String = "delete from TblWechatMenu where mchtId = '" + mchtId + "' and " + "parentId = '" + menuId + "'"
      wechatMenuDao.updateAll(sqlDeleteMenu)
    }
    wechatMenuDao.delete(tblWechatMenu)
    result
  }

  @Transactional
  @Cacheable(value = Array("wechatMenuCache"))
  def findBySql(mchtId: String, groupId: String, pagaData: String): Map[String, AnyRef] = {
    val query: StringBuilder = new StringBuilder
    query.append("from TblWechatMenu")
    query.append(" where 1=1")
    if (mchtId != null && !("" == mchtId)) {
      query.append(" AND id.mchtId like '%").append(mchtId).append("%'")
    }
    if (groupId != null && !("" == groupId)) {
      query.append(" AND groupId like '%").append(groupId).append("%'")
    }
    query.append(" ORDER BY groupId,orderNo,parentId")
    val m: Map[String, AnyRef] = wechatMenuDao.findByPage(query.toString, pagaData)
    m
  }

  @Transactional
  @Cacheable(value = Array("wechatMenuCache"), key = "#root.method.name+#mchtId+#groupId")
  def listFind(mchtId: String, groupId: String): Map[String, AnyRef] = {
    val hql: String = "FROM TblWechatMenu where mchtId = ? and groupId = ? and parentId = '-' ORDER BY menuId"
    val tblWechatMenu: List[TblWechatMenu] = wechatMenuDao.find(hql, mchtId, groupId)
    val list: List[AnyRef] = new LinkedList[AnyRef]
    val map: Map[String, AnyRef] = new LinkedHashMap[String, AnyRef]
    import scala.collection.JavaConversions._
    for (tblMenu <- tblWechatMenu) {
      val hashMap: Map[String, AnyRef] = new LinkedHashMap[String, AnyRef]
      hashMap.put("key", tblMenu.getMenuId + "-" + tblMenu.getMenuName)
      hashMap.put("value", tblMenu.getMenuId)
      list.add(hashMap)
    }
    map.put("info", list)
    map
  }

  /**
   * 微信菜单格式化一级菜单
   * @param menus 菜单
   * @return
   */
  private def tblWechatFirMenuFormate(menus: List[TblWechatMenu]): Map[String, TblWechatMenu] = {
    val map: Map[String, TblWechatMenu] = new LinkedHashMap[String, TblWechatMenu]
    import scala.collection.JavaConversions._
    for (menu <- menus) {
      if (null == menu.getParentId || (menu.getParentId == "-")) {
        map.put(menu.getMenuId, menu)
      }
    }
    map
  }

  /**
   * 微信菜单格式化二级菜单
   * @param menus
   * @return
   */
  private def tblWechatSecMenuFormate(menus: List[TblWechatMenu]): Map[String, TblWechatMenu] = {
    val map: Map[String, TblWechatMenu] = new LinkedHashMap[String, TblWechatMenu]
    import scala.collection.JavaConversions._
    for (menu <- menus) {
      if (!(menu.getParentId == "-")) {
        map.put(menu.getMenuId, menu)
      }
    }
    map
  }
}
