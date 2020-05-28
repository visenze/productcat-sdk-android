package com.visenze.productcat.android;

import java.util.List;
import java.util.Map;

public class UpdatePrivacyParams extends BaseSearchParams {

    private boolean optIn;
    private String email;

    public static String OPT_IN = "opt_in";
    public static String EMAIL = "email";
    public static String VERSION = "version";

    public UpdatePrivacyParams(boolean opt_in, String email) {

        this.optIn = opt_in;
        this.email = email;
    }

    @Override
    public Map<String, List<String>> toMap() {
        Map<String, List<String> > map = super.toMap();

        putStringInMap(map, OPT_IN, Boolean.toString(optIn));

        if(email != null) {
            putStringInMap(map, EMAIL, email);
        }

        putStringInMap(map, VERSION, "1.0.0");

        return map;
    }
}
