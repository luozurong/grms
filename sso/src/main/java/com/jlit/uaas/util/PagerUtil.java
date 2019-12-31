
package com.jlit.uaas.util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
/**
 * 分页处理Util类<br>
 * 主要区分不同数据库中查询语法的差异
 * @author laiqm
 */
public class PagerUtil {

	private static Logger log = Logger.getLogger(PagerUtil.class);	
	/**
	 * 适用于Oracle数据库的分页操作，
	 * 分页初始化工作,返回分页语句
	 * @param pager
	 * @param sql
	 * @param jdbcTemplate
	 */
	public String doPagerSQLInit4Oracle(Pager pager,String sql,JdbcTemplate jdbcTemplate){
		
		pager.init(getTotalRows(sql,jdbcTemplate));//初始化总行数
		int lastIndex = pager.getStartRow() + pager.getPageRecorders();
		StringBuffer paginationSQL = new StringBuffer(" SELECT * FROM ( ");
		paginationSQL.append(" SELECT temp.* ,ROWNUM num FROM ( ");
		paginationSQL.append(sql);
		paginationSQL.append(" ) temp ) where num <= "+lastIndex);
		paginationSQL.append("  and　num > "+pager.getStartRow());
		
		return paginationSQL.toString();
	}
	
	/**
	 * 适用于SQLServer数据库的分页操作，
	 * 分页初始化工作,返回分页语句
	 * @param pager
	 * @param sql
	 * @param jdbcTemplate
	 * @return
	 */
	public String doPagerSQLInit4SQLServer(Pager pager,String sql,JdbcTemplate jdbcTemplate){
		pager.init(getTotalRows(sql,jdbcTemplate));//初始化总行数
		int lastIndex = pager.getStartRow() + pager.getPageRecorders();
		StringBuffer paginationSQL = new StringBuffer("");
		paginationSQL.append("select *,identity(int,1,1) rownum   into   #temp from (");
		paginationSQL.append(sql);
		paginationSQL.append(") as device select * from #temp ");
		paginationSQL.append(" where rownum <= "+lastIndex);
		paginationSQL.append(" and rownum > "+pager.getStartRow());
		paginationSQL.append(" drop table #temp ");
		return paginationSQL.toString();
	}
	
	
	/**
	 * 设置Attribute属性其中将分页列表busilist和url封装
	 * 到Attribute中直接供页面通过jstl调用，简化了页面的处理
	 * @param busilist
	 * @param pager
	 * @param request
	 */
	public void initRequestAttribute(List busilist, Pager pager,
			HttpServletRequest request) {

		String pagestr = pager.getUrlOfNoWap2("wap1");
		request.setAttribute("busilist", busilist);
		List<String> list = pager.getPFilter();
		list.add("Submit");
		pager.setPFilter(list);

		request.setAttribute("url", pagestr);// 打包好的分页代码
		request.setAttribute("pager", pager);
		list = new ArrayList<String>();
		list.add("currentPage");
		list.add("p");
		list.add("getPage");
		pager.setPFilter(list);
		request.setAttribute("parameter", pager.getParameter("&amp;"));
	}

	/**
	 * 取得总行数
	 */
	public int getTotalRows(String sql, JdbcTemplate jdbcTemplate) {
		int totalrows = 0;
		if (sql != null && sql.length() > 0) {
			String tmpsql = "select count(*) from (" + sql + ") temp";
			totalrows = jdbcTemplate.queryForObject(tmpsql,Integer.class);
		}
		return totalrows;
	}
}
