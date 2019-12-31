/**
 * 
 */
package com.hori.grms.service;

import java.util.List;

import com.hori.grms.model.ProjectApproveLog;

/** 
 * @ClassName: ProjectApproveLogService 
 * @Description:项目审批日志表 
 * @author zhuqiang
 * @date 2018年8月8日 下午6:57:31 
 */

public interface ProjectApproveLogService {
	
	/**
	 * 新增日志
	 * @param projectApproveLog
	 */
	void inserLog(ProjectApproveLog projectApproveLog);
	/**
	 * 通过项目编码获取项目审核日志
	 * @param projectCode
	 * @return
	 */
	List<ProjectApproveLog> findByProjectCode(String projectCode);

}
