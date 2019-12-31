package com.hori.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.hori.db.HibernateBaseDao;
import com.hori.db.support.DataGridPage;
import com.hori.db.support.PageBean;
import com.hori.model.SynchronizationLog;
import com.hori.model.TerminalAdvertisementLogs;
import com.hori.pageModel.SLogPage;
import com.hori.utils.FuzzyQueryUtils;

@Repository
public class SynchronizationLogDao extends HibernateBaseDao<SynchronizationLog>{
    
	
	/**
	 * 通过终端序列号，素材id获取相关的广告、小区信息
	 * @param materialIds
	 * @param terminalSerial
	 * @return
	 */
	public List<TerminalAdvertisementLogs> getByMaterialId(String terminalSerial) {
		String sql =  "SELECT c.organization_seq as organization,c.name AS name FROM  community c "
					+ "RIGHT  JOIN terminal t ON t.organization_seq = c.organization_seq "
					+ "WHERE t.terminal_serial='" + terminalSerial + "'";
		RowMapper row =new  RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				TerminalAdvertisementLogs tLogs = new  TerminalAdvertisementLogs();
				tLogs.setCommunityName(rs.getString("name"));
				tLogs.setOrganizationSeq(rs.getString("organization"));
				tLogs.setTerminalSerial(terminalSerial);
				tLogs.setCreateTime(new Date());
				return tLogs;
			}
		};
		List<TerminalAdvertisementLogs> list =this.getJdbcTemplate().query(sql, row);
		return list;
	}
    
	/**
	 * 
	 * @param sLogPage
	 * @return
	 */
	public DataGridPage getByPage(SLogPage sLogPage) {
		PageBean pb = new PageBean();
		pb.setPage(sLogPage.getPageNumber());
		pb.setRp(sLogPage.getPageSize());
		StringBuffer sql = new StringBuffer("SELECT * FROM synchronization_log WHERE 1=1");
		if(sLogPage.getStatus()!=null){
			sql.append(" and status="+sLogPage.getStatus()+"");
		}
		if(FuzzyQueryUtils.isCondition(sLogPage.getStartTime())){
			sql.append(" and create_time>='"+sLogPage.getStartTime()+" 00:00:01'");
		}
		if(FuzzyQueryUtils.isCondition(sLogPage.getEndTime())){
			sql.append(" and create_time<='"+sLogPage.getEndTime()+" 23:59:59'");
		}
		sql.append(" and type <> 6");
		sql.append(" ORDER BY create_time DESC");
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		RowMapper rmp = new RowMapper(){
			@Override
			public Object mapRow(java.sql.ResultSet rs, int arg1) throws SQLException {
				Map<String,Object> map =new HashMap<String,Object>();
				map.put("id", rs.getString("id"));
				map.put("logsContent", rs.getString("logs_content"));
				map.put("type", rs.getInt("type"));
				map.put("status", rs.getInt("status"));
				map.put("createTime", sdf.format(rs.getTimestamp("create_time")));
				return map;
			}
		};
		DataGridPage dbp =this.pagedQuery(sql.toString() ,pb,rmp);
		dbp.setPage(sLogPage.getPageNumber());
		dbp.setPageSize(sLogPage.getPageSize());
		if (0 >= dbp.getTotal()) {
			dbp.setRows(new ArrayList());
		}
		return dbp;
	}
}
