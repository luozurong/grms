package com.hori.dao;

import java.math.BigInteger;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.xmlbeans.impl.jam.mutable.MSourcePosition;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.hori.db.HibernateBaseDao;
import com.hori.db.support.DataGridPage;
import com.hori.db.support.PageBean;
import com.hori.model.Messages;
import com.hori.pageModel.MessagesPage;
import com.hori.utils.FuzzyQueryUtils;

@Repository
public class MessagesDao extends HibernateBaseDao<Messages>{
    
    
	/**
	 * 批量添加消息记录
	 * @param messages
	 */
	public void bachSave(List<Messages> messages) {
		this.getHibernateTemplate().execute(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				for(int i=0;i<messages.size();i++){
					session.save(messages.get(i));
					if(i%10==0){
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
	
	
	
	public DataGridPage datagrid(MessagesPage messagesPage) {

		PageBean pb = new PageBean();
		BigInteger total = (BigInteger) this.createNavtiveSQLQuery("select count(*) from message where status="+messagesPage.getStatus()+" and view_level_no  = '"+messagesPage.getViewLevelNo()+"'").get(0);
		int resetKey=messagesPage.getPageNumber()*messagesPage.getPageSize();
		int count = Integer.parseInt(total.toString());
		int restPageNum = count/messagesPage.getPageSize();
		if(resetKey>count){   
			if((count%messagesPage.getPageSize())==0){
				messagesPage.setPageNumber(restPageNum);
			}else{
				messagesPage.setPageNumber(restPageNum+1);
			}
		}
		pb.setPage(messagesPage.getPageNumber());
		pb.setRp(messagesPage.getPageSize());
		StringBuffer hql = new StringBuffer("select m.* from message m where 1=1 and m.create_time is not null");
		if(FuzzyQueryUtils.isCondition(messagesPage.getViewLevelNo())){
			hql.append(" and m.view_level_no  = '"+messagesPage.getViewLevelNo()+"'");
		}
		hql.append(" and m.status ="+messagesPage.getStatus()+"");
		hql.append(" "+messagesPage.getSortType()+"");
		SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		RowMapper rmp = new RowMapper(){
			@Override
			public Object mapRow(java.sql.ResultSet rs, int arg1) throws SQLException {
				Map<String,Object> map =new HashMap<String,Object>();
				map.put("id", rs.getString("id"));
				map.put("content", rs.getString("content"));
				map.put("status", rs.getInt("status"));
				map.put("createTime", sdf.format(new Date(rs.getTimestamp("create_time").getTime())));
				return map;
			}
		};
		
		DataGridPage dbp =this.pagedQuery(hql.toString() ,pb,rmp);
		dbp.setPage(messagesPage.getPageNumber());
		dbp.setPageSize(messagesPage.getPageSize());
		if (0 >= dbp.getTotal()) {
			dbp.setRows(new ArrayList());
		}
		
		return dbp;
	}
    
	/**
	 * 批量更新消息的状态
	 * @param ids
	 */
	public void changeStatusById(String ids) {
		String id = FuzzyQueryUtils.getIds(ids.split(","));
		String sql  = "UPDATE message set status = 1 WHERE id IN("+id+")";
		this.jdbcTemplate.batchUpdate(sql);
	}

	public BigInteger totalCountByAccount(String userLevelNo) {
		BigInteger total = (BigInteger) this.createNavtiveSQLQuery("select count(*) from message where status=0 and view_level_no  = '"+userLevelNo+"'").get(0);
		return total;
	}

	
}
