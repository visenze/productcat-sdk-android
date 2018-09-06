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
    public static final String LIMIT = "limit";
    public static final String PAGE = "page";
    public static final String STORE_IDS = "store_ids";
    public static final String COMMA = ",";
    public static final String BRAND_IDS = "brand_ids";
    public static final String PRICE_UNIT = "price_unit";
    public static final String PRICE = "price";
    public static final String FACETS = "facets";
    public static final String FACETS_LIMIT = "facets_limit";
    public static final String FACETS_SHOW_COUNT = "facets_show_count";
    public static final String THUMBNAIL_SIZE = "thumbnail_size";
    public static final String SHOW_PRODUCT_URL = "show_product_url";
    public static final String COUNTRY = "country";
    public static final String SOURCE = "source";
    public static final String SORT_BY = "sort_by";
    public static final String CLIENT_REQID = "client_reqid";
    public static final String MAX_RESULTS_PER_STORE = "max_results_per_store";
    public static final String CLIENT_PARAM1 = "cp1";
    public static final String CLIENT_PARAM2 = "cp2";

    private Integer page;

    private Integer limit;

    private List<Integer> storeIds;

    private List<Integer> brandIds;

    private String country;

    private String price;

    private String priceUnit;

    private List<String> facets;

    private Integer facetLimit;

    private Boolean facetShowCount;

    private String thumbnailSize;

    private Boolean showProductUrl;

    private String source;

    private String sortBy;

    private String clientReqId;

    private Integer maxResultsPerStore;

    // custom parameters
    private String cp1;
    private String cp2;

    private Map<String, String> custom;

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getClientReqId() {
        return clientReqId;
    }

    public void setClientReqId(String clientReqId) {
        this.clientReqId = clientReqId;
    }

    public Integer getMaxResultsPerStore() {
        return maxResultsPerStore;
    }

    public void setMaxResultsPerStore(Integer maxResultsPerStore) {
        this.maxResultsPerStore = maxResultsPerStore;
    }

    public String getCp1() {
        return cp1;
    }

    public void setCp1(String cp1) {
        this.cp1 = cp1;
    }

    public String getCp2() {
        return cp2;
    }

    public void setCp2(String cp2) {
        this.cp2 = cp2;
    }

    public Map<String, String> getCustom() {
        return custom;
    }

    public void setCustom(Map<String, String> custom) {
        this.custom = custom;
    }

    public Map<String, List<String> > toMap() {
        Map<String, List<String> > map = new HashMap<String, List<String> >();

        if (limit != null && limit > 0) {
            putStringInMap(map, LIMIT, limit.toString());
        }

        if (page != null && page > 0) {
            putStringInMap(map, PAGE, page.toString());
        }

        if (storeIds != null && storeIds.size() > 0 ) {
            putStringInMap(map, STORE_IDS, TextUtils.join(COMMA, storeIds));
        }

        if (brandIds != null && brandIds.size() > 0) {
            putStringInMap(map, BRAND_IDS, TextUtils.join(COMMA, brandIds));
        }

        if (price != null){
            putStringInMap(map , PRICE, price.trim());
        }

        if (priceUnit != null){
            putStringInMap(map , PRICE_UNIT, priceUnit.trim());
        }

        if (facets != null && facets.size() > 0 ) {
            putStringInMap(map, FACETS, TextUtils.join(COMMA, facets));
        }

        if (facetLimit != null ) {
            putStringInMap(map, FACETS_LIMIT, facetLimit.toString());
        }

        if (facetShowCount != null) {
            putStringInMap(map, FACETS_SHOW_COUNT, String.valueOf(facetShowCount));
        }

        if (thumbnailSize!= null) {
            putStringInMap(map , THUMBNAIL_SIZE, thumbnailSize.trim());
        }

        if (showProductUrl != null ) {
            putStringInMap(map , SHOW_PRODUCT_URL, String.valueOf(showProductUrl));
        }

        if (country != null){
            putStringInMap(map , COUNTRY, country.trim());
        }

        if (source != null ) {
            putStringInMap(map, SOURCE, source);
        }

        if (sortBy!=null) {
            putStringInMap(map, SORT_BY, sortBy);
        }

        if (clientReqId!=null) {
            putStringInMap(map, CLIENT_REQID, clientReqId);
        }

        if (maxResultsPerStore!=null) {
            putStringInMap(map, MAX_RESULTS_PER_STORE, String.valueOf(maxResultsPerStore) );
        }

        if (cp1 != null){
            putStringInMap(map , CLIENT_PARAM1, cp1.trim());
        }

        if (cp2 != null){
            putStringInMap(map , CLIENT_PARAM2, cp2.trim());
        }

        if (custom != null && custom.size() > 0) {
            for (Map.Entry<String, String> entry : getCustom().entrySet()) {
                String value = entry.getValue();
                if (value != null) {
                    putStringInMap(map, entry.getKey(), value);
                }
            }
        }

        return map;
    }

    private void putStringInMap(Map<String, List<String> > map, String key, String value) {
        List<String> stringList = new ArrayList<>();
        stringList.add(value);

        map.put(key, stringList);
    }
}
