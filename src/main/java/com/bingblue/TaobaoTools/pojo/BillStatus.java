/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bingblue.TaobaoTools.pojo;

/**
 *
 * @author SayMing
 */
public enum BillStatus {
    
    STAY_CREATE("待生成"), CREATING("生成中"), OVER("已生成"), FAILURE("生成失败");
    
    BillStatus(String type) {
        this.type = type;
    }

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
