/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bingblue.TaobaoTools.service;

/**
 *
 * @author SayMing
 */
public interface ITaobaoTpwdCreateService {

    /**
     * 
     * @param ext 可选 {"xx":"xx"}扩展字段JSON格式
     * @param logoUrl 可选 http://m.taobao.com/xxx.jpg口令弹框logoURL
     * @param url 必须 http://m.taobao.com口令跳转url
     * @param text 必须 超值活动，惊喜活动多多口令弹框内容
     * @param userId 可选 24234234234 默认值：0 生成口令的淘宝用户ID
     * @return ￥AADPOKFz￥	口令内容，用于口令宣传组织
     */
    public String create(String ext, String logoUrl, String url, String text, Long userId);
}
