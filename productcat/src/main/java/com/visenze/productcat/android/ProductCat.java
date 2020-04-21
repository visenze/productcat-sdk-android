package com.visenze.productcat.android;

import android.content.Context;
import android.util.Log;

import com.visenze.productcat.BuildConfig;
import com.visenze.productcat.android.api.AdminOperations;
import com.visenze.productcat.android.api.impl.AdminOperationsImpl;
import com.visenze.productcat.android.api.impl.SearchOperationsImpl;
import com.visenze.productcat.android.data.DataCollection;
import com.visenze.productcat.android.model.DeviceInfo;
import com.visenze.productcat.android.model.ResultList;
import com.visenze.productcat.android.model.StoreResultList;
import com.visenze.productcat.android.ui.PrivacyPolicy;
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
    public static final String TERMS_DENIED = "VISENZE_TERMS_DENIED";


    private static final String USER_AGENT = "productcat-android-sdk";
    private static final String API_END_POINT = "https://productcat.visenze.com";

    private SearchOperationsImpl searchOperations;
    private AdminOperations adminOperations;

    private ResultListener mListener;
    private StoreResultListener mStoreResultListener;

    // timeout in ms
    private int timeout;

    // customize number of retries
    private int retryCount;


    private PrivacyPolicy mPrivacyPolicy;

    private DataCollection mDataCollection;


    private ProductCat(Context context,
                       String appKey,
                       String searchApiEndPoint,
                       String userAgent,
                       boolean shouldCache,
                       String adminEndpoint,
                       String adminGetStorePath) {

        this(context, appKey, searchApiEndPoint, userAgent, shouldCache);
        adminOperations = new AdminOperationsImpl(adminEndpoint, adminGetStorePath, context, appKey, userAgent);
    }


    /**
     * Initialise the ViSearcher with a valid access/secret key pair
     *
     * @param context Activity context
     * @param appKey the App Key or the Access Key
     * @param searchApiEndPoint custom ProductCat endpoint
     * @param userAgent custom user agent for ProductCat
     * @param shouldCache whether to cache API response based on URL
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

        timeout = DEFAULT_TIMEOUT_MS;
        retryCount = DEFAULT_RETRY_COUNT;
        searchOperations.setRetryPolicy(timeout, retryCount);


        mDataCollection = new DataCollection(context);
        mPrivacyPolicy = new PrivacyPolicy(context);


        // show content form for the first time initialize.
        if(!mPrivacyPolicy.isPrivacyShown()) {
            showConsentForm();
        }
        
    }



    /**
     * Sets the {@link ProductCat ResultListener} to be notified of the search result
     *
     * @param listener the ViSearcher.ResultListener to notify.
     */
    public void setListener(ResultListener listener) {
        mListener = listener;
    }

    public void setStoreResultListener(StoreResultListener mStoreResultListener) {
        this.mStoreResultListener = mStoreResultListener;
    }

    public void getStores(final StoreParams storeParams) {
        try {
            adminOperations.getStores(storeParams, mStoreResultListener);
        } catch (ProductCatException e) {
            logProductCatErrorMessage(e);
        }
    }

    /**
     * Cancel the search
     */
    public void cancelSearch() {
        searchOperations.cancelSearch(mListener);
    }

    public void showConsentForm() {
        mPrivacyPolicy.showConsentDialog();
    }

    private boolean checkPrivacyPolicy(final ImageSearchParams params) {
        if(mPrivacyPolicy.isPrivacyShown()) {
            if(mPrivacyPolicy.isTermsAccepted()) {
                DeviceInfo info = mDataCollection.getDeviceInfo(mPrivacyPolicy.isAdsAccepted());
                params.setDeviceInfo(info);
                return true;
            } else {
                mListener.onSearchError(TERMS_DENIED);
            }
        } else {
            showConsentForm();
        }
        return false;

    }

    public void imageSearchResultPage(final ImageSearchParams params) {
        if(checkPrivacyPolicy(params)) {
            try {
                searchOperations.imageSearchResultPage(params, mListener);
            } catch (ProductCatException e){
                logProductCatErrorMessage(e);
            }
        }
    }

    public void imageSearch(final ImageSearchParams params) {
        if(checkPrivacyPolicy(params)) {
            try {
                searchOperations.imageSearch(params, mListener);
            } catch (ProductCatException e) {
                logProductCatErrorMessage(e);
            }
        }
    }

    public void imageSearch(final ImageSearchParams params, String customSearchPoint) {
        if(checkPrivacyPolicy(params)) {
            try {
                searchOperations.imageSearch(params, mListener, customSearchPoint);
            } catch (ProductCatException e) {
                logProductCatErrorMessage(e);
            }
        }
    }

    public void textSearch(final TextSearchParams params) {
        try {
            searchOperations.textSearch(params, mListener);
        } catch (ProductCatException e) {
            logProductCatErrorMessage(e);
        }
    }

    private void logProductCatErrorMessage(ProductCatException e) {
        Log.e("ProductCat SDK", e.getMessage());
    }

    private void initTracking(final Context context) {
        ProductCatUIDManager.initPref(context);
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        if (timeout < 1) {
            System.out.println("timeout must be greater than 0");
            return;
        }
        this.timeout = timeout;
        searchOperations.setRetryPolicy(timeout, retryCount);
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        if (retryCount < 1) {
            System.out.println("retryCount must be at least 1");
            return;
        }
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

        private Integer timeout;
        private Integer retryCount;
        private String uid;

        private String adminEndpoint;
        private String adminGetStoresPath;

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

        public Builder setAdminEndpoint(String adminEndpoint) {
            this.adminEndpoint = adminEndpoint;
            return this;
        }

        public Builder setAdminGetStoresPath(String adminGetStoresPath) {
            this.adminGetStoresPath = adminGetStoresPath;
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

        public Builder setUid(String uid) {
            this.uid = uid;
            return this;
        }

        public ProductCat build(Context context) {

            ProductCat productCat = adminEndpoint == null ?
                    new ProductCat(context,
                        mAppKey,
                        searchApiEndPoint,
                        userAgent,
                        shouldCache) :
                    new ProductCat(context,
                        mAppKey,
                        searchApiEndPoint,
                        userAgent,
                        shouldCache,
                        adminEndpoint, adminGetStoresPath);

            if(retryCount!=null) {
                productCat.setRetryCount(retryCount);
            }

            if (timeout!=null) {
                productCat.setTimeout(timeout);
            }

            if (uid!=null) {
                ProductCatUIDManager.setUid(uid);
            }

            return productCat;
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

    public static interface StoreResultListener {
        // call back for getting list of stores
        public abstract void onResult(final StoreResultList storeResultList);

        // call upon any error
        public abstract void onError(String errorMessage);
    }


}
