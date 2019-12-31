package com.hori.service;

import java.util.List;

import com.hori.model.Auth;
import com.hori.pageModel.TreeNode;
import com.hori.vo.AuthVo;

public interface AuthService extends BaseServiceI {

	/**
	 * 根据请求参数,去权限表查询看是否存在配置
	 * @param auth
	 * @return
	 */
	public boolean findAuthExist(String curl);
	
	/**
	 * 获得权限treegrid
	 * 
	 * @param auth
	 * @return
	 */
	public List<AuthVo> treegrid(AuthVo authVo);

	/**
	 * 删除权限
	 * 
	 * @param auth
	 */
	public void delete(AuthVo authVo);

	/**
	 * 获取权限树
	 * 
	 * @param auth
	 * @param b
	 *            是否递归子节点
	 * @return
	 */
	public List<TreeNode> tree(AuthVo authVo, boolean b);
	
	
	/**
	 * 获取权限树
	 * 
	 * @param auth
	 * @param b
	 *            是否递归子节点
	 * @return
	 */
	public List<TreeNode> treeByCtype(AuthVo authVo, boolean b);

	/**
	 * 添加权限
	 * 
	 * @param auth
	 */
	public void add(AuthVo authVo);

	/**
	 * 编辑权限
	 * 
	 * @param auth
	 */
	public void edit(AuthVo authVo);
	
	/**
	 * 获取一级菜单
	 * @return
	 */
	List<Auth> getTopMenu();
	/**
	 * 角色获取一级菜单
	 * @param roleId
	 * @return
	 */
	List<AuthVo> getOneLevelMenu(String roleId);
	
	/**
	 * 父节点查找auth
	 * @param pid
	 * @return
	 */
	List<Auth> getAuthByPid(String pid);

}
