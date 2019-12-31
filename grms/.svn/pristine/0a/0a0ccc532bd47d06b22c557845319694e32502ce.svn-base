/*
 * ProjectActionExample.java
 * Copyright(C) 20xx-2015 xxxxxx公司
 * All rights reserved.
 * -----------------------------------------------
 * 2018-08-10 Created
 */
package com.hori.grms.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectActionExample {

    protected String orderByClause;
    protected boolean distinct;
    protected List<Criteria> oredCriteria;

    public ProjectActionExample() {
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
     * 项目执行清单
     * 
     * @author 
     * @version 1.0 2018-08-10
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
        public Criteria andProjectCodeIsNull() {
            addCriterion("project_code is null");
            return (Criteria) this;
        }
        public Criteria andProjectCodeIsNotNull() {
            addCriterion("project_code is not null");
            return (Criteria) this;
        }
        public Criteria andProjectCodeEqualTo(String value) {
            addCriterion("project_code =", value, "projectCode");
            return (Criteria) this;
        }
        public Criteria andProjectCodeNotEqualTo(String value) {
            addCriterion("project_code <>", value, "projectCode");
            return (Criteria) this;
        }
        public Criteria andProjectCodeGreaterThan(String value) {
            addCriterion("project_code >", value, "projectCode");
            return (Criteria) this;
        }
        public Criteria andProjectCodeGreaterThanOrEqualTo(String value) {
            addCriterion("project_code >=", value, "projectCode");
            return (Criteria) this;
        }
        public Criteria andProjectCodeLessThan(String value) {
            addCriterion("project_code <", value, "projectCode");
            return (Criteria) this;
        }
        public Criteria andProjectCodeLessThanOrEqualTo(String value) {
            addCriterion("project_code <=", value, "projectCode");
            return (Criteria) this;
        }
        public Criteria andProjectCodeLike(String value) {
            addCriterion("project_code like", value, "projectCode");
            return (Criteria) this;
        }
        public Criteria andProjectCodeNotLike(String value) {
            addCriterion("project_code not like", value, "projectCode");
            return (Criteria) this;
        }
        public Criteria andProjectCodeIn(List<String> values) {
            addCriterion("project_code in", values, "projectCode");
            return (Criteria) this;
        }
        public Criteria andProjectCodeNotIn(List<String> values) {
            addCriterion("project_code not in", values, "projectCode");
            return (Criteria) this;
        }
        public Criteria andProjectCodeBetween(String value1, String value2) {
            addCriterion("project_code between", value1, value2, "projectCode");
            return (Criteria) this;
        }
        public Criteria andProjectCodeNotBetween(String value1, String value2) {
            addCriterion("project_code not between", value1, value2, "projectCode");
            return (Criteria) this;
        }
        public Criteria andProjectNameIsNull() {
            addCriterion("project_name is null");
            return (Criteria) this;
        }
        public Criteria andProjectNameIsNotNull() {
            addCriterion("project_name is not null");
            return (Criteria) this;
        }
        public Criteria andProjectNameEqualTo(String value) {
            addCriterion("project_name =", value, "projectName");
            return (Criteria) this;
        }
        public Criteria andProjectNameNotEqualTo(String value) {
            addCriterion("project_name <>", value, "projectName");
            return (Criteria) this;
        }
        public Criteria andProjectNameGreaterThan(String value) {
            addCriterion("project_name >", value, "projectName");
            return (Criteria) this;
        }
        public Criteria andProjectNameGreaterThanOrEqualTo(String value) {
            addCriterion("project_name >=", value, "projectName");
            return (Criteria) this;
        }
        public Criteria andProjectNameLessThan(String value) {
            addCriterion("project_name <", value, "projectName");
            return (Criteria) this;
        }
        public Criteria andProjectNameLessThanOrEqualTo(String value) {
            addCriterion("project_name <=", value, "projectName");
            return (Criteria) this;
        }
        public Criteria andProjectNameLike(String value) {
            addCriterion("project_name like", value, "projectName");
            return (Criteria) this;
        }
        public Criteria andProjectNameNotLike(String value) {
            addCriterion("project_name not like", value, "projectName");
            return (Criteria) this;
        }
        public Criteria andProjectNameIn(List<String> values) {
            addCriterion("project_name in", values, "projectName");
            return (Criteria) this;
        }
        public Criteria andProjectNameNotIn(List<String> values) {
            addCriterion("project_name not in", values, "projectName");
            return (Criteria) this;
        }
        public Criteria andProjectNameBetween(String value1, String value2) {
            addCriterion("project_name between", value1, value2, "projectName");
            return (Criteria) this;
        }
        public Criteria andProjectNameNotBetween(String value1, String value2) {
            addCriterion("project_name not between", value1, value2, "projectName");
            return (Criteria) this;
        }
        public Criteria andProjectProductIdIsNull() {
            addCriterion("project_product_id is null");
            return (Criteria) this;
        }
        public Criteria andProjectProductIdIsNotNull() {
            addCriterion("project_product_id is not null");
            return (Criteria) this;
        }
        public Criteria andProjectProductIdEqualTo(String value) {
            addCriterion("project_product_id =", value, "projectProductId");
            return (Criteria) this;
        }
        public Criteria andProjectProductIdNotEqualTo(String value) {
            addCriterion("project_product_id <>", value, "projectProductId");
            return (Criteria) this;
        }
        public Criteria andProjectProductIdGreaterThan(String value) {
            addCriterion("project_product_id >", value, "projectProductId");
            return (Criteria) this;
        }
        public Criteria andProjectProductIdGreaterThanOrEqualTo(String value) {
            addCriterion("project_product_id >=", value, "projectProductId");
            return (Criteria) this;
        }
        public Criteria andProjectProductIdLessThan(String value) {
            addCriterion("project_product_id <", value, "projectProductId");
            return (Criteria) this;
        }
        public Criteria andProjectProductIdLessThanOrEqualTo(String value) {
            addCriterion("project_product_id <=", value, "projectProductId");
            return (Criteria) this;
        }
        public Criteria andProjectProductIdLike(String value) {
            addCriterion("project_product_id like", value, "projectProductId");
            return (Criteria) this;
        }
        public Criteria andProjectProductIdNotLike(String value) {
            addCriterion("project_product_id not like", value, "projectProductId");
            return (Criteria) this;
        }
        public Criteria andProjectProductIdIn(List<String> values) {
            addCriterion("project_product_id in", values, "projectProductId");
            return (Criteria) this;
        }
        public Criteria andProjectProductIdNotIn(List<String> values) {
            addCriterion("project_product_id not in", values, "projectProductId");
            return (Criteria) this;
        }
        public Criteria andProjectProductIdBetween(String value1, String value2) {
            addCriterion("project_product_id between", value1, value2, "projectProductId");
            return (Criteria) this;
        }
        public Criteria andProjectProductIdNotBetween(String value1, String value2) {
            addCriterion("project_product_id not between", value1, value2, "projectProductId");
            return (Criteria) this;
        }
        public Criteria andDepartmentIdIsNull() {
            addCriterion("department_id is null");
            return (Criteria) this;
        }
        public Criteria andDepartmentIdIsNotNull() {
            addCriterion("department_id is not null");
            return (Criteria) this;
        }
        public Criteria andDepartmentIdEqualTo(String value) {
            addCriterion("department_id =", value, "departmentId");
            return (Criteria) this;
        }
        public Criteria andDepartmentIdNotEqualTo(String value) {
            addCriterion("department_id <>", value, "departmentId");
            return (Criteria) this;
        }
        public Criteria andDepartmentIdGreaterThan(String value) {
            addCriterion("department_id >", value, "departmentId");
            return (Criteria) this;
        }
        public Criteria andDepartmentIdGreaterThanOrEqualTo(String value) {
            addCriterion("department_id >=", value, "departmentId");
            return (Criteria) this;
        }
        public Criteria andDepartmentIdLessThan(String value) {
            addCriterion("department_id <", value, "departmentId");
            return (Criteria) this;
        }
        public Criteria andDepartmentIdLessThanOrEqualTo(String value) {
            addCriterion("department_id <=", value, "departmentId");
            return (Criteria) this;
        }
        public Criteria andDepartmentIdLike(String value) {
            addCriterion("department_id like", value, "departmentId");
            return (Criteria) this;
        }
        public Criteria andDepartmentIdNotLike(String value) {
            addCriterion("department_id not like", value, "departmentId");
            return (Criteria) this;
        }
        public Criteria andDepartmentIdIn(List<String> values) {
            addCriterion("department_id in", values, "departmentId");
            return (Criteria) this;
        }
        public Criteria andDepartmentIdNotIn(List<String> values) {
            addCriterion("department_id not in", values, "departmentId");
            return (Criteria) this;
        }
        public Criteria andDepartmentIdBetween(String value1, String value2) {
            addCriterion("department_id between", value1, value2, "departmentId");
            return (Criteria) this;
        }
        public Criteria andDepartmentIdNotBetween(String value1, String value2) {
            addCriterion("department_id not between", value1, value2, "departmentId");
            return (Criteria) this;
        }
        public Criteria andDepartmentNameIsNull() {
            addCriterion("department_name is null");
            return (Criteria) this;
        }
        public Criteria andDepartmentNameIsNotNull() {
            addCriterion("department_name is not null");
            return (Criteria) this;
        }
        public Criteria andDepartmentNameEqualTo(String value) {
            addCriterion("department_name =", value, "departmentName");
            return (Criteria) this;
        }
        public Criteria andDepartmentNameNotEqualTo(String value) {
            addCriterion("department_name <>", value, "departmentName");
            return (Criteria) this;
        }
        public Criteria andDepartmentNameGreaterThan(String value) {
            addCriterion("department_name >", value, "departmentName");
            return (Criteria) this;
        }
        public Criteria andDepartmentNameGreaterThanOrEqualTo(String value) {
            addCriterion("department_name >=", value, "departmentName");
            return (Criteria) this;
        }
        public Criteria andDepartmentNameLessThan(String value) {
            addCriterion("department_name <", value, "departmentName");
            return (Criteria) this;
        }
        public Criteria andDepartmentNameLessThanOrEqualTo(String value) {
            addCriterion("department_name <=", value, "departmentName");
            return (Criteria) this;
        }
        public Criteria andDepartmentNameLike(String value) {
            addCriterion("department_name like", value, "departmentName");
            return (Criteria) this;
        }
        public Criteria andDepartmentNameNotLike(String value) {
            addCriterion("department_name not like", value, "departmentName");
            return (Criteria) this;
        }
        public Criteria andDepartmentNameIn(List<String> values) {
            addCriterion("department_name in", values, "departmentName");
            return (Criteria) this;
        }
        public Criteria andDepartmentNameNotIn(List<String> values) {
            addCriterion("department_name not in", values, "departmentName");
            return (Criteria) this;
        }
        public Criteria andDepartmentNameBetween(String value1, String value2) {
            addCriterion("department_name between", value1, value2, "departmentName");
            return (Criteria) this;
        }
        public Criteria andDepartmentNameNotBetween(String value1, String value2) {
            addCriterion("department_name not between", value1, value2, "departmentName");
            return (Criteria) this;
        }
        public Criteria andBusinessActionStatusIsNull() {
            addCriterion("business_action_status is null");
            return (Criteria) this;
        }
        public Criteria andBusinessActionStatusIsNotNull() {
            addCriterion("business_action_status is not null");
            return (Criteria) this;
        }
        public Criteria andBusinessActionStatusEqualTo(Integer value) {
            addCriterion("business_action_status =", value, "businessActionStatus");
            return (Criteria) this;
        }
        public Criteria andBusinessActionStatusNotEqualTo(Integer value) {
            addCriterion("business_action_status <>", value, "businessActionStatus");
            return (Criteria) this;
        }
        public Criteria andBusinessActionStatusGreaterThan(Integer value) {
            addCriterion("business_action_status >", value, "businessActionStatus");
            return (Criteria) this;
        }
        public Criteria andBusinessActionStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("business_action_status >=", value, "businessActionStatus");
            return (Criteria) this;
        }
        public Criteria andBusinessActionStatusLessThan(Integer value) {
            addCriterion("business_action_status <", value, "businessActionStatus");
            return (Criteria) this;
        }
        public Criteria andBusinessActionStatusLessThanOrEqualTo(Integer value) {
            addCriterion("business_action_status <=", value, "businessActionStatus");
            return (Criteria) this;
        }
        public Criteria andBusinessActionStatusIn(List<Integer> values) {
            addCriterion("business_action_status in", values, "businessActionStatus");
            return (Criteria) this;
        }
        public Criteria andBusinessActionStatusNotIn(List<Integer> values) {
            addCriterion("business_action_status not in", values, "businessActionStatus");
            return (Criteria) this;
        }
        public Criteria andBusinessActionStatusBetween(Integer value1, Integer value2) {
            addCriterion("business_action_status between", value1, value2, "businessActionStatus");
            return (Criteria) this;
        }
        public Criteria andBusinessActionStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("business_action_status not between", value1, value2, "businessActionStatus");
            return (Criteria) this;
        }
        public Criteria andActionStatusIsNull() {
            addCriterion("action_status is null");
            return (Criteria) this;
        }
        public Criteria andActionStatusIsNotNull() {
            addCriterion("action_status is not null");
            return (Criteria) this;
        }
        public Criteria andActionStatusEqualTo(Integer value) {
            addCriterion("action_status =", value, "actionStatus");
            return (Criteria) this;
        }
        public Criteria andActionStatusNotEqualTo(Integer value) {
            addCriterion("action_status <>", value, "actionStatus");
            return (Criteria) this;
        }
        public Criteria andActionStatusGreaterThan(Integer value) {
            addCriterion("action_status >", value, "actionStatus");
            return (Criteria) this;
        }
        public Criteria andActionStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("action_status >=", value, "actionStatus");
            return (Criteria) this;
        }
        public Criteria andActionStatusLessThan(Integer value) {
            addCriterion("action_status <", value, "actionStatus");
            return (Criteria) this;
        }
        public Criteria andActionStatusLessThanOrEqualTo(Integer value) {
            addCriterion("action_status <=", value, "actionStatus");
            return (Criteria) this;
        }
        public Criteria andActionStatusIn(List<Integer> values) {
            addCriterion("action_status in", values, "actionStatus");
            return (Criteria) this;
        }
        public Criteria andActionStatusNotIn(List<Integer> values) {
            addCriterion("action_status not in", values, "actionStatus");
            return (Criteria) this;
        }
        public Criteria andActionStatusBetween(Integer value1, Integer value2) {
            addCriterion("action_status between", value1, value2, "actionStatus");
            return (Criteria) this;
        }
        public Criteria andActionStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("action_status not between", value1, value2, "actionStatus");
            return (Criteria) this;
        }
        public Criteria andActionCodeIsNull() {
            addCriterion("action_code is null");
            return (Criteria) this;
        }
        public Criteria andActionCodeIsNotNull() {
            addCriterion("action_code is not null");
            return (Criteria) this;
        }
        public Criteria andActionCodeEqualTo(String value) {
            addCriterion("action_code =", value, "actionCode");
            return (Criteria) this;
        }
        public Criteria andActionCodeNotEqualTo(String value) {
            addCriterion("action_code <>", value, "actionCode");
            return (Criteria) this;
        }
        public Criteria andActionCodeGreaterThan(String value) {
            addCriterion("action_code >", value, "actionCode");
            return (Criteria) this;
        }
        public Criteria andActionCodeGreaterThanOrEqualTo(String value) {
            addCriterion("action_code >=", value, "actionCode");
            return (Criteria) this;
        }
        public Criteria andActionCodeLessThan(String value) {
            addCriterion("action_code <", value, "actionCode");
            return (Criteria) this;
        }
        public Criteria andActionCodeLessThanOrEqualTo(String value) {
            addCriterion("action_code <=", value, "actionCode");
            return (Criteria) this;
        }
        public Criteria andActionCodeLike(String value) {
            addCriterion("action_code like", value, "actionCode");
            return (Criteria) this;
        }
        public Criteria andActionCodeNotLike(String value) {
            addCriterion("action_code not like", value, "actionCode");
            return (Criteria) this;
        }
        public Criteria andActionCodeIn(List<String> values) {
            addCriterion("action_code in", values, "actionCode");
            return (Criteria) this;
        }
        public Criteria andActionCodeNotIn(List<String> values) {
            addCriterion("action_code not in", values, "actionCode");
            return (Criteria) this;
        }
        public Criteria andActionCodeBetween(String value1, String value2) {
            addCriterion("action_code between", value1, value2, "actionCode");
            return (Criteria) this;
        }
        public Criteria andActionCodeNotBetween(String value1, String value2) {
            addCriterion("action_code not between", value1, value2, "actionCode");
            return (Criteria) this;
        }
        public Criteria andExceptionStatusIsNull() {
            addCriterion("exception_status is null");
            return (Criteria) this;
        }
        public Criteria andExceptionStatusIsNotNull() {
            addCriterion("exception_status is not null");
            return (Criteria) this;
        }
        public Criteria andExceptionStatusEqualTo(Integer value) {
            addCriterion("exception_status =", value, "exceptionStatus");
            return (Criteria) this;
        }
        public Criteria andExceptionStatusNotEqualTo(Integer value) {
            addCriterion("exception_status <>", value, "exceptionStatus");
            return (Criteria) this;
        }
        public Criteria andExceptionStatusGreaterThan(Integer value) {
            addCriterion("exception_status >", value, "exceptionStatus");
            return (Criteria) this;
        }
        public Criteria andExceptionStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("exception_status >=", value, "exceptionStatus");
            return (Criteria) this;
        }
        public Criteria andExceptionStatusLessThan(Integer value) {
            addCriterion("exception_status <", value, "exceptionStatus");
            return (Criteria) this;
        }
        public Criteria andExceptionStatusLessThanOrEqualTo(Integer value) {
            addCriterion("exception_status <=", value, "exceptionStatus");
            return (Criteria) this;
        }
        public Criteria andExceptionStatusIn(List<Integer> values) {
            addCriterion("exception_status in", values, "exceptionStatus");
            return (Criteria) this;
        }
        public Criteria andExceptionStatusNotIn(List<Integer> values) {
            addCriterion("exception_status not in", values, "exceptionStatus");
            return (Criteria) this;
        }
        public Criteria andExceptionStatusBetween(Integer value1, Integer value2) {
            addCriterion("exception_status between", value1, value2, "exceptionStatus");
            return (Criteria) this;
        }
        public Criteria andExceptionStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("exception_status not between", value1, value2, "exceptionStatus");
            return (Criteria) this;
        }
        public Criteria andExceptionResultIsNull() {
            addCriterion("exception_result is null");
            return (Criteria) this;
        }
        public Criteria andExceptionResultIsNotNull() {
            addCriterion("exception_result is not null");
            return (Criteria) this;
        }
        public Criteria andExceptionResultEqualTo(Integer value) {
            addCriterion("exception_result =", value, "exceptionResult");
            return (Criteria) this;
        }
        public Criteria andExceptionResultNotEqualTo(Integer value) {
            addCriterion("exception_result <>", value, "exceptionResult");
            return (Criteria) this;
        }
        public Criteria andExceptionResultGreaterThan(Integer value) {
            addCriterion("exception_result >", value, "exceptionResult");
            return (Criteria) this;
        }
        public Criteria andExceptionResultGreaterThanOrEqualTo(Integer value) {
            addCriterion("exception_result >=", value, "exceptionResult");
            return (Criteria) this;
        }
        public Criteria andExceptionResultLessThan(Integer value) {
            addCriterion("exception_result <", value, "exceptionResult");
            return (Criteria) this;
        }
        public Criteria andExceptionResultLessThanOrEqualTo(Integer value) {
            addCriterion("exception_result <=", value, "exceptionResult");
            return (Criteria) this;
        }
        public Criteria andExceptionResultIn(List<Integer> values) {
            addCriterion("exception_result in", values, "exceptionResult");
            return (Criteria) this;
        }
        public Criteria andExceptionResultNotIn(List<Integer> values) {
            addCriterion("exception_result not in", values, "exceptionResult");
            return (Criteria) this;
        }
        public Criteria andExceptionResultBetween(Integer value1, Integer value2) {
            addCriterion("exception_result between", value1, value2, "exceptionResult");
            return (Criteria) this;
        }
        public Criteria andExceptionResultNotBetween(Integer value1, Integer value2) {
            addCriterion("exception_result not between", value1, value2, "exceptionResult");
            return (Criteria) this;
        }
        public Criteria andReceiveAccountIsNull() {
            addCriterion("receive_account is null");
            return (Criteria) this;
        }
        public Criteria andReceiveAccountIsNotNull() {
            addCriterion("receive_account is not null");
            return (Criteria) this;
        }
        public Criteria andReceiveAccountEqualTo(String value) {
            addCriterion("receive_account =", value, "receiveAccount");
            return (Criteria) this;
        }
        public Criteria andReceiveAccountNotEqualTo(String value) {
            addCriterion("receive_account <>", value, "receiveAccount");
            return (Criteria) this;
        }
        public Criteria andReceiveAccountGreaterThan(String value) {
            addCriterion("receive_account >", value, "receiveAccount");
            return (Criteria) this;
        }
        public Criteria andReceiveAccountGreaterThanOrEqualTo(String value) {
            addCriterion("receive_account >=", value, "receiveAccount");
            return (Criteria) this;
        }
        public Criteria andReceiveAccountLessThan(String value) {
            addCriterion("receive_account <", value, "receiveAccount");
            return (Criteria) this;
        }
        public Criteria andReceiveAccountLessThanOrEqualTo(String value) {
            addCriterion("receive_account <=", value, "receiveAccount");
            return (Criteria) this;
        }
        public Criteria andReceiveAccountLike(String value) {
            addCriterion("receive_account like", value, "receiveAccount");
            return (Criteria) this;
        }
        public Criteria andReceiveAccountNotLike(String value) {
            addCriterion("receive_account not like", value, "receiveAccount");
            return (Criteria) this;
        }
        public Criteria andReceiveAccountIn(List<String> values) {
            addCriterion("receive_account in", values, "receiveAccount");
            return (Criteria) this;
        }
        public Criteria andReceiveAccountNotIn(List<String> values) {
            addCriterion("receive_account not in", values, "receiveAccount");
            return (Criteria) this;
        }
        public Criteria andReceiveAccountBetween(String value1, String value2) {
            addCriterion("receive_account between", value1, value2, "receiveAccount");
            return (Criteria) this;
        }
        public Criteria andReceiveAccountNotBetween(String value1, String value2) {
            addCriterion("receive_account not between", value1, value2, "receiveAccount");
            return (Criteria) this;
        }
        public Criteria andReceiveNameIsNull() {
            addCriterion("receive_name is null");
            return (Criteria) this;
        }
        public Criteria andReceiveNameIsNotNull() {
            addCriterion("receive_name is not null");
            return (Criteria) this;
        }
        public Criteria andReceiveNameEqualTo(String value) {
            addCriterion("receive_name =", value, "receiveName");
            return (Criteria) this;
        }
        public Criteria andReceiveNameNotEqualTo(String value) {
            addCriterion("receive_name <>", value, "receiveName");
            return (Criteria) this;
        }
        public Criteria andReceiveNameGreaterThan(String value) {
            addCriterion("receive_name >", value, "receiveName");
            return (Criteria) this;
        }
        public Criteria andReceiveNameGreaterThanOrEqualTo(String value) {
            addCriterion("receive_name >=", value, "receiveName");
            return (Criteria) this;
        }
        public Criteria andReceiveNameLessThan(String value) {
            addCriterion("receive_name <", value, "receiveName");
            return (Criteria) this;
        }
        public Criteria andReceiveNameLessThanOrEqualTo(String value) {
            addCriterion("receive_name <=", value, "receiveName");
            return (Criteria) this;
        }
        public Criteria andReceiveNameLike(String value) {
            addCriterion("receive_name like", value, "receiveName");
            return (Criteria) this;
        }
        public Criteria andReceiveNameNotLike(String value) {
            addCriterion("receive_name not like", value, "receiveName");
            return (Criteria) this;
        }
        public Criteria andReceiveNameIn(List<String> values) {
            addCriterion("receive_name in", values, "receiveName");
            return (Criteria) this;
        }
        public Criteria andReceiveNameNotIn(List<String> values) {
            addCriterion("receive_name not in", values, "receiveName");
            return (Criteria) this;
        }
        public Criteria andReceiveNameBetween(String value1, String value2) {
            addCriterion("receive_name between", value1, value2, "receiveName");
            return (Criteria) this;
        }
        public Criteria andReceiveNameNotBetween(String value1, String value2) {
            addCriterion("receive_name not between", value1, value2, "receiveName");
            return (Criteria) this;
        }
        public Criteria andRecevieTimeIsNull() {
            addCriterion("recevie_time is null");
            return (Criteria) this;
        }
        public Criteria andRecevieTimeIsNotNull() {
            addCriterion("recevie_time is not null");
            return (Criteria) this;
        }
        public Criteria andRecevieTimeEqualTo(Date value) {
            addCriterion("recevie_time =", value, "recevieTime");
            return (Criteria) this;
        }
        public Criteria andRecevieTimeNotEqualTo(Date value) {
            addCriterion("recevie_time <>", value, "recevieTime");
            return (Criteria) this;
        }
        public Criteria andRecevieTimeGreaterThan(Date value) {
            addCriterion("recevie_time >", value, "recevieTime");
            return (Criteria) this;
        }
        public Criteria andRecevieTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("recevie_time >=", value, "recevieTime");
            return (Criteria) this;
        }
        public Criteria andRecevieTimeLessThan(Date value) {
            addCriterion("recevie_time <", value, "recevieTime");
            return (Criteria) this;
        }
        public Criteria andRecevieTimeLessThanOrEqualTo(Date value) {
            addCriterion("recevie_time <=", value, "recevieTime");
            return (Criteria) this;
        }
        public Criteria andRecevieTimeIn(List<Date> values) {
            addCriterion("recevie_time in", values, "recevieTime");
            return (Criteria) this;
        }
        public Criteria andRecevieTimeNotIn(List<Date> values) {
            addCriterion("recevie_time not in", values, "recevieTime");
            return (Criteria) this;
        }
        public Criteria andRecevieTimeBetween(Date value1, Date value2) {
            addCriterion("recevie_time between", value1, value2, "recevieTime");
            return (Criteria) this;
        }
        public Criteria andRecevieTimeNotBetween(Date value1, Date value2) {
            addCriterion("recevie_time not between", value1, value2, "recevieTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {


        protected Criteria() {
            super();
        }
    }

    /**
     * 项目执行清单
     * 
     * @author 
     * @version 1.0 2018-08-10
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