package com.ebeijia.service.wechat.mass

import java.util.{ArrayList, List, Map}

import com.ebeijia.dao.wechat.WechatMassDao
import com.ebeijia.entity.vo.wechat.AccessToken
import com.ebeijia.entity.vo.wechat.mass.{Filter, Image, Mpnews, Mpvideo, SendImageMass, SendImageMassByUsr, SendMpnewsMass, SendMpnewsMassByUsr, SendMpvideoMass, SendMpvideoMassByUsr, SendTextMass, SendTextMassByUsr, SendVoiceMass, SendVoiceMassByUsr, Text, ToUser, Voice}
import com.ebeijia.entity.wechat.{TblWechatFodder, TblWechatMass, TblWechatMassId, TblWechatMchtInf}
import com.ebeijia.service.wechat.core.{WechatMchtInfService, WechatTokenService}
import com.ebeijia.service.wechat.inter.{MassManager, MediaManager}
import com.ebeijia.service.wechat.media.WechatFodderService
import com.ebeijia.util.wechat.{MessageUtil, WechatUtil}
import net.sf.json.JSONObject
import org.ebeijia.tools.DateTime4J
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.{CacheEvict, Cacheable}
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * WechatMassServiceImpl
 * @author zhicheng.xu
 *         15/8/14
 */


@Service("wechatMassService")
final class WechatMassServiceImpl extends WechatMassService {
  @Autowired
  private val wechatMchtInfService: WechatMchtInfService = null
  @Autowired
  private val wechatTokenService: WechatTokenService = null
  @Autowired
  private val mediaManager: MediaManager = null
  @Autowired
  private val massManager: MassManager = null
  @Autowired
  private val wechatFodderService: WechatFodderService = null
  @Autowired
  private val wechatMassDao: WechatMassDao = null

