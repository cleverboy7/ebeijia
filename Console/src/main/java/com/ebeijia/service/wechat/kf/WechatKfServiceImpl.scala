package com.ebeijia.service.wechat.kf

import java.io.File
import java.util.Map

import com.ebeijia.dao.wechat.WechatKfDao
import com.ebeijia.entity.vo.wechat.AccessToken
import com.ebeijia.entity.vo.wechat.kf.Kf
import com.ebeijia.entity.wechat.{TblWechatKf, TblWechatKfId, TblWechatMchtInf}
import com.ebeijia.service.wechat.core.{WechatMchtInfService, WechatTokenService}
import com.ebeijia.service.wechat.inter.KfManager
import com.ebeijia.util.wechat.WechatUtil
import com.ebeijia.util.{SystemProperties, UpLoad}
import net.sf.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.{CacheEvict, Cacheable}
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

/**
 * WechatKfServiceImpl
 * @author zhicheng.xu
 *         15/8/14
 */


@Service
final class WechatKfServiceImpl extends WechatKfService {
  @Autowired
  private val wechatKfDao: WechatKfDao = null
  @Autowired
  private val wechatMchtInfService: WechatMchtInfService = null
  @Autowired
  private val wechatTokenService: WechatTokenService = null
  @Autowired
  private val kfManager: KfManager = null

  @Transactional
  @CacheEvict(value = Array("wechatKfCache"), allEntries = true)
  def save(mchtId: String, kf: Kf): String = {
    var result: String = null
    val tblWechatMchtInf: TblWechatMchtInf = wechatMchtInfService.getById(mchtId)
    val at: AccessToken = wechatTokenService.getAccessToken(tblWechatMchtInf.getAppid, tblWechatMchtInf.getAppsecret)
    if (null != at) {
      val jsonObject: JSONObject = kfManager.createKf(kf, at.getToken)
      if (null != jsonObject) {
        if (WechatUtil.getWechatCallBackStatus(jsonObject.toString) != 0) {
          result = jsonObject.getString("errcode")
        } else {
          val id: TblWechatKfId = new TblWechatKfId
          id.setKfId(kf.getKf_account)
          id.setMchtId(mchtId)
          val data: TblWechatKf = new TblWechatKf
          data.setId(id)
          data.setKfNick(kf.getNickname)
          data.setKfPwd(kf.getPassword)
          wechatKfDao.save(data)
        }
      } else {
        result = "8888888"
      }
    } else {
      result = "9999999"
    }
    result
  }

  @Transactional
  @CacheEvict(value = Array("wechatKfCache"), allEntries = true)
  def upd(mchtId: String, kf: Kf): String = {
    var result: String = null
    val tblWechatMchtInf: TblWechatMchtInf = wechatMchtInfService.getById(mchtId)
    val at: AccessToken = wechatTokenService.getAccessToken(tblWechatMchtInf.getAppid, tblWechatMchtInf.getAppsecret)
    if (null != at) {
      val jsonObject: JSONObject = kfManager.updKf(kf, at.getToken)
      if (null != jsonObject) {
        if (WechatUtil.getWechatCallBackStatus(jsonObject.toString) != 0) {
          result = jsonObject.getString("errcode")
        }
        else {
          val id: TblWechatKfId = new TblWechatKfId
          id.setKfId(kf.getKf_account)
          id.setMchtId(mchtId)
          val data: TblWechatKf = new TblWechatKf
          data.setId(id)
          data.setKfNick(kf.getNickname)
          data.setKfPwd(kf.getPassword)
          wechatKfDao.update(data)
        }
      }
    } else {
      result = "9999999"
    }
    result
  }

  @Transactional
  @CacheEvict(value = Array("wechatKfCache"), allEntries = true)
  def del(mchtId: String, account: String): String = {
    var result: String = null
    val tblWechatMchtInf: TblWechatMchtInf = wechatMchtInfService.getById(mchtId)
    val at: AccessToken = wechatTokenService.getAccessToken(tblWechatMchtInf.getAppid, tblWechatMchtInf.getAppsecret)
    if (null != at) {
      val jsonObject: JSONObject = kfManager.delKf(account, at.getToken)
      if (null != jsonObject) {
        if (WechatUtil.getWechatCallBackStatus(jsonObject.toString) != 0) {
          result = jsonObject.getString("errcode")
        } else {
          val id: TblWechatKfId = new TblWechatKfId
          id.setKfId(account)
          id.setMchtId(mchtId)
          wechatKfDao.deleteById(id)
        }
      }
    } else {
      result = "9999999"
    }
    result
  }

  @Transactional
  @CacheEvict(value = Array("wechatKfCache"), allEntries = true)
  def upHead(mchtId: String, `type`: String, f: MultipartFile, ext: String): String = {
    val upload: UpLoad = new UpLoad
    val file: File = upload.getFile(f, SystemProperties.getProperties("image.path1"), `type`, ext)
    val data: TblWechatMchtInf = wechatMchtInfService.getById(mchtId)
    val at: AccessToken = wechatTokenService.getAccessToken(data.getAppid, data.getAppsecret)
    var jsonObject: JSONObject = null
    var result: String = null
    if (null != at) {
      jsonObject = kfManager.upHeadKf(at.getToken, `type`, file, ext)
      if (null != jsonObject) {
        if (jsonObject.toString.indexOf("errcode") != -1) {
          result = jsonObject.getString("errcode")
        } else {
        }
      } else {
        result = "8888888"
      }
    } else {
      result = "9999999"
    }
    result
  }

  @Transactional
  @Cacheable(value = Array("wechatKfCache"), key = "#root.method.name+#id")
  def getById(id: TblWechatKfId): TblWechatKf = {
    wechatKfDao.getById(id)
  }

  @Transactional
  @Cacheable(value = Array("wechatKfCache"))
  def findBySql(mchtId: String, pageData: String): Map[String, AnyRef] = {
    val query: StringBuilder = new StringBuilder
    query.append("from TblWechatKf ")
    query.append(" where 1=1")
    if (mchtId != null && !("" == mchtId)) {
      query.append(" AND id.mchtId ='").append(mchtId).append("' ")
    }
    val m: Map[String, AnyRef] = wechatKfDao.findByPage(query.toString, pageData)
    m
  }

  @Transactional
  @Cacheable(value = Array("wechatKfCache"), key = "#root.method.name+#mchtId")
  def findCount(mchtId: String): Int = {
    val sb: StringBuilder = new StringBuilder
    sb.append("From TblWechatKf where id.mchtId ='").append(mchtId).append("'")
    val total: Int = wechatKfDao.getTotalRows(sb.toString)
    total
  }
}
