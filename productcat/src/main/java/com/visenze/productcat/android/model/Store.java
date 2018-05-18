package com.visenze.productcat.android.model;

/**
 * Created by visenze on 18/8/17.
 */

public class Store {
    private int storeId ;
    private String name ;
    private int availability ;

    public Store() {}

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }
}
