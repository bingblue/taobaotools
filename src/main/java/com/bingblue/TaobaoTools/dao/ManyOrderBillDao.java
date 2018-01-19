/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bingblue.TaobaoTools.dao;

import com.bingblue.TaobaoTools.mapper.ManyOrderBillMapper;
import com.bingblue.TaobaoTools.pojo.ManyOrderBill;
import com.bingblue.TaobaoTools.pojo.ManyOrderBillExample;
import java.util.List;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SayMing
 */
@Repository
public class ManyOrderBillDao {

    @Autowired
    private void setSql(SqlSessionTemplate template) {
        this.sqlSessionTemplate = template;
        this.mapper = this.sqlSessionTemplate.getMapper(ManyOrderBillMapper.class);
    }

    private ManyOrderBillMapper mapper;
    private SqlSessionTemplate sqlSessionTemplate;
    
    public Integer insert(ManyOrderBill manyOrderBill){
        return mapper.insert(manyOrderBill);
    }
    
    public ManyOrderBill selectOneById(Integer id, boolean hasDetails){
        if(hasDetails){
            return mapper.selectOneHasDetails(id);
        }else{
            return mapper.selectByPrimaryKey(id);
        }
    }
    
    public List<ManyOrderBill> selectByUserId(Integer userId, boolean hasDetails){
        ManyOrderBillExample example = new ManyOrderBillExample();
        example.createCriteria().andUserIdEqualTo(userId);
        if(hasDetails){
            return mapper.selectHasDetails(userId);
        }else{
            return mapper.selectByExample(example);
        }
    }
    
}
