package com.visenze.productcat.android.model;

/**
 * Created by visenze on 27/6/18.
 */

public class FacetRange {
    private Number min;
    private Number max;

    public FacetRange() {
    }

    public Number getMin() {
        return min;
    }

    public void setMin(Number min) {
        this.min = min;
    }

    public Number getMax() {
        return max;
    }

    public void setMax(Number max) {
        this.max = max;
    }

    @Override
    public String toString() {
        return "FacetRange{" +
                "min=" + min +
                ", max=" + max +
                '}';
    }
}