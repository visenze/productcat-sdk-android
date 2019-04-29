package com.visenze.productcat.android.model;

public enum AdPlacement {
    WITHIN("within"),
    TOP("top"),
    BOTTOM("bottom");

    private String name;

    AdPlacement(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
