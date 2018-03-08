/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bingblue.TaobaoTools.dao;

import com.bingblue.TaobaoTools.mapper.AnalyzeStoreBillMapper;
import com.bingblue.TaobaoTools.pojo.AnalyzeStoreBill;
import com.bingblue.TaobaoTools.pojo.AnalyzeStoreBillExample;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

/**
 *
 * @author hong
 */
public class AnalyzeStoreBillDao {

    @Resource
    private AnalyzeStoreBillMapper mapper;

    public Integer insert(AnalyzeStoreBill analyzeStoreBill) {
        analyzeStoreBill.setCreateDate(new Date());
        return mapper.insert(analyzeStoreBill);
    }

    public AnalyzeStoreBill selectOneById(Long id, Long memberId) {
        AnalyzeStoreBillExample ex = new AnalyzeStoreBillExample();
        ex.createCriteria().andIdEqualTo(id).andMemberIdEqualTo(memberId);
        List<AnalyzeStoreBill> datas = mapper.selectByExample(ex);
        if (datas.isEmpty()) {
            return null;
        } else {
            return datas.get(0);
        }
    }
    
    public AnalyzeStoreBill selectOneById(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    public List<AnalyzeStoreBill> selectByMemberId(Long memberId, Integer page, Integer quantity) {
        AnalyzeStoreBillExample example = new AnalyzeStoreBillExample();
        example.setStartRow(page);
        example.setPageSize(quantity);
        example.createCriteria().andMemberIdEqualTo(memberId);
        return mapper.selectByExampleLimit(example);
    }

    public void update(AnalyzeStoreBill bill){
        mapper.updateByPrimaryKey(bill);
    }
    
}
