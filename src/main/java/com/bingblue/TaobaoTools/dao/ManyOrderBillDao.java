/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bingblue.TaobaoTools.dao;

import com.bingblue.TaobaoTools.mapper.ManyOrderBillMapper;
import com.bingblue.TaobaoTools.pojo.ManyOrderBill;
import com.bingblue.TaobaoTools.pojo.ManyOrderBillExample;
import com.bingblue.TaobaoTools.pojo.ManyOrderDetail;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SayMing
 */
@Repository
public class ManyOrderBillDao {

    @Resource
    private ManyOrderDetailDao manyOrderDetailDao;
    @Resource
    private ManyOrderBillMapper mapper;

    public Integer insert(ManyOrderBill manyOrderBill) {
        manyOrderBill.setCreateDate(new Date());
        return mapper.insert(manyOrderBill);
    }

    public ManyOrderBill selectOneById(Long id, Long memberId, boolean hasDetails) {
        if (hasDetails) {
            return mapper.selectOneHasDetails(id, memberId);
        } else {
            ManyOrderBillExample ex = new ManyOrderBillExample();
            ex.createCriteria().andIdEqualTo(id).andMemberIdEqualTo(memberId);
            List<ManyOrderBill> datas = mapper.selectByExample(ex);
            if(datas.isEmpty()){
                return null;
            }else{
                return datas.get(0);
            }
        }
    }

    /**
     * 查找会员下的淘词补单List
     *
     * @param memberId
     * @param hasDetails
     * @param page
     * @param quantity
     * @return
     */
    public List<ManyOrderBill> selectByMemberId(Long memberId, boolean hasDetails, Integer page, Integer quantity) {
        ManyOrderBillExample example = new ManyOrderBillExample();
        example.setStartRow(page);
        example.setPageSize(quantity);
        if (hasDetails) {
            example.createCriteria().andMemberIdEqualTo(memberId);
            List<ManyOrderBill> manyOrderBills = mapper.selectByExample(example);
            for(ManyOrderBill manyOrderBill : manyOrderBills){
                List<ManyOrderDetail> manyOrderDetails = manyOrderDetailDao.selectByHeadId(manyOrderBill.getId());
                manyOrderBill.setManyOrderDetails(manyOrderDetails);
            }
            return manyOrderBills;
        } else {
            example.createCriteria().andMemberIdEqualTo(memberId);
            return mapper.selectByExampleLimit(example);
        }
    }
    
    public long count(Long memberId){
        ManyOrderBillExample example = new ManyOrderBillExample();
        example.createCriteria().andMemberIdEqualTo(memberId);
        return mapper.countByExample(example);
    }
    

}
