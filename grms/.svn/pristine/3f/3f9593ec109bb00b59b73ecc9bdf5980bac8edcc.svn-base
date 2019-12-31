package com.hori.grms.service;

import java.util.Map;
import java.util.Set;

import com.hori.grms.vo.AreaDataReqVo;
import com.hori.grms.vo.AreaDataRspVo;

/**
 * 小区数据服务
 * @author laizs
 * @time 2018年8月15日下午7:30:18
 */
public interface AreaDataService {
	/**
	 * 查询小区数据
	 * @param reqVo
	 * @return
	 */
	AreaDataRspVo searchAreaDataList(AreaDataReqVo reqVo);
	/**
	 * 根据小区机构编号列表查询小区数据<br>
	 * @param organizationSeqs
	 * @return 结果数
	 */
	Map<String,AreaDataRspVo.AreaData> getAreaDataByOrganizationSeqs(Set<String> organizationSeqs);
}
