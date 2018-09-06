package com.visenze.productcat.android;

import com.visenze.productcat.android.model.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by visenze on 12/4/17.
 */

public class ImageSearchParams extends UploadSearchParams {

    public static final String SEARCH_MIN_SCORE = "search_min_score";
    public static final String PRE_FILTER = "pre_filter";
    public static final String DETECTION_ONLY = "detection_only";
    public static final String RECOGNIZE_MIN_SCORE = "recognize_min_score";
    public static final String SHOW_VISUAL_SCORE = "show_visual_score";

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

    private Float recognizeMinScore;

    private Boolean showVisualScore;

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

    public Float getRecognizeMinScore() {
        return recognizeMinScore;
    }

    public void setRecognizeMinScore(Float recognizeMinScore) {
        this.recognizeMinScore = recognizeMinScore;
    }

    public Boolean getShowVisualScore() {
        return showVisualScore;
    }

    public void setShowVisualScore(Boolean showVisualScore) {
        this.showVisualScore = showVisualScore;
    }

    @Override
    public Map<String, List<String> > toMap() {
        Map<String, List<String> > map = super.toMap();

        if (searchMinScore!=null) {
            this.putStringInMap(map, SEARCH_MIN_SCORE, String.valueOf(searchMinScore));
        }

        if (preFilter != null ) {
            this.putStringInMap(map, PRE_FILTER, String.valueOf(preFilter));
        }

        if (detectionOnly!=null) {
            this.putStringInMap(map, DETECTION_ONLY, String.valueOf(detectionOnly));
        }

        if (recognizeMinScore!=null) {
            this.putStringInMap(map, RECOGNIZE_MIN_SCORE, String.valueOf(recognizeMinScore));
        }

        if (showVisualScore!=null) {
            this.putStringInMap(map, SHOW_VISUAL_SCORE, String.valueOf(showVisualScore));
        }

        return map;
    }

    private void putStringInMap(Map<String, List<String> > map, String key, String value) {
        List<String> stringList = new ArrayList<>();
        stringList.add(value);

        map.put(key, stringList);
    }



}
