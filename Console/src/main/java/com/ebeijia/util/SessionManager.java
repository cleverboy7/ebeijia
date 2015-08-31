package com.ebeijia.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

/**
 * 会话管理器,保存整个web应用范围内所有已经录用户的session信息 
 * @author bison
 *
 */
public class SessionManager {

	private static Map<String, HttpSession> sessions = new ConcurrentHashMap<String, HttpSession>();

	/**
	 * 保存用户会话信息
	 * @param operId
	 * @param newSession
	 */
	public static void putSession(String operId, HttpSession newSession) {
		if (StringUtils.isNotBlank(operId) && null != newSession) {
				HttpSession existSession = sessions.get(operId);
				if (existSession == null) {
					sessions.put(operId, newSession);
				}
		}
	}
	
	/**
	 * 删除指定用户会话信息
	 * @param operId
	 */
	public static void removeSession(String operId) {
		if (StringUtils.isNotBlank(operId)) {
			HttpSession existSession = sessions.get(operId);
			if (null != existSession) {
				sessions.remove(operId);
			}
		}
		
	}

	/**
	 * 获取指定用户会话信息
	 * @param operId
	 */
	public static HttpSession getSession(String operId) {
		HttpSession returnSession = null;
		if (StringUtils.isNotBlank(operId)) {
			returnSession = sessions.get(operId);
		}
		return returnSession;
	}
}