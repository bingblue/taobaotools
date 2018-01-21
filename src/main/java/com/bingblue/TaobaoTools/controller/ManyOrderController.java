/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bingblue.TaobaoTools.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bingblue.TaobaoTools.pojo.ManyOrderBill;
import com.bingblue.TaobaoTools.service.ManyOrderService;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
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
@RequestMapping("/manyOrder")
public class ManyOrderController {

    private Logger logger = Logger.getLogger(ManyOrderController.class);

    @Resource
    private ManyOrderService manyOrderService;

    @RequestMapping()
    public String toIndex() {
        return "manyOrder";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String orderList(@RequestParam(value = "page", defaultValue = "0") Integer page,
            HttpSession httpSession) {
        JSONObject result;
        Integer limitStart = 0;
        Integer quantity = 10;
        if(page >= 2){
            limitStart = page * quantity - quantity;
        }
        List<ManyOrderBill> manyOrderAndDetailsList = manyOrderService.manyOrderAndDetailsList(0, limitStart, quantity);
        if(manyOrderAndDetailsList.isEmpty()){
            JSONObject object = new JSONObject();
            object.put("ManyOrderBillList", new JSONArray());
            result = Tools.success(object);
        }else{
            JSONArray datas = new JSONArray();
            datas.addAll(manyOrderAndDetailsList);
            JSONObject object = new JSONObject();
            object.put("ManyOrderBillList", datas);
            result = Tools.success(object);
        }
        return result.toString();
    }

    @RequestMapping(value = "/addOne", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String addManyOrderBillOnOneLink(ManyOrderBill manyOrderBill) {

        JSONObject result;
        if (manyOrderBill == null) {//失败
            JSONObject object = new JSONObject();
            object.put("Msg", "参数错误。");
            result = Tools.error(object);
        } else {
            manyOrderBill.setUserId(0);
            manyOrderBill.setMemberId(0);
            Integer orderId = manyOrderService.addManyOrderBillOnOneLink(manyOrderBill, manyOrderBill.getManyOrderDetails());
            JSONObject object = new JSONObject();
            object.put("Msg", "订单号：" + orderId);
            result = Tools.success(object);
        }

        return result.toString();
    }
    
    @RequestMapping(value = "/addMore", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String addManyOrderBillOnMoreLink(ManyOrderBill manyOrderBill) {
        
        JSONObject result;
        if (manyOrderBill == null) {//失败
            JSONObject object = new JSONObject();
            object.put("Msg", "参数错误。");
            result = Tools.error(object);
        } else {
            manyOrderBill.setUserId(0);
            manyOrderBill.setMemberId(0);
            Integer orderId = manyOrderService.addManyOrderBillOnMoreLink(manyOrderBill, manyOrderBill.getManyOrderDetails());
            JSONObject object = new JSONObject();
            object.put("Msg", "订单号：" + orderId);
            result = Tools.success(object);
        }

        return result.toString();
    }
    
    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getManyOrderBill(@RequestParam(value = "orderId", defaultValue = "0", required = true) Integer orderId){
        
        JSONObject result;
        ManyOrderBill manyOrderBill = manyOrderService.selectManyOrderAndDetails(orderId);
        if (manyOrderBill == null) {//失败
            JSONObject object = new JSONObject();
            object.put("Msg", "未找到补单信息。");
            result = Tools.error(object);
        } else {
            JSONObject object = new JSONObject();
            object.put("Msg", manyOrderBill);
            result = Tools.success(object);
        }

        return result.toString();
    }

    @RequestMapping(value = "/share", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String share(@RequestParam(value = "orderId", defaultValue = "0", required = true) Integer orderId){
        String url = manyOrderService.share(orderId);
        if(url == null){
            return "manyOrderBillShareDone";//没有可补单的链接了，任务完成。
        }else{
            return "redirect:" + url;
        }
    }
}
