package com.visenze.productcat.android.model;

import java.util.List;
import java.util.Map;

public class ObjectList {

    private String reqid;

    private String type;
    private Double score;
    private Box box;
    private Map attributeList;

    private List<ProductSummary> productSummaryList;


    public void setReqid(String reqid) {
        this.reqid = reqid;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public void setBox(Box box) {
        this.box = box;
    }

    public void setAttributeList(Map attributeList) {
        this.attributeList = attributeList;
    }

    public void setProductSummaryList(List<ProductSummary> productSummaryList) {
        this.productSummaryList = productSummaryList;
    }

    public String getReqid() {
        return reqid;
    }

    public String getType() {
        return type;
    }

    public Double getScore() {
        return score;
    }

    public Box getBox() {
        return box;
    }

    public Map getAttributeList() {
        return attributeList;
    }

    public List<ProductSummary> getProductSummaryList() {
        return productSummaryList;
    }


}
