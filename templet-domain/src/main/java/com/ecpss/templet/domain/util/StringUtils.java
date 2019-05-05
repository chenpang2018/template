package com.ecpss.templet.domain.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class StringUtils {

    public static boolean isBlank(String string) {
        return string == null || string.trim().equals("");
    }

    public static boolean isNotBlank(String string) {
        return !isBlank(string);
    }

    /**
     * 替换指定位置的字符
     *
     * @param index
     * @param res
     * @param str
     * @return
     */
    public static String replaceIndex(int index, String res, String str) {
        return res.substring(0, index) + str + res.substring(index + 1);
    }

    @SuppressWarnings("unchecked")
	public static List<Integer> string2integerList(String value) {
        String[] split = value.split(",");
        @SuppressWarnings("rawtypes")
		List<Integer> list = new ArrayList();
        for (String s : split) {
            list.add(Integer.valueOf(s));
        }
        return list;
    }

    public static List<String> string2stringList(String value) {
        String[] split = value.split(",");
        return Arrays.asList(split);
    }

    @SuppressWarnings("rawtypes")
	public static String list2string(List<?> valueList) {
        StringBuffer buf = new StringBuffer();
        for (Iterator ite = valueList.iterator(); ite.hasNext(); ) {
            buf.append(ite.next());
            if (ite.hasNext()) buf.append(",");
        }
        return buf.toString();
    }

    public static boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
