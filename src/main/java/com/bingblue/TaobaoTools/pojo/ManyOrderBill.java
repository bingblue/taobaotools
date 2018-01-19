package com.bingblue.TaobaoTools.pojo;

import java.util.Date;
import java.util.List;

public class ManyOrderBill {

    private Integer id;

    private Integer userId;

    private String shortLink;

    private Date createDate;

    private String remark;

    private String productUrl;

    private List<ManyOrderDetail> manyOrderDetails;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getShortLink() {
        return shortLink;
    }

    public void setShortLink(String shortLink) {
        this.shortLink = shortLink == null ? null : shortLink.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl == null ? null : productUrl.trim();
    }

    public List<ManyOrderDetail> getManyOrderDetails() {
        return manyOrderDetails;
    }

    public void setManyOrderDetails(List<ManyOrderDetail> manyOrderDetails) {
        this.manyOrderDetails = manyOrderDetails;
    }

}
