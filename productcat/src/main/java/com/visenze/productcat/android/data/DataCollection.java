package com.visenze.productcat.android.data;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;

import androidx.core.app.ActivityCompat;

import com.visenze.productcat.android.model.DeviceInfo;
import com.visenze.productcat.android.util.SecurityHelper;

public class DataCollection implements GetGAIDTask.OnTaskSuccess {

    private GetGAIDTask gaidTask;

    private String gaid;
    private String didmd5;

    public DataCollection(Context context) {

        generateHashedIMEI(context);

        gaidTask = new GetGAIDTask(context, this);
        gaidTask.execute();


    }

    private void generateHashedIMEI(Context context) {
        String imei = getIMEI(context);
        didmd5 = SecurityHelper.MD5(imei);
    }

    private String getIMEI(Context context) {
        String deviceUniqueIdentifier = null;
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (null != tm) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                try {
                    deviceUniqueIdentifier = tm.getDeviceId();
                } catch (Exception e) {
                    e.printStackTrace();
                    return deviceUniqueIdentifier;
                }
            }
        }
        return deviceUniqueIdentifier;
    }



    @Override
    public void onSuccess(String gaid) {
        this.gaid = gaid;
    }

    public DeviceInfo getDeviceInfo(boolean track) {
       DeviceInfo info = new DeviceInfo();
       info.setTrack(track);

       if(gaid != null) {
           info.setIfa(gaid);
       }

       if(didmd5 != null) {
           info.setDidmd5(didmd5);
       }

       return info;
    }

}
