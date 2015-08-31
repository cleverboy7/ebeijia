package com.ebeijia.service.picInf

/**
 * Created by xuzhicheng on 15/8/13.
 */

import java.util.List

import com.ebeijia.entity.TblPicInf

trait PicInfService {
  def updatePicInf(picInf: TblPicInf)

  def deletePicInf(picInf: TblPicInf)

  def queryPicInfList: List[_]

  def queryPageList(picInf: TblPicInf, page: Int, size: Int): List[TblPicInf]

  def countTotalNum(picInf: TblPicInf): Int

  def queryPicInfById(id: Int): TblPicInf

  def save(picInf: TblPicInf)

}
