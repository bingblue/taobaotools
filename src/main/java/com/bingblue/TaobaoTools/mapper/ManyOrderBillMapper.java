package com.bingblue.TaobaoTools.mapper;

import com.bingblue.TaobaoTools.pojo.ManyOrderBill;

public interface ManyOrderBillMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ManyOrderBill record);

    int insertSelective(ManyOrderBill record);

    ManyOrderBill selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ManyOrderBill record);

    int updateByPrimaryKey(ManyOrderBill record);
}