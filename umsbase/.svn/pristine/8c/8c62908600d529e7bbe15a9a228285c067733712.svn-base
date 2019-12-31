package com.hori.service;

import java.util.List;

import com.hori.dao.queryBean.MenuQueryBean;
import com.hori.db.support.DataGridPage;
import com.hori.model.Menu;
import com.hori.model.Operation;
import com.hori.vo.EasyUiTreeVo;

public interface MenuButtonService {
	
	public DataGridPage findMenuByParam(MenuQueryBean queryBean);
	public List<EasyUiTreeVo> initSystem();
	public List<EasyUiTreeVo> initMenu(String systemId,String menuApp);
	public void saveMenu(Menu m);
	public void deleteMenu(String[] ids);
	
	public List<Operation> initButton(String menuId);
	public void saveButton(Operation o);
	public void deleteButton(String id);

}
