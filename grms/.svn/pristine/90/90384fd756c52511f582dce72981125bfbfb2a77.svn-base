package com.hori.grms.dao;

import java.util.List;
import java.util.Map;

import com.hori.grms.model.CustomerType;
import com.hori.grms.model.ProjectRole;


public interface ProjectRoleMapper {
    List<ProjectRole> selectAll();
    void delAll();
    void insert(ProjectRole projectRole);

	/**
	 * @return
	 */
    List<Map<String, Object>> findProjectRoleList();
	ProjectRole getProjectRoleById(String id);
	void update(ProjectRole role);
	ProjectRole selectByPrimaryKey(String id);
	void updateByPrimaryKeySelective(ProjectRole role);
}