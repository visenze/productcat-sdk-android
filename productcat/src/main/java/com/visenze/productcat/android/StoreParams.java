package com.visenze.productcat.android;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.visenze.productcat.android.BaseSearchParams.COUNTRY;
import static com.visenze.productcat.android.BaseSearchParams.LIMIT;
import static com.visenze.productcat.android.BaseSearchParams.PAGE;

public class StoreParams {

    public static final String PRICE_UNIT = "price_unit";

    private String country;
    private String priceUnit;
    private Integer page;
    private Integer limit;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Map<String, List<String> > toMap() {
        Map<String, List<String> > map = new HashMap<String, List<String> >();

        if (limit != null && limit > 0) {
            putStringInMap(map, LIMIT, limit.toString());
        }

        if (page != null && page > 0) {
            putStringInMap(map, PAGE, page.toString());
        }

        if (priceUnit != null){
            putStringInMap(map , PRICE_UNIT, priceUnit.trim());
        }

        if (country != null){
            putStringInMap(map , COUNTRY, country.trim());
        }

        return map;
    }

    private void putStringInMap(Map<String, List<String> > map, String key, String value) {
        List<String> stringList = new ArrayList<>();
        stringList.add(value);

        map.put(key, stringList);
    }
}