  @Transactional
  @CacheEvict(value = Array("wechatFooderCache"), allEntries = true)
  def upGtMedia(mchtId: String, title: String, dsc: String, articles: String): String = {
    val data: TblWechatMchtInf = wechatMchtInfService.getById(mchtId)
    val at: AccessToken = wechatTokenService.getAccessToken(data.getAppid, data.getAppsecret)
    var result: String = null
    if (null != at) {
      val jsonObject: JSONObject = mediaManager.massUpNews(at.getToken, articles)
      if (null != jsonObject) {
        if (WechatUtil.getWechatCallBackStatus(jsonObject.toString) != 0) {
          result = jsonObject.getString("errcode")
        } else {
          val fodder: TblWechatFodder = new TblWechatFodder
          fodder.setCreateTime(DateTime4J.getCurrentDateTime)
          fodder.setMedia(result)
          fodder.setMediaType("1")
          fodder.setMchtId(mchtId)
          fodder.setUrl(" ")
          fodder.setType("news")
          fodder.setName(title)
          fodder.setDsc(dsc)
          wechatFodderService.save(fodder)
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
  @CacheEvict(value = Array("wechatFooderCache"), allEntries = true)
  def upVideoMedia(mchtId: String, title: String, dsc: String, mediaId: String): String = {
    val data: TblWechatMchtInf = wechatMchtInfService.getById(mchtId)
    val at: AccessToken = wechatTokenService.getAccessToken(data.getAppid, data.getAppsecret)
    var result: String = null
    if (null != at) {
      val jsonObject: JSONObject = mediaManager.massUpVideo(at.getToken, mediaId, title, dsc)
      if (null != jsonObject) {
        if (WechatUtil.getWechatCallBackStatus(jsonObject.toString) != 0) {
          result = jsonObject.getString("errcode")
        } else {
          val fodder: TblWechatFodder = new TblWechatFodder
          fodder.setCreateTime(DateTime4J.getCurrentDateTime)
          fodder.setMedia(result)
          fodder.setMediaType("1")
          fodder.setMchtId(mchtId)
          fodder.setUrl(" ")
          fodder.setType("mpvideo")
          fodder.setName(title)
          fodder.setDsc(dsc)
          wechatFodderService.save(fodder)
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
  @CacheEvict(value = Array("wechatMassCache"), allEntries = true)
  def sendByGroup(mchtId: String, content: String, `type`: String, groupId: String, media: String): String = {
    val filter: Filter = new Filter
    if (groupId == null || ("" == groupId)) {
      filter.setIs_to_all(true)
    }
    else {
      filter.setIs_to_all(false)
      filter.setGroup_id(groupId)
    }
    val sendJson: String = this.build(content, filter, `type`, media)
    val data: TblWechatMchtInf = wechatMchtInfService.getById(mchtId)
    val at: AccessToken = wechatTokenService.getAccessToken(data.getAppid, data.getAppsecret)
    var result: String = null
    if (null != at) {
      val jsonObject: JSONObject = massManager.massAll(at.getToken, sendJson)
      if (null != jsonObject) {
        if (WechatUtil.getWechatCallBackStatus(jsonObject.toString) != 0) {
          result = jsonObject.getString("errcode")
        } else {
          val tblWechatMass: TblWechatMass = new TblWechatMass
          val id: TblWechatMassId = new TblWechatMassId
          id.setMchtId(mchtId)
          id.setMsgId(jsonObject.getString("msg_id"))
          tblWechatMass.setId(id)
          tblWechatMass.setContent(content)
          tblWechatMass.setCreateTime(DateTime4J.getCurrentDateTime)
          tblWechatMass.setMedia(media)
          tblWechatMass.setMsgType(`type`)
          tblWechatMass.setType("0")
          wechatMassDao.save(tblWechatMass)
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
  @CacheEvict(value = Array("wechatMassCache"), allEntries = true)
  def sendByUsr(mchtId: String, content: String, msgType: String, toUsr: String, media: String): String = {
    val toUser: ToUser = new ToUser
    val toUserList: List[Any] = new ArrayList[Any]
    for (usr <- toUsr.split(",")) {
      toUserList.add(usr)
    }
    toUser.setToUser(toUserList)
    val sendJson: String = this.buildUsr(content, toUserList, msgType, media)
    val data: TblWechatMchtInf = wechatMchtInfService.getById(mchtId)
    val at: AccessToken = wechatTokenService.getAccessToken(data.getAppid, data.getAppsecret)
    var result: String = null
    if (null != at) {
      val jsonObject: JSONObject = massManager.massUsr(at.getToken, sendJson)
      if (null != jsonObject) {
        if (WechatUtil.getWechatCallBackStatus(jsonObject.toString) != 0) {
          result = jsonObject.getString("errcode")
        } else {
          val tblWechatMass: TblWechatMass = new TblWechatMass
          val id: TblWechatMassId = new TblWechatMassId
          id.setMchtId(mchtId)
          id.setMsgId(jsonObject.getString("msg_id"))
          tblWechatMass.setId(id)
          tblWechatMass.setContent(content)
          tblWechatMass.setCreateTime(DateTime4J.getCurrentDateTime)
          tblWechatMass.setMedia(media)
          tblWechatMass.setMsgType(msgType)
          tblWechatMass.setType("1")
          tblWechatMass.setToUsr(toUserList.size)
          wechatMassDao.save(tblWechatMass)
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
  @CacheEvict(value = Array("wechatMassCache"), allEntries = true)
  def sendDel(mchtId: String, mediaId: String): String = {
    val data: TblWechatMchtInf = wechatMchtInfService.getById(mchtId)
    val at: AccessToken = wechatTokenService.getAccessToken(data.getAppid, data.getAppsecret)
    var result: String = "0"
    if (null != at) {
      val jsonObject: JSONObject = massManager.massDel(at.getToken, mediaId)
      if (null != jsonObject) {
        if (WechatUtil.getWechatCallBackStatus(jsonObject.toString) != 0) {
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
  @Cacheable(value = Array("wechatMassCache"))
  def sendStatusFind(mchtId: String, msgId: String): String = {
    val data: TblWechatMchtInf = wechatMchtInfService.getById(mchtId)
    val at: AccessToken = wechatTokenService.getAccessToken(data.getAppid, data.getAppsecret)
    var result: String = "0"
    if (null != at) {
      result = massManager.massStatus(at.getToken, msgId)
    }
    result
  }

  @Transactional
  @Cacheable(value = Array("wechatMassCache"))
  def findBySql(mchtId: String, pagaData: String): Map[String, AnyRef] = {
    val query: StringBuffer = new StringBuffer
    query.append("from TblWechatMass")
    query.append(" where 1=1")
    if (mchtId != null && !("" == mchtId)) {
      query.append(" AND id.mchtId like '%").append(mchtId).append("%'")
    }
    query.append(" ORDER BY id.mchtId")
    val m: Map[String, AnyRef] = wechatMassDao.findByPage(query.toString, pagaData)
    m
  }

  private def build(content: String, filter: Filter, `type`: String, media: String): String = {
    var sendJson: String = null
    if (MessageUtil.RESP_MESSAGE_TYPE_TEXT == `type`) {
      val text: Text = new Text
      text.setContent(content)
      val sendTextMass: SendTextMass = new SendTextMass
      sendTextMass.setFilter(filter)
      sendTextMass.setText(text)
      sendTextMass.setMsgtype(`type`)
      sendJson = JSONObject.fromObject(sendTextMass).toString
    } else if (MessageUtil.RESP_MESSAGE_TYPE_IMAGE == `type`) {
      val image: Image = new Image
      image.setMedia_id(media)
      val sendImageMass: SendImageMass = new SendImageMass
      sendImageMass.setFilter(filter)
      sendImageMass.setImage(image)
      sendImageMass.setMsgtype(`type`)
      sendJson = JSONObject.fromObject(sendImageMass).toString
    } else if (MessageUtil.RESP_MESSAGE_TYPE_VOICE == `type`) {
      val voice: Voice = new Voice
      voice.setMedia_id(media)
      val sendVoiceMass: SendVoiceMass = new SendVoiceMass
      sendVoiceMass.setFilter(filter)
      sendVoiceMass.setVoice(voice)
      sendVoiceMass.setMsgtype(`type`)
      sendJson = JSONObject.fromObject(sendVoiceMass).toString
    } else if (MessageUtil.RESP_MESSAGE_TYPE_VIDEO == `type`) {
      val video: Mpvideo = new Mpvideo
      video.setMedia_id(media)
      val sendVideoMass: SendMpvideoMass = new SendMpvideoMass
      sendVideoMass.setFilter(filter)
      sendVideoMass.setMpvideo(video)
      sendVideoMass.setMsgtype(`type`)
      sendJson = JSONObject.fromObject(sendVideoMass).toString
    } else if (MessageUtil.RESP_MESSAGE_TYPE_NEWS == `type`) {
      val news: Mpnews = new Mpnews
      news.setMedia_id(media)
      val sendMpnewsMass: SendMpnewsMass = new SendMpnewsMass
      sendMpnewsMass.setFilter(filter)
      sendMpnewsMass.setMpnews(news)
      sendMpnewsMass.setMsgtype(`type`)
      sendJson = JSONObject.fromObject(sendMpnewsMass).toString
    }
    sendJson
  }

  private def buildUsr(content: String, toUser: List[_], `type`: String, media: String): String = {
    var sendJson: String = null
    if (MessageUtil.RESP_MESSAGE_TYPE_TEXT == `type`) {
      val text: Text = new Text
      text.setContent(content)
      val sendTextMass: SendTextMassByUsr = new SendTextMassByUsr
      sendTextMass.setTouser(toUser)
      sendTextMass.setText(text)
      sendTextMass.setMsgtype(`type`)
      sendJson = JSONObject.fromObject(sendTextMass).toString
    }
    else if (MessageUtil.RESP_MESSAGE_TYPE_IMAGE == `type`) {
      val image: Image = new Image
      image.setMedia_id(media)
      val sendImageMass: SendImageMassByUsr = new SendImageMassByUsr
      sendImageMass.setTouser(toUser)
      sendImageMass.setImage(image)
      sendImageMass.setMsgtype(`type`)
      sendJson = JSONObject.fromObject(sendImageMass).toString
    }
    else if (MessageUtil.RESP_MESSAGE_TYPE_VOICE == `type`) {
      val voice: Voice = new Voice
      voice.setMedia_id(media)
      val sendVoiceMass: SendVoiceMassByUsr = new SendVoiceMassByUsr
      sendVoiceMass.setTouser(toUser)
      sendVoiceMass.setVoice(voice)
      sendVoiceMass.setMsgtype(`type`)
      sendJson = JSONObject.fromObject(sendVoiceMass).toString
    }
    else if (MessageUtil.RESP_MESSAGE_TYPE_VIDEO == `type`) {
      val video: Mpvideo = new Mpvideo
      video.setMedia_id(media)
      val sendVideoMass: SendMpvideoMassByUsr = new SendMpvideoMassByUsr
      sendVideoMass.setTouser(toUser)
      sendVideoMass.setMpvideo(video)
      sendVideoMass.setMsgtype(`type`)
      sendJson = JSONObject.fromObject(sendVideoMass).toString
    }
    else if (MessageUtil.RESP_MESSAGE_TYPE_NEWS == `type`) {
      val news: Mpnews = new Mpnews
      news.setMedia_id(media)
      val sendMpnewsMass: SendMpnewsMassByUsr = new SendMpnewsMassByUsr
      sendMpnewsMass.setTouser(toUser)
      sendMpnewsMass.setMpnews(news)
      sendMpnewsMass.setMsgtype(`type`)
      sendJson = JSONObject.fromObject(sendMpnewsMass).toString
    }
    sendJson
  }
}
