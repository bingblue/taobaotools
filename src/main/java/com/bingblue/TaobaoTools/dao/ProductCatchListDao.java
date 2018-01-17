/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bingblue.TaobaoTools.dao;

import com.bingblue.TaobaoTools.mapper.ProductCatchListMapper;
import com.bingblue.TaobaoTools.pojo.ProductCatchList;
import java.util.Date;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SayMing
 */
@Repository
public class ProductCatchListDao {

    @Resource
    ProductCatchListMapper mapper;

    public ProductCatchList selectHistory(String keywords,Date happenDate) {
        ProductCatchList productCatchList = new ProductCatchList();
        productCatchList.setHappendate(happenDate);
        productCatchList.setKeywords(keywords);
        return mapper.selectOne(productCatchList);
    }
    
    public Integer insert(ProductCatchList productCatchList){
        return mapper.insertSelective(productCatchList);
    }

}
