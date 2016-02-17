package com.ohayoyo.gateway.define.utils;

public class StringUtils {

    public static boolean isEmpty(String str) {
        return !isNotEmpty(str);
    }

    public static boolean isNotEmpty(String str) {
        return null != str && (!"".equals(str.trim()));
    }

}
