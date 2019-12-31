package com.hori.service;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.dom4j.DocumentException;

import com.hori.dao.queryBean.TerminalQueryBean;
import com.hori.db.support.DataGridPage;
import com.hori.model.Terminal;
import com.hori.pageModel.TerminalPage;
import com.hori.pageModel.TreeNode;
import com.hori.vo.ProvinceVo;

import net.sf.json.JSONArray;

public interface TerminalService extends BaseServiceI {

	public List<Terminal> getUpdateTerminalByTime(TerminalQueryBean queryBean);

	public List<Terminal> getByQueryBean(TerminalQueryBean queryBean);

	public void updateTerminals(List<Terminal> updateResult);

	public List<Terminal> getAddTerminalByTime(TerminalQueryBean queryBean);

	public void saveAll(List<Terminal> addResult);

	public Terminal getBySerial(String serial);

	public DataGridPage datagrid(TerminalPage terminalPage);

	public List<ProvinceVo> totalProvince(String code);

	public List<ProvinceVo> totalCommunity(String provinceCode, String cityCode, String countryCode);

	public void updateByJson(JSONArray jsonArray);

	public List<TreeNode> getProvinsTerminal();

	public List<TreeNode> getCityTerminal();

	public List<TreeNode> getFinalNode();

	public List<TreeNode> getCommunityNodeTerminal();

	public List<String> getXmppAccountByOrgs(List<String> orgs);

	public void updateTerminalCount();

	public void removeByIds(String[] deleteTerminals);


	/**
	 *保存终端 
	 * @param terminal
	 * @return
	 */
	int saveTerminal(Terminal terminal)throws Exception;
	/**
	 * 通过类型获取终端机
	 * @param terminalType
	 * @return
	 */
	List<TerminalPage> getTerminalPagesByType(String terminalType);

    public List<TerminalPage> getByInfo(TerminalPage terminalPage);


    /**
     * 删除预览终端
     * @param terminalSerial
     * @return
     */
    int delTerminalView(String terminalSerial)throws ClientProtocolException, DocumentException, IOException ;

public void bachSave(List<Terminal> addTerminals);

public void bachRemove(List<Terminal> deleteTerminals);


    /**
     * 素材id
     * @param materialIds
     * @param terminalSerials
     */
    void pushMsgToTerminal(String materialIds,String terminalSerials);
}
