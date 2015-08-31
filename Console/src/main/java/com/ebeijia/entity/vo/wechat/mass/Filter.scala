package com.ebeijia.entity.vo.wechat.mass

/**
 * 群发model
 * Filter
 * @author zhicheng.xu
 *         15/8/12
 */
class Filter {
  private var is_to_all: Boolean = false
  private var group_id: String = null

  def getIs_to_all: Boolean = {
    is_to_all
  }

  def setIs_to_all(is_to_all: Boolean) {
    this.is_to_all = is_to_all
  }

  def getGroup_id: String = {
    group_id
  }

  def setGroup_id(group_id: String) {
    this.group_id = group_id
  }
}
