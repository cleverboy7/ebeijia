package org.ebeijia.tools;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;



/**
 * String Convert for JAVA（字符串转换类）
 * @Author jinjian.feng
 * @version 0.1
 */
public class String4J {
	
	/**
	 * 去除字符串的前后空格
	 * @param oldString
	 * @return 如有值则去除，如没有则返回null
	 */
	public static String stringTrim(String oldString){
		String newString = null;
		if(oldString != null  && !"".equals(oldString)){
			newString = oldString.trim();
		}
		return newString;
	}
	
	/**
	 *   填充字符
	 * @param value
	 * @param len
	 * @param fillValue
	 * @return
	 */
	public static String fillValue(String value, int len, char fillValue) {
		String str = (value == null) ? "" : value.trim();
		StringBuffer result = new StringBuffer();
		result.append(str);
		int paramLen = str.length();
		if (paramLen < len) {
			for (int i = 0; i < len - paramLen; i++) {
				result.append(fillValue);
			}
		}
		return result.toString();
	}
	
	/**
	 * 在value后变插入count次appendValue
	 * 
	 * @param value
	 * @param count
	 *            插入的次数
	 * @param appendValue
	 * @return
	 */
	public static String appendValue(String value, int count, String appendValue) {
		if (count < 1) {
			return value;
		}
		StringBuffer result = new StringBuffer();
		result.append(value);
		for (int i = 0; i < count; i++) {
			result.append(appendValue);
		}
		return result.toString();
	}

	/**
	 * 填充字符
	 * 
	 * @param value
	 * @param len
	 * @param fillValue
	 * @return
	 */
	public static String beforFillValue(String value, int len, char fillValue) {
		String str = (value == null) ? "" : value.trim();
		StringBuffer result = new StringBuffer();
		int paramLen = str.length();
		if (paramLen < len) {
			for (int i = 0; i < len - paramLen; i++) {
				result.append(fillValue);
			}
		}
		result.append(str);
		return result.toString();
	}
	
	/**
	 * 在字符串左边补0,使长度达到len
	 * @param value
	 * @param len
	 * @return
	 */
	public static String fillZeroLeft(String value ,int len,String leftValue){
		while(value.length()<len){
			value = leftValue+value;
		}
		return value;
	}

