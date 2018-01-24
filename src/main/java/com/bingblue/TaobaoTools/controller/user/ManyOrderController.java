/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bingblue.TaobaoTools.controller.user;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bingblue.TaobaoTools.controller.Tools;
import com.bingblue.TaobaoTools.pojo.ManyOrderBill;
import com.bingblue.TaobaoTools.pojo.ManyOrderDetail;
import com.bingblue.TaobaoTools.service.ManyOrderService;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author SayMing
 */
@Controller
@RequestMapping("/user/manyOrder")
public class ManyOrderController {

    private Logger logger = Logger.getLogger(ManyOrderController.class);

    @Resource
    private ManyOrderService manyOrderService;

    @RequestMapping()
    public String toIndex() {
        return "/user/manyOrder";
    }

    /**
     * 获取淘词补单列表
     *
     * @param page 第几页
     * @param model model
     * @param httpSession 会话
     * @return 淘词补单Json数据
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String orderList(@RequestParam(value = "page", defaultValue = "1") Integer page,
            Model model,
            HttpSession httpSession) {
        Integer limitStart = 0;
        Integer quantity = 10;
        if (page >= 2) {
            limitStart = page * quantity - quantity;
        }
        List<ManyOrderBill> manyOrderAndDetailsList = manyOrderService.manyOrderAndDetailsList(0L, limitStart, quantity);
        long count = manyOrderService.countManyOrderByUserId(0L);
        long sumPage = (long) Math.ceil(((double) count / (double) quantity));

        model.addAttribute("manyOrderAndDetailsList", manyOrderAndDetailsList);
        model.addAttribute("manyOrderBillCount", count);
        model.addAttribute("manyOrderBillSumPage", sumPage);
        return "/user/manyOrderBillList";
    }

    /**
     * 添加淘词补单单链接。POST
     *
     * @param manyOrderBill 淘词补单信息含淘词补单明细
     * @return 结果返回Json
     */
    @RequestMapping(value = "/addOne", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String addManyOrderBillOnOneLink(ManyOrderBill manyOrderBill) {

        if (manyOrderBill == null) {//失败
            return Tools.error("参数错误。").toString();
        }
        if (manyOrderBill.getProductUrl() == null || manyOrderBill.getProductUrl().trim().isEmpty()) {
            return Tools.error("商品链接不能为空。").toString();
        }
        if (manyOrderBill.getManyOrderDetails() == null || manyOrderBill.getManyOrderDetails().isEmpty()) {
            return Tools.error("淘词补单明细不能为空。").toString();
        }
        for (ManyOrderDetail detail : manyOrderBill.getManyOrderDetails()) {
            if (detail.getKeywords() == null || detail.getKeywords().trim().isEmpty()) {
                return Tools.error("淘词补单明细关键词不能为空。").toString();
            }
            if (detail.getLimitClickQuantity() == null || detail.getLimitClickQuantity() == 0) {
                detail.setLimitClickQuantity(1);
            }
        }
        JSONObject result;
        manyOrderBill.setUserId(0L);
        manyOrderBill.setMemberId(0L);
        Long orderId = manyOrderService.addManyOrderBillOnOneLink(manyOrderBill, manyOrderBill.getManyOrderDetails());
        JSONObject object = new JSONObject();
        object.put("manyOrderBillId", orderId);
        result = Tools.success(object);

        return result.toString();
    }

    /**
     * 添加淘词补单多链接。POST
     *
     * @param manyOrderBill 淘词补单信息含淘词补单明细
     * @return 结果返回Json
     */
    @RequestMapping(value = "/addMore", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String addManyOrderBillOnMoreLink(ManyOrderBill manyOrderBill) {

        if (manyOrderBill == null) {//失败
            return Tools.error("参数错误。").toString();
        }
        
        for (ManyOrderDetail detail : manyOrderBill.getManyOrderDetails()) {
            if (detail.getProductUrl()== null || detail.getProductUrl().trim().isEmpty()) {
                return Tools.error("淘词补单商品链接不能为空。").toString();
            }
            if (detail.getLimitClickQuantity() == null || detail.getLimitClickQuantity() == 0) {
                detail.setLimitClickQuantity(1);
            }
        }
        
        JSONObject result;
        manyOrderBill.setUserId(0L);
        manyOrderBill.setMemberId(0L);
        Long orderId = manyOrderService.addManyOrderBillOnMoreLink(manyOrderBill, manyOrderBill.getManyOrderDetails());
        JSONObject object = new JSONObject();
        object.put("manyOrderBillId", orderId);
        result = Tools.success(object);

        return result.toString();
    }

    /**
     * 获取淘词补单信息。GET
     *
     * @param orderId 淘词补单Id
     * @return 返回淘词补单Json
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String getManyOrderBill(@RequestParam(value = "orderId", defaultValue = "0", required = true) Long orderId,
            Model model) {

        ManyOrderBill manyOrderBill = manyOrderService.selectManyOrderAndDetails(orderId);
        if (manyOrderBill == null) {
            return "/user/manyOrderBillList";
        }
        model.addAttribute("manyOrderBill", manyOrderBill);

        return "/user/manyOrderBillGet";
    }

    @RequestMapping(value = "/share", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String share(@RequestParam(value = "orderId", defaultValue = "0", required = true) Long orderId,
            @RequestHeader(value = "user-agent") String userAgent) {
        if (Tools.checkUserAgent(userAgent) == Tools.UserAgent.WEIXIN) {
            return "/user/showBrowserOpen";
        } else {
            String url = manyOrderService.share(orderId);
            if (url == null) {
                return "/user/manyOrderBillShareDone";//没有可补单的链接了，任务完成。
            } else {
                return "redirect:" + url;
            }
        }
    }
}