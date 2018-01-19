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
    
    public Integer addManyOrderBillOnOneLink(ManyOrderBill orderBill, List<ManyOrderDetail> details){
        orderBill.setCreateDate(new Date());
        orderBill.setUserId(0);
        manyOrderBillDao.insert(orderBill);
        for (ManyOrderDetail detail : details) {
            detail.setHeadId(orderBill.getId());
            manyOrderDetailDao.insert(detail);
        }
        return orderBill.getId();
    }
    
    public ManyOrderBill selectManyOrderAndDetails(Integer orderId){
        return manyOrderBillDao.selectOneById(orderId, true);
    }
    
}
