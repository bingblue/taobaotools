/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bingblue.TaobaoTools.service;

import com.bingblue.TaobaoTools.dao.ManyOrderBillDao;
import com.bingblue.TaobaoTools.dao.ManyOrderDetailDao;
import com.bingblue.TaobaoTools.pojo.ManyOrderBill;
import com.bingblue.TaobaoTools.pojo.ManyOrderDetail;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 淘词补单
 * 1.多关键词对应一个淘宝商品链接
 * 2.多链接商品
 * @author SayMing
 */
@Service
public class ManyOrderService {
    
    @Resource
    private ManyOrderBillDao manyOrderBillDao;
    
    @Resource
    private ManyOrderDetailDao manyOrderDetailDao;
    
    /**
     * 新增淘词补单，单链接。
     * @param orderBill
     * @param details
     * @return 
     */
    public Integer addManyOrderBillOnOneLink(ManyOrderBill orderBill, List<ManyOrderDetail> details){
        orderBill.setCreateDate(new Date());
        orderBill.setUserId(0);
        orderBill.setMemberId(0);
        manyOrderBillDao.insert(orderBill);
        for (ManyOrderDetail detail : details) {
            detail.setHeadId(orderBill.getId());
            manyOrderDetailDao.insert(detail);
        }
        return orderBill.getId();
    }
    
    /**
     * 获取指定淘词补单详情
     * @param orderId
     * @return 
     */
    public ManyOrderBill selectManyOrderAndDetails(Integer orderId){
        return manyOrderBillDao.selectOneById(orderId, true);
    }
    
    /**
     * 获取指定会员生成过的淘词补单List。
     * @param memberId
     * @param page
     * @param quantity
     * @return 
     */
    public List<ManyOrderBill> manyOrderAndDetailsList(Integer memberId, Integer page, Integer quantity){
        return manyOrderBillDao.selectByMemberId(memberId, true, page, quantity);
    }
    
}
