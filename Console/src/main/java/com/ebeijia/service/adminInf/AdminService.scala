package com.ebeijia.service.adminInf

import java.util.{List, Map}

import com.ebeijia.entity.TblAdminInf
import org.springframework.web.multipart.MultipartFile

trait AdminService {
  def findBySql(adminId: String, adminName: String, adminStat: String, pageData: String)
  : Map[String, AnyRef]

  def save(data: TblAdminInf, `type`: String, f: MultipartFile, ext: String)



  def listAdminByUpTime: List[TblAdminInf]

  def login(usrName: String): List[TblAdminInf]

  def usrInfHead(usrId: String): List[_]

  def update(data: TblAdminInf)

  def checkAdmin(id: String, pwd: String): List[TblAdminInf]

  def getById(id: String): TblAdminInf

  def delById(id: String)

  def checkScalaAdmin(id: String, pwd: String): List[TblAdminInf]
}