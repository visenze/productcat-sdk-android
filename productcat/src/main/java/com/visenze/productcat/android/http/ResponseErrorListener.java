package com.visenze.productcat.android.http;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.visenze.productcat.android.ProductCat;

public class ResponseErrorListener implements Response.ErrorListener {
    private ProductCat.ResultListener productCatResultListener;

    public ResponseErrorListener(ProductCat.ResultListener productCatResultListener) {
        this.productCatResultListener = productCatResultListener;
    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {
        volleyError.printStackTrace();
        if (null != productCatResultListener)
            productCatResultListener.onSearchError("Network Error");
    }
}
