package com.ebeijia.api.face

import java.io.{BufferedReader, InputStream, InputStreamReader}
import java.net.{HttpURLConnection, URL}
import java.util.{ArrayList, Collections, List}

import net.sf.json.{JSONArray, JSONObject}

/**
 * FaceService
 * @author zhicheng.xu
 *         15/8/19
 */

object FaceService {
  /**
   * 发送http请求
   *
   * @param requestUrl 请求地址
   * @return String
   */
  private def httpRequest(requestUrl: String): String = {
    val buffer: StringBuffer = new StringBuffer
    try {
      val url: URL = new URL(requestUrl)
      val httpUrlConn: HttpURLConnection = url.openConnection.asInstanceOf[HttpURLConnection]
      httpUrlConn.setDoInput(true)
      httpUrlConn.setRequestMethod("GET")
      httpUrlConn.connect
      var inputStream: InputStream = httpUrlConn.getInputStream
      val inputStreamReader: InputStreamReader = new InputStreamReader(inputStream, "utf-8")
      val bufferedReader: BufferedReader = new BufferedReader(inputStreamReader)
      var str: String = null
      while ((({
        str = bufferedReader.readLine;
        str
      })) != null) {
        buffer.append(str)
      }
      bufferedReader.close
      inputStreamReader.close
      inputStream.close
      inputStream = null
      httpUrlConn.disconnect
    }
    catch {
      case e: Exception => {
        e.printStackTrace
      }
    }
    buffer.toString
  }

  /**
   * 调用Face++ API实现人脸检测
   *
   * @param picUrl 待检测图片的访问地址
   * @return List<Face> 人脸列表
   */
  private def faceDetect(picUrl: String): List[Face] = {
    var faceList: List[Face] = new ArrayList[Face]
    try {
      var queryUrl: String = "http://apicn.faceplusplus.com/v2/detection/detect?url=URL&api_secret=API_SECRET&api_key=API_KEY"
      queryUrl = queryUrl.replace("URL", java.net.URLEncoder.encode(picUrl, "UTF-8"))
      queryUrl = queryUrl.replace("API_KEY", "7226346f068c33cab33aff9a7ccf4ca8")
      queryUrl = queryUrl.replace("API_SECRET", "LnQE0eu37He4h7y60laTUagdFhb03BHm")
      val json: String = httpRequest(queryUrl)
      val jsonArray: JSONArray = JSONObject.fromObject(json).getJSONArray("face")
      import scala.collection.JavaConversions._
      for (aJsonArray <- jsonArray) {
        val faceObject: JSONObject = aJsonArray.asInstanceOf[JSONObject]
        val attrObject: JSONObject = faceObject.getJSONObject("attribute")
        val posObject: JSONObject = faceObject.getJSONObject("position")
        val face: Face = new Face
        face.setFaceId(faceObject.getString("face_id"))
        face.setAgeValue(attrObject.getJSONObject("age").getInt("value"))
        face.setAgeRange(attrObject.getJSONObject("age").getInt("range"))
        face.setGenderValue(genderConvert(attrObject.getJSONObject("gender").getString("value")))
        face.setGenderConfidence(attrObject.getJSONObject("gender").getDouble("confidence"))
        face.setRaceValue(raceConvert(attrObject.getJSONObject("race").getString("value")))
        face.setRaceConfidence(attrObject.getJSONObject("race").getDouble("confidence"))
        face.setSmilingValue(attrObject.getJSONObject("smiling").getDouble("value"))
        face.setCenterX(posObject.getJSONObject("center").getDouble("x"))
        face.setCenterY(posObject.getJSONObject("center").getDouble("y"))
        faceList.add(face)
      }
      Collections.sort(faceList)
    }
    catch {
      case e: Exception => {
        faceList = null
        e.printStackTrace
      }
    }
    faceList
  }

