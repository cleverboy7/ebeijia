package org.ebeijia.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Properties for JAVA（.properties配置文件读写操作类）
 * 
 * @Author jinjian.feng
 * @version 0.1
 */
public class Properties4J {
	private final static Logger logger = LoggerFactory.getLogger(Properties4J.class);

	private Properties m_prop = null;
	private static Properties props = null;
	private static Map<Object, Object> cashePropsMap = new HashMap<Object, Object>();
	private static String projectHomePath;

	/**
	 * 读取配置文件， 增加缓存提高读取速度
	 * 
	 * @param key
	 * @return
	 */
	public static String getProperties(String fileName,String key) {
		if (props == null)
			systemInit(fileName);
		String propsValue = (String) cashePropsMap.get(key);
		if (propsValue == null) {
			propsValue = props.getProperty(key);
			cashePropsMap.put(key, propsValue);
		}
		return propsValue;
	}

	public static String getProjectHomePath(String fileName) {
		if (props == null)
			systemInit(fileName);
		return projectHomePath;
	}

	public static String getFullPathProperties(String fileName,String key) {
		if (props == null)
			systemInit(fileName);
		String propsValue = (String) cashePropsMap.get(key);
		if (propsValue == null) {
			propsValue = props.getProperty(key);
			cashePropsMap.put(key, propsValue);
		}
		return projectHomePath + propsValue;
	}

	private static void loadInitSystemProperties(String fileName) {
		props = new Properties();
		InputStream in = null;
		try {
			in = Properties4J.class.getClassLoader().getResourceAsStream(
					fileName + ".properties");
			if (in != null) {
				props.load(in);
			} else {
				throw new RuntimeException(
						" no find file : config.properties ！");
			}
			projectHomePath = props.getProperty("project.home.path");
		} catch (Exception ioe) {
			logger.error(ioe.getMessage());
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ioe) {
				logger.error(" close init file exception : " + ioe.getMessage());
			}
		}
	}

	public static void systemInit(String fileName) {
		loadInitSystemProperties(fileName);
		logger.info(" Initializes the global configuration file ");
	}

	public Properties getProperties() {
		return this.m_prop;
	}

	public Properties4J(String fileName) throws Exception {
		m_prop = File4J.getInstance().getProperties(fileName);
	}

	public String getProperty(String s) throws Exception {
		try {
			if (m_prop != null)
				return m_prop.getProperty(s);
			else
				return "";
		} catch (Exception e) {
			logger.debug("getProperty.." + e.getMessage());
			throw e;
		}
	}
	
	public static String getPropertiesContent(String path, String key) {
		try {
			InputStream inStream = new FileInputStream(new File(path));
			Properties p = new Properties();
			p.load(inStream);
			String value = p.getProperty(key);
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	 /**
	    * 更新（或插入）一对properties信息(主键及其键值)
	    * 如果该主键已经存在，更新该主键的值；
	    * 如果该主键不存在，则插件一对键值。
	    * @param keyname 键名
	    * @param keyvalue 键值
	    */
	    public static void writeProperties(String path,String keyname,String keyvalue) {       
	        try {
	            OutputStream fos = new FileOutputStream(path);
	            props.setProperty(keyname, keyvalue);
	            // 将此 Properties 表中的属性列表（键和元素对）写入输出流
	            props.store(fos, "Update '" + keyname + "' value");
	        } catch (IOException e) {
	            System.err.println("属性文件更新错误");
	        }
	    }
}