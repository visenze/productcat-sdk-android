package com.visenze.productcat.android.http;

import com.android.volley.Response;
import com.visenze.productcat.android.ProductCat;
import com.visenze.productcat.android.api.impl.SearchOperationsImpl;
import com.visenze.productcat.android.model.ResultList;
import com.visenze.productcat.android.ProductCatException;
import com.visenze.productcat.android.util.ResponseParser;

import org.json.JSONObject;

/**
 * Response listener
 */
public class ResponseListener implements Response.Listener<JSONObject> {
    private ProductCat.ResultListener resultListener;
    private String type;

    public ResponseListener(ProductCat.ResultListener resultListener,
                            String type) {
        this.resultListener = resultListener;
        this.type = type;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        if (resultListener == null) {
            return;
        }

        try {
            ResultList resultList = getResult(jsonObject.toString());
            if (resultList.getErrorMessage() != null)
                resultListener.onSearchError(resultList.getErrorMessage());
            else {
                resultListener.onSearchResult(resultList);
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
    private ResultList getResult(String jsonResponse) {
        return ResponseParser.parseResult(jsonResponse, type);

    }


}
