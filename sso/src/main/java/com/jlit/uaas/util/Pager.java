
package com.jlit.uaas.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Pager {

	private int totalRows; // 总行数

	private int pageRecorders = 15; // 每页显示的行数

	private int currentPage = 0; // 当前页号

	private int totalPages; // 总页数

	private int startRow = 0; // 当前页在数据库中的起始行

	private int pagesNo = 8; // 当前显示多少个页码

	private boolean hasNextPage = false; // 是否有下一页

	private boolean hasPreviousPage = false; // 是否有前一页

	private String listPageBreak;

	private String URL = "";

	private HttpServletRequest request;

	private String pagerMethod = "no";

	private HttpServletResponse response;

	private List pFilter = new ArrayList(); // 过滤请求的参数

	private String param = ""; // 请求的参数

	private boolean isManualPage = false; // 是否是手动的设置开始行数

	public static void main(String[] args) {
		Pager page = new Pager("", 0, 3);
		page.init(1);
		System.out.println(page.getCurrentPage());
		System.out.println("是否有下一页" + page.hasNextPage);
		System.out.println("是否有前一页" + page.hasPreviousPage);
		System.out.println("是" + (page.totalRows % page.pageRecorders));

	}

	public Pager() {
	}

	/**
	 * web或wab的初始化 默认每页显示行数 Contructor.
	 * 
	 * @param request
	 * @param response
	 */
	public Pager(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.pagerMethod = request.getParameter("getPage");
		String page = request.getParameter("thispage"); // 当前页号

		// 如果当前页号为空，表示为首次查询该页
		// 如果不为空，则刷新pager对象，输入当前页号等信息
		if (page == null || "".equals(page.trim()))
			page = "1";
		if (page != null) {
			boolean b = Pattern.matches("^\\d{1,7}$", page);
			if (!b)
				page = "1";
		}
		currentPage = Integer.parseInt(page);
	}

	/**
	 * 
	 * web或wab的初始化 指定每页显示行数
	 * 
	 * @param request
	 * @param pageRecorders
	 *            每页显示的行数
	 */
	public Pager(HttpServletRequest request, HttpServletResponse response,
			int pageRecorders) {
		this.request = request;
		this.pageRecorders = pageRecorders;
		this.response = response;
		this.pagerMethod = request.getParameter("getPage");
		String page = request.getParameter("thispage"); // 当前页号
		// 如果当前页号为空，表示为首次查询该页
		// 如果不为空，则刷新pager对象，输入当前页号等信息
		if (page == null || "".equals(page.trim()))
			page = "1";
		if (page != null) {
			boolean b = Pattern.matches("^\\d{1,7}$", page);
			if (!b)
				page = "1";
		}
		currentPage = Integer.parseInt(page);
	}

	/**
	 * web或wab的初始化 自定义分页 Contructor.
	 * 
	 * @param request
	 * @param response
	 * @param pageRecorders
	 *            每页显示的行数
	 * @param startRow
	 *            开始行数
	 */
	public Pager(HttpServletRequest request, HttpServletResponse response,
			int pageRecorders, int startRow) {
		this.request = request;
		this.pageRecorders = pageRecorders;
		this.response = response;
		this.pagerMethod = request.getParameter("getPage");
		String page = request.getParameter("thispage"); // 当前页号
		// 如果当前页号为空，表示为首次查询该页
		// 如果不为空，则刷新pager对象，输入当前页号等信息
		if (page == null || "".equals(page.trim()))
			page = "1";
		if (page != null) {
			boolean b = Pattern.matches("^\\d{1,7}$", page);
			if (!b)
				page = "1";
		}
		// currentPage = Integer.parseInt(page);
		this.startRow = startRow;
		this.isManualPage = true;
	}

	/**
	 * 
	 * Contructor.
	 * 
	 * @param _totalRows
	 *            总行数
	 * @param request
	 * @param response
	 */
	public Pager(int _totalRows, HttpServletRequest request,
			HttpServletResponse response) {
		this.request = request;
		this.response = response;
		init(_totalRows);

	}

	/**
	 * 在没有HttpServletRequest,HttpServletResponse的时候用 默认每页显示行数 Contructor.
	 * 
	 * @param _pagerMethod
	 *            获取当前执行的方法，首页(first)，前一页(previous)，后一页(next)，尾页(last)，跳到指定页(no)。
	 * @param _currentPage
	 *            当前页号, 如果当前页号为空，表示为首次查询该页
	 */
	public Pager(String _pagerMethod, int _currentPage) {

		this.pagerMethod = _pagerMethod;
		this.currentPage = _currentPage;

	}

	/**
	 * 在没有HttpServletRequest,HttpServletResponse的时候用 指定每页显示行数 Contructor.
	 * 
	 * @param _pagerMethod
	 *            获取当前执行的方法，首页(first)，前一页(previous)，后一页(next)，尾页(last)，跳到指定页(no)。
	 * @param _currentPage
	 *            当前页号, 如果当前页号为空，表示为首次查询该页
	 * @param pageRecorders
	 *            每页显示的行数
	 */
	public Pager(String _pagerMethod, int _currentPage, int pageRecorders) {
		this.pagerMethod = _pagerMethod;
		this.currentPage = _currentPage;
		this.pageRecorders = pageRecorders;
	}

	/**
	 * 初始化
	 * 
	 * @param _totalRows
	 *            总页数
	 * @param _pageRecorders
	 *            每页显示的行数
	 * 
	 */
	public void init(int _totalRows) {
		pFilter.add("getPage");
		pFilter.add("thispage");
		pFilter.add("currentPage");
		pFilter.add("User-Agent");
		pFilter.add("x-up-calling-line-id");

		totalRows = _totalRows;
		totalPages = totalRows / pageRecorders;
		int mod = totalRows % pageRecorders;
		if (mod > 0) {
			totalPages++;
		}
		// currentPage = 1;
		if (this.isManualPage) {
			if (startRow < 0) {
				startRow = 0;
				hasPreviousPage = false;
			} else
				hasPreviousPage = true;

			if (startRow + 2 >= totalRows) {
				// startRow = totalRows;
				hasNextPage = false;
			} else {
				hasNextPage = true;
			}

			return;
		}
		if (currentPage < 1)
			currentPage = 1;
		if (currentPage >= totalPages) {
			currentPage = totalPages;
			hasNextPage = false;
		} else {
			hasNextPage = true;
		}

		startRow = 0;

		// 获取当前执行的方法，首页，前一页，后一页，尾页，跳到指定页。
		if (pagerMethod != null) {
			if (pagerMethod.equals("first")) {
				first();

			} else if (pagerMethod.equals("previous")) {
				System.out.println("previous");
				previous();
			} else if (pagerMethod.equals("next")) {
				System.out.println("next");
				next();
			} else if (pagerMethod.equals("last")) {
				System.out.println("last");
				last();

			} else {
				System.out.println("no");
				no();
			}
		} else {
			// System.out.println("nodd");
			no();
		}
	}

	public int getStartRow() {
		return startRow;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setpageRecorders(int pageRecorders) {
		this.pageRecorders = pageRecorders;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void first() {
		currentPage = 1;
		startRow = 0;
	}

	public void previous() {
		if (currentPage == 1) {
			return;
		}

		currentPage--;
		startRow = (currentPage - 1) * pageRecorders;
		if (currentPage >= totalPages) {
			hasNextPage = false;
		} else {
			hasNextPage = true;
		}
		if ((currentPage - 1) > 0) {
			hasPreviousPage = true;
		} else {
			hasPreviousPage = false;
		}
	}

	public void next() {
		if (currentPage < totalPages) {
			currentPage++;
		}
		if (currentPage > totalPages) {
			currentPage = totalPages;

			// return;
		}
		startRow = (currentPage - 1) * pageRecorders;
		if (currentPage >= totalPages) {
			hasNextPage = false;
		} else {
			hasNextPage = true;
		}
		if ((currentPage - 1) > 0) {
			hasPreviousPage = true;
		} else {
			hasPreviousPage = false;
		}
	}
    //最后一页
	public void last() {
		currentPage = totalPages;
		startRow = (currentPage - 1) * pageRecorders;
		if (currentPage >= totalPages) {
			hasNextPage = false;
		} else {
			hasNextPage = true;
		}
		if ((currentPage - 1) > 0) {
			hasPreviousPage = true;
		} else {
			hasPreviousPage = false;
		}
	}

	public void no() {
		if (currentPage > totalPages) {
			currentPage = totalPages;
			startRow = (currentPage - 1) * pageRecorders;
			return;
		}
		if (currentPage < 1) {
			currentPage = 1;
			startRow = 0;
			return;
		}
		startRow = (currentPage - 1) * pageRecorders;

		if (currentPage >= totalPages) {
			hasNextPage = false;
		} else {
			hasNextPage = true;
		}
		if ((currentPage - 1) > 0) {
			hasPreviousPage = true;
		} else {
			hasPreviousPage = false;
		}
	}



	public String getUrlOfNoWap2(String type) {
		String separator = "&amp;";
		if ("web".equals(type))
			separator = "&";
		String p = getP(separator);
		if (type != null && type.equals("wap1"))
			listPageBreak = formWap1(p);
		else if (type != null && type.equals("wap2"))
			listPageBreak = formWap2(p);
		else
			listPageBreak = formWeb(p);

		return this.listPageBreak;
	}
	
	private StringBuffer sb = new StringBuffer();
	private String formWap1(String p) {
		String separator = "&amp;";
		if (this.totalPages < 1)
			return sb.toString();
		sb.append("&nbsp;共" + totalPages + "页&nbsp;&nbsp;");
		if (this.totalPages < 1)
			return sb.toString();
		StringBuffer sbs = forpager(p, separator);
		return sbs.toString();
	}

	private StringBuffer forpager(String p, String separator) {
		StringBuffer sbs = new StringBuffer();
		int tmp = currentPage;
		if (currentPage % pagesNo == 0) {
			currentPage = currentPage - pagesNo + 1;
		}

		if (currentPage % pagesNo != 1)
			currentPage = Math.abs(currentPage - currentPage % pagesNo + 1);

		if (currentPage % pagesNo == 1) {
			sb.append("<a href=\"" + p + separator + "getPage=first"
					+ separator + "thispage=1\">" + "首页</a>&nbsp;");
			if (currentPage > pagesNo) {
				sb.append("<a href=\"" + p + separator + "getPage=no"
						+ separator + "thispage=" + (currentPage - 1) + "\">"
						+ "" + (currentPage - 1) + "</a>&nbsp;");
			}
			for (int i = 0; i <= pagesNo; i++) {
				if (currentPage + i > totalPages)
					break;
				if (currentPage + i == tmp) {
					sb.append("" + (currentPage + i) + "&nbsp;");
				} else
					sb.append("<a href=\"" + p + separator + "getPage=no"
							+ separator + "thispage=" + (currentPage + i)
							+ "\">" + "" + (currentPage + i) + "</a>&nbsp;");

			}
			sb.append("<a href=\"" + p + separator + "getPage=last" + separator
					+ "thispage=" + totalPages + "\">" + "末页</a>&nbsp;");
		}
		currentPage = tmp;
		sbs.append(sb.toString());
		return sbs;

	}

	private String formWap2(String p) {
		String separator = "&amp;";

		sb.append("&nbsp;共" + totalPages + "页&nbsp;&nbsp;");
		if (this.totalPages < 1)
			return sb.toString();
		StringBuffer sbs = forpager(p, separator);
		sbs
				.append("<br/><br/><form name=\"frmgetpage\" action=\""
						+ p
						+ "&amp;getPage=no\""
						+ " method=\"post\" >"
						+ "跳转至<input type=\"text\"  name=\"thispage\" size=\"5\"/>&nbsp;&nbsp;"
						+ "<input type=\"submit\" value=\"确定\" />" + "</form>");
		return sbs.toString();
	}

	private String formWeb(String p) {
		String separator = "&";
		sb.append("总" + this.totalRows + "条&nbsp;&nbsp;&nbsp;共" + totalPages
				+ "页&nbsp;&nbsp;" + pageRecorders + "条/页&nbsp;&nbsp; ");
		if (this.totalPages < 1)
			return sb.toString();
		StringBuffer sbs = forpager(p, separator);
		sbs
				.append("<form name=\"frmgetpage\" action=\""
						+ p
						+ "&getPage=no\""
						+ " method=\"post\" >"
						+ "跳转至<input type=\"text\"  name=\"thispage\" size=\"5\"/>&nbsp;&nbsp;"
						+ "<input type=\"submit\" value=\"确定\" />" + "</form>");
		return sbs.toString();
	}

	public String getP(String separator) {
		if (URL == null || "".equals(URL))
			URL = request.getRequestURI();
		URL = response.encodeRedirectURL(URL);

		String p = URL + "?";
		p += getParameter(separator);
		return p;
	}

	/**
	 * 请求中的所有参数,以P=XX&a=XX形式返回
	 * 
	 * @param separator
	 * @return
	 */
	public String getParameter(String separator) {
		// List list = new ArrayList();
		String p = "";
		Map map = request.getParameterMap();

		boolean isAnd = false;
		for (Iterator iter = map.entrySet().iterator(); iter.hasNext();) {
			String element = (String) iter.next();
			if (pFilter.contains(element)) {
				continue;
			}

			String[] s = (String[]) map.get(element);
			for (int i = 0; i < s.length; i++) {
				try {
					if (isAnd)
						p += separator;
					p += element + "=";
					isAnd = true;
					p += URLEncoder.encode(s[i], "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}

		}
		p += param;
		return p;
	}

	public String getNextUrl(String _p) {
		// 首页
		String separator = "&amp;";
		String p = getP(separator);
		if (hasPreviousPage) {
			sb.append("<a href=\"");
			sb.append(p + _p);
			sb.append("&amp;currentPage=" + (currentPage - 1) + "");

			sb.append("\">上一条</a> &nbsp;");
		}
		if (hasNextPage) {

			sb.append("<a href=\"");
			sb.append(p);
			sb.append("&amp;currentPage=" + (currentPage + 1));
			sb.append("\" >下一条</a>");
		}
		this.listPageBreak = sb.toString();
		return sb.toString();
	}

	public void refresh(int _currentPage) {
		currentPage = _currentPage;
		if (currentPage > totalPages) {
			last();
		}
	}

	/**
	 * @return Returns the hasNextPage.
	 */
	public boolean isHasNextPage() {
		return hasNextPage;
	}

	/**
	 * @param hasNextPage
	 *            The hasNextPage to set.
	 */
	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	/**
	 * @return Returns the hasPreviousPage.
	 */
	public boolean isHasPreviousPage() {
		return hasPreviousPage;
	}

	/**
	 * @param hasPreviousPage
	 *            The hasPreviousPage to set.
	 */
	public void setHasPreviousPage(boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}

	public String getListPageBreak() {
		return listPageBreak;
	}

	public void setListPageBreak(String listPageBreak) {
		this.listPageBreak = listPageBreak;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public int getPageRecorders() {
		return pageRecorders;
	}

	public void setPageRecorders(int pageRecorders) {
		this.pageRecorders = pageRecorders;
	}

	public StringBuffer getSb() {
		return sb;
	}

	public void setSb(StringBuffer sb) {
		this.sb = sb;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String url) {
		URL = url;
	}

	public int getPagesNo() {
		return pagesNo;
	}

	public void setPagesNo(int pagesNo) {
		this.pagesNo = pagesNo;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public String getGetPage() {
		return pagerMethod;
	}

	public void setGetPage(String pagerMethod) {
		this.pagerMethod = pagerMethod;
	}

	public List getPFilter() {
		return pFilter;
	}

	public void setPFilter(List filter) {
		pFilter = filter;
	}

	public String getPagerMethod() {
		return pagerMethod;
	}

	public void setPagerMethod(String pagerMethod) {
		this.pagerMethod = pagerMethod;
	}

	public boolean isManualPage() {
		return isManualPage;
	}

	public void setManualPage(boolean isManualPage) {
		this.isManualPage = isManualPage;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}
}
