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

    private Boolean recognizeTags;

    private String keyword;

    private Float recognizeMinScore;

    private Boolean preFilter;

    private List<String> filterTagIds;

    public Float getSearchMinScore() {
        return searchMinScore;
    }

    public void setSearchMinScore(Float searchMinScore) {
        this.searchMinScore = searchMinScore;
    }

    public Boolean getRecognizeTags() {
        return recognizeTags;
    }

    public void setRecognizeTags(Boolean recognizeTags) {
        this.recognizeTags = recognizeTags;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Float getRecognizeMinScore() {
        return recognizeMinScore;
    }

    public void setRecognizeMinScore(Float recognizeMinScore) {
        this.recognizeMinScore = recognizeMinScore;
    }

    public Boolean getPreFilter() {
        return preFilter;
    }

    public void setPreFilter(Boolean preFilter) {
        this.preFilter = preFilter;
    }

    public List<String> getFilterTagIds() {
        return filterTagIds;
    }

    public void setFilterTagIds(List<String> filterTagIds) {
        this.filterTagIds = filterTagIds;
    }

    @Override
    public Map<String, List<String> > toMap() {
        Map<String, List<String> > map = super.toMap();

        if (searchMinScore!=null) {
            this.putStringInMap(map, "search_min_score", String.valueOf(searchMinScore));
        }

        if (recognizeMinScore!=null) {
            this.putStringInMap(map, "recognize_min_score", String.valueOf(recognizeMinScore));
        }

        if (keyword != null){
            this.putStringInMap(map, "keyword", keyword);
        }

        if (recognizeTags != null){
            this.putStringInMap(map, "recognize_tags", String.valueOf(recognizeTags));
        }

        if (preFilter != null ) {
            this.putStringInMap(map, "pre_filter", String.valueOf(preFilter));
        }

        if (filterTagIds!=null && filterTagIds.size() > 0 ) {
            map.put("filter_tag_id" , filterTagIds);
        }

        return map;
    }

    private void putStringInMap(Map<String, List<String> > map, String key, String value) {
        List<String> stringList = new ArrayList<>();
        stringList.add(value);

        map.put(key, stringList);
    }



}
