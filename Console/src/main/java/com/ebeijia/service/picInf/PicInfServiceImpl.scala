package com.ebeijia.service.picInf

import java.util.List
import com.ebeijia.dao.picInf.PicInfDao
import com.ebeijia.entity.TblPicInf
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.{CacheEvict, Cacheable}
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * PicInfServiceImpl
 * @author zhicheng.xu
 *         15/8/13
 */


@Service final class PicInfServiceImpl extends PicInfService {
  @Autowired
  private val picInfDao: PicInfDao = null

  @Transactional
  @CacheEvict(value = Array("picCache"), allEntries = true)
  def updatePicInf(picInf: TblPicInf) {
    picInfDao.saveOrUpdate(picInf)
  }

  @Transactional
  @CacheEvict(value = Array("picCache"), allEntries = true)
  def deletePicInf(picInf: TblPicInf) {
    picInfDao.update(picInf)
  }

  @Transactional
  @CacheEvict(value = Array("picCache"), allEntries = true)
  def save(picInf: TblPicInf) {
    picInfDao.save(picInf)
  }

  @Transactional
  @Cacheable(value = Array("picCache"))
  def queryPicInfList: List[_] = {
    picInfDao.getPicInfList
  }

  @Transactional
  @Cacheable(value = Array("picCache"))
  def queryPageList(picInf: TblPicInf, page: Int, size: Int): List[TblPicInf] = {
    picInfDao.findByPage(picInf, page, size)
  }

  @Transactional
  @Cacheable(value = Array("picCache"), key = "#root.method.name+#picInf")
  def countTotalNum(picInf: TblPicInf): Int = {
    picInfDao.countTotalNum(picInf)
  }

  @Transactional
  @Cacheable(value = Array("picCache"), key = "#root.method.name+#id")
  def queryPicInfById(id: Int): TblPicInf = {
    picInfDao.getById(id)
  }


}
