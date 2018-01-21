/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bingblue.TaobaoTools.service;

import org.springframework.stereotype.Service;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;

/**
 *
 * @author SayMing
 */
@Service()
public class StuckFirstScreenService{

    private Logger logger = Logger.getLogger(StuckFirstScreenService.class);
    
    //https://s.m.taobao.com/h5?q=洗发水&nid=529164550758
    private static final String MOBILE_BASE_URL = "https://s.m.taobao.com/h5?nid=%s&q=%s";
    //https://s.taobao.com/search?q=洗发水&nid_up=529164550758&openP4P=true
    private static final String PC_BASE_URL = "https://s.taobao.com/search?nid_up=%s&q=%s&openP4P=true";
    //编码格式
    private static final String TAOBAO_CHARACTER_ENCODING = "UTF-8";
    @Resource
    private SearchProductByKeywordsTaobaoService crawlTaobaoService;

    public String generateMobileUrl(String taobaoProductId, String keywords) {
        //根据关键词爬取淘宝搜索前20条结果，并追加到taobaoProductId之后，用逗号隔开。
        List<String> productIds = crawlTaobaoService.crawl(keywords);

        StringBuilder sb = new StringBuilder(taobaoProductId);
        for (String pid : productIds) {
            sb.append(",").append(pid);
        }
        
        String url = null;
        try {
            url = String.format(MOBILE_BASE_URL, sb.toString(), URLEncoder.encode(keywords, TAOBAO_CHARACTER_ENCODING));
        } catch (UnsupportedEncodingException ex) {
            logger.error(ex);
        }
        return url;
    }

    public String generatePcUrl(String taobaoProductId, String keywords) {
        String url = null;
        try {
            url = String.format(PC_BASE_URL, taobaoProductId, URLEncoder.encode(keywords, TAOBAO_CHARACTER_ENCODING));
        } catch (UnsupportedEncodingException ex) {
            logger.error(ex);
        }
        return url;
    }
    
    /**
     * 从url中获取宝贝Id
     * https://item.taobao.com/item.htm?spm=a230r.1.14.36.b4b75aep8p04W&id=548798597452&ns=1&abbucket=15#detail
     * @param url
     * @return 
     */
    public static final String findProductIdFromUrl(String url){
        int idStartIndex = url.indexOf("&id=");
        if(idStartIndex >= 0){
            int idEndIndex = url.indexOf("&", idStartIndex+1);
            if(idEndIndex >= 0){
                return url.substring(idStartIndex + 4, idEndIndex);
            }else{
                return null;
            }
        }else{
            return null;
        }
    }

}
