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
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SayMing
 */
@Repository
public class ProductCatchListDao {

    @Resource
    private ProductCatchListMapper mapper;

    public ProductCatchList selectOne(String keywords, Date happenDate) {
        ProductCatchListExample example = new ProductCatchListExample();
        example.or().andHappenDateEqualTo(happenDate).andKeywordsEqualTo(keywords);
        ProductCatchList productCatchList = mapper.selectOneByExample(example);
        return productCatchList;
    }

    public Integer insert(ProductCatchList productCatchList) {
        return mapper.insert(productCatchList);
    }

}
