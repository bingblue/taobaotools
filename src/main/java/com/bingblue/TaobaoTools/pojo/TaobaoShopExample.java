package com.bingblue.TaobaoTools.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaobaoShopExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_taobaoshop
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_taobaoshop
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_taobaoshop
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_taobaoshop
     *
     * @mbg.generated
     */
    public TaobaoShopExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_taobaoshop
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_taobaoshop
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_taobaoshop
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_taobaoshop
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_taobaoshop
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_taobaoshop
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_taobaoshop
     *
     * @mbg.generated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_taobaoshop
     *
     * @mbg.generated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_taobaoshop
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_taobaoshop
     *
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_taobaoshop
     *
     * @mbg.generated
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andShopIdIsNull() {
            addCriterion("shop_id is null");
            return (Criteria) this;
        }

        public Criteria andShopIdIsNotNull() {
            addCriterion("shop_id is not null");
            return (Criteria) this;
        }

        public Criteria andShopIdEqualTo(String value) {
            addCriterion("shop_id =", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdNotEqualTo(String value) {
            addCriterion("shop_id <>", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdGreaterThan(String value) {
            addCriterion("shop_id >", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdGreaterThanOrEqualTo(String value) {
            addCriterion("shop_id >=", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdLessThan(String value) {
            addCriterion("shop_id <", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdLessThanOrEqualTo(String value) {
            addCriterion("shop_id <=", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdLike(String value) {
            addCriterion("shop_id like", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdNotLike(String value) {
            addCriterion("shop_id not like", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdIn(List<String> values) {
            addCriterion("shop_id in", values, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdNotIn(List<String> values) {
            addCriterion("shop_id not in", values, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdBetween(String value1, String value2) {
            addCriterion("shop_id between", value1, value2, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdNotBetween(String value1, String value2) {
            addCriterion("shop_id not between", value1, value2, "shopId");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andUrlIsNull() {
            addCriterion("url is null");
            return (Criteria) this;
        }

        public Criteria andUrlIsNotNull() {
            addCriterion("url is not null");
            return (Criteria) this;
        }

        public Criteria andUrlEqualTo(String value) {
            addCriterion("url =", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotEqualTo(String value) {
            addCriterion("url <>", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThan(String value) {
            addCriterion("url >", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThanOrEqualTo(String value) {
            addCriterion("url >=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThan(String value) {
            addCriterion("url <", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThanOrEqualTo(String value) {
            addCriterion("url <=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLike(String value) {
            addCriterion("url like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotLike(String value) {
            addCriterion("url not like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlIn(List<String> values) {
            addCriterion("url in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotIn(List<String> values) {
            addCriterion("url not in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlBetween(String value1, String value2) {
            addCriterion("url between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotBetween(String value1, String value2) {
            addCriterion("url not between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andLatelyCaptureDateIsNull() {
            addCriterion("lately_capture_date is null");
            return (Criteria) this;
        }

        public Criteria andLatelyCaptureDateIsNotNull() {
            addCriterion("lately_capture_date is not null");
            return (Criteria) this;
        }

        public Criteria andLatelyCaptureDateEqualTo(Date value) {
            addCriterion("lately_capture_date =", value, "latelyCaptureDate");
            return (Criteria) this;
        }

        public Criteria andLatelyCaptureDateNotEqualTo(Date value) {
            addCriterion("lately_capture_date <>", value, "latelyCaptureDate");
            return (Criteria) this;
        }

        public Criteria andLatelyCaptureDateGreaterThan(Date value) {
            addCriterion("lately_capture_date >", value, "latelyCaptureDate");
            return (Criteria) this;
        }

        public Criteria andLatelyCaptureDateGreaterThanOrEqualTo(Date value) {
            addCriterion("lately_capture_date >=", value, "latelyCaptureDate");
            return (Criteria) this;
        }

        public Criteria andLatelyCaptureDateLessThan(Date value) {
            addCriterion("lately_capture_date <", value, "latelyCaptureDate");
            return (Criteria) this;
        }

        public Criteria andLatelyCaptureDateLessThanOrEqualTo(Date value) {
            addCriterion("lately_capture_date <=", value, "latelyCaptureDate");
            return (Criteria) this;
        }

        public Criteria andLatelyCaptureDateIn(List<Date> values) {
            addCriterion("lately_capture_date in", values, "latelyCaptureDate");
            return (Criteria) this;
        }

        public Criteria andLatelyCaptureDateNotIn(List<Date> values) {
            addCriterion("lately_capture_date not in", values, "latelyCaptureDate");
            return (Criteria) this;
        }

        public Criteria andLatelyCaptureDateBetween(Date value1, Date value2) {
            addCriterion("lately_capture_date between", value1, value2, "latelyCaptureDate");
            return (Criteria) this;
        }

        public Criteria andLatelyCaptureDateNotBetween(Date value1, Date value2) {
            addCriterion("lately_capture_date not between", value1, value2, "latelyCaptureDate");
            return (Criteria) this;
        }

        public Criteria andTotalSaleQuantityIsNull() {
            addCriterion("total_sale_quantity is null");
            return (Criteria) this;
        }

        public Criteria andTotalSaleQuantityIsNotNull() {
            addCriterion("total_sale_quantity is not null");
            return (Criteria) this;
        }

        public Criteria andTotalSaleQuantityEqualTo(Integer value) {
            addCriterion("total_sale_quantity =", value, "totalSaleQuantity");
            return (Criteria) this;
        }

        public Criteria andTotalSaleQuantityNotEqualTo(Integer value) {
            addCriterion("total_sale_quantity <>", value, "totalSaleQuantity");
            return (Criteria) this;
        }

        public Criteria andTotalSaleQuantityGreaterThan(Integer value) {
            addCriterion("total_sale_quantity >", value, "totalSaleQuantity");
            return (Criteria) this;
        }

        public Criteria andTotalSaleQuantityGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_sale_quantity >=", value, "totalSaleQuantity");
            return (Criteria) this;
        }

        public Criteria andTotalSaleQuantityLessThan(Integer value) {
            addCriterion("total_sale_quantity <", value, "totalSaleQuantity");
            return (Criteria) this;
        }

        public Criteria andTotalSaleQuantityLessThanOrEqualTo(Integer value) {
            addCriterion("total_sale_quantity <=", value, "totalSaleQuantity");
            return (Criteria) this;
        }

        public Criteria andTotalSaleQuantityIn(List<Integer> values) {
            addCriterion("total_sale_quantity in", values, "totalSaleQuantity");
            return (Criteria) this;
        }

        public Criteria andTotalSaleQuantityNotIn(List<Integer> values) {
            addCriterion("total_sale_quantity not in", values, "totalSaleQuantity");
            return (Criteria) this;
        }

        public Criteria andTotalSaleQuantityBetween(Integer value1, Integer value2) {
            addCriterion("total_sale_quantity between", value1, value2, "totalSaleQuantity");
            return (Criteria) this;
        }

        public Criteria andTotalSaleQuantityNotBetween(Integer value1, Integer value2) {
            addCriterion("total_sale_quantity not between", value1, value2, "totalSaleQuantity");
            return (Criteria) this;
        }

        public Criteria andTotalSaleIsNull() {
            addCriterion("total_sale is null");
            return (Criteria) this;
        }

        public Criteria andTotalSaleIsNotNull() {
            addCriterion("total_sale is not null");
            return (Criteria) this;
        }

        public Criteria andTotalSaleEqualTo(Double value) {
            addCriterion("total_sale =", value, "totalSale");
            return (Criteria) this;
        }

        public Criteria andTotalSaleNotEqualTo(Double value) {
            addCriterion("total_sale <>", value, "totalSale");
            return (Criteria) this;
        }

        public Criteria andTotalSaleGreaterThan(Double value) {
            addCriterion("total_sale >", value, "totalSale");
            return (Criteria) this;
        }

        public Criteria andTotalSaleGreaterThanOrEqualTo(Double value) {
            addCriterion("total_sale >=", value, "totalSale");
            return (Criteria) this;
        }

        public Criteria andTotalSaleLessThan(Double value) {
            addCriterion("total_sale <", value, "totalSale");
            return (Criteria) this;
        }

        public Criteria andTotalSaleLessThanOrEqualTo(Double value) {
            addCriterion("total_sale <=", value, "totalSale");
            return (Criteria) this;
        }

        public Criteria andTotalSaleIn(List<Double> values) {
            addCriterion("total_sale in", values, "totalSale");
            return (Criteria) this;
        }

        public Criteria andTotalSaleNotIn(List<Double> values) {
            addCriterion("total_sale not in", values, "totalSale");
            return (Criteria) this;
        }

        public Criteria andTotalSaleBetween(Double value1, Double value2) {
            addCriterion("total_sale between", value1, value2, "totalSale");
            return (Criteria) this;
        }

        public Criteria andTotalSaleNotBetween(Double value1, Double value2) {
            addCriterion("total_sale not between", value1, value2, "totalSale");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_taobaoshop
     *
     * @mbg.generated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_taobaoshop
     *
     * @mbg.generated
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}