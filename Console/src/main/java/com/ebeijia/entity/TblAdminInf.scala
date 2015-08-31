package com.ebeijia.entity

import java.io.Serializable
import javax.persistence._

/**
 * Tmp
 * @author zhicheng.xu
 *         15/8/3
 */
@Entity
@Table(name = "TBL_ADMIN_INF")
@SerialVersionUID(1L)
class TblAdminInf() extends Serializable{

  private var adminId: String = null
  private var adminName: String = null
  private var roleId: String = null
  private var adminPwd: String = null
  private var adminStat: String = null
  private var crtTime: String = null
  private var lastLogTime: String = null
  private var updTime: String = null
  private var adminDsc: String = null
  private var adminHead: String = null

  //  def this() {
  //    this()
  //  }

  def this(adminId: String, adminName: String, roleId: String, adminPwd: String, adminStat: String, crtTime: String, lastLogTime: String, updTime: String, adminDsc: String, adminHead: String) {
    this()
    this.adminId = adminId
    this.adminName = adminName
    this.roleId = roleId
    this.adminPwd = adminPwd
    this.adminStat = adminStat
    this.crtTime = crtTime
    this.lastLogTime = lastLogTime
    this.updTime = updTime
    this.adminDsc = adminDsc
    this.adminHead = adminHead
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ADMIN_ID", unique = true, nullable = false, length = 10)
  def getAdminId: String = {
     adminId
  }

  def setAdminId(adminId: String) {
    this.adminId = adminId
  }

  @Column(name = "ADMIN_NAME", length = 32)
  def getAdminName: String = {
     adminName
  }

  def setAdminName(adminName: String) {
    this.adminName = adminName
  }

  @Column(name = "ROLE_ID", length = 10)
  def getRoleId: String = {
     roleId
  }

  def setRoleId(roleId: String) {
    this.roleId = roleId
  }

  @Column(name = "ADMIN_PWD", length = 32)
  def getAdminPwd: String = {
     adminPwd
  }

  def setAdminPwd(adminPwd: String) {
    this.adminPwd = adminPwd
  }

  @Column(name = "ADMIN_STAT", length = 1)
  def getAdminStat: String = {
     adminStat
  }

  def setAdminStat(adminStat: String) {
    this.adminStat = adminStat
  }

  @Column(name = "CRT_TIME", length = 14)
  def getCrtTime: String = {
     crtTime
  }

  def setCrtTime(crtTime: String) {
    this.crtTime = crtTime
  }

  @Column(name = "LAST_LOG_TIME", length = 14)
  def getLastLogTime: String = {
     lastLogTime
  }

  def setLastLogTime(lastLogTime: String) {
    this.lastLogTime = lastLogTime
  }

  @Column(name = "UPD_TIME", length = 14)
  def getUpdTime: String = {
     updTime
  }

  def setUpdTime(updTime: String) {
    this.updTime = updTime
  }

  @Column(name = "ADMIN_DSC", length = 128)
  def getAdminDsc: String = {
     adminDsc
  }

  def setAdminDsc(adminDsc: String) {
    this.adminDsc = adminDsc
  }

  @Column(name = "ADMIN_HEAD", length = 10)
  def getAdminHead: String = {
     adminHead
  }

  def setAdminHead(adminHead: String) {
    this.adminHead = adminHead
  }

}
