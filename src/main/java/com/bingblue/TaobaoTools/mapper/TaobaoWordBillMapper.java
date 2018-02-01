package com.bingblue.TaobaoTools.mapper;

import com.bingblue.TaobaoTools.pojo.TaobaoWordBill;
import com.bingblue.TaobaoTools.pojo.TaobaoWordBillExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TaobaoWordBillMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_taobaowordbill
     *
     * @mbg.generated
     */
    long countByExample(TaobaoWordBillExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_taobaowordbill
     *
     * @mbg.generated
     */
    int deleteByExample(TaobaoWordBillExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_taobaowordbill
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_taobaowordbill
     *
     * @mbg.generated
     */
    int insert(TaobaoWordBill record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_taobaowordbill
     *
     * @mbg.generated
     */
    int insertSelective(TaobaoWordBill record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_taobaowordbill
     *
     * @mbg.generated
     */
    List<TaobaoWordBill> selectByExample(TaobaoWordBillExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_taobaowordbill
     *
     * @mbg.generated
     */
    TaobaoWordBill selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_taobaowordbill
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") TaobaoWordBill record, @Param("example") TaobaoWordBillExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_taobaowordbill
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") TaobaoWordBill record, @Param("example") TaobaoWordBillExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_taobaowordbill
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(TaobaoWordBill record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_taobaowordbill
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TaobaoWordBill record);
}