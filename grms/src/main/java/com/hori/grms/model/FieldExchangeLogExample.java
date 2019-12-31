/*
 * FieldExchangeLogExample.java
 * Copyright(C) 20xx-2015 xxxxxx公司
 * All rights reserved.
 * -----------------------------------------------
 * 2018-08-22 Created
 */
package com.hori.grms.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FieldExchangeLogExample {

    protected String orderByClause;
    protected boolean distinct;
    protected List<Criteria> oredCriteria;

    public FieldExchangeLogExample() {
        oredCriteria = new ArrayList<Criteria>();
    }
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }
    public String getOrderByClause() {
        return orderByClause;
    }
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }
    public boolean isDistinct() {
        return distinct;
    }
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * 场地置换记录
     * 
     * @author 
     * @version 1.0 2018-08-22
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
        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }
        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }
        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }
        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }
        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }
        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }
        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }
        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }
        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }
        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }
        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }
        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }
        public Criteria andProjectActionCodeIsNull() {
            addCriterion("project_action_code is null");
            return (Criteria) this;
        }
        public Criteria andProjectActionCodeIsNotNull() {
            addCriterion("project_action_code is not null");
            return (Criteria) this;
        }
        public Criteria andProjectActionCodeEqualTo(String value) {
            addCriterion("project_action_code =", value, "projectActionCode");
            return (Criteria) this;
        }
        public Criteria andProjectActionCodeNotEqualTo(String value) {
            addCriterion("project_action_code <>", value, "projectActionCode");
            return (Criteria) this;
        }
        public Criteria andProjectActionCodeGreaterThan(String value) {
            addCriterion("project_action_code >", value, "projectActionCode");
            return (Criteria) this;
        }
        public Criteria andProjectActionCodeGreaterThanOrEqualTo(String value) {
            addCriterion("project_action_code >=", value, "projectActionCode");
            return (Criteria) this;
        }
        public Criteria andProjectActionCodeLessThan(String value) {
            addCriterion("project_action_code <", value, "projectActionCode");
            return (Criteria) this;
        }
        public Criteria andProjectActionCodeLessThanOrEqualTo(String value) {
            addCriterion("project_action_code <=", value, "projectActionCode");
            return (Criteria) this;
        }
        public Criteria andProjectActionCodeLike(String value) {
            addCriterion("project_action_code like", value, "projectActionCode");
            return (Criteria) this;
        }
        public Criteria andProjectActionCodeNotLike(String value) {
            addCriterion("project_action_code not like", value, "projectActionCode");
            return (Criteria) this;
        }
        public Criteria andProjectActionCodeIn(List<String> values) {
            addCriterion("project_action_code in", values, "projectActionCode");
            return (Criteria) this;
        }
        public Criteria andProjectActionCodeNotIn(List<String> values) {
            addCriterion("project_action_code not in", values, "projectActionCode");
            return (Criteria) this;
        }
        public Criteria andProjectActionCodeBetween(String value1, String value2) {
            addCriterion("project_action_code between", value1, value2, "projectActionCode");
            return (Criteria) this;
        }
        public Criteria andProjectActionCodeNotBetween(String value1, String value2) {
            addCriterion("project_action_code not between", value1, value2, "projectActionCode");
            return (Criteria) this;
        }
        public Criteria andCreaterAccountIsNull() {
            addCriterion("creater_account is null");
            return (Criteria) this;
        }
        public Criteria andCreaterAccountIsNotNull() {
            addCriterion("creater_account is not null");
            return (Criteria) this;
        }
        public Criteria andCreaterAccountEqualTo(String value) {
            addCriterion("creater_account =", value, "createrAccount");
            return (Criteria) this;
        }
        public Criteria andCreaterAccountNotEqualTo(String value) {
            addCriterion("creater_account <>", value, "createrAccount");
            return (Criteria) this;
        }
        public Criteria andCreaterAccountGreaterThan(String value) {
            addCriterion("creater_account >", value, "createrAccount");
            return (Criteria) this;
        }
        public Criteria andCreaterAccountGreaterThanOrEqualTo(String value) {
            addCriterion("creater_account >=", value, "createrAccount");
            return (Criteria) this;
        }
        public Criteria andCreaterAccountLessThan(String value) {
            addCriterion("creater_account <", value, "createrAccount");
            return (Criteria) this;
        }
        public Criteria andCreaterAccountLessThanOrEqualTo(String value) {
            addCriterion("creater_account <=", value, "createrAccount");
            return (Criteria) this;
        }
        public Criteria andCreaterAccountLike(String value) {
            addCriterion("creater_account like", value, "createrAccount");
            return (Criteria) this;
        }
        public Criteria andCreaterAccountNotLike(String value) {
            addCriterion("creater_account not like", value, "createrAccount");
            return (Criteria) this;
        }
        public Criteria andCreaterAccountIn(List<String> values) {
            addCriterion("creater_account in", values, "createrAccount");
            return (Criteria) this;
        }
        public Criteria andCreaterAccountNotIn(List<String> values) {
            addCriterion("creater_account not in", values, "createrAccount");
            return (Criteria) this;
        }
        public Criteria andCreaterAccountBetween(String value1, String value2) {
            addCriterion("creater_account between", value1, value2, "createrAccount");
            return (Criteria) this;
        }
        public Criteria andCreaterAccountNotBetween(String value1, String value2) {
            addCriterion("creater_account not between", value1, value2, "createrAccount");
            return (Criteria) this;
        }
        public Criteria andCreaterNameIsNull() {
            addCriterion("creater_name is null");
            return (Criteria) this;
        }
        public Criteria andCreaterNameIsNotNull() {
            addCriterion("creater_name is not null");
            return (Criteria) this;
        }
        public Criteria andCreaterNameEqualTo(String value) {
            addCriterion("creater_name =", value, "createrName");
            return (Criteria) this;
        }
        public Criteria andCreaterNameNotEqualTo(String value) {
            addCriterion("creater_name <>", value, "createrName");
            return (Criteria) this;
        }
        public Criteria andCreaterNameGreaterThan(String value) {
            addCriterion("creater_name >", value, "createrName");
            return (Criteria) this;
        }
        public Criteria andCreaterNameGreaterThanOrEqualTo(String value) {
            addCriterion("creater_name >=", value, "createrName");
            return (Criteria) this;
        }
        public Criteria andCreaterNameLessThan(String value) {
            addCriterion("creater_name <", value, "createrName");
            return (Criteria) this;
        }
        public Criteria andCreaterNameLessThanOrEqualTo(String value) {
            addCriterion("creater_name <=", value, "createrName");
            return (Criteria) this;
        }
        public Criteria andCreaterNameLike(String value) {
            addCriterion("creater_name like", value, "createrName");
            return (Criteria) this;
        }
        public Criteria andCreaterNameNotLike(String value) {
            addCriterion("creater_name not like", value, "createrName");
            return (Criteria) this;
        }
        public Criteria andCreaterNameIn(List<String> values) {
            addCriterion("creater_name in", values, "createrName");
            return (Criteria) this;
        }
        public Criteria andCreaterNameNotIn(List<String> values) {
            addCriterion("creater_name not in", values, "createrName");
            return (Criteria) this;
        }
        public Criteria andCreaterNameBetween(String value1, String value2) {
            addCriterion("creater_name between", value1, value2, "createrName");
            return (Criteria) this;
        }
        public Criteria andCreaterNameNotBetween(String value1, String value2) {
            addCriterion("creater_name not between", value1, value2, "createrName");
            return (Criteria) this;
        }
        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }
        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }
        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }
        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }
        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }
        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }
        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }
        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }
        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }
        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }
        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }
        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {


        protected Criteria() {
            super();
        }
    }

    /**
     * 场地置换记录
     * 
     * @author 
     * @version 1.0 2018-08-22
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