package com.visenze.productcat.android.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.visenze.productcat.R;

import java.util.UUID;

/**
 * Created by visenze on 26/2/16.
 */
public class ProductCatUIDManager {
    public final static String PREF_KEY = "visearchudid";
    public static final String SET_COOKIE = "Set-Cookie";
    public static final String UID = "uid";
    public static final String SEMI_COLON = ";";
    public static final String EQUAL = "=";

    private static SharedPreferences preference;

    public static void initPref(final Context context) {

        String pref_file = context.getString(R.string.productcat_sdk_preference_file);
        preference = context.getSharedPreferences(pref_file, Context.MODE_PRIVATE);
        // PS-303. add uid by default.
        String uniqueID = UUID.randomUUID().toString();
        uniqueID = uniqueID.replace("-", "");
        setUid(uniqueID);
    }

    public static boolean hasUid() {
        String storeUid = getUid();
        return StringUtils.isNotEmpty(storeUid);
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
