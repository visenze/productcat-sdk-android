package com.visenze.productcat.android.util;

public class StringUtils {

    public static boolean isEmpty(String str) {
        return !isNotEmpty(str);
    }

    public static boolean isNotEmpty(String str) {
        return str != null && str.length() > 0 ;
    }
}
