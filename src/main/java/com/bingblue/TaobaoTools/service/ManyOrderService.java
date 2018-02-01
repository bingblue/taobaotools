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
    
    @Resource
    private StuckFirstScreenService stuckFirstScreenService;
    
    /**
     * 新增淘词补单，多关键词，单链接。
     * @param orderBill
     * @param details
     * @return 
     */
    public Long addManyOrderBillOnOneLink(ManyOrderBill orderBill, List<ManyOrderDetail> details){
        orderBill.setCreateDate(new Date());
        manyOrderBillDao.insert(orderBill);
        for (ManyOrderDetail detail : details) {
            if(null == detail.getKeywords() || detail.getKeywords().isEmpty()){
                continue;
            }
            detail.setHeadId(orderBill.getId());
            detail.setProductUrl(orderBill.getProductUrl());
            detail.setClickCount(0);
            detail.setClickQuantity(0);
            manyOrderDetailDao.insert(detail);
        }
        return orderBill.getId();
    }
    /**
     * 新增淘词补单，多链接。
     * @param orderBill
     * @param details
     * @return 
     */
    public Long addManyOrderBillOnMoreLink(ManyOrderBill orderBill, List<ManyOrderDetail> details){
        orderBill.setCreateDate(new Date());
        manyOrderBillDao.insert(orderBill);
        for (ManyOrderDetail detail : details) {
            if(null == detail.getKeywords() || detail.getKeywords().isEmpty()){
                continue;
            }
            detail.setHeadId(orderBill.getId());
            detail.setClickCount(0);
            detail.setClickQuantity(0);
            manyOrderDetailDao.insert(detail);
        }
        return orderBill.getId();
    }
    
    /**
     * 获取指定淘词补单详情
     * @param orderId
     * @return 
     */
    public ManyOrderBill selectManyOrderAndDetails(Long orderId, Long memberId){
        return manyOrderBillDao.selectOneById(orderId, memberId, true);
    }
    
    /**
     * 获取指定会员生成过的淘词补单List。
     * @param memberId
     * @param page
     * @param quantity
     * @return 
     */
    public List<ManyOrderBill> manyOrderAndDetailsList(Long memberId, Integer page, Integer quantity){
        return manyOrderBillDao.selectByMemberId(memberId, true, page, quantity);
    }
    
    /**
     * 根据淘词补单单中的URL生成卡首屏链接。
     * @param orderId 淘词补单Id
     * @return url链接
     */
    public String generateStuckFirstScreenUrl(Long orderId){
        String url = null;
        ManyOrderDetail manyOrderDetail = manyOrderDetailDao.randomByHeadId(orderId);
        if(manyOrderDetail != null){
            //直接返回商品详情Url
            if(null == manyOrderDetail.getKeywords() || manyOrderDetail.getKeywords().isEmpty()){
                url = manyOrderDetail.getProductUrl();
            }else{//根据关键字，淘宝链接，生成卡首屏URL。
                String productId = StuckFirstScreenService.findProductIdFromUrl(manyOrderDetail.getProductUrl());
                if(productId == null){
                    return null;
                }
                url = stuckFirstScreenService.generateMobileUrl(productId, manyOrderDetail.getKeywords());
            }
            //增加点击量
            manyOrderDetail.setClickCount(manyOrderDetail.getClickCount() + 1);
            manyOrderDetail.setClickQuantity(manyOrderDetail.getClickQuantity() + 1);
            manyOrderDetailDao.update(manyOrderDetail);
        }
        return url;
    }
    
    public long countManyOrderByMemberId(Long memberId){
        return manyOrderBillDao.count(memberId);
    }
    
}
