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

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String orderList(@RequestParam(value = "page", defaultValue = "0") String pageP,
            @RequestParam(value = "quantity", defaultValue = "0") String quantityP,
            HttpSession httpSession) {
        JSONObject result;
        Integer page = Integer.parseInt(pageP);
        Integer quantity = Integer.parseInt(quantityP);
        
        List<ManyOrderBill> manyOrderAndDetailsList = manyOrderService.manyOrderAndDetailsList(0, page, quantity);
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
            Integer orderId = manyOrderService.addManyOrderBillOnOneLink(manyOrderBill, manyOrderBill.getManyOrderDetails());
            JSONObject object = new JSONObject();
            object.put("Msg", "订单号：" + orderId);
            result = Tools.success(object);
        }

        return result.toString();
    }

}
