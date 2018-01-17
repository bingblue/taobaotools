package com.bingblue.TaobaoTools.mapper;

import com.bingblue.TaobaoTools.pojo.ManyOrderDetail;

public interface ManyOrderDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ManyOrderDetail record);

    int insertSelective(ManyOrderDetail record);

    ManyOrderDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ManyOrderDetail record);

    int updateByPrimaryKeyWithBLOBs(ManyOrderDetail record);

    int updateByPrimaryKey(ManyOrderDetail record);
}