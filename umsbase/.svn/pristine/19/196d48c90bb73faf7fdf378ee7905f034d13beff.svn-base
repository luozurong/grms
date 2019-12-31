package com.jlit.vdcs.webservice;


import javax.jws.WebService;

import net.sf.json.JSONObject;


@WebService
public interface IWorkOrderService {

	/**
	 * 生成工单
	 * @param terminalSerial
	 */
	public JSONObject createWorkOrder(String terminalSerial);
	
	/**
	 * 查询未处理完的工单，status<=9
	 * @param terminalSerial  终端序列号
	 * @return
	 */
	public JSONObject queryUndealWorkOrder(String terminalSerial);
	
	/**
	 * 查询工单生成的延迟时间
	 * @return
	 */
	public String queryWorkOrderDelayTime();
	/**
	 * 根据用户id查询未归结的工单数量
	 * @param account
	 * @return
	 */
	public long getWorkOrderNumByUserId(String id);
}
