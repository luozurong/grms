package com.hori.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
	

@Repository
public class SalesmanServiceDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Map<String, Object>> fetchByName(String name) {
		StringBuilder sql = new StringBuilder("SELECT ");
		 sql.append(" u.user_account as userAccount,ud.name AS userName ");
		 sql.append(" FROM user u ");
		 sql.append(" left join user_detail ud on u.user_detail_id = ud.user_detail_id ");
		 sql.append(" left join user_role ur on ur.user_id = u.user_id ");
		 sql.append(" WHERE 1=1 ");
		 sql.append(" and ur.role_type = '0' ");
		if (StringUtils.isNotBlank(name)) {
			sql.append(" and ud.name like '%"+name.trim()+"%'");
		}
		return this.jdbcTemplate.queryForList(sql.toString());
	}
	
}
