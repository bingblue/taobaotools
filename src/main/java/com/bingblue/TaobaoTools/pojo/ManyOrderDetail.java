package com.bingblue.TaobaoTools.pojo;

public class ManyOrderDetail {
    private Integer id;

    private Integer headId;

    private String keywords;

    private Integer limitClickQuantity;

    private Integer clickQuantity;

    private Integer clickCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHeadId() {
        return headId;
    }

    public void setHeadId(Integer headId) {
        this.headId = headId;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords == null ? null : keywords.trim();
    }

    public Integer getLimitClickQuantity() {
        return limitClickQuantity;
    }

    public void setLimitClickQuantity(Integer limitClickQuantity) {
        this.limitClickQuantity = limitClickQuantity;
    }

    public Integer getClickQuantity() {
        return clickQuantity;
    }

    public void setClickQuantity(Integer clickQuantity) {
        this.clickQuantity = clickQuantity;
    }

    public Integer getClickCount() {
        return clickCount;
    }

    public void setClickCount(Integer clickCount) {
        this.clickCount = clickCount;
    }
}