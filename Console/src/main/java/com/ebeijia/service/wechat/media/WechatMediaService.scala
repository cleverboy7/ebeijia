package com.ebeijia.service.wechat.media

import java.util.Map
import org.springframework.web.multipart.MultipartFile

/**
 * WechatMediaService
 * @author zhicheng.xu
 *         15/8/14
 */

trait WechatMediaService {
  def upLoadMedia(mchtId: String, `type`: String, f: MultipartFile, mediaType: String, ext: String, name: String, dsc: String): String

  def dowLoadMedia(mchtId: String, mediaId: String, mediaType: String): String

  def mediaAllCount(mchtId: String): Map[_, _]

  def upGtMedia(mchtId: String, name: String, dsc: String, articles: String): String

  def newsGet(mchtId: String, media: String): String

  def updateGtMedia(mchtId: String, name: String, dsc: String, media: String, articles: String): String
}
