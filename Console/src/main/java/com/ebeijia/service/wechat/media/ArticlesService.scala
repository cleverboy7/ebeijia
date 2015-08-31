package com.ebeijia.service.wechat.media

import java.util.Map
import com.ebeijia.entity.wechat.TblArticlesInf

/**
 * ArticlesService
 * @author zhicheng.xu
 *         15/8/14
 */

trait ArticlesService {
  def findBySql(mchtId: String, aoData: String): Map[String, AnyRef]

  def Save(data: TblArticlesInf)

  def getById(id: String): TblArticlesInf
}