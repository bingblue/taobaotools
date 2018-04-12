/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bingblue.TaobaoTools.dao;

import com.bingblue.TaobaoTools.mapper.StuckFirstScreenBillMapper;
import com.bingblue.TaobaoTools.pojo.StuckFirstScreenBill;
import com.bingblue.TaobaoTools.pojo.StuckFirstScreenBillExample;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SayMing
 */
@Repository
public class StuckFirstScreenBillDao {
    @Resource
    private StuckFirstScreenBillMapper mapper;
    
    public void insert(StuckFirstScreenBill stuckFirstScreenBill){
        stuckFirstScreenBill.setCreateDate(new Date());
        mapper.insert(stuckFirstScreenBill);
    }
    
    public void update(StuckFirstScreenBill stuckFirstScreenBill){
        mapper.updateByPrimaryKey(stuckFirstScreenBill);
    }
    
    public List<StuckFirstScreenBill> select(Long memberId, Integer page, Integer quantity, Date startDate, Date endDate){
        StuckFirstScreenBillExample ex = new StuckFirstScreenBillExample();
        ex.setStartRow(page);
        ex.setPageSize(quantity);
        ex.createCriteria().andMemberIdEqualTo(memberId);
        //ex.createCriteria().andCreateDateGreaterThanOrEqualTo(startDate).andCreateDateLessThanOrEqualTo(endDate).andMemberIdEqualTo(memberId);
        return mapper.selectHasTaobaoWord(ex);
    }
    
    public long count(Long memberId, Date startDate, Date endDate){
        StuckFirstScreenBillExample ex = new StuckFirstScreenBillExample();
        StuckFirstScreenBillExample.Criteria criteria = ex.createCriteria().andMemberIdEqualTo(memberId);
//        if(startDate != null){
//            criteria.andCreateDateGreaterThanOrEqualTo(startDate);
//        }
//        if(endDate != null){
//            criteria.andCreateDateLessThanOrEqualTo(endDate);
//        }
        return mapper.countByExample(ex);
    }
    
    public StuckFirstScreenBill selectOneHasTaobaoWord(Long id, Long memberId){
        StuckFirstScreenBillExample ex = new StuckFirstScreenBillExample();
        ex.createCriteria().andIdEqualTo(id).andMemberIdEqualTo(memberId);
        return mapper.selectOneHasTaobaoWord(ex);
    }
}
