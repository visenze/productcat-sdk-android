package com.visenze.productcat.android.http;

import android.content.Context;
import android.net.Uri;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.visenze.productcat.android.ProductCat;
import com.visenze.productcat.android.util.ProductCatUIDManager;

import org.json.JSONObject;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HttpInstance {
    /**
     * HTTP CONSTANT for Multipart Entity Uploading
     */
    public static final int TIME_OUT_FOR_UPLOAD = 60000;
    public static final int TIME_OUT_FOR_ID = 20000;
    public static final String APP_KEY = "app_key";
    public static final String PRODUCTCAT_VISUAL_SEARCH = "productcat_visual_search";
    public static final String UID = "uid";

    /**
     * http instance
     */
    private static HttpInstance     mInstance;

    /**
     * application context
     */
    private static Context          mContext;

    /**
     * api access key and secret key
     */
    private String                  appKey;

    private String                  userAgent;

    /**
     * request queue
     */
    private RequestQueue            mRequestQueue;

    /** Whether or not responses to this request should be cached. */
    private boolean shouldCache = false;

    /**
     * private constructor
     *
     * @param context application context
     */
    private HttpInstance(Context context) {
        CookieManager manager = new CookieManager();
        CookieHandler.setDefault(manager);
        mContext = context;
        mRequestQueue = getRequestQueue();
    }

    /**
     * singleton constructor
     *
     * @param context application context
     *
     * @return singleton instance
     */
    public static synchronized HttpInstance getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new HttpInstance(context);
        }

        return mInstance;
    }

    public void setKeys(String appKey) {
        this.appKey = appKey;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public void setShouldCache(boolean shouldCache) {
        this.shouldCache = shouldCache;
    }


    /**
     * request queue getter
     *
     * @return requestQueue
     */
    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }

        return mRequestQueue;
    }

    // for adding admin request
    public void addAdminGetRequestToQueue(String url,
                                          Map<String, List<String>> params,
                                          ProductCat.StoreResultListener storeResultListener) {


        StoreResponseListener responseListener = new StoreResponseListener(storeResultListener);
        addUidParam(params);
        Uri.Builder uri = buildQueryString(params);

        JsonWithHeaderRequest jsonObjectRequest = new JsonWithHeaderRequest(
                Request.Method.GET, url + uri.toString(), null,
                responseListener,
                new StoreResponseErrorListener(storeResultListener)) {

        };

        jsonObjectRequest.setTag(mContext);
        jsonObjectRequest.setShouldCache(shouldCache);

        getRequestQueue().add(jsonObjectRequest);
    }

    /**
     * start a new request by passing the url, params and result listener
     *
     * @param url url to call
     * @param params parameters
     * @param type search type
     * @param resultListener result listener
     */
    public void addGetRequestToQueue(
            final String url,
            Map<String, List<String>> params,
            String type,
            final ProductCat.ResultListener resultListener,
            RetryPolicy retryPolicy) {

        ResponseListener responseListener = new ResponseListener(resultListener, type);

        if (null == params) {
            params = new HashMap<String, List<String>>();
        }

        addUidParam(params);

        Uri.Builder uri = buildQueryString(params);

        JsonWithHeaderRequest jsonObjectRequest = new JsonWithHeaderRequest(
                Request.Method.GET, url + uri.toString(), null,
                responseListener,
                new ResponseErrorListener(resultListener)) {

        };

        if (retryPolicy!=null) {
            jsonObjectRequest.setRetryPolicy(retryPolicy);
        }

        jsonObjectRequest.setTag(mContext);
        jsonObjectRequest.setShouldCache(shouldCache);

        getRequestQueue().add(jsonObjectRequest);
    }


    public void addPostRequestToQueue(
            final String url,
            Map<String, List<String>> params,
            String type,
            final ProductCat.ResultListener resultListener,
            RetryPolicy retryPolicy
    ) {

        ResponseListener responseListener = new ResponseListener(resultListener, type);

        if (null == params) {
            params = new HashMap<String, List<String>>();
        }

        addUidParam(params);

        Uri.Builder uri = buildQueryString(params);

        JsonWithHeaderRequest jsonObjectRequest = new JsonWithHeaderRequest(
                Request.Method.POST, url + uri.toString(), null,
                responseListener,
                new ResponseErrorListener(resultListener)) {

        };

        if (retryPolicy!=null) {
            jsonObjectRequest.setRetryPolicy(retryPolicy);
        }

        jsonObjectRequest.setTag(mContext);
        jsonObjectRequest.setShouldCache(shouldCache);

        getRequestQueue().add(jsonObjectRequest);

    }

    private Uri.Builder buildQueryString(Map<String, List<String>> params) {
        Uri.Builder uri = new Uri.Builder();
        for (Map.Entry<String, List<String> > entry : params.entrySet()) {
            for (String s: entry.getValue())
                uri.appendQueryParameter(entry.getKey(), s);
        }

        // add key
        uri.appendQueryParameter(APP_KEY, appKey);
        return uri;
    }

    /**
     * start a request by add multipart parameters
     *
     * @param url url to call
     * @param params parameters
     * @param bytes byte array
     * @param resultListener result listener
     */
    public void addMultipartRequestToQueue (
            final String url,
            Map<String, List<String> > params,
            byte[] bytes,
            final ProductCat.MPSListener resultListener,
            RetryPolicy retryPolicy) {

        MPSResponseListener responseListener = new MPSResponseListener(resultListener, PRODUCTCAT_VISUAL_SEARCH);

        if (null == params) {
            params = new HashMap<String, List<String>>();
        }

        addUidParam(params);

        Uri.Builder uri = new Uri.Builder();
        uri.appendQueryParameter(APP_KEY, appKey);

        MultiPartRequest multipartRequest = new MultiPartRequest(Request.Method.POST, url + uri.toString(),
                params, bytes,
                appKey, userAgent,
                responseListener,
                new MPSResponseErrorListener(resultListener));

        if (retryPolicy!=null) {
            multipartRequest.setRetryPolicy(retryPolicy);
        }

        multipartRequest.setTag(mContext);
        multipartRequest.setShouldCache(shouldCache);
        getRequestQueue().add(multipartRequest);
    }


    /**
     * start a request by add multipart parameters
     *
     * @param url url to call
     * @param params parameters
     * @param bytes byte array
     * @param resultListener result listener
     */
    public void addMultipartRequestToQueue (
            final String url,
            Map<String, List<String> > params,
            byte[] bytes,
            final ProductCat.ResultListener resultListener,
            RetryPolicy retryPolicy) {

        ResponseListener responseListener = new ResponseListener(resultListener, PRODUCTCAT_VISUAL_SEARCH);

        if (null == params) {
            params = new HashMap<String, List<String>>();
        }

        addUidParam(params);

        Uri.Builder uri = new Uri.Builder();
        uri.appendQueryParameter(APP_KEY, appKey);

        MultiPartRequest multipartRequest = new MultiPartRequest(Request.Method.POST, url + uri.toString(),
                params, bytes,
                appKey, userAgent,
                responseListener,
                new ResponseErrorListener(resultListener));

        if (retryPolicy!=null) {
            multipartRequest.setRetryPolicy(retryPolicy);
        }

        multipartRequest.setTag(mContext);
        multipartRequest.setShouldCache(shouldCache);
        getRequestQueue().add(multipartRequest);
    }

    private void addUidParam(Map<String, List<String>> params) {
        // already have uid, no action needed
        if (params.containsKey(UID)){
            return;
        }

        String storeUid = ProductCatUIDManager.getUid();
        if (storeUid!=null) {
            List<String> uidList = new ArrayList<>();
            uidList.add(storeUid);
            params.put(UID, uidList);
        }
    }

    /**
     * cancel request
     *
     * @param resultListener callback listener
     */
    public void cancelRequest(ProductCat.ResultListener resultListener) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(mContext);

            // clear all requests
            mRequestQueue.cancelAll(new RequestQueue.RequestFilter() {
                @Override
                public boolean apply(Request<?> request) {
                    return true;
                }
            });

            if (null != resultListener)
                resultListener.onSearchCanceled();
        }
    }


}
