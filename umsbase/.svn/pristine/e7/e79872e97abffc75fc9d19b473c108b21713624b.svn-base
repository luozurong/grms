package com.hori.service.impl;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hori.dao.MessagesDao;
import com.hori.db.support.DataGridPage;
import com.hori.model.Messages;
import com.hori.pageModel.MessagesPage;
import com.hori.service.MessagesService;

@Service("messagesService")
public class MessagesServiceImpl extends BaseServiceImpl   implements MessagesService {
    
	@Autowired
	private MessagesDao  messagesDao;
	@Override
	public void addMessages(Messages messages) {
		messages.setStatus(0);
        this.messagesDao.save(messages);
	}
	
	@Override
	public void addMessages(String content, String levelNo) {
		// TODO Auto-generated method stub
		Messages messages = new Messages();
		messages.setStatus(0);
		messages.setViewLevelNo(levelNo);
		messages.setContent(content);
		this.messagesDao.save(messages);
	}
	
	@Override
	public DataGridPage datagrid(MessagesPage messagesPage) {
		return this.messagesDao.datagrid(messagesPage);
	}
	
	/**
	 * 批量更新消息的状态
	 * @param ids 每个id用逗号隔开
	 */
	@Override
	public void changeStatusById(String ids) {
		this.messagesDao.changeStatusById(ids);
		
	}
	
	/**
	 * 通过用户权限等级统计未处理的消息
	 */
	@Override
	public BigInteger totalCountByAccount(String userLevelNo) {
		return this.messagesDao.totalCountByAccount(userLevelNo);
	}
   
	/**
	 * 批量添加消息记录
	 */
	@Override
	public void bachSave(List<Messages> messages) {
		this.messagesDao.bachSave(messages);	
	}


	

}
