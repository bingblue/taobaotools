/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bingblue.TaobaoTools.service;

import java.util.Map;

/**
 *
 * @author SayMing
 * @param <T> 返回的结果
 */
public interface ICrawlTaobaoService<T> {
    
    public T crawl(T result, Map params);
    
}