	/**
	 * 将数组的每个元素后加入split，然后组成字符串返回
	 * 
	 * @param arr
	 *            字符串数组
	 * @param split
	 *            插入字符
	 * @return
	 */
	public static String arrayToStr(Object[] arr, String split) {
		if (arr == null || arr.length == 0) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			if (i > 0) {
				sb.append(split);
			}
			sb.append(arr[i]);

		}
		return sb.toString();
	}
	
	/**
	 * 判断信息是否为空
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		if(str != null && !"".equals(str.trim()))
			return true;
		else
			return false;
	}
	/**
	 * 判断 字符串数组 中每一个字符串是否有值
	 * @param values
	 * @return
	 */
	public static boolean valuesIsNotEmpty(String[] values) {
		for(int i = 0;i<values.length;i++){
			if(!isNotEmpty(values[i])){
				return false;
			}
		}
		return true;
	}
	/**
	 *判断字符串数组中是否有重复的元素
	 * @param names
	 * @return  有重复的返回true；反之false
	 */
	public static boolean StringArr(String[] names){
		boolean flag = false;
		   for(int i=0;i<names.length;i++){
		        for(int j=i+1;j<names.length;j++){
		            if(names[i].equals(names[j])){
		                 flag = true;
		            }
		       }
		   }
		return flag;
	}
			
    /**
     * 字符串数组去重
     * @param names
     * @return  返回去重后的list集合
     */
	public static List<String> StringRemove(String[] names){
		List<String> data = new ArrayList<String>();	 
        for (String name : names) {
            if (!data.contains(name)) {
                data.add(name);
            }
        } 	
		return data;
	}
	/**
	 * 查找指定元素在list集合中的位置(从0开始)
	 * @param list
	 * @param str
	 * @return 有则返回当前位置 ； 反之返回-1    
	 */
	public static int number(List<String> list,String str){
		for(int i = 0;i<list.size(); i++){
			if(str.equals(list.get(i))){
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * 返回list中的重复元素的标号，第一个不返回
	 * @param list
	 * @return
	 */
	public static String rmDuplicate(List<String> list){
		String result = "";
		for(int i = 0; i < list.size();i++){
			for(int j = i+1;j < list.size(); j++){
				if(list.get(i).equals(list.get(j))){
					result += j + ",";
				}
			}
		}
		if(String4J.isNotEmpty(result)){
		    result = result.substring(0, result.length()-1);
		}
		return result;
	}
	/**
	 * 将string数组转化为int数组
	 * @param str
	 * @return
	 */
	public static int[] StringToInt(String[] str){
	    int[] iArr = new int[str.length];
	    for (int i=0;i < iArr.length;i++)
	    {
	        iArr[i] = Integer.parseInt(str[i]);
	    }
	    return iArr;
	}

	/**
	 * 对象转字符串
	 * 
	 * @param obj 需要转的对象
	 * @return 转换后字符串
	 */
	public static String objToStr(Object obj) {
		return obj == null ? "" : obj.toString();
	}
	
	/**
	 * 截取对象(可以转为字符串的对象)
	 * 
	 * @param obj 需要转的对象
	 * @param beginIndex 截取起始位置
	 * @param endIndex 截取结束位置
	 * @return 转换成字符串后截取(若长度不足,返回该字符串)
	 */
	public static String subObj(Object obj, int beginIndex, int endIndex) {
		String str = objToStr(obj);
		return (str.length() < (endIndex - beginIndex)) ? str : str.substring(beginIndex, endIndex);
	}
	
	/**
	 * 验证集合中每一个元素是否为空
	 * @param list
	 * @return 集合中所有元素都为空返回true；否则返回false
	 */
	public static boolean listIsNull(List<BigDecimal> list){
		boolean flag = true;
		for(Object data : list){
			if(String4J.isNotEmpty(data.toString())){
				 flag = false;
				 break;
			}
		}
		return flag;	
	}
	
	/**
	 * 得到数组的最大值
	 * @param num
	 * @return
	 */
	public static int max(Integer[] num){
		int max = 0;
		for(int i = 0; i<num.length;i++){
			if(num[i] >= max){
				max = num[i];
			}
		}
		return max;
		
	}
    /**
     * 判断一个元素是否包含在一个数组中
     * @param s
     * @param val
     * @return
     */
	public static boolean isHave(String[] s,String val){
		boolean flag = false;
		for(String data : s){
			if(val.equals(data)){
				flag = true;
				break;
			}
		}
		return flag;	
	}
	
	/**
	 * 判断字符串是否以英文字母开头
	 * 
	 * @param str
	 * @return
	 */
	public static boolean startOfEnglish(String str) {
		char c = str.charAt(0);
		return ('a' < c && c <'z') || ('A' < c && c < 'Z');
	}
	
	
	public static List<Map<String,Object>> listRepeat(List<Map<String,Object>> list){
		HashSet<Map<String,Object>> hs = new HashSet<Map<String,Object>>(list);		
		list.clear();
		list.addAll(hs);		
		System.out.println(list.toString());
		return list;
	}
	public static String numberFormat(double num){
		DecimalFormat df   = new DecimalFormat("#0.00"); 
		return  df.format(num);
	}
	
	public static List<Integer> sortList(List<String> list){
		int count = list.size();
		Integer[] num = new Integer[count];
		for(int i = 0;i<count;i++){
			num[i] = (Integer.parseInt(list.get(i)));
		}
		for(int a=1; a<count; ++a)  {  
			for(int b=count-1; b>=a; --b){          
				if(num[b-1] > num[b]){                
					int t = num[b-1];         
					num[b-1] = num[b];         
					num[b] = t;     
				}   
			}
		}
		list.removeAll(list);
		return Arrays.asList(num);	
	}
	
	//编码转换
	public static String stringCode(String message){
		try{
		return new String(message.getBytes("ISO-8859-1"),"GB18030");
		} catch(Exception e){
			return "null";
		}
	}
	
	//编码转换2
	public static String stringCode2(Object message){
		try {
			return message.toString();
		} catch (Exception e) {
			return "null";
		}
	}
	
	
	public static Object findNull(Object obj){
		if(obj == null){
			return "";
		}
		return obj;
	}
	
	
	public static int findPageNum(int sum,int index,int size){
		return (sum%size == 0) ? sum/size : sum/size+1;
	}
	
	
	
//	public static void main(String[] args) {
//		String s = String4J.stringTrim("  sss    ");
//		System.out.println("---"+s+"+++");
//		String n = String4J.stringTrim(null);
//		System.out.println("---"+n+"+++");
//		String w = String4J.stringTrim("    ");
//		System.out.println("---"+ w + "+++");
//	}
}