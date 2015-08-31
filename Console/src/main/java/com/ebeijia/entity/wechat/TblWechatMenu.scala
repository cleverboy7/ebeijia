package com.ebeijia.entity.wechat

import javax.persistence._

/**
 * TblWechatMenu
 * @author zhicheng.xu
 *         15/8/12
 */


@Entity
@Table(name = "TBL_WECHAT_MENU")
@SerialVersionUID(1L)
class TblWechatMenu extends java.io.Serializable {
  private var menuId: String = null
  private var mchtId: String = null
  private var orderNo: String = null
  private var menuName: String = null
  private var parentId: String = null
  private var `type`: String = null
  private var menuKey: String = null
  private var url: String = null
  private var createTime: String = null
  private var updateTime: String = null
  private var groupId: String = null


  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "MENU_ID", unique = true, nullable = false, length = 10) def getMenuId: String = {
    menuId
  }

  def setMenuId(menuId: String) {
    this.menuId = menuId
  }

  @Column(name = "MCHT_ID", nullable = false, length = 15) def getMchtId: String = {
    mchtId
  }

  def setMchtId(mchtId: String) {
    this.mchtId = mchtId
  }

  @Column(name = "ORDER_NO", length = 4) def getOrderNo: String = {
    orderNo
  }

  def setOrderNo(orderNo: String) {
    this.orderNo = orderNo
  }

  @Column(name = "MENU_NAME", nullable = false, length = 64) def getMenuName: String = {
    menuName
  }

  def setMenuName(menuName: String) {
    this.menuName = menuName
  }

  @Column(name = "PARENT_ID", nullable = false, length = 10) def getParentId: String = {
    parentId
  }

  def setParentId(parentId: String) {
    this.parentId = parentId
  }

  @Column(name = "TYPE", length = 2) def getType: String = {
    `type`
  }

  def setType(`type`: String) {
    this.`type` = `type`
  }

  @Column(name = "MENU_KEY", length = 128) def getMenuKey: String = {
    menuKey
  }

  def setMenuKey(menuKey: String) {
    this.menuKey = menuKey
  }

  @Column(name = "URL", length = 256) def getUrl: String = {
    url
  }

  def setUrl(url: String) {
    this.url = url
  }

  @Column(name = "CREATE_TIME", length = 14) def getCreateTime: String = {
    createTime
  }

  def setCreateTime(createTime: String) {
    this.createTime = createTime
  }

  @Column(name = "UPDATE_TIME", length = 14) def getUpdateTime: String = {
    updateTime
  }

  def setUpdateTime(updateTime: String) {
    this.updateTime = updateTime
  }

  @Column(name = "GROUP_ID", length = 3) def getGroupId: String = {
    groupId
  }

  def setGroupId(groupId: String) {
    this.groupId = groupId
  }
}

