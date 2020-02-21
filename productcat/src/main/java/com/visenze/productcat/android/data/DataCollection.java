package com.visenze.productcat.android.data;

import android.content.Context;

import com.visenze.productcat.android.model.DeviceInfo;

import java.util.UUID;

public class DataCollection implements GetGAIDTask.OnTaskSuccess {

    private GetGAIDTask gaidTask;

    private String gaid;

    public DataCollection(Context context) {
        gaidTask = new GetGAIDTask(context, this);
        gaidTask.execute();
    }

    @Override
    public void onSuccess(String gaid) {
        this.gaid = gaid;
    }

    public DeviceInfo getDeviceInfo() {
       DeviceInfo info = new DeviceInfo();
       if(gaid != null) {
           info.setIfa(gaid);
       }

       return info;
    }

}
