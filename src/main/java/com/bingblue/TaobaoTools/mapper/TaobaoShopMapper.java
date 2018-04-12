package com.bingblue.TaobaoTools.mapper;

import com.bingblue.TaobaoTools.pojo.TaobaoShop;
import com.bingblue.TaobaoTools.pojo.TaobaoShopExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TaobaoShopMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_taobaoshop
     *
     * @mbg.generated
     */
    long countByExample(TaobaoShopExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_taobaoshop
     *
     * @mbg.generated
     */
    int deleteByExample(TaobaoShopExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_taobaoshop
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_taobaoshop
     *
     * @mbg.generated
     */
    int insert(TaobaoShop record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_taobaoshop
     *
     * @mbg.generated
     */
    int insertSelective(TaobaoShop record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_taobaoshop
     *
     * @mbg.generated
     */
    List<TaobaoShop> selectByExample(TaobaoShopExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_taobaoshop
     *
     * @mbg.generated
     */
    TaobaoShop selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_taobaoshop
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") TaobaoShop record, @Param("example") TaobaoShopExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_taobaoshop
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") TaobaoShop record, @Param("example") TaobaoShopExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_taobaoshop
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(TaobaoShop record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_taobaoshop
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TaobaoShop record);
}