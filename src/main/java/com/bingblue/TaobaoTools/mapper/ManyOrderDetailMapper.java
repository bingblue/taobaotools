package com.bingblue.TaobaoTools.mapper;

import com.bingblue.TaobaoTools.pojo.ManyOrderDetail;
import com.bingblue.TaobaoTools.pojo.ManyOrderDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ManyOrderDetailMapper {
    long countByExample(ManyOrderDetailExample example);

    int deleteByExample(ManyOrderDetailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ManyOrderDetail record);

    int insertSelective(ManyOrderDetail record);

    List<ManyOrderDetail> selectByExample(ManyOrderDetailExample example);

    ManyOrderDetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ManyOrderDetail record, @Param("example") ManyOrderDetailExample example);

    int updateByExample(@Param("record") ManyOrderDetail record, @Param("example") ManyOrderDetailExample example);

    int updateByPrimaryKeySelective(ManyOrderDetail record);

    int updateByPrimaryKey(ManyOrderDetail record);
}