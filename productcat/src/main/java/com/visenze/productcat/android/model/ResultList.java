package com.visenze.productcat.android.model;

import com.visenze.productcat.android.ProductCat;

import java.util.ArrayList;
import java.util.List;

/**
 * The ResultList class represents a successful search result
 * In practice you will never need to initialise a Result by yourself. Instead you should implement
 * {@link ProductCat.ResultListener ResultListener} to get the returned result from a search session.
 */
public class ResultList {

    private String errorMessage;

    private String reqid;

    private List<ProductSummary> productSummaryList;

    private List<ProductType> productTypes;

    private List<TagGroup> tagGroups = new ArrayList<TagGroup>();

    private String imId;

    private List<Facet> facets;

    private String clientReqId;

    private String countryFilter;

    public ResultList() {
        productSummaryList = new ArrayList<ProductSummary>();
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    /**
     * Get the list of Image {@link ProductSummary ProductSummary}
     *
     * @return image list.
     */
    public List<ProductSummary> getProductSummaryList() {
        return productSummaryList;
    }


    /**
     * Get the list of Product Type (detection result) {@link ProductType}
     *
     * @return product type list
     */
    public List<ProductType> getProductTypes() {
        return productTypes;
    }


    /**
     * Get the trans id of the search
     * @return trans id
     */
    public String getReqid() {
        return reqid;
    }

    /**
     * Get im id for uploadsearch
     * @return imId
     */
    public String getImId() {
        return imId;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setProductSummaryList(List<ProductSummary> productSummary) {
        this.productSummaryList = productSummary;
    }

    public void setProductTypes(List<ProductType> productTypes) {
        this.productTypes = productTypes;
    }

    public void setReqid(String reqid) {
        this.reqid = reqid;
    }

    public void setImId(String imId) {
        this.imId = imId;
    }

    public List<TagGroup> getTagGroups() {
        return tagGroups;
    }

    public void setTagGroups(List<TagGroup> tagGroups) {
        this.tagGroups = tagGroups;
    }

    public List<Facet> getFacets() {
        return facets;
    }

    public void setFacets(List<Facet> facets) {
        this.facets = facets;
    }

    public boolean hasNoResults() {
        return productSummaryList == null || productSummaryList.size() == 0;
    }

    public String getClientReqId() {
        return clientReqId;
    }

    public void setClientReqId(String clientReqId) {
        this.clientReqId = clientReqId;
    }

    public String getCountryFilter() {
        return countryFilter;
    }

    public void setCountryFilter(String countryFilter) {
        this.countryFilter = countryFilter;
    }
}
