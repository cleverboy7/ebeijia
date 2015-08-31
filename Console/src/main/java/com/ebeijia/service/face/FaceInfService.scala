package com.ebeijia.service.face

import org.springframework.web.multipart.MultipartFile

/**
 * FaceInfService
 * @author zhicheng.xu
 *         15/8/17
 */
trait FaceInfService {
  def faceDetect(f: MultipartFile, ext: String): String
}
