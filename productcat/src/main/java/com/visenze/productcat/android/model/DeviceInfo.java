package com.visenze.productcat.android.model;

public class DeviceInfo {
    private String uip;
    private String ua;
    private String deviceModel;
    private String os;
    private String osv;
    private String didmd5;
    private String ifa;
    private String latLng;
    private String uc;
    private String doNotTrack;

    public DeviceInfo() {
    }


    public String getUip() {
        return uip;
    }

    public void setUip(String uip) {
        this.uip = uip;
    }

    public String getUa() {
        return ua;
    }

    public void setUa(String ua) {
        this.ua = ua;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getOsv() {
        return osv;
    }

    public void setOsv(String osv) {
        this.osv = osv;
    }

    public String getDidmd5() {
        return didmd5;
    }

    public void setDidmd5(String didmd5) {
        this.didmd5 = didmd5;
    }

    public String getIfa() {
        return ifa;
    }

    public void setIfa(String ifa) {
        this.ifa = ifa;
    }

    public String getLatLng() {
        return latLng;
    }

    public void setLatLng(String latLng) {
        this.latLng = latLng;
    }

    public String getUc() {
        return uc;
    }

    public void setUc(String uc) {
        this.uc = uc;
    }

    public void setDoNotTrack(boolean track) {
        if(track) {
            doNotTrack = "no";
        } else {
            doNotTrack = "yes";
        }
    }

    public String getDoNotTrack() {
        return doNotTrack;
    }
}
