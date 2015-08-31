package com.ebeijia.service.wechat.core

import java.util.{ArrayList, Date, List, Map}
import javax.servlet.http.HttpServletRequest
import com.ebeijia.entity.vo.wechat.resp.{Article, Image, ImageMessage, Music, MusicMessage, NewsMessage, TextMessage, Video, VideoMessage, Voice, VoiceMessage}
import com.ebeijia.entity.wechat.{TblArticlesInf, TblWechatReqMsg, TblWechatRespMsg}
import com.ebeijia.service.wechat.media.ArticlesService
import com.ebeijia.service.wechat.msg.{WechatReqMsgService, WechatRespMsgService}
import com.ebeijia.util.wechat.MessageUtil
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

/**
 * CoreService核心服务类
 * @author zhicheng.xu
 *         15/8/13
 */

@Component
class CoreService {
  private val logger: Logger = LoggerFactory.getLogger(classOf[CoreService])
  @Autowired
  private val wechatReqMsgService: WechatReqMsgService = null
  @Autowired
  private val wechatRespMsgService: WechatRespMsgService = null
  @Autowired
  private val articlesService: ArticlesService = null

  @Transactional def processRequest(request: HttpServletRequest, mchtId: String): String = {
    var respMessage: String = null
    try {
      val requestMap: Map[String, String] = MessageUtil.parseXml(request)
      val fromUsrName: String = requestMap.get("FromUserName")
      val toUsrName: String = requestMap.get("ToUserName")
      val createTime: String = requestMap.get("CreateTime")
      var msgType: String = requestMap.get("MsgType")
      val tblWechatReqMsg: TblWechatReqMsg = new TblWechatReqMsg
      tblWechatReqMsg.setFromUsrName(fromUsrName)
      tblWechatReqMsg.setToUsrName(toUsrName)
      tblWechatReqMsg.setCreateTime(createTime)
      tblWechatReqMsg.setMsgType(msgType)
      tblWechatReqMsg.setMchtId(mchtId)
      val content: String = requestMap.get("Content")

      this.saveReqMsg(tblWechatReqMsg, requestMap)
      if (msgType == MessageUtil.REQ_MESSAGE_TYPE_EVENT) {
        msgType = requestMap.get("Event")
        if (msgType == MessageUtil.EVENT_TYPE_MASSSENDJOBFINISH) {
        }
      }
      logger.info("=msgType:" + msgType + "content:" + content)
      val findByMchtType: List[TblWechatRespMsg] = wechatRespMsgService.findByMchtType(mchtId, msgType, content)
      logger.info("findByMchtType:" + findByMchtType)
      if (findByMchtType.isEmpty) {
        val textMessage: TextMessage = new TextMessage
        textMessage.setToUserName(fromUsrName)
        textMessage.setFromUserName(toUsrName)
        textMessage.setCreateTime(new Date().getTime)
        textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT)
        textMessage.setFuncFlag(0)
        textMessage.setContent("欢迎来到我的世界")
        respMessage = MessageUtil.textMessageToXml(textMessage)
      } else {
        val data: TblWechatRespMsg = findByMchtType.get(0)
        if (MessageUtil.RESP_MESSAGE_TYPE_TEXT == data.getMsgType) {
          val textMessage: TextMessage = new TextMessage
          textMessage.setToUserName(fromUsrName)
          textMessage.setFromUserName(toUsrName)
          textMessage.setCreateTime(new Date().getTime)
          textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT)
          textMessage.setFuncFlag(0)
          textMessage.setContent(data.getContent)
          respMessage = MessageUtil.textMessageToXml(textMessage)
        } else if (MessageUtil.RESP_MESSAGE_KF == data.getMsgType) {
          val textMessage: TextMessage = new TextMessage
          textMessage.setToUserName(fromUsrName)
          textMessage.setFromUserName(toUsrName)
          textMessage.setCreateTime(new Date().getTime)
          textMessage.setMsgType(MessageUtil.RESP_MESSAGE_KF)
          respMessage = MessageUtil.textMessageToXml(textMessage)
        } else if (MessageUtil.RESP_MESSAGE_TYPE_IMAGE == data.getMsgType) {
          val imageMessage: ImageMessage = new ImageMessage
          imageMessage.setToUserName(fromUsrName)
          imageMessage.setFromUserName(toUsrName)
          imageMessage.setCreateTime(new Date().getTime)
          imageMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_IMAGE)
          imageMessage.setFuncFlag(0)
          val image: Image = new Image
          image.setMediaId(data.getMediaId)
          imageMessage.setImage(image)
          respMessage = MessageUtil.imageMessageToXml(imageMessage)
        } else if (MessageUtil.RESP_MESSAGE_TYPE_VOICE == data.getMsgType) {
          val voiceMessage: VoiceMessage = new VoiceMessage
          voiceMessage.setToUserName(fromUsrName)
          voiceMessage.setFromUserName(toUsrName)
          voiceMessage.setCreateTime(new Date().getTime)
          voiceMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_VOICE)
          voiceMessage.setFuncFlag(0)
          val voice: Voice = new Voice
          voice.setMediaId(data.getMediaId)
          voiceMessage.setVoice(voice)
          respMessage = MessageUtil.voiceMessageToXml(voiceMessage)
        } else if (MessageUtil.RESP_MESSAGE_TYPE_VIDEO == data.getMsgType) {
          val videoMessage: VideoMessage = new VideoMessage
          videoMessage.setToUserName(fromUsrName)
          videoMessage.setFromUserName(toUsrName)
          videoMessage.setCreateTime(new Date().getTime)
          videoMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_VIDEO)
          videoMessage.setFuncFlag(0)
          val video: Video = new Video
          video.setMediaId(data.getMediaId)
          video.setTitle(data.getTitle)
          video.setDescription(data.getDescription)
          videoMessage.setVideo(video)
          respMessage = MessageUtil.videoMessageToXml(videoMessage)
        } else if (MessageUtil.RESP_MESSAGE_TYPE_MUSIC == data.getMsgType) {
          val musicMessage: MusicMessage = new MusicMessage
          musicMessage.setToUserName(fromUsrName)
          musicMessage.setFromUserName(toUsrName)
          musicMessage.setCreateTime(new Date().getTime)
          musicMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_MUSIC)
          musicMessage.setFuncFlag(0)
          val music: Music = new Music
          music.setTitle(data.getTitle)
          music.setDescription(data.getDescription)
          music.setMusicUrl(data.getMusicUrl)
          music.setHQMusicUrl(data.getHqMusicUrl)
          music.setThumbMediaId(data.getMediaId)
          musicMessage.setMusic(music)
          respMessage = MessageUtil.musicMessageToXml(musicMessage)
        } else if (MessageUtil.RESP_MESSAGE_TYPE_NEWS == data.getMsgType) {
          val articleList: List[Article] = new ArrayList[Article]
          for (articleId <- data.getArticleIds.split(",")) {
            val articlesTmp: TblArticlesInf = articlesService.getById(articleId)
            if (articlesTmp != null) {
              val article: Article = new Article
              article.setTitle(articlesTmp.getTitle)
              article.setDescription(articlesTmp.getDescription)
              article.setPicUrl(articlesTmp.getPicUrl)
              article.setUrl(articlesTmp.getUrl)
              articleList.add(article)
            }
          }
          val newsMessage: NewsMessage = new NewsMessage
          newsMessage.setToUserName(fromUsrName)
          newsMessage.setFromUserName(toUsrName)
          newsMessage.setCreateTime(new Date().getTime)
          newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS)
          newsMessage.setFuncFlag(0)
          newsMessage.setArticleCount(articleList.size)
          newsMessage.setArticles(articleList)
          respMessage = MessageUtil.newsMessageToXml(newsMessage)
        }
      }
    }
    catch {
      case e: Exception => {
        respMessage = ""
        e.printStackTrace
      }
    }
    respMessage
  }

  private def saveReqMsg(tblWechatReqMsg: TblWechatReqMsg, requestMap: Map[String, String]) {
    val msgType: String = requestMap.get("MsgType")
    if (msgType == MessageUtil.REQ_MESSAGE_TYPE_TEXT) {
      val content: String = requestMap.get("Content")
      val msgId: String = requestMap.get("MsgId")
      tblWechatReqMsg.setMsgId(msgId)
      tblWechatReqMsg.setContent(content)
    } else if (msgType == MessageUtil.REQ_MESSAGE_TYPE_IMAGE) {
      val picUrl: String = requestMap.get("PicUrl")
      val mediaId: String = requestMap.get("MediaId")
      val msgId: String = requestMap.get("MsgId")
      tblWechatReqMsg.setMsgId(msgId)
      tblWechatReqMsg.setPicUrl(picUrl)
      tblWechatReqMsg.setMediaId(mediaId)
    } else if (msgType == MessageUtil.REQ_MESSAGE_TYPE_LOCATION) {
      val locationX: String = requestMap.get("Location_X")
      val locationY: String = requestMap.get("Location_Y")
      val scale: String = requestMap.get("Scale")
      val label: String = requestMap.get("Label")
      val msgId: String = requestMap.get("MsgId")
      tblWechatReqMsg.setMsgId(msgId)
      tblWechatReqMsg.setLocationX(locationX)
      tblWechatReqMsg.setLocationY(locationY)
      tblWechatReqMsg.setScale(scale)
      tblWechatReqMsg.setLabel(label)
    } else if (msgType == MessageUtil.REQ_MESSAGE_TYPE_LINK) {
      val title: String = requestMap.get("Title")
      val description: String = requestMap.get("Description")
      val url: String = requestMap.get("Url")
      val msgId: String = requestMap.get("MsgId")
      tblWechatReqMsg.setMsgId(msgId)
      tblWechatReqMsg.setTitle(title)
      tblWechatReqMsg.setDescription(description)
      tblWechatReqMsg.setUrl(url)
    } else if (msgType == MessageUtil.REQ_MESSAGE_TYPE_VOICE) {
      val mediaId: String = requestMap.get("MediaId")
      val format: String = requestMap.get("Format")
      val msgId: String = requestMap.get("MsgId")
      tblWechatReqMsg.setMsgId(msgId)
      tblWechatReqMsg.setMediaId(mediaId)
      tblWechatReqMsg.setFormat(format)
    } else if (msgType == MessageUtil.REQ_MESSAGE_TYPE_EVENT) {
      val eventType: String = requestMap.get("Event")
      if (eventType == MessageUtil.EVENT_TYPE_SUBSCRIBE) {
        tblWechatReqMsg.setEventType(MessageUtil.EVENT_TYPE_SUBSCRIBE)
        val eventKey: String = requestMap.get("EventKey")
        if (eventKey != null && eventKey.startsWith("qrscene_")) {
          tblWechatReqMsg.setEventKey(eventKey)
          tblWechatReqMsg.setTicket(requestMap.get("Ticket"))
        }
      } else if (eventType == MessageUtil.EVENT_TYPE_UNSUBSCRIBE) {
        tblWechatReqMsg.setEventType(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)
      } else if (eventType == MessageUtil.EVENT_TYPE_CLICK) {
        tblWechatReqMsg.setEventType(MessageUtil.EVENT_TYPE_CLICK)
        val eventKey: String = requestMap.get("EventKey")
        tblWechatReqMsg.setEventKey(eventKey)
        tblWechatReqMsg.setEventType(eventType)
      }
    }
    wechatReqMsgService.save(tblWechatReqMsg)
  }
}

