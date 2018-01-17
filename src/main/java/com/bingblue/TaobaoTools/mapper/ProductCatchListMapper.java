package com.bingblue.TaobaoTools.mapper;

import com.bingblue.TaobaoTools.pojo.ProductCatchList;

public interface ProductCatchListMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(ProductCatchList record);

    int insertSelective(ProductCatchList record);

    ProductCatchList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductCatchList record);

    int updateByPrimaryKeyWithBLOBs(ProductCatchList record);

    int updateByPrimaryKey(ProductCatchList record);

    ProductCatchList selectOne(ProductCatchList record);
}
