package com.ebeijia.service.wechat.inter

import java.io.File

import com.ebeijia.entity.vo.wechat.kf.Kf
import net.sf.json.JSONObject

/**
 * KfManager
 * @author zhicheng.xu
 *         15/8/13
 */
trait KfManager {
  /**
   * 添加客服
   * @param kf 客服对象
   * @param accessToken 有效的access_token
   * @return JSONObject
   */
  def createKf(kf: Kf, accessToken: String): JSONObject

  /**
   * 修改客服
   * @param kf 客服对象
   * @param accessToken 有效的access_token
   * @return JSONObject
   */
  def updKf(kf: Kf, accessToken: String): JSONObject

  /**
   * 删除客服
   * @param account 客服账号
   * @param accessToken 有效的access_token
   * @return JSONObject
   */
  def delKf(account: String, accessToken: String): JSONObject

  /**
   * 上传客服头像
   * @param type 素材类型
   * @param f 文件对象
   * @param ext 后缀
   * @param accessToken 有效的access_token
   * @return JSONObject
   */
  def upHeadKf(accessToken: String, `type`: String, f: File, ext: String): JSONObject
}

