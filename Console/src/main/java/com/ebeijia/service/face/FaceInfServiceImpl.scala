package com.ebeijia.service.face

import java.io.File

import com.ebeijia.api.face.FaceService
import com.ebeijia.util.{SystemProperties, UpLoad}
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

/**
 * FaceInfServiceImpl
 * @author zhicheng.xu
 *         15/8/17
 */


@Service
final class FaceInfServiceImpl extends FaceInfService {
  @Transactional
  def faceDetect(f: MultipartFile, ext: String): String = {
    val upload: UpLoad = new UpLoad
    val file: File = upload.getFile(f, SystemProperties.getProperties("image.face"), SystemProperties.getProperties("image.path1"), ext)
    val url: String = SystemProperties.getProperties("image.face.url") + file.getName
    System.out.println(url)
    val result: String = FaceService.detect(url)
    result
  }
}
