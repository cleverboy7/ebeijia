package com.ebeijia.entity.vo.wechat

/**
 * Group
 * @author zhicheng.xu
 *         15/8/11
 */
class Group {
  private var name: String = null
  private var id: String = null

  def getId: String = {
    id
  }

  def setId(id: String) {
    this.id = id
  }

  def getName: String = {
    name
  }

  def setName(name: String) {
    this.name = name
  }
}
