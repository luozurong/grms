package com.hori.dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.hori.dao.queryBean.TerminalQueryBean;
import com.hori.db.HibernateBaseDao;
import com.hori.db.support.DataGridPage;
import com.hori.db.support.PageBean;
import com.hori.model.Terminal;
import com.hori.pageModel.TerminalPage;
import com.hori.pageModel.TreeNode;
import com.hori.utils.FuzzyQueryUtils;
import com.hori.vo.ProvinceVo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Repository
public class TerminalDao extends HibernateBaseDao<Terminal>{
	@Autowired
    private JdbcTemplate vdcsJdbcTemplate;
	
	/**
	 * 批量更新设备
	 * @param terminals
	 */
	public void batchUpdateTerminal(final List<Terminal> terminals){
		this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				for(int inx=0;inx<terminals.size();inx++){
					session.update(terminals.get(inx));
					if(inx%200==0){
						session.flush();
						session.clear();
					}
				}
				session.flush();
				session.clear();				
				return null;
			}
			
		});
	}
	
	/**
	 * 批量添加设备
	 * @param terminals
	 */
	public void bachSave(List<Terminal> addTerminals) {
		this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				for(int inx=0;inx<addTerminals.size();inx++){
					session.save(addTerminals.get(inx));
					if(inx%200==0){
						session.flush();
						session.clear();
					}
				}
				session.flush();
				session.clear();				
				return null;
			}
			
		});
	}
	
	/**
	 * 批量删除设备
	 * @param terminals
	 */
	public void bachRemove(List<Terminal> deleteTerminals) {
		this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				for(int inx=0;inx<deleteTerminals.size();inx++){
					session.delete(deleteTerminals.get(inx));
					if(inx%200==0){
						session.flush();
						session.clear();
					}
				}
				session.flush();
				session.clear();				
				return null;
			}
			
		});
		
	}
	
	
	
	/**
	 * 从对讲获取需要同步更新的终端信息
	 * @param queryBean
	 * @return
	 */
	public List<Terminal> getUpdateTerminalByTime(TerminalQueryBean queryBean) {
		String sql = "Select a.id as id,a.terminal_name as name,a.terminal_model as model,a.area_serial as areaSerial,a.serial as serial,"
				 + "a.organization_seq as organization,a.creator_time as createTime,a.update_time as updateTime,a.register_account as registerAccount,"
				 + "a.terminal_serial as terminalSerial,a.household_serial as household,a.location_name as location,a.group_state as groupState"
				 +" From talk_serial  a Where 1=1"
				 +" AND a.update_time<= '"+queryBean.getEndTime()+"' and a.update_time >= '"+queryBean.getStartTime()+"'";
	List<Terminal> result = new ArrayList<Terminal>();
		RowMapper rmp = new RowMapper(){
			@Override
			public Object mapRow(java.sql.ResultSet rs, int arg1) throws SQLException {
				Terminal terminal =new Terminal();
				terminal.setId(rs.getString("id"));
				terminal.setTerminalName(rs.getString("name"));
				terminal.setOrganizationSeq(rs.getString("organization"));
				terminal.setCode(rs.getString("model"));
				terminal.setCommunitySerial(rs.getString("serial"));
				terminal.setTerminalSerial(rs.getString("terminalSerial"));
				terminal.setHouseholdSerial(rs.getString("household"));
				terminal.setLocationName(rs.getString("location"));
				terminal.setCreateTime(rs.getLong("createTime"));
				terminal.setUpdateTime(rs.getLong("updateTime"));
				terminal.setGroupState(rs.getString("groupState"));
				terminal.setRegisterAccount(rs.getString("registerAccount"));
				terminal.setSerial(rs.getString("serial"));
				return terminal;
			}
		};
		
		result = this.vdcsJdbcTemplate.query(sql.toString(), rmp);
	return result;
	}

	public List<Terminal> getByQueryBean(TerminalQueryBean queryBean) {
		StringBuffer hql =new StringBuffer("FROM Terminal  WHERE 1=1 ");
		if(queryBean.getIds()!=null){
			String ids=FuzzyQueryUtils.getIds(queryBean.getIds());
			hql.append(" and id IN("+ids+")");
		}
		if(queryBean.getTerminalType()!=null){
			hql.append(" and terminalType = "+queryBean.getTerminalType()+"");
		}
		return this.find(hql.toString());
	}

	public List<Terminal> getAddTerminalByTime(TerminalQueryBean queryBean) {
		String sql = "Select a.terminal_name as name,a.terminal_model as model,a.area_serial as areaSerial,a.serial as serial,a.terminal_alias_name as terminalAliasName,"
				 + "a.organization_seq as organization,a.creator_time as createTime,a.update_time as updateTime,a.register_account as registerAccount,"
				 + "a.terminal_serial as terminalSerial,a.household_serial as household,a.location_name as location,a.group_state as groupState"
				 +" From talk_serial  a Where 1=1 AND (a.terminal_door_type ='1' or a.terminal_door_type='2') AND a.terminal_serial is not null AND register_account IS NOT NULL";
//				 +" AND a.creator_time<= '"+queryBean.getEndTimeL()+"' and a.creator_time >= '"+queryBean.getStartTimeL()+"'";
	List<Terminal> result = new ArrayList<Terminal>();
		RowMapper rmp = new RowMapper(){
			@Override
			public Object mapRow(java.sql.ResultSet rs, int arg1) throws SQLException {
				Terminal terminal =new Terminal();
//				terminal.setId(rs.getString("id"));
				terminal.setTerminalName(rs.getString("name"));
				terminal.setOrganizationSeq(rs.getString("organization"));
				terminal.setCode(rs.getString("model"));
				terminal.setCommunitySerial(rs.getString("areaSerial"));
				terminal.setTerminalSerial(rs.getString("terminalSerial"));
				terminal.setHouseholdSerial(rs.getString("household"));
				terminal.setLocationName(rs.getString("location"));
				terminal.setCreateTime(rs.getLong("createTime"));
				terminal.setUpdateTime(rs.getLong("updateTime"));
				terminal.setGroupState(rs.getString("groupState"));
				terminal.setRegisterAccount(rs.getString("registerAccount"));
				terminal.setSerial(rs.getString("serial"));
				terminal.setTerminalAliasName(rs.getString("terminalAliasName"));
				terminal.setTerminalType(1);
				return terminal;
			}
		};
		result = this.vdcsJdbcTemplate.query(sql.toString(), rmp);
	return result;
	}
     
	
	/**
	 * 批量增加终端信息
	 * @param terminals
	 */
	public void saveAll(List<Terminal> terminals) {
		StringBuffer sql = new StringBuffer("INSERT INTO terminal (id,name,code,community_serial,organization_seq,creator_time,update_time,household_serial,location_name,group_state,terminal_serial,register_account,serial,terminal_alias_name) VALUES");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			if(terminals!=null&&terminals.size()>0){
				String sqlString = this.getSql(terminals, sql).substring(0,sql.toString().length()-1); 
				this.jdbcTemplate.batchUpdate(sqlString);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
	public String getSql(List<Terminal> terminals,StringBuffer sql){
		
		for(Terminal terminal:terminals){
				sql.append("('"+terminal.getId()+"',");
				if(StringUtils.isBlank(terminal.getTerminalName())){
					sql.append("NULL,");
				}else{
					sql.append("'"+terminal.getTerminalName()+"',");
				}
				if(StringUtils.isBlank(terminal.getCode())){
					sql.append("NULL,");
				}else{
					sql.append("'"+terminal.getCode()+"',");
				}
				if(StringUtils.isBlank(terminal.getCommunitySerial())){
					sql.append("NULL,");
				}else{
					sql.append("'"+terminal.getCommunitySerial()+"',");
				}
				
				if(StringUtils.isBlank(terminal.getOrganizationSeq())){
					sql.append("NULL,");
				}else{
					sql.append("'"+terminal.getOrganizationSeq()+"',");
				}
				
				if(terminal.getCreateTime()==null){
					sql.append("NULL,");
				}else{
					sql.append("'"+terminal.getCreateTime()+"',");
				}
				
				if(terminal.getUpdateTime()==null){
					sql.append("NULL,");
				}else{
					sql.append("'"+terminal.getCreateTime()+"',");
				}
				if(StringUtils.isBlank(terminal.getHouseholdSerial())){
					sql.append("NULL,");
				}else{
					sql.append("'"+terminal.getHouseholdSerial()+"',");
				}
				
				if(StringUtils.isBlank(terminal.getLocationName())){
					sql.append("NULL,");
				}else{
					sql.append("'"+terminal.getLocationName()+"',");
				}
			    
				if(StringUtils.isBlank(terminal.getGroupState())){
					sql.append("NULL,");
				}else{
					sql.append("'"+terminal.getGroupState()+"',");
				}
			
				if(StringUtils.isBlank(terminal.getTerminalSerial())){
					sql.append("NULL,");
				}else{
					sql.append("'"+terminal.getTerminalSerial()+"',");
				}
				
				if(StringUtils.isBlank(terminal.getRegisterAccount())){
					sql.append("NULL,");
				}else{
					sql.append("'"+terminal.getRegisterAccount()+"',");
				}
				
				if(StringUtils.isBlank(terminal.getSerial())){
					sql.append("NULL,");
				}else{
					sql.append("'"+terminal.getSerial()+"',");
				}
				if(StringUtils.isBlank(terminal.getTerminalAliasName())){
					sql.append("'"+terminal.getTerminalName()+"'");
				}else{
					sql.append("'"+terminal.getTerminalAliasName()+"'");
				}
				sql.append("),");
		}
		return sql.toString();
	}
     
	
	public Terminal getBySerial(String serial) {
		String hql = "FROM Terminal WHERE terminalSerial ='"+serial+"' and terminalType=1";
		List<Terminal> list = this.find(hql);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
    
	/**
	 * 终端设备的分页
	 * @param terminalPage
	 * @return
	 */
	public DataGridPage datagrid(TerminalPage terminalPage) {
		PageBean pb = new PageBean();
		pb.setPage(terminalPage.getPageNumber());
		pb.setRp(terminalPage.getPageSize());
		StringBuffer hql = new StringBuffer(
				"select t.id,t.name,t.terminal_alias_name ,t.terminal_serial,tps.status_type,tps.update_time "
				+ "from terminal t "
				+ "LEFT JOIN community c  ON c.organization_seq = t.organization_seq "
				+ "LEFT JOIN terminal_play_status tps ON tps.terminal_serial = t.terminal_serial "
				+ "WHERE  1=1 ");
		if(FuzzyQueryUtils.isCondition(terminalPage.getTerminalName())){
			String name=terminalPage.getTerminalName();
			hql.append(" and t.terminal_alias_name like '").append(FuzzyQueryUtils.fuzzyQueryCondition(name)).append("'");
		}
		if(FuzzyQueryUtils.isCondition(terminalPage.getTerminalSerial())){
			String terminalSerial = terminalPage.getTerminalSerial();
			hql.append(" and t.terminal_serial like '").append(
					FuzzyQueryUtils.fuzzyQueryCondition(terminalSerial)).append("'");
		}
		if(FuzzyQueryUtils.isCondition(terminalPage.getOrganizationSeq())){
			hql.append(" and t.organization_seq='"+terminalPage.getOrganizationSeq()+"'");
		}
		if(null!=terminalPage.getTerminalType()){
			hql.append(" and t.terminal_type="+terminalPage.getTerminalType()+"");
		}
		if(FuzzyQueryUtils.isCondition(terminalPage.getFuzzyKey())){
			String  fuzzyKey  = terminalPage.getFuzzyKey();
			hql.append(" and ");
			hql.append("( t.terminal_serial like '").append(
					FuzzyQueryUtils.fuzzyQueryCondition(fuzzyKey)).append("'");
			hql.append(" or ");
			hql.append(" t.terminal_alias_name like '").append(FuzzyQueryUtils.fuzzyQueryCondition(fuzzyKey)).append("' )");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		RowMapper rmp = new RowMapper(){
			@Override
			public Object mapRow(java.sql.ResultSet rs, int arg1) throws SQLException {
				Map<String,Object> map =new HashMap<String,Object>();
				String terminalName=rs.getString("terminal_alias_name");
				if(StringUtils.isBlank(terminalName)){
					terminalName = rs.getString("name");
				}
				map.put("id", rs.getString("id"));
				map.put("terminalName", terminalName);
				map.put("serial", rs.getString("terminal_serial"));
//				map.put("statusType", "待填写");
//				map.put("updateTime", "待填写");
//				map.put("updateTime", rs.getTimestamp("update_time"));
				map.put("statusType", rs.getInt("status_type"));
//				map.put("familyCount", rs.getString("family_count"));
//				map.put("peopleCount", rs.getString("people_count"));
//				map.put("terminalCount", rs.getString("terminal_count"));
				
				
				
				
//				map.put("grandCardCount", rs.getString("grand_card_count"));
				return map;
			}
		};
		DataGridPage dbp =this.pagedQuery(hql.toString() ,pb,rmp);
		dbp.setPage(terminalPage.getPageNumber());
		dbp.setPageSize(terminalPage.getPageSize());
		if (0 >= dbp.getTotal()) {
			dbp.setRows(new ArrayList());
		}
		
		return dbp;
	}
    
	/**
	 * 统计省市区的终端设备
	 * @return  String name, Integer code, Integer terminalNums
	 */
	public List<ProvinceVo> totalProvince(String code) {
		List<ProvinceVo> provinceVos = new ArrayList<ProvinceVo>();
		StringBuffer sql = new StringBuffer(" SELECT pcat.name,pcat.code,SUM(IFNULL(Ctotal.orgNum,0)) FROM   pro_city_area_town pcat");
		if(StringUtils.isNotBlank(code)){
			String key = code.substring(3,4);
			if(!key.equals("0")){
				sql.append(" LEFT JOIN community c ON c.country = pcat.code ");
			}else{
				 sql.append(" LEFT JOIN community c ON c.city = pcat.code ");
			}
		}else{
			sql.append(" LEFT JOIN community c ON c.province = pcat.code ");
		}
		sql.append(" RIGHT JOIN (SELECT  COUNT(organization_seq) as orgNum,organization_seq FROM terminal"+
					 " WHERE organization_seq IS NOT NULL GROUP BY organization_seq) AS Ctotal"+
					 " ON Ctotal.organization_seq= c.organization_seq WHERE 1=1 ");
		if(StringUtils.isBlank(code)){
			sql.append(" AND pcat.parentId = '0086'");
		}else{
			sql.append(" AND pcat.parentId = '"+code+"'");
		}
		sql.append(" GROUP BY pcat.code");
		List<Object> result = this.createNavtiveSQLQuery(sql.toString());
		if(result!=null&&result.size()>0){
			int size = result.size();
			for(int i=0;i<size;i++){
				Object []object=(Object[]) result.get(i);
				ProvinceVo provinceVo = new ProvinceVo();
				provinceVo.setName(object[0].toString());
				provinceVo.setCode(object[1].toString());
				provinceVo.setTerminalNums(Integer.valueOf(object[2].toString()));
				provinceVos.add(provinceVo);
			}
		}
		return provinceVos;
	}
   
	/**
	 * 返回该区域下小区的终端信息
	 * @param code
	 * @return
	 */
	public List<ProvinceVo> totalCommunity(String provinceCode, String cityCode, String countryCode) {
		List<ProvinceVo> provinceVos = new ArrayList<ProvinceVo>();
		String sql = " SELECT c.organization_seq,c.name,Ctotal.orgNum FROM community c "+
					" RIGHT  JOIN (SELECT  COUNT(organization_seq) as orgNum,organization_seq FROM"+
					" terminal WHERE organization_seq IS NOT NULL GROUP BY organization_seq) AS Ctotal"+
					" ON Ctotal.organization_seq= c.organization_seq "
					+ " WHERE c.province ='"+provinceCode+"' AND c.city ='"+cityCode+"' AND c.country ='"+countryCode+"'";
		List<Object> result = this.createNavtiveSQLQuery(sql.toString());
		if(result!=null&&result.size()>0){
			int size = result.size();
			for(int i=0;i<size;i++){
				Object []object=(Object[]) result.get(i);
				ProvinceVo provinceVo = new ProvinceVo();
				provinceVo.setName(object[1].toString());
				provinceVo.setCode(object[0].toString());
				provinceVo.setTerminalNums(Integer.valueOf(object[2].toString()));
				provinceVos.add(provinceVo);
			}
		}
		return provinceVos;
	}
    
	/**
	 * 修改指定终端的名称
	 * @param 终端id
	 * @param 终端名称
	 * @param jsonArray
	 */
	public void updateByJson(JSONArray jsonArray) {
		try {
			if(jsonArray.size()>0){
				List<Object[]> list = new ArrayList<Object[]>();
				  for(int i=0;i<jsonArray.size();i++){
				    JSONObject job = jsonArray.getJSONObject(i);  
				     String name = (String) job.get("terminalName");
				     String sql = "UPDATE terminal set terminal_alias_name ='"+name+"' where id='"+(String) job.get("id")+"'";
				 	 this.jdbcTemplate.batchUpdate(sql);
				  }
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<TreeNode> getProvinsTerminal() {
		String sql = "SELECT pcat.name as name,pcat.code as code,pcat.parentId as parentId, SUM(c.terminal_count) as total FROM  pro_city_area_town pcat"
				  + " LEFT JOIN community c ON c.province = pcat.code"
				  + " WHERE c.terminal_count !=0 GROUP BY c.province  ORDER BY c.name";
		RowMapper rmp = new RowMapper(){
			@Override
			public Object mapRow(java.sql.ResultSet rs, int arg1) throws SQLException {
				TreeNode treeNode = new TreeNode();
				treeNode.setId((rs.getString("code")));
				treeNode.setText("<span data-id="+rs.getString("code")+">"+rs.getString("name")+"("+rs.getInt("total")+")"+"</span>");
				treeNode.setParentId(rs.getString("parentId"));
				return treeNode;
			}
		};
		List<TreeNode> result = this.jdbcTemplate.query(sql, rmp);
		return result;
	}
	
	
	public List<TreeNode> getCityTerminal() {
		String sql = "SELECT pcat.name as name,pcat.code as code,pcat.parentId as parentId, SUM(c.terminal_count) as total FROM  pro_city_area_town pcat"
				  + " LEFT JOIN community c ON c.city = pcat.code"
				  + " WHERE c.terminal_count !=0 GROUP BY c.city ORDER BY c.name";
		RowMapper rmp = new RowMapper(){
			@Override
			public Object mapRow(java.sql.ResultSet rs, int arg1) throws SQLException {
				TreeNode treeNode = new TreeNode();
				treeNode.setId(rs.getString("code"));
				treeNode.setText("<span data-id="+rs.getString("code")+">"+rs.getString("name")+"("+rs.getInt("total")+")"+"</span>");
				treeNode.setParentId(rs.getString("parentId"));
				return treeNode;
			}
		};
		List<TreeNode> result = this.jdbcTemplate.query(sql, rmp);
		return result;
	}
	
	public List<TreeNode> getFinnalyTerminal() {
		String sql = "SELECT pcat.name as name,pcat.code as code,pcat.parentId as parentId, SUM(c.terminal_count) as total FROM  pro_city_area_town pcat"
				  + " LEFT JOIN community c ON c.country = pcat.code"
				  + " WHERE c.terminal_count !=0 GROUP BY c.country ORDER BY c.name";
		RowMapper rmp = new RowMapper(){
			@Override
			public Object mapRow(java.sql.ResultSet rs, int arg1) throws SQLException {
				TreeNode treeNode = new TreeNode();
				treeNode.setId((rs.getString("code")));
				treeNode.setText("<span data-id="+rs.getString("code")+">"+rs.getString("name")+"("+rs.getInt("total")+")"+"</span>");
				treeNode.setParentId(rs.getString("parentId"));
				return treeNode;
			}
		};
		List<TreeNode> result = this.jdbcTemplate.query(sql, rmp);
		return result;
	}

	public List<TreeNode> getCommunityNodeTerminal() {
		String sql ="SELECT c.name AS name,SUM(c.terminal_count) AS total"
				+ ",c.organization_seq AS code,c.country AS parentId FROM  "
				+ " community c WHERE c.terminal_count !=0 GROUP BY c.id";
		RowMapper rmp = new RowMapper(){
			@Override
			public Object mapRow(java.sql.ResultSet rs, int arg1) throws SQLException {
				TreeNode treeNode = new TreeNode();
				String id = rs.getString("code");
				treeNode.setId(id);
				treeNode.setText("<span lang="+rs.getString("code")+" data-id="+rs.getString("code")+">"+rs.getString("name")+"("+rs.getInt("total")+")"+"</span>");
				treeNode.setParentId(rs.getString("parentId"));
				treeNode.setState("open");
				return treeNode;
			}
		};
		List<TreeNode> result = this.jdbcTemplate.query(sql, rmp);
		return result;
	}
   
	/**
	 * 通过小区机构编号获取设备的openfire账号
	 * @param orgs
	 * @return
	 */
	public List<String> getXmppAccountByOrgs(List<String> orgs) {
		String organizationSeq = FuzzyQueryUtils.getIds(orgs);
		String sql = "select terminal_serial FROM terminal  where organization_seq IN("+organizationSeq+") AND terminal_serial IS NOT NULL";
		return this.createNavtiveSQLQuery(sql);
	}
    
	/**
	 * 更新小区的媒体终端数量
	 */
	public void updateTerminalCount() {
		String resetCount ="update community set terminal_count=0";  
		this.executeSQLUpdate(resetCount);
		String sql = "update community c set c.terminal_count = (select COUNT(t.organization_seq) from terminal  t where  c.organization_seq = t.organization_seq  and t.organization_seq IS NOT NULL GROUP BY t.organization_seq)"
					+"where c.organization_seq in (select t2.organization_seq from terminal t2 WHERE t2.organization_seq IS NOT NULL AND t2.terminal_type=1  GROUP BY t2.organization_seq )";
		this.executeSQLUpdate(sql);
	}

	
	/**
	 * 通过类型获取终端机
	 * @param terminalType
	 * @return
	 */
	public List<TerminalPage> getTerminalPagesByType(String terminalType){
		StringBuffer hql = new StringBuffer("select t.id,t.name,t.terminal_serial from terminal t where 1=1 ");
		if(StringUtils.isNotBlank(terminalType)){
			hql.append(" AND t.terminal_type = "+terminalType+" ");
		}
		RowMapper rmp = new RowMapper(){
			@Override
			public Object mapRow(java.sql.ResultSet rs, int arg1) throws SQLException {
				TerminalPage terminalPage = new TerminalPage();
				terminalPage.setId(rs.getString("id"));
				terminalPage.setTerminalName(rs.getString("name"));
				terminalPage.setTerminalSerial(rs.getString("terminal_serial"));
				return terminalPage;
			}
		};
		List<TerminalPage> list =  this.jdbcTemplate.query(hql.toString(), rmp);
		if(null!=list&&list.size()>0){
			return list;
		}
		return new ArrayList<TerminalPage>();
	}

    
	/**
	 * 导出数据前的查询
	 * @param terminalPage
	 * @return
	 */
	public List<TerminalPage> getByInfo(TerminalPage terminalPage) {
		StringBuffer sql = new StringBuffer(
						  "select t.name,t.terminal_alias_name ,t.terminal_serial,c.name as communityName,c.province,c.city,c.country,tps.status_type,tps.update_time "
						+ "from terminal t "
						+ "LEFT JOIN community c  ON c.organization_seq = t.organization_seq "
						+ "LEFT JOIN terminal_play_status tps ON tps.terminal_serial = t.terminal_serial "
						+ "WHERE 1=1 ");
		if(FuzzyQueryUtils.isCondition(terminalPage.getOrganizationSeq())){
			sql.append("and t.organization_seq = '"+terminalPage.getOrganizationSeq()+"' ");
		}
		if(FuzzyQueryUtils.isCondition(terminalPage.getFuzzyKey())){
			String  fuzzyKey  = terminalPage.getFuzzyKey();
			sql.append(" and ");
			sql.append("( t.terminal_serial like '").append(
					FuzzyQueryUtils.fuzzyQueryCondition(fuzzyKey)).append("'");
			sql.append(" or ");
			sql.append(" t.terminal_alias_name like '").append(FuzzyQueryUtils.fuzzyQueryCondition(fuzzyKey)).append("' )");
		}
		sql.append("and t.terminal_type = 1 ");
		
		SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		RowMapper rmp = new RowMapper(){
			@Override
			public Object mapRow(java.sql.ResultSet rs, int arg1) throws SQLException {
				TerminalPage  terminalPage =new TerminalPage();
				String terminalName=rs.getString("terminal_alias_name");
				if(StringUtils.isBlank(terminalName)){
					terminalName = rs.getString("name");
				}
				String statusTime = "";
				if(rs.getTimestamp("update_time")!=null){
					statusTime = sdf.format(rs.getTimestamp("update_time"));
				}
				terminalPage.setTerminalName(terminalName);
				terminalPage.setCommunityName(rs.getString("communityName"));
				terminalPage.setProvince(rs.getString("province"));
				terminalPage.setCity(rs.getString("city"));
				terminalPage.setCountry(rs.getString("country"));
				terminalPage.setTerminalSerial(rs.getString("terminal_serial"));
				terminalPage.setStatuTime(statusTime);
				terminalPage.setStatuType(rs.getInt("status_type"));
				return terminalPage;
			}
		};
		
		List<TerminalPage> result = this.jdbcTemplate.query(sql.toString(), rmp);
		return result;
	}

	
    /**
     * 通过设备系列号删除元组
     * @param terminalSerial
     */
	public void delterminalView(String terminalSerial){
		executeSQLUpdate("DELETE FROM terminal WHERE terminal_serial='"+terminalSerial+"'");
	}

}
