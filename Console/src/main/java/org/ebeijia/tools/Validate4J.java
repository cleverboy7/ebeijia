package org.ebeijia.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ebeijia.util.PatternUtil;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;


/**
 * Datebase Field is Validate for JAVA（数据库字段的合法性验证 增删改的操作时，验证字段的格式）
 *
 * @version 0.1
 *
 */
public class Validate4J {

    /**
     * 验证邮箱地址是否正确
     *
     * @param email
     * @return boolean
     */
    public static boolean checkEmail(String email) {
        try {
            Matcher matcher = PatternUtil.EMAIL_REGEX.matcher(email);
            return matcher.matches();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 验证手机号码
     *
     * @param mobiles
     * @return
     */
    public static boolean checkMobileNO(String mobiles) {
        boolean flag = false;
        try {
            Matcher m = PatternUtil.MOBILE_REGEX.matcher(mobiles);
            flag = m.matches();
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }


    /**
     * 验证密码 长度为 6-20 的数据或字母
     *
     * @param password
     * @return
     */
    public static boolean checkPassword(String password) {
        boolean flag = false;
        try {
            Matcher m = PatternUtil.NUMBER6_REGEX.matcher(password);
            flag = m.matches();
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    /**
     * 检测jsonString格式是否正确
     *
     * @param jsonString
     * @return
     */
    public static boolean isGoodJson(String jsonString) {
        if (StringUtils.isBlank(jsonString)) {
            return false;
        }
        try {
            JSONObject.fromObject(jsonString);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public static String checkSqlInject(String jsonString) {
        if (!isGoodJson(jsonString)) {
            return "0";
        }
        jsonString = jsonString.replaceAll(" ", "");
        String tmp = jsonString;
        String inj_str = "'";
        String inj_stra[] = inj_str.split(":");
        jsonString = jsonString.replace("}", "").replace("{", "").replaceAll("\"", "");
        String[] s = jsonString.split("[:,]");
        for (int i = 1; i < s.length; i++) {
            for (int j = 0; j < inj_stra.length; j++) {
                if (s[i].trim().equalsIgnoreCase(inj_stra[j])) {
                    return "0";
                }
            }
        }
        return tmp;
    }

    /**
     * 判断字符串长度(两者之间)
     *
     * @param str  字符串
     * @param len1 长度1
     * @param len2 长度2
     * @return str长度>=len1且str长度<=len2,返回true;反之false
     */
    public static boolean checkStringLength(String str, int len1, int len2) {
        char[] c = str.toCharArray();
        int len = 0;
        for (int i = 0; i < c.length; i++) {
            len++;
            if (!isLetter(c[i])) {
                len++;
            }
        }
        if (len2 == 0) {
            return (len >= len1);
        }
        return (len >= len1) && (len <= len2);
    }

    /**
     * 判断字符串数组长度
     * 注:1.两者之间(字符串,参数1,参数2)
     * 2.小于等于某个值(字符串,0,参数2)
     * 3.等于某个值(字符串,参数1,参数1)
     *
     * @param s 字符串数组(二维)
     * @return 都满足条件, 返回true;反之false
     */
    public static boolean checkStrArrLen(String[][] s) {
        for (int i = 0; i < s.length; i++) {
            String str = s[i][0];
            if ("".equals(str) || str == null) {
                if (s[i][1] == null || Integer.parseInt(s[i][1]) == 0) {
                    continue;
                } else {
                    return false;
                }
            } else {
                int len1 = Integer.parseInt(s[i][1]);
                int len2 = 0;
                if ("".equals(s[i][2]) || s[i][2] == null) {
                } else {
                    len2 = Integer.parseInt(s[i][2]);
                }
                if (!checkStringLength(str, len1, len2)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 判断字符串长度(小于等于某值)
     *
     * @param str    字符串
     * @param length 长度
     * @return str长度<=length,返回true;反之false
     */
    public static boolean checkStringLength(String str, int length) {
        return checkStringLength(str, 0, length);
    }

    /**
     * 判断字符串长度(等于某值)
     *
     * @param str    字符串
     * @param length 长度
     * @return str长度=length,返回true;反之false
     */
    public static boolean checkStrLenEquals(String str, int length) {
        return checkStringLength(str, length, length);
    }

    /**
     * 判断字符是否是非汉字字符
     *
     * @param c 字符
     * @return c不是汉字字符, 返回true;反之false
     */
    private static boolean isLetter(char c) {
        int k = 0x80;
        return c / k == 0 ? true : false;
    }

    /**
     * 判断Number的长度(注:小数点)
     *
     * @param str           String类型的Number
     * @param dataPrecision 指定数字数据的精度,精度是指数字的位数
     * @param dataScale     指定数字数据的小数位数,小数位数是指小数点后的位数
     * @return 符合条件返回true, 反之false
     */
    public static boolean checkNumberLength(String str, int dataPrecision, int dataScale) {
        if (str.contains(".")) {
            String str1 = str.replace(".", "&");
            String[] s = str1.split("&");
            int len = dataPrecision - dataScale;
            return s[0].length() <= len; //无论是否有小数点,s[0]都表示整数位
        }
        int len = dataPrecision - dataScale;
        return str.length() <= len; //无论是否有小数点,s[0]都表示整数位
    }

    /**
     * 判断参数的长度和类型(大于0的int)
     *
     * @param str 参数
     * @return 参数是大于0的int, 返回true;反之false
     */
    public static boolean checkMoreThan(String str) {
        try {
            int i = Integer.parseInt(str);
            return i > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean checkNum(int i) {
        return i > 0;
    }

    /**
     * 判断两个参数的长度和类型(大于0的int)
     *
     * @param str1 参数1
     * @param str2 参数2
     * @return 两个参数都是大于0的int, 返回true;反之false
     */
    public static boolean checkMoreThan(String str1, String str2) {
        return checkMoreThan(str1) && checkMoreThan(str2);
    }

    /**
     * 判断参数是否是int类型的数字
     *
     * @param str
     * @return 是int类型的数字, 返回true;反之false
     */
    public static boolean checkInt(String str) {
        boolean flag = false;
        try {
            int i = Integer.parseInt(str);
            flag = true;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    /**
     * 判断字符串中是否包含非英文数字字符
     *
     * @param str 参数
     * @return 字符串中都是英文, 返回true;反之false
     */
    public static boolean checkEnglishDigital(String str) {
        return str.matches("\\w+");
    }

    /**
     * 验证邮编是否正确
     *
     * @param post
     * @return
     */
    public static boolean checkPost(String post) {
        boolean flag = false;
        try {
            Matcher matcher = PatternUtil.EMAIL_REGEX.matcher(post);
            flag = matcher.matches();
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    /**
     * 验证参数是否是double类型的数字
     *
     * @param str
     * @return
     */
    public static boolean checkDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 判断第三方商户号格式
     *
     * @param merchantId
     * @return 26个英文大小写字母和数字组成的小于等于15位的字符串, 返回true;否则false
     */
    public static boolean checkMerchantId(String merchantId) {
        return merchantId.matches("^[A-Za-z0-9]+$") && checkStringLength(merchantId, 15);
    }

    /**
     * 检查日期 YYYYMMDD
     *
     * @param str
     * @return
     */
    public static boolean checkDate(String str) {
        boolean flag = false;
        try {
            Matcher matcher = PatternUtil.DATE_REGEX.matcher(str);
            flag = matcher.matches();
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    /**
     * 检查时间 ddmmss
     *
     * @param str
     * @return
     */
    public static boolean checkTime(String str) {
        boolean flag = false;
        try {
            Matcher matcher = PatternUtil.DTS_REGEX.matcher(str);
            flag = matcher.matches();
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    /**
     * 验证时间  YYYY
     *
     * @param year
     * @return
     */
    public static boolean checkYear(String year) {
        boolean flag = false;
        try {
            Matcher matcher = PatternUtil.DATEY_REGEX.matcher(year);
            flag = matcher.matches();
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    /**
     * 验证24小时  时分秒是否正确
     *
     * @param time
     * @return
     */
    public static boolean check24Time(String time) {
        boolean flag = false;
        try {
            Matcher matcher = PatternUtil.DTS_REGEX.matcher(time);
            flag = matcher.matches();
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }


}
