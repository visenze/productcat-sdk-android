package com.visenze.productcat.android.api.impl;

import android.content.Context;

import com.visenze.productcat.android.TrackParams;
import com.visenze.productcat.android.api.TrackOperations;
import com.visenze.productcat.android.http.HttpInstance;

/**
 * Created by visenze on 26/2/16.
 */
public class TrackOperationsImpl implements TrackOperations {
    private Context context;
    private String accessKey;

    /**
     * volley http instance
     */
    private HttpInstance httpInstance;

    public TrackOperationsImpl(Context context, String accessKey) {
        this.context = context;
        this.accessKey = accessKey;
        httpInstance = HttpInstance.getInstance(context.getApplicationContext());
    }

    @Override
    public void track(TrackParams trackParams) {
        httpInstance.addGetRequestToQueueWithoutResponse("http://track.visenze.com/__aq.gif", trackParams.toMap(accessKey));
    }
}
