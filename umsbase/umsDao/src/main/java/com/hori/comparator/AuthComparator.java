package com.hori.comparator;

import java.util.Comparator;

import com.hori.model.Auth;

/**
 * Auth排序
 * 
 * @author 
 * 
 */
public class AuthComparator implements Comparator<Auth> {

	public int compare(Auth o1, Auth o2) {
		int i1 = o1.getCseq() != null ? o1.getCseq().intValue() : -1;
		int i2 = o2.getCseq() != null ? o2.getCseq().intValue() : -1;
		return i1 - i2;
	}

}
