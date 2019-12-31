package com.hori.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.dom4j.DocumentException;

import com.hori.dao.queryBean.UserManagementQueryBean;
import com.hori.dao.queryBean.UserQueryBean;
import com.hori.db.support.DataGridPage;
import com.hori.db.support.PageBean;
import com.hori.model.Author;
import com.hori.model.User;
import com.hori.pageModel.Customer;
import com.hori.pageModel.DataGrid;
import com.hori.ums.webservice.bean.UserDto;
import com.hori.ums.webservice.bean.UserWsQueryBean;
import com.hori.vo.AddUserVo;
import com.hori.vo.AuthVo;
import com.hori.vo.LoginForSystemVo;
import com.hori.vo.SelectVo;
import com.hori.vo.UserInfoDto;
import com.hori.vo.UserVo;


public interface UserService  extends BaseServiceI {

	/**
	 * 用户登录
	 * 
	 * @param userAccount password
	 * @return
	 */
	public User login(String userAccount,String password);

	/**
	 * 用户datagrid
	 * 
	 * @param queryBean
	 * @return
	 */
	public DataGrid datagrid(UserQueryBean queryBean);
	
	/**
	 * 用户下拉列表
	 * 
	 * @param q
	 * @return
	 */
	public List<UserVo> loginCombobox(String q);


	/**
	 * 获得当前用户所有权限
	 * 
	 * @param user
	 * @return
	 */
	public List<Author> getAuthVos(User u);
	

    public List<User> listAll();


    /**
     * 通过user 等级 类型获取
     * @param user
     * @return
     */
	public List<UserQueryBean> getAllbyType(String levelNo,String accuont);
	
	
	/**
	 * 获取跟进人
	 * @param levelNo
	 * @param accuont
	 * @param pb
	 * @return
	 */
	public DataGridPage getAllbyType(String levelNo,String accuont,PageBean pb);

	

    /**
     * 获取密码连续输入错误次数限制后登录需要验证码
     * @return
     */
    public int getPassErrerTimsLimitVevifiCodeNeed();

    /**
     * 通过账号获取相关信息
     * @param userAccount
     * @return
     */
    public User getUserByAccount(String userAccount);
    /**
     * 获取短信验证多数次后进行验证
     * @return
     */
    public int getCodeByMoblieTimsLimitVevifiCodeNeed();
    
    /**
     * 更改用户
     * @param user
     */
    public void update(User user);
    
    /**
     * 更新状态
     * @param id
     * @param status
     */
    public void updateStatus(String id,int status);
    
    /**
     * 查找id 获取用户信息
     * @param id
     * @return
     */
    public User getUserById(String id);
    
    /**
     * 保存用户
     * @param user
     */
	 void saveUser(User user);
	 /**
	  * 等级是否唯一
	  * @param levelNo
	  * @return
	  */
	 boolean isUniqueLevelNo(String levelNo);
	 
	 /**
	  * 生成唯一的用户等级
	  * @param levelNo
	  * @return
	  */
	 String createUniqueLevelNo(String levelNo);
	 
	 /**
	  * 对应的角色是否存在对应的用户
	  * @param roleId
	  * @return
	  */
	 boolean isExistUserByRoleId(String roleId);
	 /**
	  * 页面显示列表
	  * @param queryBean
	  * @return
	  */
	 public DataGridPage findUserPage(UserQueryBean queryBean);
	 /**
	  * 通过用户类型过滤用户
	  * @param userType
	  * @return
	  */
	 List<User> getUserByUserType(String userType);
     /**
      * 是否查看超管和广告管理员
      * @return
      */
     List<User> getSYSAndADSSYS();
     
 	/**
 	 * 根据参数分页查询人员管理页面
 	 * @return
 	 */
     public DataGridPage findUserManagementByParam(UserManagementQueryBean queryBean,String userType,byte dataArea,List<String> userList);

 	/**
	 * 获取门禁卡发卡时用到的用户DataGridPage
	 * 
	 * @param queryBean
	 * @return
	 */
	public DataGridPage queryUsers(UserQueryBean queryBean);
 	/**
 	 * 根据id获取用户信息列表
 	 * @param userIds
 	 * @return
 	 */
	public List<UserDto> getUsersByIds(String[] userIds);

	
	public List<String> getUserIds(UserQueryBean userQueryBean);

	/**
 	 * 根据id获取用户包括角色，部门，责任区域等的详细信息
 	 * @param userId 用户id
 	 * @return
 	 */
	public UserDto getUserDetailInfoById(String userId);

	/**
 	 * 根据手机号获取用户包括角色，部门等的详细信息
 	 * @param mobile 手机号
 	 * @return
 	 */
	public UserDto getUserDetailInfoByMobile(String mobile);
 	
	/**
	 * 当前账号及下级账号创建的角色
	 * @return
	 */
	public List<SelectVo> roleSelect(String systemId,String departId,String userAccount,byte dataArea,List<String> userList);
	
	/**
	 * 根据部门Id获取信息
	 * @return
	 */
	public List<SelectVo> getUserDetailById(String departId);
	
	/**
	 * 保存或者修改帐号信息
	 * @return
	 */
	public Map<String,Object>  saveOrUpdateAccount(AddUserVo addUserVo) throws ClientProtocolException, DocumentException, IOException;
	
	/**
	 * 登录接口
	 * @return
	 */
	public LoginForSystemVo loginForSystemByUms(String userAccount,String password,String systemId,String resourceApp);
	
	/**
	 * 修改绑定手机号码
	 * @return 
	 */
	
	public void alterMobile(User user,String mobile);

	public UserInfoDto getUserInfoByAccount(String account);
	/**
	 * 删除当前系统帐号关联角色
	 * @return 
	 */
	
	public Map<String,String> deleteAccountRole(String[] idsArray,String systemId);
	/**
	 * 发送手机验证码
	 * @return 
	 */
	
	public Map<String,String> sendMessge(String userAccount);
	/**
	 * 修改绑定手机
	 * @return 
	 */
	public Map<String,String> alterMobile(String userAccount,String code,String mobileNew);
	/**
	 * 修改密码
	 * @return 
	 */
	public Map<String,String> alterPassword(String userAccount,String passwordNew,String passwordOld);
	/**
	 * 根据帐号获取用户信息
	 * @param userAccount
	 * @return
	 */
	public Map<String, String> findUserDetailByUserAccount(String userAccount);
}
