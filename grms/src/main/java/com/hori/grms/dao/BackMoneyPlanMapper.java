package com.hori.grms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hori.grms.model.BackMoneyPlan;
import com.hori.grms.model.BackMoneyPlanPeriods;
import com.hori.grms.queryBean.BackMoneyPlanQueryBean;
import com.hori.grms.vo.BackMoneyPlanVo;
import com.hori.grms.vo.ContractDetailVo;
import com.hori.grms.vo.ContractVo;



public interface BackMoneyPlanMapper {

	List<BackMoneyPlanVo> getBackMoneyPlanListByCondition(BackMoneyPlanQueryBean queryBean);

	void insertSelective(BackMoneyPlan backMoneyPlan);

	ContractDetailVo getContractDetail(@Param("contractCode")String contractCode);

	BackMoneyPlan getBackMoneyPlanByCode(@Param("backMoneyPlanCode")String backMoneyPlanCode);
	
	void updateByPrimaryKeySelective(BackMoneyPlan backMoneyPlan);

	BackMoneyPlan getBackMoneyPlanByContractCode(@Param("contractCode")String contractCode);

	List<BackMoneyPlanVo> getBackMoneyPlanList();

	/**
	 * 获取收款计划
	 * @param contractCode 合同编号
	 * @param status 收款计划审核状态，-1已删除 0待审核 1已审核 2已结案
	 * @return
	 */
	BackMoneyPlan getBackMoneyPlanByContractCodeAndStatus(@Param("contractCode") String contractCode, @Param("status") int status);
	
	List<ContractDetailVo> getContractLists(@Param("condition")String condition, @Param("pageNo")int pageNo);
	/**
	 * 获取合同列表总数
	 * @param condition
	 * @return
	 */
	int getContractListTatal(@Param("condition")String condition);

	void deleteByCode(@Param("backMoneyPlanCode")String backMoneyPlanCode);
}