package com.hori.grms.quartz;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hori.grms.model.BackMoneyPlan;
import com.hori.grms.model.BackMoneyPlanPeriods;
import com.hori.grms.model.Project;
import com.hori.grms.service.BackMoneyPlanPeriodsService;
import com.hori.grms.service.BackMoneyPlanService;
import com.hori.grms.service.PendingEventService;
import com.hori.grms.service.ProjectService;


@Component("backMoneyPendingTaskEvent")
public class BackMoneyPendingTaskEvent {
	
	@Autowired
	BackMoneyPlanPeriodsService backMoneyPlanPeriodsservice;
	@Autowired
	PendingEventService pendingEventService;
	@Autowired
	BackMoneyPlanService backMoneyPlanService;
	@Autowired
	ProjectService projectService;
	
	@Scheduled(cron="0 0 0 * * ?")   //每天凌晨十二点执行一次  
	public void createBackMoneyPendingEventByTask(){
		List<BackMoneyPlanPeriods> list = backMoneyPlanPeriodsservice.getNotBackPlanList();
		for (BackMoneyPlanPeriods backMoneyPlanPeriods : list) {
			Long currentTime = System.currentTimeMillis();
			Long planTime = backMoneyPlanPeriods.getPlanBackTime().getTime();
			
			if((currentTime-planTime)>=259200000){//计划收款时间到期后3天，系统自动生成待办提醒消息给财务人员。
				BackMoneyPlan plan = backMoneyPlanService.getBackMoneyPlanByCode(backMoneyPlanPeriods.getBackMoneyPlanCode());
				Project project = projectService.getByContractCode(plan.getContractCode());
				pendingEventService.createBackMoneyPendingEventByTask(project.getName(),project.getProductCode(),backMoneyPlanPeriods.getId());
			}
		}
	}
}
