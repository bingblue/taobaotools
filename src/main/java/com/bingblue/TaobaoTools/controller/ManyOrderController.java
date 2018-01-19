/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bingblue.TaobaoTools.controller;

import com.alibaba.fastjson.JSONObject;
import com.bingblue.TaobaoTools.pojo.ManyOrderBill;
import com.bingblue.TaobaoTools.service.ManyOrderService;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
        return "/manyOrder";
    }

    @RequestMapping("/list")
    public String orderList() {
        return "/manyOrder";
    }

    @RequestMapping(value = "/addOne", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String addManyOrderBillOnOneLink(ManyOrderBill manyOrderBill) {

        JSONObject result;
        if (manyOrderBill == null) {//失败
            JSONObject object = new JSONObject();
            object.put("Msg", "生成淘口令失败。");
            result = Tools.error(object);
        } else {
            Integer orderId = manyOrderService.addManyOrderBillOnOneLink(manyOrderBill, manyOrderBill.getManyOrderDetails());
            JSONObject object = new JSONObject();
            object.put("Msg", "订单号：" + orderId);
            result = Tools.success(object);
        }

        return result.toString();
    }

}
