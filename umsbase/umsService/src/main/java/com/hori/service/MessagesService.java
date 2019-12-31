package com.hori.service;

import java.math.BigInteger;
import java.util.List;

import com.hori.db.support.DataGridPage;
import com.hori.model.Messages;
import com.hori.pageModel.MessagesPage;

public interface MessagesService {
	public void addMessages(Messages messages);
	
	public void addMessages(String content , String levelNo);

	public DataGridPage datagrid(MessagesPage messagesPage);

	public void changeStatusById(String ids);

	public BigInteger totalCountByAccount(String userLevelNo);

	public void bachSave(List<Messages> messages);
}
