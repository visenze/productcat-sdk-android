package com.visenze.productcat.android.data;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;

import androidx.core.app.ActivityCompat;

import com.visenze.productcat.android.model.DeviceInfo;

import java.io.UnsupportedEncodingException;

public class DataCollection implements GetGAIDTask.OnTaskSuccess {

    private GetGAIDTask gaidTask;

    private String gaid;
    private String didmd5;

    public DataCollection(Context context) {

        generateDoubleHashIMEI(context);

        gaidTask = new GetGAIDTask(context, this);
        gaidTask.execute();


    }

    private void generateDoubleHashIMEI(Context context) {
        String imei = getIMEI(context);
        didmd5 = MD5(imei);
        didmd5 = MD5(didmd5);
    }

    private String getIMEI(Context context) {
        String deviceUniqueIdentifier = null;
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (null != tm) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                deviceUniqueIdentifier = tm.getDeviceId();
            }
        }
        return deviceUniqueIdentifier;
    }

    private String MD5(String md5) {
        if(md5 == null) {
            return null;
        }
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes("UTF-8"));
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch(UnsupportedEncodingException ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void onSuccess(String gaid) {
        this.gaid = gaid;
    }

    public DeviceInfo getDeviceInfo(boolean track) {
       DeviceInfo info = new DeviceInfo();
       info.setDoNotTrack(track);

       if(gaid != null) {
           info.setIfa(gaid);
       }

       if(didmd5 != null) {
           info.setDidmd5(didmd5);
       }

       return info;
    }

}
