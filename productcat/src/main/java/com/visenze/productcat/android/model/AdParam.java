package com.visenze.productcat.android.model;

public class AdParam {
    // sponsored banner image generally at the top
    private AdImgParam bannerImg;

    // image for featured product or ads within product feed in search results
    private AdImgParam img;

    private Integer titleMaxLen;

    private Integer descMaxLen;

    private DeviceInfo deviceInfo;

    public AdParam() {
    }

    public AdImgParam getBannerImg() {
        return bannerImg;
    }

    public void setBannerImg(AdImgParam bannerImg) {
        this.bannerImg = bannerImg;
    }

    public AdImgParam getImg() {
        return img;
    }

    public void setImg(AdImgParam img) {
        this.img = img;
    }

    public Integer getTitleMaxLen() {
        return titleMaxLen;
    }

    public void setTitleMaxLen(Integer titleMaxLen) {
        this.titleMaxLen = titleMaxLen;
    }

    public Integer getDescMaxLen() {
        return descMaxLen;
    }

    public void setDescMaxLen(Integer descMaxLen) {
        this.descMaxLen = descMaxLen;
    }

    public DeviceInfo getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(DeviceInfo deviceInfo) {
        this.deviceInfo = deviceInfo;
    }
}
