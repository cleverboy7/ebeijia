package com.ebeijia.util.wechat

import javax.net.ssl.{HostnameVerifier, SSLSession}

/**
 * CustomizedHostnameVerifier
 * @author zhicheng.xu
 *         15/8/17
 */

class CustomizedHostnameVerifier extends HostnameVerifier {
  def verify(arg0: String, arg1: SSLSession): Boolean = {
    true
  }
}
