package com.visenze.productcat.android;


import android.util.Log;

import com.visenze.productcat.android.model.Box;
import com.visenze.productcat.android.model.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Set upload search parameters
 * Upload search can be initialised with an image attached or an image url.
 */
public class UploadSearchParams extends SearchParams {

    public static final String BOX = "box";
    public static final String COMMA = ",";
    public static final String IM_URL = "im_url";
    public static final String IM_ID = "im_id";

    private Image image;

    private String imageUrl;

    private String imId;

    private Box uploadBox;

    public UploadSearchParams() {
        super();
    }

    /**
     * Construct with an image url.
     *
     * @param imageUrl image url.
     */
    public UploadSearchParams(String imageUrl) {
        super();
        this.imageUrl = imageUrl;
    }

    /**
     * Construct with an {@link Image Image}
     *
     * @param image {@link Image Image} instance, handles image decode and optimisation
     */
    public UploadSearchParams(Image image) {
        super();
        this.image = image;
    }

    /**
     * Set the {@link Image Image} to upload
     *
     * @param image {@link Image Image} instance.
     * @return this instance.
     */
    public UploadSearchParams setImage(Image image) {
        //free memory
        this.image = image;

        return this;
    }

    /**
     * Set image url
     *
     * @param imageUrl image url.
     * @return this instance.
     */
    public UploadSearchParams setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    /**
     * Set image id
     *
     * @param imId im id
     * @return this instance.
     */
    public UploadSearchParams setImId(String imId) {
        this.imId = imId;
        return this;
    }

    /**
     * set cropping box for im_url or im_id. Use box in {@link Image} if upload
     * an image instead
     *
     * @param uploadBox box
     * @return this instance
     */
    public UploadSearchParams setBox(Box uploadBox) {
        this.uploadBox = uploadBox;
        return this;
    }

    /**
     * Get {@link Image Image} that is set to search
     *
     * @return {@link Image Image} instance
     */
    public Image getImage() {
        return image;
    }

    /**
     * Get image url that is set to search
     *
     * @return image url.
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Get image id that is set to search
     *
     * @return imId.
     */
    public String getImId() {
        return imId;
    }

    @Override
    public Map<String, List<String> > toMap() {
        Map<String, List<String> > map = super.toMap();

        if (image != null && image.getBox() != null) {
            putBox(map, image.getBox());
        }

        if (imageUrl != null) {
            putStringInMap(map, IM_URL, imageUrl);
            putBox(map);
        }

        if (imId != null) {
            putStringInMap(map, IM_ID, imId);
            putBox(map);
        }

        return map;
    }

    private void putBox(Map<String, List<String>> map) {
        putBox(map, uploadBox);
    }

    private void putBox(Map<String, List<String>> map, Box box) {
        if (box == null) {
            return;
        }

        if (box.getX1() != null && box.getX2() != null &&
                box.getY1() != null && box.getY2() != null) {
            putStringInMap(map, BOX, box.getX1() + COMMA + box.getY1() + COMMA + box.getX2() + COMMA + box.getY2());
        }

    }

    private void putStringInMap(Map<String, List<String> > map, String key, String value) {
        List<String> stringList = new ArrayList<>();
        stringList.add(value);

        map.put(key, stringList);
    }
}
