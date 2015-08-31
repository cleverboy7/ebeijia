package org.ebeijia.tools;

import java.security.MessageDigest;

public class Md5Algorithm4J {

//	private final static Logger _logger = LoggerFactory.getLogger(Md5Algorithm4J.class);
	
	public static String _algorithm(String _$p1, String _$p2) {
		char __s1[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a',
				'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] __s2 = _$p1.getBytes();
			MessageDigest s3 = MessageDigest.getInstance("MD5");
			s3.update(__s2);
			byte[] __s4 = s3.digest();
			int __s5 = __s4.length;
			char __s6[] = new char[__s5 * 2];
			int k = 0;
			for (int i = 0; i < __s5; i++) {
				byte byte0 = __s4[i];
				__s6[k++] = __s1[byte0 >>> 4 & 0xf];
				__s6[k++] = __s1[byte0 & 0xf];
			}

			if ("UCase".equals(_$p2)) {
				return new String(__s6).toUpperCase();
			} else if ("LCase".equals(_$p2)) {
				return new String(__s6);
			} else {
				return new String("Error back MD5 algorithm.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 对 String 进行MD5 加密
	 * @param pwd
	 * @return
	 */
	public static String stringToMD5(String pwd){
		try{
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(pwd.getBytes());
			return new String(messageDigest.digest());
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String encrypt(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};       
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
	

//	@SuppressWarnings("static-access")
//	public static void main(String[] args) {
//		Md5Algorithm4J md5 = new Md5Algorithm4J();
//		_logger.info(md5._algorithm("123456", "LCase"));
//		_logger.info(md5._algorithm("123456", "UCase"));
//		_logger.info(md5._algorithm("123456", "1"));
//	}
}