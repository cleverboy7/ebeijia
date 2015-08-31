package com.ebeijia.entity.page
import com.ebeijia.util.Constant
/**
 * PageEntity
 * @author zhicheng.xu
 *         15/8/17
 */

class PageEntity {
  private[page] var sEcho: String = null
  private[page] var iDisplayStart: Int = 0
  private[page] var iDisplayLength: Int = Constant.PAGE_SIZE

  def getsEcho: String = {
    sEcho
  }

  def setsEcho(sEcho: String) {
    this.sEcho = sEcho
  }

  def getiDisplayStart: Int = {
    iDisplayStart
  }

  def setiDisplayStart(iDisplayStart: Int) {
    this.iDisplayStart = iDisplayStart
  }

  def getiDisplayLength: Int = {
    iDisplayLength
  }

  def setiDisplayLength(iDisplayLength: Int) {
    this.iDisplayLength = iDisplayLength
  }
}
