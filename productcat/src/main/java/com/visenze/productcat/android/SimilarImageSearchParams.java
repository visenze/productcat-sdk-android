package com.visenze.productcat.android;

public class SimilarImageSearchParams extends SearchParams {

    private String pid;

    public SimilarImageSearchParams(String pid, String country) {
        this.pid = pid;
        this.setCountry(country);
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

}
