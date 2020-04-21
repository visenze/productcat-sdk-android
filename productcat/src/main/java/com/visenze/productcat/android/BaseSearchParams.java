package com.visenze.productcat.android;

import android.text.TextUtils;

import com.visenze.productcat.android.model.DeviceInfo;
import com.visenze.productcat.android.util.StringUtils;

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
    public static final String PRICE = "price";
    public static final String FACETS = "facets";
    public static final String FACETS_LIMIT = "facets_limit";
    public static final String THUMBNAIL_SIZE = "thumbnail_size";
    public static final String COUNTRY = "country";
    public static final String SOURCE = "source";
    public static final String SORT_BY = "sort_by";
    public static final String CLIENT_REQID = "client_reqid";
    public static final String MAX_RESULTS_PER_STORE = "max_results_per_store";
    public static final String CLIENT_PARAM1 = "cp1";
    public static final String CLIENT_PARAM2 = "cp2";
    public static final String UID = "uid";

    // device info
    public static final String UIP = "uip";
    public static final String DEVICE_MODEL = "device_model";
    public static final String OS = "os";
    public static final String OSV = "osv";
    public static final String DIDMD5 = "didmd5";
    public static final String IFA = "ifa";
    public static final String GEO = "geo";
    public static final String UA = "ua";
    public static final String UC = "uc";
    public static final String DO_NOT_TRACK = "do_not_track";

    private Integer page;

    private Integer limit;

    private List<Integer> storeIds;

    private List<Integer> brandIds;

    private String country;

    private String price;

    private List<String> facets;

    private Integer facetLimit;

    private String thumbnailSize;

    private String source;

    private String sortBy;

    private String clientReqId;

    private Integer maxResultsPerStore;

    // custom parameters
    private String cp1;
    private String cp2;

    // allow uid override from client
    private String uid;

    private Map<String, String> custom;

    private DeviceInfo deviceInfo;

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

    public void setFacets(List<String> facets) {
        this.facets = facets;
    }

    public void setFacetLimit(Integer facetLimit) {
        this.facetLimit = facetLimit;
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

    public List<Integer> getStoreIds() {
        return storeIds;
    }

    public List<Integer> getBrandIds() {
        return brandIds;
    }

    public String getPrice() {
        return price;
    }

    public List<String> getFacets() {
        return facets;
    }

    public Integer getFacetLimit() {
        return facetLimit;
    }

    public String getThumbnailSize() {
        return thumbnailSize;
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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public DeviceInfo getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(DeviceInfo deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public Map<String, List<String> > toMap() {
        Map<String, List<String> > map = new HashMap<String, List<String> >();

        if (StringUtils.isNotEmpty(uid) ) {
            putStringInMap(map, UID, uid);
        }

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

        if (facets != null && facets.size() > 0 ) {
            putStringInMap(map, FACETS, TextUtils.join(COMMA, facets));
        }

        if (facetLimit != null ) {
            putStringInMap(map, FACETS_LIMIT, facetLimit.toString());
        }

        if (thumbnailSize!= null) {
            putStringInMap(map , THUMBNAIL_SIZE, thumbnailSize.trim());
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

        if (deviceInfo != null) {
            addDeviceInfoParams(map, deviceInfo);
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

    private void addDeviceInfoParams(Map<String, List<String>> map,
                                     DeviceInfo deviceInfo) {

        if (deviceInfo == null) {
            return;
        }

        if(deviceInfo.getUip() != null){
            this.putStringInMap(map, UIP, deviceInfo.getUip());
        }

        if(deviceInfo.getUa() != null){
            this.putStringInMap(map, UA, deviceInfo.getUa());
        }

        if(deviceInfo.getDeviceModel()!=null){
            this.putStringInMap(map, DEVICE_MODEL, deviceInfo.getDeviceModel());
        }

        if(deviceInfo.getOs()!=null){
            this.putStringInMap(map, OS, deviceInfo.getOs());
        }

        if(deviceInfo.getOsv()!=null){
            this.putStringInMap(map, OSV, deviceInfo.getOsv());
        }

        if(deviceInfo.getDidmd5()!=null){
            this.putStringInMap(map, DIDMD5, deviceInfo.getDidmd5());
        }

        if(deviceInfo.getIfa()!=null){
            this.putStringInMap(map, IFA, deviceInfo.getIfa());
        }

        if(deviceInfo.getLatLng()!=null){
            this.putStringInMap(map, GEO, deviceInfo.getLatLng());
        }

        if(deviceInfo.getUc() != null) {
            this.putStringInMap(map, UC, deviceInfo.getUc());
        }

        if(deviceInfo.getDoNotTrack() != null) {
            this.putStringInMap(map, DO_NOT_TRACK, deviceInfo.getDoNotTrack());
        }

    }

    protected void putStringInMap(Map<String, List<String> > map, String key, String value) {
        List<String> stringList = new ArrayList<>();
        stringList.add(value);

        map.put(key, stringList);
    }
}
