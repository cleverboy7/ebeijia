package com.ebeijia.service.adminInf

import java.io.File
import java.util
import java.util.{List, Map}

import com.ebeijia.dao.adminInf.AdminInfDao
import com.ebeijia.entity.{TblAdminInf, TblPicInf}
import com.ebeijia.service.picInf.PicInfService
import com.ebeijia.util.{Constant, SystemProperties, UpLoad}
import org.ebeijia.tools.DateTime4J
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.{CacheEvict, Cacheable}
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

/**
 * AdminServiceImpl
 * @author zhicheng.xu
 *         15/8/3
 */
@Service
final class AdminServiceImpl extends AdminService {
  @Autowired
  private val adminInfDao: AdminInfDao = null
  @Autowired
  private val picInfService: PicInfService = null

  @Transactional
  @Cacheable(value = Array("adminCache"), key = "#root.method.name+#id")
  def getById(id: String): TblAdminInf = {
    adminInfDao.getById(id)
  }

  @Transactional
  @Cacheable(value = Array("adminCache"))
  def findBySql(adminId: String, adminName: String, adminStat: String, pageData: String): Map[String, AnyRef] = {
    val query: StringBuilder = new StringBuilder
    query.append("select p.picUrl,a.adminId,a.adminName,a.roleId,a.adminDsc,a.adminStat")
    query.append(" from TblAdminInf a ,TblPicInf p ")
    query.append(" where a.adminHead = p.picId ")
    if (adminId != null && !("" == adminId)) {
      query.append(" AND a.adminId like '%").append(adminId).append("%'")
    }
    if (adminName != null && !("" == adminName)) {
      query.append(" AND a.adminName like '%").append(adminName).append("%'")
    }
    if (adminStat != null && !("" == adminStat)) {
      query.append(" AND a.adminStat ='").append(adminStat).append("'")
    }
    val m: Map[String, AnyRef] = adminInfDao.findByPage(query.toString(), pageData)
    m
  }

  @Transactional
  @Cacheable(value = Array("adminCache"), key = "#root.method.name")
  def listAdminByUpTime: List[TblAdminInf] = {
    val sb: StringBuilder = new StringBuilder
    sb.append("select u.adminId,u.adminName,u.lastLogTime,p.picUrl from TblAdminInf u, TblPicInf p")
    sb.append(" where u.adminHead = p.picId order by u.lastLogTime desc")
    val result: List[TblAdminInf] = adminInfDao.findByPage(sb.toString(), 1, 5)
    result
  }

  def usrInfHead(usrId: String): List[TblAdminInf] = {
    val sb: StringBuilder = new StringBuilder
    sb.append("select u.adminId,u.adminName,p.picUrl from TblAdminInf u, TblPicInf p")
    sb.append(" where u.adminHead = p.picId  and u.adminId ='").append(usrId).append("'")
    val tblAdminInf: TblAdminInf = this.getById(usrId)
    tblAdminInf.setLastLogTime(DateTime4J.getCurrentDateTime)
    adminInfDao.update(tblAdminInf)
    adminInfDao.find(sb.toString())
  }

  @Transactional
  @Cacheable(value = Array("adminCache"), key = "#root.method.name+#name")
  def login(name: String): List[TblAdminInf] = {
    val sb: StringBuilder = new StringBuilder
    sb.append("FROM TblAdminInf WHERE adminName = '").append(name).append("'")
    val adminInfs: List[TblAdminInf] = adminInfDao.find(sb.toString())
    adminInfs
  }

  @Transactional
  @Cacheable(value = Array("adminCache"))
  def checkAdmin(id: String, pwd: String): List[TblAdminInf] = {
    val sb: StringBuilder = new StringBuilder
    sb.append("FROM TblAdminInf WHERE adminId = '").append(id).append("'")
    sb.append(" and adminPwd ='").append(pwd).append("'")
    val adminInfs: List[TblAdminInf] = adminInfDao.find(sb.toString())
    adminInfs
  }

  @Transactional
  @Cacheable(value = Array("adminCache"))
  def checkScalaAdmin(id: String, pwd: String): util.List[TblAdminInf] = {
    val sb: StringBuilder = new StringBuilder
    sb.append("FROM TblAdminInf WHERE adminId = '").append(id).append("'")
    sb.append(" and adminPwd ='").append(pwd).append("'")
    val adminInfs: List[TblAdminInf] = adminInfDao.find(sb.toString())
    adminInfs
  }

  @Transactional
  @CacheEvict(value = Array("adminCache"), allEntries = true) def update(data: TblAdminInf) {
    adminInfDao.update(data)
  }

  @CacheEvict(value = Array("adminCache"), allEntries = true)
  @Transactional def save(data: TblAdminInf, `type`: String, f: MultipartFile, ext: String) {
    val upload: UpLoad = new UpLoad
    if (f != null) {
      val file: File = upload.getFile(f, SystemProperties.getProperties("image.head"), `type`, ext)
      val picData: TblPicInf = new TblPicInf
      picData.setPicName("管理员" + data.getAdminId + "头像")
      picData.setPicType(Constant.PIC_HEAD)
      val picUrl: String = SystemProperties.getProperties("mediaPath") + SystemProperties.getProperties("image.head") + "/" + `type` + "/" + file.getName
      picData.setPicUrl(picUrl)
      picInfService.save(picData)
      data.setAdminHead(picData.getPicId)
    }
    else {
      data.setAdminHead("1")
    }
    adminInfDao.save(data)
  }

  @CacheEvict(value = Array("adminCache"), allEntries = true)
  @Transactional
  def delById(id: String) {
    adminInfDao.deleteById(id)
  }


}
