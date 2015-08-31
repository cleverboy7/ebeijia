package com.ebeijia.util;

import java.util.ResourceBundle;

/**
 * 系统参数 配置
 * @author lingfeng.jiang
 *
 */
public class ParamInfoUtil {
	
	private static String SYSPARAM_FILE = "SysParam";

	private static ResourceBundle RB = ResourceBundle.getBundle(SYSPARAM_FILE);
	
	private static String JDBC_FILE = "jdbc";
	
	private static ResourceBundle JDBC = ResourceBundle.getBundle(JDBC_FILE);
	/**
	 * 获得系统参数
	 * @param key
	 * @return
	 */
	public static String getParam(String key) {
		return RB.getString(key);
	}
	
	/**
	 * 获取jdbc连接参数
	 */
	public static String getJdbc(String key){
		return JDBC.getString(key);
	}
}
