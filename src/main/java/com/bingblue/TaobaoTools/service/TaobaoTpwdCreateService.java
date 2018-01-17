/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bingblue.TaobaoTools.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * 生成淘口令
 *
 * @author SayMing
 */
@Service()
public class TaobaoTpwdCreateService {

    private Logger logger = Logger.getLogger(TaobaoTpwdCreateService.class);
//    private final TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);

    public String create(String ext, String logoUrl, String url, String text, Long userId) {
        logger.info("ext ====> " + ext);
        logger.info("logoUrl ====> " + logoUrl);
        logger.info("url ====> " + url);
        logger.info("text ====> " + text);
        logger.info("userId ====> " + userId);
        String result = "￥TestAADPOKFzTest￥";

//        WirelessShareTpwdCreateRequest req = new WirelessShareTpwdCreateRequest();
//        GenPwdIsvParamDto obj1 = new GenPwdIsvParamDto();
//        obj1.setExt("{\"xx\":\"xx\"}");
//        obj1.setLogo("http://m.taobao.com/xxx.jpg");
//        obj1.setUrl("http://m.taobao.com");
//        obj1.setText("超值活动，惊喜活动多多");
//        obj1.setUserId(24234234234L);
//        req.setTpwdParam(obj1);
//        WirelessShareTpwdCreateResponse rsp = client.execute(req);
//        System.out.println(rsp.getBody());
        return result;
    }

}
