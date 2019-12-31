/**
 * 
 */
package com.hori.grms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hori.grms.dao.ProjectApproveLogMapper;
import com.hori.grms.model.ProjectApproveLog;
import com.hori.grms.service.ProjectApproveLogService;

/** 
 * @ClassName: ProjectApproveLogServiceImpl 
 * @Description: 项目审批日志表
 * @author zhuqiang
 * @date 2018年8月8日 下午6:58:41 
 */
@Service("projectApproveLogService")
public class ProjectApproveLogServiceImpl implements ProjectApproveLogService {
	@Autowired
	private ProjectApproveLogMapper projectApproveLogMapper;

	@Override
	public void inserLog(ProjectApproveLog projectApproveLog) {
		projectApproveLogMapper.insert(projectApproveLog);
	}

	@Override
	public List<ProjectApproveLog> findByProjectCode(String projectCode) {
		return projectApproveLogMapper.findByProjectCode(projectCode);
	}
}
