package com.bingblue.TaobaoTools.mapper;

import com.bingblue.TaobaoTools.pojo.ManyOrderBill;
import com.bingblue.TaobaoTools.pojo.ManyOrderBillExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ManyOrderBillMapper {
    long countByExample(ManyOrderBillExample example);

    int deleteByExample(ManyOrderBillExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ManyOrderBill record);

    int insertSelective(ManyOrderBill record);

    List<ManyOrderBill> selectByExample(ManyOrderBillExample example);

    ManyOrderBill selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ManyOrderBill record, @Param("example") ManyOrderBillExample example);

    int updateByExample(@Param("record") ManyOrderBill record, @Param("example") ManyOrderBillExample example);

    int updateByPrimaryKeySelective(ManyOrderBill record);

    int updateByPrimaryKey(ManyOrderBill record);
    
    ManyOrderBill selectOneHasDetails(Integer id);
    
    List<ManyOrderBill> selectHasDetails(@Param("memberId") Integer memberId, @Param("page") Integer page, @Param("quantity") Integer quantity);
}