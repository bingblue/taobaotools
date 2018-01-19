package com.bingblue.TaobaoTools.mapper;

import com.bingblue.TaobaoTools.pojo.ProductCatchList;
import com.bingblue.TaobaoTools.pojo.ProductCatchListExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductCatchListMapper {
    long countByExample(ProductCatchListExample example);

    int deleteByExample(ProductCatchListExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductCatchList record);

    int insertSelective(ProductCatchList record);

    List<ProductCatchList> selectByExample(ProductCatchListExample example);

    ProductCatchList selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductCatchList record, @Param("example") ProductCatchListExample example);

    int updateByExample(@Param("record") ProductCatchList record, @Param("example") ProductCatchListExample example);

    int updateByPrimaryKeySelective(ProductCatchList record);

    int updateByPrimaryKey(ProductCatchList record);
    
    ProductCatchList selectOneByExample(ProductCatchListExample example);
    
}