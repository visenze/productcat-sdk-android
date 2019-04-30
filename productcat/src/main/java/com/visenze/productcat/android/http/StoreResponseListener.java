package com.visenze.productcat.android.http;

import com.android.volley.Response;
import com.visenze.productcat.android.ProductCat;
import com.visenze.productcat.android.ProductCatException;
import com.visenze.productcat.android.model.ResultList;
import com.visenze.productcat.android.model.StoreResultList;
import com.visenze.productcat.android.util.ResponseParser;

import org.json.JSONObject;

public class StoreResponseListener implements Response.Listener<JSONObject> {
    private ProductCat.StoreResultListener resultListener;

    public StoreResponseListener(ProductCat.StoreResultListener resultListener) {
        this.resultListener = resultListener;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        if (resultListener == null) {
            return;
        }

        try {
            StoreResultList resultList = getResult(jsonObject.toString());
            if (resultList.getErrorMessage() != null)
                resultListener.onError(resultList.getErrorMessage());
            else {
                resultListener.onResult(resultList);
            }
        } catch (ProductCatException e) {
            e.printStackTrace();
        }
    }

    /**
     * pass the json response to result list
     *
     * @param jsonResponse json response
     * @return result list
     */
    private StoreResultList getResult(String jsonResponse) {
        return ResponseParser.parseStoresResult(jsonResponse);
    }
}
