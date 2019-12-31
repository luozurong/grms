/*
 * CustomerExample.java
 * Copyright(C) 20xx-2015 xxxxxx公司
 * All rights reserved.
 * -----------------------------------------------
 * 2018-08-10 Created
 */
package com.hori.grms.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerExample {

    protected String orderByClause;
    protected boolean distinct;
    protected List<Criteria> oredCriteria;

    public CustomerExample() {
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
     * 客户信息表
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
        public Criteria andCustomerTypeIdIsNull() {
            addCriterion("customer_type_id is null");
            return (Criteria) this;
        }
        public Criteria andCustomerTypeIdIsNotNull() {
            addCriterion("customer_type_id is not null");
            return (Criteria) this;
        }
        public Criteria andCustomerTypeIdEqualTo(String value) {
            addCriterion("customer_type_id =", value, "customerTypeId");
            return (Criteria) this;
        }
        public Criteria andCustomerTypeIdNotEqualTo(String value) {
            addCriterion("customer_type_id <>", value, "customerTypeId");
            return (Criteria) this;
        }
        public Criteria andCustomerTypeIdGreaterThan(String value) {
            addCriterion("customer_type_id >", value, "customerTypeId");
            return (Criteria) this;
        }
        public Criteria andCustomerTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("customer_type_id >=", value, "customerTypeId");
            return (Criteria) this;
        }
        public Criteria andCustomerTypeIdLessThan(String value) {
            addCriterion("customer_type_id <", value, "customerTypeId");
            return (Criteria) this;
        }
        public Criteria andCustomerTypeIdLessThanOrEqualTo(String value) {
            addCriterion("customer_type_id <=", value, "customerTypeId");
            return (Criteria) this;
        }
        public Criteria andCustomerTypeIdLike(String value) {
            addCriterion("customer_type_id like", value, "customerTypeId");
            return (Criteria) this;
        }
        public Criteria andCustomerTypeIdNotLike(String value) {
            addCriterion("customer_type_id not like", value, "customerTypeId");
            return (Criteria) this;
        }
        public Criteria andCustomerTypeIdIn(List<String> values) {
            addCriterion("customer_type_id in", values, "customerTypeId");
            return (Criteria) this;
        }
        public Criteria andCustomerTypeIdNotIn(List<String> values) {
            addCriterion("customer_type_id not in", values, "customerTypeId");
            return (Criteria) this;
        }
        public Criteria andCustomerTypeIdBetween(String value1, String value2) {
            addCriterion("customer_type_id between", value1, value2, "customerTypeId");
            return (Criteria) this;
        }
        public Criteria andCustomerTypeIdNotBetween(String value1, String value2) {
            addCriterion("customer_type_id not between", value1, value2, "customerTypeId");
            return (Criteria) this;
        }
        public Criteria andIndustryIsNull() {
            addCriterion("industry is null");
            return (Criteria) this;
        }
        public Criteria andIndustryIsNotNull() {
            addCriterion("industry is not null");
            return (Criteria) this;
        }
        public Criteria andIndustryEqualTo(String value) {
            addCriterion("industry =", value, "industry");
            return (Criteria) this;
        }
        public Criteria andIndustryNotEqualTo(String value) {
            addCriterion("industry <>", value, "industry");
            return (Criteria) this;
        }
        public Criteria andIndustryGreaterThan(String value) {
            addCriterion("industry >", value, "industry");
            return (Criteria) this;
        }
        public Criteria andIndustryGreaterThanOrEqualTo(String value) {
            addCriterion("industry >=", value, "industry");
            return (Criteria) this;
        }
        public Criteria andIndustryLessThan(String value) {
            addCriterion("industry <", value, "industry");
            return (Criteria) this;
        }
        public Criteria andIndustryLessThanOrEqualTo(String value) {
            addCriterion("industry <=", value, "industry");
            return (Criteria) this;
        }
        public Criteria andIndustryLike(String value) {
            addCriterion("industry like", value, "industry");
            return (Criteria) this;
        }
        public Criteria andIndustryNotLike(String value) {
            addCriterion("industry not like", value, "industry");
            return (Criteria) this;
        }
        public Criteria andIndustryIn(List<String> values) {
            addCriterion("industry in", values, "industry");
            return (Criteria) this;
        }
        public Criteria andIndustryNotIn(List<String> values) {
            addCriterion("industry not in", values, "industry");
            return (Criteria) this;
        }
        public Criteria andIndustryBetween(String value1, String value2) {
            addCriterion("industry between", value1, value2, "industry");
            return (Criteria) this;
        }
        public Criteria andIndustryNotBetween(String value1, String value2) {
            addCriterion("industry not between", value1, value2, "industry");
            return (Criteria) this;
        }
        public Criteria andDepartmentIsNull() {
            addCriterion("department is null");
            return (Criteria) this;
        }
        public Criteria andDepartmentIsNotNull() {
            addCriterion("department is not null");
            return (Criteria) this;
        }
        public Criteria andDepartmentEqualTo(String value) {
            addCriterion("department =", value, "department");
            return (Criteria) this;
        }
        public Criteria andDepartmentNotEqualTo(String value) {
            addCriterion("department <>", value, "department");
            return (Criteria) this;
        }
        public Criteria andDepartmentGreaterThan(String value) {
            addCriterion("department >", value, "department");
            return (Criteria) this;
        }
        public Criteria andDepartmentGreaterThanOrEqualTo(String value) {
            addCriterion("department >=", value, "department");
            return (Criteria) this;
        }
        public Criteria andDepartmentLessThan(String value) {
            addCriterion("department <", value, "department");
            return (Criteria) this;
        }
        public Criteria andDepartmentLessThanOrEqualTo(String value) {
            addCriterion("department <=", value, "department");
            return (Criteria) this;
        }
        public Criteria andDepartmentLike(String value) {
            addCriterion("department like", value, "department");
            return (Criteria) this;
        }
        public Criteria andDepartmentNotLike(String value) {
            addCriterion("department not like", value, "department");
            return (Criteria) this;
        }
        public Criteria andDepartmentIn(List<String> values) {
            addCriterion("department in", values, "department");
            return (Criteria) this;
        }
        public Criteria andDepartmentNotIn(List<String> values) {
            addCriterion("department not in", values, "department");
            return (Criteria) this;
        }
        public Criteria andDepartmentBetween(String value1, String value2) {
            addCriterion("department between", value1, value2, "department");
            return (Criteria) this;
        }
        public Criteria andDepartmentNotBetween(String value1, String value2) {
            addCriterion("department not between", value1, value2, "department");
            return (Criteria) this;
        }
        public Criteria andProvinceIsNull() {
            addCriterion("province is null");
            return (Criteria) this;
        }
        public Criteria andProvinceIsNotNull() {
            addCriterion("province is not null");
            return (Criteria) this;
        }
        public Criteria andProvinceEqualTo(String value) {
            addCriterion("province =", value, "province");
            return (Criteria) this;
        }
        public Criteria andProvinceNotEqualTo(String value) {
            addCriterion("province <>", value, "province");
            return (Criteria) this;
        }
        public Criteria andProvinceGreaterThan(String value) {
            addCriterion("province >", value, "province");
            return (Criteria) this;
        }
        public Criteria andProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("province >=", value, "province");
            return (Criteria) this;
        }
        public Criteria andProvinceLessThan(String value) {
            addCriterion("province <", value, "province");
            return (Criteria) this;
        }
        public Criteria andProvinceLessThanOrEqualTo(String value) {
            addCriterion("province <=", value, "province");
            return (Criteria) this;
        }
        public Criteria andProvinceLike(String value) {
            addCriterion("province like", value, "province");
            return (Criteria) this;
        }
        public Criteria andProvinceNotLike(String value) {
            addCriterion("province not like", value, "province");
            return (Criteria) this;
        }
        public Criteria andProvinceIn(List<String> values) {
            addCriterion("province in", values, "province");
            return (Criteria) this;
        }
        public Criteria andProvinceNotIn(List<String> values) {
            addCriterion("province not in", values, "province");
            return (Criteria) this;
        }
        public Criteria andProvinceBetween(String value1, String value2) {
            addCriterion("province between", value1, value2, "province");
            return (Criteria) this;
        }
        public Criteria andProvinceNotBetween(String value1, String value2) {
            addCriterion("province not between", value1, value2, "province");
            return (Criteria) this;
        }
        public Criteria andProvinceNameIsNull() {
            addCriterion("province_name is null");
            return (Criteria) this;
        }
        public Criteria andProvinceNameIsNotNull() {
            addCriterion("province_name is not null");
            return (Criteria) this;
        }
        public Criteria andProvinceNameEqualTo(String value) {
            addCriterion("province_name =", value, "provinceName");
            return (Criteria) this;
        }
        public Criteria andProvinceNameNotEqualTo(String value) {
            addCriterion("province_name <>", value, "provinceName");
            return (Criteria) this;
        }
        public Criteria andProvinceNameGreaterThan(String value) {
            addCriterion("province_name >", value, "provinceName");
            return (Criteria) this;
        }
        public Criteria andProvinceNameGreaterThanOrEqualTo(String value) {
            addCriterion("province_name >=", value, "provinceName");
            return (Criteria) this;
        }
        public Criteria andProvinceNameLessThan(String value) {
            addCriterion("province_name <", value, "provinceName");
            return (Criteria) this;
        }
        public Criteria andProvinceNameLessThanOrEqualTo(String value) {
            addCriterion("province_name <=", value, "provinceName");
            return (Criteria) this;
        }
        public Criteria andProvinceNameLike(String value) {
            addCriterion("province_name like", value, "provinceName");
            return (Criteria) this;
        }
        public Criteria andProvinceNameNotLike(String value) {
            addCriterion("province_name not like", value, "provinceName");
            return (Criteria) this;
        }
        public Criteria andProvinceNameIn(List<String> values) {
            addCriterion("province_name in", values, "provinceName");
            return (Criteria) this;
        }
        public Criteria andProvinceNameNotIn(List<String> values) {
            addCriterion("province_name not in", values, "provinceName");
            return (Criteria) this;
        }
        public Criteria andProvinceNameBetween(String value1, String value2) {
            addCriterion("province_name between", value1, value2, "provinceName");
            return (Criteria) this;
        }
        public Criteria andProvinceNameNotBetween(String value1, String value2) {
            addCriterion("province_name not between", value1, value2, "provinceName");
            return (Criteria) this;
        }
        public Criteria andCityIsNull() {
            addCriterion("city is null");
            return (Criteria) this;
        }
        public Criteria andCityIsNotNull() {
            addCriterion("city is not null");
            return (Criteria) this;
        }
        public Criteria andCityEqualTo(String value) {
            addCriterion("city =", value, "city");
            return (Criteria) this;
        }
        public Criteria andCityNotEqualTo(String value) {
            addCriterion("city <>", value, "city");
            return (Criteria) this;
        }
        public Criteria andCityGreaterThan(String value) {
            addCriterion("city >", value, "city");
            return (Criteria) this;
        }
        public Criteria andCityGreaterThanOrEqualTo(String value) {
            addCriterion("city >=", value, "city");
            return (Criteria) this;
        }
        public Criteria andCityLessThan(String value) {
            addCriterion("city <", value, "city");
            return (Criteria) this;
        }
        public Criteria andCityLessThanOrEqualTo(String value) {
            addCriterion("city <=", value, "city");
            return (Criteria) this;
        }
        public Criteria andCityLike(String value) {
            addCriterion("city like", value, "city");
            return (Criteria) this;
        }
        public Criteria andCityNotLike(String value) {
            addCriterion("city not like", value, "city");
            return (Criteria) this;
        }
        public Criteria andCityIn(List<String> values) {
            addCriterion("city in", values, "city");
            return (Criteria) this;
        }
        public Criteria andCityNotIn(List<String> values) {
            addCriterion("city not in", values, "city");
            return (Criteria) this;
        }
        public Criteria andCityBetween(String value1, String value2) {
            addCriterion("city between", value1, value2, "city");
            return (Criteria) this;
        }
        public Criteria andCityNotBetween(String value1, String value2) {
            addCriterion("city not between", value1, value2, "city");
            return (Criteria) this;
        }
        public Criteria andCityNameIsNull() {
            addCriterion("city_name is null");
            return (Criteria) this;
        }
        public Criteria andCityNameIsNotNull() {
            addCriterion("city_name is not null");
            return (Criteria) this;
        }
        public Criteria andCityNameEqualTo(String value) {
            addCriterion("city_name =", value, "cityName");
            return (Criteria) this;
        }
        public Criteria andCityNameNotEqualTo(String value) {
            addCriterion("city_name <>", value, "cityName");
            return (Criteria) this;
        }
        public Criteria andCityNameGreaterThan(String value) {
            addCriterion("city_name >", value, "cityName");
            return (Criteria) this;
        }
        public Criteria andCityNameGreaterThanOrEqualTo(String value) {
            addCriterion("city_name >=", value, "cityName");
            return (Criteria) this;
        }
        public Criteria andCityNameLessThan(String value) {
            addCriterion("city_name <", value, "cityName");
            return (Criteria) this;
        }
        public Criteria andCityNameLessThanOrEqualTo(String value) {
            addCriterion("city_name <=", value, "cityName");
            return (Criteria) this;
        }
        public Criteria andCityNameLike(String value) {
            addCriterion("city_name like", value, "cityName");
            return (Criteria) this;
        }
        public Criteria andCityNameNotLike(String value) {
            addCriterion("city_name not like", value, "cityName");
            return (Criteria) this;
        }
        public Criteria andCityNameIn(List<String> values) {
            addCriterion("city_name in", values, "cityName");
            return (Criteria) this;
        }
        public Criteria andCityNameNotIn(List<String> values) {
            addCriterion("city_name not in", values, "cityName");
            return (Criteria) this;
        }
        public Criteria andCityNameBetween(String value1, String value2) {
            addCriterion("city_name between", value1, value2, "cityName");
            return (Criteria) this;
        }
        public Criteria andCityNameNotBetween(String value1, String value2) {
            addCriterion("city_name not between", value1, value2, "cityName");
            return (Criteria) this;
        }
        public Criteria andAreaIsNull() {
            addCriterion("area is null");
            return (Criteria) this;
        }
        public Criteria andAreaIsNotNull() {
            addCriterion("area is not null");
            return (Criteria) this;
        }
        public Criteria andAreaEqualTo(String value) {
            addCriterion("area =", value, "area");
            return (Criteria) this;
        }
        public Criteria andAreaNotEqualTo(String value) {
            addCriterion("area <>", value, "area");
            return (Criteria) this;
        }
        public Criteria andAreaGreaterThan(String value) {
            addCriterion("area >", value, "area");
            return (Criteria) this;
        }
        public Criteria andAreaGreaterThanOrEqualTo(String value) {
            addCriterion("area >=", value, "area");
            return (Criteria) this;
        }
        public Criteria andAreaLessThan(String value) {
            addCriterion("area <", value, "area");
            return (Criteria) this;
        }
        public Criteria andAreaLessThanOrEqualTo(String value) {
            addCriterion("area <=", value, "area");
            return (Criteria) this;
        }
        public Criteria andAreaLike(String value) {
            addCriterion("area like", value, "area");
            return (Criteria) this;
        }
        public Criteria andAreaNotLike(String value) {
            addCriterion("area not like", value, "area");
            return (Criteria) this;
        }
        public Criteria andAreaIn(List<String> values) {
            addCriterion("area in", values, "area");
            return (Criteria) this;
        }
        public Criteria andAreaNotIn(List<String> values) {
            addCriterion("area not in", values, "area");
            return (Criteria) this;
        }
        public Criteria andAreaBetween(String value1, String value2) {
            addCriterion("area between", value1, value2, "area");
            return (Criteria) this;
        }
        public Criteria andAreaNotBetween(String value1, String value2) {
            addCriterion("area not between", value1, value2, "area");
            return (Criteria) this;
        }
        public Criteria andAreaNameIsNull() {
            addCriterion("area_name is null");
            return (Criteria) this;
        }
        public Criteria andAreaNameIsNotNull() {
            addCriterion("area_name is not null");
            return (Criteria) this;
        }
        public Criteria andAreaNameEqualTo(String value) {
            addCriterion("area_name =", value, "areaName");
            return (Criteria) this;
        }
        public Criteria andAreaNameNotEqualTo(String value) {
            addCriterion("area_name <>", value, "areaName");
            return (Criteria) this;
        }
        public Criteria andAreaNameGreaterThan(String value) {
            addCriterion("area_name >", value, "areaName");
            return (Criteria) this;
        }
        public Criteria andAreaNameGreaterThanOrEqualTo(String value) {
            addCriterion("area_name >=", value, "areaName");
            return (Criteria) this;
        }
        public Criteria andAreaNameLessThan(String value) {
            addCriterion("area_name <", value, "areaName");
            return (Criteria) this;
        }
        public Criteria andAreaNameLessThanOrEqualTo(String value) {
            addCriterion("area_name <=", value, "areaName");
            return (Criteria) this;
        }
        public Criteria andAreaNameLike(String value) {
            addCriterion("area_name like", value, "areaName");
            return (Criteria) this;
        }
        public Criteria andAreaNameNotLike(String value) {
            addCriterion("area_name not like", value, "areaName");
            return (Criteria) this;
        }
        public Criteria andAreaNameIn(List<String> values) {
            addCriterion("area_name in", values, "areaName");
            return (Criteria) this;
        }
        public Criteria andAreaNameNotIn(List<String> values) {
            addCriterion("area_name not in", values, "areaName");
            return (Criteria) this;
        }
        public Criteria andAreaNameBetween(String value1, String value2) {
            addCriterion("area_name between", value1, value2, "areaName");
            return (Criteria) this;
        }
        public Criteria andAreaNameNotBetween(String value1, String value2) {
            addCriterion("area_name not between", value1, value2, "areaName");
            return (Criteria) this;
        }
        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }
        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }
        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }
        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }
        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }
        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }
        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }
        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }
        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }
        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }
        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }
        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }
        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }
        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }
        public Criteria andDutyNameIsNull() {
            addCriterion("duty_name is null");
            return (Criteria) this;
        }
        public Criteria andDutyNameIsNotNull() {
            addCriterion("duty_name is not null");
            return (Criteria) this;
        }
        public Criteria andDutyNameEqualTo(String value) {
            addCriterion("duty_name =", value, "dutyName");
            return (Criteria) this;
        }
        public Criteria andDutyNameNotEqualTo(String value) {
            addCriterion("duty_name <>", value, "dutyName");
            return (Criteria) this;
        }
        public Criteria andDutyNameGreaterThan(String value) {
            addCriterion("duty_name >", value, "dutyName");
            return (Criteria) this;
        }
        public Criteria andDutyNameGreaterThanOrEqualTo(String value) {
            addCriterion("duty_name >=", value, "dutyName");
            return (Criteria) this;
        }
        public Criteria andDutyNameLessThan(String value) {
            addCriterion("duty_name <", value, "dutyName");
            return (Criteria) this;
        }
        public Criteria andDutyNameLessThanOrEqualTo(String value) {
            addCriterion("duty_name <=", value, "dutyName");
            return (Criteria) this;
        }
        public Criteria andDutyNameLike(String value) {
            addCriterion("duty_name like", value, "dutyName");
            return (Criteria) this;
        }
        public Criteria andDutyNameNotLike(String value) {
            addCriterion("duty_name not like", value, "dutyName");
            return (Criteria) this;
        }
        public Criteria andDutyNameIn(List<String> values) {
            addCriterion("duty_name in", values, "dutyName");
            return (Criteria) this;
        }
        public Criteria andDutyNameNotIn(List<String> values) {
            addCriterion("duty_name not in", values, "dutyName");
            return (Criteria) this;
        }
        public Criteria andDutyNameBetween(String value1, String value2) {
            addCriterion("duty_name between", value1, value2, "dutyName");
            return (Criteria) this;
        }
        public Criteria andDutyNameNotBetween(String value1, String value2) {
            addCriterion("duty_name not between", value1, value2, "dutyName");
            return (Criteria) this;
        }
        public Criteria andDutyPhoneIsNull() {
            addCriterion("duty_phone is null");
            return (Criteria) this;
        }
        public Criteria andDutyPhoneIsNotNull() {
            addCriterion("duty_phone is not null");
            return (Criteria) this;
        }
        public Criteria andDutyPhoneEqualTo(String value) {
            addCriterion("duty_phone =", value, "dutyPhone");
            return (Criteria) this;
        }
        public Criteria andDutyPhoneNotEqualTo(String value) {
            addCriterion("duty_phone <>", value, "dutyPhone");
            return (Criteria) this;
        }
        public Criteria andDutyPhoneGreaterThan(String value) {
            addCriterion("duty_phone >", value, "dutyPhone");
            return (Criteria) this;
        }
        public Criteria andDutyPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("duty_phone >=", value, "dutyPhone");
            return (Criteria) this;
        }
        public Criteria andDutyPhoneLessThan(String value) {
            addCriterion("duty_phone <", value, "dutyPhone");
            return (Criteria) this;
        }
        public Criteria andDutyPhoneLessThanOrEqualTo(String value) {
            addCriterion("duty_phone <=", value, "dutyPhone");
            return (Criteria) this;
        }
        public Criteria andDutyPhoneLike(String value) {
            addCriterion("duty_phone like", value, "dutyPhone");
            return (Criteria) this;
        }
        public Criteria andDutyPhoneNotLike(String value) {
            addCriterion("duty_phone not like", value, "dutyPhone");
            return (Criteria) this;
        }
        public Criteria andDutyPhoneIn(List<String> values) {
            addCriterion("duty_phone in", values, "dutyPhone");
            return (Criteria) this;
        }
        public Criteria andDutyPhoneNotIn(List<String> values) {
            addCriterion("duty_phone not in", values, "dutyPhone");
            return (Criteria) this;
        }
        public Criteria andDutyPhoneBetween(String value1, String value2) {
            addCriterion("duty_phone between", value1, value2, "dutyPhone");
            return (Criteria) this;
        }
        public Criteria andDutyPhoneNotBetween(String value1, String value2) {
            addCriterion("duty_phone not between", value1, value2, "dutyPhone");
            return (Criteria) this;
        }
        public Criteria andContactorIsNull() {
            addCriterion("contactor is null");
            return (Criteria) this;
        }
        public Criteria andContactorIsNotNull() {
            addCriterion("contactor is not null");
            return (Criteria) this;
        }
        public Criteria andContactorEqualTo(String value) {
            addCriterion("contactor =", value, "contactor");
            return (Criteria) this;
        }
        public Criteria andContactorNotEqualTo(String value) {
            addCriterion("contactor <>", value, "contactor");
            return (Criteria) this;
        }
        public Criteria andContactorGreaterThan(String value) {
            addCriterion("contactor >", value, "contactor");
            return (Criteria) this;
        }
        public Criteria andContactorGreaterThanOrEqualTo(String value) {
            addCriterion("contactor >=", value, "contactor");
            return (Criteria) this;
        }
        public Criteria andContactorLessThan(String value) {
            addCriterion("contactor <", value, "contactor");
            return (Criteria) this;
        }
        public Criteria andContactorLessThanOrEqualTo(String value) {
            addCriterion("contactor <=", value, "contactor");
            return (Criteria) this;
        }
        public Criteria andContactorLike(String value) {
            addCriterion("contactor like", value, "contactor");
            return (Criteria) this;
        }
        public Criteria andContactorNotLike(String value) {
            addCriterion("contactor not like", value, "contactor");
            return (Criteria) this;
        }
        public Criteria andContactorIn(List<String> values) {
            addCriterion("contactor in", values, "contactor");
            return (Criteria) this;
        }
        public Criteria andContactorNotIn(List<String> values) {
            addCriterion("contactor not in", values, "contactor");
            return (Criteria) this;
        }
        public Criteria andContactorBetween(String value1, String value2) {
            addCriterion("contactor between", value1, value2, "contactor");
            return (Criteria) this;
        }
        public Criteria andContactorNotBetween(String value1, String value2) {
            addCriterion("contactor not between", value1, value2, "contactor");
            return (Criteria) this;
        }
        public Criteria andContactorPhoneIsNull() {
            addCriterion("contactor_phone is null");
            return (Criteria) this;
        }
        public Criteria andContactorPhoneIsNotNull() {
            addCriterion("contactor_phone is not null");
            return (Criteria) this;
        }
        public Criteria andContactorPhoneEqualTo(String value) {
            addCriterion("contactor_phone =", value, "contactorPhone");
            return (Criteria) this;
        }
        public Criteria andContactorPhoneNotEqualTo(String value) {
            addCriterion("contactor_phone <>", value, "contactorPhone");
            return (Criteria) this;
        }
        public Criteria andContactorPhoneGreaterThan(String value) {
            addCriterion("contactor_phone >", value, "contactorPhone");
            return (Criteria) this;
        }
        public Criteria andContactorPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("contactor_phone >=", value, "contactorPhone");
            return (Criteria) this;
        }
        public Criteria andContactorPhoneLessThan(String value) {
            addCriterion("contactor_phone <", value, "contactorPhone");
            return (Criteria) this;
        }
        public Criteria andContactorPhoneLessThanOrEqualTo(String value) {
            addCriterion("contactor_phone <=", value, "contactorPhone");
            return (Criteria) this;
        }
        public Criteria andContactorPhoneLike(String value) {
            addCriterion("contactor_phone like", value, "contactorPhone");
            return (Criteria) this;
        }
        public Criteria andContactorPhoneNotLike(String value) {
            addCriterion("contactor_phone not like", value, "contactorPhone");
            return (Criteria) this;
        }
        public Criteria andContactorPhoneIn(List<String> values) {
            addCriterion("contactor_phone in", values, "contactorPhone");
            return (Criteria) this;
        }
        public Criteria andContactorPhoneNotIn(List<String> values) {
            addCriterion("contactor_phone not in", values, "contactorPhone");
            return (Criteria) this;
        }
        public Criteria andContactorPhoneBetween(String value1, String value2) {
            addCriterion("contactor_phone between", value1, value2, "contactorPhone");
            return (Criteria) this;
        }
        public Criteria andContactorPhoneNotBetween(String value1, String value2) {
            addCriterion("contactor_phone not between", value1, value2, "contactorPhone");
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
        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }
        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }
        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }
        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }
        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }
        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }
        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }
        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }
        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }
        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }
        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }
        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }
        public Criteria andCreatorNameIsNull() {
            addCriterion("creator_name is null");
            return (Criteria) this;
        }
        public Criteria andCreatorNameIsNotNull() {
            addCriterion("creator_name is not null");
            return (Criteria) this;
        }
        public Criteria andCreatorNameEqualTo(String value) {
            addCriterion("creator_name =", value, "creatorName");
            return (Criteria) this;
        }
        public Criteria andCreatorNameNotEqualTo(String value) {
            addCriterion("creator_name <>", value, "creatorName");
            return (Criteria) this;
        }
        public Criteria andCreatorNameGreaterThan(String value) {
            addCriterion("creator_name >", value, "creatorName");
            return (Criteria) this;
        }
        public Criteria andCreatorNameGreaterThanOrEqualTo(String value) {
            addCriterion("creator_name >=", value, "creatorName");
            return (Criteria) this;
        }
        public Criteria andCreatorNameLessThan(String value) {
            addCriterion("creator_name <", value, "creatorName");
            return (Criteria) this;
        }
        public Criteria andCreatorNameLessThanOrEqualTo(String value) {
            addCriterion("creator_name <=", value, "creatorName");
            return (Criteria) this;
        }
        public Criteria andCreatorNameLike(String value) {
            addCriterion("creator_name like", value, "creatorName");
            return (Criteria) this;
        }
        public Criteria andCreatorNameNotLike(String value) {
            addCriterion("creator_name not like", value, "creatorName");
            return (Criteria) this;
        }
        public Criteria andCreatorNameIn(List<String> values) {
            addCriterion("creator_name in", values, "creatorName");
            return (Criteria) this;
        }
        public Criteria andCreatorNameNotIn(List<String> values) {
            addCriterion("creator_name not in", values, "creatorName");
            return (Criteria) this;
        }
        public Criteria andCreatorNameBetween(String value1, String value2) {
            addCriterion("creator_name between", value1, value2, "creatorName");
            return (Criteria) this;
        }
        public Criteria andCreatorNameNotBetween(String value1, String value2) {
            addCriterion("creator_name not between", value1, value2, "creatorName");
            return (Criteria) this;
        }
        public Criteria andCreatorAccountIsNull() {
            addCriterion("creator_account is null");
            return (Criteria) this;
        }
        public Criteria andCreatorAccountIsNotNull() {
            addCriterion("creator_account is not null");
            return (Criteria) this;
        }
        public Criteria andCreatorAccountEqualTo(String value) {
            addCriterion("creator_account =", value, "creatorAccount");
            return (Criteria) this;
        }
        public Criteria andCreatorAccountNotEqualTo(String value) {
            addCriterion("creator_account <>", value, "creatorAccount");
            return (Criteria) this;
        }
        public Criteria andCreatorAccountGreaterThan(String value) {
            addCriterion("creator_account >", value, "creatorAccount");
            return (Criteria) this;
        }
        public Criteria andCreatorAccountGreaterThanOrEqualTo(String value) {
            addCriterion("creator_account >=", value, "creatorAccount");
            return (Criteria) this;
        }
        public Criteria andCreatorAccountLessThan(String value) {
            addCriterion("creator_account <", value, "creatorAccount");
            return (Criteria) this;
        }
        public Criteria andCreatorAccountLessThanOrEqualTo(String value) {
            addCriterion("creator_account <=", value, "creatorAccount");
            return (Criteria) this;
        }
        public Criteria andCreatorAccountLike(String value) {
            addCriterion("creator_account like", value, "creatorAccount");
            return (Criteria) this;
        }
        public Criteria andCreatorAccountNotLike(String value) {
            addCriterion("creator_account not like", value, "creatorAccount");
            return (Criteria) this;
        }
        public Criteria andCreatorAccountIn(List<String> values) {
            addCriterion("creator_account in", values, "creatorAccount");
            return (Criteria) this;
        }
        public Criteria andCreatorAccountNotIn(List<String> values) {
            addCriterion("creator_account not in", values, "creatorAccount");
            return (Criteria) this;
        }
        public Criteria andCreatorAccountBetween(String value1, String value2) {
            addCriterion("creator_account between", value1, value2, "creatorAccount");
            return (Criteria) this;
        }
        public Criteria andCreatorAccountNotBetween(String value1, String value2) {
            addCriterion("creator_account not between", value1, value2, "creatorAccount");
            return (Criteria) this;
        }
        public Criteria andCreatorLevelIsNull() {
            addCriterion("creator_level is null");
            return (Criteria) this;
        }
        public Criteria andCreatorLevelIsNotNull() {
            addCriterion("creator_level is not null");
            return (Criteria) this;
        }
        public Criteria andCreatorLevelEqualTo(String value) {
            addCriterion("creator_level =", value, "creatorLevel");
            return (Criteria) this;
        }
        public Criteria andCreatorLevelNotEqualTo(String value) {
            addCriterion("creator_level <>", value, "creatorLevel");
            return (Criteria) this;
        }
        public Criteria andCreatorLevelGreaterThan(String value) {
            addCriterion("creator_level >", value, "creatorLevel");
            return (Criteria) this;
        }
        public Criteria andCreatorLevelGreaterThanOrEqualTo(String value) {
            addCriterion("creator_level >=", value, "creatorLevel");
            return (Criteria) this;
        }
        public Criteria andCreatorLevelLessThan(String value) {
            addCriterion("creator_level <", value, "creatorLevel");
            return (Criteria) this;
        }
        public Criteria andCreatorLevelLessThanOrEqualTo(String value) {
            addCriterion("creator_level <=", value, "creatorLevel");
            return (Criteria) this;
        }
        public Criteria andCreatorLevelLike(String value) {
            addCriterion("creator_level like", value, "creatorLevel");
            return (Criteria) this;
        }
        public Criteria andCreatorLevelNotLike(String value) {
            addCriterion("creator_level not like", value, "creatorLevel");
            return (Criteria) this;
        }
        public Criteria andCreatorLevelIn(List<String> values) {
            addCriterion("creator_level in", values, "creatorLevel");
            return (Criteria) this;
        }
        public Criteria andCreatorLevelNotIn(List<String> values) {
            addCriterion("creator_level not in", values, "creatorLevel");
            return (Criteria) this;
        }
        public Criteria andCreatorLevelBetween(String value1, String value2) {
            addCriterion("creator_level between", value1, value2, "creatorLevel");
            return (Criteria) this;
        }
        public Criteria andCreatorLevelNotBetween(String value1, String value2) {
            addCriterion("creator_level not between", value1, value2, "creatorLevel");
            return (Criteria) this;
        }
        public Criteria andOwnerNameIsNull() {
            addCriterion("owner_name is null");
            return (Criteria) this;
        }
        public Criteria andOwnerNameIsNotNull() {
            addCriterion("owner_name is not null");
            return (Criteria) this;
        }
        public Criteria andOwnerNameEqualTo(String value) {
            addCriterion("owner_name =", value, "ownerName");
            return (Criteria) this;
        }
        public Criteria andOwnerNameNotEqualTo(String value) {
            addCriterion("owner_name <>", value, "ownerName");
            return (Criteria) this;
        }
        public Criteria andOwnerNameGreaterThan(String value) {
            addCriterion("owner_name >", value, "ownerName");
            return (Criteria) this;
        }
        public Criteria andOwnerNameGreaterThanOrEqualTo(String value) {
            addCriterion("owner_name >=", value, "ownerName");
            return (Criteria) this;
        }
        public Criteria andOwnerNameLessThan(String value) {
            addCriterion("owner_name <", value, "ownerName");
            return (Criteria) this;
        }
        public Criteria andOwnerNameLessThanOrEqualTo(String value) {
            addCriterion("owner_name <=", value, "ownerName");
            return (Criteria) this;
        }
        public Criteria andOwnerNameLike(String value) {
            addCriterion("owner_name like", value, "ownerName");
            return (Criteria) this;
        }
        public Criteria andOwnerNameNotLike(String value) {
            addCriterion("owner_name not like", value, "ownerName");
            return (Criteria) this;
        }
        public Criteria andOwnerNameIn(List<String> values) {
            addCriterion("owner_name in", values, "ownerName");
            return (Criteria) this;
        }
        public Criteria andOwnerNameNotIn(List<String> values) {
            addCriterion("owner_name not in", values, "ownerName");
            return (Criteria) this;
        }
        public Criteria andOwnerNameBetween(String value1, String value2) {
            addCriterion("owner_name between", value1, value2, "ownerName");
            return (Criteria) this;
        }
        public Criteria andOwnerNameNotBetween(String value1, String value2) {
            addCriterion("owner_name not between", value1, value2, "ownerName");
            return (Criteria) this;
        }
        public Criteria andOwnerAccountIsNull() {
            addCriterion("owner_account is null");
            return (Criteria) this;
        }
        public Criteria andOwnerAccountIsNotNull() {
            addCriterion("owner_account is not null");
            return (Criteria) this;
        }
        public Criteria andOwnerAccountEqualTo(String value) {
            addCriterion("owner_account =", value, "ownerAccount");
            return (Criteria) this;
        }
        public Criteria andOwnerAccountNotEqualTo(String value) {
            addCriterion("owner_account <>", value, "ownerAccount");
            return (Criteria) this;
        }
        public Criteria andOwnerAccountGreaterThan(String value) {
            addCriterion("owner_account >", value, "ownerAccount");
            return (Criteria) this;
        }
        public Criteria andOwnerAccountGreaterThanOrEqualTo(String value) {
            addCriterion("owner_account >=", value, "ownerAccount");
            return (Criteria) this;
        }
        public Criteria andOwnerAccountLessThan(String value) {
            addCriterion("owner_account <", value, "ownerAccount");
            return (Criteria) this;
        }
        public Criteria andOwnerAccountLessThanOrEqualTo(String value) {
            addCriterion("owner_account <=", value, "ownerAccount");
            return (Criteria) this;
        }
        public Criteria andOwnerAccountLike(String value) {
            addCriterion("owner_account like", value, "ownerAccount");
            return (Criteria) this;
        }
        public Criteria andOwnerAccountNotLike(String value) {
            addCriterion("owner_account not like", value, "ownerAccount");
            return (Criteria) this;
        }
        public Criteria andOwnerAccountIn(List<String> values) {
            addCriterion("owner_account in", values, "ownerAccount");
            return (Criteria) this;
        }
        public Criteria andOwnerAccountNotIn(List<String> values) {
            addCriterion("owner_account not in", values, "ownerAccount");
            return (Criteria) this;
        }
        public Criteria andOwnerAccountBetween(String value1, String value2) {
            addCriterion("owner_account between", value1, value2, "ownerAccount");
            return (Criteria) this;
        }
        public Criteria andOwnerAccountNotBetween(String value1, String value2) {
            addCriterion("owner_account not between", value1, value2, "ownerAccount");
            return (Criteria) this;
        }
        public Criteria andOwnerLevelIsNull() {
            addCriterion("owner_level is null");
            return (Criteria) this;
        }
        public Criteria andOwnerLevelIsNotNull() {
            addCriterion("owner_level is not null");
            return (Criteria) this;
        }
        public Criteria andOwnerLevelEqualTo(String value) {
            addCriterion("owner_level =", value, "ownerLevel");
            return (Criteria) this;
        }
        public Criteria andOwnerLevelNotEqualTo(String value) {
            addCriterion("owner_level <>", value, "ownerLevel");
            return (Criteria) this;
        }
        public Criteria andOwnerLevelGreaterThan(String value) {
            addCriterion("owner_level >", value, "ownerLevel");
            return (Criteria) this;
        }
        public Criteria andOwnerLevelGreaterThanOrEqualTo(String value) {
            addCriterion("owner_level >=", value, "ownerLevel");
            return (Criteria) this;
        }
        public Criteria andOwnerLevelLessThan(String value) {
            addCriterion("owner_level <", value, "ownerLevel");
            return (Criteria) this;
        }
        public Criteria andOwnerLevelLessThanOrEqualTo(String value) {
            addCriterion("owner_level <=", value, "ownerLevel");
            return (Criteria) this;
        }
        public Criteria andOwnerLevelLike(String value) {
            addCriterion("owner_level like", value, "ownerLevel");
            return (Criteria) this;
        }
        public Criteria andOwnerLevelNotLike(String value) {
            addCriterion("owner_level not like", value, "ownerLevel");
            return (Criteria) this;
        }
        public Criteria andOwnerLevelIn(List<String> values) {
            addCriterion("owner_level in", values, "ownerLevel");
            return (Criteria) this;
        }
        public Criteria andOwnerLevelNotIn(List<String> values) {
            addCriterion("owner_level not in", values, "ownerLevel");
            return (Criteria) this;
        }
        public Criteria andOwnerLevelBetween(String value1, String value2) {
            addCriterion("owner_level between", value1, value2, "ownerLevel");
            return (Criteria) this;
        }
        public Criteria andOwnerLevelNotBetween(String value1, String value2) {
            addCriterion("owner_level not between", value1, value2, "ownerLevel");
            return (Criteria) this;
        }
        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }
        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }
        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }
        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }
        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }
        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }
        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }
        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }
        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }
        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }
        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }
        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }
        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }
        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {


        protected Criteria() {
            super();
        }
    }

    /**
     * 客户信息表
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