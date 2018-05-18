package com.visenze.productcat.android.api;

import com.visenze.productcat.android.ImageSearchParams;
import com.visenze.productcat.android.ProductCat;
import com.visenze.productcat.android.TextSearchParams;


/**
 * Search operations interface.
 */
public interface SearchOperations {
    public void imageSearch(ImageSearchParams params, final ProductCat.ResultListener resultListener);

    public void textSearch(TextSearchParams params, ProductCat.ResultListener mListener);
}
