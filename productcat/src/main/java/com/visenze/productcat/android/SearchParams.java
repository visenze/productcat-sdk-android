package com.visenze.productcat.android;

import com.visenze.productcat.android.model.DeviceInfo;

import java.util.List;
import java.util.Map;

/**
 * Abstract class for image search related settings.
 * This class is extended by
 * {@link UploadSearchParams UploadSearchParams}.
 */
public abstract class SearchParams {
    public static final String SEARCH_MIN_SCORE = "search_min_score";

    private Float searchMinScore = null;

    protected BaseSearchParams mBaseSearchParams;

    protected SearchParams() {
        mBaseSearchParams = new BaseSearchParams();
    }

    protected SearchParams(BaseSearchParams baseSearchParams) {
        this.mBaseSearchParams = baseSearchParams;
    }

    /**
     * Set the basic search parameters. If not set, default settings are used
     *
     * @param baseSearchParams basic search parameter settings
     */
    public void setBaseSearchParams(BaseSearchParams baseSearchParams) {
        this.mBaseSearchParams = baseSearchParams;
    }

    public DeviceInfo getDeviceInfo() {
        return this.mBaseSearchParams.getDeviceInfo();
    }

    public void setDeviceInfo(DeviceInfo deviceInfo) {
        this.mBaseSearchParams.setDeviceInfo(deviceInfo);
    }

    public Float getSearchMinScore() {
        return searchMinScore;
    }

    public void setSearchMinScore(Float searchMinScore) {
        this.searchMinScore = searchMinScore;
    }

    /**
     * Get the basic search parameter settings
     *
     * @return basic search parameter settings.
     */
    public BaseSearchParams getBaseSearchParams() {
        return mBaseSearchParams;
    }

    public String getCountry() {
        return mBaseSearchParams.getCountry();
    }

    public void setCountry(String country) {
        this.mBaseSearchParams.setCountry(country);
    }


    public Map<String, List<String>> toMap() {

        Map<String, List<String> > map = mBaseSearchParams.toMap();

        if (searchMinScore != null) {
            this.putStringInMap(map, SEARCH_MIN_SCORE, String.valueOf(searchMinScore));
        }

        return map;
    }

    protected void putStringInMap(Map<String, List<String> > map, String key, String value) {
        mBaseSearchParams.putStringInMap(map, key, value);
    }


}
