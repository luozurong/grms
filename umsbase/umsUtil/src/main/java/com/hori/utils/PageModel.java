package com.hori.utils;
import java.util.List;

public class PageModel {
	private int pageSize = 1; //每页显示的记录数
	private long recordCount = 0; //记录总数
	private int pageCount = 1; //总页数
	private int page = 1; //当前页码
	private int recordStartNum = 0;//从哪一条记录开始取
	private int startPage = 0;//页面显示页码首个
	private int endPage = 0;//页面显示页码最后一个
	private int showPageCount=3;//页面显示几个页码，默认是3个
	private List data;
	/**
	 * 构造函数
	 * @param pageSize
	 * @param recordCount
	 * @param page
	 * @param showPageCount
	 */
	public PageModel(int pageSize,long recordCount,int page,int showPageCount){
		this.pageSize=pageSize;
		this.recordCount=recordCount;
		this.page=page;
		this.showPageCount=showPageCount;
		init();
	}
	/**
	 * 初始化
	 */
	private void init() {
		//计算总页数
		if(recordCount>0){
			if ((recordCount % pageSize) == 0) {
				pageCount = (int) recordCount / pageSize;
			} else {
				pageCount =(int) recordCount / pageSize + 1;
			}
		}
		else{
			pageCount=0;
		}
		//计算从哪一条记录开始
		recordStartNum = (page - 1) * pageSize;
		//计算页面显示的开始页和结束页
		if(pageCount<1){
			return;
		}
		int temp=showPageCount/2;
		if (pageCount <= showPageCount) {
			startPage = 1;
			endPage = pageCount;
		} else if ((page + temp) >= pageCount) {
			startPage = pageCount - showPageCount+1;
			endPage = pageCount;
		} else if((page - temp) <= 1){
			startPage = 1;
			endPage = showPageCount;
		}
		else {
			startPage = page-temp;
			endPage = page +temp;
		}
	}
	//getter and setter
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public long getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(long recordCount) {
		this.recordCount = recordCount;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRecordStartNum() {
		return recordStartNum;
	}
	public void setRecordStartNum(int recordStartNum) {
		this.recordStartNum = recordStartNum;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getShowPageCount() {
		return showPageCount;
	}
	public void setShowPageCount(int showPageCount) {
		this.showPageCount = showPageCount;
	}
	public List getData() {
		return data;
	}
	public void setData(List data) {
		this.data = data;
	}
	
}
