package com.visenze.productcat.android;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by visenze on 26/2/16.
 */
public class TrackParams {
    private String cuid;
    private String reqid;
    private String pid;
    private String action;

    private String cid;

    public TrackParams() {

    }

    public String getCuid() {
        return cuid;
    }

    public TrackParams setCuid(String cuid) {
        this.cuid = cuid;
        return this;
    }

    public String getCid() {
        return cid;
    }

    public String getReqid() {
        return reqid;
    }

    public TrackParams setReqid(String reqid) {
        this.reqid = reqid;
        return this;
    }

    public String getPid() {
        return pid;
    }

    public TrackParams setPid(String pid) {
        this.pid = pid;
        return this;
    }

    public String getAction() {
        return action;
    }

    public TrackParams setAction(String action) {
        this.action = action;
        return this;
    }

    public Map<String, String> toMap(String accessKey) {
        Map<String, String> map = new HashMap<>();
        map.put("cid", accessKey);

        if (cuid != null) {
            map.put("cuid", cuid);
        }

        if (reqid != null) {
            map.put("reqid", reqid);
        }

        if (pid != null) {
            map.put("pid", pid);
        }

        if (action != null) {
            map.put("action", action);
        }

        return map;
    }
}
