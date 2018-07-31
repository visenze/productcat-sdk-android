package com.visenze.productcat.android;

import android.content.Context;
import android.util.Log;

import com.visenze.productcat.BuildConfig;
import com.visenze.productcat.android.api.impl.SearchOperationsImpl;
import com.visenze.productcat.android.api.impl.TrackOperationsImpl;
import com.visenze.productcat.android.model.ResultList;
import com.visenze.productcat.android.util.ProductCatUIDManager;

import java.net.URL;

/**
 * ProductCat singleton handles all the search methods.
 *
 * ProductCat should be initialised by Builder with a valid API access/secret key pair before it can be used.
 */
public class ProductCat {

    public static final int DEFAULT_TIMEOUT_MS = 60000;
    public static final int DEFAULT_RETRY_COUNT = 1;

    private static final String USER_AGENT = "productcat-android-sdk";
    private static final String API_END_POINT = "https://productcat.visenze.com";

    private SearchOperationsImpl searchOperations;

    private TrackOperationsImpl trackOperations;

    private ResultListener mListener;

    private String uid;

    // timeout in ms
    private int timeout;

    // customize number of retries
    private int retryCount;

    /**
     * Initialise the ViSearcher with a valid access/secret key pair
     *
     * @param context Activity context
     * @param appKey the App Key or the Access Key
     */
    private ProductCat(Context context,
                       String appKey,
                       String searchApiEndPoint,
                       String userAgent,
                       boolean shouldCache) {

        initTracking(context.getApplicationContext());
        searchOperations = new SearchOperationsImpl(
                searchApiEndPoint,
                context,
                appKey, userAgent, shouldCache);
        trackOperations = new TrackOperationsImpl(context, appKey);

        timeout = DEFAULT_TIMEOUT_MS;
        retryCount = DEFAULT_RETRY_COUNT;
        searchOperations.setRetryPolicy(timeout, retryCount);
    }

    /**
     * Sets the {@link ProductCat ResultListener} to be notified of the search result
     *
     * @param listener the ViSearcher.ResultListener to notify.
     */
    public void setListener(ResultListener listener) {
        mListener = listener;
    }


    /**
     * Cancel the search
     */
    public void cancelSearch() {
        searchOperations.cancelSearch(mListener);
    }

    public void imageSearch(final ImageSearchParams params) {
        try {
            searchOperations.imageSearch(params, mListener);
        } catch (ProductCatException e) {
            Log.e("ProductCat SDK", e.getMessage());
        }
    }

    public void textSearch(final TextSearchParams params) {
        try {
            searchOperations.textSearch(params, mListener);
        } catch (ProductCatException e) {
            Log.e("ProductCat SDK", e.getMessage());
        }
    }

    public void track(final TrackParams trackParams) {
        try {
            trackOperations.track(trackParams);
        } catch (ProductCatException e) {
            Log.e("ProductCat SDK", e.getMessage());
        }
    }

    private void initTracking(final Context context) {
        ProductCatUIDManager.generateUniqueDeviceId(context);
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
        searchOperations.setRetryPolicy(timeout, retryCount);
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
        searchOperations.setRetryPolicy(timeout, retryCount);
    }

    /**
     * Builder class for {@link ProductCat}
     */
    public static class Builder {
        private String mAppKey;
        private String searchApiEndPoint;
        private String userAgent;

        private boolean shouldCache = false;

        private int timeout;
        private int retryCount;

        public Builder(String appKey) {
            mAppKey = appKey;
            searchApiEndPoint = API_END_POINT;
            userAgent = USER_AGENT + "/" + BuildConfig.VERSION_NAME;
        }

        public Builder(URL endPoint, String appKey) {
            if (endPoint == null)
                throw new ProductCatException("Endpoint is not specified");

            searchApiEndPoint = endPoint.toString();
            mAppKey = appKey;
            userAgent = USER_AGENT + "/" + BuildConfig.VERSION_NAME;
        }


        public Builder setApiEndPoint(URL endPoint) {
            searchApiEndPoint = endPoint.toString();
            return this;
        }

        public Builder setUserAgent(String userAgent) {
            this.userAgent = userAgent;
            return this;
        }

        public Builder setShouldCache(boolean shouldCache) {
            this.shouldCache = shouldCache;
            return this;
        }

        public Builder setTimeout(int timeout) {
            this.timeout = timeout;
            return this;
        }

        public Builder setRetryCount(int retryCount) {
            this.retryCount = retryCount;
            return this;
        }

        public ProductCat build(Context context) {

            return new ProductCat(context,
                    mAppKey,
                    searchApiEndPoint,
                    userAgent,
                    shouldCache);
        }
    }

    /**
     * Interface to be notified of results and potential errors
     */
    public static interface ResultListener {

        /**
         * Called after a search session is started and a valid result is returned
         *
         * @param resultList the result list if any, null otherwise.
         */
        public abstract void onSearchResult(final ResultList resultList);

        /**
         * Called after a search session is started and an error occurs
         *
         * @param errorMessage the error message if error occurs.
         */
        public abstract void onSearchError(String errorMessage);

        /**
         * Called when cancelSearch is called
         */
        public abstract void onSearchCanceled();

    }

}
