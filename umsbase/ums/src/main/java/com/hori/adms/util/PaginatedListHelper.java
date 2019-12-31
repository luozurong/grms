package com.hori.adms.util;

import java.util.List;

import org.displaytag.pagination.PaginatedList;
import org.displaytag.properties.SortOrderEnum;

import com.hori.db.support.DataGridPage;
import com.hori.db.support.Page;

/**
 * PaginatedListHelper
 * fullListSize 所有条目数目
 * pageNumber 当前所在页号
 * objectsPerPage 每页显示条数
 * list 此页所需要显示的数据
 * 
 * @author cici
 * @time 2008-12-05
 */
public class PaginatedListHelper implements PaginatedList {

	private Page page;

	private String searchId;

	private String sortCriterion;

	private SortOrderEnum sortDirection;
	
	private int pageNumber;

	public PaginatedListHelper(Page pageTemp) {
		this.page = pageTemp;
	}

	public PaginatedListHelper(Page pageTemp, String si) {
		this.page = pageTemp;
		this.searchId = si;
	}

	public PaginatedListHelper(Page pageTemp, String si, String sc) {
		this.page = pageTemp;
		this.searchId = si;
		this.sortCriterion = sc;
	}

	public PaginatedListHelper(Page pageTemp, String si, String sc, SortOrderEnum soe) {
		this.page = pageTemp;
		this.searchId = si;
		this.sortCriterion = sc;
		this.sortDirection = soe;
	}

	public int getFullListSize() {
		// TODO Auto-generated method stub
		return (int) page.getTotalCount();// fullListSize;
	}

	public List getList() {
		// TODO Auto-generated method stub
		return (List) page.getResult();//list;
	}

	public int getObjectsPerPage() {
		// TODO Auto-generated method stub
		return page.getPageSize(); //objectsPerPage;
	}

	public int getPageNumber() {
		// TODO Auto-generated method stub
		return (int) page.getCurrentPageNo();//pageNumber;
	}

	public String getSearchId() {
		// TODO Auto-generated method stub
		return searchId;
	}

	public String getSortCriterion() {
		// TODO Auto-generated method stub
		return sortCriterion;
	}

	public SortOrderEnum getSortDirection() {
		// TODO Auto-generated method stub
		return sortDirection;
	}

	public void setSearchId(String searchId) {
		this.searchId = searchId;
	}

	public void setSortCriterion(String sortCriterion) {
		this.sortCriterion = sortCriterion;
	}

	public void setSortDirection(SortOrderEnum sortDirection) {
		this.sortDirection = sortDirection;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
	
}
