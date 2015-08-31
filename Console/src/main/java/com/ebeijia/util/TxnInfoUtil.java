package com.ebeijia.util;

import java.util.ResourceBundle;


/**
 * 获取交易码 名称  key : value
 * @author lingfeng.jiang
 *
 */
public class TxnInfoUtil {

	private static String TXN = "txn";
	
	private static ResourceBundle RB = ResourceBundle.getBundle(TXN);
	
	/**
	 * 获得交易信息
	 * @param key
	 * @return
	 */
	public static String getTxnInfo(String key) {
		return RB.getString(key);
	}
	
}
