package com.visenze.productcat.android;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by visenze on 22/8/17.
 */

public class TextSearchParams extends BaseSearchParams {

    public static final String QUERY_PARAM = "q";

    private String q;

    public TextSearchParams(String q) {
        this.q = q;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    @Override
    public Map<String, List<String> > toMap() {
        Map<String, List<String> > map = super.toMap();

        if (q != null) {
            putStringInMap(map, QUERY_PARAM, q);
        }
        return map;
    }

}
