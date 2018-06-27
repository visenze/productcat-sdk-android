package com.visenze.productcat.android.model;

/**
 * Created by visenze on 27/6/18.
 */

public class FacetItem {

    private Long id;
    private String name;
    private String value;
    private Integer count;

    public String getValue() {
        return value;
    }

    public Integer getCount() {
        return count;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "FacetItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", count=" + count +
                '}';
    }
}
