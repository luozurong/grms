package com.hori.vo;

public class MaterialVo {
	private String id;
    /**
     * 素材下载路径
     */
	private String sourceUrl;
	/**
	 * 素材类型
	 */
	private Integer category;
	/**
	 * 时长
	 */
	private Integer duration;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSourceUrl() {
		return sourceUrl;
	}
	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}
	public Integer getCategory() {
		return category;
	}
	public void setCategory(Integer category) {
		this.category = category;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public MaterialVo(String id, String sourceUrl, Integer category, Integer duration) {
		super();
		this.id = id;
		this.sourceUrl = sourceUrl;
		this.category = category;
		this.duration = duration;
	} 
	
	public MaterialVo() {
	}
	
}
