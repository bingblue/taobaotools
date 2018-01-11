/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bingblue.TaobaoTools.service.impl;

import org.springframework.stereotype.Service;
import com.bingblue.TaobaoTools.service.IStuckFirstScreenService;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SayMing
 */
@Service("stuckFirstScreenService")
public class StuckFirstScreenServiceImpl implements IStuckFirstScreenService {

    //https://s.m.taobao.com/h5?q=洗发水&nid=529164550758
    private static final String MOBILE_BASE_URL = "https://s.m.taobao.com/h5?nid=%s&q=%s";
    //https://s.taobao.com/search?q=洗发水&nid_up=529164550758&openP4P=true
    private static final String PC_BASE_URL = "https://s.taobao.com/search?nid_up=%s&q=%s&openP4P=true";
    //编码格式
    private static final String TAOBAO_CHARACTER_ENCODING = "UTF-8";

    @Override
    public String generateMobileUrl(String taobaoProductId, String keywords) {
        String url = null;
        try {
            url = String.format(MOBILE_BASE_URL, taobaoProductId, URLEncoder.encode(keywords, TAOBAO_CHARACTER_ENCODING));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(StuckFirstScreenServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return url;
    }

    @Override
    public String generatePcUrl(String taobaoProductId, String keywords) {
        String url = null;
        try {
            url = String.format(PC_BASE_URL, taobaoProductId, URLEncoder.encode(keywords, TAOBAO_CHARACTER_ENCODING));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(StuckFirstScreenServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return url;
    }

}
