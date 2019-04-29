package com.visenze.productcat.android.model;

public enum AdContentType {
    ADS("ads", 1),
    BANNER("banner", 2),
    FEATURED_LISTING("featured_listing", 3);

    private String name;
    private int id;

    AdContentType(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public boolean isBanner() {
        return BANNER.equals(this);
    }

    public boolean isAds() {
        return ADS.equals(this);
    }

    public int getId() {
        return id;
    }
}
