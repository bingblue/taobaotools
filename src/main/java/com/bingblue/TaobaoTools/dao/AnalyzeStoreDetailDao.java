/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bingblue.TaobaoTools.dao;

import com.bingblue.TaobaoTools.mapper.AnalyzeStoreDetailMapper;
import com.bingblue.TaobaoTools.pojo.AnalyzeStoreDetail;
import com.bingblue.TaobaoTools.pojo.AnalyzeStoreDetailExample;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pdmilk
 */
@Repository
public class AnalyzeStoreDetailDao {
    @Resource
    private AnalyzeStoreDetailMapper mapper;
    
    public void insert(AnalyzeStoreDetail detail){
        mapper.insert(detail);
    }
    
    public List<AnalyzeStoreDetail> selectByBillId(Long billId){
        AnalyzeStoreDetailExample ex = new AnalyzeStoreDetailExample();
        ex.createCriteria().andAnalyzeStoreBillIdEqualTo(billId);
        return mapper.selectByExample(ex);
    }
    
}
