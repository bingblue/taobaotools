/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bingblue.TaobaoTools.controller.user;

import com.alibaba.fastjson.JSONObject;
import com.bingblue.TaobaoTools.controller.Tools;
import com.bingblue.TaobaoTools.pojo.ManyOrderBill;
import com.bingblue.TaobaoTools.pojo.ManyOrderDetail;
import com.bingblue.TaobaoTools.service.ManyOrderService;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
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
     * @param httpSession 会话
     * @param request request
     * @return 淘词补单Json数据
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String orderList(@RequestParam(value = "page", defaultValue = "1") Integer page,
            HttpSession httpSession, HttpServletRequest request) {
        Integer limitStart = 0;
        Integer quantity = 10;
        if (page >= 2) {
            limitStart = page * quantity - quantity;
        }
        List<ManyOrderBill> manyOrderAndDetailsList = manyOrderService.manyOrderAndDetailsList(0L, limitStart, quantity);

        //短链接生成
        for (ManyOrderBill bill : manyOrderAndDetailsList) {
            bill.setShortLink(createShortUrl(request, bill.getId()));
        }

        long count = manyOrderService.countManyOrderByMemberId(0L);
        long sumPage = (long) Math.ceil(((double) count / (double) quantity));

        JSONObject manyOrderAndDetailsListJson = new JSONObject();
        manyOrderAndDetailsListJson.put("manyOrderAndDetailsList", manyOrderAndDetailsList);
        manyOrderAndDetailsListJson.put("manyOrderBillCount", count);
        manyOrderAndDetailsListJson.put("manyOrderBillSumPage", sumPage);

        return Tools.success(manyOrderAndDetailsListJson).toString();
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
            if (detail.getProductUrl() == null || detail.getProductUrl().trim().isEmpty()) {
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
    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getManyOrderBill(@RequestParam(value = "orderId", defaultValue = "0", required = true) Long orderId,
            HttpSession session, HttpServletRequest request) {
        ManyOrderBill manyOrderBill = manyOrderService.selectManyOrderAndDetails(orderId, 0L);
        manyOrderBill.setShortLink(createShortUrl(request, orderId));
        JSONObject manyOrderBillJson = new JSONObject();
        manyOrderBillJson.put("manyOrderBill", manyOrderBill);
        return Tools.success(manyOrderBillJson).toString();

    }

    @RequestMapping(value = "/share", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String share(@RequestParam(value = "orderId", defaultValue = "0", required = true) Long orderId,
            @RequestHeader(value = "user-agent") String userAgent) {
        if (Tools.checkUserAgent(userAgent) == Tools.UserAgent.WEIXIN) {
            return "/user/showBrowserOpen";
        } else {
            String url = manyOrderService.generateStuckFirstScreenUrl(orderId);
            if (url == null) {
                return "/user/manyOrderBillShareDone";//没有可补单的链接了，任务完成。
            } else {
                return "redirect:" + url;
            }
        }
    }

    private String createShortUrl(HttpServletRequest request, Long billId) {
        String root = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        return root + "/user/manyOrder/share.do?orderId=" + billId;
    }
}