  /**
   * 性别转换（英文->中文）
   *
   * @param gender
   * @return
   */
  private def genderConvert(gender: String): String = {
    var result: String = "男"
    if ("Male" == gender) result = "男"
    else if ("Female" == gender) result = "女"
    result
  }

  /**
   * 人种转换（英文->中文）
   *
   * @param race
   * @return
   */
  private def raceConvert(race: String): String = {
    var result: String = "黄种人"
    if ("Asian" == race) result = "黄种人"
    else if ("White" == race) result = "白人"
    else if ("Black" == race) result = "黑人"
    result
  }

  /**
   * 根据人脸识别结果组装消息
   *
   * @param faceList 人脸列表
   * @return
   */
  private def makeMessage(faceList: List[Face]): String = {
    val buffer: StringBuffer = new StringBuffer
    if (1 == faceList.size) {
      buffer.append("共检测到 ").append(faceList.size).append(" 张人脸,")
      import scala.collection.JavaConversions._
      for (face <- faceList) {
        buffer.append(face.getRaceValue).append(",")
        buffer.append(face.getGenderValue).append(", ")
        buffer.append(face.getAgeValue).append("岁左右, ")
        buffer.append(face.getSmilingValue).append("%微笑度")
      }
    }
    else if (faceList.size > 1 && faceList.size <= 10) {
      buffer.append("共检测到 ").append(faceList.size).append(" 张人脸,按脸部中心位置从左至右依次为：")
      import scala.collection.JavaConversions._
      for (face <- faceList) {
        buffer.append(face.getRaceValue).append(",")
        buffer.append(face.getGenderValue).append(", ")
        buffer.append(face.getAgeValue).append("岁左右, ")
        buffer.append(face.getSmilingValue).append("%微笑度")
      }
    }
    else if (faceList.size > 10) {
      buffer.append("共检测到 ").append(faceList.size).append(" 张人脸,")
      var asiaMale: Int = 0
      var asiaFemale: Int = 0
      var whiteMale: Int = 0
      var whiteFemale: Int = 0
      var blackMale: Int = 0
      var blackFemale: Int = 0
      import scala.collection.JavaConversions._
      for (face <- faceList) {
        if ("黄种人" == face.getRaceValue) if ("男" == face.getGenderValue) ({
          asiaMale += 1;
          asiaMale - 1
        })
        else ({
          asiaFemale += 1;
          asiaFemale - 1
        })
        else if ("白人" == face.getRaceValue) if ("男" == face.getGenderValue) ({
          whiteMale += 1;
          whiteMale - 1
        })
        else ({
          whiteFemale += 1;
          whiteFemale - 1
        })
        else if ("黑人" == face.getRaceValue) if ("男" == face.getGenderValue) ({
          blackMale += 1;
          blackMale - 1
        })
        else ({
          blackFemale += 1;
          blackFemale - 1
        })
      }
      if (0 != asiaMale || 0 != asiaFemale) buffer.append("黄种人：").append(asiaMale).append("男").append(asiaFemale).append("女,")
      if (0 != whiteMale || 0 != whiteFemale) buffer.append("白人：").append(whiteMale).append("男").append(whiteFemale).append("女,")
      if (0 != blackMale || 0 != blackFemale) buffer.append("黑人：").append(blackMale).append("男").append(blackFemale).append("女,")
    }
    buffer.toString
  }

  /**
   * 提供给外部调用的人脸检测方法
   *
   * @param picUrl 待检测图片的访问地址
   * @return String
   */
  def detect(picUrl: String): String = {
    var result: String = "未识别到人脸，请换一张清晰的照片再试！"
    val faceList: List[Face] = faceDetect(picUrl)
    if (!faceList.isEmpty) {
      result = makeMessage(faceList)
    }
    result
  }

  def main(args: Array[String]) {
    val picUrl: String = "http://114.215.180.20/ipyPIC/111.jpg"
    System.out.println(detect(picUrl))
  }
}


