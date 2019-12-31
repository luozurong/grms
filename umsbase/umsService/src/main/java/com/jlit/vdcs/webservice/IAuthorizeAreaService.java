package com.jlit.vdcs.webservice;

import java.util.List;

import javax.jws.WebService;


/**
 * 运维人员责任区域webservice
 * @author sucs
 *
 */
@WebService
public interface IAuthorizeAreaService {

	/**
	 * 更新用户的责任区域
	 * @param userId 用户id
	 * @param newAreaIds 新增的区域id列表
	 * @param delAreaIds 删除的区域id列表
	 */
	public void updateUserAuthorizeAreas(String userId, List<String> newAreaIds,
			List<String> delAreaIds);
}
