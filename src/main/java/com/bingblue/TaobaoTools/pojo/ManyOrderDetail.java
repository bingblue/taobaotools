package com.bingblue.TaobaoTools.pojo;

public class ManyOrderDetail {
    private Integer id;

    private Integer headid;

    private String keywords;

    private Integer limitclickquantity;

    private Integer clickquantity;

    private Integer clickcount;

    private String producturl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHeadid() {
        return headid;
    }

    public void setHeadid(Integer headid) {
        this.headid = headid;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords == null ? null : keywords.trim();
    }

    public Integer getLimitclickquantity() {
        return limitclickquantity;
    }

    public void setLimitclickquantity(Integer limitclickquantity) {
        this.limitclickquantity = limitclickquantity;
    }

    public Integer getClickquantity() {
        return clickquantity;
    }

    public void setClickquantity(Integer clickquantity) {
        this.clickquantity = clickquantity;
    }

    public Integer getClickcount() {
        return clickcount;
    }

    public void setClickcount(Integer clickcount) {
        this.clickcount = clickcount;
    }

    public String getProducturl() {
        return producturl;
    }

    public void setProducturl(String producturl) {
        this.producturl = producturl == null ? null : producturl.trim();
    }
}