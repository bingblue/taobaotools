/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bingblue.TaobaoTools.dao;

import com.bingblue.TaobaoTools.mapper.AnalyzeStoreBillMapper;
import com.bingblue.TaobaoTools.mapper.AnalyzeStoreDetailMapper;
import com.bingblue.TaobaoTools.pojo.AnalyzeStoreBill;
import com.bingblue.TaobaoTools.pojo.AnalyzeStoreBillExample;
import com.bingblue.TaobaoTools.pojo.AnalyzeStoreDetail;
import com.bingblue.TaobaoTools.pojo.AnalyzeStoreDetailExample;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;

/**
 *
 * @author hong
 */
@Repository
public class AnalyzeStoreBillDao {

    @Resource
    private AnalyzeStoreBillMapper mapper;
    @Resource
    private AnalyzeStoreDetailMapper detailMapper;

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

    public void update(AnalyzeStoreBill bill) {
        mapper.updateByPrimaryKey(bill);
    }

    /**
     * 获取单个店铺分析详情
     * @param id
     * @param memberId
     * @param hasDetails
     * @return 
     */
    public AnalyzeStoreBill selectOneById(Long id, Long memberId, boolean hasDetails) {
        AnalyzeStoreBill analyzeStoreBill = null;
        AnalyzeStoreBillExample ex = new AnalyzeStoreBillExample();
        ex.createCriteria().andIdEqualTo(id).andMemberIdEqualTo(memberId);
        List<AnalyzeStoreBill> datas = mapper.selectByExample(ex);
        if (!datas.isEmpty()) {
            analyzeStoreBill = datas.get(0);
        }
        if (hasDetails && analyzeStoreBill != null) {
            AnalyzeStoreDetailExample detailEx = new AnalyzeStoreDetailExample();
            detailEx.createCriteria().andAnalyzeStoreBillIdEqualTo(analyzeStoreBill.getId());
            List<AnalyzeStoreDetail> analyzeStoreDetails = detailMapper.selectByExample(detailEx);
            analyzeStoreBill.setAnalyzeStoreDetails(analyzeStoreDetails);
        }
        return analyzeStoreBill;
    }

    /**
     * 查找会员下的店铺分析List
     *
     * @param memberId
     * @param hasDetails
     * @param page
     * @param quantity
     * @return
     */
    public List<AnalyzeStoreBill> selectByMemberId(Long memberId, boolean hasDetails, Integer page, Integer quantity) {
        AnalyzeStoreBillExample example = new AnalyzeStoreBillExample();
        example.setStartRow(page);
        example.setPageSize(quantity);
        if (hasDetails) {
            example.createCriteria().andMemberIdEqualTo(memberId);
            List<AnalyzeStoreBill> analyzeStoreBills = mapper.selectByExampleLimit(example);
            for (AnalyzeStoreBill analyzeStoreBill : analyzeStoreBills) {
                AnalyzeStoreDetailExample detailEx = new AnalyzeStoreDetailExample();
                detailEx.createCriteria().andAnalyzeStoreBillIdEqualTo(analyzeStoreBill.getId());
                List<AnalyzeStoreDetail> analyzeStoreDetails = detailMapper.selectByExample(detailEx);
                analyzeStoreBill.setAnalyzeStoreDetails(analyzeStoreDetails);
            }
            return analyzeStoreBills;
        } else {
            example.createCriteria().andMemberIdEqualTo(memberId);
            return mapper.selectByExampleLimit(example);
        }
    }

    public long count(Long memberId) {
        AnalyzeStoreBillExample example = new AnalyzeStoreBillExample();
        example.createCriteria().andMemberIdEqualTo(memberId);
        return mapper.countByExample(example);
    }
}
