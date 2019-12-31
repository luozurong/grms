package com.hori.service;

import java.util.List;

import com.hori.dao.queryBean.RoleQueryBean;
import com.hori.db.support.DataGridPage;
import com.hori.model.Role;
import com.hori.pageModel.DataGrid;
import com.hori.vo.EasyUiTreeVo;


/**
 * 角色Service
 * 
 * @author hhb
 * 
 */
public interface RoleService extends BaseServiceI {

	/**
	 * 根据系统初始化信息
	 * @param 
	 * @return
	 */
	List<Object> initRoleBySystemId(String systemId);
	
	
	/**
	 * 根据系统初始化菜单信息
	 * @param 
	 * @return
	 */
	List<EasyUiTreeVo> initSystemMenu(String systemId,String menuApp);
	
	/**
	 * 创建角色及相关权限
	 * @param 
	 * @return
	 */
	public void createSystemRole(List<EasyUiTreeVo> easyUiTreeVo,Role role);

	/**
	 * 初始化角色及相关权限
	 * @param 
	 * @return
	 */
	public List<EasyUiTreeVo> initSystemMenuAlter(String systemId,String roleId,String menuApp);
	/**
	 * 修改角色及相关权限
	 * @param 
	 * @return
	 */
	public void updateSystemRole(List<EasyUiTreeVo> easyUiTreeVo,Role role);
	/**
	 * 删除角色及相关权限
	 * @param 
	 * @return
	 */
	public int delSystemRole(String roleId);
	/**
	 * 分页查询角色
	 * @param 
	 * @return
	 */ 
	public DataGridPage findRoleByParam(RoleQueryBean queryBean,String userType,byte dataArea,List<String> userList);
	/**
	 * 检查角色是否重复
	 * @param 
	 * @return
	 */ 
    public boolean checkForRoleName(String roleName,String roleId,String systemId );
	/**
	 * 根据菜单类型返回按钮
	 * @param 
	 * @return
	 */ 
    public List<EasyUiTreeVo> initSystemMenuForApp(String systemId,String roleId,String menuApp,String menuType);
    /**
	 * 根据菜单类型返回按钮
	 * @param 
	 * @return
	 */ 
	
  	 public List<EasyUiTreeVo> initSystemMenuApp(String systemId,String menuApp,String menuType);

	/**
	 * 初始化菜单修改非管理员
	 * @return
	 */
	public List<EasyUiTreeVo> initSystemMenuAlterPeople(String systemId,String roleId,String menuApp,String userAccount);
 	/**
 	 * 初始化菜单添加非管理员
 	 * @return
 	 */
	public List<EasyUiTreeVo> initSystemMenuPeople(String systemId,String menuApp,String userAccount);
}
