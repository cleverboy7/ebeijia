package com.ebeijia.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

/**
 * 处理生成文件需要处理的字符串
 *
 * @author bison
 */
public class StringUtil {

    public static String getTemplateContent(File file) {
        try {
            return FileUtils.readFileToString(file);
        } catch (IOException e) {

            throw new IllegalArgumentException("无效的模板文件异常!");
        }
    }

    public static boolean isNull(Object object) {
        if (object instanceof String) {
            return StringUtil.isEmpty(object.toString());
        }
        return object == null;
    }

    /**
     * 获取本地时间
     *
     * @param style yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getcurrdate(String style) {
        Date currDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(style); // "yyyy-MM-dd
        // HH:mm:ss"
        String strdate = sdf.format(currDate);
        return strdate;
    }

    /**
     * @param tableName
     * @return 根据表名得到类名 TBL_CSM_CRDBSC->TblCsmCrdbsc
     */
    public static String getClassName(String tableName) {
        return StringUtils.remove(WordUtils.capitalizeFully(tableName, new char[]{'_'}), "_");
    }


    /**
     * @param tableName
     * @return 表名生成的三级目录
     */
    public static String getDirLev3(String tableName) {
        return StringUtils.substringAfterLast(tableName, "_").toLowerCase();
    }

    /**
     * 判断信息是否为空
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        if (str != null && !"".equals(str.trim()))
            return true;
        else
            return false;
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

    public static boolean isEmpty(final String value) {
        return value == null || value.trim().length() == 0
                || "null".endsWith(value);
    }

    /**
     * 拆分 字符串
     *
     * @param str
     * @return
     */
    public static String[] ceilString(String str) {
        double len = str.length() / 15.0;
        int y = (int) Math.ceil(len);
        String[] ss = new String[y];
        for (int i = 0; i < y; i++) {
            int start = 15 * i;
            int end = 15 * (i + 1);
            if (i == y - 1)
                ss[i] = str.substring(start, str.length());
            else
                ss[i] = str.substring(start, end);
        }
        return ss;
    }

    /**
     * 判断 JSONArray 中json中字段值 是否有空
     *
     * @param jsonArray
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Boolean findNullList(JSONArray jsonArray) {
        Boolean flag = false;
        Iterator<JSONObject> iter = jsonArray.iterator();
        while (iter.hasNext()) {
            JSONObject jsonObject_a = (JSONObject) iter.next();
            String value = jsonObject_a.get("fldValue").toString();
            if (StringUtil.isEmpty(value) || ",".equals(value.trim())) {
                flag = true;
                break;
            }
        }
        return flag;
    }
}
