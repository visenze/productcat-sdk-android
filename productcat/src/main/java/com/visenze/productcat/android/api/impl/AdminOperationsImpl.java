package com.visenze.productcat.android.api.impl;

import android.content.Context;

import com.visenze.productcat.android.ProductCat;
import com.visenze.productcat.android.StoreParams;
import com.visenze.productcat.android.api.AdminOperations;
import com.visenze.productcat.android.http.HttpInstance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminOperationsImpl implements AdminOperations {
    /**
     * URL
     */
    private String apiBase;

    private String getStoresPath ;

    /**
     * volley http instance
     */
    private HttpInstance httpInstance;

    /**
     * SearchOperationsImpl: search operation implementation
     *
     * @param endpoint api endpoint
     * @param context activity context
     * @param appKey app key
     */
    public AdminOperationsImpl(String endpoint,
                               String getStorePath,
                               Context context,
                               String appKey,
                               String userAgent) {

        this.apiBase = endpoint;
        this.getStoresPath = getStoresPath;

        httpInstance = HttpInstance.getInstance(context.getApplicationContext());
        httpInstance.setKeys(appKey);
        httpInstance.setUserAgent(userAgent);

    }

    @Override
    public void getStores(StoreParams params, ProductCat.StoreResultListener mListener) {
        httpInstance.addAdminGetRequestToQueue(apiBase + getStoresPath, params.toMap(), mListener) ;
    }


}
