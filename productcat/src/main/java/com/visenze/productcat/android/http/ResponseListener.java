package com.visenze.productcat.android.http;

import com.android.volley.Response;
import com.visenze.productcat.android.ProductCat;
import com.visenze.productcat.android.model.ResultList;
import com.visenze.productcat.android.TrackParams;
import com.visenze.productcat.android.ProductCatException;
import com.visenze.productcat.android.api.impl.TrackOperationsImpl;
import com.visenze.productcat.android.util.ResponseParser;

import org.json.JSONObject;

/**
 * Response listener
 */
public class ResponseListener implements Response.Listener<JSONObject> {
    private ProductCat.ResultListener resultListener;
    private TrackOperationsImpl trackOperations;
    private String type;

    public ResponseListener(ProductCat.ResultListener resultListener,
                            TrackOperationsImpl trackOperations,
                            String type) {
        this.resultListener = resultListener;
        this.trackOperations = trackOperations;
        this.type = type;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        if (null != resultListener) {
            try {
                ResultList resultList = getResult(jsonObject.toString());
                if (resultList.getErrorMessage() != null)
                    resultListener.onSearchError(resultList.getErrorMessage());
                else {
                    trackOperations.track(new TrackParams().setAction(type).setReqid(resultList.getReqid()));
                    resultListener.onSearchResult(getResult(jsonObject.toString()));
                }
            } catch (ProductCatException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * pass the json response to result list
     *
     * @param jsonResponse json response
     * @return result list
     */
    private ResultList getResult(String jsonResponse) {
        ResultList resultList = null;

        resultList = ResponseParser.parseResult(jsonResponse);

        return resultList;
    }
}
