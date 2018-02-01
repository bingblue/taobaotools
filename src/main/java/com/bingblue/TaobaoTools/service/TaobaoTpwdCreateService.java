/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bingblue.TaobaoTools.service;

import com.bingblue.TaobaoTools.dao.TaobaoWordDao;
import com.bingblue.TaobaoTools.pojo.TaobaoWord;
import com.bingblue.TaobaoTools.pojo.TaobaoWordType;
import java.util.Calendar;
import java.util.Date;
import javax.annotation.Resource;
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
    
    @Resource
    private TaobaoWordDao taobaoWordDao;
    
    public TaobaoWord create(TaobaoWord taobaoWord) {
        logger.info("ext ====> " + taobaoWord.getExt());
        logger.info("logoUrl ====> " + taobaoWord.getLogoUrl());
        logger.info("url ====> " + taobaoWord.getUrl());
        logger.info("text ====> " + taobaoWord.getText());
        if(taobaoWord.getId() == null || taobaoWord.getId() == 0L){
            taobaoWordDao.insert(taobaoWord);
        }
        //跟淘宝sdk生成中。
        taobaoWord.setStatus(TaobaoWordType.CREATING.toString());
        taobaoWordDao.update(taobaoWord);
        
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
        
        //生成完毕后。
        taobaoWord.setTpwd("￥TestAADPOKFzTest￥");
        taobaoWord.setStatus(TaobaoWordType.OVER.toString());//成功
        Calendar nowDate = Calendar.getInstance();
        nowDate.setTime(new Date());
        nowDate.add(Calendar.DATE, 30);
        taobaoWord.setFailureDate(nowDate.getTime());//设置失效时间
        taobaoWordDao.update(taobaoWord);
        
        return taobaoWord;
    }

}
