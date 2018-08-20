package com.visenze.productcat.android.http;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.visenze.productcat.android.util.ProductCatUIDManager;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by visenze on 29/2/16.
 */
public class JsonWithUUIDRequest extends JsonObjectRequest {

    public JsonWithUUIDRequest(int method, String url, JSONObject jsonRequest,
                               Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {

        super(method, url, jsonRequest, listener, errorListener);
        setRetryPolicy(new DefaultRetryPolicy(HttpInstance.TIME_OUT_FOR_ID, 0, 1));
    }

    public JsonWithUUIDRequest(String url, JSONObject jsonRequest,
                               Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(url, jsonRequest, listener, errorListener);
    }

    //set auth information in header
    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> header = new HashMap<>();
        String uid = ProductCatUIDManager.getUid();
        if (uid != null) {
            header.put("Cookie", "uid=" + uid);
        }

        return header;
    }
}
