package com.ebeijia.entity.vo.wechat.mass

;

import java.util.List;

/**
 * 群发筛选用户
 *
 * @author zhicheng.xu 
 */
class SendImageMassByUsr extends BaseMass {


  private var image: Image = null
  private var touser: List[_] = null

  def getTouser: List[_] = {
    touser
  }

  def setTouser(toUser: List[_]): Unit = {
    this.touser = touser
  }

  def getImage: Image= {
    image
  }

  def setImage(image: Image): Unit = {
    this.image = image
  }

}  
