package com.visenze.productcat.android;

import com.visenze.productcat.android.model.DeviceInfo;

import java.util.List;
import java.util.Map;

/**
 * Abstract class. Search parameter settings.
 * This class is extended by
 * {@link UploadSearchParams UploadSearchParams}.
 */
public abstract class SearchParams {
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

    /**
     * Get the basic search parameter settings
     *
     * @return basic search parameter settings.
     */
    public BaseSearchParams getBaseSearchParams() {
        return mBaseSearchParams;
    }

    protected Map<String, List<String>> toMap() {
        return mBaseSearchParams.toMap();
    }

    protected void putStringInMap(Map<String, List<String> > map, String key, String value) {
        mBaseSearchParams.putStringInMap(map, key, value);
    }

}
