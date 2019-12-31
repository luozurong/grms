package com.hori.vo;

/**
 * 统计省份终端设备的vo类
 * 
 * @author lishen
 *
 */
public class ProvinceVo {
	/**
	 * 省名称
	 * 
	 */
	private java.lang.String name;
	/**
	 * 编号
	 * 
	 */
	private java.lang.String code;
	/**
	 * 终端数量
	 */
	private Integer terminalNums = 0;

	
	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}


	public Integer getTerminalNums() {
		return terminalNums;
	}

	public void setTerminalNums(Integer terminalNums) {
		this.terminalNums = terminalNums;
	}

	
	public java.lang.String getCode() {
		return code;
	}

	public void setCode(java.lang.String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "ProvinceVo [name=" + name + ", code=" + code + ", terminalNums=" + terminalNums + "]";
	}
    
	
}
