package com.visenze.productcat.android.api.impl;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RetryPolicy;
import com.visenze.productcat.android.ImageSearchParams;
import com.visenze.productcat.android.ProductCat;
import com.visenze.productcat.android.ProductCatException;
import com.visenze.productcat.android.SimilarImageSearchParams;
import com.visenze.productcat.android.TextSearchParams;
import com.visenze.productcat.android.UpdatePrivacyParams;
import com.visenze.productcat.android.api.SearchOperations;
import com.visenze.productcat.android.http.HttpInstance;
import com.visenze.productcat.android.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of search operations interface.
 */
public class SearchOperationsImpl implements SearchOperations {
    public static final String PRODUCTCAT_TEXT_SEARCH = "productcat_text_search";
    public static final String UPDATE_PRIVACY_STATUS = "update_privacy_status";

    private final static String PRODUCT_SUMMARY_SEARCH = "/summary/products" ;
    private final static String PRODUCT_SUMMARY_SEARCH_RESULT_PAGE = "/summary/products/srp";
    public static final String PRODUCTCAT_SIMILAR_SEARCH = "productcat_similar_search";
    public static final String SIMILAR_PRODUCTS_PATH = "/similar/products/";

    public static final String PRIVACY_STATUS = "/users/privacy";

    /**
     * URL
     */
    private static String apiBase;

    /**
     * volley http instance
     */
    private HttpInstance httpInstance;

    // retry policy
    private RetryPolicy retryPolicy;


    /**
     * SearchOperationsImpl: search operation implementation
     *
     * @param apiUrl api url
     * @param context activity context
     * @param appKey app_key
     * @param userAgent custom user agent
     */
    public SearchOperationsImpl(String apiUrl, Context context, String appKey, String userAgent) {
        apiBase = apiUrl;
        httpInstance = HttpInstance.getInstance(context.getApplicationContext());
        httpInstance.setKeys(appKey);
        httpInstance.setUserAgent(userAgent);

        retryPolicy = new DefaultRetryPolicy();

    }

    public SearchOperationsImpl(String apiUrl, Context context, String appKey, String userAgent, boolean shouldCache) {
        this(apiUrl, context, appKey, userAgent);
        httpInstance.setShouldCache(shouldCache);
    }

    @Override
    public void imageSearch(ImageSearchParams params, final ProductCat.ResultListener resultListener) {
        imageSearch(params, resultListener, PRODUCT_SUMMARY_SEARCH);
    }

    @Override
    public void imageSearchResultPage(ImageSearchParams params, final ProductCat.ResultListener resultListener) {
        imageSearch(params, resultListener, PRODUCT_SUMMARY_SEARCH_RESULT_PAGE);
    }

    @Override
    public void imageSearch(ImageSearchParams params, final ProductCat.ResultListener resultListener, String customSearchPath) {
        byte[] imageBytes = null;
        if (params.getImage() != null) {
            imageBytes = params.getImage().getByteArray();
        }
        String imageUrl = params.getImageUrl();
        String imId = params.getImId();

        if (imageBytes == null && (imageUrl == null || imageUrl.isEmpty()) && (imId == null || imId.isEmpty())) {
            throw new ProductCatException("Missing parameter, image empty");

        } else if (imageBytes != null) {
            httpInstance.addMultipartRequestToQueue(
                    apiBase + customSearchPath, params.toMap(), imageBytes, resultListener, retryPolicy);
        } else {
            httpInstance.addMultipartRequestToQueue(
                    apiBase + customSearchPath, params.toMap(), null, resultListener, retryPolicy);
        }
    }

    @Override
    public void similarImageSearch(SimilarImageSearchParams params, final ProductCat.ResultListener mListener) {
        if (StringUtils.isEmpty(params.getPid()) ) {
            throw new ProductCatException("Missing pid parameter");
        }

        if (StringUtils.isEmpty(params.getCountry()) ) {
            throw new ProductCatException("Missing country parameter");
        }

        httpInstance.addGetRequestToQueue(
                apiBase + SIMILAR_PRODUCTS_PATH + params.getPid(),
                params.toMap(),
                PRODUCTCAT_SIMILAR_SEARCH,
                mListener, retryPolicy) ;
    }

    @Override
    public void getPrivacyPolicyStatus(String uid, final ProductCat.ResultListener mListener) {

        Map<String, List<String>> params = new HashMap<String, List<String>>();
        List<String> uidList = new ArrayList<>();
        uidList.add(uid);
        params.put(HttpInstance.UID, uidList);
        httpInstance.addGetRequestToQueue(apiBase + PRIVACY_STATUS, params, PRIVACY_STATUS, mListener, retryPolicy);
    }

    @Override
    public void updatePrivacyPolicyStatus(boolean opt_in, String email, final ProductCat.ResultListener mListener) {
        UpdatePrivacyParams params = new UpdatePrivacyParams(opt_in, email);
        httpInstance.addPostRequestToQueue(apiBase + PRIVACY_STATUS, params.toMap(), PRIVACY_STATUS, mListener, retryPolicy);
    }


    @Override
    public void textSearch(TextSearchParams params, ProductCat.ResultListener mListener) {
        if (StringUtils.isEmpty(params.getQ()) ) {
            throw new ProductCatException("Missing parameter, query keyword empty");
        }
        httpInstance.addGetRequestToQueue(apiBase + PRODUCT_SUMMARY_SEARCH, params.toMap(),
                PRODUCTCAT_TEXT_SEARCH, mListener, retryPolicy) ;
    }

    @Override
    public void setRetryPolicy(int timeout, int retryCount) {
        retryPolicy = new DefaultRetryPolicy(timeout, retryCount, 1);
    }

    /**
     * cancel search
     *
     * @param resultListener callback listener to be canceled
     */
    public void cancelSearch(ProductCat.ResultListener resultListener) {
        httpInstance.cancelRequest(resultListener);
    }
}
