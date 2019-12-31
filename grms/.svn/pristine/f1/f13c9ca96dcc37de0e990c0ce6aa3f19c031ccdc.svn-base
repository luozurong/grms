package com.hori.grms.dao;

import com.hori.grms.model.ProjectActionException;
import com.hori.grms.model.ProjectActionExceptionExample;
import com.hori.grms.queryBean.PAExceptionQueryBean;
import com.hori.grms.vo.ProjectActionExceptionVo;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ProjectActionExceptionMapper {
    long countByExample(ProjectActionExceptionExample example);
    int deleteByExample(ProjectActionExceptionExample example);
    int deleteByPrimaryKey(String id);
    int insert(ProjectActionException record);
    int insertSelective(ProjectActionException record);
    List<ProjectActionException> selectByExample(ProjectActionExceptionExample example);
    ProjectActionException selectByPrimaryKey(String id);
    int updateByExampleSelective(@Param("record") ProjectActionException record, @Param("example") ProjectActionExceptionExample example);
    int updateByExample(@Param("record") ProjectActionException record, @Param("example") ProjectActionExceptionExample example);
    int updateByPrimaryKeySelective(ProjectActionException record);
    int updateByPrimaryKey(ProjectActionException record);
    
    /**
     * 根据参数查询异常列表，按照上报时间倒序
     * @param queryBean
     * @return
     */
	List<ProjectActionExceptionVo> listException(PAExceptionQueryBean queryBean);

	/**
	 * 获取清单异常上报记录
	 * @param queryBean
	 * @return
	 */
	List<Map<String, Object>> listProjectActionExceptions(PAExceptionQueryBean queryBean);
	/**
	 * 通过父异常id查找对应的异常
	 * @param pId 关联的父id
	 * @return
	 */
	List<ProjectActionException> listByParentExceptionId(@Param("pId") String pId);
	/**
	 * 处理已操作的异常，把待确定改为已确定
	 * @param code
	 * @param date
	 */
	void sureException(@Param("code")String code, @Param("date")Date date);
	/**
	 * 关闭其它财务异常
	 * @param code
	 * @param id
	 */
	void closeOterException(@Param("code")String code,@Param("id") String id,@Param("dealAccount")String dealAccount,@Param("dealName")String dealName,@Param("date")Date date);

	/**
	 * 根据项目执行编码或项目编码更新确认状态
	 * @param projectCode
	 * @return
	 */
	int updateConfirmStatus(@Param("code")String code, @Param("type")Integer type);
	/**
	 * 获取项目的【父财务异常】
	 * @param projectCode 项目编号
	 * @return
	 */
	List<ProjectActionException> listParentExceptionByProjectCode(@Param("projectCode") String projectCode);
}