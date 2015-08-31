package com.ebeijia.service.wechat.win

import java.util.Map

import com.ebeijia.entity.wechat.TblWechatWinning

/**
 * WechatWinService
 * @author zhicheng.xu
 *         15/8/14
 */


trait WechatWinService {
  def save(data: TblWechatWinning): String

  def upd(data: TblWechatWinning): String

  def del(id: String): String

  def getById(id: String): TblWechatWinning

  def findBySql(mchtId: String, pageData: String): Map[String, AnyRef]

  def check(openid: String, appid: String, actId: String, `type`: String): String

  def calculation(openid: String, appid: String, actId: String, `type`: String): String

  def winCheck(actId: String, openid: String): TblWechatWinning
}