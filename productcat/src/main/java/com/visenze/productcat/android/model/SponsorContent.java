package com.visenze.productcat.android.model;

import java.util.List;

public class SponsorContent {

    private int id;
    private AdContentType contentType;
    private String title;
    private String desc;
    private String trackerUrl;
    private String link;
    private AdPlacement placement;
    private Integer placementPosition;
    private Integer orgSearchResultPosition;
    private List<SponsorContentImg> images;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AdContentType getContentType() {
        return contentType;
    }

    public void setContentType(AdContentType contentType) {
        this.contentType = contentType;
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

    public String getTrackerUrl() {
        return trackerUrl;
    }

    public void setTrackerUrl(String trackerUrl) {
        this.trackerUrl = trackerUrl;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public AdPlacement getPlacement() {
        return placement;
    }

    public void setPlacement(AdPlacement placement) {
        this.placement = placement;
    }

    public Integer getPlacementPosition() {
        return placementPosition;
    }

    public void setPlacementPosition(Integer placementPosition) {
        this.placementPosition = placementPosition;
    }

    public Integer getOrgSearchResultPosition() {
        return orgSearchResultPosition;
    }

    public void setOrgSearchResultPosition(Integer orgSearchResultPosition) {
        this.orgSearchResultPosition = orgSearchResultPosition;
    }

    public List<SponsorContentImg> getImages() {
        return images;
    }

    public void setImages(List<SponsorContentImg> images) {
        this.images = images;
    }
}
