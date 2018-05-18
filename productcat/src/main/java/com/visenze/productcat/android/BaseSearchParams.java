package com.visenze.productcat.android;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Sets the basic search parameters
 */
public class BaseSearchParams {
    private Integer page;

    private Integer limit;

    private List<Integer> storeIds;

    private List<Integer> brandIds;

    private String price;

    private String priceUnit;

    private String shipCountry;

    private List<String> facets;

    private Integer facetLimit;

    private Boolean facetShowCount;

    private String thumbnailSize;

    private Boolean showProductUrl;

    private Boolean showPid;

    private Boolean vdebug;


    /**
     * The default sets limit at 10 and page at 1, other basic parameters are set as null
     */
    public BaseSearchParams() {
        this.limit = 10;
        this.page = 1;
    }

    /**
     * Set the limit of items per page
     *
     * @param limit limit of items per page.
     * @return this instance
     */
    public BaseSearchParams setLimit(int limit) {
        this.limit = limit;
        return this;
    }

    /**
     * Set the page number of the request result
     *
     * @param page page number.
     * @return this instance.
     */
    public BaseSearchParams setPage(int page) {
        this.page = page;
        return this;
    }


    /**
     * Get the limit value
     *
     * @return limit of items per page
     */
    public Integer getLimit() {
        return limit;
    }

    /**
     * Get the page numberfq
     *
     * @return page number
     */
    public Integer getPage() {
        return page;
    }

    public void setShowProductUrl(Boolean showProductUrl) {
        this.showProductUrl = showProductUrl;
    }

    public void setShipCountry(String shipCountry) {
        this.shipCountry = shipCountry;
    }

    public void setFacets(List<String> facets) {
        this.facets = facets;
    }

    public void setFacetLimit(Integer facetLimit) {
        this.facetLimit = facetLimit;
    }

    public void setFacetShowCount(Boolean facetShowCount) {
        this.facetShowCount = facetShowCount;
    }

    public void setThumbnailSize(String thumbnailSize) {
        this.thumbnailSize = thumbnailSize;
    }

    public void setStoreIds(List<Integer> storeIds) {
        this.storeIds = storeIds;
    }

    public void setBrandIds(List<Integer> brandIds) {
        this.brandIds = brandIds;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
    }

    public List<Integer> getStoreIds() {
        return storeIds;
    }

    public List<Integer> getBrandIds() {
        return brandIds;
    }

    public String getPrice() {
        return price;
    }

    public String getPriceUnit() {
        return priceUnit;
    }

    public String getShipCountry() {
        return shipCountry;
    }

    public List<String> getFacets() {
        return facets;
    }

    public Integer getFacetLimit() {
        return facetLimit;
    }

    public Boolean getFacetShowCount() {
        return facetShowCount;
    }

    public String getThumbnailSize() {
        return thumbnailSize;
    }

    public Boolean getShowProductUrl() {
        return showProductUrl;
    }

    public Boolean getShowPid() {
        return showPid;
    }

    public void setShowPid(Boolean showPid) {
        this.showPid = showPid;
    }

    public Boolean getVdebug() {
        return vdebug;
    }

    public void setVdebug(Boolean vDebug) {
        this.vdebug = vDebug;
    }

    public Map<String, List<String> > toMap() {
        Map<String, List<String> > map = new HashMap<String, List<String> >();

        if (limit != null && limit > 0) {
            putStringInMap(map, "limit", limit.toString());
        }

        if (page != null && page > 0) {
            putStringInMap(map, "page", page.toString());
        }

        if (storeIds != null && storeIds.size() > 0 ) {
            putStringInMap(map, "store_ids", TextUtils.join(",", storeIds));
        }

        if (brandIds != null && brandIds.size() > 0) {
            putStringInMap(map, "brand_ids", TextUtils.join(",", brandIds));
        }

        if (price != null){
            putStringInMap(map , "price", price.trim());
        }

        if (priceUnit != null){
            putStringInMap(map , "price_unit", priceUnit.trim());
        }

        if (shipCountry != null){
            putStringInMap(map , "ship_country", shipCountry.trim());
        }

        if (facets != null && facets.size() > 0 ) {
            putStringInMap(map, "facets", TextUtils.join(",", facets));
        }

        if (facetLimit != null ) {
            putStringInMap(map, "facets_limit" , facetLimit.toString());
        }

        if (facetShowCount != null) {
            putStringInMap(map, "facets_show_count" , String.valueOf(facetShowCount));
        }

        if (thumbnailSize!= null) {
            putStringInMap(map , "thumbnailSize" , thumbnailSize.trim());
        }

        if (showProductUrl != null ) {
            putStringInMap(map , "show_product_url" , String.valueOf(showProductUrl));
        }

        if (showPid != null ) {
            putStringInMap(map , "show_pid" , String.valueOf(showPid));
        }

        if (vdebug != null ) {
            putStringInMap(map , "vdebug" , String.valueOf(vdebug));
        }


        return map;
    }

    private void putStringInMap(Map<String, List<String> > map, String key, String value) {
        List<String> stringList = new ArrayList<>();
        stringList.add(value);

        map.put(key, stringList);
    }


}
