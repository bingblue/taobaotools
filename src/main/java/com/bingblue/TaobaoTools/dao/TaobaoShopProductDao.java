/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bingblue.TaobaoTools.dao;

import com.bingblue.TaobaoTools.mapper.TaobaoShopProductMapper;
import com.bingblue.TaobaoTools.pojo.TaobaoShopProduct;
import java.util.Date;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SayMing
 */
@Repository
public class TaobaoShopProductDao {

    @Resource
    private TaobaoShopProductMapper mapper;
    
    public void insert(TaobaoShopProduct product){
        product.setCreateDate(new Date());
        mapper.insert(product);
    }
    
}
