/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bingblue.TaobaoTools.controller.user;

import com.alibaba.fastjson.JSONObject;
import com.bingblue.TaobaoTools.controller.Tools;
import com.bingblue.TaobaoTools.pojo.AnalyzeStoreBill;
import com.bingblue.TaobaoTools.service.AnalyzeStoreService;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author pdmilk
 */
@Controller
@RequestMapping("/user/analyzeStore")
public class AnalyzeStoreController {
    
    private Logger logger = Logger.getLogger(AnalyzeStoreController.class);
    
    @Resource
    private AnalyzeStoreService analyzeStoreService;
    
    @RequestMapping()
    public String toIndex() {
        return "/user/analyzeStore";
    }
    
    /**
     * 添加店铺分析订单
     * @param shopUrl 店铺URL
     * @return 结果，成功-订单ID，错误-错误信息。
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String addOne(@RequestParam(value = "shopUrl") String shopUrl){
        if(shopUrl == null || shopUrl.isEmpty()){
            return Tools.error("店铺链接不能为空！").toString();
        }
        Long billId = analyzeStoreService.receiveBill(shopUrl, 0L);
        JSONObject billJSON = new JSONObject();
        billJSON.put("billId", billId);
        return Tools.success(billJSON, "成功").toString();
    }
    
    /**
     * 接收爬虫服务发来的爬取结果。
     * @param result 结果JSON
     * @return 通知
     */
    @RequestMapping(value = "/receiveResult", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String receiveResultBill(@RequestParam(value = "resultJSON") String result){
        analyzeStoreService.receiveResultBill(result);
        return Tools.success("成功").toString();
    }
    
    
    /**
     * 获取店铺分析订单详情
     * @param orderId
     * @return 结果
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String get(@RequestParam(value = "orderId") Long orderId){
        if(orderId == null || orderId == 0L){
            return Tools.error("订单Id不能为空！").toString();
        }
        AnalyzeStoreBill analyzeStoreBill = analyzeStoreService.selectOneBillAndDetails(orderId, 0L);
        if(analyzeStoreBill == null){
            return Tools.error("未找到订单详情！").toString();
        }
        JSONObject billJSON = new JSONObject();
        billJSON.put("analyzeStoreBill", analyzeStoreBill);
        return Tools.success(billJSON, "成功").toString();
    }
    
    /**
     * 获取店铺分析订单List
     * @param page 第几页
     * @param httpSession
     * @param request
     * @return 
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String list(@RequestParam(value = "page", defaultValue = "1") Integer page,
            HttpSession httpSession, HttpServletRequest request) {
        Integer limitStart = 0;
        Integer quantity = 10;
        if (page >= 2) {
            limitStart = page * quantity - quantity;
        }
        List<AnalyzeStoreBill> billAndDetailsList = analyzeStoreService.billAndDetailsList(0L, limitStart, quantity);

        long count = analyzeStoreService.countBillByMemberId(0L);
        long sumPage = (long) Math.ceil(((double) count / (double) quantity));

        JSONObject billAndDetailsListJson = new JSONObject();
        billAndDetailsListJson.put("billAndDetailsList", billAndDetailsList);
        billAndDetailsListJson.put("billCount", count);
        billAndDetailsListJson.put("billSumPage", sumPage);

        return Tools.success(billAndDetailsListJson).toString();
    }
    
}
