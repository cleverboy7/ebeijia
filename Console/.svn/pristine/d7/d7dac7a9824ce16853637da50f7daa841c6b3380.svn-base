package org.ebeijia.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class File4J {
	
	static Logger logger = LoggerFactory.getLogger(File4J.class.getName());
	
	private String fileName = null;
	private static File4J instance = new File4J();
	private Properties prop = new Properties();

	private File4J() {

	}

	public static File4J getInstance() {
		
		return instance;
	}

	public void setTitle(String titleStr) {
		fileName = "/" + titleStr;
		try {
			java.io.InputStream is = getClass().getResourceAsStream(fileName);
			prop.load(is);
			if (is != null)
				is.close();
		} catch (Exception e) {
			logger.debug(e + "file  not found");
		}
	}

	public String getProperty(String keyStr) {
		return prop.getProperty(keyStr);
	}

	public Properties getProperties(String fileName) {
		fileName = "/" + fileName;
		try {
			java.io.InputStream is = getClass().getResourceAsStream(fileName);
			prop.load(is);
			logger.debug("配置文件:" + fileName + " 读取成功");
		} catch (NullPointerException e) {
			throw new NullPointerException("配置文件不存在");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	/**
	 * 得到配置文件app.properties的下载路径
	 * 
	 * 
	 */
	public static String getdownLoadPath() throws FileNotFoundException {
		Properties p = File4J.getInstance().getProperties("./app.properties");
		String pp = p.getProperty("downLoadPath");
		return pp;
	}

	/**
	 * 得到配置文件app.properties的下载路径
	 * 
	 * 
	 */
	public static String getdownLoadPath(String filePath)
			throws FileNotFoundException {
		Properties p = File4J.getInstance().getProperties("./app.properties");
		String pp = p.getProperty(filePath);
		return pp;
	}

	/**
	 * 得到配置文件app.properties的保存文件路径
	 * 
	 * 
	 */
	public static String getdateFilePath() throws FileNotFoundException {
		Properties p = File4J.getInstance().getProperties("./app.properties");
		String pp = p.getProperty("dateFilePath");
		return pp;
	}

	/**
	 * 得到配置文件app.properties的保存文件路径
	 * 
	 * 
	 */
	public static String getdateFilePath(String filePath)
			throws FileNotFoundException {
		Properties p = File4J.getInstance().getProperties("./app.properties");
		String pp = p.getProperty(filePath);
		return pp;

	}

	public Properties getFileProperties(String fileName) {
		try {
			File file = new File(fileName);
			java.io.InputStream is = new java.io.FileInputStream(file);
			prop.load(is);
			logger.info("配置文件:" + fileName + " 读取成功");
		} catch (NullPointerException e) {
			throw new NullPointerException("配置文件不存在");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	/**
	 * 创建单个文件
	 * 
	 * @param destFileName 目标文件名
	 * @return 创建成功，返回true，否则返回false
	 */
	public static boolean createFile(String destFileName) {
		File file = new File(destFileName);
		if (file.exists()) {
			// System.out.println("创建单个文件" + destFileName + "失败，目标文件已存在！");
			return false;
		}
		if (destFileName.endsWith(File.separator)) {
			logger.debug("创建单个文件" + destFileName + "失败，目标文件不能为目录！");
			return false;
		}
		// 判断目标文件所在的目录是否存在
		if (!file.getParentFile().exists()) {
			// 如果目标文件所在的文件夹不存在，则创建父文件夹
			logger.debug("目标文件所在目录不存在，准备创建它！");
			if (!file.getParentFile().mkdirs()) {
				logger.debug("创建目标文件所在的目录失败！");
				return false;
			}
		}
		// 创建目标文件
		try {
			if (file.createNewFile()) {
				// logger.debug("创建单个文件" + destFileName + "成功！");
				return true;
			} else {
				logger.debug("创建单个文件" + destFileName + "失败！");
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.debug("创建单个文件" + destFileName + "失败！" + e.getMessage());
			return false;
		}
	}

	/**
	 * 创建目录
	 * 
	 * @param destDirName
	 *            目标目录名
	 * @return 目录创建成功放回true，否则返回false
	 */
	public static boolean createDir(String destDirName) {
		File dir = new File(destDirName);
		if (dir.exists()) {
			// logger.debug("创建目录" + destDirName + "失败，目标目录已存在！");
			return false;
		}
		if (!destDirName.endsWith(File.separator)) {
			destDirName = destDirName + File.separator;
		}
		// 创建目标目录
		if (dir.mkdirs()) {
			logger.debug("创建目录" + destDirName + "成功！");
			return true;
		} else {
			logger.debug("创建目录" + destDirName + "失败！");
			return false;
		}
	}

	/**
	 * 创建临时文件
	 * 
	 * @param prefix 临时文件名的前缀
	 * @param suffix 临时文件名的后缀
	 * @param dirName 临时文件所在的目录，如果输入null，则在用户的文档目录下创建临时文件
	 * @return 临时文件创建成功返回true，否则返回false
	 */
	public static String createTempFile(String prefix, String suffix,
			String dirName) {
		File tempFile = null;
		if (dirName == null) {
			try {
				// 在默认文件夹下创建临时文件
				tempFile = File.createTempFile(prefix, suffix);
				// 返回临时文件的路径
				return tempFile.getCanonicalPath();
			} catch (IOException e) {
				e.printStackTrace();
				logger.debug("创建临时文件失败!" + e.getMessage());
				return null;
			}
		} else {
			File dir = new File(dirName);
			// 如果临时文件所在目录不存在，首先创建
			if (!dir.exists()) {
				if (File4J.createDir(dirName)) {
					logger.debug("创建临时文件失败，不能创建临时文件所在的目录！");
					return null;
				}
			}
			try {
				// 在指定目录下创建临时文件
				tempFile = File.createTempFile(prefix, suffix, dir);
				return tempFile.getCanonicalPath();
			} catch (IOException e) {
				e.printStackTrace();
				logger.debug("创建临时文件失败!" + e.getMessage());
				return null;
			}
		}
	}

	/**
	 * 删除单个文件
	 * 
	 * @param fileName 被删除文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		// 如果文件路径对应的文件存在，并且是一个文件，则直接删除。
		if (file.exists() && file.isFile()) {
			if (file.delete()) {
				logger.debug("删除单个文件" + fileName + "成功！");
				return true;
			} else {
				logger.debug("删除单个文件" + fileName + "失败！");
				return false;
			}
		} else {
			logger.debug("删除单个文件失败：" + fileName + "文件不存在！");
			return false;
		}
	}

	/**
	 * 以行为单位读取文件，常用于读面向行的格式化文件
	 * 
	 * @param fileName
	 *            文件名
	 * 
	 */
	public static void readFileByLines(String fileName) {
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			logger.debug("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				logger.debug("line " + line + ": " + tempString);
				line++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
	}

	/**
	 * 以行为单位写文件
	 * 
	 * @param fileName
	 *            文件名
	 * 
	 */
	public static void writeFileByLines(String fileName, String str) {
		File file = new File(fileName);
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new FileOutputStream(file));
			// 写字符串
			writer.print(str);
			// 能写各种基本类型数据
			// writer.print(true);
			// writer.print(155);
			// 换行
			// writer.println();
			// 写入文件
			writer.flush();
			logger.debug("写文件" + file.getAbsolutePath() + "成功！");
		} catch (FileNotFoundException e) {
			logger.debug("写文件" + file.getAbsolutePath() + "失败！");
			e.printStackTrace();
		} finally {
			if (writer != null) {
				// 关闭输出文件流
				writer.close();
			}
		}
	}

	public static void changeRight(String fileName) {
		String cmds = "chmod 777 " + fileName;
		try {
			Runtime.getRuntime().exec(cmds);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 字符串编码转换的实现方法
	 * 
	 * @param str
	 *            待转换编码的字符串
	 * @param oldCharset
	 *            原编码
	 * @param newCharset
	 *            目标编码
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String changeCharset(String str, String oldCharset,
			String newCharset) throws UnsupportedEncodingException {
		if (str != null) {
			// 用旧的字符编码解码字符串。解码可能会出现异常。
			byte[] bs = str.getBytes(oldCharset);
			// 用新的字符编码生成字符串
			return new String(bs, newCharset);
		}
		return null;
	}

//	/*
//	 * 转换文件名编码
//	 */
//	public static String convertFileName(String srcFileName) {
//
//		try {
//			srcFileName = ChangeCharset.changeCharset(srcFileName,
//					ChangeCharset.GBK, ChangeCharset.ISO_8859_1);
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//
//		return srcFileName;
//	}
	
	/*********************************************/
	/**
	 * 新建目录
	 * 
	 * @param folderPath
	 *            String 如 c:/fqf
	 * @return boolean
	 */
	public static void newFolder(String folderPath) {
		try {
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			if (!myFilePath.exists()) {
				myFilePath.mkdir();
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("新建目录操作出错");
		}
	}

	/**
	 * 新建文件
	 * 
	 * @param filePathAndName
	 *            String 文件路径及名称 如c:/fqf.txt
	 * @param fileContent
	 *            String 文件内容
	 * @return boolean
	 */
	public static void newFile(String filePathAndName, String fileContent) {
		FileWriter resultFile = null;
		PrintWriter myFile = null;
		try {
			String filePath = filePathAndName;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			if (!myFilePath.exists()) {
				myFilePath.createNewFile();
			}
			resultFile = new FileWriter(myFilePath);
			myFile = new PrintWriter(resultFile);
			String strContent = fileContent;
			myFile.println(strContent);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("新建文件出错");
		} finally {
			try {
				if (resultFile != null) {
					resultFile.close();
				}
				if (myFile != null) {
					myFile.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				logger.error("关闭文件出错");
			}
		}

	}

	/**
	 * 删除文件
	 * 
	 * @param filePathAndName
	 *            String 文件路径及名称 如c:/fqf.txt
	 * @param fileContent
	 *            String
	 * @return boolean
	 */
	public static boolean delFile(String filePathAndName) {
		try {
			String filePath = filePathAndName;
			filePath = filePath.toString();
			java.io.File myDelFile = new java.io.File(filePath);
			myDelFile.delete();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除文件出错");
			return false;
		}

	}

	/**
	 * 删除文件夹
	 * 
	 * @param filePathAndName
	 *            String 文件夹路径及名称 如c:/fqf
	 * @param fileContent
	 *            String
	 * @return boolean
	 */
	public static boolean delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			myFilePath.delete(); // 删除空文件夹
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除文件夹出错");
		}
		return true;

	}

	/**
	 * 删除文件夹里面的所有文件
	 * 
	 * @param path
	 *            String 文件夹路径 如 c:/fqf
	 */
	public static void delAllFile(String path) {
		File file = new File(path);
		if (!file.exists()) {
			return;
		}
		if (!file.isDirectory()) {
			return;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + File.separator + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + File.separator + tempList[i]);// 再删除空文件夹
			}
		}
	}

	/**
	 * 复制单个文件
	 * 
	 * @param oldPath
	 *            String 原文件路径 如：c:/fqf.txt
	 * @param newPath
	 *            String 复制后路径 如：f:/fqf.txt
	 * @return boolean
	 */
	public static boolean copyFile(String oldPath, String newPath) {
		InputStream inStream = null;
		FileOutputStream fs = null;
		try {
			//int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { // 文件存在时
				inStream = new FileInputStream(oldPath); // 读入原文件
				File file = new File(newPath);
				if (!file.exists()) { /* 如果文件已经存在则创建新文件 */
					file.createNewFile();
				}
				fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				while ((byteread = inStream.read(buffer)) != -1) {
					//bytesum += byteread; // 字节数 文件大小
					fs.write(buffer, 0, byteread);
				}
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("复制文件出错");
			return false;
		} finally {
			try {
				if (inStream != null) {
					inStream.close();
				}
				if (fs != null) {
					fs.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				logger.error("关闭文件出错");
			}
		}
	}

	/**
	 * 复制整个文件夹内容
	 * 
	 * @param oldPath
	 *            String 原文件路径 如：c:/fqf
	 * @param newPath
	 *            String 复制后路径 如：f:/fqf/ff
	 * @return boolean
	 */
	public static boolean copyFolder(String oldPath, String newPath) {
		FileInputStream input = null;
		FileOutputStream output = null;
		try {
			(new File(newPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹
			File a = new File(oldPath);
			String[] file = a.list();
			File temp = null;
			for (int i = 0; i < file.length; i++) {
				if (oldPath.endsWith(File.separator)) {
					temp = new File(oldPath + file[i]);
				} else {
					temp = new File(oldPath + File.separator + file[i]);
				}

				if (temp.isFile()) {
					input = new FileInputStream(temp);
					output = new FileOutputStream(newPath + File.separator
							+ (temp.getName()).toString());
					byte[] b = new byte[1024 * 5];
					int len;
					while ((len = input.read(b)) != -1) {
						output.write(b, 0, len);
					}
					output.flush();
				}
				if (temp.isDirectory()) {// 如果是子文件夹
					copyFolder(oldPath + File.separator + file[i], newPath
							+ File.separator + file[i]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("复制文件出错");

		} finally {
			try {
				if (output != null) {
					output.close();
				}
				if (input != null) {
					input.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				logger.error("关闭文件出错");
			}
		}
		return true;

	}

	/**
	 * 移动文件到指定目录
	 * 
	 * @param oldPath
	 *            String 如：c:/fqf.txt
	 * @param newPath
	 *            String 如：d:/fqf.txt
	 */
	public static boolean moveFile(String oldPath, String newPath) {
		if (copyFile(oldPath, newPath)) {
			return delFile(oldPath);
		}
		return false;
	}

	/**
	 * 移动文件夹到指定目录
	 * 
	 * @param oldPath
	 *            String 如：c:/fqf.txt
	 * @param newPath
	 *            String 如：d:/fqf.txt
	 */
	/**
	 * 根据文件路径查找所有的子文件
	 * 
	 * @param dirPath
	 *            String : 目录路径
	 */
	public static boolean moveFolder(String oldPath, String newPath) {
		copyFolder(oldPath, newPath);
		delFolder(oldPath);
		return true;
	}


	// 判断是否是zip文件
	public static boolean isZip(String filename) {
		int len = filename.length();
		int spotNum = filename.lastIndexOf(".");
		String suffix = filename.substring(spotNum, len);
		if (suffix.equals(".zip")) {
			return true;
		}
		return false;
	}



}
