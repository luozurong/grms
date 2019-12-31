package com.hori.grms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hori.grms.model.BackMoneyPlanPeriods;
import com.hori.grms.queryBean.BackMoneyPlanPeriodsQueryBean;
import com.hori.grms.vo.BackMoneyPlanPeriodsVo;


public interface BackMoneyPlanPeriodsMapper {

    int insertSelective(BackMoneyPlanPeriods record);

    BackMoneyPlanPeriods selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BackMoneyPlanPeriods record);

	List<BackMoneyPlanPeriods> getPeriodsListByPlanCode(@Param("backMoneyPlanCode")String backMoneyPlanCode);

	void deleteByCode(@Param("backMoneyPlanCode")String backMoneyPlanCode);

	List<BackMoneyPlanPeriodsVo> getBackMoneyPlanListByCondition(BackMoneyPlanPeriodsQueryBean queryBean);

	List<BackMoneyPlanPeriods> getNotBackPlanList();
	//获取结算款
	BackMoneyPlanPeriods getJSPeriodsByPlanCode(@Param("backMoneyPlanCode")String backMoneyPlanCode);

	List<BackMoneyPlanPeriods> getJDListByPlanCode(@Param("backMoneyPlanCode")String backMoneyPlanCode);

	List<BackMoneyPlanPeriods> getKKListByPlanCode(@Param("backMoneyPlanCode")String backMoneyPlanCode);

	List<BackMoneyPlanPeriods> getJSListByPlanCode(@Param("backMoneyPlanCode")String backMoneyPlanCode);

	List<BackMoneyPlanPeriods> getJDYCListByPlanCode(@Param("backMoneyPlanCode")String backMoneyPlanCode);

	List<BackMoneyPlanPeriods> getWSListByPlanCode(@Param("backMoneyPlanCode")String backMoneyPlanCode);
}