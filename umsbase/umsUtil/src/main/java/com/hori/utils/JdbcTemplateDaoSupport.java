package com.hori.utils;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;

import com.hori.db.support.Page;

/**
 * @affect 分页函数  jdbcTemplate
 * @author damon
 * @date 2009-4-23 下午04:31:25
 */
public class JdbcTemplateDaoSupport { 

	
	
	//分页函数
	private Page page;
	
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	 
	/**分页构造函数 
	 * @param sql 根据传入的sql语句得到一些基本分页信息 
	 * @param currentPage 当前页 
	 * @param numPerPage 每页记录数 
	 * @param jTemplate JdbcTemplate实例 
	 * @param rowMapperResultSetExtractor rowMapperResultSetExtractor实例 
	 * @return page
	 */ 
	public Page jdbcTemplatePage(String sql,int currentPage,int numPerPage,JdbcTemplate jdbcTemplate,RowMapperResultSetExtractor rowMapperResultSetExtractor){ 
		
		
		//计算总记录数sql
		StringBuffer totalSQL = new StringBuffer(" SELECT count(*) FROM ( "); 
		totalSQL.append(sql); 
		totalSQL.append(" ) totalTable "); 
		
		
		/*int totalCount = jdbcTemplate.query;
		int totalCount = 10;*/
		Number number =jdbcTemplate.queryForObject(sql, Integer.class);
		int totalCount = (number != null ? number.intValue() : 0);
		if (totalCount < 1) {
			return new Page();
		}		
		
		int startIndex = Page.getStartOfPage(numPerPage,currentPage);
		
		List list =(List)jdbcTemplate.query(doPagerSQLInit4Mysql(numPerPage,currentPage,totalCount,sql), rowMapperResultSetExtractor);
		// 实际查询返回分页对象
		
	
		return new Page(startIndex,totalCount,currentPage,list);
	} 
	
	/**
	 * 适用于Oracle数据库的分页操作，
	 * 分页初始化工作,返回分页语句
	 * @param startIndex
	 * @param lastIndex
	 * @param sql
	 */
	private String doPagerSQLInit4Oracle(int numPerPage,int currentPage,int totalRows,String sql){		
		int startIndex = Page.getStartOfPage(numPerPage,currentPage);
		int lastIndex = getLastIndex( numPerPage, currentPage, totalRows);
		StringBuffer paginationSQL = new StringBuffer(" SELECT * FROM ( ");
		paginationSQL.append(" SELECT temp.* ,ROWNUM num FROM ( ");
		paginationSQL.append(sql);
		paginationSQL.append(" ) temp ) where num <= "+lastIndex);
		paginationSQL.append("  and　num > "+startIndex);
		
		return paginationSQL.toString();
	}
	
	
		/**
	 * 适用于Mysql数据库的分页操作，
	 * 分页初始化工作,返回分页语句
	 * @param startIndex
	 * @param lastIndex
	 * @param sql
	 */
	private String doPagerSQLInit4Mysql(int numPerPage,int currentPage,int totalRows,String sql){		
		int startIndex = Page.getStartOfPage(numPerPage,currentPage);
		
		StringBuffer paginationSQL = new StringBuffer("");
		
		paginationSQL.append(sql);
		paginationSQL.append(" limit " );
		paginationSQL.append(startIndex+","+currentPage);
		
		return paginationSQL.toString();
	}
	
	/**
	 * 适用于SQLServer数据库的分页操作，
	 * 分页初始化工作,返回分页语句
	 * @param startIndex
	 * @param lastIndex
	 * @param sql
	 * @return
	 */
	private String doPagerSQLInit4SQLServer(int numPerPage,int currentPage,int totalRows,String sql){
		int startIndex = Page.getStartOfPage(numPerPage,currentPage);
		int lastIndex = getLastIndex( numPerPage, currentPage, totalRows);
		
		StringBuffer paginationSQL = new StringBuffer("");
		paginationSQL.append("select *,identity(int,1,1) rownum   into   #temp from (");
		paginationSQL.append(sql);
		paginationSQL.append(") as device select * from #temp ");
		paginationSQL.append(" where rownum <= "+lastIndex);
		paginationSQL.append(" and rownum > "+startIndex);
		paginationSQL.append(" drop table #temp ");
		return paginationSQL.toString();
	}
	
	private int getLastIndex(int currentPage,int numPerPage,int totalRows) { 
		int lastIndex = 0;
		int totalPages =0;
		if(totalRows % numPerPage == 0){ 
			totalPages = totalRows / numPerPage; 
		}else{ 
			totalPages = (totalRows / numPerPage) + 1; 
		} 
		if( totalRows < numPerPage){ 
			lastIndex = totalRows; 
		}else if((totalRows % numPerPage == 0) || (totalRows % numPerPage != 0 && currentPage < totalPages)){ 
			lastIndex = currentPage * numPerPage; 
		}else if(totalRows % numPerPage != 0 && currentPage == totalPages){//最后一页 
			lastIndex = totalRows ; 
		} 
		return lastIndex;
	}
	
 
}