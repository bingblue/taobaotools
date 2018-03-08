/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bingblue.TaobaoTools.service;

import com.alibaba.fastjson.JSONObject;
import com.bingblue.TaobaoTools.dao.AnalyzeStoreBillDao;
import com.bingblue.TaobaoTools.pojo.AnalyzeStoreBill;
import java.util.Date;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author hong
 */
@Service
public class AnalyzeStoreService {
    
    public static final String BILL_STATUS_DISPOSE = "处理中";
    public static final String BILL_STATUS_DONE = "完成";
    
    @Resource
    private AnalyzeStoreBillDao analyzeStoreBillDao;
    
    /**
     * 接收订单
     * @param catchUrl 需要分析的店铺url
     * @param memberId 会员Id
     * @return 订单Id
     */
    public Long receiveBill(String catchUrl, Long memberId){
        AnalyzeStoreBill bill = new AnalyzeStoreBill();
        bill.setStatus(BILL_STATUS_DISPOSE);
        bill.setCatchUrl(catchUrl);
        bill.setHappenDate(new Date());
        bill.setMemberId(memberId);
        analyzeStoreBillDao.insert(bill);
        return bill.getId();
    }
    
    /**
     * 接收爬取到的店铺明细商品信息数据，并录入数据库。
     * @param billId 分析店铺单Id
     * @param remark 备注
     * @param result 爬取结果
     */
    public void receiveResultBill(Long billId, String remark, String result){
        AnalyzeStoreBill bill = analyzeStoreBillDao.selectOneById(billId);
        if(bill != null){
            bill.setDoneDate(new Date());
            bill.setStatus(BILL_STATUS_DONE);
            bill.setCatchRemark(remark);
            analyzeStoreBillDao.update(bill);
        }
        JSONObject resultJSON = JSONObject.parseObject(result);
        //处理爬取到的商品数据信息。插入进数据库。
    }
    
}
