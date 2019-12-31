package com.hori.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hori.dao.RoleAuthDao;
import com.hori.model.RoleAuth;
import com.hori.service.RoleAuthService;
@Service
public class RoleAuthServiceImpl extends BaseServiceImpl implements RoleAuthService {
	@Autowired
	public RoleAuthDao roleAuthDao;

	@Override
	public void saveRoleAuth(RoleAuth roleAuth) {
		this.roleAuthDao.save(roleAuth);
	}

	@Override
	public List<RoleAuth> getRoleAuthListByRoleId(String roleId) {
		return roleAuthDao.getRoleAuthListByRoleId(roleId);
	}

	@Override
	public void deleteRoleAuthByRoleId(String roleId) {
		this.roleAuthDao.deleteRoleAuthByRoleId(roleId);
	}

	
}
