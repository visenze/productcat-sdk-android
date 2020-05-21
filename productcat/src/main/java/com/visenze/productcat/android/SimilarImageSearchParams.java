package com.visenze.productcat.android;

import java.util.List;
import java.util.Map;

public class SimilarImageSearchParams extends SearchParams {


    public static final String CID = "cid";
    public static final String REQ_ID = "reqid";


    private String pid;
    private String cid;
    private String reqid;



    public SimilarImageSearchParams(String pid, String country, String cid) {
        this.pid = pid;
        this.cid = cid;
        this.setCountry(country);
    }

    public void setReqId(String reqid) {
        this.reqid = reqid;
    }

    public void setSource(String source) {
        this.getBaseSearchParams().setSource(source);
    }

    public void setLimit(int limit) {
        this.getBaseSearchParams().setLimit(limit);
    }


    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    @Override
    public Map<String, List<String>> toMap() {
        Map<String, List<String>> map = super.toMap();
        if(cid!=null){
            this.putStringInMap(map, CID, cid);
        }
        if(reqid != null) {
            this.putStringInMap(map, REQ_ID, reqid);
        }
        return map;
    }


}
