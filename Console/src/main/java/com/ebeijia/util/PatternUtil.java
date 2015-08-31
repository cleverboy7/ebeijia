package com.ebeijia.util;

import java.util.regex.Pattern;

/**
 * PatternUtil
 *
 * @author zhicheng.xu
 * @date 15/6/24
 */
public class PatternUtil {
    //数字
    public static final Pattern NUMBER_REGEX = Pattern.compile("[0-9]*");
    //中文字符扩充
    public static final Pattern CHINESE_REGEX = Pattern.compile("^[\u4e00-\u9fa5]");
    //金额格式
    public static final Pattern AMOUNT_REGEX = Pattern.compile("^(([1-9]\\d*)|0)(\\.\\d{1,2})?$");
    //日期格式YYYYMMDD
    public static final Pattern DATE_REGEX = Pattern.compile("(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})(((0[13578]|1[02])(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)(0[1-9]|[12][0-9]|30))|(02(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))0229)(([0-1][0-9])|2[0-3])[0-5][0-9]");
    //时分秒
    public static final Pattern DTS_REGEX = Pattern.compile("^(([0-1]\\d)|(2[0-3]))[0-5]\\d[0-5]\\d$");
    //邮箱地址
    public static final Pattern EMAIL_REGEX = Pattern.compile("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
    //手机号码
    public static final Pattern MOBILE_REGEX = Pattern.compile("^((13[0-9])|(15[0-9])|(18[0-9])|(14[0-9])|(17[0-9])|999)\\d{8}$");
    //6-20 的数据或字母
    public static final Pattern NUMBER6_REGEX =Pattern.compile("[0-9a-zA-Z]{6,20}");
    //验证日期YYYY
    public static final Pattern DATEY_REGEX = Pattern.compile("\\d{3}[1-9]|\\d{2}[1-9]\\d{1}|\\d{1}[1-9]\\d{2}|[1-9]\\d{3}?");
}
