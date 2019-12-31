package com.hori.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hori.model.User;
import com.hori.service.XmppAdvertisementService;
import com.hori.util.HttpClientUtil;
import com.hori.util.enums.OpenfireResultType;
import com.hori.vo.OfUserVo;

import net.sf.json.JSONObject;

@Service("xmppAdvertisementService")
public class XmppAdvertisementServiceImpl extends BaseServiceImpl implements XmppAdvertisementService{
	
	private final static Logger logger=Logger.getLogger(XmppAdvertisementServiceImpl.class);
	/**
	 * xmpp服务器userService插件servlet访问地址
	 */
	public final static String XMPP_USERSERVICE_URL="/plugins/userService/userservice";
	/**
	 *  xmpp服务器servlet插件获取好友联系人访问地址
	 */
	public final static String XMPP_GETUSERROSTER_URL="/plugins/servlets/getuserroster";
	/**
	 * xmpp服务器地址
	 */
	@Value("${xmpp_server_address}")private String xmppServerHost;
	/**
	 * xmpp服务器servelet端口
	 */
	@Value("${xmpp_server_port}")private String xmppServerPort;
	/**
	 * 
	 */
	@Value("${xmpp_userservice_secret}")private String xmppUserserviceSecret;
	
	
	
	@Override
	public void pushUpdateAdvertisementStatus(List<String> accountList, String contents, String subjects,
			int batcheSize, boolean online, String resource) throws ClientProtocolException, IOException, InterruptedException {
		 //设置多少条信息发送一次
	      if(accountList!=null&& accountList.size()>0){
	    	  int size=accountList.size();
	    	  String tampAccounts="";
	    	  for(int i=0;i<size;i++){
	    		  tampAccounts=tampAccounts+accountList.get(i)+",";
	    		  if(i%batcheSize==0){//每50条发送一次
	    			  pushmultisysmsg( tampAccounts, contents, subjects,online,resource);
	    			  // 睡眠1S毫秒  ;
		    		  TimeUnit.SECONDS.sleep(1);
	    			  //清空暂存数据
	    			  tampAccounts="";
	    			  continue;
	    		  }
	    		  
	    		  //如果到最后一条数据，得发送一次
	    		  if(i==(size-1)){
	    			  pushmultisysmsg( tampAccounts, contents, subjects,online,resource);
	    			 tampAccounts="";
	    		  }
	    	  }
	      }
	}
	
	/**
	 * 一次给多个终端推送消息
	 *@author kls
	 *@param toAcount 目的账号拼接成的字符串（账号1,账号2）
	 *@param contents 消息内容
	 *@param subjects 消息主题
	 *@param online 
	 *@param resource
	 *@return
	 *@throws ClientProtocolException
	 *@throws IOException
	 */
	private String pushmultisysmsg(String toAcount, String contents,
			String subjects, boolean online,String resource) throws ClientProtocolException, IOException {
		List<NameValuePair> paramsList=new ArrayList<NameValuePair>();
		//参数
		NameValuePair to=new BasicNameValuePair("to", toAcount);
		NameValuePair body=new BasicNameValuePair("body", contents);
		NameValuePair subject=new BasicNameValuePair("subject", "");
		paramsList.add(to);
		paramsList.add(body);
		paramsList.add(subject);
		if(online){//指定只发送给在线的用户
			paramsList.add(new BasicNameValuePair("online", "true"));
		}
		if(StringUtils.isNotBlank(resource)){//资源号
			paramsList.add(new BasicNameValuePair("resource", resource));
		}
		String url="http://"+this.xmppServerHost+":"+xmppServerPort+"/plugins/servlets/pushmultisysmsg";
		System.out.println("推送xmpp系统消息,account:"+toAcount+",subjects:"+subjects+",contents:"+contents+",params:"+paramsList);
		String resultStr=HttpClientUtil.post(url, paramsList);
		System.out.println("###消息推送结果："+resultStr);
		return resultStr;
	}
	
	@Override
	public int addUser(OfUserVo ofUserVo ,String password) throws DocumentException, ClientProtocolException, IOException {
		logger.info("新增xmpp用户，用户名："+ofUserVo.getUserAccount()+",用户密码："+password+",用户姓名："+ofUserVo.getUserName());
		//返回结果
		int resultCode=OpenfireResultType.RESULT_FAIL.getValue();
		//http://example.com:9090/plugins/userService/userservice?type=add&secret=bigsecret&username=kafka&password=drowssap&name=f
		String url="http://"+this.xmppServerHost+":"+xmppServerPort+XMPP_USERSERVICE_URL;
		List<NameValuePair> paramsList=new ArrayList<NameValuePair>();
		//参数
		NameValuePair typeNVP=new BasicNameValuePair("type", "add");
		NameValuePair secretNVP=new BasicNameValuePair("secret", this.xmppUserserviceSecret);
		NameValuePair usernameNVP=new BasicNameValuePair("username", ofUserVo.getUserAccount());
		NameValuePair passwordNVP=new BasicNameValuePair("password", password);
		NameValuePair nameNVP=new BasicNameValuePair("name", ofUserVo.getUserName());
		paramsList.add(typeNVP);
		paramsList.add(secretNVP);
		paramsList.add(usernameNVP);
		paramsList.add(passwordNVP);
		paramsList.add(nameNVP);
		//xml格式的返回值
		String resultXMl=HttpClientUtil.post(url, paramsList);
		logger.info("新增xmpp用户调用opfire服务器接口结果："+resultXMl);
		Document document= DocumentHelper.parseText(resultXMl);
		Element root = document.getRootElement();
		if(root.getName().equals("result")){//成功的响应
			if(root.getText().equals("ok")){
				resultCode=OpenfireResultType.RESULT_SUCCESS.getValue();//添加用户成功
			}
		}else if(root.getName().equals("error")){
			if(root.getText().equals("UserAlreadyExistsException")){
				resultCode=OpenfireResultType.RESULT_ERROR.getValue();//用户名存在
			}
		}
		return resultCode;
	}
	
