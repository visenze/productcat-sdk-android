package com.visenze.productcat.android.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.NetworkResponse;

import java.util.Map;
import java.util.UUID;

/**
 * Created by visenze on 26/2/16.
 */
public class ProductCatUIDManager {
    public final static String PREF_KEY = "visearchudid";
    public final static String PREFS_NAME = "visearchuid_prefs";
    public static final String SET_COOKIE = "Set-Cookie";
    public static final String UID = "uid";
    public static final String SEMI_COLON = ";";
    public static final String EQUAL = "=";

    private static SharedPreferences preference;

    public static void initPref(final Context context) {
        preference =  context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    // store uid from server cookie
    public static void storeUidIfNeeded(NetworkResponse response) {
        if (ProductCatUIDManager.hasUid()) {
            return;
        }

        Map headers = response.headers;
        if (headers.containsKey(SET_COOKIE)) {
            String value = (String)headers.get(SET_COOKIE);
            String[] cv = value.split(SEMI_COLON);
            String[] uid = new String[0];
            for (String v : cv) {
                if (v.startsWith(UID)) {
                    uid = v.split(EQUAL);
                    break;
                }
            }
            if (uid.length > 0) {
                ProductCatUIDManager.setUid(uid[1]);
            }
        }
    }

    public static boolean hasUid() {
        String storeUid = getUid();
        return storeUid!=null && storeUid.length() > 0;
    }

    public static String getUid() {
        return preference.getString(PREF_KEY, null);
    }

    public static void setUid(String uid) {
        if (uid == null || uid.trim().length() == 0) {
            return;
        }

        final SharedPreferences.Editor e = preference.edit();
        e.putString(PREF_KEY, uid);
        e.apply();
    }
}
