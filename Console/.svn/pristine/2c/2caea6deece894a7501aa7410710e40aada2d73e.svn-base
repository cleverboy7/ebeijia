package org.ebeijia.tools;

import java.util.Random;

/**
 * Random Number for JAVA（随机数生成类）
 * 
 * @Author jinjian.feng
 * @version 0.1
 */
public class RandomNum4J {

	public static void main(String[] args) {
		for (int i = 0; i < 30; i++) {
			Random random = new Random();
			int num = random.nextInt(1);
			System.out.println(num);
		}
	}
	
	/**
	 * 随机生成6位数字验证码
	 * @return
	 */
	public static String random6(){
		String result = "";
		for (int i = 0; i < 6; i++) {
			Random random = new Random();
			int num = random.nextInt(10);
			result += num;
		}
		return result;	
	}
	
	/**
	 * 随机生成8位数字验证码
	 * @return
	 */
	public static String random8(){
		String result = "";
		for (int i = 0; i < 8; i++) {
			Random random = new Random();
			int num = random.nextInt(10);
			result += num;
		}
		return result;	
	}
	
	
	
	
}
