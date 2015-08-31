package com.ebeijia.service.wechat.inter
import net.sf.json.JSONObject
import com.ebeijia.entity.vo.wechat.Menu
/**
 * MenuManager菜单管理器类
 * @author zhicheng.xu
 *         15/8/13
 */

trait MenuManager {
  /**
   * 同步菜单
   * @param menu 菜单实例
   * @param accessToken 有效的access_token
   * @return JSONObject
   */
  def createMenu(menu: Menu, accessToken: String): JSONObject

  /**
   * 删除菜单
   * @param accessToken 有效的access_token
   * @return JSONObject
   */
  def deleteMenu(accessToken: String): Int

  /**
   * 查询菜单
   * @param accessToken 有效的access_token
   * @return JSONObject
   */
  def getMenu(accessToken: String): JSONObject
}
