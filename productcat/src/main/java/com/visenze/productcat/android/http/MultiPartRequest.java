package com.visenze.productcat.android.http;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;

import org.apache.http.HttpEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MultiPartRequest extends Request<JSONObject> {

    private static final String FILE_PART_NAME = "image";

    private final Response.Listener<JSONObject> mListener;
    private String                              appKey;
    private HttpEntity                          entity;
    private String                              userAgent;


    public MultiPartRequest(int method, String url,
                            Map<String, List<String>> params, byte[] bytes,
                            String appKey, String userAgent,
                            Response.Listener<JSONObject> mListener,
                            Response.ErrorListener listener) {

        super(method, url, listener);
        this.mListener = mListener;
        this.appKey = appKey;
        this.userAgent = userAgent;

        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        for (Map.Entry<String, List<String> > entry : params.entrySet()) {
            for (String s : entry.getValue()) {
                builder.addTextBody(entry.getKey(), s);
            }
        }

        if(bytes!=null) {
            ByteArrayBody byteArrayBody = new ByteArrayBody(bytes, FILE_PART_NAME);
            builder.addPart(FILE_PART_NAME, byteArrayBody);
        }

        entity = builder.build();

        //retry policy for upload multipart, set retry number as 1
        setRetryPolicy(new DefaultRetryPolicy(HttpInstance.TIME_OUT_FOR_UPLOAD, 1, 1));
    }

    @Override
    public String getBodyContentType() {
        return entity.getContentType().getValue();
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> params = new HashMap<>();
        //add request header
        params.put("X-Requested-With", userAgent);
        return params;
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            entity.writeTo(bos);
        } catch (IOException e) {
            VolleyLog.e("IOException writing to ByteArrayOutputStream");
        }

        return bos.toByteArray();
    }


    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            String utf8String =
                    new String(response.data, HTTP.UTF_8);
            JSONObject result = new JSONObject(utf8String);

            return Response.success(result,
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }

    @Override
    protected void deliverResponse(JSONObject jsonObject) {
        mListener.onResponse(jsonObject);
    }
}
