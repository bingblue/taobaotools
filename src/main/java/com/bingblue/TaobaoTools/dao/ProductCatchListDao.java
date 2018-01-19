/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bingblue.TaobaoTools.dao;

import com.bingblue.TaobaoTools.mapper.ProductCatchListMapper;
import com.bingblue.TaobaoTools.pojo.ProductCatchList;
import com.bingblue.TaobaoTools.pojo.ProductCatchListExample;
import java.util.Date;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SayMing
 */
@Repository
public class ProductCatchListDao {
    
    @Autowired
    private void setSql(SqlSessionTemplate template) {
        this.sqlSessionTemplate = template;
        this.mapper = this.sqlSessionTemplate.getMapper(ProductCatchListMapper.class);
    }

    private ProductCatchListMapper mapper;
    private SqlSessionTemplate sqlSessionTemplate;
    
    public ProductCatchList selectOne(String keywords, Date happenDate){
        ProductCatchListExample example = new ProductCatchListExample();
        example.or().andHappenDateEqualTo(happenDate).andKeywordsEqualTo(keywords);
        ProductCatchList productCatchList = mapper.selectOneByExample(example);
        return productCatchList;
    }
    
    public Integer insert(ProductCatchList productCatchList){
        return mapper.insert(productCatchList);
    }
    
}
