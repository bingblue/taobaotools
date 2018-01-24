package com.bingblue.TaobaoTools.mapper;

import com.bingblue.TaobaoTools.pojo.ManyOrderDetail;
import com.bingblue.TaobaoTools.pojo.ManyOrderDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ManyOrderDetailMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_manyorderdetail
     *
     * @mbg.generated
     */
    long countByExample(ManyOrderDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_manyorderdetail
     *
     * @mbg.generated
     */
    int deleteByExample(ManyOrderDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_manyorderdetail
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_manyorderdetail
     *
     * @mbg.generated
     */
    int insert(ManyOrderDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_manyorderdetail
     *
     * @mbg.generated
     */
    int insertSelective(ManyOrderDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_manyorderdetail
     *
     * @mbg.generated
     */
    List<ManyOrderDetail> selectByExample(ManyOrderDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_manyorderdetail
     *
     * @mbg.generated
     */
    ManyOrderDetail selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_manyorderdetail
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") ManyOrderDetail record, @Param("example") ManyOrderDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_manyorderdetail
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") ManyOrderDetail record, @Param("example") ManyOrderDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_manyorderdetail
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ManyOrderDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_manyorderdetail
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ManyOrderDetail record);
	
	
    //自定义Start
    ManyOrderDetail randomByHeadId(Integer headId);
    
    //自定义End
}