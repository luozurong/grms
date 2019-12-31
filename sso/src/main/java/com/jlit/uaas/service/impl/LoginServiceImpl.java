package com.jlit.uaas.service.impl;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.hori.vo.AuthorLoginVo;
import com.hori.vo.LoginForSystemLoginVo;
import com.hori.vo.UserDetailLoginVo;
import com.jlit.uaas.enums.LoginKeys;
import com.jlit.uaas.oauth2.servlet.ValidateCodeServlet;
import com.jlit.uaas.service.LoginService;
import com.jlit.uaas.service.UserService;
import com.jlit.uaas.util.CookieUtil;
import com.jlit.uaas.util.GlobalPropertiesValue;
import com.jlit.uaas.util.HttpConnectUtil;
import com.jlit.uaas.util.LoginUtil;
import com.jlit.uaas.util.MD5Util;
import com.jlit.vdcs.webservice.ILoginService;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import net.sf.json.JSONObject;


@Service("loginService")
public class LoginServiceImpl implements LoginService {
	private static final Logger logger=Logger.getLogger(LoginServiceImpl.class);
	private static final SimpleDateFormat DateSDF=new SimpleDateFormat("yyyyMMdd");
	private static final String PASS_ERRE_FIX="password_errer_";
	@Resource(name="userService")
	private UserService userService;
	@Resource
	private ILoginService iLoginService;
	@Resource(name="redisTemplate")
	private RedisTemplate  redisTemplate;
	/**
	 * 当天连续输入3密码错误后登录需要验证码
	 */
	private int passErrerTimsLimitVevifiCodeNeed=3;
	@SuppressWarnings("unchecked")
	@Override
	public LoginResultBean login(String userAccount, String password,
			String vevifiCode, HttpServletRequest request,HttpServletResponse response) {
		LoginResultBean resultBean=null;
		String loginName=userAccount;//页面输入的用户名
		//如果用户连续输入密码错误N次，则需要校验验证码
		int password_erre_times=getPassErrerTims(loginName);
		if(password_erre_times>=getPassErrerTimsLimitVevifiCodeNeed()){
			String sessionVevifiCode=(String) request.getSession().getAttribute(ValidateCodeServlet.LOGIN_VEVIFICODE);	
			if(StringUtils.isNotBlank(vevifiCode)&&StringUtils.isNotBlank(sessionVevifiCode)//验证码不区分大小写
					&&sessionVevifiCode.toLowerCase().equals(vevifiCode.toLowerCase())){
				
			}else{
				resultBean=new LoginResultBean("3", "验证码不正确！");
				saveLoginLogger(request,userAccount,false);
				logger.info("用户登录失败："+loginName+",错误：验证码不正确！");
				return resultBean;
			}
		}
		
		//获取登录平台code
		String selected_platform= "1"; //测试 先注释下面真实获取
//		selected_platform = (String) request.getSession().getAttribute("redirectUri");
//		if(selected_platform != null && !"".equals(selected_platform)){
//			selected_platform  = selected_platform.substring(0,selected_platform.lastIndexOf("/"));
//			selected_platform = selected_platform.substring(selected_platform.lastIndexOf("/")+1,selected_platform.length());
//		}
		
		request.getSession().setAttribute(LoginKeys.USERPLATFORM.getValue(), selected_platform);
		
		String umsLoginBack = "";
		String resourceApp = "0"; //登录类别，0：平台，1：客户端
		//调用ums登录接口，验证用户账号
		umsLoginBack = this.vaildLoginUser(userAccount, password, selected_platform, resourceApp);
		
		//logger.info("umsLoginBack:::"+umsLoginBack);
		
		
		if(null!=umsLoginBack && !"".equals(umsLoginBack)){
			JSONObject object=JSONObject.fromObject(umsLoginBack);
			String resultCode = object.getString("result");
			
			if("1".equals(resultCode)){
				resultBean=new LoginResultBean("1", "用户不存在");
				saveLoginLogger(request,userAccount,false);
				logger.info("用户登录失败："+userAccount+",错误：用户账号不存在");
				return resultBean;
			}
			if("3".equals(resultCode)){
				resultBean=new LoginResultBean("3", "为用户为停用状态请联系管理员");
				saveLoginLogger(request,userAccount,false);
				logger.info("用户登录失败："+userAccount+",错误：为用户为停用状态请联系管理员");
				return resultBean;
			}
			if("4".equals(resultCode)){
				resultBean=new LoginResultBean("4", "传入参数错误");
				saveLoginLogger(request,userAccount,false);
				logger.info("用户登录失败："+userAccount+",错误：传入参数错误");
				return resultBean;
			}
			
			if("0".equals(resultCode)){//登录验证成功
				
				//有权限的按钮集合
				Set<String> resourceButtonCodeSet = new HashSet<String>();
				//有权限的url集合
				Set<String> resourceUrlSet = new HashSet<String>();
				
				LoginForSystemLoginVo loginForSystemVo = LoginUtil.Json2LoginForSystemVo(umsLoginBack);
				
				String userType = loginForSystemVo.getUserType();
				String userToken = loginForSystemVo.getToken();
				
				UserDetailLoginVo userDetailVo = loginForSystemVo.getUserDetail();
				List<AuthorLoginVo> resourceMenu1 = loginForSystemVo.getResourceMenu1(); 
				List<AuthorLoginVo> resourceMenu2 = loginForSystemVo.getResourceMenu2(); 
				List<AuthorLoginVo> resourceButton = loginForSystemVo.getResourceButton(); 
				
				if(resourceMenu1 != null && resourceMenu1.size()>0){
					for(AuthorLoginVo menu2Vo:resourceMenu1){
						//保存有权限模块的URL
						String resourceUrl = menu2Vo.getResourceUrl();
						if(null != resourceUrl && !"".equals(resourceUrl)){
							String[] resourceUrlArray = resourceUrl.split("#");
							for(int i=0;i<resourceUrlArray.length;i++){
								resourceUrlSet.add(resourceUrlArray[i]);
							}
						}
						
					}
				}
				
				if(resourceMenu2 != null && resourceMenu2.size()>0){
					for(AuthorLoginVo menu2Vo:resourceMenu2){
						//保存有权限模块的URL
						String resourceUrl = menu2Vo.getResourceUrl();
						if(null != resourceUrl && !"".equals(resourceUrl)){
							String[] resourceUrlArray = resourceUrl.split("#");
							for(int i=0;i<resourceUrlArray.length;i++){
								resourceUrlSet.add(resourceUrlArray[i]);
							}
						}
						
					}
				}
				
				if(resourceButton != null && resourceButton.size()>0){
					for(AuthorLoginVo buttonVo:resourceButton){
						//保存有权限的按钮
						resourceButtonCodeSet.add(buttonVo.getResourceCode());
						//保存有权限按钮的URL
						String resourceUrl = buttonVo.getResourceUrl();
						if(null != resourceUrl && !"".equals(resourceUrl)){
							String[] resourceUrlArray = resourceUrl.split("#");
							for(int i=0;i<resourceUrlArray.length;i++){
								resourceUrlSet.add(resourceUrlArray[i]);
							}
						}
						
					}
				}
				
				
				//保存用户账号到cookie，有效期7天
				CookieUtil.setCookie(request, response, LoginKeys.USERACCOUNT.getValue(), userAccount, 7*24*3600);
				request.getSession().setAttribute(LoginKeys.USERACCOUNT.getValue(), userAccount);
				request.getSession().setAttribute(LoginKeys.USERTYPE.getValue(), userType);
				request.getSession().setAttribute(LoginKeys.USERTOKEN.getValue(), userToken);
				request.getSession().setAttribute(LoginKeys.USERNAME.getValue(), userDetailVo.getName());
				request.getSession().setAttribute(LoginKeys.USERDETAIL.getValue(), userDetailVo);
				request.getSession().setAttribute(LoginKeys.RESOURCEMENU1.getValue(), resourceMenu1);
				request.getSession().setAttribute(LoginKeys.RESOURCEMENU2.getValue(), resourceMenu2);
				request.getSession().setAttribute(LoginKeys.RESOURCEBUTTON.getValue(), resourceButton);
				request.getSession().setAttribute(LoginKeys.RESOURCEBUTTONCODE.getValue(), resourceButtonCodeSet);
				request.getSession().setAttribute(LoginKeys.RESOURCEBUTTONURL.getValue(), resourceUrlSet);
				//添加用户类型
				request.getSession().setAttribute(LoginKeys.ROLETYPE.getValue(), loginForSystemVo.getRoleType());
				request.getSession().setAttribute(LoginKeys.DATAAREA.getValue(),loginForSystemVo.getDataArea());
				request.getSession().setAttribute(LoginKeys.ROLENAME.getValue(), loginForSystemVo.getRoleName());

				if(loginForSystemVo.getUserList()!=null){
					request.getSession().setAttribute(LoginKeys.USERLIST.getValue(),loginForSystemVo.getUserList());
				}else{
					request.getSession().setAttribute(LoginKeys.USERLIST.getValue(),new ArrayList<String>());

				}
				if(loginForSystemVo.getDepartList()!=null){
					request.getSession().setAttribute(LoginKeys.DEPARTLIST.getValue(),loginForSystemVo.getDepartList());
				}else{
					request.getSession().setAttribute(LoginKeys.DEPARTLIST.getValue(),new ArrayList<String>());

				}
				resultBean=new LoginResultBean("0", null);
				//清除记录用户当天登录密码输入错误次数的记录
				String mKey=getPassErrerTimsKey(loginName);
				//SessionService.getInstance().remove(mKey);
				this.redisTemplate.delete(mKey);
/*				saveLoginLogger(request,userAccount,true);
*/				logger.info("用户登录成功："+userAccount);
				logger.info("用户登录成功,清除记录用户当天登录密码输入错误次数的记录,mKey："+mKey);
			}else{//密码错误
				//记录用户当前登录密码输入错误次数
				String mKey=getPassErrerTimsKey(loginName);
				++password_erre_times;
				//错误次数存在memcache，有限期2天
				/*Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.DAY_OF_MONTH, 2);// 有效期2天*/				
				this.redisTemplate.opsForValue().set(mKey, password_erre_times+"", 48, TimeUnit.HOURS);
				//SessionService.getInstance().saveOutTime(mKey, password_erre_times, calendar.getTime());
				logger.info("用户登录失败,存储用户当天登录密码连续输入错误次数到memcached,mKey："+mKey+",错误次数："+password_erre_times);
				
				resultBean=new LoginResultBean("1", "密码错误！");
				saveLoginLogger(request,userAccount,false);
				logger.info("用户登录失败："+userAccount+",错误：密码错误");
			}
		}
		return resultBean;
	}
	@Override
	public boolean isLogin(HttpServletRequest request) {
		String  userAccount=(String) request.getSession().getAttribute(LoginKeys.USERACCOUNT.getValue());
		if(StringUtils.isNotBlank(userAccount)){
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public String logout(HttpServletRequest request,
			HttpServletResponse response) {
		//清除会话信息
		request.getSession().invalidate();
		return "success";
	}
	@Override
	public String getPassErrerTimsKey(String userAccount) {
		//当天时间格式字符串
		Date now=new Date();
		String currentDateStr=DateSDF.format(now);
		//记录用户当前登录密码输入错误次数
		String mKey=PASS_ERRE_FIX+userAccount+"_"+currentDateStr;
		return mKey;
	}
	@Override
	public int getPassErrerTims(String userAccount) {
		int m=0;
		if(StringUtils.isNotBlank(userAccount)){
			Object o=(Object) this.redisTemplate.opsForValue().get(getPassErrerTimsKey(userAccount));
			if(null==o){
				m=0;
			}else{
				m = Integer.parseInt(o.toString());
			}
		}
		return m;
	}
	@Override
	public int getPassErrerTimsLimitVevifiCodeNeed() {
		return passErrerTimsLimitVevifiCodeNeed;
	}
	
	/**
	 * 保存登录历史记录
	 * @param request
	 * @param userAccount	用户账号
	 * @param resultS		登录是否成功
	 */
	public void saveLoginLogger(HttpServletRequest request,String userAccount,boolean resultS){
		//获得IP地址
		String ip = request.getRemoteAddr();
		//获取客户端浏览器
		UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));  
	    Browser userbrowser = userAgent.getBrowser();  
	    OperatingSystem useros = userAgent.getOperatingSystem();
		iLoginService.login(userAccount, ip, resultS, userbrowser.getName(), useros.getName());
	}
	
