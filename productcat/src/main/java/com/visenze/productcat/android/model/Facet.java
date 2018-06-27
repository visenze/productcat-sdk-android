package com.visenze.productcat.android.model;

import java.util.List;

/**
 * Created by visenze on 27/6/18.
 */

public class Facet {
    private String key;
    private List<FacetItem> items;
    private FacetRange range;

    public Facet() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<FacetItem> getItems() {
        return items;
    }

    public void setItems(List<FacetItem> items) {
        this.items = items;
    }

    public FacetRange getRange() {
        return range;
    }

    public void setRange(FacetRange range) {
        this.range = range;
    }

    @Override
    public String toString() {
        return "Facet{" +
                "key='" + key + '\'' +
                ", items=" + items +
                ", range=" + range +
                '}';
    }
}