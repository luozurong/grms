package com.hori.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hori.dao.AuthorDao;
import com.hori.dao.MenuDao;
import com.hori.dao.OperationDao;
import com.hori.dao.SystemDao;
import com.hori.dao.queryBean.MenuQueryBean;
import com.hori.db.support.DataGridPage;
import com.hori.model.Menu;
import com.hori.model.Operation;
import com.hori.model.SystemAdmin;
import com.hori.service.MenuButtonService;
import com.hori.vo.EasyUiTreeVo;
@Service
public class MenuButtonServiceImpl extends BaseServiceImpl implements MenuButtonService{
	@Autowired
	MenuDao menuDao;
	@Autowired
	SystemDao systemDao;
	@Autowired
	OperationDao operationDao;
	@Autowired
	AuthorDao authorDao;
	
	//分页查询
	public DataGridPage findMenuByParam(MenuQueryBean queryBean){
		
		DataGridPage dataGridPage=menuDao.findMenuPage(queryBean);
		return dataGridPage;
	}
	
	//保存菜单
	public void saveMenu(Menu m){
		if(StringUtils.isNotBlank(m.getMenuId())){
			m.setModifiedTime(new Date());
			//保存当前菜单
			menuDao.update(m);
			//更新当前关联角色的菜单
			authorDao.updateAuthorByMenu(m);
			
		}else{
			m.setCreateTime(new Date());
			m.setModifiedTime(new Date());
			menuDao.save(m);
		}
	}
	
	//删除菜单
	public void deleteMenu(String[] ids){
	    menuDao.deleteByIds(ids);
	}
	
	//查询按钮
	public List<Operation> initButton(String menuId){
		List<Operation> operationList=operationDao.getMenuAll(menuId);
		return operationList;
	}
	//保存按钮
	public void saveButton(Operation o){
		if(StringUtils.isNotBlank(o.getBtnId())){
			o.setModifiedTime(new Date());
			operationDao.update(o);
			authorDao.updateAuthorByButton(o);

			
		}else{
			o.setCreateTime(new Date());
			o.setModifiedTime(new Date());
			operationDao.save(o);
		}
	}
	
	//删除按钮
	public void deleteButton(String id){
		operationDao.deleteById(id);
	}
	public List<EasyUiTreeVo> initSystem(){
		List<EasyUiTreeVo> easyUiTreeVoList=new ArrayList<EasyUiTreeVo>();
		List<SystemAdmin> systemAdminList=systemDao.getSystemStart();
		for(int i=0;i<systemAdminList.size();i++){
			EasyUiTreeVo easyUiTreeVo=new EasyUiTreeVo();
			easyUiTreeVo.setId(systemAdminList.get(i).getSystemId());
			easyUiTreeVo.setText(systemAdminList.get(i).getSystemName());
			easyUiTreeVoList.add(easyUiTreeVo);
		}
		
		return easyUiTreeVoList;
	}
	
	public List<EasyUiTreeVo> initMenu(String systemId,String menuApp){
		List<EasyUiTreeVo> easyUiTreeVoList=new ArrayList<EasyUiTreeVo>();
		List<Menu> menuFatherList=menuDao.getMenuFather(systemId,menuApp);
		for(int i=0;i<menuFatherList.size();i++){
			EasyUiTreeVo easyUiTreeVo=new EasyUiTreeVo();
			easyUiTreeVo.setId(menuFatherList.get(i).getMenuId());
			easyUiTreeVo.setText(menuFatherList.get(i).getMenuname());
			easyUiTreeVoList.add(easyUiTreeVo);

		}
		
		return easyUiTreeVoList;
	}

}
