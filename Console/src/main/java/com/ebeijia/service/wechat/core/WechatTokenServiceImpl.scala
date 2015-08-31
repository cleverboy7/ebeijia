
package com.ebeijia.service.wechat.core

import java.io.{BufferedReader, File, IOException, InputStream, InputStreamReader, OutputStream}
import java.net.{ConnectException, URL}
import java.security.SecureRandom
import javax.net.ssl.{HttpsURLConnection, SSLContext, SSLSocketFactory, TrustManager}

import com.ebeijia.entity.vo.wechat.AccessToken
import com.ebeijia.util.wechat.{CustomizedHostnameVerifier, MyX509TrustManager, WechatUtil}
import net.sf.json.{JSONException, JSONObject}
import org.apache.commons.httpclient.HttpClient
import org.apache.commons.httpclient.methods.multipart.{FilePart, MultipartRequestEntity, Part, StringPart}
import org.apache.commons.httpclient.methods.{GetMethod, PostMethod}
import org.apache.commons.httpclient.params.HttpMethodParams
import org.apache.http.client.methods.HttpPost
import org.apache.http.client.params.CookiePolicy
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.DefaultHttpClient
import org.apache.http.{HttpResponse, HttpStatus}
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

/**
 * WechatTokenServiceImpl
 * @author zhicheng.xu
 *         15/8/13
 */

@Service
final class WechatTokenServiceImpl extends WechatTokenService {
  private var logger: Logger = LoggerFactory.getLogger(classOf[WechatTokenServiceImpl])

  /**
   * 发起https请求并获取结果
   * @param requestUrl 请求地址
   * @param requestMethod  请求方式（GET、POST）
   * @param outputStr  提交的数据
   * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
   */
  def httpRequest(requestUrl: String, requestMethod: String, outputStr: String): JSONObject = {
    var jsonObject: JSONObject = null
    val buffer: StringBuffer = new StringBuffer
    try {
      val tm: Array[TrustManager] = Array(new MyX509TrustManager)
      val sslContext: SSLContext = SSLContext.getInstance("SSL", "SunJSSE")
      sslContext.init(null, tm, new SecureRandom)
      val ssf: SSLSocketFactory = sslContext.getSocketFactory
      val url: URL = new URL(requestUrl)
      val httpUrlConn: HttpsURLConnection = url.openConnection.asInstanceOf[HttpsURLConnection]
      httpUrlConn.setSSLSocketFactory(ssf)
      httpUrlConn.setDoOutput(true)
      httpUrlConn.setDoInput(true)
      httpUrlConn.setUseCaches(false)
      httpUrlConn.setHostnameVerifier(new CustomizedHostnameVerifier)
      httpUrlConn.setRequestMethod(requestMethod)
      System.setProperty("jsse.enableSNIExtension", "false")
      if ("GET".equalsIgnoreCase(requestMethod))
        httpUrlConn.connect()
      if (null != outputStr) {
        val outputStream: OutputStream = httpUrlConn.getOutputStream
        outputStream.write(outputStr.getBytes("UTF-8"))
        outputStream.close()
      }
      var inputStream: InputStream = httpUrlConn.getInputStream
      val inputStreamReader: InputStreamReader = new InputStreamReader(inputStream, "utf-8")
      val bufferedReader: BufferedReader = new BufferedReader(inputStreamReader)
      var str: String = null
      while ((str = bufferedReader.readLine()) != null) {
        buffer.append(str)
      }
      bufferedReader.close()
      inputStreamReader.close()
      inputStream.close()
      inputStream = null
      httpUrlConn.disconnect()
      jsonObject = JSONObject.fromObject(buffer.toString)
    }
    catch {
      case ce: ConnectException => {
        logger.info("Weixin server connection timed out.")
      }
      case e: Exception => {
        logger.info("https request error:{}" + e)
      }
    }
    jsonObject
  }

  /**
   * 获取access_token
   * @param appid 凭证
   * @param appsecret 密钥
   * @return AccessToken
   */
  @Cacheable(Array("wechatTokenCache")) def getAccessToken(appid: String, appsecret: String): AccessToken = {
    var accessToken: AccessToken = null
    val requestUrl: String = WechatUtil.ACCESS_TOKEN_URL.replace("APPID", appid).replace("APPSECRET", appsecret)
    System.out.println(requestUrl)
    val jsonObject: JSONObject = httpRequest(requestUrl, "GET", null)
    if (null != jsonObject) {
      try {
        accessToken = new AccessToken
        accessToken.setToken(jsonObject.getString("access_token"))
        accessToken.setExpiresIn(jsonObject.getInt("expires_in"))
      }
      catch {
        case e: JSONException => {
          accessToken = null
        }
      }
    }
    accessToken
  }

  /**
   * 上传多媒体
   * @param url
   * @param access_token
   * @param file
   * @return result
   */
  def upload(url: String, access_token: String, file: File, `type`: String, ext: String, flag: String): JSONObject = {
    val client: HttpClient = new HttpClient
    val post: PostMethod = new PostMethod(url)
    try {
      if (file != null && file.exists) {
        val filepart: FilePart = new FilePart("media", file, `type` + "/" + ext, "UTF-8")
        var parts: Array[Part] = null
        if (flag != null) {
          val tmp: String = "{\"title\":\"VIDEO_TITLE\",\"introduction\":\"INTRODUCTION\"}"
          val sp: StringPart = new StringPart("description", tmp)
          parts = Array[Part](filepart, sp)
        }
        else {
          parts = Array[Part](filepart)
        }
        val entity: MultipartRequestEntity = new MultipartRequestEntity(parts, post.getParams)
        post.setRequestEntity(entity)
        val status: Int = client.executeMethod(post)
        if (status == HttpStatus.SC_OK) {
          val responseContent: String = post.getResponseBodyAsString
          JSONObject.fromObject(responseContent)
        }
      }
    }
    catch {
      case e: Exception => {
        e.printStackTrace
      }
    }
    null
  }

  /**
   * 下载多媒体
   *
   * @param url
   * @param method
   * @param mediaId
   * @return String
   */
  def dowload(url: String, method: String, mediaId: String): String = {
    val httpClient: HttpClient = new HttpClient
    httpClient.getParams.setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8")
    var br: BufferedReader = null
    var response: HttpResponse = null
    httpClient.getParams.setCookiePolicy(CookiePolicy.IGNORE_COOKIES)
    var result: String = null
    var statusCode: Int = 0
    try {
      val getMethod: GetMethod = new GetMethod(url)
      val request: HttpPost = new HttpPost(url)
      if (method == "GET") {
        statusCode = httpClient.executeMethod(getMethod)
      }
      else {
        val se: StringEntity = new StringEntity("{\"media_id\":\"" + mediaId + "\"}")
        request.setEntity(se)
        response = new DefaultHttpClient().execute(request)
        statusCode = response.getStatusLine.getStatusCode
      }
      if (statusCode == 200) {
        var in: InputStream = null
        if (method == "GET") {
          in = getMethod.getResponseBodyAsStream
        }
        else {
          in = response.getEntity.getContent
        }
        br = new BufferedReader(new InputStreamReader(in, "UTF-8"))
        val line: String = br.readLine
        if (line != null && line.indexOf("errcode") == -1) {
          result = url
        }
        else {
           line
        }
        if (br != null) br.close
      }
    }
    catch {
      case ex: Exception => {
        ex.printStackTrace
      }
    } finally {
      if (br != null) {
        try {
          br.close
        }
        catch {
          case e: IOException => {
            e.printStackTrace
          }
        }
      }
    }
     result
  }
}
