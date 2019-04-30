package com.visenze.productcat.android.http;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.visenze.productcat.android.ProductCat;

public class StoreResponseErrorListener implements Response.ErrorListener  {
    private ProductCat.StoreResultListener productCatResultListener;

    public StoreResponseErrorListener(ProductCat.StoreResultListener productCatResultListener) {
        this.productCatResultListener = productCatResultListener;
    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {
        volleyError.printStackTrace();
        if (null != productCatResultListener)
            productCatResultListener.onError("Network Error");
    }
}
