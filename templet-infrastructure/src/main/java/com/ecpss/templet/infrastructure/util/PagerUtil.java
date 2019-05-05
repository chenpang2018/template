package com.ecpss.templet.infrastructure.util;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/10/27.
 */
public class PagerUtil {
    public static String getTotalNumHQL(String hql, Object... args) {
        String countQueryString = removeSelect(removeOrders(hql));
        // String countQueryString = removeOrders(hql);
        // return commonDao.executeStat(countQueryString,args).intValue();
        String count = "select count(*) " + countQueryString;
        return count;
    }
    /**
     * 去除hql的orderby 子句，用于pagedQuery.
     *
     * @see
     */
    public static String removeOrders(String hql) {
        Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(hql);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, "");
        }
        m.appendTail(sb);
        return sb.toString();
    }
    /**
     * 去除hql的select 子句，未考虑union的情况,用于pagedQuery.
     *
     * @see
     */
    private static String removeSelect(String hql) {
        int beginPos = hql.toLowerCase().indexOf("from");
        return hql.substring(beginPos);
    }

    public static String getTotalNumHQL(String hql) {
        String countQueryString = removeSelect(removeOrders(hql));
        String countHql = "select count(*) " + countQueryString;
        return countHql;
    }



}
