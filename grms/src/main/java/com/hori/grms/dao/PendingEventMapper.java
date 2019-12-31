package com.hori.grms.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hori.grms.model.PendingEvent;
import com.hori.grms.queryBean.PendingEventQueryBean;

public interface PendingEventMapper {
    int deleteByPrimaryKey(String id);

    int insert(PendingEvent record);

    int insertSelective(PendingEvent record);

    PendingEvent selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PendingEvent record);

    int updateByPrimaryKey(PendingEvent record);
    
    
    /**
     * 查询待办事项列表
     * @param projectQueryBean
     * @return
     */
    List<Map<String, Object>> listForPendingEvent(@Param("queryBean")PendingEventQueryBean pendingEventQueryBean);
	
	
	/**
	 * 通过模块编号修改对应的待办事件对象
	 * @param pendingEvent
	 * @return
	 */
	int updateByModelCode(PendingEvent pendingEvent);
	
	/**
	 * 通过项目编号、模块编号、模块名称查询待办事项
	 * @param projectCode
	 * @param modelCode
	 * @param modelName
	 * @return
	 */
	PendingEvent selectByModelCodeAndName(@Param("projectCode")String projectCode,
			@Param("modelCode")String modelCode,@Param("modelName")String modelName,
			@Param("roleType")String roleType);
	
	/**
	 * 通过执行异常ID查询待办事项
	 * @param exceptionId
	 * @return
	 */
	PendingEvent selectByExceptionId(@Param("exceptionId")String exceptionId);
	
    /**
     * 通过项目编号、模块名称、角色类型查询待办事项
     * @return
     */
    List<PendingEvent> selectByProjectCodeAndRoleType(@Param("projectCode")String projectCode,
			@Param("roleTypes")String roleTypes,@Param("modelName")String modelName
			,@Param("modelCode")String modelCode);
    
    
    /**
	 * 通过项目编号、模块名称、角色类型、异常ID  查询待办事项
	 * @param projectCode
	 * @param modelCode
	 * @param modelName
	 * @param roleType
	 * @param exceptionId
	 * @return
	 */
	PendingEvent selectByProjectCodeAndRoleTypeExceptionId(@Param("projectCode")String projectCode,
			@Param("modelCode")String modelCode,@Param("modelName")String modelName,
			@Param("roleType")String roleType,@Param("exceptionId")String exceptionId);
	
	
	/**
	 * 删除待办事项
	 * @param projectCode
	 * @param modelCode
	 * @param modelName
	 * @param roleType
	 * @param exceptionId
	 * @return
	 */
	void deleteByCodeNameType(@Param("projectCode")String projectCode,
			@Param("modelCode")String modelCode,@Param("modelName")String modelName,
			@Param("roleType")String roleType,@Param("exceptionId")String exceptionId);
}