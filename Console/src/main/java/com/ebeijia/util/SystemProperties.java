package com.ebeijia.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;


/**
 * 
 * Read Configuration Files
 * @author 
 * 
 */
public class SystemProperties {
	private final static Logger logger = LoggerFactory.getLogger(SystemProperties.class);

	private static Properties props = null;
	@SuppressWarnings("unchecked")
	private static Map cashePropsMap = new HashMap();
	private static String projectHomePath;
	private static boolean isDebugMode;
	/**
	 * Read Configuration Files，
	 * 增加缓存提高读取速度(improve reading speed increase cache)
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Cacheable(value = "sysCache")
	public static String getProperties(String key) {
		if (props == null)
			systemInit();
		String propsValue = (String)cashePropsMap.get(key);
		if(propsValue==null){
			propsValue = props.getProperty(key);
			cashePropsMap.put(key, propsValue);
		}
		return propsValue;
	}
	
	@SuppressWarnings("unchecked")
	public static int getPropertiesValueOfInteger(String key) {
		if (props == null)
			systemInit();
		String propsValue = (String)cashePropsMap.get(key);
		if(propsValue==null){
			propsValue = props.getProperty(key);
			cashePropsMap.put(key, propsValue);
		}
		return converter(propsValue);
	}
	
	private static int converter(String s){
		int n;
		try{
			n=Integer.parseInt(s);
		}catch(NumberFormatException e){
			throw new RuntimeException(e);
		}
		return n;
	}
	
	public static String getProjectHomePath() {
		return projectHomePath;
	}
	
	public static boolean isDebug(){
		return isDebugMode;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public static String getFullPathProperties(String key) {
		if (props == null)
			systemInit();
		String propsValue = (String)cashePropsMap.get(key);
		if(propsValue==null){
			propsValue = props.getProperty(key);
			cashePropsMap.put(key, propsValue);
		}
		return projectHomePath+propsValue;
	}
	@SuppressWarnings("unchecked")
	public static String getFilePathLoad(String key) {
		if (props == null)
			systemInit();
		String propsValue = (String)cashePropsMap.get(key);
		if(propsValue==null){
			propsValue = props.getProperty(key);
			cashePropsMap.put(key, propsValue);
		}
		return propsValue;
	}	

	private static void loadInitSystemProperties() {
		props = new Properties();
		InputStream in = null;
		try {
			in = SystemProperties.class.getClassLoader().getResourceAsStream(
					"config.properties");
			if (in != null) {
				props.load(in);
			} else {
				throw new RuntimeException(
						" Unable to find the configuration files :config.properties ！");
			}
			projectHomePath = props.getProperty("project.home.path");	
			
			if("1".equals("project.debug.flag")){
				isDebugMode=true;
			}else{
				isDebugMode=true;
			}
			
			
		} catch (Exception ioe) {
			logger.error(ioe.getMessage());
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ioe) {
				logger.error("Close initialization file,throw exception" + ioe.getMessage());
			}
		}
	}

	public static void systemInit() {
		loadInitSystemProperties();
	}
}
