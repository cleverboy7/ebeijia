package com.ebeijia.service.wechat.inter

import com.ebeijia.entity.vo.wechat.Menu
import com.ebeijia.service.wechat.core.WechatTokenService
import com.ebeijia.util.wechat.WechatUtil
import net.sf.json.JSONObject
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * MenuManagerImpl菜单管理器类
 * @author zhicheng.xu
 *         15/8/13
 */


@Service("MenuManager")
class MenuManagerImpl extends MenuManager {
  private val logger: Logger = LoggerFactory.getLogger(classOf[MenuManagerImpl])
  @Autowired
  private val wechatTokenService: WechatTokenService = null

  /**
   * 同步菜单
   * @param menu 菜单实例
   * @param accessToken 有效的access_token
   * @return JSONObject
   */
  def createMenu(menu: Menu, accessToken: String): JSONObject = {
    val url: String = WechatUtil.MENU_CREATE_URL.replace("ACCESS_TOKEN", accessToken)
    val jsonMenu: String = JSONObject.fromObject(menu).toString
    val jsonObject: JSONObject = wechatTokenService.httpRequest(url, "POST", jsonMenu)
    jsonObject
  }

  /**
   * 删除菜单
   * @param accessToken 有效的access_token
   * @return JSONObject
   */
  def deleteMenu(accessToken: String): Int = {
    var result: Int = -2
    val requestUrl: String = WechatUtil.MENU_DEL_URL.replace("ACCESS_TOKEN", accessToken)
    val jsonObject: JSONObject = wechatTokenService.httpRequest(requestUrl, "GET", null)
    if (null != jsonObject) {
      if (0 != jsonObject.getInt("errcode")) {
        result = jsonObject.getInt("errcode")
        logger.info("删除菜单失败 errcode:{" + jsonObject.getInt("errcode") + "} errmsg:{," + jsonObject.getString("errmsg") + "}")
      }
    }
    result
  }

  /**
   * 查询菜单
   * @param accessToken 有效的access_token
   * @return JSONObject
   */
  def getMenu(accessToken: String): JSONObject = {
    val requestUrl: String = WechatUtil.MENU_GET_URL.replace("ACCESS_TOKEN", accessToken)
    val jsonObject: JSONObject = wechatTokenService.httpRequest(requestUrl, "post", null)
    jsonObject
  }
}