	@Override
	public int deleteUser(String userAccount) throws DocumentException, ClientProtocolException, IOException{
		//http://example.com:9090/plugins/userService/userservice?type=delete&secret=bigsecret&username=kafka
		//返回结果
		int resultCode=2;
		String url="http://"+this.xmppServerHost+":"+xmppServerPort+XMPP_USERSERVICE_URL;
		//参数
		List<NameValuePair> paramsList=new ArrayList<NameValuePair>();
		NameValuePair typeNVP=new BasicNameValuePair("type", "delete");
		NameValuePair secretNVP=new BasicNameValuePair("secret",  this.xmppUserserviceSecret);
		NameValuePair usernameNVP=new BasicNameValuePair("username", userAccount);
		paramsList.add(typeNVP);
		paramsList.add(secretNVP);
		paramsList.add(usernameNVP);
		//xml格式的返回值
		String resultXMl=HttpClientUtil.post(url, paramsList);
		Document document= DocumentHelper.parseText(resultXMl);
		Element root = document.getRootElement();
		if(root.getName().equals("result")){//成功的响应
			if(root.getText().equals("ok")){
				resultCode=0;//删除用户成功
			}
		}else if(root.getName().equals("error")){
			if(root.getText().equals("UserNotFoundException")){
				resultCode=1;//用户不存在
			}
		}
		return resultCode;
	}
	
	@Override
	public int updateUser(User user,String newPassword,String name) throws DocumentException,
			ClientProtocolException, IOException {
		
		//http://example.com:9090/plugins/userService/userservice?type=update&secret=bigsecret&username=kafka
		//&password=drowssap&name=franz&email=beetle@kafka.com
		//返回结果
		int resultCode=2;
		String url="http://"+this.xmppServerHost+":"+xmppServerPort+XMPP_USERSERVICE_URL;
		//参数
		List<NameValuePair> paramsList=new ArrayList<NameValuePair>();
		NameValuePair typeNVP=new BasicNameValuePair("type", "update");
		NameValuePair secretNVP=new BasicNameValuePair("secret", this.xmppUserserviceSecret);
		NameValuePair usernameNVP=new BasicNameValuePair("username", user.getUserAccount());
		if(StringUtils.isNotBlank(newPassword)){
			NameValuePair passwordNVP=new BasicNameValuePair("password", newPassword);
			paramsList.add(passwordNVP);
		}
		if(StringUtils.isNotBlank(name)){
			NameValuePair nameNVP=new BasicNameValuePair("name", name);
	
			paramsList.add(nameNVP);
	}
		if(StringUtils.isNotBlank(user.getMobile())){
			NameValuePair emailNVP=new BasicNameValuePair("email", user.getMobile());//openfire服务器用户表email字段存用户的手机号
			paramsList.add(emailNVP);
		}
		paramsList.add(typeNVP);
		paramsList.add(secretNVP);
		paramsList.add(usernameNVP);
		//xml格式的返回值
		String resultXMl=HttpClientUtil.post(url, paramsList);
		Document document= DocumentHelper.parseText(resultXMl);
		Element root = document.getRootElement();
		if(root.getName().equals("result")){//成功的响应
			if(root.getText().equals("ok")){
				resultCode=0;//更新用户成功
			}
		}else if(root.getName().equals("error")){
			if(root.getText().equals("UserNotFoundException")){
				resultCode=1;//用户不存在
			}
		}
		return resultCode;
	}
	
	
	public String getXmppServerHost() {
		return xmppServerHost;
	}

	public void setXmppServerHost(String xmppServerHost) {
		this.xmppServerHost = xmppServerHost;
	}

	public String getXmppServerPort() {
		return xmppServerPort;
	}

	public void setXmppServerPort(String xmppServerPort) {
		this.xmppServerPort = xmppServerPort;
	}

	public String getXmppUserserviceSecret() {
		return xmppUserserviceSecret;
	}

	public void setXmppUserserviceSecret(String xmppUserserviceSecret) {
		this.xmppUserserviceSecret = xmppUserserviceSecret;
	}

	public static void main(String[] args) throws ClientProtocolException, IOException, InterruptedException {
		XmppAdvertisementServiceImpl impl = new XmppAdvertisementServiceImpl();
		Map<String,Object> contengMap=new HashMap<String, Object>();
		Map<String,String> content=new HashMap<String,String>();
		List<String> accountList = new ArrayList<String>();
		content.put("title", "更新广告的通知！");
		contengMap.put("type", "1060001");
		contengMap.put("content", content);
		String s=JSONObject.fromObject(contengMap).toString();
		impl.setXmppServerHost("tt.hori-gz.com");
		impl.setXmppServerPort("9099");
		accountList.add("04100300HL1200100201605230000032");
		impl.pushUpdateAdvertisementStatus(accountList, s, "", 50, false, null);
	}
  
}
