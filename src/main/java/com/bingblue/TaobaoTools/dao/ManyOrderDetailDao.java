/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bingblue.TaobaoTools.dao;

import com.bingblue.TaobaoTools.mapper.ManyOrderDetailMapper;
import com.bingblue.TaobaoTools.pojo.ManyOrderDetail;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SayMing
 */
@Repository
public class ManyOrderDetailDao {
    
    @Autowired
    private void setSql(SqlSessionTemplate template) {
        this.sqlSessionTemplate = template;
        this.mapper = this.sqlSessionTemplate.getMapper(ManyOrderDetailMapper.class);
    }

    private ManyOrderDetailMapper mapper;
    private SqlSessionTemplate sqlSessionTemplate;
    
    public Integer insert(ManyOrderDetail manyOrderDetail){
        return mapper.insert(manyOrderDetail);
    }
    
}
