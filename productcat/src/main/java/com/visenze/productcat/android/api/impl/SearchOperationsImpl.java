package com.visenze.productcat.android.api.impl;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RetryPolicy;
import com.visenze.productcat.android.ImageSearchParams;
import com.visenze.productcat.android.ProductCat;
import com.visenze.productcat.android.ProductCatException;
import com.visenze.productcat.android.TextSearchParams;
import com.visenze.productcat.android.api.SearchOperations;
import com.visenze.productcat.android.http.HttpInstance;

/**
 * Implementation of search operations interface.
 */
public class SearchOperationsImpl implements SearchOperations {
    /**
     * URL
     */
    private static String apiBase;

    private final static String PRODUCT_SUMMARY_SEARCH = "/summary/products" ;

    /**
     * volley http instance
     */
    private HttpInstance httpInstance;

    private RetryPolicy retryPolicy;

    /**
     * SearchOperationsImpl: search operation implementation
     *
     * @param apiUrl api url
     * @param context activity context
     * @param appKey app key
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
                    apiBase + PRODUCT_SUMMARY_SEARCH, params.toMap(), imageBytes, resultListener, retryPolicy);
        } else {
            httpInstance.addMultipartRequestToQueue(
                    apiBase + PRODUCT_SUMMARY_SEARCH, params.toMap(), null, resultListener, retryPolicy);
        }
    }

    @Override
    public void textSearch(TextSearchParams params, ProductCat.ResultListener mListener) {
        if (params.getQ() == null || params.getQ().length() == 0) {
            throw new ProductCatException("Missing parameter, query keyword empty");
        }
        httpInstance.addGetRequestToQueue(apiBase + PRODUCT_SUMMARY_SEARCH, params.toMap(),
                "productcat_text_search", mListener, retryPolicy) ;
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
