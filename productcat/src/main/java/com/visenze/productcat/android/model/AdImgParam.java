package com.visenze.productcat.android.model;

public class AdImgParam {
    private Integer width;
    private Integer minWidth;
    private Integer height;
    private Integer minHeight;
    private String supportedMimes;

    public AdImgParam() {
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getMinWidth() {
        return minWidth;
    }

    public void setMinWidth(Integer minWidth) {
        this.minWidth = minWidth;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getMinHeight() {
        return minHeight;
    }

    public void setMinHeight(Integer minHeight) {
        this.minHeight = minHeight;
    }

    public String getSupportedMimes() {
        return supportedMimes;
    }

    public void setSupportedMimes(String supportedMimes) {
        this.supportedMimes = supportedMimes;
    }
}
