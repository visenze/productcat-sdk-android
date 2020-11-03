package com.visenze.productcat.android.http;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.visenze.productcat.android.ProductCat;

public class MPSResponseErrorListener implements Response.ErrorListener {

    private ProductCat.MPSListener productCatMPSListener;

    public MPSResponseErrorListener(ProductCat.MPSListener productCatMPSListener) {
        this.productCatMPSListener = productCatMPSListener;
    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {
        volleyError.printStackTrace();
        if (null != productCatMPSListener)
            productCatMPSListener.onSearchError("Network Error");
    }

}
