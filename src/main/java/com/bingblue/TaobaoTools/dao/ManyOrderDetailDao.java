/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bingblue.TaobaoTools.dao;

import com.bingblue.TaobaoTools.mapper.ManyOrderDetailMapper;
import com.bingblue.TaobaoTools.pojo.ManyOrderDetail;
import com.bingblue.TaobaoTools.pojo.ManyOrderDetailExample;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SayMing
 */
@Repository
public class ManyOrderDetailDao {

    @Resource
    private ManyOrderDetailMapper mapper;

    public Integer insert(ManyOrderDetail manyOrderDetail) {
        return mapper.insert(manyOrderDetail);
    }

    public List<ManyOrderDetail> selectByHeadId(Long headId) {
        ManyOrderDetailExample example = new ManyOrderDetailExample();
        example.createCriteria().andHeadIdEqualTo(headId);
        return mapper.selectByExample(example);
    }

    public ManyOrderDetail randomByHeadId(Long headId) {
        return mapper.randomByHeadId(headId);
    }

    public void update(ManyOrderDetail manyOrderDetail) {
        mapper.updateByPrimaryKey(manyOrderDetail);
    }

}
