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
public interface IStuckFirstScreenService {
    
    /**
     * 根据淘宝商品Id和关键词生成手机APP卡首屏最终URL。
     * @param taobaoProductId
     * @param keywords
     * @return 
     */
    public String generateMobileUrl(String taobaoProductId, String keywords);
    
    /**
     * 根据淘宝商品Id和关键词生成PC-WEB卡首屏最终URL。
     * @param taobaoProductId
     * @param keywords
     * @return 
     */
    public String generatePcUrl(String taobaoProductId, String keywords);
}
