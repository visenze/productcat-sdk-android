package com.visenze.productcat.android.http;

import com.android.volley.Response;
import com.visenze.productcat.android.ProductCat;
import com.visenze.productcat.android.ProductCatException;
import com.visenze.productcat.android.model.ObjectList;
import com.visenze.productcat.android.model.ResultList;
import com.visenze.productcat.android.util.ResponseParser;

import org.json.JSONObject;

import java.util.List;

public class MPSResponseListener implements Response.Listener<JSONObject> {


    private ProductCat.MPSListener mpsListener;
    private String type;

    public MPSResponseListener(ProductCat.MPSListener mpsListener, String type) {
        this.mpsListener = mpsListener;
        this.type = type;
    }
    @Override
    public void onResponse(JSONObject jsonObject) {
        if (this.mpsListener == null) {
            return;
        }

        try {
            String errMsg = ResponseParser.parseResponseError(jsonObject);
            if(errMsg != null) {
                mpsListener.onSearchError(errMsg);
            } else {
                mpsListener.onSearchResult(ResponseParser.parseMPSResponse(jsonObject));
            }

        } catch (ProductCatException e) {
            e.printStackTrace();
        }
    }


}
