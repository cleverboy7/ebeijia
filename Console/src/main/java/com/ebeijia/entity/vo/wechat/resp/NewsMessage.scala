package com.ebeijia.entity.vo.wechat.resp
import java.util.List
/**
 * NewsMessage图文消息
 * @author zhicheng.xu
 *         15/8/17
 */

class NewsMessage extends BaseMessage {
  private var ArticleCount: Int = 0
  private var Articles: List[Article] = null

  def getArticleCount: Int = {
    ArticleCount
  }

  def setArticleCount(articleCount: Int) {
    ArticleCount = articleCount
  }

  def getArticles: List[Article] = {
    Articles
  }

  def setArticles(articles: List[Article]) {
    Articles = articles
  }
}
