package com.visenze.productcat.android.api;

import com.visenze.productcat.android.ImageSearchParams;
import com.visenze.productcat.android.ProductCat;
import com.visenze.productcat.android.SimilarImageSearchParams;
import com.visenze.productcat.android.TextSearchParams;


/**
 * Search operations interface.
 */
public interface SearchOperations {
    public void imageSearch(ImageSearchParams params, final ProductCat.ResultListener resultListener);

    public void imageSearch(ImageSearchParams params, final ProductCat.ResultListener resultListener, String customSearchPath);

    public void textSearch(TextSearchParams params, ProductCat.ResultListener mListener);

    public void setRetryPolicy(int timeout, int retryCount);

    public void imageSearchResultPage(ImageSearchParams params, final ProductCat.ResultListener resultListener);

    public void similarImageSearch(SimilarImageSearchParams params, final ProductCat.ResultListener resultListener);

    public void getPrivacyPolicyStatus(String uid, final ProductCat.ResultListener resultListener);

    public void updatePrivacyPolicyStatus(boolean opt_in, String email, final ProductCat.ResultListener resultListener);

}
