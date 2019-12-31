package com.hori.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.http.client.ClientProtocolException;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hori.dao.TerminalDao;
import com.hori.dao.queryBean.TerminalQueryBean;
import com.hori.db.support.DataGridPage;
import com.hori.model.Terminal;
import com.hori.pageModel.TerminalPage;
import com.hori.pageModel.TreeNode;
import com.hori.service.TerminalService;
import com.hori.service.XmppAdvertisementService;
import com.hori.util.enums.OpenfireResultType;
import com.hori.utils.StaticValue;
import com.hori.vo.OfUserVo;
import com.hori.vo.ProvinceVo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("terminalService")
public class TerminalServiceImpl extends BaseServiceImpl implements TerminalService{
	@Autowired
	private TerminalDao terminalDao;
	@Autowired
	private XmppAdvertisementService xmppAdvertisementService;
	/**
	 * 推送消息到哪个设备的资源号
	 */
	@Value("${xmpp_resource}")
	private String xmppResource;
	
	/**
	 * 预览终端默认密码
	 */
	private final static String TERMINALPASSWORD = StaticValue.TERMINAL_PASSWORD;
	@Override
	public List<Terminal> getUpdateTerminalByTime(TerminalQueryBean queryBean) {
		return this.terminalDao.getUpdateTerminalByTime(queryBean);
	}

	@Override
	public List<Terminal> getByQueryBean(TerminalQueryBean queryBean) {
		// TODO Auto-generated method stub
		return this.terminalDao.getByQueryBean(queryBean);
	}

	@Override
	public void updateTerminals(List<Terminal> terminals) {
		 this.terminalDao.batchUpdateTerminal(terminals);
	}

	@Override
	public List<Terminal> getAddTerminalByTime(TerminalQueryBean queryBean) {
		return this.terminalDao.getAddTerminalByTime(queryBean);
	}

	@Override
	public void saveAll(List<Terminal> terminals) {
		terminalDao.saveAll(terminals);
	}

	@Override
	public Terminal getBySerial(String serial) {
		return this.terminalDao.getBySerial(serial);
	}

	@Override
	public DataGridPage datagrid(TerminalPage terminalPage) {
		return this.terminalDao.datagrid(terminalPage);
	}
  
	@Override
	public List<ProvinceVo> totalProvince(String code) {
		return this.terminalDao.totalProvince(code);
	}

	@Override
	public List<ProvinceVo> totalCommunity(String provinceCode, String cityCode, String countryCode) {
		return this.terminalDao.totalCommunity(provinceCode,cityCode,countryCode);
	}

	@Override
	public void updateByJson(JSONArray jsonArray) {
		this.terminalDao.updateByJson(jsonArray);
		
	}

	@Override
	public List<TreeNode> getProvinsTerminal() {
		return this.terminalDao.getProvinsTerminal();
	}

	@Override
	public List<TreeNode> getCityTerminal() {
		return this.terminalDao.getCityTerminal();
	}

	@Override
	public List<TreeNode> getFinalNode() {
		return this.terminalDao.getFinnalyTerminal();
	}

	@Override
	public List<TreeNode> getCommunityNodeTerminal() {
		return this.terminalDao.getCommunityNodeTerminal();
	}
 
	/**
	 * 通过小区机构编号获取设备的openfire账号
	 */
	@Override
	public List<String> getXmppAccountByOrgs(List<String> orgs) {
		return this.terminalDao.getXmppAccountByOrgs(orgs);
	}
    
	/**
	 * 更新小区的终端媒体数量
	 */
	@Override
	public void updateTerminalCount() {
		this.terminalDao.updateTerminalCount();
	}

	@Override
	public void removeByIds(String []deleteTerminals) {
		this.terminalDao.deleteByIds(deleteTerminals);
	}
    
	/**
	 * 导出终端数据前的查询数据
	 */
	@Override
	public List<TerminalPage> getByInfo(TerminalPage terminalPage) {
		return this.terminalDao.getByInfo(terminalPage);
	}

	@Override
	public void bachSave(List<Terminal> addTerminals) {
		this.terminalDao.bachSave(addTerminals);
	}

	@Override
	public void bachRemove(List<Terminal> deleteTerminals) {
		this.terminalDao.bachRemove(deleteTerminals);
	}

	

	@Override
	public int saveTerminal(Terminal terminal) throws Exception {
		OfUserVo ofUserVo = new OfUserVo();
		ofUserVo.setUserAccount(terminal.getRegisterAccount());
		ofUserVo.setUserName(terminal.getRegisterAccount());
		String password = TERMINALPASSWORD;
		int r = xmppAdvertisementService.addUser(ofUserVo, password);
		try {
			if (OpenfireResultType.RESULT_SUCCESS.getValue() == r) {
				terminalDao.save(terminal);
			}
		} catch (Exception e) {
			e.printStackTrace();
			r = OpenfireResultType.RESULT_FAIL.getValue();
			xmppAdvertisementService.deleteUser(terminal.getRegisterAccount());
		}
		
		return r;
	}

	@Override
	public List<TerminalPage> getTerminalPagesByType(String terminalType) {
		// TODO Auto-generated method stub
		return terminalDao.getTerminalPagesByType(terminalType);
	}

	@Override
	public int delTerminalView(String terminalSerial) throws ClientProtocolException, DocumentException, IOException {
		int r = xmppAdvertisementService.deleteUser(terminalSerial);
		if (OpenfireResultType.RESULT_SUCCESS.getValue() == r||OpenfireResultType.RESULT_ERROR.getValue()==r) {
			terminalDao.delterminalView(terminalSerial);
		}
		return r;
	}

	@Override
	public void pushMsgToTerminal(String materialIds,String terminalSerials) {
		// TODO Auto-generated method stub
		List xmppAccount =  new ArrayList<>();
		if(StringUtils.isNotBlank(terminalSerials)){
			xmppAccount = Arrays.asList(terminalSerials.split(","));
		}
		Map<String, Object> map = new HashMap<>();
    	List<String> list = new ArrayList<String>();
    	for (String str : materialIds.split(",")) {
    		list.add(str);
		}
    	map.put("sourceIds", list);
    	Map<String, Object> contengMap = new HashMap<String, Object>();
		Map<String, Object> content = new HashMap<String, Object>();
		content.put("title", "广告预览消息通知！");
		content.put("sourceIds", list);
		contengMap.put("type", "1060002");
		contengMap.put("content", content);
		String s = JSONObject.fromObject(contengMap).toString();
		try {
			xmppAdvertisementService.pushUpdateAdvertisementStatus(xmppAccount, s, "", 50, false, xmppResource);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
