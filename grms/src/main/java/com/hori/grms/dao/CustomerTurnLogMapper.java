package com.hori.grms.dao;

import com.hori.grms.model.CustomerTurnLog;

public interface CustomerTurnLogMapper {
	/**
	 * 向customer_turn_log表插入一条记录
	 */
	int insert(CustomerTurnLog ctl);
}
