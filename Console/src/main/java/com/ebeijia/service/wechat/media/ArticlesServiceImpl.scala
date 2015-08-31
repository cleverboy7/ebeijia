package com.ebeijia.service.wechat.media

import java.util.Map
import com.ebeijia.dao.wechat.ArticlesDao
import com.ebeijia.entity.wechat.TblArticlesInf
import com.ebeijia.util.StringUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.{CacheEvict, Cacheable}
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
/**
 * ArticlesServiceImpl
 * @author zhicheng.xu
 *         15/8/14
 */


@Service
final class ArticlesServiceImpl extends ArticlesService {
  @Autowired
  private val articlesDao: ArticlesDao = null

  @Transactional
  @Cacheable(value = Array("articlesCache"))
  def findBySql(mchtId: String, pagaData: String): Map[String, AnyRef] = {
    val query: StringBuilder = new StringBuilder
    query.append("from TblWechatRespMsg")
    query.append(" where 1=1")
    if (mchtId != null && !("" == mchtId)) {
      query.append(" AND mchtId like '%").append(mchtId).append("%'")
    }
    query.append(" ORDER BY id.mchtId")
    val m: Map[String, AnyRef] = articlesDao.findByPage(query.toString, pagaData)
    m
  }

  @Transactional
  @CacheEvict(value = Array("articlesCache"), allEntries = true)
  def Save(data: TblArticlesInf) {
    articlesDao.save(data)
  }

  @Transactional
  @Cacheable(value = Array("articlesCache"), key = "#root.method.name+#id")
  def getById(id: String): TblArticlesInf = {
    articlesDao.getById(id)
  }

}
