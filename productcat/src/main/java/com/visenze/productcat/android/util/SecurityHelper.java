package com.visenze.productcat.android.util;

import java.io.UnsupportedEncodingException;

public class SecurityHelper {

    public static String MD5(String input) {
        if(input == null) {
            return null;
        }
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(input.getBytes("UTF-8"));
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch(UnsupportedEncodingException ex){
            ex.printStackTrace();
        }
        return null;
    }
}
