package com.hori.grms.service;

import java.util.List;

import com.hori.grms.model.BackMoneyPlanPeriods;
import com.hori.grms.queryBean.BackMoneyPlanPeriodsQueryBean;
import com.hori.grms.vo.BackMoneyPlanPeriodsVo;


public interface BackMoneyPlanPeriodsService {
	void insertSelective(BackMoneyPlanPeriods backMoneyPlanPeriods);

	void updateByPrimaryKeySelective(BackMoneyPlanPeriods backMoneyPlanPeriods);

	BackMoneyPlanPeriods selectByPrimaryKey(String id);

	List<BackMoneyPlanPeriods> getPeriodsListByPlanCode(String backMoneyPlanCode);

	void deleteByCode(String backMoneyPlanCode);

	List<BackMoneyPlanPeriodsVo> getBackMoneyPlanListByCondition(BackMoneyPlanPeriodsQueryBean queryBean);

	List<BackMoneyPlanPeriods> getNotBackPlanList();
	//获取进度款
	List<BackMoneyPlanPeriods> getJDListByPlanCode(String backMoneyPlanCode);
	//获取扣款
	List<BackMoneyPlanPeriods> getKKListByPlanCode(String backMoneyPlanCode);
	//获取结算款
	List<BackMoneyPlanPeriods> getJSListByPlanCode(String backMoneyPlanCode);

	List<BackMoneyPlanPeriods> getJDYCListByPlanCode(String backMoneyPlanCode);

	List<BackMoneyPlanPeriods> getWSListByPlanCode(String backMoneyPlanCode);
}
