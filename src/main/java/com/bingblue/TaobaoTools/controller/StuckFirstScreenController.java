/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bingblue.TaobaoTools.controller;

import com.alibaba.fastjson.JSONObject;
import com.bingblue.TaobaoTools.service.SearchProductByKeywordsTaobaoService;
import com.bingblue.TaobaoTools.service.StuckFirstScreenService;
import com.bingblue.TaobaoTools.service.TaobaoProductService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
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

    private Logger logger = Logger.getLogger(StuckFirstScreenController.class);

    @Resource
    private StuckFirstScreenService stuckFirstScreenService;

    @Resource
    private SearchProductByKeywordsTaobaoService crawlTaobaoService;
    
    @Resource
    private TaobaoProductService taobaoProductService;

    @Resource
    private TaobaoTpwdCreateController taobaoTpwdCreateController;

    @RequestMapping()
    public String toIndex() {
        return "stuck";
    }

    /**
     * 卡首屏 手机端
     *
     * @param taobaoProductId 必填
     * @param keywords 必填
     * @param logoUrl
     * @param productTitle 必填
     * @return JSON 包含淘口令
     */
    @RequestMapping(value = "/mobile", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String mobile(String taobaoProductId, String keywords, String logoUrl, String productTitle) {
        //验证
        if(taobaoProductId == null || "".equals(taobaoProductId)){
            return Tools.error("商品链接地址不能为空。").toString();
        }
        if(keywords == null || "".equals(keywords)){
            return Tools.error("搜索的关键字不能为空。").toString();
        }
        if(productTitle == null || "".equals(productTitle)){
            return Tools.error("口令提示内容不能为空。").toString();
        }
        
        //根据关键词爬取淘宝搜索前20条结果，并追加到taobaoProductId之后，用逗号隔开。
        List<String> productIds = crawlTaobaoService.crawl(keywords);
        

        StringBuilder sb = new StringBuilder(taobaoProductId);
        for (String pid : productIds) {
            sb.append(",").append(pid);
        }

        String url = stuckFirstScreenService.generateMobileUrl(sb.toString(), keywords);
        //需要补上淘口令生成代码。Start
        return taobaoTpwdCreateController.createTpwd(url, logoUrl, productTitle);
        //需要补上淘口令生成代码。End

    }

    /**
     * 卡首屏 PC端
     *
     * @param taobaoProductId
     * @param keywords
     * @return
     */
    @RequestMapping(value = "/pc", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String pc(String taobaoProductId, String keywords) {
        //验证
        if(taobaoProductId == null || "".equals(taobaoProductId)){
            return Tools.error("商品链接地址不能为空。").toString();
        }
        if(keywords == null || "".equals(keywords)){
            return Tools.error("搜索的关键字不能为空。").toString();
        }
        
        String url = stuckFirstScreenService.generatePcUrl(taobaoProductId, keywords);

        JSONObject object = new JSONObject();
        object.put("Url", url);
        JSONObject result = Tools.success(object);

        return result.toJSONString();
    }

    /**
     *
     * @param competitorProductId1 必填 同行商品1
     * @param competitorProductId2 必填 同行商品2
     * @param competitorProductId3 必填 同行商品3
     * @param taobaoProductId 必填 自己商品
     * @param keywords 必填
     * @param logoUrl
     * @param productTitle 必填
     * @return JSON 包含淘口令
     */
    @RequestMapping(value = "/competitor", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String competitor(String competitorProductId1, String competitorProductId2, String competitorProductId3,
            String taobaoProductId, String keywords, String logoUrl, String productTitle) {
        //验证
        if(competitorProductId1 == null || "".equals(competitorProductId1)){
            return Tools.error("同行链接地址1不能为空。").toString();
        }
        if(competitorProductId2 == null || "".equals(competitorProductId2)){
            return Tools.error("同行链接地址2不能为空。").toString();
        }
        if(competitorProductId3 == null || "".equals(competitorProductId3)){
            return Tools.error("同行链接地址3不能为空。").toString();
        }
        if(taobaoProductId == null || "".equals(taobaoProductId)){
            return Tools.error("商品链接地址不能为空。").toString();
        }
        if(keywords == null || "".equals(keywords)){
            return Tools.error("搜索的关键字不能为空。").toString();
        }
        if(productTitle == null || "".equals(productTitle)){
            return Tools.error("口令提示内容不能为空。").toString();
        }
        
        //根据关键词爬取淘宝搜索前20条结果，并追加到taobaoProductId之后，用逗号隔开。
        List<String> productIds = crawlTaobaoService.crawl(keywords);
        

        StringBuilder sb = new StringBuilder(taobaoProductId);
        sb.append(",").append(competitorProductId1).append(",")
                .append(competitorProductId2).append(",")
                .append(competitorProductId3);

        int i = 0;
        for (String pid : productIds) {
            if (i >= 20) {
                break;
            }
            sb.append(",").append(pid);
            i++;
        }

        String url = stuckFirstScreenService.generateMobileUrl(sb.toString(), keywords);
        //需要补上淘口令生成代码。Start
        return taobaoTpwdCreateController.createTpwd(url, logoUrl, productTitle);
        //需要补上淘口令生成代码。End
    }
}
