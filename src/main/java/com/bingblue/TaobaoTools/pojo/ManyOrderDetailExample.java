package com.bingblue.TaobaoTools.pojo;

import java.util.ArrayList;
import java.util.List;

public class ManyOrderDetailExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_manyorderdetail
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_manyorderdetail
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_manyorderdetail
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_manyorderdetail
     *
     * @mbg.generated
     */
    public ManyOrderDetailExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_manyorderdetail
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_manyorderdetail
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_manyorderdetail
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_manyorderdetail
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_manyorderdetail
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_manyorderdetail
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_manyorderdetail
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
     * This method corresponds to the database table t_manyorderdetail
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
     * This method corresponds to the database table t_manyorderdetail
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_manyorderdetail
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
     * This class corresponds to the database table t_manyorderdetail
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

        public Criteria andHeadIdIsNull() {
            addCriterion("head_id is null");
            return (Criteria) this;
        }

        public Criteria andHeadIdIsNotNull() {
            addCriterion("head_id is not null");
            return (Criteria) this;
        }

        public Criteria andHeadIdEqualTo(Long value) {
            addCriterion("head_id =", value, "headId");
            return (Criteria) this;
        }

        public Criteria andHeadIdNotEqualTo(Long value) {
            addCriterion("head_id <>", value, "headId");
            return (Criteria) this;
        }

        public Criteria andHeadIdGreaterThan(Long value) {
            addCriterion("head_id >", value, "headId");
            return (Criteria) this;
        }

        public Criteria andHeadIdGreaterThanOrEqualTo(Long value) {
            addCriterion("head_id >=", value, "headId");
            return (Criteria) this;
        }

        public Criteria andHeadIdLessThan(Long value) {
            addCriterion("head_id <", value, "headId");
            return (Criteria) this;
        }

        public Criteria andHeadIdLessThanOrEqualTo(Long value) {
            addCriterion("head_id <=", value, "headId");
            return (Criteria) this;
        }

        public Criteria andHeadIdIn(List<Long> values) {
            addCriterion("head_id in", values, "headId");
            return (Criteria) this;
        }

        public Criteria andHeadIdNotIn(List<Long> values) {
            addCriterion("head_id not in", values, "headId");
            return (Criteria) this;
        }

        public Criteria andHeadIdBetween(Long value1, Long value2) {
            addCriterion("head_id between", value1, value2, "headId");
            return (Criteria) this;
        }

        public Criteria andHeadIdNotBetween(Long value1, Long value2) {
            addCriterion("head_id not between", value1, value2, "headId");
            return (Criteria) this;
        }

        public Criteria andKeywordsIsNull() {
            addCriterion("keywords is null");
            return (Criteria) this;
        }

        public Criteria andKeywordsIsNotNull() {
            addCriterion("keywords is not null");
            return (Criteria) this;
        }

        public Criteria andKeywordsEqualTo(String value) {
            addCriterion("keywords =", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsNotEqualTo(String value) {
            addCriterion("keywords <>", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsGreaterThan(String value) {
            addCriterion("keywords >", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsGreaterThanOrEqualTo(String value) {
            addCriterion("keywords >=", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsLessThan(String value) {
            addCriterion("keywords <", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsLessThanOrEqualTo(String value) {
            addCriterion("keywords <=", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsLike(String value) {
            addCriterion("keywords like", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsNotLike(String value) {
            addCriterion("keywords not like", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsIn(List<String> values) {
            addCriterion("keywords in", values, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsNotIn(List<String> values) {
            addCriterion("keywords not in", values, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsBetween(String value1, String value2) {
            addCriterion("keywords between", value1, value2, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsNotBetween(String value1, String value2) {
            addCriterion("keywords not between", value1, value2, "keywords");
            return (Criteria) this;
        }

        public Criteria andLimitClickQuantityIsNull() {
            addCriterion("limit_click_quantity is null");
            return (Criteria) this;
        }

        public Criteria andLimitClickQuantityIsNotNull() {
            addCriterion("limit_click_quantity is not null");
            return (Criteria) this;
        }

        public Criteria andLimitClickQuantityEqualTo(Integer value) {
            addCriterion("limit_click_quantity =", value, "limitClickQuantity");
            return (Criteria) this;
        }

        public Criteria andLimitClickQuantityNotEqualTo(Integer value) {
            addCriterion("limit_click_quantity <>", value, "limitClickQuantity");
            return (Criteria) this;
        }

        public Criteria andLimitClickQuantityGreaterThan(Integer value) {
            addCriterion("limit_click_quantity >", value, "limitClickQuantity");
            return (Criteria) this;
        }

        public Criteria andLimitClickQuantityGreaterThanOrEqualTo(Integer value) {
            addCriterion("limit_click_quantity >=", value, "limitClickQuantity");
            return (Criteria) this;
        }

        public Criteria andLimitClickQuantityLessThan(Integer value) {
            addCriterion("limit_click_quantity <", value, "limitClickQuantity");
            return (Criteria) this;
        }

        public Criteria andLimitClickQuantityLessThanOrEqualTo(Integer value) {
            addCriterion("limit_click_quantity <=", value, "limitClickQuantity");
            return (Criteria) this;
        }

        public Criteria andLimitClickQuantityIn(List<Integer> values) {
            addCriterion("limit_click_quantity in", values, "limitClickQuantity");
            return (Criteria) this;
        }

        public Criteria andLimitClickQuantityNotIn(List<Integer> values) {
            addCriterion("limit_click_quantity not in", values, "limitClickQuantity");
            return (Criteria) this;
        }

        public Criteria andLimitClickQuantityBetween(Integer value1, Integer value2) {
            addCriterion("limit_click_quantity between", value1, value2, "limitClickQuantity");
            return (Criteria) this;
        }

        public Criteria andLimitClickQuantityNotBetween(Integer value1, Integer value2) {
            addCriterion("limit_click_quantity not between", value1, value2, "limitClickQuantity");
            return (Criteria) this;
        }

        public Criteria andClickQuantityIsNull() {
            addCriterion("click_quantity is null");
            return (Criteria) this;
        }

        public Criteria andClickQuantityIsNotNull() {
            addCriterion("click_quantity is not null");
            return (Criteria) this;
        }

        public Criteria andClickQuantityEqualTo(Integer value) {
            addCriterion("click_quantity =", value, "clickQuantity");
            return (Criteria) this;
        }

        public Criteria andClickQuantityNotEqualTo(Integer value) {
            addCriterion("click_quantity <>", value, "clickQuantity");
            return (Criteria) this;
        }

        public Criteria andClickQuantityGreaterThan(Integer value) {
            addCriterion("click_quantity >", value, "clickQuantity");
            return (Criteria) this;
        }

        public Criteria andClickQuantityGreaterThanOrEqualTo(Integer value) {
            addCriterion("click_quantity >=", value, "clickQuantity");
            return (Criteria) this;
        }

        public Criteria andClickQuantityLessThan(Integer value) {
            addCriterion("click_quantity <", value, "clickQuantity");
            return (Criteria) this;
        }

        public Criteria andClickQuantityLessThanOrEqualTo(Integer value) {
            addCriterion("click_quantity <=", value, "clickQuantity");
            return (Criteria) this;
        }

        public Criteria andClickQuantityIn(List<Integer> values) {
            addCriterion("click_quantity in", values, "clickQuantity");
            return (Criteria) this;
        }

        public Criteria andClickQuantityNotIn(List<Integer> values) {
            addCriterion("click_quantity not in", values, "clickQuantity");
            return (Criteria) this;
        }

        public Criteria andClickQuantityBetween(Integer value1, Integer value2) {
            addCriterion("click_quantity between", value1, value2, "clickQuantity");
            return (Criteria) this;
        }

        public Criteria andClickQuantityNotBetween(Integer value1, Integer value2) {
            addCriterion("click_quantity not between", value1, value2, "clickQuantity");
            return (Criteria) this;
        }

        public Criteria andClickCountIsNull() {
            addCriterion("click_count is null");
            return (Criteria) this;
        }

        public Criteria andClickCountIsNotNull() {
            addCriterion("click_count is not null");
            return (Criteria) this;
        }

        public Criteria andClickCountEqualTo(Integer value) {
            addCriterion("click_count =", value, "clickCount");
            return (Criteria) this;
        }

        public Criteria andClickCountNotEqualTo(Integer value) {
            addCriterion("click_count <>", value, "clickCount");
            return (Criteria) this;
        }

        public Criteria andClickCountGreaterThan(Integer value) {
            addCriterion("click_count >", value, "clickCount");
            return (Criteria) this;
        }

        public Criteria andClickCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("click_count >=", value, "clickCount");
            return (Criteria) this;
        }

        public Criteria andClickCountLessThan(Integer value) {
            addCriterion("click_count <", value, "clickCount");
            return (Criteria) this;
        }

        public Criteria andClickCountLessThanOrEqualTo(Integer value) {
            addCriterion("click_count <=", value, "clickCount");
            return (Criteria) this;
        }

        public Criteria andClickCountIn(List<Integer> values) {
            addCriterion("click_count in", values, "clickCount");
            return (Criteria) this;
        }

        public Criteria andClickCountNotIn(List<Integer> values) {
            addCriterion("click_count not in", values, "clickCount");
            return (Criteria) this;
        }

        public Criteria andClickCountBetween(Integer value1, Integer value2) {
            addCriterion("click_count between", value1, value2, "clickCount");
            return (Criteria) this;
        }

        public Criteria andClickCountNotBetween(Integer value1, Integer value2) {
            addCriterion("click_count not between", value1, value2, "clickCount");
            return (Criteria) this;
        }

        public Criteria andProductUrlIsNull() {
            addCriterion("product_url is null");
            return (Criteria) this;
        }

        public Criteria andProductUrlIsNotNull() {
            addCriterion("product_url is not null");
            return (Criteria) this;
        }

        public Criteria andProductUrlEqualTo(String value) {
            addCriterion("product_url =", value, "productUrl");
            return (Criteria) this;
        }

        public Criteria andProductUrlNotEqualTo(String value) {
            addCriterion("product_url <>", value, "productUrl");
            return (Criteria) this;
        }

        public Criteria andProductUrlGreaterThan(String value) {
            addCriterion("product_url >", value, "productUrl");
            return (Criteria) this;
        }

        public Criteria andProductUrlGreaterThanOrEqualTo(String value) {
            addCriterion("product_url >=", value, "productUrl");
            return (Criteria) this;
        }

        public Criteria andProductUrlLessThan(String value) {
            addCriterion("product_url <", value, "productUrl");
            return (Criteria) this;
        }

        public Criteria andProductUrlLessThanOrEqualTo(String value) {
            addCriterion("product_url <=", value, "productUrl");
            return (Criteria) this;
        }

        public Criteria andProductUrlLike(String value) {
            addCriterion("product_url like", value, "productUrl");
            return (Criteria) this;
        }

        public Criteria andProductUrlNotLike(String value) {
            addCriterion("product_url not like", value, "productUrl");
            return (Criteria) this;
        }

        public Criteria andProductUrlIn(List<String> values) {
            addCriterion("product_url in", values, "productUrl");
            return (Criteria) this;
        }

        public Criteria andProductUrlNotIn(List<String> values) {
            addCriterion("product_url not in", values, "productUrl");
            return (Criteria) this;
        }

        public Criteria andProductUrlBetween(String value1, String value2) {
            addCriterion("product_url between", value1, value2, "productUrl");
            return (Criteria) this;
        }

        public Criteria andProductUrlNotBetween(String value1, String value2) {
            addCriterion("product_url not between", value1, value2, "productUrl");
            return (Criteria) this;
        }

        public Criteria andTaobaoWordIdIsNull() {
            addCriterion("taobao_word_id is null");
            return (Criteria) this;
        }

        public Criteria andTaobaoWordIdIsNotNull() {
            addCriterion("taobao_word_id is not null");
            return (Criteria) this;
        }

        public Criteria andTaobaoWordIdEqualTo(Long value) {
            addCriterion("taobao_word_id =", value, "taobaoWordId");
            return (Criteria) this;
        }

        public Criteria andTaobaoWordIdNotEqualTo(Long value) {
            addCriterion("taobao_word_id <>", value, "taobaoWordId");
            return (Criteria) this;
        }

        public Criteria andTaobaoWordIdGreaterThan(Long value) {
            addCriterion("taobao_word_id >", value, "taobaoWordId");
            return (Criteria) this;
        }

        public Criteria andTaobaoWordIdGreaterThanOrEqualTo(Long value) {
            addCriterion("taobao_word_id >=", value, "taobaoWordId");
            return (Criteria) this;
        }

        public Criteria andTaobaoWordIdLessThan(Long value) {
            addCriterion("taobao_word_id <", value, "taobaoWordId");
            return (Criteria) this;
        }

        public Criteria andTaobaoWordIdLessThanOrEqualTo(Long value) {
            addCriterion("taobao_word_id <=", value, "taobaoWordId");
            return (Criteria) this;
        }

        public Criteria andTaobaoWordIdIn(List<Long> values) {
            addCriterion("taobao_word_id in", values, "taobaoWordId");
            return (Criteria) this;
        }

        public Criteria andTaobaoWordIdNotIn(List<Long> values) {
            addCriterion("taobao_word_id not in", values, "taobaoWordId");
            return (Criteria) this;
        }

        public Criteria andTaobaoWordIdBetween(Long value1, Long value2) {
            addCriterion("taobao_word_id between", value1, value2, "taobaoWordId");
            return (Criteria) this;
        }

        public Criteria andTaobaoWordIdNotBetween(Long value1, Long value2) {
            addCriterion("taobao_word_id not between", value1, value2, "taobaoWordId");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_manyorderdetail
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
     * This class corresponds to the database table t_manyorderdetail
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