package com.visenze.productcat.android;

import com.visenze.productcat.android.model.AdImgParam;
import com.visenze.productcat.android.model.AdParam;
import com.visenze.productcat.android.model.DeviceInfo;
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
    public static final String AUTO_ROI = "auto_roi";

    //PC-642 Sponsor Content API
    public static final String RETURN_SPONSORED_CONTENT = "return_sponsored_content";
    public static final String S_BANNER_IMG_W = "s_banner_img_w";
    public static final String S_BANNER_IMG_WMIN = "s_banner_img_wmin";
    public static final String S_BANNER_IMG_H = "s_banner_img_h";
    public static final String S_BANNER_IMG_HMIN = "s_banner_img_hmin";
    public static final String S_BANNER_IMG_MIME = "s_banner_img_mime";
    public static final String S_TITLE_MAX_LEN = "s_title_max_len";
    public static final String S_DESC_MAX_LEN = "s_desc_max_len";
    public static final String S_IMG_W = "s_img_w";
    public static final String S_IMG_WMIN = "s_img_wmin";
    public static final String S_IMG_H = "s_img_h";
    public static final String S_IMG_HMIN = "s_img_hmin";
    public static final String S_IMG_MIME = "s_img_mime";

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

    private Boolean autoRoi;

    private Boolean returnSponsoredContent;

    private AdParam adParam;

    private DeviceInfo deviceInfo;


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

    public Boolean getAutoRoi() {
        return autoRoi;
    }

    public void setAutoRoi(Boolean autoRoi) {
        this.autoRoi = autoRoi;
    }

    public Boolean getReturnSponsoredContent() {
        return returnSponsoredContent;
    }

    public void setReturnSponsoredContent(Boolean returnSponsoredContent) {
        this.returnSponsoredContent = returnSponsoredContent;
    }

    public AdParam getAdParam() {
        return adParam;
    }

    public void setAdParam(AdParam adParam) {
        this.adParam = adParam;
    }

    public DeviceInfo getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(DeviceInfo deviceInfo) {
        this.deviceInfo = deviceInfo;
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

        if (autoRoi!=null) {
            this.putStringInMap(map, AUTO_ROI, String.valueOf(autoRoi));
        }

        if (deviceInfo != null) {
            addDeviceInfoParams(map, deviceInfo);
        }

        if (returnSponsoredContent!=null) {
            this.putStringInMap(map, RETURN_SPONSORED_CONTENT, String.valueOf(returnSponsoredContent));

            addSponsorContentParams(map);
        }

        return map;
    }

    private void addSponsorContentParams(Map<String, List<String>> map) {
        if(returnSponsoredContent.booleanValue() && adParam!=null) {

            // add banner param
            addBannerParams(map, adParam.getBannerImg());

            // featured image param (within search result)
            addFeaturedImgParams(map, adParam.getImg());

            // title and desc
            if(adParam.getTitleMaxLen()!=null){
                this.putStringInMap(map, S_TITLE_MAX_LEN, String.valueOf(adParam.getTitleMaxLen()) );
            }

            if(adParam.getDescMaxLen()!=null){
                this.putStringInMap(map, S_DESC_MAX_LEN, String.valueOf(adParam.getDescMaxLen()) );
            }

        }
    }

    private void addFeaturedImgParams(Map<String, List<String>> map, AdImgParam img) {
        if(img == null){
            return;
        }

        if(img.getWidth()!=null){
            this.putStringInMap(map, S_IMG_W, String.valueOf(img.getWidth()) );
        }

        if(img.getMinWidth()!=null){
            this.putStringInMap(map, S_IMG_WMIN, String.valueOf(img.getMinWidth()) );
        }

        if(img.getHeight()!=null){
            this.putStringInMap(map, S_IMG_H, String.valueOf(img.getHeight()) );
        }

        if(img.getMinHeight()!=null){
            this.putStringInMap(map, S_IMG_HMIN, String.valueOf(img.getMinHeight()) );
        }

        if(img.getSupportedMimes()!=null){
            this.putStringInMap(map, S_IMG_MIME, img.getSupportedMimes());
        }

    }

    private void addBannerParams(Map<String, List<String>> map, AdImgParam bannerImg) {
        if (bannerImg == null){
            return;
        }

        if(bannerImg.getWidth()!=null){
            this.putStringInMap(map, S_BANNER_IMG_W, String.valueOf(bannerImg.getWidth()) );
        }

        if(bannerImg.getMinWidth()!=null){
            this.putStringInMap(map, S_BANNER_IMG_WMIN, String.valueOf(bannerImg.getMinWidth()) );
        }

        if(bannerImg.getHeight()!=null){
            this.putStringInMap(map, S_BANNER_IMG_H, String.valueOf(bannerImg.getHeight()) );
        }

        if(bannerImg.getMinHeight()!=null){
            this.putStringInMap(map, S_BANNER_IMG_HMIN, String.valueOf(bannerImg.getMinHeight()) );
        }

        if(bannerImg.getSupportedMimes()!=null){
            this.putStringInMap(map, S_BANNER_IMG_MIME, bannerImg.getSupportedMimes());
        }

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

    }

    private void putStringInMap(Map<String, List<String> > map, String key, String value) {
        List<String> stringList = new ArrayList<>();
        stringList.add(value);

        map.put(key, stringList);
    }



}
