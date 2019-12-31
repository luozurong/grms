/**
 * 
 */
package com.hori.grms.service;

import java.util.List;

import com.hori.grms.queryBean.ProjectActionQueryBean;
import com.hori.grms.vo.ExportMGVo;

/** 
 * @ClassName: ProjectProductMenuAreaService 
 * @Description: 项目产品明细小区关联表
 * @author zhuqiang
 * @date 2018年8月8日 下午7:14:56 
 */
public interface ProjectProductMenuAreaService{

	/**
	 * 查找媒管部门需要导出的数据
	 * @param queryBean
	 * @return
	 */
	List<ExportMGVo> listMGData(ProjectActionQueryBean queryBean);

	/**
	 * 根据项目清单id查找关联的小区名称，适用于用户运营/媒管/电商<br/>
	 * 关联多个小区，则小区名称用,分隔。<br/>
	 * 如：天朗明居,骏景园,合理正通测试小区,...
	 * @param projectProductId
	 * @return
	 */
	String getAreaNamesByPPID(String projectProductId);

}
