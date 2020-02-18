package com.visenze.productcat.android;

import android.content.Context;
import android.os.AsyncTask;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;

import java.io.IOException;

public class GetGAIDTask extends AsyncTask<Void, Void, String> {


    private final OnTaskSuccess mListener;
    private Context mContext;

    GetGAIDTask(Context context, OnTaskSuccess listener) {
        mContext = context;
        mListener = listener;
    }

    @Override
    protected String doInBackground(Void... params) {
        AdvertisingIdClient.Info idInfo = null;

        try {
            idInfo = AdvertisingIdClient.getAdvertisingIdInfo(mContext.getApplicationContext());
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        }

        String gaid = null;

        try {
            gaid = idInfo.getId();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return gaid;
    }

    @Override
    protected void onPostExecute(String gaid) {
        if (mListener != null) {
            mListener.onSuccess(gaid);
        }
    }

    public interface OnTaskSuccess {
        void onSuccess(String gaid);
    }

}