	/**
	 * 验证用户登录权限
	 * @param userAccount	用户登录账号
	 * @param password		用户登录密码
	 * @param selected_platform	登录系统CODE
	 * @param resourceApp	登录类别，0：平台，1：客户端
	 * @return
	 */
	public String vaildLoginUser(String userAccount,String password,String selected_platform,String resourceApp){
		String umsLoginBack = "";
		String umsServletUrl = GlobalPropertiesValue.umsServletUrl + "/servlet/umsLoginServlet";
		password=MD5Util.getMD5EncodeUpper(password);
		Map<String,String> map = new HashMap<String,String>();
		map.put("userAccount", userAccount);
		map.put("password", MD5Util.getMD5EncodeUpper(password+userAccount));
		map.put("systemId", selected_platform);
		map.put("resourceApp", resourceApp);
		Map<String,Map<String,String>> mapResult = new HashMap<String,Map<String,String>>();
		mapResult.put("body", map);
		mapResult.put("header", new HashMap<String,String>());
		try {
			String content=JSONObject.fromObject(mapResult).toString();
			umsLoginBack = HttpConnectUtil.getInstance().sendStrOfPost(umsServletUrl, content);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return umsLoginBack;
	}
	
	public void setiLoginService(ILoginService iLoginService) {
		this.iLoginService = iLoginService;
	}
	
}
