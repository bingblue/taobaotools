/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bingblue.TaobaoTools.controller;

import com.alibaba.fastjson.JSONObject;
import com.bingblue.TaobaoTools.service.IStuckFirstScreenService;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author SayMing
 */
@Controller
@RequestMapping("/stuck")
public class StuckFirstScreenController {

    @Resource
    private IStuckFirstScreenService stuckFirstScreenService;

    @RequestMapping()
    public String toIndex() {
        return "stuck";
    }

    @RequestMapping("/mobile")
    @ResponseBody
    public String mobile(String taobaoProductId, String keywords) {
        //根据关键词爬取淘宝搜索前20条结果，并追加到taobaoProductId之后，用逗号隔开。
        
        String url = stuckFirstScreenService.generateMobileUrl(taobaoProductId, keywords);
        //需要补上淘口令生成代码。Start

        //需要补上淘口令生成代码。End
        JSONObject object = new JSONObject();
        object.put("Url", url);
        JSONObject result = Tools.success(object);

        return result.toJSONString();
    }

    @RequestMapping("/pc")
    @ResponseBody
    public String pc(String taobaoProductId, String keywords) {
        String url = stuckFirstScreenService.generatePcUrl(taobaoProductId, keywords);

        JSONObject object = new JSONObject();
        object.put("Url", url);
        JSONObject result = Tools.success(object);

        return result.toJSONString();
    }
}
