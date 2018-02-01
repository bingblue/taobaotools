/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bingblue.TaobaoTools.controller.user;

import com.alibaba.fastjson.JSONObject;
import com.bingblue.TaobaoTools.controller.api.TaobaoTpwdCreateController;
import com.bingblue.TaobaoTools.controller.Tools;
import com.bingblue.TaobaoTools.pojo.StuckFirstScreenBill;
import com.bingblue.TaobaoTools.pojo.TaobaoWord;
import com.bingblue.TaobaoTools.service.StuckFirstScreenService;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author SayMing
 */
@Controller
@RequestMapping("/user/stuck")
public class StuckFirstScreenController {

    private Logger logger = Logger.getLogger(StuckFirstScreenController.class);

    @Resource
    private StuckFirstScreenService stuckFirstScreenService;

    @Resource
    private TaobaoTpwdCreateController taobaoTpwdCreateController;

    @RequestMapping()
    public String toIndex() {
        return "/user/stuck";
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
    @RequestMapping(value = "/mobile", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String mobile(String taobaoProductId, String keywords, String logoUrl, String productTitle) {
        //验证
        if (taobaoProductId == null || "".equals(taobaoProductId)) {
            return Tools.error("商品链接地址不能为空。").toString();
        }
        if (keywords == null || "".equals(keywords)) {
            return Tools.error("搜索的关键字不能为空。").toString();
        }
        if (productTitle == null || "".equals(productTitle)) {
            return Tools.error("口令提示内容不能为空。").toString();
        }

        StuckFirstScreenBill stuckFirstScreenBill = new StuckFirstScreenBill();
        stuckFirstScreenBill.setProductId(taobaoProductId);
        stuckFirstScreenBill.setKeywords(keywords);
        stuckFirstScreenBill.setMemberId(0L);//登录会员Id
        stuckFirstScreenBill.setCompetitor(Boolean.FALSE);
        TaobaoWord taobaoWord = new TaobaoWord();
        taobaoWord.setText(productTitle);
        taobaoWord.setLogoUrl(logoUrl);
        taobaoWord.setMemberId(stuckFirstScreenBill.getMemberId());
        stuckFirstScreenBill.setTaobaoWord(taobaoWord);
        stuckFirstScreenService.generatePlan(true, stuckFirstScreenBill);

        JSONObject tpwdJSON = new JSONObject();
        tpwdJSON.put("tpwd", stuckFirstScreenBill.getTaobaoWord().getTpwd());
        tpwdJSON.put("url", stuckFirstScreenBill.getTaobaoWord().getUrl());
        return Tools.success(tpwdJSON).toString();
    }

    /**
     * 卡同行
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
    @RequestMapping(value = "/competitor", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String competitor(String competitorProductId1, String competitorProductId2, String competitorProductId3,
            String taobaoProductId, String keywords, String logoUrl, String productTitle) {
        //验证
        if (competitorProductId1 == null || "".equals(competitorProductId1)) {
            return Tools.error("同行链接地址1不能为空。").toString();
        }
        if (competitorProductId2 == null || "".equals(competitorProductId2)) {
            return Tools.error("同行链接地址2不能为空。").toString();
        }
        if (competitorProductId3 == null || "".equals(competitorProductId3)) {
            return Tools.error("同行链接地址3不能为空。").toString();
        }
        if (taobaoProductId == null || "".equals(taobaoProductId)) {
            return Tools.error("商品链接地址不能为空。").toString();
        }
        if (keywords == null || "".equals(keywords)) {
            return Tools.error("搜索的关键字不能为空。").toString();
        }
        if (productTitle == null || "".equals(productTitle)) {
            return Tools.error("口令提示内容不能为空。").toString();
        }

        StringBuilder productIds = new StringBuilder(taobaoProductId);
        productIds.append(",").append(competitorProductId1).append(",")
                .append(competitorProductId2).append(",")
                .append(competitorProductId3);

        StuckFirstScreenBill stuckFirstScreenBill = new StuckFirstScreenBill();
        stuckFirstScreenBill.setProductId(productIds.toString());
        stuckFirstScreenBill.setKeywords(keywords);
        stuckFirstScreenBill.setMemberId(0L);//登录会员Id
        stuckFirstScreenBill.setCompetitor(Boolean.TRUE);
        TaobaoWord taobaoWord = new TaobaoWord();
        taobaoWord.setText(productTitle);
        taobaoWord.setLogoUrl(logoUrl);
        taobaoWord.setMemberId(stuckFirstScreenBill.getMemberId());
        stuckFirstScreenBill.setTaobaoWord(taobaoWord);
        stuckFirstScreenService.generatePlan(true, stuckFirstScreenBill);

        JSONObject tpwdJSON = new JSONObject();
        tpwdJSON.put("tpwd", stuckFirstScreenBill.getTaobaoWord().getTpwd());
        tpwdJSON.put("url", stuckFirstScreenBill.getTaobaoWord().getUrl());
        return Tools.success(tpwdJSON).toString();
    }

    /**
     * 获取卡首屏单List
     * @param page 获取第几页
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @param httpSession 会话
     * @return 结果List
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String list(@RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(value = "endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            HttpSession httpSession) {
        Integer limitStart = 0;
        Integer quantity = 10;
        if (page >= 2) {
            limitStart = page * quantity - quantity;
        }

        List<StuckFirstScreenBill> datas = stuckFirstScreenService.list(0L, limitStart, quantity, startDate, endDate);
        long count = stuckFirstScreenService.countStuckFirstScreenBillByMemberId(0L, startDate, endDate);
        long sumPage = (long) Math.ceil(((double) count / (double) quantity));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("stuckFirstScreenBills", datas);
        jsonObject.put("stuckFirstScreenBillCount", count);
        jsonObject.put("stuckFirstScreenBillSumPage", sumPage);

        return Tools.success(jsonObject).toString();
    }

    /**
     * 获取单个卡首屏单
     * @param id 卡首屏单Id
     * @return 数据
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getOne(@RequestParam(value = "id") Long id) {
        if (id == null || id <= 0) {
            return Tools.error("Id不正确！").toString();
        }
        StuckFirstScreenBill stuckFirstScreenBill = stuckFirstScreenService.getOneHasTaobaoWord(id, 0L);
        if(stuckFirstScreenBill == null){
            return Tools.error("未找到数据！").toString();
        }else{
            JSONObject stuckFirstScreenBillJson = new JSONObject();
            stuckFirstScreenBillJson.put("stuckFirstScreenBill", stuckFirstScreenBill);
            return Tools.success(stuckFirstScreenBillJson).toString();
        }
    }

    /**
     * 卡首屏 PC端
     *
     * @param taobaoProductId 需要卡的淘宝商品Id
     * @param keywords 关键词
     * @return 返回包含卡首屏JSON
     */
    @RequestMapping(value = "/pc", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String pc(String taobaoProductId, String keywords) {
        //验证
        if (taobaoProductId == null || "".equals(taobaoProductId)) {
            return Tools.error("商品链接地址不能为空。").toString();
        }
        if (keywords == null || "".equals(keywords)) {
            return Tools.error("搜索的关键字不能为空。").toString();
        }

        String url = stuckFirstScreenService.generatePcUrl(taobaoProductId, keywords);

        JSONObject object = new JSONObject();
        object.put("url", url);
        JSONObject result = Tools.success(object);

        return result.toJSONString();
    }

}
