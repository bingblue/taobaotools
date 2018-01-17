/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bingblue.TaobaoTools.service;

import com.bingblue.TaobaoTools.dao.ProductCatchListDao;
import com.bingblue.TaobaoTools.pojo.ProductCatchList;
import java.util.Date;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author SayMing
 */
@Service()
public class TaobaoProductService {
    
    @Resource
    private ProductCatchListDao productCatchListDao;
    
    /**
     * 插入查询结果
     * @param productCatchList
     * @return 
     */
    public Integer insertProductCatchList(ProductCatchList productCatchList){
        return productCatchListDao.insert(productCatchList);
    }
    
    /**
     * 查询本地数据库中的历史搜索结果
     * @param keywords
     * @param happenDate
     * @return 
     */
    public String localProductCatchList(String keywords, Date happenDate){
        ProductCatchList productCatchList = productCatchListDao.selectHistory(keywords, happenDate);
        if(productCatchList == null){
            return null;
        }
        return productCatchList.getProductids();
    }
    
    public String nowLocalProductCatchList(String keywords){
        Date now = new Date();
        return localProductCatchList(keywords, now);
    }
    
}