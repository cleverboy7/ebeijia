package org.ebeijia.tools;

import java.util.Random;
import java.util.UUID;

public class UUID4J {

	/**
	 * 生成32位编码
	 * 
	 * @return string
	 */
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		return uuid;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			String str = UUID4J.getUUID();
			System.out.println(str);
		}
	}
}