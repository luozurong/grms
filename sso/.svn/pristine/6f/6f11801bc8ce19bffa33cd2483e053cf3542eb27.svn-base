package com.jlit.db.support;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * WebService调用，平台页面展现数据
 * 解决原Page data类型为Object类型不能传递问题
 * @author viliam
 *
 * @param <T>
 */
@SuppressWarnings("serial")
public class PageWs<T> implements Serializable{
	private static int DEFAULT_PAGE_SIZE = 20;

    /**
     * 每页的记录数
     */
    private int pageSize = DEFAULT_PAGE_SIZE;

	private long start; // 当前页第一条数据在List中的位置,从0开始

	private List<T> data; // 当前页中存放的记录,类型一般为List

	private long totalCount; // 总记录数

	/** 
	 * 构造方法，只构造空页.
	 */
	public PageWs() {
		this(0, 0, DEFAULT_PAGE_SIZE, new ArrayList());
	}

	/**
	 * 默认构造方法.
	 *
	 * @param start	 本页数据在数据库中的起始位置
	 * @param totalSize 数据库中总记录条数
	 * @param pageSize  本页容量
	 * @param data	  本页包含的数据
	 */
	public PageWs(long start, long totalSize, int pageSize, List<T> data) {
		this.pageSize = pageSize;
		this.start = start;
		this.totalCount = totalSize;
		this.data = data;
	}

	/**
	 * 取总记录数.
	 */
	public long getTotalCount() {
		return this.totalCount;
	}

	/**
	 * 取总页数.
	 */
	public long getTotalPageCount() {
		if (totalCount % pageSize == 0)
			return totalCount / pageSize;
		else
			return totalCount / pageSize + 1;
	}

	/**
	 * 取每页数据容量.
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 取当前页中的记录.
	 */
	public List<T> getResult() {
		return data;
	}

	/**
	 * 取该页当前页码,页码从1开始.
	 */
	public long getCurrentPageNo() {
		return start / pageSize + 1;
	}

	/**
	 * 该页是否有下一页.
	 */
	public boolean hasNextPage() {
		return this.getCurrentPageNo() < this.getTotalPageCount() - 1;
	}

	/**
	 * 该页是否有上一页.
	 */
	public boolean hasPreviousPage() {
		return this.getCurrentPageNo() > 1;
	}

	/**
	 * 获取任一页第一条数据在数据集的位置，每页条数使用默认值.
	 *
	 * @see #getStartOfPage(int,int)
	 */
	protected static int getStartOfPage(int pageNo) {
		return getStartOfPage(pageNo, DEFAULT_PAGE_SIZE);
	}

	/**
	 * 获取任一页第一条数据在数据集的位置.
	 *
	 * @param pageNo   从1开始的页号
	 * @param pageSize 每页记录条数
	 * @return 该页第一条数据
	 */
	public static int getStartOfPage(int pageNo, int pageSize) {
		return (pageNo - 1) * pageSize;
	}
	
	/**
	 * 根据开始记录数据，算出要取的数据在第几页；
	 * @param startIndex：开始的记录号
	 * @param pageSize:每页的记录数
	 * @return
	 * jacsen
	 * 2010-12-6
	 */
	public static int getPageNumBy_StartIndex(int startIndex,int pageSize){
		int pageNum=1;
		if(startIndex<=0 || pageSize<=0)return 0;
		
		if(startIndex % pageSize==0){
			pageNum=startIndex / pageSize;
		}else{
			pageNum=startIndex / pageSize+1;
		}
		return pageNum;
	}
	
	/**
	 * 取得分页结束记录的下标值
	 * @param totalCount：总记录数
	 * @param pageNo:：第几页
	 * @param pageSize：每页的记录数
	 * @return
	 * jacsen
	 * 2010-12-6
	 */
	public static int getEndIndex(long totalCount,int pageNo,int pageSize){
		if(totalCount<=0)return 0;
		int temp=pageNo*pageSize;
		int endIndex=(int)(totalCount>=temp?temp:totalCount);
		return endIndex;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}
	
}
