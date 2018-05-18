package com.visenze.productcat.android;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by visenze on 22/8/17.
 */

public class TextSearchParams extends BaseSearchParams {

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
            putStringInMap(map, "q", q);
        }
        return map;
    }

    private void putStringInMap(Map<String, List<String> > map, String key, String value) {
        List<String> stringList = new ArrayList<>();
        stringList.add(value);

        map.put(key, stringList);
    }
}
