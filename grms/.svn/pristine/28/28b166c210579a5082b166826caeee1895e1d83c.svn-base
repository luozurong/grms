package com.hori.grms.page;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * 分页参数类
 * @param <T>
 * 
 */
public class PageParameter<T> {

    public static final int DEFAULT_PAGE_SIZE = 10;

    private int pageSize;
    private int currentPage;
    private int prePage;
    private int nextPage;
    private int totalPage;
    private int totalCount;
    private String orderBy;
    private String sort;
    
    
    
    /**
	 * 当前页的数据
	 */
	private List<?> result;

	/**
	 * 获得分页内容
	 * 
	 * @return
	 */
	public List<?> getResult() {
		return result;
	}

	
	/**
	 * 设置分页内容
	 * 
	 * @param list
	 */
	public void setResult(List<?> result) {
		this.result = result;
	}


    public PageParameter() {
        this.currentPage = 1;
        this.pageSize = DEFAULT_PAGE_SIZE;
    }


	/**
     * 
     * @param currentPage
     * @param pageSize
     */
    public PageParameter(int currentPage, int pageSize) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

	public PageParameter(int pageNo,int pageSize,String orderBy,String sort){
		this.currentPage=pageNo;
		this.pageSize=pageSize;
		this.orderBy=orderBy;
		this.sort=sort;
	}
    
    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}


}
