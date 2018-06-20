package com.visenze.productcat.android;

import com.visenze.productcat.android.model.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by visenze on 12/4/17.
 */

public class ImageSearchParams extends UploadSearchParams {

    public ImageSearchParams() {
        super();
    }

    /**
     * Construct with an image url.
     *
     * @param imageUrl image url.
     */
    public ImageSearchParams(String imageUrl) {
        super(imageUrl);
    }

    /**
     * Construct with an {@link Image Image}
     *
     * @param image {@link Image Image} instance, handles image decode and optimisation
     */
    public ImageSearchParams(Image image) {
        super(image);
    }

    private Float searchMinScore = null;

    private Boolean preFilter;

    private Boolean detectionOnly;

    public Float getSearchMinScore() {
        return searchMinScore;
    }

    public void setSearchMinScore(Float searchMinScore) {
        this.searchMinScore = searchMinScore;
    }

    public Boolean getPreFilter() {
        return preFilter;
    }

    public void setPreFilter(Boolean preFilter) {
        this.preFilter = preFilter;
    }

    public Boolean getDetectionOnly() {
        return detectionOnly;
    }

    public void setDetectionOnly(Boolean detectionOnly) {
        this.detectionOnly = detectionOnly;
    }

    @Override
    public Map<String, List<String> > toMap() {
        Map<String, List<String> > map = super.toMap();

        if (searchMinScore!=null) {
            this.putStringInMap(map, "search_min_score", String.valueOf(searchMinScore));
        }

        if (preFilter != null ) {
            this.putStringInMap(map, "pre_filter", String.valueOf(preFilter));
        }

        if (detectionOnly!=null) {
            this.putStringInMap(map, "detection_only", String.valueOf(detectionOnly));
        }

        return map;
    }

    private void putStringInMap(Map<String, List<String> > map, String key, String value) {
        List<String> stringList = new ArrayList<>();
        stringList.add(value);

        map.put(key, stringList);
    }



}