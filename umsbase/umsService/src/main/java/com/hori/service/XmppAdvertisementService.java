package com.hori.service;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.dom4j.DocumentException;

import com.hori.model.User;
import com.hori.vo.OfUserVo;

/**
 * xmpp服务器广告管理
 *@author kls
 */

public interface XmppAdvertisementService extends BaseServiceI{
	
	/**
	 * 广告上下线的消息推送
	 * @param accountList 终端序列号
	 * @param contents 消息内容
	 * @param subjects 消息主题
	 * @param batcheSize 每次发送的终端数，建议为50或100
	 */
	public void pushUpdateAdvertisementStatus(List<String> accountList, String contents, String subjects,
			int batcheSize, boolean online, String resource)  throws ClientProtocolException, IOException, InterruptedException ;

	/**
	 * 添加账户
	 * @param user
	 * @param password 用户密码，需要是明文;不能为空
	 * @return 0 成功  1用户名已经存在 2服务器其他异常
	 */
	int addUser(OfUserVo ofUserVo,String password)throws DocumentException, ClientProtocolException, IOException;

	/**
	 * 删除账户及好友关联关系
	 * @param userAccount
	 * @return 0 成功 1用户不存在 2服务器其他异常
	 */
	int deleteUser(String userAccount) throws DocumentException, ClientProtocolException, IOException;
	/**
	 * 修改用户
	 * @param userAccount
	 * @return 0 成功 1用户不存在 2服务器其他异常
	 */
	public int updateUser(User user,String newPassword,String name) throws DocumentException,
	ClientProtocolException, IOException;

}
