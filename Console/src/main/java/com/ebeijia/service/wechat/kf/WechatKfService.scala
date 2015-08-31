package com.ebeijia.service.wechat.kf

import java.util.Map
import com.ebeijia.entity.vo.wechat.kf.Kf
import com.ebeijia.entity.wechat.{TblWechatKf, TblWechatKfId}
import org.springframework.web.multipart.MultipartFile

/**
 * WechatKfService
 * @author zhicheng.xu
 *         15/8/14
 */


trait WechatKfService {
  def save(mchtId: String, kf: Kf): String

  def upd(mchtId: String, kf: Kf): String

  def del(mchtId: String, account: String): String

  def upHead(mchtId: String, `type`: String, f: MultipartFile, ext: String): String

  def findCount(mchtId: String): Int

  def getById(id: TblWechatKfId): TblWechatKf

  def findBySql(mchtId: String, pageData: String): Map[String, AnyRef]
}
