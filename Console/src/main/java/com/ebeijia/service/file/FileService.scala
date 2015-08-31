package com.ebeijia.service.file

/**
 * FileService
 * @author zhicheng.xu
 *         15/8/17
 */
trait FileService {
  @throws(classOf[Exception])
  def generateHTML(fileInfo: String)
}
