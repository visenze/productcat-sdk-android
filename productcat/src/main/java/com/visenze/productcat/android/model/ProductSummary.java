package com.visenze.productcat.android.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by visenze on 14/9/15.
 */
public class ProductSummary {
    private String pgid;
    private String pid;
    private String mainImage;
    private List<String> images = new ArrayList<>();
    private String title;
    private String desc;
    private String country;
    private Double minPrice;
    private Double maxPrice;
    private String priceUnit;
    private List<Store> stores = new ArrayList<>();
    private int availability;
    private String productUrl;

    private String priceString;
    private String sellerName;
    private Double visualScore;

    private Double orgMinPrice;
    private Double orgMaxPrice;
    private String orgPriceUnit;

    private boolean boosted = false;

    private String brand;
    private String brandId;

    public ProductSummary() {

    }

    public String getPgid() {
        return pgid;
    }

    public void setPgid(String pgid) {
        this.pgid = pgid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
    }

    public List<Store> getStores() {
        return stores;
    }

    public void setStores(List<Store> stores) {
        this.stores = stores;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getPriceString() {
        return priceString;
    }

    public void setPriceString(String priceString) {
        this.priceString = priceString;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public Double getVisualScore() {
        return visualScore;
    }

    public void setVisualScore(Double visualScore) {
        this.visualScore = visualScore;
    }

    public Double getOrgMinPrice() {
        return orgMinPrice;
    }

    public void setOrgMinPrice(Double orgMinPrice) {
        this.orgMinPrice = orgMinPrice;
    }

    public Double getOrgMaxPrice() {
        return orgMaxPrice;
    }

    public void setOrgMaxPrice(Double orgMaxPrice) {
        this.orgMaxPrice = orgMaxPrice;
    }

    public String getOrgPriceUnit() {
        return orgPriceUnit;
    }

    public void setOrgPriceUnit(String orgPriceUnit) {
        this.orgPriceUnit = orgPriceUnit;
    }

    public boolean isBoosted() {
        return boosted;
    }

    public void setBoosted(boolean boosted) {
        this.boosted = boosted;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }
}
