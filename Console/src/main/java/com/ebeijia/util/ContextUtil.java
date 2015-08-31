package com.ebeijia.util;

import org.springframework.context.ApplicationContext;

/**
 * @author Lingfeng.jiang
 * 
 */
public class ContextUtil {
	
	private static ApplicationContext context;
	public static void setContext(ApplicationContext ctx) {
		context = ctx;
	}
	/**
	 * 获得sping对象
	 * @param id
	 * @return
	 */
	public static Object getBean(String id) {
		Object obj = context.getBean(id);
		if(obj == null) {
		}
		return obj;
	}

}
