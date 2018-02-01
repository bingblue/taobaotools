package com.bingblue.TaobaoTools.mapper;

import com.bingblue.TaobaoTools.pojo.TaobaoWord;
import com.bingblue.TaobaoTools.pojo.TaobaoWordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TaobaoWordMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_taobaoword
     *
     * @mbg.generated
     */
    long countByExample(TaobaoWordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_taobaoword
     *
     * @mbg.generated
     */
    int deleteByExample(TaobaoWordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_taobaoword
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_taobaoword
     *
     * @mbg.generated
     */
    int insert(TaobaoWord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_taobaoword
     *
     * @mbg.generated
     */
    int insertSelective(TaobaoWord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_taobaoword
     *
     * @mbg.generated
     */
    List<TaobaoWord> selectByExample(TaobaoWordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_taobaoword
     *
     * @mbg.generated
     */
    TaobaoWord selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_taobaoword
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") TaobaoWord record, @Param("example") TaobaoWordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_taobaoword
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") TaobaoWord record, @Param("example") TaobaoWordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_taobaoword
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(TaobaoWord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_taobaoword
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TaobaoWord record);
}