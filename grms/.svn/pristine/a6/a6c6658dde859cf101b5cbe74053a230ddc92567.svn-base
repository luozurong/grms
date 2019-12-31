/**
 * 
 */
package com.hori.grms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hori.grms.dao.ProjectProductMenuAreaMapper;
import com.hori.grms.queryBean.ProjectActionQueryBean;
import com.hori.grms.service.ProjectProductMenuAreaService;
import com.hori.grms.vo.ExportMGVo;

/** 
 * @ClassName: ProjectProductMenuAreaServiceImpl 
 * @Description: 项目产品明细小区关联表
 * @author zhuqiang
 * @date 2018年8月8日 下午7:15:41 
 */
@Service("projectProductMenuAreaService")
public class ProjectProductMenuAreaServiceImpl implements ProjectProductMenuAreaService {
	@Autowired
	private ProjectProductMenuAreaMapper projectProductMenuAreaMapper;

	@Override
	public List<ExportMGVo> listMGData(ProjectActionQueryBean queryBean) {
		return projectProductMenuAreaMapper.listMGData(queryBean);
	}

	@Override
	public String getAreaNamesByPPID(String projectProductId) {
		return projectProductMenuAreaMapper.getAreaNamesByPPID(projectProductId);
	}
}
