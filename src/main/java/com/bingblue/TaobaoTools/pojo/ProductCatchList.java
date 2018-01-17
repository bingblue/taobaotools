package com.bingblue.TaobaoTools.pojo;

import java.util.Date;

public class ProductCatchList {
    private Integer id;

    private String keywords;

    private Date happendate;

    private String productids;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords == null ? null : keywords.trim();
    }

    public Date getHappendate() {
        return happendate;
    }

    public void setHappendate(Date happendate) {
        this.happendate = happendate;
    }

    public String getProductids() {
        return productids;
    }

    public void setProductids(String productids) {
        this.productids = productids == null ? null : productids.trim();
    }
}